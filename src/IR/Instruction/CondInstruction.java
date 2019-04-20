package IR.Instruction;

abstract public class CondInstruction extends Instruction {
    protected Operator.CompareOp op;

    public void setOp(Operator.CompareOp op) {
        this.op = op;
    }
}
