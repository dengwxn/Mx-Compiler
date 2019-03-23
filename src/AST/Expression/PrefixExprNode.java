package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.Type;

public class PrefixExprNode extends ExprNode {
    String op;
    ExprNode expr;

    public PrefixExprNode(String o, ExprNode e) {
        op = o;
        expr = e;
    }

    public Type getExprType() { return expr == null ? null : expr.getType(); }

    public String getOp() { return op; }

    public ExprNode getExpr() { return expr; }

    @Override
    public boolean isLeftValue() { return op.equals("++") || op.equals("--"); }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        expr.dump(indent + 4);
    }
}
