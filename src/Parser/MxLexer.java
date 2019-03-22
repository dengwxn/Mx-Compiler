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
		StringLiteral=1, MUL=2, DIV=3, ADD=4, SUB=5, MOD=6, LESS=7, LESSEQUAL=8, 
		GREATER=9, GREATEREQUAL=10, EQUAL=11, NOTEQUAL=12, LOGICAND=13, LOGICOR=14, 
		LOGICNOT=15, AND=16, OR=17, NOT=18, XOR=19, LEFTSHIFT=20, RIGHTSHIFT=21, 
		ASSIGN=22, UNARYADD=23, UNARYSUB=24, SEMICOLON=25, COMMA=26, DOT=27, LEFTPAREN=28, 
		RIGHTPAREN=29, LEFTBRACKET=30, RIGHTBRACKET=31, LEFTBRACE=32, RIGHTBRACE=33, 
		WHITESPACE=34, LINECOMMENT=35, BOOL=36, INT=37, STRING=38, NULL=39, VOID=40, 
		TRUE=41, FALSE=42, IF=43, ELSE=44, FOR=45, WHILE=46, BREAK=47, CONTINUE=48, 
		RETURN=49, NEW=50, CLASS=51, THIS=52, Number=53, Identifier=54;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"StringLiteral", "MUL", "DIV", "ADD", "SUB", "MOD", "LESS", "LESSEQUAL", 
			"GREATER", "GREATEREQUAL", "EQUAL", "NOTEQUAL", "LOGICAND", "LOGICOR", 
			"LOGICNOT", "AND", "OR", "NOT", "XOR", "LEFTSHIFT", "RIGHTSHIFT", "ASSIGN", 
			"UNARYADD", "UNARYSUB", "SEMICOLON", "COMMA", "DOT", "LEFTPAREN", "RIGHTPAREN", 
			"LEFTBRACKET", "RIGHTBRACKET", "LEFTBRACE", "RIGHTBRACE", "WHITESPACE", 
			"LINECOMMENT", "BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", "FALSE", 
			"IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", 
			"THIS", "Number", "Identifier"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\28\u013e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\3\2\7\2t\n\2\f\2\16\2w\13"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3"+
		"#\6#\u00c6\n#\r#\16#\u00c7\3#\3#\3$\3$\3$\3$\7$\u00d0\n$\f$\16$\u00d3"+
		"\13$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3"+
		"(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3"+
		"-\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3"+
		"\65\3\65\3\65\3\65\3\66\6\66\u0134\n\66\r\66\16\66\u0135\3\67\3\67\7\67"+
		"\u013a\n\67\f\67\16\67\u013d\13\67\3u\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8\3\2\b\b\2$$^^ddppttvv\5\2\13\f"+
		"\17\17\"\"\4\2\f\f\17\17\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\2\u0143\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o"+
		"\3\2\2\2\5z\3\2\2\2\7|\3\2\2\2\t~\3\2\2\2\13\u0080\3\2\2\2\r\u0082\3\2"+
		"\2\2\17\u0084\3\2\2\2\21\u0086\3\2\2\2\23\u0089\3\2\2\2\25\u008b\3\2\2"+
		"\2\27\u008e\3\2\2\2\31\u0091\3\2\2\2\33\u0094\3\2\2\2\35\u0097\3\2\2\2"+
		"\37\u009a\3\2\2\2!\u009c\3\2\2\2#\u009e\3\2\2\2%\u00a0\3\2\2\2\'\u00a2"+
		"\3\2\2\2)\u00a4\3\2\2\2+\u00a7\3\2\2\2-\u00aa\3\2\2\2/\u00ac\3\2\2\2\61"+
		"\u00af\3\2\2\2\63\u00b2\3\2\2\2\65\u00b4\3\2\2\2\67\u00b6\3\2\2\29\u00b8"+
		"\3\2\2\2;\u00ba\3\2\2\2=\u00bc\3\2\2\2?\u00be\3\2\2\2A\u00c0\3\2\2\2C"+
		"\u00c2\3\2\2\2E\u00c5\3\2\2\2G\u00cb\3\2\2\2I\u00d6\3\2\2\2K\u00db\3\2"+
		"\2\2M\u00df\3\2\2\2O\u00e6\3\2\2\2Q\u00eb\3\2\2\2S\u00f0\3\2\2\2U\u00f5"+
		"\3\2\2\2W\u00fb\3\2\2\2Y\u00fe\3\2\2\2[\u0103\3\2\2\2]\u0107\3\2\2\2_"+
		"\u010d\3\2\2\2a\u0113\3\2\2\2c\u011c\3\2\2\2e\u0123\3\2\2\2g\u0127\3\2"+
		"\2\2i\u012d\3\2\2\2k\u0133\3\2\2\2m\u0137\3\2\2\2ou\7$\2\2pq\7^\2\2qt"+
		"\t\2\2\2rt\13\2\2\2sp\3\2\2\2sr\3\2\2\2tw\3\2\2\2uv\3\2\2\2us\3\2\2\2"+
		"vx\3\2\2\2wu\3\2\2\2xy\7$\2\2y\4\3\2\2\2z{\7,\2\2{\6\3\2\2\2|}\7\61\2"+
		"\2}\b\3\2\2\2~\177\7-\2\2\177\n\3\2\2\2\u0080\u0081\7/\2\2\u0081\f\3\2"+
		"\2\2\u0082\u0083\7\'\2\2\u0083\16\3\2\2\2\u0084\u0085\7>\2\2\u0085\20"+
		"\3\2\2\2\u0086\u0087\7>\2\2\u0087\u0088\7?\2\2\u0088\22\3\2\2\2\u0089"+
		"\u008a\7@\2\2\u008a\24\3\2\2\2\u008b\u008c\7@\2\2\u008c\u008d\7?\2\2\u008d"+
		"\26\3\2\2\2\u008e\u008f\7?\2\2\u008f\u0090\7?\2\2\u0090\30\3\2\2\2\u0091"+
		"\u0092\7#\2\2\u0092\u0093\7?\2\2\u0093\32\3\2\2\2\u0094\u0095\7(\2\2\u0095"+
		"\u0096\7(\2\2\u0096\34\3\2\2\2\u0097\u0098\7~\2\2\u0098\u0099\7~\2\2\u0099"+
		"\36\3\2\2\2\u009a\u009b\7#\2\2\u009b \3\2\2\2\u009c\u009d\7(\2\2\u009d"+
		"\"\3\2\2\2\u009e\u009f\7~\2\2\u009f$\3\2\2\2\u00a0\u00a1\7\u0080\2\2\u00a1"+
		"&\3\2\2\2\u00a2\u00a3\7`\2\2\u00a3(\3\2\2\2\u00a4\u00a5\7>\2\2\u00a5\u00a6"+
		"\7>\2\2\u00a6*\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8\u00a9\7@\2\2\u00a9,\3"+
		"\2\2\2\u00aa\u00ab\7?\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7-\2\2\u00ad\u00ae"+
		"\7-\2\2\u00ae\60\3\2\2\2\u00af\u00b0\7/\2\2\u00b0\u00b1\7/\2\2\u00b1\62"+
		"\3\2\2\2\u00b2\u00b3\7=\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7.\2\2\u00b5"+
		"\66\3\2\2\2\u00b6\u00b7\7\60\2\2\u00b78\3\2\2\2\u00b8\u00b9\7*\2\2\u00b9"+
		":\3\2\2\2\u00ba\u00bb\7+\2\2\u00bb<\3\2\2\2\u00bc\u00bd\7]\2\2\u00bd>"+
		"\3\2\2\2\u00be\u00bf\7_\2\2\u00bf@\3\2\2\2\u00c0\u00c1\7}\2\2\u00c1B\3"+
		"\2\2\2\u00c2\u00c3\7\177\2\2\u00c3D\3\2\2\2\u00c4\u00c6\t\3\2\2\u00c5"+
		"\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\b#\2\2\u00caF\3\2\2\2\u00cb\u00cc"+
		"\7\61\2\2\u00cc\u00cd\7\61\2\2\u00cd\u00d1\3\2\2\2\u00ce\u00d0\n\4\2\2"+
		"\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d5\b$\2\2\u00d5"+
		"H\3\2\2\2\u00d6\u00d7\7d\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9\7q\2\2\u00d9"+
		"\u00da\7n\2\2\u00daJ\3\2\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7p\2\2\u00dd"+
		"\u00de\7v\2\2\u00deL\3\2\2\2\u00df\u00e0\7u\2\2\u00e0\u00e1\7v\2\2\u00e1"+
		"\u00e2\7t\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7i\2\2"+
		"\u00e5N\3\2\2\2\u00e6\u00e7\7p\2\2\u00e7\u00e8\7w\2\2\u00e8\u00e9\7n\2"+
		"\2\u00e9\u00ea\7n\2\2\u00eaP\3\2\2\2\u00eb\u00ec\7x\2\2\u00ec\u00ed\7"+
		"q\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7f\2\2\u00efR\3\2\2\2\u00f0\u00f1"+
		"\7v\2\2\u00f1\u00f2\7t\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7g\2\2\u00f4"+
		"T\3\2\2\2\u00f5\u00f6\7h\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8"+
		"\u00f9\7u\2\2\u00f9\u00fa\7g\2\2\u00faV\3\2\2\2\u00fb\u00fc\7k\2\2\u00fc"+
		"\u00fd\7h\2\2\u00fdX\3\2\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7n\2\2\u0100"+
		"\u0101\7u\2\2\u0101\u0102\7g\2\2\u0102Z\3\2\2\2\u0103\u0104\7h\2\2\u0104"+
		"\u0105\7q\2\2\u0105\u0106\7t\2\2\u0106\\\3\2\2\2\u0107\u0108\7y\2\2\u0108"+
		"\u0109\7j\2\2\u0109\u010a\7k\2\2\u010a\u010b\7n\2\2\u010b\u010c\7g\2\2"+
		"\u010c^\3\2\2\2\u010d\u010e\7d\2\2\u010e\u010f\7t\2\2\u010f\u0110\7g\2"+
		"\2\u0110\u0111\7c\2\2\u0111\u0112\7m\2\2\u0112`\3\2\2\2\u0113\u0114\7"+
		"e\2\2\u0114\u0115\7q\2\2\u0115\u0116\7p\2\2\u0116\u0117\7v\2\2\u0117\u0118"+
		"\7k\2\2\u0118\u0119\7p\2\2\u0119\u011a\7w\2\2\u011a\u011b\7g\2\2\u011b"+
		"b\3\2\2\2\u011c\u011d\7t\2\2\u011d\u011e\7g\2\2\u011e\u011f\7v\2\2\u011f"+
		"\u0120\7w\2\2\u0120\u0121\7t\2\2\u0121\u0122\7p\2\2\u0122d\3\2\2\2\u0123"+
		"\u0124\7p\2\2\u0124\u0125\7g\2\2\u0125\u0126\7y\2\2\u0126f\3\2\2\2\u0127"+
		"\u0128\7e\2\2\u0128\u0129\7n\2\2\u0129\u012a\7c\2\2\u012a\u012b\7u\2\2"+
		"\u012b\u012c\7u\2\2\u012ch\3\2\2\2\u012d\u012e\7v\2\2\u012e\u012f\7j\2"+
		"\2\u012f\u0130\7k\2\2\u0130\u0131\7u\2\2\u0131j\3\2\2\2\u0132\u0134\t"+
		"\5\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136l\3\2\2\2\u0137\u013b\t\6\2\2\u0138\u013a\t\7\2\2"+
		"\u0139\u0138\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013cn\3\2\2\2\u013d\u013b\3\2\2\2\t\2su\u00c7\u00d1\u0135\u013b"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}