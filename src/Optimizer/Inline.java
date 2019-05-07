package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.IR.functionIRMap;

public class Inline {
    static public void optimize() throws Exception {
        // only support one-layer inline
        for (int i = 0; i < 1; ++i) {
            for (FunctionIR functionIR : functionIRMap.values())
                functionIR.inline();
            IR.dump();
        }
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.setCalling();
    }
}
