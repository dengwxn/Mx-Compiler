package IR.Instruction;

import IR.Build.Block;

import static IR.Build.IR.formatInstr;

public class CondJumpInstruction extends Instruction implements Jump {
    private Operator.CompareOp op;
    private Block dst;

    public CondJumpInstruction(Operator.CompareOp op, Block dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public Instruction makeCopy() {
        return new CondJumpInstruction(op, dst);
    }

    public Operator.CompareOp getOp() {
        return op;
    }

    public void setOp(Operator.CompareOp op) {
        this.op = op;
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
        str.append(formatInstr("j" + op.toString().toLowerCase(), dst.getProgLabel()));
        return str.toString();
    }
}
