package AST.Expression;

import AST.Basic.ExprNode;

public class SuffixExprNode extends ExprNode {
    String op;
    ExprNode expr;

    public SuffixExprNode(String o, ExprNode e) {
        op = o;
        expr = e;
    }

    @Override
    public void dump(int indent) {
        expr.dump(indent + 4);
        format(indent);
        System.out.printf("%s:\n", op);
    }
}
