package AST.Expression;

import AST.Basic.ExprNode;
import AST.Type.NullType;

public class NullCstExprNode extends ExprNode {

    public NullCstExprNode() {
        type = NullType.getInstance();
    }
}
