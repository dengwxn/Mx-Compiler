package AST.Type;

import AST.Basic.Type;

public class NullType extends Type {
    public static NullType instance = new NullType();

    private NullType() {}

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "null"; }
}
