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

public class WhileStmtNode extends LoopStmtNode {
    private ExprNode condExpr;
    private StmtNode thenStmt;

    public WhileStmtNode(ExprNode condExpr, StmtNode thenStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        Block whileHeader = new Block(funcName + ".whileHeader", block.size());
        Block whileBlock = new Block(funcName + ".whileBlock", block.size() + 1);
        Block whileExit = new Block(funcName + ".whileExit", block.size() + 2);
        Instruction jumpWhileExit = new JumpInstruction(whileExit);
        loopContinueBlock = whileHeader;
        loopBreakBlock = whileExit;

        // current block
        Instruction jumpWhileHeader = new JumpInstruction(whileHeader);
        block.get(block.size() - 1).add(jumpWhileHeader);

        // whileHeader
        block.add(whileHeader);
        if (condExpr != null) {
            condExpr.generateIR(block);
            Instruction cmp = new CompareInstruction(condExpr.getOperand(), new Immediate(1));
            Instruction cjumpWhileBlock = new CondJumpInstruction(EQ, whileBlock);
            block.get(block.size() - 1).add(cmp, cjumpWhileBlock, jumpWhileExit);
        }
        else {
            Instruction jumpWhileBlock = new JumpInstruction(whileBlock);
            block.get(block.size() - 1).add(jumpWhileBlock);
        }

        // whileBlock
        block.add(whileBlock);
        thenStmt.generateIR(block);
        block.get(block.size() - 1).add(jumpWhileHeader);

        // whileExit
        block.add(whileExit);
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
