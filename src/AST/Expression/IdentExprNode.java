package AST.Expression;

import AST.Table.Symbol;
import IR.Build.BlockList;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import static IR.Operand.Address.getOffset;

public class IdentExprNode extends ExprNode {
    private String ident;
    private Symbol symbol;
    private Symbol classThis;

    public IdentExprNode(String ident) {
        this.ident = ident;
    }

    @Override
    public void generateIR(BlockList blockList) {
        if (classThis != null) {
            Operand base = classThis.getOperand();
            int offset = getOffset(symbol.getPrevTypeName() + "." + ident);
            operand = new Address((VirtualRegister) base, offset);
        } else if (symbol != null) {
            operand = symbol.getOperand();
        }
    }

    public void setClassThis(Symbol classThis) {
        this.classThis = classThis;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getIdent() {
        return ident;
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(ident);
    }
}
