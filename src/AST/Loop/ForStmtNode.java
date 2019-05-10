package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.JumpInstruction;

import static AST.Expression.BoolCstExprNode.*;

public class ForStmtNode extends LoopStmtNode {
    private ExprNode initExpr, condExpr, incrExpr;
    private StmtNode thenStmt;

    public ForStmtNode(ExprNode initExpr, ExprNode condExpr, ExprNode incrExpr, StmtNode thenStmt) {
        this.initExpr = initExpr;
        this.condExpr = condExpr;
        this.incrExpr = incrExpr;
        this.thenStmt = thenStmt;
    }

    @Override
    public void generateIR(BlockList blockList) {
        Block forHeader = new Block("forHeader");
        Block forBlock = new Block("forBlock");
        Block forIncr = new Block("forIncr");
        Block forExit = new Block("forExit");
        loopContinueBlock = forIncr;
        loopBreakBlock = forExit;

        // current blockList
        if (initExpr != null) initExpr.generateIR(blockList);
        blockList.add(new JumpInstruction(forHeader));

        // forHeader
        blockList.add(forHeader);
        if (condExpr != null) {
            condExpr.generateIR(blockList);
            generateLogicRecur(blockList, condExpr.getOperand());
            Block trueRecur = getTrueRecur();
            Block falseRecur = getFalseRecur();
            clearLogicRecur();

            blockList.add(trueRecur);
            blockList.add(new JumpInstruction(forBlock));
            blockList.add(falseRecur);
            blockList.add(new JumpInstruction(forExit));
        } else {
            blockList.add(new JumpInstruction(forBlock));
        }

        // forBlock
        blockList.add(forBlock);
        if (thenStmt != null) thenStmt.generateIR(blockList);
        blockList.add(new JumpInstruction(forIncr));

        // forIncr
        blockList.add(forIncr);
        if (incrExpr != null) incrExpr.generateIR(blockList);
        blockList.add(new JumpInstruction(forHeader));

        // forExit
        blockList.add(forExit);
    }


    public ExprNode getCondExpr() {
        return condExpr;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("for:");
        if (initExpr != null) {
            format(indent + 4);
            System.out.println("init:");
            initExpr.dump(indent + 8);
        }
        if (condExpr != null) {
            format(indent + 4);
            System.out.println("cond:");
            condExpr.dump(indent + 8);
        }
        if (incrExpr != null) {
            format(indent + 4);
            System.out.println("incr:");
            incrExpr.dump(indent + 8);
        }
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
    }
}
