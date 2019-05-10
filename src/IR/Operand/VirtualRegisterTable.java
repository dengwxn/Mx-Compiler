package IR.Operand;

import AST.Table.Symbol;

import java.util.HashMap;

import static Optimizer.RegisterAllocation.addVertex;
import static Optimizer.RegisterAllocation.regList;

public class VirtualRegisterTable {
    public static HashMap<Symbol, VirtualRegister> virtualRegisterTable = new HashMap<>();
    private static int tmpCnt;
    private static HashMap<String, Symbol> register = new HashMap<>();

    public static VirtualRegister getVirtualRegister(Symbol symbol) {
        if (!virtualRegisterTable.containsKey(symbol))
            virtualRegisterTable.put(symbol, new VirtualRegister(symbol));
        return virtualRegisterTable.get(symbol);
    }

    public static VirtualRegister getVirtualRegister(String symbol) {
        return getVirtualRegister(register.get(symbol));
    }

    public static VirtualRegister getTemporaryRegister() {
        ++tmpCnt;
        Symbol symbol = new Symbol("t" + tmpCnt);
        VirtualRegister reg = new VirtualRegister(symbol);
        virtualRegisterTable.put(symbol, reg);
        return reg;
    }

    private static void precolor(String reg) {
        Symbol symbol = new Symbol(reg, true);
        register.put(reg, symbol);
    }

    public static void precolor() {
        regList.forEach(reg -> precolor(reg));
        regList.forEach(reg -> addVertex(getVirtualRegister(reg)));
    }
}
