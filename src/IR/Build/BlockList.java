package IR.Build;

import IR.Instruction.CondJumpInstruction;
import IR.Instruction.Instruction;
import IR.Instruction.Jump;
import IR.Instruction.JumpInstruction;
import IR.Operand.Operand;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockList {
    private ArrayList<Block> blockList;
    private String funcName;
    private Operand classThis;

    BlockList() {
        blockList = new ArrayList<>();
    }

    BlockList(BlockList blockList) {
        this.blockList = new ArrayList<>();
        this.funcName = blockList.funcName;
        this.classThis = blockList.classThis;

        HashMap<Block, Block> map = new HashMap<>();
        for (Block block : blockList.blockList) {
            Block newBlock = new Block(block.getLabel());
            newBlock.setId(block.getId());
            map.put(block, newBlock);
            this.blockList.add(newBlock);
        }

        for (Block block : blockList.blockList) {
            ArrayList<Instruction> instr = block.getInstr();
            Block newBlock = map.get(block);
            for (Instruction u : instr) {
                if (u instanceof Jump) {
                    Block dst = map.get(((Jump) u).getDst());
                    if (u instanceof JumpInstruction)
                        newBlock.add(new JumpInstruction(dst));
                    else if (u instanceof CondJumpInstruction)
                        newBlock.add(new CondJumpInstruction(((CondJumpInstruction) u).getOp(), dst));
                } else
                    newBlock.add(u);
            }
        }
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
