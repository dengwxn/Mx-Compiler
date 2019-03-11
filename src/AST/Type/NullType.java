package AST.Type;

import AST.Basic.Type;

public class NullType extends Type {
    static final NullType instance = new NullType();

    private NullType() {}

    static public NullType getInstance() { return instance; }

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }
}
