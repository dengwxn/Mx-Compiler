package AST.Build;

import AST.Branch.BreakStmtNode;
import AST.Branch.ContinueStmtNode;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Loop.ForStmtNode;
import AST.Loop.WhileStmtNode;
import AST.Program.ClassDeclNode;
import AST.Program.FuncDeclNode;
import AST.Statement.IfStmtNode;
import AST.Statement.VarDeclStmtNode;
import AST.Table.Symbol;
import AST.Type.*;
import Parser.MxParser;

import java.util.ArrayList;

import static AST.Build.Tree.*;
import static IR.Operand.Address.putOffset;

public class TypeCheckListener extends Listener {
    @Override
    public void exitProgram(MxParser.ProgramContext ctx) {
        errorAnalyze();
    }

    @Override
    public void exitVariableDeclaration(MxParser.VariableDeclarationContext ctx) {
        if (!symbolTable.isInClassDeclScope()) {
            VarDeclStmtNode varDeclStmt = (VarDeclStmtNode) map.get(ctx);
            Type defType = typeTable.getType(varDeclStmt.getType());
            if (defType != null) {
                if (defType instanceof VoidType)
                    addCompileError("'void' type must not be the type of a variable.");
                else if (defType instanceof NullType)
                    addCompileError("'null' type must not be the type of a variable.");
                else {
                    symbolTable.putType(varDeclStmt.getName(), defType);
                    symbolTable.putSymbol(varDeclStmt.getName());
                    varDeclStmt.setSymbol(symbolTable.getSymbol(varDeclStmt.getName()));
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

        int cnt = 0;
        for (VarDeclStmtNode varDecl : classDecl.getVarDecl()) {
            String varName = classDecl.getName() + "." + varDecl.getName();
            symbolTable.putType(varDecl.getName(), symbolTable.getType(varName));
            symbolTable.putSymbol(varDecl.getName(), classDecl.getName());
            putOffset(varName, (cnt++) << 3);
            if (varDecl.getName().equals("this"))
                addCompileError("'this' must not be a variable name.");
        }
        putOffset(classDecl.getName(), cnt << 3);

        for (FuncDeclNode funcDecl : classDecl.getFuncDecl()) {
            String funcName = funcDecl.getFuncName();
            symbolTable.putType(filterClassName(funcName), symbolTable.getType(funcName));
        }
    }

    private String filterClassName(String str) {
        return str.substring(str.indexOf(".") + 1);
    }

    @Override
    public void exitClassDeclaration(MxParser.ClassDeclarationContext ctx) {
        symbolTable.setClassName(null);
        exitScope();
    }

    @Override
    public void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = (FuncDeclNode) map.get(ctx);
        FuncType funcType = (FuncType) symbolTable.getType(funcDecl.getFuncName());
        if (funcDecl.getFuncName() == null) {
            if (!symbolTable.isInClassDeclScope()) {
                addCompileError("expect an identifier of the function name.");
            } else if (!symbolTable.getClassName().equals(funcType.getRetType().getTypeName())) {
                addCompileError("an illegal function declaration without a function name.");
            }
        }
        enterScope();
        if (funcDecl.getFuncName() != null)
            symbolTable.setRetType(funcType.getRetType());
        ArrayList<String> paramType = funcDecl.getParamType();
        ArrayList<String> paramName = funcDecl.getParamName();
        ArrayList<Symbol> paramSymbol = new ArrayList<>();
        for (int i = 0; i < paramName.size(); ++i) {
            symbolTable.putType(paramName.get(i), typeTable.getType(paramType.get(i)));
            symbolTable.putSymbol(paramName.get(i));
            paramSymbol.add(symbolTable.getSymbol(paramName.get(i)));
        }
        funcDecl.setParamSymbol(paramSymbol);
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
    public void enterIfStatement(MxParser.IfStatementContext ctx) {
        enterScope();
    }

    @Override
    public void exitIfStatement(MxParser.IfStatementContext ctx) {
        IfStmtNode ifStmt = (IfStmtNode) map.get(ctx);
        ExprNode condExpr = ifStmt.getCondExpr();
        if (!(condExpr.getType() instanceof BoolType))
            addCompileError("expect a 'bool' type expression in the if-condition.");
        exitScope();
    }

    @Override
    public void enterFor(MxParser.ForContext ctx) {
        enterScope();
        ForStmtNode forStmt = (ForStmtNode) map.get(ctx);
        enterLoop(forStmt);
    }

    @Override
    public void enterWhile(MxParser.WhileContext ctx) {
        enterScope();
        WhileStmtNode whileStmt = (WhileStmtNode) map.get(ctx);
        enterLoop(whileStmt);
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
        if (getLoopCount() == 0) {
            addCompileError("expect 'continue' to appear inside a loop.");
        } else {
            ContinueStmtNode continueStmt = (ContinueStmtNode) map.get(ctx);
            continueStmt.setLoopStmt(getLoopStmt());
        }
    }

    @Override
    public void enterBreak(MxParser.BreakContext ctx) {
        if (getLoopCount() == 0) {
            addCompileError("expect 'break' to appear inside a loop.");
        } else {
            BreakStmtNode breakStmt = (BreakStmtNode) map.get(ctx);
            breakStmt.setLoopStmt(getLoopStmt());
        }
    }

    @Override
    public void exitNewArray(MxParser.NewArrayContext ctx) {
        NewArrayExprNode newArrayExpr = (NewArrayExprNode) map.get(ctx);
        Type baseType = symbolTable.getType(newArrayExpr.getBase());
        if (!(baseType instanceof ArrayType))
            newArrayExpr.setType(new ArrayType(baseType, newArrayExpr.getDim()));
        else
            addCompileError("expect a valid type to new an array.");
    }

    @Override
    public void exitNewType(MxParser.NewTypeContext ctx) {
        NewTypeExprNode newTypeExpr = (NewTypeExprNode) map.get(ctx);
        Type baseType = typeTable.getType(newTypeExpr.getBase());
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

                int thisShift = 0;
                if (funcType.isClassFunc()) thisShift = 1;
                if (param.size() + thisShift != paramType.size()) {
                    addCompileError(String.format("expect %d parameter(s).", paramType.size() - thisShift));
                    return;
                }
                for (int i = 0; i < param.size(); ++i) {
                    Type type = paramType.get(i + thisShift);
                    if (type != null) {
                        if (!type.canOperateWith(param.get(i).getType()))
                            addCompileError(String.format("expect a '%s' type parameter.", type.getTypeName()));
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
            if (!symbolTable.getType("bool").canOperateWith(prefixExpr.getExprType()))
                addCompileError("expect a 'bool' type expression.");
        } else {
            if (!symbolTable.getType("int").canOperateWith(prefixExpr.getExprType()))
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
        if (!symbolTable.getType("int").canOperateWith(suffixExpr.getExprType()))
            addCompileError("expect a 'int' type expression.");
        else if (!suffixExpr.getExpr().isLeftValue())
            addCompileError("expect a l-value expression.");
    }

    @Override
    public void exitMember(MxParser.MemberContext ctx) {
        MemberExprNode memberExpr = (MemberExprNode) map.get(ctx);
        String name = memberExpr.getPrevTypeName() + "." + memberExpr.getIdent();
        Type type = symbolTable.getType(name);
        memberExpr.setType(type);
    }

    @Override
    public void exitIdentifier(MxParser.IdentifierContext ctx) {
        IdentExprNode identExpr = (IdentExprNode) map.get(ctx);
        identExpr.setType(symbolTable.getType(identExpr.getIdent()));
        Symbol symbol = symbolTable.getSymbol(identExpr.getIdent());
        if (symbol != null) {
            identExpr.setSymbol(symbol);
            if (symbol.isInClassDeclScope())
                identExpr.setClassThis(symbolTable.getSymbol("this"));
        }
        if (identExpr.getIdent().equals("this")) {
            if (symbolTable.getClassName() == null)
                addCompileError("expect a class scope to obtain 'this'.");
        }
    }

    private void exitBinaryExpression(BinaryExprNode binaryExpr, Type base) {
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
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("int"));
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
                if (symbolTable.getType("string").canOperateWith(lhsExprType)) {
                    if (ctx.op.getText().equals("+")) {
                        binaryExpr.setType(symbolTable.getType("string"));
                    } else {
                        addCompileError("two string expressions can not take the '-' operation.");
                    }
                } else if (symbolTable.getType("int").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.getType("int"));
                } else {
                    addCompileError("expect two expressions of int type.");
                }
            }
        }
    }

    @Override
    public void exitShift(MxParser.ShiftContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("int"));
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
                if (symbolTable.getType("string").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.getType("bool"));
                } else if (symbolTable.getType("int").canOperateWith(lhsExprType)) {
                    binaryExpr.setType(symbolTable.getType("bool"));
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
                binaryExpr.setType(symbolTable.getType("bool"));
            }
        }
    }

    @Override
    public void exitAnd(MxParser.AndContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("int"));
    }

    @Override
    public void exitXor(MxParser.XorContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("int"));
    }

    @Override
    public void exitOr(MxParser.OrContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("int"));
    }

    @Override
    public void exitLogicAnd(MxParser.LogicAndContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("bool"));
    }

    @Override
    public void exitLogicOr(MxParser.LogicOrContext ctx) {
        exitBinaryExpression((BinaryExprNode) map.get(ctx), symbolTable.getType("bool"));
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
        stringCstExpr.setType(symbolTable.getType("string"));
    }

    @Override
    public void exitBool(MxParser.BoolContext ctx) {
        BoolCstExprNode boolCstExpr = (BoolCstExprNode) map.get(ctx);
        boolCstExpr.setType(symbolTable.getType("bool"));
    }

    @Override
    public void exitNumber(MxParser.NumberContext ctx) {
        NumberCstExprNode numberCstExpr = (NumberCstExprNode) map.get(ctx);
        numberCstExpr.setType(symbolTable.getType("int"));
    }

    @Override
    public void exitNull(MxParser.NullContext ctx) {
        NullCstExprNode nullCstExpr = (NullCstExprNode) map.get(ctx);
        nullCstExpr.setType(symbolTable.getType("null"));
    }

    @Override
    public void exitReturn(MxParser.ReturnContext ctx) {
        ReturnStmtNode returnStmt = (ReturnStmtNode) map.get(ctx);
        Type exprType = returnStmt.getExprType();
        Type retType = symbolTable.getRetType();
        if (retType != null) {
            if (!retType.canOperateWith(exprType))
                addCompileError(String.format("expect a '%s' type expression.", retType.getTypeName()));
        } else if (exprType != null)
            addCompileError("expected no value returned in a class constructor.");
    }
}
