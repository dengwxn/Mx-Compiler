package IR.Instruction;

import IR.Build.Block;

public interface Jump {
    Block getDst();

    void setDst(Block dst);
}
