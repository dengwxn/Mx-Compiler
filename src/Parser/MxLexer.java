// Generated from /home/rd/Documents/Code/Mx-Compiler/src/Mx.g4 by ANTLR 4.7.2
package Parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MUL=1, DIV=2, ADD=3, SUB=4, MOD=5, LESS=6, LESSEQUAL=7, GREATER=8, GREATEREQUAL=9, 
		EQUAL=10, NOTEQUAL=11, LOGICAND=12, LOGICOR=13, LOGICNOT=14, AND=15, OR=16, 
		NOT=17, XOR=18, LEFTSHIFT=19, RIGHTSHIFT=20, ASSIGN=21, UNARYADD=22, UNARYSUB=23, 
		SEMICOLON=24, COMMA=25, DOT=26, LEFTPAREN=27, RIGHTPAREN=28, LEFTBRACKET=29, 
		RIGHTBRACKET=30, LEFTBRACE=31, RIGHTBRACE=32, WHITESPACE=33, LINECOMMENT=34, 
		BOOL=35, INT=36, STRING=37, NULL=38, VOID=39, TRUE=40, FALSE=41, IF=42, 
		ELSE=43, FOR=44, WHILE=45, BREAK=46, CONTINUE=47, RETURN=48, NEW=49, CLASS=50, 
		THIS=51, Number=52, Identifier=53;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"MUL", "DIV", "ADD", "SUB", "MOD", "LESS", "LESSEQUAL", "GREATER", "GREATEREQUAL", 
			"EQUAL", "NOTEQUAL", "LOGICAND", "LOGICOR", "LOGICNOT", "AND", "OR", 
			"NOT", "XOR", "LEFTSHIFT", "RIGHTSHIFT", "ASSIGN", "UNARYADD", "UNARYSUB", 
			"SEMICOLON", "COMMA", "DOT", "LEFTPAREN", "RIGHTPAREN", "LEFTBRACKET", 
			"RIGHTBRACKET", "LEFTBRACE", "RIGHTBRACE", "WHITESPACE", "LINECOMMENT", 
			"BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", 
			"FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", 
			"Number", "Identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "'/'", "'+'", "'-'", "'%'", "'<'", "'<='", "'>'", "'>='", 
			"'=='", "'!='", "'&&'", "'||'", "'!'", "'&'", "'|'", "'~'", "'^'", "'<<'", 
			"'>>'", "'='", "'++'", "'--'", "';'", "','", "'.'", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", null, null, "'bool'", "'int'", "'string'", "'null'", 
			"'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MUL", "DIV", "ADD", "SUB", "MOD", "LESS", "LESSEQUAL", "GREATER", 
			"GREATEREQUAL", "EQUAL", "NOTEQUAL", "LOGICAND", "LOGICOR", "LOGICNOT", 
			"AND", "OR", "NOT", "XOR", "LEFTSHIFT", "RIGHTSHIFT", "ASSIGN", "UNARYADD", 
			"UNARYSUB", "SEMICOLON", "COMMA", "DOT", "LEFTPAREN", "RIGHTPAREN", "LEFTBRACKET", 
			"RIGHTBRACKET", "LEFTBRACE", "RIGHTBRACE", "WHITESPACE", "LINECOMMENT", 
			"BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", "FALSE", "IF", "ELSE", 
			"FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", 
			"Number", "Identifier"
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u0131\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\6\"\u00b9\n\"\r\"\16\"\u00ba\3\"\3\"\3#\3#\3#\3#\7#\u00c3"+
		"\n#\f#\16#\u00c6\13#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3"+
		"&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*"+
		"\3*\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/"+
		"\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64"+
		"\3\64\3\64\3\64\3\64\3\65\6\65\u0127\n\65\r\65\16\65\u0128\3\66\3\66\7"+
		"\66\u012d\n\66\f\66\16\66\u0130\13\66\2\2\67\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S"+
		"+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67\3\2\7\5\2\13\f\17\17\"\"\4\2"+
		"\f\f\17\17\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\2\u0134\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5o\3\2\2\2\7q\3\2\2"+
		"\2\ts\3\2\2\2\13u\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21|\3\2\2\2\23~\3\2\2"+
		"\2\25\u0081\3\2\2\2\27\u0084\3\2\2\2\31\u0087\3\2\2\2\33\u008a\3\2\2\2"+
		"\35\u008d\3\2\2\2\37\u008f\3\2\2\2!\u0091\3\2\2\2#\u0093\3\2\2\2%\u0095"+
		"\3\2\2\2\'\u0097\3\2\2\2)\u009a\3\2\2\2+\u009d\3\2\2\2-\u009f\3\2\2\2"+
		"/\u00a2\3\2\2\2\61\u00a5\3\2\2\2\63\u00a7\3\2\2\2\65\u00a9\3\2\2\2\67"+
		"\u00ab\3\2\2\29\u00ad\3\2\2\2;\u00af\3\2\2\2=\u00b1\3\2\2\2?\u00b3\3\2"+
		"\2\2A\u00b5\3\2\2\2C\u00b8\3\2\2\2E\u00be\3\2\2\2G\u00c9\3\2\2\2I\u00ce"+
		"\3\2\2\2K\u00d2\3\2\2\2M\u00d9\3\2\2\2O\u00de\3\2\2\2Q\u00e3\3\2\2\2S"+
		"\u00e8\3\2\2\2U\u00ee\3\2\2\2W\u00f1\3\2\2\2Y\u00f6\3\2\2\2[\u00fa\3\2"+
		"\2\2]\u0100\3\2\2\2_\u0106\3\2\2\2a\u010f\3\2\2\2c\u0116\3\2\2\2e\u011a"+
		"\3\2\2\2g\u0120\3\2\2\2i\u0126\3\2\2\2k\u012a\3\2\2\2mn\7,\2\2n\4\3\2"+
		"\2\2op\7\61\2\2p\6\3\2\2\2qr\7-\2\2r\b\3\2\2\2st\7/\2\2t\n\3\2\2\2uv\7"+
		"\'\2\2v\f\3\2\2\2wx\7>\2\2x\16\3\2\2\2yz\7>\2\2z{\7?\2\2{\20\3\2\2\2|"+
		"}\7@\2\2}\22\3\2\2\2~\177\7@\2\2\177\u0080\7?\2\2\u0080\24\3\2\2\2\u0081"+
		"\u0082\7?\2\2\u0082\u0083\7?\2\2\u0083\26\3\2\2\2\u0084\u0085\7#\2\2\u0085"+
		"\u0086\7?\2\2\u0086\30\3\2\2\2\u0087\u0088\7(\2\2\u0088\u0089\7(\2\2\u0089"+
		"\32\3\2\2\2\u008a\u008b\7~\2\2\u008b\u008c\7~\2\2\u008c\34\3\2\2\2\u008d"+
		"\u008e\7#\2\2\u008e\36\3\2\2\2\u008f\u0090\7(\2\2\u0090 \3\2\2\2\u0091"+
		"\u0092\7~\2\2\u0092\"\3\2\2\2\u0093\u0094\7\u0080\2\2\u0094$\3\2\2\2\u0095"+
		"\u0096\7`\2\2\u0096&\3\2\2\2\u0097\u0098\7>\2\2\u0098\u0099\7>\2\2\u0099"+
		"(\3\2\2\2\u009a\u009b\7@\2\2\u009b\u009c\7@\2\2\u009c*\3\2\2\2\u009d\u009e"+
		"\7?\2\2\u009e,\3\2\2\2\u009f\u00a0\7-\2\2\u00a0\u00a1\7-\2\2\u00a1.\3"+
		"\2\2\2\u00a2\u00a3\7/\2\2\u00a3\u00a4\7/\2\2\u00a4\60\3\2\2\2\u00a5\u00a6"+
		"\7=\2\2\u00a6\62\3\2\2\2\u00a7\u00a8\7.\2\2\u00a8\64\3\2\2\2\u00a9\u00aa"+
		"\7\60\2\2\u00aa\66\3\2\2\2\u00ab\u00ac\7*\2\2\u00ac8\3\2\2\2\u00ad\u00ae"+
		"\7+\2\2\u00ae:\3\2\2\2\u00af\u00b0\7]\2\2\u00b0<\3\2\2\2\u00b1\u00b2\7"+
		"_\2\2\u00b2>\3\2\2\2\u00b3\u00b4\7}\2\2\u00b4@\3\2\2\2\u00b5\u00b6\7\177"+
		"\2\2\u00b6B\3\2\2\2\u00b7\u00b9\t\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba"+
		"\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\b\"\2\2\u00bdD\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c0\7\61\2"+
		"\2\u00c0\u00c4\3\2\2\2\u00c1\u00c3\n\3\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6"+
		"\u00c4\3\2\2\2\u00c7\u00c8\b#\2\2\u00c8F\3\2\2\2\u00c9\u00ca\7d\2\2\u00ca"+
		"\u00cb\7q\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd\7n\2\2\u00cdH\3\2\2\2\u00ce"+
		"\u00cf\7k\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7v\2\2\u00d1J\3\2\2\2\u00d2"+
		"\u00d3\7u\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7k\2\2"+
		"\u00d6\u00d7\7p\2\2\u00d7\u00d8\7i\2\2\u00d8L\3\2\2\2\u00d9\u00da\7p\2"+
		"\2\u00da\u00db\7w\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7n\2\2\u00ddN\3\2"+
		"\2\2\u00de\u00df\7x\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2"+
		"\7f\2\2\u00e2P\3\2\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6"+
		"\7w\2\2\u00e6\u00e7\7g\2\2\u00e7R\3\2\2\2\u00e8\u00e9\7h\2\2\u00e9\u00ea"+
		"\7c\2\2\u00ea\u00eb\7n\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7g\2\2\u00ed"+
		"T\3\2\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0\7h\2\2\u00f0V\3\2\2\2\u00f1\u00f2"+
		"\7g\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f5\7g\2\2\u00f5"+
		"X\3\2\2\2\u00f6\u00f7\7h\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7t\2\2\u00f9"+
		"Z\3\2\2\2\u00fa\u00fb\7y\2\2\u00fb\u00fc\7j\2\2\u00fc\u00fd\7k\2\2\u00fd"+
		"\u00fe\7n\2\2\u00fe\u00ff\7g\2\2\u00ff\\\3\2\2\2\u0100\u0101\7d\2\2\u0101"+
		"\u0102\7t\2\2\u0102\u0103\7g\2\2\u0103\u0104\7c\2\2\u0104\u0105\7m\2\2"+
		"\u0105^\3\2\2\2\u0106\u0107\7e\2\2\u0107\u0108\7q\2\2\u0108\u0109\7p\2"+
		"\2\u0109\u010a\7v\2\2\u010a\u010b\7k\2\2\u010b\u010c\7p\2\2\u010c\u010d"+
		"\7w\2\2\u010d\u010e\7g\2\2\u010e`\3\2\2\2\u010f\u0110\7t\2\2\u0110\u0111"+
		"\7g\2\2\u0111\u0112\7v\2\2\u0112\u0113\7w\2\2\u0113\u0114\7t\2\2\u0114"+
		"\u0115\7p\2\2\u0115b\3\2\2\2\u0116\u0117\7p\2\2\u0117\u0118\7g\2\2\u0118"+
		"\u0119\7y\2\2\u0119d\3\2\2\2\u011a\u011b\7e\2\2\u011b\u011c\7n\2\2\u011c"+
		"\u011d\7c\2\2\u011d\u011e\7u\2\2\u011e\u011f\7u\2\2\u011ff\3\2\2\2\u0120"+
		"\u0121\7v\2\2\u0121\u0122\7j\2\2\u0122\u0123\7k\2\2\u0123\u0124\7u\2\2"+
		"\u0124h\3\2\2\2\u0125\u0127\t\4\2\2\u0126\u0125\3\2\2\2\u0127\u0128\3"+
		"\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129j\3\2\2\2\u012a\u012e"+
		"\t\5\2\2\u012b\u012d\t\6\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012fl\3\2\2\2\u0130\u012e\3\2\2\2"+
		"\7\2\u00ba\u00c4\u0128\u012e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}