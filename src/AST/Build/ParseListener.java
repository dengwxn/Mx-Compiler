package AST.Build;

import AST.Basic.ExprNode;
import AST.Basic.Listener;
import AST.Basic.Node;
import AST.Basic.StmtNode;
import AST.Branch.BreakStmtNode;
import AST.Branch.ContinueStmtNode;
import AST.Branch.ReturnStmtNode;
import AST.Expression.*;
import AST.Loop.ForStmtNode;
import AST.Loop.WhileStmtNode;
import AST.Program.FuncDeclNode;
import AST.Program.ProgNode;
import AST.Statement.BlockStmtNode;
import AST.Statement.ExprStmtNode;
import AST.Statement.IfStmtNode;
import AST.Statement.VarDeclStmtNode;
import Parser.MxBaseListener;
import Parser.MxParser;
import org.antlr.v4.runtime.tree.ParseTree;

import static AST.Build.Tree.*;

public class ParseListener extends Listener {
    @Override
    public void exitProgram(MxParser.ProgramContext ctx) {
        prog = new ProgNode();
        ctx.declaration().forEach(declarationContext -> prog.addDecl((Node) map.get(declarationContext)));
        errorAnalyze();
    }

    @Override
    public void exitDeclaration(MxParser.DeclarationContext ctx) {
        map.put(ctx, map.get(ctx.getChild(0)));
    }

    @Override
    public void exitFunctionDeclaration(MxParser.FunctionDeclarationContext ctx) {
        FuncDeclNode funcDecl = new FuncDeclNode();
        ctx.dataType().forEach(dataTypeContext -> funcDecl.addTypeLit(dataTypeContext.getText()));
        ctx.Identifier().forEach(ident -> funcDecl.addName(ident.getText()));
        funcDecl.setBlockStmt((BlockStmtNode) map.get(ctx.blockStatement()));
        map.put(ctx, funcDecl);
    }

    @Override
    public void exitBlockStatement(MxParser.BlockStatementContext ctx) {
        BlockStmtNode blockStmt = new BlockStmtNode();
        ctx.statement().forEach(statementContext -> blockStmt.addStmt((StmtNode) map.get(statementContext)));
        map.put(ctx, blockStmt);
    }

    @Override
    public void exitStatement(MxParser.StatementContext ctx) {
        map.put(ctx, map.get(ctx.getChild(0)));
    }

    @Override
    public void exitIfStatement(MxParser.IfStatementContext ctx) {
        ExprNode ifExpr = (ExprNode) map.get(ctx.expression());
        StmtNode thenStmt = (StmtNode) map.get(ctx.statement(0));
        StmtNode elseStmt = (StmtNode) map.get(ctx.statement(1));
        map.put(ctx, new IfStmtNode(ifExpr, thenStmt, elseStmt));
    }

    @Override
    public void exitFor(MxParser.ForContext ctx) {
        ExprNode init, cond, incr;
        init = cond = incr = null;
        int counter = 0;
        for (ParseTree u : ctx.children) {
            if (u.getText().equals(";"))
                ++counter;
            if (u instanceof MxParser.ExpressionContext) {
                switch (counter) {
                    case 0:
                        init = (ExprNode) map.get(u);
                        break;
                    case 1:
                        cond = (ExprNode) map.get(u);
                        break;
                    case 2:
                        incr = (ExprNode) map.get(u);
                        break;
                }
            }
        }
        StmtNode thenStmt = (StmtNode) map.get(ctx.statement());
        map.put(ctx, new ForStmtNode(init, cond, incr, thenStmt));
    }

    @Override
    public void exitWhile(MxParser.WhileContext ctx) {
        ExprNode cond = (ExprNode) map.get(ctx.expression());
        StmtNode thenStmt = (StmtNode) map.get(ctx.statement());
        map.put(ctx, new WhileStmtNode(cond, thenStmt));
    }

    @Override
    public void exitVariableDeclaration(MxParser.VariableDeclarationContext ctx) {
        String type = ctx.dataType().getText();
        String name = ctx.Identifier().getText();
        ExprNode expr = (ExprNode) map.get(ctx.expression());
        map.put(ctx, new VarDeclStmtNode(type, name, expr));
    }

    @Override
    public void exitExpressionStatement(MxParser.ExpressionStatementContext ctx) {
        ExprNode expr = (ExprNode) map.get(ctx.expression());
        map.put(ctx, new ExprStmtNode(expr));
    }

    @Override
    public void exitFunctionCall(MxParser.FunctionCallContext ctx) {
        FuncCallExprNode funcCall = new FuncCallExprNode(ctx.Identifier().getText());
        ctx.expression().forEach(expr -> funcCall.addParam((ExprNode) map.get(expr)));
        map.put(ctx, funcCall);
    }

    @Override
    public void exitPrefix(MxParser.PrefixContext ctx) {
        ExprNode expr = (ExprNode) map.get(ctx.expression());
        map.put(ctx, new PrefixExprNode(ctx.op.getText(), expr));
    }

    @Override
    public void exitSuffix(MxParser.SuffixContext ctx) {
        ExprNode expr = (ExprNode) map.get(ctx.expression());
        map.put(ctx, new SuffixExprNode(ctx.op.getText(), expr));
    }

    @Override
    public void exitIdentifier(MxParser.IdentifierContext ctx) {
        map.put(ctx, new IdentExprNode(ctx.Identifier().getText()));
    }

    @Override
    public void exitSubExpr(MxParser.SubExprContext ctx) {
        map.put(ctx, map.get(ctx.expression()));
    }

    @Override
    public void exitMulDivMod(MxParser.MulDivModContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitAddSub(MxParser.AddSubContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitShift(MxParser.ShiftContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitCompare(MxParser.CompareContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitEqual(MxParser.EqualContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitAnd(MxParser.AndContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitXor(MxParser.XorContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitOr(MxParser.OrContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitLogicAnd(MxParser.LogicAndContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitLogicOr(MxParser.LogicOrContext ctx) {
        ExprNode lhs = (ExprNode) map.get(ctx.expression(0));
        ExprNode rhs = (ExprNode) map.get(ctx.expression(1));
        map.put(ctx, new BinaryExprNode(ctx.op.getText(), lhs, rhs));
    }

    @Override
    public void exitAssign(MxParser.AssignContext ctx) {
        ExprNode expr = (ExprNode) map.get(ctx.expression());
        map.put(ctx, new AssignExprNode(ctx.Identifier().getText(), expr));
    }

    @Override
    public void exitConstantLiteral(MxParser.ConstantLiteralContext ctx) {
        map.put(ctx, map.get(ctx.constant()));
    }

    @Override
    public void exitBool(MxParser.BoolContext ctx) {
        boolean val = ctx.getText().equals("true");
        map.put(ctx, new BoolCstExprNode(val));
    }

    @Override
    public void exitNumber(MxParser.NumberContext ctx) {
        int val = Integer.parseInt(ctx.getText());
        map.put(ctx, new NumberCstExprNode(val));
    }

    @Override
    public void exitNull(MxParser.NullContext ctx) {
        map.put(ctx, new NullCstExprNode());
    }

    @Override
    public void exitBreak(MxParser.BreakContext ctx) {
        map.put(ctx, new BreakStmtNode());
    }

    @Override
    public void exitContinue(MxParser.ContinueContext ctx) {
        map.put(ctx, new ContinueStmtNode());
    }

    @Override
    public void exitReturn(MxParser.ReturnContext ctx) {
        map.put(ctx, new ReturnStmtNode((ExprNode) map.get(ctx.expression())));
    }
}
