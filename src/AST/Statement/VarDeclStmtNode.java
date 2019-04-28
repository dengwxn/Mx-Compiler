package AST.Statement;

import AST.Expression.ExprNode;
import AST.Table.Symbol;
import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Operand.Operand;

public class VarDeclStmtNode extends StmtNode {
    private String type, name;
    private ExprNode expr;
    private Symbol symbol;

    public VarDeclStmtNode(String type, String name, ExprNode expr) {
        this.type = type;
        this.name = name;
        this.expr = expr;
    }

    public String getSymbolName() {
        return symbol.getName();
    }

    public void setGlobal() {
        symbol.setGlobal();
    }

    @Override
    public void generateIR(BlockList blockList) {
        symbol.setOperand();
        if (expr != null) {
            expr.generateIR(blockList);
            Operand dst = symbol.getOperand();
            Instruction mov = new MoveInstruction(dst, expr.getOperand());
            blockList.add(mov);
        }
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Type getExprType() {
        return expr == null ? null : expr.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s %s:\n", type, name);
        if (expr != null)
            expr.dump(indent + 4);
    }
}
