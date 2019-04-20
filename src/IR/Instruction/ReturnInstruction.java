package IR.Instruction;

import IR.Operand.Operand;

import static IR.Build.IR.formatInstruction;

public class ReturnInstruction extends Instruction {
    private Operand val;

    public ReturnInstruction(Operand val) {
        this.val = val;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstruction("ret", (val != null ? val.dump() : "")));
        return str.toString();
    }
}
