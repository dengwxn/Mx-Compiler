package IR.Operand;

public class StringConstant extends Operand {
    private String str;

    public StringConstant(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
