package IR.Instruction;

import IR.Operand.Operand;

public class CondSetInstruction extends Instruction {
    private Operator.CompareOp op;
    private Operand dst;

    public CondSetInstruction(Operator.CompareOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("set" + op.toString().toLowerCase() + "\t" + dst.dump() + "\n");
        return str.toString();
    }
}
