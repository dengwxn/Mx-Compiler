package AST.Table;

import AST.Basic.Type;
import AST.Type.ArrayType;

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

    boolean isArray(String name) {
        if (name == null) return false;
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
            addCompileError(String.format("identifier '%s' not defined.", name));
            return null;
        }
    }
}
