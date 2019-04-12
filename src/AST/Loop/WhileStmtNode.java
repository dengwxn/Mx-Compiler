package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;

public class WhileStmtNode extends StmtNode {
    ExprNode condExpr;
    StmtNode thenStmt;

    public WhileStmtNode(ExprNode condExpr, StmtNode thenStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
    }

    public ExprNode getCondExpr() {
        return condExpr;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("while:");
        format(indent + 4);
        System.out.println("cond:");
        condExpr.dump(indent + 8);
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
