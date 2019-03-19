package AST.Branch;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;
import AST.Basic.Type;

public class ReturnStmtNode extends StmtNode {
    ExprNode expr;

    public ReturnStmtNode(ExprNode e) {
        expr = e;
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
        }
        else {
            System.out.println(":");
            expr.dump(indent + 4);
        }
    }
}
