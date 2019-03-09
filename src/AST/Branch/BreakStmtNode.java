package AST.Branch;

import AST.Basic.StmtNode;

public class BreakStmtNode extends StmtNode {
    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("break\n");
    }
}
