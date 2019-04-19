package AST.Branch;

import AST.Loop.LoopStmtNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import java.util.ArrayList;

public class BreakStmtNode extends StmtNode {
    private LoopStmtNode loopStmt;

    public void setLoopStmt(LoopStmtNode loopStmt) {
        this.loopStmt = loopStmt;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        Instruction jumpLoopExit = new JumpInstruction(loopStmt.getLoopBreakBlock());
        block.get(block.size() - 1).add(jumpLoopExit);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("break\n");
    }
}
