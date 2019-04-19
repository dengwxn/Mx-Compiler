package AST.Loop;

import AST.Statement.StmtNode;
import IR.Build.Block;

abstract public class LoopStmtNode extends StmtNode {
    protected Block loopContinueBlock;
    protected Block loopBreakBlock;

    public Block getLoopContinueBlock() {
        return loopContinueBlock;
    }

    public Block getLoopBreakBlock() {
        return loopBreakBlock;
    }
}
