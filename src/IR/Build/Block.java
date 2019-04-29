package IR.Build;

import IR.Instruction.*;
import IR.Operand.Immediate;

import java.util.ArrayList;

public class Block {
    private String label;
    private ArrayList<Instruction> instr;
    private int id;
    private JumpInstruction jump;

    public Block(String label) {
        this.label = label;
        this.instr = new ArrayList<>();
    }

    void putSpill() {
        instr.forEach(i -> i.putSpill());
    }

    void linkPreSuc() {
        for (int i = 0; i < instr.size(); ++i) {
            Instruction u = instr.get(i);

            if (i < instr.size() - 1) {
                Instruction v = instr.get(i + 1);
                u.linkSuc(v);
                v.linkPre(u);
            }

            if (u instanceof JumpInterface) {
                Instruction v = ((JumpInterface) u).getDst().getHead();
                if (v != null) {
                    u.linkSuc(v);
                    v.linkPre(u);
                }
            }
        }
    }

    void livenessAnalysis() {
        instr.forEach(i -> i.livenessAnalysis());
    }

    void setFuncName(String funcName) {
        if (label.equals("")) label = funcName;
        else label = funcName + "." + label;
    }

    private Instruction getHead() {
        return instr.size() > 0 ? instr.get(0) : null;
    }

    void setId(int id) {
        this.id = id;
    }

    public void add(Instruction... il) {
        for (Instruction i : il) {
            if (jump != null) break;
            if (i instanceof JumpInstruction) jump = (JumpInstruction) i;
            else instr.add(i);

            if (instr.size() >= 3) {
                if (instr.get(instr.size() - 3) instanceof CondSetInstruction
                        && instr.get(instr.size() - 2) instanceof CompareInstruction
                        && instr.get(instr.size() - 1) instanceof CondInstruction) {

                    CondSetInstruction condSet = (CondSetInstruction) instr.get(instr.size() - 3);
                    CompareInstruction cmp = (CompareInstruction) instr.get(instr.size() - 2);

                    if (condSet.getDst() == cmp.getLhs()
                            && cmp.getRhs() instanceof Immediate
                            && ((Immediate) cmp.getRhs()).getVal() == 1) {

                        CondInstruction cond = (CondInstruction) instr.get(instr.size() - 1);
                        cond.setOp(condSet.getOp());
                        instr.remove(instr.size() - 2);
                        instr.remove(instr.size() - 2);
                    }
                }
            }
        }
    }

    public String getLabel() {
        if (id == 0) return label;
        else return label + "." + id;
    }

    public String toNASM(Block nextBlock) {
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        instr.forEach(i -> str.append(i.toNASM()));
        if (jump != null && jump.getDst() != nextBlock)
            str.append(jump.toNASM());
        return str.toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        instr.forEach(i -> str.append(i.toString()));
        if (jump != null) str.append(jump.toString());
        return str.toString();
    }
}
