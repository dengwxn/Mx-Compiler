package AST.Loop;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class ForStmtNode extends StmtNode {
    ExprNode initExpr, condExpr, incrExpr;
    StmtNode thenStmt;

    public ForStmtNode(ExprNode it, ExprNode cn, ExprNode ic, StmtNode t) {
        initExpr = it;
        condExpr = cn;
        incrExpr = ic;
        thenStmt = t;
    }

    public ExprNode getCondExpr() { return condExpr; }

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
