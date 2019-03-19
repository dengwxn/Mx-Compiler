package AST.Program;

import AST.Basic.Node;

import java.util.ArrayList;

public class ProgNode extends Node {
    ArrayList<Node> decl;

    public ProgNode() {
        decl = new ArrayList<>();
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
