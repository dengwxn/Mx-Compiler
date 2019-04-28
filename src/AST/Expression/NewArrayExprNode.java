package AST.Expression;

import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.*;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.Arrays;

import static IR.Instruction.Operator.BinaryOp.ADD;
import static IR.Instruction.Operator.BinaryOp.SHL;
import static IR.Instruction.Operator.CompareOp.L;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class NewArrayExprNode extends ExprNode {
    private String base;
    private int dim;
    private ArrayList<ExprNode> param;

    public NewArrayExprNode(String base, int dim) {
        this.base = base;
        this.dim = dim;
        this.param = new ArrayList<>();
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = getTemporaryRegister();
        int paramSize = param.size();
        Block[] newBodyList = new Block[paramSize];
        Block[] newCondList = new Block[paramSize];
        Operand[] lastList = new Operand[paramSize];
        VirtualRegister[] headList = new VirtualRegister[paramSize];
        lastList[0] = operand;
        for (int i = 0; i < paramSize; ++i) {
            param.get(i).generateIR(blockList);
            newBodyList[i] = new Block("new.body");
            newCondList[i] = new Block("new.cond");
            headList[i] = getTemporaryRegister();
        }
        blockList.add(new JumpInstruction(newBodyList[0]));
        Block newExit = new Block("new.exit");

        for (int i = 0; i < paramSize; ++i) {
            VirtualRegister len = getTemporaryRegister();
            Block newBody = newBodyList[i];
            Block newCond = newCondList[i];
            Operand size = param.get(i).getOperand();

            // newBody
            blockList.add(newBody);
            blockList.add(new MoveInstruction(len, size));
            blockList.add(new BinaryInstruction(ADD, len, 1));
            blockList.add(new BinaryInstruction(SHL, len, 3));
            ArrayList<Operand> paramOp = new ArrayList<>(Arrays.asList(len));
            blockList.add(new FuncCallInstruction("malloc", paramOp));
            VirtualRegister ptr = getTemporaryRegister();
            blockList.add(new MoveInstruction(ptr, "res0"));

            blockList.add(new MoveInstruction(new Address(ptr, 0), size));
            VirtualRegister head = headList[i];
            blockList.add(new MoveInstruction(head, ptr));
            blockList.add(new BinaryInstruction(ADD, head, 8));
            blockList.add(new MoveInstruction(lastList[i], head));

            if (i < paramSize - 1) {
                VirtualRegister tail = getTemporaryRegister();
                blockList.add(new MoveInstruction(tail, ptr));
                blockList.add(new BinaryInstruction(ADD, tail, len));
                blockList.add(new JumpInstruction(newCond));

                blockList.add(newCond);
                lastList[i + 1] = new Address(head, 0);
                blockList.add(new CompareInstruction(head, tail));
                blockList.add(new CondJumpInstruction(L, newBodyList[i + 1]));
            }

            if (i > 0) {
                blockList.add(new BinaryInstruction(ADD, headList[i - 1], 8));
                blockList.add(new JumpInstruction(newCondList[i - 1]));
            } else {
                blockList.add(new JumpInstruction(newExit));
            }
        }
        blockList.add(newExit);
    }

    public int getDim() {
        return dim;
    }

    public void addParam(ExprNode p) {
        param.add(p);
    }

    public String getBase() {
        return base;
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("new %s:\n", base);
        for (ExprNode expr : param)
            expr.dump(indent + 4);
    }
}
