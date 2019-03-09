package AST.Loop;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class ForStmtNode extends StmtNode {
    ExprNode init, cond, incr;
    StmtNode thenStmt;

    public ForStmtNode(ExprNode it, ExprNode cn, ExprNode ic, StmtNode t) {
        init = it;
        cond = cn;
        incr = ic;
        thenStmt = t;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("for:");
        if (init != null) {
            format(indent + 4);
            System.out.println("init:");
            init.dump(indent + 8);
        }
        if (cond != null) {
            format(indent + 4);
            System.out.println("cond:");
            cond.dump(indent + 8);
        }
        if (incr != null) {
            format(indent + 4);
            System.out.println("incr:");
            incr.dump(indent + 8);
        }
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
