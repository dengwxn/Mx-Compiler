package IR.Instruction;

import IR.Operand.Operand;

public interface ConstantFolding {
    boolean foldToConstant();

    Operand getDst();

    Integer getCstVal();
}
