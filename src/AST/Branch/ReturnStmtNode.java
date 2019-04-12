package AST.Branch;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import AST.Type.Type;

public class ReturnStmtNode extends StmtNode {
    ExprNode expr;

    public ReturnStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("return");
        if (expr == null) {
            System.out.println();
        } else {
            System.out.println(":");
            expr.dump(indent + 4);
        }
    }
}
