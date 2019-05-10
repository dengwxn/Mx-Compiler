package AST.Type;

public class VoidType extends Type {
    private static VoidType instance = new VoidType();

    private VoidType() {
    }

    public static Type getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof VoidType || t == null;
    }

    @Override
    public String getTypeName() {
        return "void";
    }
}
