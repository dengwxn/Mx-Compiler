package AST.Type;

abstract public class Type {
    abstract public String getTypeName();

    abstract public boolean canOperateWith(Type t);
}
