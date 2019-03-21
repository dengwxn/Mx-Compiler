package AST.SymbolTable;

import AST.Basic.Type;
import AST.Type.ArrayType;

import java.util.HashMap;

import static AST.Basic.Listener.addCompileError;

public class SymbolTable {
    SymbolTable lastScope;
    HashMap<String, Type> hashMap = new HashMap<>();
    Type retType;
    String className;

    public SymbolTable(SymbolTable l) {
        lastScope = l;
    }

    public Type getRetType() {
        if (retType != null)
            return retType;
        else if (lastScope != null)
            return lastScope.getRetType();
        return null;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String c) {
        className = c;
    }

    public void setRetType(Type t) {
        retType = t;
    }

    public SymbolTable getLastScope() {
        return lastScope;
    }

    public void put(String name, Type type) {
        if (hashMap.containsKey(name)) {
            Type t = get(name);
            if (name.equals(t.getTypeName()))
                addCompileError(String.format("found type '%s', illegal to be a identifier name.", name));
            else
                addCompileError(String.format("ambiguous redefinition on '%s'.", name));
        } else {
            hashMap.put(name, type);
        }
    }

    boolean isArray(String name) {
        return name.indexOf('[') != -1;
    }

    String arrayBase(String name) {
        return name.substring(0, name.indexOf('['));
    }

    int arrayDim(String name) {
        return (name.length() - name.indexOf('[')) / 2;
    }

    public Type get(String name) {
        if (isArray(name)) {
            Type base = get(arrayBase(name));
            int dim = arrayDim(name);
            return new ArrayType(base, dim);
        } else {
            if (hashMap.containsKey(name))
                return hashMap.get(name);
            else if (lastScope != null)
                return lastScope.get(name);
            addCompileError(String.format("identifier '%s' not defined.", name));
            return null;
        }
    }
}
