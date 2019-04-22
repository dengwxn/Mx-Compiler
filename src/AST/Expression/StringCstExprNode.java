package AST.Expression;

import IR.Build.BlockList;
import IR.Operand.StringConstant;

import static IR.Build.IR.putStringConst;

public class StringCstExprNode extends ExprNode {
    private String str;

    public StringCstExprNode(String str) {
        this.str = str;
    }

    @Override
    public void generateIR(BlockList blockList) {
        int id = putStringConst(str);
        operand = new StringConstant("_string_constant_" + id);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(str);
    }
}
