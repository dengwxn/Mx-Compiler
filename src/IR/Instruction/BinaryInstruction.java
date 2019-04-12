package IR.Instruction;

import IR.Operand.VirtualRegister;

public class BinaryInstruction extends Instruction {
    Operator.BinaryOp op;
    VirtualRegister dst, src;
}
