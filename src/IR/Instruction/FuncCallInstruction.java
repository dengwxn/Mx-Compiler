package IR.Instruction;

import Generator.Operand.PhysicalAddress;
import Generator.Operand.PhysicalOperand;
import IR.Build.BlockList;
import IR.Operand.Operand;

import java.util.ArrayList;
import java.util.Arrays;

import static Generator.Operand.PhysicalOperand.convertVirtualOperand;
import static IR.Build.FunctionIR.setMaxParamSize;
import static IR.Build.IR.formatInstr;

public class FuncCallInstruction extends Instruction {
    private static final ArrayList<String> def = new ArrayList<>(Arrays.asList(
            "res0", "arg1", "arg2", "arg3", "arg4", "arg5", "arg6", "ler7"));
    private String name;
    private ArrayList<Operand> param;
    private int argCnt;

    public FuncCallInstruction(String name, ArrayList<Operand> param, int argCnt) {
        this.name = name;
        this.param = param;
        this.argCnt = argCnt;
    }

    public static int moveArg(BlockList blockList, ArrayList<Operand> operand) {
        int argCnt = 0;
        for (int i = 0; i < 6 && operand.size() > 0; ++i) {
            blockList.add(new MoveInstruction("arg" + (i + 1), operand.get(0)));
            operand.remove(0);
            ++argCnt;
        }
        return argCnt;
    }

    @Override
    public Instruction makeCopy() {
        ArrayList<Operand> newParam = new ArrayList<>();
        param.forEach(p -> newParam.add(makeCopy(p)));
        return new FuncCallInstruction(name, newParam, argCnt);
    }

    public ArrayList<Operand> getParam() {
        return param;
    }

    public int getArgCnt() {
        return argCnt;
    }

    public String getName() {
        return name;
    }

    @Override
    public void assignPhysicalOperand() {
        param.forEach(p -> p.assignPhysicalOperand());
        setMaxParamSize(param.size());
    }

    @Override
    public void putNec() {
        for (int i = 0; i < argCnt; ++i)
            putNec("arg" + (i + 1));
        param.forEach(p -> putNec(p));
    }

    @Override
    public void putUse() {
        for (int i = 0; i < argCnt; ++i)
            putUse("arg" + (i + 1));
    }

    @Override
    public void putDef() {
        def.forEach(p -> putDef(p));
    }

    @Override
    public String toNASM() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < param.size(); ++i) {
            PhysicalOperand param = convertVirtualOperand(str, this.param.get(i), false);
            PhysicalAddress pos = new PhysicalAddress("rsp", i * 8);
            if (param instanceof PhysicalAddress) {
                str.append(formatInstr("mov", "ler8", param.toNASM()));
                str.append(formatInstr("mov", pos.toNASM(), "ler8"));
            } else {
                str.append(formatInstr("mov", pos.toNASM(), param.toNASM()));
            }
        }
        str.append(formatInstr("call", name));
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name + "(");
        boolean comma = false;
        for (Operand p : param) {
            if (comma)
                str.append(", " + p.toString());
            else {
                str.append(p.toString());
                comma = true;
            }
        }
        str.append(")");
        return formatInstr("call", str.toString());
    }
}
