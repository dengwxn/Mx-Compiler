package AST.Expression;

import IR.Build.BlockList;
import IR.Operand.Immediate;

public class NumberCstExprNode extends ExprNode {
    private int val;

    public NumberCstExprNode(int val) {
        this.val = val;
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = new Immediate(val);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%d\n", val);
    }
}
