package AST.Expression;

import AST.Basic.ExprNode;
import AST.Type.IntType;

public class NumberCstExprNode extends ExprNode {
    int val;

    public NumberCstExprNode(int v) {
        val = v;
        type = IntType.getInstance();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%d\n", val);
    }
}
