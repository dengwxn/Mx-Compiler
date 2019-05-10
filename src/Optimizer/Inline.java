package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.FunctionIR.VirtualRegisterPool;
import static IR.Build.IR.functionIRMap;
import static IR.Operand.VirtualRegisterTable.virtualRegisterTable;

public class Inline {
    public static int optimize() throws Exception {
        VirtualRegisterPool.addAll(virtualRegisterTable.values());
        for (int i = 0; i < 3; ++i) {
            for (FunctionIR functionIR : functionIRMap.values())
                functionIR.inline();
            for (FunctionIR functionIR : functionIRMap.values())
                functionIR.getNewBlockList();
            IR.dump("inline" + i);
        }
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.setCalling();

        // due to time limit in OJ
        if (getMaxLine() > 4000) return 1;
        return 2;
    }

    private static int getMaxLine() {
        int line = 0;
        for (FunctionIR functionIR : functionIRMap.values())
            line = Math.max(line, functionIR.instrListSize());
        return line;
    }

    public static int getAllLine() {
        int line = 0;
        for (FunctionIR functionIR : functionIRMap.values())
            line += functionIR.instrListSize();
        return line;
    }
}
