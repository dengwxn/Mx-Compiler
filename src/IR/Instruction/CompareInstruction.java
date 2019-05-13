package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalImmediate;
import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.HashMap;
import java.util.HashSet;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.IR.formatInstr;

public class CompareInstruction extends Instruction implements CopyRemove {
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
    public void setGlobalVar(HashMap<Address, VirtualRegister> globalToReg) {
        if (lhs instanceof Address && globalToReg.containsKey(lhs))
            lhs = globalToReg.get(lhs);
        if (rhs instanceof Address && globalToReg.containsKey(rhs))
            rhs = globalToReg.get(rhs);
    }


    @Override
    public void collectGlobalVar(HashSet<Address> globalVar) {
        collectGlobalVar(lhs, globalVar);
        collectGlobalVar(rhs, globalVar);
    }

    @Override
    public Instruction makeCopy() {
        return new CompareInstruction(makeCopy(lhs), makeCopy(rhs));
    }

    @Override
    public boolean hasNecAddress() {
        return lhs instanceof Address || rhs instanceof Address;
    }

    @Override
    public void putNec() {
        putNec(lhs);
        putNec(rhs);
    }

    @Override
    public boolean removeCopy(VirtualRegister reg) {
        if (!isCopy(lhs) && !isCopy(rhs))
            return false;
        if (isCopy(lhs)) {
            removeCopy((VirtualRegister) lhs, reg);
            lhs = reg;
        }
        if (isCopy(rhs)) {
            removeCopy((VirtualRegister) rhs, reg);
            rhs = reg;
        }
        return true;
    }

    public Integer getCstLhs() {
        Integer cst = getConstant(lhs);
        if (cst != null) lhs = new Immediate(cst);
        return cst;
    }

    public Integer getCstRhs() {
        Integer cst = getConstant(rhs);
        if (cst != null) rhs = new Immediate(cst);
        return cst;
    }

    @Override
    public void assignPhysicalOperand() {
        lhs.assignPhysicalOperand();
        rhs.assignPhysicalOperand();
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

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand lhs = convertVirtualOperand(str, this.lhs, true);
        PhysicalOperand rhs = convertVirtualOperand(str, this.rhs, true);
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
