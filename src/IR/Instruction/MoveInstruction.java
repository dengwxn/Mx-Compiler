package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;
import Optimizer.RegisterAllocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.IR.formatInstr;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;
import static Optimizer.RegisterAllocation.getPhysicalRegister;

public class MoveInstruction extends Instruction implements ConstantFolding, CopyRemove, DeadCodeElimination {
    private Operand dst, src;
    private Integer cstVal;

    public MoveInstruction(Operand dst, Operand src) {
        if (dst instanceof Address && src instanceof Address)
            throw new Error("must not have two address operands.");
        this.dst = dst;
        this.src = src;
    }

    public MoveInstruction(Operand dst, int src) {
        this.dst = dst;
        this.src = new Immediate(src);
    }

    public MoveInstruction(Operand dst, String src) {
        this.dst = dst;
        this.src = getVirtualRegister(src);
    }

    public MoveInstruction(String dst, Operand src) {
        this.dst = getVirtualRegister(dst);
        this.src = src;
    }

    public MoveInstruction(String dst, int src) {
        this.dst = getVirtualRegister(dst);
        this.src = new Immediate(src);
    }

    @Override
    public void setGlobalVar(HashMap<Address, VirtualRegister> globalToReg) {
        if (dst instanceof Address && globalToReg.containsKey(dst))
            dst = globalToReg.get(dst);
        if (src instanceof Address && globalToReg.containsKey(src))
            src = globalToReg.get(src);
    }

    @Override
    public void collectGlobalVar(HashSet<Address> globalVar) {
        collectGlobalVar(dst, globalVar);
        collectGlobalVar(src, globalVar);
    }

    @Override
    public Instruction makeCopy() {
        return new MoveInstruction(makeCopy(dst), makeCopy(src));
    }

    @Override
    public boolean isDeadCode() {
        if (dst instanceof VirtualRegister)
            return isDeadCode((VirtualRegister) dst);
        return false;
    }

    @Override
    public void putNec() {
        if (dst == getVirtualRegister("res0")) {
            putNec(src);
        } else if (dst instanceof Address) {
            putNec(dst);
            putNec(src);
        }
    }

    @Override
    public boolean removeCopy(VirtualRegister reg) {
        if (isCopy(src)) {
            removeCopy((VirtualRegister) src, reg);
            src = reg;
            return true;
        }
        return false;
    }

    public void propagateCopy(VirtualRegister reg, HashSet<Instruction> flag, HashSet<Instruction> clear) {
        ArrayList<VirtualRegister> cpyQueue = new ArrayList<>();
        ArrayList<MoveInstruction> instrQueue = new ArrayList<>();
        if (dst instanceof VirtualRegister && src == reg) {
            cpyQueue.add((VirtualRegister) dst);
            instrQueue.add(this);
            flag.add(this);
            clear.add(this);

            for (int i = 0; i < cpyQueue.size(); ++i) {
                VirtualRegister cpy = cpyQueue.get(i);
                MoveInstruction u = instrQueue.get(i);
                HashSet<Instruction> from = u.reach;

                for (Instruction v : u.defReach) {
                    if (from.equals(v.reach)) {
                        clear.add(v);
                        if (v.receiveCopy(cpy, reg)) {
                            if (v instanceof CopyRemove) {
                                if (((CopyRemove) v).removeCopy(reg)) {
                                    if (v instanceof MoveInstruction) {
                                        MoveInstruction w = (MoveInstruction) v;
                                        if (w.getDst() instanceof VirtualRegister && flag.add(w)) {
                                            cpyQueue.add((VirtualRegister) w.getDst());
                                            instrQueue.add(w);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void propagateConstant() {
        if (dst instanceof VirtualRegister && src instanceof Immediate) {
            cstVal = ((Immediate) src).getVal();
            ArrayList<VirtualRegister> regQueue = new ArrayList<>();
            ArrayList<Integer> cstQueue = new ArrayList<>();
            ArrayList<Instruction> instrQueue = new ArrayList<>();
            regQueue.add((VirtualRegister) dst);
            cstQueue.add(cstVal);
            instrQueue.add(this);

            for (int i = 0; i < regQueue.size(); ++i) {
                VirtualRegister reg = regQueue.get(i);
                Integer cst = cstQueue.get(i);
                Instruction u = instrQueue.get(i);
                for (Instruction v : u.defReach) {
                    v.receiveConstant(reg, cst);
                    if (v instanceof ConstantFolding) {
                        ConstantFolding w = (ConstantFolding) v;
                        if (w.foldToConstant()) {
                            if (w.getDst() instanceof VirtualRegister) {
                                regQueue.add((VirtualRegister) w.getDst());
                                cstQueue.add(w.getCstVal());
                                instrQueue.add(v);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean foldToConstant() {
        if (cstVal != null)
            return false;
        cstVal = getConstant(src);
        return cstVal != null;
    }

    @Override
    public Operand getDst() {
        return dst;
    }

    public Operand getSrc() {
        return src;
    }

    @Override
    public Integer getCstVal() {
        return cstVal;
    }

    public String getPhyDst() {
        if (dst instanceof VirtualRegister)
            return getPhysicalRegister((VirtualRegister) dst);
        return null;
    }

    public String getPhySrc() {
        if (src instanceof VirtualRegister)
            return getPhysicalRegister((VirtualRegister) src);
        return null;
    }

    @Override
    public void buildIntrfGraph() {
        for (VirtualRegister u : def) {
            for (VirtualRegister v : live) {
                if (u != v) {
                    if (u == dst && v == src) continue;
                    RegisterAllocation.buildIntrfGraph(u, v);
                }
            }
        }
    }

    @Override
    public void assignPhysicalOperand() {
        dst.assignPhysicalOperand();
        src.assignPhysicalOperand();
    }

    @Override
    public void putUse() {
        putUse(src);
    }

    @Override
    public void putDef() {
        if (dst instanceof Address)
            putDef("ler7");
        putDef(dst);
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertVirtualOperand(str, this.dst, true);
        PhysicalOperand src = convertVirtualOperand(str, this.src, false);
        if (dst instanceof PhysicalAddress && src instanceof PhysicalAddress) {
            str.append(formatInstr("mov", "ler8", src.toNASM()));
            str.append(formatInstr("mov", dst.toNASM(), "ler8"));
        } else {
            str.append(formatInstr("mov", dst.toNASM(), src.toNASM()));
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr("mov", dst.toString(), src.toString()));
        return str.toString();
    }
}
