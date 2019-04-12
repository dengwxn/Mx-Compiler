package IR.Build;

import AST.Program.FuncDeclNode;

import java.util.ArrayList;

public class FunctionIR {
    private FuncDeclNode funcDecl;
    private ArrayList<Block> block;

    FunctionIR(FuncDeclNode funcDecl) {
        this.funcDecl = funcDecl;
        block = new ArrayList<>();
        block.add(new Block(funcDecl.getFuncName() + ".entry", block.size()));
        funcDecl.generateIR(block);
        block.add(new Block(funcDecl.getFuncName() + ".exit", block.size()));
    }

    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("." + funcDecl.getFuncName() + "(");
        boolean comma = false;
        for (String s : funcDecl.getParamName()) {
            if (comma)
                str.append(" ," + s);
            else {
                str.append(s);
                comma = true;
            }
        }
        str.append("):\n");
        for (Block b : block)
            str.append(b.dump());
        return str.toString();
    }
}
