package AST.Type;

import AST.Basic.Type;

public class ArrayType extends Type {
    Type base;
    int dim;

    public ArrayType(Type b, int d) {
        base = b;
        dim = d;
    }

    public Type getBase() { return base; }

    public int getDim() { return dim; }

    @Override
    public boolean canOperateWith(Type t) {
        if (t == NullType.getInstance())
            return true;
        if (t instanceof ArrayType)
            return ((ArrayType) t).base.canOperateWith(base) && ((ArrayType) t).dim == dim;
        return false;
    }

    String repeat(String s, int d) {
        String t = "";
        for (int i = 0; i < d; ++i)
            t = t.concat(s);
        return t;
    }

    @Override
    public String getTypeName() { return base.getTypeName() + repeat("[]", dim); }
}
