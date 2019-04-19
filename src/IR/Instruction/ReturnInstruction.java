package IR.Instruction;

import IR.Operand.Operand;

public class ReturnInstruction extends Instruction {
    private Operand val;

    public ReturnInstruction(Operand val) {
        this.val = val;
    }

    @Override
    public String dump() {
        StringBuilder str = new StringBuilder();
        str.append("ret" + "\t\t" + (val != null ? val.dump() : "") + "\n");
        return str.toString();
    }
}
