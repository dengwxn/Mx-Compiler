package AST.Statement;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class ExprStmtNode extends StmtNode {
    ExprNode expr;

    public ExprStmtNode(ExprNode e) {
        expr = e;
    }

    @Override
    public void dump(int indent) {
        expr.dump(indent);
    }
}
