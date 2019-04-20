package IR.Build;

import IR.Instruction.Instruction;

import java.util.ArrayList;

public class BlockList {
    private ArrayList<Block> blockList;

    BlockList() {
        blockList = new ArrayList<>();
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
