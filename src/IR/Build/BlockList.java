package IR.Build;

import IR.Instruction.BinaryInstruction;
import IR.Instruction.Instruction;
import IR.Operand.VirtualRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static IR.Build.IR.formatInstr;
import static IR.Instruction.Operator.BinaryOp.ADD;
import static IR.Instruction.Operator.BinaryOp.SUB;

public class BlockList {
    static public LinkedHashSet<VirtualRegister> spillPool = new LinkedHashSet<>();
    static private HashMap<VirtualRegister, Integer> spillPos = new HashMap<>();
    static public int maxParamSize;

    private ArrayList<Block> blockList;
    private String funcName;

    BlockList() {
        blockList = new ArrayList<>();
    }

    static public int getSpillPos(VirtualRegister reg) {
        return spillPos.get(reg);
    }

    public String toNASM() {
        blockList.forEach(block -> block.putSpill());
        int cnt = spillPool.size();
        if (maxParamSize > 6)
            cnt += maxParamSize - 6;
        if (cnt % 2 == 0)
            ++cnt;
        int pos = cnt;
        for (VirtualRegister i : spillPool)
            spillPos.put(i, (--pos) * 8);

        StringBuilder str = new StringBuilder();
        Block head = blockList.get(0);
        Block tail = blockList.get(blockList.size() - 1);
        head.add(new BinaryInstruction(SUB, "rsp", cnt * 8));
        tail.add(new BinaryInstruction(ADD, "rsp", cnt * 8));
        for (int i = 0; i < blockList.size(); ++i) {
            Block block = blockList.get(i);
            Block nextBlock = i < blockList.size() - 1 ? blockList.get(i + 1) : null;
            str.append(block.toNASM(nextBlock));
        }
        str.append(formatInstr("ret"));
        return str.toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        blockList.forEach(b -> str.append(b.toString()));
        return str.toString();
    }

    void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void add(Block block) {
        block.setId(blockList.size());
        block.setFuncName(funcName);
        blockList.add(block);
    }

    public void add(Instruction... il) {
        blockList.get(blockList.size() - 1).add(il);
    }
}
