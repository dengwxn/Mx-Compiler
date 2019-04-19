package IR.Instruction;

import IR.Build.Block;

public class CondJumpInstruction extends Instruction {
    private Operator.CompareOp op;
    private Block dst;

    public CondJumpInstruction(Operator.CompareOp op, Block dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("j" + op.toString().toLowerCase() + "\t\t" + dst.getLabel() + "\n");
        return str.toString();
    }
}
