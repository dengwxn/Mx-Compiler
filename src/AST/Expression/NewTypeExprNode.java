package AST.Expression;

public class NewTypeExprNode extends ExprNode {
    String base;

    public NewTypeExprNode(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("new %s\n", getType().getTypeName());
    }
}
