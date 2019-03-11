package AST.Build;

import AST.Basic.Listener;
import AST.Program.ProgNode;
import AST.SymbolTable.SymbolTable;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class Tree {
    static public ErrorListener errorListener = new ErrorListener();
    static ProgNode prog;
    static ParseTreeProperty<Object> map = new ParseTreeProperty<>();
    static SymbolTable localSymbolTable = new SymbolTable(null);

    static void enterNewScope() {
        localSymbolTable = new SymbolTable(localSymbolTable);
    }

    static void exitScope() {
        localSymbolTable = localSymbolTable.getLastScope();
    }

    static public void errorAnalyze() {
        if (Tree.errorListener.msgs.size() > 0) {
            Tree.errorListener.msgs.forEach(System.out::println);
            System.exit(1);
        }
    }
}
