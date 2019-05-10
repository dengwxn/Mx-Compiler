package AST.Expression;

import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.CompareInstruction;
import IR.Instruction.CondJumpInstruction;
import IR.Instruction.JumpInstruction;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import static IR.Instruction.Operator.CompareOp.E;

public class BoolCstExprNode extends ExprNode {
    private static Block trueRecur, falseRecur;
    private boolean val;

    public BoolCstExprNode(boolean val) {
        this.val = val;
    }

    public static boolean hasLogicRecur() {
        return trueRecur != null;
    }

    public static void clearLogicRecur() {
        trueRecur = falseRecur = null;
    }

    public static Block getTrueRecur() {
        return trueRecur;
    }

    static void setTrueRecur(Block trueRecur) {
        BoolCstExprNode.trueRecur = trueRecur;
    }

    public static Block getFalseRecur() {
        return falseRecur;
    }

    static void setFalseRecur(Block falseRecur) {
        BoolCstExprNode.falseRecur = falseRecur;
    }

    static void swapLogicRecur() {
        Block swap = trueRecur;
        trueRecur = falseRecur;
        falseRecur = swap;
    }

    public static void generateLogicRecur(BlockList blockList, Operand op) {
        if (!hasLogicRecur()) {
            trueRecur = new Block("logicTrue");
            falseRecur = new Block("logicFalse");
            blockList.add(new CompareInstruction(op, 1));
            blockList.add(new CondJumpInstruction(E, trueRecur));
            blockList.add(new JumpInstruction(falseRecur));
        }
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = new Immediate(val ? 1 : 0);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(val);
    }
}
