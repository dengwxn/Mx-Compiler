package Generator.Operand;

public class PhysicalImmediate extends PhysicalOperand {
    private int str;

    PhysicalImmediate(int str) {
        this.str = str;
    }

    @Override
    public String toNASM() { return String.valueOf(str); }
}
