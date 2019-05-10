package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.IR.functionIRMap;

public class GlobalVarLoading {
    public static void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.collectGlobalVar();
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.passGlobalVar();
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.loadGlobalVar();
        IR.dump("globalVarLoad");
    }
}
