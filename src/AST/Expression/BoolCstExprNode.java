package AST.Expression;

import IR.Build.Block;
import IR.Operand.Immediate;

import java.util.ArrayList;

public class BoolCstExprNode extends ExprNode {
    boolean val;

    public BoolCstExprNode(boolean val) {
        this.val = val;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        operand = new Immediate(val ? 1 : 0);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
