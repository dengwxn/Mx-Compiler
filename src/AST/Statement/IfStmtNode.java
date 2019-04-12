package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.Block;

import java.util.ArrayList;
import java.util.List;

public class IfStmtNode extends StmtNode {
    private ExprNode condExpr;
    private StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode condExpr, StmtNode thenStmt, StmtNode elseStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {}

    public ExprNode getCondExpr() {
        return condExpr;
    }

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
