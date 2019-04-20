package IR.Instruction;

import IR.Build.Block;

import static IR.Build.IR.formatInstruction;

public class CondJumpInstruction extends CondInstruction {
    private Block dst;

    public CondJumpInstruction(Operator.CompareOp op, Block dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("j" + op.toString().toLowerCase(), dst.getLabel()));
        return str.toString();
    }
}
