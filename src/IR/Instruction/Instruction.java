package IR.Instruction;

import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;
import Optimizer.RegisterAllocation;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

abstract public class Instruction {
    LinkedHashSet<VirtualRegister> live = new LinkedHashSet<>();
    LinkedHashSet<VirtualRegister> def = new LinkedHashSet<>();
    private ArrayList<Instruction> pre = new ArrayList<>();
    private ArrayList<Instruction> suc = new ArrayList<>();

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

    void putUse(Operand op) {
        VirtualRegister var = extractVirtualRegister(op);
        if (var != null) {
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

    void putDef(Operand op) {
        if (op instanceof VirtualRegister) def.add((VirtualRegister) op);
        else if (op instanceof Address) putUse(op);
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

    public void convertVirtualOperand() {
    }

    abstract public String toString();

    abstract public String toNASM();

    public String toDisplayLive() {
        StringBuilder str = new StringBuilder();
        str.append("\t\t\t<def>: ");
        def.forEach(var -> str.append(var.toString() + ", "));
        str.append("\n\t\t\t<live>: ");
        live.forEach(var -> str.append(var.toString() + ", "));
        str.append("\n");
        return str.toString();
    }
}
