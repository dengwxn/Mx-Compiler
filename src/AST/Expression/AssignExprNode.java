package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.Type;

public class AssignExprNode extends ExprNode {
    ExprNode lhs, rhs;

    public AssignExprNode(ExprNode l, ExprNode r) {
        lhs = l;
        rhs = r;
    }

    public Type getLhsType() {
        return lhs == null ? null : lhs.getType();
    }

    public Type getRhsType() {
        return rhs == null ? null : rhs.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("=:");
        lhs.dump(indent + 4);
        rhs.dump(indent + 4);
    }
}
