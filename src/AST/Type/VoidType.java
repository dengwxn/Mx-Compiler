package AST.Type;

import AST.Basic.Type;

public class VoidType extends Type {
    static final VoidType instance = new VoidType();

    private VoidType() {
    }

    static public VoidType getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t == instance;
    }
}
