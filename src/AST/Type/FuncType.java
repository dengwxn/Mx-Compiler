package AST.Type;

import AST.Basic.Type;

import java.util.ArrayList;

public class FuncType extends Type {
    Type retType;
    ArrayList<Type> paramType;

    public FuncType(Type r) {
        retType = r;
        paramType = new ArrayList<>();
    }

    public FuncType(Type r, ArrayList<Type> t) {
        retType = r;
        paramType = t;
    }

    public Type getRetType() { return retType; }
    
    public ArrayList<Type> getParamType() { return paramType; }

    @Override
    public boolean canOperateWith(Type t) { return false; }

    @Override
    public String getTypeName() { return retType.getTypeName(); }
}
