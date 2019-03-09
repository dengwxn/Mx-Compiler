import AST.Build.ParseListener;
import AST.Build.Tree;
import Parser.MxLexer;
import Parser.MxParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        buildAST();
    }

    private static void buildAST() {
        try {
            InputStream is = new FileInputStream("./testcase/naive.c");
            CharStream input = CharStreams.fromStream(is);
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MxParser parser = new MxParser(tokens);

            parser.removeErrorListeners();
            Tree.init();
            parser.addErrorListener(Tree.errorListener);

            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            ParseListener parseListener = new ParseListener();
            walker.walk(parseListener, tree);
            is.close();

            Tree.errorAnalyze();
            parseListener.dump();
            Tree AST = new Tree(parseListener);
            AST.check();
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
