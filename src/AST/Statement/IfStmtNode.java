package AST.Statement;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class IfStmtNode extends StmtNode {
    ExprNode condExpr;
    StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode c, StmtNode t, StmtNode e) {
        condExpr = c;
        thenStmt = t;
        elseStmt = e;
    }

    public ExprNode getCondExpr() { return condExpr; }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("if:");
        format(indent + 4);
        System.out.println("cond:");
        condExpr.dump(indent + 8);
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
        if (elseStmt != null) {
            format(indent + 4);
            System.out.println("else:");
            elseStmt.dump(indent + 8);
        }
    }
}
