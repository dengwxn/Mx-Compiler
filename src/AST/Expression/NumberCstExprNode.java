package AST.Expression;

import AST.Basic.ExprNode;

public class NumberCstExprNode extends ExprNode {
    int val;

    public NumberCstExprNode(int v) {
        val = v;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%d\n", val);
    }
}
