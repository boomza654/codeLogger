package api.antlr4;
// Generated from Minispec.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MinispecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, UpperCaseIdentifier=64, LowerCaseIdentifier=65, 
		DollarIdentifier=66, IntLiteral=67, StringLiteral=68, WhiteSpace=69, OneLineComment=70, 
		InlineComment=71;
	public static final int
		RULE_lowerCaseIdentifier = 0, RULE_upperCaseIdentifier = 1, RULE_identifier = 2, 
		RULE_anyIdentifier = 3, RULE_arg = 4, RULE_args = 5, RULE_argFormal = 6, 
		RULE_argFormals = 7, RULE_param = 8, RULE_params = 9, RULE_paramFormal = 10, 
		RULE_paramFormals = 11, RULE_type = 12, RULE_packageDef = 13, RULE_packageStmt = 14, 
		RULE_importDecl = 15, RULE_bsvImportDecl = 16, RULE_typeDecl = 17, RULE_typeDefSynonym = 18, 
		RULE_typeId = 19, RULE_typeDefEnum = 20, RULE_typeDefEnumElement = 21, 
		RULE_typeDefStruct = 22, RULE_structMember = 23, RULE_varDecl = 24, RULE_varInit = 25, 
		RULE_moduleDef = 26, RULE_moduleId = 27, RULE_moduleStmt = 28, RULE_submoduleDecl = 29, 
		RULE_inputDef = 30, RULE_methodDef = 31, RULE_ruleDef = 32, RULE_functionDef = 33, 
		RULE_functionId = 34, RULE_varAssign = 35, RULE_lvalue = 36, RULE_expression = 37, 
		RULE_caseExprItem = 38, RULE_binopExpr = 39, RULE_unopExpr = 40, RULE_exprPrimary = 41, 
		RULE_memberBinds = 42, RULE_memberBind = 43, RULE_beginEndBlock = 44, 
		RULE_regWrite = 45, RULE_stmt = 46, RULE_ifStmt = 47, RULE_caseStmt = 48, 
		RULE_caseStmtItem = 49, RULE_caseStmtDefaultItem = 50, RULE_forStmt = 51;
	private static String[] makeRuleNames() {
		return new String[] {
			"lowerCaseIdentifier", "upperCaseIdentifier", "identifier", "anyIdentifier", 
			"arg", "args", "argFormal", "argFormals", "param", "params", "paramFormal", 
			"paramFormals", "type", "packageDef", "packageStmt", "importDecl", "bsvImportDecl", 
			"typeDecl", "typeDefSynonym", "typeId", "typeDefEnum", "typeDefEnumElement", 
			"typeDefStruct", "structMember", "varDecl", "varInit", "moduleDef", "moduleId", 
			"moduleStmt", "submoduleDecl", "inputDef", "methodDef", "ruleDef", "functionDef", 
			"functionId", "varAssign", "lvalue", "expression", "caseExprItem", "binopExpr", 
			"unopExpr", "exprPrimary", "memberBinds", "memberBind", "beginEndBlock", 
			"regWrite", "stmt", "ifStmt", "caseStmt", "caseStmtItem", "caseStmtDefaultItem", 
			"forStmt"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'#'", "'type'", "'import'", "';'", "'bsvimport'", 
			"'typedef'", "'enum'", "'{'", "'}'", "'='", "'struct'", "'let'", "'module'", 
			"'endmodule'", "'input'", "'default'", "'method'", "'endmethod'", "'rule'", 
			"'endrule'", "'function'", "'endfunction'", "'.'", "'['", "']'", "':'", 
			"'?'", "'case'", "'endcase'", "'**'", "'*'", "'/'", "'%'", "'+'", "'-'", 
			"'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&'", 
			"'^'", "'^~'", "'~^'", "'|'", "'&&'", "'||'", "'!'", "'~'", "'~&'", "'~|'", 
			"'return'", "'begin'", "'end'", "'if'", "'else'", "'for'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "UpperCaseIdentifier", "LowerCaseIdentifier", 
			"DollarIdentifier", "IntLiteral", "StringLiteral", "WhiteSpace", "OneLineComment", 
			"InlineComment"
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
	public String getGrammarFileName() { return "Minispec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MinispecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class LowerCaseIdentifierContext extends ParserRuleContext {
		public TerminalNode LowerCaseIdentifier() { return getToken(MinispecParser.LowerCaseIdentifier, 0); }
		public LowerCaseIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lowerCaseIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterLowerCaseIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitLowerCaseIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitLowerCaseIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LowerCaseIdentifierContext lowerCaseIdentifier() throws RecognitionException {
		LowerCaseIdentifierContext _localctx = new LowerCaseIdentifierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_lowerCaseIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(LowerCaseIdentifier);
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

	public static class UpperCaseIdentifierContext extends ParserRuleContext {
		public TerminalNode UpperCaseIdentifier() { return getToken(MinispecParser.UpperCaseIdentifier, 0); }
		public UpperCaseIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upperCaseIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterUpperCaseIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitUpperCaseIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitUpperCaseIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpperCaseIdentifierContext upperCaseIdentifier() throws RecognitionException {
		UpperCaseIdentifierContext _localctx = new UpperCaseIdentifierContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_upperCaseIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(UpperCaseIdentifier);
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

	public static class IdentifierContext extends ParserRuleContext {
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_identifier);
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LowerCaseIdentifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				lowerCaseIdentifier();
				}
				break;
			case UpperCaseIdentifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				upperCaseIdentifier();
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

	public static class AnyIdentifierContext extends ParserRuleContext {
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public TerminalNode DollarIdentifier() { return getToken(MinispecParser.DollarIdentifier, 0); }
		public AnyIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterAnyIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitAnyIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitAnyIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnyIdentifierContext anyIdentifier() throws RecognitionException {
		AnyIdentifierContext _localctx = new AnyIdentifierContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_anyIdentifier);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LowerCaseIdentifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				lowerCaseIdentifier();
				}
				break;
			case UpperCaseIdentifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				upperCaseIdentifier();
				}
				break;
			case DollarIdentifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(DollarIdentifier);
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

	public static class ArgContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			expression(0);
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

	public static class ArgsContext extends ParserRuleContext {
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__0);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(120);
				arg();
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(121);
					match(T__1);
					setState(122);
					arg();
					}
					}
					setState(127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(130);
			match(T__2);
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

	public static class ArgFormalContext extends ParserRuleContext {
		public LowerCaseIdentifierContext argName;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ArgFormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argFormal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterArgFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitArgFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitArgFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgFormalContext argFormal() throws RecognitionException {
		ArgFormalContext _localctx = new ArgFormalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argFormal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			type();
			setState(133);
			((ArgFormalContext)_localctx).argName = lowerCaseIdentifier();
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

	public static class ArgFormalsContext extends ParserRuleContext {
		public List<ArgFormalContext> argFormal() {
			return getRuleContexts(ArgFormalContext.class);
		}
		public ArgFormalContext argFormal(int i) {
			return getRuleContext(ArgFormalContext.class,i);
		}
		public ArgFormalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argFormals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterArgFormals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitArgFormals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitArgFormals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgFormalsContext argFormals() throws RecognitionException {
		ArgFormalsContext _localctx = new ArgFormalsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_argFormals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(T__0);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UpperCaseIdentifier) {
				{
				setState(136);
				argFormal();
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(137);
					match(T__1);
					setState(138);
					argFormal();
					}
					}
					setState(143);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(146);
			match(T__2);
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

	public static class ParamContext extends ParserRuleContext {
		public ExpressionContext intParam;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_param);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				((ParamContext)_localctx).intParam = expression(0);
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

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__3);
			setState(153);
			match(T__0);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(154);
				param();
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(155);
					match(T__1);
					setState(156);
					param();
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(164);
			match(T__2);
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

	public static class ParamFormalContext extends ParserRuleContext {
		public LowerCaseIdentifierContext intName;
		public UpperCaseIdentifierContext typeName;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public ParamFormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramFormal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterParamFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitParamFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitParamFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamFormalContext paramFormal() throws RecognitionException {
		ParamFormalContext _localctx = new ParamFormalContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paramFormal);
		try {
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				type();
				setState(167);
				((ParamFormalContext)_localctx).intName = lowerCaseIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(T__4);
				setState(170);
				((ParamFormalContext)_localctx).typeName = upperCaseIdentifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				param();
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

	public static class ParamFormalsContext extends ParserRuleContext {
		public List<ParamFormalContext> paramFormal() {
			return getRuleContexts(ParamFormalContext.class);
		}
		public ParamFormalContext paramFormal(int i) {
			return getRuleContext(ParamFormalContext.class,i);
		}
		public ParamFormalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramFormals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterParamFormals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitParamFormals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitParamFormals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamFormalsContext paramFormals() throws RecognitionException {
		ParamFormalsContext _localctx = new ParamFormalsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_paramFormals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__3);
			setState(175);
			match(T__0);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__10) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(176);
				paramFormal();
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(177);
					match(T__1);
					setState(178);
					paramFormal();
					}
					}
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(186);
			match(T__2);
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

	public static class TypeContext extends ParserRuleContext {
		public UpperCaseIdentifierContext name;
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((TypeContext)_localctx).name = upperCaseIdentifier();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(189);
				params();
				}
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

	public static class PackageDefContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MinispecParser.EOF, 0); }
		public List<PackageStmtContext> packageStmt() {
			return getRuleContexts(PackageStmtContext.class);
		}
		public PackageStmtContext packageStmt(int i) {
			return getRuleContext(PackageStmtContext.class,i);
		}
		public PackageDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterPackageDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitPackageDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitPackageDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDefContext packageDef() throws RecognitionException {
		PackageDefContext _localctx = new PackageDefContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_packageDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (T__5 - 6)) | (1L << (T__7 - 6)) | (1L << (T__8 - 6)) | (1L << (T__14 - 6)) | (1L << (T__15 - 6)) | (1L << (T__23 - 6)) | (1L << (UpperCaseIdentifier - 6)))) != 0)) {
				{
				{
				setState(192);
				packageStmt();
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
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

	public static class PackageStmtContext extends ParserRuleContext {
		public ImportDeclContext importDecl() {
			return getRuleContext(ImportDeclContext.class,0);
		}
		public BsvImportDeclContext bsvImportDecl() {
			return getRuleContext(BsvImportDeclContext.class,0);
		}
		public TypeDeclContext typeDecl() {
			return getRuleContext(TypeDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public FunctionDefContext functionDef() {
			return getRuleContext(FunctionDefContext.class,0);
		}
		public ModuleDefContext moduleDef() {
			return getRuleContext(ModuleDefContext.class,0);
		}
		public PackageStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterPackageStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitPackageStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitPackageStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageStmtContext packageStmt() throws RecognitionException {
		PackageStmtContext _localctx = new PackageStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_packageStmt);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				importDecl();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				bsvImportDecl();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				typeDecl();
				}
				break;
			case T__14:
			case UpperCaseIdentifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				varDecl();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 5);
				{
				setState(204);
				functionDef();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(205);
				moduleDef();
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

	public static class ImportDeclContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_importDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__5);
			setState(209);
			identifier();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(210);
				match(T__1);
				setState(211);
				identifier();
				}
				}
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(217);
			match(T__6);
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

	public static class BsvImportDeclContext extends ParserRuleContext {
		public List<UpperCaseIdentifierContext> upperCaseIdentifier() {
			return getRuleContexts(UpperCaseIdentifierContext.class);
		}
		public UpperCaseIdentifierContext upperCaseIdentifier(int i) {
			return getRuleContext(UpperCaseIdentifierContext.class,i);
		}
		public BsvImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bsvImportDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterBsvImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitBsvImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitBsvImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BsvImportDeclContext bsvImportDecl() throws RecognitionException {
		BsvImportDeclContext _localctx = new BsvImportDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bsvImportDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__7);
			setState(220);
			upperCaseIdentifier();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(221);
				match(T__1);
				setState(222);
				upperCaseIdentifier();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(228);
			match(T__6);
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

	public static class TypeDeclContext extends ParserRuleContext {
		public TypeDefSynonymContext typeDefSynonym() {
			return getRuleContext(TypeDefSynonymContext.class,0);
		}
		public TypeDefEnumContext typeDefEnum() {
			return getRuleContext(TypeDefEnumContext.class,0);
		}
		public TypeDefStructContext typeDefStruct() {
			return getRuleContext(TypeDefStructContext.class,0);
		}
		public TypeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeDecl);
		try {
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				typeDefSynonym();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				typeDefEnum();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(232);
				typeDefStruct();
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

	public static class TypeDefSynonymContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeIdContext typeId() {
			return getRuleContext(TypeIdContext.class,0);
		}
		public TypeDefSynonymContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefSynonym; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeDefSynonym(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeDefSynonym(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeDefSynonym(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefSynonymContext typeDefSynonym() throws RecognitionException {
		TypeDefSynonymContext _localctx = new TypeDefSynonymContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeDefSynonym);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__8);
			setState(236);
			type();
			setState(237);
			typeId();
			setState(238);
			match(T__6);
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

	public static class TypeIdContext extends ParserRuleContext {
		public UpperCaseIdentifierContext name;
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public ParamFormalsContext paramFormals() {
			return getRuleContext(ParamFormalsContext.class,0);
		}
		public TypeIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIdContext typeId() throws RecognitionException {
		TypeIdContext _localctx = new TypeIdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			((TypeIdContext)_localctx).name = upperCaseIdentifier();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(241);
				paramFormals();
				}
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

	public static class TypeDefEnumContext extends ParserRuleContext {
		public List<TypeDefEnumElementContext> typeDefEnumElement() {
			return getRuleContexts(TypeDefEnumElementContext.class);
		}
		public TypeDefEnumElementContext typeDefEnumElement(int i) {
			return getRuleContext(TypeDefEnumElementContext.class,i);
		}
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public TypeDefEnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefEnum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeDefEnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeDefEnum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeDefEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefEnumContext typeDefEnum() throws RecognitionException {
		TypeDefEnumContext _localctx = new TypeDefEnumContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeDefEnum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(T__8);
			setState(245);
			match(T__9);
			setState(246);
			match(T__10);
			setState(247);
			typeDefEnumElement();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(248);
				match(T__1);
				setState(249);
				typeDefEnumElement();
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(255);
			match(T__11);
			setState(256);
			upperCaseIdentifier();
			setState(257);
			match(T__6);
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

	public static class TypeDefEnumElementContext extends ParserRuleContext {
		public UpperCaseIdentifierContext tag;
		public Token tagval;
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public TerminalNode IntLiteral() { return getToken(MinispecParser.IntLiteral, 0); }
		public TypeDefEnumElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefEnumElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeDefEnumElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeDefEnumElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeDefEnumElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefEnumElementContext typeDefEnumElement() throws RecognitionException {
		TypeDefEnumElementContext _localctx = new TypeDefEnumElementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeDefEnumElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			((TypeDefEnumElementContext)_localctx).tag = upperCaseIdentifier();
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(260);
				match(T__12);
				setState(261);
				((TypeDefEnumElementContext)_localctx).tagval = match(IntLiteral);
				}
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

	public static class TypeDefStructContext extends ParserRuleContext {
		public TypeIdContext typeId() {
			return getRuleContext(TypeIdContext.class,0);
		}
		public List<StructMemberContext> structMember() {
			return getRuleContexts(StructMemberContext.class);
		}
		public StructMemberContext structMember(int i) {
			return getRuleContext(StructMemberContext.class,i);
		}
		public TypeDefStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterTypeDefStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitTypeDefStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitTypeDefStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefStructContext typeDefStruct() throws RecognitionException {
		TypeDefStructContext _localctx = new TypeDefStructContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeDefStruct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(T__8);
			setState(265);
			match(T__13);
			setState(266);
			match(T__10);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UpperCaseIdentifier) {
				{
				{
				setState(267);
				structMember();
				}
				}
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(273);
			match(T__11);
			setState(274);
			typeId();
			setState(275);
			match(T__6);
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

	public static class StructMemberContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public StructMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structMember; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterStructMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitStructMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitStructMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructMemberContext structMember() throws RecognitionException {
		StructMemberContext _localctx = new StructMemberContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_structMember);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			type();
			setState(278);
			lowerCaseIdentifier();
			setState(279);
			match(T__6);
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

	public static class VarDeclContext extends ParserRuleContext {
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
	 
		public VarDeclContext() { }
		public void copyFrom(VarDeclContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LetBindingContext extends VarDeclContext {
		public ExpressionContext rhs;
		public List<LowerCaseIdentifierContext> lowerCaseIdentifier() {
			return getRuleContexts(LowerCaseIdentifierContext.class);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier(int i) {
			return getRuleContext(LowerCaseIdentifierContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LetBindingContext(VarDeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterLetBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitLetBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitLetBinding(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarBindingContext extends VarDeclContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public VarBindingContext(VarDeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterVarBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitVarBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitVarBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_varDecl);
		int _la;
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UpperCaseIdentifier:
				_localctx = new VarBindingContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				type();
				setState(282);
				varInit();
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(283);
					match(T__1);
					setState(284);
					varInit();
					}
					}
					setState(289);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(290);
				match(T__6);
				}
				break;
			case T__14:
				_localctx = new LetBindingContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(T__14);
				setState(305);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LowerCaseIdentifier:
					{
					setState(293);
					lowerCaseIdentifier();
					}
					break;
				case T__10:
					{
					{
					setState(294);
					match(T__10);
					setState(295);
					lowerCaseIdentifier();
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(296);
						match(T__1);
						setState(297);
						lowerCaseIdentifier();
						}
						}
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(303);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(307);
					match(T__12);
					setState(308);
					((LetBindingContext)_localctx).rhs = expression(0);
					}
				}

				setState(311);
				match(T__6);
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

	public static class VarInitContext extends ParserRuleContext {
		public LowerCaseIdentifierContext var;
		public ExpressionContext rhs;
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterVarInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitVarInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitVarInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_varInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			((VarInitContext)_localctx).var = lowerCaseIdentifier();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(316);
				match(T__12);
				setState(317);
				((VarInitContext)_localctx).rhs = expression(0);
				}
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

	public static class ModuleDefContext extends ParserRuleContext {
		public ModuleIdContext moduleId() {
			return getRuleContext(ModuleIdContext.class,0);
		}
		public ArgFormalsContext argFormals() {
			return getRuleContext(ArgFormalsContext.class,0);
		}
		public List<ModuleStmtContext> moduleStmt() {
			return getRuleContexts(ModuleStmtContext.class);
		}
		public ModuleStmtContext moduleStmt(int i) {
			return getRuleContext(ModuleStmtContext.class,i);
		}
		public ModuleDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterModuleDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitModuleDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitModuleDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDefContext moduleDef() throws RecognitionException {
		ModuleDefContext _localctx = new ModuleDefContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_moduleDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(T__15);
			setState(321);
			moduleId();
			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(322);
				argFormals();
				}
			}

			setState(325);
			match(T__6);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__14) | (1L << T__17) | (1L << T__19) | (1L << T__21) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				{
				setState(326);
				moduleStmt();
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(332);
			match(T__16);
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

	public static class ModuleIdContext extends ParserRuleContext {
		public UpperCaseIdentifierContext name;
		public UpperCaseIdentifierContext upperCaseIdentifier() {
			return getRuleContext(UpperCaseIdentifierContext.class,0);
		}
		public ParamFormalsContext paramFormals() {
			return getRuleContext(ParamFormalsContext.class,0);
		}
		public ModuleIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterModuleId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitModuleId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitModuleId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleIdContext moduleId() throws RecognitionException {
		ModuleIdContext _localctx = new ModuleIdContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_moduleId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			((ModuleIdContext)_localctx).name = upperCaseIdentifier();
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(335);
				paramFormals();
				}
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

	public static class ModuleStmtContext extends ParserRuleContext {
		public SubmoduleDeclContext submoduleDecl() {
			return getRuleContext(SubmoduleDeclContext.class,0);
		}
		public InputDefContext inputDef() {
			return getRuleContext(InputDefContext.class,0);
		}
		public MethodDefContext methodDef() {
			return getRuleContext(MethodDefContext.class,0);
		}
		public RuleDefContext ruleDef() {
			return getRuleContext(RuleDefContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ModuleStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterModuleStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitModuleStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitModuleStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleStmtContext moduleStmt() throws RecognitionException {
		ModuleStmtContext _localctx = new ModuleStmtContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_moduleStmt);
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				submoduleDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				inputDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				methodDef();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(341);
				ruleDef();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(342);
				stmt();
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

	public static class SubmoduleDeclContext extends ParserRuleContext {
		public LowerCaseIdentifierContext name;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public SubmoduleDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_submoduleDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterSubmoduleDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitSubmoduleDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitSubmoduleDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubmoduleDeclContext submoduleDecl() throws RecognitionException {
		SubmoduleDeclContext _localctx = new SubmoduleDeclContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_submoduleDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			type();
			setState(346);
			((SubmoduleDeclContext)_localctx).name = lowerCaseIdentifier();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(347);
				args();
				}
			}

			setState(350);
			match(T__6);
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

	public static class InputDefContext extends ParserRuleContext {
		public LowerCaseIdentifierContext name;
		public ExpressionContext defaultVal;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InputDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterInputDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitInputDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitInputDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputDefContext inputDef() throws RecognitionException {
		InputDefContext _localctx = new InputDefContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_inputDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(T__17);
			setState(353);
			type();
			setState(354);
			((InputDefContext)_localctx).name = lowerCaseIdentifier();
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(355);
				match(T__18);
				setState(356);
				match(T__12);
				setState(357);
				((InputDefContext)_localctx).defaultVal = expression(0);
				}
			}

			setState(360);
			match(T__6);
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

	public static class MethodDefContext extends ParserRuleContext {
		public LowerCaseIdentifierContext name;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgFormalsContext argFormals() {
			return getRuleContext(ArgFormalsContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public MethodDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterMethodDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitMethodDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitMethodDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDefContext methodDef() throws RecognitionException {
		MethodDefContext _localctx = new MethodDefContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_methodDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(T__19);
			setState(363);
			type();
			setState(364);
			((MethodDefContext)_localctx).name = lowerCaseIdentifier();
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(365);
				argFormals();
				}
			}

			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(368);
				match(T__6);
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__14) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
					{
					{
					setState(369);
					stmt();
					}
					}
					setState(374);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(375);
				match(T__20);
				}
				break;
			case T__12:
				{
				setState(376);
				match(T__12);
				setState(377);
				expression(0);
				setState(378);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class RuleDefContext extends ParserRuleContext {
		public LowerCaseIdentifierContext name;
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public RuleDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterRuleDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitRuleDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitRuleDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleDefContext ruleDef() throws RecognitionException {
		RuleDefContext _localctx = new RuleDefContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ruleDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(T__21);
			setState(383);
			((RuleDefContext)_localctx).name = lowerCaseIdentifier();
			setState(384);
			match(T__6);
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__14) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				{
				setState(385);
				stmt();
				}
				}
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(391);
			match(T__22);
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

	public static class FunctionDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionIdContext functionId() {
			return getRuleContext(FunctionIdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgFormalsContext argFormals() {
			return getRuleContext(ArgFormalsContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitFunctionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(T__23);
			setState(394);
			type();
			setState(395);
			functionId();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(396);
				argFormals();
				}
			}

			setState(411);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(399);
				match(T__6);
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__14) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
					{
					{
					setState(400);
					stmt();
					}
					}
					setState(405);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(406);
				match(T__24);
				}
				break;
			case T__12:
				{
				setState(407);
				match(T__12);
				setState(408);
				expression(0);
				setState(409);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class FunctionIdContext extends ParserRuleContext {
		public LowerCaseIdentifierContext name;
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public ParamFormalsContext paramFormals() {
			return getRuleContext(ParamFormalsContext.class,0);
		}
		public FunctionIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterFunctionId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitFunctionId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitFunctionId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionIdContext functionId() throws RecognitionException {
		FunctionIdContext _localctx = new FunctionIdContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_functionId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			((FunctionIdContext)_localctx).name = lowerCaseIdentifier();
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(414);
				paramFormals();
				}
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

	public static class VarAssignContext extends ParserRuleContext {
		public LvalueContext var;
		public Token vars;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<LvalueContext> lvalue() {
			return getRuleContexts(LvalueContext.class);
		}
		public LvalueContext lvalue(int i) {
			return getRuleContext(LvalueContext.class,i);
		}
		public VarAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterVarAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitVarAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitVarAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignContext varAssign() throws RecognitionException {
		VarAssignContext _localctx = new VarAssignContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_varAssign);
		int _la;
		try {
			setState(436);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LowerCaseIdentifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				((VarAssignContext)_localctx).var = lvalue(0);
				setState(418);
				match(T__12);
				setState(419);
				expression(0);
				setState(420);
				match(T__6);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				((VarAssignContext)_localctx).vars = match(T__10);
				setState(423);
				lvalue(0);
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(424);
					match(T__1);
					setState(425);
					lvalue(0);
					}
					}
					setState(430);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(431);
				match(T__11);
				setState(432);
				match(T__12);
				setState(433);
				expression(0);
				setState(434);
				match(T__6);
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

	public static class LvalueContext extends ParserRuleContext {
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	 
		public LvalueContext() { }
		public void copyFrom(LvalueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MemberLvalueContext extends LvalueContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public MemberLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterMemberLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitMemberLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitMemberLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexLvalueContext extends LvalueContext {
		public ExpressionContext index;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IndexLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterIndexLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitIndexLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitIndexLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleLvalueContext extends LvalueContext {
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public SimpleLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterSimpleLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitSimpleLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitSimpleLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SliceLvalueContext extends LvalueContext {
		public ExpressionContext msb;
		public ExpressionContext lsb;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SliceLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterSliceLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitSliceLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitSliceLvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 72;
		enterRecursionRule(_localctx, 72, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SimpleLvalueContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(439);
			lowerCaseIdentifier();
			}
			_ctx.stop = _input.LT(-1);
			setState(458);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(456);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						_localctx = new MemberLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(441);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(442);
						match(T__25);
						setState(443);
						lowerCaseIdentifier();
						}
						break;
					case 2:
						{
						_localctx = new IndexLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(444);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(445);
						match(T__26);
						setState(446);
						((IndexLvalueContext)_localctx).index = expression(0);
						setState(447);
						match(T__27);
						}
						break;
					case 3:
						{
						_localctx = new SliceLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(449);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(450);
						match(T__26);
						setState(451);
						((SliceLvalueContext)_localctx).msb = expression(0);
						setState(452);
						match(T__28);
						setState(453);
						((SliceLvalueContext)_localctx).lsb = expression(0);
						setState(454);
						match(T__27);
						}
						break;
					}
					} 
				}
				setState(460);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
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
	public static class OperatorExprContext extends ExpressionContext {
		public BinopExprContext binopExpr() {
			return getRuleContext(BinopExprContext.class,0);
		}
		public OperatorExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterOperatorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitOperatorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitOperatorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondExprContext extends ExpressionContext {
		public ExpressionContext pred;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CondExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCondExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCondExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCondExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CaseExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<CaseExprItemContext> caseExprItem() {
			return getRuleContexts(CaseExprItemContext.class);
		}
		public CaseExprItemContext caseExprItem(int i) {
			return getRuleContext(CaseExprItemContext.class,i);
		}
		public CaseExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCaseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCaseExpr(this);
			else return visitor.visitChildren(this);
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
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__30:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(462);
				match(T__30);
				setState(463);
				match(T__0);
				setState(464);
				expression(0);
				setState(465);
				match(T__2);
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__18) | (1L << T__29) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
					{
					{
					setState(466);
					caseExprItem();
					}
					}
					setState(471);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(472);
				match(T__31);
				}
				break;
			case T__0:
			case T__10:
			case T__29:
			case T__36:
			case T__37:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case UpperCaseIdentifier:
			case LowerCaseIdentifier:
			case DollarIdentifier:
			case IntLiteral:
			case StringLiteral:
				{
				_localctx = new OperatorExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(474);
				binopExpr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(485);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CondExprContext(new ExpressionContext(_parentctx, _parentState));
					((CondExprContext)_localctx).pred = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(477);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(478);
					match(T__29);
					setState(479);
					expression(0);
					setState(480);
					match(T__28);
					setState(481);
					expression(3);
					}
					} 
				}
				setState(487);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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

	public static class CaseExprItemContext extends ParserRuleContext {
		public ExpressionContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ExprPrimaryContext> exprPrimary() {
			return getRuleContexts(ExprPrimaryContext.class);
		}
		public ExprPrimaryContext exprPrimary(int i) {
			return getRuleContext(ExprPrimaryContext.class,i);
		}
		public CaseExprItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExprItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCaseExprItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCaseExprItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCaseExprItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExprItemContext caseExprItem() throws RecognitionException {
		CaseExprItemContext _localctx = new CaseExprItemContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_caseExprItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(488);
				match(T__18);
				}
				break;
			case T__0:
			case T__10:
			case T__29:
			case T__57:
			case T__58:
			case UpperCaseIdentifier:
			case LowerCaseIdentifier:
			case DollarIdentifier:
			case IntLiteral:
			case StringLiteral:
				{
				{
				setState(489);
				exprPrimary(0);
				setState(494);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(490);
					match(T__1);
					setState(491);
					exprPrimary(0);
					}
					}
					setState(496);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(499);
			match(T__28);
			setState(500);
			((CaseExprItemContext)_localctx).body = expression(0);
			setState(501);
			match(T__6);
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

	public static class BinopExprContext extends ParserRuleContext {
		public BinopExprContext left;
		public Token op;
		public BinopExprContext right;
		public UnopExprContext unopExpr() {
			return getRuleContext(UnopExprContext.class,0);
		}
		public List<BinopExprContext> binopExpr() {
			return getRuleContexts(BinopExprContext.class);
		}
		public BinopExprContext binopExpr(int i) {
			return getRuleContext(BinopExprContext.class,i);
		}
		public BinopExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binopExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterBinopExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitBinopExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitBinopExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinopExprContext binopExpr() throws RecognitionException {
		return binopExpr(0);
	}

	private BinopExprContext binopExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BinopExprContext _localctx = new BinopExprContext(_ctx, _parentState);
		BinopExprContext _prevctx = _localctx;
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_binopExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(504);
			unopExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(538);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(536);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
					case 1:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(506);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(507);
						((BinopExprContext)_localctx).op = match(T__32);
						setState(508);
						((BinopExprContext)_localctx).right = binopExpr(12);
						}
						break;
					case 2:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(509);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(510);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(511);
						((BinopExprContext)_localctx).right = binopExpr(11);
						}
						break;
					case 3:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(512);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(513);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__36 || _la==T__37) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(514);
						((BinopExprContext)_localctx).right = binopExpr(10);
						}
						break;
					case 4:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(515);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(516);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__38 || _la==T__39) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(517);
						((BinopExprContext)_localctx).right = binopExpr(9);
						}
						break;
					case 5:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(518);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(519);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43))) != 0)) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(520);
						((BinopExprContext)_localctx).right = binopExpr(8);
						}
						break;
					case 6:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(521);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(522);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__44 || _la==T__45) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(523);
						((BinopExprContext)_localctx).right = binopExpr(7);
						}
						break;
					case 7:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(524);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(525);
						((BinopExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49))) != 0)) ) {
							((BinopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(526);
						((BinopExprContext)_localctx).right = binopExpr(6);
						}
						break;
					case 8:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(527);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(528);
						((BinopExprContext)_localctx).op = match(T__50);
						setState(529);
						((BinopExprContext)_localctx).right = binopExpr(5);
						}
						break;
					case 9:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(530);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(531);
						((BinopExprContext)_localctx).op = match(T__51);
						setState(532);
						((BinopExprContext)_localctx).right = binopExpr(4);
						}
						break;
					case 10:
						{
						_localctx = new BinopExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_binopExpr);
						setState(533);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(534);
						((BinopExprContext)_localctx).op = match(T__52);
						setState(535);
						((BinopExprContext)_localctx).right = binopExpr(3);
						}
						break;
					}
					} 
				}
				setState(540);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
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

	public static class UnopExprContext extends ParserRuleContext {
		public Token op;
		public ExprPrimaryContext exprPrimary() {
			return getRuleContext(ExprPrimaryContext.class,0);
		}
		public UnopExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unopExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterUnopExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitUnopExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitUnopExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnopExprContext unopExpr() throws RecognitionException {
		UnopExprContext _localctx = new UnopExprContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_unopExpr);
		int _la;
		try {
			setState(546);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
				enterOuterAlt(_localctx, 1);
				{
				setState(541);
				((UnopExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56))) != 0)) ) {
					((UnopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(542);
				exprPrimary(0);
				}
				break;
			case T__36:
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(543);
				((UnopExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__36 || _la==T__37) ) {
					((UnopExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(544);
				exprPrimary(0);
				}
				break;
			case T__0:
			case T__10:
			case T__29:
			case T__57:
			case T__58:
			case UpperCaseIdentifier:
			case LowerCaseIdentifier:
			case DollarIdentifier:
			case IntLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(545);
				exprPrimary(0);
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

	public static class ExprPrimaryContext extends ParserRuleContext {
		public ExprPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprPrimary; }
	 
		public ExprPrimaryContext() { }
		public void copyFrom(ExprPrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarExprContext extends ExprPrimaryContext {
		public AnyIdentifierContext var;
		public AnyIdentifierContext anyIdentifier() {
			return getRuleContext(AnyIdentifierContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public VarExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitConcatContext extends ExprPrimaryContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BitConcatContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterBitConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitBitConcat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitBitConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExprPrimaryContext {
		public TerminalNode StringLiteral() { return getToken(MinispecParser.StringLiteral, 0); }
		public StringLiteralContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiteralContext extends ExprPrimaryContext {
		public TerminalNode IntLiteral() { return getToken(MinispecParser.IntLiteral, 0); }
		public IntLiteralContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnExprContext extends ExprPrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterReturnExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitReturnExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitReturnExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StructExprContext extends ExprPrimaryContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public MemberBindsContext memberBinds() {
			return getRuleContext(MemberBindsContext.class,0);
		}
		public StructExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterStructExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitStructExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitStructExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UndefinedExprContext extends ExprPrimaryContext {
		public UndefinedExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterUndefinedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitUndefinedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitUndefinedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockExprContext extends ExprPrimaryContext {
		public BeginEndBlockContext beginEndBlock() {
			return getRuleContext(BeginEndBlockContext.class,0);
		}
		public BlockExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterBlockExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitBlockExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitBlockExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SliceExprContext extends ExprPrimaryContext {
		public ExprPrimaryContext array;
		public ExpressionContext msb;
		public ExpressionContext lsb;
		public ExprPrimaryContext exprPrimary() {
			return getRuleContext(ExprPrimaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SliceExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterSliceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitSliceExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitSliceExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallExprContext extends ExprPrimaryContext {
		public ExprPrimaryContext fcn;
		public ExprPrimaryContext exprPrimary() {
			return getRuleContext(ExprPrimaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CallExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FieldExprContext extends ExprPrimaryContext {
		public IdentifierContext field;
		public ExprPrimaryContext exprPrimary() {
			return getRuleContext(ExprPrimaryContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterFieldExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitFieldExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitFieldExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExprPrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(ExprPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprPrimaryContext exprPrimary() throws RecognitionException {
		return exprPrimary(0);
	}

	private ExprPrimaryContext exprPrimary(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprPrimaryContext _localctx = new ExprPrimaryContext(_ctx, _parentState);
		ExprPrimaryContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_exprPrimary, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(549);
				match(T__0);
				setState(550);
				expression(0);
				setState(551);
				match(T__2);
				}
				break;
			case 2:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(553);
				((VarExprContext)_localctx).var = anyIdentifier();
				setState(555);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(554);
					params();
					}
					break;
				}
				}
				break;
			case 3:
				{
				_localctx = new IntLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(557);
				match(IntLiteral);
				}
				break;
			case 4:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(558);
				match(StringLiteral);
				}
				break;
			case 5:
				{
				_localctx = new UndefinedExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(559);
				match(T__29);
				}
				break;
			case 6:
				{
				_localctx = new ReturnExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(560);
				match(T__57);
				setState(561);
				expression(0);
				}
				break;
			case 7:
				{
				_localctx = new BitConcatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(562);
				match(T__10);
				setState(563);
				expression(0);
				setState(568);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(564);
					match(T__1);
					setState(565);
					expression(0);
					}
					}
					setState(570);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(571);
				match(T__11);
				}
				break;
			case 8:
				{
				_localctx = new StructExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(573);
				type();
				setState(574);
				match(T__10);
				setState(575);
				memberBinds();
				setState(576);
				match(T__11);
				}
				break;
			case 9:
				{
				_localctx = new BlockExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(578);
				beginEndBlock();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(608);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(606);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
					case 1:
						{
						_localctx = new FieldExprContext(new ExprPrimaryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exprPrimary);
						setState(581);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(582);
						match(T__25);
						setState(583);
						((FieldExprContext)_localctx).field = identifier();
						}
						break;
					case 2:
						{
						_localctx = new SliceExprContext(new ExprPrimaryContext(_parentctx, _parentState));
						((SliceExprContext)_localctx).array = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exprPrimary);
						setState(584);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(585);
						match(T__26);
						setState(586);
						((SliceExprContext)_localctx).msb = expression(0);
						setState(589);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__28) {
							{
							setState(587);
							match(T__28);
							setState(588);
							((SliceExprContext)_localctx).lsb = expression(0);
							}
						}

						setState(591);
						match(T__27);
						}
						break;
					case 3:
						{
						_localctx = new CallExprContext(new ExprPrimaryContext(_parentctx, _parentState));
						((CallExprContext)_localctx).fcn = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exprPrimary);
						setState(593);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(594);
						match(T__0);
						setState(603);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
							{
							setState(595);
							expression(0);
							setState(600);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__1) {
								{
								{
								setState(596);
								match(T__1);
								setState(597);
								expression(0);
								}
								}
								setState(602);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(605);
						match(T__2);
						}
						break;
					}
					} 
				}
				setState(610);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
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

	public static class MemberBindsContext extends ParserRuleContext {
		public List<MemberBindContext> memberBind() {
			return getRuleContexts(MemberBindContext.class);
		}
		public MemberBindContext memberBind(int i) {
			return getRuleContext(MemberBindContext.class,i);
		}
		public MemberBindsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberBinds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterMemberBinds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitMemberBinds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitMemberBinds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberBindsContext memberBinds() throws RecognitionException {
		MemberBindsContext _localctx = new MemberBindsContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_memberBinds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611);
			memberBind();
			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(612);
				match(T__1);
				setState(613);
				memberBind();
				}
				}
				setState(618);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class MemberBindContext extends ParserRuleContext {
		public LowerCaseIdentifierContext field;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier() {
			return getRuleContext(LowerCaseIdentifierContext.class,0);
		}
		public MemberBindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberBind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterMemberBind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitMemberBind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitMemberBind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberBindContext memberBind() throws RecognitionException {
		MemberBindContext _localctx = new MemberBindContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_memberBind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			((MemberBindContext)_localctx).field = lowerCaseIdentifier();
			setState(620);
			match(T__28);
			setState(621);
			expression(0);
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

	public static class BeginEndBlockContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BeginEndBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginEndBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterBeginEndBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitBeginEndBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitBeginEndBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginEndBlockContext beginEndBlock() throws RecognitionException {
		BeginEndBlockContext _localctx = new BeginEndBlockContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_beginEndBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(T__58);
			setState(627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__14) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				{
				setState(624);
				stmt();
				}
				}
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(630);
			match(T__59);
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

	public static class RegWriteContext extends ParserRuleContext {
		public LvalueContext lhs;
		public ExpressionContext rhs;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RegWriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regWrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterRegWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitRegWrite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitRegWrite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegWriteContext regWrite() throws RecognitionException {
		RegWriteContext _localctx = new RegWriteContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_regWrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(632);
			((RegWriteContext)_localctx).lhs = lvalue(0);
			setState(633);
			match(T__41);
			setState(634);
			((RegWriteContext)_localctx).rhs = expression(0);
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

	public static class StmtContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarAssignContext varAssign() {
			return getRuleContext(VarAssignContext.class,0);
		}
		public RegWriteContext regWrite() {
			return getRuleContext(RegWriteContext.class,0);
		}
		public BeginEndBlockContext beginEndBlock() {
			return getRuleContext(BeginEndBlockContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public CaseStmtContext caseStmt() {
			return getRuleContext(CaseStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_stmt);
		try {
			setState(648);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(636);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(637);
				varAssign();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(638);
				regWrite();
				setState(639);
				match(T__6);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(641);
				beginEndBlock();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(642);
				ifStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(643);
				caseStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(644);
				forStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(645);
				expression(0);
				setState(646);
				match(T__6);
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

	public static class IfStmtContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
			match(T__60);
			setState(651);
			match(T__0);
			setState(652);
			expression(0);
			setState(653);
			match(T__2);
			setState(654);
			stmt();
			setState(657);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(655);
				match(T__61);
				setState(656);
				stmt();
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

	public static class CaseStmtContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<CaseStmtItemContext> caseStmtItem() {
			return getRuleContexts(CaseStmtItemContext.class);
		}
		public CaseStmtItemContext caseStmtItem(int i) {
			return getRuleContext(CaseStmtItemContext.class,i);
		}
		public CaseStmtDefaultItemContext caseStmtDefaultItem() {
			return getRuleContext(CaseStmtDefaultItemContext.class,0);
		}
		public CaseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCaseStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCaseStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCaseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStmtContext caseStmt() throws RecognitionException {
		CaseStmtContext _localctx = new CaseStmtContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_caseStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(659);
			match(T__30);
			setState(660);
			match(T__0);
			setState(661);
			expression(0);
			setState(662);
			match(T__2);
			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UpperCaseIdentifier - 64)) | (1L << (LowerCaseIdentifier - 64)) | (1L << (DollarIdentifier - 64)) | (1L << (IntLiteral - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				{
				setState(663);
				caseStmtItem();
				}
				}
				setState(668);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(669);
				caseStmtDefaultItem();
				}
			}

			setState(672);
			match(T__31);
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

	public static class CaseStmtItemContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public CaseStmtItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStmtItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCaseStmtItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCaseStmtItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCaseStmtItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStmtItemContext caseStmtItem() throws RecognitionException {
		CaseStmtItemContext _localctx = new CaseStmtItemContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_caseStmtItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(674);
			expression(0);
			setState(679);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(675);
				match(T__1);
				setState(676);
				expression(0);
				}
				}
				setState(681);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(682);
			match(T__28);
			setState(683);
			stmt();
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

	public static class CaseStmtDefaultItemContext extends ParserRuleContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public CaseStmtDefaultItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStmtDefaultItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterCaseStmtDefaultItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitCaseStmtDefaultItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitCaseStmtDefaultItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStmtDefaultItemContext caseStmtDefaultItem() throws RecognitionException {
		CaseStmtDefaultItemContext _localctx = new CaseStmtDefaultItemContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_caseStmtDefaultItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(685);
			match(T__18);
			setState(686);
			match(T__28);
			setState(687);
			stmt();
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

	public static class ForStmtContext extends ParserRuleContext {
		public LowerCaseIdentifierContext initVar;
		public LowerCaseIdentifierContext updVar;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public List<LowerCaseIdentifierContext> lowerCaseIdentifier() {
			return getRuleContexts(LowerCaseIdentifierContext.class);
		}
		public LowerCaseIdentifierContext lowerCaseIdentifier(int i) {
			return getRuleContext(LowerCaseIdentifierContext.class,i);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MinispecListener ) ((MinispecListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinispecVisitor ) return ((MinispecVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			match(T__62);
			setState(690);
			match(T__0);
			setState(691);
			type();
			setState(692);
			((ForStmtContext)_localctx).initVar = lowerCaseIdentifier();
			setState(693);
			match(T__12);
			setState(694);
			expression(0);
			setState(695);
			match(T__6);
			setState(696);
			expression(0);
			setState(697);
			match(T__6);
			setState(698);
			((ForStmtContext)_localctx).updVar = lowerCaseIdentifier();
			setState(699);
			match(T__12);
			setState(700);
			expression(0);
			setState(701);
			match(T__2);
			setState(702);
			stmt();
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
		case 36:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		case 37:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 39:
			return binopExpr_sempred((BinopExprContext)_localctx, predIndex);
		case 41:
			return exprPrimary_sempred((ExprPrimaryContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean binopExpr_sempred(BinopExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		case 8:
			return precpred(_ctx, 7);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 4);
		case 12:
			return precpred(_ctx, 3);
		case 13:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exprPrimary_sempred(ExprPrimaryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 11);
		case 15:
			return precpred(_ctx, 4);
		case 16:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3I\u02c3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\5\4q\n\4\3\5\3\5\3\5\5\5v\n\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13\7\5\7\u0083\n\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\7\t\u008e\n\t\f\t\16\t\u0091\13\t\5\t"+
		"\u0093\n\t\3\t\3\t\3\n\3\n\5\n\u0099\n\n\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u00a0\n\13\f\13\16\13\u00a3\13\13\5\13\u00a5\n\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00af\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u00b6\n\r\f\r\16"+
		"\r\u00b9\13\r\5\r\u00bb\n\r\3\r\3\r\3\16\3\16\5\16\u00c1\n\16\3\17\7\17"+
		"\u00c4\n\17\f\17\16\17\u00c7\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00d1\n\20\3\21\3\21\3\21\3\21\7\21\u00d7\n\21\f\21\16\21\u00da"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00e2\n\22\f\22\16\22\u00e5"+
		"\13\22\3\22\3\22\3\23\3\23\3\23\5\23\u00ec\n\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\5\25\u00f5\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u00fd"+
		"\n\26\f\26\16\26\u0100\13\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\5\27\u0109"+
		"\n\27\3\30\3\30\3\30\3\30\7\30\u010f\n\30\f\30\16\30\u0112\13\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u0120\n\32"+
		"\f\32\16\32\u0123\13\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u012d"+
		"\n\32\f\32\16\32\u0130\13\32\3\32\3\32\5\32\u0134\n\32\3\32\3\32\5\32"+
		"\u0138\n\32\3\32\3\32\5\32\u013c\n\32\3\33\3\33\3\33\5\33\u0141\n\33\3"+
		"\34\3\34\3\34\5\34\u0146\n\34\3\34\3\34\7\34\u014a\n\34\f\34\16\34\u014d"+
		"\13\34\3\34\3\34\3\35\3\35\5\35\u0153\n\35\3\36\3\36\3\36\3\36\3\36\5"+
		"\36\u015a\n\36\3\37\3\37\3\37\5\37\u015f\n\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \5 \u0169\n \3 \3 \3!\3!\3!\3!\5!\u0171\n!\3!\3!\7!\u0175\n!\f!\16"+
		"!\u0178\13!\3!\3!\3!\3!\3!\5!\u017f\n!\3\"\3\"\3\"\3\"\7\"\u0185\n\"\f"+
		"\"\16\"\u0188\13\"\3\"\3\"\3#\3#\3#\3#\5#\u0190\n#\3#\3#\7#\u0194\n#\f"+
		"#\16#\u0197\13#\3#\3#\3#\3#\3#\5#\u019e\n#\3$\3$\5$\u01a2\n$\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\7%\u01ad\n%\f%\16%\u01b0\13%\3%\3%\3%\3%\3%\5%\u01b7"+
		"\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\7&\u01cb\n&"+
		"\f&\16&\u01ce\13&\3\'\3\'\3\'\3\'\3\'\3\'\7\'\u01d6\n\'\f\'\16\'\u01d9"+
		"\13\'\3\'\3\'\3\'\5\'\u01de\n\'\3\'\3\'\3\'\3\'\3\'\3\'\7\'\u01e6\n\'"+
		"\f\'\16\'\u01e9\13\'\3(\3(\3(\3(\7(\u01ef\n(\f(\16(\u01f2\13(\5(\u01f4"+
		"\n(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\7)\u021b\n)\f)\16)\u021e"+
		"\13)\3*\3*\3*\3*\3*\5*\u0225\n*\3+\3+\3+\3+\3+\3+\3+\5+\u022e\n+\3+\3"+
		"+\3+\3+\3+\3+\3+\3+\3+\7+\u0239\n+\f+\16+\u023c\13+\3+\3+\3+\3+\3+\3+"+
		"\3+\3+\5+\u0246\n+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u0250\n+\3+\3+\3+\3+\3+"+
		"\3+\3+\7+\u0259\n+\f+\16+\u025c\13+\5+\u025e\n+\3+\7+\u0261\n+\f+\16+"+
		"\u0264\13+\3,\3,\3,\7,\u0269\n,\f,\16,\u026c\13,\3-\3-\3-\3-\3.\3.\7."+
		"\u0274\n.\f.\16.\u0277\13.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u028b\n\60\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\5\61\u0294\n\61\3\62\3\62\3\62\3\62\3\62\7\62\u029b\n"+
		"\62\f\62\16\62\u029e\13\62\3\62\5\62\u02a1\n\62\3\62\3\62\3\63\3\63\3"+
		"\63\7\63\u02a8\n\63\f\63\16\63\u02ab\13\63\3\63\3\63\3\63\3\64\3\64\3"+
		"\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\65\3\65\2\6JLPT\66\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 "+
		"\"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfh\2\t\3\2$&\3\2\'(\3\2)*"+
		"\3\2+.\3\2/\60\3\2\61\64\4\2\61\658;\2\u02f5\2j\3\2\2\2\4l\3\2\2\2\6p"+
		"\3\2\2\2\bu\3\2\2\2\nw\3\2\2\2\fy\3\2\2\2\16\u0086\3\2\2\2\20\u0089\3"+
		"\2\2\2\22\u0098\3\2\2\2\24\u009a\3\2\2\2\26\u00ae\3\2\2\2\30\u00b0\3\2"+
		"\2\2\32\u00be\3\2\2\2\34\u00c5\3\2\2\2\36\u00d0\3\2\2\2 \u00d2\3\2\2\2"+
		"\"\u00dd\3\2\2\2$\u00eb\3\2\2\2&\u00ed\3\2\2\2(\u00f2\3\2\2\2*\u00f6\3"+
		"\2\2\2,\u0105\3\2\2\2.\u010a\3\2\2\2\60\u0117\3\2\2\2\62\u013b\3\2\2\2"+
		"\64\u013d\3\2\2\2\66\u0142\3\2\2\28\u0150\3\2\2\2:\u0159\3\2\2\2<\u015b"+
		"\3\2\2\2>\u0162\3\2\2\2@\u016c\3\2\2\2B\u0180\3\2\2\2D\u018b\3\2\2\2F"+
		"\u019f\3\2\2\2H\u01b6\3\2\2\2J\u01b8\3\2\2\2L\u01dd\3\2\2\2N\u01f3\3\2"+
		"\2\2P\u01f9\3\2\2\2R\u0224\3\2\2\2T\u0245\3\2\2\2V\u0265\3\2\2\2X\u026d"+
		"\3\2\2\2Z\u0271\3\2\2\2\\\u027a\3\2\2\2^\u028a\3\2\2\2`\u028c\3\2\2\2"+
		"b\u0295\3\2\2\2d\u02a4\3\2\2\2f\u02af\3\2\2\2h\u02b3\3\2\2\2jk\7C\2\2"+
		"k\3\3\2\2\2lm\7B\2\2m\5\3\2\2\2nq\5\2\2\2oq\5\4\3\2pn\3\2\2\2po\3\2\2"+
		"\2q\7\3\2\2\2rv\5\2\2\2sv\5\4\3\2tv\7D\2\2ur\3\2\2\2us\3\2\2\2ut\3\2\2"+
		"\2v\t\3\2\2\2wx\5L\'\2x\13\3\2\2\2y\u0082\7\3\2\2z\177\5\n\6\2{|\7\4\2"+
		"\2|~\5\n\6\2}{\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0083\3\2\2\2\u0081\177\3\2\2\2\u0082z\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0085\7\5\2\2\u0085\r\3\2\2\2\u0086\u0087\5\32\16"+
		"\2\u0087\u0088\5\2\2\2\u0088\17\3\2\2\2\u0089\u0092\7\3\2\2\u008a\u008f"+
		"\5\16\b\2\u008b\u008c\7\4\2\2\u008c\u008e\5\16\b\2\u008d\u008b\3\2\2\2"+
		"\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0093"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u008a\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\7\5\2\2\u0095\21\3\2\2\2\u0096\u0099\5\32\16"+
		"\2\u0097\u0099\5L\'\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\23"+
		"\3\2\2\2\u009a\u009b\7\6\2\2\u009b\u00a4\7\3\2\2\u009c\u00a1\5\22\n\2"+
		"\u009d\u009e\7\4\2\2\u009e\u00a0\5\22\n\2\u009f\u009d\3\2\2\2\u00a0\u00a3"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u009c\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a7\7\5\2\2\u00a7\25\3\2\2\2\u00a8\u00a9\5\32\16\2\u00a9"+
		"\u00aa\5\2\2\2\u00aa\u00af\3\2\2\2\u00ab\u00ac\7\7\2\2\u00ac\u00af\5\4"+
		"\3\2\u00ad\u00af\5\22\n\2\u00ae\u00a8\3\2\2\2\u00ae\u00ab\3\2\2\2\u00ae"+
		"\u00ad\3\2\2\2\u00af\27\3\2\2\2\u00b0\u00b1\7\6\2\2\u00b1\u00ba\7\3\2"+
		"\2\u00b2\u00b7\5\26\f\2\u00b3\u00b4\7\4\2\2\u00b4\u00b6\5\26\f\2\u00b5"+
		"\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00b2\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\5\2\2\u00bd\31\3\2\2"+
		"\2\u00be\u00c0\5\4\3\2\u00bf\u00c1\5\24\13\2\u00c0\u00bf\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\33\3\2\2\2\u00c2\u00c4\5\36\20\2\u00c3\u00c2\3\2"+
		"\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\2\2\3\u00c9\35\3\2\2"+
		"\2\u00ca\u00d1\5 \21\2\u00cb\u00d1\5\"\22\2\u00cc\u00d1\5$\23\2\u00cd"+
		"\u00d1\5\62\32\2\u00ce\u00d1\5D#\2\u00cf\u00d1\5\66\34\2\u00d0\u00ca\3"+
		"\2\2\2\u00d0\u00cb\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d0\u00cd\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\37\3\2\2\2\u00d2\u00d3\7\b\2"+
		"\2\u00d3\u00d8\5\6\4\2\u00d4\u00d5\7\4\2\2\u00d5\u00d7\5\6\4\2\u00d6\u00d4"+
		"\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00db\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\7\t\2\2\u00dc!\3\2\2\2"+
		"\u00dd\u00de\7\n\2\2\u00de\u00e3\5\4\3\2\u00df\u00e0\7\4\2\2\u00e0\u00e2"+
		"\5\4\3\2\u00e1\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3"+
		"\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7\t"+
		"\2\2\u00e7#\3\2\2\2\u00e8\u00ec\5&\24\2\u00e9\u00ec\5*\26\2\u00ea\u00ec"+
		"\5.\30\2\u00eb\u00e8\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec"+
		"%\3\2\2\2\u00ed\u00ee\7\13\2\2\u00ee\u00ef\5\32\16\2\u00ef\u00f0\5(\25"+
		"\2\u00f0\u00f1\7\t\2\2\u00f1\'\3\2\2\2\u00f2\u00f4\5\4\3\2\u00f3\u00f5"+
		"\5\30\r\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5)\3\2\2\2\u00f6"+
		"\u00f7\7\13\2\2\u00f7\u00f8\7\f\2\2\u00f8\u00f9\7\r\2\2\u00f9\u00fe\5"+
		",\27\2\u00fa\u00fb\7\4\2\2\u00fb\u00fd\5,\27\2\u00fc\u00fa\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2"+
		"\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\7\16\2\2\u0102\u0103\5\4\3\2\u0103"+
		"\u0104\7\t\2\2\u0104+\3\2\2\2\u0105\u0108\5\4\3\2\u0106\u0107\7\17\2\2"+
		"\u0107\u0109\7E\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109-\3\2"+
		"\2\2\u010a\u010b\7\13\2\2\u010b\u010c\7\20\2\2\u010c\u0110\7\r\2\2\u010d"+
		"\u010f\5\60\31\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0114\7\16\2\2\u0114\u0115\5(\25\2\u0115\u0116\7\t\2\2\u0116/\3\2\2\2"+
		"\u0117\u0118\5\32\16\2\u0118\u0119\5\2\2\2\u0119\u011a\7\t\2\2\u011a\61"+
		"\3\2\2\2\u011b\u011c\5\32\16\2\u011c\u0121\5\64\33\2\u011d\u011e\7\4\2"+
		"\2\u011e\u0120\5\64\33\2\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0124\u0125\7\t\2\2\u0125\u013c\3\2\2\2\u0126\u0133\7\21\2\2\u0127"+
		"\u0134\5\2\2\2\u0128\u0129\7\r\2\2\u0129\u012e\5\2\2\2\u012a\u012b\7\4"+
		"\2\2\u012b\u012d\5\2\2\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0131\u0132\7\16\2\2\u0132\u0134\3\2\2\2\u0133\u0127\3\2\2\2\u0133"+
		"\u0128\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0136\7\17\2\2\u0136\u0138\5"+
		"L\'\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u013a\7\t\2\2\u013a\u013c\3\2\2\2\u013b\u011b\3\2\2\2\u013b\u0126\3\2"+
		"\2\2\u013c\63\3\2\2\2\u013d\u0140\5\2\2\2\u013e\u013f\7\17\2\2\u013f\u0141"+
		"\5L\'\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\65\3\2\2\2\u0142"+
		"\u0143\7\22\2\2\u0143\u0145\58\35\2\u0144\u0146\5\20\t\2\u0145\u0144\3"+
		"\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014b\7\t\2\2\u0148"+
		"\u014a\5:\36\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2"+
		"\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e"+
		"\u014f\7\23\2\2\u014f\67\3\2\2\2\u0150\u0152\5\4\3\2\u0151\u0153\5\30"+
		"\r\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u01539\3\2\2\2\u0154\u015a"+
		"\5<\37\2\u0155\u015a\5> \2\u0156\u015a\5@!\2\u0157\u015a\5B\"\2\u0158"+
		"\u015a\5^\60\2\u0159\u0154\3\2\2\2\u0159\u0155\3\2\2\2\u0159\u0156\3\2"+
		"\2\2\u0159\u0157\3\2\2\2\u0159\u0158\3\2\2\2\u015a;\3\2\2\2\u015b\u015c"+
		"\5\32\16\2\u015c\u015e\5\2\2\2\u015d\u015f\5\f\7\2\u015e\u015d\3\2\2\2"+
		"\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\7\t\2\2\u0161=\3"+
		"\2\2\2\u0162\u0163\7\24\2\2\u0163\u0164\5\32\16\2\u0164\u0168\5\2\2\2"+
		"\u0165\u0166\7\25\2\2\u0166\u0167\7\17\2\2\u0167\u0169\5L\'\2\u0168\u0165"+
		"\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\7\t\2\2\u016b"+
		"?\3\2\2\2\u016c\u016d\7\26\2\2\u016d\u016e\5\32\16\2\u016e\u0170\5\2\2"+
		"\2\u016f\u0171\5\20\t\2\u0170\u016f\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u017e\3\2\2\2\u0172\u0176\7\t\2\2\u0173\u0175\5^\60\2\u0174\u0173\3\2"+
		"\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017f\7\27\2\2\u017a\u017b\7"+
		"\17\2\2\u017b\u017c\5L\'\2\u017c\u017d\7\t\2\2\u017d\u017f\3\2\2\2\u017e"+
		"\u0172\3\2\2\2\u017e\u017a\3\2\2\2\u017fA\3\2\2\2\u0180\u0181\7\30\2\2"+
		"\u0181\u0182\5\2\2\2\u0182\u0186\7\t\2\2\u0183\u0185\5^\60\2\u0184\u0183"+
		"\3\2\2\2\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0189\3\2\2\2\u0188\u0186\3\2\2\2\u0189\u018a\7\31\2\2\u018aC\3\2\2\2"+
		"\u018b\u018c\7\32\2\2\u018c\u018d\5\32\16\2\u018d\u018f\5F$\2\u018e\u0190"+
		"\5\20\t\2\u018f\u018e\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u019d\3\2\2\2"+
		"\u0191\u0195\7\t\2\2\u0192\u0194\5^\60\2\u0193\u0192\3\2\2\2\u0194\u0197"+
		"\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0198\3\2\2\2\u0197"+
		"\u0195\3\2\2\2\u0198\u019e\7\33\2\2\u0199\u019a\7\17\2\2\u019a\u019b\5"+
		"L\'\2\u019b\u019c\7\t\2\2\u019c\u019e\3\2\2\2\u019d\u0191\3\2\2\2\u019d"+
		"\u0199\3\2\2\2\u019eE\3\2\2\2\u019f\u01a1\5\2\2\2\u01a0\u01a2\5\30\r\2"+
		"\u01a1\u01a0\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2G\3\2\2\2\u01a3\u01a4\5"+
		"J&\2\u01a4\u01a5\7\17\2\2\u01a5\u01a6\5L\'\2\u01a6\u01a7\7\t\2\2\u01a7"+
		"\u01b7\3\2\2\2\u01a8\u01a9\7\r\2\2\u01a9\u01ae\5J&\2\u01aa\u01ab\7\4\2"+
		"\2\u01ab\u01ad\5J&\2\u01ac\u01aa\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac"+
		"\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1"+
		"\u01b2\7\16\2\2\u01b2\u01b3\7\17\2\2\u01b3\u01b4\5L\'\2\u01b4\u01b5\7"+
		"\t\2\2\u01b5\u01b7\3\2\2\2\u01b6\u01a3\3\2\2\2\u01b6\u01a8\3\2\2\2\u01b7"+
		"I\3\2\2\2\u01b8\u01b9\b&\1\2\u01b9\u01ba\5\2\2\2\u01ba\u01cc\3\2\2\2\u01bb"+
		"\u01bc\f\5\2\2\u01bc\u01bd\7\34\2\2\u01bd\u01cb\5\2\2\2\u01be\u01bf\f"+
		"\4\2\2\u01bf\u01c0\7\35\2\2\u01c0\u01c1\5L\'\2\u01c1\u01c2\7\36\2\2\u01c2"+
		"\u01cb\3\2\2\2\u01c3\u01c4\f\3\2\2\u01c4\u01c5\7\35\2\2\u01c5\u01c6\5"+
		"L\'\2\u01c6\u01c7\7\37\2\2\u01c7\u01c8\5L\'\2\u01c8\u01c9\7\36\2\2\u01c9"+
		"\u01cb\3\2\2\2\u01ca\u01bb\3\2\2\2\u01ca\u01be\3\2\2\2\u01ca\u01c3\3\2"+
		"\2\2\u01cb\u01ce\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"K\3\2\2\2\u01ce\u01cc\3\2\2\2\u01cf\u01d0\b\'\1\2\u01d0\u01d1\7!\2\2\u01d1"+
		"\u01d2\7\3\2\2\u01d2\u01d3\5L\'\2\u01d3\u01d7\7\5\2\2\u01d4\u01d6\5N("+
		"\2\u01d5\u01d4\3\2\2\2\u01d6\u01d9\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8"+
		"\3\2\2\2\u01d8\u01da\3\2\2\2\u01d9\u01d7\3\2\2\2\u01da\u01db\7\"\2\2\u01db"+
		"\u01de\3\2\2\2\u01dc\u01de\5P)\2\u01dd\u01cf\3\2\2\2\u01dd\u01dc\3\2\2"+
		"\2\u01de\u01e7\3\2\2\2\u01df\u01e0\f\5\2\2\u01e0\u01e1\7 \2\2\u01e1\u01e2"+
		"\5L\'\2\u01e2\u01e3\7\37\2\2\u01e3\u01e4\5L\'\5\u01e4\u01e6\3\2\2\2\u01e5"+
		"\u01df\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7\u01e8\3\2"+
		"\2\2\u01e8M\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01f4\7\25\2\2\u01eb\u01f0"+
		"\5T+\2\u01ec\u01ed\7\4\2\2\u01ed\u01ef\5T+\2\u01ee\u01ec\3\2\2\2\u01ef"+
		"\u01f2\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f4\3\2"+
		"\2\2\u01f2\u01f0\3\2\2\2\u01f3\u01ea\3\2\2\2\u01f3\u01eb\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f6\7\37\2\2\u01f6\u01f7\5L\'\2\u01f7\u01f8\7\t"+
		"\2\2\u01f8O\3\2\2\2\u01f9\u01fa\b)\1\2\u01fa\u01fb\5R*\2\u01fb\u021c\3"+
		"\2\2\2\u01fc\u01fd\f\r\2\2\u01fd\u01fe\7#\2\2\u01fe\u021b\5P)\16\u01ff"+
		"\u0200\f\f\2\2\u0200\u0201\t\2\2\2\u0201\u021b\5P)\r\u0202\u0203\f\13"+
		"\2\2\u0203\u0204\t\3\2\2\u0204\u021b\5P)\f\u0205\u0206\f\n\2\2\u0206\u0207"+
		"\t\4\2\2\u0207\u021b\5P)\13\u0208\u0209\f\t\2\2\u0209\u020a\t\5\2\2\u020a"+
		"\u021b\5P)\n\u020b\u020c\f\b\2\2\u020c\u020d\t\6\2\2\u020d\u021b\5P)\t"+
		"\u020e\u020f\f\7\2\2\u020f\u0210\t\7\2\2\u0210\u021b\5P)\b\u0211\u0212"+
		"\f\6\2\2\u0212\u0213\7\65\2\2\u0213\u021b\5P)\7\u0214\u0215\f\5\2\2\u0215"+
		"\u0216\7\66\2\2\u0216\u021b\5P)\6\u0217\u0218\f\4\2\2\u0218\u0219\7\67"+
		"\2\2\u0219\u021b\5P)\5\u021a\u01fc\3\2\2\2\u021a\u01ff\3\2\2\2\u021a\u0202"+
		"\3\2\2\2\u021a\u0205\3\2\2\2\u021a\u0208\3\2\2\2\u021a\u020b\3\2\2\2\u021a"+
		"\u020e\3\2\2\2\u021a\u0211\3\2\2\2\u021a\u0214\3\2\2\2\u021a\u0217\3\2"+
		"\2\2\u021b\u021e\3\2\2\2\u021c\u021a\3\2\2\2\u021c\u021d\3\2\2\2\u021d"+
		"Q\3\2\2\2\u021e\u021c\3\2\2\2\u021f\u0220\t\b\2\2\u0220\u0225\5T+\2\u0221"+
		"\u0222\t\3\2\2\u0222\u0225\5T+\2\u0223\u0225\5T+\2\u0224\u021f\3\2\2\2"+
		"\u0224\u0221\3\2\2\2\u0224\u0223\3\2\2\2\u0225S\3\2\2\2\u0226\u0227\b"+
		"+\1\2\u0227\u0228\7\3\2\2\u0228\u0229\5L\'\2\u0229\u022a\7\5\2\2\u022a"+
		"\u0246\3\2\2\2\u022b\u022d\5\b\5\2\u022c\u022e\5\24\13\2\u022d\u022c\3"+
		"\2\2\2\u022d\u022e\3\2\2\2\u022e\u0246\3\2\2\2\u022f\u0246\7E\2\2\u0230"+
		"\u0246\7F\2\2\u0231\u0246\7 \2\2\u0232\u0233\7<\2\2\u0233\u0246\5L\'\2"+
		"\u0234\u0235\7\r\2\2\u0235\u023a\5L\'\2\u0236\u0237\7\4\2\2\u0237\u0239"+
		"\5L\'\2\u0238\u0236\3\2\2\2\u0239\u023c\3\2\2\2\u023a\u0238\3\2\2\2\u023a"+
		"\u023b\3\2\2\2\u023b\u023d\3\2\2\2\u023c\u023a\3\2\2\2\u023d\u023e\7\16"+
		"\2\2\u023e\u0246\3\2\2\2\u023f\u0240\5\32\16\2\u0240\u0241\7\r\2\2\u0241"+
		"\u0242\5V,\2\u0242\u0243\7\16\2\2\u0243\u0246\3\2\2\2\u0244\u0246\5Z."+
		"\2\u0245\u0226\3\2\2\2\u0245\u022b\3\2\2\2\u0245\u022f\3\2\2\2\u0245\u0230"+
		"\3\2\2\2\u0245\u0231\3\2\2\2\u0245\u0232\3\2\2\2\u0245\u0234\3\2\2\2\u0245"+
		"\u023f\3\2\2\2\u0245\u0244\3\2\2\2\u0246\u0262\3\2\2\2\u0247\u0248\f\r"+
		"\2\2\u0248\u0249\7\34\2\2\u0249\u0261\5\6\4\2\u024a\u024b\f\6\2\2\u024b"+
		"\u024c\7\35\2\2\u024c\u024f\5L\'\2\u024d\u024e\7\37\2\2\u024e\u0250\5"+
		"L\'\2\u024f\u024d\3\2\2\2\u024f\u0250\3\2\2\2\u0250\u0251\3\2\2\2\u0251"+
		"\u0252\7\36\2\2\u0252\u0261\3\2\2\2\u0253\u0254\f\5\2\2\u0254\u025d\7"+
		"\3\2\2\u0255\u025a\5L\'\2\u0256\u0257\7\4\2\2\u0257\u0259\5L\'\2\u0258"+
		"\u0256\3\2\2\2\u0259\u025c\3\2\2\2\u025a\u0258\3\2\2\2\u025a\u025b\3\2"+
		"\2\2\u025b\u025e\3\2\2\2\u025c\u025a\3\2\2\2\u025d\u0255\3\2\2\2\u025d"+
		"\u025e\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0261\7\5\2\2\u0260\u0247\3\2"+
		"\2\2\u0260\u024a\3\2\2\2\u0260\u0253\3\2\2\2\u0261\u0264\3\2\2\2\u0262"+
		"\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263U\3\2\2\2\u0264\u0262\3\2\2\2"+
		"\u0265\u026a\5X-\2\u0266\u0267\7\4\2\2\u0267\u0269\5X-\2\u0268\u0266\3"+
		"\2\2\2\u0269\u026c\3\2\2\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2\2\2\u026b"+
		"W\3\2\2\2\u026c\u026a\3\2\2\2\u026d\u026e\5\2\2\2\u026e\u026f\7\37\2\2"+
		"\u026f\u0270\5L\'\2\u0270Y\3\2\2\2\u0271\u0275\7=\2\2\u0272\u0274\5^\60"+
		"\2\u0273\u0272\3\2\2\2\u0274\u0277\3\2\2\2\u0275\u0273\3\2\2\2\u0275\u0276"+
		"\3\2\2\2\u0276\u0278\3\2\2\2\u0277\u0275\3\2\2\2\u0278\u0279\7>\2\2\u0279"+
		"[\3\2\2\2\u027a\u027b\5J&\2\u027b\u027c\7,\2\2\u027c\u027d\5L\'\2\u027d"+
		"]\3\2\2\2\u027e\u028b\5\62\32\2\u027f\u028b\5H%\2\u0280\u0281\5\\/\2\u0281"+
		"\u0282\7\t\2\2\u0282\u028b\3\2\2\2\u0283\u028b\5Z.\2\u0284\u028b\5`\61"+
		"\2\u0285\u028b\5b\62\2\u0286\u028b\5h\65\2\u0287\u0288\5L\'\2\u0288\u0289"+
		"\7\t\2\2\u0289\u028b\3\2\2\2\u028a\u027e\3\2\2\2\u028a\u027f\3\2\2\2\u028a"+
		"\u0280\3\2\2\2\u028a\u0283\3\2\2\2\u028a\u0284\3\2\2\2\u028a\u0285\3\2"+
		"\2\2\u028a\u0286\3\2\2\2\u028a\u0287\3\2\2\2\u028b_\3\2\2\2\u028c\u028d"+
		"\7?\2\2\u028d\u028e\7\3\2\2\u028e\u028f\5L\'\2\u028f\u0290\7\5\2\2\u0290"+
		"\u0293\5^\60\2\u0291\u0292\7@\2\2\u0292\u0294\5^\60\2\u0293\u0291\3\2"+
		"\2\2\u0293\u0294\3\2\2\2\u0294a\3\2\2\2\u0295\u0296\7!\2\2\u0296\u0297"+
		"\7\3\2\2\u0297\u0298\5L\'\2\u0298\u029c\7\5\2\2\u0299\u029b\5d\63\2\u029a"+
		"\u0299\3\2\2\2\u029b\u029e\3\2\2\2\u029c\u029a\3\2\2\2\u029c\u029d\3\2"+
		"\2\2\u029d\u02a0\3\2\2\2\u029e\u029c\3\2\2\2\u029f\u02a1\5f\64\2\u02a0"+
		"\u029f\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2\u02a3\7\""+
		"\2\2\u02a3c\3\2\2\2\u02a4\u02a9\5L\'\2\u02a5\u02a6\7\4\2\2\u02a6\u02a8"+
		"\5L\'\2\u02a7\u02a5\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9\u02a7\3\2\2\2\u02a9"+
		"\u02aa\3\2\2\2\u02aa\u02ac\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ac\u02ad\7\37"+
		"\2\2\u02ad\u02ae\5^\60\2\u02aee\3\2\2\2\u02af\u02b0\7\25\2\2\u02b0\u02b1"+
		"\7\37\2\2\u02b1\u02b2\5^\60\2\u02b2g\3\2\2\2\u02b3\u02b4\7A\2\2\u02b4"+
		"\u02b5\7\3\2\2\u02b5\u02b6\5\32\16\2\u02b6\u02b7\5\2\2\2\u02b7\u02b8\7"+
		"\17\2\2\u02b8\u02b9\5L\'\2\u02b9\u02ba\7\t\2\2\u02ba\u02bb\5L\'\2\u02bb"+
		"\u02bc\7\t\2\2\u02bc\u02bd\5\2\2\2\u02bd\u02be\7\17\2\2\u02be\u02bf\5"+
		"L\'\2\u02bf\u02c0\7\5\2\2\u02c0\u02c1\5^\60\2\u02c1i\3\2\2\2Gpu\177\u0082"+
		"\u008f\u0092\u0098\u00a1\u00a4\u00ae\u00b7\u00ba\u00c0\u00c5\u00d0\u00d8"+
		"\u00e3\u00eb\u00f4\u00fe\u0108\u0110\u0121\u012e\u0133\u0137\u013b\u0140"+
		"\u0145\u014b\u0152\u0159\u015e\u0168\u0170\u0176\u017e\u0186\u018f\u0195"+
		"\u019d\u01a1\u01ae\u01b6\u01ca\u01cc\u01d7\u01dd\u01e7\u01f0\u01f3\u021a"+
		"\u021c\u0224\u022d\u023a\u0245\u024f\u025a\u025d\u0260\u0262\u026a\u0275"+
		"\u028a\u0293\u029c\u02a0\u02a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}