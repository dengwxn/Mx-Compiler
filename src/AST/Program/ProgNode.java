package AST.Program;

import AST.Build.Node;

import java.util.ArrayList;

public class ProgNode extends Node {
    private ArrayList<Node> decl;

    public ProgNode() {
        decl = new ArrayList<>();
    }

    public ArrayList<Node> getDecl() {
        return decl;
    }

    public void addDecl(Node d) {
        decl.add(d);
    }

    @Override
    public void dump(int indent) {
        System.out.println("program:");
        decl.forEach(decl -> decl.dump(indent + 4));
    }
}
