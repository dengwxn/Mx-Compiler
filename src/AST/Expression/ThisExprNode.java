package AST.Expression;

import AST.Table.Symbol;
import IR.Build.BlockList;

import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class ThisExprNode extends ExprNode {
    private Symbol symbol;

    public ThisExprNode() {
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = getVirtualRegister(symbol);
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("this");
    }
}