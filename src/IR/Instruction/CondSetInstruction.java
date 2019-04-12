package IR.Instruction;

import IR.Operand.VirtualRegister;

public class CondSetInstruction extends Instruction {
    Operator.ConditonOp op;
    VirtualRegister dst;
}
