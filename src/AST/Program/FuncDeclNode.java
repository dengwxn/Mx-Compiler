package AST.Program;

import AST.Basic.Node;
import AST.Statement.BlockStmtNode;

import java.util.ArrayList;

public class FuncDeclNode extends Node {
    String retType, funcName;
    ArrayList<String> paramType, paramName;
    BlockStmtNode blockStmt;

    public FuncDeclNode() {
        paramType = new ArrayList<>();
        paramName = new ArrayList<>();
    }

    public String getRetType() {
        return retType;
    }

    public ArrayList<String> getParamType() {
        return paramType;
    }

    public ArrayList<String> getParamName() {
        return paramName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String f) { funcName = f; }

    public void addType(String t) {
        if (retType == null) retType = t;
        else paramType.add(t);
    }

    public void addName(String n) {
        paramName.add(n);
    }

    public void setBlockStmt(BlockStmtNode bs) {
        blockStmt = bs;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s(", retType, funcName);
        for (int i = 0; i < paramType.size(); ++i) {
            System.out.printf("%s %s", paramType.get(i), paramName.get(i));
            if (i + 1 < paramType.size()) {
                System.out.printf(", ");
            }
        }
        System.out.printf("):\n");
        if (blockStmt != null)
            blockStmt.dump(indent + 4);
    }
}
