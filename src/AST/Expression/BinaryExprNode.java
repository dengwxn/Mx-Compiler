package AST.Expression;

import AST.Type.Type;
import IR.Build.Block;
import IR.Build.BlockList;
import IR.Instruction.*;

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
                return EQ;
            case "!=":
                return NEQ;
            case "<":
                return LT;
            case "<=":
                return LE;
            case ">":
                return GT;
            case ">=":
                return GE;
            default:
                return null;
        }
    }

    @Override
    public void generateIR(BlockList blockList) {
        operand = getTemporaryRegister();
        if (op.equals("&&") || op.equals("||")) {
            Block lhsTrue = new Block(funcName + ".lhsTrue");
            Block lhsFalse = new Block(funcName + ".lhsFalse");
            Block logicExit = new Block(funcName + ".logicExit");
            Instruction jumpLogicExit = new JumpInstruction(logicExit);

            // currentBlock
            lhs.generateIR(blockList);
            Instruction cmpLhs = new CompareInstruction(lhs.getOperand(), 1);
            Instruction cjumpLhsTrue = new CondJumpInstruction(EQ, lhsTrue);
            Instruction jumpLhsFalse = new JumpInstruction(lhsFalse);
            blockList.add(cmpLhs, cjumpLhsTrue, jumpLhsFalse);

            if (op.equals("&&")) {
                // lhsTrue
                blockList.add(lhsTrue);
                rhs.generateIR(blockList);
                Instruction cmpRhs = new CompareInstruction(rhs.getOperand(), 1);
                Instruction csetRhs = new CondSetInstruction(EQ, operand);
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
                Instruction csetRhs = new CondSetInstruction(EQ, operand);
                blockList.add(cmpRhs, csetRhs, jumpLogicExit);
            }
            blockList.add(logicExit);
        } else {
            lhs.generateIR(blockList);
            rhs.generateIR(blockList);
            if (convertBinaryOp() != null) {
                Instruction mov = new MoveInstruction(operand, lhs.getOperand());
                Instruction binary = new BinaryInstruction(convertBinaryOp(), operand, rhs.getOperand());
                blockList.add(mov, binary);
            } else {
                Instruction cmp = new CompareInstruction(lhs.getOperand(), rhs.getOperand());
                Instruction cset = new CondSetInstruction(convertCompareOp(), operand);
                blockList.add(cmp, cset);
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
