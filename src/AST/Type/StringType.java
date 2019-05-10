package AST.Type;

public class StringType extends Type {
    private static StringType instance = new StringType();

    private StringType() {
    }

    public static Type getInstance() {
        return instance;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return t instanceof StringType;
    }

    @Override
    public String getTypeName() {
        return "string";
    }
}
