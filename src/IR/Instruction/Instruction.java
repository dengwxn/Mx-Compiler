package IR.Instruction;

import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.HashSet;

abstract public class Instruction {
    private ArrayList<Instruction> pre = new ArrayList<>();
    private ArrayList<Instruction> suc = new ArrayList<>();
    private HashSet<VirtualRegister> live = new HashSet<>();
    private HashSet<VirtualRegister> def = new HashSet<>();

    void putDef(Operand op) {
        if (op instanceof VirtualRegister)
            def.add((VirtualRegister) op);
    }

    void putUse(Operand op) {
        if (op instanceof VirtualRegister)
            putLive((VirtualRegister) op);
    }

    private void putLive(VirtualRegister var) {
        if (!live.contains(var)) {
            live.add(var);
            pre.forEach(p -> p.propLive(var));
        }
    }

    private void propLive(VirtualRegister var) {
        if (!def.contains(var))
            putLive(var);
    }

    public void linkSuc(Instruction suc) {
        this.suc.add(suc);
    }

    public void linkPre(Instruction pre) {
        this.pre.add(pre);
    }

    public String dump() {
        return null;
    }

    public void livenessAnalysis() {
    }
}
