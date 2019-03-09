package AST.Statement;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class VarDeclStmtNode extends StmtNode {
    String type, name;
    ExprNode expr;

    public VarDeclStmtNode(String t, String n, ExprNode e) {
        type = t;
        name = n;
        expr = e;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s:\n", type, name);
        if (expr != null)
            expr.dump(indent + 4);
    }
}
