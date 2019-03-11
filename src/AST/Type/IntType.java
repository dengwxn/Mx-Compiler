package AST.Type;

import AST.Basic.Type;

public class IntType extends Type {
    static final IntType instance = new IntType();

    private IntType() {}

    static public IntType getInstance() { return instance; }

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }
}
