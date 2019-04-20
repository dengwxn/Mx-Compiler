package AST.Statement;

import IR.Build.BlockList;

import java.util.ArrayList;

public class BlockStmtNode extends StmtNode {
    private ArrayList<StmtNode> stmt;

    public BlockStmtNode() {
        stmt = new ArrayList<>();
    }

    @Override
    public void generateIR(BlockList blockList) {
        stmt.forEach(s -> s.generateIR(blockList));
    }

    public void addStmt(StmtNode s) {
        stmt.add(s);
    }

    @Override
    public void dump(int indent) {
        stmt.forEach(s -> s.dump(indent));
    }
}
