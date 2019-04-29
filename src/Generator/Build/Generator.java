package Generator.Build;

import AST.Build.Node;
import AST.Build.Tree;
import AST.Statement.VarDeclStmtNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static IR.Build.IR.*;

public class Generator {
    static public void generate() throws Exception {
        String header = readFileAsString("./lib/header.asm");
        String builtin = readFileAsString("./lib/builtin.asm");

        StringBuilder str = new StringBuilder();
        str.append(header + "\n");
        str.append(toNASM() + "\n");
        str.append(generateData() + "\n");
        str.append(generateBss() + "\n");
        str.append(builtin + "\n");

        File file = new File("prog.asm");
        OutputStream fout = new FileOutputStream(file);
        PrintStream fprint = new PrintStream(fout);
        fprint.print(str.toString());
        System.out.println(str.toString());
    }

    static private String generateData() {
        StringBuilder str = new StringBuilder();
        str.append("section .data\n");
        for (Map.Entry<String, Integer> entry : stringConst.entrySet()) {
            String key = entry.getKey();
            str.append(formatInstr("dq", String.valueOf(convertLiteralEscape(key).length() - 2)));
            str.append("_string_constant_" + entry.getValue() + ":\n");
            key = convertEscapeToNumber(key);
            str.append(formatInstr("db", key + ", 0"));
        }
        return str.toString();
    }

    static private String convertEscapeToNumber(String s) {
        String t = s;
        t = t.replace("\\b", "\", 8, \"");
        t = t.replace("\\t", "\", 9, \"");
        t = t.replace("\\n", "\", 10, \"");
        t = t.replace("\\r", "\", 13, \"");
        t = t.replace("\\\"", "\", 34, \"");
        t = t.replace("\\\\", "\", 92, \"");
        t = t.replace(", \"\"", "");
        return t;
    }

    static private String convertLiteralEscape(String s) {
        String t = s;
        t = t.replace("\\b", "\b");
        t = t.replace("\\t", "\t");
        t = t.replace("\\n", "\n");
        t = t.replace("\\r", "\r");
        t = t.replace("\\\"", "\"");
        t = t.replace("\\\\", "\\");
        return t;
    }

    static private String generateBss() {
        StringBuilder str = new StringBuilder();
        str.append("section .bss\n");
        for (Node decl : Tree.prog.getDecl()) {
            if (decl instanceof VarDeclStmtNode) {
                str.append(((VarDeclStmtNode) decl).getSymbolName() + ":\n");
                str.append(formatInstr("resq", "1"));
            }
        }
        return str.toString();
    }

    static private String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
