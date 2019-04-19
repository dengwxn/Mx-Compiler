package AST.Loop;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import IR.Build.Block;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;
import IR.Operand.Immediate;

import java.util.ArrayList;

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
    public void generateIR(ArrayList<Block> block) {
        Block forHeader = new Block(funcName + ".forHeader", block.size());
        Block forBlock = new Block(funcName + ".forBlock", block.size() + 1);
        Block forIncr = new Block(funcName + ".forIncr", block.size() + 2);
        Block forExit = new Block(funcName + ".forExit", block.size() + 3);
        Instruction jumpForIncr = new JumpInstruction(forIncr);
        Instruction jumpForExit = new JumpInstruction(forExit);
        loopContinueBlock = forIncr;
        loopBreakBlock = forExit;

        // current block
        if (initExpr != null) initExpr.generateIR(block);
        Instruction jumpForHeader = new JumpInstruction(forHeader);
        block.get(block.size() - 1).add(jumpForHeader);

        // forHeader
        block.add(forHeader);
        if (condExpr != null) {
            condExpr.generateIR(block);
            Instruction cmp = new CompareInstruction(condExpr.getOperand(), new Immediate(1));
            Instruction cjumpForBlock = new CondJumpInstruction(EQ, forBlock);
            block.get(block.size() - 1).add(cmp, cjumpForBlock, jumpForExit);
        }
        else {
            Instruction jumpForBlock = new JumpInstruction(forBlock);
            block.get(block.size() - 1).add(jumpForBlock);
        }

        // forBlock
        block.add(forBlock);
        if (thenStmt != null) thenStmt.generateIR(block);
        block.get(block.size() - 1).add(jumpForIncr);

        // forIncr
        block.add(forIncr);
        if (incrExpr != null) incrExpr.generateIR(block);
        block.get(block.size() - 1).add(jumpForHeader);

        // forExit
        block.add(forExit);
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
