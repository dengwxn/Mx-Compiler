package AST.Table;

import AST.Basic.Type;

import java.util.HashMap;

import static AST.Basic.Listener.addCompileError;

public class TypeTable {
    HashMap<String, Type> hashMap = new HashMap<>();

    public void put(String name, Type type) {
        if (hashMap.containsKey(name)) {
            Type t = get(name);
            addCompileError(String.format("ambiguous redefinition on type '%s'.", name));
        } else {
            hashMap.put(name, type);
        }
    }

    public Type get(String name) {
        if (hashMap.containsKey(name))
            return hashMap.get(name);
        addCompileError(String.format("type '%s' not defined.", name));
        return null;
    }
}
