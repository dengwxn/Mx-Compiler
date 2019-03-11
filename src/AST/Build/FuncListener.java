package AST.Build;

import AST.Basic.*;
import AST.Branch.BreakStmtNode;
import AST.Branch.ContinueStmtNode;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Loop.ForStmtNode;
import AST.Loop.WhileStmtNode;
import AST.Program.FuncDeclNode;
import AST.Program.ProgNode;
import AST.Statement.BlockStmtNode;
import AST.Statement.ExprStmtNode;
import AST.Statement.IfStmtNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.FuncType;
import Parser.MxBaseListener;
import Parser.MxParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

import static AST.Build.Tree.*;

public class FuncListener extends Listener {
    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode decl = (FuncDeclNode) map.get(ctx);
        Type retType = localSymbolTable.get(decl.getRetTypeLit());
        ArrayList<Type> type = new ArrayList<>();
        decl.getTypeLit().forEach(typeLit -> type.add(localSymbolTable.get(typeLit)));
        localSymbolTable.put(decl.getFuncName(), new FuncType(retType, type));
    }
}
