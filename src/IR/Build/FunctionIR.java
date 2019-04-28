package IR.Build;

import AST.Program.FuncDeclNode;
import IR.Instruction.FuncCallInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import java.util.ArrayList;

public class FunctionIR {
    static public Instruction jumpFuncEpilogue;
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

        // linkPreSuc
        blockList.getBlockList().forEach(block -> block.linkPreSuc());
    }

    public void livenessAnalysis() {
        blockList.getBlockList().forEach(block -> block.livenessAnalysis());
    }

    String toNASM() {
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
