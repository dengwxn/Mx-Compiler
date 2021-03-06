package AST.Build;

import Parser.MxBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;

import static AST.Build.Tree.errorListener;

public class Listener extends MxBaseListener {
    static int row, col;

    public static void addCompileError(String msg) {
        errorListener.addCompilerError(String.format("line %d:%d %s", Listener.row, Listener.col, msg));
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        Listener.row = ctx.getStart().getLine();
        Listener.col = ctx.getStart().getCharPositionInLine();
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        Listener.row = ctx.getStart().getLine();
        Listener.col = ctx.getStart().getCharPositionInLine();
    }
}
