package AST.Basic;

public class Node {
    public void dump(int indent) {}

    protected void format(int indent) {
        for (int i = 0; i < indent; ++i)
            System.out.print(" ");
    }
}
