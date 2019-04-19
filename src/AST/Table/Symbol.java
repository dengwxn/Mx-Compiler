package AST.Table;

public class Symbol {
    private String name, prevTypeName;

    public Symbol(String name) {
        this.name = name;
    }

    Symbol(String name, String prevTypeName) {
        this.name = name;
        this.prevTypeName = prevTypeName;
    }

    public String getPrevTypeName() {
        return prevTypeName;
    }

    public boolean isInClassDeclScope() {
        return prevTypeName != null;
    }

    public String getName() {
        return name;
    }
}
