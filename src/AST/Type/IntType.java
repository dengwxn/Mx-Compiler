package AST.Type;

import AST.Basic.Type;

public class IntType extends Type {
    public static IntType instance = new IntType();

    private IntType() {}

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "int"; }
}
