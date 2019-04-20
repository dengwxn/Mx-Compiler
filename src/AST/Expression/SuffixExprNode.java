package AST.Expression;

import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Instruction.Operator;
import IR.Instruction.UnaryInstruction;

import static IR.Instruction.Operator.UnaryOp.DEC;
import static IR.Instruction.Operator.UnaryOp.INC;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class SuffixExprNode extends ExprNode {
    private String op;
    private ExprNode expr;

    public SuffixExprNode(String op, ExprNode expr) {
        this.op = op;
        this.expr = expr;
    }

    private Operator.UnaryOp convertUnaryOp() {
        switch (op) {
            case "++":
                return INC;
            case "--":
                return DEC;
            default:
                return null;
        }
    }

    @Override
    public void generateIR(BlockList blockList) {
        expr.generateIR(blockList);
        operand = getTemporaryRegister();
        Instruction mov = new MoveInstruction(operand, expr.getOperand());
        Instruction inc = new UnaryInstruction(convertUnaryOp(), expr.getOperand());
        blockList.add(mov, inc);
    }

    public ExprNode getExpr() {
        return expr;
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    @Override
    public void dump(int indent) {
        expr.dump(indent + 4);
        format(indent);
        System.out.printf("%s:\n", op);
    }
}
