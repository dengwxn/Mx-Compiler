package IR.Instruction;

import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;
import Optimizer.RegisterAllocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

abstract public class Instruction {
    LinkedHashSet<VirtualRegister> live = new LinkedHashSet<>();
    LinkedHashSet<VirtualRegister> def = new LinkedHashSet<>();
    HashSet<Instruction> singleDefReach = new HashSet<>();
    private ArrayList<Instruction> pre = new ArrayList<>();
    private ArrayList<Instruction> suc = new ArrayList<>();
    private HashSet<VirtualRegister> use = new HashSet<>();
    private HashSet<Instruction> reach = new HashSet<>();

    public void buildSingleDefReach(VirtualRegister reg) {
        if (use.contains(reg)) {
            if (reach != null && reach.size() == 1)
                reach.iterator().next().singleDefReach.add(this);
        }
    }

    public void putReach(VirtualRegister reg) {
        if (def.contains(reg)) {
            ArrayList<Instruction> queue = new ArrayList<>(suc);
            suc.forEach(u -> u.reach.add(this));
            for (int i = 0; i < queue.size(); ++i) {
                Instruction u = queue.get(i);
                if (!u.def.contains(reg)) {
                    for (Instruction v : u.suc) {
                        if (v.reach.add(this))
                            queue.add(v);
                    }
                }
            }
        }
    }

    private boolean propLive(VirtualRegister var) {
        if (!def.contains(var))
            return live.add(var);
        return false;
    }

    public void putLive() {
        for (VirtualRegister var : use) {
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

    void putUse(Operand op) {
        VirtualRegister var = extractVirtualRegister(op);
        if (var != null)
            use.add(var);
    }

    void putDef(Operand op) {
        if (op instanceof VirtualRegister) {
            VirtualRegister var = (VirtualRegister) op;
            def.add(var);
        } else if (op instanceof Address)
            putUse(op);
    }

    public void linkSuc(Instruction suc) {
        this.suc.add(suc);
    }

    public void linkPre(Instruction pre) {
        this.pre.add(pre);
    }

    public void putUse() {
    }

    public void putDef() {
    }

    void putUse(String op) {
        putUse(getVirtualRegister(op));
    }

    void putDef(String op) {
        putDef(getVirtualRegister(op));
    }

    public void clearReach() {
        reach.clear();
    }

    public void clearAnalysis() {
        pre.clear();
        suc.clear();
        live.clear();
        def.clear();
        use.clear();
        singleDefReach.clear();
    }

    private VirtualRegister extractVirtualRegister(Operand op) {
        VirtualRegister var = null;
        if (op instanceof VirtualRegister) {
            var = (VirtualRegister) op;
        } else if (op instanceof Address) {
            var = ((Address) op).getBase();
            if (var.getSymbol().isGlobal())
                var = null;
        }
        return var;
    }

    public void assignPhysicalOperand() {
    }

    public void addVertex() {
        for (VirtualRegister u : def)
            RegisterAllocation.addVertex(u);
    }

    public void buildIntrfGraph() {
        for (VirtualRegister u : def) {
            for (VirtualRegister v : live) {
                if (u != v)
                    RegisterAllocation.buildIntrfGraph(u, v);
            }
        }
    }

    abstract public String toString();

    abstract public String toNASM();

    public String dumpLive() {
        StringBuilder str = new StringBuilder();
        str.append("\t\t\t<def>: ");
        def.forEach(var -> str.append(var.toString() + ", "));
        str.append("\n\t\t\t<live>: ");
        live.forEach(var -> str.append(var.toString() + ", "));
        str.append("\n");
        return str.toString();
    }
}
