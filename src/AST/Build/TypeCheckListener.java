package AST.Build;

import AST.Basic.ExprNode;
import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.ArrayType;
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
        if (defType != null) {
            symbolTable.put(varDeclStmt.getName(), defType);
            Type exprType = varDeclStmt.getExprType();
            if (exprType != null && !defType.canOperateWith(exprType))
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
    public void exitNewArray(MxParser.NewArrayContext ctx) {
        NewArrayExprNode newArrayExpr = (NewArrayExprNode) map.get(ctx);
        Type baseType = symbolTable.get(newArrayExpr.getBase());
        if (!(baseType instanceof ArrayType))
            newArrayExpr.setType(new ArrayType(baseType, newArrayExpr.getDim()));
        else
            addCompileError("expected a valid type to new an array.");
    }

    @Override
    public void exitArray(MxParser.ArrayContext ctx) {
        ArrayExprNode arrayExpr = (ArrayExprNode) map.get(ctx);
        Type arrayType = arrayExpr.getNameType();
        if (arrayType instanceof ArrayType) {
            Type base = ((ArrayType) arrayType).getBase();
            int dim = ((ArrayType) arrayType).getDim() - arrayExpr.getDim();
            if (dim > 0)
                arrayExpr.setType(new ArrayType(base, dim));
            else
                arrayExpr.setType(symbolTable.get("int"));
        } else
            addCompileError("expected an array type.");
    }

    @Override
    public void exitFunctionCall(MxParser.FunctionCallContext ctx) {
        FuncCallExprNode funcCallExpr = (FuncCallExprNode) map.get(ctx);
        if (funcCallExpr.getFuncType() instanceof FuncType) {
            FuncType funcType = (FuncType) funcCallExpr.getFuncType();
            if (funcType != null) {
                funcCallExpr.setType(funcType.getRetType());
                ArrayList<ExprNode> param = funcCallExpr.getParam();
                ArrayList<Type> paramType = funcType.getParamType();
                if (param.size() != paramType.size()) {
                    addCompileError(String.format("expected %d parameter(s).", paramType.size()));
                    return;
                }
                for (int i = 0; i < param.size(); ++i) {
                    if (!paramType.get(i).canOperateWith(param.get(i).getType()))
                        addCompileError(String.format("expected a '%s' type parameter.", paramType.get(i).getTypeName()));
                }
            }
        }
    }

    @Override
    public void exitPrefix(MxParser.PrefixContext ctx) {
        PrefixExprNode prefixExpr = (PrefixExprNode) map.get(ctx);
        prefixExpr.setType(prefixExpr.getExprType());
        if (prefixExpr.getOp().equals("!")) {
            if (!symbolTable.get("bool").canOperateWith(prefixExpr.getExprType()))
                addCompileError("expected a 'bool' type expression.");
        } else {
            if (!symbolTable.get("int").canOperateWith(prefixExpr.getExprType()))
                addCompileError("expected a 'int' type expression.");
        }
    }

    @Override
    public void exitSuffix(MxParser.SuffixContext ctx) {
        SuffixExprNode suffixExpr = (SuffixExprNode) map.get(ctx);
        suffixExpr.setType(suffixExpr.getExprType());
        if (!symbolTable.get("int").canOperateWith(suffixExpr.getExprType()))
            addCompileError("expected a 'int' type expression.");
    }

    @Override
    public void exitMember(MxParser.MemberContext ctx) {
        MemberExprNode memberExpr = (MemberExprNode) map.get(ctx);
        String name = memberExpr.getRootTypeName() + "." + memberExpr.getIdent();
        Type type = symbolTable.get(name);
        memberExpr.setType(type);
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
        if (!lhsExprType.canOperateWith(rhsExprType)) {
            addCompileError("expected two expressions of same type.");
        } else if (op.equals("==") || op.equals("!=")) {
            binaryExpr.setType(symbolTable.get("bool"));
        } else if (op.equals("&&") || op.equals("||")) {
            binaryExpr.setType(symbolTable.get("bool"));
            if (!symbolTable.get("bool").canOperateWith(lhsExprType))
                addCompileError("expected a 'bool' type expression.");
        } else {
            if (symbolTable.get("int").canOperateWith(lhsExprType))
                binaryExpr.setType(symbolTable.get("int"));
            else if (symbolTable.get("string").canOperateWith(lhsExprType))
                binaryExpr.setType(symbolTable.get("string"));
            else
                addCompileError("expected two expressions of int type or string type.");
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
            if (!lhsType.canOperateWith(rhsType))
                addCompileError(String.format("expected a '%s' type expression on the right.", lhsType.getTypeName()));
        }
    }

    @Override
    public void exitString(MxParser.StringContext ctx) {
        StringCstExprNode stringCstExpr = (StringCstExprNode) map.get(ctx);
        stringCstExpr.setType(symbolTable.get("string"));
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
        if (!exprType.canOperateWith(retType))
            addCompileError(String.format("expected a '%s' type expression.", retType.getTypeName()));
    }
}
