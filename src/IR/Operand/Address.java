package IR.Operand;

import java.util.HashMap;

public class Address extends Operand {
    static private HashMap<String, Integer> offsetTable = new HashMap<>();
    private VirtualRegister base;
    private Immediate offset;

    public Address(VirtualRegister base, Immediate offset) {
        this.base = base;
        this.offset = offset;
    }

    static public int getOffset(String symbol) {
        return offsetTable.get(symbol);
    }

    static public void putOffset(String symbol, Integer offset) {
        offsetTable.put(symbol, offset);
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("[" + base.dump() + " + " + offset.dump() + "]");
        return str.toString();
    }
}
