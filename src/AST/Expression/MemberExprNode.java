package AST.Expression;

import AST.Type.ArrayType;
import IR.Build.BlockList;
import IR.Instruction.Instruction;
import IR.Instruction.MoveInstruction;
import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

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
    public void generateIR(BlockList blockList) {
        ls.generateIR(blockList);
        Operand base = ls.getOperand();
        int offset = getOffset(getPrevTypeName() + "." + ident);
        if (base instanceof VirtualRegister) {
            operand = new Address((VirtualRegister) base, offset);
        } else {
            VirtualRegister lsAddr = getTemporaryRegister();
            Instruction movBase = new MoveInstruction(lsAddr, base);
            blockList.add(movBase);
            operand = new Address(lsAddr, offset);
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
