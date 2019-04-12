package IR.Operand;

import AST.Table.Symbol;

import java.util.HashMap;

public class VirtualRegisterTable {
    static private HashMap<Symbol, VirtualRegister> table = new HashMap<>();

    static public Operand getVirtualRegister(Symbol symbol) {
        if (!table.containsKey(symbol))
            table.put(symbol, new VirtualRegister(symbol));
        return table.get(symbol);
    }
}
