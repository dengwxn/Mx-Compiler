package AST.Type;

import AST.Basic.Type;

public class BoolType extends Type {
    public static BoolType instance = new BoolType();

    private BoolType() {}

    @Override
    public boolean canOperateWith(Type t) { return t instanceof BoolType; }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "bool"; }
}
