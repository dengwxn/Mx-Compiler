package AST.Expression;

import IR.Build.BlockList;
import IR.Operand.Immediate;

public class NullCstExprNode extends ExprNode {
    public NullCstExprNode() {
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = new Immediate(0);
    }

    public void dump(int indent) {
    }
}
