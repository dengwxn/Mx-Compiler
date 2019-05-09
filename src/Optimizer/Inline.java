package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.FunctionIR.VirtualRegisterPool;
import static IR.Build.IR.functionIRMap;
import static IR.Operand.VirtualRegisterTable.virtualRegisterTable;

public class Inline {
    static public int optimize() throws Exception {
        VirtualRegisterPool.addAll(virtualRegisterTable.values());
        for (int i = 0; i < 3; ++i) {
            for (FunctionIR functionIR : functionIRMap.values())
                functionIR.inline();
            IR.dump("inline" + i);
        }
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.setCalling();
        return getOptimizeRound();
    }

    static private int getOptimizeRound() {
        int line = 0;
        for (FunctionIR functionIR : functionIRMap.values())
            line = Math.max(line, functionIR.instrListSize());
        // due to time limit in OJ
        if (line > 5000) return 1;
        return 3;
    }
}
