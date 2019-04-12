package AST.Type;

public class ClassType extends Type {
    String name;

    public ClassType(String name) {
        this.name = name;
    }

    @Override
    public boolean canOperateWith(Type t) {
        if (t instanceof NullType)
            return true;
        else if (t instanceof ClassType)
            return t.getTypeName().equals(name);
        return false;
    }

    @Override
    public String getTypeName() {
        return name;
    }
}
