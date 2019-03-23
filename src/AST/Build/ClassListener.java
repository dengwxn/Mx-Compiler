package AST.Build;

import AST.Basic.Listener;
import AST.Program.ClassDeclNode;
import AST.Type.*;
import Parser.MxParser;

import static AST.Build.Tree.*;

public class ClassListener extends Listener {


    @Override
    public void enterProgram(MxParser.ProgramContext ctx) {
        symbolTable.put("int", IntType.getInstance());
        symbolTable.put("bool", BoolType.getInstance());
        symbolTable.put("void", VoidType.getInstance());
        symbolTable.put("null", NullType.getInstance());
        symbolTable.put("string", StringType.getInstance());

        typeTable.put("int", IntType.getInstance());
        typeTable.put("bool", BoolType.getInstance());
        typeTable.put("void", VoidType.getInstance());
        typeTable.put("null", NullType.getInstance());
        typeTable.put("string", StringType.getInstance());
    }

    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.put(classDecl.getName(), new ClassType(classDecl.getName()));
        typeTable.put(classDecl.getName(), new ClassType(classDecl.getName()));
    }
}
