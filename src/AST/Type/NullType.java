package AST.Type;

import AST.Basic.Type;

public class NullType extends Type {
    public static NullType instance = new NullType();

    private NullType() {}

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof NullType || t instanceof ClassType || t instanceof ArrayType;
    }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "null"; }
}
