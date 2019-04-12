package AST.Type;

public class NullType extends Type {
    private static NullType instance = new NullType();

    private NullType() {
    }

    static public Type getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof NullType || t instanceof ClassType || t instanceof ArrayType;
    }

    @Override
    public String getTypeName() {
        return "null";
    }
}
