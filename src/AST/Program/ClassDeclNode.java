package AST.Program;

import AST.Build.Node;
import AST.Statement.VarDeclStmtNode;

import java.util.ArrayList;

public class ClassDeclNode extends Node {
    String name;
    ArrayList<VarDeclStmtNode> varDecl;
    ArrayList<FuncDeclNode> funcDecl;

    public ClassDeclNode(String name) {
        this.name = name;
        this.varDecl = new ArrayList<>();
        this.funcDecl = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<VarDeclStmtNode> getVarDecl() {
        return varDecl;
    }

    public ArrayList<FuncDeclNode> getFuncDecl() {
        return funcDecl;
    }

    public void addVarDecl(VarDeclStmtNode v) {
        varDecl.add(v);
    }

    public void addFuncDecl(FuncDeclNode f) {
        funcDecl.add(f);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", name);
        varDecl.forEach(varDecl -> varDecl.dump(indent + 4));
        funcDecl.forEach(funcDecl -> funcDecl.dump(indent + 4));
    }
}
