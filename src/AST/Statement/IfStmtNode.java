package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.Block;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;
import IR.Operand.Immediate;

import java.util.ArrayList;

import static IR.Build.FunctionIR.funcName;
import static IR.Instruction.Operator.CompareOp.EQ;

public class IfStmtNode extends StmtNode {
    private ExprNode condExpr;
    private StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode condExpr, StmtNode thenStmt, StmtNode elseStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        Block ifTrue = new Block(funcName + ".ifTrue", block.size());
        Block ifFalse = new Block(funcName + ".ifFalse", block.size() + 1);
        Block ifExit = new Block(funcName + ".ifExit", block.size() + 2);
        Instruction jumpIfExit = new JumpInstruction(ifExit);

        // current block
        condExpr.generateIR(block);
        Instruction cmp = new CompareInstruction(condExpr.getOperand(), new Immediate(1));
        Instruction cjumpIfTrue = new CondJumpInstruction(EQ, ifTrue);
        Instruction jumpIfFalse = new JumpInstruction(ifFalse);
        block.get(block.size() - 1).add(cmp, cjumpIfTrue, jumpIfFalse);

        // ifTrue
        block.add(ifTrue);
        if (thenStmt != null) thenStmt.generateIR(block);
        block.get(block.size() - 1).add(jumpIfExit);

        // ifFalse
        block.add(ifFalse);
        if (elseStmt != null) elseStmt.generateIR(block);
        block.get(block.size() - 1).add(jumpIfExit);

        // ifExit
        block.add(ifExit);
    }

    public ExprNode getCondExpr() {
        return condExpr;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("if:");
        format(indent + 4);
        System.out.println("cond:");
        condExpr.dump(indent + 8);
        format(indent + 4);
        System.out.println("then:");
        thenStmt.dump(indent + 8);
        if (elseStmt != null) {
            format(indent + 4);
            System.out.println("else:");
            elseStmt.dump(indent + 8);
        }
    }
}
