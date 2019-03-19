package AST.Expression;

import AST.Basic.ExprNode;
import AST.Type.BoolType;

public class BoolCstExprNode extends ExprNode {
    boolean val;

    public BoolCstExprNode(boolean v) {
        val = v;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
