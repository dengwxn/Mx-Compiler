package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;
import IR.Operand.Immediate;

import static IR.Build.FunctionIR.funcName;
import static IR.Instruction.Operator.CompareOp.EQ;

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
        Block forHeader = new Block(funcName + ".forHeader");
        Block forBlock = new Block(funcName + ".forBlock");
        Block forIncr = new Block(funcName + ".forIncr");
        Block forExit = new Block(funcName + ".forExit");
        Instruction jumpForIncr = new JumpInstruction(forIncr);
        Instruction jumpForExit = new JumpInstruction(forExit);
        loopContinueBlock = forIncr;
        loopBreakBlock = forExit;

        // current blockList
        if (initExpr != null) initExpr.generateIR(blockList);
        Instruction jumpForHeader = new JumpInstruction(forHeader);
        blockList.add(jumpForHeader);

        // forHeader
        blockList.add(forHeader);
        if (condExpr != null) {
            condExpr.generateIR(blockList);
            Instruction cmp = new CompareInstruction(condExpr.getOperand(), 1);
            Instruction cjumpForBlock = new CondJumpInstruction(EQ, forBlock);
            blockList.add(cmp, cjumpForBlock, jumpForExit);
        } else {
            Instruction jumpForBlock = new JumpInstruction(forBlock);
            blockList.add(jumpForBlock);
        }

        // forBlock
        blockList.add(forBlock);
        if (thenStmt != null) thenStmt.generateIR(blockList);
        blockList.add(jumpForIncr);

        // forIncr
        blockList.add(forIncr);
        if (incrExpr != null) incrExpr.generateIR(blockList);
        blockList.add(jumpForHeader);

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
