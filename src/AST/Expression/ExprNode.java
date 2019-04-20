package AST.Expression;

import AST.Build.Node;
import AST.Type.Type;
import IR.Operand.Operand;

public abstract class ExprNode extends Node {
    protected Type type;
    protected Operand operand;

    public Operand getOperand() {
        return operand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type t) {
        type = t;
    }

    public boolean isLeftValue() {
        return false;
    }
}
