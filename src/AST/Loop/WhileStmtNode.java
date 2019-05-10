package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.JumpInstruction;

import static AST.Expression.BoolCstExprNode.*;

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
        loopContinueBlock = whileHeader;
        loopBreakBlock = whileExit;

        // current blockList
        blockList.add(new JumpInstruction(whileHeader));

        // whileHeader
        blockList.add(whileHeader);
        if (condExpr != null) {
            condExpr.generateIR(blockList);
            generateLogicRecur(blockList, condExpr.getOperand());
            Block trueRecur = getTrueRecur();
            Block falseRecur = getFalseRecur();
            clearLogicRecur();

            blockList.add(trueRecur);
            blockList.add(new JumpInstruction(whileBlock));
            blockList.add(falseRecur);
            blockList.add(new JumpInstruction(whileExit));
        } else {
            blockList.add(new JumpInstruction(whileBlock));
        }

        // whileBlock
        blockList.add(whileBlock);
        thenStmt.generateIR(blockList);
        blockList.add(new JumpInstruction(whileHeader));

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
