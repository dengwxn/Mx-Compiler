package Generator.Operand;

public class PhysicalRegister extends PhysicalOperand {
    private String str;

    PhysicalRegister(String str) {
        this.str = str;
    }

    @Override
    public String toNASM() { return str; }
}
