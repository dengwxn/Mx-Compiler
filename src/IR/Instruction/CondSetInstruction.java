package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.IR.formatInstr;

public class CondSetInstruction extends CondInstruction {
    private Operand dst;

    public CondSetInstruction(Operator.CompareOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public void assignPhysicalOperand() {
        dst.assignPhysicalOperand();
    }

    @Override
    public void putDef() {
        if (dst instanceof Address)
            putDef("ler7");
        putDef(dst);
    }

    public Operator.CompareOp getOp() {
        return op;
    }

    public Operand getDst() {
        return dst;
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertVirtualOperand(str, this.dst, true);
        if (dst instanceof PhysicalAddress) {
            str.append(formatInstr("mov", "ler8", "0"));
            str.append(formatInstr("set" + op.toString().toLowerCase(), "ler8_l8"));
            str.append(formatInstr("mov", dst.toNASM(), "ler8"));
        } else {
            str.append(formatInstr("mov", dst.toNASM(), "0"));
            str.append(formatInstr("set" + op.toString().toLowerCase(), dst.toNASM() + "_l8"));
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr("set" + op.toString().toLowerCase(), dst.toString()));
        return str.toString();
    }
}
