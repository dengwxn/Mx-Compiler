package AST.Build;

import AST.Loop.LoopStmtNode;
import AST.Program.ProgNode;
import AST.Table.SymbolTable;
import AST.Table.TypeTable;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Stack;

public class Tree {
    static public ErrorListener errorListener = new ErrorListener();
    static public ProgNode prog;
    static ParseTreeProperty<Object> map = new ParseTreeProperty<>();
    static SymbolTable symbolTable = new SymbolTable(null);
    static TypeTable typeTable = new TypeTable();
    static private Stack<LoopStmtNode> loopStmtStack = new Stack<>();

    static void enterLoop(LoopStmtNode loopStmt) {
        loopStmtStack.push(loopStmt);
    }

    static void exitLoop() {
        loopStmtStack.pop();
    }

    static int getLoopCount() {
        return loopStmtStack.size();
    }

    static LoopStmtNode getLoopStmt() {
        return loopStmtStack.peek();
    }

    static void enterScope() {
        symbolTable = new SymbolTable(symbolTable);
    }

    static void exitScope() {
        symbolTable = symbolTable.getLastScope();
    }

    static void errorAnalyze() {
        if (errorListener.msgs.size() > 0) {
            errorListener.msgs.forEach(System.out::println);
            System.exit(1);
        }
    }

    static public void dump() {
        prog.dump(0);
    }
}
