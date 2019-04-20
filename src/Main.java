import AST.Build.*;
import AST.Table.Symbol;
import IR.Build.IR;
import Parser.MxLexer;
import Parser.MxParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;

import static AST.Build.Tree.errorListener;

public class Main {
    static public void main(String[] args) throws Exception {
        buildAST();
        // Tree.dump();
        generateIR();
    }

    static private void generateIR() throws Exception {
        IR.generate();
        IR.dump();
    }

    static private void buildAST() throws Exception {
        // InputStream is = new FileInputStream("./testcase/naive.c");
        InputStream is = System.in;
        CharStream input = CharStreams.fromStream(is);
        MxLexer lexer = new MxLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MxParser parser = new MxParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ParseListener(), tree);
        walker.walk(new ClassListener(), tree);
        walker.walk(new DeclarationListener(), tree);
        walker.walk(new TypeCheckListener(), tree);
    }
}
