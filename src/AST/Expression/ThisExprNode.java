package AST.Expression;

import AST.Basic.ExprNode;

public class ThisExprNode extends ExprNode {
    public ThisExprNode() {}

    @Override
    public boolean isLeftValue() { return true; }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("this");
    }
}
