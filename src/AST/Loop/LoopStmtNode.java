package AST.Loop;

import AST.Statement.StmtNode;
import IR.Build.Block;

abstract public class LoopStmtNode extends StmtNode {
    Block loopContinueBlock;
    Block loopBreakBlock;

    public Block getLoopContinueBlock() {
        return loopContinueBlock;
    }

    public Block getLoopBreakBlock() {
        return loopBreakBlock;
    }
}
