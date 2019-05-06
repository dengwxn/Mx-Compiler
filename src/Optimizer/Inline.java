package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.IR.functionIRMap;

public class Inline {
    static public void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.inline();
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.setCalling();
        IR.dump();
    }
}
