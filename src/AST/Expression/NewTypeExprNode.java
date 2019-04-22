package AST.Expression;

import AST.Type.ClassType;
import IR.Build.BlockList;
import IR.Instruction.FuncCallInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import java.util.ArrayList;
import java.util.Arrays;

import static AST.Build.Tree.symbolTable;
import static IR.Operand.Address.getOffset;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;

public class NewTypeExprNode extends ExprNode {
    private String base;

    public NewTypeExprNode(String base) {
        this.base = base;
    }

    @Override
    public void generateIR(BlockList blockList) {
        ArrayList<Operand> paramOpMem = new ArrayList<>(Arrays.asList(new Immediate(getOffset(base))));
        Instruction mallocMem = new FuncCallInstruction("malloc", paramOpMem);
        operand = getTemporaryRegister();
        Instruction mov = new MoveInstruction(operand, getVirtualRegister("rax"));
        blockList.add(mallocMem, mov);

        String newType = base + ".null";
        if (symbolTable.getType(newType) != null) {
            ArrayList<Operand> paramOpNew = new ArrayList<>(Arrays.asList(operand));
            Instruction callNew = new FuncCallInstruction(newType, paramOpNew);
            blockList.add(callNew);
        }
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
        System.out.printf("new %s\n", getType().getTypeName());
    }
}
