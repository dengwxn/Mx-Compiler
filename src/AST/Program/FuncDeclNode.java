package AST.Program;

import AST.Basic.Node;
import AST.Statement.BlockStmtNode;

import java.util.ArrayList;

public class FuncDeclNode extends Node {
    ArrayList<String> type, name;
    BlockStmtNode blockStmt;

    public FuncDeclNode() {
        type = new ArrayList<>();
        name = new ArrayList<>();
    }

    public void addType(String t) {
        type.add(t);
    }

    public void addName(String n) {
        name.add(n);
    }

    public void setBlockStmt(BlockStmtNode bs) {
        blockStmt = bs;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s(", type.get(0), name.get(0));
        for (int i = 1; i < type.size(); ++i) {
            System.out.printf("%s %s", type.get(i), name.get(i));
            if (i + 1 < type.size()) {
                System.out.printf(", ");
            }
        }
        System.out.printf("):\n");
        if (blockStmt != null)
            blockStmt.dump(indent + 4);
    }
}
