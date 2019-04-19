package IR.Instruction;

import IR.Operand.Operand;

public class UnaryInstruction extends Instruction {
    private Operator.UnaryOp op;
    private Operand dst;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(op.toString().toLowerCase() + "\t\t" + dst.dump() + "\n");
        return str.toString();
    }
}
