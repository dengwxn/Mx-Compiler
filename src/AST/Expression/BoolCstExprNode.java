package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.ExprType;

public class BoolCstExprNode extends ExprNode {
    boolean val;

    public BoolCstExprNode(boolean v) {
        val = v;
        type = new ExprType("bool");
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
