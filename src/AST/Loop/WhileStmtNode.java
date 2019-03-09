package AST.Loop;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class WhileStmtNode extends StmtNode {
    ExprNode cond;
    StmtNode thenStmt;

    public WhileStmtNode(ExprNode c, StmtNode t) {
        cond = c;
        thenStmt = t;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("while:");
        format(indent + 4);
        System.out.println("cond:");
        cond.dump(indent + 8);
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
