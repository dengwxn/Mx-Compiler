package AST.Build;

import AST.Program.ClassDeclNode;
import AST.Type.*;
import Parser.MxParser;

import static AST.Build.Tree.*;
import static IR.Operand.Address.putOffset;

public class ClassListener extends Listener {
    @Override
    public void enterProgram(MxParser.ProgramContext ctx) {
        symbolTable.putType("int", IntType.getInstance());
        symbolTable.putType("bool", BoolType.getInstance());
        symbolTable.putType("void", VoidType.getInstance());
        symbolTable.putType("null", NullType.getInstance());
        symbolTable.putType("string", StringType.getInstance());

        typeTable.putType("int", IntType.getInstance());
        typeTable.putType("bool", BoolType.getInstance());
        typeTable.putType("void", VoidType.getInstance());
        typeTable.putType("null", NullType.getInstance());
        typeTable.putType("string", StringType.getInstance());
    }

    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.putType(classDecl.getName(), new ClassType(classDecl.getName()));
        typeTable.putType(classDecl.getName(), new ClassType(classDecl.getName()));
    }
}
