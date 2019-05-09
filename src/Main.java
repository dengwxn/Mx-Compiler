import AST.Build.ClassListener;
import AST.Build.DeclarationListener;
import AST.Build.ParseListener;
import AST.Build.TypeCheckListener;
import Generator.Build.Generator;
import IR.Build.IR;
import Optimizer.*;
import Parser.MxLexer;
import Parser.MxParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static AST.Build.Tree.errorListener;

public class Main {
    static public void main(String[] args) throws Exception {
        buildAST();
        generateIR();
        optimize();
        generate();
    }

    static private void generateIR() throws Exception {
        IR.generate();
        IR.dump("plain");
    }

    static private void optimize() throws Exception {
        int cnt = Inline.optimize();
        for (int i = 0; i < cnt; ++i) {
            Propagation.optimize();
            NeedednessAnalysis.optimize();
        }
        GlobalVarLoading.optimize();
        for (int i = 0; i < cnt - 1; ++i) {
            Propagation.optimize();
            NeedednessAnalysis.optimize();
        }
        RegisterAllocation.optimize();
    }

    static private void generate() throws Exception {
        Generator.generate();
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
