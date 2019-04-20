package AST.Build;

import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.FuncType;
import AST.Type.IntType;
import AST.Type.Type;
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
            symbolTable.putType(varName, typeTable.getType(varDecl.getType()));
        }
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        Type retType = typeTable.getType(funcDecl.getRetType());
        ArrayList<Type> paramType = new ArrayList<>();
        funcDecl.getParamType().forEach(t -> paramType.add(typeTable.getType(t)));
        String funcName = funcDecl.getFuncName();
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType, funcName.contains(".")));
        symbolTable.getType(funcName);
    }

    private void mainPrint() {
        Type retType = symbolTable.getType("void");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.getType("string"));
        String funcName = "print";
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType));
    }

    private void mainPrintln() {
        Type retType = symbolTable.getType("void");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.getType("string"));
        String funcName = "println";
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType));
    }

    private void mainGetString() {
        Type retType = symbolTable.getType("string");
        String funcName = "getString";
        symbolTable.putType(funcName, new FuncType(funcName, retType));
    }

    private void mainGetInt() {
        Type retType = symbolTable.getType("int");
        String funcName = "getInt";
        symbolTable.putType(funcName, new FuncType(funcName, retType));
    }

    private void mainToString() {
        Type retType = symbolTable.getType("string");
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.getType("int"));
        String funcName = "toString";
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType));
    }

    private void arraySize() {
        Type retType = symbolTable.getType("int");
        String funcName = ".size";
        symbolTable.putType(funcName, new FuncType(funcName, retType));
    }

    private void stringLength() {
        Type retType = symbolTable.getType("int");
        String funcName = "string.length";
        symbolTable.putType(funcName, new FuncType(funcName, retType));
    }

    private void stringSubstring() {
        Type retType = symbolTable.getType("string");
        String funcName = "string.substring";
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.getType("int"));
        paramType.add(symbolTable.getType("int"));
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType));
    }

    private void stringParseInt() {
        Type retType = symbolTable.getType("int");
        String funcName = "string.parseInt";
        symbolTable.putType(funcName, new FuncType(funcName, retType));
    }

    private void stringOrd() {
        Type retType = symbolTable.getType("int");
        String funcName = "string.ord";
        ArrayList<Type> paramType = new ArrayList<>();
        paramType.add(symbolTable.getType("int"));
        symbolTable.putType(funcName, new FuncType(funcName, retType, paramType));
    }

    private boolean isMainValid() {
        FuncType funcType = (FuncType) symbolTable.getType("main");
        if (funcType == null) return false;
        if (!(funcType.getRetType() instanceof IntType)) return false;
        return funcType.getParamType().size() <= 0;
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
