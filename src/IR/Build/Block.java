package IR.Build;

import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

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

    void setId(int id) {
        this.id = id;
    }

    public Block(String label, int id) {
        this.label = label;
        this.instr = new ArrayList<>();
        this.id = id;
    }

    public void add(Instruction... il) {
        for (Instruction i : il) {
            if (jump != null) break;
            instr.add(i);
            if (i instanceof JumpInstruction) jump = i;
        }
    }

    public String getLabel() {
        return label + "." + id;
    }

    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("\t" + getLabel() + ":\n");
        instr.forEach(i -> str.append("\t\t" + i.dump()));
        return str.toString();
    }
}
