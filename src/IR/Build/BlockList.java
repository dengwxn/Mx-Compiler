package IR.Build;

import IR.Instruction.Instruction;
import IR.Operand.Operand;

import java.util.ArrayList;

public class BlockList {
    private ArrayList<Block> blockList;
    private String funcName;
    private Operand classThis;

    BlockList() {
        blockList = new ArrayList<>();
    }

    void add(int id, Block block) {
        block.setId(blockList.size());
        block.setFuncName(funcName);
        blockList.add(id, block);
    }

    Block get(int id) {
        return blockList.get(id);
    }

    int size() {
        return blockList.size();
    }

    ArrayList<Instruction> getInstrList() {
        ArrayList<Instruction> instrList = new ArrayList<>();
        blockList.forEach(block -> instrList.addAll(block.getInstr()));
        return instrList;
    }

    ArrayList<Block> getBlockList() {
        return blockList;
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

    void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void add(Block... blockList) {
        for (Block block : blockList) {
            block.setId(this.blockList.size());
            block.setFuncName(funcName);
            this.blockList.add(block);
        }
    }

    public void add(Instruction... instructionList) {
        blockList.get(blockList.size() - 1).add(instructionList);
    }
}
