package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalImmediate;
import Generator.Operand.PhysicalOperand;
import Generator.Operand.PhysicalRegister;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.HashMap;
import java.util.HashSet;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.FunctionIR.*;
import static IR.Build.IR.formatInstr;
import static IR.Instruction.Operator.BinaryOp.DIV;
import static IR.Instruction.Operator.BinaryOp.SHL;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class BinaryInstruction extends Instruction implements ConstantFolding, CopyRemove, DeadCodeElimination, ValueNumbering {
    private Operator.BinaryOp op;
    private Operand dst, src;
    private Integer cstVal;
    private Operand prop;

    public BinaryInstruction(Operator.BinaryOp op, Operand dst, Operand src) {
        if (dst instanceof Address && src instanceof Address)
            throw new Error("must not have two address operands.");
        this.op = op;
        this.dst = dst;
        this.src = src;
    }

    public BinaryInstruction(Operator.BinaryOp op, Operand dst, int src) {
        this.op = op;
        this.dst = dst;
        this.src = new Immediate(src);
    }

    public BinaryInstruction(Operator.BinaryOp op, String dst, int src) {
        this.op = op;
        this.dst = getVirtualRegister(dst);
        this.src = new Immediate(src);
    }

    @Override
    public Operand getProp() {
        return prop;
    }

    @Override
    public void numberValue() {
        if (dst instanceof VirtualRegister) {
            Integer x = getMapReg(dst);
            if (x != null) {
                Integer y = null;
                if (src instanceof VirtualRegister)
                    y = getMapReg(src);
                else if (src instanceof Immediate)
                    y = -((Immediate) src).getVal();

                if (y != null) {
                    String instr = op + " " + x + " " + y;
                    Integer z = getMapInstr(instr);
                    if (z != null) {
                        putMapReg(dst, z);
                        prop = getMapVal(z);
                        if (prop != null) return;
                    }
                    incValueNumberingCount();
                    putMapInstr(instr, getValueNumberingCount());
                    putMapReg(dst, getValueNumberingCount());
                    putMapVal(getValueNumberingCount(), dst);
                    return;
                }
            }
            incValueNumberingCount();
            putMapReg(dst, getValueNumberingCount());
            putMapVal(getValueNumberingCount(), dst);
        }
    }

    @Override
    public void setGlobalVar(HashMap<Address, VirtualRegister> globalToReg) {
        if (dst instanceof Address && globalToReg.containsKey(dst))
            dst = globalToReg.get(dst);
        if (src instanceof Address && globalToReg.containsKey(src))
            src = globalToReg.get(src);
    }

    @Override
    public void collectGlobalVar(HashSet<Address> globalVar) {
        collectGlobalVar(dst, globalVar);
        collectGlobalVar(src, globalVar);
    }

    @Override
    public Instruction makeCopy() {
        return new BinaryInstruction(op, makeCopy(dst), makeCopy(src));
    }

    @Override
    public boolean isDeadCode() {
        if (dst instanceof VirtualRegister)
            return isDeadCode((VirtualRegister) dst);
        return false;
    }

    @Override
    public boolean hasNecAddress() {
        return dst instanceof Address;
    }

    @Override
    public void putNec() {
        if (dst instanceof Address) {
            putNec(dst);
            putNec(src);
        }
    }

    @Override
    public boolean removeCopy(VirtualRegister reg) {
        if (isCopy(src)) {
            removeCopy((VirtualRegister) src, reg);
            src = reg;
            return true;
        }
        return false;
    }

    @Override
    public boolean foldToConstant() {
        if (cstVal != null)
            return false;
        Integer cstDst = getConstant(dst);
        Integer cstSrc = getConstant(src);
        if (cstSrc != null)
            src = new Immediate(cstSrc);
        if (cstDst != null && cstSrc != null) {
            switch (op) {
                case ADD:
                    cstVal = cstDst + cstSrc;
                    break;
                case SUB:
                    cstVal = cstDst - cstSrc;
                    break;
                case MUL:
                    cstVal = cstDst * cstSrc;
                    break;
                case DIV:
                    if (cstSrc == 0) return false;
                    cstVal = cstDst / cstSrc;
                    break;
                case MOD:
                    if (cstSrc == 0) return false;
                    cstVal = cstDst % cstSrc;
                    break;
                case AND:
                    cstVal = cstDst & cstSrc;
                    break;
                case OR:
                    cstVal = cstDst | cstSrc;
                    break;
                case XOR:
                    cstVal = cstDst ^ cstSrc;
                    break;
                case SHR:
                    cstVal = cstDst >> cstSrc;
                    break;
                case SHL:
                    cstVal = cstDst << cstSrc;
                    break;
            }
            return true;
        }
        return false;
    }

    public Operator.BinaryOp getOp() {
        return op;
    }

    public Operand getSrc() {
        return src;
    }

    @Override
    public Operand getDst() {
        return dst;
    }

    @Override
    public Integer getCstVal() {
        return cstVal;
    }

    @Override
    public void assignPhysicalOperand() {
        dst.assignPhysicalOperand();
        src.assignPhysicalOperand();
    }

    @Override
    public void putUse() {
        putUse(dst);
        putUse(src);
    }

    @Override
    public void putDef() {
        if (dst instanceof Address || src instanceof Address)
            putDef("ler7");
        putDef(dst);
        switch (op) {
            case SHL:
            case SHR:
                // rcx arg4
                if (!(src instanceof Immediate))
                    putDef("arg4");
                break;
            case DIV:
            case MOD:
                // rax res0
                // rdx arg3
                putDef("res0");
                putDef("arg3");
        }
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertVirtualOperand(str, this.dst, true);
        PhysicalOperand src = convertVirtualOperand(str, this.src, true);
        switch (op) {
            case SHL:
            case SHR:
                if (src instanceof PhysicalImmediate) {
                    str.append(formatInstr(op == SHL ? "sal" : "sar", dst.toNASM(), src.toNASM()));
                } else {
                    // rcx arg4
                    str.append(formatInstr("mov", "arg4", src.toNASM()));
                    str.append(formatInstr(op == SHL ? "sal" : "sar", dst.toNASM(), "arg4_l8"));
                }
                break;
            case MUL:
                if (dst instanceof PhysicalRegister) {
                    str.append(formatInstr("imul", dst.toNASM(), src.toNASM()));
                } else {
                    str.append(formatInstr("mov", "ler8", dst.toNASM()));
                    str.append(formatInstr("imul", "ler8", src.toNASM()));
                    str.append(formatInstr("mov", dst.toNASM(), "ler8"));
                }
                break;
            case DIV:
            case MOD:
                // rax res0
                // rdx arg3
                str.append(formatInstr("mov", "res0", dst.toNASM()));
                str.append(formatInstr("cqo"));
                if (src instanceof PhysicalImmediate) {
                    str.append(formatInstr("mov", "ler8", src.toNASM()));
                    str.append(formatInstr("idiv", "ler8_l32"));
                } else {
                    str.append(formatInstr("idiv", src.toNASM() + (src instanceof PhysicalRegister ? "_l32" : "")));
                }
                String reg = op == DIV ? "res0" : "arg3";
                str.append(formatInstr("movsx", reg, reg + "_l32"));
                str.append(formatInstr("mov", dst.toNASM(), reg));
                break;
            default:
                if (dst instanceof PhysicalAddress && src instanceof PhysicalAddress) {
                    str.append(formatInstr("mov", "ler8", src.toNASM()));
                    str.append(formatInstr(op.toString().toLowerCase(), dst.toNASM(), "ler8"));
                } else {
                    str.append(formatInstr(op.toString().toLowerCase(), dst.toNASM(), src.toNASM()));
                }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr(op.toString().toLowerCase(), dst.toString(), src.toString()));
        return str.toString();
    }
}
