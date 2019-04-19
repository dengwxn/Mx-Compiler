package IR.Instruction;

import IR.Operand.Operand;

public class MoveInstruction extends Instruction {
    private Operand dst, src;

    public MoveInstruction(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("mov\t\t" + dst.dump() + " " + src.dump() + "\n");
        return str.toString();
    }
}
