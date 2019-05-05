package IR.Operand;

abstract public class Operand {
    static public Operand convertCopyOperand(Operand old, VirtualRegister cpy, VirtualRegister reg) {
        if (old instanceof VirtualRegister) {
            return old == cpy ? reg : old;
        } else if (old instanceof Address) {
            if (((Address) old).getBase() == cpy)
                ((Address) old).setBase(reg);
            return old;
        } else
            return old;
    }

    public void assignPhysicalOperand() {
    }

    abstract public String toString();
}
