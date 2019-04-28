package IR.Build;

import AST.Program.FuncDeclNode;
import IR.Instruction.BinaryInstruction;
import IR.Instruction.FuncCallInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static IR.Build.IR.formatInstr;
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

        blockList.putSpill();
        int cnt = spillPool.size();
        if (maxParamSize > 6)
            cnt += maxParamSize - 6;
        if (cnt % 2 == 0)
            ++cnt;
        int pos = cnt;
        for (VirtualRegister i : spillPool)
            spillPos.put(i, (--pos) * 8);
        blockList.getHead().add(new BinaryInstruction(SUB, "rsp", cnt * 8));
        blockList.getTail().add(new BinaryInstruction(ADD, "rsp", cnt * 8));

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
