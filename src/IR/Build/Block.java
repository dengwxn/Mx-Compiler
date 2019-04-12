package IR.Build;

import IR.Instruction.Instruction;

import java.util.ArrayList;

public class Block {
    private String label;
    private ArrayList<Instruction> instr;
    private int id;

    Block(String label, int id) {
        this.label = label;
        this.instr = new ArrayList<>();
        this.id = id;
    }

    public void add(Instruction i) {
        instr.add(i);
    }

    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("\t." + label + "." + id + ":\n");
        instr.forEach(i -> str.append("\t\t" + i.dump()));
        return str.toString();
    }
}
