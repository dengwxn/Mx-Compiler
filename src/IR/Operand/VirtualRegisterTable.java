package IR.Operand;

import AST.Table.Symbol;

import java.util.HashMap;

import static Optimizer.RegisterAllocation.addVertex;
import static Optimizer.RegisterAllocation.regList;

public class VirtualRegisterTable {
    static private int tmpCnt;
    static private HashMap<Symbol, VirtualRegister> table = new HashMap<>();
    static private HashMap<String, Symbol> register = new HashMap<>();

    static public VirtualRegister getVirtualRegister(Symbol symbol) {
        if (!table.containsKey(symbol))
            table.put(symbol, new VirtualRegister(symbol));
        return table.get(symbol);
    }

    static public VirtualRegister getVirtualRegister(String symbol) {
        return getVirtualRegister(register.get(symbol));
    }

    static public VirtualRegister getTemporaryRegister() {
        ++tmpCnt;
        return new VirtualRegister(new Symbol("t" + tmpCnt));
    }

    static private void precolor(String reg) {
        Symbol symbol = new Symbol(reg);
        register.put(reg, symbol);
        // putPhysicalRegister(getVirtualRegister(reg), reg);
    }

    static public void precolor() {
        regList.forEach(reg -> precolor(reg));
        regList.forEach(reg -> addVertex(getVirtualRegister(reg)));
    }
}
