package AST.Statement;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class IfStmtNode extends StmtNode {
    ExprNode cond;
    StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode c, StmtNode t, StmtNode e) {
        cond = c;
        thenStmt = t;
        elseStmt = e;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("if:");
        format(indent + 4);
        System.out.println("cond:");
        cond.dump(indent + 8);
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
