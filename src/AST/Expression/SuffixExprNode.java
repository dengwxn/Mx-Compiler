package AST.Expression;

import AST.Type.Type;

public class SuffixExprNode extends ExprNode {
    String op;
    ExprNode expr;

    public SuffixExprNode(String op, ExprNode expr) {
        this.op = op;
        this.expr = expr;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    @Override
    public void dump(int indent) {
        expr.dump(indent + 4);
        format(indent);
        System.out.printf("%s:\n", op);
    }
}
