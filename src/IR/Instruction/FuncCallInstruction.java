package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Operand;

import java.util.ArrayList;

import static Generator.Operand.PhysicalOperand.convertOperand;
import static IR.Build.BlockList.maxParamSize;
import static IR.Build.IR.formatInstr;
import static java.lang.Math.max;

public class FuncCallInstruction extends Instruction {
    private String name;
    private ArrayList<Operand> param;

    public FuncCallInstruction(String name, ArrayList<Operand> param) {
        this.name = name;
        this.param = param;
    }

    @Override
    public void putSpill() {
        param.forEach(p -> p.putSpill());
        maxParamSize = max(maxParamSize, param.size());
    }

    @Override
    public void livenessAnalysis() {
        param.forEach(p -> putUse(p));
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < param.size(); ++i) {
            PhysicalOperand param = convertOperand(str, this.param.get(i));
            if (i < 6) {
                str.append(formatInstr("mov", "arg" + (i + 1), param.toNASM()));
            }
            else {
                PhysicalAddress pos = new PhysicalAddress("rsp", (i - 5) * 8);
                if (param instanceof PhysicalAddress) {
                    str.append(formatInstr("mov", "ler8", param.toNASM()));
                    str.append(formatInstr("mov", pos.toNASM(), "ler8"));
                } else {
                    str.append(formatInstr("mov", pos.toNASM(), param.toNASM()));
                }
            }
        }
        str.append(formatInstr("call", name));
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name + "(");
        boolean comma = false;
        for (Operand p : param) {
            if (comma)
                str.append(", " + p.toString());
            else {
                str.append(p.toString());
                comma = true;
            }
        }
        str.append(")");
        return formatInstr("call", str.toString());
    }
}
