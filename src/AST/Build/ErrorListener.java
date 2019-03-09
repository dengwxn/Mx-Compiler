package AST.Build;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;

public class ErrorListener extends BaseErrorListener {
    public ArrayList<String> msgs;

    public ErrorListener() {
        msgs = new ArrayList<>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        msgs.add("line " + line + ":" + charPositionInLine + " " + msg);
    }
}
