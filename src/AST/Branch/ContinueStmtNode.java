package AST.Branch;

import AST.Basic.StmtNode;

public class ContinueStmtNode extends StmtNode {
    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("continue\n");
    }
}
