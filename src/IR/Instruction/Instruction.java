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

    public void putSpill() {}

    public void livenessAnalysis() {}

    abstract public String toString();

    abstract public String toNASM();

    void putDef(Operand op) {
        if (op instanceof VirtualRegister)
            def.add((VirtualRegister) op);
    }

    void putUse(Operand op) {
        if (op instanceof VirtualRegister) {
            VirtualRegister var = (VirtualRegister) op;
            live.add(var);

            ArrayList<Instruction> queue = new ArrayList<>();
            queue.add(this);
            for (int i = 0; i < queue.size(); ++i) {
                Instruction u = queue.get(i);
                for (Instruction v : u.pre) {
                    if (v.propLive(var))
                        queue.add(v);
                }
            }
        }
    }

    private boolean propLive(VirtualRegister var) {
        if (!def.contains(var) && !live.contains(var)) {
            live.add(var);
            return true;
        }
        return false;
    }

    public void linkSuc(Instruction suc) {
        this.suc.add(suc);
    }

    public void linkPre(Instruction pre) {
        this.pre.add(pre);
    }
}
