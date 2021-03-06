package IR.Operand;

public class Immediate extends Operand {
    private int val;

    public Immediate(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
