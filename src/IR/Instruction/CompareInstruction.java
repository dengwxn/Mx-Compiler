package IR.Instruction;

import IR.Operand.Operand;

public class CompareInstruction extends Instruction {
    private Operand dst, src;

    public CompareInstruction(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("cmp\t\t" + dst.dump() + " " + src.dump() + "\n");
        return str.toString();
    }
}
