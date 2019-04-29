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
            Block lhsTrue = new Block("lhsTrue");
            Block lhsFalse = new Block("lhsFalse");
            Block logicExit = new Block("logicExit");
            Instruction jumpLogicExit = new JumpInstruction(logicExit);

            // currentBlock
            lhs.generateIR(blockList);
            Instruction cmpLhs = new CompareInstruction(lhs.getOperand(), 1);
            Instruction cjumpLhsTrue = new CondJumpInstruction(E, lhsTrue);
            Instruction jumpLhsFalse = new JumpInstruction(lhsFalse);
            blockList.add(cmpLhs, cjumpLhsTrue, jumpLhsFalse);

            if (op.equals("&&")) {
                // lhsTrue
                blockList.add(lhsTrue);
                rhs.generateIR(blockList);
                Instruction cmpRhs = new CompareInstruction(rhs.getOperand(), 1);
                Instruction csetRhs = new CondSetInstruction(E, operand);
                blockList.add(cmpRhs, csetRhs, jumpLogicExit);

                // lhsFalse
                blockList.add(lhsFalse);
                Instruction moveFalse = new MoveInstruction(operand, 0);
                blockList.add(moveFalse, jumpLogicExit);
            } else {
                // lhsTrue
                blockList.add(lhsTrue);
                Instruction moveTrue = new MoveInstruction(operand, 1);
                blockList.add(moveTrue, jumpLogicExit);

                // lhsFalse
                blockList.add(lhsFalse);
                rhs.generateIR(blockList);
                Instruction cmpRhs = new CompareInstruction(rhs.getOperand(), 1);
                Instruction csetRhs = new CondSetInstruction(E, operand);
                blockList.add(cmpRhs, csetRhs, jumpLogicExit);
            }
            blockList.add(logicExit);
        } else {
            lhs.generateIR(blockList);
            rhs.generateIR(blockList);
            if (lhs.getType() instanceof StringType) {
                ArrayList<Operand> paramOp = new ArrayList<>(Arrays.asList(lhs.getOperand(), rhs.getOperand()));
                blockList.add(new FuncCallInstruction("string." + convertStringOp(), paramOp));
                blockList.add(new MoveInstruction(operand, "res0"));
            } else if (convertBinaryOp() != null) {
                Instruction mov = new MoveInstruction(operand, lhs.getOperand());
                Instruction binary = new BinaryInstruction(convertBinaryOp(), operand, rhs.getOperand());
                blockList.add(mov, binary);
            } else {
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
