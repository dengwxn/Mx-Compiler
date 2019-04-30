package IR.Instruction;

public class Operator {
    public enum BinaryOp {
        ADD, SUB, MUL, DIV, MOD, SHL, SHR, AND, OR, XOR,
    }

    public enum CompareOp {
        E, NE, L, LE, G, GE,
        NL, NLE, NG, NGE
    }

    public enum UnaryOp {
        INC, DEC, NEG, NOT
    }
}
