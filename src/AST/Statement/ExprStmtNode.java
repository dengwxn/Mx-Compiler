package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.BlockList;

public class ExprStmtNode extends StmtNode {
    private ExprNode expr;

    public ExprStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void generateIR(BlockList blockList) {
        if (expr != null) expr.generateIR(blockList);
    }

    @Override
    public void dump(int indent) {
        if (expr != null)
            expr.dump(indent);
    }
}
