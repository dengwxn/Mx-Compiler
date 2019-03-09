package AST.Program;

import AST.Basic.Node;

import java.util.ArrayList;

public class ProgNode extends Node {
    ArrayList<Node> decls;

    public ProgNode() {
        decls = new ArrayList<>();
    }

    public void addDecl(Node d) {
        decls.add(d);
    }

    public void typeCheck() {

    }

    @Override
    public void dump(int indent) {
        System.out.println("program:");
        decls.forEach(decl -> decl.dump(indent + 4));
    }
}
