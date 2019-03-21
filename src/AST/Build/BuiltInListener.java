package AST.Build;

import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.FuncType;
import Parser.MxParser;

import java.util.ArrayList;

import static AST.Build.Tree.map;
import static AST.Build.Tree.symbolTable;

public class BuiltInListener extends Listener {
    void arraySize() {
        Type retType = symbolTable.get("int");
        String funcName = ".size";
        symbolTable.put(funcName, new FuncType(retType));
    }

    @Override
    public void enterProgram(MxParser.ProgramContext ctx) {
        arraySize();
    }
}
