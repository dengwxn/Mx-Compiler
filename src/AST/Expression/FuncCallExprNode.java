package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.Type;

import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode {
    ExprNode func;
    ArrayList<ExprNode> param;

    public void addExpr(ExprNode e) {
        if (func == null)
            func = e;
        else
            param.add(e);
    }

    public FuncCallExprNode() {
        param = new ArrayList<>();
    }

    public ArrayList<ExprNode> getParam() { return param; }

    public Type getFuncType() { return func == null ? null : func.getType(); }

    public String getFuncName() {
        if (func instanceof IdentExprNode) {
            return ((IdentExprNode) func).getIdent();
        } else if (func instanceof MemberExprNode) {
            return ((MemberExprNode) func).getRootTypeName() + "." + ((MemberExprNode) func).getIdent();
        }
        return null;
    }

    @Override
    public void dump(int indent) {
        func.dump(indent);
        format(indent + 4);
        System.out.printf("%s():\n", getFuncName());
        param.forEach(p -> p.dump(indent + 4));
    }
}
