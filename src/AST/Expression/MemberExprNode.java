package AST.Expression;

import AST.Basic.ExprNode;

public class MemberExprNode extends ExprNode {
    ExprNode cls;
    String ident;

    public MemberExprNode(ExprNode c, String i) {
        cls = c;
        ident = i;
    }

    public String getIdent() { return ident; }

    public String getClsName() {
        if (cls.getType() != null)
            return cls.getType().getTypeName();
        else
            return null;
    }

    @Override
    public boolean isLeftValue() { return true; }

    @Override
    public void dump(int indent) {
        if (cls != null) {
            cls.dump(indent);
            format(indent + 4);
            System.out.printf(".%s\n", ident);
        } else {
            System.out.println(ident);
        }
    }
}
