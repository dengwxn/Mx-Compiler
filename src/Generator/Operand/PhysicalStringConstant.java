package Generator.Operand;

public class PhysicalStringConstant extends PhysicalOperand {
    private String str;

    PhysicalStringConstant(String str) {
        this.str = str;
    }

    @Override
    public String toNASM() { return str; }
}
