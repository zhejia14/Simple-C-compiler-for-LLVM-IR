grammar myCompiler;

options {
   language = Java;
}

@header {
    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;
    import java.util.List;
}

@members {
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
}

program:(LIB)* VOID MAIN '(' ')'
        {
           /* Output function prologue */
           prologue();
        }

        '{' 
           declarations
           statements
        '}'
        {
	   if (TRACEON)
	      System.out.println("VOID MAIN () {declarations statements}");

           /* output function epilogue */	  
           epilogue();
        }
        ;


declarations: type Identifier ';' declarations
        {
           if (TRACEON)
              System.out.println("declarations: type Identifier : declarations");

           if (symtab.containsKey($Identifier.text)) {
              // variable re-declared.
              System.out.println("Type Error: " + 
                                  $Identifier.getLine() + 
                                 ": Redeclared identifier.");
              System.exit(0);
           }
                 
           /* Add ID and its info into the symbol table. */
	       Info the_entry = new Info();
		    the_entry.theType = $type.attr_type;
		    the_entry.theVar.varIndex = varCount;
		    varCount ++;
		    symtab.put($Identifier.text, the_entry);

           // issue the instruction.
		   // Ex: \%a = alloca i32, align 4
           if ($type.attr_type == Type.INT) { 
              TextCode.add("\%t" + the_entry.theVar.varIndex +" = alloca i32, align 4");
           }
           if($type.attr_type == Type.FLOAT){
               TextCode.add("\%t" + the_entry.theVar.varIndex +" = alloca float, align 4");
           }
        }
        | 
        {
           if (TRACEON)
              System.out.println("declarations: ");
        }
        ;


type
returns [Type attr_type]
    : 'int' { if (TRACEON) System.out.println("type: INT"); $attr_type=Type.INT; }
    | 'char' { if (TRACEON) System.out.println("type: CHAR"); $attr_type=Type.CHAR; }
    | 'float' {if (TRACEON) System.out.println("type: FLOAT"); $attr_type=Type.FLOAT; }
    | 'double'  {if (TRACEON) System.out.println("type: DOUBLE");   $attr_type=Type.DOUBLE;}
    | 'short'  {if (TRACEON) System.out.println("type: SHORT");   $attr_type=Type.SHORT;}
    | 'long'  {if (TRACEON) System.out.println("type: LONG");   $attr_type=Type.LONG;}
    | 'void'  {if (TRACEON) System.out.println("type: VOID");   $attr_type=Type.UNKNOW;}
    | 'unsigned' {if (TRACEON) System.out.println("type: UNSIGNED");   $attr_type=Type.Unsigned;}
   ;


statements:statement statements
          |
          ;


statement: assign_stmt ';'
         | if_stmt
         | printf_func ';'
         | func_no_return_stmt ';'
         | for_stmt
         | while_loop
         ;

printf_func:PRINTF '(' STRING_LITERAL  ( ',' a=arith_expression{Parameterlist.add(a);})* ')'
            {  String ss = $STRING_LITERAL.text;
               ss=ss.substring(1,ss.length()-1);
               if( ss.contains("\%d") || ss.contains("\%f")){
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
                        TextCode.add("\%t" + varCount + " = fpext float \%t" +Parameterlist.get(i).theVar.varIndex + " to double");
                        Parameterlist.get(i).theVar.varIndex = varCount;
                        varCount++;
                        pstr = pstr + "double \%t" + Parameterlist.get(i).theVar.varIndex + ", ";
                     }
                     else if(Parameterlist.get(i).theType == Type.INT){
                        pstr=pstr+"i32 \%t" + Parameterlist.get(i).theVar.varIndex + ", ";
                     }
                  }
                  pstr=pstr.substring(0,pstr.length()-2);
                  TextCode.add("\%s" + str_count + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + len + " x i8], [" + len +" x i8]* @str"+ str_count +", i64 0,i64 0), " + pstr + ")");
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
                  TextCode.add("\%s" + str_count + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + len + " x i8], [" + len +" x i8]* @str"+ str_count +", i64 0,i64 0))");
                  str_count++;
               }
            }
            ;


for_stmt: FOR '(' assign_stmt ';'{  TextCode.add("br label \%" + newLabel());
                                    TextCode.add("L"+ Integer.toString(labelCount) +":");
                                 }
                  a=compare ';'{
                                    TextCode.add("br i1 \%t" + $a.theInfo.theVar.varIndex + ", label \%" + newLabel() + ", label \%" + newLabel());
                                    TextCode.add(newLabel() + ":");
                               }
                  assign_stmt{
                       TextCode.add("br label \%L" + Integer.toString(labelCount-3));
                  }
              ')'{      
                      TextCode.add("L" + Integer.toString(labelCount-2) + ":");
                 }
                  block_stmt
                 {    
                      TextCode.add("br label \%L" + Integer.toString(labelCount));
                      TextCode.add("L" + Integer.toString(labelCount-1) + ":");
                 }
        ;
		 
 while_loop
 @init{ TextCode.add("br label \%" + newLabel());
        TextCode.add("L" + Integer.toString(labelCount) + ":");
      }
         : WHILE
         '(' a=compare ')'{
            TextCode.add("br i1 \%t" + $a.theInfo.theVar.varIndex + ", label \%" + newLabel() + ", label \%" + newLabel());
            TextCode.add("L"+Integer.toString(labelCount-1) + ":");
         } 
         block_stmt{
            TextCode.add("br label \%L" + Integer.toString(labelCount-2));
            TextCode.add("L" + Integer.toString(labelCount) + ":");
         }
      ;      
		 
if_stmt: if_then_stmt (if_else_stmt)?
         {  String label_exit = "L" + Integer.toString(labelCount-1) + ":";
            if(!TextCode.contains(label_exit)){
               labelCount--;
               TextCode.set(counter_temp,"br label \%L" + Integer.toString(labelCount));
            }
            TextCode.add("L" + Integer.toString(labelCount) + ":");
         }
         ;

if_then_stmt
returns[Info theInfo]
@init{theInfo = new Info();}
            :   IF '(' a=compare ')' 
            {
               TextCode.add("br i1 \%t" + $a.theInfo.theVar.varIndex + ", label \%" + newLabel() + ", label \%" + newLabel());
               TextCode.add("L" + Integer.toString(labelCount-1) + ":");
            }
            block_stmt
            {  
               TextCode.add("br label \%" + newLabel());
               counter_temp = TextCode.size()-1;
            }
            ;


if_else_stmt      
            : ELSE
               {
                  TextCode.add("L" + Integer.toString(labelCount-1) + ":");
               }
            block_stmt
            {
               TextCode.add("br label \%L" + Integer.toString(labelCount));
            }
            ;

				  
block_stmt   
     : '{' statements '}' 
	  ;


assign_stmt: Identifier '=' arith_expression
             {
                Info theRHS = $arith_expression.theInfo;
				    Info theLHS = symtab.get($Identifier.text); 
		   
                if ((theLHS.theType == Type.INT) &&
                    (theRHS.theType == Type.INT)) {		   
                   // issue store insruction.
                   // Ex: store i32 \%tx, i32* \%ty
                   TextCode.add("store i32 \%t" + theRHS.theVar.varIndex + ", i32* \%t" + theLHS.theVar.varIndex);
				} else if ((theLHS.theType == Type.INT) &&
				    (theRHS.theType == Type.CONST_INT)) {
                   // issue store insruction.
                   // Ex: store i32 value, i32* \%ty
                   TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* \%t" + theLHS.theVar.varIndex);				
				} else if((theLHS.theType == Type.FLOAT) &&
                    (theRHS.theType == Type.CONST_FLOAT)){
                     double temp = theRHS.theVar.fValue;
                     long temp2 = Double.doubleToLongBits(temp);
                     TextCode.add("store float 0x" + Long.toHexString(temp2) + ", ptr \%t" + theLHS.theVar.varIndex );
            }else if((theLHS.theType == Type.FLOAT) &&
                    (theRHS.theType == Type.FLOAT)){
                     TextCode.add("store float \%t" + theRHS.theVar.varIndex + ", ptr \%t" + theLHS.theVar.varIndex + " align 4");
                    }
			 }
             ;

		   
func_no_return_stmt: Identifier '(' argument ')'
                   ;


argument: arg (',' arg)*
        ;

arg: arith_expression
   | STRING_LITERAL
   ;

compare
returns [Info theInfo]
@init {theInfo = new Info();}
        : a=arith_expression {$theInfo = $a.theInfo;}
        ( '<' b=arith_expression {if (TRACEON) System.out.println("compare : <");
                                     if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp slt i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp slt i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp olt float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp olt double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }                
                                 }
        |'>' b=arith_expression {if (TRACEON) System.out.println("compare : >");
                                       if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp sgt i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp sgt i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp ogt float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp ogt double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }      
                                    }
        |'<=' b=arith_expression {if (TRACEON) System.out.println("compare : <=");
                                        if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp sle i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp sle i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp ole float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp ole double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }                      
                                 }
        |'>=' b=arith_expression {if (TRACEON) System.out.println("compare : >=");
                                       if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp sge i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp sge i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp oge float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp oge double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }                             
                                 }
        |'==' b=arith_expression {if (TRACEON) System.out.println("compare : ==");
                                       if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp eq i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp eq i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp oeq float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp oeq double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }      
                                 }
        |'!=' b=arith_expression {if (TRACEON) System.out.println("compare : !=");
                                       if(($a.theInfo.theType == Type.INT) &&
                                        ($b.theInfo.theType == Type.INT)){
                                             TextCode.add("\%t" + varCount + " = icmp ne i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                             
                                             $theInfo.theType = Type.BOOL;
					                              $theInfo.theVar.varIndex = varCount;
					                              varCount ++;
                                       }
                                       else if(($a.theInfo.theType == Type.INT) &&
					                                 ($b.theInfo.theType == Type.CONST_INT)){
                                                TextCode.add("\%t" + varCount + " = icmp ne i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

                                                $theInfo.theType = Type.BOOL;
					                                 $theInfo.theVar.varIndex = varCount;
					                                 varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.FLOAT)){
                                                 TextCode.add("\%t" + varCount + " = fcmp one float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                                                 $theInfo.theType = Type.BOOL;
					                                  $theInfo.theVar.varIndex = varCount;
					                                  varCount ++;
                                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                                   double temp = b.theVar.fValue;
                                                   long temp2 = Double.doubleToLongBits(temp);
                                                   TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                                                   $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                                   TextCode.add("\%t" + varCount + " = fcmp one double \%t" + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                                   $theInfo.theType = Type.BOOL;
					                                    $theInfo.theVar.varIndex = varCount;
					                                    varCount ++;
                                               }      
                                  }
        )*
        ;


arith_expression
returns [Info theInfo]
@init {theInfo = new Info();}
                : a=multExpr { $theInfo=$a.theInfo; }
                 ( '+' b=multExpr
                    {
                       // int + code generation.					   
                       if (($a.theInfo.theType == Type.INT) &&
                           ($b.theInfo.theType == Type.INT)) {
                           TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       } else if (($a.theInfo.theType == Type.INT) &&
					       ($b.theInfo.theType == Type.CONST_INT)) {
                           TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       }else if(($a.theInfo.theType == Type.CONST_INT) &&
                               ($b.theInfo.theType == Type.CONST_INT)) {
                           $theInfo.theVar.iValue = $theInfo.theVar.iValue + $b.theInfo.theVar.iValue;
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.CONST_INT;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%t" + varCount + " = fadd float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           $theInfo.theType = Type.FLOAT;
					            $theInfo.theVar.varIndex = varCount;
					            varCount ++;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 double temp = b.theVar.fValue;
                                 long temp2 = Double.doubleToLongBits(temp);
                                 TextCode.add("\%t" + varCount + " = fpext float " + $theInfo.theVar.varIndex +" to double" );
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fadd double " + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fptrunc double " + $theInfo.theVar.varIndex + " to float");
                                 $theInfo.theType = Type.FLOAT;
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                       }else if(($a.theInfo.theType == Type.CONST_FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 $theInfo.theVar.fValue = $theInfo.theVar.fValue + $b.theInfo.theVar.fValue;
                                  $theInfo.theType = Type.CONST_FLOAT;
                       }

                    }
                 | '-' b=multExpr
                     {
                        // int - code generation.					   
                       if (($a.theInfo.theType == Type.INT) &&
                           ($b.theInfo.theType == Type.INT)) {
                           TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       } else if (($a.theInfo.theType == Type.INT) &&
					       ($b.theInfo.theType == Type.CONST_INT)) {
                           TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       }else if(($a.theInfo.theType == Type.CONST_INT) &&
                               ($b.theInfo.theType == Type.CONST_INT)) {
                          $theInfo.theVar.iValue = $theInfo.theVar.iValue - $b.theInfo.theVar.iValue;
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.CONST_INT;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%t" + varCount + " = fsub float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           $theInfo.theType = Type.FLOAT;
					            $theInfo.theVar.varIndex = varCount;
					            varCount ++;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 double temp = b.theVar.fValue;
                                 long temp2 = Double.doubleToLongBits(temp);
                                 TextCode.add("\%t" + varCount + " = fpext float " + $theInfo.theVar.varIndex +" to double" );
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fsub double " + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fptrunc double " + $theInfo.theVar.varIndex + " to float");
                                 $theInfo.theType = Type.FLOAT;
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                       }else if(($a.theInfo.theType == Type.CONST_FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 $theInfo.theVar.fValue = $theInfo.theVar.fValue - $b.theInfo.theVar.fValue;
                                  $theInfo.theType = Type.CONST_FLOAT;
                       }
                     }
                 )*
                 ;

multExpr
returns [Info theInfo]
@init {theInfo = new Info();}
          : a=signExpr { $theInfo=$a.theInfo; }
          ( '*' b=signExpr
             {
               // int * code generation.					   
                       if (($a.theInfo.theType == Type.INT) &&
                           ($b.theInfo.theType == Type.INT)) {
                           TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.INT;
                           $theInfo.theVar.varIndex = varCount;
                           varCount ++;
                       } else if (($a.theInfo.theType == Type.INT) &&
					                  ($b.theInfo.theType == Type.CONST_INT)) {
                              
                              TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
                               // Update arith_expression's theInfo.
                              $theInfo.theType = Type.INT;
                              $theInfo.theVar.varIndex = varCount;
                              varCount ++;
                       }else if(($a.theInfo.theType == Type.CONST_INT) &&
                               ($b.theInfo.theType == Type.CONST_INT)) {
                           $theInfo.theVar.iValue = $theInfo.theVar.iValue * $b.theInfo.theVar.iValue;
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.CONST_INT;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%t" + varCount + " = fmul float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           $theInfo.theType = Type.FLOAT;
					            $theInfo.theVar.varIndex = varCount;
					            varCount ++;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 double temp = b.theVar.fValue;
                                 long temp2 = Double.doubleToLongBits(temp);
                                 TextCode.add("\%t" + varCount + " = fpext float " + $theInfo.theVar.varIndex +" to double" );
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fmul double " + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fptrunc double " + $theInfo.theVar.varIndex + " to float");
                                 $theInfo.theType = Type.FLOAT;
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                       }else if(($a.theInfo.theType == Type.CONST_FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 $theInfo.theVar.fValue = $theInfo.theVar.fValue * $b.theInfo.theVar.fValue;
                                  $theInfo.theType = Type.CONST_FLOAT;
                       }
             }
          | '/' b=signExpr
             {
               // int / code generation.					   
                       if (($a.theInfo.theType == Type.INT) &&
                           ($b.theInfo.theType == Type.INT)) {
                           TextCode.add("\%t" + varCount + " = sdiv nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       } else if (($a.theInfo.theType == Type.INT) &&
					       ($b.theInfo.theType == Type.CONST_INT)) {
                           TextCode.add("\%t" + varCount + " = sdiv nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       }else if(($a.theInfo.theType == Type.CONST_INT) &&
                               ($b.theInfo.theType == Type.CONST_INT)) {
                           $theInfo.theVar.iValue = $theInfo.theVar.iValue / $b.theInfo.theVar.iValue;
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.CONST_INT;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%t" + varCount + " = fdiv float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           $theInfo.theType = Type.FLOAT;
					            $theInfo.theVar.varIndex = varCount;
					            varCount ++;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 double temp = b.theVar.fValue;
                                 long temp2 = Double.doubleToLongBits(temp);
                                 TextCode.add("\%t" + varCount + " = fpext float " + $theInfo.theVar.varIndex +" to double" );
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fdiv double " + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fptrunc double " + $theInfo.theVar.varIndex + " to float");
                                 $theInfo.theType = Type.FLOAT;
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                       }else if(($a.theInfo.theType == Type.CONST_FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 $theInfo.theVar.fValue = $theInfo.theVar.fValue / $b.theInfo.theVar.fValue;
                                  $theInfo.theType = Type.CONST_FLOAT;
                       }
             }
         | MOD b=signExpr
            {
               // int mod code generation.					   
                       if (($a.theInfo.theType == Type.INT) &&
                           ($b.theInfo.theType == Type.INT)) {
                           TextCode.add("\%t" + varCount + " = srem nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       } else if (($a.theInfo.theType == Type.INT) &&
					       ($b.theInfo.theType == Type.CONST_INT)) {
                           TextCode.add("\%t" + varCount + " = srem nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
					   
					       // Update arith_expression's theInfo.
					       $theInfo.theType = Type.INT;
					       $theInfo.theVar.varIndex = varCount;
					       varCount ++;
                       }else if(($a.theInfo.theType == Type.CONST_INT) &&
                               ($b.theInfo.theType == Type.CONST_INT)) {
                           $theInfo.theVar.iValue = $theInfo.theVar.iValue \% $b.theInfo.theVar.iValue;
					   
                           // Update arith_expression's theInfo.
                           $theInfo.theType = Type.CONST_INT;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%t" + varCount + " = frem float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           $theInfo.theType = Type.FLOAT;
					            $theInfo.theVar.varIndex = varCount;
					            varCount ++;
                       }else if(($a.theInfo.theType == Type.FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 double temp = b.theVar.fValue;
                                 long temp2 = Double.doubleToLongBits(temp);
                                 TextCode.add("\%t" + varCount + " = fpext float " + $theInfo.theVar.varIndex +" to double" );
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = frem double " + $theInfo.theVar.varIndex + ", 0x" + Long.toHexString(temp2));
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                                 TextCode.add("\%t" + varCount + " = fptrunc double " + $theInfo.theVar.varIndex + " to float");
                                 $theInfo.theType = Type.FLOAT;
                                 $theInfo.theVar.varIndex = varCount;
                                 varCount++;
                       }else if(($a.theInfo.theType == Type.CONST_FLOAT) &&
                               ($b.theInfo.theType == Type.CONST_FLOAT)){
                                 $theInfo.theVar.fValue = $theInfo.theVar.fValue \% $b.theInfo.theVar.fValue;
                                  $theInfo.theType = Type.CONST_FLOAT;
                       }
            }
	  )*
	  ;

signExpr
returns [Info theInfo]
@init {theInfo = new Info();}
        : a=primaryExpr { $theInfo=$a.theInfo; } 
        | '-' primaryExpr 
	;
		  
primaryExpr
returns [Info theInfo]
@init {theInfo = new Info();}
           : Integer_constant
               {
                   $theInfo.theType = Type.CONST_INT;
                   $theInfo.theVar.iValue = Integer.parseInt($Integer_constant.text);
               }
           | Floating_point_constant
               {
                   $theInfo.theType = Type.CONST_FLOAT;
                   $theInfo.theVar.fValue = Float.parseFloat($Floating_point_constant.text);
               }
           | Identifier
               {
                // get type information from symtab.
                Type the_type = symtab.get($Identifier.text).theType;
				    $theInfo.theType = the_type;

                // get variable index from symtab.
                int vIndex = symtab.get($Identifier.text).theVar.varIndex;
				
                switch (the_type) {
                case INT: 
                         // get a new temporary variable and
						       // load the variable into the temporary variable.
                         
						       // Ex: \%tx = load i32, i32* \%ty.
						       TextCode.add("\%t" + varCount + " = load i32, i32* \%t" + vIndex);
				         
						       // Now, Identifier's value is at the temporary variable \%t[varCount].
						       // Therefore, update it.
						       $theInfo.theVar.varIndex = varCount;
						       varCount ++;
                         break;
                case FLOAT:
                         TextCode.add("\%t" + varCount + " = load float, ptr \%t" + vIndex);
                         $theInfo.theVar.varIndex = varCount;
						       varCount ++;
                         break;
                case CHAR:
                         break;
			
                }
              }
	   | '&' Identifier
	   | '(' a=arith_expression ')'{ $theInfo = $a.theInfo;}
      ;

		   
/* description of the tokens */
FLOAT:'float';
INT:'int';
CHAR: 'char';
DOUBLE : 'double';
SHORT : 'short';
LONG : 'long';
UNSIGNED : 'unsigned';

PLUS : '+';
MINUS : '-';
MULTIPLE : '*';
DEVIDE : '/';
MOD : '%';
EQUAL: '=';

COMMA : ',';
DOT : '.';
PARENTHESES_LEFT : '(';
PARENTHESES_RIGHT : ')';
BRACKET_LEFT : '[';
BRACKET_RIGHT : ']';
CURLY_BRACKET_LEFT : '{';
CURLY_BRACKET_RIGHT : '}';
COLON : ':';
SEMICOLON : ';';

MAIN: 'main';
VOID: 'void';
IF: 'if';
ELSE: 'else';
FOR: 'for';
PRINTF: 'printf';
WHILE: 'while';

EQ : '==';
LE : '<';
EQ_LE : '<=';
GE : '>';
EQ_GE : '>=';
NOT_EQ : '!=';
//RelationOP: '>' |'>=' | '<' | '<=' | '==' | '!=';

Identifier:('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
Integer_constant:'0'..'9'+;
Floating_point_constant:'0'..'9'+ '.' '0'..'9'+;

LIB : '#include <' .* '>';

STRING_LITERAL
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

WS:( ' ' | '\t' | '\r' | '\n' )  {$channel=HIDDEN;};
COMMENT:'/*' .* '*/'   {$channel=HIDDEN;};

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;
