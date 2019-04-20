package AST.Table;

import AST.Type.Type;

import java.util.HashMap;

import static AST.Build.Listener.addCompileError;

public class SymbolTable {
    private SymbolTable lastScope;
    private HashMap<String, Type> typeHashMap = new HashMap<>();
    private HashMap<String, Symbol> symbolHashMap = new HashMap<>();
    private Type retType;
    private String className;

    public SymbolTable(SymbolTable lastScope) {
        this.lastScope = lastScope;
    }

    public Type getRetType() {
        if (retType != null)
            return retType;
        else if (lastScope != null)
            return lastScope.getRetType();
        return null;
    }

    public void setRetType(Type t) {
        retType = t;
    }

    public String getClassName() {
        if (className != null)
            return className;
        else if (lastScope != null)
            return lastScope.getClassName();
        return null;
    }

    public void setClassName(String c) {
        className = c;
    }

    public boolean isInClassDeclScope() {
        return className != null;
    }

    public SymbolTable getLastScope() {
        return lastScope;
    }

    public void putType(String name, Type type) {
        if (typeHashMap.containsKey(name)) {
            Type t = getType(name);
            if (name.equals(t.getTypeName()))
                addCompileError(String.format("found type '%s', illegal to be a identifier name.", name));
            else
                addCompileError(String.format("ambiguous redefinition on '%s'.", name));
        } else {
            if (type == null) {
                int d = 0;
            }
            typeHashMap.put(name, type);
        }
    }

    public Type getType(String name) {
        if (typeHashMap.containsKey(name))
            return typeHashMap.get(name);
        else if (lastScope != null)
            return lastScope.getType(name);
        addCompileError(String.format("identifier '%s' not defined.", name));
        return null;
    }

    public void putSymbol(String name) {
        symbolHashMap.put(name, new Symbol(name));
    }

    public void putSymbol(String name, String prevTypeName) {
        symbolHashMap.put(name, new Symbol(name, prevTypeName));
    }

    public Symbol getSymbol(String name) {
        if (symbolHashMap.containsKey(name))
            return symbolHashMap.get(name);
        else if (lastScope != null)
            return lastScope.getSymbol(name);
        return null;
    }
}
