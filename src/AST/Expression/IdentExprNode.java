package AST.Expression;

import AST.Basic.ExprNode;

public class IdentExprNode extends ExprNode {
    String ident;

    public IdentExprNode(String i) {
        ident = i;
    }

    public String getIdent() { return ident; }

    @Override
    public boolean isLeftValue() { return true; }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(ident);
    }
}
