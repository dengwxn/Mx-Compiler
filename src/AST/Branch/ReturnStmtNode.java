package AST.Branch;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import AST.Type.Type;
import IR.Build.Block;
import IR.Build.FunctionIR;
import IR.Instruction.Instruction;
import IR.Instruction.ReturnInstruction;

import java.util.ArrayList;

import static IR.Build.FunctionIR.jumpFuncExit;

public class ReturnStmtNode extends StmtNode {
    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        if (expr != null) {
            expr.generateIR(block);
            Instruction ret = new ReturnInstruction(expr.getOperand());
            block.get(block.size() - 1).add(ret, jumpFuncExit);
        }
        else {
            block.get(block.size() - 1).add(jumpFuncExit);
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
