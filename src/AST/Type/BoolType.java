package AST.Type;

import AST.Basic.Type;

public class BoolType extends Type {
    static final BoolType instance = new BoolType();

    private BoolType() {}

    static public BoolType getInstance() { return instance; }

    @Override
    public boolean canOperateWith(Type t) { return t == instance; }
}
