package IR.Operand;

import java.util.HashMap;

public class Address extends Operand {
    static private HashMap<String, Integer> offsetTable = new HashMap<>();
    private VirtualRegister base;
    private Operand offset;

    public Address(VirtualRegister base, Operand offset) {
        this.base = base;
        this.offset = offset;
    }

    public Address(VirtualRegister base, int offset) {
        this.base = base;
        this.offset = new Immediate(offset);
    }

    static public int getOffset(String name) {
        return offsetTable.get(name);
    }

    static public void putOffset(String name, Integer offset) {
        offsetTable.put(name, offset);
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        if (offset instanceof Immediate && ((Immediate) offset).getVal() == 0) str.append("[" + base.dump() + "]");
        else str.append("[" + base.dump() + " + " + offset.dump() + "]");
        return str.toString();
    }
}
