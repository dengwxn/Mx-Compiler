package AST.Build;

import AST.Program.ProgNode;

public class Tree {
    public static ErrorListener errorListener;
    ProgNode prog;

    public static void init() {
        errorListener = new ErrorListener();
    }

    public Tree(ParseListener p) {
        prog = p.prog;
    }

    public static void errorAnalyze() {
        if (Tree.errorListener.msgs.size() > 0) {
            Tree.errorListener.msgs.forEach(System.out::println);
            System.exit(1);
        }
    }

    public void check() {
        prog.declarationVisit();
        prog.typeCheckVisit();
        errorAnalyze();
    }
}
