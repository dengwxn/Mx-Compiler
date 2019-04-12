package AST.Statement;

import IR.Build.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockStmtNode extends StmtNode {
    private ArrayList<StmtNode> stmt;

    public BlockStmtNode() {
        stmt = new ArrayList<>();
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        stmt.forEach(s -> s.generateIR(block));
    }

    public void addStmt(StmtNode s) {
        stmt.add(s);
    }

    @Override
    public void dump(int indent) {
        stmt.forEach(s -> s.dump(indent));
    }
}
