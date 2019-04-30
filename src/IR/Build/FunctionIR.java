package IR.Build;

import AST.Program.FuncDeclNode;
import AST.Table.Symbol;
import Generator.Operand.PhysicalAddress;
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
    private FuncDeclNode funcDecl;
    private BlockList blockList;

    static private LinkedHashSet<VirtualRegister> spillPool = new LinkedHashSet<>();
    static private HashMap<VirtualRegister, Integer> spillPos = new HashMap<>();
    static private int maxParamSize;

    FunctionIR(FuncDeclNode funcDecl) {
        blockList = new BlockList();
        blockList.setFuncName(funcDecl.getFuncName());
        this.funcDecl = funcDecl;
        Block func = new Block("");
        Block funcEntry = new Block("entry");
        Block funcExit = new Block("exit");
        jumpFuncEpilogue = new JumpInstruction(funcExit);

        // func
        blockList.add(func);

        // funcEntry
        blockList.add(funcEntry);
        if (funcDecl.getFuncName().equals("main"))
            blockList.add(new FuncCallInstruction("@global_var_decl", new ArrayList<>()));

        // blockStatement
        funcDecl.generateIR(blockList);

        // funcExit
        blockList.add(funcExit);
    }

    public void livenessAnalysis() {
        blockList.getBlockList().forEach(block -> block.linkPreSuc());
        blockList.getBlockList().forEach(block -> block.livenessAnalysis());
    }

    static public int getSpillPos(VirtualRegister reg) {
        return spillPos.get(reg);
    }

    static public void addSpill(VirtualRegister reg) {
        spillPool.add(reg);
    }

    static public void setMaxParamSize(int maxParamSize) {
        FunctionIR.maxParamSize = max(FunctionIR.maxParamSize, maxParamSize);
    }

    public String toNASM() {
        spillPool.clear();
        spillPos.clear();
        maxParamSize = 0;
        ArrayList<Symbol> param = funcDecl.getParamSymbol();

        blockList.putSpill();
        param.forEach(p -> p.getOperand().putSpill());
        int cnt = spillPool.size();
        if (maxParamSize > 6)
            cnt += maxParamSize - 6;
        if (cnt % 2 == 0)
            ++cnt;
        int ptr = cnt;
        for (VirtualRegister i : spillPool)
            spillPos.put(i, (--ptr) * 8);

        Block head = blockList.getHead();
        Block tail = blockList.getTail();
        head.add(new BinaryInstruction(SUB, "rsp", cnt * 8));
        tail.add(new BinaryInstruction(ADD, "rsp", cnt * 8));

        for (int i = 0; i < param.size(); ++i) {
            Operand operand = param.get(i).getOperand();
            if (i < 6) {
                head.add(new MoveInstruction(operand, "arg" + (i + 1)));
            } else {
                Address pos = new Address("rsp", (cnt + (i - 5)) * 8);
                head.add(new MoveInstruction(operand, pos));
            }
        }

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
