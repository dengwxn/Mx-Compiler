package AST.Expression;

import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;

import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class AssignExprNode extends ExprNode {
    private ExprNode lhs, rhs;

    public AssignExprNode(ExprNode lhs, ExprNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void generateIR(BlockList blockList) {
        lhs.generateIR(blockList);
        rhs.generateIR(blockList);
        operand = getTemporaryRegister();
        Instruction cpy = new MoveInstruction(operand, rhs.getOperand());
        Instruction mov = new MoveInstruction(lhs.getOperand(), operand);
        blockList.add(cpy, mov);
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
