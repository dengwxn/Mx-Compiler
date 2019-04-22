package IR.Instruction;

import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class UnaryInstruction extends Instruction {
    private Operator.UnaryOp op;
    private Operand dst;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public void livenessAnalysis() {
        putDef(dst);
        putUse(dst);
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction(op.toString().toLowerCase(), dst.dump()));
        return str.toString();
    }
}
