package AST.Build;

import AST.Basic.ExprNode;
import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.FuncType;
import Parser.MxParser;

import java.util.ArrayList;

import static AST.Build.Tree.*;

public class TypeCheckListener extends Listener {
    @Override
    public void exitProgram(MxParser.ProgramContext ctx) {
        errorAnalyze();
    }

    @Override
    public void exitVariableDeclaration(MxParser.VariableDeclarationContext ctx) {
        VarDeclStmtNode varDeclStmt = (VarDeclStmtNode) map.get(ctx);
        Type defType = symbolTable.get(varDeclStmt.getType());
        if (defType != null)
            symbolTable.put(varDeclStmt.getName(), defType);
        if (varDeclStmt.getExpr() != null) {
            Type exprType = varDeclStmt.getExprType();
            if (defType != exprType)
                addCompileError(String.format("expected a '%s' type expression.", defType.getTypeName()));
        }
    }

    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        enterNewScope();
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.setClassName(classDecl.getName());
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
        exitCurScope();
    }

    String getFuncName(FuncDeclNode funcDecl) {
        if (symbolTable.getClassName() == null)
            return funcDecl.getFuncName();
        else
            return symbolTable.getClassName() + "." + funcDecl.getFuncName();
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        FuncType funcType = (FuncType) symbolTable.get(getFuncName(funcDecl));
        if (funcDecl.getFuncName() == null) {
            if (symbolTable.getClassName() == null) {
                addCompileError("expected an identifier of the function name.");
            } else if (!symbolTable.getClassName().equals(funcType.getRetType().getTypeName())) {
                addCompileError("an illegal function declaration without a function name.");
            }
        }
        enterNewScope();
        symbolTable.setRetType(funcType.getRetType());
        ArrayList<String> paramType = funcDecl.getParamType();
        ArrayList<String> paramName = funcDecl.getParamName();
        for (int i = 0; i < paramName.size(); ++i)
            symbolTable.put(paramName.get(i), symbolTable.get(paramType.get(i)));
    }

    @Override
    public void exitFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        exitCurScope();
    }

    @Override
    public void enterBlockStatement(MxParser.BlockStatementContext ctx) {
        enterNewScope();
    }

    @Override
    public void exitBlockStatement(MxParser.BlockStatementContext ctx) {
        exitCurScope();
    }

    @Override
    public void exitFunctionCall(MxParser.FunctionCallContext ctx) {
        FuncCallExprNode funcCallExpr = (FuncCallExprNode) map.get(ctx);
        FuncType funcType = (FuncType) symbolTable.get(funcCallExpr.getFuncName());
        if (funcType != null) {
            funcCallExpr.setType(funcType.getRetType());
            ArrayList<ExprNode> param = funcCallExpr.getParam();
            ArrayList<Type> paramType = funcType.getParamType();
            if (param.size() != paramType.size()) {
                addCompileError(String.format("expected %d parameter(s).", paramType.size()));
                return;
            }
            for (int i = 0; i < param.size(); ++i) {
                if (paramType.get(i) != param.get(i).getType())
                    addCompileError(String.format("expected a '%s' type parameter.", paramType.get(i).getTypeName()));
            }
        }
    }

    @Override
    public void exitPrefix(MxParser.PrefixContext ctx) {
        PrefixExprNode prefixExpr = (PrefixExprNode) map.get(ctx);
        prefixExpr.setType(prefixExpr.getExprType());
        if (prefixExpr.getOp().equals("!")) {
            if (symbolTable.get("bool") != prefixExpr.getExprType())
                addCompileError("expected a 'bool' type expression.");
        } else {
            if (symbolTable.get("int") != prefixExpr.getExprType())
                addCompileError("expected a 'int' type expression.");
        }
    }

    @Override
    public void exitSuffix(MxParser.SuffixContext ctx) {
        SuffixExprNode suffixExpr = (SuffixExprNode) map.get(ctx);
        suffixExpr.setType(suffixExpr.getExprType());
        if (symbolTable.get("int") != suffixExpr.getExprType())
            addCompileError("expected a 'int' type expression.");
    }

    @Override
    public void exitMember(MxParser.MemberContext ctx) {
        MemberExprNode memberExpr = (MemberExprNode) map.get(ctx);
        String name = memberExpr.getClsName() + "." + memberExpr.getIdent();
        memberExpr.setType(symbolTable.get(name));
    }

    @Override
    public void exitIdentifier(MxParser.IdentifierContext ctx) {
        IdentExprNode identExpr = (IdentExprNode) map.get(ctx);
        identExpr.setType(symbolTable.get(identExpr.getIdent()));
    }

    void exitBinaryExpression(MxParser.ExpressionContext ctx, String op) {
        BinaryExprNode binaryExpr = (BinaryExprNode) map.get(ctx);
        Type lhsExprType = binaryExpr.getLhsType();
        Type rhsExprType = binaryExpr.getRhsType();
        if (op.equals("==") || op.equals("!=")) {
            binaryExpr.setType(symbolTable.get("bool"));
            if (lhsExprType != rhsExprType)
                addCompileError("expected two expressions of same type.");
        } else if (op.equals("&&") || op.equals("||")) {
            binaryExpr.setType(symbolTable.get("bool"));
            if (symbolTable.get("bool") == lhsExprType && symbolTable.get("bool") == rhsExprType)
                addCompileError("expected a 'bool' type expression.");
        } else {
            binaryExpr.setType(symbolTable.get("int"));
            if (symbolTable.get("int") != lhsExprType)
                addCompileError("expected a 'int' type expression on the left.");
            if (symbolTable.get("int") != rhsExprType)
                addCompileError("expected a 'int' type expression on the right.");
        }
    }

    @Override
    public void exitMulDivMod(MxParser.MulDivModContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitAddSub(MxParser.AddSubContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitShift(MxParser.ShiftContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitCompare(MxParser.CompareContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitEqual(MxParser.EqualContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitAnd(MxParser.AndContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitXor(MxParser.XorContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitOr(MxParser.OrContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitLogicAnd(MxParser.LogicAndContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitLogicOr(MxParser.LogicOrContext ctx) {
        exitBinaryExpression(ctx, ctx.op.getText());
    }

    @Override
    public void exitAssign(MxParser.AssignContext ctx) {
        AssignExprNode assignExpr = (AssignExprNode) map.get(ctx);
        if (assignExpr == null) return;
        Type lhsType = assignExpr.getLhsType();
        Type rhsType = assignExpr.getRhsType();
        if (lhsType != null) {
            assignExpr.setType(rhsType);
            if (lhsType != rhsType)
                addCompileError(String.format("expected a '%s' type expression on the right.", lhsType.getTypeName()));
        }
    }

    @Override
    public void exitBool(MxParser.BoolContext ctx) {
        BoolCstExprNode boolCstExpr = (BoolCstExprNode) map.get(ctx);
        boolCstExpr.setType(symbolTable.get("bool"));
    }

    @Override
    public void exitNumber(MxParser.NumberContext ctx) {
        NumberCstExprNode numberCstExpr = (NumberCstExprNode) map.get(ctx);
        numberCstExpr.setType(symbolTable.get("int"));
    }

    @Override
    public void exitNull(MxParser.NullContext ctx) {
        NullCstExprNode nullCstExpr = (NullCstExprNode) map.get(ctx);
        nullCstExpr.setType(symbolTable.get("null"));
    }

    @Override
    public void exitReturn(MxParser.ReturnContext ctx) {
        ReturnStmtNode returnStmt = (ReturnStmtNode) map.get(ctx);
        Type exprType = returnStmt.getExprType();
        Type retType = symbolTable.getRetType();
        if (exprType != retType)
            addCompileError(String.format("expected a '%s' type expression.", retType.getTypeName()));
    }
}
