package AST.Expression;

import AST.Type.Type;
import IR.Build.Block;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;

import java.util.ArrayList;

public class AssignExprNode extends ExprNode {
    private ExprNode lhs, rhs;

    public AssignExprNode(ExprNode lhs, ExprNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        lhs.generateIR(block);
        rhs.generateIR(block);
        Instruction instr = new MoveInstruction(lhs.getOperand(), rhs.getOperand());
        block.get(block.size() - 1).add(instr);
    }

    public Type getLhsType() {
        return lhs == null ? null : lhs.getType();
    }

    public Type getRhsType() {
        return rhs == null ? null : rhs.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println("=:");
        lhs.dump(indent + 4);
        rhs.dump(indent + 4);
    }
}
