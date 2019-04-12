package IR.Instruction;

import IR.Operand.Operand;

public class UnaryInstruction extends Instruction {
    Operator.UnaryOp op;
    Operand dst;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }
}
