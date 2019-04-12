package AST.Expression;

import AST.Type.Type;

public class PrefixExprNode extends ExprNode {
    String op;
    ExprNode expr;

    public PrefixExprNode(String op, ExprNode expr) {
        this.op = op;
        this.expr = expr;
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    public String getOp() {
        return op;
    }

    public ExprNode getExpr() {
        return expr;
    }

    @Override
    public boolean isLeftValue() {
        return op.equals("++") || op.equals("--");
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        expr.dump(indent + 4);
    }
}
