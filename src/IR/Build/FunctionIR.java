package IR.Build;

import AST.Program.FuncDeclNode;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

public class FunctionIR {
    static public String funcName;
    static public Instruction jumpFuncExit;
    private FuncDeclNode funcDecl;
    private BlockList blockList;

    FunctionIR(FuncDeclNode funcDecl) {
        blockList = new BlockList();
        this.funcDecl = funcDecl;
        Block funcEntry = new Block(funcDecl.getFuncName() + ".entry");
        Block funcExit = new Block(funcDecl.getFuncName() + ".exit");
        jumpFuncExit = new JumpInstruction(funcExit);
        funcName = funcDecl.getFuncName();

        // funcEntry
        blockList.add(funcEntry);

        // blockStatement
        funcDecl.generateIR(blockList);

        // funcExit
        blockList.add(jumpFuncExit);
        blockList.add(funcExit);
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
