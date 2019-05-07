package IR.Instruction;

import IR.Operand.*;
import Optimizer.RegisterAllocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static IR.Build.FunctionIR.copyOperandTable;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

abstract public class Instruction {
    LinkedHashSet<VirtualRegister> live = new LinkedHashSet<>();
    LinkedHashSet<VirtualRegister> def = new LinkedHashSet<>();
    HashSet<Instruction> singleDefReach = new HashSet<>();
    HashSet<Instruction> singleDefFrom = new HashSet<>();
    HashSet<Instruction> defReach = new HashSet<>();
    HashSet<Instruction> reach = new HashSet<>();
    private HashMap<VirtualRegister, Integer> reachCnt = new HashMap<>();
    private HashMap<VirtualRegister, Integer> receiveCnt = new HashMap<>();
    private HashMap<VirtualRegister, Integer> receiveVal = new HashMap<>();
    private ArrayList<Instruction> pre = new ArrayList<>();
    private ArrayList<Instruction> suc = new ArrayList<>();
    private HashSet<VirtualRegister> use = new HashSet<>();
    private HashSet<VirtualRegister> nec = new HashSet<>();
    private HashSet<VirtualRegister> needed = new HashSet<>();

    public Operand makeCopy(Operand op) {
        if (op instanceof Immediate || op instanceof StringConstant)
            return op;
        if (op instanceof VirtualRegister) {
            VirtualRegister reg = (VirtualRegister) op;
            if (!copyOperandTable.containsKey(reg))
                copyOperandTable.put(reg, reg.makeCopy());
            return copyOperandTable.get(reg);
        }
        if (op instanceof Address) {
            VirtualRegister reg = ((Address) op).getBase();
            if (!copyOperandTable.containsKey(reg)) return op;
            else return new Address(copyOperandTable.get(reg), ((Address) op).getOffset());
        }
        throw new Error("fail to make operand copy.");
    }

    boolean receiveCopy(VirtualRegister cpy, VirtualRegister reg, Instruction from) {
        return false;
    }

    public Integer getConstant(Operand operand) {
        if (operand instanceof Immediate) {
            return ((Immediate) operand).getVal();
        } else if (operand instanceof VirtualRegister) {
            VirtualRegister reg = (VirtualRegister) operand;
            if (reachCnt.get(reg).equals(receiveCnt.get(reg)))
                return receiveVal.get(reg);
        }
        return null;
    }

    void receiveConstant(VirtualRegister reg, int val) {
        if (use.contains(reg)) {
            if (receiveCnt.get(reg) != -1) {
                if (receiveVal.get(reg) == null) {
                    receiveVal.put(reg, val);
                    receiveCnt.put(reg, 1);
                } else {
                    if (receiveVal.get(reg) != val) receiveCnt.put(reg, -1);
                    else receiveCnt.put(reg, receiveCnt.get(reg) + 1);
                }
            }
        }
    }

    public void buildDefReach(VirtualRegister reg) {
        if (use.contains(reg)) {
            reachCnt.put(reg, 0);
            receiveCnt.put(reg, 0);
            for (Instruction u : reach) {
                u.defReach.add(this);
                reachCnt.put(reg, reachCnt.get(reg) + 1);
            }
            if (reach.size() == 1) {
                reach.iterator().next().singleDefReach.add(this);
                singleDefFrom.add(reach.iterator().next());
            }
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

    public void putLive() {
        for (VirtualRegister reg : use) {
            live.add(reg);
            ArrayList<Instruction> queue = new ArrayList<>();
            queue.add(this);
            for (int i = 0; i < queue.size(); ++i) {
                Instruction u = queue.get(i);
                for (Instruction v : u.pre) {
                    if (!v.def.contains(reg)) {
                        if (v.live.add(reg))
                            queue.add(v);
                    }
                }
            }
        }
    }

    boolean isDeadCode(VirtualRegister reg) {
        for (Instruction u : suc) {
            if (u.needed.contains(reg))
                return false;
        }
        return true;
    }

    public void putNeeded() {
        for (VirtualRegister reg : nec) {
            needed.add(reg);
            ArrayList<VirtualRegister> neededQueue = new ArrayList<>();
            ArrayList<Instruction> instrQueue = new ArrayList<>();
            neededQueue.add(reg);
            instrQueue.add(this);
            for (int i = 0; i < neededQueue.size(); ++i) {
                VirtualRegister nec = neededQueue.get(i);
                Instruction u = instrQueue.get(i);
                for (Instruction v : u.pre) {
                    if (!v.def.contains(nec)) {
                        if (v.needed.add(nec)) {
                            neededQueue.add(nec);
                            instrQueue.add(v);
                        }
                    } else {
                        for (VirtualRegister use : v.use) {
                            if (v.needed.add(use)) {
                                neededQueue.add(use);
                                instrQueue.add(v);
                            }
                        }
                    }
                }
            }
        }
    }

    void putNec(Operand op) {
        VirtualRegister reg = extractVirtualRegister(op);
        if (reg != null)
            nec.add(reg);
    }

    void putNec(String op) {
        putNec(getVirtualRegister(op));
    }

    public void putNec() {
    }

    void putUse(Operand op) {
        VirtualRegister reg = extractVirtualRegister(op);
        if (reg != null)
            use.add(reg);
    }

    void putDef(Operand op) {
        if (op instanceof VirtualRegister) {
            VirtualRegister reg = (VirtualRegister) op;
            def.add(reg);
        } else if (op instanceof Address)
            putUse(op);
    }

    void putUse(String op) {
        putUse(getVirtualRegister(op));
    }

    void putDef(String op) {
        putDef(getVirtualRegister(op));
    }

    public void putUse() {
    }

    public void putDef() {
    }

    public void linkSuc(Instruction suc) {
        this.suc.add(suc);
    }

    public void linkPre(Instruction pre) {
        this.pre.add(pre);
    }

    public void clearReach() {
        reach.clear();
    }

    void clearUse() {
        use.clear();
    }

    public void clearAnalysis() {
        pre.clear();
        suc.clear();
        live.clear();
        def.clear();
        use.clear();

        singleDefReach.clear();
        singleDefFrom.clear();
        defReach.clear();
        reachCnt.clear();
        receiveCnt.clear();
        receiveVal.clear();

        nec.clear();
        needed.clear();
    }

    private VirtualRegister extractVirtualRegister(Operand op) {
        VirtualRegister reg = null;
        if (op instanceof VirtualRegister) {
            reg = (VirtualRegister) op;
        } else if (op instanceof Address) {
            reg = ((Address) op).getBase();
            if (reg.getSymbol().isGlobal())
                reg = null;
        }
        return reg;
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

    abstract public Instruction makeCopy();

    abstract public String toNASM();

    abstract public String toString();

    public String dumpNeeded() {
        StringBuilder str = new StringBuilder();
        str.append("\t\t\t<needed>: ");
        needed.forEach(reg -> str.append(reg.toString() + ", "));
        str.append("\n");
        return str.toString();
    }

    public String dumpLive() {
        StringBuilder str = new StringBuilder();
        str.append("\t\t\t<def>: ");
        def.forEach(reg -> str.append(reg.toString() + ", "));
        str.append("\n\t\t\t<live>: ");
        live.forEach(reg -> str.append(reg.toString() + ", "));
        str.append("\n");
        return str.toString();
    }
}
