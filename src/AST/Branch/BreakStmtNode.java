package AST.Branch;

import AST.Loop.LoopStmtNode;
import AST.Statement.StmtNode;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

public class BreakStmtNode extends StmtNode {
    private LoopStmtNode loopStmt;

    public void setLoopStmt(LoopStmtNode loopStmt) {
        this.loopStmt = loopStmt;
    }

    @Override
    public void generateIR(BlockList blockList) {
        Instruction jumpLoopExit = new JumpInstruction(loopStmt.getLoopBreakBlock());
        blockList.add(jumpLoopExit);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("break\n");
    }
}
