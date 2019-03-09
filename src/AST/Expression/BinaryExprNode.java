package AST.Expression;

import AST.Basic.ExprNode;

public class BinaryExprNode extends ExprNode {
    String op;
    ExprNode lhs, rhs;

    public BinaryExprNode(String o, ExprNode l, ExprNode r) {
        op = o;
        lhs = l;
        rhs = r;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        lhs.dump(indent + 4);
        rhs.dump(indent + 4);
    }
}
