package Optimizer.Build;

import IR.Build.FunctionIR;

import static IR.Build.IR.functionIRMap;

public class Liveness {
    static public void run() {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.livenessAnalysis();
    }
}
