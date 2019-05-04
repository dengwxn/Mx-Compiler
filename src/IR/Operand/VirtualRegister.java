package IR.Operand;

import AST.Table.Symbol;

import static IR.Build.FunctionIR.addLee;
import static IR.Build.FunctionIR.addSpill;
import static Optimizer.RegisterAllocation.getPhysicalRegister;

public class VirtualRegister extends Operand {
    private Symbol symbol;

    VirtualRegister(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
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
