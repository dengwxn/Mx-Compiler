package AST.Expression;

public class StringCstExprNode extends ExprNode {
    String str;

    public StringCstExprNode(String str) {
        this.str = str;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(str);
    }
}
