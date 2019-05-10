package AST.Type;

public class NullType extends Type {
    private static NullType instance = new NullType();

    private NullType() {
    }

    public static Type getInstance() {
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
