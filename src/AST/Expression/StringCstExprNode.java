package AST.Expression;

import AST.Basic.ExprNode;

public class StringCstExprNode extends ExprNode {
    String str;

    public StringCstExprNode(String s) {
        str = s;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(str);
    }
}
