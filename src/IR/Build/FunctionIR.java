package IR.Build;

import AST.Program.FuncDeclNode;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import java.util.ArrayList;

public class FunctionIR {
    static public String funcName;
    static public Instruction jumpFuncExit;
    private FuncDeclNode funcDecl;
    private ArrayList<Block> block;

    FunctionIR(FuncDeclNode funcDecl) {
        this.funcDecl = funcDecl;
        Block funcEntry = new Block(funcDecl.getFuncName() + ".entry", 0);
        Block funcExit = new Block(funcDecl.getFuncName() + ".exit");
        jumpFuncExit = new JumpInstruction(funcExit);
        funcName = funcDecl.getFuncName();

        block = new ArrayList<>();

        // funcEntry
        block.add(funcEntry);

        // blockStatement
        funcDecl.generateIR(block);

        // funcExit
        funcExit.setId(block.size());
        block.add(funcExit);
        block.get(block.size() - 2).add(jumpFuncExit);
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
        for (Block b : block)
            str.append(b.dump());
        str.append("\n");
        return str.toString();
    }
}
