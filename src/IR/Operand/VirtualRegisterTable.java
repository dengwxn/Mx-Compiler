package IR.Operand;

import AST.Table.Symbol;

import java.util.HashMap;

public class VirtualRegisterTable {
    static private int tmpCnt;
    static private HashMap<Symbol, VirtualRegister> table = new HashMap<>();
    static private HashMap<String, Symbol> specificRegister = new HashMap<>();

    static public Operand getVirtualRegister(Symbol symbol) {
        if (!table.containsKey(symbol))
            table.put(symbol, new VirtualRegister(symbol));
        return table.get(symbol);
    }

    static public Operand getVirtualRegister(String symbol) {
        return getVirtualRegister(specificRegister.get(symbol));
    }

    static public VirtualRegister getTemporaryRegister() {
        ++tmpCnt;
        return new VirtualRegister(new Symbol("t" + tmpCnt));
    }

    static public void setSpecificRegister() {
        specificRegister.put("rax", new Symbol("rax"));
    }
}
