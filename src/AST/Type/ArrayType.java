package AST.Type;

public class ArrayType extends Type {
    Type base;
    int dim;

    public ArrayType(Type base, int dim) {
        this.base = base;
        this.dim = dim;
    }

    public Type getBase() {
        return base;
    }

    public int getDim() {
        return dim;
    }

    @Override
    public boolean canOperateWith(Type t) {
        if (t instanceof NullType)
            return true;
        if (t instanceof ArrayType)
            return ((ArrayType) t).base.canOperateWith(base) && ((ArrayType) t).dim == dim;
        return false;
    }

    private String repeat(String s, int d) {
        String t = "";
        for (int i = 0; i < d; ++i)
            t = t.concat(s);
        return t;
    }

    @Override
    public String getTypeName() {
        return base.getTypeName() + repeat("[]", dim);
    }
}
