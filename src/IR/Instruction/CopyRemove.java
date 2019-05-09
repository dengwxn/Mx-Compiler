package IR.Instruction;

import IR.Operand.VirtualRegister;

public interface CopyRemove {
    boolean removeCopy(VirtualRegister reg);
}
