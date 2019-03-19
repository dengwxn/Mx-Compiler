package AST.Basic;

public class ExprNode extends Node {
    protected Type type;

    public Type getType() { return type; }

    public void setType(Type t) { type = t; }

    public boolean isLeftValue() { return false; }
}
