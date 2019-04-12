package AST.Expression;

public class BoolCstExprNode extends ExprNode {
    boolean val;

    public BoolCstExprNode(boolean val) {
        this.val = val;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
