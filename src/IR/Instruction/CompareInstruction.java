package IR.Instruction;

import IR.Operand.VirtualRegister;

public class CompareInstruction extends Instruction {
    VirtualRegister dst, src;

    public CompareInstruction(VirtualRegister dst, VirtualRegister src) {
        this.dst = dst;
        this.src = src;
    }
}
