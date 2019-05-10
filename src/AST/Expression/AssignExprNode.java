package AST.Expression;

import AST.Type.Type;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.JumpInstruction;
import IR.Instruction.MoveInstruction;

import static AST.Expression.BoolCstExprNode.*;
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
        if (hasLogicRecur()) {
            Block trueRecur = getTrueRecur();
            Block falseRecur = getFalseRecur();
            clearLogicRecur();
            Block logicExit = new Block("logicExit");

            blockList.add(trueRecur);
            blockList.add(new MoveInstruction(operand, 1));
            blockList.add(new JumpInstruction(logicExit));

            blockList.add(falseRecur);
            blockList.add(new MoveInstruction(operand, 0));
            blockList.add(new JumpInstruction(logicExit));

            blockList.add(logicExit);
        } else {
            blockList.add(new MoveInstruction(operand, rhs.getOperand()));
        }
        blockList.add(new MoveInstruction(lhs.getOperand(), operand));
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
