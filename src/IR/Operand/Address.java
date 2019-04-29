package IR.Operand;

import java.util.HashMap;

import static IR.Build.FunctionIR.addSpill;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;
import static Optimizer.RegisterAllocation.getPhysicalRegister;

public class Address extends Operand {
    static private HashMap<String, Integer> offsetTable = new HashMap<>();
    private VirtualRegister base;
    private Immediate offset;

    public Address(VirtualRegister base, int offset) {
        this.base = base;
        this.offset = new Immediate(offset);
    }

    public Address(String base, int offset) {
        this.base = getVirtualRegister(base);
        this.offset = new Immediate(offset);
    }

    @Override
    public void putSpill() {
        if (getPhysicalRegister(base) == null)
            addSpill(base);
    }

    public VirtualRegister getBase() {
        return base;
    }

    public int getOffset() {
        return offset.getVal();
    }

    static public int getOffset(String name) {
        if (offsetTable.containsKey(name))
            return offsetTable.get(name);
        return -1;
    }

    static public void putOffset(String name, Integer offset) {
        offsetTable.put(name, offset);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (offset.getVal() == 0) str.append("[" + base.toString() + "]");
        else str.append("[" + base.toString() + " + " + offset.toString() + "]");
        return str.toString();
    }
}
