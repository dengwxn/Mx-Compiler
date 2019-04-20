package AST.Branch;

import AST.Loop.LoopStmtNode;
import AST.Statement.StmtNode;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

public class ContinueStmtNode extends StmtNode {
    private LoopStmtNode loopStmt;

    public void setLoopStmt(LoopStmtNode loopStmt) {
        this.loopStmt = loopStmt;
    }

    @Override
    public void generateIR(BlockList blockList) {
        Instruction jumpLoopContinue = new JumpInstruction(loopStmt.getLoopContinueBlock());
        blockList.add(jumpLoopContinue);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("continue\n");
    }
}
