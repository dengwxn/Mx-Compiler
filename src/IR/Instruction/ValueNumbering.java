package IR.Instruction;

import IR.Operand.Operand;

public interface ValueNumbering {
    Operand getDst();

    Operand getProp();
}
