package IR.Build;

import IR.Instruction.*;
import IR.Operand.Immediate;

import java.util.ArrayList;

public class Block {
    private String label;
    private ArrayList<Instruction> instr;
    private int id;
    private Instruction jump;

    public Block(String label) {
        this.label = label;
        this.instr = new ArrayList<>();
    }

    void linkPreSuc() {
        for (int i = 0; i < instr.size() - 1; ++i) {
            Instruction u = instr.get(i);
            Instruction v = instr.get(i + 1);
            u.linkSuc(v);
            v.linkPre(u);
        }
    }

    void livenessAnalysis() {
        instr.forEach(Instruction::livenessAnalysis);
    }

    Instruction getHead() {
        return instr.size() > 0 ? instr.get(0) : null;
    }

    JumpInstruction getTail() {
        return (JumpInstruction) jump;
    }

    void setId(int id) {
        this.id = id;
    }

    public void add(Instruction... il) {
        for (Instruction i : il) {
            if (jump != null) break;
            instr.add(i);
            if (i instanceof JumpInstruction) jump = i;
        }

        if (instr.size() >= 4) {
            if (instr.get(instr.size() - 4) instanceof CondSetInstruction
                    && instr.get(instr.size() - 3) instanceof CompareInstruction
                    && instr.get(instr.size() - 2) instanceof CondInstruction) {

                CondSetInstruction condSet = (CondSetInstruction) instr.get(instr.size() - 4);
                CompareInstruction cmp = (CompareInstruction) instr.get(instr.size() - 3);

                if (condSet.getDst() == cmp.getLhs()
                        && cmp.getRhs() instanceof Immediate
                        && ((Immediate) cmp.getRhs()).getVal() == 1) {

                    CondInstruction cond = (CondInstruction) instr.get(instr.size() - 2);
                    cond.setOp(condSet.getOp());
                    instr.remove(instr.size() - 3);
                    instr.remove(instr.size() - 3);
                }
            }
        }
    }

    public String getLabel() {
        return label + "." + id;
    }

    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("\t" + getLabel() + ":\n");
        instr.forEach(i -> str.append(i.dump()));
        return str.toString();
    }
}
