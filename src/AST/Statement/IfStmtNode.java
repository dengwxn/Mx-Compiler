package AST.Statement;

import AST.Expression.ExprNode;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.JumpInstruction;

import static AST.Expression.BoolCstExprNode.*;

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
        Block ifExit = new Block("ifExit");
        // current blockList
        condExpr.generateIR(blockList);
        if (!hasLogicRecur())
            generateLogicRecur(blockList, condExpr.getOperand());
        Block trueRecur = getTrueRecur();
        Block falseRecur = getFalseRecur();
        clearLogicRecur();

        // ifTrue
        blockList.add(trueRecur);
        if (thenStmt != null) thenStmt.generateIR(blockList);
        blockList.add(new JumpInstruction(ifExit));

        // ifFalse
        blockList.add(falseRecur);
        if (elseStmt != null) elseStmt.generateIR(blockList);
        blockList.add(new JumpInstruction(ifExit));

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
