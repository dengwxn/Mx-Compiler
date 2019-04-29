package AST.Table;

import IR.Operand.Address;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import static IR.Operand.VirtualRegisterTable.getVirtualRegister;
import static Optimizer.RegisterAllocation.putPhysicalRegister;

public class Symbol {
    private String name, prevTypeName;
    private boolean isGlobal;
    private Operand operand;

    public Symbol(String name) {
        this.name = name;
    }

    Symbol(String name, String prevTypeName) {
        this.name = name;
        this.prevTypeName = prevTypeName;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand() {
        if (isGlobal) {
            name = "@" + name;
            VirtualRegister base = getVirtualRegister(this);
            putPhysicalRegister(base, name);
            operand = new Address(base, 0);
        }
        else {
            operand = getVirtualRegister(this);
        }
    }

    public void setGlobal() {
        isGlobal = true;
    }

    public String getPrevTypeName() {
        return prevTypeName;
    }

    public boolean isInClassDeclScope() {
        return prevTypeName != null;
    }

    public String getName() {
        return name;
    }
}
