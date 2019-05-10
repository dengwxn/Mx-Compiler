package Optimizer;

import IR.Build.FunctionIR;
import IR.Operand.VirtualRegister;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

import static IR.Build.IR.functionIRMap;
import static IR.Operand.VirtualRegisterTable.getVirtualRegister;
import static java.lang.Math.max;

public class RegisterAllocation {
    public static final ArrayList<String> regList = new ArrayList<>(Arrays.asList(
            "res0", "arg1", "arg2", "arg3", "arg4", "arg5", "arg6", "ler7", "ler8",
            "lee9", "lee10", "lee11", "lee12", "lee13", "lee14", "rsp"));
    private static HashMap<VirtualRegister, String> physicalRegister = new HashMap<>();
    private static HashMap<VirtualRegister, HashSet<VirtualRegister>> intrf = new HashMap<>();
    private static LinkedHashSet<VirtualRegister> vertex = new LinkedHashSet<>();

    public static void optimize() throws Exception {
        for (FunctionIR functionIR : functionIRMap.values())
            functionIR.analyzeLiveness();
        dumpLivenessAnalysis();
        dumpIntrf();
        greedyColor(getOrder());
    }

    private static void greedyColor(ArrayList<VirtualRegister> order) throws Exception {
        HashMap<VirtualRegister, Integer> color = new HashMap<>();
        LinkedHashSet<Integer> num = new LinkedHashSet<>();
        for (int i = 0; i < 15; ++i) {
            // preserve ler8
            if (i != 8) num.add(i);
        }
        int ptr = 0;
        for (VirtualRegister u : order) {
            if (ptr < 16) {
                putPhysicalRegister(u, regList.get(ptr));
                color.put(u, ptr++);
            } else {
                LinkedHashSet<Integer> avail = new LinkedHashSet<>(num);
                if (intrf.get(u) != null) {
                    for (VirtualRegister v : intrf.get(u)) {
                        if (color.get(v) != null)
                            avail.remove(color.get(v));
                    }
                }
                if (avail.iterator().hasNext()) {
                    int c = avail.iterator().next();
                    putPhysicalRegister(u, regList.get(c));
                    color.put(u, c);
                }
            }
        }
        dumpColor(order, color);
    }

    private static ArrayList<VirtualRegister> getOrder() {
        ArrayList<VirtualRegister> order = new ArrayList<>();
        HashMap<VirtualRegister, Integer> wt = new HashMap<>();
        ArrayList<LinkedHashSet<VirtualRegister>> pool = new ArrayList<>();
        HashSet<VirtualRegister> flag = new HashSet<>(vertex);

        vertex.forEach(v -> wt.put(v, 0));
        for (int i = 0; i < vertex.size(); ++i)
            pool.add(new LinkedHashSet<>());
        vertex.forEach(v -> pool.get(0).add(v));
        int ptr = 0;
        for (int i = 0; i < vertex.size(); ++i) {
            while (pool.get(ptr).size() == 0) --ptr;
            VirtualRegister u;
            if (i < 16) {
                u = getVirtualRegister(regList.get(i));
                pool.get(wt.get(u)).remove(u);
            } else {
                u = pool.get(ptr).iterator().next();
                pool.get(ptr).remove(u);
            }
            order.add(u);
            flag.remove(u);
            if (intrf.get(u) != null) {
                for (VirtualRegister v : intrf.get(u)) {
                    if (flag.contains(v)) {
                        pool.get(wt.get(v)).remove(v);
                        wt.put(v, wt.get(v) + 1);
                        pool.get(wt.get(v)).add(v);
                        ptr = max(ptr, wt.get(v));
                    }
                }
            }
        }
        return order;
    }

    public static void buildIntrfGraph(VirtualRegister u, VirtualRegister v) {
        addEdge(u, v);
        addEdge(v, u);
    }

    private static void addEdge(VirtualRegister u, VirtualRegister v) {
        HashSet<VirtualRegister> s = intrf.get(u);
        if (s == null) {
            s = new HashSet<>();
            s.add(v);
            intrf.put(u, s);
        } else
            s.add(v);
    }

    public static String getPhysicalRegister(VirtualRegister reg) {
        return physicalRegister.get(reg);
    }

    public static void putPhysicalRegister(VirtualRegister reg, String str) {
        physicalRegister.put(reg, str);
    }

    public static void addVertex(VirtualRegister reg) {
        vertex.add(reg);
    }

    private static void dumpColor(ArrayList<VirtualRegister> order, HashMap<VirtualRegister, Integer> color) throws Exception {
        StringBuilder str = new StringBuilder();
        for (VirtualRegister u : order)
            str.append(u.toString() + ": " + color.get(u) + "\n");
        File file = new File("color.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }

    private static void dumpIntrf() throws Exception {
        StringBuilder str = new StringBuilder();
        for (VirtualRegister u : vertex) {
            str.append(u.toString() + ": ");
            if (intrf.get(u) != null) {
                for (VirtualRegister v : intrf.get(u)) {
                    str.append(v.toString() + ", ");
                }
            }
            str.append("\n");
        }
        File file = new File("Intrf.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }

    private static void dumpLivenessAnalysis() throws Exception {
        StringBuilder str = new StringBuilder();
        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.dumpLivenessAnalysis());
        File file = new File("livenessAnalysis.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }
}
