package AST.Expression;

import AST.Type.Type;
import IR.Build.BlockList;
import IR.Instruction.BinaryInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Operand.Address;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;

import static IR.Instruction.Operator.BinaryOp.SHL;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class ArrayExprNode extends ExprNode {
    private ExprNode name;
    private int dim;
    private ArrayList<ExprNode> param;

    public ArrayExprNode(int dim) {
        this.dim = dim;
        this.param = new ArrayList<>();
    }

    @Override
    public void generateIR(BlockList blockList) {
        name.generateIR(blockList);
        operand = getTemporaryRegister();
        VirtualRegister offset = getTemporaryRegister();
        Instruction base = new MoveInstruction(operand, name.getOperand());
        blockList.add(base);
        for (int i = 0; i < dim; ++i) {
            param.get(i).generateIR(blockList);
            Instruction movOffset = new MoveInstruction(offset, param.get(i).getOperand());
            Instruction shl = new BinaryInstruction(SHL, offset, 3);
            Address addr = new Address((VirtualRegister) operand, offset);
            blockList.add(movOffset, shl);
            if (i < dim - 1) {
                Instruction movOperand = new MoveInstruction(operand, addr);
                blockList.add(movOperand);
            } else {
                operand = addr;
            }
        }
    }

    public int getDim() {
        return dim;
    }

    public Type getNameType() {
        return name.getType();
    }

    public void addExpr(ExprNode e) {
        if (name == null) name = e;
        else param.add(e);
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void dump(int indent) {
        name.dump(indent);
        param.forEach(p -> p.dump(indent + 4));
    }
}
