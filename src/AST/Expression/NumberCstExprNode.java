package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.ExprType;

public class NumberCstExprNode extends ExprNode {
    int val;

    public NumberCstExprNode(int v) {
        val = v;
        type = new ExprType("int");
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%d\n", val);
    }
}
