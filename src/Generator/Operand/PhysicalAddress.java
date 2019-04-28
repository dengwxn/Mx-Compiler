package Generator.Operand;

public class PhysicalAddress extends PhysicalOperand {
    private String str;
    private int offset;

    public PhysicalAddress(String str, int offset) {
        this.str = str;
        this.offset = offset;
    }

    @Override
    public String toNASM() { return "qword [" + str + " + " + offset + "]"; }
}
