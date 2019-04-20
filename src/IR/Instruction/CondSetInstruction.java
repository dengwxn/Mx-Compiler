package IR.Instruction;

import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class CondSetInstruction extends CondInstruction {
    private Operand dst;

    public CondSetInstruction(Operator.CompareOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    public Operator.CompareOp getOp() {
        return op;
    }

    public Operand getDst() {
        return dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("set" + op.toString().toLowerCase(), dst.dump()));
        return str.toString();
    }
}
