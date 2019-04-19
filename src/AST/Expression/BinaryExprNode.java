package AST.Expression;

import AST.Type.Type;
import IR.Build.Block;
import IR.Instruction.*;
import IR.Operand.Immediate;

import java.util.ArrayList;
import java.util.Arrays;

import static IR.Build.FunctionIR.funcName;
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

    private Operator.BinaryOp convertBinaryOp() {
        switch (op) {
            case "+": return ADD;
            case "-": return SUB;
            case "*": return MUL;
            case "/": return DIV;
            case "%": return MOD;
            case "<<": return SHL;
            case ">>": return SHR;
            case "&": return AND;
            case "|": return OR;
            case "^": return XOR;
            default: return null;
        }
    }

    private Operator.CompareOp convertCompareOp() {
        switch (op) {
            case "==": return EQ;
            case "!=": return NEQ;
            case "<": return LT;
            case "<=": return LE;
            case ">": return GT;
            case ">=": return GE;
            default: return null;
        }
    }

    @Override
    public void generateIR(ArrayList<Block> block) {
        operand = getTemporaryRegister();
        if (op.equals("&&") || op.equals("||")) {
            Block lhsTrue = new Block(funcName + ".lhsTrue", block.size());
            Block lhsFalse = new Block(funcName + ".lhsFalse", block.size() + 1);
            Block logicExit = new Block(funcName + ".logicExit", block.size() + 2);
            Instruction jumpLogicExit = new JumpInstruction(logicExit);

            // currentBlock
            lhs.generateIR(block);
            Instruction cmpLhs = new CompareInstruction(lhs.getOperand(), new Immediate(1));
            Instruction cjumpLhsTrue = new CondJumpInstruction(EQ, lhsTrue);
            Instruction jumpLhsFalse = new JumpInstruction(lhsFalse);
            block.get(block.size() - 1).add(cmpLhs, cjumpLhsTrue, jumpLhsFalse);

            if (op.equals("&&")) {
                // lhsTrue
                block.add(lhsTrue);
                rhs.generateIR(block);
                Instruction cmpRhs = new CompareInstruction(rhs.getOperand(), new Immediate(1));
                Instruction csetRhs = new CondSetInstruction(EQ, operand);
                lhsTrue.add(cmpRhs, csetRhs, jumpLogicExit);

                // lhsFalse
                block.add(lhsFalse);
                Instruction moveFalse = new MoveInstruction(operand, new Immediate(0));
                lhsFalse.add(moveFalse, jumpLogicExit);
            }
            else {
                // lhsTrue
                block.add(lhsTrue);
                Instruction moveTrue = new MoveInstruction(operand, new Immediate(1));
                lhsTrue.add(moveTrue, jumpLogicExit);

                // lhsFalse
                block.add(lhsFalse);
                rhs.generateIR(block);
                Instruction cmpRhs = new CompareInstruction(rhs.getOperand(), new Immediate(1));
                Instruction csetRhs = new CondSetInstruction(EQ, operand);
                lhsFalse.add(cmpRhs, csetRhs, jumpLogicExit);
            }
            block.add(logicExit);
        }
        else {
            lhs.generateIR(block);
            rhs.generateIR(block);
            if (convertBinaryOp() != null) {
                Instruction mov = new MoveInstruction(operand, lhs.getOperand());
                Instruction binary = new BinaryInstruction(convertBinaryOp(), operand, rhs.getOperand());
                block.get(block.size() - 1).add(mov, binary);
            }
            else {
                Instruction cmp = new CompareInstruction(lhs.getOperand(), rhs.getOperand());
                Instruction cset = new CondSetInstruction(convertCompareOp(), operand);
                block.get(block.size() - 1).add(cmp, cset);
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
}
