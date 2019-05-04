package IR.Build;

import IR.Instruction.*;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import java.util.ArrayList;

import static IR.Instruction.Operator.CompareOp.*;

public class Block {
    static final private boolean _DEBUG_REDUNDANT_ADJACENT_MOVE = false;
    static final private boolean _DEBUG_REDUNDANT_SELF_MOVE_ = false;

    private String label;
    private ArrayList<Instruction> instr;
    private int id;
    private JumpInstruction jump;

    public Block(String label) {
        this.label = label;
        this.instr = new ArrayList<>();
    }

    static private Operator.CompareOp getNot(Operator.CompareOp op) {
        switch (op) {
            case L:
                return NL;
            case LE:
                return NLE;
            case G:
                return NG;
            case GE:
                return NGE;
            case E:
                return NE;
            case NE:
                return E;
            default:
                throw new Error("unexpected CompareOp.");
        }
    }

    void constantFolding() {
        for (int i = 0; i < instr.size(); ++i) {
            Instruction u = instr.get(i);
            if (u instanceof ConstantFolding) {
                Operand dst = ((ConstantFolding) u).getDst();
                Integer cstVal = ((ConstantFolding) u).getCstVal();
                if (cstVal != null)
                    instr.set(i, new MoveInstruction(dst, cstVal));
            }
        }
    }

    ArrayList<Instruction> getInstr() {
        return instr;
    }

    private void link(Instruction u, Instruction v) {
        if (u != null && v != null) {
            u.linkSuc(v);
            v.linkPre(u);
        }
    }

    void linkPreSuc() {
        for (int i = 0; i < instr.size(); ++i) {
            Instruction u = instr.get(i);
            if (i < instr.size() - 1) link(u, instr.get(i + 1));
            if (u instanceof Jump)
                link(u, ((Jump) u).getDst().getHead());
        }
    }

    String dumpLivenessAnalysis() {
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        for (Instruction i : instr) {
            str.append(i.toString());
            str.append(i.dumpLive());
        }
        return str.toString();
    }

    private Instruction getHead() {
        return instr.size() > 0 ? instr.get(0) : null;
    }

    void setFuncName(String funcName) {
        if (label.equals("")) label = funcName;
        else label = funcName + "." + label;
    }

    void setId(int id) {
        this.id = id;
    }

    void set(int id, Instruction instr) {
        this.instr.set(id, instr);
    }

    void add(int id, Instruction instr) {
        this.instr.add(id, instr);
    }

    public void add(Instruction... il) {
        for (Instruction i : il) {
            if (jump != null) break;
            instr.add(i);
            if (i instanceof JumpInstruction)
                jump = (JumpInstruction) i;

            if (instr.size() >= 3) {
                if (instr.get(instr.size() - 3) instanceof CondSetInstruction
                        && instr.get(instr.size() - 2) instanceof CompareInstruction
                        && instr.get(instr.size() - 1) instanceof CondInstruction) {

                    CondSetInstruction condSet = (CondSetInstruction) instr.get(instr.size() - 3);
                    CompareInstruction cmp = (CompareInstruction) instr.get(instr.size() - 2);

                    if (condSet.getDst() == cmp.getLhs()
                            && cmp.getRhs() instanceof Immediate
                            && ((Immediate) cmp.getRhs()).getVal() == 1) {

                        CondInstruction cond = (CondInstruction) instr.get(instr.size() - 1);
                        cond.setOp(condSet.getOp());
                        instr.remove(instr.size() - 2);
                        instr.remove(instr.size() - 2);
                    }
                }
            }
        }
    }

    public String getLabel() {
        if (id == 0) return label;
        else return label + "." + id;
    }

    public String toNASM(Block nextBlock) {
        detectRedundantJump(nextBlock);
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        for (int i = 0; i < instr.size(); ++i) {
            if (detectRedundantAdjacentMove(i)) continue;
            if (detectRedundantSelfMove(i)) continue;
            str.append(instr.get(i).toNASM());
        }
        return str.toString();
    }

    private void detectRedundantJump(Block nextBlock) {
        if (instr.size() > 1 && instr.get(instr.size() - 2) instanceof CondJumpInstruction) {
            CondJumpInstruction cjump = (CondJumpInstruction) instr.get(instr.size() - 2);
            if (cjump.getDst() == nextBlock) {
                cjump.setOp(getNot(cjump.getOp()));
                cjump.setDst(jump.getDst());
                instr.remove(instr.size() - 1);
                jump = null;
            }
        }
        if (jump != null && jump.getDst() == nextBlock)
            instr.remove(instr.size() - 1);
    }

    private boolean detectRedundantAdjacentMove(int id) {
        if (!_DEBUG_REDUNDANT_ADJACENT_MOVE) {
            if (id < instr.size() - 1) {
                if (instr.get(id) instanceof MoveInstruction
                        && instr.get(id + 1) instanceof MoveInstruction) {
                    String x = ((MoveInstruction) instr.get(id)).getPhyDst();
                    String y = ((MoveInstruction) instr.get(id + 1)).getPhyDst();
                    String z = ((MoveInstruction) instr.get(id + 1)).getPhySrc();
                    if (x != null && x.equals(y))
                        return !x.equals(z);
                }
            }
        }
        return false;
    }

    private boolean detectRedundantSelfMove(int id) {
        if (!_DEBUG_REDUNDANT_SELF_MOVE_) {
            if (instr.get(id) instanceof MoveInstruction) {
                String x = ((MoveInstruction) instr.get(id)).getPhyDst();
                String y = ((MoveInstruction) instr.get(id)).getPhySrc();
                if (x != null)
                    return x.equals(y);
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        instr.forEach(i -> str.append(i.toString()));
        return str.toString();
    }
}
