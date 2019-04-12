package AST.Branch;

import AST.Statement.StmtNode;

public class BreakStmtNode extends StmtNode {
    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("break\n");
    }
}
