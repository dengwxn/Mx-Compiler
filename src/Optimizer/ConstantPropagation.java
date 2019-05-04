package Optimizer;

import IR.Build.FunctionIR;

import static IR.Build.IR.functionIRMap;

public class ConstantPropagation {
    static public void optimize() {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.propagateConstant();
    }
}
