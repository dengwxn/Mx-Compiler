package IR.Operand;

import AST.Table.Symbol;

import static IR.Build.FunctionIR.addSpill;
import static Optimizer.RegisterAllocation.getPhysicalRegister;

public class VirtualRegister extends Operand {
    private Symbol symbol;

    VirtualRegister(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public void putSpill() {
        if (getPhysicalRegister(this) == null)
            addSpill(this);
    }

    @Override
    public String toString() {
        return symbol.getName();
    }
}
