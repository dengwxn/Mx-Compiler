package IR.Operand;

public class Immediate extends Operand {
    int val;

    public Immediate(int val) {
        this.val = val;
    }

    @Override
    public String dump() {
        return String.valueOf(val);
    }
}
