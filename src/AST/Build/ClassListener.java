package AST.Build;

import AST.Basic.Listener;
import AST.Program.ClassDeclNode;
import AST.Type.*;
import Parser.MxParser;

import static AST.Build.Tree.map;
import static AST.Build.Tree.symbolTable;

public class ClassListener extends Listener {
    @Override
    public void enterProgram(MxParser.ProgramContext ctx) {
        symbolTable.put("int", IntType.getInstance());
        symbolTable.put("bool", BoolType.getInstance());
        symbolTable.put("void", VoidType.getInstance());
        symbolTable.put("null", NullType.getInstance());
    }

    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.put(classDecl.getName(), new ClassType(classDecl.getName()));
    }
}
