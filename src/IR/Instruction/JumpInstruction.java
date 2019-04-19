package IR.Instruction;

import IR.Build.Block;

public class JumpInstruction extends Instruction {
    private Block dst;

    public JumpInstruction(Block dst) {
        this.dst = dst;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("jmp" + "\t\t" + dst.getLabel() + "\n");
        return str.toString();
    }
}
