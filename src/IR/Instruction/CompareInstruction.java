package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalImmediate;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertOperand;
import static IR.Build.IR.formatInstr;

public class CompareInstruction extends Instruction {
    private Operand lhs, rhs;

    public CompareInstruction(Operand lhs, Operand rhs) {
        if (lhs instanceof Address && rhs instanceof Address)
            throw new Error("must not have two address operands.");
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public CompareInstruction(Operand lhs, int rhs) {
        this.lhs = lhs;
        this.rhs = new Immediate(rhs);
    }

    @Override
    public void convertVirtualOperand() {
        lhs.convertVirtualOperand();
        rhs.convertVirtualOperand();
    }

    @Override
    public void putUse() {
        putUse(lhs);
        putUse(rhs);
    }

    @Override
    public void putDef() {
        if (lhs instanceof Address || rhs instanceof Address)
            putDef("ler7");
    }

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand lhs = convertOperand(str, this.lhs, true);
        PhysicalOperand rhs = convertOperand(str, this.rhs, true);
        if (lhs instanceof PhysicalImmediate || (lhs instanceof PhysicalAddress && rhs instanceof PhysicalAddress)) {
            str.append(formatInstr("mov", "ler8", lhs.toNASM()));
            str.append(formatInstr("cmp", "ler8", rhs.toNASM()));
        } else {
            str.append(formatInstr("cmp", lhs.toNASM(), rhs.toNASM()));
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr("cmp", lhs.toString(), rhs.toString()));
        return str.toString();
    }
}
