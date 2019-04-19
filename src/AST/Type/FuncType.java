package AST.Type;

import java.util.ArrayList;

public class FuncType extends Type {
    private String funcName;
    private Type retType;
    private ArrayList<Type> paramType;

    public FuncType(String funcName, Type retType) {
        this.funcName = funcName;
        this.retType = retType;
        this.paramType = new ArrayList<>();
    }

    public FuncType(String funcName, Type retType, ArrayList<Type> paramType) {
        this.funcName = funcName;
        this.retType = retType;
        this.paramType = paramType;
    }

    public String getFuncName() {
        return funcName;
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
