package AST.Type;

import AST.Basic.Type;

public class StringType extends Type {
    public static StringType instance = new StringType();

    private StringType() {}

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }

    static public Type getInstance() { return instance; }

    @Override
    public String getTypeName() { return "string"; }
}
