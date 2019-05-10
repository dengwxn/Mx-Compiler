package IR.Build;

import IR.Instruction.*;
import IR.Operand.Immediate;
import IR.Operand.Operand;

import java.util.ArrayList;

import static IR.Instruction.Operator.BinaryOp.SHL;
import static IR.Instruction.Operator.CompareOp.*;
import static IR.Instruction.Operator.getCompare;

public class Block {
    private static final boolean _DEBUG_REDUNDANT_ADJACENT_MOVE = false;
    private static final boolean _DEBUG_REDUNDANT_SELF_MOVE_ = false;

    private String label;
    private ArrayList<Instruction> instr;
    private int id;
    private JumpInstruction jump;

    public Block(Block cpy) {
        this.label = cpy.label;
        this.instr = new ArrayList<>();
        for (Instruction instr : cpy.instr) {
            if (!(instr instanceof ReturnInstruction))
                this.instr.add(instr.makeCopy());
        }
        id = -cpy.id;
        jump = null;
    }

    public Block(String label) {
        this.label = label;
        this.instr = new ArrayList<>();
    }

    private static Operator.CompareOp getNot(Operator.CompareOp op) {
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

    private void foldCmp(CompareInstruction cmp, int id) {
        Integer cstLhs = cmp.getCstLhs();
        Integer cstRhs = cmp.getCstRhs();
        if (cstLhs != null && cstRhs != null) {
            if (instr.get(id + 1) instanceof CondInstruction) {
                CondInstruction v = (CondInstruction) instr.get(id + 1);
                Operator.CompareOp op = v.getOp();
                boolean res = getCompare(op, cstLhs, cstRhs);
                if (v instanceof CondSetInstruction) {
                    Operand dst = ((CondSetInstruction) v).getDst();
                    instr.set(id, new MoveInstruction(dst, res ? 1 : 0));
                    instr.remove(id + 1);
                } else if (v instanceof CondJumpInstruction) {
                    if (res) {
                        Block dst = ((CondJumpInstruction) v).getDst();
                        jump = new JumpInstruction(dst);
                        instr.set(id, jump);
                        while (instr.size() > id + 1)
                            instr.remove(instr.size() - 1);
                    } else {
                        instr.remove(id);
                        instr.remove(id);
                    }
                }
            }
        }
    }

    private void foldArith(ConstantFolding arith, int id) {
        if (arith.getCstVal() != null)
            instr.set(id, new MoveInstruction(arith.getDst(), arith.getCstVal()));
    }

    private int reduceStrength(int id) {
        if (instr.get(id) instanceof BinaryInstruction) {
            BinaryInstruction x = (BinaryInstruction) instr.get(id);
            Operand dst = x.getDst();
            Operand src = x.getSrc();
            Integer val = x.getConstant(src);
            if (val != null) {
                switch (x.getOp()) {
                    case ADD:
                    case SUB:
                        if (val == 0) {
                            instr.remove(id);
                            return id - 1;
                        }
                        break;
                    case MUL:
                        if (val == 0)
                            instr.set(id, new MoveInstruction(dst, 0));
                        else if (val == 1)
                            instr.set(id, new MoveInstruction(dst, dst));
                        else {
                            for (int k = 0; k < 64; ++k) {
                                if ((1 << k) == val) {
                                    instr.set(id, new BinaryInstruction(SHL, dst, k));
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        }
        return id;
    }

    void eliminateDeadCode() {
        for (int i = 0; i < instr.size(); ++i) {
            Instruction u = instr.get(i);
            if (u instanceof DeadCodeElimination) {
                if (((DeadCodeElimination) u).isDeadCode())
                    instr.remove(i--);
            }
        }
    }

    void foldConstant() {
        for (int i = 0; i < instr.size(); ++i) {
            Instruction u = instr.get(i);
            if (u instanceof CompareInstruction)
                foldCmp((CompareInstruction) u, i);
            else if (u instanceof ConstantFolding)
                foldArith((ConstantFolding) u, i);
            i = reduceStrength(i);
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

    String dumpNeedednessAnalysis() {
        StringBuilder str = new StringBuilder();
        str.append(getLabel() + ":\n");
        for (Instruction i : instr) {
            str.append(i.toString());
            str.append(i.dumpNeeded());
        }
        return str.toString();
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

    void setJump(JumpInstruction jump) {
        this.jump = jump;
    }

    void clearJump() {
        jump = null;
    }

    int size() {
        return instr.size();
    }

    Instruction get(int id) {
        return instr.get(id);
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

    public void setLabel(String label) {
        this.label = label;
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
                    String a = ((MoveInstruction) instr.get(id)).getPhyDst();
                    String b = ((MoveInstruction) instr.get(id)).getPhySrc();
                    String c = ((MoveInstruction) instr.get(id + 1)).getPhyDst();
                    String d = ((MoveInstruction) instr.get(id + 1)).getPhySrc();
                    if (a != null && b != null && a.equals(d) && b.equals(c))
                        instr.remove(id + 1);
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
