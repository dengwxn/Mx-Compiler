package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.Block;

import java.util.ArrayList;

public class ExprStmtNode extends StmtNode {
    private ExprNode expr;

    public ExprStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        if (expr != null) expr.generateIR(block);
    }

    @Override
    public void dump(int indent) {
        if (expr != null)
            expr.dump(indent);
    }
}
