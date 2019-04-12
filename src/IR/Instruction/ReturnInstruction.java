package IR.Instruction;

import IR.Operand.Operand;

public class ReturnInstruction extends Instruction {
    Operand val;

    public ReturnInstruction(Operand val) {
        this.val = val;
    }
}
