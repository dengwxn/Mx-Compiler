package AST.Program;

import AST.Build.Node;
import AST.Statement.BlockStmtNode;
import IR.Build.Block;

import java.util.ArrayList;

public class FuncDeclNode extends Node {
    private String retType, funcName;
    private ArrayList<String> paramType, paramName;
    private BlockStmtNode blockStmt;

    public FuncDeclNode() {
        paramType = new ArrayList<>();
        paramName = new ArrayList<>();
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        blockStmt.generateIR(block);
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

    public void setFuncName(String f) {
        funcName = f;
    }

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
                System.out.print(", ");
            }
        }
        System.out.print("):\n");
        if (blockStmt != null)
            blockStmt.dump(indent + 4);
    }
}
