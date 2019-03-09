package AST.Expression;

import AST.Basic.ExprNode;

public class AssignExprNode extends ExprNode {
    String ident;
    ExprNode expr;

    public AssignExprNode(String i, ExprNode e) {
        ident = i;
        expr = e;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("=:");
        format(indent + 4);
        System.out.println(ident);
        expr.dump(indent + 4);
    }
}
