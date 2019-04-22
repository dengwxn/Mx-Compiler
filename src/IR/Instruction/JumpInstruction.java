package IR.Instruction;

import IR.Build.Block;

import static IR.Build.IR.formatInstruction;

public class JumpInstruction extends Instruction {
    private Block dst;

    public JumpInstruction(Block dst) {
        this.dst = dst;
    }

    public Block getDst() {
        return dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("jmp", dst.getLabel()));
        return str.toString();
    }
}
