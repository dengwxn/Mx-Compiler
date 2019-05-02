package IR.Instruction;

import Generator.Operand.PhysicalOperand;
import IR.Operand.Operand;

import static Generator.Operand.PhysicalOperand.convertOperand;
import static IR.Build.IR.formatInstr;

public class UnaryInstruction extends Instruction {
    private Operator.UnaryOp op;
    private Operand dst;

    public UnaryInstruction(Operator.UnaryOp op, Operand dst) {
        this.op = op;
        this.dst = dst;
    }

    @Override
    public void convertVirtualOperand() {
        dst.convertVirtualOperand();
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
        PhysicalOperand dst = convertOperand(str, this.dst, false);
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
