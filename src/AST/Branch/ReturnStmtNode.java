package AST.Branch;

import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;

import static IR.Build.FunctionIR.jumpFuncEpilogue;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class ReturnStmtNode extends StmtNode {
    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void generateIR(BlockList blockList) {
        if (expr != null) {
            expr.generateIR(blockList);
            Instruction mov = new MoveInstruction("res0", expr.getOperand());
            blockList.add(mov, jumpFuncEpilogue);
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
