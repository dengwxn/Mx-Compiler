package IR.Instruction;

import Generator.Operand.PhysicalOperand;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.HashMap;
import java.util.HashSet;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.FunctionIR.*;
import static IR.Build.IR.formatInstr;

public class UnaryInstruction extends Instruction implements ConstantFolding, DeadCodeElimination, ValueNumbering {
    private Operator.UnaryOp op;
    private Operand dst;
    private Integer cstVal;
    private Operand prop;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
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
                String instr = op + " " + x;
                Integer y = getMapInstr(instr);
                if (y != null) {
                    putMapReg(dst, y);
                    prop = getMapVal(y);
                    if (prop != null) return;
                }
                incValueNumberingCount();
                putMapInstr(instr, getValueNumberingCount());
                putMapReg(dst, getValueNumberingCount());
                putMapVal(getValueNumberingCount(), dst);
                return;
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
    }

    @Override
    public void collectGlobalVar(HashSet<Address> globalVar) {
        collectGlobalVar(dst, globalVar);
    }

    @Override
    public Instruction makeCopy() {
        return new UnaryInstruction(op, makeCopy(dst));
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
        if (dst instanceof Address)
            putNec(dst);
    }

    @Override
    public boolean foldToConstant() {
        if (cstVal != null)
            return false;
        Integer val = getConstant(dst);
        if (val != null) {
            switch (op) {
                case INC:
                    cstVal = val + 1;
                    break;
                case DEC:
                    cstVal = val - 1;
                    break;
                case NEG:
                    cstVal = -val;
                    break;
                case NOT:
                    cstVal = ~val;
            }
            return true;
        }
        return false;
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
    }

    @Override
    public void putUse() {
        putUse(dst);
    }

    @Override
    public void putDef() {
        putDef(dst);
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        PhysicalOperand dst = convertVirtualOperand(str, this.dst, false);
        str.append(formatInstr(op.toString().toLowerCase(), dst.toNASM()));
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(formatInstr(op.toString().toLowerCase(), dst.toString()));
        return str.toString();
    }
}
