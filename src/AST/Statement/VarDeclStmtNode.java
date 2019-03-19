package AST.Statement;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;
import AST.Basic.Type;

public class VarDeclStmtNode extends StmtNode {
    String type, name;
    ExprNode expr;

    public VarDeclStmtNode(String t, String n, ExprNode e) {
        type = t;
        name = n;
        expr = e;
    }

    public String getType() { return type; }

    public String getName() { return name; }

    public ExprNode getExpr() { return expr; }

    public Type getExprType() { return expr == null ? null : expr.getType(); }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s:\n", type, name);
        if (expr != null)
            expr.dump(indent + 4);
    }
}
