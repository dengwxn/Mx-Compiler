package AST.Build;

import IR.Build.Block;

import java.util.ArrayList;

abstract public class Node {
    abstract public void dump(int indent);

    public void generateIR(ArrayList<Block> block) {}

    protected void format(int indent) {
        for (int i = 0; i < indent; ++i)
            System.out.print(" ");
    }
}
