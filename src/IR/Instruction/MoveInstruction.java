package IR.Instruction;

import IR.Operand.Immediate;
import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class MoveInstruction extends Instruction {
    private Operand dst, src;

    public MoveInstruction(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    public MoveInstruction(Operand dst, int src) {
        this.dst = dst;
        this.src = new Immediate(src);
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("mov", dst.dump(), src.dump()));
        return str.toString();
    }
}