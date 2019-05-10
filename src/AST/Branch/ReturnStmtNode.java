package AST.Branch;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import AST.Type.Type;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.MoveInstruction;

import static AST.Expression.BoolCstExprNode.*;
import static IR.Build.FunctionIR.jumpFuncEpilogue;

public class ReturnStmtNode extends StmtNode {
    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void generateIR(BlockList blockList) {
        if (expr != null) {
            expr.generateIR(blockList);
            if (haveLogicRecur()) {
                Block trueRecur = getTrueRecur();
                Block falseRecur = getFalseRecur();
                clearLogicRecur();

                blockList.add(trueRecur);
                blockList.add(new MoveInstruction("res0", 1));
                blockList.add(jumpFuncEpilogue);

                blockList.add(falseRecur);
                blockList.add(new MoveInstruction("res0", 0));
                blockList.add(jumpFuncEpilogue);
            } else {
                blockList.add(new MoveInstruction("res0", expr.getOperand()));
                blockList.add(jumpFuncEpilogue);
            }
        } else {
            blockList.add(jumpFuncEpilogue);
        }
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.print("return");
        if (expr == null) {
            System.out.println();
        } else {
            System.out.println(":");
            expr.dump(indent + 4);
        }
    }
}
