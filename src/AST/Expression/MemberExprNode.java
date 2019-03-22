package AST.Expression;

import AST.Basic.ExprNode;
import AST.Type.ArrayType;

public class MemberExprNode extends ExprNode {
    ExprNode root;
    String ident;

    public MemberExprNode(ExprNode c, String i) {
        root = c;
        ident = i;
    }

    public String getIdent() { return ident; }

    public String getRootTypeName() {
        if (root.getType() != null) {
            if (root.getType() instanceof ArrayType)
                return "";
            else
                return root.getType().getTypeName();
        }
        else
            return null;
    }

    @Override
    public boolean isLeftValue() { return true; }

    @Override
    public void dump(int indent) {
        if (root != null) {
            root.dump(indent);
            format(indent + 4);
            System.out.printf(".%s\n", ident);
        } else {
            System.out.println(ident);
        }
    }
}
