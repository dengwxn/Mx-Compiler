package IR.Build;

import IR.Instruction.Instruction;
import IR.Operand.Operand;

import java.util.ArrayList;

import static IR.Build.IR.formatInstr;

public class BlockList {
    private ArrayList<Block> blockList;
    private String funcName;
    private Operand classThis;

    BlockList() {
        blockList = new ArrayList<>();
    }

    void putSpill() {
        blockList.forEach(block -> block.putSpill());
    }

    public Operand getClassThis() {
        return classThis;
    }

    public void setClassThis(Operand classThis) {
        this.classThis = classThis;
    }

    Block getHead() {
        return blockList.get(0);
    }

    Block getTail() {
        return blockList.get(blockList.size() - 1);
    }

    public String toNASM() {
        StringBuilder str = new StringBuilder();
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
