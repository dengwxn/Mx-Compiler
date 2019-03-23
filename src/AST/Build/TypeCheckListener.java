package AST.Build;

import AST.Basic.ExprNode;
import AST.Basic.Listener;
import AST.Basic.Type;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Loop.ForStmtNode;
import AST.Loop.WhileStmtNode;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.IfStmtNode;
import AST.Statement.VarDeclStmtNode;
import AST.Type.*;
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
        if (!symbolTable.inClassDeclScope()) {
            VarDeclStmtNode varDeclStmt = (VarDeclStmtNode) map.get(ctx);
            Type defType = typeTable.get(varDeclStmt.getType());
            if (defType != null) {
                if (defType instanceof VoidType)
                    addCompileError("'void' type must not be the type of a variable.");
                else if (defType instanceof NullType)
                    addCompileError("'null' type must not be the type of a variable.");
                else {
                    symbolTable.put(varDeclStmt.getName(), defType);
                    Type exprType = varDeclStmt.getExprType();
                    if (exprType != null && !defType.canOperateWith(exprType))
                        addCompileError(String.format("expect a '%s' type expression.", defType.getTypeName()));
                }
            }
        }
    }

    @Override
    public void enterClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        enterScope();
        ClassDeclNode classDecl = (ClassDeclNode) map.get(ctx);
        symbolTable.setClassName(classDecl.getName());

        for (VarDeclStmtNode varDecl : classDecl.getVarDecl()) {
            String varName = classDecl.getName() + "." + varDecl.getName();
            symbolTable.put(varDecl.getName(), symbolTable.get(varName));
        }

        for (FuncDeclNode funcDecl : classDecl.getFuncDecl()) {
            String funcName = classDecl.getName() + "." + funcDecl.getFuncName();
            symbolTable.put(funcDecl.getFuncName(), symbolTable.get(funcName));
        }
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
        exitScope();
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        FuncType funcType = (FuncType) symbolTable.get(funcDecl.getFuncName());
        if (funcDecl.getFuncName() == null) {
            if (!symbolTable.inClassDeclScope()) {
                addCompileError("expect an identifier of the function name.");
            } else if (!symbolTable.getClassName().equals(funcType.getRetType().getTypeName())) {
                addCompileError("an illegal function declaration without a function name.");
            }
        }
        enterScope();
        symbolTable.setRetType(funcType.getRetType());
        ArrayList<String> paramType = funcDecl.getParamType();
        ArrayList<String> paramName = funcDecl.getParamName();
        for (int i = 0; i < paramName.size(); ++i)
            symbolTable.put(paramName.get(i), typeTable.get(paramType.get(i)));
    }

    @Override
    public void exitFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        exitScope();
    }

    @Override
    public void enterBlockStatement(MxParser.BlockStatementContext ctx) {
        enterScope();
    }

    @Override
    public void exitBlockStatement(MxParser.BlockStatementContext ctx) {
        exitScope();
    }

    @Override
    public void exitIfStatement(MxParser.IfStatementContext ctx) {
        IfStmtNode ifStmt = (IfStmtNode) map.get(ctx);
        ExprNode condExpr = ifStmt.getCondExpr();
        if (!(condExpr.getType() instanceof BoolType))
            addCompileError("expect a 'bool' type expression in the if-condition.");
    }

    @Override
    public void enterFor(MxParser.ForContext ctx) {
        enterScope();
        enterLoop();
    }

    @Override
    public void enterWhile(MxParser.WhileContext ctx) {
        enterScope();
        enterLoop();
    }

    @Override
    public void exitFor(MxParser.ForContext ctx) {
        ForStmtNode forStmt = (ForStmtNode) map.get(ctx);
        ExprNode condExpr = forStmt.getCondExpr();
        if (condExpr != null && !(condExpr.getType() instanceof BoolType))
            addCompileError("expect a 'bool' type expression in the for-condition.");
        exitScope();
        exitLoop();
    }

    @Override
    public void exitWhile(MxParser.WhileContext ctx) {
        WhileStmtNode whileStmt = (WhileStmtNode) map.get(ctx);
        ExprNode condExpr = whileStmt.getCondExpr();
        if (!(condExpr.getType() instanceof BoolType))
            addCompileError("expect a 'bool' type expression in the while-condition.");
        exitScope();
        exitLoop();
    }

    @Override
    public void enterContinue(MxParser.ContinueContext ctx) {
        if (getLoopCount() == 0)
            addCompileError("expect 'continue' to appear inside a loop.");
    }

    @Override
    public void enterBreak(MxParser.BreakContext ctx) {
        if (getLoopCount() == 0)
            addCompileError("expect 'break' to appear inside a loop.");
    }

    @Override
    public void exitNewArray(MxParser.NewArrayContext ctx) {
        NewArrayExprNode newArrayExpr = (NewArrayExprNode) map.get(ctx);
        Type baseType = symbolTable.get(newArrayExpr.getBase());
        if (!(baseType instanceof ArrayType))
            newArrayExpr.setType(new ArrayType(baseType, newArrayExpr.getDim()));
        else
            addCompileError("expect a valid type to new an array.");
    }

    @Override
    public void exitNewType(MxParser.NewTypeContext ctx) {
        NewTypeExprNode newTypeExpr = (NewTypeExprNode) map.get(ctx);
        Type baseType = typeTable.get(newTypeExpr.getBase());
        if (baseType instanceof VoidType)
            addCompileError("'void' type must not be the type of a variable.");
        else if (baseType instanceof NullType)
            addCompileError("'null' type must not be the type of a variable.");
        else
            newTypeExpr.setType(baseType);
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
                arrayExpr.setType(base);
        } else
            addCompileError("expect an array type.");
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
                    addCompileError(String.format("expect %d parameter(s).", paramType.size()));
                    return;
                }
                for (int i = 0; i < param.size(); ++i) {
                    if (paramType.get(i) != null) {
                        if (!paramType.get(i).canOperateWith(param.get(i).getType()))
                            addCompileError(String.format("expect a '%s' type parameter.", paramType.get(i).getTypeName()));
                    }
                }
            }
        } else if (funcCallExpr.getFuncType() != null) {
            addCompileError(String.format("Identifier '%s' not defined to be a function call.", funcCallExpr.getFuncName()));
        }
    }

    @Override
    public void exitPrefix(MxParser.PrefixContext ctx) {
        PrefixExprNode prefixExpr = (PrefixExprNode) map.get(ctx);
        prefixExpr.setType(prefixExpr.getExprType());
        if (prefixExpr.getOp().equals("!")) {
            if (!symbolTable.get("bool").canOperateWith(prefixExpr.getExprType()))
                addCompileError("expect a 'bool' type expression.");
        } else {
            if (!symbolTable.get("int").canOperateWith(prefixExpr.getExprType()))
                addCompileError("expect a 'int' type expression.");
            else if (prefixExpr.getOp().equals("++") || prefixExpr.getOp().equals("--")) {
                if (!prefixExpr.getExpr().isLeftValue())
                    addCompileError("expect a l-value expression.");
            }
        }
    }

    @Override
    public void exitSuffix(MxParser.SuffixContext ctx) {
        SuffixExprNode suffixExpr = (SuffixExprNode) map.get(ctx);
        suffixExpr.setType(suffixExpr.getExprType());
        if (!symbolTable.get("int").canOperateWith(suffixExpr.getExprType()))
            addCompileError("expect a 'int' type expression.");
    }

    @Override
    public void exitMember(MxParser.MemberContext ctx) {
        MemberExprNode memberExpr = (MemberExprNode) map.get(ctx);
        String name = memberExpr.getRootTypeName() + "." + memberExpr.getIdent();
        Type type = symbolTable.get(name);
        memberExpr.setType(type);
    }

    @Override
    public void exitThis(MxParser.ThisContext ctx) {
        ThisExprNode thisExpr = (ThisExprNode) map.get(ctx);
        if (symbolTable.getClassName() != null)
            thisExpr.setType(symbolTable.get(symbolTable.getClassName()));
        else
            addCompileError("expect a class scope to obtain 'this'.");
    }

    @Override
    public void exitIdentifier(MxParser.IdentifierContext ctx) {
        IdentExprNode identExpr = (IdentExprNode) map.get(ctx);
        identExpr.setType(symbolTable.get(identExpr.getIdent()));
    }

    void exitBinaryExpression(BinaryExprNode binaryExpr, Type base) {
        Type lhsExprType = binaryExpr.getLhsType();
        Type rhsExprType = binaryExpr.getRhsType();
        if (lhsExprType != null) {
            if (!lhsExprType.canOperateWith(rhsExprType))
                addCompileError("expect two expressions of same type.");
            else if (base.canOperateWith(lhsExprType))
                binaryExpr.setType(base);
            else
                addCompileError(String.format("expect two expressions of '%s' type.", base.getTypeName()));
        }
    }

    @Override
    public void exitMulDivMod(MxParser.MulDivModContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("int"));
    }

    @Override
    public void exitAddSub(MxParser.AddSubContext ctx) {
        BinaryExprNode binaryExpr = (BinaryExprNode) map.get(ctx);
        Type lhsExprType = binaryExpr.getLhsType();
        Type rhsExprType = binaryExpr.getRhsType();
        if (lhsExprType != null) {
            if (!lhsExprType.canOperateWith(rhsExprType)) {
                addCompileError("expect two expressions of same type.");
            } else {
                if (symbolTable.get("string").canOperateWith(lhsExprType)) {
                    if (ctx.op.getText().equals("+")) {
                        binaryExpr.setType(symbolTable.get("string"));
                    } else {
                        addCompileError("two string expressions can not take the '-' operation.");
                    }
                } else if (symbolTable.get("int").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.get("int"));
                } else {
                    addCompileError("expect two expressions of int type.");
                }
            }
        }
    }

    @Override
    public void exitShift(MxParser.ShiftContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("int"));
    }

    @Override
    public void exitCompare(MxParser.CompareContext ctx) {
        BinaryExprNode binaryExpr = (BinaryExprNode) map.get(ctx);
        Type lhsExprType = binaryExpr.getLhsType();
        Type rhsExprType = binaryExpr.getRhsType();
        if (lhsExprType != null) {
            if (!lhsExprType.canOperateWith(rhsExprType)) {
                addCompileError("expect two expressions of same type.");
            } else {
                if (symbolTable.get("string").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.get("bool"));
                } else if (symbolTable.get("int").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.get("bool"));
                } else {
                    addCompileError("expect two expressions of int type or string type.");
                }
            }
        }
    }

    @Override
    public void exitEqual(MxParser.EqualContext ctx) {
        BinaryExprNode binaryExpr = (BinaryExprNode) map.get(ctx);
        Type lhsExprType = binaryExpr.getLhsType();
        Type rhsExprType = binaryExpr.getRhsType();
        if (lhsExprType != null) {
            if (!lhsExprType.canOperateWith(rhsExprType)) {
                addCompileError("expect two expressions of same type.");
            } else {
                binaryExpr.setType(symbolTable.get("bool"));
            }
        }
    }

    @Override
    public void exitAnd(MxParser.AndContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("int"));
    }

    @Override
    public void exitXor(MxParser.XorContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("int"));
    }

    @Override
    public void exitOr(MxParser.OrContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("int"));
    }

    @Override
    public void exitLogicAnd(MxParser.LogicAndContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("bool"));
    }

    @Override
    public void exitLogicOr(MxParser.LogicOrContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.get("bool"));
    }

    @Override
    public void exitAssign(MxParser.AssignContext ctx) {
        AssignExprNode assignExpr = (AssignExprNode) map.get(ctx);
        if (assignExpr == null) return;
        Type lhsType = assignExpr.getLhsType();
        Type rhsType = assignExpr.getRhsType();
        if (lhsType != null) {
            if (!(lhsType instanceof VoidType)) {
                if (!lhsType.canOperateWith(rhsType))
                    addCompileError(String.format("expect a '%s' type expression on the right.", lhsType.getTypeName()));
                else
                    assignExpr.setType(lhsType);
            } else {
                addCompileError("the left hand side must not be 'void' type.");
            }
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
        if (retType != null) {
            if (!retType.canOperateWith(exprType))
                addCompileError(String.format("expect a '%s' type expression.", retType.getTypeName()));
        }
    }
}
