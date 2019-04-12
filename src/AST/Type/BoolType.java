package AST.Type;

public class BoolType extends Type {
    private static BoolType instance = new BoolType();

    private BoolType() {
    }

    static public Type getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof BoolType;
    }

    @Override
    public String getTypeName() {
        return "bool";
    }
}
