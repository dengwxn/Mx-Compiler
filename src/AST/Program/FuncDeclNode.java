package AST.Program;

import AST.Basic.Node;
import AST.Statement.BlockStmtNode;

import java.util.ArrayList;

public class FuncDeclNode extends Node {
    String retTypeLit, funcName;
    ArrayList<String> typeLit, name;
    BlockStmtNode blockStmt;

    public FuncDeclNode() {
        typeLit = new ArrayList<>();
        name = new ArrayList<>();
    }

    public String getRetTypeLit() {
        return retTypeLit;
    }

    public ArrayList<String> getTypeLit() {
        return typeLit;
    }

    public String getFuncName() {
        return funcName;
    }

    public void addTypeLit(String t) {
        if (retTypeLit.equals("")) retTypeLit = t;
        else typeLit.add(t);
    }

    public void addName(String n) {
        if (funcName.equals("")) funcName = n;
        else name.add(n);
    }

    public void setBlockStmt(BlockStmtNode bs) {
        blockStmt = bs;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s(", retTypeLit, funcName);
        for (int i = 0; i < typeLit.size(); ++i) {
            System.out.printf("%s %s", typeLit.get(i), name.get(i));
            if (i + 1 < typeLit.size()) {
                System.out.printf(", ");
            }
        }
        System.out.printf("):\n");
        if (blockStmt != null)
            blockStmt.dump(indent + 4);
    }
}
