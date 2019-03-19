package AST.Build;

import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.ClassType;
import AST.Type.FuncType;
import Parser.MxParser;

import java.util.ArrayList;

import static AST.Build.Tree.map;
import static AST.Build.Tree.symbolTable;

public class DeclarationListener extends Listener {
    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.setClassName(classDecl.getName());
        for (VarDeclStmtNode varDecl : classDecl.getVarDecls()) {
            String varName = classDecl.getName() + "." + varDecl.getName();
            symbolTable.put(varName, symbolTable.get(varDecl.getType()));
        }
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
    }
    
    String getFuncName(FuncDeclNode funcDecl) {
        if (symbolTable.getClassName() == null)
            return funcDecl.getFuncName();
        else
            return symbolTable.getClassName() + "." + funcDecl.getFuncName();
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        Type retType = symbolTable.get(funcDecl.getRetType());
        ArrayList<Type> paramType = new ArrayList<>();
        funcDecl.getParamType().forEach(t -> paramType.add(symbolTable.get(t)));
        String funcName = getFuncName(funcDecl);
        symbolTable.put(funcName, new FuncType(retType, paramType));
    }
}
