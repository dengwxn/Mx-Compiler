package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import static IR.Instruction.Operator.CompareOp.E;

public class WhileStmtNode extends LoopStmtNode {
    private ExprNode condExpr;
    private StmtNode thenStmt;

    public WhileStmtNode(ExprNode condExpr, StmtNode thenStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
    }

    @Override
    public void generateIR(BlockList blockList) {
        Block whileHeader = new Block("whileHeader");
        Block whileBlock = new Block("whileBlock");
        Block whileExit = new Block("whileExit");
        Instruction jumpWhileExit = new JumpInstruction(whileExit);
        loopContinueBlock = whileHeader;
        loopBreakBlock = whileExit;

        // current blockList
        Instruction jumpWhileHeader = new JumpInstruction(whileHeader);
        blockList.add(jumpWhileHeader);

        // whileHeader
        blockList.add(whileHeader);
        if (condExpr != null) {
            condExpr.generateIR(blockList);
            Instruction cmp = new CompareInstruction(condExpr.getOperand(), 1);
            Instruction cjumpWhileBlock = new CondJumpInstruction(E, whileBlock);
            blockList.add(cmp, cjumpWhileBlock, jumpWhileExit);
        } else {
            Instruction jumpWhileBlock = new JumpInstruction(whileBlock);
            blockList.add(jumpWhileBlock);
        }

        // whileBlock
        blockList.add(whileBlock);
        thenStmt.generateIR(blockList);
        blockList.add(jumpWhileHeader);

        // whileExit
        blockList.add(whileExit);
    }

    public ExprNode getCondExpr() {
        return condExpr;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("while:");
        format(indent + 4);
        System.out.println("cond:");
        condExpr.dump(indent + 8);
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
