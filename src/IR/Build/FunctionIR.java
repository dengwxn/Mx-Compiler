package IR.Build;

import AST.Program.FuncDeclNode;
import AST.Table.Symbol;
import IR.Instruction.*;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static IR.Instruction.Operator.BinaryOp.ADD;
import static IR.Instruction.Operator.BinaryOp.SUB;
import static java.lang.Math.max;

public class FunctionIR {
    static public Instruction jumpFuncEpilogue;
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
            blockList.add(new FuncCallInstruction("@global_var_decl", new ArrayList<>()));
        funcDecl.generateIR(blockList);
        blockList.add(funcExit);

        setCalling();
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

    public void livenessAnalysis() {
        blockList.getBlockList().forEach(block -> block.linkPreSuc());
        blockList.getBlockList().forEach(block -> block.livenessAnalysis());
        blockList.getBlockList().forEach(block -> block.buildIntrfGraph());
    }

    public String dumpLivenessAnalysis() {
        StringBuilder str = new StringBuilder();
        for (Block block : blockList.getBlockList()) {
            str.append(block.dumpLivenessAnalysis());
        }
        return str.toString();
    }

    private void setCalling() {
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
            // real address
            Address pos = new Address("rsp", (cnt + (i - 5)) * 8);
            // can change another way to change the address offset to support propagation
            head.set(i, new MoveInstruction(operand, pos));
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
        for (int i = 14; i > 8; --i) {
            if (leePool.contains("lee" + i)) {
                Address pos = new Address("rsp", (cnt - ptr--) * 8);
                tail.add(0, new MoveInstruction("lee" + i, pos));
            }
        }
        tail.add(new BinaryInstruction(ADD, "rsp", cnt * 8));
    }

    public String toNASM() {
        leePool.clear();
        spillPool.clear();
        spillPos.clear();
        maxParamSize = 0;

        blockList.convertVirtualOperand();
        int cnt = leePool.size();
        cnt += spillPool.size();
        cnt += maxParamSize;
        if (cnt % 2 == 0) ++cnt;
        int ptr = cnt - leePool.size();
        for (VirtualRegister i : spillPool)
            spillPos.put(i, (--ptr) * 8);

        updateCalling(cnt);
        return blockList.toNASM();
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
        str.append(blockList.toString());
        str.append("\n");
        return str.toString();
    }
}
