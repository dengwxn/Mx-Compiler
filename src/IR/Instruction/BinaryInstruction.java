package IR.Instruction;

import IR.Operand.Immediate;
import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class BinaryInstruction extends Instruction {
    private Operator.BinaryOp op;
    private Operand dst, src;

    public BinaryInstruction(Operator.BinaryOp op, Operand dst, Operand src) {
        this.op = op;
        this.dst = dst;
        this.src = src;
    }

    public BinaryInstruction(Operator.BinaryOp op, Operand dst, int src) {
        this.op = op;
        this.dst = dst;
        this.src = new Immediate(src);
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction(op.toString().toLowerCase(), dst.dump(), src.dump()));
        return str.toString();
    }
}
