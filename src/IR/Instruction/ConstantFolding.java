package IR.Instruction;

import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

public interface ConstantFolding {
    boolean receiveConstant(VirtualRegister reg, int val);

    Operand getDst();

    Integer getCstVal();
}
