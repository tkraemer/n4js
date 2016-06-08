package eu.numberfour.n4js.ts.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import eu.numberfour.n4js.ts.services.TypesGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalTypesParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AssignmnentCompatible", "ProtectedInternal", "ProvidedByRuntime", "PublicInternal", "AutoboxedType", "Intersection", "Constructor", "VirtualBase", "Implements", "Interface", "Primitive", "Protected", "Undefined", "Abstract", "Function", "Nullable", "Extends", "Indexed", "Notnull", "Private", "Project", "Object", "Public", "Static", "Class", "Const", "Final", "Super", "Union", "This", "Enum", "From", "Null", "This_1", "Type", "Void", "With", "FullStopFullStopFullStop", "Any", "Get", "Out", "Set", "EqualsSignGreaterThanSign", "As", "In", "ExclamationMark", "Ampersand", "LeftParenthesis", "RightParenthesis", "PlusSign", "Comma", "FullStop", "Solidus", "Colon", "Semicolon", "LessThanSign", "GreaterThanSign", "QuestionMark", "CommercialAt", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "VerticalLine", "RightCurlyBracket", "Tilde", "RULE_SINGLE_STRING_CHAR", "RULE_STRING", "RULE_LINE_TERMINATOR_FRAGMENT", "RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT", "RULE_STRUCTMODSUFFIX", "RULE_IDENTIFIER_START", "RULE_IDENTIFIER_PART", "RULE_IDENTIFIER", "RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT", "RULE_INT", "RULE_ML_COMMENT_FRAGMENT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_EOL", "RULE_WHITESPACE_FRAGMENT", "RULE_WS", "RULE_HEX_DIGIT", "RULE_UNICODE_ESCAPE_FRAGMENT", "RULE_UNICODE_LETTER_FRAGMENT", "RULE_UNICODE_COMBINING_MARK_FRAGMENT", "RULE_UNICODE_DIGIT_FRAGMENT", "RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT", "RULE_ZWNJ", "RULE_ZWJ", "RULE_DOT_DOT", "RULE_DECIMAL_DIGIT_FRAGMENT", "RULE_BOM", "RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT", "RULE_SL_COMMENT_FRAGMENT", "RULE_ANY_OTHER"
    };
    public static final int Enum=34;
    public static final int EqualsSignGreaterThanSign=46;
    public static final int LessThanSign=59;
    public static final int LeftParenthesis=51;
    public static final int VirtualBase=11;
    public static final int Private=23;
    public static final int Extends=20;
    public static final int ExclamationMark=49;
    public static final int GreaterThanSign=60;
    public static final int RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT=72;
    public static final int RULE_STRUCTMODSUFFIX=73;
    public static final int RULE_EOL=82;
    public static final int ProtectedInternal=5;
    public static final int Out=44;
    public static final int RULE_ZWNJ=91;
    public static final int Project=24;
    public static final int VerticalLine=66;
    public static final int PlusSign=53;
    public static final int RULE_INT=78;
    public static final int Get=43;
    public static final int RULE_ML_COMMENT=80;
    public static final int Object=25;
    public static final int LeftSquareBracket=63;
    public static final int Intersection=9;
    public static final int Set=45;
    public static final int RULE_UNICODE_ESCAPE_FRAGMENT=86;
    public static final int In=48;
    public static final int Union=32;
    public static final int Comma=54;
    public static final int RULE_SL_COMMENT_FRAGMENT=97;
    public static final int As=47;
    public static final int RULE_IDENTIFIER_PART=75;
    public static final int RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT=96;
    public static final int Solidus=56;
    public static final int RightCurlyBracket=67;
    public static final int Final=30;
    public static final int FullStop=55;
    public static final int Constructor=10;
    public static final int Abstract=17;
    public static final int CommercialAt=62;
    public static final int Semicolon=58;
    public static final int RULE_LINE_TERMINATOR_FRAGMENT=71;
    public static final int Type=38;
    public static final int QuestionMark=61;
    public static final int PublicInternal=7;
    public static final int RULE_HEX_DIGIT=85;
    public static final int RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT=90;
    public static final int Interface=13;
    public static final int Null=36;
    public static final int ProvidedByRuntime=6;
    public static final int FullStopFullStopFullStop=41;
    public static final int RULE_IDENTIFIER_START=74;
    public static final int Implements=12;
    public static final int RULE_WHITESPACE_FRAGMENT=83;
    public static final int Super=31;
    public static final int This=33;
    public static final int Ampersand=50;
    public static final int Notnull=22;
    public static final int Void=39;
    public static final int RightSquareBracket=64;
    public static final int Undefined=16;
    public static final int Protected=15;
    public static final int AutoboxedType=8;
    public static final int Const=29;
    public static final int RightParenthesis=52;
    public static final int RULE_UNICODE_COMBINING_MARK_FRAGMENT=88;
    public static final int Public=26;
    public static final int This_1=37;
    public static final int RULE_DOT_DOT=93;
    public static final int Class=28;
    public static final int Static=27;
    public static final int Nullable=19;
    public static final int RULE_SINGLE_STRING_CHAR=69;
    public static final int AssignmnentCompatible=4;
    public static final int RULE_IDENTIFIER=76;
    public static final int RULE_ML_COMMENT_FRAGMENT=79;
    public static final int RULE_STRING=70;
    public static final int Any=42;
    public static final int With=40;
    public static final int RULE_SL_COMMENT=81;
    public static final int Function=18;
    public static final int RULE_ZWJ=92;
    public static final int Primitive=14;
    public static final int RULE_UNICODE_DIGIT_FRAGMENT=89;
    public static final int Colon=57;
    public static final int EOF=-1;
    public static final int Indexed=21;
    public static final int RULE_WS=84;
    public static final int RULE_BOM=95;
    public static final int LeftCurlyBracket=65;
    public static final int Tilde=68;
    public static final int From=35;
    public static final int RULE_ANY_OTHER=98;
    public static final int RULE_UNICODE_LETTER_FRAGMENT=87;
    public static final int RULE_DECIMAL_DIGIT_FRAGMENT=94;
    public static final int RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT=77;

    // delegates
    // delegators


        public InternalTypesParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTypesParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTypesParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTypesParser.g"; }




    	private TypesGrammarAccess grammarAccess;
    	 	
    	public InternalTypesParser(TokenStream input, TypesGrammarAccess grammarAccess) {
    		this(input);
    		this.grammarAccess = grammarAccess;
    		registerRules(grammarAccess.getGrammar());
    	}
    	
    	@Override
    	protected String getFirstRuleName() {
    		return "TypeDefs";	
    	} 
    	   	   	
    	@Override
    	protected TypesGrammarAccess getGrammarAccess() {
    		return grammarAccess;
    	}



    // $ANTLR start "entryRuleTypeDefs"
    // InternalTypesParser.g:62:1: entryRuleTypeDefs returns [EObject current=null] : iv_ruleTypeDefs= ruleTypeDefs EOF ;
    public final EObject entryRuleTypeDefs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeDefs = null;


        try {
            // InternalTypesParser.g:63:2: (iv_ruleTypeDefs= ruleTypeDefs EOF )
            // InternalTypesParser.g:64:2: iv_ruleTypeDefs= ruleTypeDefs EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeDefsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeDefs=ruleTypeDefs();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeDefs; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeDefs"


    // $ANTLR start "ruleTypeDefs"
    // InternalTypesParser.g:71:1: ruleTypeDefs returns [EObject current=null] : ( (lv_types_0_0= ruleType ) )* ;
    public final EObject ruleTypeDefs() throws RecognitionException {
        EObject current = null;

        EObject lv_types_0_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:74:28: ( ( (lv_types_0_0= ruleType ) )* )
            // InternalTypesParser.g:75:1: ( (lv_types_0_0= ruleType ) )*
            {
            // InternalTypesParser.g:75:1: ( (lv_types_0_0= ruleType ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==PublicInternal||LA1_0==VirtualBase||LA1_0==Primitive||LA1_0==Undefined||LA1_0==Project||LA1_0==Public||LA1_0==Null||LA1_0==Void||LA1_0==Any||LA1_0==RULE_IDENTIFIER) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTypesParser.g:76:1: (lv_types_0_0= ruleType )
            	    {
            	    // InternalTypesParser.g:76:1: (lv_types_0_0= ruleType )
            	    // InternalTypesParser.g:77:3: lv_types_0_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTypeDefsAccess().getTypesTypeParserRuleCall_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_3);
            	    lv_types_0_0=ruleType();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTypeDefsRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"types",
            	              		lv_types_0_0, 
            	              		"eu.numberfour.n4js.ts.Types.Type");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeDefs"


    // $ANTLR start "entryRuleTAnnotation"
    // InternalTypesParser.g:101:1: entryRuleTAnnotation returns [EObject current=null] : iv_ruleTAnnotation= ruleTAnnotation EOF ;
    public final EObject entryRuleTAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnnotation = null;


        try {
            // InternalTypesParser.g:102:2: (iv_ruleTAnnotation= ruleTAnnotation EOF )
            // InternalTypesParser.g:103:2: iv_ruleTAnnotation= ruleTAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTAnnotationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTAnnotation=ruleTAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTAnnotation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTAnnotation"


    // $ANTLR start "ruleTAnnotation"
    // InternalTypesParser.g:110:1: ruleTAnnotation returns [EObject current=null] : ( ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) ) ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )? ) ;
    public final EObject ruleTAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_args_3_0 = null;

        EObject lv_args_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:113:28: ( ( ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) ) ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )? ) )
            // InternalTypesParser.g:114:1: ( ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) ) ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )? )
            {
            // InternalTypesParser.g:114:1: ( ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) ) ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )? )
            // InternalTypesParser.g:114:2: ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) ) ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )?
            {
            // InternalTypesParser.g:114:2: ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) ) )
            // InternalTypesParser.g:114:3: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) )
            {
            // InternalTypesParser.g:121:6: (otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) ) )
            // InternalTypesParser.g:122:2: otherlv_0= CommercialAt ( (lv_name_1_0= RULE_IDENTIFIER ) )
            {
            otherlv_0=(Token)match(input,CommercialAt,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTAnnotationAccess().getCommercialAtKeyword_0_0_0());
                  
            }
            // InternalTypesParser.g:126:1: ( (lv_name_1_0= RULE_IDENTIFIER ) )
            // InternalTypesParser.g:127:1: (lv_name_1_0= RULE_IDENTIFIER )
            {
            // InternalTypesParser.g:127:1: (lv_name_1_0= RULE_IDENTIFIER )
            // InternalTypesParser.g:128:3: lv_name_1_0= RULE_IDENTIFIER
            {
            lv_name_1_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_1_0, grammarAccess.getTAnnotationAccess().getNameIDENTIFIERTerminalRuleCall_0_0_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.IDENTIFIER");
              	    
            }

            }


            }


            }


            }

            // InternalTypesParser.g:144:4: ( ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==LeftParenthesis) && (synpred2_InternalTypesParser())) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalTypesParser.g:144:5: ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis ) ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )? otherlv_6= RightParenthesis
                    {
                    // InternalTypesParser.g:144:5: ( ( LeftParenthesis )=>otherlv_2= LeftParenthesis )
                    // InternalTypesParser.g:144:6: ( LeftParenthesis )=>otherlv_2= LeftParenthesis
                    {
                    otherlv_2=(Token)match(input,LeftParenthesis,FOLLOW_6); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getTAnnotationAccess().getLeftParenthesisKeyword_1_0());
                          
                    }

                    }

                    // InternalTypesParser.g:151:2: ( ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )* )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( ((LA3_0>=Intersection && LA3_0<=Constructor)||LA3_0==Undefined||LA3_0==Indexed||LA3_0==Union||(LA3_0>=Null && LA3_0<=Void)||LA3_0==Any||LA3_0==LeftCurlyBracket||LA3_0==Tilde||LA3_0==RULE_STRING||LA3_0==RULE_IDENTIFIER) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalTypesParser.g:151:3: ( (lv_args_3_0= ruleTAnnotationArgument ) ) (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )*
                            {
                            // InternalTypesParser.g:151:3: ( (lv_args_3_0= ruleTAnnotationArgument ) )
                            // InternalTypesParser.g:152:1: (lv_args_3_0= ruleTAnnotationArgument )
                            {
                            // InternalTypesParser.g:152:1: (lv_args_3_0= ruleTAnnotationArgument )
                            // InternalTypesParser.g:153:3: lv_args_3_0= ruleTAnnotationArgument
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getTAnnotationAccess().getArgsTAnnotationArgumentParserRuleCall_1_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_7);
                            lv_args_3_0=ruleTAnnotationArgument();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getTAnnotationRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_3_0, 
                                      		"eu.numberfour.n4js.ts.Types.TAnnotationArgument");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // InternalTypesParser.g:169:2: (otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) ) )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==Comma) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // InternalTypesParser.g:170:2: otherlv_4= Comma ( (lv_args_5_0= ruleTAnnotationArgument ) )
                            	    {
                            	    otherlv_4=(Token)match(input,Comma,FOLLOW_8); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_4, grammarAccess.getTAnnotationAccess().getCommaKeyword_1_1_1_0());
                            	          
                            	    }
                            	    // InternalTypesParser.g:174:1: ( (lv_args_5_0= ruleTAnnotationArgument ) )
                            	    // InternalTypesParser.g:175:1: (lv_args_5_0= ruleTAnnotationArgument )
                            	    {
                            	    // InternalTypesParser.g:175:1: (lv_args_5_0= ruleTAnnotationArgument )
                            	    // InternalTypesParser.g:176:3: lv_args_5_0= ruleTAnnotationArgument
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getTAnnotationAccess().getArgsTAnnotationArgumentParserRuleCall_1_1_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_7);
                            	    lv_args_5_0=ruleTAnnotationArgument();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTAnnotationRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_5_0, 
                            	              		"eu.numberfour.n4js.ts.Types.TAnnotationArgument");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop2;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_6=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getTAnnotationAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnnotation"


    // $ANTLR start "entryRuleTAnnotationArgument"
    // InternalTypesParser.g:205:1: entryRuleTAnnotationArgument returns [EObject current=null] : iv_ruleTAnnotationArgument= ruleTAnnotationArgument EOF ;
    public final EObject entryRuleTAnnotationArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnnotationArgument = null;


        try {
            // InternalTypesParser.g:206:2: (iv_ruleTAnnotationArgument= ruleTAnnotationArgument EOF )
            // InternalTypesParser.g:207:2: iv_ruleTAnnotationArgument= ruleTAnnotationArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTAnnotationArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTAnnotationArgument=ruleTAnnotationArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTAnnotationArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTAnnotationArgument"


    // $ANTLR start "ruleTAnnotationArgument"
    // InternalTypesParser.g:214:1: ruleTAnnotationArgument returns [EObject current=null] : (this_TAnnotationStringArgument_0= ruleTAnnotationStringArgument | this_TAnnotationTypeRefArgument_1= ruleTAnnotationTypeRefArgument ) ;
    public final EObject ruleTAnnotationArgument() throws RecognitionException {
        EObject current = null;

        EObject this_TAnnotationStringArgument_0 = null;

        EObject this_TAnnotationTypeRefArgument_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:217:28: ( (this_TAnnotationStringArgument_0= ruleTAnnotationStringArgument | this_TAnnotationTypeRefArgument_1= ruleTAnnotationTypeRefArgument ) )
            // InternalTypesParser.g:218:1: (this_TAnnotationStringArgument_0= ruleTAnnotationStringArgument | this_TAnnotationTypeRefArgument_1= ruleTAnnotationTypeRefArgument )
            {
            // InternalTypesParser.g:218:1: (this_TAnnotationStringArgument_0= ruleTAnnotationStringArgument | this_TAnnotationTypeRefArgument_1= ruleTAnnotationTypeRefArgument )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_STRING) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=Intersection && LA5_0<=Constructor)||LA5_0==Undefined||LA5_0==Indexed||LA5_0==Union||(LA5_0>=Null && LA5_0<=Void)||LA5_0==Any||LA5_0==LeftCurlyBracket||LA5_0==Tilde||LA5_0==RULE_IDENTIFIER) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalTypesParser.g:219:5: this_TAnnotationStringArgument_0= ruleTAnnotationStringArgument
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTAnnotationArgumentAccess().getTAnnotationStringArgumentParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TAnnotationStringArgument_0=ruleTAnnotationStringArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TAnnotationStringArgument_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:229:5: this_TAnnotationTypeRefArgument_1= ruleTAnnotationTypeRefArgument
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTAnnotationArgumentAccess().getTAnnotationTypeRefArgumentParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TAnnotationTypeRefArgument_1=ruleTAnnotationTypeRefArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TAnnotationTypeRefArgument_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnnotationArgument"


    // $ANTLR start "entryRuleTAnnotationStringArgument"
    // InternalTypesParser.g:245:1: entryRuleTAnnotationStringArgument returns [EObject current=null] : iv_ruleTAnnotationStringArgument= ruleTAnnotationStringArgument EOF ;
    public final EObject entryRuleTAnnotationStringArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnnotationStringArgument = null;


        try {
            // InternalTypesParser.g:246:2: (iv_ruleTAnnotationStringArgument= ruleTAnnotationStringArgument EOF )
            // InternalTypesParser.g:247:2: iv_ruleTAnnotationStringArgument= ruleTAnnotationStringArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTAnnotationStringArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTAnnotationStringArgument=ruleTAnnotationStringArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTAnnotationStringArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTAnnotationStringArgument"


    // $ANTLR start "ruleTAnnotationStringArgument"
    // InternalTypesParser.g:254:1: ruleTAnnotationStringArgument returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleTAnnotationStringArgument() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:257:28: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // InternalTypesParser.g:258:1: ( (lv_value_0_0= RULE_STRING ) )
            {
            // InternalTypesParser.g:258:1: ( (lv_value_0_0= RULE_STRING ) )
            // InternalTypesParser.g:259:1: (lv_value_0_0= RULE_STRING )
            {
            // InternalTypesParser.g:259:1: (lv_value_0_0= RULE_STRING )
            // InternalTypesParser.g:260:3: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_0_0, grammarAccess.getTAnnotationStringArgumentAccess().getValueSTRINGTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTAnnotationStringArgumentRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_0_0, 
                      		"eu.numberfour.n4js.ts.Types.STRING");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnnotationStringArgument"


    // $ANTLR start "entryRuleTAnnotationTypeRefArgument"
    // InternalTypesParser.g:284:1: entryRuleTAnnotationTypeRefArgument returns [EObject current=null] : iv_ruleTAnnotationTypeRefArgument= ruleTAnnotationTypeRefArgument EOF ;
    public final EObject entryRuleTAnnotationTypeRefArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnnotationTypeRefArgument = null;


        try {
            // InternalTypesParser.g:285:2: (iv_ruleTAnnotationTypeRefArgument= ruleTAnnotationTypeRefArgument EOF )
            // InternalTypesParser.g:286:2: iv_ruleTAnnotationTypeRefArgument= ruleTAnnotationTypeRefArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTAnnotationTypeRefArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTAnnotationTypeRefArgument=ruleTAnnotationTypeRefArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTAnnotationTypeRefArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTAnnotationTypeRefArgument"


    // $ANTLR start "ruleTAnnotationTypeRefArgument"
    // InternalTypesParser.g:293:1: ruleTAnnotationTypeRefArgument returns [EObject current=null] : ( (lv_typeRef_0_0= ruleTypeRef ) ) ;
    public final EObject ruleTAnnotationTypeRefArgument() throws RecognitionException {
        EObject current = null;

        EObject lv_typeRef_0_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:296:28: ( ( (lv_typeRef_0_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:297:1: ( (lv_typeRef_0_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:297:1: ( (lv_typeRef_0_0= ruleTypeRef ) )
            // InternalTypesParser.g:298:1: (lv_typeRef_0_0= ruleTypeRef )
            {
            // InternalTypesParser.g:298:1: (lv_typeRef_0_0= ruleTypeRef )
            // InternalTypesParser.g:299:3: lv_typeRef_0_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTAnnotationTypeRefArgumentAccess().getTypeRefTypeRefParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_typeRef_0_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTAnnotationTypeRefArgumentRule());
              	        }
                     		set(
                     			current, 
                     			"typeRef",
                      		lv_typeRef_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnnotationTypeRefArgument"


    // $ANTLR start "entryRuleType"
    // InternalTypesParser.g:323:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalTypesParser.g:324:2: (iv_ruleType= ruleType EOF )
            // InternalTypesParser.g:325:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalTypesParser.g:332:1: ruleType returns [EObject current=null] : (this_TObjectPrototype_0= ruleTObjectPrototype | this_TClass_1= ruleTClass | this_TInterface_2= ruleTInterface | this_TEnum_3= ruleTEnum | this_AnyType_4= ruleAnyType | this_VoidType_5= ruleVoidType | this_UndefinedType_6= ruleUndefinedType | this_NullType_7= ruleNullType | this_PrimitiveType_8= rulePrimitiveType | this_TFunction_9= ruleTFunction | this_TypeVariable_10= ruleTypeVariable | this_VirtualBaseType_11= ruleVirtualBaseType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_TObjectPrototype_0 = null;

        EObject this_TClass_1 = null;

        EObject this_TInterface_2 = null;

        EObject this_TEnum_3 = null;

        EObject this_AnyType_4 = null;

        EObject this_VoidType_5 = null;

        EObject this_UndefinedType_6 = null;

        EObject this_NullType_7 = null;

        EObject this_PrimitiveType_8 = null;

        EObject this_TFunction_9 = null;

        EObject this_TypeVariable_10 = null;

        EObject this_VirtualBaseType_11 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:335:28: ( (this_TObjectPrototype_0= ruleTObjectPrototype | this_TClass_1= ruleTClass | this_TInterface_2= ruleTInterface | this_TEnum_3= ruleTEnum | this_AnyType_4= ruleAnyType | this_VoidType_5= ruleVoidType | this_UndefinedType_6= ruleUndefinedType | this_NullType_7= ruleNullType | this_PrimitiveType_8= rulePrimitiveType | this_TFunction_9= ruleTFunction | this_TypeVariable_10= ruleTypeVariable | this_VirtualBaseType_11= ruleVirtualBaseType ) )
            // InternalTypesParser.g:336:1: (this_TObjectPrototype_0= ruleTObjectPrototype | this_TClass_1= ruleTClass | this_TInterface_2= ruleTInterface | this_TEnum_3= ruleTEnum | this_AnyType_4= ruleAnyType | this_VoidType_5= ruleVoidType | this_UndefinedType_6= ruleUndefinedType | this_NullType_7= ruleNullType | this_PrimitiveType_8= rulePrimitiveType | this_TFunction_9= ruleTFunction | this_TypeVariable_10= ruleTypeVariable | this_VirtualBaseType_11= ruleVirtualBaseType )
            {
            // InternalTypesParser.g:336:1: (this_TObjectPrototype_0= ruleTObjectPrototype | this_TClass_1= ruleTClass | this_TInterface_2= ruleTInterface | this_TEnum_3= ruleTEnum | this_AnyType_4= ruleAnyType | this_VoidType_5= ruleVoidType | this_UndefinedType_6= ruleUndefinedType | this_NullType_7= ruleNullType | this_PrimitiveType_8= rulePrimitiveType | this_TFunction_9= ruleTFunction | this_TypeVariable_10= ruleTypeVariable | this_VirtualBaseType_11= ruleVirtualBaseType )
            int alt6=12;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalTypesParser.g:337:5: this_TObjectPrototype_0= ruleTObjectPrototype
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTObjectPrototypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TObjectPrototype_0=ruleTObjectPrototype();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TObjectPrototype_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:347:5: this_TClass_1= ruleTClass
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTClassParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TClass_1=ruleTClass();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TClass_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:357:5: this_TInterface_2= ruleTInterface
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTInterfaceParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TInterface_2=ruleTInterface();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TInterface_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:367:5: this_TEnum_3= ruleTEnum
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTEnumParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TEnum_3=ruleTEnum();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TEnum_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:377:5: this_AnyType_4= ruleAnyType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getAnyTypeParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_AnyType_4=ruleAnyType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_AnyType_4;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:387:5: this_VoidType_5= ruleVoidType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getVoidTypeParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_VoidType_5=ruleVoidType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_VoidType_5;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalTypesParser.g:397:5: this_UndefinedType_6= ruleUndefinedType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getUndefinedTypeParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UndefinedType_6=ruleUndefinedType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UndefinedType_6;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 8 :
                    // InternalTypesParser.g:407:5: this_NullType_7= ruleNullType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getNullTypeParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_NullType_7=ruleNullType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_NullType_7;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 9 :
                    // InternalTypesParser.g:417:5: this_PrimitiveType_8= rulePrimitiveType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getPrimitiveTypeParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_PrimitiveType_8=rulePrimitiveType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrimitiveType_8;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 10 :
                    // InternalTypesParser.g:427:5: this_TFunction_9= ruleTFunction
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTFunctionParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TFunction_9=ruleTFunction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TFunction_9;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 11 :
                    // InternalTypesParser.g:437:5: this_TypeVariable_10= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getTypeVariableParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TypeVariable_10=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeVariable_10;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 12 :
                    // InternalTypesParser.g:447:5: this_VirtualBaseType_11= ruleVirtualBaseType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getVirtualBaseTypeParserRuleCall_11()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_VirtualBaseType_11=ruleVirtualBaseType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_VirtualBaseType_11;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleTypeRef"
    // InternalTypesParser.g:463:1: entryRuleTypeRef returns [EObject current=null] : iv_ruleTypeRef= ruleTypeRef EOF ;
    public final EObject entryRuleTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRef = null;


        try {
            // InternalTypesParser.g:464:2: (iv_ruleTypeRef= ruleTypeRef EOF )
            // InternalTypesParser.g:465:2: iv_ruleTypeRef= ruleTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeRef=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRef"


    // $ANTLR start "ruleTypeRef"
    // InternalTypesParser.g:472:1: ruleTypeRef returns [EObject current=null] : (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ( (lv_nullModifier_2_0= ruleNullModifierToken ) )? ) ;
    public final EObject ruleTypeRef() throws RecognitionException {
        EObject current = null;

        EObject this_TypeRefWithoutModifiers_0 = null;

        AntlrDatatypeRuleToken lv_undefModifier_1_0 = null;

        AntlrDatatypeRuleToken lv_nullModifier_2_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:475:28: ( (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ( (lv_nullModifier_2_0= ruleNullModifierToken ) )? ) )
            // InternalTypesParser.g:476:1: (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ( (lv_nullModifier_2_0= ruleNullModifierToken ) )? )
            {
            // InternalTypesParser.g:476:1: (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ( (lv_nullModifier_2_0= ruleNullModifierToken ) )? )
            // InternalTypesParser.g:477:5: this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ( (lv_nullModifier_2_0= ruleNullModifierToken ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTypeRefAccess().getTypeRefWithoutModifiersParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_9);
            this_TypeRefWithoutModifiers_0=ruleTypeRefWithoutModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeRefWithoutModifiers_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalTypesParser.g:485:1: ( (lv_undefModifier_1_0= ruleUndefModifierToken ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ExclamationMark||LA7_0==QuestionMark) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalTypesParser.g:486:1: (lv_undefModifier_1_0= ruleUndefModifierToken )
                    {
                    // InternalTypesParser.g:486:1: (lv_undefModifier_1_0= ruleUndefModifierToken )
                    // InternalTypesParser.g:487:3: lv_undefModifier_1_0= ruleUndefModifierToken
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeRefAccess().getUndefModifierUndefModifierTokenParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_10);
                    lv_undefModifier_1_0=ruleUndefModifierToken();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRefRule());
                      	        }
                             		set(
                             			current, 
                             			"undefModifier",
                              		lv_undefModifier_1_0, 
                              		"eu.numberfour.n4js.ts.Types.UndefModifierToken");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:503:3: ( (lv_nullModifier_2_0= ruleNullModifierToken ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==Nullable||LA8_0==Notnull) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalTypesParser.g:504:1: (lv_nullModifier_2_0= ruleNullModifierToken )
                    {
                    // InternalTypesParser.g:504:1: (lv_nullModifier_2_0= ruleNullModifierToken )
                    // InternalTypesParser.g:505:3: lv_nullModifier_2_0= ruleNullModifierToken
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeRefAccess().getNullModifierNullModifierTokenParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_nullModifier_2_0=ruleNullModifierToken();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRefRule());
                      	        }
                             		set(
                             			current, 
                             			"nullModifier",
                              		lv_nullModifier_2_0, 
                              		"eu.numberfour.n4js.ts.Types.NullModifierToken");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRef"


    // $ANTLR start "entryRuleNullModifierToken"
    // InternalTypesParser.g:529:1: entryRuleNullModifierToken returns [String current=null] : iv_ruleNullModifierToken= ruleNullModifierToken EOF ;
    public final String entryRuleNullModifierToken() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNullModifierToken = null;


        try {
            // InternalTypesParser.g:530:1: (iv_ruleNullModifierToken= ruleNullModifierToken EOF )
            // InternalTypesParser.g:531:2: iv_ruleNullModifierToken= ruleNullModifierToken EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullModifierTokenRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNullModifierToken=ruleNullModifierToken();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullModifierToken.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullModifierToken"


    // $ANTLR start "ruleNullModifierToken"
    // InternalTypesParser.g:538:1: ruleNullModifierToken returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= Notnull | kw= Nullable ) ;
    public final AntlrDatatypeRuleToken ruleNullModifierToken() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:542:6: ( (kw= Notnull | kw= Nullable ) )
            // InternalTypesParser.g:543:1: (kw= Notnull | kw= Nullable )
            {
            // InternalTypesParser.g:543:1: (kw= Notnull | kw= Nullable )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==Notnull) ) {
                alt9=1;
            }
            else if ( (LA9_0==Nullable) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalTypesParser.g:544:2: kw= Notnull
                    {
                    kw=(Token)match(input,Notnull,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNullModifierTokenAccess().getNotnullKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:551:2: kw= Nullable
                    {
                    kw=(Token)match(input,Nullable,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNullModifierTokenAccess().getNullableKeyword_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNullModifierToken"


    // $ANTLR start "entryRuleUndefModifierToken"
    // InternalTypesParser.g:564:1: entryRuleUndefModifierToken returns [String current=null] : iv_ruleUndefModifierToken= ruleUndefModifierToken EOF ;
    public final String entryRuleUndefModifierToken() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUndefModifierToken = null;


        try {
            // InternalTypesParser.g:565:1: (iv_ruleUndefModifierToken= ruleUndefModifierToken EOF )
            // InternalTypesParser.g:566:2: iv_ruleUndefModifierToken= ruleUndefModifierToken EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUndefModifierTokenRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUndefModifierToken=ruleUndefModifierToken();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUndefModifierToken.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUndefModifierToken"


    // $ANTLR start "ruleUndefModifierToken"
    // InternalTypesParser.g:573:1: ruleUndefModifierToken returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= QuestionMark | kw= ExclamationMark ) ;
    public final AntlrDatatypeRuleToken ruleUndefModifierToken() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:577:6: ( (kw= QuestionMark | kw= ExclamationMark ) )
            // InternalTypesParser.g:578:1: (kw= QuestionMark | kw= ExclamationMark )
            {
            // InternalTypesParser.g:578:1: (kw= QuestionMark | kw= ExclamationMark )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==QuestionMark) ) {
                alt10=1;
            }
            else if ( (LA10_0==ExclamationMark) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalTypesParser.g:579:2: kw= QuestionMark
                    {
                    kw=(Token)match(input,QuestionMark,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUndefModifierTokenAccess().getQuestionMarkKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:586:2: kw= ExclamationMark
                    {
                    kw=(Token)match(input,ExclamationMark,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUndefModifierTokenAccess().getExclamationMarkKeyword_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUndefModifierToken"


    // $ANTLR start "entryRulePrimitiveType"
    // InternalTypesParser.g:599:1: entryRulePrimitiveType returns [EObject current=null] : iv_rulePrimitiveType= rulePrimitiveType EOF ;
    public final EObject entryRulePrimitiveType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveType = null;


        try {
            // InternalTypesParser.g:600:2: (iv_rulePrimitiveType= rulePrimitiveType EOF )
            // InternalTypesParser.g:601:2: iv_rulePrimitiveType= rulePrimitiveType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimitiveType=rulePrimitiveType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimitiveType"


    // $ANTLR start "rulePrimitiveType"
    // InternalTypesParser.g:608:1: rulePrimitiveType returns [EObject current=null] : (otherlv_0= Primitive ( (lv_name_1_0= ruleTypesIdentifier ) ) (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )? (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )? otherlv_7= LeftCurlyBracket (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )? (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )? otherlv_12= RightCurlyBracket ) ;
    public final EObject rulePrimitiveType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_typeVars_3_0 = null;

        EObject lv_declaredElementType_6_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:611:28: ( (otherlv_0= Primitive ( (lv_name_1_0= ruleTypesIdentifier ) ) (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )? (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )? otherlv_7= LeftCurlyBracket (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )? (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )? otherlv_12= RightCurlyBracket ) )
            // InternalTypesParser.g:612:1: (otherlv_0= Primitive ( (lv_name_1_0= ruleTypesIdentifier ) ) (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )? (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )? otherlv_7= LeftCurlyBracket (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )? (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )? otherlv_12= RightCurlyBracket )
            {
            // InternalTypesParser.g:612:1: (otherlv_0= Primitive ( (lv_name_1_0= ruleTypesIdentifier ) ) (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )? (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )? otherlv_7= LeftCurlyBracket (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )? (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )? otherlv_12= RightCurlyBracket )
            // InternalTypesParser.g:613:2: otherlv_0= Primitive ( (lv_name_1_0= ruleTypesIdentifier ) ) (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )? (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )? otherlv_7= LeftCurlyBracket (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )? (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )? otherlv_12= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,Primitive,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPrimitiveTypeAccess().getPrimitiveKeyword_0());
                  
            }
            // InternalTypesParser.g:617:1: ( (lv_name_1_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:618:1: (lv_name_1_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:618:1: (lv_name_1_0= ruleTypesIdentifier )
            // InternalTypesParser.g:619:3: lv_name_1_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPrimitiveTypeAccess().getNameTypesIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_12);
            lv_name_1_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPrimitiveTypeRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:635:2: (otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LessThanSign) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalTypesParser.g:636:2: otherlv_2= LessThanSign ( (lv_typeVars_3_0= ruleTypeVariable ) ) otherlv_4= GreaterThanSign
                    {
                    otherlv_2=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getPrimitiveTypeAccess().getLessThanSignKeyword_2_0());
                          
                    }
                    // InternalTypesParser.g:640:1: ( (lv_typeVars_3_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:641:1: (lv_typeVars_3_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:641:1: (lv_typeVars_3_0= ruleTypeVariable )
                    // InternalTypesParser.g:642:3: lv_typeVars_3_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrimitiveTypeAccess().getTypeVarsTypeVariableParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_13);
                    lv_typeVars_3_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrimitiveTypeRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_3_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,GreaterThanSign,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getPrimitiveTypeAccess().getGreaterThanSignKeyword_2_2());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:663:3: (otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==Indexed) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalTypesParser.g:664:2: otherlv_5= Indexed ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) )
                    {
                    otherlv_5=(Token)match(input,Indexed,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getPrimitiveTypeAccess().getIndexedKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:668:1: ( (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:669:1: (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:669:1: (lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:670:3: lv_declaredElementType_6_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrimitiveTypeAccess().getDeclaredElementTypeParameterizedTypeRefNominalParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_16);
                    lv_declaredElementType_6_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrimitiveTypeRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredElementType",
                              		lv_declaredElementType_6_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,LeftCurlyBracket,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getPrimitiveTypeAccess().getLeftCurlyBracketKeyword_4());
                  
            }
            // InternalTypesParser.g:691:1: (otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==AutoboxedType) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalTypesParser.g:692:2: otherlv_8= AutoboxedType ( ( ruleTypeReferenceName ) )
                    {
                    otherlv_8=(Token)match(input,AutoboxedType,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getPrimitiveTypeAccess().getAutoboxedTypeKeyword_5_0());
                          
                    }
                    // InternalTypesParser.g:696:1: ( ( ruleTypeReferenceName ) )
                    // InternalTypesParser.g:697:1: ( ruleTypeReferenceName )
                    {
                    // InternalTypesParser.g:697:1: ( ruleTypeReferenceName )
                    // InternalTypesParser.g:698:3: ruleTypeReferenceName
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrimitiveTypeAccess().getAutoboxedTypeTClassifierCrossReference_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_18);
                    ruleTypeReferenceName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:712:4: (otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==AssignmnentCompatible) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalTypesParser.g:713:2: otherlv_10= AssignmnentCompatible ( ( ruleTypeReferenceName ) )
                    {
                    otherlv_10=(Token)match(input,AssignmnentCompatible,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getPrimitiveTypeAccess().getAssignmnentCompatibleKeyword_6_0());
                          
                    }
                    // InternalTypesParser.g:717:1: ( ( ruleTypeReferenceName ) )
                    // InternalTypesParser.g:718:1: ( ruleTypeReferenceName )
                    {
                    // InternalTypesParser.g:718:1: ( ruleTypeReferenceName )
                    // InternalTypesParser.g:719:3: ruleTypeReferenceName
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrimitiveTypeAccess().getAssignmentCompatiblePrimitiveTypeCrossReference_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_19);
                    ruleTypeReferenceName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_12, grammarAccess.getPrimitiveTypeAccess().getRightCurlyBracketKeyword_7());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimitiveType"


    // $ANTLR start "entryRuleTypeReferenceName"
    // InternalTypesParser.g:746:1: entryRuleTypeReferenceName returns [String current=null] : iv_ruleTypeReferenceName= ruleTypeReferenceName EOF ;
    public final String entryRuleTypeReferenceName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypeReferenceName = null;


        try {
            // InternalTypesParser.g:747:1: (iv_ruleTypeReferenceName= ruleTypeReferenceName EOF )
            // InternalTypesParser.g:748:2: iv_ruleTypeReferenceName= ruleTypeReferenceName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeReferenceNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeReferenceName=ruleTypeReferenceName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeReferenceName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeReferenceName"


    // $ANTLR start "ruleTypeReferenceName"
    // InternalTypesParser.g:755:1: ruleTypeReferenceName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= Void | kw= Any | kw= Undefined | kw= Null | kw= Indexed | (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* ) ) ;
    public final AntlrDatatypeRuleToken ruleTypeReferenceName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_IDENTIFIER_5=null;
        Token this_IDENTIFIER_7=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:759:6: ( (kw= Void | kw= Any | kw= Undefined | kw= Null | kw= Indexed | (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* ) ) )
            // InternalTypesParser.g:760:1: (kw= Void | kw= Any | kw= Undefined | kw= Null | kw= Indexed | (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* ) )
            {
            // InternalTypesParser.g:760:1: (kw= Void | kw= Any | kw= Undefined | kw= Null | kw= Indexed | (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* ) )
            int alt16=6;
            switch ( input.LA(1) ) {
            case Void:
                {
                alt16=1;
                }
                break;
            case Any:
                {
                alt16=2;
                }
                break;
            case Undefined:
                {
                alt16=3;
                }
                break;
            case Null:
                {
                alt16=4;
                }
                break;
            case Indexed:
                {
                alt16=5;
                }
                break;
            case RULE_IDENTIFIER:
                {
                alt16=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // InternalTypesParser.g:761:2: kw= Void
                    {
                    kw=(Token)match(input,Void,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getVoidKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:768:2: kw= Any
                    {
                    kw=(Token)match(input,Any,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getAnyKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:775:2: kw= Undefined
                    {
                    kw=(Token)match(input,Undefined,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getUndefinedKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:782:2: kw= Null
                    {
                    kw=(Token)match(input,Null,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getNullKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:789:2: kw= Indexed
                    {
                    kw=(Token)match(input,Indexed,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getIndexedKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:795:6: (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* )
                    {
                    // InternalTypesParser.g:795:6: (this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )* )
                    // InternalTypesParser.g:795:11: this_IDENTIFIER_5= RULE_IDENTIFIER (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )*
                    {
                    this_IDENTIFIER_5=(Token)match(input,RULE_IDENTIFIER,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_IDENTIFIER_5);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_IDENTIFIER_5, grammarAccess.getTypeReferenceNameAccess().getIDENTIFIERTerminalRuleCall_5_0()); 
                          
                    }
                    // InternalTypesParser.g:802:1: (kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==Solidus) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalTypesParser.g:803:2: kw= Solidus this_IDENTIFIER_7= RULE_IDENTIFIER
                    	    {
                    	    kw=(Token)match(input,Solidus,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	              current.merge(kw);
                    	              newLeafNode(kw, grammarAccess.getTypeReferenceNameAccess().getSolidusKeyword_5_1_0()); 
                    	          
                    	    }
                    	    this_IDENTIFIER_7=(Token)match(input,RULE_IDENTIFIER,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		current.merge(this_IDENTIFIER_7);
                    	          
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	       
                    	          newLeafNode(this_IDENTIFIER_7, grammarAccess.getTypeReferenceNameAccess().getIDENTIFIERTerminalRuleCall_5_1_1()); 
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeReferenceName"


    // $ANTLR start "entryRuleAnyType"
    // InternalTypesParser.g:823:1: entryRuleAnyType returns [EObject current=null] : iv_ruleAnyType= ruleAnyType EOF ;
    public final EObject entryRuleAnyType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyType = null;


        try {
            // InternalTypesParser.g:824:2: (iv_ruleAnyType= ruleAnyType EOF )
            // InternalTypesParser.g:825:2: iv_ruleAnyType= ruleAnyType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnyTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAnyType=ruleAnyType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnyType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnyType"


    // $ANTLR start "ruleAnyType"
    // InternalTypesParser.g:832:1: ruleAnyType returns [EObject current=null] : ( () ( (lv_name_1_0= Any ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) ;
    public final EObject ruleAnyType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:835:28: ( ( () ( (lv_name_1_0= Any ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) )
            // InternalTypesParser.g:836:1: ( () ( (lv_name_1_0= Any ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            {
            // InternalTypesParser.g:836:1: ( () ( (lv_name_1_0= Any ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            // InternalTypesParser.g:836:2: () ( (lv_name_1_0= Any ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket
            {
            // InternalTypesParser.g:836:2: ()
            // InternalTypesParser.g:837:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getAnyTypeAccess().getAnyTypeAction_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:842:2: ( (lv_name_1_0= Any ) )
            // InternalTypesParser.g:843:1: (lv_name_1_0= Any )
            {
            // InternalTypesParser.g:843:1: (lv_name_1_0= Any )
            // InternalTypesParser.g:844:3: lv_name_1_0= Any
            {
            lv_name_1_0=(Token)match(input,Any,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_1_0, grammarAccess.getAnyTypeAccess().getNameAnyKeyword_1_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getAnyTypeRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_1_0, "any");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getAnyTypeAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getAnyTypeAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnyType"


    // $ANTLR start "entryRuleVoidType"
    // InternalTypesParser.g:876:1: entryRuleVoidType returns [EObject current=null] : iv_ruleVoidType= ruleVoidType EOF ;
    public final EObject entryRuleVoidType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVoidType = null;


        try {
            // InternalTypesParser.g:877:2: (iv_ruleVoidType= ruleVoidType EOF )
            // InternalTypesParser.g:878:2: iv_ruleVoidType= ruleVoidType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getVoidTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleVoidType=ruleVoidType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleVoidType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVoidType"


    // $ANTLR start "ruleVoidType"
    // InternalTypesParser.g:885:1: ruleVoidType returns [EObject current=null] : ( () ( (lv_name_1_0= Void ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) ;
    public final EObject ruleVoidType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:888:28: ( ( () ( (lv_name_1_0= Void ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) )
            // InternalTypesParser.g:889:1: ( () ( (lv_name_1_0= Void ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            {
            // InternalTypesParser.g:889:1: ( () ( (lv_name_1_0= Void ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            // InternalTypesParser.g:889:2: () ( (lv_name_1_0= Void ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket
            {
            // InternalTypesParser.g:889:2: ()
            // InternalTypesParser.g:890:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getVoidTypeAccess().getVoidTypeAction_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:895:2: ( (lv_name_1_0= Void ) )
            // InternalTypesParser.g:896:1: (lv_name_1_0= Void )
            {
            // InternalTypesParser.g:896:1: (lv_name_1_0= Void )
            // InternalTypesParser.g:897:3: lv_name_1_0= Void
            {
            lv_name_1_0=(Token)match(input,Void,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_1_0, grammarAccess.getVoidTypeAccess().getNameVoidKeyword_1_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getVoidTypeRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_1_0, "void");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getVoidTypeAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getVoidTypeAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVoidType"


    // $ANTLR start "entryRuleUndefinedType"
    // InternalTypesParser.g:929:1: entryRuleUndefinedType returns [EObject current=null] : iv_ruleUndefinedType= ruleUndefinedType EOF ;
    public final EObject entryRuleUndefinedType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUndefinedType = null;


        try {
            // InternalTypesParser.g:930:2: (iv_ruleUndefinedType= ruleUndefinedType EOF )
            // InternalTypesParser.g:931:2: iv_ruleUndefinedType= ruleUndefinedType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUndefinedTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUndefinedType=ruleUndefinedType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUndefinedType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUndefinedType"


    // $ANTLR start "ruleUndefinedType"
    // InternalTypesParser.g:938:1: ruleUndefinedType returns [EObject current=null] : ( () ( (lv_name_1_0= Undefined ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) ;
    public final EObject ruleUndefinedType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:941:28: ( ( () ( (lv_name_1_0= Undefined ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) )
            // InternalTypesParser.g:942:1: ( () ( (lv_name_1_0= Undefined ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            {
            // InternalTypesParser.g:942:1: ( () ( (lv_name_1_0= Undefined ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            // InternalTypesParser.g:942:2: () ( (lv_name_1_0= Undefined ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket
            {
            // InternalTypesParser.g:942:2: ()
            // InternalTypesParser.g:943:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getUndefinedTypeAccess().getUndefinedTypeAction_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:948:2: ( (lv_name_1_0= Undefined ) )
            // InternalTypesParser.g:949:1: (lv_name_1_0= Undefined )
            {
            // InternalTypesParser.g:949:1: (lv_name_1_0= Undefined )
            // InternalTypesParser.g:950:3: lv_name_1_0= Undefined
            {
            lv_name_1_0=(Token)match(input,Undefined,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_1_0, grammarAccess.getUndefinedTypeAccess().getNameUndefinedKeyword_1_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getUndefinedTypeRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_1_0, "undefined");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getUndefinedTypeAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getUndefinedTypeAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUndefinedType"


    // $ANTLR start "entryRuleNullType"
    // InternalTypesParser.g:982:1: entryRuleNullType returns [EObject current=null] : iv_ruleNullType= ruleNullType EOF ;
    public final EObject entryRuleNullType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullType = null;


        try {
            // InternalTypesParser.g:983:2: (iv_ruleNullType= ruleNullType EOF )
            // InternalTypesParser.g:984:2: iv_ruleNullType= ruleNullType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNullType=ruleNullType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullType"


    // $ANTLR start "ruleNullType"
    // InternalTypesParser.g:991:1: ruleNullType returns [EObject current=null] : ( () ( (lv_name_1_0= Null ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) ;
    public final EObject ruleNullType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:994:28: ( ( () ( (lv_name_1_0= Null ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket ) )
            // InternalTypesParser.g:995:1: ( () ( (lv_name_1_0= Null ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            {
            // InternalTypesParser.g:995:1: ( () ( (lv_name_1_0= Null ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket )
            // InternalTypesParser.g:995:2: () ( (lv_name_1_0= Null ) ) otherlv_2= LeftCurlyBracket otherlv_3= RightCurlyBracket
            {
            // InternalTypesParser.g:995:2: ()
            // InternalTypesParser.g:996:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNullTypeAccess().getNullTypeAction_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:1001:2: ( (lv_name_1_0= Null ) )
            // InternalTypesParser.g:1002:1: (lv_name_1_0= Null )
            {
            // InternalTypesParser.g:1002:1: (lv_name_1_0= Null )
            // InternalTypesParser.g:1003:3: lv_name_1_0= Null
            {
            lv_name_1_0=(Token)match(input,Null,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_1_0, grammarAccess.getNullTypeAccess().getNameNullKeyword_1_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNullTypeRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_1_0, "null");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getNullTypeAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getNullTypeAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNullType"


    // $ANTLR start "entryRuleTypesIdentifier"
    // InternalTypesParser.g:1035:1: entryRuleTypesIdentifier returns [String current=null] : iv_ruleTypesIdentifier= ruleTypesIdentifier EOF ;
    public final String entryRuleTypesIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypesIdentifier = null;


        try {
            // InternalTypesParser.g:1036:1: (iv_ruleTypesIdentifier= ruleTypesIdentifier EOF )
            // InternalTypesParser.g:1037:2: iv_ruleTypesIdentifier= ruleTypesIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypesIdentifierRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypesIdentifier=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypesIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypesIdentifier"


    // $ANTLR start "ruleTypesIdentifier"
    // InternalTypesParser.g:1044:1: ruleTypesIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENTIFIER_0= RULE_IDENTIFIER | kw= Get | kw= Set | kw= Abstract | kw= Any | kw= Project | kw= Union | kw= Intersection | kw= As | kw= From | kw= Type | kw= Void | kw= Null | kw= Undefined | kw= Constructor | kw= Object | kw= VirtualBase | kw= Primitive | kw= AutoboxedType | kw= AssignmnentCompatible ) ;
    public final AntlrDatatypeRuleToken ruleTypesIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENTIFIER_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:1048:6: ( (this_IDENTIFIER_0= RULE_IDENTIFIER | kw= Get | kw= Set | kw= Abstract | kw= Any | kw= Project | kw= Union | kw= Intersection | kw= As | kw= From | kw= Type | kw= Void | kw= Null | kw= Undefined | kw= Constructor | kw= Object | kw= VirtualBase | kw= Primitive | kw= AutoboxedType | kw= AssignmnentCompatible ) )
            // InternalTypesParser.g:1049:1: (this_IDENTIFIER_0= RULE_IDENTIFIER | kw= Get | kw= Set | kw= Abstract | kw= Any | kw= Project | kw= Union | kw= Intersection | kw= As | kw= From | kw= Type | kw= Void | kw= Null | kw= Undefined | kw= Constructor | kw= Object | kw= VirtualBase | kw= Primitive | kw= AutoboxedType | kw= AssignmnentCompatible )
            {
            // InternalTypesParser.g:1049:1: (this_IDENTIFIER_0= RULE_IDENTIFIER | kw= Get | kw= Set | kw= Abstract | kw= Any | kw= Project | kw= Union | kw= Intersection | kw= As | kw= From | kw= Type | kw= Void | kw= Null | kw= Undefined | kw= Constructor | kw= Object | kw= VirtualBase | kw= Primitive | kw= AutoboxedType | kw= AssignmnentCompatible )
            int alt17=20;
            switch ( input.LA(1) ) {
            case RULE_IDENTIFIER:
                {
                alt17=1;
                }
                break;
            case Get:
                {
                alt17=2;
                }
                break;
            case Set:
                {
                alt17=3;
                }
                break;
            case Abstract:
                {
                alt17=4;
                }
                break;
            case Any:
                {
                alt17=5;
                }
                break;
            case Project:
                {
                alt17=6;
                }
                break;
            case Union:
                {
                alt17=7;
                }
                break;
            case Intersection:
                {
                alt17=8;
                }
                break;
            case As:
                {
                alt17=9;
                }
                break;
            case From:
                {
                alt17=10;
                }
                break;
            case Type:
                {
                alt17=11;
                }
                break;
            case Void:
                {
                alt17=12;
                }
                break;
            case Null:
                {
                alt17=13;
                }
                break;
            case Undefined:
                {
                alt17=14;
                }
                break;
            case Constructor:
                {
                alt17=15;
                }
                break;
            case Object:
                {
                alt17=16;
                }
                break;
            case VirtualBase:
                {
                alt17=17;
                }
                break;
            case Primitive:
                {
                alt17=18;
                }
                break;
            case AutoboxedType:
                {
                alt17=19;
                }
                break;
            case AssignmnentCompatible:
                {
                alt17=20;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // InternalTypesParser.g:1049:6: this_IDENTIFIER_0= RULE_IDENTIFIER
                    {
                    this_IDENTIFIER_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_IDENTIFIER_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_IDENTIFIER_0, grammarAccess.getTypesIdentifierAccess().getIDENTIFIERTerminalRuleCall_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:1058:2: kw= Get
                    {
                    kw=(Token)match(input,Get,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getGetKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:1065:2: kw= Set
                    {
                    kw=(Token)match(input,Set,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getSetKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:1072:2: kw= Abstract
                    {
                    kw=(Token)match(input,Abstract,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getAbstractKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:1079:2: kw= Any
                    {
                    kw=(Token)match(input,Any,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getAnyKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:1086:2: kw= Project
                    {
                    kw=(Token)match(input,Project,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getProjectKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalTypesParser.g:1093:2: kw= Union
                    {
                    kw=(Token)match(input,Union,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getUnionKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // InternalTypesParser.g:1100:2: kw= Intersection
                    {
                    kw=(Token)match(input,Intersection,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getIntersectionKeyword_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // InternalTypesParser.g:1107:2: kw= As
                    {
                    kw=(Token)match(input,As,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getAsKeyword_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // InternalTypesParser.g:1114:2: kw= From
                    {
                    kw=(Token)match(input,From,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getFromKeyword_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // InternalTypesParser.g:1121:2: kw= Type
                    {
                    kw=(Token)match(input,Type,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getTypeKeyword_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // InternalTypesParser.g:1128:2: kw= Void
                    {
                    kw=(Token)match(input,Void,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getVoidKeyword_11()); 
                          
                    }

                    }
                    break;
                case 13 :
                    // InternalTypesParser.g:1135:2: kw= Null
                    {
                    kw=(Token)match(input,Null,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getNullKeyword_12()); 
                          
                    }

                    }
                    break;
                case 14 :
                    // InternalTypesParser.g:1142:2: kw= Undefined
                    {
                    kw=(Token)match(input,Undefined,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getUndefinedKeyword_13()); 
                          
                    }

                    }
                    break;
                case 15 :
                    // InternalTypesParser.g:1149:2: kw= Constructor
                    {
                    kw=(Token)match(input,Constructor,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getConstructorKeyword_14()); 
                          
                    }

                    }
                    break;
                case 16 :
                    // InternalTypesParser.g:1156:2: kw= Object
                    {
                    kw=(Token)match(input,Object,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getObjectKeyword_15()); 
                          
                    }

                    }
                    break;
                case 17 :
                    // InternalTypesParser.g:1163:2: kw= VirtualBase
                    {
                    kw=(Token)match(input,VirtualBase,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getVirtualBaseKeyword_16()); 
                          
                    }

                    }
                    break;
                case 18 :
                    // InternalTypesParser.g:1170:2: kw= Primitive
                    {
                    kw=(Token)match(input,Primitive,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getPrimitiveKeyword_17()); 
                          
                    }

                    }
                    break;
                case 19 :
                    // InternalTypesParser.g:1177:2: kw= AutoboxedType
                    {
                    kw=(Token)match(input,AutoboxedType,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getAutoboxedTypeKeyword_18()); 
                          
                    }

                    }
                    break;
                case 20 :
                    // InternalTypesParser.g:1184:2: kw= AssignmnentCompatible
                    {
                    kw=(Token)match(input,AssignmnentCompatible,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypesIdentifierAccess().getAssignmnentCompatibleKeyword_19()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypesIdentifier"


    // $ANTLR start "entryRuleTypesComputedPropertyName"
    // InternalTypesParser.g:1197:1: entryRuleTypesComputedPropertyName returns [String current=null] : iv_ruleTypesComputedPropertyName= ruleTypesComputedPropertyName EOF ;
    public final String entryRuleTypesComputedPropertyName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypesComputedPropertyName = null;


        try {
            // InternalTypesParser.g:1198:1: (iv_ruleTypesComputedPropertyName= ruleTypesComputedPropertyName EOF )
            // InternalTypesParser.g:1199:2: iv_ruleTypesComputedPropertyName= ruleTypesComputedPropertyName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypesComputedPropertyNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypesComputedPropertyName=ruleTypesComputedPropertyName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypesComputedPropertyName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypesComputedPropertyName"


    // $ANTLR start "ruleTypesComputedPropertyName"
    // InternalTypesParser.g:1206:1: ruleTypesComputedPropertyName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= LeftSquareBracket (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName ) kw= RightSquareBracket ) ;
    public final AntlrDatatypeRuleToken ruleTypesComputedPropertyName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_TypesSymbolLiteralComputedName_1 = null;

        AntlrDatatypeRuleToken this_TypesStringLiteralComputedName_2 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:1210:6: ( (kw= LeftSquareBracket (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName ) kw= RightSquareBracket ) )
            // InternalTypesParser.g:1211:1: (kw= LeftSquareBracket (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName ) kw= RightSquareBracket )
            {
            // InternalTypesParser.g:1211:1: (kw= LeftSquareBracket (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName ) kw= RightSquareBracket )
            // InternalTypesParser.g:1212:2: kw= LeftSquareBracket (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName ) kw= RightSquareBracket
            {
            kw=(Token)match(input,LeftSquareBracket,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypesComputedPropertyNameAccess().getLeftSquareBracketKeyword_0()); 
                  
            }
            // InternalTypesParser.g:1217:1: (this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName | this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==AssignmnentCompatible||(LA18_0>=AutoboxedType && LA18_0<=VirtualBase)||LA18_0==Primitive||(LA18_0>=Undefined && LA18_0<=Abstract)||(LA18_0>=Project && LA18_0<=Object)||LA18_0==Union||(LA18_0>=From && LA18_0<=Null)||(LA18_0>=Type && LA18_0<=Void)||(LA18_0>=Any && LA18_0<=Get)||LA18_0==Set||LA18_0==As||LA18_0==RULE_IDENTIFIER) ) {
                alt18=1;
            }
            else if ( (LA18_0==RULE_STRING) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalTypesParser.g:1218:5: this_TypesSymbolLiteralComputedName_1= ruleTypesSymbolLiteralComputedName
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypesComputedPropertyNameAccess().getTypesSymbolLiteralComputedNameParserRuleCall_1_0()); 
                          
                    }
                    pushFollow(FOLLOW_22);
                    this_TypesSymbolLiteralComputedName_1=ruleTypesSymbolLiteralComputedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_TypesSymbolLiteralComputedName_1);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:1230:5: this_TypesStringLiteralComputedName_2= ruleTypesStringLiteralComputedName
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypesComputedPropertyNameAccess().getTypesStringLiteralComputedNameParserRuleCall_1_1()); 
                          
                    }
                    pushFollow(FOLLOW_22);
                    this_TypesStringLiteralComputedName_2=ruleTypesStringLiteralComputedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_TypesStringLiteralComputedName_2);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }

            kw=(Token)match(input,RightSquareBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypesComputedPropertyNameAccess().getRightSquareBracketKeyword_2()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypesComputedPropertyName"


    // $ANTLR start "entryRuleTypesSymbolLiteralComputedName"
    // InternalTypesParser.g:1254:1: entryRuleTypesSymbolLiteralComputedName returns [String current=null] : iv_ruleTypesSymbolLiteralComputedName= ruleTypesSymbolLiteralComputedName EOF ;
    public final String entryRuleTypesSymbolLiteralComputedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypesSymbolLiteralComputedName = null;


        try {
            // InternalTypesParser.g:1255:1: (iv_ruleTypesSymbolLiteralComputedName= ruleTypesSymbolLiteralComputedName EOF )
            // InternalTypesParser.g:1256:2: iv_ruleTypesSymbolLiteralComputedName= ruleTypesSymbolLiteralComputedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypesSymbolLiteralComputedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypesSymbolLiteralComputedName=ruleTypesSymbolLiteralComputedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypesSymbolLiteralComputedName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypesSymbolLiteralComputedName"


    // $ANTLR start "ruleTypesSymbolLiteralComputedName"
    // InternalTypesParser.g:1263:1: ruleTypesSymbolLiteralComputedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TypesIdentifier_0= ruleTypesIdentifier kw= FullStop this_TypesIdentifier_2= ruleTypesIdentifier ) ;
    public final AntlrDatatypeRuleToken ruleTypesSymbolLiteralComputedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_TypesIdentifier_0 = null;

        AntlrDatatypeRuleToken this_TypesIdentifier_2 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:1267:6: ( (this_TypesIdentifier_0= ruleTypesIdentifier kw= FullStop this_TypesIdentifier_2= ruleTypesIdentifier ) )
            // InternalTypesParser.g:1268:1: (this_TypesIdentifier_0= ruleTypesIdentifier kw= FullStop this_TypesIdentifier_2= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:1268:1: (this_TypesIdentifier_0= ruleTypesIdentifier kw= FullStop this_TypesIdentifier_2= ruleTypesIdentifier )
            // InternalTypesParser.g:1269:5: this_TypesIdentifier_0= ruleTypesIdentifier kw= FullStop this_TypesIdentifier_2= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTypesSymbolLiteralComputedNameAccess().getTypesIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_23);
            this_TypesIdentifier_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_TypesIdentifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            kw=(Token)match(input,FullStop,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypesSymbolLiteralComputedNameAccess().getFullStopKeyword_1()); 
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTypesSymbolLiteralComputedNameAccess().getTypesIdentifierParserRuleCall_2()); 
                  
            }
            pushFollow(FOLLOW_2);
            this_TypesIdentifier_2=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_TypesIdentifier_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypesSymbolLiteralComputedName"


    // $ANTLR start "entryRuleTypesStringLiteralComputedName"
    // InternalTypesParser.g:1304:1: entryRuleTypesStringLiteralComputedName returns [String current=null] : iv_ruleTypesStringLiteralComputedName= ruleTypesStringLiteralComputedName EOF ;
    public final String entryRuleTypesStringLiteralComputedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypesStringLiteralComputedName = null;


        try {
            // InternalTypesParser.g:1305:1: (iv_ruleTypesStringLiteralComputedName= ruleTypesStringLiteralComputedName EOF )
            // InternalTypesParser.g:1306:2: iv_ruleTypesStringLiteralComputedName= ruleTypesStringLiteralComputedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypesStringLiteralComputedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypesStringLiteralComputedName=ruleTypesStringLiteralComputedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypesStringLiteralComputedName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypesStringLiteralComputedName"


    // $ANTLR start "ruleTypesStringLiteralComputedName"
    // InternalTypesParser.g:1313:1: ruleTypesStringLiteralComputedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleTypesStringLiteralComputedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:1317:6: (this_STRING_0= RULE_STRING )
            // InternalTypesParser.g:1318:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_STRING_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_STRING_0, grammarAccess.getTypesStringLiteralComputedNameAccess().getSTRINGTerminalRuleCall()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypesStringLiteralComputedName"


    // $ANTLR start "entryRuleTObjectPrototype"
    // InternalTypesParser.g:1333:1: entryRuleTObjectPrototype returns [EObject current=null] : iv_ruleTObjectPrototype= ruleTObjectPrototype EOF ;
    public final EObject entryRuleTObjectPrototype() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTObjectPrototype = null;


        try {
            // InternalTypesParser.g:1334:2: (iv_ruleTObjectPrototype= ruleTObjectPrototype EOF )
            // InternalTypesParser.g:1335:2: iv_ruleTObjectPrototype= ruleTObjectPrototype EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTObjectPrototypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTObjectPrototype=ruleTObjectPrototype();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTObjectPrototype; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTObjectPrototype"


    // $ANTLR start "ruleTObjectPrototype"
    // InternalTypesParser.g:1342:1: ruleTObjectPrototype returns [EObject current=null] : ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredFinal_2_0= Final ) )? otherlv_3= Object ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )? otherlv_19= RightCurlyBracket ) ;
    public final EObject ruleTObjectPrototype() throws RecognitionException {
        EObject current = null;

        Token lv_declaredProvidedByRuntime_1_0=null;
        Token lv_declaredFinal_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_15=null;
        Token otherlv_19=null;
        Enumerator lv_declaredTypeAccessModifier_0_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_typeVars_6_0 = null;

        EObject lv_typeVars_8_0 = null;

        EObject lv_superType_11_0 = null;

        EObject lv_declaredElementType_13_0 = null;

        EObject lv_annotations_14_0 = null;

        EObject lv_ownedMembers_16_0 = null;

        EObject lv_callableCtor_17_0 = null;

        EObject lv_ownedMembers_18_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:1345:28: ( ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredFinal_2_0= Final ) )? otherlv_3= Object ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )? otherlv_19= RightCurlyBracket ) )
            // InternalTypesParser.g:1346:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredFinal_2_0= Final ) )? otherlv_3= Object ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )? otherlv_19= RightCurlyBracket )
            {
            // InternalTypesParser.g:1346:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredFinal_2_0= Final ) )? otherlv_3= Object ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )? otherlv_19= RightCurlyBracket )
            // InternalTypesParser.g:1346:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredFinal_2_0= Final ) )? otherlv_3= Object ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )? otherlv_19= RightCurlyBracket
            {
            // InternalTypesParser.g:1346:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) )
            // InternalTypesParser.g:1347:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            {
            // InternalTypesParser.g:1347:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            // InternalTypesParser.g:1348:3: lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getDeclaredTypeAccessModifierTypeAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_24);
            lv_declaredTypeAccessModifier_0_0=ruleTypeAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeAccessModifier",
                      		lv_declaredTypeAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:1364:2: ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ProvidedByRuntime) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalTypesParser.g:1365:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    {
                    // InternalTypesParser.g:1365:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    // InternalTypesParser.g:1366:3: lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime
                    {
                    lv_declaredProvidedByRuntime_1_0=(Token)match(input,ProvidedByRuntime,FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredProvidedByRuntime_1_0, grammarAccess.getTObjectPrototypeAccess().getDeclaredProvidedByRuntimeProvidedByRuntimeKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		setWithLastConsumed(current, "declaredProvidedByRuntime", true, "providedByRuntime");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1380:3: ( (lv_declaredFinal_2_0= Final ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==Final) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalTypesParser.g:1381:1: (lv_declaredFinal_2_0= Final )
                    {
                    // InternalTypesParser.g:1381:1: (lv_declaredFinal_2_0= Final )
                    // InternalTypesParser.g:1382:3: lv_declaredFinal_2_0= Final
                    {
                    lv_declaredFinal_2_0=(Token)match(input,Final,FOLLOW_26); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredFinal_2_0, grammarAccess.getTObjectPrototypeAccess().getDeclaredFinalFinalKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		setWithLastConsumed(current, "declaredFinal", true, "final");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,Object,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTObjectPrototypeAccess().getObjectKeyword_3());
                  
            }
            // InternalTypesParser.g:1401:1: ( (lv_name_4_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:1402:1: (lv_name_4_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:1402:1: (lv_name_4_0= ruleTypesIdentifier )
            // InternalTypesParser.g:1403:3: lv_name_4_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getNameTypesIdentifierParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_27);
            lv_name_4_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_4_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:1419:2: (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==LessThanSign) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalTypesParser.g:1420:2: otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign
                    {
                    otherlv_5=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTObjectPrototypeAccess().getLessThanSignKeyword_5_0());
                          
                    }
                    // InternalTypesParser.g:1424:1: ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:1425:1: (lv_typeVars_6_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:1425:1: (lv_typeVars_6_0= ruleTypeVariable )
                    // InternalTypesParser.g:1426:3: lv_typeVars_6_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getTypeVarsTypeVariableParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_6_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_6_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:1442:2: (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==Comma) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalTypesParser.g:1443:2: otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_7=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_7, grammarAccess.getTObjectPrototypeAccess().getCommaKeyword_5_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:1447:1: ( (lv_typeVars_8_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:1448:1: (lv_typeVars_8_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:1448:1: (lv_typeVars_8_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:1449:3: lv_typeVars_8_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getTypeVarsTypeVariableParserRuleCall_5_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_8_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_8_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,GreaterThanSign,FOLLOW_29); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getTObjectPrototypeAccess().getGreaterThanSignKeyword_5_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:1470:3: (otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==Extends) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalTypesParser.g:1471:2: otherlv_10= Extends ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) )
                    {
                    otherlv_10=(Token)match(input,Extends,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getTObjectPrototypeAccess().getExtendsKeyword_6_0());
                          
                    }
                    // InternalTypesParser.g:1475:1: ( (lv_superType_11_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:1476:1: (lv_superType_11_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:1476:1: (lv_superType_11_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:1477:3: lv_superType_11_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getSuperTypeParameterizedTypeRefNominalParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_30);
                    lv_superType_11_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		set(
                             			current, 
                             			"superType",
                              		lv_superType_11_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1493:4: (otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==Indexed) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalTypesParser.g:1494:2: otherlv_12= Indexed ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) )
                    {
                    otherlv_12=(Token)match(input,Indexed,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getTObjectPrototypeAccess().getIndexedKeyword_7_0());
                          
                    }
                    // InternalTypesParser.g:1498:1: ( (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:1499:1: (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:1499:1: (lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:1500:3: lv_declaredElementType_13_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getDeclaredElementTypeParameterizedTypeRefNominalParserRuleCall_7_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
                    lv_declaredElementType_13_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredElementType",
                              		lv_declaredElementType_13_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1516:4: ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==CommercialAt) && (synpred3_InternalTypesParser())) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalTypesParser.g:1516:5: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation )
            	    {
            	    // InternalTypesParser.g:1524:1: (lv_annotations_14_0= ruleTAnnotation )
            	    // InternalTypesParser.g:1525:3: lv_annotations_14_0= ruleTAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getAnnotationsTAnnotationParserRuleCall_8_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_31);
            	    lv_annotations_14_0=ruleTAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_14_0, 
            	              		"eu.numberfour.n4js.ts.Types.TAnnotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            otherlv_15=(Token)match(input,LeftCurlyBracket,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getTObjectPrototypeAccess().getLeftCurlyBracketKeyword_9());
                  
            }
            // InternalTypesParser.g:1546:1: ( (lv_ownedMembers_16_0= ruleTMember ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==ProtectedInternal||LA26_0==PublicInternal||LA26_0==Protected||(LA26_0>=Private && LA26_0<=Project)||LA26_0==Public) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalTypesParser.g:1547:1: (lv_ownedMembers_16_0= ruleTMember )
            	    {
            	    // InternalTypesParser.g:1547:1: (lv_ownedMembers_16_0= ruleTMember )
            	    // InternalTypesParser.g:1548:3: lv_ownedMembers_16_0= ruleTMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getOwnedMembersTMemberParserRuleCall_10_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_32);
            	    lv_ownedMembers_16_0=ruleTMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedMembers",
            	              		lv_ownedMembers_16_0, 
            	              		"eu.numberfour.n4js.ts.Types.TMember");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            // InternalTypesParser.g:1564:3: ( ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LeftParenthesis) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalTypesParser.g:1564:4: ( (lv_callableCtor_17_0= ruleCallableCtor ) ) ( (lv_ownedMembers_18_0= ruleTMember ) )*
                    {
                    // InternalTypesParser.g:1564:4: ( (lv_callableCtor_17_0= ruleCallableCtor ) )
                    // InternalTypesParser.g:1565:1: (lv_callableCtor_17_0= ruleCallableCtor )
                    {
                    // InternalTypesParser.g:1565:1: (lv_callableCtor_17_0= ruleCallableCtor )
                    // InternalTypesParser.g:1566:3: lv_callableCtor_17_0= ruleCallableCtor
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getCallableCtorCallableCtorParserRuleCall_11_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_33);
                    lv_callableCtor_17_0=ruleCallableCtor();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                      	        }
                             		set(
                             			current, 
                             			"callableCtor",
                              		lv_callableCtor_17_0, 
                              		"eu.numberfour.n4js.ts.Types.CallableCtor");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:1582:2: ( (lv_ownedMembers_18_0= ruleTMember ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==ProtectedInternal||LA27_0==PublicInternal||LA27_0==Protected||(LA27_0>=Private && LA27_0<=Project)||LA27_0==Public) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalTypesParser.g:1583:1: (lv_ownedMembers_18_0= ruleTMember )
                    	    {
                    	    // InternalTypesParser.g:1583:1: (lv_ownedMembers_18_0= ruleTMember )
                    	    // InternalTypesParser.g:1584:3: lv_ownedMembers_18_0= ruleTMember
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTObjectPrototypeAccess().getOwnedMembersTMemberParserRuleCall_11_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_33);
                    	    lv_ownedMembers_18_0=ruleTMember();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTObjectPrototypeRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"ownedMembers",
                    	              		lv_ownedMembers_18_0, 
                    	              		"eu.numberfour.n4js.ts.Types.TMember");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_19, grammarAccess.getTObjectPrototypeAccess().getRightCurlyBracketKeyword_12());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTObjectPrototype"


    // $ANTLR start "entryRuleVirtualBaseType"
    // InternalTypesParser.g:1613:1: entryRuleVirtualBaseType returns [EObject current=null] : iv_ruleVirtualBaseType= ruleVirtualBaseType EOF ;
    public final EObject entryRuleVirtualBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVirtualBaseType = null;


        try {
            // InternalTypesParser.g:1614:2: (iv_ruleVirtualBaseType= ruleVirtualBaseType EOF )
            // InternalTypesParser.g:1615:2: iv_ruleVirtualBaseType= ruleVirtualBaseType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getVirtualBaseTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleVirtualBaseType=ruleVirtualBaseType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleVirtualBaseType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVirtualBaseType"


    // $ANTLR start "ruleVirtualBaseType"
    // InternalTypesParser.g:1622:1: ruleVirtualBaseType returns [EObject current=null] : ( () otherlv_1= VirtualBase ( (lv_name_2_0= ruleTypesIdentifier ) ) otherlv_3= LeftCurlyBracket ( (lv_ownedMembers_4_0= ruleTMember ) )* otherlv_5= RightCurlyBracket ) ;
    public final EObject ruleVirtualBaseType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedMembers_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:1625:28: ( ( () otherlv_1= VirtualBase ( (lv_name_2_0= ruleTypesIdentifier ) ) otherlv_3= LeftCurlyBracket ( (lv_ownedMembers_4_0= ruleTMember ) )* otherlv_5= RightCurlyBracket ) )
            // InternalTypesParser.g:1626:1: ( () otherlv_1= VirtualBase ( (lv_name_2_0= ruleTypesIdentifier ) ) otherlv_3= LeftCurlyBracket ( (lv_ownedMembers_4_0= ruleTMember ) )* otherlv_5= RightCurlyBracket )
            {
            // InternalTypesParser.g:1626:1: ( () otherlv_1= VirtualBase ( (lv_name_2_0= ruleTypesIdentifier ) ) otherlv_3= LeftCurlyBracket ( (lv_ownedMembers_4_0= ruleTMember ) )* otherlv_5= RightCurlyBracket )
            // InternalTypesParser.g:1626:2: () otherlv_1= VirtualBase ( (lv_name_2_0= ruleTypesIdentifier ) ) otherlv_3= LeftCurlyBracket ( (lv_ownedMembers_4_0= ruleTMember ) )* otherlv_5= RightCurlyBracket
            {
            // InternalTypesParser.g:1626:2: ()
            // InternalTypesParser.g:1627:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getVirtualBaseTypeAccess().getVirtualBaseTypeAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,VirtualBase,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getVirtualBaseTypeAccess().getVirtualBaseKeyword_1());
                  
            }
            // InternalTypesParser.g:1637:1: ( (lv_name_2_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:1638:1: (lv_name_2_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:1638:1: (lv_name_2_0= ruleTypesIdentifier )
            // InternalTypesParser.g:1639:3: lv_name_2_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getVirtualBaseTypeAccess().getNameTypesIdentifierParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
            lv_name_2_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getVirtualBaseTypeRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_2_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,LeftCurlyBracket,FOLLOW_33); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getVirtualBaseTypeAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // InternalTypesParser.g:1660:1: ( (lv_ownedMembers_4_0= ruleTMember ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==ProtectedInternal||LA29_0==PublicInternal||LA29_0==Protected||(LA29_0>=Private && LA29_0<=Project)||LA29_0==Public) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalTypesParser.g:1661:1: (lv_ownedMembers_4_0= ruleTMember )
            	    {
            	    // InternalTypesParser.g:1661:1: (lv_ownedMembers_4_0= ruleTMember )
            	    // InternalTypesParser.g:1662:3: lv_ownedMembers_4_0= ruleTMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getVirtualBaseTypeAccess().getOwnedMembersTMemberParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_33);
            	    lv_ownedMembers_4_0=ruleTMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getVirtualBaseTypeRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedMembers",
            	              		lv_ownedMembers_4_0, 
            	              		"eu.numberfour.n4js.ts.Types.TMember");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            otherlv_5=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getVirtualBaseTypeAccess().getRightCurlyBracketKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVirtualBaseType"


    // $ANTLR start "entryRuleTClass"
    // InternalTypesParser.g:1691:1: entryRuleTClass returns [EObject current=null] : iv_ruleTClass= ruleTClass EOF ;
    public final EObject entryRuleTClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTClass = null;


        try {
            // InternalTypesParser.g:1692:2: (iv_ruleTClass= ruleTClass EOF )
            // InternalTypesParser.g:1693:2: iv_ruleTClass= ruleTClass EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTClassRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTClass=ruleTClass();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTClass; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTClass"


    // $ANTLR start "ruleTClass"
    // InternalTypesParser.g:1700:1: ruleTClass returns [EObject current=null] : ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredAbstract_2_0= Abstract ) )? ( (lv_declaredFinal_3_0= Final ) )? otherlv_4= Class ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_6_0= ruleTypesIdentifier ) ) (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )? (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )* otherlv_19= LeftCurlyBracket ( (lv_ownedMembers_20_0= ruleTMember ) )* ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )? otherlv_23= RightCurlyBracket ) ;
    public final EObject ruleTClass() throws RecognitionException {
        EObject current = null;

        Token lv_declaredProvidedByRuntime_1_0=null;
        Token lv_declaredAbstract_2_0=null;
        Token lv_declaredFinal_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_19=null;
        Token otherlv_23=null;
        Enumerator lv_declaredTypeAccessModifier_0_0 = null;

        AntlrDatatypeRuleToken lv_typingStrategy_5_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;

        EObject lv_typeVars_8_0 = null;

        EObject lv_typeVars_10_0 = null;

        EObject lv_superClassRef_13_0 = null;

        EObject lv_implementedInterfaceRefs_15_0 = null;

        EObject lv_implementedInterfaceRefs_17_0 = null;

        EObject lv_annotations_18_0 = null;

        EObject lv_ownedMembers_20_0 = null;

        EObject lv_callableCtor_21_0 = null;

        EObject lv_ownedMembers_22_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:1703:28: ( ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredAbstract_2_0= Abstract ) )? ( (lv_declaredFinal_3_0= Final ) )? otherlv_4= Class ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_6_0= ruleTypesIdentifier ) ) (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )? (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )* otherlv_19= LeftCurlyBracket ( (lv_ownedMembers_20_0= ruleTMember ) )* ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )? otherlv_23= RightCurlyBracket ) )
            // InternalTypesParser.g:1704:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredAbstract_2_0= Abstract ) )? ( (lv_declaredFinal_3_0= Final ) )? otherlv_4= Class ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_6_0= ruleTypesIdentifier ) ) (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )? (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )* otherlv_19= LeftCurlyBracket ( (lv_ownedMembers_20_0= ruleTMember ) )* ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )? otherlv_23= RightCurlyBracket )
            {
            // InternalTypesParser.g:1704:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredAbstract_2_0= Abstract ) )? ( (lv_declaredFinal_3_0= Final ) )? otherlv_4= Class ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_6_0= ruleTypesIdentifier ) ) (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )? (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )* otherlv_19= LeftCurlyBracket ( (lv_ownedMembers_20_0= ruleTMember ) )* ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )? otherlv_23= RightCurlyBracket )
            // InternalTypesParser.g:1704:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? ( (lv_declaredAbstract_2_0= Abstract ) )? ( (lv_declaredFinal_3_0= Final ) )? otherlv_4= Class ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_6_0= ruleTypesIdentifier ) ) (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )? (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )? (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )* otherlv_19= LeftCurlyBracket ( (lv_ownedMembers_20_0= ruleTMember ) )* ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )? otherlv_23= RightCurlyBracket
            {
            // InternalTypesParser.g:1704:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) )
            // InternalTypesParser.g:1705:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            {
            // InternalTypesParser.g:1705:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            // InternalTypesParser.g:1706:3: lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTClassAccess().getDeclaredTypeAccessModifierTypeAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_34);
            lv_declaredTypeAccessModifier_0_0=ruleTypeAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTClassRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeAccessModifier",
                      		lv_declaredTypeAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:1722:2: ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==ProvidedByRuntime) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalTypesParser.g:1723:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    {
                    // InternalTypesParser.g:1723:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    // InternalTypesParser.g:1724:3: lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime
                    {
                    lv_declaredProvidedByRuntime_1_0=(Token)match(input,ProvidedByRuntime,FOLLOW_35); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredProvidedByRuntime_1_0, grammarAccess.getTClassAccess().getDeclaredProvidedByRuntimeProvidedByRuntimeKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTClassRule());
                      	        }
                             		setWithLastConsumed(current, "declaredProvidedByRuntime", true, "providedByRuntime");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1738:3: ( (lv_declaredAbstract_2_0= Abstract ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==Abstract) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalTypesParser.g:1739:1: (lv_declaredAbstract_2_0= Abstract )
                    {
                    // InternalTypesParser.g:1739:1: (lv_declaredAbstract_2_0= Abstract )
                    // InternalTypesParser.g:1740:3: lv_declaredAbstract_2_0= Abstract
                    {
                    lv_declaredAbstract_2_0=(Token)match(input,Abstract,FOLLOW_36); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredAbstract_2_0, grammarAccess.getTClassAccess().getDeclaredAbstractAbstractKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTClassRule());
                      	        }
                             		setWithLastConsumed(current, "declaredAbstract", true, "abstract");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1754:3: ( (lv_declaredFinal_3_0= Final ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Final) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalTypesParser.g:1755:1: (lv_declaredFinal_3_0= Final )
                    {
                    // InternalTypesParser.g:1755:1: (lv_declaredFinal_3_0= Final )
                    // InternalTypesParser.g:1756:3: lv_declaredFinal_3_0= Final
                    {
                    lv_declaredFinal_3_0=(Token)match(input,Final,FOLLOW_37); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredFinal_3_0, grammarAccess.getTClassAccess().getDeclaredFinalFinalKeyword_3_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTClassRule());
                      	        }
                             		setWithLastConsumed(current, "declaredFinal", true, "final");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,Class,FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTClassAccess().getClassKeyword_4());
                  
            }
            // InternalTypesParser.g:1775:1: ( (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==Tilde) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalTypesParser.g:1776:1: (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator )
                    {
                    // InternalTypesParser.g:1776:1: (lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator )
                    // InternalTypesParser.g:1777:3: lv_typingStrategy_5_0= ruleTypingStrategyDefSiteOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTClassAccess().getTypingStrategyTypingStrategyDefSiteOperatorParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_11);
                    lv_typingStrategy_5_0=ruleTypingStrategyDefSiteOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                      	        }
                             		set(
                             			current, 
                             			"typingStrategy",
                              		lv_typingStrategy_5_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypingStrategyDefSiteOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1793:3: ( (lv_name_6_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:1794:1: (lv_name_6_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:1794:1: (lv_name_6_0= ruleTypesIdentifier )
            // InternalTypesParser.g:1795:3: lv_name_6_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTClassAccess().getNameTypesIdentifierParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_39);
            lv_name_6_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTClassRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_6_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:1811:2: (otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LessThanSign) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalTypesParser.g:1812:2: otherlv_7= LessThanSign ( (lv_typeVars_8_0= ruleTypeVariable ) ) (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )* otherlv_11= GreaterThanSign
                    {
                    otherlv_7=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getTClassAccess().getLessThanSignKeyword_7_0());
                          
                    }
                    // InternalTypesParser.g:1816:1: ( (lv_typeVars_8_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:1817:1: (lv_typeVars_8_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:1817:1: (lv_typeVars_8_0= ruleTypeVariable )
                    // InternalTypesParser.g:1818:3: lv_typeVars_8_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTClassAccess().getTypeVarsTypeVariableParserRuleCall_7_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_8_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_8_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:1834:2: (otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==Comma) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalTypesParser.g:1835:2: otherlv_9= Comma ( (lv_typeVars_10_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_9=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_9, grammarAccess.getTClassAccess().getCommaKeyword_7_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:1839:1: ( (lv_typeVars_10_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:1840:1: (lv_typeVars_10_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:1840:1: (lv_typeVars_10_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:1841:3: lv_typeVars_10_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTClassAccess().getTypeVarsTypeVariableParserRuleCall_7_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_10_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_10_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,GreaterThanSign,FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getTClassAccess().getGreaterThanSignKeyword_7_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:1862:3: (otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Extends) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalTypesParser.g:1863:2: otherlv_12= Extends ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) )
                    {
                    otherlv_12=(Token)match(input,Extends,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getTClassAccess().getExtendsKeyword_8_0());
                          
                    }
                    // InternalTypesParser.g:1867:1: ( (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:1868:1: (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:1868:1: (lv_superClassRef_13_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:1869:3: lv_superClassRef_13_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTClassAccess().getSuperClassRefParameterizedTypeRefNominalParserRuleCall_8_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_41);
                    lv_superClassRef_13_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                      	        }
                             		set(
                             			current, 
                             			"superClassRef",
                              		lv_superClassRef_13_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:1885:4: (otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )* )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==Implements) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalTypesParser.g:1886:2: otherlv_14= Implements ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) ) (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )*
                    {
                    otherlv_14=(Token)match(input,Implements,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getTClassAccess().getImplementsKeyword_9_0());
                          
                    }
                    // InternalTypesParser.g:1890:1: ( (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:1891:1: (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:1891:1: (lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:1892:3: lv_implementedInterfaceRefs_15_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTClassAccess().getImplementedInterfaceRefsParameterizedTypeRefNominalParserRuleCall_9_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_42);
                    lv_implementedInterfaceRefs_15_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                      	        }
                             		add(
                             			current, 
                             			"implementedInterfaceRefs",
                              		lv_implementedInterfaceRefs_15_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:1908:2: (otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==Comma) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // InternalTypesParser.g:1909:2: otherlv_16= Comma ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) )
                    	    {
                    	    otherlv_16=(Token)match(input,Comma,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_16, grammarAccess.getTClassAccess().getCommaKeyword_9_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:1913:1: ( (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal ) )
                    	    // InternalTypesParser.g:1914:1: (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal )
                    	    {
                    	    // InternalTypesParser.g:1914:1: (lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal )
                    	    // InternalTypesParser.g:1915:3: lv_implementedInterfaceRefs_17_0= ruleParameterizedTypeRefNominal
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTClassAccess().getImplementedInterfaceRefsParameterizedTypeRefNominalParserRuleCall_9_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_42);
                    	    lv_implementedInterfaceRefs_17_0=ruleParameterizedTypeRefNominal();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"implementedInterfaceRefs",
                    	              		lv_implementedInterfaceRefs_17_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalTypesParser.g:1931:6: ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==CommercialAt) && (synpred4_InternalTypesParser())) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalTypesParser.g:1931:7: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_18_0= ruleTAnnotation )
            	    {
            	    // InternalTypesParser.g:1939:1: (lv_annotations_18_0= ruleTAnnotation )
            	    // InternalTypesParser.g:1940:3: lv_annotations_18_0= ruleTAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTClassAccess().getAnnotationsTAnnotationParserRuleCall_10_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_31);
            	    lv_annotations_18_0=ruleTAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTClassRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_18_0, 
            	              		"eu.numberfour.n4js.ts.Types.TAnnotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            otherlv_19=(Token)match(input,LeftCurlyBracket,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_19, grammarAccess.getTClassAccess().getLeftCurlyBracketKeyword_11());
                  
            }
            // InternalTypesParser.g:1961:1: ( (lv_ownedMembers_20_0= ruleTMember ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==ProtectedInternal||LA40_0==PublicInternal||LA40_0==Protected||(LA40_0>=Private && LA40_0<=Project)||LA40_0==Public) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalTypesParser.g:1962:1: (lv_ownedMembers_20_0= ruleTMember )
            	    {
            	    // InternalTypesParser.g:1962:1: (lv_ownedMembers_20_0= ruleTMember )
            	    // InternalTypesParser.g:1963:3: lv_ownedMembers_20_0= ruleTMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTClassAccess().getOwnedMembersTMemberParserRuleCall_12_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_32);
            	    lv_ownedMembers_20_0=ruleTMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTClassRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedMembers",
            	              		lv_ownedMembers_20_0, 
            	              		"eu.numberfour.n4js.ts.Types.TMember");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            // InternalTypesParser.g:1979:3: ( ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )* )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LeftParenthesis) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalTypesParser.g:1979:4: ( (lv_callableCtor_21_0= ruleCallableCtor ) ) ( (lv_ownedMembers_22_0= ruleTMember ) )*
                    {
                    // InternalTypesParser.g:1979:4: ( (lv_callableCtor_21_0= ruleCallableCtor ) )
                    // InternalTypesParser.g:1980:1: (lv_callableCtor_21_0= ruleCallableCtor )
                    {
                    // InternalTypesParser.g:1980:1: (lv_callableCtor_21_0= ruleCallableCtor )
                    // InternalTypesParser.g:1981:3: lv_callableCtor_21_0= ruleCallableCtor
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTClassAccess().getCallableCtorCallableCtorParserRuleCall_13_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_33);
                    lv_callableCtor_21_0=ruleCallableCtor();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                      	        }
                             		set(
                             			current, 
                             			"callableCtor",
                              		lv_callableCtor_21_0, 
                              		"eu.numberfour.n4js.ts.Types.CallableCtor");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:1997:2: ( (lv_ownedMembers_22_0= ruleTMember ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==ProtectedInternal||LA41_0==PublicInternal||LA41_0==Protected||(LA41_0>=Private && LA41_0<=Project)||LA41_0==Public) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // InternalTypesParser.g:1998:1: (lv_ownedMembers_22_0= ruleTMember )
                    	    {
                    	    // InternalTypesParser.g:1998:1: (lv_ownedMembers_22_0= ruleTMember )
                    	    // InternalTypesParser.g:1999:3: lv_ownedMembers_22_0= ruleTMember
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTClassAccess().getOwnedMembersTMemberParserRuleCall_13_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_33);
                    	    lv_ownedMembers_22_0=ruleTMember();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTClassRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"ownedMembers",
                    	              		lv_ownedMembers_22_0, 
                    	              		"eu.numberfour.n4js.ts.Types.TMember");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_23=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_23, grammarAccess.getTClassAccess().getRightCurlyBracketKeyword_14());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTClass"


    // $ANTLR start "entryRuleTInterface"
    // InternalTypesParser.g:2028:1: entryRuleTInterface returns [EObject current=null] : iv_ruleTInterface= ruleTInterface EOF ;
    public final EObject entryRuleTInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTInterface = null;


        try {
            // InternalTypesParser.g:2029:2: (iv_ruleTInterface= ruleTInterface EOF )
            // InternalTypesParser.g:2030:2: iv_ruleTInterface= ruleTInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTInterfaceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTInterface=ruleTInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTInterface; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTInterface"


    // $ANTLR start "ruleTInterface"
    // InternalTypesParser.g:2037:1: ruleTInterface returns [EObject current=null] : ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Interface ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* otherlv_17= RightCurlyBracket ) ;
    public final EObject ruleTInterface() throws RecognitionException {
        EObject current = null;

        Token lv_declaredProvidedByRuntime_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Enumerator lv_declaredTypeAccessModifier_0_0 = null;

        AntlrDatatypeRuleToken lv_typingStrategy_3_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_typeVars_6_0 = null;

        EObject lv_typeVars_8_0 = null;

        EObject lv_superInterfaceRefs_11_0 = null;

        EObject lv_superInterfaceRefs_13_0 = null;

        EObject lv_annotations_14_0 = null;

        EObject lv_ownedMembers_16_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2040:28: ( ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Interface ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* otherlv_17= RightCurlyBracket ) )
            // InternalTypesParser.g:2041:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Interface ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* otherlv_17= RightCurlyBracket )
            {
            // InternalTypesParser.g:2041:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Interface ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* otherlv_17= RightCurlyBracket )
            // InternalTypesParser.g:2041:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Interface ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )? ( (lv_name_4_0= ruleTypesIdentifier ) ) (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )? (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )? ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )* otherlv_15= LeftCurlyBracket ( (lv_ownedMembers_16_0= ruleTMember ) )* otherlv_17= RightCurlyBracket
            {
            // InternalTypesParser.g:2041:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) )
            // InternalTypesParser.g:2042:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            {
            // InternalTypesParser.g:2042:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            // InternalTypesParser.g:2043:3: lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTInterfaceAccess().getDeclaredTypeAccessModifierTypeAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_43);
            lv_declaredTypeAccessModifier_0_0=ruleTypeAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeAccessModifier",
                      		lv_declaredTypeAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2059:2: ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==ProvidedByRuntime) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalTypesParser.g:2060:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    {
                    // InternalTypesParser.g:2060:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    // InternalTypesParser.g:2061:3: lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime
                    {
                    lv_declaredProvidedByRuntime_1_0=(Token)match(input,ProvidedByRuntime,FOLLOW_44); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredProvidedByRuntime_1_0, grammarAccess.getTInterfaceAccess().getDeclaredProvidedByRuntimeProvidedByRuntimeKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTInterfaceRule());
                      	        }
                             		setWithLastConsumed(current, "declaredProvidedByRuntime", true, "providedByRuntime");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,Interface,FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getTInterfaceAccess().getInterfaceKeyword_2());
                  
            }
            // InternalTypesParser.g:2080:1: ( (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==Tilde) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalTypesParser.g:2081:1: (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator )
                    {
                    // InternalTypesParser.g:2081:1: (lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator )
                    // InternalTypesParser.g:2082:3: lv_typingStrategy_3_0= ruleTypingStrategyDefSiteOperator
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getTypingStrategyTypingStrategyDefSiteOperatorParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_11);
                    lv_typingStrategy_3_0=ruleTypingStrategyDefSiteOperator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
                      	        }
                             		set(
                             			current, 
                             			"typingStrategy",
                              		lv_typingStrategy_3_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypingStrategyDefSiteOperator");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:2098:3: ( (lv_name_4_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:2099:1: (lv_name_4_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:2099:1: (lv_name_4_0= ruleTypesIdentifier )
            // InternalTypesParser.g:2100:3: lv_name_4_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTInterfaceAccess().getNameTypesIdentifierParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_45);
            lv_name_4_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_4_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2116:2: (otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LessThanSign) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalTypesParser.g:2117:2: otherlv_5= LessThanSign ( (lv_typeVars_6_0= ruleTypeVariable ) ) (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )* otherlv_9= GreaterThanSign
                    {
                    otherlv_5=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTInterfaceAccess().getLessThanSignKeyword_5_0());
                          
                    }
                    // InternalTypesParser.g:2121:1: ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:2122:1: (lv_typeVars_6_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:2122:1: (lv_typeVars_6_0= ruleTypeVariable )
                    // InternalTypesParser.g:2123:3: lv_typeVars_6_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getTypeVarsTypeVariableParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_6_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_6_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:2139:2: (otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==Comma) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // InternalTypesParser.g:2140:2: otherlv_7= Comma ( (lv_typeVars_8_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_7=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_7, grammarAccess.getTInterfaceAccess().getCommaKeyword_5_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:2144:1: ( (lv_typeVars_8_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:2145:1: (lv_typeVars_8_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:2145:1: (lv_typeVars_8_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:2146:3: lv_typeVars_8_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getTypeVarsTypeVariableParserRuleCall_5_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_8_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_8_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,GreaterThanSign,FOLLOW_46); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getTInterfaceAccess().getGreaterThanSignKeyword_5_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:2167:3: (otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )* )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==Extends) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // InternalTypesParser.g:2168:2: otherlv_10= Extends ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) ) (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )*
                    {
                    otherlv_10=(Token)match(input,Extends,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getTInterfaceAccess().getExtendsKeyword_6_0());
                          
                    }
                    // InternalTypesParser.g:2172:1: ( (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal ) )
                    // InternalTypesParser.g:2173:1: (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal )
                    {
                    // InternalTypesParser.g:2173:1: (lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal )
                    // InternalTypesParser.g:2174:3: lv_superInterfaceRefs_11_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getSuperInterfaceRefsParameterizedTypeRefNominalParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_42);
                    lv_superInterfaceRefs_11_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
                      	        }
                             		add(
                             			current, 
                             			"superInterfaceRefs",
                              		lv_superInterfaceRefs_11_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:2190:2: (otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==Comma) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // InternalTypesParser.g:2191:2: otherlv_12= Comma ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) )
                    	    {
                    	    otherlv_12=(Token)match(input,Comma,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_12, grammarAccess.getTInterfaceAccess().getCommaKeyword_6_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:2195:1: ( (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal ) )
                    	    // InternalTypesParser.g:2196:1: (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal )
                    	    {
                    	    // InternalTypesParser.g:2196:1: (lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal )
                    	    // InternalTypesParser.g:2197:3: lv_superInterfaceRefs_13_0= ruleParameterizedTypeRefNominal
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getSuperInterfaceRefsParameterizedTypeRefNominalParserRuleCall_6_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_42);
                    	    lv_superInterfaceRefs_13_0=ruleParameterizedTypeRefNominal();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"superInterfaceRefs",
                    	              		lv_superInterfaceRefs_13_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRefNominal");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalTypesParser.g:2213:6: ( ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation ) )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==CommercialAt) && (synpred5_InternalTypesParser())) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // InternalTypesParser.g:2213:7: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )=> (lv_annotations_14_0= ruleTAnnotation )
            	    {
            	    // InternalTypesParser.g:2221:1: (lv_annotations_14_0= ruleTAnnotation )
            	    // InternalTypesParser.g:2222:3: lv_annotations_14_0= ruleTAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getAnnotationsTAnnotationParserRuleCall_7_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_31);
            	    lv_annotations_14_0=ruleTAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_14_0, 
            	              		"eu.numberfour.n4js.ts.Types.TAnnotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);

            otherlv_15=(Token)match(input,LeftCurlyBracket,FOLLOW_33); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getTInterfaceAccess().getLeftCurlyBracketKeyword_8());
                  
            }
            // InternalTypesParser.g:2243:1: ( (lv_ownedMembers_16_0= ruleTMember ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==ProtectedInternal||LA50_0==PublicInternal||LA50_0==Protected||(LA50_0>=Private && LA50_0<=Project)||LA50_0==Public) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalTypesParser.g:2244:1: (lv_ownedMembers_16_0= ruleTMember )
            	    {
            	    // InternalTypesParser.g:2244:1: (lv_ownedMembers_16_0= ruleTMember )
            	    // InternalTypesParser.g:2245:3: lv_ownedMembers_16_0= ruleTMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTInterfaceAccess().getOwnedMembersTMemberParserRuleCall_9_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_33);
            	    lv_ownedMembers_16_0=ruleTMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTInterfaceRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedMembers",
            	              		lv_ownedMembers_16_0, 
            	              		"eu.numberfour.n4js.ts.Types.TMember");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);

            otherlv_17=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_17, grammarAccess.getTInterfaceAccess().getRightCurlyBracketKeyword_10());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTInterface"


    // $ANTLR start "entryRuleCallableCtor"
    // InternalTypesParser.g:2274:1: entryRuleCallableCtor returns [EObject current=null] : iv_ruleCallableCtor= ruleCallableCtor EOF ;
    public final EObject entryRuleCallableCtor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCallableCtor = null;


        try {
            // InternalTypesParser.g:2275:2: (iv_ruleCallableCtor= ruleCallableCtor EOF )
            // InternalTypesParser.g:2276:2: iv_ruleCallableCtor= ruleCallableCtor EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCallableCtorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCallableCtor=ruleCallableCtor();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCallableCtor; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCallableCtor"


    // $ANTLR start "ruleCallableCtor"
    // InternalTypesParser.g:2283:1: ruleCallableCtor returns [EObject current=null] : ( () otherlv_1= LeftParenthesis ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )? otherlv_5= RightParenthesis (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )? (otherlv_8= Semicolon )? ) ;
    public final EObject ruleCallableCtor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_fpars_2_0 = null;

        EObject lv_fpars_4_0 = null;

        EObject lv_returnTypeRef_7_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2286:28: ( ( () otherlv_1= LeftParenthesis ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )? otherlv_5= RightParenthesis (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )? (otherlv_8= Semicolon )? ) )
            // InternalTypesParser.g:2287:1: ( () otherlv_1= LeftParenthesis ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )? otherlv_5= RightParenthesis (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )? (otherlv_8= Semicolon )? )
            {
            // InternalTypesParser.g:2287:1: ( () otherlv_1= LeftParenthesis ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )? otherlv_5= RightParenthesis (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )? (otherlv_8= Semicolon )? )
            // InternalTypesParser.g:2287:2: () otherlv_1= LeftParenthesis ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )? otherlv_5= RightParenthesis (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )? (otherlv_8= Semicolon )?
            {
            // InternalTypesParser.g:2287:2: ()
            // InternalTypesParser.g:2288:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCallableCtorAccess().getTMethodAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_47); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCallableCtorAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalTypesParser.g:2298:1: ( ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )* )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==AssignmnentCompatible||(LA52_0>=AutoboxedType && LA52_0<=Abstract)||(LA52_0>=Private && LA52_0<=Static)||LA52_0==Union||(LA52_0>=From && LA52_0<=Null)||(LA52_0>=Type && LA52_0<=Void)||(LA52_0>=FullStopFullStopFullStop && LA52_0<=Get)||LA52_0==Set||LA52_0==As||LA52_0==RULE_IDENTIFIER) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalTypesParser.g:2298:2: ( (lv_fpars_2_0= ruleTFormalParameter ) ) (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )*
                    {
                    // InternalTypesParser.g:2298:2: ( (lv_fpars_2_0= ruleTFormalParameter ) )
                    // InternalTypesParser.g:2299:1: (lv_fpars_2_0= ruleTFormalParameter )
                    {
                    // InternalTypesParser.g:2299:1: (lv_fpars_2_0= ruleTFormalParameter )
                    // InternalTypesParser.g:2300:3: lv_fpars_2_0= ruleTFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCallableCtorAccess().getFparsTFormalParameterParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_7);
                    lv_fpars_2_0=ruleTFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCallableCtorRule());
                      	        }
                             		add(
                             			current, 
                             			"fpars",
                              		lv_fpars_2_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:2316:2: (otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==Comma) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // InternalTypesParser.g:2317:2: otherlv_3= Comma ( (lv_fpars_4_0= ruleTFormalParameter ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_48); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCallableCtorAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:2321:1: ( (lv_fpars_4_0= ruleTFormalParameter ) )
                    	    // InternalTypesParser.g:2322:1: (lv_fpars_4_0= ruleTFormalParameter )
                    	    {
                    	    // InternalTypesParser.g:2322:1: (lv_fpars_4_0= ruleTFormalParameter )
                    	    // InternalTypesParser.g:2323:3: lv_fpars_4_0= ruleTFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getCallableCtorAccess().getFparsTFormalParameterParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_7);
                    	    lv_fpars_4_0=ruleTFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getCallableCtorRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"fpars",
                    	              		lv_fpars_4_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_49); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getCallableCtorAccess().getRightParenthesisKeyword_3());
                  
            }
            // InternalTypesParser.g:2344:1: (otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==Colon) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalTypesParser.g:2345:2: otherlv_6= Colon ( (lv_returnTypeRef_7_0= ruleTypeRef ) )
                    {
                    otherlv_6=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getCallableCtorAccess().getColonKeyword_4_0());
                          
                    }
                    // InternalTypesParser.g:2349:1: ( (lv_returnTypeRef_7_0= ruleTypeRef ) )
                    // InternalTypesParser.g:2350:1: (lv_returnTypeRef_7_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:2350:1: (lv_returnTypeRef_7_0= ruleTypeRef )
                    // InternalTypesParser.g:2351:3: lv_returnTypeRef_7_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCallableCtorAccess().getReturnTypeRefTypeRefParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_50);
                    lv_returnTypeRef_7_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCallableCtorRule());
                      	        }
                             		set(
                             			current, 
                             			"returnTypeRef",
                              		lv_returnTypeRef_7_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:2367:4: (otherlv_8= Semicolon )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==Semicolon) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalTypesParser.g:2368:2: otherlv_8= Semicolon
                    {
                    otherlv_8=(Token)match(input,Semicolon,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getCallableCtorAccess().getSemicolonKeyword_5());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCallableCtor"


    // $ANTLR start "entryRuleTMember"
    // InternalTypesParser.g:2380:1: entryRuleTMember returns [EObject current=null] : iv_ruleTMember= ruleTMember EOF ;
    public final EObject entryRuleTMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTMember = null;


        try {
            // InternalTypesParser.g:2381:2: (iv_ruleTMember= ruleTMember EOF )
            // InternalTypesParser.g:2382:2: iv_ruleTMember= ruleTMember EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTMemberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTMember=ruleTMember();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTMember; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTMember"


    // $ANTLR start "ruleTMember"
    // InternalTypesParser.g:2389:1: ruleTMember returns [EObject current=null] : ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter ) | ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter ) | ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod ) | this_TField_3= ruleTField ) ;
    public final EObject ruleTMember() throws RecognitionException {
        EObject current = null;

        EObject this_TGetter_0 = null;

        EObject this_TSetter_1 = null;

        EObject this_TMethod_2 = null;

        EObject this_TField_3 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2392:28: ( ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter ) | ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter ) | ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod ) | this_TField_3= ruleTField ) )
            // InternalTypesParser.g:2393:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter ) | ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter ) | ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod ) | this_TField_3= ruleTField )
            {
            // InternalTypesParser.g:2393:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter ) | ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter ) | ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod ) | this_TField_3= ruleTField )
            int alt55=4;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // InternalTypesParser.g:2393:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter )
                    {
                    // InternalTypesParser.g:2393:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter )
                    // InternalTypesParser.g:2393:3: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTMemberAccess().getTGetterParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TGetter_0=ruleTGetter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TGetter_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:2434:6: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter )
                    {
                    // InternalTypesParser.g:2434:6: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter )
                    // InternalTypesParser.g:2434:7: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTMemberAccess().getTSetterParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TSetter_1=ruleTSetter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TSetter_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:2475:6: ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod )
                    {
                    // InternalTypesParser.g:2475:6: ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod )
                    // InternalTypesParser.g:2475:7: ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTMemberAccess().getTMethodParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TMethod_2=ruleTMethod();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TMethod_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:2530:5: this_TField_3= ruleTField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTMemberAccess().getTFieldParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TField_3=ruleTField();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TField_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTMember"


    // $ANTLR start "entryRuleTMethod"
    // InternalTypesParser.g:2546:1: entryRuleTMethod returns [EObject current=null] : iv_ruleTMethod= ruleTMethod EOF ;
    public final EObject entryRuleTMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTMethod = null;


        try {
            // InternalTypesParser.g:2547:2: (iv_ruleTMethod= ruleTMethod EOF )
            // InternalTypesParser.g:2548:2: iv_ruleTMethod= ruleTMethod EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTMethodRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTMethod=ruleTMethod();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTMethod; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTMethod"


    // $ANTLR start "ruleTMethod"
    // InternalTypesParser.g:2555:1: ruleTMethod returns [EObject current=null] : ( ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) ) ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )? otherlv_14= RightParenthesis otherlv_15= Colon ( (lv_returnTypeRef_16_0= ruleTypeRef ) ) (otherlv_17= Semicolon )? ) ;
    public final EObject ruleTMethod() throws RecognitionException {
        EObject current = null;

        Token lv_declaredStatic_1_0=null;
        Token lv_declaredAbstract_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Enumerator lv_declaredMemberAccessModifier_0_0 = null;

        EObject lv_typeVars_4_0 = null;

        EObject lv_typeVars_6_0 = null;

        AntlrDatatypeRuleToken lv_name_8_0 = null;

        AntlrDatatypeRuleToken lv_name_9_0 = null;

        EObject lv_fpars_11_0 = null;

        EObject lv_fpars_13_0 = null;

        EObject lv_returnTypeRef_16_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2558:28: ( ( ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) ) ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )? otherlv_14= RightParenthesis otherlv_15= Colon ( (lv_returnTypeRef_16_0= ruleTypeRef ) ) (otherlv_17= Semicolon )? ) )
            // InternalTypesParser.g:2559:1: ( ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) ) ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )? otherlv_14= RightParenthesis otherlv_15= Colon ( (lv_returnTypeRef_16_0= ruleTypeRef ) ) (otherlv_17= Semicolon )? )
            {
            // InternalTypesParser.g:2559:1: ( ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) ) ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )? otherlv_14= RightParenthesis otherlv_15= Colon ( (lv_returnTypeRef_16_0= ruleTypeRef ) ) (otherlv_17= Semicolon )? )
            // InternalTypesParser.g:2559:2: ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) ) ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )? otherlv_14= RightParenthesis otherlv_15= Colon ( (lv_returnTypeRef_16_0= ruleTypeRef ) ) (otherlv_17= Semicolon )?
            {
            // InternalTypesParser.g:2559:2: ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis ) )
            // InternalTypesParser.g:2559:3: ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=> ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis )
            {
            // InternalTypesParser.g:2603:5: ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis )
            // InternalTypesParser.g:2603:6: ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )? (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) ) otherlv_10= LeftParenthesis
            {
            // InternalTypesParser.g:2603:6: ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) )
            // InternalTypesParser.g:2604:1: (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier )
            {
            // InternalTypesParser.g:2604:1: (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier )
            // InternalTypesParser.g:2605:3: lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTMethodAccess().getDeclaredMemberAccessModifierMemberAccessModifierEnumRuleCall_0_0_0_0()); 
              	    
            }
            pushFollow(FOLLOW_51);
            lv_declaredMemberAccessModifier_0_0=ruleMemberAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTMethodRule());
              	        }
                     		set(
                     			current, 
                     			"declaredMemberAccessModifier",
                      		lv_declaredMemberAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.MemberAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2621:2: ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_declaredAbstract_2_0= Abstract ) ) )?
            int alt56=3;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==Static) ) {
                alt56=1;
            }
            else if ( (LA56_0==Abstract) ) {
                int LA56_2 = input.LA(2);

                if ( (LA56_2==AssignmnentCompatible||(LA56_2>=AutoboxedType && LA56_2<=VirtualBase)||LA56_2==Primitive||(LA56_2>=Undefined && LA56_2<=Abstract)||(LA56_2>=Project && LA56_2<=Object)||LA56_2==Union||(LA56_2>=From && LA56_2<=Null)||(LA56_2>=Type && LA56_2<=Void)||(LA56_2>=Any && LA56_2<=Get)||LA56_2==Set||LA56_2==As||LA56_2==LessThanSign||LA56_2==LeftSquareBracket||LA56_2==RULE_IDENTIFIER) ) {
                    alt56=2;
                }
            }
            switch (alt56) {
                case 1 :
                    // InternalTypesParser.g:2621:3: ( (lv_declaredStatic_1_0= Static ) )
                    {
                    // InternalTypesParser.g:2621:3: ( (lv_declaredStatic_1_0= Static ) )
                    // InternalTypesParser.g:2622:1: (lv_declaredStatic_1_0= Static )
                    {
                    // InternalTypesParser.g:2622:1: (lv_declaredStatic_1_0= Static )
                    // InternalTypesParser.g:2623:3: lv_declaredStatic_1_0= Static
                    {
                    lv_declaredStatic_1_0=(Token)match(input,Static,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredStatic_1_0, grammarAccess.getTMethodAccess().getDeclaredStaticStaticKeyword_0_0_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTMethodRule());
                      	        }
                             		setWithLastConsumed(current, "declaredStatic", true, "static");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:2638:6: ( (lv_declaredAbstract_2_0= Abstract ) )
                    {
                    // InternalTypesParser.g:2638:6: ( (lv_declaredAbstract_2_0= Abstract ) )
                    // InternalTypesParser.g:2639:1: (lv_declaredAbstract_2_0= Abstract )
                    {
                    // InternalTypesParser.g:2639:1: (lv_declaredAbstract_2_0= Abstract )
                    // InternalTypesParser.g:2640:3: lv_declaredAbstract_2_0= Abstract
                    {
                    lv_declaredAbstract_2_0=(Token)match(input,Abstract,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredAbstract_2_0, grammarAccess.getTMethodAccess().getDeclaredAbstractAbstractKeyword_0_0_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTMethodRule());
                      	        }
                             		setWithLastConsumed(current, "declaredAbstract", true, "abstract");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:2654:4: (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==LessThanSign) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalTypesParser.g:2655:2: otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign
                    {
                    otherlv_3=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTMethodAccess().getLessThanSignKeyword_0_0_2_0());
                          
                    }
                    // InternalTypesParser.g:2659:1: ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:2660:1: (lv_typeVars_4_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:2660:1: (lv_typeVars_4_0= ruleTypeVariable )
                    // InternalTypesParser.g:2661:3: lv_typeVars_4_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTMethodAccess().getTypeVarsTypeVariableParserRuleCall_0_0_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_4_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_4_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:2677:2: (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==Comma) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalTypesParser.g:2678:2: otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_5=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getTMethodAccess().getCommaKeyword_0_0_2_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:2682:1: ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:2683:1: (lv_typeVars_6_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:2683:1: (lv_typeVars_6_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:2684:3: lv_typeVars_6_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTMethodAccess().getTypeVarsTypeVariableParserRuleCall_0_0_2_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_6_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_6_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,GreaterThanSign,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getTMethodAccess().getGreaterThanSignKeyword_0_0_2_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:2705:3: ( ( (lv_name_8_0= ruleTypesIdentifier ) ) | ( (lv_name_9_0= ruleTypesComputedPropertyName ) ) )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==AssignmnentCompatible||(LA59_0>=AutoboxedType && LA59_0<=VirtualBase)||LA59_0==Primitive||(LA59_0>=Undefined && LA59_0<=Abstract)||(LA59_0>=Project && LA59_0<=Object)||LA59_0==Union||(LA59_0>=From && LA59_0<=Null)||(LA59_0>=Type && LA59_0<=Void)||(LA59_0>=Any && LA59_0<=Get)||LA59_0==Set||LA59_0==As||LA59_0==RULE_IDENTIFIER) ) {
                alt59=1;
            }
            else if ( (LA59_0==LeftSquareBracket) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // InternalTypesParser.g:2705:4: ( (lv_name_8_0= ruleTypesIdentifier ) )
                    {
                    // InternalTypesParser.g:2705:4: ( (lv_name_8_0= ruleTypesIdentifier ) )
                    // InternalTypesParser.g:2706:1: (lv_name_8_0= ruleTypesIdentifier )
                    {
                    // InternalTypesParser.g:2706:1: (lv_name_8_0= ruleTypesIdentifier )
                    // InternalTypesParser.g:2707:3: lv_name_8_0= ruleTypesIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTMethodAccess().getNameTypesIdentifierParserRuleCall_0_0_3_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_8_0=ruleTypesIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_8_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:2724:6: ( (lv_name_9_0= ruleTypesComputedPropertyName ) )
                    {
                    // InternalTypesParser.g:2724:6: ( (lv_name_9_0= ruleTypesComputedPropertyName ) )
                    // InternalTypesParser.g:2725:1: (lv_name_9_0= ruleTypesComputedPropertyName )
                    {
                    // InternalTypesParser.g:2725:1: (lv_name_9_0= ruleTypesComputedPropertyName )
                    // InternalTypesParser.g:2726:3: lv_name_9_0= ruleTypesComputedPropertyName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTMethodAccess().getNameTypesComputedPropertyNameParserRuleCall_0_0_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_9_0=ruleTypesComputedPropertyName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_9_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesComputedPropertyName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,LeftParenthesis,FOLLOW_47); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_10, grammarAccess.getTMethodAccess().getLeftParenthesisKeyword_0_0_4());
                  
            }

            }


            }

            // InternalTypesParser.g:2747:3: ( ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==AssignmnentCompatible||(LA61_0>=AutoboxedType && LA61_0<=Abstract)||(LA61_0>=Private && LA61_0<=Static)||LA61_0==Union||(LA61_0>=From && LA61_0<=Null)||(LA61_0>=Type && LA61_0<=Void)||(LA61_0>=FullStopFullStopFullStop && LA61_0<=Get)||LA61_0==Set||LA61_0==As||LA61_0==RULE_IDENTIFIER) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalTypesParser.g:2747:4: ( (lv_fpars_11_0= ruleTFormalParameter ) ) (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )*
                    {
                    // InternalTypesParser.g:2747:4: ( (lv_fpars_11_0= ruleTFormalParameter ) )
                    // InternalTypesParser.g:2748:1: (lv_fpars_11_0= ruleTFormalParameter )
                    {
                    // InternalTypesParser.g:2748:1: (lv_fpars_11_0= ruleTFormalParameter )
                    // InternalTypesParser.g:2749:3: lv_fpars_11_0= ruleTFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTMethodAccess().getFparsTFormalParameterParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_7);
                    lv_fpars_11_0=ruleTFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                      	        }
                             		add(
                             			current, 
                             			"fpars",
                              		lv_fpars_11_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:2765:2: (otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==Comma) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // InternalTypesParser.g:2766:2: otherlv_12= Comma ( (lv_fpars_13_0= ruleTFormalParameter ) )
                    	    {
                    	    otherlv_12=(Token)match(input,Comma,FOLLOW_48); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_12, grammarAccess.getTMethodAccess().getCommaKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:2770:1: ( (lv_fpars_13_0= ruleTFormalParameter ) )
                    	    // InternalTypesParser.g:2771:1: (lv_fpars_13_0= ruleTFormalParameter )
                    	    {
                    	    // InternalTypesParser.g:2771:1: (lv_fpars_13_0= ruleTFormalParameter )
                    	    // InternalTypesParser.g:2772:3: lv_fpars_13_0= ruleTFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTMethodAccess().getFparsTFormalParameterParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_7);
                    	    lv_fpars_13_0=ruleTFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTMethodRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"fpars",
                    	              		lv_fpars_13_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_14=(Token)match(input,RightParenthesis,FOLLOW_53); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_14, grammarAccess.getTMethodAccess().getRightParenthesisKeyword_2());
                  
            }
            otherlv_15=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getTMethodAccess().getColonKeyword_3());
                  
            }
            // InternalTypesParser.g:2798:1: ( (lv_returnTypeRef_16_0= ruleTypeRef ) )
            // InternalTypesParser.g:2799:1: (lv_returnTypeRef_16_0= ruleTypeRef )
            {
            // InternalTypesParser.g:2799:1: (lv_returnTypeRef_16_0= ruleTypeRef )
            // InternalTypesParser.g:2800:3: lv_returnTypeRef_16_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTMethodAccess().getReturnTypeRefTypeRefParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_50);
            lv_returnTypeRef_16_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTMethodRule());
              	        }
                     		set(
                     			current, 
                     			"returnTypeRef",
                      		lv_returnTypeRef_16_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2816:2: (otherlv_17= Semicolon )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==Semicolon) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalTypesParser.g:2817:2: otherlv_17= Semicolon
                    {
                    otherlv_17=(Token)match(input,Semicolon,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getTMethodAccess().getSemicolonKeyword_5());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTMethod"


    // $ANTLR start "entryRuleTField"
    // InternalTypesParser.g:2829:1: entryRuleTField returns [EObject current=null] : iv_ruleTField= ruleTField EOF ;
    public final EObject entryRuleTField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTField = null;


        try {
            // InternalTypesParser.g:2830:2: (iv_ruleTField= ruleTField EOF )
            // InternalTypesParser.g:2831:2: iv_ruleTField= ruleTField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTFieldRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTField=ruleTField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTField; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTField"


    // $ANTLR start "ruleTField"
    // InternalTypesParser.g:2838:1: ruleTField returns [EObject current=null] : ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )? ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) ) otherlv_6= Colon ( (lv_typeRef_7_0= ruleTypeRef ) ) (otherlv_8= Semicolon )? ) ;
    public final EObject ruleTField() throws RecognitionException {
        EObject current = null;

        Token lv_declaredStatic_1_0=null;
        Token lv_const_2_0=null;
        Token lv_declaredFinal_3_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_declaredMemberAccessModifier_0_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_typeRef_7_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2841:28: ( ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )? ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) ) otherlv_6= Colon ( (lv_typeRef_7_0= ruleTypeRef ) ) (otherlv_8= Semicolon )? ) )
            // InternalTypesParser.g:2842:1: ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )? ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) ) otherlv_6= Colon ( (lv_typeRef_7_0= ruleTypeRef ) ) (otherlv_8= Semicolon )? )
            {
            // InternalTypesParser.g:2842:1: ( ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )? ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) ) otherlv_6= Colon ( (lv_typeRef_7_0= ruleTypeRef ) ) (otherlv_8= Semicolon )? )
            // InternalTypesParser.g:2842:2: ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )? ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) ) otherlv_6= Colon ( (lv_typeRef_7_0= ruleTypeRef ) ) (otherlv_8= Semicolon )?
            {
            // InternalTypesParser.g:2842:2: ( (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier ) )
            // InternalTypesParser.g:2843:1: (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier )
            {
            // InternalTypesParser.g:2843:1: (lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier )
            // InternalTypesParser.g:2844:3: lv_declaredMemberAccessModifier_0_0= ruleMemberAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFieldAccess().getDeclaredMemberAccessModifierMemberAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_54);
            lv_declaredMemberAccessModifier_0_0=ruleMemberAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFieldRule());
              	        }
                     		set(
                     			current, 
                     			"declaredMemberAccessModifier",
                      		lv_declaredMemberAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.MemberAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2860:2: ( ( (lv_declaredStatic_1_0= Static ) ) | ( (lv_const_2_0= Const ) ) | ( (lv_declaredFinal_3_0= Final ) ) )?
            int alt63=4;
            switch ( input.LA(1) ) {
                case Static:
                    {
                    alt63=1;
                    }
                    break;
                case Const:
                    {
                    alt63=2;
                    }
                    break;
                case Final:
                    {
                    alt63=3;
                    }
                    break;
            }

            switch (alt63) {
                case 1 :
                    // InternalTypesParser.g:2860:3: ( (lv_declaredStatic_1_0= Static ) )
                    {
                    // InternalTypesParser.g:2860:3: ( (lv_declaredStatic_1_0= Static ) )
                    // InternalTypesParser.g:2861:1: (lv_declaredStatic_1_0= Static )
                    {
                    // InternalTypesParser.g:2861:1: (lv_declaredStatic_1_0= Static )
                    // InternalTypesParser.g:2862:3: lv_declaredStatic_1_0= Static
                    {
                    lv_declaredStatic_1_0=(Token)match(input,Static,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredStatic_1_0, grammarAccess.getTFieldAccess().getDeclaredStaticStaticKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTFieldRule());
                      	        }
                             		setWithLastConsumed(current, "declaredStatic", true, "static");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:2877:6: ( (lv_const_2_0= Const ) )
                    {
                    // InternalTypesParser.g:2877:6: ( (lv_const_2_0= Const ) )
                    // InternalTypesParser.g:2878:1: (lv_const_2_0= Const )
                    {
                    // InternalTypesParser.g:2878:1: (lv_const_2_0= Const )
                    // InternalTypesParser.g:2879:3: lv_const_2_0= Const
                    {
                    lv_const_2_0=(Token)match(input,Const,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_const_2_0, grammarAccess.getTFieldAccess().getConstConstKeyword_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTFieldRule());
                      	        }
                             		setWithLastConsumed(current, "const", true, "const");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:2894:6: ( (lv_declaredFinal_3_0= Final ) )
                    {
                    // InternalTypesParser.g:2894:6: ( (lv_declaredFinal_3_0= Final ) )
                    // InternalTypesParser.g:2895:1: (lv_declaredFinal_3_0= Final )
                    {
                    // InternalTypesParser.g:2895:1: (lv_declaredFinal_3_0= Final )
                    // InternalTypesParser.g:2896:3: lv_declaredFinal_3_0= Final
                    {
                    lv_declaredFinal_3_0=(Token)match(input,Final,FOLLOW_51); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredFinal_3_0, grammarAccess.getTFieldAccess().getDeclaredFinalFinalKeyword_1_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTFieldRule());
                      	        }
                             		setWithLastConsumed(current, "declaredFinal", true, "final");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTypesParser.g:2910:4: ( ( (lv_name_4_0= ruleTypesIdentifier ) ) | ( (lv_name_5_0= ruleTypesComputedPropertyName ) ) )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==AssignmnentCompatible||(LA64_0>=AutoboxedType && LA64_0<=VirtualBase)||LA64_0==Primitive||(LA64_0>=Undefined && LA64_0<=Abstract)||(LA64_0>=Project && LA64_0<=Object)||LA64_0==Union||(LA64_0>=From && LA64_0<=Null)||(LA64_0>=Type && LA64_0<=Void)||(LA64_0>=Any && LA64_0<=Get)||LA64_0==Set||LA64_0==As||LA64_0==RULE_IDENTIFIER) ) {
                alt64=1;
            }
            else if ( (LA64_0==LeftSquareBracket) ) {
                alt64=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // InternalTypesParser.g:2910:5: ( (lv_name_4_0= ruleTypesIdentifier ) )
                    {
                    // InternalTypesParser.g:2910:5: ( (lv_name_4_0= ruleTypesIdentifier ) )
                    // InternalTypesParser.g:2911:1: (lv_name_4_0= ruleTypesIdentifier )
                    {
                    // InternalTypesParser.g:2911:1: (lv_name_4_0= ruleTypesIdentifier )
                    // InternalTypesParser.g:2912:3: lv_name_4_0= ruleTypesIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTFieldAccess().getNameTypesIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_53);
                    lv_name_4_0=ruleTypesIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTFieldRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:2929:6: ( (lv_name_5_0= ruleTypesComputedPropertyName ) )
                    {
                    // InternalTypesParser.g:2929:6: ( (lv_name_5_0= ruleTypesComputedPropertyName ) )
                    // InternalTypesParser.g:2930:1: (lv_name_5_0= ruleTypesComputedPropertyName )
                    {
                    // InternalTypesParser.g:2930:1: (lv_name_5_0= ruleTypesComputedPropertyName )
                    // InternalTypesParser.g:2931:3: lv_name_5_0= ruleTypesComputedPropertyName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTFieldAccess().getNameTypesComputedPropertyNameParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_53);
                    lv_name_5_0=ruleTypesComputedPropertyName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTFieldRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_5_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesComputedPropertyName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getTFieldAccess().getColonKeyword_3());
                  
            }
            // InternalTypesParser.g:2952:1: ( (lv_typeRef_7_0= ruleTypeRef ) )
            // InternalTypesParser.g:2953:1: (lv_typeRef_7_0= ruleTypeRef )
            {
            // InternalTypesParser.g:2953:1: (lv_typeRef_7_0= ruleTypeRef )
            // InternalTypesParser.g:2954:3: lv_typeRef_7_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFieldAccess().getTypeRefTypeRefParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_50);
            lv_typeRef_7_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFieldRule());
              	        }
                     		set(
                     			current, 
                     			"typeRef",
                      		lv_typeRef_7_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:2970:2: (otherlv_8= Semicolon )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==Semicolon) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalTypesParser.g:2971:2: otherlv_8= Semicolon
                    {
                    otherlv_8=(Token)match(input,Semicolon,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getTFieldAccess().getSemicolonKeyword_5());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTField"


    // $ANTLR start "entryRuleTGetter"
    // InternalTypesParser.g:2983:1: entryRuleTGetter returns [EObject current=null] : iv_ruleTGetter= ruleTGetter EOF ;
    public final EObject entryRuleTGetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTGetter = null;


        try {
            // InternalTypesParser.g:2984:2: (iv_ruleTGetter= ruleTGetter EOF )
            // InternalTypesParser.g:2985:2: iv_ruleTGetter= ruleTGetter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTGetterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTGetter=ruleTGetter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTGetter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTGetter"


    // $ANTLR start "ruleTGetter"
    // InternalTypesParser.g:2992:1: ruleTGetter returns [EObject current=null] : ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis otherlv_8= RightParenthesis otherlv_9= Colon ( (lv_declaredTypeRef_10_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTGetter() throws RecognitionException {
        EObject current = null;

        Token lv_declaredAbstract_2_0=null;
        Token lv_declaredStatic_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Enumerator lv_declaredMemberAccessModifier_1_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;

        EObject lv_declaredTypeRef_10_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:2995:28: ( ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis otherlv_8= RightParenthesis otherlv_9= Colon ( (lv_declaredTypeRef_10_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:2996:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis otherlv_8= RightParenthesis otherlv_9= Colon ( (lv_declaredTypeRef_10_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:2996:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis otherlv_8= RightParenthesis otherlv_9= Colon ( (lv_declaredTypeRef_10_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:2996:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis otherlv_8= RightParenthesis otherlv_9= Colon ( (lv_declaredTypeRef_10_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:2996:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) )
            // InternalTypesParser.g:2996:3: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) )
            {
            // InternalTypesParser.g:3027:7: ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) )
            // InternalTypesParser.g:3027:8: () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Get ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) )
            {
            // InternalTypesParser.g:3027:8: ()
            // InternalTypesParser.g:3028:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTGetterAccess().getTGetterAction_0_0_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:3033:2: ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) )
            // InternalTypesParser.g:3034:1: (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier )
            {
            // InternalTypesParser.g:3034:1: (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier )
            // InternalTypesParser.g:3035:3: lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTGetterAccess().getDeclaredMemberAccessModifierMemberAccessModifierEnumRuleCall_0_0_1_0()); 
              	    
            }
            pushFollow(FOLLOW_55);
            lv_declaredMemberAccessModifier_1_0=ruleMemberAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTGetterRule());
              	        }
                     		set(
                     			current, 
                     			"declaredMemberAccessModifier",
                      		lv_declaredMemberAccessModifier_1_0, 
                      		"eu.numberfour.n4js.ts.Types.MemberAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:3051:2: ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )?
            int alt66=3;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==Abstract) ) {
                alt66=1;
            }
            else if ( (LA66_0==Static) ) {
                alt66=2;
            }
            switch (alt66) {
                case 1 :
                    // InternalTypesParser.g:3051:3: ( (lv_declaredAbstract_2_0= Abstract ) )
                    {
                    // InternalTypesParser.g:3051:3: ( (lv_declaredAbstract_2_0= Abstract ) )
                    // InternalTypesParser.g:3052:1: (lv_declaredAbstract_2_0= Abstract )
                    {
                    // InternalTypesParser.g:3052:1: (lv_declaredAbstract_2_0= Abstract )
                    // InternalTypesParser.g:3053:3: lv_declaredAbstract_2_0= Abstract
                    {
                    lv_declaredAbstract_2_0=(Token)match(input,Abstract,FOLLOW_56); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredAbstract_2_0, grammarAccess.getTGetterAccess().getDeclaredAbstractAbstractKeyword_0_0_2_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTGetterRule());
                      	        }
                             		setWithLastConsumed(current, "declaredAbstract", true, "abstract");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3068:6: ( (lv_declaredStatic_3_0= Static ) )
                    {
                    // InternalTypesParser.g:3068:6: ( (lv_declaredStatic_3_0= Static ) )
                    // InternalTypesParser.g:3069:1: (lv_declaredStatic_3_0= Static )
                    {
                    // InternalTypesParser.g:3069:1: (lv_declaredStatic_3_0= Static )
                    // InternalTypesParser.g:3070:3: lv_declaredStatic_3_0= Static
                    {
                    lv_declaredStatic_3_0=(Token)match(input,Static,FOLLOW_56); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredStatic_3_0, grammarAccess.getTGetterAccess().getDeclaredStaticStaticKeyword_0_0_2_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTGetterRule());
                      	        }
                             		setWithLastConsumed(current, "declaredStatic", true, "static");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,Get,FOLLOW_51); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTGetterAccess().getGetKeyword_0_0_3());
                  
            }
            // InternalTypesParser.g:3089:1: ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==AssignmnentCompatible||(LA67_0>=AutoboxedType && LA67_0<=VirtualBase)||LA67_0==Primitive||(LA67_0>=Undefined && LA67_0<=Abstract)||(LA67_0>=Project && LA67_0<=Object)||LA67_0==Union||(LA67_0>=From && LA67_0<=Null)||(LA67_0>=Type && LA67_0<=Void)||(LA67_0>=Any && LA67_0<=Get)||LA67_0==Set||LA67_0==As||LA67_0==RULE_IDENTIFIER) ) {
                alt67=1;
            }
            else if ( (LA67_0==LeftSquareBracket) ) {
                alt67=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // InternalTypesParser.g:3089:2: ( (lv_name_5_0= ruleTypesIdentifier ) )
                    {
                    // InternalTypesParser.g:3089:2: ( (lv_name_5_0= ruleTypesIdentifier ) )
                    // InternalTypesParser.g:3090:1: (lv_name_5_0= ruleTypesIdentifier )
                    {
                    // InternalTypesParser.g:3090:1: (lv_name_5_0= ruleTypesIdentifier )
                    // InternalTypesParser.g:3091:3: lv_name_5_0= ruleTypesIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTGetterAccess().getNameTypesIdentifierParserRuleCall_0_0_4_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_5_0=ruleTypesIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTGetterRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_5_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3108:6: ( (lv_name_6_0= ruleTypesComputedPropertyName ) )
                    {
                    // InternalTypesParser.g:3108:6: ( (lv_name_6_0= ruleTypesComputedPropertyName ) )
                    // InternalTypesParser.g:3109:1: (lv_name_6_0= ruleTypesComputedPropertyName )
                    {
                    // InternalTypesParser.g:3109:1: (lv_name_6_0= ruleTypesComputedPropertyName )
                    // InternalTypesParser.g:3110:3: lv_name_6_0= ruleTypesComputedPropertyName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTGetterAccess().getNameTypesComputedPropertyNameParserRuleCall_0_0_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_6_0=ruleTypesComputedPropertyName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTGetterRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_6_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesComputedPropertyName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            otherlv_7=(Token)match(input,LeftParenthesis,FOLLOW_57); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getTGetterAccess().getLeftParenthesisKeyword_1());
                  
            }
            otherlv_8=(Token)match(input,RightParenthesis,FOLLOW_53); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getTGetterAccess().getRightParenthesisKeyword_2());
                  
            }
            otherlv_9=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getTGetterAccess().getColonKeyword_3());
                  
            }
            // InternalTypesParser.g:3141:1: ( (lv_declaredTypeRef_10_0= ruleTypeRef ) )
            // InternalTypesParser.g:3142:1: (lv_declaredTypeRef_10_0= ruleTypeRef )
            {
            // InternalTypesParser.g:3142:1: (lv_declaredTypeRef_10_0= ruleTypeRef )
            // InternalTypesParser.g:3143:3: lv_declaredTypeRef_10_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTGetterAccess().getDeclaredTypeRefTypeRefParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_declaredTypeRef_10_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTGetterRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeRef",
                      		lv_declaredTypeRef_10_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTGetter"


    // $ANTLR start "entryRuleTSetter"
    // InternalTypesParser.g:3167:1: entryRuleTSetter returns [EObject current=null] : iv_ruleTSetter= ruleTSetter EOF ;
    public final EObject entryRuleTSetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTSetter = null;


        try {
            // InternalTypesParser.g:3168:2: (iv_ruleTSetter= ruleTSetter EOF )
            // InternalTypesParser.g:3169:2: iv_ruleTSetter= ruleTSetter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTSetterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTSetter=ruleTSetter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTSetter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTSetter"


    // $ANTLR start "ruleTSetter"
    // InternalTypesParser.g:3176:1: ruleTSetter returns [EObject current=null] : ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis ( (lv_fpar_8_0= ruleTFormalParameter ) ) otherlv_9= RightParenthesis ) ;
    public final EObject ruleTSetter() throws RecognitionException {
        EObject current = null;

        Token lv_declaredAbstract_2_0=null;
        Token lv_declaredStatic_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Enumerator lv_declaredMemberAccessModifier_1_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;

        EObject lv_fpar_8_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3179:28: ( ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis ( (lv_fpar_8_0= ruleTFormalParameter ) ) otherlv_9= RightParenthesis ) )
            // InternalTypesParser.g:3180:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis ( (lv_fpar_8_0= ruleTFormalParameter ) ) otherlv_9= RightParenthesis )
            {
            // InternalTypesParser.g:3180:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis ( (lv_fpar_8_0= ruleTFormalParameter ) ) otherlv_9= RightParenthesis )
            // InternalTypesParser.g:3180:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) ) otherlv_7= LeftParenthesis ( (lv_fpar_8_0= ruleTFormalParameter ) ) otherlv_9= RightParenthesis
            {
            // InternalTypesParser.g:3180:2: ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) ) )
            // InternalTypesParser.g:3180:3: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=> ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) )
            {
            // InternalTypesParser.g:3211:7: ( () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) ) )
            // InternalTypesParser.g:3211:8: () ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) ) ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )? otherlv_4= Set ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) )
            {
            // InternalTypesParser.g:3211:8: ()
            // InternalTypesParser.g:3212:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTSetterAccess().getTSetterAction_0_0_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:3217:2: ( (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier ) )
            // InternalTypesParser.g:3218:1: (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier )
            {
            // InternalTypesParser.g:3218:1: (lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier )
            // InternalTypesParser.g:3219:3: lv_declaredMemberAccessModifier_1_0= ruleMemberAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTSetterAccess().getDeclaredMemberAccessModifierMemberAccessModifierEnumRuleCall_0_0_1_0()); 
              	    
            }
            pushFollow(FOLLOW_58);
            lv_declaredMemberAccessModifier_1_0=ruleMemberAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTSetterRule());
              	        }
                     		set(
                     			current, 
                     			"declaredMemberAccessModifier",
                      		lv_declaredMemberAccessModifier_1_0, 
                      		"eu.numberfour.n4js.ts.Types.MemberAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:3235:2: ( ( (lv_declaredAbstract_2_0= Abstract ) ) | ( (lv_declaredStatic_3_0= Static ) ) )?
            int alt68=3;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==Abstract) ) {
                alt68=1;
            }
            else if ( (LA68_0==Static) ) {
                alt68=2;
            }
            switch (alt68) {
                case 1 :
                    // InternalTypesParser.g:3235:3: ( (lv_declaredAbstract_2_0= Abstract ) )
                    {
                    // InternalTypesParser.g:3235:3: ( (lv_declaredAbstract_2_0= Abstract ) )
                    // InternalTypesParser.g:3236:1: (lv_declaredAbstract_2_0= Abstract )
                    {
                    // InternalTypesParser.g:3236:1: (lv_declaredAbstract_2_0= Abstract )
                    // InternalTypesParser.g:3237:3: lv_declaredAbstract_2_0= Abstract
                    {
                    lv_declaredAbstract_2_0=(Token)match(input,Abstract,FOLLOW_59); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredAbstract_2_0, grammarAccess.getTSetterAccess().getDeclaredAbstractAbstractKeyword_0_0_2_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTSetterRule());
                      	        }
                             		setWithLastConsumed(current, "declaredAbstract", true, "abstract");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3252:6: ( (lv_declaredStatic_3_0= Static ) )
                    {
                    // InternalTypesParser.g:3252:6: ( (lv_declaredStatic_3_0= Static ) )
                    // InternalTypesParser.g:3253:1: (lv_declaredStatic_3_0= Static )
                    {
                    // InternalTypesParser.g:3253:1: (lv_declaredStatic_3_0= Static )
                    // InternalTypesParser.g:3254:3: lv_declaredStatic_3_0= Static
                    {
                    lv_declaredStatic_3_0=(Token)match(input,Static,FOLLOW_59); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredStatic_3_0, grammarAccess.getTSetterAccess().getDeclaredStaticStaticKeyword_0_0_2_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTSetterRule());
                      	        }
                             		setWithLastConsumed(current, "declaredStatic", true, "static");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,Set,FOLLOW_51); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTSetterAccess().getSetKeyword_0_0_3());
                  
            }
            // InternalTypesParser.g:3273:1: ( ( (lv_name_5_0= ruleTypesIdentifier ) ) | ( (lv_name_6_0= ruleTypesComputedPropertyName ) ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==AssignmnentCompatible||(LA69_0>=AutoboxedType && LA69_0<=VirtualBase)||LA69_0==Primitive||(LA69_0>=Undefined && LA69_0<=Abstract)||(LA69_0>=Project && LA69_0<=Object)||LA69_0==Union||(LA69_0>=From && LA69_0<=Null)||(LA69_0>=Type && LA69_0<=Void)||(LA69_0>=Any && LA69_0<=Get)||LA69_0==Set||LA69_0==As||LA69_0==RULE_IDENTIFIER) ) {
                alt69=1;
            }
            else if ( (LA69_0==LeftSquareBracket) ) {
                alt69=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // InternalTypesParser.g:3273:2: ( (lv_name_5_0= ruleTypesIdentifier ) )
                    {
                    // InternalTypesParser.g:3273:2: ( (lv_name_5_0= ruleTypesIdentifier ) )
                    // InternalTypesParser.g:3274:1: (lv_name_5_0= ruleTypesIdentifier )
                    {
                    // InternalTypesParser.g:3274:1: (lv_name_5_0= ruleTypesIdentifier )
                    // InternalTypesParser.g:3275:3: lv_name_5_0= ruleTypesIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTSetterAccess().getNameTypesIdentifierParserRuleCall_0_0_4_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_5_0=ruleTypesIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTSetterRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_5_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3292:6: ( (lv_name_6_0= ruleTypesComputedPropertyName ) )
                    {
                    // InternalTypesParser.g:3292:6: ( (lv_name_6_0= ruleTypesComputedPropertyName ) )
                    // InternalTypesParser.g:3293:1: (lv_name_6_0= ruleTypesComputedPropertyName )
                    {
                    // InternalTypesParser.g:3293:1: (lv_name_6_0= ruleTypesComputedPropertyName )
                    // InternalTypesParser.g:3294:3: lv_name_6_0= ruleTypesComputedPropertyName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTSetterAccess().getNameTypesComputedPropertyNameParserRuleCall_0_0_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_52);
                    lv_name_6_0=ruleTypesComputedPropertyName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTSetterRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_6_0, 
                              		"eu.numberfour.n4js.ts.Types.TypesComputedPropertyName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            otherlv_7=(Token)match(input,LeftParenthesis,FOLLOW_48); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getTSetterAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalTypesParser.g:3315:1: ( (lv_fpar_8_0= ruleTFormalParameter ) )
            // InternalTypesParser.g:3316:1: (lv_fpar_8_0= ruleTFormalParameter )
            {
            // InternalTypesParser.g:3316:1: (lv_fpar_8_0= ruleTFormalParameter )
            // InternalTypesParser.g:3317:3: lv_fpar_8_0= ruleTFormalParameter
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTSetterAccess().getFparTFormalParameterParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_57);
            lv_fpar_8_0=ruleTFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTSetterRule());
              	        }
                     		set(
                     			current, 
                     			"fpar",
                      		lv_fpar_8_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_9=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getTSetterAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTSetter"


    // $ANTLR start "entryRuleTFunction"
    // InternalTypesParser.g:3346:1: entryRuleTFunction returns [EObject current=null] : iv_ruleTFunction= ruleTFunction EOF ;
    public final EObject entryRuleTFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTFunction = null;


        try {
            // InternalTypesParser.g:3347:2: (iv_ruleTFunction= ruleTFunction EOF )
            // InternalTypesParser.g:3348:2: iv_ruleTFunction= ruleTFunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTFunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTFunction=ruleTFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTFunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTFunction"


    // $ANTLR start "ruleTFunction"
    // InternalTypesParser.g:3355:1: ruleTFunction returns [EObject current=null] : ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Function (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( (lv_name_8_0= ruleTIdentifier ) ) otherlv_9= LeftParenthesis ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )? otherlv_13= RightParenthesis otherlv_14= Colon ( (lv_returnTypeRef_15_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTFunction() throws RecognitionException {
        EObject current = null;

        Token lv_declaredProvidedByRuntime_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Enumerator lv_declaredTypeAccessModifier_0_0 = null;

        EObject lv_typeVars_4_0 = null;

        EObject lv_typeVars_6_0 = null;

        AntlrDatatypeRuleToken lv_name_8_0 = null;

        EObject lv_fpars_10_0 = null;

        EObject lv_fpars_12_0 = null;

        EObject lv_returnTypeRef_15_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3358:28: ( ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Function (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( (lv_name_8_0= ruleTIdentifier ) ) otherlv_9= LeftParenthesis ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )? otherlv_13= RightParenthesis otherlv_14= Colon ( (lv_returnTypeRef_15_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:3359:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Function (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( (lv_name_8_0= ruleTIdentifier ) ) otherlv_9= LeftParenthesis ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )? otherlv_13= RightParenthesis otherlv_14= Colon ( (lv_returnTypeRef_15_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:3359:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Function (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( (lv_name_8_0= ruleTIdentifier ) ) otherlv_9= LeftParenthesis ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )? otherlv_13= RightParenthesis otherlv_14= Colon ( (lv_returnTypeRef_15_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:3359:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Function (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )? ( (lv_name_8_0= ruleTIdentifier ) ) otherlv_9= LeftParenthesis ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )? otherlv_13= RightParenthesis otherlv_14= Colon ( (lv_returnTypeRef_15_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:3359:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) )
            // InternalTypesParser.g:3360:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            {
            // InternalTypesParser.g:3360:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            // InternalTypesParser.g:3361:3: lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFunctionAccess().getDeclaredTypeAccessModifierTypeAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_60);
            lv_declaredTypeAccessModifier_0_0=ruleTypeAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeAccessModifier",
                      		lv_declaredTypeAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:3377:2: ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==ProvidedByRuntime) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalTypesParser.g:3378:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    {
                    // InternalTypesParser.g:3378:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    // InternalTypesParser.g:3379:3: lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime
                    {
                    lv_declaredProvidedByRuntime_1_0=(Token)match(input,ProvidedByRuntime,FOLLOW_61); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredProvidedByRuntime_1_0, grammarAccess.getTFunctionAccess().getDeclaredProvidedByRuntimeProvidedByRuntimeKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTFunctionRule());
                      	        }
                             		setWithLastConsumed(current, "declaredProvidedByRuntime", true, "providedByRuntime");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,Function,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getTFunctionAccess().getFunctionKeyword_2());
                  
            }
            // InternalTypesParser.g:3398:1: (otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==LessThanSign) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalTypesParser.g:3399:2: otherlv_3= LessThanSign ( (lv_typeVars_4_0= ruleTypeVariable ) ) (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )* otherlv_7= GreaterThanSign
                    {
                    otherlv_3=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTFunctionAccess().getLessThanSignKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:3403:1: ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:3404:1: (lv_typeVars_4_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:3404:1: (lv_typeVars_4_0= ruleTypeVariable )
                    // InternalTypesParser.g:3405:3: lv_typeVars_4_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTFunctionAccess().getTypeVarsTypeVariableParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_4_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_4_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:3421:2: (otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==Comma) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // InternalTypesParser.g:3422:2: otherlv_5= Comma ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_5=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getTFunctionAccess().getCommaKeyword_3_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:3426:1: ( (lv_typeVars_6_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:3427:1: (lv_typeVars_6_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:3427:1: (lv_typeVars_6_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:3428:3: lv_typeVars_6_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTFunctionAccess().getTypeVarsTypeVariableParserRuleCall_3_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_6_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_6_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,GreaterThanSign,FOLLOW_48); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getTFunctionAccess().getGreaterThanSignKeyword_3_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:3449:3: ( (lv_name_8_0= ruleTIdentifier ) )
            // InternalTypesParser.g:3450:1: (lv_name_8_0= ruleTIdentifier )
            {
            // InternalTypesParser.g:3450:1: (lv_name_8_0= ruleTIdentifier )
            // InternalTypesParser.g:3451:3: lv_name_8_0= ruleTIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFunctionAccess().getNameTIdentifierParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_52);
            lv_name_8_0=ruleTIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_8_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_9=(Token)match(input,LeftParenthesis,FOLLOW_47); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getTFunctionAccess().getLeftParenthesisKeyword_5());
                  
            }
            // InternalTypesParser.g:3472:1: ( ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==AssignmnentCompatible||(LA74_0>=AutoboxedType && LA74_0<=Abstract)||(LA74_0>=Private && LA74_0<=Static)||LA74_0==Union||(LA74_0>=From && LA74_0<=Null)||(LA74_0>=Type && LA74_0<=Void)||(LA74_0>=FullStopFullStopFullStop && LA74_0<=Get)||LA74_0==Set||LA74_0==As||LA74_0==RULE_IDENTIFIER) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalTypesParser.g:3472:2: ( (lv_fpars_10_0= ruleTFormalParameter ) ) (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )*
                    {
                    // InternalTypesParser.g:3472:2: ( (lv_fpars_10_0= ruleTFormalParameter ) )
                    // InternalTypesParser.g:3473:1: (lv_fpars_10_0= ruleTFormalParameter )
                    {
                    // InternalTypesParser.g:3473:1: (lv_fpars_10_0= ruleTFormalParameter )
                    // InternalTypesParser.g:3474:3: lv_fpars_10_0= ruleTFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTFunctionAccess().getFparsTFormalParameterParserRuleCall_6_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_7);
                    lv_fpars_10_0=ruleTFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
                      	        }
                             		add(
                             			current, 
                             			"fpars",
                              		lv_fpars_10_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:3490:2: (otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==Comma) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // InternalTypesParser.g:3491:2: otherlv_11= Comma ( (lv_fpars_12_0= ruleTFormalParameter ) )
                    	    {
                    	    otherlv_11=(Token)match(input,Comma,FOLLOW_48); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_11, grammarAccess.getTFunctionAccess().getCommaKeyword_6_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:3495:1: ( (lv_fpars_12_0= ruleTFormalParameter ) )
                    	    // InternalTypesParser.g:3496:1: (lv_fpars_12_0= ruleTFormalParameter )
                    	    {
                    	    // InternalTypesParser.g:3496:1: (lv_fpars_12_0= ruleTFormalParameter )
                    	    // InternalTypesParser.g:3497:3: lv_fpars_12_0= ruleTFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTFunctionAccess().getFparsTFormalParameterParserRuleCall_6_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_7);
                    	    lv_fpars_12_0=ruleTFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"fpars",
                    	              		lv_fpars_12_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TFormalParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_13=(Token)match(input,RightParenthesis,FOLLOW_53); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_13, grammarAccess.getTFunctionAccess().getRightParenthesisKeyword_7());
                  
            }
            otherlv_14=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_14, grammarAccess.getTFunctionAccess().getColonKeyword_8());
                  
            }
            // InternalTypesParser.g:3523:1: ( (lv_returnTypeRef_15_0= ruleTypeRef ) )
            // InternalTypesParser.g:3524:1: (lv_returnTypeRef_15_0= ruleTypeRef )
            {
            // InternalTypesParser.g:3524:1: (lv_returnTypeRef_15_0= ruleTypeRef )
            // InternalTypesParser.g:3525:3: lv_returnTypeRef_15_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFunctionAccess().getReturnTypeRefTypeRefParserRuleCall_9_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_returnTypeRef_15_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFunctionRule());
              	        }
                     		set(
                     			current, 
                     			"returnTypeRef",
                      		lv_returnTypeRef_15_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTFunction"


    // $ANTLR start "entryRuleTEnum"
    // InternalTypesParser.g:3549:1: entryRuleTEnum returns [EObject current=null] : iv_ruleTEnum= ruleTEnum EOF ;
    public final EObject entryRuleTEnum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTEnum = null;


        try {
            // InternalTypesParser.g:3550:2: (iv_ruleTEnum= ruleTEnum EOF )
            // InternalTypesParser.g:3551:2: iv_ruleTEnum= ruleTEnum EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTEnumRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTEnum=ruleTEnum();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTEnum; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTEnum"


    // $ANTLR start "ruleTEnum"
    // InternalTypesParser.g:3558:1: ruleTEnum returns [EObject current=null] : ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Enum ( (lv_name_3_0= ruleTypesIdentifier ) ) otherlv_4= LeftCurlyBracket ( (lv_literals_5_0= ruleTEnumLiteral ) ) (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )* otherlv_8= RightCurlyBracket ) ;
    public final EObject ruleTEnum() throws RecognitionException {
        EObject current = null;

        Token lv_declaredProvidedByRuntime_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_declaredTypeAccessModifier_0_0 = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_literals_5_0 = null;

        EObject lv_literals_7_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3561:28: ( ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Enum ( (lv_name_3_0= ruleTypesIdentifier ) ) otherlv_4= LeftCurlyBracket ( (lv_literals_5_0= ruleTEnumLiteral ) ) (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )* otherlv_8= RightCurlyBracket ) )
            // InternalTypesParser.g:3562:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Enum ( (lv_name_3_0= ruleTypesIdentifier ) ) otherlv_4= LeftCurlyBracket ( (lv_literals_5_0= ruleTEnumLiteral ) ) (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )* otherlv_8= RightCurlyBracket )
            {
            // InternalTypesParser.g:3562:1: ( ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Enum ( (lv_name_3_0= ruleTypesIdentifier ) ) otherlv_4= LeftCurlyBracket ( (lv_literals_5_0= ruleTEnumLiteral ) ) (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )* otherlv_8= RightCurlyBracket )
            // InternalTypesParser.g:3562:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) ) ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )? otherlv_2= Enum ( (lv_name_3_0= ruleTypesIdentifier ) ) otherlv_4= LeftCurlyBracket ( (lv_literals_5_0= ruleTEnumLiteral ) ) (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )* otherlv_8= RightCurlyBracket
            {
            // InternalTypesParser.g:3562:2: ( (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier ) )
            // InternalTypesParser.g:3563:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            {
            // InternalTypesParser.g:3563:1: (lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier )
            // InternalTypesParser.g:3564:3: lv_declaredTypeAccessModifier_0_0= ruleTypeAccessModifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTEnumAccess().getDeclaredTypeAccessModifierTypeAccessModifierEnumRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_63);
            lv_declaredTypeAccessModifier_0_0=ruleTypeAccessModifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTEnumRule());
              	        }
                     		set(
                     			current, 
                     			"declaredTypeAccessModifier",
                      		lv_declaredTypeAccessModifier_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeAccessModifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:3580:2: ( (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==ProvidedByRuntime) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalTypesParser.g:3581:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    {
                    // InternalTypesParser.g:3581:1: (lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime )
                    // InternalTypesParser.g:3582:3: lv_declaredProvidedByRuntime_1_0= ProvidedByRuntime
                    {
                    lv_declaredProvidedByRuntime_1_0=(Token)match(input,ProvidedByRuntime,FOLLOW_64); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_declaredProvidedByRuntime_1_0, grammarAccess.getTEnumAccess().getDeclaredProvidedByRuntimeProvidedByRuntimeKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTEnumRule());
                      	        }
                             		setWithLastConsumed(current, "declaredProvidedByRuntime", true, "providedByRuntime");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,Enum,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getTEnumAccess().getEnumKeyword_2());
                  
            }
            // InternalTypesParser.g:3601:1: ( (lv_name_3_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:3602:1: (lv_name_3_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:3602:1: (lv_name_3_0= ruleTypesIdentifier )
            // InternalTypesParser.g:3603:3: lv_name_3_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTEnumAccess().getNameTypesIdentifierParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
            lv_name_3_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTEnumRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_3_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,LeftCurlyBracket,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTEnumAccess().getLeftCurlyBracketKeyword_4());
                  
            }
            // InternalTypesParser.g:3624:1: ( (lv_literals_5_0= ruleTEnumLiteral ) )
            // InternalTypesParser.g:3625:1: (lv_literals_5_0= ruleTEnumLiteral )
            {
            // InternalTypesParser.g:3625:1: (lv_literals_5_0= ruleTEnumLiteral )
            // InternalTypesParser.g:3626:3: lv_literals_5_0= ruleTEnumLiteral
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTEnumAccess().getLiteralsTEnumLiteralParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_literals_5_0=ruleTEnumLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTEnumRule());
              	        }
                     		add(
                     			current, 
                     			"literals",
                      		lv_literals_5_0, 
                      		"eu.numberfour.n4js.ts.Types.TEnumLiteral");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:3642:2: (otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) ) )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==Comma) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // InternalTypesParser.g:3643:2: otherlv_6= Comma ( (lv_literals_7_0= ruleTEnumLiteral ) )
            	    {
            	    otherlv_6=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_6, grammarAccess.getTEnumAccess().getCommaKeyword_6_0());
            	          
            	    }
            	    // InternalTypesParser.g:3647:1: ( (lv_literals_7_0= ruleTEnumLiteral ) )
            	    // InternalTypesParser.g:3648:1: (lv_literals_7_0= ruleTEnumLiteral )
            	    {
            	    // InternalTypesParser.g:3648:1: (lv_literals_7_0= ruleTEnumLiteral )
            	    // InternalTypesParser.g:3649:3: lv_literals_7_0= ruleTEnumLiteral
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTEnumAccess().getLiteralsTEnumLiteralParserRuleCall_6_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_65);
            	    lv_literals_7_0=ruleTEnumLiteral();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTEnumRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"literals",
            	              		lv_literals_7_0, 
            	              		"eu.numberfour.n4js.ts.Types.TEnumLiteral");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);

            otherlv_8=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getTEnumAccess().getRightCurlyBracketKeyword_7());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTEnum"


    // $ANTLR start "entryRuleTEnumLiteral"
    // InternalTypesParser.g:3678:1: entryRuleTEnumLiteral returns [EObject current=null] : iv_ruleTEnumLiteral= ruleTEnumLiteral EOF ;
    public final EObject entryRuleTEnumLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTEnumLiteral = null;


        try {
            // InternalTypesParser.g:3679:2: (iv_ruleTEnumLiteral= ruleTEnumLiteral EOF )
            // InternalTypesParser.g:3680:2: iv_ruleTEnumLiteral= ruleTEnumLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTEnumLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTEnumLiteral=ruleTEnumLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTEnumLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTEnumLiteral"


    // $ANTLR start "ruleTEnumLiteral"
    // InternalTypesParser.g:3687:1: ruleTEnumLiteral returns [EObject current=null] : ( (lv_name_0_0= RULE_IDENTIFIER ) ) ;
    public final EObject ruleTEnumLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:3690:28: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) )
            // InternalTypesParser.g:3691:1: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            {
            // InternalTypesParser.g:3691:1: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalTypesParser.g:3692:1: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalTypesParser.g:3692:1: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalTypesParser.g:3693:3: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getTEnumLiteralAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTEnumLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.IDENTIFIER");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTEnumLiteral"


    // $ANTLR start "entryRuleIntersectionTypeExpression"
    // InternalTypesParser.g:3719:1: entryRuleIntersectionTypeExpression returns [EObject current=null] : iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF ;
    public final EObject entryRuleIntersectionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntersectionTypeExpression = null;


        try {
            // InternalTypesParser.g:3720:2: (iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF )
            // InternalTypesParser.g:3721:2: iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntersectionTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIntersectionTypeExpression=ruleIntersectionTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntersectionTypeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntersectionTypeExpression"


    // $ANTLR start "ruleIntersectionTypeExpression"
    // InternalTypesParser.g:3728:1: ruleIntersectionTypeExpression returns [EObject current=null] : (this_PrimaryTypeExpression_0= rulePrimaryTypeExpression ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )? ) ;
    public final EObject ruleIntersectionTypeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PrimaryTypeExpression_0 = null;

        EObject lv_typeRefs_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3731:28: ( (this_PrimaryTypeExpression_0= rulePrimaryTypeExpression ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )? ) )
            // InternalTypesParser.g:3732:1: (this_PrimaryTypeExpression_0= rulePrimaryTypeExpression ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )? )
            {
            // InternalTypesParser.g:3732:1: (this_PrimaryTypeExpression_0= rulePrimaryTypeExpression ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )? )
            // InternalTypesParser.g:3733:5: this_PrimaryTypeExpression_0= rulePrimaryTypeExpression ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIntersectionTypeExpressionAccess().getPrimaryTypeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_66);
            this_PrimaryTypeExpression_0=rulePrimaryTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_PrimaryTypeExpression_0;
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalTypesParser.g:3741:1: ( () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+ )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==Ampersand) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalTypesParser.g:3741:2: () (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+
                    {
                    // InternalTypesParser.g:3741:2: ()
                    // InternalTypesParser.g:3742:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndAdd(
                                  grammarAccess.getIntersectionTypeExpressionAccess().getIntersectionTypeExpressionTypeRefsAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalTypesParser.g:3747:2: (otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) ) )+
                    int cnt77=0;
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==Ampersand) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // InternalTypesParser.g:3748:2: otherlv_2= Ampersand ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) )
                    	    {
                    	    otherlv_2=(Token)match(input,Ampersand,FOLLOW_67); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getIntersectionTypeExpressionAccess().getAmpersandKeyword_1_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:3752:1: ( (lv_typeRefs_3_0= rulePrimaryTypeExpression ) )
                    	    // InternalTypesParser.g:3753:1: (lv_typeRefs_3_0= rulePrimaryTypeExpression )
                    	    {
                    	    // InternalTypesParser.g:3753:1: (lv_typeRefs_3_0= rulePrimaryTypeExpression )
                    	    // InternalTypesParser.g:3754:3: lv_typeRefs_3_0= rulePrimaryTypeExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getIntersectionTypeExpressionAccess().getTypeRefsPrimaryTypeExpressionParserRuleCall_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_66);
                    	    lv_typeRefs_3_0=rulePrimaryTypeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getIntersectionTypeExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeRefs",
                    	              		lv_typeRefs_3_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.PrimaryTypeExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt77 >= 1 ) break loop77;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(77, input);
                                throw eee;
                        }
                        cnt77++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntersectionTypeExpression"


    // $ANTLR start "entryRulePrimaryTypeExpression"
    // InternalTypesParser.g:3778:1: entryRulePrimaryTypeExpression returns [EObject current=null] : iv_rulePrimaryTypeExpression= rulePrimaryTypeExpression EOF ;
    public final EObject entryRulePrimaryTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryTypeExpression = null;


        try {
            // InternalTypesParser.g:3779:2: (iv_rulePrimaryTypeExpression= rulePrimaryTypeExpression EOF )
            // InternalTypesParser.g:3780:2: iv_rulePrimaryTypeExpression= rulePrimaryTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimaryTypeExpression=rulePrimaryTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryTypeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimaryTypeExpression"


    // $ANTLR start "rulePrimaryTypeExpression"
    // InternalTypesParser.g:3787:1: rulePrimaryTypeExpression returns [EObject current=null] : ( ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) | this_ArrayTypeRef_6= ruleArrayTypeRef | this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers | (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis ) ) ;
    public final EObject rulePrimaryTypeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject this_TAnonymousFormalParameterList_2 = null;

        EObject lv_returnTypeRef_5_0 = null;

        EObject this_ArrayTypeRef_6 = null;

        EObject this_TypeRefWithModifiers_7 = null;

        EObject this_TypeRef_9 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3790:28: ( ( ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) | this_ArrayTypeRef_6= ruleArrayTypeRef | this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers | (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis ) ) )
            // InternalTypesParser.g:3791:1: ( ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) | this_ArrayTypeRef_6= ruleArrayTypeRef | this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers | (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis ) )
            {
            // InternalTypesParser.g:3791:1: ( ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) | this_ArrayTypeRef_6= ruleArrayTypeRef | this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers | (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis ) )
            int alt79=4;
            alt79 = dfa79.predict(input);
            switch (alt79) {
                case 1 :
                    // InternalTypesParser.g:3791:2: ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) )
                    {
                    // InternalTypesParser.g:3791:2: ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) )
                    // InternalTypesParser.g:3791:3: ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) )
                    {
                    // InternalTypesParser.g:3791:3: ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) )
                    // InternalTypesParser.g:3791:4: ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign )
                    {
                    // InternalTypesParser.g:3798:5: ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign )
                    // InternalTypesParser.g:3798:6: () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign
                    {
                    // InternalTypesParser.g:3798:6: ()
                    // InternalTypesParser.g:3799:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrimaryTypeExpressionAccess().getFunctionTypeExpressionAction_0_0_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getPrimaryTypeExpressionAccess().getLeftParenthesisKeyword_0_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      		if (current==null) {
                      			current = createModelElement(grammarAccess.getPrimaryTypeExpressionRule());
                      		}
                              newCompositeNode(grammarAccess.getPrimaryTypeExpressionAccess().getTAnonymousFormalParameterListParserRuleCall_0_0_0_2()); 
                          
                    }
                    pushFollow(FOLLOW_57);
                    this_TAnonymousFormalParameterList_2=ruleTAnonymousFormalParameterList(current);

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TAnonymousFormalParameterList_2;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_69); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getPrimaryTypeExpressionAccess().getRightParenthesisKeyword_0_0_0_3());
                          
                    }
                    otherlv_4=(Token)match(input,EqualsSignGreaterThanSign,FOLLOW_67); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getPrimaryTypeExpressionAccess().getEqualsSignGreaterThanSignKeyword_0_0_0_4());
                          
                    }

                    }


                    }

                    // InternalTypesParser.g:3831:3: ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) )
                    // InternalTypesParser.g:3832:1: (lv_returnTypeRef_5_0= rulePrimaryTypeExpression )
                    {
                    // InternalTypesParser.g:3832:1: (lv_returnTypeRef_5_0= rulePrimaryTypeExpression )
                    // InternalTypesParser.g:3833:3: lv_returnTypeRef_5_0= rulePrimaryTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrimaryTypeExpressionAccess().getReturnTypeRefPrimaryTypeExpressionParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_returnTypeRef_5_0=rulePrimaryTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrimaryTypeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"returnTypeRef",
                              		lv_returnTypeRef_5_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.PrimaryTypeExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3851:5: this_ArrayTypeRef_6= ruleArrayTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryTypeExpressionAccess().getArrayTypeRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ArrayTypeRef_6=ruleArrayTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ArrayTypeRef_6;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:3861:5: this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryTypeExpressionAccess().getTypeRefWithModifiersParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TypeRefWithModifiers_7=ruleTypeRefWithModifiers();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeRefWithModifiers_7;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:3870:6: (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis )
                    {
                    // InternalTypesParser.g:3870:6: (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis )
                    // InternalTypesParser.g:3871:2: otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis
                    {
                    otherlv_8=(Token)match(input,LeftParenthesis,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getPrimaryTypeExpressionAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryTypeExpressionAccess().getTypeRefParserRuleCall_3_1()); 
                          
                    }
                    pushFollow(FOLLOW_57);
                    this_TypeRef_9=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeRef_9;
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_10=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getPrimaryTypeExpressionAccess().getRightParenthesisKeyword_3_2());
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimaryTypeExpression"


    // $ANTLR start "entryRuleTypeRefWithModifiers"
    // InternalTypesParser.g:3899:1: entryRuleTypeRefWithModifiers returns [EObject current=null] : iv_ruleTypeRefWithModifiers= ruleTypeRefWithModifiers EOF ;
    public final EObject entryRuleTypeRefWithModifiers() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefWithModifiers = null;


        try {
            // InternalTypesParser.g:3900:2: (iv_ruleTypeRefWithModifiers= ruleTypeRefWithModifiers EOF )
            // InternalTypesParser.g:3901:2: iv_ruleTypeRefWithModifiers= ruleTypeRefWithModifiers EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefWithModifiersRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeRefWithModifiers=ruleTypeRefWithModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRefWithModifiers; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRefWithModifiers"


    // $ANTLR start "ruleTypeRefWithModifiers"
    // InternalTypesParser.g:3908:1: ruleTypeRefWithModifiers returns [EObject current=null] : ( (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ) | ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) ) ) ;
    public final EObject ruleTypeRefWithModifiers() throws RecognitionException {
        EObject current = null;

        EObject this_TypeRefWithoutModifiers_0 = null;

        AntlrDatatypeRuleToken lv_undefModifier_1_0 = null;

        AntlrDatatypeRuleToken lv_undefModifier_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3911:28: ( ( (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ) | ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) ) ) )
            // InternalTypesParser.g:3912:1: ( (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ) | ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) ) )
            {
            // InternalTypesParser.g:3912:1: ( (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? ) | ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) ) )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=Intersection && LA81_0<=Constructor)||LA81_0==Undefined||LA81_0==Indexed||LA81_0==Union||(LA81_0>=Null && LA81_0<=Void)||LA81_0==Any||LA81_0==LeftCurlyBracket||LA81_0==Tilde||LA81_0==RULE_IDENTIFIER) ) {
                alt81=1;
            }
            else if ( (LA81_0==ExclamationMark||LA81_0==QuestionMark) ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // InternalTypesParser.g:3912:2: (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? )
                    {
                    // InternalTypesParser.g:3912:2: (this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )? )
                    // InternalTypesParser.g:3913:5: this_TypeRefWithoutModifiers_0= ruleTypeRefWithoutModifiers ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )?
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithModifiersAccess().getTypeRefWithoutModifiersParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_70);
                    this_TypeRefWithoutModifiers_0=ruleTypeRefWithoutModifiers();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeRefWithoutModifiers_0;
                              afterParserOrEnumRuleCall();
                          
                    }
                    // InternalTypesParser.g:3921:1: ( ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken ) )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==QuestionMark) && (synpred13_InternalTypesParser())) {
                        alt80=1;
                    }
                    else if ( (LA80_0==ExclamationMark) && (synpred13_InternalTypesParser())) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // InternalTypesParser.g:3921:2: ( ( ruleUndefModifierToken ) )=> (lv_undefModifier_1_0= ruleUndefModifierToken )
                            {
                            // InternalTypesParser.g:3926:1: (lv_undefModifier_1_0= ruleUndefModifierToken )
                            // InternalTypesParser.g:3927:3: lv_undefModifier_1_0= ruleUndefModifierToken
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getTypeRefWithModifiersAccess().getUndefModifierUndefModifierTokenParserRuleCall_0_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_2);
                            lv_undefModifier_1_0=ruleUndefModifierToken();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getTypeRefWithModifiersRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"undefModifier",
                                      		lv_undefModifier_1_0, 
                                      		"eu.numberfour.n4js.ts.Types.UndefModifierToken");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:3944:6: ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) )
                    {
                    // InternalTypesParser.g:3944:6: ( () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) ) )
                    // InternalTypesParser.g:3944:7: () ( (lv_undefModifier_3_0= ruleUndefModifierToken ) )
                    {
                    // InternalTypesParser.g:3944:7: ()
                    // InternalTypesParser.g:3945:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getTypeRefWithModifiersAccess().getParameterizedTypeRefAction_1_0(),
                                  current);
                          
                    }

                    }

                    // InternalTypesParser.g:3950:2: ( (lv_undefModifier_3_0= ruleUndefModifierToken ) )
                    // InternalTypesParser.g:3951:1: (lv_undefModifier_3_0= ruleUndefModifierToken )
                    {
                    // InternalTypesParser.g:3951:1: (lv_undefModifier_3_0= ruleUndefModifierToken )
                    // InternalTypesParser.g:3952:3: lv_undefModifier_3_0= ruleUndefModifierToken
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeRefWithModifiersAccess().getUndefModifierUndefModifierTokenParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_undefModifier_3_0=ruleUndefModifierToken();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRefWithModifiersRule());
                      	        }
                             		set(
                             			current, 
                             			"undefModifier",
                              		lv_undefModifier_3_0, 
                              		"eu.numberfour.n4js.ts.Types.UndefModifierToken");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRefWithModifiers"


    // $ANTLR start "entryRuleTypeRefWithoutModifiers"
    // InternalTypesParser.g:3976:1: entryRuleTypeRefWithoutModifiers returns [EObject current=null] : iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF ;
    public final EObject entryRuleTypeRefWithoutModifiers() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefWithoutModifiers = null;


        try {
            // InternalTypesParser.g:3977:2: (iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF )
            // InternalTypesParser.g:3978:2: iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefWithoutModifiersRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeRefWithoutModifiers=ruleTypeRefWithoutModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRefWithoutModifiers; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRefWithoutModifiers"


    // $ANTLR start "ruleTypeRefWithoutModifiers"
    // InternalTypesParser.g:3985:1: ruleTypeRefWithoutModifiers returns [EObject current=null] : ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpressionOLD_5= ruleFunctionTypeExpressionOLD | this_UnionTypeExpressionOLD_6= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_7= ruleIntersectionTypeExpressionOLD ) ;
    public final EObject ruleTypeRefWithoutModifiers() throws RecognitionException {
        EObject current = null;

        Token lv_dynamic_2_0=null;
        EObject this_ParameterizedTypeRef_0 = null;

        EObject this_ThisTypeRef_1 = null;

        EObject this_ConstructorTypeRef_3 = null;

        EObject this_ClassifierTypeRef_4 = null;

        EObject this_FunctionTypeExpressionOLD_5 = null;

        EObject this_UnionTypeExpressionOLD_6 = null;

        EObject this_IntersectionTypeExpressionOLD_7 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3988:28: ( ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpressionOLD_5= ruleFunctionTypeExpressionOLD | this_UnionTypeExpressionOLD_6= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_7= ruleIntersectionTypeExpressionOLD ) )
            // InternalTypesParser.g:3989:1: ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpressionOLD_5= ruleFunctionTypeExpressionOLD | this_UnionTypeExpressionOLD_6= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_7= ruleIntersectionTypeExpressionOLD )
            {
            // InternalTypesParser.g:3989:1: ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpressionOLD_5= ruleFunctionTypeExpressionOLD | this_UnionTypeExpressionOLD_6= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_7= ruleIntersectionTypeExpressionOLD )
            int alt84=6;
            switch ( input.LA(1) ) {
            case Undefined:
            case Indexed:
            case Null:
            case This_1:
            case Void:
            case Any:
            case Tilde:
            case RULE_IDENTIFIER:
                {
                alt84=1;
                }
                break;
            case Constructor:
                {
                alt84=2;
                }
                break;
            case Type:
                {
                alt84=3;
                }
                break;
            case LeftCurlyBracket:
                {
                alt84=4;
                }
                break;
            case Union:
                {
                alt84=5;
                }
                break;
            case Intersection:
                {
                alt84=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }

            switch (alt84) {
                case 1 :
                    // InternalTypesParser.g:3989:2: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? )
                    {
                    // InternalTypesParser.g:3989:2: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? )
                    // InternalTypesParser.g:3989:3: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )?
                    {
                    // InternalTypesParser.g:3989:3: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef )
                    int alt82=2;
                    switch ( input.LA(1) ) {
                    case Undefined:
                    case Indexed:
                    case Null:
                    case Void:
                    case Any:
                    case RULE_IDENTIFIER:
                        {
                        alt82=1;
                        }
                        break;
                    case Tilde:
                        {
                        switch ( input.LA(2) ) {
                        case Tilde:
                            {
                            int LA82_4 = input.LA(3);

                            if ( (LA82_4==Undefined||LA82_4==Indexed||LA82_4==Null||LA82_4==Void||LA82_4==Any||LA82_4==RULE_IDENTIFIER) ) {
                                alt82=1;
                            }
                            else if ( (LA82_4==This_1) ) {
                                alt82=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 82, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case RULE_STRUCTMODSUFFIX:
                            {
                            int LA82_5 = input.LA(3);

                            if ( (LA82_5==Undefined||LA82_5==Indexed||LA82_5==Null||LA82_5==Void||LA82_5==Any||LA82_5==RULE_IDENTIFIER) ) {
                                alt82=1;
                            }
                            else if ( (LA82_5==This_1) ) {
                                alt82=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 82, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case This_1:
                            {
                            alt82=2;
                            }
                            break;
                        case Undefined:
                        case Indexed:
                        case Null:
                        case Void:
                        case Any:
                        case RULE_IDENTIFIER:
                            {
                            alt82=1;
                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 82, 2, input);

                            throw nvae;
                        }

                        }
                        break;
                    case This_1:
                        {
                        alt82=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 82, 0, input);

                        throw nvae;
                    }

                    switch (alt82) {
                        case 1 :
                            // InternalTypesParser.g:3990:5: this_ParameterizedTypeRef_0= ruleParameterizedTypeRef
                            {
                            if ( state.backtracking==0 ) {
                               
                                      newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getParameterizedTypeRefParserRuleCall_0_0_0()); 
                                  
                            }
                            pushFollow(FOLLOW_71);
                            this_ParameterizedTypeRef_0=ruleParameterizedTypeRef();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current = this_ParameterizedTypeRef_0;
                                      afterParserOrEnumRuleCall();
                                  
                            }

                            }
                            break;
                        case 2 :
                            // InternalTypesParser.g:4000:5: this_ThisTypeRef_1= ruleThisTypeRef
                            {
                            if ( state.backtracking==0 ) {
                               
                                      newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getThisTypeRefParserRuleCall_0_0_1()); 
                                  
                            }
                            pushFollow(FOLLOW_71);
                            this_ThisTypeRef_1=ruleThisTypeRef();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current = this_ThisTypeRef_1;
                                      afterParserOrEnumRuleCall();
                                  
                            }

                            }
                            break;

                    }

                    // InternalTypesParser.g:4008:2: ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==PlusSign) && (synpred14_InternalTypesParser())) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // InternalTypesParser.g:4008:3: ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign )
                            {
                            // InternalTypesParser.g:4016:1: (lv_dynamic_2_0= PlusSign )
                            // InternalTypesParser.g:4017:3: lv_dynamic_2_0= PlusSign
                            {
                            lv_dynamic_2_0=(Token)match(input,PlusSign,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_dynamic_2_0, grammarAccess.getTypeRefWithoutModifiersAccess().getDynamicPlusSignKeyword_0_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getTypeRefWithoutModifiersRule());
                              	        }
                                     		setWithLastConsumed(current, "dynamic", true, "+");
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:4033:5: this_ConstructorTypeRef_3= ruleConstructorTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getConstructorTypeRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ConstructorTypeRef_3=ruleConstructorTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ConstructorTypeRef_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:4043:5: this_ClassifierTypeRef_4= ruleClassifierTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getClassifierTypeRefParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ClassifierTypeRef_4=ruleClassifierTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ClassifierTypeRef_4;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:4053:5: this_FunctionTypeExpressionOLD_5= ruleFunctionTypeExpressionOLD
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getFunctionTypeExpressionOLDParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_FunctionTypeExpressionOLD_5=ruleFunctionTypeExpressionOLD();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_FunctionTypeExpressionOLD_5;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:4063:5: this_UnionTypeExpressionOLD_6= ruleUnionTypeExpressionOLD
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getUnionTypeExpressionOLDParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnionTypeExpressionOLD_6=ruleUnionTypeExpressionOLD();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UnionTypeExpressionOLD_6;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:4073:5: this_IntersectionTypeExpressionOLD_7= ruleIntersectionTypeExpressionOLD
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getIntersectionTypeExpressionOLDParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_IntersectionTypeExpressionOLD_7=ruleIntersectionTypeExpressionOLD();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_IntersectionTypeExpressionOLD_7;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRefWithoutModifiers"


    // $ANTLR start "entryRuleTypeRefFunctionTypeExpression"
    // InternalTypesParser.g:4089:1: entryRuleTypeRefFunctionTypeExpression returns [EObject current=null] : iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF ;
    public final EObject entryRuleTypeRefFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefFunctionTypeExpression = null;


        try {
            // InternalTypesParser.g:4090:2: (iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF )
            // InternalTypesParser.g:4091:2: iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeRefFunctionTypeExpression=ruleTypeRefFunctionTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRefFunctionTypeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRefFunctionTypeExpression"


    // $ANTLR start "ruleTypeRefFunctionTypeExpression"
    // InternalTypesParser.g:4098:1: ruleTypeRefFunctionTypeExpression returns [EObject current=null] : (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ArrayTypeRef_1= ruleArrayTypeRef | this_ConstructorTypeRef_2= ruleConstructorTypeRef | this_ClassifierTypeRef_3= ruleClassifierTypeRef | this_UnionTypeExpressionOLD_4= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_5= ruleIntersectionTypeExpressionOLD ) ;
    public final EObject ruleTypeRefFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRef_0 = null;

        EObject this_ArrayTypeRef_1 = null;

        EObject this_ConstructorTypeRef_2 = null;

        EObject this_ClassifierTypeRef_3 = null;

        EObject this_UnionTypeExpressionOLD_4 = null;

        EObject this_IntersectionTypeExpressionOLD_5 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4101:28: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ArrayTypeRef_1= ruleArrayTypeRef | this_ConstructorTypeRef_2= ruleConstructorTypeRef | this_ClassifierTypeRef_3= ruleClassifierTypeRef | this_UnionTypeExpressionOLD_4= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_5= ruleIntersectionTypeExpressionOLD ) )
            // InternalTypesParser.g:4102:1: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ArrayTypeRef_1= ruleArrayTypeRef | this_ConstructorTypeRef_2= ruleConstructorTypeRef | this_ClassifierTypeRef_3= ruleClassifierTypeRef | this_UnionTypeExpressionOLD_4= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_5= ruleIntersectionTypeExpressionOLD )
            {
            // InternalTypesParser.g:4102:1: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ArrayTypeRef_1= ruleArrayTypeRef | this_ConstructorTypeRef_2= ruleConstructorTypeRef | this_ClassifierTypeRef_3= ruleClassifierTypeRef | this_UnionTypeExpressionOLD_4= ruleUnionTypeExpressionOLD | this_IntersectionTypeExpressionOLD_5= ruleIntersectionTypeExpressionOLD )
            int alt85=6;
            switch ( input.LA(1) ) {
            case Undefined:
            case Indexed:
            case Null:
            case Void:
            case Any:
            case Tilde:
            case RULE_IDENTIFIER:
                {
                alt85=1;
                }
                break;
            case LeftSquareBracket:
                {
                alt85=2;
                }
                break;
            case Constructor:
                {
                alt85=3;
                }
                break;
            case Type:
                {
                alt85=4;
                }
                break;
            case Union:
                {
                alt85=5;
                }
                break;
            case Intersection:
                {
                alt85=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }

            switch (alt85) {
                case 1 :
                    // InternalTypesParser.g:4103:5: this_ParameterizedTypeRef_0= ruleParameterizedTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getParameterizedTypeRefParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ParameterizedTypeRef_0=ruleParameterizedTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ParameterizedTypeRef_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:4113:5: this_ArrayTypeRef_1= ruleArrayTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getArrayTypeRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ArrayTypeRef_1=ruleArrayTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ArrayTypeRef_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:4123:5: this_ConstructorTypeRef_2= ruleConstructorTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getConstructorTypeRefParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ConstructorTypeRef_2=ruleConstructorTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ConstructorTypeRef_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:4133:5: this_ClassifierTypeRef_3= ruleClassifierTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getClassifierTypeRefParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ClassifierTypeRef_3=ruleClassifierTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ClassifierTypeRef_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:4143:5: this_UnionTypeExpressionOLD_4= ruleUnionTypeExpressionOLD
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getUnionTypeExpressionOLDParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnionTypeExpressionOLD_4=ruleUnionTypeExpressionOLD();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UnionTypeExpressionOLD_4;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:4153:5: this_IntersectionTypeExpressionOLD_5= ruleIntersectionTypeExpressionOLD
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getIntersectionTypeExpressionOLDParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_IntersectionTypeExpressionOLD_5=ruleIntersectionTypeExpressionOLD();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_IntersectionTypeExpressionOLD_5;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRefFunctionTypeExpression"


    // $ANTLR start "entryRuleTypeRefInClassifierType"
    // InternalTypesParser.g:4171:1: entryRuleTypeRefInClassifierType returns [EObject current=null] : iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF ;
    public final EObject entryRuleTypeRefInClassifierType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefInClassifierType = null;


        try {
            // InternalTypesParser.g:4172:2: (iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF )
            // InternalTypesParser.g:4173:2: iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefInClassifierTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeRefInClassifierType=ruleTypeRefInClassifierType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRefInClassifierType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRefInClassifierType"


    // $ANTLR start "ruleTypeRefInClassifierType"
    // InternalTypesParser.g:4180:1: ruleTypeRefInClassifierType returns [EObject current=null] : (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal ) ;
    public final EObject ruleTypeRefInClassifierType() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRefNominal_0 = null;

        EObject this_ThisTypeRefNominal_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4183:28: ( (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal ) )
            // InternalTypesParser.g:4184:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal )
            {
            // InternalTypesParser.g:4184:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==Undefined||LA86_0==Indexed||LA86_0==Null||LA86_0==Void||LA86_0==Any||LA86_0==RULE_IDENTIFIER) ) {
                alt86=1;
            }
            else if ( (LA86_0==This_1) ) {
                alt86=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // InternalTypesParser.g:4185:5: this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefInClassifierTypeAccess().getParameterizedTypeRefNominalParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ParameterizedTypeRefNominal_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ParameterizedTypeRefNominal_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:4195:5: this_ThisTypeRefNominal_1= ruleThisTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefInClassifierTypeAccess().getThisTypeRefNominalParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ThisTypeRefNominal_1=ruleThisTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ThisTypeRefNominal_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRefInClassifierType"


    // $ANTLR start "entryRuleThisTypeRef"
    // InternalTypesParser.g:4211:1: entryRuleThisTypeRef returns [EObject current=null] : iv_ruleThisTypeRef= ruleThisTypeRef EOF ;
    public final EObject entryRuleThisTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRef = null;


        try {
            // InternalTypesParser.g:4212:2: (iv_ruleThisTypeRef= ruleThisTypeRef EOF )
            // InternalTypesParser.g:4213:2: iv_ruleThisTypeRef= ruleThisTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getThisTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleThisTypeRef=ruleThisTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleThisTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisTypeRef"


    // $ANTLR start "ruleThisTypeRef"
    // InternalTypesParser.g:4220:1: ruleThisTypeRef returns [EObject current=null] : (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural ) ;
    public final EObject ruleThisTypeRef() throws RecognitionException {
        EObject current = null;

        EObject this_ThisTypeRefNominal_0 = null;

        EObject this_ThisTypeRefStructural_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4223:28: ( (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural ) )
            // InternalTypesParser.g:4224:1: (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural )
            {
            // InternalTypesParser.g:4224:1: (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==This_1) ) {
                alt87=1;
            }
            else if ( (LA87_0==Tilde) ) {
                alt87=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // InternalTypesParser.g:4225:5: this_ThisTypeRefNominal_0= ruleThisTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getThisTypeRefAccess().getThisTypeRefNominalParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ThisTypeRefNominal_0=ruleThisTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ThisTypeRefNominal_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:4235:5: this_ThisTypeRefStructural_1= ruleThisTypeRefStructural
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getThisTypeRefAccess().getThisTypeRefStructuralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ThisTypeRefStructural_1=ruleThisTypeRefStructural();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ThisTypeRefStructural_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisTypeRef"


    // $ANTLR start "entryRuleThisTypeRefNominal"
    // InternalTypesParser.g:4251:1: entryRuleThisTypeRefNominal returns [EObject current=null] : iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF ;
    public final EObject entryRuleThisTypeRefNominal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRefNominal = null;


        try {
            // InternalTypesParser.g:4252:2: (iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF )
            // InternalTypesParser.g:4253:2: iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getThisTypeRefNominalRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleThisTypeRefNominal=ruleThisTypeRefNominal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleThisTypeRefNominal; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisTypeRefNominal"


    // $ANTLR start "ruleThisTypeRefNominal"
    // InternalTypesParser.g:4260:1: ruleThisTypeRefNominal returns [EObject current=null] : ( () otherlv_1= This_1 ) ;
    public final EObject ruleThisTypeRefNominal() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:4263:28: ( ( () otherlv_1= This_1 ) )
            // InternalTypesParser.g:4264:1: ( () otherlv_1= This_1 )
            {
            // InternalTypesParser.g:4264:1: ( () otherlv_1= This_1 )
            // InternalTypesParser.g:4264:2: () otherlv_1= This_1
            {
            // InternalTypesParser.g:4264:2: ()
            // InternalTypesParser.g:4265:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getThisTypeRefNominalAccess().getThisTypeRefNominalAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,This_1,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getThisTypeRefNominalAccess().getThisKeyword_1());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisTypeRefNominal"


    // $ANTLR start "entryRuleThisTypeRefStructural"
    // InternalTypesParser.g:4283:1: entryRuleThisTypeRefStructural returns [EObject current=null] : iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF ;
    public final EObject entryRuleThisTypeRefStructural() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRefStructural = null;


        try {
            // InternalTypesParser.g:4284:2: (iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF )
            // InternalTypesParser.g:4285:2: iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getThisTypeRefStructuralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleThisTypeRefStructural=ruleThisTypeRefStructural();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleThisTypeRefStructural; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisTypeRefStructural"


    // $ANTLR start "ruleThisTypeRefStructural"
    // InternalTypesParser.g:4292:1: ruleThisTypeRefStructural returns [EObject current=null] : ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? ) ;
    public final EObject ruleThisTypeRefStructural() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_definedTypingStrategy_0_0 = null;

        EObject this_TStructMemberList_3 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4295:28: ( ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? ) )
            // InternalTypesParser.g:4296:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? )
            {
            // InternalTypesParser.g:4296:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? )
            // InternalTypesParser.g:4296:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )?
            {
            // InternalTypesParser.g:4296:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) )
            // InternalTypesParser.g:4297:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            {
            // InternalTypesParser.g:4297:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            // InternalTypesParser.g:4298:3: lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getThisTypeRefStructuralAccess().getDefinedTypingStrategyTypingStrategyUseSiteOperatorParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_72);
            lv_definedTypingStrategy_0_0=ruleTypingStrategyUseSiteOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getThisTypeRefStructuralRule());
              	        }
                     		set(
                     			current, 
                     			"definedTypingStrategy",
                      		lv_definedTypingStrategy_0_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypingStrategyUseSiteOperator");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,This_1,FOLLOW_73); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getThisTypeRefStructuralAccess().getThisKeyword_1());
                  
            }
            // InternalTypesParser.g:4319:1: (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==With) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalTypesParser.g:4320:2: otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current]
                    {
                    otherlv_2=(Token)match(input,With,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getThisTypeRefStructuralAccess().getWithKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      		if (current==null) {
                      			current = createModelElement(grammarAccess.getThisTypeRefStructuralRule());
                      		}
                              newCompositeNode(grammarAccess.getThisTypeRefStructuralAccess().getTStructMemberListParserRuleCall_2_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructMemberList_3=ruleTStructMemberList(current);

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructMemberList_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisTypeRefStructural"


    // $ANTLR start "entryRuleFunctionTypeExpressionOLD"
    // InternalTypesParser.g:4344:1: entryRuleFunctionTypeExpressionOLD returns [EObject current=null] : iv_ruleFunctionTypeExpressionOLD= ruleFunctionTypeExpressionOLD EOF ;
    public final EObject entryRuleFunctionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionTypeExpressionOLD = null;


        try {
            // InternalTypesParser.g:4345:2: (iv_ruleFunctionTypeExpressionOLD= ruleFunctionTypeExpressionOLD EOF )
            // InternalTypesParser.g:4346:2: iv_ruleFunctionTypeExpressionOLD= ruleFunctionTypeExpressionOLD EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionTypeExpressionOLD=ruleFunctionTypeExpressionOLD();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionTypeExpressionOLD; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionTypeExpressionOLD"


    // $ANTLR start "ruleFunctionTypeExpressionOLD"
    // InternalTypesParser.g:4353:1: ruleFunctionTypeExpressionOLD returns [EObject current=null] : ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket ) ;
    public final EObject ruleFunctionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_declaredThisType_5_0 = null;

        EObject lv_ownedTypeVars_9_0 = null;

        EObject lv_ownedTypeVars_11_0 = null;

        EObject this_TAnonymousFormalParameterList_14 = null;

        EObject lv_returnTypeRef_17_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4356:28: ( ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket ) )
            // InternalTypesParser.g:4357:1: ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket )
            {
            // InternalTypesParser.g:4357:1: ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket )
            // InternalTypesParser.g:4357:2: () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket
            {
            // InternalTypesParser.g:4357:2: ()
            // InternalTypesParser.g:4358:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getFunctionTypeExpressionOLDAccess().getFunctionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,LeftCurlyBracket,FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionTypeExpressionOLDAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // InternalTypesParser.g:4368:1: (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==CommercialAt) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // InternalTypesParser.g:4369:2: otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,CommercialAt,FOLLOW_75); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getFunctionTypeExpressionOLDAccess().getCommercialAtKeyword_2_0());
                          
                    }
                    otherlv_3=(Token)match(input,This,FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getFunctionTypeExpressionOLDAccess().getThisKeyword_2_1());
                          
                    }
                    otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_76); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getFunctionTypeExpressionOLDAccess().getLeftParenthesisKeyword_2_2());
                          
                    }
                    // InternalTypesParser.g:4383:1: ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) )
                    // InternalTypesParser.g:4384:1: (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression )
                    {
                    // InternalTypesParser.g:4384:1: (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression )
                    // InternalTypesParser.g:4385:3: lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDAccess().getDeclaredThisTypeTypeRefFunctionTypeExpressionParserRuleCall_2_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_57);
                    lv_declaredThisType_5_0=ruleTypeRefFunctionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionOLDRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredThisType",
                              		lv_declaredThisType_5_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefFunctionTypeExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,RightParenthesis,FOLLOW_61); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getFunctionTypeExpressionOLDAccess().getRightParenthesisKeyword_2_4());
                          
                    }

                    }
                    break;

            }

            otherlv_7=(Token)match(input,Function,FOLLOW_77); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getFunctionTypeExpressionOLDAccess().getFunctionKeyword_3());
                  
            }
            // InternalTypesParser.g:4411:1: (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==LessThanSign) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // InternalTypesParser.g:4412:2: otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign
                    {
                    otherlv_8=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getFunctionTypeExpressionOLDAccess().getLessThanSignKeyword_4_0());
                          
                    }
                    // InternalTypesParser.g:4416:1: ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:4417:1: (lv_ownedTypeVars_9_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:4417:1: (lv_ownedTypeVars_9_0= ruleTypeVariable )
                    // InternalTypesParser.g:4418:3: lv_ownedTypeVars_9_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDAccess().getOwnedTypeVarsTypeVariableParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_ownedTypeVars_9_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionOLDRule());
                      	        }
                             		add(
                             			current, 
                             			"ownedTypeVars",
                              		lv_ownedTypeVars_9_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:4434:2: (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==Comma) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // InternalTypesParser.g:4435:2: otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_10=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getFunctionTypeExpressionOLDAccess().getCommaKeyword_4_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:4439:1: ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:4440:1: (lv_ownedTypeVars_11_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:4440:1: (lv_ownedTypeVars_11_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:4441:3: lv_ownedTypeVars_11_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDAccess().getOwnedTypeVarsTypeVariableParserRuleCall_4_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_ownedTypeVars_11_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionOLDRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"ownedTypeVars",
                    	              		lv_ownedTypeVars_11_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,GreaterThanSign,FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getFunctionTypeExpressionOLDAccess().getGreaterThanSignKeyword_4_3());
                          
                    }

                    }
                    break;

            }

            otherlv_13=(Token)match(input,LeftParenthesis,FOLLOW_68); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_13, grammarAccess.getFunctionTypeExpressionOLDAccess().getLeftParenthesisKeyword_5());
                  
            }
            if ( state.backtracking==0 ) {
               
              		if (current==null) {
              			current = createModelElement(grammarAccess.getFunctionTypeExpressionOLDRule());
              		}
                      newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDAccess().getTAnonymousFormalParameterListParserRuleCall_6()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_TAnonymousFormalParameterList_14=ruleTAnonymousFormalParameterList(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TAnonymousFormalParameterList_14;
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_15=(Token)match(input,RightParenthesis,FOLLOW_78); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getFunctionTypeExpressionOLDAccess().getRightParenthesisKeyword_7());
                  
            }
            // InternalTypesParser.g:4484:1: (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==Colon) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalTypesParser.g:4485:2: otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) )
                    {
                    otherlv_16=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getFunctionTypeExpressionOLDAccess().getColonKeyword_8_0());
                          
                    }
                    // InternalTypesParser.g:4489:1: ( (lv_returnTypeRef_17_0= ruleTypeRef ) )
                    // InternalTypesParser.g:4490:1: (lv_returnTypeRef_17_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:4490:1: (lv_returnTypeRef_17_0= ruleTypeRef )
                    // InternalTypesParser.g:4491:3: lv_returnTypeRef_17_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionOLDAccess().getReturnTypeRefTypeRefParserRuleCall_8_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_19);
                    lv_returnTypeRef_17_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionOLDRule());
                      	        }
                             		set(
                             			current, 
                             			"returnTypeRef",
                              		lv_returnTypeRef_17_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_18=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_18, grammarAccess.getFunctionTypeExpressionOLDAccess().getRightCurlyBracketKeyword_9());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionTypeExpressionOLD"


    // $ANTLR start "entryRuleArrowFunctionTypeExpression"
    // InternalTypesParser.g:4520:1: entryRuleArrowFunctionTypeExpression returns [EObject current=null] : iv_ruleArrowFunctionTypeExpression= ruleArrowFunctionTypeExpression EOF ;
    public final EObject entryRuleArrowFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrowFunctionTypeExpression = null;


        try {
            // InternalTypesParser.g:4521:2: (iv_ruleArrowFunctionTypeExpression= ruleArrowFunctionTypeExpression EOF )
            // InternalTypesParser.g:4522:2: iv_ruleArrowFunctionTypeExpression= ruleArrowFunctionTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrowFunctionTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArrowFunctionTypeExpression=ruleArrowFunctionTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrowFunctionTypeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrowFunctionTypeExpression"


    // $ANTLR start "ruleArrowFunctionTypeExpression"
    // InternalTypesParser.g:4529:1: ruleArrowFunctionTypeExpression returns [EObject current=null] : ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) ;
    public final EObject ruleArrowFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject this_TAnonymousFormalParameterList_2 = null;

        EObject lv_returnTypeRef_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4532:28: ( ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) )
            // InternalTypesParser.g:4533:1: ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) )
            {
            // InternalTypesParser.g:4533:1: ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) )
            // InternalTypesParser.g:4533:2: () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) )
            {
            // InternalTypesParser.g:4533:2: ()
            // InternalTypesParser.g:4534:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrowFunctionTypeExpressionAccess().getFunctionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,LeftParenthesis,FOLLOW_68); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrowFunctionTypeExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            if ( state.backtracking==0 ) {
               
              		if (current==null) {
              			current = createModelElement(grammarAccess.getArrowFunctionTypeExpressionRule());
              		}
                      newCompositeNode(grammarAccess.getArrowFunctionTypeExpressionAccess().getTAnonymousFormalParameterListParserRuleCall_2()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_TAnonymousFormalParameterList_2=ruleTAnonymousFormalParameterList(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TAnonymousFormalParameterList_2;
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_3=(Token)match(input,RightParenthesis,FOLLOW_69); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getArrowFunctionTypeExpressionAccess().getRightParenthesisKeyword_3());
                  
            }
            otherlv_4=(Token)match(input,EqualsSignGreaterThanSign,FOLLOW_67); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getArrowFunctionTypeExpressionAccess().getEqualsSignGreaterThanSignKeyword_4());
                  
            }
            // InternalTypesParser.g:4566:1: ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) )
            // InternalTypesParser.g:4567:1: (lv_returnTypeRef_5_0= rulePrimaryTypeExpression )
            {
            // InternalTypesParser.g:4567:1: (lv_returnTypeRef_5_0= rulePrimaryTypeExpression )
            // InternalTypesParser.g:4568:3: lv_returnTypeRef_5_0= rulePrimaryTypeExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrowFunctionTypeExpressionAccess().getReturnTypeRefPrimaryTypeExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_returnTypeRef_5_0=rulePrimaryTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getArrowFunctionTypeExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"returnTypeRef",
                      		lv_returnTypeRef_5_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.PrimaryTypeExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrowFunctionTypeExpression"


    // $ANTLR start "ruleTAnonymousFormalParameterList"
    // InternalTypesParser.g:4593:1: ruleTAnonymousFormalParameterList[EObject in_current] returns [EObject current=in_current] : ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )? ;
    public final EObject ruleTAnonymousFormalParameterList(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_1=null;
        EObject lv_fpars_0_0 = null;

        EObject lv_fpars_2_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4596:28: ( ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )? )
            // InternalTypesParser.g:4597:1: ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )?
            {
            // InternalTypesParser.g:4597:1: ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==AssignmnentCompatible||(LA94_0>=AutoboxedType && LA94_0<=Abstract)||LA94_0==Indexed||(LA94_0>=Private && LA94_0<=Static)||LA94_0==Union||(LA94_0>=From && LA94_0<=Void)||(LA94_0>=FullStopFullStopFullStop && LA94_0<=Get)||LA94_0==Set||LA94_0==As||LA94_0==LeftCurlyBracket||LA94_0==Tilde||LA94_0==RULE_IDENTIFIER) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalTypesParser.g:4597:2: ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )*
                    {
                    // InternalTypesParser.g:4597:2: ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) )
                    // InternalTypesParser.g:4598:1: (lv_fpars_0_0= ruleTAnonymousFormalParameter )
                    {
                    // InternalTypesParser.g:4598:1: (lv_fpars_0_0= ruleTAnonymousFormalParameter )
                    // InternalTypesParser.g:4599:3: lv_fpars_0_0= ruleTAnonymousFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterListAccess().getFparsTAnonymousFormalParameterParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_79);
                    lv_fpars_0_0=ruleTAnonymousFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTAnonymousFormalParameterListRule());
                      	        }
                             		add(
                             			current, 
                             			"fpars",
                              		lv_fpars_0_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TAnonymousFormalParameter");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:4615:2: (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==Comma) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // InternalTypesParser.g:4616:2: otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) )
                    	    {
                    	    otherlv_1=(Token)match(input,Comma,FOLLOW_80); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_1, grammarAccess.getTAnonymousFormalParameterListAccess().getCommaKeyword_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:4620:1: ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) )
                    	    // InternalTypesParser.g:4621:1: (lv_fpars_2_0= ruleTAnonymousFormalParameter )
                    	    {
                    	    // InternalTypesParser.g:4621:1: (lv_fpars_2_0= ruleTAnonymousFormalParameter )
                    	    // InternalTypesParser.g:4622:3: lv_fpars_2_0= ruleTAnonymousFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterListAccess().getFparsTAnonymousFormalParameterParserRuleCall_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_79);
                    	    lv_fpars_2_0=ruleTAnonymousFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTAnonymousFormalParameterListRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"fpars",
                    	              		lv_fpars_2_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TAnonymousFormalParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnonymousFormalParameterList"


    // $ANTLR start "entryRuleTAnonymousFormalParameter"
    // InternalTypesParser.g:4646:1: entryRuleTAnonymousFormalParameter returns [EObject current=null] : iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF ;
    public final EObject entryRuleTAnonymousFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnonymousFormalParameter = null;


        try {
            // InternalTypesParser.g:4647:2: (iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF )
            // InternalTypesParser.g:4648:2: iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTAnonymousFormalParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTAnonymousFormalParameter=ruleTAnonymousFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTAnonymousFormalParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTAnonymousFormalParameter"


    // $ANTLR start "ruleTAnonymousFormalParameter"
    // InternalTypesParser.g:4655:1: ruleTAnonymousFormalParameter returns [EObject current=null] : ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTAnonymousFormalParameter() throws RecognitionException {
        EObject current = null;

        Token lv_variadic_0_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_typeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4658:28: ( ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:4659:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:4659:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:4659:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:4659:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==FullStopFullStopFullStop) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalTypesParser.g:4660:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    {
                    // InternalTypesParser.g:4660:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    // InternalTypesParser.g:4661:3: lv_variadic_0_0= FullStopFullStopFullStop
                    {
                    lv_variadic_0_0=(Token)match(input,FullStopFullStopFullStop,FOLLOW_80); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_variadic_0_0, grammarAccess.getTAnonymousFormalParameterAccess().getVariadicFullStopFullStopFullStopKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTAnonymousFormalParameterRule());
                      	        }
                             		setWithLastConsumed(current, "variadic", true, "...");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:4675:3: ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )?
            int alt96=2;
            alt96 = dfa96.predict(input);
            switch (alt96) {
                case 1 :
                    // InternalTypesParser.g:4675:4: ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon
                    {
                    // InternalTypesParser.g:4675:4: ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) )
                    // InternalTypesParser.g:4675:5: ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier )
                    {
                    // InternalTypesParser.g:4680:1: (lv_name_1_0= ruleTIdentifier )
                    // InternalTypesParser.g:4681:3: lv_name_1_0= ruleTIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterAccess().getNameTIdentifierParserRuleCall_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_53);
                    lv_name_1_0=ruleTIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTAnonymousFormalParameterRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_1_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TIdentifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_2=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getTAnonymousFormalParameterAccess().getColonKeyword_1_1());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:4702:3: ( (lv_typeRef_3_0= ruleTypeRef ) )
            // InternalTypesParser.g:4703:1: (lv_typeRef_3_0= ruleTypeRef )
            {
            // InternalTypesParser.g:4703:1: (lv_typeRef_3_0= ruleTypeRef )
            // InternalTypesParser.g:4704:3: lv_typeRef_3_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterAccess().getTypeRefTypeRefParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_typeRef_3_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTAnonymousFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"typeRef",
                      		lv_typeRef_3_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTAnonymousFormalParameter"


    // $ANTLR start "entryRuleTFormalParameter"
    // InternalTypesParser.g:4728:1: entryRuleTFormalParameter returns [EObject current=null] : iv_ruleTFormalParameter= ruleTFormalParameter EOF ;
    public final EObject entryRuleTFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTFormalParameter = null;


        try {
            // InternalTypesParser.g:4729:2: (iv_ruleTFormalParameter= ruleTFormalParameter EOF )
            // InternalTypesParser.g:4730:2: iv_ruleTFormalParameter= ruleTFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTFormalParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTFormalParameter=ruleTFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTFormalParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTFormalParameter"


    // $ANTLR start "ruleTFormalParameter"
    // InternalTypesParser.g:4737:1: ruleTFormalParameter returns [EObject current=null] : ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTFormalParameter() throws RecognitionException {
        EObject current = null;

        Token lv_variadic_0_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_typeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4740:28: ( ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:4741:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:4741:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:4741:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:4741:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==FullStopFullStopFullStop) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalTypesParser.g:4742:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    {
                    // InternalTypesParser.g:4742:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    // InternalTypesParser.g:4743:3: lv_variadic_0_0= FullStopFullStopFullStop
                    {
                    lv_variadic_0_0=(Token)match(input,FullStopFullStopFullStop,FOLLOW_48); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_variadic_0_0, grammarAccess.getTFormalParameterAccess().getVariadicFullStopFullStopFullStopKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTFormalParameterRule());
                      	        }
                             		setWithLastConsumed(current, "variadic", true, "...");
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalTypesParser.g:4757:3: ( (lv_name_1_0= ruleTIdentifier ) )
            // InternalTypesParser.g:4758:1: (lv_name_1_0= ruleTIdentifier )
            {
            // InternalTypesParser.g:4758:1: (lv_name_1_0= ruleTIdentifier )
            // InternalTypesParser.g:4759:3: lv_name_1_0= ruleTIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFormalParameterAccess().getNameTIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_53);
            lv_name_1_0=ruleTIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getTFormalParameterAccess().getColonKeyword_2());
                  
            }
            // InternalTypesParser.g:4780:1: ( (lv_typeRef_3_0= ruleTypeRef ) )
            // InternalTypesParser.g:4781:1: (lv_typeRef_3_0= ruleTypeRef )
            {
            // InternalTypesParser.g:4781:1: (lv_typeRef_3_0= ruleTypeRef )
            // InternalTypesParser.g:4782:3: lv_typeRef_3_0= ruleTypeRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTFormalParameterAccess().getTypeRefTypeRefParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
            lv_typeRef_3_0=ruleTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"typeRef",
                      		lv_typeRef_3_0, 
                      		"eu.numberfour.n4js.ts.Types.TypeRef");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTFormalParameter"


    // $ANTLR start "entryRuleUnionTypeExpressionOLD"
    // InternalTypesParser.g:4806:1: entryRuleUnionTypeExpressionOLD returns [EObject current=null] : iv_ruleUnionTypeExpressionOLD= ruleUnionTypeExpressionOLD EOF ;
    public final EObject entryRuleUnionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnionTypeExpressionOLD = null;


        try {
            // InternalTypesParser.g:4807:2: (iv_ruleUnionTypeExpressionOLD= ruleUnionTypeExpressionOLD EOF )
            // InternalTypesParser.g:4808:2: iv_ruleUnionTypeExpressionOLD= ruleUnionTypeExpressionOLD EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnionTypeExpressionOLDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnionTypeExpressionOLD=ruleUnionTypeExpressionOLD();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnionTypeExpressionOLD; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnionTypeExpressionOLD"


    // $ANTLR start "ruleUnionTypeExpressionOLD"
    // InternalTypesParser.g:4815:1: ruleUnionTypeExpressionOLD returns [EObject current=null] : ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleUnionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_typeRefs_3_0 = null;

        EObject lv_typeRefs_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4818:28: ( ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) )
            // InternalTypesParser.g:4819:1: ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            {
            // InternalTypesParser.g:4819:1: ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            // InternalTypesParser.g:4819:2: () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket
            {
            // InternalTypesParser.g:4819:2: ()
            // InternalTypesParser.g:4820:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getUnionTypeExpressionOLDAccess().getUnionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Union,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUnionTypeExpressionOLDAccess().getUnionKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getUnionTypeExpressionOLDAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:4835:1: ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) )
            // InternalTypesParser.g:4836:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            {
            // InternalTypesParser.g:4836:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            // InternalTypesParser.g:4837:3: lv_typeRefs_3_0= ruleTypeRefWithoutModifiers
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnionTypeExpressionOLDAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_typeRefs_3_0=ruleTypeRefWithoutModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnionTypeExpressionOLDRule());
              	        }
                     		add(
                     			current, 
                     			"typeRefs",
                      		lv_typeRefs_3_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefWithoutModifiers");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:4853:2: (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==Comma) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // InternalTypesParser.g:4854:2: otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    {
            	    otherlv_4=(Token)match(input,Comma,FOLLOW_8); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getUnionTypeExpressionOLDAccess().getCommaKeyword_4_0());
            	          
            	    }
            	    // InternalTypesParser.g:4858:1: ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    // InternalTypesParser.g:4859:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    {
            	    // InternalTypesParser.g:4859:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    // InternalTypesParser.g:4860:3: lv_typeRefs_5_0= ruleTypeRefWithoutModifiers
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getUnionTypeExpressionOLDAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_65);
            	    lv_typeRefs_5_0=ruleTypeRefWithoutModifiers();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getUnionTypeExpressionOLDRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"typeRefs",
            	              		lv_typeRefs_5_0, 
            	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefWithoutModifiers");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getUnionTypeExpressionOLDAccess().getRightCurlyBracketKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnionTypeExpressionOLD"


    // $ANTLR start "entryRuleIntersectionTypeExpressionOLD"
    // InternalTypesParser.g:4889:1: entryRuleIntersectionTypeExpressionOLD returns [EObject current=null] : iv_ruleIntersectionTypeExpressionOLD= ruleIntersectionTypeExpressionOLD EOF ;
    public final EObject entryRuleIntersectionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntersectionTypeExpressionOLD = null;


        try {
            // InternalTypesParser.g:4890:2: (iv_ruleIntersectionTypeExpressionOLD= ruleIntersectionTypeExpressionOLD EOF )
            // InternalTypesParser.g:4891:2: iv_ruleIntersectionTypeExpressionOLD= ruleIntersectionTypeExpressionOLD EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntersectionTypeExpressionOLDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIntersectionTypeExpressionOLD=ruleIntersectionTypeExpressionOLD();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntersectionTypeExpressionOLD; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntersectionTypeExpressionOLD"


    // $ANTLR start "ruleIntersectionTypeExpressionOLD"
    // InternalTypesParser.g:4898:1: ruleIntersectionTypeExpressionOLD returns [EObject current=null] : ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleIntersectionTypeExpressionOLD() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_typeRefs_3_0 = null;

        EObject lv_typeRefs_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4901:28: ( ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) )
            // InternalTypesParser.g:4902:1: ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            {
            // InternalTypesParser.g:4902:1: ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            // InternalTypesParser.g:4902:2: () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket
            {
            // InternalTypesParser.g:4902:2: ()
            // InternalTypesParser.g:4903:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getIntersectionTypeExpressionOLDAccess().getIntersectionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Intersection,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getIntersectionTypeExpressionOLDAccess().getIntersectionKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIntersectionTypeExpressionOLDAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:4918:1: ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) )
            // InternalTypesParser.g:4919:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            {
            // InternalTypesParser.g:4919:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            // InternalTypesParser.g:4920:3: lv_typeRefs_3_0= ruleTypeRefWithoutModifiers
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntersectionTypeExpressionOLDAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_typeRefs_3_0=ruleTypeRefWithoutModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIntersectionTypeExpressionOLDRule());
              	        }
                     		add(
                     			current, 
                     			"typeRefs",
                      		lv_typeRefs_3_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefWithoutModifiers");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:4936:2: (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==Comma) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // InternalTypesParser.g:4937:2: otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    {
            	    otherlv_4=(Token)match(input,Comma,FOLLOW_8); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getIntersectionTypeExpressionOLDAccess().getCommaKeyword_4_0());
            	          
            	    }
            	    // InternalTypesParser.g:4941:1: ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    // InternalTypesParser.g:4942:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    {
            	    // InternalTypesParser.g:4942:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    // InternalTypesParser.g:4943:3: lv_typeRefs_5_0= ruleTypeRefWithoutModifiers
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getIntersectionTypeExpressionOLDAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_65);
            	    lv_typeRefs_5_0=ruleTypeRefWithoutModifiers();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getIntersectionTypeExpressionOLDRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"typeRefs",
            	              		lv_typeRefs_5_0, 
            	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefWithoutModifiers");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getIntersectionTypeExpressionOLDAccess().getRightCurlyBracketKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntersectionTypeExpressionOLD"


    // $ANTLR start "entryRuleParameterizedTypeRef"
    // InternalTypesParser.g:4972:1: entryRuleParameterizedTypeRef returns [EObject current=null] : iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF ;
    public final EObject entryRuleParameterizedTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRef = null;


        try {
            // InternalTypesParser.g:4973:2: (iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF )
            // InternalTypesParser.g:4974:2: iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterizedTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameterizedTypeRef=ruleParameterizedTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterizedTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterizedTypeRef"


    // $ANTLR start "ruleParameterizedTypeRef"
    // InternalTypesParser.g:4981:1: ruleParameterizedTypeRef returns [EObject current=null] : (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural ) ;
    public final EObject ruleParameterizedTypeRef() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRefNominal_0 = null;

        EObject this_ParameterizedTypeRefStructural_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4984:28: ( (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural ) )
            // InternalTypesParser.g:4985:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural )
            {
            // InternalTypesParser.g:4985:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural )
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==Undefined||LA100_0==Indexed||LA100_0==Null||LA100_0==Void||LA100_0==Any||LA100_0==RULE_IDENTIFIER) ) {
                alt100=1;
            }
            else if ( (LA100_0==Tilde) ) {
                alt100=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }
            switch (alt100) {
                case 1 :
                    // InternalTypesParser.g:4986:5: this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getParameterizedTypeRefAccess().getParameterizedTypeRefNominalParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ParameterizedTypeRefNominal_0=ruleParameterizedTypeRefNominal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ParameterizedTypeRefNominal_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:4996:5: this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getParameterizedTypeRefAccess().getParameterizedTypeRefStructuralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ParameterizedTypeRefStructural_1=ruleParameterizedTypeRefStructural();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ParameterizedTypeRefStructural_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterizedTypeRef"


    // $ANTLR start "entryRuleParameterizedTypeRefNominal"
    // InternalTypesParser.g:5012:1: entryRuleParameterizedTypeRefNominal returns [EObject current=null] : iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF ;
    public final EObject entryRuleParameterizedTypeRefNominal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRefNominal = null;


        try {
            // InternalTypesParser.g:5013:2: (iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF )
            // InternalTypesParser.g:5014:2: iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterizedTypeRefNominalRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameterizedTypeRefNominal=ruleParameterizedTypeRefNominal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterizedTypeRefNominal; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterizedTypeRefNominal"


    // $ANTLR start "ruleParameterizedTypeRefNominal"
    // InternalTypesParser.g:5021:1: ruleParameterizedTypeRefNominal returns [EObject current=null] : ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? ) ;
    public final EObject ruleParameterizedTypeRefNominal() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_typeArgs_2_0 = null;

        EObject lv_typeArgs_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5024:28: ( ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? ) )
            // InternalTypesParser.g:5025:1: ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? )
            {
            // InternalTypesParser.g:5025:1: ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? )
            // InternalTypesParser.g:5025:2: ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?
            {
            // InternalTypesParser.g:5025:2: ( ( ruleTypeReferenceName ) )
            // InternalTypesParser.g:5026:1: ( ruleTypeReferenceName )
            {
            // InternalTypesParser.g:5026:1: ( ruleTypeReferenceName )
            // InternalTypesParser.g:5027:3: ruleTypeReferenceName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getParameterizedTypeRefNominalRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getParameterizedTypeRefNominalAccess().getDeclaredTypeTypeCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_81);
            ruleTypeReferenceName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:5041:2: ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?
            int alt102=2;
            alt102 = dfa102.predict(input);
            switch (alt102) {
                case 1 :
                    // InternalTypesParser.g:5041:3: ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign
                    {
                    // InternalTypesParser.g:5041:3: ( ( LessThanSign )=>otherlv_1= LessThanSign )
                    // InternalTypesParser.g:5041:4: ( LessThanSign )=>otherlv_1= LessThanSign
                    {
                    otherlv_1=(Token)match(input,LessThanSign,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getParameterizedTypeRefNominalAccess().getLessThanSignKeyword_1_0());
                          
                    }

                    }

                    // InternalTypesParser.g:5048:2: ( (lv_typeArgs_2_0= ruleTypeArgument ) )
                    // InternalTypesParser.g:5049:1: (lv_typeArgs_2_0= ruleTypeArgument )
                    {
                    // InternalTypesParser.g:5049:1: (lv_typeArgs_2_0= ruleTypeArgument )
                    // InternalTypesParser.g:5050:3: lv_typeArgs_2_0= ruleTypeArgument
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getParameterizedTypeRefNominalAccess().getTypeArgsTypeArgumentParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeArgs_2_0=ruleTypeArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getParameterizedTypeRefNominalRule());
                      	        }
                             		add(
                             			current, 
                             			"typeArgs",
                              		lv_typeArgs_2_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeArgument");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:5066:2: (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )*
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==Comma) ) {
                            alt101=1;
                        }


                        switch (alt101) {
                    	case 1 :
                    	    // InternalTypesParser.g:5067:2: otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_82); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getParameterizedTypeRefNominalAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5071:1: ( (lv_typeArgs_4_0= ruleTypeArgument ) )
                    	    // InternalTypesParser.g:5072:1: (lv_typeArgs_4_0= ruleTypeArgument )
                    	    {
                    	    // InternalTypesParser.g:5072:1: (lv_typeArgs_4_0= ruleTypeArgument )
                    	    // InternalTypesParser.g:5073:3: lv_typeArgs_4_0= ruleTypeArgument
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getParameterizedTypeRefNominalAccess().getTypeArgsTypeArgumentParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeArgs_4_0=ruleTypeArgument();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getParameterizedTypeRefNominalRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeArgs",
                    	              		lv_typeArgs_4_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeArgument");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop101;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,GreaterThanSign,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getParameterizedTypeRefNominalAccess().getGreaterThanSignKeyword_1_3());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterizedTypeRefNominal"


    // $ANTLR start "entryRuleArrayTypeRef"
    // InternalTypesParser.g:5102:1: entryRuleArrayTypeRef returns [EObject current=null] : iv_ruleArrayTypeRef= ruleArrayTypeRef EOF ;
    public final EObject entryRuleArrayTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayTypeRef = null;


        try {
            // InternalTypesParser.g:5103:2: (iv_ruleArrayTypeRef= ruleArrayTypeRef EOF )
            // InternalTypesParser.g:5104:2: iv_ruleArrayTypeRef= ruleArrayTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArrayTypeRef=ruleArrayTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayTypeRef"


    // $ANTLR start "ruleArrayTypeRef"
    // InternalTypesParser.g:5111:1: ruleArrayTypeRef returns [EObject current=null] : ( ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) ) ( (lv_typeArgs_1_0= ruleTypeArgument ) ) otherlv_2= RightSquareBracket ) ;
    public final EObject ruleArrayTypeRef() throws RecognitionException {
        EObject current = null;

        Token lv_arrayTypeLiteral_0_0=null;
        Token otherlv_2=null;
        EObject lv_typeArgs_1_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5114:28: ( ( ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) ) ( (lv_typeArgs_1_0= ruleTypeArgument ) ) otherlv_2= RightSquareBracket ) )
            // InternalTypesParser.g:5115:1: ( ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) ) ( (lv_typeArgs_1_0= ruleTypeArgument ) ) otherlv_2= RightSquareBracket )
            {
            // InternalTypesParser.g:5115:1: ( ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) ) ( (lv_typeArgs_1_0= ruleTypeArgument ) ) otherlv_2= RightSquareBracket )
            // InternalTypesParser.g:5115:2: ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) ) ( (lv_typeArgs_1_0= ruleTypeArgument ) ) otherlv_2= RightSquareBracket
            {
            // InternalTypesParser.g:5115:2: ( (lv_arrayTypeLiteral_0_0= LeftSquareBracket ) )
            // InternalTypesParser.g:5116:1: (lv_arrayTypeLiteral_0_0= LeftSquareBracket )
            {
            // InternalTypesParser.g:5116:1: (lv_arrayTypeLiteral_0_0= LeftSquareBracket )
            // InternalTypesParser.g:5117:3: lv_arrayTypeLiteral_0_0= LeftSquareBracket
            {
            lv_arrayTypeLiteral_0_0=(Token)match(input,LeftSquareBracket,FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_arrayTypeLiteral_0_0, grammarAccess.getArrayTypeRefAccess().getArrayTypeLiteralLeftSquareBracketKeyword_0_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getArrayTypeRefRule());
              	        }
                     		setWithLastConsumed(current, "arrayTypeLiteral", true, "[");
              	    
            }

            }


            }

            // InternalTypesParser.g:5131:2: ( (lv_typeArgs_1_0= ruleTypeArgument ) )
            // InternalTypesParser.g:5132:1: (lv_typeArgs_1_0= ruleTypeArgument )
            {
            // InternalTypesParser.g:5132:1: (lv_typeArgs_1_0= ruleTypeArgument )
            // InternalTypesParser.g:5133:3: lv_typeArgs_1_0= ruleTypeArgument
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayTypeRefAccess().getTypeArgsTypeArgumentParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_22);
            lv_typeArgs_1_0=ruleTypeArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getArrayTypeRefRule());
              	        }
                     		add(
                     			current, 
                     			"typeArgs",
                      		lv_typeArgs_1_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeArgument");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,RightSquareBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getArrayTypeRefAccess().getRightSquareBracketKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayTypeRef"


    // $ANTLR start "entryRuleParameterizedTypeRefStructural"
    // InternalTypesParser.g:5162:1: entryRuleParameterizedTypeRefStructural returns [EObject current=null] : iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF ;
    public final EObject entryRuleParameterizedTypeRefStructural() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRefStructural = null;


        try {
            // InternalTypesParser.g:5163:2: (iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF )
            // InternalTypesParser.g:5164:2: iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameterizedTypeRefStructural=ruleParameterizedTypeRefStructural();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterizedTypeRefStructural; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterizedTypeRefStructural"


    // $ANTLR start "ruleParameterizedTypeRefStructural"
    // InternalTypesParser.g:5171:1: ruleParameterizedTypeRefStructural returns [EObject current=null] : ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? ) ;
    public final EObject ruleParameterizedTypeRefStructural() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_definedTypingStrategy_0_0 = null;

        EObject lv_typeArgs_3_0 = null;

        EObject lv_typeArgs_5_0 = null;

        EObject this_TStructMemberList_8 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5174:28: ( ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? ) )
            // InternalTypesParser.g:5175:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? )
            {
            // InternalTypesParser.g:5175:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? )
            // InternalTypesParser.g:5175:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )?
            {
            // InternalTypesParser.g:5175:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) )
            // InternalTypesParser.g:5176:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            {
            // InternalTypesParser.g:5176:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            // InternalTypesParser.g:5177:3: lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getDefinedTypingStrategyTypingStrategyUseSiteOperatorParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_15);
            lv_definedTypingStrategy_0_0=ruleTypingStrategyUseSiteOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getParameterizedTypeRefStructuralRule());
              	        }
                     		set(
                     			current, 
                     			"definedTypingStrategy",
                      		lv_definedTypingStrategy_0_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypingStrategyUseSiteOperator");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:5193:2: ( ( ruleTypeReferenceName ) )
            // InternalTypesParser.g:5194:1: ( ruleTypeReferenceName )
            {
            // InternalTypesParser.g:5194:1: ( ruleTypeReferenceName )
            // InternalTypesParser.g:5195:3: ruleTypeReferenceName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getParameterizedTypeRefStructuralRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getDeclaredTypeTypeCrossReference_1_0()); 
              	    
            }
            pushFollow(FOLLOW_83);
            ruleTypeReferenceName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:5209:2: ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )?
            int alt104=2;
            alt104 = dfa104.predict(input);
            switch (alt104) {
                case 1 :
                    // InternalTypesParser.g:5209:3: ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign
                    {
                    // InternalTypesParser.g:5209:3: ( ( LessThanSign )=>otherlv_2= LessThanSign )
                    // InternalTypesParser.g:5209:4: ( LessThanSign )=>otherlv_2= LessThanSign
                    {
                    otherlv_2=(Token)match(input,LessThanSign,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getParameterizedTypeRefStructuralAccess().getLessThanSignKeyword_2_0());
                          
                    }

                    }

                    // InternalTypesParser.g:5216:2: ( (lv_typeArgs_3_0= ruleTypeArgument ) )
                    // InternalTypesParser.g:5217:1: (lv_typeArgs_3_0= ruleTypeArgument )
                    {
                    // InternalTypesParser.g:5217:1: (lv_typeArgs_3_0= ruleTypeArgument )
                    // InternalTypesParser.g:5218:3: lv_typeArgs_3_0= ruleTypeArgument
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getTypeArgsTypeArgumentParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeArgs_3_0=ruleTypeArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getParameterizedTypeRefStructuralRule());
                      	        }
                             		add(
                             			current, 
                             			"typeArgs",
                              		lv_typeArgs_3_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeArgument");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:5234:2: (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )*
                    loop103:
                    do {
                        int alt103=2;
                        int LA103_0 = input.LA(1);

                        if ( (LA103_0==Comma) ) {
                            alt103=1;
                        }


                        switch (alt103) {
                    	case 1 :
                    	    // InternalTypesParser.g:5235:2: otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_82); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_4, grammarAccess.getParameterizedTypeRefStructuralAccess().getCommaKeyword_2_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5239:1: ( (lv_typeArgs_5_0= ruleTypeArgument ) )
                    	    // InternalTypesParser.g:5240:1: (lv_typeArgs_5_0= ruleTypeArgument )
                    	    {
                    	    // InternalTypesParser.g:5240:1: (lv_typeArgs_5_0= ruleTypeArgument )
                    	    // InternalTypesParser.g:5241:3: lv_typeArgs_5_0= ruleTypeArgument
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getTypeArgsTypeArgumentParserRuleCall_2_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeArgs_5_0=ruleTypeArgument();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getParameterizedTypeRefStructuralRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeArgs",
                    	              		lv_typeArgs_5_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeArgument");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop103;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,GreaterThanSign,FOLLOW_73); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getParameterizedTypeRefStructuralAccess().getGreaterThanSignKeyword_2_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:5262:3: (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==With) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalTypesParser.g:5263:2: otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current]
                    {
                    otherlv_7=(Token)match(input,With,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getParameterizedTypeRefStructuralAccess().getWithKeyword_3_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      		if (current==null) {
                      			current = createModelElement(grammarAccess.getParameterizedTypeRefStructuralRule());
                      		}
                              newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getTStructMemberListParserRuleCall_3_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructMemberList_8=ruleTStructMemberList(current);

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructMemberList_8;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterizedTypeRefStructural"


    // $ANTLR start "ruleTStructMemberList"
    // InternalTypesParser.g:5288:1: ruleTStructMemberList[EObject in_current] returns [EObject current=in_current] : (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleTStructMemberList(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_astStructuralMembers_1_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5291:28: ( (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:5292:1: (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:5292:1: (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:5293:2: otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,LeftCurlyBracket,FOLLOW_84); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTStructMemberListAccess().getLeftCurlyBracketKeyword_0());
                  
            }
            // InternalTypesParser.g:5297:1: ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )*
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==AssignmnentCompatible||(LA107_0>=AutoboxedType && LA107_0<=VirtualBase)||LA107_0==Primitive||(LA107_0>=Undefined && LA107_0<=Abstract)||(LA107_0>=Project && LA107_0<=Object)||LA107_0==Union||(LA107_0>=From && LA107_0<=Null)||(LA107_0>=Type && LA107_0<=Void)||(LA107_0>=Any && LA107_0<=Get)||LA107_0==Set||LA107_0==As||LA107_0==LessThanSign||LA107_0==RULE_IDENTIFIER) ) {
                    alt107=1;
                }


                switch (alt107) {
            	case 1 :
            	    // InternalTypesParser.g:5297:2: ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )?
            	    {
            	    // InternalTypesParser.g:5297:2: ( (lv_astStructuralMembers_1_0= ruleTStructMember ) )
            	    // InternalTypesParser.g:5298:1: (lv_astStructuralMembers_1_0= ruleTStructMember )
            	    {
            	    // InternalTypesParser.g:5298:1: (lv_astStructuralMembers_1_0= ruleTStructMember )
            	    // InternalTypesParser.g:5299:3: lv_astStructuralMembers_1_0= ruleTStructMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTStructMemberListAccess().getAstStructuralMembersTStructMemberParserRuleCall_1_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_85);
            	    lv_astStructuralMembers_1_0=ruleTStructMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTStructMemberListRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"astStructuralMembers",
            	              		lv_astStructuralMembers_1_0, 
            	              		"eu.numberfour.n4js.ts.TypeExpressions.TStructMember");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // InternalTypesParser.g:5315:2: (otherlv_2= Semicolon | otherlv_3= Comma )?
            	    int alt106=3;
            	    int LA106_0 = input.LA(1);

            	    if ( (LA106_0==Semicolon) ) {
            	        alt106=1;
            	    }
            	    else if ( (LA106_0==Comma) ) {
            	        alt106=2;
            	    }
            	    switch (alt106) {
            	        case 1 :
            	            // InternalTypesParser.g:5316:2: otherlv_2= Semicolon
            	            {
            	            otherlv_2=(Token)match(input,Semicolon,FOLLOW_84); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_2, grammarAccess.getTStructMemberListAccess().getSemicolonKeyword_1_1_0());
            	                  
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalTypesParser.g:5322:2: otherlv_3= Comma
            	            {
            	            otherlv_3=(Token)match(input,Comma,FOLLOW_84); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_3, grammarAccess.getTStructMemberListAccess().getCommaKeyword_1_1_1());
            	                  
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop107;
                }
            } while (true);

            otherlv_4=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTStructMemberListAccess().getRightCurlyBracketKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructMemberList"


    // $ANTLR start "entryRuleTStructMember"
    // InternalTypesParser.g:5339:1: entryRuleTStructMember returns [EObject current=null] : iv_ruleTStructMember= ruleTStructMember EOF ;
    public final EObject entryRuleTStructMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructMember = null;


        try {
            // InternalTypesParser.g:5340:2: (iv_ruleTStructMember= ruleTStructMember EOF )
            // InternalTypesParser.g:5341:2: iv_ruleTStructMember= ruleTStructMember EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTStructMemberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTStructMember=ruleTStructMember();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTStructMember; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTStructMember"


    // $ANTLR start "ruleTStructMember"
    // InternalTypesParser.g:5348:1: ruleTStructMember returns [EObject current=null] : ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField ) ;
    public final EObject ruleTStructMember() throws RecognitionException {
        EObject current = null;

        EObject this_TStructGetter_0 = null;

        EObject this_TStructSetter_1 = null;

        EObject this_TStructMethod_2 = null;

        EObject this_TStructField_3 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5351:28: ( ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField ) )
            // InternalTypesParser.g:5352:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )
            {
            // InternalTypesParser.g:5352:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )
            int alt108=4;
            alt108 = dfa108.predict(input);
            switch (alt108) {
                case 1 :
                    // InternalTypesParser.g:5352:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter )
                    {
                    // InternalTypesParser.g:5352:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter )
                    // InternalTypesParser.g:5352:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTStructMemberAccess().getTStructGetterParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructGetter_0=ruleTStructGetter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructGetter_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:5369:6: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter )
                    {
                    // InternalTypesParser.g:5369:6: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter )
                    // InternalTypesParser.g:5369:7: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTStructMemberAccess().getTStructSetterParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructSetter_1=ruleTStructSetter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructSetter_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:5386:6: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod )
                    {
                    // InternalTypesParser.g:5386:6: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod )
                    // InternalTypesParser.g:5386:7: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTStructMemberAccess().getTStructMethodParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructMethod_2=ruleTStructMethod();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructMethod_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:5418:5: this_TStructField_3= ruleTStructField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTStructMemberAccess().getTStructFieldParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TStructField_3=ruleTStructField();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TStructField_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructMember"


    // $ANTLR start "entryRuleTStructMethod"
    // InternalTypesParser.g:5434:1: entryRuleTStructMethod returns [EObject current=null] : iv_ruleTStructMethod= ruleTStructMethod EOF ;
    public final EObject entryRuleTStructMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructMethod = null;


        try {
            // InternalTypesParser.g:5435:2: (iv_ruleTStructMethod= ruleTStructMethod EOF )
            // InternalTypesParser.g:5436:2: iv_ruleTStructMethod= ruleTStructMethod EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTStructMethodRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTStructMethod=ruleTStructMethod();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTStructMethod; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTStructMethod"


    // $ANTLR start "ruleTStructMethod"
    // InternalTypesParser.g:5443:1: ruleTStructMethod returns [EObject current=null] : ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? ) ;
    public final EObject ruleTStructMethod() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        EObject lv_typeVars_2_0 = null;

        EObject lv_typeVars_4_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;

        EObject this_TAnonymousFormalParameterList_8 = null;

        EObject lv_returnTypeRef_11_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5446:28: ( ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:5447:1: ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:5447:1: ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:5447:2: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:5447:2: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) )
            // InternalTypesParser.g:5447:3: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis )
            {
            // InternalTypesParser.g:5468:5: ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis )
            // InternalTypesParser.g:5468:6: () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis
            {
            // InternalTypesParser.g:5468:6: ()
            // InternalTypesParser.g:5469:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTStructMethodAccess().getTStructMethodAction_0_0_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:5474:2: (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==LessThanSign) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // InternalTypesParser.g:5475:2: otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign
                    {
                    otherlv_1=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTStructMethodAccess().getLessThanSignKeyword_0_0_1_0());
                          
                    }
                    // InternalTypesParser.g:5479:1: ( (lv_typeVars_2_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:5480:1: (lv_typeVars_2_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:5480:1: (lv_typeVars_2_0= ruleTypeVariable )
                    // InternalTypesParser.g:5481:3: lv_typeVars_2_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTStructMethodAccess().getTypeVarsTypeVariableParserRuleCall_0_0_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_typeVars_2_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTStructMethodRule());
                      	        }
                             		add(
                             			current, 
                             			"typeVars",
                              		lv_typeVars_2_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:5497:2: (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )*
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==Comma) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // InternalTypesParser.g:5498:2: otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTStructMethodAccess().getCommaKeyword_0_0_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5502:1: ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:5503:1: (lv_typeVars_4_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:5503:1: (lv_typeVars_4_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:5504:3: lv_typeVars_4_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTStructMethodAccess().getTypeVarsTypeVariableParserRuleCall_0_0_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_typeVars_4_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTStructMethodRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeVars",
                    	              		lv_typeVars_4_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.TypeVariable");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop109;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,GreaterThanSign,FOLLOW_11); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTStructMethodAccess().getGreaterThanSignKeyword_0_0_1_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:5525:3: ( (lv_name_6_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5526:1: (lv_name_6_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5526:1: (lv_name_6_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5527:3: lv_name_6_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructMethodAccess().getNameTypesIdentifierParserRuleCall_0_0_2_0()); 
              	    
            }
            pushFollow(FOLLOW_52);
            lv_name_6_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTStructMethodRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_6_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_7=(Token)match(input,LeftParenthesis,FOLLOW_68); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getTStructMethodAccess().getLeftParenthesisKeyword_0_0_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               
              		if (current==null) {
              			current = createModelElement(grammarAccess.getTStructMethodRule());
              		}
                      newCompositeNode(grammarAccess.getTStructMethodAccess().getTAnonymousFormalParameterListParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_TAnonymousFormalParameterList_8=ruleTAnonymousFormalParameterList(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TAnonymousFormalParameterList_8;
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_9=(Token)match(input,RightParenthesis,FOLLOW_86); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getTStructMethodAccess().getRightParenthesisKeyword_2());
                  
            }
            // InternalTypesParser.g:5565:1: (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==Colon) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalTypesParser.g:5566:2: otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) )
                    {
                    otherlv_10=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getTStructMethodAccess().getColonKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:5570:1: ( (lv_returnTypeRef_11_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5571:1: (lv_returnTypeRef_11_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5571:1: (lv_returnTypeRef_11_0= ruleTypeRef )
                    // InternalTypesParser.g:5572:3: lv_returnTypeRef_11_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTStructMethodAccess().getReturnTypeRefTypeRefParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_returnTypeRef_11_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTStructMethodRule());
                      	        }
                             		set(
                             			current, 
                             			"returnTypeRef",
                              		lv_returnTypeRef_11_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructMethod"


    // $ANTLR start "entryRuleTStructField"
    // InternalTypesParser.g:5596:1: entryRuleTStructField returns [EObject current=null] : iv_ruleTStructField= ruleTStructField EOF ;
    public final EObject entryRuleTStructField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructField = null;


        try {
            // InternalTypesParser.g:5597:2: (iv_ruleTStructField= ruleTStructField EOF )
            // InternalTypesParser.g:5598:2: iv_ruleTStructField= ruleTStructField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTStructFieldRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTStructField=ruleTStructField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTStructField; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTStructField"


    // $ANTLR start "ruleTStructField"
    // InternalTypesParser.g:5605:1: ruleTStructField returns [EObject current=null] : ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? ) ;
    public final EObject ruleTStructField() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_typeRef_2_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5608:28: ( ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:5609:1: ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:5609:1: ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:5609:2: ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:5609:2: ( (lv_name_0_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5610:1: (lv_name_0_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5610:1: (lv_name_0_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5611:3: lv_name_0_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructFieldAccess().getNameTypesIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_86);
            lv_name_0_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTStructFieldRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:5627:2: (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==Colon) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // InternalTypesParser.g:5628:2: otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) )
                    {
                    otherlv_1=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTStructFieldAccess().getColonKeyword_1_0());
                          
                    }
                    // InternalTypesParser.g:5632:1: ( (lv_typeRef_2_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5633:1: (lv_typeRef_2_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5633:1: (lv_typeRef_2_0= ruleTypeRef )
                    // InternalTypesParser.g:5634:3: lv_typeRef_2_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTStructFieldAccess().getTypeRefTypeRefParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_typeRef_2_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTStructFieldRule());
                      	        }
                             		set(
                             			current, 
                             			"typeRef",
                              		lv_typeRef_2_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructField"


    // $ANTLR start "entryRuleTStructGetter"
    // InternalTypesParser.g:5658:1: entryRuleTStructGetter returns [EObject current=null] : iv_ruleTStructGetter= ruleTStructGetter EOF ;
    public final EObject entryRuleTStructGetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructGetter = null;


        try {
            // InternalTypesParser.g:5659:2: (iv_ruleTStructGetter= ruleTStructGetter EOF )
            // InternalTypesParser.g:5660:2: iv_ruleTStructGetter= ruleTStructGetter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTStructGetterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTStructGetter=ruleTStructGetter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTStructGetter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTStructGetter"


    // $ANTLR start "ruleTStructGetter"
    // InternalTypesParser.g:5667:1: ruleTStructGetter returns [EObject current=null] : ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? ) ;
    public final EObject ruleTStructGetter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_declaredTypeRef_6_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5670:28: ( ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:5671:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:5671:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:5671:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:5671:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) )
            // InternalTypesParser.g:5671:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            {
            // InternalTypesParser.g:5678:6: ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            // InternalTypesParser.g:5678:7: () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) )
            {
            // InternalTypesParser.g:5678:7: ()
            // InternalTypesParser.g:5679:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTStructGetterAccess().getTStructGetterAction_0_0_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Get,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTStructGetterAccess().getGetKeyword_0_0_1());
                  
            }
            // InternalTypesParser.g:5689:1: ( (lv_name_2_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5690:1: (lv_name_2_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5690:1: (lv_name_2_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5691:3: lv_name_2_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructGetterAccess().getNameTypesIdentifierParserRuleCall_0_0_2_0()); 
              	    
            }
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTStructGetterRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_2_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            otherlv_3=(Token)match(input,LeftParenthesis,FOLLOW_57); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTStructGetterAccess().getLeftParenthesisKeyword_1());
                  
            }
            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_86); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTStructGetterAccess().getRightParenthesisKeyword_2());
                  
            }
            // InternalTypesParser.g:5717:1: (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==Colon) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // InternalTypesParser.g:5718:2: otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) )
                    {
                    otherlv_5=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTStructGetterAccess().getColonKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:5722:1: ( (lv_declaredTypeRef_6_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5723:1: (lv_declaredTypeRef_6_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5723:1: (lv_declaredTypeRef_6_0= ruleTypeRef )
                    // InternalTypesParser.g:5724:3: lv_declaredTypeRef_6_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTStructGetterAccess().getDeclaredTypeRefTypeRefParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_declaredTypeRef_6_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTStructGetterRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredTypeRef",
                              		lv_declaredTypeRef_6_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructGetter"


    // $ANTLR start "entryRuleTStructSetter"
    // InternalTypesParser.g:5748:1: entryRuleTStructSetter returns [EObject current=null] : iv_ruleTStructSetter= ruleTStructSetter EOF ;
    public final EObject entryRuleTStructSetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructSetter = null;


        try {
            // InternalTypesParser.g:5749:2: (iv_ruleTStructSetter= ruleTStructSetter EOF )
            // InternalTypesParser.g:5750:2: iv_ruleTStructSetter= ruleTStructSetter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTStructSetterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTStructSetter=ruleTStructSetter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTStructSetter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTStructSetter"


    // $ANTLR start "ruleTStructSetter"
    // InternalTypesParser.g:5757:1: ruleTStructSetter returns [EObject current=null] : ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis ) ;
    public final EObject ruleTStructSetter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_fpar_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5760:28: ( ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis ) )
            // InternalTypesParser.g:5761:1: ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis )
            {
            // InternalTypesParser.g:5761:1: ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis )
            // InternalTypesParser.g:5761:2: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis
            {
            // InternalTypesParser.g:5761:2: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) )
            // InternalTypesParser.g:5761:3: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            {
            // InternalTypesParser.g:5768:6: ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            // InternalTypesParser.g:5768:7: () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) )
            {
            // InternalTypesParser.g:5768:7: ()
            // InternalTypesParser.g:5769:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTStructSetterAccess().getTStructSetterAction_0_0_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Set,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTStructSetterAccess().getSetKeyword_0_0_1());
                  
            }
            // InternalTypesParser.g:5779:1: ( (lv_name_2_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5780:1: (lv_name_2_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5780:1: (lv_name_2_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5781:3: lv_name_2_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructSetterAccess().getNameTypesIdentifierParserRuleCall_0_0_2_0()); 
              	    
            }
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleTypesIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTStructSetterRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_2_0, 
                      		"eu.numberfour.n4js.ts.Types.TypesIdentifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            otherlv_3=(Token)match(input,LeftParenthesis,FOLLOW_80); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTStructSetterAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalTypesParser.g:5802:1: ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) )
            // InternalTypesParser.g:5803:1: (lv_fpar_4_0= ruleTAnonymousFormalParameter )
            {
            // InternalTypesParser.g:5803:1: (lv_fpar_4_0= ruleTAnonymousFormalParameter )
            // InternalTypesParser.g:5804:3: lv_fpar_4_0= ruleTAnonymousFormalParameter
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructSetterAccess().getFparTAnonymousFormalParameterParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_57);
            lv_fpar_4_0=ruleTAnonymousFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTStructSetterRule());
              	        }
                     		set(
                     			current, 
                     			"fpar",
                      		lv_fpar_4_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TAnonymousFormalParameter");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_5=(Token)match(input,RightParenthesis,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getTStructSetterAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTStructSetter"


    // $ANTLR start "entryRuleTypingStrategyUseSiteOperator"
    // InternalTypesParser.g:5833:1: entryRuleTypingStrategyUseSiteOperator returns [String current=null] : iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF ;
    public final String entryRuleTypingStrategyUseSiteOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypingStrategyUseSiteOperator = null;


        try {
            // InternalTypesParser.g:5834:1: (iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF )
            // InternalTypesParser.g:5835:2: iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypingStrategyUseSiteOperatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypingStrategyUseSiteOperator=ruleTypingStrategyUseSiteOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypingStrategyUseSiteOperator.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypingStrategyUseSiteOperator"


    // $ANTLR start "ruleTypingStrategyUseSiteOperator"
    // InternalTypesParser.g:5842:1: ruleTypingStrategyUseSiteOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? ) ;
    public final AntlrDatatypeRuleToken ruleTypingStrategyUseSiteOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_STRUCTMODSUFFIX_2=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:5846:6: ( (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? ) )
            // InternalTypesParser.g:5847:1: (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? )
            {
            // InternalTypesParser.g:5847:1: (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? )
            // InternalTypesParser.g:5848:2: kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )?
            {
            kw=(Token)match(input,Tilde,FOLLOW_87); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypingStrategyUseSiteOperatorAccess().getTildeKeyword_0()); 
                  
            }
            // InternalTypesParser.g:5853:1: (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )?
            int alt114=3;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==Tilde) ) {
                alt114=1;
            }
            else if ( (LA114_0==RULE_STRUCTMODSUFFIX) ) {
                alt114=2;
            }
            switch (alt114) {
                case 1 :
                    // InternalTypesParser.g:5854:2: kw= Tilde
                    {
                    kw=(Token)match(input,Tilde,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypingStrategyUseSiteOperatorAccess().getTildeKeyword_1_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:5860:10: this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX
                    {
                    this_STRUCTMODSUFFIX_2=(Token)match(input,RULE_STRUCTMODSUFFIX,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_STRUCTMODSUFFIX_2);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_STRUCTMODSUFFIX_2, grammarAccess.getTypingStrategyUseSiteOperatorAccess().getSTRUCTMODSUFFIXTerminalRuleCall_1_1()); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypingStrategyUseSiteOperator"


    // $ANTLR start "entryRuleTypingStrategyDefSiteOperator"
    // InternalTypesParser.g:5875:1: entryRuleTypingStrategyDefSiteOperator returns [String current=null] : iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF ;
    public final String entryRuleTypingStrategyDefSiteOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypingStrategyDefSiteOperator = null;


        try {
            // InternalTypesParser.g:5876:1: (iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF )
            // InternalTypesParser.g:5877:2: iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypingStrategyDefSiteOperatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypingStrategyDefSiteOperator=ruleTypingStrategyDefSiteOperator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypingStrategyDefSiteOperator.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypingStrategyDefSiteOperator"


    // $ANTLR start "ruleTypingStrategyDefSiteOperator"
    // InternalTypesParser.g:5884:1: ruleTypingStrategyDefSiteOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= Tilde ;
    public final AntlrDatatypeRuleToken ruleTypingStrategyDefSiteOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:5888:6: (kw= Tilde )
            // InternalTypesParser.g:5890:2: kw= Tilde
            {
            kw=(Token)match(input,Tilde,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypingStrategyDefSiteOperatorAccess().getTildeKeyword()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypingStrategyDefSiteOperator"


    // $ANTLR start "entryRuleConstructorTypeRef"
    // InternalTypesParser.g:5903:1: entryRuleConstructorTypeRef returns [EObject current=null] : iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF ;
    public final EObject entryRuleConstructorTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorTypeRef = null;


        try {
            // InternalTypesParser.g:5904:2: (iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF )
            // InternalTypesParser.g:5905:2: iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConstructorTypeRef=ruleConstructorTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstructorTypeRef"


    // $ANTLR start "ruleConstructorTypeRef"
    // InternalTypesParser.g:5912:1: ruleConstructorTypeRef returns [EObject current=null] : ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleConstructorTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_staticTypeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5915:28: ( ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:5916:1: ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:5916:1: ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:5916:2: () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket
            {
            // InternalTypesParser.g:5916:2: ()
            // InternalTypesParser.g:5917:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getConstructorTypeRefAccess().getConstructorTypeRefAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Constructor,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getConstructorTypeRefAccess().getConstructorKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_88); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getConstructorTypeRefAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:5932:1: ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) )
            // InternalTypesParser.g:5933:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            {
            // InternalTypesParser.g:5933:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            // InternalTypesParser.g:5934:3: lv_staticTypeRef_3_0= ruleTypeRefInClassifierType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorTypeRefAccess().getStaticTypeRefTypeRefInClassifierTypeParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_19);
            lv_staticTypeRef_3_0=ruleTypeRefInClassifierType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getConstructorTypeRefRule());
              	        }
                     		set(
                     			current, 
                     			"staticTypeRef",
                      		lv_staticTypeRef_3_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefInClassifierType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getConstructorTypeRefAccess().getRightCurlyBracketKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstructorTypeRef"


    // $ANTLR start "entryRuleClassifierTypeRef"
    // InternalTypesParser.g:5963:1: entryRuleClassifierTypeRef returns [EObject current=null] : iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF ;
    public final EObject entryRuleClassifierTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifierTypeRef = null;


        try {
            // InternalTypesParser.g:5964:2: (iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF )
            // InternalTypesParser.g:5965:2: iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClassifierTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleClassifierTypeRef=ruleClassifierTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClassifierTypeRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassifierTypeRef"


    // $ANTLR start "ruleClassifierTypeRef"
    // InternalTypesParser.g:5972:1: ruleClassifierTypeRef returns [EObject current=null] : ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleClassifierTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_staticTypeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5975:28: ( ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:5976:1: ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:5976:1: ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:5976:2: () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket
            {
            // InternalTypesParser.g:5976:2: ()
            // InternalTypesParser.g:5977:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getClassifierTypeRefAccess().getClassifierTypeRefAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Type,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getClassifierTypeRefAccess().getTypeKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_88); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getClassifierTypeRefAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:5992:1: ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) )
            // InternalTypesParser.g:5993:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            {
            // InternalTypesParser.g:5993:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            // InternalTypesParser.g:5994:3: lv_staticTypeRef_3_0= ruleTypeRefInClassifierType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getClassifierTypeRefAccess().getStaticTypeRefTypeRefInClassifierTypeParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_19);
            lv_staticTypeRef_3_0=ruleTypeRefInClassifierType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getClassifierTypeRefRule());
              	        }
                     		set(
                     			current, 
                     			"staticTypeRef",
                      		lv_staticTypeRef_3_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefInClassifierType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getClassifierTypeRefAccess().getRightCurlyBracketKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassifierTypeRef"


    // $ANTLR start "entryRuleTypeArgument"
    // InternalTypesParser.g:6023:1: entryRuleTypeArgument returns [EObject current=null] : iv_ruleTypeArgument= ruleTypeArgument EOF ;
    public final EObject entryRuleTypeArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeArgument = null;


        try {
            // InternalTypesParser.g:6024:2: (iv_ruleTypeArgument= ruleTypeArgument EOF )
            // InternalTypesParser.g:6025:2: iv_ruleTypeArgument= ruleTypeArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeArgument=ruleTypeArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeArgument"


    // $ANTLR start "ruleTypeArgument"
    // InternalTypesParser.g:6032:1: ruleTypeArgument returns [EObject current=null] : ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef ) ;
    public final EObject ruleTypeArgument() throws RecognitionException {
        EObject current = null;

        EObject this_Wildcard_0 = null;

        EObject this_WildcardNewNotation_1 = null;

        EObject this_TypeRef_2 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:6035:28: ( ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef ) )
            // InternalTypesParser.g:6036:1: ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef )
            {
            // InternalTypesParser.g:6036:1: ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef )
            int alt115=3;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==QuestionMark) && (synpred24_InternalTypesParser())) {
                alt115=1;
            }
            else if ( (LA115_0==Out||LA115_0==In) ) {
                alt115=2;
            }
            else if ( ((LA115_0>=Intersection && LA115_0<=Constructor)||LA115_0==Undefined||LA115_0==Indexed||LA115_0==Union||(LA115_0>=Null && LA115_0<=Void)||LA115_0==Any||LA115_0==LeftCurlyBracket||LA115_0==Tilde||LA115_0==RULE_IDENTIFIER) ) {
                alt115=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }
            switch (alt115) {
                case 1 :
                    // InternalTypesParser.g:6036:2: ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard )
                    {
                    // InternalTypesParser.g:6036:2: ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard )
                    // InternalTypesParser.g:6036:3: ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeArgumentAccess().getWildcardParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_Wildcard_0=ruleWildcard();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_Wildcard_0;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6050:5: this_WildcardNewNotation_1= ruleWildcardNewNotation
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeArgumentAccess().getWildcardNewNotationParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_WildcardNewNotation_1=ruleWildcardNewNotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_WildcardNewNotation_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:6060:5: this_TypeRef_2= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeArgumentAccess().getTypeRefParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TypeRef_2=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeRef_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeArgument"


    // $ANTLR start "entryRuleWildcard"
    // InternalTypesParser.g:6076:1: entryRuleWildcard returns [EObject current=null] : iv_ruleWildcard= ruleWildcard EOF ;
    public final EObject entryRuleWildcard() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcard = null;


        try {
            // InternalTypesParser.g:6077:2: (iv_ruleWildcard= ruleWildcard EOF )
            // InternalTypesParser.g:6078:2: iv_ruleWildcard= ruleWildcard EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWildcardRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWildcard=ruleWildcard();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWildcard; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWildcard"


    // $ANTLR start "ruleWildcard"
    // InternalTypesParser.g:6085:1: ruleWildcard returns [EObject current=null] : ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? ) ;
    public final EObject ruleWildcard() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_declaredUpperBound_3_0 = null;

        EObject lv_declaredLowerBound_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:6088:28: ( ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? ) )
            // InternalTypesParser.g:6089:1: ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? )
            {
            // InternalTypesParser.g:6089:1: ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? )
            // InternalTypesParser.g:6089:2: ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )?
            {
            // InternalTypesParser.g:6089:2: ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) )
            // InternalTypesParser.g:6089:3: ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark )
            {
            // InternalTypesParser.g:6092:5: ( () otherlv_1= QuestionMark )
            // InternalTypesParser.g:6092:6: () otherlv_1= QuestionMark
            {
            // InternalTypesParser.g:6092:6: ()
            // InternalTypesParser.g:6093:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getWildcardAccess().getWildcardAction_0_0_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,QuestionMark,FOLLOW_89); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getWildcardAccess().getQuestionMarkKeyword_0_0_1());
                  
            }

            }


            }

            // InternalTypesParser.g:6103:3: ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )?
            int alt116=3;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==Extends) ) {
                alt116=1;
            }
            else if ( (LA116_0==Super) ) {
                alt116=2;
            }
            switch (alt116) {
                case 1 :
                    // InternalTypesParser.g:6103:4: (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:6103:4: (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:6104:2: otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) )
                    {
                    otherlv_2=(Token)match(input,Extends,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardAccess().getExtendsKeyword_1_0_0());
                          
                    }
                    // InternalTypesParser.g:6108:1: ( (lv_declaredUpperBound_3_0= ruleTypeRef ) )
                    // InternalTypesParser.g:6109:1: (lv_declaredUpperBound_3_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:6109:1: (lv_declaredUpperBound_3_0= ruleTypeRef )
                    // InternalTypesParser.g:6110:3: lv_declaredUpperBound_3_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWildcardAccess().getDeclaredUpperBoundTypeRefParserRuleCall_1_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_declaredUpperBound_3_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWildcardRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredUpperBound",
                              		lv_declaredUpperBound_3_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6127:6: (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:6127:6: (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:6128:2: otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) )
                    {
                    otherlv_4=(Token)match(input,Super,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getWildcardAccess().getSuperKeyword_1_1_0());
                          
                    }
                    // InternalTypesParser.g:6132:1: ( (lv_declaredLowerBound_5_0= ruleTypeRef ) )
                    // InternalTypesParser.g:6133:1: (lv_declaredLowerBound_5_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:6133:1: (lv_declaredLowerBound_5_0= ruleTypeRef )
                    // InternalTypesParser.g:6134:3: lv_declaredLowerBound_5_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWildcardAccess().getDeclaredLowerBoundTypeRefParserRuleCall_1_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_declaredLowerBound_5_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWildcardRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredLowerBound",
                              		lv_declaredLowerBound_5_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWildcard"


    // $ANTLR start "entryRuleWildcardNewNotation"
    // InternalTypesParser.g:6158:1: entryRuleWildcardNewNotation returns [EObject current=null] : iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF ;
    public final EObject entryRuleWildcardNewNotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardNewNotation = null;


        try {
            // InternalTypesParser.g:6159:2: (iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF )
            // InternalTypesParser.g:6160:2: iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWildcardNewNotationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWildcardNewNotation=ruleWildcardNewNotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWildcardNewNotation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWildcardNewNotation"


    // $ANTLR start "ruleWildcardNewNotation"
    // InternalTypesParser.g:6167:1: ruleWildcardNewNotation returns [EObject current=null] : ( ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) ) ;
    public final EObject ruleWildcardNewNotation() throws RecognitionException {
        EObject current = null;

        Token lv_usingInOutNotation_0_0=null;
        Token lv_usingInOutNotation_2_0=null;
        EObject lv_declaredUpperBound_1_0 = null;

        EObject lv_declaredLowerBound_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:6170:28: ( ( ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) ) )
            // InternalTypesParser.g:6171:1: ( ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) )
            {
            // InternalTypesParser.g:6171:1: ( ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) )
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==Out) ) {
                alt117=1;
            }
            else if ( (LA117_0==In) ) {
                alt117=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }
            switch (alt117) {
                case 1 :
                    // InternalTypesParser.g:6171:2: ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:6171:2: ( ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:6171:3: ( (lv_usingInOutNotation_0_0= Out ) ) ( (lv_declaredUpperBound_1_0= ruleTypeRef ) )
                    {
                    // InternalTypesParser.g:6171:3: ( (lv_usingInOutNotation_0_0= Out ) )
                    // InternalTypesParser.g:6172:1: (lv_usingInOutNotation_0_0= Out )
                    {
                    // InternalTypesParser.g:6172:1: (lv_usingInOutNotation_0_0= Out )
                    // InternalTypesParser.g:6173:3: lv_usingInOutNotation_0_0= Out
                    {
                    lv_usingInOutNotation_0_0=(Token)match(input,Out,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_usingInOutNotation_0_0, grammarAccess.getWildcardNewNotationAccess().getUsingInOutNotationOutKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getWildcardNewNotationRule());
                      	        }
                             		setWithLastConsumed(current, "usingInOutNotation", true, "out");
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:6187:2: ( (lv_declaredUpperBound_1_0= ruleTypeRef ) )
                    // InternalTypesParser.g:6188:1: (lv_declaredUpperBound_1_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:6188:1: (lv_declaredUpperBound_1_0= ruleTypeRef )
                    // InternalTypesParser.g:6189:3: lv_declaredUpperBound_1_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWildcardNewNotationAccess().getDeclaredUpperBoundTypeRefParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_declaredUpperBound_1_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWildcardNewNotationRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredUpperBound",
                              		lv_declaredUpperBound_1_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6206:6: ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:6206:6: ( ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:6206:7: ( (lv_usingInOutNotation_2_0= In ) ) ( (lv_declaredLowerBound_3_0= ruleTypeRef ) )
                    {
                    // InternalTypesParser.g:6206:7: ( (lv_usingInOutNotation_2_0= In ) )
                    // InternalTypesParser.g:6207:1: (lv_usingInOutNotation_2_0= In )
                    {
                    // InternalTypesParser.g:6207:1: (lv_usingInOutNotation_2_0= In )
                    // InternalTypesParser.g:6208:3: lv_usingInOutNotation_2_0= In
                    {
                    lv_usingInOutNotation_2_0=(Token)match(input,In,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_usingInOutNotation_2_0, grammarAccess.getWildcardNewNotationAccess().getUsingInOutNotationInKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getWildcardNewNotationRule());
                      	        }
                             		setWithLastConsumed(current, "usingInOutNotation", true, "in");
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:6222:2: ( (lv_declaredLowerBound_3_0= ruleTypeRef ) )
                    // InternalTypesParser.g:6223:1: (lv_declaredLowerBound_3_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:6223:1: (lv_declaredLowerBound_3_0= ruleTypeRef )
                    // InternalTypesParser.g:6224:3: lv_declaredLowerBound_3_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getWildcardNewNotationAccess().getDeclaredLowerBoundTypeRefParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_declaredLowerBound_3_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWildcardNewNotationRule());
                      	        }
                             		set(
                             			current, 
                             			"declaredLowerBound",
                              		lv_declaredLowerBound_3_0, 
                              		"eu.numberfour.n4js.ts.Types.TypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWildcardNewNotation"


    // $ANTLR start "entryRuleTypeVariable"
    // InternalTypesParser.g:6248:1: entryRuleTypeVariable returns [EObject current=null] : iv_ruleTypeVariable= ruleTypeVariable EOF ;
    public final EObject entryRuleTypeVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeVariable = null;


        try {
            // InternalTypesParser.g:6249:2: (iv_ruleTypeVariable= ruleTypeVariable EOF )
            // InternalTypesParser.g:6250:2: iv_ruleTypeVariable= ruleTypeVariable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeVariableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeVariable=ruleTypeVariable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeVariable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeVariable"


    // $ANTLR start "ruleTypeVariable"
    // InternalTypesParser.g:6257:1: ruleTypeVariable returns [EObject current=null] : ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? ) ;
    public final EObject ruleTypeVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_declaredUpperBounds_2_0 = null;

        EObject lv_declaredUpperBounds_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:6260:28: ( ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? ) )
            // InternalTypesParser.g:6261:1: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? )
            {
            // InternalTypesParser.g:6261:1: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? )
            // InternalTypesParser.g:6261:2: ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )?
            {
            // InternalTypesParser.g:6261:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalTypesParser.g:6262:1: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalTypesParser.g:6262:1: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalTypesParser.g:6263:3: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_90); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getTypeVariableAccess().getNameIDENTIFIERTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTypeVariableRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"eu.numberfour.n4js.ts.TypeExpressions.IDENTIFIER");
              	    
            }

            }


            }

            // InternalTypesParser.g:6279:2: (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==Extends) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalTypesParser.g:6280:2: otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )*
                    {
                    otherlv_1=(Token)match(input,Extends,FOLLOW_91); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeVariableAccess().getExtendsKeyword_1_0());
                          
                    }
                    // InternalTypesParser.g:6284:1: ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) )
                    // InternalTypesParser.g:6285:1: (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef )
                    {
                    // InternalTypesParser.g:6285:1: (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef )
                    // InternalTypesParser.g:6286:3: lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeVariableAccess().getDeclaredUpperBoundsParameterizedTypeRefParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_66);
                    lv_declaredUpperBounds_2_0=ruleParameterizedTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeVariableRule());
                      	        }
                             		add(
                             			current, 
                             			"declaredUpperBounds",
                              		lv_declaredUpperBounds_2_0, 
                              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRef");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalTypesParser.g:6302:2: (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )*
                    loop118:
                    do {
                        int alt118=2;
                        int LA118_0 = input.LA(1);

                        if ( (LA118_0==Ampersand) ) {
                            alt118=1;
                        }


                        switch (alt118) {
                    	case 1 :
                    	    // InternalTypesParser.g:6303:2: otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Ampersand,FOLLOW_91); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTypeVariableAccess().getAmpersandKeyword_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:6307:1: ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) )
                    	    // InternalTypesParser.g:6308:1: (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef )
                    	    {
                    	    // InternalTypesParser.g:6308:1: (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef )
                    	    // InternalTypesParser.g:6309:3: lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTypeVariableAccess().getDeclaredUpperBoundsParameterizedTypeRefParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_66);
                    	    lv_declaredUpperBounds_4_0=ruleParameterizedTypeRef();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTypeVariableRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"declaredUpperBounds",
                    	              		lv_declaredUpperBounds_4_0, 
                    	              		"eu.numberfour.n4js.ts.TypeExpressions.ParameterizedTypeRef");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop118;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeVariable"


    // $ANTLR start "entryRuleTIdentifier"
    // InternalTypesParser.g:6333:1: entryRuleTIdentifier returns [String current=null] : iv_ruleTIdentifier= ruleTIdentifier EOF ;
    public final String entryRuleTIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTIdentifier = null;


        try {
            // InternalTypesParser.g:6334:1: (iv_ruleTIdentifier= ruleTIdentifier EOF )
            // InternalTypesParser.g:6335:2: iv_ruleTIdentifier= ruleTIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTIdentifierRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTIdentifier=ruleTIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTIdentifier"


    // $ANTLR start "ruleTIdentifier"
    // InternalTypesParser.g:6342:1: ruleTIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static ) ;
    public final AntlrDatatypeRuleToken ruleTIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_TypesIdentifier_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:6346:6: ( (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static ) )
            // InternalTypesParser.g:6347:1: (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static )
            {
            // InternalTypesParser.g:6347:1: (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static )
            int alt120=7;
            switch ( input.LA(1) ) {
            case AssignmnentCompatible:
            case AutoboxedType:
            case Intersection:
            case Constructor:
            case VirtualBase:
            case Primitive:
            case Undefined:
            case Abstract:
            case Project:
            case Object:
            case Union:
            case From:
            case Null:
            case Type:
            case Void:
            case Any:
            case Get:
            case Set:
            case As:
            case RULE_IDENTIFIER:
                {
                alt120=1;
                }
                break;
            case Implements:
                {
                alt120=2;
                }
                break;
            case Interface:
                {
                alt120=3;
                }
                break;
            case Private:
                {
                alt120=4;
                }
                break;
            case Protected:
                {
                alt120=5;
                }
                break;
            case Public:
                {
                alt120=6;
                }
                break;
            case Static:
                {
                alt120=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 120, 0, input);

                throw nvae;
            }

            switch (alt120) {
                case 1 :
                    // InternalTypesParser.g:6348:5: this_TypesIdentifier_0= ruleTypesIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTIdentifierAccess().getTypesIdentifierParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_TypesIdentifier_0=ruleTypesIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_TypesIdentifier_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6360:2: kw= Implements
                    {
                    kw=(Token)match(input,Implements,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getImplementsKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:6367:2: kw= Interface
                    {
                    kw=(Token)match(input,Interface,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getInterfaceKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:6374:2: kw= Private
                    {
                    kw=(Token)match(input,Private,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getPrivateKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:6381:2: kw= Protected
                    {
                    kw=(Token)match(input,Protected,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getProtectedKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:6388:2: kw= Public
                    {
                    kw=(Token)match(input,Public,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getPublicKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalTypesParser.g:6395:2: kw= Static
                    {
                    kw=(Token)match(input,Static,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getStaticKeyword_6()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule();
                  
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTIdentifier"


    // $ANTLR start "ruleTypeAccessModifier"
    // InternalTypesParser.g:6408:1: ruleTypeAccessModifier returns [Enumerator current=null] : ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) ) ;
    public final Enumerator ruleTypeAccessModifier() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalTypesParser.g:6410:28: ( ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) ) )
            // InternalTypesParser.g:6411:1: ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) )
            {
            // InternalTypesParser.g:6411:1: ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) )
            int alt121=3;
            switch ( input.LA(1) ) {
            case Project:
                {
                alt121=1;
                }
                break;
            case PublicInternal:
                {
                alt121=2;
                }
                break;
            case Public:
                {
                alt121=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;
            }

            switch (alt121) {
                case 1 :
                    // InternalTypesParser.g:6411:2: (enumLiteral_0= Project )
                    {
                    // InternalTypesParser.g:6411:2: (enumLiteral_0= Project )
                    // InternalTypesParser.g:6411:7: enumLiteral_0= Project
                    {
                    enumLiteral_0=(Token)match(input,Project,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getTypeAccessModifierAccess().getProjectEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getTypeAccessModifierAccess().getProjectEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6417:6: (enumLiteral_1= PublicInternal )
                    {
                    // InternalTypesParser.g:6417:6: (enumLiteral_1= PublicInternal )
                    // InternalTypesParser.g:6417:11: enumLiteral_1= PublicInternal
                    {
                    enumLiteral_1=(Token)match(input,PublicInternal,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getTypeAccessModifierAccess().getPublicInternalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_1, grammarAccess.getTypeAccessModifierAccess().getPublicInternalEnumLiteralDeclaration_1()); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:6423:6: (enumLiteral_2= Public )
                    {
                    // InternalTypesParser.g:6423:6: (enumLiteral_2= Public )
                    // InternalTypesParser.g:6423:11: enumLiteral_2= Public
                    {
                    enumLiteral_2=(Token)match(input,Public,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getTypeAccessModifierAccess().getPublicEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_2, grammarAccess.getTypeAccessModifierAccess().getPublicEnumLiteralDeclaration_2()); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeAccessModifier"


    // $ANTLR start "ruleMemberAccessModifier"
    // InternalTypesParser.g:6433:1: ruleMemberAccessModifier returns [Enumerator current=null] : ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) ) ;
    public final Enumerator ruleMemberAccessModifier() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;

         enterRule(); 
        try {
            // InternalTypesParser.g:6435:28: ( ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) ) )
            // InternalTypesParser.g:6436:1: ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) )
            {
            // InternalTypesParser.g:6436:1: ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) )
            int alt122=6;
            switch ( input.LA(1) ) {
            case Private:
                {
                alt122=1;
                }
                break;
            case Project:
                {
                alt122=2;
                }
                break;
            case ProtectedInternal:
                {
                alt122=3;
                }
                break;
            case Protected:
                {
                alt122=4;
                }
                break;
            case PublicInternal:
                {
                alt122=5;
                }
                break;
            case Public:
                {
                alt122=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }

            switch (alt122) {
                case 1 :
                    // InternalTypesParser.g:6436:2: (enumLiteral_0= Private )
                    {
                    // InternalTypesParser.g:6436:2: (enumLiteral_0= Private )
                    // InternalTypesParser.g:6436:7: enumLiteral_0= Private
                    {
                    enumLiteral_0=(Token)match(input,Private,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getPrivateEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getMemberAccessModifierAccess().getPrivateEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:6442:6: (enumLiteral_1= Project )
                    {
                    // InternalTypesParser.g:6442:6: (enumLiteral_1= Project )
                    // InternalTypesParser.g:6442:11: enumLiteral_1= Project
                    {
                    enumLiteral_1=(Token)match(input,Project,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getProjectEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_1, grammarAccess.getMemberAccessModifierAccess().getProjectEnumLiteralDeclaration_1()); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:6448:6: (enumLiteral_2= ProtectedInternal )
                    {
                    // InternalTypesParser.g:6448:6: (enumLiteral_2= ProtectedInternal )
                    // InternalTypesParser.g:6448:11: enumLiteral_2= ProtectedInternal
                    {
                    enumLiteral_2=(Token)match(input,ProtectedInternal,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getProtectedInternalEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_2, grammarAccess.getMemberAccessModifierAccess().getProtectedInternalEnumLiteralDeclaration_2()); 
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:6454:6: (enumLiteral_3= Protected )
                    {
                    // InternalTypesParser.g:6454:6: (enumLiteral_3= Protected )
                    // InternalTypesParser.g:6454:11: enumLiteral_3= Protected
                    {
                    enumLiteral_3=(Token)match(input,Protected,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getProtectedEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_3, grammarAccess.getMemberAccessModifierAccess().getProtectedEnumLiteralDeclaration_3()); 
                          
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:6460:6: (enumLiteral_4= PublicInternal )
                    {
                    // InternalTypesParser.g:6460:6: (enumLiteral_4= PublicInternal )
                    // InternalTypesParser.g:6460:11: enumLiteral_4= PublicInternal
                    {
                    enumLiteral_4=(Token)match(input,PublicInternal,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getPublicInternalEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_4, grammarAccess.getMemberAccessModifierAccess().getPublicInternalEnumLiteralDeclaration_4()); 
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:6466:6: (enumLiteral_5= Public )
                    {
                    // InternalTypesParser.g:6466:6: (enumLiteral_5= Public )
                    // InternalTypesParser.g:6466:11: enumLiteral_5= Public
                    {
                    enumLiteral_5=(Token)match(input,Public,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMemberAccessModifierAccess().getPublicEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_5, grammarAccess.getMemberAccessModifierAccess().getPublicEnumLiteralDeclaration_5()); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMemberAccessModifier"

    // $ANTLR start synpred2_InternalTypesParser
    public final void synpred2_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:144:6: ( LeftParenthesis )
        // InternalTypesParser.g:145:1: LeftParenthesis
        {
        match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalTypesParser

    // $ANTLR start synpred3_InternalTypesParser
    public final void synpred3_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:1516:5: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )
        // InternalTypesParser.g:1516:6: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        {
        // InternalTypesParser.g:1516:6: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        // InternalTypesParser.g:1517:1: CommercialAt ( ( RULE_IDENTIFIER ) )
        {
        match(input,CommercialAt,FOLLOW_4); if (state.failed) return ;
        // InternalTypesParser.g:1518:1: ( ( RULE_IDENTIFIER ) )
        // InternalTypesParser.g:1519:1: ( RULE_IDENTIFIER )
        {
        // InternalTypesParser.g:1519:1: ( RULE_IDENTIFIER )
        // InternalTypesParser.g:1520:1: RULE_IDENTIFIER
        {
        match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalTypesParser

    // $ANTLR start synpred4_InternalTypesParser
    public final void synpred4_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:1931:7: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )
        // InternalTypesParser.g:1931:8: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        {
        // InternalTypesParser.g:1931:8: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        // InternalTypesParser.g:1932:1: CommercialAt ( ( RULE_IDENTIFIER ) )
        {
        match(input,CommercialAt,FOLLOW_4); if (state.failed) return ;
        // InternalTypesParser.g:1933:1: ( ( RULE_IDENTIFIER ) )
        // InternalTypesParser.g:1934:1: ( RULE_IDENTIFIER )
        {
        // InternalTypesParser.g:1934:1: ( RULE_IDENTIFIER )
        // InternalTypesParser.g:1935:1: RULE_IDENTIFIER
        {
        match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalTypesParser

    // $ANTLR start synpred5_InternalTypesParser
    public final void synpred5_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:2213:7: ( ( CommercialAt ( ( RULE_IDENTIFIER ) ) ) )
        // InternalTypesParser.g:2213:8: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        {
        // InternalTypesParser.g:2213:8: ( CommercialAt ( ( RULE_IDENTIFIER ) ) )
        // InternalTypesParser.g:2214:1: CommercialAt ( ( RULE_IDENTIFIER ) )
        {
        match(input,CommercialAt,FOLLOW_4); if (state.failed) return ;
        // InternalTypesParser.g:2215:1: ( ( RULE_IDENTIFIER ) )
        // InternalTypesParser.g:2216:1: ( RULE_IDENTIFIER )
        {
        // InternalTypesParser.g:2216:1: ( RULE_IDENTIFIER )
        // InternalTypesParser.g:2217:1: RULE_IDENTIFIER
        {
        match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred5_InternalTypesParser

    // $ANTLR start synpred6_InternalTypesParser
    public final void synpred6_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:2393:3: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )
        // InternalTypesParser.g:2393:4: ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) )
        {
        // InternalTypesParser.g:2393:4: ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) )
        // InternalTypesParser.g:2393:5: () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        {
        // InternalTypesParser.g:2393:5: ()
        // InternalTypesParser.g:2394:1: 
        {
        }

        // InternalTypesParser.g:2394:2: ( ( ruleMemberAccessModifier ) )
        // InternalTypesParser.g:2395:1: ( ruleMemberAccessModifier )
        {
        // InternalTypesParser.g:2395:1: ( ruleMemberAccessModifier )
        // InternalTypesParser.g:2396:1: ruleMemberAccessModifier
        {
        pushFollow(FOLLOW_55);
        ruleMemberAccessModifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalTypesParser.g:2398:2: ( ( ( Abstract ) ) | ( ( Static ) ) )?
        int alt123=3;
        int LA123_0 = input.LA(1);

        if ( (LA123_0==Abstract) ) {
            alt123=1;
        }
        else if ( (LA123_0==Static) ) {
            alt123=2;
        }
        switch (alt123) {
            case 1 :
                // InternalTypesParser.g:2398:3: ( ( Abstract ) )
                {
                // InternalTypesParser.g:2398:3: ( ( Abstract ) )
                // InternalTypesParser.g:2399:1: ( Abstract )
                {
                // InternalTypesParser.g:2399:1: ( Abstract )
                // InternalTypesParser.g:2401:1: Abstract
                {
                match(input,Abstract,FOLLOW_56); if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2406:6: ( ( Static ) )
                {
                // InternalTypesParser.g:2406:6: ( ( Static ) )
                // InternalTypesParser.g:2407:1: ( Static )
                {
                // InternalTypesParser.g:2407:1: ( Static )
                // InternalTypesParser.g:2409:1: Static
                {
                match(input,Static,FOLLOW_56); if (state.failed) return ;

                }


                }


                }
                break;

        }

        match(input,Get,FOLLOW_51); if (state.failed) return ;
        // InternalTypesParser.g:2415:1: ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        int alt124=2;
        int LA124_0 = input.LA(1);

        if ( (LA124_0==AssignmnentCompatible||(LA124_0>=AutoboxedType && LA124_0<=VirtualBase)||LA124_0==Primitive||(LA124_0>=Undefined && LA124_0<=Abstract)||(LA124_0>=Project && LA124_0<=Object)||LA124_0==Union||(LA124_0>=From && LA124_0<=Null)||(LA124_0>=Type && LA124_0<=Void)||(LA124_0>=Any && LA124_0<=Get)||LA124_0==Set||LA124_0==As||LA124_0==RULE_IDENTIFIER) ) {
            alt124=1;
        }
        else if ( (LA124_0==LeftSquareBracket) ) {
            alt124=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 124, 0, input);

            throw nvae;
        }
        switch (alt124) {
            case 1 :
                // InternalTypesParser.g:2415:2: ( ( ruleTypesIdentifier ) )
                {
                // InternalTypesParser.g:2415:2: ( ( ruleTypesIdentifier ) )
                // InternalTypesParser.g:2416:1: ( ruleTypesIdentifier )
                {
                // InternalTypesParser.g:2416:1: ( ruleTypesIdentifier )
                // InternalTypesParser.g:2417:1: ruleTypesIdentifier
                {
                pushFollow(FOLLOW_2);
                ruleTypesIdentifier();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2420:6: ( ( ruleTypesComputedPropertyName ) )
                {
                // InternalTypesParser.g:2420:6: ( ( ruleTypesComputedPropertyName ) )
                // InternalTypesParser.g:2421:1: ( ruleTypesComputedPropertyName )
                {
                // InternalTypesParser.g:2421:1: ( ruleTypesComputedPropertyName )
                // InternalTypesParser.g:2422:1: ruleTypesComputedPropertyName
                {
                pushFollow(FOLLOW_2);
                ruleTypesComputedPropertyName();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred6_InternalTypesParser

    // $ANTLR start synpred7_InternalTypesParser
    public final void synpred7_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:2434:7: ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )
        // InternalTypesParser.g:2434:8: ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) )
        {
        // InternalTypesParser.g:2434:8: ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) )
        // InternalTypesParser.g:2434:9: () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        {
        // InternalTypesParser.g:2434:9: ()
        // InternalTypesParser.g:2435:1: 
        {
        }

        // InternalTypesParser.g:2435:2: ( ( ruleMemberAccessModifier ) )
        // InternalTypesParser.g:2436:1: ( ruleMemberAccessModifier )
        {
        // InternalTypesParser.g:2436:1: ( ruleMemberAccessModifier )
        // InternalTypesParser.g:2437:1: ruleMemberAccessModifier
        {
        pushFollow(FOLLOW_58);
        ruleMemberAccessModifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalTypesParser.g:2439:2: ( ( ( Abstract ) ) | ( ( Static ) ) )?
        int alt125=3;
        int LA125_0 = input.LA(1);

        if ( (LA125_0==Abstract) ) {
            alt125=1;
        }
        else if ( (LA125_0==Static) ) {
            alt125=2;
        }
        switch (alt125) {
            case 1 :
                // InternalTypesParser.g:2439:3: ( ( Abstract ) )
                {
                // InternalTypesParser.g:2439:3: ( ( Abstract ) )
                // InternalTypesParser.g:2440:1: ( Abstract )
                {
                // InternalTypesParser.g:2440:1: ( Abstract )
                // InternalTypesParser.g:2442:1: Abstract
                {
                match(input,Abstract,FOLLOW_59); if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2447:6: ( ( Static ) )
                {
                // InternalTypesParser.g:2447:6: ( ( Static ) )
                // InternalTypesParser.g:2448:1: ( Static )
                {
                // InternalTypesParser.g:2448:1: ( Static )
                // InternalTypesParser.g:2450:1: Static
                {
                match(input,Static,FOLLOW_59); if (state.failed) return ;

                }


                }


                }
                break;

        }

        match(input,Set,FOLLOW_51); if (state.failed) return ;
        // InternalTypesParser.g:2456:1: ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        int alt126=2;
        int LA126_0 = input.LA(1);

        if ( (LA126_0==AssignmnentCompatible||(LA126_0>=AutoboxedType && LA126_0<=VirtualBase)||LA126_0==Primitive||(LA126_0>=Undefined && LA126_0<=Abstract)||(LA126_0>=Project && LA126_0<=Object)||LA126_0==Union||(LA126_0>=From && LA126_0<=Null)||(LA126_0>=Type && LA126_0<=Void)||(LA126_0>=Any && LA126_0<=Get)||LA126_0==Set||LA126_0==As||LA126_0==RULE_IDENTIFIER) ) {
            alt126=1;
        }
        else if ( (LA126_0==LeftSquareBracket) ) {
            alt126=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 126, 0, input);

            throw nvae;
        }
        switch (alt126) {
            case 1 :
                // InternalTypesParser.g:2456:2: ( ( ruleTypesIdentifier ) )
                {
                // InternalTypesParser.g:2456:2: ( ( ruleTypesIdentifier ) )
                // InternalTypesParser.g:2457:1: ( ruleTypesIdentifier )
                {
                // InternalTypesParser.g:2457:1: ( ruleTypesIdentifier )
                // InternalTypesParser.g:2458:1: ruleTypesIdentifier
                {
                pushFollow(FOLLOW_2);
                ruleTypesIdentifier();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2461:6: ( ( ruleTypesComputedPropertyName ) )
                {
                // InternalTypesParser.g:2461:6: ( ( ruleTypesComputedPropertyName ) )
                // InternalTypesParser.g:2462:1: ( ruleTypesComputedPropertyName )
                {
                // InternalTypesParser.g:2462:1: ( ruleTypesComputedPropertyName )
                // InternalTypesParser.g:2463:1: ruleTypesComputedPropertyName
                {
                pushFollow(FOLLOW_2);
                ruleTypesComputedPropertyName();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred7_InternalTypesParser

    // $ANTLR start synpred8_InternalTypesParser
    public final void synpred8_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:2475:7: ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )
        // InternalTypesParser.g:2475:8: ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis )
        {
        // InternalTypesParser.g:2475:8: ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis )
        // InternalTypesParser.g:2475:9: ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis
        {
        // InternalTypesParser.g:2475:9: ( ( ruleMemberAccessModifier ) )
        // InternalTypesParser.g:2476:1: ( ruleMemberAccessModifier )
        {
        // InternalTypesParser.g:2476:1: ( ruleMemberAccessModifier )
        // InternalTypesParser.g:2477:1: ruleMemberAccessModifier
        {
        pushFollow(FOLLOW_51);
        ruleMemberAccessModifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalTypesParser.g:2479:2: ( ( ( Static ) ) | ( ( Abstract ) ) )?
        int alt127=3;
        int LA127_0 = input.LA(1);

        if ( (LA127_0==Static) ) {
            alt127=1;
        }
        else if ( (LA127_0==Abstract) ) {
            int LA127_2 = input.LA(2);

            if ( (LA127_2==AssignmnentCompatible||(LA127_2>=AutoboxedType && LA127_2<=VirtualBase)||LA127_2==Primitive||(LA127_2>=Undefined && LA127_2<=Abstract)||(LA127_2>=Project && LA127_2<=Object)||LA127_2==Union||(LA127_2>=From && LA127_2<=Null)||(LA127_2>=Type && LA127_2<=Void)||(LA127_2>=Any && LA127_2<=Get)||LA127_2==Set||LA127_2==As||LA127_2==LessThanSign||LA127_2==LeftSquareBracket||LA127_2==RULE_IDENTIFIER) ) {
                alt127=2;
            }
        }
        switch (alt127) {
            case 1 :
                // InternalTypesParser.g:2479:3: ( ( Static ) )
                {
                // InternalTypesParser.g:2479:3: ( ( Static ) )
                // InternalTypesParser.g:2480:1: ( Static )
                {
                // InternalTypesParser.g:2480:1: ( Static )
                // InternalTypesParser.g:2482:1: Static
                {
                match(input,Static,FOLLOW_51); if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2487:6: ( ( Abstract ) )
                {
                // InternalTypesParser.g:2487:6: ( ( Abstract ) )
                // InternalTypesParser.g:2488:1: ( Abstract )
                {
                // InternalTypesParser.g:2488:1: ( Abstract )
                // InternalTypesParser.g:2490:1: Abstract
                {
                match(input,Abstract,FOLLOW_51); if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalTypesParser.g:2494:4: ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )?
        int alt129=2;
        int LA129_0 = input.LA(1);

        if ( (LA129_0==LessThanSign) ) {
            alt129=1;
        }
        switch (alt129) {
            case 1 :
                // InternalTypesParser.g:2495:1: LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign
                {
                match(input,LessThanSign,FOLLOW_4); if (state.failed) return ;
                // InternalTypesParser.g:2496:1: ( ( ruleTypeVariable ) )
                // InternalTypesParser.g:2497:1: ( ruleTypeVariable )
                {
                // InternalTypesParser.g:2497:1: ( ruleTypeVariable )
                // InternalTypesParser.g:2498:1: ruleTypeVariable
                {
                pushFollow(FOLLOW_28);
                ruleTypeVariable();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalTypesParser.g:2500:2: ( Comma ( ( ruleTypeVariable ) ) )*
                loop128:
                do {
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==Comma) ) {
                        alt128=1;
                    }


                    switch (alt128) {
                	case 1 :
                	    // InternalTypesParser.g:2501:1: Comma ( ( ruleTypeVariable ) )
                	    {
                	    match(input,Comma,FOLLOW_4); if (state.failed) return ;
                	    // InternalTypesParser.g:2502:1: ( ( ruleTypeVariable ) )
                	    // InternalTypesParser.g:2503:1: ( ruleTypeVariable )
                	    {
                	    // InternalTypesParser.g:2503:1: ( ruleTypeVariable )
                	    // InternalTypesParser.g:2504:1: ruleTypeVariable
                	    {
                	    pushFollow(FOLLOW_28);
                	    ruleTypeVariable();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop128;
                    }
                } while (true);

                match(input,GreaterThanSign,FOLLOW_51); if (state.failed) return ;

                }
                break;

        }

        // InternalTypesParser.g:2508:3: ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        int alt130=2;
        int LA130_0 = input.LA(1);

        if ( (LA130_0==AssignmnentCompatible||(LA130_0>=AutoboxedType && LA130_0<=VirtualBase)||LA130_0==Primitive||(LA130_0>=Undefined && LA130_0<=Abstract)||(LA130_0>=Project && LA130_0<=Object)||LA130_0==Union||(LA130_0>=From && LA130_0<=Null)||(LA130_0>=Type && LA130_0<=Void)||(LA130_0>=Any && LA130_0<=Get)||LA130_0==Set||LA130_0==As||LA130_0==RULE_IDENTIFIER) ) {
            alt130=1;
        }
        else if ( (LA130_0==LeftSquareBracket) ) {
            alt130=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 130, 0, input);

            throw nvae;
        }
        switch (alt130) {
            case 1 :
                // InternalTypesParser.g:2508:4: ( ( ruleTypesIdentifier ) )
                {
                // InternalTypesParser.g:2508:4: ( ( ruleTypesIdentifier ) )
                // InternalTypesParser.g:2509:1: ( ruleTypesIdentifier )
                {
                // InternalTypesParser.g:2509:1: ( ruleTypesIdentifier )
                // InternalTypesParser.g:2510:1: ruleTypesIdentifier
                {
                pushFollow(FOLLOW_52);
                ruleTypesIdentifier();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // InternalTypesParser.g:2513:6: ( ( ruleTypesComputedPropertyName ) )
                {
                // InternalTypesParser.g:2513:6: ( ( ruleTypesComputedPropertyName ) )
                // InternalTypesParser.g:2514:1: ( ruleTypesComputedPropertyName )
                {
                // InternalTypesParser.g:2514:1: ( ruleTypesComputedPropertyName )
                // InternalTypesParser.g:2515:1: ruleTypesComputedPropertyName
                {
                pushFollow(FOLLOW_52);
                ruleTypesComputedPropertyName();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred8_InternalTypesParser

    // $ANTLR start synpred12_InternalTypesParser
    public final void synpred12_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:3791:4: ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )
        // InternalTypesParser.g:3791:5: ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign )
        {
        // InternalTypesParser.g:3791:5: ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign )
        // InternalTypesParser.g:3791:6: () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign
        {
        // InternalTypesParser.g:3791:6: ()
        // InternalTypesParser.g:3792:1: 
        {
        }

        match(input,LeftParenthesis,FOLLOW_68); if (state.failed) return ;
        pushFollow(FOLLOW_57);
        ruleTAnonymousFormalParameterList(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,RightParenthesis,FOLLOW_69); if (state.failed) return ;
        match(input,EqualsSignGreaterThanSign,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred12_InternalTypesParser

    // $ANTLR start synpred13_InternalTypesParser
    public final void synpred13_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:3921:2: ( ( ruleUndefModifierToken ) )
        // InternalTypesParser.g:3922:1: ( ruleUndefModifierToken )
        {
        // InternalTypesParser.g:3922:1: ( ruleUndefModifierToken )
        // InternalTypesParser.g:3923:1: ruleUndefModifierToken
        {
        pushFollow(FOLLOW_2);
        ruleUndefModifierToken();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred13_InternalTypesParser

    // $ANTLR start synpred14_InternalTypesParser
    public final void synpred14_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4008:3: ( ( PlusSign ) )
        // InternalTypesParser.g:4009:1: ( PlusSign )
        {
        // InternalTypesParser.g:4009:1: ( PlusSign )
        // InternalTypesParser.g:4011:1: PlusSign
        {
        match(input,PlusSign,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred14_InternalTypesParser

    // $ANTLR start synpred15_InternalTypesParser
    public final void synpred15_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4675:5: ( ( ruleTIdentifier ) )
        // InternalTypesParser.g:4676:1: ( ruleTIdentifier )
        {
        // InternalTypesParser.g:4676:1: ( ruleTIdentifier )
        // InternalTypesParser.g:4677:1: ruleTIdentifier
        {
        pushFollow(FOLLOW_2);
        ruleTIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred15_InternalTypesParser

    // $ANTLR start synpred16_InternalTypesParser
    public final void synpred16_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5041:4: ( LessThanSign )
        // InternalTypesParser.g:5042:1: LessThanSign
        {
        match(input,LessThanSign,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_InternalTypesParser

    // $ANTLR start synpred17_InternalTypesParser
    public final void synpred17_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5209:4: ( LessThanSign )
        // InternalTypesParser.g:5210:1: LessThanSign
        {
        match(input,LessThanSign,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred17_InternalTypesParser

    // $ANTLR start synpred18_InternalTypesParser
    public final void synpred18_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5352:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )
        // InternalTypesParser.g:5352:4: ( () Get ( ( ruleTypesIdentifier ) ) )
        {
        // InternalTypesParser.g:5352:4: ( () Get ( ( ruleTypesIdentifier ) ) )
        // InternalTypesParser.g:5352:5: () Get ( ( ruleTypesIdentifier ) )
        {
        // InternalTypesParser.g:5352:5: ()
        // InternalTypesParser.g:5353:1: 
        {
        }

        match(input,Get,FOLLOW_11); if (state.failed) return ;
        // InternalTypesParser.g:5355:1: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:5356:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:5356:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:5357:1: ruleTypesIdentifier
        {
        pushFollow(FOLLOW_2);
        ruleTypesIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred18_InternalTypesParser

    // $ANTLR start synpred19_InternalTypesParser
    public final void synpred19_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5369:7: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )
        // InternalTypesParser.g:5369:8: ( () Set ( ( ruleTypesIdentifier ) ) )
        {
        // InternalTypesParser.g:5369:8: ( () Set ( ( ruleTypesIdentifier ) ) )
        // InternalTypesParser.g:5369:9: () Set ( ( ruleTypesIdentifier ) )
        {
        // InternalTypesParser.g:5369:9: ()
        // InternalTypesParser.g:5370:1: 
        {
        }

        match(input,Set,FOLLOW_11); if (state.failed) return ;
        // InternalTypesParser.g:5372:1: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:5373:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:5373:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:5374:1: ruleTypesIdentifier
        {
        pushFollow(FOLLOW_2);
        ruleTypesIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred19_InternalTypesParser

    // $ANTLR start synpred20_InternalTypesParser
    public final void synpred20_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5386:7: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )
        // InternalTypesParser.g:5386:8: ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis )
        {
        // InternalTypesParser.g:5386:8: ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis )
        // InternalTypesParser.g:5386:9: () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis
        {
        // InternalTypesParser.g:5386:9: ()
        // InternalTypesParser.g:5387:1: 
        {
        }

        // InternalTypesParser.g:5387:2: ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )?
        int alt140=2;
        int LA140_0 = input.LA(1);

        if ( (LA140_0==LessThanSign) ) {
            alt140=1;
        }
        switch (alt140) {
            case 1 :
                // InternalTypesParser.g:5388:1: LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign
                {
                match(input,LessThanSign,FOLLOW_4); if (state.failed) return ;
                // InternalTypesParser.g:5389:1: ( ( ruleTypeVariable ) )
                // InternalTypesParser.g:5390:1: ( ruleTypeVariable )
                {
                // InternalTypesParser.g:5390:1: ( ruleTypeVariable )
                // InternalTypesParser.g:5391:1: ruleTypeVariable
                {
                pushFollow(FOLLOW_28);
                ruleTypeVariable();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalTypesParser.g:5393:2: ( Comma ( ( ruleTypeVariable ) ) )*
                loop139:
                do {
                    int alt139=2;
                    int LA139_0 = input.LA(1);

                    if ( (LA139_0==Comma) ) {
                        alt139=1;
                    }


                    switch (alt139) {
                	case 1 :
                	    // InternalTypesParser.g:5394:1: Comma ( ( ruleTypeVariable ) )
                	    {
                	    match(input,Comma,FOLLOW_4); if (state.failed) return ;
                	    // InternalTypesParser.g:5395:1: ( ( ruleTypeVariable ) )
                	    // InternalTypesParser.g:5396:1: ( ruleTypeVariable )
                	    {
                	    // InternalTypesParser.g:5396:1: ( ruleTypeVariable )
                	    // InternalTypesParser.g:5397:1: ruleTypeVariable
                	    {
                	    pushFollow(FOLLOW_28);
                	    ruleTypeVariable();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop139;
                    }
                } while (true);

                match(input,GreaterThanSign,FOLLOW_11); if (state.failed) return ;

                }
                break;

        }

        // InternalTypesParser.g:5401:3: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:5402:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:5402:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:5403:1: ruleTypesIdentifier
        {
        pushFollow(FOLLOW_52);
        ruleTypesIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred20_InternalTypesParser

    // $ANTLR start synpred24_InternalTypesParser
    public final void synpred24_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:6036:3: ( ( () QuestionMark ) )
        // InternalTypesParser.g:6036:4: ( () QuestionMark )
        {
        // InternalTypesParser.g:6036:4: ( () QuestionMark )
        // InternalTypesParser.g:6036:5: () QuestionMark
        {
        // InternalTypesParser.g:6036:5: ()
        // InternalTypesParser.g:6037:1: 
        {
        }

        match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred24_InternalTypesParser

    // Delegated rules

    public final boolean synpred4_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalTypesParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA79 dfa79 = new DFA79(this);
    protected DFA96 dfa96 = new DFA96(this);
    protected DFA102 dfa102 = new DFA102(this);
    protected DFA104 dfa104 = new DFA104(this);
    protected DFA108 dfa108 = new DFA108(this);
    static final String dfa_1s = "\22\uffff";
    static final String dfa_2s = "\1\7\3\6\7\uffff\1\15\2\uffff\1\31\3\uffff";
    static final String dfa_3s = "\1\114\3\42\7\uffff\1\42\2\uffff\1\34\3\uffff";
    static final String dfa_4s = "\4\uffff\1\5\1\6\1\7\1\10\1\11\1\13\1\14\1\uffff\1\12\1\2\1\uffff\1\3\1\4\1\1";
    static final String dfa_5s = "\22\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\3\uffff\1\12\2\uffff\1\10\1\uffff\1\6\7\uffff\1\1\1\uffff\1\3\11\uffff\1\7\2\uffff\1\5\2\uffff\1\4\41\uffff\1\11",
            "\1\13\6\uffff\1\17\3\uffff\1\15\1\14\6\uffff\1\21\2\uffff\1\15\1\uffff\1\16\3\uffff\1\20",
            "\1\13\6\uffff\1\17\3\uffff\1\15\1\14\6\uffff\1\21\2\uffff\1\15\1\uffff\1\16\3\uffff\1\20",
            "\1\13\6\uffff\1\17\3\uffff\1\15\1\14\6\uffff\1\21\2\uffff\1\15\1\uffff\1\16\3\uffff\1\20",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\17\3\uffff\1\15\1\14\6\uffff\1\21\2\uffff\1\15\1\uffff\1\16\3\uffff\1\20",
            "",
            "",
            "\1\21\2\uffff\1\15",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "336:1: (this_TObjectPrototype_0= ruleTObjectPrototype | this_TClass_1= ruleTClass | this_TInterface_2= ruleTInterface | this_TEnum_3= ruleTEnum | this_AnyType_4= ruleAnyType | this_VoidType_5= ruleVoidType | this_UndefinedType_6= ruleUndefinedType | this_NullType_7= ruleNullType | this_PrimitiveType_8= rulePrimitiveType | this_TFunction_9= ruleTFunction | this_TypeVariable_10= ruleTypeVariable | this_VirtualBaseType_11= ruleVirtualBaseType )";
        }
    }
    static final String dfa_7s = "\u008b\uffff";
    static final String dfa_8s = "\1\5\10\4\1\uffff\1\63\2\4\20\63\1\4\1\uffff\1\63\2\4\76\uffff\24\67\1\100\1\4\1\63\24\100";
    static final String dfa_9s = "\1\32\10\114\1\uffff\1\71\2\114\20\71\1\114\1\uffff\1\71\2\114\76\uffff\24\67\1\100\1\114\1\71\24\100";
    static final String dfa_10s = "\11\uffff\1\3\24\uffff\1\4\3\uffff\24\3\25\1\25\2\53\uffff";
    static final String dfa_11s = "\1\uffff\1\5\1\11\1\14\1\20\1\25\1\35\1\22\1\26\1\uffff\1\4\1\32\1\3\1\0\1\1\1\2\1\6\1\7\1\10\1\12\1\13\1\15\1\16\1\17\1\21\1\23\1\27\1\30\1\33\2\uffff\1\34\1\24\1\31\124\uffff\1\36\24\uffff}>";
    static final String[] dfa_12s = {
            "\1\3\1\uffff\1\5\7\uffff\1\4\7\uffff\1\1\1\2\1\uffff\1\6",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\37\6\uffff\1\16\1\30\6\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\uffff\1\21\13\uffff\1\11\3\uffff\1\35\14\uffff\1\12",
            "\1\63\3\uffff\1\62\1\47\1\56\1\60\2\uffff\1\61\1\uffff\1\55\1\43\6\uffff\1\45\1\57\6\uffff\1\46\2\uffff\1\51\1\54\1\uffff\1\52\1\53\2\uffff\1\44\1\40\1\uffff\1\41\1\uffff\1\50\3\uffff\1\65\5\uffff\1\36\1\uffff\1\11\3\uffff\1\64\14\uffff\1\42",
            "",
            "\1\65\5\uffff\1\36",
            "\1\111\3\uffff\1\110\1\75\1\104\1\106\2\uffff\1\107\1\uffff\1\103\1\71\6\uffff\1\73\1\105\6\uffff\1\74\2\uffff\1\77\1\102\1\uffff\1\100\1\101\2\uffff\1\72\1\67\1\uffff\1\70\1\uffff\1\76\3\uffff\1\65\5\uffff\1\36\5\uffff\1\112\14\uffff\1\66",
            "\1\136\3\uffff\1\135\1\122\1\131\1\133\2\uffff\1\134\1\uffff\1\130\1\116\6\uffff\1\120\1\132\6\uffff\1\121\2\uffff\1\124\1\127\1\uffff\1\125\1\126\2\uffff\1\117\1\114\1\uffff\1\115\1\uffff\1\123\3\uffff\1\65\5\uffff\1\36\5\uffff\1\137\14\uffff\1\113",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\65\5\uffff\1\36",
            "\1\163\3\uffff\1\162\1\147\1\156\1\160\2\uffff\1\161\1\uffff\1\155\1\143\6\uffff\1\145\1\157\6\uffff\1\146\2\uffff\1\151\1\154\1\uffff\1\152\1\153\2\uffff\1\144\1\141\1\uffff\1\142\1\uffff\1\150\26\uffff\1\164\5\uffff\1\140",
            "",
            "\1\65\5\uffff\1\36",
            "\1\111\3\uffff\1\110\1\75\1\104\1\106\2\uffff\1\107\1\uffff\1\103\1\71\6\uffff\1\73\1\105\6\uffff\1\74\2\uffff\1\77\1\102\1\uffff\1\100\1\101\2\uffff\1\72\1\67\1\uffff\1\70\1\uffff\1\76\3\uffff\1\65\13\uffff\1\112\14\uffff\1\66",
            "\1\136\3\uffff\1\135\1\122\1\131\1\133\2\uffff\1\134\1\uffff\1\130\1\116\6\uffff\1\120\1\132\6\uffff\1\121\2\uffff\1\124\1\127\1\uffff\1\125\1\126\2\uffff\1\117\1\114\1\uffff\1\115\1\uffff\1\123\3\uffff\1\65\13\uffff\1\137\14\uffff\1\113",
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
            "",
            "",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\165",
            "\1\166",
            "\1\u008a\3\uffff\1\u0089\1\176\1\u0085\1\u0087\2\uffff\1\u0088\1\uffff\1\u0084\1\172\6\uffff\1\174\1\u0086\6\uffff\1\175\2\uffff\1\u0080\1\u0083\1\uffff\1\u0081\1\u0082\2\uffff\1\173\1\170\1\uffff\1\171\1\uffff\1\177\34\uffff\1\167",
            "\1\65\5\uffff\1\36",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166",
            "\1\166"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA55 extends DFA {

        public DFA55(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 55;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "2393:1: ( ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Get ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TGetter_0= ruleTGetter ) | ( ( ( () ( ( ruleMemberAccessModifier ) ) ( ( ( Abstract ) ) | ( ( Static ) ) )? Set ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) ) )=>this_TSetter_1= ruleTSetter ) | ( ( ( ( ( ruleMemberAccessModifier ) ) ( ( ( Static ) ) | ( ( Abstract ) ) )? ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) ) LeftParenthesis ) )=>this_TMethod_2= ruleTMethod ) | this_TField_3= ruleTField )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA55_13 = input.LA(1);

                         
                        int index55_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_13==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_13==Colon) ) {s = 30;}

                         
                        input.seek(index55_13);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA55_14 = input.LA(1);

                         
                        int index55_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_14==Colon) ) {s = 30;}

                        else if ( (LA55_14==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_14);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA55_15 = input.LA(1);

                         
                        int index55_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_15==Colon) ) {s = 30;}

                        else if ( (LA55_15==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_15);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA55_12 = input.LA(1);

                         
                        int index55_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_12==Colon) ) {s = 30;}

                        else if ( (LA55_12==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_12==RULE_IDENTIFIER) && (synpred7_InternalTypesParser())) {s = 75;}

                        else if ( (LA55_12==Get) && (synpred7_InternalTypesParser())) {s = 76;}

                        else if ( (LA55_12==Set) && (synpred7_InternalTypesParser())) {s = 77;}

                        else if ( (LA55_12==Abstract) && (synpred7_InternalTypesParser())) {s = 78;}

                        else if ( (LA55_12==Any) && (synpred7_InternalTypesParser())) {s = 79;}

                        else if ( (LA55_12==Project) && (synpred7_InternalTypesParser())) {s = 80;}

                        else if ( (LA55_12==Union) && (synpred7_InternalTypesParser())) {s = 81;}

                        else if ( (LA55_12==Intersection) && (synpred7_InternalTypesParser())) {s = 82;}

                        else if ( (LA55_12==As) && (synpred7_InternalTypesParser())) {s = 83;}

                        else if ( (LA55_12==From) && (synpred7_InternalTypesParser())) {s = 84;}

                        else if ( (LA55_12==Type) && (synpred7_InternalTypesParser())) {s = 85;}

                        else if ( (LA55_12==Void) && (synpred7_InternalTypesParser())) {s = 86;}

                        else if ( (LA55_12==Null) && (synpred7_InternalTypesParser())) {s = 87;}

                        else if ( (LA55_12==Undefined) && (synpred7_InternalTypesParser())) {s = 88;}

                        else if ( (LA55_12==Constructor) && (synpred7_InternalTypesParser())) {s = 89;}

                        else if ( (LA55_12==Object) && (synpred7_InternalTypesParser())) {s = 90;}

                        else if ( (LA55_12==VirtualBase) && (synpred7_InternalTypesParser())) {s = 91;}

                        else if ( (LA55_12==Primitive) && (synpred7_InternalTypesParser())) {s = 92;}

                        else if ( (LA55_12==AutoboxedType) && (synpred7_InternalTypesParser())) {s = 93;}

                        else if ( (LA55_12==AssignmnentCompatible) && (synpred7_InternalTypesParser())) {s = 94;}

                        else if ( (LA55_12==LeftSquareBracket) && (synpred7_InternalTypesParser())) {s = 95;}

                         
                        input.seek(index55_12);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA55_10 = input.LA(1);

                         
                        int index55_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_10==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_10==Colon) ) {s = 30;}

                         
                        input.seek(index55_10);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA55_1 = input.LA(1);

                         
                        int index55_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_1==Static) ) {s = 7;}

                        else if ( (LA55_1==Abstract) ) {s = 8;}

                        else if ( (LA55_1==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_1==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_1==Get) ) {s = 11;}

                        else if ( (LA55_1==Set) ) {s = 12;}

                        else if ( (LA55_1==Any) ) {s = 13;}

                        else if ( (LA55_1==Project) ) {s = 14;}

                        else if ( (LA55_1==Union) ) {s = 15;}

                        else if ( (LA55_1==Intersection) ) {s = 16;}

                        else if ( (LA55_1==As) ) {s = 17;}

                        else if ( (LA55_1==From) ) {s = 18;}

                        else if ( (LA55_1==Type) ) {s = 19;}

                        else if ( (LA55_1==Void) ) {s = 20;}

                        else if ( (LA55_1==Null) ) {s = 21;}

                        else if ( (LA55_1==Undefined) ) {s = 22;}

                        else if ( (LA55_1==Constructor) ) {s = 23;}

                        else if ( (LA55_1==Object) ) {s = 24;}

                        else if ( (LA55_1==VirtualBase) ) {s = 25;}

                        else if ( (LA55_1==Primitive) ) {s = 26;}

                        else if ( (LA55_1==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_1==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_1==LeftSquareBracket) ) {s = 29;}

                        else if ( ((LA55_1>=Const && LA55_1<=Final)) ) {s = 30;}

                         
                        input.seek(index55_1);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA55_16 = input.LA(1);

                         
                        int index55_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_16==Colon) ) {s = 30;}

                        else if ( (LA55_16==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_16);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA55_17 = input.LA(1);

                         
                        int index55_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_17==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_17==Colon) ) {s = 30;}

                         
                        input.seek(index55_17);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA55_18 = input.LA(1);

                         
                        int index55_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_18==Colon) ) {s = 30;}

                        else if ( (LA55_18==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_18);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA55_2 = input.LA(1);

                         
                        int index55_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_2==Static) ) {s = 7;}

                        else if ( (LA55_2==Abstract) ) {s = 8;}

                        else if ( (LA55_2==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_2==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_2==Get) ) {s = 11;}

                        else if ( (LA55_2==Set) ) {s = 12;}

                        else if ( (LA55_2==Any) ) {s = 13;}

                        else if ( (LA55_2==Project) ) {s = 14;}

                        else if ( (LA55_2==Union) ) {s = 15;}

                        else if ( (LA55_2==Intersection) ) {s = 16;}

                        else if ( (LA55_2==As) ) {s = 17;}

                        else if ( (LA55_2==From) ) {s = 18;}

                        else if ( (LA55_2==Type) ) {s = 19;}

                        else if ( (LA55_2==Void) ) {s = 20;}

                        else if ( (LA55_2==Null) ) {s = 21;}

                        else if ( (LA55_2==Undefined) ) {s = 22;}

                        else if ( (LA55_2==Constructor) ) {s = 23;}

                        else if ( (LA55_2==Object) ) {s = 24;}

                        else if ( (LA55_2==VirtualBase) ) {s = 25;}

                        else if ( (LA55_2==Primitive) ) {s = 26;}

                        else if ( (LA55_2==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_2==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_2==LeftSquareBracket) ) {s = 29;}

                        else if ( ((LA55_2>=Const && LA55_2<=Final)) ) {s = 30;}

                         
                        input.seek(index55_2);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA55_19 = input.LA(1);

                         
                        int index55_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_19==Colon) ) {s = 30;}

                        else if ( (LA55_19==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_19);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA55_20 = input.LA(1);

                         
                        int index55_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_20==Colon) ) {s = 30;}

                        else if ( (LA55_20==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_20);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA55_3 = input.LA(1);

                         
                        int index55_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_3==Abstract) ) {s = 8;}

                        else if ( (LA55_3==Static) ) {s = 7;}

                        else if ( (LA55_3==Get) ) {s = 11;}

                        else if ( (LA55_3==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_3==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_3==Set) ) {s = 12;}

                        else if ( (LA55_3==Any) ) {s = 13;}

                        else if ( (LA55_3==Project) ) {s = 14;}

                        else if ( (LA55_3==Union) ) {s = 15;}

                        else if ( (LA55_3==Intersection) ) {s = 16;}

                        else if ( (LA55_3==As) ) {s = 17;}

                        else if ( (LA55_3==From) ) {s = 18;}

                        else if ( (LA55_3==Type) ) {s = 19;}

                        else if ( (LA55_3==Void) ) {s = 20;}

                        else if ( (LA55_3==Null) ) {s = 21;}

                        else if ( (LA55_3==Undefined) ) {s = 22;}

                        else if ( (LA55_3==Constructor) ) {s = 23;}

                        else if ( (LA55_3==Object) ) {s = 24;}

                        else if ( (LA55_3==VirtualBase) ) {s = 25;}

                        else if ( (LA55_3==Primitive) ) {s = 26;}

                        else if ( (LA55_3==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_3==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_3==LeftSquareBracket) ) {s = 29;}

                        else if ( ((LA55_3>=Const && LA55_3<=Final)) ) {s = 30;}

                         
                        input.seek(index55_3);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA55_21 = input.LA(1);

                         
                        int index55_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_21==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_21==Colon) ) {s = 30;}

                         
                        input.seek(index55_21);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA55_22 = input.LA(1);

                         
                        int index55_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_22==Colon) ) {s = 30;}

                        else if ( (LA55_22==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_22);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA55_23 = input.LA(1);

                         
                        int index55_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_23==Colon) ) {s = 30;}

                        else if ( (LA55_23==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_23);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA55_4 = input.LA(1);

                         
                        int index55_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_4==Abstract) ) {s = 8;}

                        else if ( (LA55_4==Static) ) {s = 7;}

                        else if ( (LA55_4==Set) ) {s = 12;}

                        else if ( ((LA55_4>=Const && LA55_4<=Final)) ) {s = 30;}

                        else if ( (LA55_4==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_4==Get) ) {s = 11;}

                        else if ( (LA55_4==Any) ) {s = 13;}

                        else if ( (LA55_4==Project) ) {s = 14;}

                        else if ( (LA55_4==Union) ) {s = 15;}

                        else if ( (LA55_4==Intersection) ) {s = 16;}

                        else if ( (LA55_4==As) ) {s = 17;}

                        else if ( (LA55_4==From) ) {s = 18;}

                        else if ( (LA55_4==Type) ) {s = 19;}

                        else if ( (LA55_4==Void) ) {s = 20;}

                        else if ( (LA55_4==Null) ) {s = 21;}

                        else if ( (LA55_4==Undefined) ) {s = 22;}

                        else if ( (LA55_4==Constructor) ) {s = 23;}

                        else if ( (LA55_4==Object) ) {s = 24;}

                        else if ( (LA55_4==VirtualBase) ) {s = 25;}

                        else if ( (LA55_4==Primitive) ) {s = 26;}

                        else if ( (LA55_4==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_4==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_4==LeftSquareBracket) ) {s = 29;}

                        else if ( (LA55_4==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                         
                        input.seek(index55_4);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA55_24 = input.LA(1);

                         
                        int index55_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_24==Colon) ) {s = 30;}

                        else if ( (LA55_24==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_24);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA55_7 = input.LA(1);

                         
                        int index55_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_7==Set) ) {s = 12;}

                        else if ( (LA55_7==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_7==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_7==Get) ) {s = 11;}

                        else if ( (LA55_7==Abstract) ) {s = 31;}

                        else if ( (LA55_7==Any) ) {s = 13;}

                        else if ( (LA55_7==Project) ) {s = 14;}

                        else if ( (LA55_7==Union) ) {s = 15;}

                        else if ( (LA55_7==Intersection) ) {s = 16;}

                        else if ( (LA55_7==As) ) {s = 17;}

                        else if ( (LA55_7==From) ) {s = 18;}

                        else if ( (LA55_7==Type) ) {s = 19;}

                        else if ( (LA55_7==Void) ) {s = 20;}

                        else if ( (LA55_7==Null) ) {s = 21;}

                        else if ( (LA55_7==Undefined) ) {s = 22;}

                        else if ( (LA55_7==Constructor) ) {s = 23;}

                        else if ( (LA55_7==Object) ) {s = 24;}

                        else if ( (LA55_7==VirtualBase) ) {s = 25;}

                        else if ( (LA55_7==Primitive) ) {s = 26;}

                        else if ( (LA55_7==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_7==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_7==LeftSquareBracket) ) {s = 29;}

                         
                        input.seek(index55_7);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA55_25 = input.LA(1);

                         
                        int index55_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_25==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_25==Colon) ) {s = 30;}

                         
                        input.seek(index55_25);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA55_32 = input.LA(1);

                         
                        int index55_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_32==RULE_IDENTIFIER) && (synpred6_InternalTypesParser())) {s = 54;}

                        else if ( (LA55_32==Get) && (synpred6_InternalTypesParser())) {s = 55;}

                        else if ( (LA55_32==Set) && (synpred6_InternalTypesParser())) {s = 56;}

                        else if ( (LA55_32==Abstract) && (synpred6_InternalTypesParser())) {s = 57;}

                        else if ( (LA55_32==Any) && (synpred6_InternalTypesParser())) {s = 58;}

                        else if ( (LA55_32==Project) && (synpred6_InternalTypesParser())) {s = 59;}

                        else if ( (LA55_32==Union) && (synpred6_InternalTypesParser())) {s = 60;}

                        else if ( (LA55_32==Intersection) && (synpred6_InternalTypesParser())) {s = 61;}

                        else if ( (LA55_32==As) && (synpred6_InternalTypesParser())) {s = 62;}

                        else if ( (LA55_32==From) && (synpred6_InternalTypesParser())) {s = 63;}

                        else if ( (LA55_32==Type) && (synpred6_InternalTypesParser())) {s = 64;}

                        else if ( (LA55_32==Void) && (synpred6_InternalTypesParser())) {s = 65;}

                        else if ( (LA55_32==Null) && (synpred6_InternalTypesParser())) {s = 66;}

                        else if ( (LA55_32==Undefined) && (synpred6_InternalTypesParser())) {s = 67;}

                        else if ( (LA55_32==Constructor) && (synpred6_InternalTypesParser())) {s = 68;}

                        else if ( (LA55_32==Object) && (synpred6_InternalTypesParser())) {s = 69;}

                        else if ( (LA55_32==VirtualBase) && (synpred6_InternalTypesParser())) {s = 70;}

                        else if ( (LA55_32==Primitive) && (synpred6_InternalTypesParser())) {s = 71;}

                        else if ( (LA55_32==AutoboxedType) && (synpred6_InternalTypesParser())) {s = 72;}

                        else if ( (LA55_32==AssignmnentCompatible) && (synpred6_InternalTypesParser())) {s = 73;}

                        else if ( (LA55_32==LeftSquareBracket) && (synpred6_InternalTypesParser())) {s = 74;}

                        else if ( (LA55_32==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_32);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA55_5 = input.LA(1);

                         
                        int index55_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_5==Static) ) {s = 7;}

                        else if ( (LA55_5==Abstract) ) {s = 8;}

                        else if ( (LA55_5==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_5==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_5==Get) ) {s = 11;}

                        else if ( (LA55_5==Set) ) {s = 12;}

                        else if ( (LA55_5==Any) ) {s = 13;}

                        else if ( (LA55_5==Project) ) {s = 14;}

                        else if ( (LA55_5==Union) ) {s = 15;}

                        else if ( (LA55_5==Intersection) ) {s = 16;}

                        else if ( (LA55_5==As) ) {s = 17;}

                        else if ( (LA55_5==From) ) {s = 18;}

                        else if ( (LA55_5==Type) ) {s = 19;}

                        else if ( (LA55_5==Void) ) {s = 20;}

                        else if ( (LA55_5==Null) ) {s = 21;}

                        else if ( (LA55_5==Undefined) ) {s = 22;}

                        else if ( (LA55_5==Constructor) ) {s = 23;}

                        else if ( (LA55_5==Object) ) {s = 24;}

                        else if ( (LA55_5==VirtualBase) ) {s = 25;}

                        else if ( (LA55_5==Primitive) ) {s = 26;}

                        else if ( (LA55_5==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_5==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_5==LeftSquareBracket) ) {s = 29;}

                        else if ( ((LA55_5>=Const && LA55_5<=Final)) ) {s = 30;}

                         
                        input.seek(index55_5);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA55_8 = input.LA(1);

                         
                        int index55_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_8==Get) ) {s = 32;}

                        else if ( (LA55_8==Set) ) {s = 33;}

                        else if ( (LA55_8==Colon) ) {s = 30;}

                        else if ( (LA55_8==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_8==RULE_IDENTIFIER) && (synpred8_InternalTypesParser())) {s = 34;}

                        else if ( (LA55_8==Abstract) && (synpred8_InternalTypesParser())) {s = 35;}

                        else if ( (LA55_8==Any) && (synpred8_InternalTypesParser())) {s = 36;}

                        else if ( (LA55_8==Project) && (synpred8_InternalTypesParser())) {s = 37;}

                        else if ( (LA55_8==Union) && (synpred8_InternalTypesParser())) {s = 38;}

                        else if ( (LA55_8==Intersection) && (synpred8_InternalTypesParser())) {s = 39;}

                        else if ( (LA55_8==As) && (synpred8_InternalTypesParser())) {s = 40;}

                        else if ( (LA55_8==From) && (synpred8_InternalTypesParser())) {s = 41;}

                        else if ( (LA55_8==Type) && (synpred8_InternalTypesParser())) {s = 42;}

                        else if ( (LA55_8==Void) && (synpred8_InternalTypesParser())) {s = 43;}

                        else if ( (LA55_8==Null) && (synpred8_InternalTypesParser())) {s = 44;}

                        else if ( (LA55_8==Undefined) && (synpred8_InternalTypesParser())) {s = 45;}

                        else if ( (LA55_8==Constructor) && (synpred8_InternalTypesParser())) {s = 46;}

                        else if ( (LA55_8==Object) && (synpred8_InternalTypesParser())) {s = 47;}

                        else if ( (LA55_8==VirtualBase) && (synpred8_InternalTypesParser())) {s = 48;}

                        else if ( (LA55_8==Primitive) && (synpred8_InternalTypesParser())) {s = 49;}

                        else if ( (LA55_8==AutoboxedType) && (synpred8_InternalTypesParser())) {s = 50;}

                        else if ( (LA55_8==AssignmnentCompatible) && (synpred8_InternalTypesParser())) {s = 51;}

                        else if ( (LA55_8==LeftSquareBracket) && (synpred8_InternalTypesParser())) {s = 52;}

                        else if ( (LA55_8==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_8);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA55_26 = input.LA(1);

                         
                        int index55_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_26==Colon) ) {s = 30;}

                        else if ( (LA55_26==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_26);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA55_27 = input.LA(1);

                         
                        int index55_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_27==Colon) ) {s = 30;}

                        else if ( (LA55_27==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_27);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA55_33 = input.LA(1);

                         
                        int index55_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_33==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_33==RULE_IDENTIFIER) && (synpred7_InternalTypesParser())) {s = 75;}

                        else if ( (LA55_33==Get) && (synpred7_InternalTypesParser())) {s = 76;}

                        else if ( (LA55_33==Set) && (synpred7_InternalTypesParser())) {s = 77;}

                        else if ( (LA55_33==Abstract) && (synpred7_InternalTypesParser())) {s = 78;}

                        else if ( (LA55_33==Any) && (synpred7_InternalTypesParser())) {s = 79;}

                        else if ( (LA55_33==Project) && (synpred7_InternalTypesParser())) {s = 80;}

                        else if ( (LA55_33==Union) && (synpred7_InternalTypesParser())) {s = 81;}

                        else if ( (LA55_33==Intersection) && (synpred7_InternalTypesParser())) {s = 82;}

                        else if ( (LA55_33==As) && (synpred7_InternalTypesParser())) {s = 83;}

                        else if ( (LA55_33==From) && (synpred7_InternalTypesParser())) {s = 84;}

                        else if ( (LA55_33==Type) && (synpred7_InternalTypesParser())) {s = 85;}

                        else if ( (LA55_33==Void) && (synpred7_InternalTypesParser())) {s = 86;}

                        else if ( (LA55_33==Null) && (synpred7_InternalTypesParser())) {s = 87;}

                        else if ( (LA55_33==Undefined) && (synpred7_InternalTypesParser())) {s = 88;}

                        else if ( (LA55_33==Constructor) && (synpred7_InternalTypesParser())) {s = 89;}

                        else if ( (LA55_33==Object) && (synpred7_InternalTypesParser())) {s = 90;}

                        else if ( (LA55_33==VirtualBase) && (synpred7_InternalTypesParser())) {s = 91;}

                        else if ( (LA55_33==Primitive) && (synpred7_InternalTypesParser())) {s = 92;}

                        else if ( (LA55_33==AutoboxedType) && (synpred7_InternalTypesParser())) {s = 93;}

                        else if ( (LA55_33==AssignmnentCompatible) && (synpred7_InternalTypesParser())) {s = 94;}

                        else if ( (LA55_33==LeftSquareBracket) && (synpred7_InternalTypesParser())) {s = 95;}

                         
                        input.seek(index55_33);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA55_11 = input.LA(1);

                         
                        int index55_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_11==Colon) ) {s = 30;}

                        else if ( (LA55_11==RULE_IDENTIFIER) && (synpred6_InternalTypesParser())) {s = 54;}

                        else if ( (LA55_11==Get) && (synpred6_InternalTypesParser())) {s = 55;}

                        else if ( (LA55_11==Set) && (synpred6_InternalTypesParser())) {s = 56;}

                        else if ( (LA55_11==Abstract) && (synpred6_InternalTypesParser())) {s = 57;}

                        else if ( (LA55_11==Any) && (synpred6_InternalTypesParser())) {s = 58;}

                        else if ( (LA55_11==Project) && (synpred6_InternalTypesParser())) {s = 59;}

                        else if ( (LA55_11==Union) && (synpred6_InternalTypesParser())) {s = 60;}

                        else if ( (LA55_11==Intersection) && (synpred6_InternalTypesParser())) {s = 61;}

                        else if ( (LA55_11==As) && (synpred6_InternalTypesParser())) {s = 62;}

                        else if ( (LA55_11==From) && (synpred6_InternalTypesParser())) {s = 63;}

                        else if ( (LA55_11==Type) && (synpred6_InternalTypesParser())) {s = 64;}

                        else if ( (LA55_11==Void) && (synpred6_InternalTypesParser())) {s = 65;}

                        else if ( (LA55_11==Null) && (synpred6_InternalTypesParser())) {s = 66;}

                        else if ( (LA55_11==Undefined) && (synpred6_InternalTypesParser())) {s = 67;}

                        else if ( (LA55_11==Constructor) && (synpred6_InternalTypesParser())) {s = 68;}

                        else if ( (LA55_11==Object) && (synpred6_InternalTypesParser())) {s = 69;}

                        else if ( (LA55_11==VirtualBase) && (synpred6_InternalTypesParser())) {s = 70;}

                        else if ( (LA55_11==Primitive) && (synpred6_InternalTypesParser())) {s = 71;}

                        else if ( (LA55_11==AutoboxedType) && (synpred6_InternalTypesParser())) {s = 72;}

                        else if ( (LA55_11==AssignmnentCompatible) && (synpred6_InternalTypesParser())) {s = 73;}

                        else if ( (LA55_11==LeftSquareBracket) && (synpred6_InternalTypesParser())) {s = 74;}

                        else if ( (LA55_11==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_11);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA55_28 = input.LA(1);

                         
                        int index55_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_28==Colon) ) {s = 30;}

                        else if ( (LA55_28==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_28);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA55_31 = input.LA(1);

                         
                        int index55_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_31==Colon) ) {s = 30;}

                        else if ( (LA55_31==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_31);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA55_6 = input.LA(1);

                         
                        int index55_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_6==Abstract) ) {s = 8;}

                        else if ( (LA55_6==Static) ) {s = 7;}

                        else if ( (LA55_6==Get) ) {s = 11;}

                        else if ( (LA55_6==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_6==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_6==Set) ) {s = 12;}

                        else if ( (LA55_6==Any) ) {s = 13;}

                        else if ( (LA55_6==Project) ) {s = 14;}

                        else if ( (LA55_6==Union) ) {s = 15;}

                        else if ( (LA55_6==Intersection) ) {s = 16;}

                        else if ( (LA55_6==As) ) {s = 17;}

                        else if ( (LA55_6==From) ) {s = 18;}

                        else if ( (LA55_6==Type) ) {s = 19;}

                        else if ( (LA55_6==Void) ) {s = 20;}

                        else if ( (LA55_6==Null) ) {s = 21;}

                        else if ( (LA55_6==Undefined) ) {s = 22;}

                        else if ( (LA55_6==Constructor) ) {s = 23;}

                        else if ( (LA55_6==Object) ) {s = 24;}

                        else if ( (LA55_6==VirtualBase) ) {s = 25;}

                        else if ( (LA55_6==Primitive) ) {s = 26;}

                        else if ( (LA55_6==AutoboxedType) ) {s = 27;}

                        else if ( (LA55_6==AssignmnentCompatible) ) {s = 28;}

                        else if ( (LA55_6==LeftSquareBracket) ) {s = 29;}

                        else if ( ((LA55_6>=Const && LA55_6<=Final)) ) {s = 30;}

                         
                        input.seek(index55_6);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA55_118 = input.LA(1);

                         
                        int index55_118 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_118==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 53;}

                        else if ( (LA55_118==Colon) ) {s = 30;}

                         
                        input.seek(index55_118);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 55, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\24\uffff";
    static final String dfa_14s = "\1\11\1\0\22\uffff";
    static final String dfa_15s = "\1\114\1\0\22\uffff";
    static final String dfa_16s = "\2\uffff\1\2\1\3\16\uffff\1\1\1\4";
    static final String dfa_17s = "\1\uffff\1\0\22\uffff}>";
    static final String[] dfa_18s = {
            "\2\3\5\uffff\1\3\4\uffff\1\3\12\uffff\1\3\3\uffff\4\3\2\uffff\1\3\6\uffff\1\3\1\uffff\1\1\11\uffff\1\3\1\uffff\1\2\1\uffff\1\3\2\uffff\1\3\7\uffff\1\3",
            "\1\uffff",
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
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA79 extends DFA {

        public DFA79(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 79;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "3791:1: ( ( ( ( ( () LeftParenthesis ruleTAnonymousFormalParameterList[null] RightParenthesis EqualsSignGreaterThanSign ) )=> ( () otherlv_1= LeftParenthesis this_TAnonymousFormalParameterList_2= ruleTAnonymousFormalParameterList[$current] otherlv_3= RightParenthesis otherlv_4= EqualsSignGreaterThanSign ) ) ( (lv_returnTypeRef_5_0= rulePrimaryTypeExpression ) ) ) | this_ArrayTypeRef_6= ruleArrayTypeRef | this_TypeRefWithModifiers_7= ruleTypeRefWithModifiers | (otherlv_8= LeftParenthesis this_TypeRef_9= ruleTypeRef otherlv_10= RightParenthesis ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA79_1 = input.LA(1);

                         
                        int index79_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_InternalTypesParser()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index79_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 79, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_19s = "\35\uffff";
    static final String dfa_20s = "\1\uffff\1\33\3\uffff\1\33\6\uffff\3\33\16\uffff";
    static final String dfa_21s = "\1\4\1\23\3\uffff\1\23\1\uffff\2\71\2\uffff\1\71\3\23\1\71\15\uffff";
    static final String dfa_22s = "\1\114\1\75\3\uffff\1\75\1\uffff\2\101\2\uffff\1\101\3\75\1\101\15\uffff";
    static final String dfa_23s = "\2\uffff\3\1\1\uffff\1\1\2\uffff\2\1\5\uffff\13\1\1\2\1\1";
    static final String dfa_24s = "\1\4\1\6\3\uffff\1\11\1\uffff\1\5\1\2\2\uffff\1\3\1\7\1\0\1\1\1\10\15\uffff}>";
    static final String[] dfa_25s = {
            "\1\24\3\uffff\1\23\1\10\1\17\1\21\1\25\1\26\1\22\1\30\1\16\1\4\3\uffff\1\33\1\uffff\1\27\1\6\1\20\1\31\1\32\4\uffff\1\7\2\uffff\1\12\1\15\1\33\1\13\1\14\2\uffff\1\5\1\2\1\uffff\1\3\1\uffff\1\11\21\uffff\1\33\2\uffff\1\33\7\uffff\1\1",
            "\1\33\2\uffff\1\33\32\uffff\1\33\2\uffff\3\33\1\uffff\1\33\1\34\1\uffff\1\33\1\uffff\1\33",
            "",
            "",
            "",
            "\1\33\2\uffff\1\33\32\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "",
            "\1\34\7\uffff\1\33",
            "\1\34\7\uffff\1\33",
            "",
            "",
            "\1\34\7\uffff\1\33",
            "\1\33\2\uffff\1\33\32\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "\1\33\2\uffff\1\33\32\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "\1\33\2\uffff\1\33\32\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "\1\34\7\uffff\1\33",
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
            "",
            "",
            ""
    };

    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[][] dfa_25 = unpackEncodedStringArray(dfa_25s);

    class DFA96 extends DFA {

        public DFA96(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 96;
            this.eot = dfa_19;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "4675:3: ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA96_13 = input.LA(1);

                         
                        int index96_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_13==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_13==EOF||LA96_13==Nullable||LA96_13==Notnull||LA96_13==ExclamationMark||(LA96_13>=RightParenthesis && LA96_13<=Comma)||LA96_13==LessThanSign||LA96_13==QuestionMark) ) {s = 27;}

                         
                        input.seek(index96_13);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA96_14 = input.LA(1);

                         
                        int index96_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_14==EOF||LA96_14==Nullable||LA96_14==Notnull||LA96_14==ExclamationMark||(LA96_14>=RightParenthesis && LA96_14<=Comma)||LA96_14==LessThanSign||LA96_14==QuestionMark) ) {s = 27;}

                        else if ( (LA96_14==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index96_14);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA96_8 = input.LA(1);

                         
                        int index96_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_8==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_8==LeftCurlyBracket) ) {s = 27;}

                         
                        input.seek(index96_8);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA96_11 = input.LA(1);

                         
                        int index96_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_11==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_11==LeftCurlyBracket) ) {s = 27;}

                         
                        input.seek(index96_11);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA96_0 = input.LA(1);

                         
                        int index96_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_0==RULE_IDENTIFIER) ) {s = 1;}

                        else if ( (LA96_0==Get) && (synpred15_InternalTypesParser())) {s = 2;}

                        else if ( (LA96_0==Set) && (synpred15_InternalTypesParser())) {s = 3;}

                        else if ( (LA96_0==Abstract) && (synpred15_InternalTypesParser())) {s = 4;}

                        else if ( (LA96_0==Any) ) {s = 5;}

                        else if ( (LA96_0==Project) && (synpred15_InternalTypesParser())) {s = 6;}

                        else if ( (LA96_0==Union) ) {s = 7;}

                        else if ( (LA96_0==Intersection) ) {s = 8;}

                        else if ( (LA96_0==As) && (synpred15_InternalTypesParser())) {s = 9;}

                        else if ( (LA96_0==From) && (synpred15_InternalTypesParser())) {s = 10;}

                        else if ( (LA96_0==Type) ) {s = 11;}

                        else if ( (LA96_0==Void) ) {s = 12;}

                        else if ( (LA96_0==Null) ) {s = 13;}

                        else if ( (LA96_0==Undefined) ) {s = 14;}

                        else if ( (LA96_0==Constructor) ) {s = 15;}

                        else if ( (LA96_0==Object) && (synpred15_InternalTypesParser())) {s = 16;}

                        else if ( (LA96_0==VirtualBase) && (synpred15_InternalTypesParser())) {s = 17;}

                        else if ( (LA96_0==Primitive) && (synpred15_InternalTypesParser())) {s = 18;}

                        else if ( (LA96_0==AutoboxedType) && (synpred15_InternalTypesParser())) {s = 19;}

                        else if ( (LA96_0==AssignmnentCompatible) && (synpred15_InternalTypesParser())) {s = 20;}

                        else if ( (LA96_0==Implements) && (synpred15_InternalTypesParser())) {s = 21;}

                        else if ( (LA96_0==Interface) && (synpred15_InternalTypesParser())) {s = 22;}

                        else if ( (LA96_0==Private) && (synpred15_InternalTypesParser())) {s = 23;}

                        else if ( (LA96_0==Protected) && (synpred15_InternalTypesParser())) {s = 24;}

                        else if ( (LA96_0==Public) && (synpred15_InternalTypesParser())) {s = 25;}

                        else if ( (LA96_0==Static) && (synpred15_InternalTypesParser())) {s = 26;}

                        else if ( (LA96_0==Indexed||LA96_0==This_1||LA96_0==LeftCurlyBracket||LA96_0==Tilde) ) {s = 27;}

                         
                        input.seek(index96_0);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA96_7 = input.LA(1);

                         
                        int index96_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_7==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_7==LeftCurlyBracket) ) {s = 27;}

                         
                        input.seek(index96_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA96_1 = input.LA(1);

                         
                        int index96_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_1==EOF||LA96_1==Nullable||LA96_1==Notnull||LA96_1==ExclamationMark||(LA96_1>=RightParenthesis && LA96_1<=Comma)||LA96_1==Solidus||LA96_1==LessThanSign||LA96_1==QuestionMark) ) {s = 27;}

                        else if ( (LA96_1==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index96_1);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA96_12 = input.LA(1);

                         
                        int index96_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_12==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_12==EOF||LA96_12==Nullable||LA96_12==Notnull||LA96_12==ExclamationMark||(LA96_12>=RightParenthesis && LA96_12<=Comma)||LA96_12==LessThanSign||LA96_12==QuestionMark) ) {s = 27;}

                         
                        input.seek(index96_12);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA96_15 = input.LA(1);

                         
                        int index96_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_15==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                        else if ( (LA96_15==LeftCurlyBracket) ) {s = 27;}

                         
                        input.seek(index96_15);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA96_5 = input.LA(1);

                         
                        int index96_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA96_5==EOF||LA96_5==Nullable||LA96_5==Notnull||LA96_5==ExclamationMark||(LA96_5>=RightParenthesis && LA96_5<=Comma)||LA96_5==LessThanSign||LA96_5==QuestionMark) ) {s = 27;}

                        else if ( (LA96_5==Colon) && (synpred15_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index96_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 96, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_26s = "\63\uffff";
    static final String dfa_27s = "\1\2\62\uffff";
    static final String dfa_28s = "\1\4\1\11\11\uffff\1\23\16\uffff\1\11\1\0\10\uffff\1\23\16\uffff";
    static final String dfa_29s = "\2\114\11\uffff\1\75\16\uffff\1\114\1\0\10\uffff\1\75\16\uffff";
    static final String dfa_30s = "\2\uffff\1\2\10\1\1\uffff\16\1\2\uffff\10\1\1\uffff\16\1";
    static final String dfa_31s = "\1\uffff\1\4\11\uffff\1\2\16\uffff\1\3\1\0\10\uffff\1\1\16\uffff}>";
    static final String[] dfa_32s = {
            "\2\2\1\uffff\6\2\1\uffff\4\2\1\uffff\1\2\1\uffff\6\2\5\uffff\1\2\2\uffff\2\2\1\uffff\2\2\2\uffff\2\2\1\uffff\1\2\1\uffff\1\2\1\uffff\6\2\3\uffff\1\2\1\1\3\2\1\uffff\2\2\1\uffff\1\2\10\uffff\1\2",
            "\1\22\1\16\5\uffff\1\10\4\uffff\1\12\12\uffff\1\21\3\uffff\1\11\1\15\1\17\1\6\2\uffff\1\7\1\uffff\1\4\3\uffff\1\5\14\uffff\1\3\3\uffff\1\20\2\uffff\1\14\7\uffff\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\31\1\2\1\uffff\1\30\32\uffff\1\27\3\uffff\1\25\1\32\1\uffff\1\23\2\uffff\1\24\1\33\1\26",
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
            "",
            "",
            "",
            "",
            "\1\53\1\47\5\uffff\1\41\4\uffff\1\43\12\uffff\1\52\3\uffff\1\42\1\46\1\50\1\37\2\uffff\1\40\1\uffff\1\35\3\uffff\1\36\14\uffff\1\34\3\uffff\1\51\2\uffff\1\45\7\uffff\1\44",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\2\1\uffff\1\61\32\uffff\1\60\3\uffff\1\56\1\32\1\uffff\1\54\2\uffff\1\55\1\33\1\57",
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
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_26 = DFA.unpackEncodedString(dfa_26s);
    static final short[] dfa_27 = DFA.unpackEncodedString(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final char[] dfa_29 = DFA.unpackEncodedStringToUnsignedChars(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[] dfa_31 = DFA.unpackEncodedString(dfa_31s);
    static final short[][] dfa_32 = unpackEncodedStringArray(dfa_32s);

    class DFA102 extends DFA {

        public DFA102(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 102;
            this.eot = dfa_26;
            this.eof = dfa_27;
            this.min = dfa_28;
            this.max = dfa_29;
            this.accept = dfa_30;
            this.special = dfa_31;
            this.transition = dfa_32;
        }
        public String getDescription() {
            return "5041:2: ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA102_27 = input.LA(1);

                         
                        int index102_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalTypesParser()) ) {s = 43;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index102_27);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA102_36 = input.LA(1);

                         
                        int index102_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA102_36==Solidus) && (synpred16_InternalTypesParser())) {s = 44;}

                        else if ( (LA102_36==LessThanSign) && (synpred16_InternalTypesParser())) {s = 45;}

                        else if ( (LA102_36==PlusSign) && (synpred16_InternalTypesParser())) {s = 46;}

                        else if ( (LA102_36==QuestionMark) && (synpred16_InternalTypesParser())) {s = 47;}

                        else if ( (LA102_36==ExclamationMark) && (synpred16_InternalTypesParser())) {s = 48;}

                        else if ( (LA102_36==Notnull) && (synpred16_InternalTypesParser())) {s = 49;}

                        else if ( (LA102_36==Nullable) && (synpred16_InternalTypesParser())) {s = 50;}

                        else if ( (LA102_36==GreaterThanSign) ) {s = 27;}

                        else if ( (LA102_36==Comma) ) {s = 26;}

                        else if ( (LA102_36==Extends) ) {s = 2;}

                         
                        input.seek(index102_36);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA102_11 = input.LA(1);

                         
                        int index102_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA102_11==Solidus) && (synpred16_InternalTypesParser())) {s = 19;}

                        else if ( (LA102_11==LessThanSign) && (synpred16_InternalTypesParser())) {s = 20;}

                        else if ( (LA102_11==PlusSign) && (synpred16_InternalTypesParser())) {s = 21;}

                        else if ( (LA102_11==QuestionMark) && (synpred16_InternalTypesParser())) {s = 22;}

                        else if ( (LA102_11==ExclamationMark) && (synpred16_InternalTypesParser())) {s = 23;}

                        else if ( (LA102_11==Notnull) && (synpred16_InternalTypesParser())) {s = 24;}

                        else if ( (LA102_11==Nullable) && (synpred16_InternalTypesParser())) {s = 25;}

                        else if ( (LA102_11==Comma) ) {s = 26;}

                        else if ( (LA102_11==GreaterThanSign) ) {s = 27;}

                        else if ( (LA102_11==Extends) ) {s = 2;}

                         
                        input.seek(index102_11);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA102_26 = input.LA(1);

                         
                        int index102_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA102_26==QuestionMark) && (synpred16_InternalTypesParser())) {s = 28;}

                        else if ( (LA102_26==Out) && (synpred16_InternalTypesParser())) {s = 29;}

                        else if ( (LA102_26==In) && (synpred16_InternalTypesParser())) {s = 30;}

                        else if ( (LA102_26==Void) && (synpred16_InternalTypesParser())) {s = 31;}

                        else if ( (LA102_26==Any) && (synpred16_InternalTypesParser())) {s = 32;}

                        else if ( (LA102_26==Undefined) && (synpred16_InternalTypesParser())) {s = 33;}

                        else if ( (LA102_26==Null) && (synpred16_InternalTypesParser())) {s = 34;}

                        else if ( (LA102_26==Indexed) && (synpred16_InternalTypesParser())) {s = 35;}

                        else if ( (LA102_26==RULE_IDENTIFIER) ) {s = 36;}

                        else if ( (LA102_26==Tilde) && (synpred16_InternalTypesParser())) {s = 37;}

                        else if ( (LA102_26==This_1) && (synpred16_InternalTypesParser())) {s = 38;}

                        else if ( (LA102_26==Constructor) && (synpred16_InternalTypesParser())) {s = 39;}

                        else if ( (LA102_26==Type) && (synpred16_InternalTypesParser())) {s = 40;}

                        else if ( (LA102_26==LeftCurlyBracket) && (synpred16_InternalTypesParser())) {s = 41;}

                        else if ( (LA102_26==Union) && (synpred16_InternalTypesParser())) {s = 42;}

                        else if ( (LA102_26==Intersection) && (synpred16_InternalTypesParser())) {s = 43;}

                         
                        input.seek(index102_26);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA102_1 = input.LA(1);

                         
                        int index102_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA102_1==QuestionMark) && (synpred16_InternalTypesParser())) {s = 3;}

                        else if ( (LA102_1==Out) && (synpred16_InternalTypesParser())) {s = 4;}

                        else if ( (LA102_1==In) && (synpred16_InternalTypesParser())) {s = 5;}

                        else if ( (LA102_1==Void) && (synpred16_InternalTypesParser())) {s = 6;}

                        else if ( (LA102_1==Any) && (synpred16_InternalTypesParser())) {s = 7;}

                        else if ( (LA102_1==Undefined) && (synpred16_InternalTypesParser())) {s = 8;}

                        else if ( (LA102_1==Null) && (synpred16_InternalTypesParser())) {s = 9;}

                        else if ( (LA102_1==Indexed) && (synpred16_InternalTypesParser())) {s = 10;}

                        else if ( (LA102_1==RULE_IDENTIFIER) ) {s = 11;}

                        else if ( (LA102_1==Tilde) && (synpred16_InternalTypesParser())) {s = 12;}

                        else if ( (LA102_1==This_1) && (synpred16_InternalTypesParser())) {s = 13;}

                        else if ( (LA102_1==Constructor) && (synpred16_InternalTypesParser())) {s = 14;}

                        else if ( (LA102_1==Type) && (synpred16_InternalTypesParser())) {s = 15;}

                        else if ( (LA102_1==LeftCurlyBracket) && (synpred16_InternalTypesParser())) {s = 16;}

                        else if ( (LA102_1==Union) && (synpred16_InternalTypesParser())) {s = 17;}

                        else if ( (LA102_1==Intersection) && (synpred16_InternalTypesParser())) {s = 18;}

                         
                        input.seek(index102_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 102, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_33s = "\1\4\1\11\1\uffff\1\23\26\uffff\1\11\1\0\1\23\26\uffff";
    static final String dfa_34s = "\2\114\1\uffff\1\75\26\uffff\1\114\1\0\1\75\26\uffff";
    static final String dfa_35s = "\2\uffff\1\2\1\uffff\26\1\3\uffff\26\1";
    static final String dfa_36s = "\1\uffff\1\3\1\uffff\1\1\26\uffff\1\0\1\2\1\4\26\uffff}>";
    static final String[] dfa_37s = {
            "\2\2\1\uffff\5\2\2\uffff\4\2\1\uffff\1\2\2\uffff\5\2\5\uffff\1\2\2\uffff\2\2\1\uffff\3\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\1\uffff\6\2\3\uffff\1\2\1\1\2\2\2\uffff\1\2\2\uffff\1\2\10\uffff\1\2",
            "\1\22\1\16\5\uffff\1\11\4\uffff\1\13\12\uffff\1\21\3\uffff\1\12\1\15\1\17\1\7\2\uffff\1\10\1\uffff\1\5\3\uffff\1\6\14\uffff\1\4\3\uffff\1\20\2\uffff\1\14\7\uffff\1\3",
            "",
            "\1\31\1\2\1\uffff\1\30\32\uffff\1\27\3\uffff\1\25\1\32\1\uffff\1\23\2\uffff\1\24\1\33\1\26",
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
            "",
            "",
            "\1\53\1\47\5\uffff\1\42\4\uffff\1\44\12\uffff\1\52\3\uffff\1\43\1\46\1\50\1\40\2\uffff\1\41\1\uffff\1\36\3\uffff\1\37\14\uffff\1\35\3\uffff\1\51\2\uffff\1\45\7\uffff\1\34",
            "\1\uffff",
            "\1\62\1\2\1\uffff\1\61\32\uffff\1\60\3\uffff\1\56\1\32\1\uffff\1\54\2\uffff\1\55\1\33\1\57",
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
            "",
            ""
    };
    static final char[] dfa_33 = DFA.unpackEncodedStringToUnsignedChars(dfa_33s);
    static final char[] dfa_34 = DFA.unpackEncodedStringToUnsignedChars(dfa_34s);
    static final short[] dfa_35 = DFA.unpackEncodedString(dfa_35s);
    static final short[] dfa_36 = DFA.unpackEncodedString(dfa_36s);
    static final short[][] dfa_37 = unpackEncodedStringArray(dfa_37s);

    class DFA104 extends DFA {

        public DFA104(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 104;
            this.eot = dfa_26;
            this.eof = dfa_27;
            this.min = dfa_33;
            this.max = dfa_34;
            this.accept = dfa_35;
            this.special = dfa_36;
            this.transition = dfa_37;
        }
        public String getDescription() {
            return "5209:2: ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA104_26 = input.LA(1);

                         
                        int index104_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA104_26==RULE_IDENTIFIER) ) {s = 28;}

                        else if ( (LA104_26==QuestionMark) && (synpred17_InternalTypesParser())) {s = 29;}

                        else if ( (LA104_26==Out) && (synpred17_InternalTypesParser())) {s = 30;}

                        else if ( (LA104_26==In) && (synpred17_InternalTypesParser())) {s = 31;}

                        else if ( (LA104_26==Void) && (synpred17_InternalTypesParser())) {s = 32;}

                        else if ( (LA104_26==Any) && (synpred17_InternalTypesParser())) {s = 33;}

                        else if ( (LA104_26==Undefined) && (synpred17_InternalTypesParser())) {s = 34;}

                        else if ( (LA104_26==Null) && (synpred17_InternalTypesParser())) {s = 35;}

                        else if ( (LA104_26==Indexed) && (synpred17_InternalTypesParser())) {s = 36;}

                        else if ( (LA104_26==Tilde) && (synpred17_InternalTypesParser())) {s = 37;}

                        else if ( (LA104_26==This_1) && (synpred17_InternalTypesParser())) {s = 38;}

                        else if ( (LA104_26==Constructor) && (synpred17_InternalTypesParser())) {s = 39;}

                        else if ( (LA104_26==Type) && (synpred17_InternalTypesParser())) {s = 40;}

                        else if ( (LA104_26==LeftCurlyBracket) && (synpred17_InternalTypesParser())) {s = 41;}

                        else if ( (LA104_26==Union) && (synpred17_InternalTypesParser())) {s = 42;}

                        else if ( (LA104_26==Intersection) && (synpred17_InternalTypesParser())) {s = 43;}

                         
                        input.seek(index104_26);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA104_3 = input.LA(1);

                         
                        int index104_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA104_3==Solidus) && (synpred17_InternalTypesParser())) {s = 19;}

                        else if ( (LA104_3==LessThanSign) && (synpred17_InternalTypesParser())) {s = 20;}

                        else if ( (LA104_3==PlusSign) && (synpred17_InternalTypesParser())) {s = 21;}

                        else if ( (LA104_3==QuestionMark) && (synpred17_InternalTypesParser())) {s = 22;}

                        else if ( (LA104_3==ExclamationMark) && (synpred17_InternalTypesParser())) {s = 23;}

                        else if ( (LA104_3==Notnull) && (synpred17_InternalTypesParser())) {s = 24;}

                        else if ( (LA104_3==Nullable) && (synpred17_InternalTypesParser())) {s = 25;}

                        else if ( (LA104_3==Comma) ) {s = 26;}

                        else if ( (LA104_3==GreaterThanSign) ) {s = 27;}

                        else if ( (LA104_3==Extends) ) {s = 2;}

                         
                        input.seek(index104_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA104_27 = input.LA(1);

                         
                        int index104_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 43;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index104_27);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA104_1 = input.LA(1);

                         
                        int index104_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA104_1==RULE_IDENTIFIER) ) {s = 3;}

                        else if ( (LA104_1==QuestionMark) && (synpred17_InternalTypesParser())) {s = 4;}

                        else if ( (LA104_1==Out) && (synpred17_InternalTypesParser())) {s = 5;}

                        else if ( (LA104_1==In) && (synpred17_InternalTypesParser())) {s = 6;}

                        else if ( (LA104_1==Void) && (synpred17_InternalTypesParser())) {s = 7;}

                        else if ( (LA104_1==Any) && (synpred17_InternalTypesParser())) {s = 8;}

                        else if ( (LA104_1==Undefined) && (synpred17_InternalTypesParser())) {s = 9;}

                        else if ( (LA104_1==Null) && (synpred17_InternalTypesParser())) {s = 10;}

                        else if ( (LA104_1==Indexed) && (synpred17_InternalTypesParser())) {s = 11;}

                        else if ( (LA104_1==Tilde) && (synpred17_InternalTypesParser())) {s = 12;}

                        else if ( (LA104_1==This_1) && (synpred17_InternalTypesParser())) {s = 13;}

                        else if ( (LA104_1==Constructor) && (synpred17_InternalTypesParser())) {s = 14;}

                        else if ( (LA104_1==Type) && (synpred17_InternalTypesParser())) {s = 15;}

                        else if ( (LA104_1==LeftCurlyBracket) && (synpred17_InternalTypesParser())) {s = 16;}

                        else if ( (LA104_1==Union) && (synpred17_InternalTypesParser())) {s = 17;}

                        else if ( (LA104_1==Intersection) && (synpred17_InternalTypesParser())) {s = 18;}

                         
                        input.seek(index104_1);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA104_28 = input.LA(1);

                         
                        int index104_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA104_28==Extends) ) {s = 2;}

                        else if ( (LA104_28==GreaterThanSign) ) {s = 27;}

                        else if ( (LA104_28==Comma) ) {s = 26;}

                        else if ( (LA104_28==Solidus) && (synpred17_InternalTypesParser())) {s = 44;}

                        else if ( (LA104_28==LessThanSign) && (synpred17_InternalTypesParser())) {s = 45;}

                        else if ( (LA104_28==PlusSign) && (synpred17_InternalTypesParser())) {s = 46;}

                        else if ( (LA104_28==QuestionMark) && (synpred17_InternalTypesParser())) {s = 47;}

                        else if ( (LA104_28==ExclamationMark) && (synpred17_InternalTypesParser())) {s = 48;}

                        else if ( (LA104_28==Notnull) && (synpred17_InternalTypesParser())) {s = 49;}

                        else if ( (LA104_28==Nullable) && (synpred17_InternalTypesParser())) {s = 50;}

                         
                        input.seek(index104_28);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 104, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_38s = "\31\uffff";
    static final String dfa_39s = "\1\4\2\0\1\uffff\22\0\3\uffff";
    static final String dfa_40s = "\1\114\2\0\1\uffff\22\0\3\uffff";
    static final String dfa_41s = "\3\uffff\1\3\22\uffff\1\1\1\4\1\2";
    static final String dfa_42s = "\1\0\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\3\uffff}>";
    static final String[] dfa_43s = {
            "\1\25\3\uffff\1\24\1\11\1\20\1\22\2\uffff\1\23\1\uffff\1\17\1\5\6\uffff\1\7\1\21\6\uffff\1\10\2\uffff\1\13\1\16\1\uffff\1\14\1\15\2\uffff\1\6\1\1\1\uffff\1\2\1\uffff\1\12\13\uffff\1\3\20\uffff\1\4",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] dfa_38 = DFA.unpackEncodedString(dfa_38s);
    static final char[] dfa_39 = DFA.unpackEncodedStringToUnsignedChars(dfa_39s);
    static final char[] dfa_40 = DFA.unpackEncodedStringToUnsignedChars(dfa_40s);
    static final short[] dfa_41 = DFA.unpackEncodedString(dfa_41s);
    static final short[] dfa_42 = DFA.unpackEncodedString(dfa_42s);
    static final short[][] dfa_43 = unpackEncodedStringArray(dfa_43s);

    class DFA108 extends DFA {

        public DFA108(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 108;
            this.eot = dfa_38;
            this.eof = dfa_38;
            this.min = dfa_39;
            this.max = dfa_40;
            this.accept = dfa_41;
            this.special = dfa_42;
            this.transition = dfa_43;
        }
        public String getDescription() {
            return "5352:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA108_0 = input.LA(1);

                         
                        int index108_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_0==Get) ) {s = 1;}

                        else if ( (LA108_0==Set) ) {s = 2;}

                        else if ( (LA108_0==LessThanSign) && (synpred20_InternalTypesParser())) {s = 3;}

                        else if ( (LA108_0==RULE_IDENTIFIER) ) {s = 4;}

                        else if ( (LA108_0==Abstract) ) {s = 5;}

                        else if ( (LA108_0==Any) ) {s = 6;}

                        else if ( (LA108_0==Project) ) {s = 7;}

                        else if ( (LA108_0==Union) ) {s = 8;}

                        else if ( (LA108_0==Intersection) ) {s = 9;}

                        else if ( (LA108_0==As) ) {s = 10;}

                        else if ( (LA108_0==From) ) {s = 11;}

                        else if ( (LA108_0==Type) ) {s = 12;}

                        else if ( (LA108_0==Void) ) {s = 13;}

                        else if ( (LA108_0==Null) ) {s = 14;}

                        else if ( (LA108_0==Undefined) ) {s = 15;}

                        else if ( (LA108_0==Constructor) ) {s = 16;}

                        else if ( (LA108_0==Object) ) {s = 17;}

                        else if ( (LA108_0==VirtualBase) ) {s = 18;}

                        else if ( (LA108_0==Primitive) ) {s = 19;}

                        else if ( (LA108_0==AutoboxedType) ) {s = 20;}

                        else if ( (LA108_0==AssignmnentCompatible) ) {s = 21;}

                         
                        input.seek(index108_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA108_1 = input.LA(1);

                         
                        int index108_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred18_InternalTypesParser()) ) {s = 22;}

                        else if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA108_2 = input.LA(1);

                         
                        int index108_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred19_InternalTypesParser()) ) {s = 24;}

                        else if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA108_4 = input.LA(1);

                         
                        int index108_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA108_5 = input.LA(1);

                         
                        int index108_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA108_6 = input.LA(1);

                         
                        int index108_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA108_7 = input.LA(1);

                         
                        int index108_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA108_8 = input.LA(1);

                         
                        int index108_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA108_9 = input.LA(1);

                         
                        int index108_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA108_10 = input.LA(1);

                         
                        int index108_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA108_11 = input.LA(1);

                         
                        int index108_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA108_12 = input.LA(1);

                         
                        int index108_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA108_13 = input.LA(1);

                         
                        int index108_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA108_14 = input.LA(1);

                         
                        int index108_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA108_15 = input.LA(1);

                         
                        int index108_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA108_16 = input.LA(1);

                         
                        int index108_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA108_17 = input.LA(1);

                         
                        int index108_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA108_18 = input.LA(1);

                         
                        int index108_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA108_19 = input.LA(1);

                         
                        int index108_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA108_20 = input.LA(1);

                         
                        int index108_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA108_21 = input.LA(1);

                         
                        int index108_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index108_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 108, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000049005014882L,0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x001004F100210600L,0x0000000000001052L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0050000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000004F100210600L,0x0000000000001052L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x2002000000480002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000480002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000ACD903034F10L,0x0000000000001000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0800000000200000L,0x0000000000000002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000049000210000L,0x0000000000001000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000110L,0x0000000000000008L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000008L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000ACD903034F10L,0x0000000000001040L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000042000040L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000042000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x4800000000300000L,0x0000000000000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x1040000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x4000000000300000L,0x0000000000000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x4000000000200000L,0x0000000000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00080000058080A0L,0x0000000000000008L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00000000058080A0L,0x0000000000000008L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000050020040L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000050020000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000ACD903034F10L,0x0000000000001010L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x4800000000101000L,0x0000000000000002L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x4000000000101000L,0x0000000000000002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x4000000000001000L,0x0000000000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x4040000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x4800000000100000L,0x0000000000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x4000000000100000L,0x0000000000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0010AED90F83FF10L,0x0000000000001000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000AED90F83FF10L,0x0000000000001000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x8800ACD90B034F10L,0x0000000000001000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x8800ACD96B034F10L,0x0000000000001000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000080008020000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000200008020000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000040040L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0800AED90F83FF10L,0x0000000000001000L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000400000040L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0xA00A04F100210600L,0x0000000000001052L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0010AEF90FA3FF10L,0x0000000000001052L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x2002000000000002L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x4000000000040000L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x800004F100210600L,0x0000000000001052L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0808000000000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000AEF90FA3FF10L,0x0000000000001052L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x200114F100210600L,0x0000000000001052L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0800010000000002L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0800ACD903034F10L,0x0000000000001008L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0C40ACD903034F10L,0x0000000000001008L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000210L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x000004B000210000L,0x0000000000001000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000080100002L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000049000210000L,0x0000000000001010L});

}