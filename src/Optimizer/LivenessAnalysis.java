package Optimizer;

import IR.Build.FunctionIR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static IR.Build.IR.functionIRMap;

public class LivenessAnalysis {
    static public void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.livenessAnalysis();
        dumpLivenessAnalysis();
    }

    static private void dumpLivenessAnalysis() throws Exception {
        StringBuilder str = new StringBuilder();
        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.dumpLivenessAnalysis());
        File file = new File("livenessAnalysis.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }

}
