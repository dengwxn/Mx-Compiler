package Generator.Operand;

import IR.Operand.*;

import static IR.Build.FunctionIR.getSpillPos;
import static IR.Build.IR.formatInstr;
import static Optimizer.RegisterAllocation.getPhysicalRegister;

abstract public class PhysicalOperand {
    static public PhysicalOperand convertOperand(StringBuilder str, Operand operand) {
        if (operand instanceof Immediate) {
            return new PhysicalImmediate(((Immediate) operand).getVal());
        } else if (operand instanceof StringConstant) {
            return new PhysicalStringConstant(((StringConstant) operand).getStr());
        } else if (operand instanceof VirtualRegister) {
            VirtualRegister reg = (VirtualRegister) operand;
            if (getPhysicalRegister(reg) != null) {
                return new PhysicalRegister(getPhysicalRegister(reg));
            } else {
                return new PhysicalAddress("rsp", getSpillPos(reg));
            }
        } else {
            Address addr = (Address) operand;
            if (getPhysicalRegister(addr.getBase()) != null) {
                String base = getPhysicalRegister(addr.getBase());
                int offset = addr.getOffset();
                return new PhysicalAddress(base, offset);
            } else {
                PhysicalAddress base = new PhysicalAddress("rsp", getSpillPos(addr.getBase()));
                str.append(formatInstr("mov", "ler8", base.toNASM()));
                return new PhysicalAddress("ler8", addr.getOffset());
            }
        }
    }

    abstract public String toNASM();
}
