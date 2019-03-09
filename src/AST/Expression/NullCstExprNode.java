package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.ExprType;

public class NullCstExprNode extends ExprNode {

    public NullCstExprNode() {
        type = new ExprType("null");
    }
}
