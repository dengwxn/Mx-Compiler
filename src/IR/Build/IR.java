package IR.Build;

import AST.Build.Node;
import AST.Build.Tree;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.BlockStmtNode;
import AST.Statement.StmtNode;
import AST.Statement.VarDeclStmtNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static IR.Operand.VirtualRegisterTable.setSpecificRegister;

public class IR {
    static private FuncDeclNode globalVarDecl;
    static public HashMap<FuncDeclNode, FunctionIR> functionIRMap;
    static public HashMap<String, Integer> stringConst;

    static public int putStringConst(String str) {
        if (!stringConst.containsKey(str))
            stringConst.put(str, stringConst.size());
        return stringConst.get(str);
    }

    static private void setGlobalVarDecl() {
        BlockStmtNode block = new BlockStmtNode();
        for (Node decl : Tree.prog.getDecl()) {
            if (decl instanceof VarDeclStmtNode)
                block.addStmt((StmtNode) decl);
        }
        globalVarDecl = new FuncDeclNode();
        globalVarDecl.setFuncName("_global_var_decl");
        globalVarDecl.setBlockStmt(block);
    }

    static public void generate() {
        setGlobalVarDecl();
        setSpecificRegister();
        functionIRMap = new HashMap<>();
        stringConst = new HashMap<>();
        functionIRMap.put(globalVarDecl, new FunctionIR(globalVarDecl));
        for (Node decl : Tree.prog.getDecl()) {
            if (decl instanceof FuncDeclNode) {
                functionIRMap.put((FuncDeclNode) decl, new FunctionIR((FuncDeclNode) decl));
            } else if (decl instanceof ClassDeclNode) {
                for (FuncDeclNode funcDecl : ((ClassDeclNode) decl).getFuncDecl())
                    functionIRMap.put(funcDecl, new FunctionIR(funcDecl));
            }
        }
    }

    static public void dump() throws Exception {
        StringBuilder str = new StringBuilder();

        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.dump());

        str.append("section .data\n");
        for (Map.Entry<String, Integer> entry : stringConst.entrySet()) {
            String key = entry.getKey();
            key = key.substring(1, key.length() - 1);
            key = key.replace("\\b", "\b");
            key = key.replace("\\t", "\t");
            key = key.replace("\\n", "\n");
            key = key.replace("\\r", "\r");
            key = key.replace("\\\"", "\"");
            key = key.replace("\\\\", "\\");
            str.append(formatInstruction("dq  ", String.valueOf(key.length())));
            str.append("_string_constant_" + key.length() + ":\n");
            str.append(formatInstruction("db  ", entry.getKey() + ", 0"));
        }

        File file = new File("IR.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
        // System.out.println(str.toString());
    }

    static public String formatInstruction(String instr, String op) {
        return String.format("\t\t%s \t%s\n", instr, op);
    }

    static public String formatInstruction(String instr, String lhs, String rhs) {
        return String.format("\t\t%s \t%s %s\n", instr, lhs, rhs);
    }
}
