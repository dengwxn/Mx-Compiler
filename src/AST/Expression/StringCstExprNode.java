package AST.Expression;

import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;

import static IR.Build.IR.putStringConst;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class StringCstExprNode extends ExprNode {
    private String str;

    public StringCstExprNode(String str) {
        this.str = str;
    }

    @Override
    public void generateIR(BlockList blockList) {
        int id = putStringConst(str);
        operand = getTemporaryRegister();
        Instruction move = new MoveInstruction(operand, "_string_constant_" + id);
        blockList.add(move);
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.println(str);
    }
}
