package Optimizer;

import IR.Operand.VirtualRegister;

import java.util.HashMap;

public class RegisterAllocation {
    static private HashMap<VirtualRegister, String> physicalRegister = new HashMap<>();

    static public String getPhysicalRegister(VirtualRegister reg) {
        return physicalRegister.get(reg);
    }

    static public void putPhysicalRegister(VirtualRegister reg, String str) {
        physicalRegister.put(reg, str);
    }

    static public void optimize() {
    }
}
