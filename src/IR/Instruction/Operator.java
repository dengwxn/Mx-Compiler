package IR.Instruction;

public class Operator {
    public enum BinaryOp {
        ADD, SUB, MUL, DIV, MOD, SHL, SHR, AND, OR, XOR
    }

    public enum ConditonOp {
        EQ, NEQ, LT, LE, GT, GE
    }

    public enum UnaryOp {
        INC, DEC, NEG, NOT
    }
}
