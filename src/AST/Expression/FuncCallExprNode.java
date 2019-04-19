package AST.Expression;

import AST.Type.FuncType;
import AST.Type.Type;
import IR.Build.Block;
import IR.Instruction.FuncCallInstruction;
import IR.Instruction.Instruction;
import IR.Operand.Operand;

import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode {
    private ExprNode func;
    private ArrayList<ExprNode> param;

    public FuncCallExprNode() {
        param = new ArrayList<>();
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        param.forEach(p -> p.generateIR(block));
        ArrayList<Operand> paramOp = new ArrayList<>();
        param.forEach(p -> paramOp.add(p.getOperand()));
        Instruction call = new FuncCallInstruction(getFuncName(), paramOp);
        block.get(block.size() - 1).add(call);
    }

    public String getFuncName() {
        return ((FuncType) getFuncType()).getFuncName();
    }

    public void addExpr(ExprNode e) {
        if (func == null) func = e;
        else param.add(e);
    }

    public ArrayList<ExprNode> getParam() {
        return param;
    }

    public Type getFuncType() {
        return func == null ? null : func.getType();
    }

    @Override
    public void dump(int indent) {
        func.dump(indent);
        format(indent + 4);
        System.out.printf("%s():\n", getFuncName());
        param.forEach(p -> p.dump(indent + 4));
    }
}
