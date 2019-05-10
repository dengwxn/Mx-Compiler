package AST.Type;

public class IntType extends Type {
    private static IntType instance = new IntType();

    private IntType() {
    }

    public static Type getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof IntType;
    }

    @Override
    public String getTypeName() {
        return "int";
    }
}
