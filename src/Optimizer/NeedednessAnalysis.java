package Optimizer;

import IR.Build.FunctionIR;
import IR.Build.IR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static IR.Build.IR.functionIRMap;

public class NeedednessAnalysis {
    static public void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.analyzeNeededness();
        dumpNeedednessAnalysis();
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.eliminateDeadCode();
        IR.dump();
    }

    static private void dumpNeedednessAnalysis() throws Exception {
        StringBuilder str = new StringBuilder();
        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.dumpNeedednessAnalysis());
        File file = new File("neededNessAnalysis.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }
}
