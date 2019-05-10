package IR.Instruction;

public class Operator {
    public static boolean getCompare(CompareOp op, int lhs, int rhs) {
        switch (op) {
            case E:
                return lhs == rhs;
            case NE:
                return lhs != rhs;
            case L:
                return lhs < rhs;
            case LE:
                return lhs <= rhs;
            case G:
                return lhs > rhs;
            case GE:
                return lhs >= rhs;
            case NL:
                return !(lhs < rhs);
            case NG:
                return !(lhs > rhs);
            case NLE:
                return !(lhs <= rhs);
            case NGE:
                return !(lhs >= rhs);
        }
        throw new Error("CompareOp undefined.");
    }

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
