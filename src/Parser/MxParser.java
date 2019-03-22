// Generated from /home/rd/Documents/Code/Mx-Compiler/src/Mx.g4 by ANTLR 4.7.2
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		StringLiteral=1, MUL=2, DIV=3, ADD=4, SUB=5, MOD=6, LESS=7, LESSEQUAL=8, 
		GREATER=9, GREATEREQUAL=10, EQUAL=11, NOTEQUAL=12, LOGICAND=13, LOGICOR=14, 
		LOGICNOT=15, AND=16, OR=17, NOT=18, XOR=19, LEFTSHIFT=20, RIGHTSHIFT=21, 
		ASSIGN=22, UNARYADD=23, UNARYSUB=24, SEMICOLON=25, COMMA=26, DOT=27, LEFTPAREN=28, 
		RIGHTPAREN=29, LEFTBRACKET=30, RIGHTBRACKET=31, LEFTBRACE=32, RIGHTBRACE=33, 
		WHITESPACE=34, LINECOMMENT=35, BOOL=36, INT=37, STRING=38, NULL=39, VOID=40, 
		TRUE=41, FALSE=42, IF=43, ELSE=44, FOR=45, WHILE=46, BREAK=47, CONTINUE=48, 
		RETURN=49, NEW=50, CLASS=51, THIS=52, Number=53, Identifier=54;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_functionDeclaration = 2, 
		RULE_variableDeclaration = 3, RULE_classDeclaration = 4, RULE_blockStatement = 5, 
		RULE_statement = 6, RULE_expressionStatement = 7, RULE_expression = 8, 
		RULE_ifStatement = 9, RULE_loopStatement = 10, RULE_branchStatement = 11, 
		RULE_dataType = 12, RULE_constant = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declaration", "functionDeclaration", "variableDeclaration", 
			"classDeclaration", "blockStatement", "statement", "expressionStatement", 
			"expression", "ifStatement", "loopStatement", "branchStatement", "dataType", 
			"constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'*'", "'/'", "'+'", "'-'", "'%'", "'<'", "'<='", "'>'", 
			"'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'&'", "'|'", "'~'", "'^'", 
			"'<<'", "'>>'", "'='", "'++'", "'--'", "';'", "','", "'.'", "'('", "')'", 
			"'['", "']'", "'{'", "'}'", null, null, "'bool'", "'int'", "'string'", 
			"'null'", "'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "StringLiteral", "MUL", "DIV", "ADD", "SUB", "MOD", "LESS", "LESSEQUAL", 
			"GREATER", "GREATEREQUAL", "EQUAL", "NOTEQUAL", "LOGICAND", "LOGICOR", 
			"LOGICNOT", "AND", "OR", "NOT", "XOR", "LEFTSHIFT", "RIGHTSHIFT", "ASSIGN", 
			"UNARYADD", "UNARYSUB", "SEMICOLON", "COMMA", "DOT", "LEFTPAREN", "RIGHTPAREN", 
			"LEFTBRACKET", "RIGHTBRACKET", "LEFTBRACE", "RIGHTBRACE", "WHITESPACE", 
			"LINECOMMENT", "BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", "FALSE", 
			"IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", 
			"THIS", "Number", "Identifier"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << Identifier))) != 0)) {
				{
				{
				setState(28);
				declaration();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				variableDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				classDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public List<DataTypeContext> dataType() {
			return getRuleContexts(DataTypeContext.class);
		}
		public DataTypeContext dataType(int i) {
			return getRuleContext(DataTypeContext.class,i);
		}
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxParser.COMMA, i);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionDeclaration(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			dataType(0);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(42);
				match(Identifier);
				}
			}

			setState(45);
			match(LEFTPAREN);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
				{
				setState(46);
				dataType(0);
				setState(47);
				match(Identifier);
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(48);
					match(COMMA);
					setState(49);
					dataType(0);
					setState(50);
					match(Identifier);
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(59);
			match(RIGHTPAREN);
			setState(60);
			blockStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public TerminalNode ASSIGN() { return getToken(MxParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			dataType(0);
			setState(63);
			match(Identifier);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(64);
				match(ASSIGN);
				setState(65);
				expression(0);
				}
			}

			setState(68);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MxParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LEFTBRACE() { return getToken(MxParser.LEFTBRACE, 0); }
		public TerminalNode RIGHTBRACE() { return getToken(MxParser.RIGHTBRACE, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(CLASS);
			setState(71);
			match(Identifier);
			setState(72);
			match(LEFTBRACE);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
				{
				setState(75);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(73);
					variableDeclaration();
					}
					break;
				case 2:
					{
					setState(74);
					functionDeclaration();
					}
					break;
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			match(RIGHTBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public TerminalNode LEFTBRACE() { return getToken(MxParser.LEFTBRACE, 0); }
		public TerminalNode RIGHTBRACE() { return getToken(MxParser.RIGHTBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_blockStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(LEFTBRACE);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << SEMICOLON) | (1L << LEFTPAREN) | (1L << LEFTBRACE) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				{
				setState(83);
				statement();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(RIGHTBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public BranchStatementContext branchStatement() {
			return getRuleContext(BranchStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				blockStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				variableDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(95);
				loopStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(96);
				branchStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(99);
				expression(0);
				}
			}

			setState(102);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ShiftContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LEFTSHIFT() { return getToken(MxParser.LEFTSHIFT, 0); }
		public TerminalNode RIGHTSHIFT() { return getToken(MxParser.RIGHTSHIFT, 0); }
		public ShiftContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterShift(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitShift(this);
		}
	}
	public static class LogicOrContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LOGICOR() { return getToken(MxParser.LOGICOR, 0); }
		public LogicOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLogicOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLogicOr(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(MxParser.OR, 0); }
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitOr(this);
		}
	}
	public static class ConstantLiteralContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConstantLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConstantLiteral(this);
		}
	}
	public static class AddSubContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(MxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxParser.SUB, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAddSub(this);
		}
	}
	public static class SubExprContext extends ExpressionContext {
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public SubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSubExpr(this);
		}
	}
	public static class PrefixContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD() { return getToken(MxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(MxParser.NOT, 0); }
		public TerminalNode LOGICNOT() { return getToken(MxParser.LOGICNOT, 0); }
		public TerminalNode UNARYADD() { return getToken(MxParser.UNARYADD, 0); }
		public TerminalNode UNARYSUB() { return getToken(MxParser.UNARYSUB, 0); }
		public PrefixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrefix(this);
		}
	}
	public static class ArrayContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> LEFTBRACKET() { return getTokens(MxParser.LEFTBRACKET); }
		public TerminalNode LEFTBRACKET(int i) {
			return getToken(MxParser.LEFTBRACKET, i);
		}
		public List<TerminalNode> RIGHTBRACKET() { return getTokens(MxParser.RIGHTBRACKET); }
		public TerminalNode RIGHTBRACKET(int i) {
			return getToken(MxParser.RIGHTBRACKET, i);
		}
		public ArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArray(this);
		}
	}
	public static class NewArrayContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<TerminalNode> LEFTBRACKET() { return getTokens(MxParser.LEFTBRACKET); }
		public TerminalNode LEFTBRACKET(int i) {
			return getToken(MxParser.LEFTBRACKET, i);
		}
		public List<TerminalNode> RIGHTBRACKET() { return getTokens(MxParser.RIGHTBRACKET); }
		public TerminalNode RIGHTBRACKET(int i) {
			return getToken(MxParser.RIGHTBRACKET, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArray(this);
		}
	}
	public static class MulDivModContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MxParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MxParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MxParser.MOD, 0); }
		public MulDivModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMulDivMod(this);
		}
	}
	public static class IdentifierContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public IdentifierContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIdentifier(this);
		}
	}
	public static class SuffixContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode UNARYADD() { return getToken(MxParser.UNARYADD, 0); }
		public TerminalNode UNARYSUB() { return getToken(MxParser.UNARYSUB, 0); }
		public SuffixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuffix(this);
		}
	}
	public static class EqualContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(MxParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(MxParser.NOTEQUAL, 0); }
		public EqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEqual(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(MxParser.AND, 0); }
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAnd(this);
		}
	}
	public static class CompareContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESS() { return getToken(MxParser.LESS, 0); }
		public TerminalNode LESSEQUAL() { return getToken(MxParser.LESSEQUAL, 0); }
		public TerminalNode GREATER() { return getToken(MxParser.GREATER, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(MxParser.GREATEREQUAL, 0); }
		public CompareContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCompare(this);
		}
	}
	public static class XorContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode XOR() { return getToken(MxParser.XOR, 0); }
		public XorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitXor(this);
		}
	}
	public static class AssignContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(MxParser.ASSIGN, 0); }
		public AssignContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssign(this);
		}
	}
	public static class FunctionCallContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxParser.COMMA, i);
		}
		public FunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionCall(this);
		}
	}
	public static class LogicAndContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LOGICAND() { return getToken(MxParser.LOGICAND, 0); }
		public LogicAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLogicAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLogicAnd(this);
		}
	}
	public static class MemberContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MxParser.DOT, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public MemberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMember(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StringLiteral:
			case NULL:
			case TRUE:
			case FALSE:
			case Number:
				{
				_localctx = new ConstantLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(105);
				constant();
				}
				break;
			case NEW:
				{
				_localctx = new NewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(NEW);
				setState(107);
				dataType(0);
				setState(113); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(108);
						match(LEFTBRACKET);
						setState(110);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
							{
							setState(109);
							expression(0);
							}
						}

						setState(112);
						match(RIGHTBRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(115); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case Identifier:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(Identifier);
				}
				break;
			case LEFTPAREN:
				{
				_localctx = new SubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(LEFTPAREN);
				setState(119);
				expression(0);
				setState(120);
				match(RIGHTPAREN);
				}
				break;
			case ADD:
			case SUB:
			case LOGICNOT:
			case NOT:
			case UNARYADD:
			case UNARYSUB:
				{
				_localctx = new PrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				((PrefixContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB))) != 0)) ) {
					((PrefixContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(123);
				expression(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(186);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(126);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(127);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(128);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(129);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(130);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(131);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new ShiftContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(132);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(133);
						((ShiftContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LEFTSHIFT || _la==RIGHTSHIFT) ) {
							((ShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(134);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new CompareContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(136);
						((CompareContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << LESSEQUAL) | (1L << GREATER) | (1L << GREATEREQUAL))) != 0)) ) {
							((CompareContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(137);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new EqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(138);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(139);
						((EqualContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((EqualContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(140);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(142);
						((AndContext)_localctx).op = match(AND);
						setState(143);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new XorContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(145);
						((XorContext)_localctx).op = match(XOR);
						setState(146);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(147);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(148);
						((OrContext)_localctx).op = match(OR);
						setState(149);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new LogicAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(150);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(151);
						((LogicAndContext)_localctx).op = match(LOGICAND);
						setState(152);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new LogicOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(153);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(154);
						((LogicOrContext)_localctx).op = match(LOGICOR);
						setState(155);
						expression(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(156);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(157);
						((AssignContext)_localctx).op = match(ASSIGN);
						setState(158);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new ArrayContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(159);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(164); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(160);
								match(LEFTBRACKET);
								setState(161);
								expression(0);
								setState(162);
								match(RIGHTBRACKET);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(166); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 13:
						{
						_localctx = new FunctionCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(169);
						match(LEFTPAREN);
						setState(178);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
							{
							setState(170);
							expression(0);
							setState(175);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(171);
								match(COMMA);
								setState(172);
								expression(0);
								}
								}
								setState(177);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(180);
						match(RIGHTPAREN);
						}
						break;
					case 14:
						{
						_localctx = new MemberContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(182);
						match(DOT);
						setState(183);
						match(Identifier);
						}
						break;
					case 15:
						{
						_localctx = new SuffixContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(185);
						((SuffixContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==UNARYADD || _la==UNARYSUB) ) {
							((SuffixContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MxParser.IF, 0); }
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(IF);
			setState(192);
			match(LEFTPAREN);
			setState(193);
			expression(0);
			setState(194);
			match(RIGHTPAREN);
			setState(195);
			statement();
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(196);
				match(ELSE);
				setState(197);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	 
		public LoopStatementContext() { }
		public void copyFrom(LoopStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForContext extends LoopStatementContext {
		public TerminalNode FOR() { return getToken(MxParser.FOR, 0); }
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(MxParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MxParser.SEMICOLON, i);
		}
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFor(this);
		}
	}
	public static class WhileContext extends LoopStatementContext {
		public TerminalNode WHILE() { return getToken(MxParser.WHILE, 0); }
		public TerminalNode LEFTPAREN() { return getToken(MxParser.LEFTPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHTPAREN() { return getToken(MxParser.RIGHTPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhile(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_loopStatement);
		int _la;
		try {
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				_localctx = new ForContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(FOR);
				setState(201);
				match(LEFTPAREN);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
					{
					setState(202);
					expression(0);
					}
				}

				setState(205);
				match(SEMICOLON);
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
					{
					setState(206);
					expression(0);
					}
				}

				setState(209);
				match(SEMICOLON);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
					{
					setState(210);
					expression(0);
					}
				}

				setState(213);
				match(RIGHTPAREN);
				setState(214);
				statement();
				}
				break;
			case WHILE:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				match(WHILE);
				setState(216);
				match(LEFTPAREN);
				setState(217);
				expression(0);
				setState(218);
				match(RIGHTPAREN);
				setState(219);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchStatementContext extends ParserRuleContext {
		public BranchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branchStatement; }
	 
		public BranchStatementContext() { }
		public void copyFrom(BranchStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnContext extends BranchStatementContext {
		public TerminalNode RETURN() { return getToken(MxParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(BranchStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturn(this);
		}
	}
	public static class BreakContext extends BranchStatementContext {
		public TerminalNode BREAK() { return getToken(MxParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public BreakContext(BranchStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreak(this);
		}
	}
	public static class ContinueContext extends BranchStatementContext {
		public TerminalNode CONTINUE() { return getToken(MxParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ContinueContext(BranchStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinue(this);
		}
	}

	public final BranchStatementContext branchStatement() throws RecognitionException {
		BranchStatementContext _localctx = new BranchStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_branchStatement);
		int _la;
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				match(RETURN);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << ADD) | (1L << SUB) | (1L << LOGICNOT) | (1L << NOT) | (1L << UNARYADD) | (1L << UNARYSUB) | (1L << LEFTPAREN) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << Number) | (1L << Identifier))) != 0)) {
					{
					setState(224);
					expression(0);
					}
				}

				setState(227);
				match(SEMICOLON);
				}
				break;
			case BREAK:
				_localctx = new BreakContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				match(BREAK);
				setState(229);
				match(SEMICOLON);
				}
				break;
			case CONTINUE:
				_localctx = new ContinueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(230);
				match(CONTINUE);
				setState(231);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VoidTypeContext extends DataTypeContext {
		public TerminalNode VOID() { return getToken(MxParser.VOID, 0); }
		public VoidTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVoidType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVoidType(this);
		}
	}
	public static class ArrayTypeContext extends DataTypeContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode LEFTBRACKET() { return getToken(MxParser.LEFTBRACKET, 0); }
		public TerminalNode RIGHTBRACKET() { return getToken(MxParser.RIGHTBRACKET, 0); }
		public ArrayTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayType(this);
		}
	}
	public static class BoolTypeContext extends DataTypeContext {
		public TerminalNode BOOL() { return getToken(MxParser.BOOL, 0); }
		public BoolTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBoolType(this);
		}
	}
	public static class StringTypeContext extends DataTypeContext {
		public TerminalNode STRING() { return getToken(MxParser.STRING, 0); }
		public StringTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStringType(this);
		}
	}
	public static class ClassTypeContext extends DataTypeContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ClassTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassType(this);
		}
	}
	public static class IntTypeContext extends DataTypeContext {
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public IntTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIntType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		return dataType(0);
	}

	private DataTypeContext dataType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DataTypeContext _localctx = new DataTypeContext(_ctx, _parentState);
		DataTypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_dataType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(235);
				match(INT);
				}
				break;
			case BOOL:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				match(BOOL);
				}
				break;
			case VOID:
				{
				_localctx = new VoidTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(237);
				match(VOID);
				}
				break;
			case Identifier:
				{
				_localctx = new ClassTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(238);
				match(Identifier);
				}
				break;
			case STRING:
				{
				_localctx = new StringTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(247);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeContext(new DataTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_dataType);
					setState(242);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(243);
					match(LEFTBRACKET);
					setState(244);
					match(RIGHTBRACKET);
					}
					} 
				}
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NullContext extends ConstantContext {
		public TerminalNode NULL() { return getToken(MxParser.NULL, 0); }
		public NullContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNull(this);
		}
	}
	public static class NumberContext extends ConstantContext {
		public TerminalNode Number() { return getToken(MxParser.Number, 0); }
		public NumberContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNumber(this);
		}
	}
	public static class BoolContext extends ConstantContext {
		public TerminalNode TRUE() { return getToken(MxParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MxParser.FALSE, 0); }
		public BoolContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBool(this);
		}
	}
	public static class StringContext extends ConstantContext {
		public TerminalNode StringLiteral() { return getToken(MxParser.StringLiteral, 0); }
		public StringContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitString(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constant);
		int _la;
		try {
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NULL:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				match(NULL);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BoolContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Number:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(252);
				match(Number);
				}
				}
				break;
			case StringLiteral:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(253);
				match(StringLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 12:
			return dataType_sempred((DataTypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 16);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 13);
		}
		return true;
	}
	private boolean dataType_sempred(DataTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u0103\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\5\3*\n\3\3\4\3\4\5\4.\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\67\n\4\f\4\16\4:\13\4\5\4<\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5"+
		"E\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3\6\3\6\3\7"+
		"\3\7\7\7W\n\7\f\7\16\7Z\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bd\n\b"+
		"\3\t\5\tg\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nq\n\n\3\n\6\nt\n\n\r"+
		"\n\16\nu\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\177\n\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00a7"+
		"\n\n\r\n\16\n\u00a8\3\n\3\n\3\n\3\n\3\n\7\n\u00b0\n\n\f\n\16\n\u00b3\13"+
		"\n\5\n\u00b5\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0"+
		"\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00c9\n\13\3\f\3\f\3\f\5"+
		"\f\u00ce\n\f\3\f\3\f\5\f\u00d2\n\f\3\f\3\f\5\f\u00d6\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00e0\n\f\3\r\3\r\5\r\u00e4\n\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00eb\n\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f3\n\16\3\16"+
		"\3\16\3\16\7\16\u00f8\n\16\f\16\16\16\u00fb\13\16\3\17\3\17\3\17\3\17"+
		"\5\17\u0101\n\17\3\17\2\4\22\32\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\2\n\6\2\6\7\21\21\24\24\31\32\4\2\4\5\b\b\3\2\6\7\3\2\26\27\3\2\t\f\3"+
		"\2\r\16\3\2\31\32\3\2+,\2\u012c\2!\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b@\3"+
		"\2\2\2\nH\3\2\2\2\fT\3\2\2\2\16c\3\2\2\2\20f\3\2\2\2\22~\3\2\2\2\24\u00c1"+
		"\3\2\2\2\26\u00df\3\2\2\2\30\u00ea\3\2\2\2\32\u00f2\3\2\2\2\34\u0100\3"+
		"\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3"+
		"\2\2\2#!\3\2\2\2$%\7\2\2\3%\3\3\2\2\2&*\5\6\4\2\'*\5\b\5\2(*\5\n\6\2)"+
		"&\3\2\2\2)\'\3\2\2\2)(\3\2\2\2*\5\3\2\2\2+-\5\32\16\2,.\78\2\2-,\3\2\2"+
		"\2-.\3\2\2\2./\3\2\2\2/;\7\36\2\2\60\61\5\32\16\2\618\78\2\2\62\63\7\34"+
		"\2\2\63\64\5\32\16\2\64\65\78\2\2\65\67\3\2\2\2\66\62\3\2\2\2\67:\3\2"+
		"\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3\2\2\2;\60\3\2\2\2;<\3\2\2\2<"+
		"=\3\2\2\2=>\7\37\2\2>?\5\f\7\2?\7\3\2\2\2@A\5\32\16\2AD\78\2\2BC\7\30"+
		"\2\2CE\5\22\n\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7\33\2\2G\t\3\2\2\2HI"+
		"\7\65\2\2IJ\78\2\2JO\7\"\2\2KN\5\b\5\2LN\5\6\4\2MK\3\2\2\2ML\3\2\2\2N"+
		"Q\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7#\2\2S\13\3\2\2\2"+
		"TX\7\"\2\2UW\5\16\b\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2"+
		"\2ZX\3\2\2\2[\\\7#\2\2\\\r\3\2\2\2]d\5\f\7\2^d\5\20\t\2_d\5\b\5\2`d\5"+
		"\24\13\2ad\5\26\f\2bd\5\30\r\2c]\3\2\2\2c^\3\2\2\2c_\3\2\2\2c`\3\2\2\2"+
		"ca\3\2\2\2cb\3\2\2\2d\17\3\2\2\2eg\5\22\n\2fe\3\2\2\2fg\3\2\2\2gh\3\2"+
		"\2\2hi\7\33\2\2i\21\3\2\2\2jk\b\n\1\2k\177\5\34\17\2lm\7\64\2\2ms\5\32"+
		"\16\2np\7 \2\2oq\5\22\n\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2rt\7!\2\2sn\3\2"+
		"\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v\177\3\2\2\2w\177\78\2\2xy\7\36\2\2"+
		"yz\5\22\n\2z{\7\37\2\2{\177\3\2\2\2|}\t\2\2\2}\177\5\22\n\16~j\3\2\2\2"+
		"~l\3\2\2\2~w\3\2\2\2~x\3\2\2\2~|\3\2\2\2\177\u00be\3\2\2\2\u0080\u0081"+
		"\f\r\2\2\u0081\u0082\t\3\2\2\u0082\u00bd\5\22\n\16\u0083\u0084\f\f\2\2"+
		"\u0084\u0085\t\4\2\2\u0085\u00bd\5\22\n\r\u0086\u0087\f\13\2\2\u0087\u0088"+
		"\t\5\2\2\u0088\u00bd\5\22\n\f\u0089\u008a\f\n\2\2\u008a\u008b\t\6\2\2"+
		"\u008b\u00bd\5\22\n\13\u008c\u008d\f\t\2\2\u008d\u008e\t\7\2\2\u008e\u00bd"+
		"\5\22\n\n\u008f\u0090\f\b\2\2\u0090\u0091\7\22\2\2\u0091\u00bd\5\22\n"+
		"\t\u0092\u0093\f\7\2\2\u0093\u0094\7\25\2\2\u0094\u00bd\5\22\n\b\u0095"+
		"\u0096\f\6\2\2\u0096\u0097\7\23\2\2\u0097\u00bd\5\22\n\7\u0098\u0099\f"+
		"\5\2\2\u0099\u009a\7\17\2\2\u009a\u00bd\5\22\n\6\u009b\u009c\f\4\2\2\u009c"+
		"\u009d\7\20\2\2\u009d\u00bd\5\22\n\5\u009e\u009f\f\3\2\2\u009f\u00a0\7"+
		"\30\2\2\u00a0\u00bd\5\22\n\4\u00a1\u00a6\f\24\2\2\u00a2\u00a3\7 \2\2\u00a3"+
		"\u00a4\5\22\n\2\u00a4\u00a5\7!\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a2\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00bd\3\2\2\2\u00aa\u00ab\f\22\2\2\u00ab\u00b4\7\36\2\2\u00ac\u00b1\5"+
		"\22\n\2\u00ad\u00ae\7\34\2\2\u00ae\u00b0\5\22\n\2\u00af\u00ad\3\2\2\2"+
		"\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b5"+
		"\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00ac\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00bd\7\37\2\2\u00b7\u00b8\f\21\2\2\u00b8\u00b9\7"+
		"\35\2\2\u00b9\u00bd\78\2\2\u00ba\u00bb\f\17\2\2\u00bb\u00bd\t\b\2\2\u00bc"+
		"\u0080\3\2\2\2\u00bc\u0083\3\2\2\2\u00bc\u0086\3\2\2\2\u00bc\u0089\3\2"+
		"\2\2\u00bc\u008c\3\2\2\2\u00bc\u008f\3\2\2\2\u00bc\u0092\3\2\2\2\u00bc"+
		"\u0095\3\2\2\2\u00bc\u0098\3\2\2\2\u00bc\u009b\3\2\2\2\u00bc\u009e\3\2"+
		"\2\2\u00bc\u00a1\3\2\2\2\u00bc\u00aa\3\2\2\2\u00bc\u00b7\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\23\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7-\2\2\u00c2\u00c3"+
		"\7\36\2\2\u00c3\u00c4\5\22\n\2\u00c4\u00c5\7\37\2\2\u00c5\u00c8\5\16\b"+
		"\2\u00c6\u00c7\7.\2\2\u00c7\u00c9\5\16\b\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9"+
		"\3\2\2\2\u00c9\25\3\2\2\2\u00ca\u00cb\7/\2\2\u00cb\u00cd\7\36\2\2\u00cc"+
		"\u00ce\5\22\n\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3"+
		"\2\2\2\u00cf\u00d1\7\33\2\2\u00d0\u00d2\5\22\n\2\u00d1\u00d0\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\7\33\2\2\u00d4\u00d6\5"+
		"\22\n\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d8\7\37\2\2\u00d8\u00e0\5\16\b\2\u00d9\u00da\7\60\2\2\u00da\u00db"+
		"\7\36\2\2\u00db\u00dc\5\22\n\2\u00dc\u00dd\7\37\2\2\u00dd\u00de\5\16\b"+
		"\2\u00de\u00e0\3\2\2\2\u00df\u00ca\3\2\2\2\u00df\u00d9\3\2\2\2\u00e0\27"+
		"\3\2\2\2\u00e1\u00e3\7\63\2\2\u00e2\u00e4\5\22\n\2\u00e3\u00e2\3\2\2\2"+
		"\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00eb\7\33\2\2\u00e6\u00e7"+
		"\7\61\2\2\u00e7\u00eb\7\33\2\2\u00e8\u00e9\7\62\2\2\u00e9\u00eb\7\33\2"+
		"\2\u00ea\u00e1\3\2\2\2\u00ea\u00e6\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\31"+
		"\3\2\2\2\u00ec\u00ed\b\16\1\2\u00ed\u00f3\7\'\2\2\u00ee\u00f3\7&\2\2\u00ef"+
		"\u00f3\7*\2\2\u00f0\u00f3\78\2\2\u00f1\u00f3\7(\2\2\u00f2\u00ec\3\2\2"+
		"\2\u00f2\u00ee\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f1"+
		"\3\2\2\2\u00f3\u00f9\3\2\2\2\u00f4\u00f5\f\5\2\2\u00f5\u00f6\7 \2\2\u00f6"+
		"\u00f8\7!\2\2\u00f7\u00f4\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2"+
		"\2\2\u00f9\u00fa\3\2\2\2\u00fa\33\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u0101"+
		"\7)\2\2\u00fd\u0101\t\t\2\2\u00fe\u0101\7\67\2\2\u00ff\u0101\7\3\2\2\u0100"+
		"\u00fc\3\2\2\2\u0100\u00fd\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2"+
		"\2\2\u0101\35\3\2\2\2\37!)-8;DMOXcfpu~\u00a8\u00b1\u00b4\u00bc\u00be\u00c8"+
		"\u00cd\u00d1\u00d5\u00df\u00e3\u00ea\u00f2\u00f9\u0100";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}