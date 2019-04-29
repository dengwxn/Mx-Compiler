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
import java.util.ArrayList;
import java.util.HashMap;

import static IR.Operand.VirtualRegisterTable.precolor;

public class IR {
    static private FuncDeclNode globalVarDecl;
    static public HashMap<FuncDeclNode, FunctionIR> functionIRMap = new HashMap<>();
    static public HashMap<String, Integer> stringConst = new HashMap<>();

    static public int putStringConst(String str) {
        if (!stringConst.containsKey(str))
            stringConst.put(str, stringConst.size());
        return stringConst.get(str);
    }

    static private void setGlobalVarDecl() {
        BlockStmtNode block = new BlockStmtNode();
        for (Node decl : Tree.prog.getDecl()) {
            if (decl instanceof VarDeclStmtNode) {
                ((VarDeclStmtNode) decl).setGlobal();
                block.addStmt((StmtNode) decl);
            }
        }
        globalVarDecl = new FuncDeclNode();
        globalVarDecl.setFuncName("@global_var_decl");
        globalVarDecl.setBlockStmt(block);
        globalVarDecl.setParamSymbol(new ArrayList<>());
    }

    static public void generate() {
        setGlobalVarDecl();
        precolor();
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

    static public String toNASM() {
        StringBuilder str = new StringBuilder();
        str.append("section .text\n");
        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.toNASM());
        return str.toString();
    }

    static public void dump() throws Exception {
        StringBuilder str = new StringBuilder();
        for (FunctionIR functionIR : functionIRMap.values())
            str.append(functionIR.toString());
        File file = new File("IR.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
    }

    static final private boolean _DEBUG_TRANSLATE_ = false;

    static public String translateRegister(String op) {
        if (_DEBUG_TRANSLATE_)
            return op;
        switch (op) {
            case "res0":
                return "rax";
            case "res0_l8":
                return "al";

            case "arg1":
                return "rdi";
            case "arg1_l8":
                return "dil";
            case "arg2":
                return "rsi";
            case "arg2_l8":
                return "sil";
            case "arg3":
                return "rdx";
            case "arg3_l8":
                return "dl";
            case "arg4":
                return "rcx";
            case "arg4_l8":
                return "cl";
            case "arg5":
                return "r8";
            case "arg5_l8":
                return "r8b";
            case "arg6":
                return "r9";
            case "arg6_l8":
                return "r9b";
            case "ler7":
                return "r10";
            case "ler7_l8":
                return "r10b";
            case "ler8":
                return "r11";
            case "ler8_l8":
                return "r11b";

            case "lee9":
                return "rbx";
            case "lee9_l8":
                return "bl";
            case "lee10":
                return "rbp";
            case "lee10_l8":
                return "bpl";
            case "lee11":
                return "r12";
            case "lee11_l8":
                return "r12b";
            case "lee12":
                return "r13";
            case "lee12_l8":
                return "r13b";
            case "lee13":
                return "r14";
            case "lee13_l8":
                return "r14b";
            case "lee14":
                return "r15";
            case "lee14_l8":
                return "r15b";
            case "rsp":
                return "rsp";
            case "rsp_l8":
                return "spl";

            default:
                return op;
        }
    }


    static public String formatInstr(String instr) {
        return String.format("\t%s\n", instr);
    }

    static public String formatInstr(String instr, String op) {
        return String.format("\t%s  \t%s\n", instr, translateRegister(op));
    }

    static public String formatInstr(String instr, String lhs, String rhs) {
        return String.format("\t%s  \t%s, %s\n", instr, translateRegister(lhs), translateRegister(rhs));
    }
}
