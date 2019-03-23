package AST.Build;

import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.FuncType;
import AST.Type.IntType;
import Parser.MxParser;

import java.util.ArrayList;

import static AST.Build.Tree.*;

public class DeclarationListener extends Listener {
    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.setClassName(classDecl.getName());
        for (VarDeclStmtNode varDecl : classDecl.getVarDecl()) {
            String varName = classDecl.getName() + "." + varDecl.getName();
            symbolTable.put(varName, typeTable.get(varDecl.getType()));
        }
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
    }

    String getScopeName() {
        if (!symbolTable.inClassDeclScope())
            return "";
        else
            return symbolTable.getClassName() + ".";
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        Type retType = symbolTable.get(funcDecl.getRetType());
        ArrayList<Type> paramType = new ArrayList<>();
        funcDecl.getParamType().forEach(t -> paramType.add(typeTable.get(t)));
        String funcName = getScopeName() + funcDecl.getFuncName();
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    void mainPrint() {
        Type retType = symbolTable.get("void");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.get("string"));
        String funcName = "print";
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    void mainPrintln() {
        Type retType = symbolTable.get("void");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.get("string"));
        String funcName = "println";
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    void mainGetString() {
        Type retType = symbolTable.get("string");
        String funcName = "getString";
        symbolTable.put(funcName, new FuncType(retType));
    }

    void mainGetInt() {
        Type retType = symbolTable.get("int");
        String funcName = "getInt";
        symbolTable.put(funcName, new FuncType(retType));
    }

    void mainToString() {
        Type retType = symbolTable.get("string");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.get("int"));
        String funcName = "toString";
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    void arraySize() {
        Type retType = symbolTable.get("int");
        String funcName = ".size";
        symbolTable.put(funcName, new FuncType(retType));
    }

    void stringLength() {
        Type retType = symbolTable.get("int");
        String funcName = "string.length";
        symbolTable.put(funcName, new FuncType(retType));
    }

    void stringSubstring() {
        Type retType = symbolTable.get("string");
        String funcName = "string.substring";
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.get("int"));
        paramType.add(symbolTable.get("int"));
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    void stringParseInt() {
        Type retType = symbolTable.get("int");
        String funcName = "string.parseInt";
        symbolTable.put(funcName, new FuncType(retType));
    }

    void stringOrd() {
        Type retType = symbolTable.get("int");
        String funcName = "string.ord";
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.get("int"));
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }

    boolean isMainValid() {
        FuncType funcType = (FuncType) symbolTable.get("main");
        if (funcType == null) return false;
        if (!(funcType.getRetType() instanceof IntType)) return false;
        if (funcType.getParamType().size() > 0) return false;
        return true;
    }

    @Override
    public void exitProgram(MxParser.ProgramContext ctx) {
        if (!isMainValid())
            addCompileError("expect a 'int main()' function.");

        arraySize();
        stringLength();
        stringSubstring();
        stringParseInt();
        stringOrd();

        mainPrint();
        mainPrintln();
        mainGetString();
        mainGetInt();
        mainToString();
    }
}
