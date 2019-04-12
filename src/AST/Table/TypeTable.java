package AST.Table;

import AST.Type.ArrayType;
import AST.Type.Type;

import java.util.HashMap;

import static AST.Build.Listener.addCompileError;

public class TypeTable {
    private HashMap<String, Type> hashMap = new HashMap<>();

    public void putType(String name, Type type) {
        if (hashMap.containsKey(name))
            addCompileError(String.format("ambiguous redefinition on type '%s'.", name));
        else
            hashMap.put(name, type);
    }

    private boolean isArray(String name) {
        if (name == null) return false;
        return name.indexOf('[') != -1;
    }

    private String arrayBase(String name) {
        return name.substring(0, name.indexOf('['));
    }

    private int arrayDim(String name) {
        return (name.length() - name.indexOf('[')) / 2;
    }

    public Type getType(String name) {
        if (isArray(name)) {
            Type base = getType(arrayBase(name));
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
