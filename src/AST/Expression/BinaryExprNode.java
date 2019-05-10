package AST.Expression;

import AST.Type.StringType;
import AST.Type.Type;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.*;
import IR.Operand.Operand;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.Arrays;

import static AST.Expression.BoolCstExprNode.*;
import static IR.Instruction.Operator.BinaryOp.*;
import static IR.Instruction.Operator.CompareOp.*;
import static IR.Operand.VirtualRegisterTable.getTemporaryRegister;

public class BinaryExprNode extends ExprNode {
    private String op;
    private ExprNode lhs, rhs;

    public BinaryExprNode(String op, ExprNode lhs, ExprNode rhs) {
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    private String convertStringOp() {
        if (op.equals("+")) return "add";
        else return convertCompareOp().toString().toLowerCase();
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = getTemporaryRegister();
        if (op.equals("&&") || op.equals("||")) {
            lhs.generateIR(blockList);
            generateLogicRecur(blockList, lhs.getOperand());
            Block lhsTrue = getTrueRecur();
            Block lhsFalse = getFalseRecur();
            clearLogicRecur();

            if (op.equals("&&")) {
                lhsTrue.setLabel("lhsTrue");
                blockList.add(lhsTrue);
                rhs.generateIR(blockList);
                generateLogicRecur(blockList, rhs.getOperand());
                Block rhsTrue = getTrueRecur();
                Block rhsFalse = getFalseRecur();

                rhsFalse.setLabel("rhsFalse");
                blockList.add(rhsFalse);
                blockList.add(new JumpInstruction(lhsFalse));
                rhsTrue.setLabel("logicTrue");
                lhsFalse.setLabel("logicFalse");
                setTrueRecur(rhsTrue);
                setFalseRecur(lhsFalse);
            } else {
                lhsFalse.setLabel("lhsFalse");
                blockList.add(lhsFalse);
                rhs.generateIR(blockList);
                generateLogicRecur(blockList, rhs.getOperand());
                Block rhsTrue = getTrueRecur();
                Block rhsFalse = getFalseRecur();

                rhsTrue.setLabel("rhsTrue");
                blockList.add(rhsTrue);
                blockList.add(new JumpInstruction(lhsTrue));
                lhsTrue.setLabel("logicTrue");
                rhsFalse.setLabel("logicFalse");
                setTrueRecur(lhsTrue);
                setFalseRecur(rhsFalse);
            }
        } else {
            lhs.generateIR(blockList);
            rhs.generateIR(blockList);
            if (lhs.getType() instanceof StringType) {
                // string
                ArrayList<Operand> paramOp = new ArrayList<>(Arrays.asList(lhs.getOperand(), rhs.getOperand()));
                int argCnt = FuncCallInstruction.moveArg(blockList, paramOp);
                blockList.add(new FuncCallInstruction("string." + convertStringOp(), paramOp, argCnt));
                blockList.add(new MoveInstruction(operand, "res0"));
            } else if (convertBinaryOp() != null) {
                // arithmetic
                Instruction mov = new MoveInstruction(operand, lhs.getOperand());
                Instruction binary = new BinaryInstruction(convertBinaryOp(), operand, rhs.getOperand());
                blockList.add(mov, binary);
            } else {
                // relation
                VirtualRegister tmp = getTemporaryRegister();
                Instruction mov = new MoveInstruction(tmp, lhs.getOperand());
                Instruction cmp = new CompareInstruction(tmp, rhs.getOperand());
                Instruction cset = new CondSetInstruction(convertCompareOp(), operand);
                blockList.add(mov, cmp, cset);
            }
        }
    }

    public Type getLhsType() {
        return lhs == null ? null : lhs.getType();
    }

    public Type getRhsType() {
        return rhs == null ? null : rhs.getType();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s:\n", op);
        lhs.dump(indent + 4);
        rhs.dump(indent + 4);
    }

    private Operator.BinaryOp convertBinaryOp() {
        switch (op) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            case "%":
                return MOD;
            case "<<":
                return SHL;
            case ">>":
                return SHR;
            case "&":
                return AND;
            case "|":
                return OR;
            case "^":
                return XOR;
            default:
                return null;
        }
    }

    private Operator.CompareOp convertCompareOp() {
        switch (op) {
            case "==":
                return E;
            case "!=":
                return NE;
            case "<":
                return L;
            case "<=":
                return LE;
            case ">":
                return G;
            case ">=":
                return GE;
            default:
                return null;
        }
    }
}
