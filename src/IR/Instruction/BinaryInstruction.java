package IR.Instruction;

import IR.Operand.Operand;

public class BinaryInstruction extends Instruction {
    private Operator.BinaryOp op;
    private Operand dst, src;

    public BinaryInstruction(Operator.BinaryOp op, Operand dst, Operand src) {
        this.op = op;
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(op.toString().toLowerCase() + "\t\t" + dst.dump() + " " + src.dump() + "\n");
        return str.toString();
    }
}
