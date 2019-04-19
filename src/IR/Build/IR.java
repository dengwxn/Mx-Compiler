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

public class IR {
    static private FuncDeclNode globalVarDecl;
    static private HashMap<FuncDeclNode, FunctionIR> functionIRMap;

    static private void setGlobalVarDecl() {
        BlockStmtNode block = new BlockStmtNode();
        for (Node decl : Tree.prog.getDecl()) {
            if (decl instanceof VarDeclStmtNode)
                block.addStmt((StmtNode) decl);
        }
        globalVarDecl = new FuncDeclNode();
        globalVarDecl.setFuncName("_globalVarDecl");
        globalVarDecl.setBlockStmt(block);
    }

    static public void generate() {
        setGlobalVarDecl();
        functionIRMap = new HashMap<>();
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
        File file = new File("IR.txt");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
        // System.out.println(str.toString());
    }
}
