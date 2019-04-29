package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalImmediate;
import Generator.Operand.PhysicalOperand;
import Generator.Operand.PhysicalRegister;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertOperand;
import static IR.Build.IR.formatInstr;
import static IR.Instruction.Operator.BinaryOp.DIV;
import static IR.Instruction.Operator.BinaryOp.SHL;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class BinaryInstruction extends Instruction {
    private Operator.BinaryOp op;
    private Operand dst, src;

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
    public void putSpill() {
        dst.putSpill();
        src.putSpill();
    }

    @Override
    public void livenessAnalysis() {
        putDef(dst);
        putUse(dst);
        putUse(src);
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertOperand(str, this.dst);
        PhysicalOperand src = convertOperand(str, this.src);
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
                    str.append(formatInstr("idiv", "ler8"));
                } else {
                    str.append(formatInstr("idiv", src.toNASM()));
                }
                str.append(formatInstr("mov", dst.toNASM(), op == DIV ? "res0" : "arg3"));
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
