package IR.Build;

import IR.Instruction.Instruction;
import IR.Instruction.JumpInstruction;

import java.util.ArrayList;

public class BlockList {
    private ArrayList<Block> blockList;

    BlockList() {
        blockList = new ArrayList<>();
    }

    void livenessAnalysis() {
        blockList.forEach(Block::livenessAnalysis);
    }

    void linkPreSuc() {
        for (Block block : blockList) {
            block.linkPreSuc();
            JumpInstruction u = block.getTail();
            if (u != null) {
                Instruction v = u.getDst().getHead();
                if (v != null) {
                    u.linkSuc(v);
                    v.linkPre(u);
                }
            }
        }
    }

    ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void add(Block block) {
        block.setId(blockList.size());
        blockList.add(block);
    }

    public void add(Instruction... il) {
        blockList.get(blockList.size() - 1).add(il);
    }
}
