package IR.Instruction;

import IR.Build.Block;

import static IR.Build.IR.formatInstr;

public class CondJumpInstruction extends CondInstruction implements Jump {
    private Block dst;

    public CondJumpInstruction(Operator.CompareOp op, Block dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public Instruction makeCopy() {
        return new CondJumpInstruction(op, dst);
    }

    public Block getDst() {
        return dst;
    }

    public void setDst(Block dst) {
        this.dst = dst;
    }

    @Override
    public String toNASM() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr("j" + op.toString().toLowerCase(), dst.getLabel()));
        return str.toString();
    }
}
