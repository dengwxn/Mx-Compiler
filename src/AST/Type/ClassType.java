package AST.Type;

import AST.Basic.Type;

public class ClassType extends Type {
    String name;

    public ClassType(String n) {
        name = n;
    }

    @Override
    public String getTypeName() {
        return name;
    }
}
