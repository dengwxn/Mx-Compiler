package AST.Expression;

import AST.Type.ArrayType;
import IR.Build.Block;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Operand.Address;
import IR.Operand.Immediate;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;

import static IR.Operand.Address.getOffset;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class MemberExprNode extends ExprNode {
    private ExprNode ls;
    private String ident;

    public MemberExprNode(ExprNode ls, String ident) {
        this.ls = ls;
        this.ident = ident;
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        ls.generateIR(block);
        Operand base = ls.getOperand();
        Immediate offset = new Immediate(getOffset(getPrevTypeName() + "." +  ident));
        if (base instanceof VirtualRegister) {
            operand = new Address((VirtualRegister) base, offset);
        } else {
            Operand lsAddr = getTemporaryRegister();
            Instruction movBase = new MoveInstruction(lsAddr, base);
            block.get(block.size() - 1).add(movBase);
            operand = new Address((VirtualRegister) lsAddr, offset);
        }
    }

    public String getIdent() {
        return ident;
    }

    public String getPrevTypeName() {
        if (ls.getType() != null) {
            if (ls.getType() instanceof ArrayType)
                return "";
            else
                return ls.getType().getTypeName();
        } else
            return null;
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void dump(int indent) {
        if (ls != null) {
            ls.dump(indent);
            format(indent + 4);
            System.out.printf(".%s\n", ident);
        } else {
            System.out.println(ident);
        }
    }
}
