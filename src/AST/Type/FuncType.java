package AST.Type;

import AST.Basic.Type;

import java.util.ArrayList;

public class FuncType extends Type {
    Type retType;
    ArrayList<Type> type;

    public FuncType(Type r, ArrayList<Type> t) {
        retType = r;
        type = t;
    }

    @Override
    public boolean canOperateWith(Type t) { return t == retType; }
}
