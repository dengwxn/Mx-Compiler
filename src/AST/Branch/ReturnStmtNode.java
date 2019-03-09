package AST.Branch;

import AST.Basic.ExprNode;
import AST.Basic.StmtNode;

public class ReturnStmtNode extends StmtNode {
    ExprNode expr;

    public ReturnStmtNode(ExprNode e) {
        expr = e;
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
