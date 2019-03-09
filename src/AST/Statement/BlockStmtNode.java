package AST.Statement;

import AST.Basic.StmtNode;

import java.util.ArrayList;

public class BlockStmtNode extends StmtNode {
    ArrayList<StmtNode> stmts;

    public BlockStmtNode() {
        stmts = new ArrayList<>();
    }

    public void addStmt(StmtNode s) {
        stmts.add(s);
    }

    @Override
    public void dump(int indent) {
        stmts.forEach(s -> s.dump(indent));
    }
}
