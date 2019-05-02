package Generator.Operand;

import static IR.Build.IR.translateRegister;

public class PhysicalAddress extends PhysicalOperand {
    private String str;
    private int offset;

    public PhysicalAddress(String str, int offset) {
        if (offset == -1)
            throw new Error("offset must not be -1.");
        this.str = str;
        this.offset = offset;
    }

    @Override
    public String toNASM() {
        return "qword [" + translateRegister(str) + " + " + offset + "]";
    }
}
