package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;

public class ForStmtNode extends StmtNode {
    ExprNode initExpr, condExpr, incrExpr;
    StmtNode thenStmt;

    public ForStmtNode(ExprNode initExpr, ExprNode condExpr, ExprNode incrExpr, StmtNode thenStmt) {
        this.initExpr = initExpr;
        this.condExpr = condExpr;
        this.incrExpr = incrExpr;
        this.thenStmt = thenStmt;
    }

    public ExprNode getCondExpr() {
        return condExpr;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("for:");
        if (initExpr != null) {
            format(indent + 4);
            System.out.println("init:");
            initExpr.dump(indent + 8);
        }
        if (condExpr != null) {
            format(indent + 4);
            System.out.println("cond:");
            condExpr.dump(indent + 8);
        }
        if (incrExpr != null) {
            format(indent + 4);
            System.out.println("incr:");
            incrExpr.dump(indent + 8);
        }
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
