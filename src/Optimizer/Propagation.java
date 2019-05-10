package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.IR.functionIRMap;

public class Propagation {
    public static void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.propagateConstant();
        IR.dump("constant");

        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.propagateCopy();
        IR.dump("copy");
    }
}
