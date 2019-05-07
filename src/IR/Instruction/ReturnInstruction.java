package IR.Instruction;

import static IR.Build.IR.formatInstr;

public class ReturnInstruction extends Instruction {
    @Override
    public Instruction makeCopy() {
        return new ReturnInstruction();
    }

    @Override
    public void putNec() { putNec("res0"); }

    @Override
    public void putUse() { putUse("res0"); }

    @Override
    public String toNASM() {
        return formatInstr("ret");
    }

    @Override
    public String toString() {
        return formatInstr("ret");
    }
}
