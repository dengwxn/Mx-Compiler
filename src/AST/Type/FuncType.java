package AST.Type;

import java.util.ArrayList;

public class FuncType extends Type {
    Type retType;
    ArrayList<Type> paramType;

    public FuncType(Type retType) {
        this.retType = retType;
        this.paramType = new ArrayList<>();
    }

    public FuncType(Type retType, ArrayList<Type> paramType) {
        this.retType = retType;
        this.paramType = paramType;
    }

    public Type getRetType() {
        return retType;
    }

    public ArrayList<Type> getParamType() {
        return paramType;
    }

    @Override
    public boolean canOperateWith(Type t) {
        return false;
    }

    @Override
    public String getTypeName() {
        return retType.getTypeName();
    }
}
