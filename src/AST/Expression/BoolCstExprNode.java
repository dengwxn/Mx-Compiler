package AST.Expression;

import IR.Build.BlockList;
import IR.Operand.Immediate;

public class BoolCstExprNode extends ExprNode {
    private boolean val;

    public BoolCstExprNode(boolean val) {
        this.val = val;
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = new Immediate(val ? 1 : 0);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
