package IR.Build;

import AST.Program.FuncDeclNode;
import AST.Table.Symbol;
import IR.Instruction.*;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static IR.Build.IR.functionIRMap;
import static IR.Instruction.Operator.BinaryOp.ADD;
import static IR.Instruction.Operator.BinaryOp.SUB;
import static java.lang.Math.max;

public class FunctionIR {
    static public Instruction jumpFuncEpilogue;
    static public HashSet<VirtualRegister> allVirtualRegister = new HashSet<>();
    static public HashMap<VirtualRegister, VirtualRegister> copyOperandTable = new HashMap<>();
    static private LinkedHashSet<VirtualRegister> spillPool = new LinkedHashSet<>();
    static private LinkedHashSet<String> leePool = new LinkedHashSet<>();
    static private HashMap<VirtualRegister, Integer> spillPos = new HashMap<>();
    static private int maxParamSize;
    private FuncDeclNode funcDecl;
    private BlockList blockList;

    FunctionIR(FuncDeclNode funcDecl) {
        blockList = new BlockList();
        blockList.setFuncName(funcDecl.getFuncName());
        this.funcDecl = funcDecl;
        Block func = new Block("");
        Block funcEntry = new Block("entry");
        Block funcExit = new Block("exit");
        jumpFuncEpilogue = new JumpInstruction(funcExit);

        blockList.add(func);
        blockList.add(new JumpInstruction(funcEntry));
        blockList.add(funcEntry);
        if (funcDecl.getFuncName().equals("main"))
            blockList.add(new FuncCallInstruction("@global_var_decl", new ArrayList<>(), 0));
        funcDecl.generateIR(blockList);
        blockList.add(new JumpInstruction(funcExit));
        blockList.add(funcExit);
        blockList.add(new ReturnInstruction());
    }

    static public int getSpillPos(VirtualRegister reg) {
        return spillPos.get(reg);
    }

    static public void addSpill(VirtualRegister reg) {
        spillPool.add(reg);
    }

    static public void addLee(String reg) {
        leePool.add(reg);
    }

    static public void setMaxParamSize(int maxParamSize) {
        FunctionIR.maxParamSize = max(FunctionIR.maxParamSize, maxParamSize);
    }

    public void inline() {
        for (int i = 0; i < blockList.size(); ++i) {
            Block block = blockList.get(i);
            ArrayList<Instruction> instr = block.getInstr();
            for (int j = 0; j < instr.size(); ++j) {
                if (instr.get(j) instanceof FuncCallInstruction) {
                    FuncCallInstruction call = (FuncCallInstruction) instr.get(j);
                    FunctionIR func = functionIRMap.get(call.getName());
                    if (func != null && func != this && func.blockList.getInstrList().size() < 100) {
                        // remove previous move arg <- operand
                        int argCnt = call.getArgCnt();
                        ArrayList<Operand> operand = new ArrayList<>();
                        while (argCnt-- > 0) {
                            Instruction u = instr.get(--j);
                            instr.remove(j);
                            if (u instanceof MoveInstruction)
                                operand.add(0, ((MoveInstruction) u).getSrc());
                        }
                        operand.addAll(call.getParam());

                        // make copy
                        copyOperandTable.clear();
                        ArrayList<Symbol> param = func.funcDecl.getParamSymbol();
                        ArrayList<VirtualRegister> paramCopy = new ArrayList<>();
                        for (Symbol u : param) {
                            VirtualRegister v = (VirtualRegister) u.getOperand();
                            VirtualRegister w = v.makeCopy();
                            paramCopy.add(w);
                            copyOperandTable.put(v, w);
                        }

                        // get inline function
                        ArrayList<Block> inlineBlockList = copyBlockList(func.blockList.getBlockList());
                        for (Block inlineBlock : inlineBlockList)
                            blockList.add(++i, inlineBlock);
                        --i;

                        // inlineRest
                        Block inlineRest = inlineBlockList.get(inlineBlockList.size() - 1);
                        instr.remove(j);
                        for (int k = j; k < instr.size(); ) {
                            inlineRest.add(instr.get(k));
                            instr.remove(k);
                        }
                        block.clearJump();

                        // pass param
                        for (int k = 0; k < paramCopy.size(); ++k)
                            block.add(new MoveInstruction(paramCopy.get(k), operand.get(k)));
                        block.add(new JumpInstruction(inlineBlockList.get(0)));
                    }
                }
            }
        }
    }

    private ArrayList<Block> copyBlockList(ArrayList<Block> blockList) {
        ArrayList<Block> newBlockList = new ArrayList<>();
        HashMap<Block, Block> label = new HashMap<>();
        for (int i = 1; i < blockList.size(); ++i) {
            Block block = blockList.get(i);
            Block newBlock = new Block(block);
            newBlockList.add(newBlock);
            label.put(block, newBlock);
        }
        for (Block block : newBlockList) {
            ArrayList<Instruction> instr = block.getInstr();
            for (Instruction u : instr) {
                if (u instanceof Jump) {
                    Block oldDst = ((Jump) u).getDst();
                    Block newDst = label.get(oldDst);
                    if (newDst == null)
                        throw new Error("fail to copy blockList.");
                    ((Jump) u).setDst(newDst);
                    if (u instanceof JumpInstruction)
                        block.setJump((JumpInstruction) u);
                }
            }
        }
        return newBlockList;
    }

    public void propagateConstant() {
        ArrayList<Instruction> instrList = this.blockList.getInstrList();
        ArrayList<Block> blockList = this.blockList.getBlockList();
        instrList.forEach(instr -> instr.clearAnalysis());
        blockList.forEach(block -> block.linkPreSuc());
        instrList.forEach(instr -> instr.putDef());
        instrList.forEach(instr -> instr.putUse());
        for (VirtualRegister reg : allVirtualRegister) {
            instrList.forEach(instr -> instr.clearReach());
            instrList.forEach(instr -> instr.putReach(reg));
            instrList.forEach(instr -> instr.buildDefReach(reg));
        }
        for (Instruction instr : instrList) {
            if (instr instanceof MoveInstruction)
                ((MoveInstruction) instr).propagateConstant();
        }
        blockList.forEach(block -> block.foldConstant());
    }

    public void propagateCopy() {
        ArrayList<Instruction> instrList = this.blockList.getInstrList();
        ArrayList<Block> blockList = this.blockList.getBlockList();
        // due to time limit in OJ
        if (instrList.size() > 5000) return;
        instrList.forEach(instr -> instr.clearAnalysis());
        blockList.forEach(block -> block.linkPreSuc());
        instrList.forEach(instr -> instr.putDef());
        instrList.forEach(instr -> instr.putUse());
        for (VirtualRegister reg : allVirtualRegister) {
            instrList.forEach(instr -> instr.clearReach());
            instrList.forEach(instr -> instr.putReach(reg));
            instrList.forEach(instr -> instr.buildDefReach(reg));
        }
        for (VirtualRegister reg : allVirtualRegister) {
            // should not propagate precoloring registers!
            if (reg.getSymbol().isPrecolor()) continue;
            instrList.forEach(instr -> instr.clearReach());
            instrList.forEach(instr -> instr.putReach(reg));
            HashSet<Instruction> flag = new HashSet<>();
            HashSet<Instruction> clear = new HashSet<>();
            for (Instruction instr : instrList) {
                if (instr instanceof MoveInstruction) {
                    if (!flag.contains(instr))
                        ((MoveInstruction) instr).propagateCopy(reg, flag, clear);
                }
            }
            for (Instruction instr : clear)
                instr.clearReceive(reg);
        }
    }

    public void analyzeNeededness() {
        ArrayList<Instruction> instrList = this.blockList.getInstrList();
        ArrayList<Block> blockList = this.blockList.getBlockList();
        instrList.forEach(instr -> instr.clearAnalysis());
        blockList.forEach(block -> block.linkPreSuc());
        instrList.forEach(instr -> instr.putDef());
        instrList.forEach(instr -> instr.putUse());
        instrList.forEach(instr -> instr.putNec());
        instrList.forEach(instr -> instr.putNeeded());
    }

    public void eliminateBlock() {
        ArrayList<Block> blockList = this.blockList.getBlockList();
        Block start = blockList.get(0);
        ArrayList<Block> queue = new ArrayList<>();
        queue.add(start);
        HashSet<Block> visit = new HashSet<>();
        visit.add(start);
        for (int i = 0; i < queue.size(); ++i) {
            Block u = queue.get(i);
            ArrayList<Instruction> instrList = u.getInstr();
            for (Instruction instr : instrList) {
                if (instr instanceof Jump) {
                    Block dst = ((Jump) instr).getDst();
                    if (visit.add(dst))
                        queue.add(dst);
                }
            }
        }
        for (int i = 0; i < blockList.size(); ++i) {
            if (!visit.contains(blockList.get(i)))
                blockList.remove(i--);
        }
    }

    public void eliminateJump() {
        ArrayList<Block> blockList = this.blockList.getBlockList();
        for (Block block : blockList) {
            ArrayList<Instruction> instrList = block.getInstr();
            for (Instruction instr : instrList) {
                if (instr instanceof Jump) {
                    Block x = ((Jump) instr).getDst();
                    while (x.size() == 1) {
                        Instruction u = x.getInstr().get(0);
                        if (u instanceof JumpInstruction) x = ((JumpInstruction) u).getDst();
                        else if (u instanceof ReturnInstruction) break;
                        else throw new Error("invalid block.");
                    }
                    ((Jump) instr).setDst(x);
                }
            }
            for (int i = 0; i < instrList.size(); ++i) {
                if (instrList.get(i) instanceof CondJumpInstruction
                        && instrList.get(i + 1) instanceof JumpInstruction) {
                    Block x = ((CondJumpInstruction) instrList.get(i)).getDst();
                    Block y = ((JumpInstruction) instrList.get(i + 1)).getDst();
                    if (x == y) {
                        instrList.remove(i - 1);
                        instrList.remove(i - 1);
                        i -= 2;
                    }
                }
            }
        }
    }

    public void eliminateDeadCode() {
        ArrayList<Block> blockList = this.blockList.getBlockList();
        blockList.forEach(block -> block.eliminateDeadCode());
    }

    public void analyzeLiveness() {
        ArrayList<Instruction> instrList = this.blockList.getInstrList();
        ArrayList<Block> blockList = this.blockList.getBlockList();
        instrList.forEach(instr -> instr.clearAnalysis());
        blockList.forEach(block -> block.linkPreSuc());
        instrList.forEach(instr -> instr.putDef());
        instrList.forEach(instr -> instr.putUse());
        instrList.forEach(instr -> instr.putLive());
        instrList.forEach(instr -> instr.addVertex());
        instrList.forEach(instr -> instr.buildIntrfGraph());
    }

    public String dumpNeedednessAnalysis() {
        StringBuilder str = new StringBuilder();
        blockList.getBlockList().forEach(block -> str.append(block.dumpNeedednessAnalysis()));
        return str.toString();
    }

    public String dumpLivenessAnalysis() {
        StringBuilder str = new StringBuilder();
        blockList.getBlockList().forEach(block -> str.append(block.dumpLivenessAnalysis()));
        return str.toString();
    }

    public void setCalling() {
        Block head = blockList.getHead();
        ArrayList<Symbol> param = funcDecl.getParamSymbol();
        for (int i = 0; i < param.size(); ++i) {
            Operand operand = param.get(i).getOperand();
            if (i < 6) {
                head.add(i, new MoveInstruction(operand, "arg" + (i + 1)));
            } else {
                // fake address
                Address pos = new Address("rsp", -1);
                head.add(i, new MoveInstruction(operand, pos));
            }
        }
    }

    private void updateCalling(int cnt) {
        Block head = blockList.getHead();
        ArrayList<Symbol> param = funcDecl.getParamSymbol();
        for (int i = 6; i < param.size(); ++i) {
            Operand operand = param.get(i).getOperand();
            int id = -1;
            for (int j = 0; j < head.size(); ++j) {
                Instruction u = head.get(j);
                if (u instanceof MoveInstruction) {
                    if (((MoveInstruction) u).getDst() == operand) {
                        id = j;
                        break;
                    }
                }
            }
            // real address
            Address pos = new Address("rsp", (cnt + (i - 5)) * 8);
            if (id != -1)
                head.set(id, new MoveInstruction(operand, pos));
        }
        int ptr = leePool.size();
        for (int i = 14; i > 8; --i) {
            if (leePool.contains("lee" + i)) {
                Address pos = new Address("rsp", (cnt - ptr--) * 8);
                head.add(0, new MoveInstruction(pos, "lee" + i));
            }
        }
        head.add(0, new BinaryInstruction(SUB, "rsp", cnt * 8));

        Block tail = blockList.getTail();
        ptr = leePool.size();
        tail.add(0, new BinaryInstruction(ADD, "rsp", cnt * 8));
        for (int i = 14; i > 8; --i) {
            if (leePool.contains("lee" + i)) {
                Address pos = new Address("rsp", (cnt - ptr--) * 8);
                tail.add(0, new MoveInstruction("lee" + i, pos));
            }
        }
    }

    public int instrListSize() {
        return blockList.getInstrList().size();
    }

    public String toNASM() {
        leePool.clear();
        spillPool.clear();
        spillPos.clear();
        maxParamSize = 0;

        blockList.getInstrList().forEach(instr -> instr.assignPhysicalOperand());
        int cnt = leePool.size();
        cnt += spillPool.size();
        cnt += maxParamSize;
        if (cnt % 2 == 0) ++cnt;
        int ptr = cnt - leePool.size();
        for (VirtualRegister i : spillPool)
            spillPos.put(i, (--ptr) * 8);
        updateCalling(cnt);

        StringBuilder str = new StringBuilder();
        ArrayList<Block> blockList = this.blockList.getBlockList();
        for (int i = 0; i < blockList.size(); ++i) {
            Block block = blockList.get(i);
            Block nextBlock = i < blockList.size() - 1 ? blockList.get(i + 1) : null;
            str.append(block.toNASM(nextBlock));
        }
        return str.toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(funcDecl.getFuncName() + "(");
        boolean comma = false;
        for (String s : funcDecl.getParamName()) {
            if (comma)
                str.append(", " + s);
            else {
                str.append(s);
                comma = true;
            }
        }
        str.append("):\n\n");
        blockList.getBlockList().forEach(block -> str.append(block.toString()));
        str.append("\n");
        return str.toString();
    }
}
