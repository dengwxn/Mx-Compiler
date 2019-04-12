package IR.Operand;

import AST.Table.Symbol;

public class VirtualRegister extends Operand {
    private Symbol symbol;

    VirtualRegister(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String dump() {
        return symbol.getName();
    }
}
