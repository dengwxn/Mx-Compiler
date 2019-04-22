package IR.Build;

import AST.Program.FuncDeclNode;
import IR.Instruction.FuncCallInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import java.util.ArrayList;

public class FunctionIR {
    static public String funcName;
    static public Instruction jumpFuncEpilogue;
    private FuncDeclNode funcDecl;
    private BlockList blockList;

    FunctionIR(FuncDeclNode funcDecl) {
        blockList = new BlockList();
        this.funcDecl = funcDecl;
        funcName = funcDecl.getFuncName();
        Block funcPrologue = new Block(funcName + ".prologue");
        Block funcEntry = new Block(funcName + ".entry");
        Block funcEpilogue = new Block(funcName + ".epilogue");
        jumpFuncEpilogue = new JumpInstruction(funcEpilogue);

        // funcPrologue
        blockList.add(funcPrologue);
        blockList.add(new JumpInstruction(funcEntry));

        // funcEntry
        blockList.add(funcEntry);
        if (funcName.equals("main"))
            blockList.add(new FuncCallInstruction("_global_var_decl", new ArrayList<>()));

        // blockStatement
        funcDecl.generateIR(blockList);

        // funcExit
        blockList.add(jumpFuncEpilogue);
        blockList.add(funcEpilogue);

        // optimize
        blockList.linkPreSuc();
    }

    public void livenessAnalysis() {
        blockList.livenessAnalysis();
    }

    public String dump() {
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
        str.append("):\n");
        for (Block b : blockList.getBlockList())
            str.append(b.dump());
        str.append("\n");
        return str.toString();
    }
}
