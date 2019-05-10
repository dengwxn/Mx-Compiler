package AST.Expression;

import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.MoveInstruction;
import IR.Instruction.Operator;
import IR.Instruction.UnaryInstruction;

import static AST.Expression.BoolCstExprNode.*;
import static IR.Instruction.Operator.UnaryOp.*;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class PrefixExprNode extends ExprNode {
    private String op;
    private ExprNode expr;

    public PrefixExprNode(String op, ExprNode expr) {
        this.op = op;
        this.expr = expr;
    }

    private Operator.UnaryOp convertUnaryOp() {
        switch (op) {
            case "++":
                return INC;
            case "--":
                return DEC;
            case "-":
                return NEG;
            case "~":
                return NOT;
            default:
                return null;
        }
    }

    @Override
    public void generateIR(BlockList blockList) {
        expr.generateIR(blockList);
        switch (op) {
            case "+":
                operand = expr.getOperand();
                break;
            case "!":
                if (!hasLogicRecur())
                    generateLogicRecur(blockList, expr.getOperand());
                swapLogicRecur();
                break;
            case "++":
            case "--":
                operand = expr.getOperand();
                blockList.add(new UnaryInstruction(convertUnaryOp(), operand));
                break;
            default:
                operand = getTemporaryRegister();
                blockList.add(new MoveInstruction(operand, expr.getOperand()));
                blockList.add(new UnaryInstruction(convertUnaryOp(), operand));
                break;
        }
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    public String getOp() {
        return op;
    }

    public ExprNode getExpr() {
        return expr;
    }

    @Override
    public boolean isLeftValue() {
        return op.equals("++") || op.equals("--");
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        expr.dump(indent + 4);
    }
}
