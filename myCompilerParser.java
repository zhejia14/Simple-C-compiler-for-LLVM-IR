// $ANTLR 3.5.3 myCompiler.g 2023-06-15 22:45:33

    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;
    import java.util.List;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class myCompilerParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BRACKET_LEFT", "BRACKET_RIGHT", 
		"CHAR", "COLON", "COMMA", "COMMENT", "CURLY_BRACKET_LEFT", "CURLY_BRACKET_RIGHT", 
		"DEVIDE", "DOT", "DOUBLE", "ELSE", "EQ", "EQUAL", "EQ_GE", "EQ_LE", "EscapeSequence", 
		"FLOAT", "FOR", "Floating_point_constant", "GE", "IF", "INT", "Identifier", 
		"Integer_constant", "LE", "LIB", "LONG", "MAIN", "MINUS", "MOD", "MULTIPLE", 
		"NOT_EQ", "PARENTHESES_LEFT", "PARENTHESES_RIGHT", "PLUS", "PRINTF", "SEMICOLON", 
		"SHORT", "STRING_LITERAL", "UNSIGNED", "VOID", "WHILE", "WS", "'&'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public myCompilerParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return myCompilerParser.tokenNames; }
	@Override public String getGrammarFileName() { return "myCompiler.g"; }


	    boolean TRACEON = false;
	    int str_count = 0;
	    // Type information.
	    public enum Type{
	       ERR, BOOL, INT, FLOAT, CONST_FLOAT, CHAR, CONST_INT, DOUBLE, SHORT, LONG, UNKNOW, Unsigned, Label;
	    }

	    // This structure is used to record the information of a variable or a constant.
	    class tVar {
		   int   varIndex; // temporary variable's index. Ex: t1, t2, ..., etc.
		   int   iValue;   // value of constant integer. Ex: 123.
		   float fValue;   // value of constant floating point. Ex: 2.314.
		};

	    class Info {
	       Type theType;  // type information.
	       tVar theVar;
		   
		   Info() {
	          theType = Type.ERR;
			    theVar = new tVar();
		   }
	    };

		
	    // ============================================
	    // Create a symbol table.
		 // ArrayList is easy to extend to add more info. into symbol table.
		 //
		 // The structure of symbol table:
		 // <variable ID, [Type, [varIndex or iValue, or fValue]]>
		 //    - type: the variable type   (please check "enum Type")
		 //    - varIndex: the variable's index, ex: t1, t2, ...
		 //    - iValue: value of integer constant.
		 //    - fValue: value of floating-point constant.
	    // ============================================

	    HashMap<String, Info> symtab = new HashMap<String, Info>();

	    // labelCount is used to represent temporary label.
	    // The first index is 0.
	    int labelCount = 0;
	    int counter_temp=0;     
	    // varCount is used to represent temporary variables.
	    // The first index is 0.
	    int varCount = 0;

	    // Record all assembly instructions.
	    List<String> TextCode = new ArrayList<String>();

	    List<Info> Parameterlist = new ArrayList<Info>();
	    /*
	     * Output prologue.
	     */
	    void prologue()
	    {
	       TextCode.add("; === prologue ====");
	       TextCode.add("declare dso_local i32 @printf(i8*, ...)\n");
		   TextCode.add("define dso_local i32 @main()");
		   TextCode.add("{");
	    }
	    
	   
	    /*
	     * Output epilogue.
	     */
	    void epilogue()
	    {
	       /* handle epilogue */
	       TextCode.add("\n; === epilogue ===");
		   TextCode.add("ret i32 0");
	       TextCode.add("}");
	    }
	    
	    
	    /* Generate a new label */
	    String newLabel()
	    {
	       labelCount ++;
	       return (new String("L")) + Integer.toString(labelCount);
	    } 
	    
	    
	    public List<String> getTextCode()
	    {
	       return TextCode;
	    }



	// $ANTLR start "program"
	// myCompiler.g:104:1: program : ( LIB )* VOID MAIN '(' ')' '{' declarations statements '}' ;
	public final void program() throws RecognitionException {
		try {
			// myCompiler.g:104:8: ( ( LIB )* VOID MAIN '(' ')' '{' declarations statements '}' )
			// myCompiler.g:104:9: ( LIB )* VOID MAIN '(' ')' '{' declarations statements '}'
			{
			// myCompiler.g:104:9: ( LIB )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==LIB) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// myCompiler.g:104:10: LIB
					{
					match(input,LIB,FOLLOW_LIB_in_program36); 
					}
					break;

				default :
					break loop1;
				}
			}

			match(input,VOID,FOLLOW_VOID_in_program40); 
			match(input,MAIN,FOLLOW_MAIN_in_program42); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_program44); 
			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_program46); 

			           /* Output function prologue */
			           prologue();
			        
			match(input,CURLY_BRACKET_LEFT,FOLLOW_CURLY_BRACKET_LEFT_in_program67); 
			pushFollow(FOLLOW_declarations_in_program81);
			declarations();
			state._fsp--;

			pushFollow(FOLLOW_statements_in_program94);
			statements();
			state._fsp--;

			match(input,CURLY_BRACKET_RIGHT,FOLLOW_CURLY_BRACKET_RIGHT_in_program104); 

				   if (TRACEON)
				      System.out.println("VOID MAIN () {declarations statements}");

			           /* output function epilogue */	  
			           epilogue();
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "declarations"
	// myCompiler.g:124:1: declarations : ( type Identifier ';' declarations |);
	public final void declarations() throws RecognitionException {
		Token Identifier1=null;
		Type type2 =null;

		try {
			// myCompiler.g:124:13: ( type Identifier ';' declarations |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==CHAR||LA2_0==DOUBLE||LA2_0==FLOAT||LA2_0==INT||LA2_0==LONG||LA2_0==SHORT||(LA2_0 >= UNSIGNED && LA2_0 <= VOID)) ) {
				alt2=1;
			}
			else if ( (LA2_0==CURLY_BRACKET_RIGHT||LA2_0==FOR||LA2_0==IF||LA2_0==Identifier||LA2_0==PRINTF||LA2_0==WHILE) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// myCompiler.g:124:15: type Identifier ';' declarations
					{
					pushFollow(FOLLOW_type_in_declarations131);
					type2=type();
					state._fsp--;

					Identifier1=(Token)match(input,Identifier,FOLLOW_Identifier_in_declarations133); 
					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declarations135); 
					pushFollow(FOLLOW_declarations_in_declarations137);
					declarations();
					state._fsp--;


					           if (TRACEON)
					              System.out.println("declarations: type Identifier : declarations");

					           if (symtab.containsKey((Identifier1!=null?Identifier1.getText():null))) {
					              // variable re-declared.
					              System.out.println("Type Error: " + 
					                                  Identifier1.getLine() + 
					                                 ": Redeclared identifier.");
					              System.exit(0);
					           }
					                 
					           /* Add ID and its info into the symbol table. */
						       Info the_entry = new Info();
							    the_entry.theType = type2;
							    the_entry.theVar.varIndex = varCount;
							    varCount ++;
							    symtab.put((Identifier1!=null?Identifier1.getText():null), the_entry);

					           // issue the instruction.
							   // Ex: %a = alloca i32, align 4
					           if (type2 == Type.INT) { 
					              TextCode.add("%t" + the_entry.theVar.varIndex +" = alloca i32, align 4");
					           }
					           if(type2 == Type.FLOAT){
					               TextCode.add("%t" + the_entry.theVar.varIndex +" = alloca float, align 4");
					           }
					        
					}
					break;
				case 2 :
					// myCompiler.g:154:9: 
					{

					           if (TRACEON)
					              System.out.println("declarations: ");
					        
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declarations"



	// $ANTLR start "type"
	// myCompiler.g:161:1: type returns [Type attr_type] : ( 'int' | 'char' | 'float' | 'double' | 'short' | 'long' | 'void' | 'unsigned' );
	public final Type type() throws RecognitionException {
		Type attr_type = null;


		try {
			// myCompiler.g:163:5: ( 'int' | 'char' | 'float' | 'double' | 'short' | 'long' | 'void' | 'unsigned' )
			int alt3=8;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt3=1;
				}
				break;
			case CHAR:
				{
				alt3=2;
				}
				break;
			case FLOAT:
				{
				alt3=3;
				}
				break;
			case DOUBLE:
				{
				alt3=4;
				}
				break;
			case SHORT:
				{
				alt3=5;
				}
				break;
			case LONG:
				{
				alt3=6;
				}
				break;
			case VOID:
				{
				alt3=7;
				}
				break;
			case UNSIGNED:
				{
				alt3=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// myCompiler.g:163:7: 'int'
					{
					match(input,INT,FOLLOW_INT_in_type194); 
					 if (TRACEON) System.out.println("type: INT"); attr_type =Type.INT; 
					}
					break;
				case 2 :
					// myCompiler.g:164:7: 'char'
					{
					match(input,CHAR,FOLLOW_CHAR_in_type204); 
					 if (TRACEON) System.out.println("type: CHAR"); attr_type =Type.CHAR; 
					}
					break;
				case 3 :
					// myCompiler.g:165:7: 'float'
					{
					match(input,FLOAT,FOLLOW_FLOAT_in_type214); 
					if (TRACEON) System.out.println("type: FLOAT"); attr_type =Type.FLOAT; 
					}
					break;
				case 4 :
					// myCompiler.g:166:7: 'double'
					{
					match(input,DOUBLE,FOLLOW_DOUBLE_in_type224); 
					if (TRACEON) System.out.println("type: DOUBLE");   attr_type =Type.DOUBLE;
					}
					break;
				case 5 :
					// myCompiler.g:167:7: 'short'
					{
					match(input,SHORT,FOLLOW_SHORT_in_type235); 
					if (TRACEON) System.out.println("type: SHORT");   attr_type =Type.SHORT;
					}
					break;
				case 6 :
					// myCompiler.g:168:7: 'long'
					{
					match(input,LONG,FOLLOW_LONG_in_type246); 
					if (TRACEON) System.out.println("type: LONG");   attr_type =Type.LONG;
					}
					break;
				case 7 :
					// myCompiler.g:169:7: 'void'
					{
					match(input,VOID,FOLLOW_VOID_in_type257); 
					if (TRACEON) System.out.println("type: VOID");   attr_type =Type.UNKNOW;
					}
					break;
				case 8 :
					// myCompiler.g:170:7: 'unsigned'
					{
					match(input,UNSIGNED,FOLLOW_UNSIGNED_in_type268); 
					if (TRACEON) System.out.println("type: UNSIGNED");   attr_type =Type.Unsigned;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return attr_type;
	}
	// $ANTLR end "type"



	// $ANTLR start "statements"
	// myCompiler.g:174:1: statements : ( statement statements |);
	public final void statements() throws RecognitionException {
		try {
			// myCompiler.g:174:11: ( statement statements |)
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==FOR||LA4_0==IF||LA4_0==Identifier||LA4_0==PRINTF||LA4_0==WHILE) ) {
				alt4=1;
			}
			else if ( (LA4_0==CURLY_BRACKET_RIGHT) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// myCompiler.g:174:12: statement statements
					{
					pushFollow(FOLLOW_statement_in_statements281);
					statement();
					state._fsp--;

					pushFollow(FOLLOW_statements_in_statements283);
					statements();
					state._fsp--;

					}
					break;
				case 2 :
					// myCompiler.g:176:11: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statements"



	// $ANTLR start "statement"
	// myCompiler.g:179:1: statement : ( assign_stmt ';' | if_stmt | printf_func ';' | func_no_return_stmt ';' | for_stmt | while_loop );
	public final void statement() throws RecognitionException {
		try {
			// myCompiler.g:179:10: ( assign_stmt ';' | if_stmt | printf_func ';' | func_no_return_stmt ';' | for_stmt | while_loop )
			int alt5=6;
			switch ( input.LA(1) ) {
			case Identifier:
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1==EQUAL) ) {
					alt5=1;
				}
				else if ( (LA5_1==PARENTHESES_LEFT) ) {
					alt5=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case IF:
				{
				alt5=2;
				}
				break;
			case PRINTF:
				{
				alt5=3;
				}
				break;
			case FOR:
				{
				alt5=5;
				}
				break;
			case WHILE:
				{
				alt5=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// myCompiler.g:179:12: assign_stmt ';'
					{
					pushFollow(FOLLOW_assign_stmt_in_statement314);
					assign_stmt();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement316); 
					}
					break;
				case 2 :
					// myCompiler.g:180:12: if_stmt
					{
					pushFollow(FOLLOW_if_stmt_in_statement329);
					if_stmt();
					state._fsp--;

					}
					break;
				case 3 :
					// myCompiler.g:181:12: printf_func ';'
					{
					pushFollow(FOLLOW_printf_func_in_statement342);
					printf_func();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement344); 
					}
					break;
				case 4 :
					// myCompiler.g:182:12: func_no_return_stmt ';'
					{
					pushFollow(FOLLOW_func_no_return_stmt_in_statement357);
					func_no_return_stmt();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement359); 
					}
					break;
				case 5 :
					// myCompiler.g:183:12: for_stmt
					{
					pushFollow(FOLLOW_for_stmt_in_statement372);
					for_stmt();
					state._fsp--;

					}
					break;
				case 6 :
					// myCompiler.g:184:12: while_loop
					{
					pushFollow(FOLLOW_while_loop_in_statement385);
					while_loop();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement"



	// $ANTLR start "printf_func"
	// myCompiler.g:187:1: printf_func : PRINTF '(' STRING_LITERAL ( ',' a= arith_expression )* ')' ;
	public final void printf_func() throws RecognitionException {
		Token STRING_LITERAL3=null;
		Info a =null;

		try {
			// myCompiler.g:187:12: ( PRINTF '(' STRING_LITERAL ( ',' a= arith_expression )* ')' )
			// myCompiler.g:187:13: PRINTF '(' STRING_LITERAL ( ',' a= arith_expression )* ')'
			{
			match(input,PRINTF,FOLLOW_PRINTF_in_printf_func401); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_printf_func403); 
			STRING_LITERAL3=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_printf_func405); 
			// myCompiler.g:187:40: ( ',' a= arith_expression )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==COMMA) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// myCompiler.g:187:42: ',' a= arith_expression
					{
					match(input,COMMA,FOLLOW_COMMA_in_printf_func410); 
					pushFollow(FOLLOW_arith_expression_in_printf_func414);
					a=arith_expression();
					state._fsp--;

					Parameterlist.add(a);
					}
					break;

				default :
					break loop6;
				}
			}

			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_printf_func419); 
			  String ss = (STRING_LITERAL3!=null?STRING_LITERAL3.getText():null);
			               ss=ss.substring(1,ss.length()-1);
			               if( ss.contains("%d") || ss.contains("%f")){
			                     int len = ss.length();
			                     if(ss.contains("\\n")){
			                     int index = ss.indexOf("\\n");
			                     while(index!=-1){
			                           index=2+index;
			                           len--;
			                           index=ss.indexOf("\\n",index);
			                        }
			                     }  
			                  ss = ss.replace("\\n","\\0A");
			                  ss = ss+"\\00";
			                  len++;
			                  TextCode.add(2+str_count, "@str" + str_count + " = private unnamed_addr constant [" + len + " x i8] c\"" + ss + "\"");
			                  String pstr = new String();
			                  for(int i=0;i<Parameterlist.size();i++){
			                     if(Parameterlist.get(i).theType == Type.FLOAT){
			                        TextCode.add("%t" + varCount + " = fpext float %t" +Parameterlist.get(i).theVar.varIndex + " to double");
			                        Parameterlist.get(i).theVar.varIndex = varCount;
			                        varCount++;
			                        pstr = pstr + "double %t" + Parameterlist.get(i).theVar.varIndex + ", ";
			                     }
			                     else if(Parameterlist.get(i).theType == Type.INT){
			                        pstr=pstr+"i32 %t" + Parameterlist.get(i).theVar.varIndex + ", ";
			                     }
			                  }
			                  pstr=pstr.substring(0,pstr.length()-2);
			                  TextCode.add("%s" + str_count + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + len + " x i8], [" + len +" x i8]* @str"+ str_count +", i64 0,i64 0), " + pstr + ")");
			                  Parameterlist.clear();
			                  str_count++;
			               }else{
			                  int len = ss.length();
			                  if(ss.contains("\\n")){
			                      int index = ss.indexOf("\\n");
			                     while(index!=-1){
			                        index=2+index;
			                        len--;
			                        index=ss.indexOf("\\n",index);
			                     }
			                  }
			                  ss = ss.replace("\\n","\\0A");
			                  ss = ss+"\\00";
			                  len++;
			                  TextCode.add(2+str_count, "@str" + str_count + " = private unnamed_addr constant [" + len + " x i8] c\"" + ss + "\"");
			                  TextCode.add("%s" + str_count + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + len + " x i8], [" + len +" x i8]* @str"+ str_count +", i64 0,i64 0))");
			                  str_count++;
			               }
			            
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "printf_func"



	// $ANTLR start "for_stmt"
	// myCompiler.g:241:1: for_stmt : FOR '(' assign_stmt ';' a= compare ';' assign_stmt ')' block_stmt ;
	public final void for_stmt() throws RecognitionException {
		Info a =null;

		try {
			// myCompiler.g:241:9: ( FOR '(' assign_stmt ';' a= compare ';' assign_stmt ')' block_stmt )
			// myCompiler.g:241:11: FOR '(' assign_stmt ';' a= compare ';' assign_stmt ')' block_stmt
			{
			match(input,FOR,FOLLOW_FOR_in_for_stmt454); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_for_stmt456); 
			pushFollow(FOLLOW_assign_stmt_in_for_stmt458);
			assign_stmt();
			state._fsp--;

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_for_stmt460); 
			  TextCode.add("br label %" + newLabel());
			                                    TextCode.add("L"+ Integer.toString(labelCount) +":");
			                                 
			pushFollow(FOLLOW_compare_in_for_stmt483);
			a=compare();
			state._fsp--;

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_for_stmt485); 

			                                    TextCode.add("br i1 %t" + a.theVar.varIndex + ", label %" + newLabel() + ", label %" + newLabel());
			                                    TextCode.add(newLabel() + ":");
			                               
			pushFollow(FOLLOW_assign_stmt_in_for_stmt506);
			assign_stmt();
			state._fsp--;


			                       TextCode.add("br label %L" + Integer.toString(labelCount-3));
			                  
			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_for_stmt523); 
			      
			                      TextCode.add("L" + Integer.toString(labelCount-2) + ":");
			                 
			pushFollow(FOLLOW_block_stmt_in_for_stmt544);
			block_stmt();
			state._fsp--;

			    
			                      TextCode.add("br label %L" + Integer.toString(labelCount));
			                      TextCode.add("L" + Integer.toString(labelCount-1) + ":");
			                 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "for_stmt"



	// $ANTLR start "while_loop"
	// myCompiler.g:261:2: while_loop : WHILE '(' a= compare ')' block_stmt ;
	public final void while_loop() throws RecognitionException {
		Info a =null;

		 TextCode.add("br label %" + newLabel());
		        TextCode.add("L" + Integer.toString(labelCount) + ":");
		      
		try {
			// myCompiler.g:265:10: ( WHILE '(' a= compare ')' block_stmt )
			// myCompiler.g:265:12: WHILE '(' a= compare ')' block_stmt
			{
			match(input,WHILE,FOLLOW_WHILE_in_while_loop598); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_while_loop609); 
			pushFollow(FOLLOW_compare_in_while_loop613);
			a=compare();
			state._fsp--;

			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_while_loop615); 

			            TextCode.add("br i1 %t" + a.theVar.varIndex + ", label %" + newLabel() + ", label %" + newLabel());
			            TextCode.add("L"+Integer.toString(labelCount-1) + ":");
			         
			pushFollow(FOLLOW_block_stmt_in_while_loop628);
			block_stmt();
			state._fsp--;


			            TextCode.add("br label %L" + Integer.toString(labelCount-2));
			            TextCode.add("L" + Integer.toString(labelCount) + ":");
			         
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "while_loop"



	// $ANTLR start "if_stmt"
	// myCompiler.g:276:1: if_stmt : if_then_stmt ( if_else_stmt )? ;
	public final void if_stmt() throws RecognitionException {
		try {
			// myCompiler.g:276:8: ( if_then_stmt ( if_else_stmt )? )
			// myCompiler.g:276:10: if_then_stmt ( if_else_stmt )?
			{
			pushFollow(FOLLOW_if_then_stmt_in_if_stmt652);
			if_then_stmt();
			state._fsp--;

			// myCompiler.g:276:23: ( if_else_stmt )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==ELSE) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// myCompiler.g:276:24: if_else_stmt
					{
					pushFollow(FOLLOW_if_else_stmt_in_if_stmt655);
					if_else_stmt();
					state._fsp--;

					}
					break;

			}

			  String label_exit = "L" + Integer.toString(labelCount-1) + ":";
			            if(!TextCode.contains(label_exit)){
			               labelCount--;
			               TextCode.set(counter_temp,"br label %L" + Integer.toString(labelCount));
			            }
			            TextCode.add("L" + Integer.toString(labelCount) + ":");
			         
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "if_stmt"



	// $ANTLR start "if_then_stmt"
	// myCompiler.g:286:1: if_then_stmt returns [Info theInfo] : IF '(' a= compare ')' block_stmt ;
	public final Info if_then_stmt() throws RecognitionException {
		Info theInfo = null;


		Info a =null;

		theInfo = new Info();
		try {
			// myCompiler.g:289:13: ( IF '(' a= compare ')' block_stmt )
			// myCompiler.g:289:17: IF '(' a= compare ')' block_stmt
			{
			match(input,IF,FOLLOW_IF_in_if_then_stmt707); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_if_then_stmt709); 
			pushFollow(FOLLOW_compare_in_if_then_stmt713);
			a=compare();
			state._fsp--;

			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_if_then_stmt715); 

			               TextCode.add("br i1 %t" + a.theVar.varIndex + ", label %" + newLabel() + ", label %" + newLabel());
			               TextCode.add("L" + Integer.toString(labelCount-1) + ":");
			            
			pushFollow(FOLLOW_block_stmt_in_if_then_stmt744);
			block_stmt();
			state._fsp--;

			  
			               TextCode.add("br label %" + newLabel());
			               counter_temp = TextCode.size()-1;
			            
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "if_then_stmt"



	// $ANTLR start "if_else_stmt"
	// myCompiler.g:302:1: if_else_stmt : ELSE block_stmt ;
	public final void if_else_stmt() throws RecognitionException {
		try {
			// myCompiler.g:303:13: ( ELSE block_stmt )
			// myCompiler.g:303:15: ELSE block_stmt
			{
			match(input,ELSE,FOLLOW_ELSE_in_if_else_stmt798); 

			                  TextCode.add("L" + Integer.toString(labelCount-1) + ":");
			               
			pushFollow(FOLLOW_block_stmt_in_if_else_stmt829);
			block_stmt();
			state._fsp--;


			               TextCode.add("br label %L" + Integer.toString(labelCount));
			            
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "if_else_stmt"



	// $ANTLR start "block_stmt"
	// myCompiler.g:314:1: block_stmt : '{' statements '}' ;
	public final void block_stmt() throws RecognitionException {
		try {
			// myCompiler.g:315:6: ( '{' statements '}' )
			// myCompiler.g:315:8: '{' statements '}'
			{
			match(input,CURLY_BRACKET_LEFT,FOLLOW_CURLY_BRACKET_LEFT_in_block_stmt879); 
			pushFollow(FOLLOW_statements_in_block_stmt881);
			statements();
			state._fsp--;

			match(input,CURLY_BRACKET_RIGHT,FOLLOW_CURLY_BRACKET_RIGHT_in_block_stmt883); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "block_stmt"



	// $ANTLR start "assign_stmt"
	// myCompiler.g:319:1: assign_stmt : Identifier '=' arith_expression ;
	public final void assign_stmt() throws RecognitionException {
		Token Identifier5=null;
		Info arith_expression4 =null;

		try {
			// myCompiler.g:319:12: ( Identifier '=' arith_expression )
			// myCompiler.g:319:14: Identifier '=' arith_expression
			{
			Identifier5=(Token)match(input,Identifier,FOLLOW_Identifier_in_assign_stmt896); 
			match(input,EQUAL,FOLLOW_EQUAL_in_assign_stmt898); 
			pushFollow(FOLLOW_arith_expression_in_assign_stmt900);
			arith_expression4=arith_expression();
			state._fsp--;


			                Info theRHS = arith_expression4;
							    Info theLHS = symtab.get((Identifier5!=null?Identifier5.getText():null)); 
					   
			                if ((theLHS.theType == Type.INT) &&
			                    (theRHS.theType == Type.INT)) {		   
			                   // issue store insruction.
			                   // Ex: store i32 %tx, i32* %ty
			                   TextCode.add("store i32 %t" + theRHS.theVar.varIndex + ", i32* %t" + theLHS.theVar.varIndex);
							} else if ((theLHS.theType == Type.INT) &&
							    (theRHS.theType == Type.CONST_INT)) {
			                   // issue store insruction.
			                   // Ex: store i32 value, i32* %ty
			                   TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* %t" + theLHS.theVar.varIndex);				
							} else if((theLHS.theType == Type.FLOAT) &&
			                    (theRHS.theType == Type.CONST_FLOAT)){
			                     double temp = theRHS.theVar.fValue;
			                     long temp2 = Double.doubleToLongBits(temp);
			                     TextCode.add("store float 0x" + Long.toHexString(temp2) + ", ptr %t" + theLHS.theVar.varIndex );
			            }else if((theLHS.theType == Type.FLOAT) &&
			                    (theRHS.theType == Type.FLOAT)){
			                     TextCode.add("store float %t" + theRHS.theVar.varIndex + ", ptr %t" + theLHS.theVar.varIndex + " align 4");
			                    }
						 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "assign_stmt"



	// $ANTLR start "func_no_return_stmt"
	// myCompiler.g:347:1: func_no_return_stmt : Identifier '(' argument ')' ;
	public final void func_no_return_stmt() throws RecognitionException {
		try {
			// myCompiler.g:347:20: ( Identifier '(' argument ')' )
			// myCompiler.g:347:22: Identifier '(' argument ')'
			{
			match(input,Identifier,FOLLOW_Identifier_in_func_no_return_stmt942); 
			match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_func_no_return_stmt944); 
			pushFollow(FOLLOW_argument_in_func_no_return_stmt946);
			argument();
			state._fsp--;

			match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_func_no_return_stmt948); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "func_no_return_stmt"



	// $ANTLR start "argument"
	// myCompiler.g:351:1: argument : arg ( ',' arg )* ;
	public final void argument() throws RecognitionException {
		try {
			// myCompiler.g:351:9: ( arg ( ',' arg )* )
			// myCompiler.g:351:11: arg ( ',' arg )*
			{
			pushFollow(FOLLOW_arg_in_argument976);
			arg();
			state._fsp--;

			// myCompiler.g:351:15: ( ',' arg )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==COMMA) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// myCompiler.g:351:16: ',' arg
					{
					match(input,COMMA,FOLLOW_COMMA_in_argument979); 
					pushFollow(FOLLOW_arg_in_argument981);
					arg();
					state._fsp--;

					}
					break;

				default :
					break loop8;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "argument"



	// $ANTLR start "arg"
	// myCompiler.g:354:1: arg : ( arith_expression | STRING_LITERAL );
	public final void arg() throws RecognitionException {
		try {
			// myCompiler.g:354:4: ( arith_expression | STRING_LITERAL )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==Floating_point_constant||(LA9_0 >= Identifier && LA9_0 <= Integer_constant)||LA9_0==MINUS||LA9_0==PARENTHESES_LEFT||LA9_0==48) ) {
				alt9=1;
			}
			else if ( (LA9_0==STRING_LITERAL) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// myCompiler.g:354:6: arith_expression
					{
					pushFollow(FOLLOW_arith_expression_in_arg999);
					arith_expression();
					state._fsp--;

					}
					break;
				case 2 :
					// myCompiler.g:355:6: STRING_LITERAL
					{
					match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_arg1006); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "arg"



	// $ANTLR start "compare"
	// myCompiler.g:358:1: compare returns [Info theInfo] : a= arith_expression ( '<' b= arith_expression | '>' b= arith_expression | '<=' b= arith_expression | '>=' b= arith_expression | '==' b= arith_expression | '!=' b= arith_expression )* ;
	public final Info compare() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// myCompiler.g:361:9: (a= arith_expression ( '<' b= arith_expression | '>' b= arith_expression | '<=' b= arith_expression | '>=' b= arith_expression | '==' b= arith_expression | '!=' b= arith_expression )* )
			// myCompiler.g:361:11: a= arith_expression ( '<' b= arith_expression | '>' b= arith_expression | '<=' b= arith_expression | '>=' b= arith_expression | '==' b= arith_expression | '!=' b= arith_expression )*
			{
			pushFollow(FOLLOW_arith_expression_in_compare1037);
			a=arith_expression();
			state._fsp--;

			theInfo = a;
			// myCompiler.g:362:9: ( '<' b= arith_expression | '>' b= arith_expression | '<=' b= arith_expression | '>=' b= arith_expression | '==' b= arith_expression | '!=' b= arith_expression )*
			loop10:
			while (true) {
				int alt10=7;
				switch ( input.LA(1) ) {
				case LE:
					{
					alt10=1;
					}
					break;
				case GE:
					{
					alt10=2;
					}
					break;
				case EQ_LE:
					{
					alt10=3;
					}
					break;
				case EQ_GE:
					{
					alt10=4;
					}
					break;
				case EQ:
					{
					alt10=5;
					}
					break;
				case NOT_EQ:
					{
					alt10=6;
					}
					break;
				}
				switch (alt10) {
				case 1 :
					// myCompiler.g:362:11: '<' b= arith_expression
					{
					match(input,LE,FOLLOW_LE_in_compare1051); 
					pushFollow(FOLLOW_arith_expression_in_compare1055);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : <");
					                                     if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp slt i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp slt i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp olt float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp olt double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }                
					                                 
					}
					break;
				case 2 :
					// myCompiler.g:397:10: '>' b= arith_expression
					{
					match(input,GE,FOLLOW_GE_in_compare1068); 
					pushFollow(FOLLOW_arith_expression_in_compare1072);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : >");
					                                       if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp sgt i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp sgt i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp ogt float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp ogt double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }      
					                                    
					}
					break;
				case 3 :
					// myCompiler.g:432:10: '<=' b= arith_expression
					{
					match(input,EQ_LE,FOLLOW_EQ_LE_in_compare1085); 
					pushFollow(FOLLOW_arith_expression_in_compare1089);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : <=");
					                                        if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp sle i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp sle i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp ole float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp ole double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }                      
					                                 
					}
					break;
				case 4 :
					// myCompiler.g:467:10: '>=' b= arith_expression
					{
					match(input,EQ_GE,FOLLOW_EQ_GE_in_compare1102); 
					pushFollow(FOLLOW_arith_expression_in_compare1106);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : >=");
					                                       if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp sge i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp sge i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp oge float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp oge double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }                             
					                                 
					}
					break;
				case 5 :
					// myCompiler.g:502:10: '==' b= arith_expression
					{
					match(input,EQ,FOLLOW_EQ_in_compare1119); 
					pushFollow(FOLLOW_arith_expression_in_compare1123);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : ==");
					                                       if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp eq i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp eq i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp oeq float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp oeq double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }      
					                                 
					}
					break;
				case 6 :
					// myCompiler.g:537:10: '!=' b= arith_expression
					{
					match(input,NOT_EQ,FOLLOW_NOT_EQ_in_compare1136); 
					pushFollow(FOLLOW_arith_expression_in_compare1140);
					b=arith_expression();
					state._fsp--;

					if (TRACEON) System.out.println("compare : !=");
					                                       if((a.theType == Type.INT) &&
					                                        (b.theType == Type.INT)){
					                                             TextCode.add("%t" + varCount + " = icmp ne i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                             
					                                             theInfo.theType = Type.BOOL;
										                              theInfo.theVar.varIndex = varCount;
										                              varCount ++;
					                                       }
					                                       else if((a.theType == Type.INT) &&
										                                 (b.theType == Type.CONST_INT)){
					                                                TextCode.add("%t" + varCount + " = icmp ne i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					                                                theInfo.theType = Type.BOOL;
										                                 theInfo.theVar.varIndex = varCount;
										                                 varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.FLOAT)){
					                                                 TextCode.add("%t" + varCount + " = fcmp one float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                                                 theInfo.theType = Type.BOOL;
										                                  theInfo.theVar.varIndex = varCount;
										                                  varCount ++;
					                                       }else if((a.theType == Type.FLOAT) &&
					                                               (b.theType == Type.CONST_FLOAT)){
					                                                   double temp = b.theVar.fValue;
					                                                   long temp2 = Double.doubleToLongBits(temp);
					                                                   TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                                                   theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                                   TextCode.add("%t" + varCount + " = fcmp one double %t" + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                                   theInfo.theType = Type.BOOL;
										                                    theInfo.theVar.varIndex = varCount;
										                                    varCount ++;
					                                               }      
					                                  
					}
					break;

				default :
					break loop10;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "compare"



	// $ANTLR start "arith_expression"
	// myCompiler.g:576:1: arith_expression returns [Info theInfo] : a= multExpr ( '+' b= multExpr | '-' b= multExpr )* ;
	public final Info arith_expression() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// myCompiler.g:579:17: (a= multExpr ( '+' b= multExpr | '-' b= multExpr )* )
			// myCompiler.g:579:19: a= multExpr ( '+' b= multExpr | '-' b= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_arith_expression1198);
			a=multExpr();
			state._fsp--;

			 theInfo =a; 
			// myCompiler.g:580:18: ( '+' b= multExpr | '-' b= multExpr )*
			loop11:
			while (true) {
				int alt11=3;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==PLUS) ) {
					alt11=1;
				}
				else if ( (LA11_0==MINUS) ) {
					alt11=2;
				}

				switch (alt11) {
				case 1 :
					// myCompiler.g:580:20: '+' b= multExpr
					{
					match(input,PLUS,FOLLOW_PLUS_in_arith_expression1221); 
					pushFollow(FOLLOW_multExpr_in_arith_expression1225);
					b=multExpr();
					state._fsp--;


					                       // int + code generation.					   
					                       if ((a.theType == Type.INT) &&
					                           (b.theType == Type.INT)) {
					                           TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       } else if ((a.theType == Type.INT) &&
										       (b.theType == Type.CONST_INT)) {
					                           TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       }else if((a.theType == Type.CONST_INT) &&
					                               (b.theType == Type.CONST_INT)) {
					                           theInfo.theVar.iValue = theInfo.theVar.iValue + b.theVar.iValue;
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.CONST_INT;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.FLOAT)){
					                           TextCode.add("%t" + varCount + " = fadd float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                           theInfo.theType = Type.FLOAT;
										            theInfo.theVar.varIndex = varCount;
										            varCount ++;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 double temp = b.theVar.fValue;
					                                 long temp2 = Double.doubleToLongBits(temp);
					                                 TextCode.add("%t" + varCount + " = fpext float " + theInfo.theVar.varIndex +" to double" );
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fadd double " + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fptrunc double " + theInfo.theVar.varIndex + " to float");
					                                 theInfo.theType = Type.FLOAT;
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                       }else if((a.theType == Type.CONST_FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 theInfo.theVar.fValue = theInfo.theVar.fValue + b.theVar.fValue;
					                                  theInfo.theType = Type.CONST_FLOAT;
					                       }

					                    
					}
					break;
				case 2 :
					// myCompiler.g:632:20: '-' b= multExpr
					{
					match(input,MINUS,FOLLOW_MINUS_in_arith_expression1268); 
					pushFollow(FOLLOW_multExpr_in_arith_expression1272);
					b=multExpr();
					state._fsp--;


					                        // int - code generation.					   
					                       if ((a.theType == Type.INT) &&
					                           (b.theType == Type.INT)) {
					                           TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       } else if ((a.theType == Type.INT) &&
										       (b.theType == Type.CONST_INT)) {
					                           TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       }else if((a.theType == Type.CONST_INT) &&
					                               (b.theType == Type.CONST_INT)) {
					                          theInfo.theVar.iValue = theInfo.theVar.iValue - b.theVar.iValue;
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.CONST_INT;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.FLOAT)){
					                           TextCode.add("%t" + varCount + " = fsub float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                           theInfo.theType = Type.FLOAT;
										            theInfo.theVar.varIndex = varCount;
										            varCount ++;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 double temp = b.theVar.fValue;
					                                 long temp2 = Double.doubleToLongBits(temp);
					                                 TextCode.add("%t" + varCount + " = fpext float " + theInfo.theVar.varIndex +" to double" );
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fsub double " + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fptrunc double " + theInfo.theVar.varIndex + " to float");
					                                 theInfo.theType = Type.FLOAT;
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                       }else if((a.theType == Type.CONST_FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 theInfo.theVar.fValue = theInfo.theVar.fValue - b.theVar.fValue;
					                                  theInfo.theType = Type.CONST_FLOAT;
					                       }
					                     
					}
					break;

				default :
					break loop11;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "arith_expression"



	// $ANTLR start "multExpr"
	// myCompiler.g:686:1: multExpr returns [Info theInfo] : a= signExpr ( '*' b= signExpr | '/' b= signExpr | MOD b= signExpr )* ;
	public final Info multExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// myCompiler.g:689:11: (a= signExpr ( '*' b= signExpr | '/' b= signExpr | MOD b= signExpr )* )
			// myCompiler.g:689:13: a= signExpr ( '*' b= signExpr | '/' b= signExpr | MOD b= signExpr )*
			{
			pushFollow(FOLLOW_signExpr_in_multExpr1362);
			a=signExpr();
			state._fsp--;

			 theInfo =a; 
			// myCompiler.g:690:11: ( '*' b= signExpr | '/' b= signExpr | MOD b= signExpr )*
			loop12:
			while (true) {
				int alt12=4;
				switch ( input.LA(1) ) {
				case MULTIPLE:
					{
					alt12=1;
					}
					break;
				case DEVIDE:
					{
					alt12=2;
					}
					break;
				case MOD:
					{
					alt12=3;
					}
					break;
				}
				switch (alt12) {
				case 1 :
					// myCompiler.g:690:13: '*' b= signExpr
					{
					match(input,MULTIPLE,FOLLOW_MULTIPLE_in_multExpr1378); 
					pushFollow(FOLLOW_signExpr_in_multExpr1382);
					b=signExpr();
					state._fsp--;


					               // int * code generation.					   
					                       if ((a.theType == Type.INT) &&
					                           (b.theType == Type.INT)) {
					                           TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.INT;
					                           theInfo.theVar.varIndex = varCount;
					                           varCount ++;
					                       } else if ((a.theType == Type.INT) &&
										                  (b.theType == Type.CONST_INT)) {
					                              
					                              TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
					                               // Update arith_expression's theInfo.
					                              theInfo.theType = Type.INT;
					                              theInfo.theVar.varIndex = varCount;
					                              varCount ++;
					                       }else if((a.theType == Type.CONST_INT) &&
					                               (b.theType == Type.CONST_INT)) {
					                           theInfo.theVar.iValue = theInfo.theVar.iValue * b.theVar.iValue;
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.CONST_INT;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.FLOAT)){
					                           TextCode.add("%t" + varCount + " = fmul float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                           theInfo.theType = Type.FLOAT;
										            theInfo.theVar.varIndex = varCount;
										            varCount ++;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 double temp = b.theVar.fValue;
					                                 long temp2 = Double.doubleToLongBits(temp);
					                                 TextCode.add("%t" + varCount + " = fpext float " + theInfo.theVar.varIndex +" to double" );
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fmul double " + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fptrunc double " + theInfo.theVar.varIndex + " to float");
					                                 theInfo.theType = Type.FLOAT;
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                       }else if((a.theType == Type.CONST_FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 theInfo.theVar.fValue = theInfo.theVar.fValue * b.theVar.fValue;
					                                  theInfo.theType = Type.CONST_FLOAT;
					                       }
					             
					}
					break;
				case 2 :
					// myCompiler.g:741:13: '/' b= signExpr
					{
					match(input,DEVIDE,FOLLOW_DEVIDE_in_multExpr1411); 
					pushFollow(FOLLOW_signExpr_in_multExpr1415);
					b=signExpr();
					state._fsp--;


					               // int / code generation.					   
					                       if ((a.theType == Type.INT) &&
					                           (b.theType == Type.INT)) {
					                           TextCode.add("%t" + varCount + " = sdiv nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       } else if ((a.theType == Type.INT) &&
										       (b.theType == Type.CONST_INT)) {
					                           TextCode.add("%t" + varCount + " = sdiv nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       }else if((a.theType == Type.CONST_INT) &&
					                               (b.theType == Type.CONST_INT)) {
					                           theInfo.theVar.iValue = theInfo.theVar.iValue / b.theVar.iValue;
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.CONST_INT;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.FLOAT)){
					                           TextCode.add("%t" + varCount + " = fdiv float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                           theInfo.theType = Type.FLOAT;
										            theInfo.theVar.varIndex = varCount;
										            varCount ++;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 double temp = b.theVar.fValue;
					                                 long temp2 = Double.doubleToLongBits(temp);
					                                 TextCode.add("%t" + varCount + " = fpext float " + theInfo.theVar.varIndex +" to double" );
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fdiv double " + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fptrunc double " + theInfo.theVar.varIndex + " to float");
					                                 theInfo.theType = Type.FLOAT;
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                       }else if((a.theType == Type.CONST_FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 theInfo.theVar.fValue = theInfo.theVar.fValue / b.theVar.fValue;
					                                  theInfo.theType = Type.CONST_FLOAT;
					                       }
					             
					}
					break;
				case 3 :
					// myCompiler.g:792:12: MOD b= signExpr
					{
					match(input,MOD,FOLLOW_MOD_in_multExpr1443); 
					pushFollow(FOLLOW_signExpr_in_multExpr1447);
					b=signExpr();
					state._fsp--;


					               // int mod code generation.					   
					                       if ((a.theType == Type.INT) &&
					                           (b.theType == Type.INT)) {
					                           TextCode.add("%t" + varCount + " = srem nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       } else if ((a.theType == Type.INT) &&
										       (b.theType == Type.CONST_INT)) {
					                           TextCode.add("%t" + varCount + " = srem nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
										   
										       // Update arith_expression's theInfo.
										       theInfo.theType = Type.INT;
										       theInfo.theVar.varIndex = varCount;
										       varCount ++;
					                       }else if((a.theType == Type.CONST_INT) &&
					                               (b.theType == Type.CONST_INT)) {
					                           theInfo.theVar.iValue = theInfo.theVar.iValue % b.theVar.iValue;
										   
					                           // Update arith_expression's theInfo.
					                           theInfo.theType = Type.CONST_INT;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.FLOAT)){
					                           TextCode.add("%t" + varCount + " = frem float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					                           theInfo.theType = Type.FLOAT;
										            theInfo.theVar.varIndex = varCount;
										            varCount ++;
					                       }else if((a.theType == Type.FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 double temp = b.theVar.fValue;
					                                 long temp2 = Double.doubleToLongBits(temp);
					                                 TextCode.add("%t" + varCount + " = fpext float " + theInfo.theVar.varIndex +" to double" );
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = frem double " + theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                                 TextCode.add("%t" + varCount + " = fptrunc double " + theInfo.theVar.varIndex + " to float");
					                                 theInfo.theType = Type.FLOAT;
					                                 theInfo.theVar.varIndex = varCount;
					                                 varCount++;
					                       }else if((a.theType == Type.CONST_FLOAT) &&
					                               (b.theType == Type.CONST_FLOAT)){
					                                 theInfo.theVar.fValue = theInfo.theVar.fValue % b.theVar.fValue;
					                                  theInfo.theType = Type.CONST_FLOAT;
					                       }
					            
					}
					break;

				default :
					break loop12;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "multExpr"



	// $ANTLR start "signExpr"
	// myCompiler.g:846:1: signExpr returns [Info theInfo] : (a= primaryExpr | '-' primaryExpr );
	public final Info signExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;

		theInfo = new Info();
		try {
			// myCompiler.g:849:9: (a= primaryExpr | '-' primaryExpr )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==Floating_point_constant||(LA13_0 >= Identifier && LA13_0 <= Integer_constant)||LA13_0==PARENTHESES_LEFT||LA13_0==48) ) {
				alt13=1;
			}
			else if ( (LA13_0==MINUS) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// myCompiler.g:849:11: a= primaryExpr
					{
					pushFollow(FOLLOW_primaryExpr_in_signExpr1498);
					a=primaryExpr();
					state._fsp--;

					 theInfo =a; 
					}
					break;
				case 2 :
					// myCompiler.g:850:11: '-' primaryExpr
					{
					match(input,MINUS,FOLLOW_MINUS_in_signExpr1513); 
					pushFollow(FOLLOW_primaryExpr_in_signExpr1515);
					primaryExpr();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "signExpr"



	// $ANTLR start "primaryExpr"
	// myCompiler.g:853:1: primaryExpr returns [Info theInfo] : ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' a= arith_expression ')' );
	public final Info primaryExpr() throws RecognitionException {
		Info theInfo = null;


		Token Integer_constant6=null;
		Token Floating_point_constant7=null;
		Token Identifier8=null;
		Info a =null;

		theInfo = new Info();
		try {
			// myCompiler.g:856:12: ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' a= arith_expression ')' )
			int alt14=5;
			switch ( input.LA(1) ) {
			case Integer_constant:
				{
				alt14=1;
				}
				break;
			case Floating_point_constant:
				{
				alt14=2;
				}
				break;
			case Identifier:
				{
				alt14=3;
				}
				break;
			case 48:
				{
				alt14=4;
				}
				break;
			case PARENTHESES_LEFT:
				{
				alt14=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// myCompiler.g:856:14: Integer_constant
					{
					Integer_constant6=(Token)match(input,Integer_constant,FOLLOW_Integer_constant_in_primaryExpr1550); 

					                   theInfo.theType = Type.CONST_INT;
					                   theInfo.theVar.iValue = Integer.parseInt((Integer_constant6!=null?Integer_constant6.getText():null));
					               
					}
					break;
				case 2 :
					// myCompiler.g:861:14: Floating_point_constant
					{
					Floating_point_constant7=(Token)match(input,Floating_point_constant,FOLLOW_Floating_point_constant_in_primaryExpr1582); 

					                   theInfo.theType = Type.CONST_FLOAT;
					                   theInfo.theVar.fValue = Float.parseFloat((Floating_point_constant7!=null?Floating_point_constant7.getText():null));
					               
					}
					break;
				case 3 :
					// myCompiler.g:866:14: Identifier
					{
					Identifier8=(Token)match(input,Identifier,FOLLOW_Identifier_in_primaryExpr1614); 

					                // get type information from symtab.
					                Type the_type = symtab.get((Identifier8!=null?Identifier8.getText():null)).theType;
									    theInfo.theType = the_type;

					                // get variable index from symtab.
					                int vIndex = symtab.get((Identifier8!=null?Identifier8.getText():null)).theVar.varIndex;
									
					                switch (the_type) {
					                case INT: 
					                         // get a new temporary variable and
											       // load the variable into the temporary variable.
					                         
											       // Ex: %tx = load i32, i32* %ty.
											       TextCode.add("%t" + varCount + " = load i32, i32* %t" + vIndex);
									         
											       // Now, Identifier's value is at the temporary variable %t[varCount].
											       // Therefore, update it.
											       theInfo.theVar.varIndex = varCount;
											       varCount ++;
					                         break;
					                case FLOAT:
					                         TextCode.add("%t" + varCount + " = load float, ptr %t" + vIndex);
					                         theInfo.theVar.varIndex = varCount;
											       varCount ++;
					                         break;
					                case CHAR:
					                         break;
								
					                }
					              
					}
					break;
				case 4 :
					// myCompiler.g:898:7: '&' Identifier
					{
					match(input,48,FOLLOW_48_in_primaryExpr1639); 
					match(input,Identifier,FOLLOW_Identifier_in_primaryExpr1641); 
					}
					break;
				case 5 :
					// myCompiler.g:899:7: '(' a= arith_expression ')'
					{
					match(input,PARENTHESES_LEFT,FOLLOW_PARENTHESES_LEFT_in_primaryExpr1649); 
					pushFollow(FOLLOW_arith_expression_in_primaryExpr1653);
					a=arith_expression();
					state._fsp--;

					match(input,PARENTHESES_RIGHT,FOLLOW_PARENTHESES_RIGHT_in_primaryExpr1655); 
					 theInfo = a;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "primaryExpr"

	// Delegated rules



	public static final BitSet FOLLOW_LIB_in_program36 = new BitSet(new long[]{0x0000200040000000L});
	public static final BitSet FOLLOW_VOID_in_program40 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_MAIN_in_program42 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_program44 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_program46 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_CURLY_BRACKET_LEFT_in_program67 = new BitSet(new long[]{0x000075008E604840L});
	public static final BitSet FOLLOW_declarations_in_program81 = new BitSet(new long[]{0x000041000A400800L});
	public static final BitSet FOLLOW_statements_in_program94 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_CURLY_BRACKET_RIGHT_in_program104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_declarations131 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_Identifier_in_declarations133 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_declarations135 = new BitSet(new long[]{0x0000340084204040L});
	public static final BitSet FOLLOW_declarations_in_declarations137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_type194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_in_type204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_type214 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_in_type224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_in_type235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_in_type246 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_type257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UNSIGNED_in_type268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statements281 = new BitSet(new long[]{0x000041000A400000L});
	public static final BitSet FOLLOW_statements_in_statements283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assign_stmt_in_statement314 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_statement316 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_stmt_in_statement329 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_printf_func_in_statement342 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_statement344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_no_return_stmt_in_statement357 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_statement359 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_stmt_in_statement372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_loop_in_statement385 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINTF_in_printf_func401 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_printf_func403 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_printf_func405 = new BitSet(new long[]{0x0000004000000100L});
	public static final BitSet FOLLOW_COMMA_in_printf_func410 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_printf_func414 = new BitSet(new long[]{0x0000004000000100L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_printf_func419 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_for_stmt454 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_for_stmt456 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_assign_stmt_in_for_stmt458 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_for_stmt460 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_compare_in_for_stmt483 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_for_stmt485 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_assign_stmt_in_for_stmt506 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_for_stmt523 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_block_stmt_in_for_stmt544 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_while_loop598 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_while_loop609 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_compare_in_while_loop613 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_while_loop615 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_block_stmt_in_while_loop628 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_then_stmt_in_if_stmt652 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_if_else_stmt_in_if_stmt655 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_if_then_stmt707 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_if_then_stmt709 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_compare_in_if_then_stmt713 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_if_then_stmt715 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_block_stmt_in_if_then_stmt744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_if_else_stmt798 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_block_stmt_in_if_else_stmt829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CURLY_BRACKET_LEFT_in_block_stmt879 = new BitSet(new long[]{0x000041000A400800L});
	public static final BitSet FOLLOW_statements_in_block_stmt881 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_CURLY_BRACKET_RIGHT_in_block_stmt883 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_assign_stmt896 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_EQUAL_in_assign_stmt898 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_assign_stmt900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_func_no_return_stmt942 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_func_no_return_stmt944 = new BitSet(new long[]{0x0001082218800000L});
	public static final BitSet FOLLOW_argument_in_func_no_return_stmt946 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_func_no_return_stmt948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arg_in_argument976 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_COMMA_in_argument979 = new BitSet(new long[]{0x0001082218800000L});
	public static final BitSet FOLLOW_arg_in_argument981 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_arith_expression_in_arg999 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_arg1006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arith_expression_in_compare1037 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_LE_in_compare1051 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1055 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_GE_in_compare1068 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1072 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_EQ_LE_in_compare1085 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1089 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_EQ_GE_in_compare1102 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1106 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_EQ_in_compare1119 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1123 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_NOT_EQ_in_compare1136 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_compare1140 = new BitSet(new long[]{0x00000010210D0002L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression1198 = new BitSet(new long[]{0x0000008200000002L});
	public static final BitSet FOLLOW_PLUS_in_arith_expression1221 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression1225 = new BitSet(new long[]{0x0000008200000002L});
	public static final BitSet FOLLOW_MINUS_in_arith_expression1268 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression1272 = new BitSet(new long[]{0x0000008200000002L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1362 = new BitSet(new long[]{0x0000000C00001002L});
	public static final BitSet FOLLOW_MULTIPLE_in_multExpr1378 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1382 = new BitSet(new long[]{0x0000000C00001002L});
	public static final BitSet FOLLOW_DEVIDE_in_multExpr1411 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1415 = new BitSet(new long[]{0x0000000C00001002L});
	public static final BitSet FOLLOW_MOD_in_multExpr1443 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1447 = new BitSet(new long[]{0x0000000C00001002L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr1498 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_signExpr1513 = new BitSet(new long[]{0x0001002018800000L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr1515 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Integer_constant_in_primaryExpr1550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Floating_point_constant_in_primaryExpr1582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr1614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_48_in_primaryExpr1639 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr1641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARENTHESES_LEFT_in_primaryExpr1649 = new BitSet(new long[]{0x0001002218800000L});
	public static final BitSet FOLLOW_arith_expression_in_primaryExpr1653 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_PARENTHESES_RIGHT_in_primaryExpr1655 = new BitSet(new long[]{0x0000000000000002L});
}
