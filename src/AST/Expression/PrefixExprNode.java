package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.ExprType;

public class PrefixExprNode extends ExprNode {
    String op;
    ExprNode expr;

    public PrefixExprNode(String o, ExprNode e) {
        op = o;
        expr = e;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        expr.dump(indent + 4);
    }
}
