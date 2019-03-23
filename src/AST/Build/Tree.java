package AST.Build;

import AST.Program.ProgNode;
import AST.Table.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class Tree {
    static public ErrorListener errorListener = new ErrorListener();
    static ProgNode prog;
    static ParseTreeProperty<Object> map = new ParseTreeProperty<>();
    static SymbolTable symbolTable = new SymbolTable(null);
    static TypeTable typeTable = new TypeTable();
    static int loopCount;

    static public void enterLoop() { ++loopCount; }

    static public void exitLoop() { --loopCount; }

    static public int getLoopCount() { return loopCount; }

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
