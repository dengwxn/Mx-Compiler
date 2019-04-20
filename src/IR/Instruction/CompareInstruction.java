package IR.Instruction;

import IR.Operand.Immediate;
import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class CompareInstruction extends Instruction {
    private Operand lhs, rhs;

    public CompareInstruction(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public CompareInstruction(Operand lhs, int rhs) {
        this.lhs = lhs;
        this.rhs = new Immediate(rhs);
    }

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("cmp", lhs.dump(), rhs.dump()));
        return str.toString();
    }
}
