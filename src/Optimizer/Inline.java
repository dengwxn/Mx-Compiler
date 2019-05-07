package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import static IR.Build.FunctionIR.allVirtualRegister;
import static IR.Build.IR.functionIRMap;
import static IR.Operand.VirtualRegisterTable.virtualRegisterTable;

public class Inline {
    static public void optimize() throws Exception {
        allVirtualRegister.addAll(virtualRegisterTable.values());
        for (int i = 0; i < 3; ++i) {
            for (FunctionIR functionIR : functionIRMap.values())
                functionIR.inline();
            IR.dump("inline" + i);
        }
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.setCalling();
    }
}
