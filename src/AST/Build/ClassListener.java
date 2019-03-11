package AST.Build;

import AST.Basic.Listener;
import AST.Type.BoolType;
import AST.Type.IntType;
import AST.Type.NullType;
import AST.Type.VoidType;
import Parser.MxParser;

import static AST.Build.Tree.localSymbolTable;

public class ClassListener extends Listener {
    @Override
    public void enterProgram(MxParser.ProgramContext ctx) {
        localSymbolTable.put("int", IntType.getInstance());
        localSymbolTable.put("bool", BoolType.getInstance());
        localSymbolTable.put("void", VoidType.getInstance());
        localSymbolTable.put("null", NullType.getInstance());
    }
}
