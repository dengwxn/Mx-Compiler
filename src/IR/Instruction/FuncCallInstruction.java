package IR.Instruction;

import IR.Operand.Operand;

import java.util.ArrayList;

import static IR.Build.IR.formatInstruction;

public class FuncCallInstruction extends Instruction {
    private String name;
    private ArrayList<Operand> param;

    public FuncCallInstruction(String name, ArrayList<Operand> param) {
        this.name = name;
        this.param = param;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append(name + "(");
        boolean comma = false;
        for (Operand p : param) {
            if (comma)
                str.append(", " + p.dump());
            else {
                str.append(p.dump());
                comma = true;
            }
        }
        str.append(")");
        return formatInstruction("call", str.toString());
    }
}
