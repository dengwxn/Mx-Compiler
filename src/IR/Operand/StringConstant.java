package IR.Operand;

public class StringConstant extends Operand {
    private String str;

    public StringConstant(String str) {
        this.str = str;
    }

    @Override
    public String dump() {
        return str;
    }
}
