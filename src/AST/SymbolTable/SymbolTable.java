package AST.SymbolTable;

import AST.Basic.Listener;
import AST.Basic.Type;

import java.util.HashMap;

public class SymbolTable {
    SymbolTable lastScope;
    HashMap<String, Type> hashMap = new HashMap<>();

    public SymbolTable(SymbolTable l) {
        lastScope = l;
    }

    public SymbolTable getLastScope() { return lastScope; }

    public void put(String name, Type type) {
        if (contain(name))
            Listener.addCompileError("ambiguous redefinition on " + name);
        else
            hashMap.put(name, type);
    }

    boolean contain(String name) {
        if (hashMap.containsKey(name))
            return true;
        else if (lastScope != null)
            return lastScope.contain(name);
        return false;
    }

    public Type get(String name) {
        if (hashMap.containsKey(name))
            return hashMap.get(name);
        else if (lastScope != null)
            return lastScope.get(name);
        Listener.addCompileError(name + " not found.");
        return null;
    }
}
