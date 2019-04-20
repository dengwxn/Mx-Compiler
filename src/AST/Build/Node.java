package AST.Build;

import IR.Build.BlockList;

abstract public class Node {
    abstract public void dump(int indent);

    public void generateIR(BlockList blockList) {
    }

    protected void format(int indent) {
        for (int i = 0; i < indent; ++i)
            System.out.print(" ");
    }
}
