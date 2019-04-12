package AST.Expression;

import AST.Table.Symbol;
import IR.Build.Block;
import IR.Operand.Operand;

import java.util.ArrayList;

import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class IdentExprNode extends ExprNode {
    private String ident;
    private Symbol symbol;

    public IdentExprNode(String ident) {
        this.ident = ident;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        operand = getVirtualRegister(symbol);
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
