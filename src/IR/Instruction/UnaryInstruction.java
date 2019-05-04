package IR.Instruction;

import Generator.Operand.PhysicalOperand;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.IR.formatInstr;

public class UnaryInstruction extends Instruction implements ConstantFolding {
    private Operator.UnaryOp op;
    private Operand dst;
    private Integer cstVal;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
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
