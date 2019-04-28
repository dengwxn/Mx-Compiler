package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertOperand;
import static IR.Build.IR.formatInstr;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class MoveInstruction extends Instruction {
    private Operand dst, src;

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

    @Override
    public void putSpill() {
        dst.putSpill();
        src.putSpill();
    }

    @Override
    public void livenessAnalysis() {
        putDef(dst);
        putUse(src);
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertOperand(str, this.dst);
        PhysicalOperand src = convertOperand(str, this.src);
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
