package IR.Operand;

import AST.Table.Symbol;

import static IR.Build.FunctionIR.*;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;
import static Optimizer.RegisterAllocation.getPhysicalRegister;
import static Optimizer.RegisterAllocation.regList;

public class VirtualRegister extends Operand {
    private Symbol symbol;

    VirtualRegister(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public VirtualRegister makeCopy() {
        for (String reg : regList) {
            if (this == getVirtualRegister(reg))
                return this;
        }
        VirtualRegister reg = new VirtualRegister(symbol);
        VirtualRegisterPool.add(reg);
        return reg;
    }

    @Override
    public void assignPhysicalOperand() {
        String var = getPhysicalRegister(this);
        if (var == null) addSpill(this);
        else if (var.contains("lee")) addLee(var);
    }

    @Override
    public String toString() {
        return symbol.getName();
    }
}
