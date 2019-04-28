package IR.Operand;

import AST.Table.Symbol;

import java.util.HashMap;

import static Optimizer.RegisterAllocation.putPhysicalRegister;

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
        putPhysicalRegister(getVirtualRegister(reg), reg);
    }

    static public void precolor() {
        precolor("res0");

        precolor("arg1");
        precolor("arg2");
        precolor("arg3");
        precolor("arg4");
        precolor("arg5");
        precolor("arg6");
        precolor("ler7");
        precolor("ler8");

        precolor("lee9");
        precolor("lee10");
        precolor("lee11");
        precolor("lee12");
        precolor("lee13");
        precolor("lee14");

        precolor("rsp");
    }
}
