// $ANTLR 3.5.3 myCompiler.g 2023-06-15 22:45:33

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class myCompilerLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__48=48;
	public static final int BRACKET_LEFT=4;
	public static final int BRACKET_RIGHT=5;
	public static final int CHAR=6;
	public static final int COLON=7;
	public static final int COMMA=8;
	public static final int COMMENT=9;
	public static final int CURLY_BRACKET_LEFT=10;
	public static final int CURLY_BRACKET_RIGHT=11;
	public static final int DEVIDE=12;
	public static final int DOT=13;
	public static final int DOUBLE=14;
	public static final int ELSE=15;
	public static final int EQ=16;
	public static final int EQUAL=17;
	public static final int EQ_GE=18;
	public static final int EQ_LE=19;
	public static final int EscapeSequence=20;
	public static final int FLOAT=21;
	public static final int FOR=22;
	public static final int Floating_point_constant=23;
	public static final int GE=24;
	public static final int IF=25;
	public static final int INT=26;
	public static final int Identifier=27;
	public static final int Integer_constant=28;
	public static final int LE=29;
	public static final int LIB=30;
	public static final int LONG=31;
	public static final int MAIN=32;
	public static final int MINUS=33;
	public static final int MOD=34;
	public static final int MULTIPLE=35;
	public static final int NOT_EQ=36;
	public static final int PARENTHESES_LEFT=37;
	public static final int PARENTHESES_RIGHT=38;
	public static final int PLUS=39;
	public static final int PRINTF=40;
	public static final int SEMICOLON=41;
	public static final int SHORT=42;
	public static final int STRING_LITERAL=43;
	public static final int UNSIGNED=44;
	public static final int VOID=45;
	public static final int WHILE=46;
	public static final int WS=47;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public myCompilerLexer() {} 
	public myCompilerLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "myCompiler.g"; }

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:7:7: ( '&' )
			// myCompiler.g:7:9: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:904:6: ( 'float' )
			// myCompiler.g:904:7: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:905:4: ( 'int' )
			// myCompiler.g:905:5: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "CHAR"
	public final void mCHAR() throws RecognitionException {
		try {
			int _type = CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:906:5: ( 'char' )
			// myCompiler.g:906:7: 'char'
			{
			match("char"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:907:8: ( 'double' )
			// myCompiler.g:907:10: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "SHORT"
	public final void mSHORT() throws RecognitionException {
		try {
			int _type = SHORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:908:7: ( 'short' )
			// myCompiler.g:908:9: 'short'
			{
			match("short"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHORT"

	// $ANTLR start "LONG"
	public final void mLONG() throws RecognitionException {
		try {
			int _type = LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:909:6: ( 'long' )
			// myCompiler.g:909:8: 'long'
			{
			match("long"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG"

	// $ANTLR start "UNSIGNED"
	public final void mUNSIGNED() throws RecognitionException {
		try {
			int _type = UNSIGNED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:910:10: ( 'unsigned' )
			// myCompiler.g:910:12: 'unsigned'
			{
			match("unsigned"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNSIGNED"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:912:6: ( '+' )
			// myCompiler.g:912:8: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:913:7: ( '-' )
			// myCompiler.g:913:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "MULTIPLE"
	public final void mMULTIPLE() throws RecognitionException {
		try {
			int _type = MULTIPLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:914:10: ( '*' )
			// myCompiler.g:914:12: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULTIPLE"

	// $ANTLR start "DEVIDE"
	public final void mDEVIDE() throws RecognitionException {
		try {
			int _type = DEVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:915:8: ( '/' )
			// myCompiler.g:915:10: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEVIDE"

	// $ANTLR start "MOD"
	public final void mMOD() throws RecognitionException {
		try {
			int _type = MOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:916:5: ( '%' )
			// myCompiler.g:916:7: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOD"

	// $ANTLR start "EQUAL"
	public final void mEQUAL() throws RecognitionException {
		try {
			int _type = EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:917:6: ( '=' )
			// myCompiler.g:917:8: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:919:7: ( ',' )
			// myCompiler.g:919:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:920:5: ( '.' )
			// myCompiler.g:920:7: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "PARENTHESES_LEFT"
	public final void mPARENTHESES_LEFT() throws RecognitionException {
		try {
			int _type = PARENTHESES_LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:921:18: ( '(' )
			// myCompiler.g:921:20: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARENTHESES_LEFT"

	// $ANTLR start "PARENTHESES_RIGHT"
	public final void mPARENTHESES_RIGHT() throws RecognitionException {
		try {
			int _type = PARENTHESES_RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:922:19: ( ')' )
			// myCompiler.g:922:21: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARENTHESES_RIGHT"

	// $ANTLR start "BRACKET_LEFT"
	public final void mBRACKET_LEFT() throws RecognitionException {
		try {
			int _type = BRACKET_LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:923:14: ( '[' )
			// myCompiler.g:923:16: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRACKET_LEFT"

	// $ANTLR start "BRACKET_RIGHT"
	public final void mBRACKET_RIGHT() throws RecognitionException {
		try {
			int _type = BRACKET_RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:924:15: ( ']' )
			// myCompiler.g:924:17: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRACKET_RIGHT"

	// $ANTLR start "CURLY_BRACKET_LEFT"
	public final void mCURLY_BRACKET_LEFT() throws RecognitionException {
		try {
			int _type = CURLY_BRACKET_LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:925:20: ( '{' )
			// myCompiler.g:925:22: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURLY_BRACKET_LEFT"

	// $ANTLR start "CURLY_BRACKET_RIGHT"
	public final void mCURLY_BRACKET_RIGHT() throws RecognitionException {
		try {
			int _type = CURLY_BRACKET_RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:926:21: ( '}' )
			// myCompiler.g:926:23: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURLY_BRACKET_RIGHT"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:927:7: ( ':' )
			// myCompiler.g:927:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:928:11: ( ';' )
			// myCompiler.g:928:13: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "MAIN"
	public final void mMAIN() throws RecognitionException {
		try {
			int _type = MAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:930:5: ( 'main' )
			// myCompiler.g:930:7: 'main'
			{
			match("main"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MAIN"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:931:5: ( 'void' )
			// myCompiler.g:931:7: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:932:3: ( 'if' )
			// myCompiler.g:932:5: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:933:5: ( 'else' )
			// myCompiler.g:933:7: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:934:4: ( 'for' )
			// myCompiler.g:934:6: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "PRINTF"
	public final void mPRINTF() throws RecognitionException {
		try {
			int _type = PRINTF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:935:7: ( 'printf' )
			// myCompiler.g:935:9: 'printf'
			{
			match("printf"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINTF"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:936:6: ( 'while' )
			// myCompiler.g:936:8: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:938:4: ( '==' )
			// myCompiler.g:938:6: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "LE"
	public final void mLE() throws RecognitionException {
		try {
			int _type = LE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:939:4: ( '<' )
			// myCompiler.g:939:6: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LE"

	// $ANTLR start "EQ_LE"
	public final void mEQ_LE() throws RecognitionException {
		try {
			int _type = EQ_LE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:940:7: ( '<=' )
			// myCompiler.g:940:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ_LE"

	// $ANTLR start "GE"
	public final void mGE() throws RecognitionException {
		try {
			int _type = GE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:941:4: ( '>' )
			// myCompiler.g:941:6: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GE"

	// $ANTLR start "EQ_GE"
	public final void mEQ_GE() throws RecognitionException {
		try {
			int _type = EQ_GE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:942:7: ( '>=' )
			// myCompiler.g:942:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ_GE"

	// $ANTLR start "NOT_EQ"
	public final void mNOT_EQ() throws RecognitionException {
		try {
			int _type = NOT_EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:943:8: ( '!=' )
			// myCompiler.g:943:10: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT_EQ"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:946:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// myCompiler.g:946:12: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// myCompiler.g:946:36: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "Integer_constant"
	public final void mInteger_constant() throws RecognitionException {
		try {
			int _type = Integer_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:947:17: ( ( '0' .. '9' )+ )
			// myCompiler.g:947:18: ( '0' .. '9' )+
			{
			// myCompiler.g:947:18: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Integer_constant"

	// $ANTLR start "Floating_point_constant"
	public final void mFloating_point_constant() throws RecognitionException {
		try {
			int _type = Floating_point_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:948:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
			// myCompiler.g:948:25: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
			{
			// myCompiler.g:948:25: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			match('.'); 
			// myCompiler.g:948:39: ( '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Floating_point_constant"

	// $ANTLR start "LIB"
	public final void mLIB() throws RecognitionException {
		try {
			int _type = LIB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:950:5: ( '#include <' ( . )* '>' )
			// myCompiler.g:950:7: '#include <' ( . )* '>'
			{
			match("#include <"); 

			// myCompiler.g:950:20: ( . )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='>') ) {
					alt5=2;
				}
				else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '=')||(LA5_0 >= '?' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// myCompiler.g:950:20: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop5;
				}
			}

			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LIB"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:953:5: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
			// myCompiler.g:953:8: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
			{
			match('\"'); 
			// myCompiler.g:953:12: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
			loop6:
			while (true) {
				int alt6=3;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='\\') ) {
					alt6=1;
				}
				else if ( ((LA6_0 >= '\u0000' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
					alt6=2;
				}

				switch (alt6) {
				case 1 :
					// myCompiler.g:953:14: EscapeSequence
					{
					mEscapeSequence(); 

					}
					break;
				case 2 :
					// myCompiler.g:953:31: ~ ( '\\\\' | '\"' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:956:3: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// myCompiler.g:956:4: ( ' ' | '\\t' | '\\r' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:957:8: ( '/*' ( . )* '*/' )
			// myCompiler.g:957:9: '/*' ( . )* '*/'
			{
			match("/*"); 

			// myCompiler.g:957:14: ( . )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0=='*') ) {
					int LA7_1 = input.LA(2);
					if ( (LA7_1=='/') ) {
						alt7=2;
					}
					else if ( ((LA7_1 >= '\u0000' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '\uFFFF')) ) {
						alt7=1;
					}

				}
				else if ( ((LA7_0 >= '\u0000' && LA7_0 <= ')')||(LA7_0 >= '+' && LA7_0 <= '\uFFFF')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// myCompiler.g:957:14: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop7;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// myCompiler.g:962:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) )
			// myCompiler.g:962:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
			{
			match('\\'); 
			if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EscapeSequence"

	@Override
	public void mTokens() throws RecognitionException {
		// myCompiler.g:1:8: ( T__48 | FLOAT | INT | CHAR | DOUBLE | SHORT | LONG | UNSIGNED | PLUS | MINUS | MULTIPLE | DEVIDE | MOD | EQUAL | COMMA | DOT | PARENTHESES_LEFT | PARENTHESES_RIGHT | BRACKET_LEFT | BRACKET_RIGHT | CURLY_BRACKET_LEFT | CURLY_BRACKET_RIGHT | COLON | SEMICOLON | MAIN | VOID | IF | ELSE | FOR | PRINTF | WHILE | EQ | LE | EQ_LE | GE | EQ_GE | NOT_EQ | Identifier | Integer_constant | Floating_point_constant | LIB | STRING_LITERAL | WS | COMMENT )
		int alt8=44;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// myCompiler.g:1:10: T__48
				{
				mT__48(); 

				}
				break;
			case 2 :
				// myCompiler.g:1:16: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 3 :
				// myCompiler.g:1:22: INT
				{
				mINT(); 

				}
				break;
			case 4 :
				// myCompiler.g:1:26: CHAR
				{
				mCHAR(); 

				}
				break;
			case 5 :
				// myCompiler.g:1:31: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 6 :
				// myCompiler.g:1:38: SHORT
				{
				mSHORT(); 

				}
				break;
			case 7 :
				// myCompiler.g:1:44: LONG
				{
				mLONG(); 

				}
				break;
			case 8 :
				// myCompiler.g:1:49: UNSIGNED
				{
				mUNSIGNED(); 

				}
				break;
			case 9 :
				// myCompiler.g:1:58: PLUS
				{
				mPLUS(); 

				}
				break;
			case 10 :
				// myCompiler.g:1:63: MINUS
				{
				mMINUS(); 

				}
				break;
			case 11 :
				// myCompiler.g:1:69: MULTIPLE
				{
				mMULTIPLE(); 

				}
				break;
			case 12 :
				// myCompiler.g:1:78: DEVIDE
				{
				mDEVIDE(); 

				}
				break;
			case 13 :
				// myCompiler.g:1:85: MOD
				{
				mMOD(); 

				}
				break;
			case 14 :
				// myCompiler.g:1:89: EQUAL
				{
				mEQUAL(); 

				}
				break;
			case 15 :
				// myCompiler.g:1:95: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 16 :
				// myCompiler.g:1:101: DOT
				{
				mDOT(); 

				}
				break;
			case 17 :
				// myCompiler.g:1:105: PARENTHESES_LEFT
				{
				mPARENTHESES_LEFT(); 

				}
				break;
			case 18 :
				// myCompiler.g:1:122: PARENTHESES_RIGHT
				{
				mPARENTHESES_RIGHT(); 

				}
				break;
			case 19 :
				// myCompiler.g:1:140: BRACKET_LEFT
				{
				mBRACKET_LEFT(); 

				}
				break;
			case 20 :
				// myCompiler.g:1:153: BRACKET_RIGHT
				{
				mBRACKET_RIGHT(); 

				}
				break;
			case 21 :
				// myCompiler.g:1:167: CURLY_BRACKET_LEFT
				{
				mCURLY_BRACKET_LEFT(); 

				}
				break;
			case 22 :
				// myCompiler.g:1:186: CURLY_BRACKET_RIGHT
				{
				mCURLY_BRACKET_RIGHT(); 

				}
				break;
			case 23 :
				// myCompiler.g:1:206: COLON
				{
				mCOLON(); 

				}
				break;
			case 24 :
				// myCompiler.g:1:212: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 25 :
				// myCompiler.g:1:222: MAIN
				{
				mMAIN(); 

				}
				break;
			case 26 :
				// myCompiler.g:1:227: VOID
				{
				mVOID(); 

				}
				break;
			case 27 :
				// myCompiler.g:1:232: IF
				{
				mIF(); 

				}
				break;
			case 28 :
				// myCompiler.g:1:235: ELSE
				{
				mELSE(); 

				}
				break;
			case 29 :
				// myCompiler.g:1:240: FOR
				{
				mFOR(); 

				}
				break;
			case 30 :
				// myCompiler.g:1:244: PRINTF
				{
				mPRINTF(); 

				}
				break;
			case 31 :
				// myCompiler.g:1:251: WHILE
				{
				mWHILE(); 

				}
				break;
			case 32 :
				// myCompiler.g:1:257: EQ
				{
				mEQ(); 

				}
				break;
			case 33 :
				// myCompiler.g:1:260: LE
				{
				mLE(); 

				}
				break;
			case 34 :
				// myCompiler.g:1:263: EQ_LE
				{
				mEQ_LE(); 

				}
				break;
			case 35 :
				// myCompiler.g:1:269: GE
				{
				mGE(); 

				}
				break;
			case 36 :
				// myCompiler.g:1:272: EQ_GE
				{
				mEQ_GE(); 

				}
				break;
			case 37 :
				// myCompiler.g:1:278: NOT_EQ
				{
				mNOT_EQ(); 

				}
				break;
			case 38 :
				// myCompiler.g:1:285: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 39 :
				// myCompiler.g:1:296: Integer_constant
				{
				mInteger_constant(); 

				}
				break;
			case 40 :
				// myCompiler.g:1:313: Floating_point_constant
				{
				mFloating_point_constant(); 

				}
				break;
			case 41 :
				// myCompiler.g:1:337: LIB
				{
				mLIB(); 

				}
				break;
			case 42 :
				// myCompiler.g:1:341: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 43 :
				// myCompiler.g:1:356: WS
				{
				mWS(); 

				}
				break;
			case 44 :
				// myCompiler.g:1:359: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\2\uffff\7\41\3\uffff\1\60\1\uffff\1\62\12\uffff\5\41\1\71\1\73\2\uffff"+
		"\1\74\3\uffff\3\41\1\101\5\41\4\uffff\5\41\6\uffff\1\41\1\115\1\116\1"+
		"\uffff\13\41\2\uffff\1\132\2\41\1\135\1\41\1\137\1\140\1\141\2\41\1\144"+
		"\1\uffff\1\41\1\146\1\uffff\1\41\3\uffff\1\41\1\151\1\uffff\1\152\1\uffff"+
		"\1\41\1\154\2\uffff\1\41\1\uffff\1\156\1\uffff";
	static final String DFA8_eofS =
		"\157\uffff";
	static final String DFA8_minS =
		"\1\11\1\uffff\1\154\1\146\1\150\1\157\1\150\1\157\1\156\3\uffff\1\52\1"+
		"\uffff\1\75\12\uffff\1\141\1\157\1\154\1\162\1\150\2\75\2\uffff\1\56\3"+
		"\uffff\1\157\1\162\1\164\1\60\1\141\1\165\1\157\1\156\1\163\4\uffff\2"+
		"\151\1\163\2\151\6\uffff\1\141\2\60\1\uffff\1\162\1\142\1\162\1\147\1"+
		"\151\1\156\1\144\1\145\1\156\1\154\1\164\2\uffff\1\60\1\154\1\164\1\60"+
		"\1\147\3\60\1\164\1\145\1\60\1\uffff\1\145\1\60\1\uffff\1\156\3\uffff"+
		"\1\146\1\60\1\uffff\1\60\1\uffff\1\145\1\60\2\uffff\1\144\1\uffff\1\60"+
		"\1\uffff";
	static final String DFA8_maxS =
		"\1\175\1\uffff\1\157\1\156\1\150\1\157\1\150\1\157\1\156\3\uffff\1\52"+
		"\1\uffff\1\75\12\uffff\1\141\1\157\1\154\1\162\1\150\2\75\2\uffff\1\71"+
		"\3\uffff\1\157\1\162\1\164\1\172\1\141\1\165\1\157\1\156\1\163\4\uffff"+
		"\2\151\1\163\2\151\6\uffff\1\141\2\172\1\uffff\1\162\1\142\1\162\1\147"+
		"\1\151\1\156\1\144\1\145\1\156\1\154\1\164\2\uffff\1\172\1\154\1\164\1"+
		"\172\1\147\3\172\1\164\1\145\1\172\1\uffff\1\145\1\172\1\uffff\1\156\3"+
		"\uffff\1\146\1\172\1\uffff\1\172\1\uffff\1\145\1\172\2\uffff\1\144\1\uffff"+
		"\1\172\1\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\7\uffff\1\11\1\12\1\13\1\uffff\1\15\1\uffff\1\17\1\20\1\21"+
		"\1\22\1\23\1\24\1\25\1\26\1\27\1\30\7\uffff\1\45\1\46\1\uffff\1\51\1\52"+
		"\1\53\11\uffff\1\54\1\14\1\40\1\16\5\uffff\1\42\1\41\1\44\1\43\1\47\1"+
		"\50\3\uffff\1\33\13\uffff\1\35\1\3\13\uffff\1\4\2\uffff\1\7\1\uffff\1"+
		"\31\1\32\1\34\2\uffff\1\2\1\uffff\1\6\2\uffff\1\37\1\5\1\uffff\1\36\1"+
		"\uffff\1\10";
	static final String DFA8_specialS =
		"\157\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\45\2\uffff\1\45\22\uffff\1\45\1\40\1\44\1\43\1\uffff\1\15\1\1\1\uffff"+
			"\1\21\1\22\1\13\1\11\1\17\1\12\1\20\1\14\12\42\1\27\1\30\1\36\1\16\1"+
			"\37\2\uffff\32\41\1\23\1\uffff\1\24\1\uffff\1\41\1\uffff\2\41\1\4\1\5"+
			"\1\33\1\2\2\41\1\3\2\41\1\7\1\31\2\41\1\34\2\41\1\6\1\41\1\10\1\32\1"+
			"\35\3\41\1\25\1\uffff\1\26",
			"",
			"\1\46\2\uffff\1\47",
			"\1\51\7\uffff\1\50",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"",
			"",
			"",
			"\1\57",
			"",
			"\1\61",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\72",
			"",
			"",
			"\1\75\1\uffff\12\42",
			"",
			"",
			"",
			"\1\76",
			"\1\77",
			"\1\100",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"",
			"",
			"",
			"",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\114",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131",
			"",
			"",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\1\133",
			"\1\134",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\1\136",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"\1\142",
			"\1\143",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"\1\145",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"\1\147",
			"",
			"",
			"",
			"\1\150",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"\1\153",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			"",
			"",
			"\1\155",
			"",
			"\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__48 | FLOAT | INT | CHAR | DOUBLE | SHORT | LONG | UNSIGNED | PLUS | MINUS | MULTIPLE | DEVIDE | MOD | EQUAL | COMMA | DOT | PARENTHESES_LEFT | PARENTHESES_RIGHT | BRACKET_LEFT | BRACKET_RIGHT | CURLY_BRACKET_LEFT | CURLY_BRACKET_RIGHT | COLON | SEMICOLON | MAIN | VOID | IF | ELSE | FOR | PRINTF | WHILE | EQ | LE | EQ_LE | GE | EQ_GE | NOT_EQ | Identifier | Integer_constant | Floating_point_constant | LIB | STRING_LITERAL | WS | COMMENT );";
		}
	}

}
