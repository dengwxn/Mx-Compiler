package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import static IR.Instruction.Operator.CompareOp.E;

public class IfStmtNode extends StmtNode {
    private ExprNode condExpr;
    private StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode condExpr, StmtNode thenStmt, StmtNode elseStmt) {
        this.condExpr = condExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void generateIR(BlockList blockList) {
        Block ifTrue = new Block("ifTrue");
        Block ifFalse = new Block("ifFalse");
        Block ifExit = new Block("ifExit");
        Instruction jumpIfExit = new JumpInstruction(ifExit);

        // current blockList
        condExpr.generateIR(blockList);
        Instruction cmp = new CompareInstruction(condExpr.getOperand(), 1);
        Instruction cjumpIfTrue = new CondJumpInstruction(E, ifTrue);
        Instruction jumpIfFalse = new JumpInstruction(ifFalse);
        blockList.add(cmp, cjumpIfTrue, jumpIfFalse);

        // ifTrue
        blockList.add(ifTrue);
        if (thenStmt != null) thenStmt.generateIR(blockList);
        blockList.add(jumpIfExit);

        // ifFalse
        blockList.add(ifFalse);
        if (elseStmt != null) elseStmt.generateIR(blockList);
        blockList.add(jumpIfExit);

        // ifExit
        blockList.add(ifExit);
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
