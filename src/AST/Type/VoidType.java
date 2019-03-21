package AST.Type;

import AST.Basic.Type;

public class VoidType extends Type {
    public static VoidType instance = new VoidType();

    private VoidType() {}

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "void"; }
}
