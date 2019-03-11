package AST.Expression;

import AST.Basic.ExprNode;

public class IdentExprNode extends ExprNode {
    String ident;

    public IdentExprNode(String i) {
        ident = i;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s\n", ident);
    }
}
