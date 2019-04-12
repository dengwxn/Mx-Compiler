package AST.Expression;

import IR.Build.Block;
import IR.Operand.Immediate;

import java.util.ArrayList;

public class NumberCstExprNode extends ExprNode {
    private int val;

    public NumberCstExprNode(int val) {
        this.val = val;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        operand = new Immediate(val);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%d\n", val);
    }
}
