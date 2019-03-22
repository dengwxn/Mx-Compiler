package AST.Expression;

import AST.Basic.ExprNode;

public class NewTypeExprNode extends ExprNode {
    String base;

    public NewTypeExprNode(String b) { base = b; }

    public String getBase() { return base; }

    @Override
    public boolean isLeftValue() { return false; }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("new %s\n", getType().getTypeName());
    }
}
