package IR.Instruction;

import IR.Build.Block;

import static IR.Build.IR.formatInstr;

public class JumpInstruction extends Instruction implements Jump {
    private Block dst;

    public JumpInstruction(Block dst) {
        this.dst = dst;
    }

    @Override
    public Instruction makeCopy() {
        return new JumpInstruction(dst);
    }

    @Override
    public Block getDst() {
        return dst;
    }

    @Override
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
        str.append(formatInstr("jmp", dst.getLabel()));
        return str.toString();
    }
}
