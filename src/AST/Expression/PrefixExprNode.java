package AST.Expression;

import AST.Type.Type;
import IR.Build.Block;
import IR.Instruction.*;
import IR.Operand.Immediate;

import java.util.ArrayList;

import static IR.Instruction.Operator.BinaryOp.XOR;
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
            case "++": return INC;
            case "--": return DEC;
            case "-": return NEG;
            case "~": return NOT;
            default: return null;
        }
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        expr.generateIR(block);
        if (op.equals("+")) {
            operand = expr.getOperand();
        }
        else if (op.equals("!")) {
            operand = getTemporaryRegister();
            Instruction mov = new MoveInstruction(operand, expr.getOperand());
            Instruction not = new BinaryInstruction(XOR, operand, new Immediate(1));
            block.get(block.size() - 1).add(mov, not);
        }
        else if (op.equals("++") || op.equals("--")) {
            operand = expr.getOperand();
            Instruction unary = new UnaryInstruction(convertUnaryOp(), operand);
            block.get(block.size() - 1).add(unary);
        }
        else {
            operand = getTemporaryRegister();
            Instruction mov = new MoveInstruction(operand, expr.getOperand());
            Instruction unary = new UnaryInstruction(convertUnaryOp(), operand);
            block.get(block.size() - 1).add(mov, unary);
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
