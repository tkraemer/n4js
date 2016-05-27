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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AssignmnentCompatible", "ProtectedInternal", "ProvidedByRuntime", "PublicInternal", "AutoboxedType", "Intersection", "Constructor", "VirtualBase", "Implements", "Interface", "Primitive", "Protected", "Undefined", "Abstract", "Function", "Nullable", "Extends", "Indexed", "Notnull", "Private", "Project", "Object", "Public", "Static", "Class", "Const", "Final", "Super", "Union", "This", "Enum", "From", "Null", "This_1", "Type", "Void", "With", "FullStopFullStopFullStop", "Any", "Get", "Out", "Set", "As", "In", "ExclamationMark", "Ampersand", "LeftParenthesis", "RightParenthesis", "PlusSign", "Comma", "FullStop", "Solidus", "Colon", "Semicolon", "LessThanSign", "GreaterThanSign", "QuestionMark", "CommercialAt", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "Tilde", "RULE_SINGLE_STRING_CHAR", "RULE_STRING", "RULE_LINE_TERMINATOR_FRAGMENT", "RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT", "RULE_STRUCTMODSUFFIX", "RULE_IDENTIFIER_START", "RULE_IDENTIFIER_PART", "RULE_IDENTIFIER", "RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT", "RULE_INT", "RULE_ML_COMMENT_FRAGMENT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_EOL", "RULE_WHITESPACE_FRAGMENT", "RULE_WS", "RULE_HEX_DIGIT", "RULE_UNICODE_ESCAPE_FRAGMENT", "RULE_UNICODE_LETTER_FRAGMENT", "RULE_UNICODE_COMBINING_MARK_FRAGMENT", "RULE_UNICODE_DIGIT_FRAGMENT", "RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT", "RULE_ZWNJ", "RULE_ZWJ", "RULE_DOT_DOT", "RULE_DECIMAL_DIGIT_FRAGMENT", "RULE_BOM", "RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT", "RULE_SL_COMMENT_FRAGMENT", "RULE_ANY_OTHER"
    };
    public static final int Enum=34;
    public static final int LessThanSign=58;
    public static final int LeftParenthesis=50;
    public static final int VirtualBase=11;
    public static final int Private=23;
    public static final int Extends=20;
    public static final int ExclamationMark=48;
    public static final int GreaterThanSign=59;
    public static final int RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT=70;
    public static final int RULE_STRUCTMODSUFFIX=71;
    public static final int RULE_EOL=80;
    public static final int ProtectedInternal=5;
    public static final int Out=44;
    public static final int RULE_ZWNJ=89;
    public static final int Project=24;
    public static final int PlusSign=52;
    public static final int RULE_INT=76;
    public static final int Get=43;
    public static final int RULE_ML_COMMENT=78;
    public static final int Object=25;
    public static final int LeftSquareBracket=62;
    public static final int Intersection=9;
    public static final int Set=45;
    public static final int RULE_UNICODE_ESCAPE_FRAGMENT=84;
    public static final int In=47;
    public static final int Union=32;
    public static final int Comma=53;
    public static final int RULE_SL_COMMENT_FRAGMENT=95;
    public static final int As=46;
    public static final int RULE_IDENTIFIER_PART=73;
    public static final int RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT=94;
    public static final int Solidus=55;
    public static final int RightCurlyBracket=65;
    public static final int Final=30;
    public static final int FullStop=54;
    public static final int Constructor=10;
    public static final int Abstract=17;
    public static final int CommercialAt=61;
    public static final int Semicolon=57;
    public static final int RULE_LINE_TERMINATOR_FRAGMENT=69;
    public static final int Type=38;
    public static final int QuestionMark=60;
    public static final int PublicInternal=7;
    public static final int RULE_HEX_DIGIT=83;
    public static final int RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT=88;
    public static final int Interface=13;
    public static final int Null=36;
    public static final int ProvidedByRuntime=6;
    public static final int FullStopFullStopFullStop=41;
    public static final int RULE_IDENTIFIER_START=72;
    public static final int Implements=12;
    public static final int RULE_WHITESPACE_FRAGMENT=81;
    public static final int Super=31;
    public static final int This=33;
    public static final int Ampersand=49;
    public static final int Notnull=22;
    public static final int Void=39;
    public static final int RightSquareBracket=63;
    public static final int Undefined=16;
    public static final int Protected=15;
    public static final int AutoboxedType=8;
    public static final int Const=29;
    public static final int RightParenthesis=51;
    public static final int RULE_UNICODE_COMBINING_MARK_FRAGMENT=86;
    public static final int Public=26;
    public static final int This_1=37;
    public static final int RULE_DOT_DOT=91;
    public static final int Class=28;
    public static final int Static=27;
    public static final int Nullable=19;
    public static final int RULE_SINGLE_STRING_CHAR=67;
    public static final int AssignmnentCompatible=4;
    public static final int RULE_IDENTIFIER=74;
    public static final int RULE_ML_COMMENT_FRAGMENT=77;
    public static final int RULE_STRING=68;
    public static final int Any=42;
    public static final int With=40;
    public static final int RULE_SL_COMMENT=79;
    public static final int Function=18;
    public static final int RULE_ZWJ=90;
    public static final int Primitive=14;
    public static final int RULE_UNICODE_DIGIT_FRAGMENT=87;
    public static final int Colon=56;
    public static final int EOF=-1;
    public static final int Indexed=21;
    public static final int RULE_WS=82;
    public static final int RULE_BOM=93;
    public static final int LeftCurlyBracket=64;
    public static final int Tilde=66;
    public static final int From=35;
    public static final int RULE_ANY_OTHER=96;
    public static final int RULE_UNICODE_LETTER_FRAGMENT=85;
    public static final int RULE_DECIMAL_DIGIT_FRAGMENT=92;
    public static final int RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT=75;

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

            if ( (LA18_0==AssignmnentCompatible||(LA18_0>=AutoboxedType && LA18_0<=VirtualBase)||LA18_0==Primitive||(LA18_0>=Undefined && LA18_0<=Abstract)||(LA18_0>=Project && LA18_0<=Object)||LA18_0==Union||(LA18_0>=From && LA18_0<=Null)||(LA18_0>=Type && LA18_0<=Void)||(LA18_0>=Any && LA18_0<=Get)||(LA18_0>=Set && LA18_0<=As)||LA18_0==RULE_IDENTIFIER) ) {
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

            if ( (LA52_0==AssignmnentCompatible||(LA52_0>=AutoboxedType && LA52_0<=Abstract)||(LA52_0>=Private && LA52_0<=Static)||LA52_0==Union||(LA52_0>=From && LA52_0<=Null)||(LA52_0>=Type && LA52_0<=Void)||(LA52_0>=FullStopFullStopFullStop && LA52_0<=Get)||(LA52_0>=Set && LA52_0<=As)||LA52_0==RULE_IDENTIFIER) ) {
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

                if ( (LA56_2==AssignmnentCompatible||(LA56_2>=AutoboxedType && LA56_2<=VirtualBase)||LA56_2==Primitive||(LA56_2>=Undefined && LA56_2<=Abstract)||(LA56_2>=Project && LA56_2<=Object)||LA56_2==Union||(LA56_2>=From && LA56_2<=Null)||(LA56_2>=Type && LA56_2<=Void)||(LA56_2>=Any && LA56_2<=Get)||(LA56_2>=Set && LA56_2<=As)||LA56_2==LessThanSign||LA56_2==LeftSquareBracket||LA56_2==RULE_IDENTIFIER) ) {
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

            if ( (LA59_0==AssignmnentCompatible||(LA59_0>=AutoboxedType && LA59_0<=VirtualBase)||LA59_0==Primitive||(LA59_0>=Undefined && LA59_0<=Abstract)||(LA59_0>=Project && LA59_0<=Object)||LA59_0==Union||(LA59_0>=From && LA59_0<=Null)||(LA59_0>=Type && LA59_0<=Void)||(LA59_0>=Any && LA59_0<=Get)||(LA59_0>=Set && LA59_0<=As)||LA59_0==RULE_IDENTIFIER) ) {
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

            if ( (LA61_0==AssignmnentCompatible||(LA61_0>=AutoboxedType && LA61_0<=Abstract)||(LA61_0>=Private && LA61_0<=Static)||LA61_0==Union||(LA61_0>=From && LA61_0<=Null)||(LA61_0>=Type && LA61_0<=Void)||(LA61_0>=FullStopFullStopFullStop && LA61_0<=Get)||(LA61_0>=Set && LA61_0<=As)||LA61_0==RULE_IDENTIFIER) ) {
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

            if ( (LA64_0==AssignmnentCompatible||(LA64_0>=AutoboxedType && LA64_0<=VirtualBase)||LA64_0==Primitive||(LA64_0>=Undefined && LA64_0<=Abstract)||(LA64_0>=Project && LA64_0<=Object)||LA64_0==Union||(LA64_0>=From && LA64_0<=Null)||(LA64_0>=Type && LA64_0<=Void)||(LA64_0>=Any && LA64_0<=Get)||(LA64_0>=Set && LA64_0<=As)||LA64_0==RULE_IDENTIFIER) ) {
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

            if ( (LA67_0==AssignmnentCompatible||(LA67_0>=AutoboxedType && LA67_0<=VirtualBase)||LA67_0==Primitive||(LA67_0>=Undefined && LA67_0<=Abstract)||(LA67_0>=Project && LA67_0<=Object)||LA67_0==Union||(LA67_0>=From && LA67_0<=Null)||(LA67_0>=Type && LA67_0<=Void)||(LA67_0>=Any && LA67_0<=Get)||(LA67_0>=Set && LA67_0<=As)||LA67_0==RULE_IDENTIFIER) ) {
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

            if ( (LA69_0==AssignmnentCompatible||(LA69_0>=AutoboxedType && LA69_0<=VirtualBase)||LA69_0==Primitive||(LA69_0>=Undefined && LA69_0<=Abstract)||(LA69_0>=Project && LA69_0<=Object)||LA69_0==Union||(LA69_0>=From && LA69_0<=Null)||(LA69_0>=Type && LA69_0<=Void)||(LA69_0>=Any && LA69_0<=Get)||(LA69_0>=Set && LA69_0<=As)||LA69_0==RULE_IDENTIFIER) ) {
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

            if ( (LA74_0==AssignmnentCompatible||(LA74_0>=AutoboxedType && LA74_0<=Abstract)||(LA74_0>=Private && LA74_0<=Static)||LA74_0==Union||(LA74_0>=From && LA74_0<=Null)||(LA74_0>=Type && LA74_0<=Void)||(LA74_0>=FullStopFullStopFullStop && LA74_0<=Get)||(LA74_0>=Set && LA74_0<=As)||LA74_0==RULE_IDENTIFIER) ) {
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


    // $ANTLR start "entryRuleTypeRefWithoutModifiers"
    // InternalTypesParser.g:3717:1: entryRuleTypeRefWithoutModifiers returns [EObject current=null] : iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF ;
    public final EObject entryRuleTypeRefWithoutModifiers() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefWithoutModifiers = null;


        try {
            // InternalTypesParser.g:3718:2: (iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF )
            // InternalTypesParser.g:3719:2: iv_ruleTypeRefWithoutModifiers= ruleTypeRefWithoutModifiers EOF
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
    // InternalTypesParser.g:3726:1: ruleTypeRefWithoutModifiers returns [EObject current=null] : ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpression_5= ruleFunctionTypeExpression | this_UnionTypeExpression_6= ruleUnionTypeExpression | this_IntersectionTypeExpression_7= ruleIntersectionTypeExpression ) ;
    public final EObject ruleTypeRefWithoutModifiers() throws RecognitionException {
        EObject current = null;

        Token lv_dynamic_2_0=null;
        EObject this_ParameterizedTypeRef_0 = null;

        EObject this_ThisTypeRef_1 = null;

        EObject this_ConstructorTypeRef_3 = null;

        EObject this_ClassifierTypeRef_4 = null;

        EObject this_FunctionTypeExpression_5 = null;

        EObject this_UnionTypeExpression_6 = null;

        EObject this_IntersectionTypeExpression_7 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3729:28: ( ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpression_5= ruleFunctionTypeExpression | this_UnionTypeExpression_6= ruleUnionTypeExpression | this_IntersectionTypeExpression_7= ruleIntersectionTypeExpression ) )
            // InternalTypesParser.g:3730:1: ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpression_5= ruleFunctionTypeExpression | this_UnionTypeExpression_6= ruleUnionTypeExpression | this_IntersectionTypeExpression_7= ruleIntersectionTypeExpression )
            {
            // InternalTypesParser.g:3730:1: ( ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? ) | this_ConstructorTypeRef_3= ruleConstructorTypeRef | this_ClassifierTypeRef_4= ruleClassifierTypeRef | this_FunctionTypeExpression_5= ruleFunctionTypeExpression | this_UnionTypeExpression_6= ruleUnionTypeExpression | this_IntersectionTypeExpression_7= ruleIntersectionTypeExpression )
            int alt79=6;
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
                alt79=1;
                }
                break;
            case Constructor:
                {
                alt79=2;
                }
                break;
            case Type:
                {
                alt79=3;
                }
                break;
            case LeftCurlyBracket:
                {
                alt79=4;
                }
                break;
            case Union:
                {
                alt79=5;
                }
                break;
            case Intersection:
                {
                alt79=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }

            switch (alt79) {
                case 1 :
                    // InternalTypesParser.g:3730:2: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? )
                    {
                    // InternalTypesParser.g:3730:2: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )? )
                    // InternalTypesParser.g:3730:3: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef ) ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )?
                    {
                    // InternalTypesParser.g:3730:3: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ThisTypeRef_1= ruleThisTypeRef )
                    int alt77=2;
                    switch ( input.LA(1) ) {
                    case Undefined:
                    case Indexed:
                    case Null:
                    case Void:
                    case Any:
                    case RULE_IDENTIFIER:
                        {
                        alt77=1;
                        }
                        break;
                    case Tilde:
                        {
                        switch ( input.LA(2) ) {
                        case Tilde:
                            {
                            int LA77_4 = input.LA(3);

                            if ( (LA77_4==Undefined||LA77_4==Indexed||LA77_4==Null||LA77_4==Void||LA77_4==Any||LA77_4==RULE_IDENTIFIER) ) {
                                alt77=1;
                            }
                            else if ( (LA77_4==This_1) ) {
                                alt77=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 77, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case RULE_STRUCTMODSUFFIX:
                            {
                            int LA77_5 = input.LA(3);

                            if ( (LA77_5==Undefined||LA77_5==Indexed||LA77_5==Null||LA77_5==Void||LA77_5==Any||LA77_5==RULE_IDENTIFIER) ) {
                                alt77=1;
                            }
                            else if ( (LA77_5==This_1) ) {
                                alt77=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 77, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case This_1:
                            {
                            alt77=2;
                            }
                            break;
                        case Undefined:
                        case Indexed:
                        case Null:
                        case Void:
                        case Any:
                        case RULE_IDENTIFIER:
                            {
                            alt77=1;
                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 77, 2, input);

                            throw nvae;
                        }

                        }
                        break;
                    case This_1:
                        {
                        alt77=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 77, 0, input);

                        throw nvae;
                    }

                    switch (alt77) {
                        case 1 :
                            // InternalTypesParser.g:3731:5: this_ParameterizedTypeRef_0= ruleParameterizedTypeRef
                            {
                            if ( state.backtracking==0 ) {
                               
                                      newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getParameterizedTypeRefParserRuleCall_0_0_0()); 
                                  
                            }
                            pushFollow(FOLLOW_66);
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
                            // InternalTypesParser.g:3741:5: this_ThisTypeRef_1= ruleThisTypeRef
                            {
                            if ( state.backtracking==0 ) {
                               
                                      newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getThisTypeRefParserRuleCall_0_0_1()); 
                                  
                            }
                            pushFollow(FOLLOW_66);
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

                    // InternalTypesParser.g:3749:2: ( ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign ) )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==PlusSign) && (synpred12_InternalTypesParser())) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // InternalTypesParser.g:3749:3: ( ( PlusSign ) )=> (lv_dynamic_2_0= PlusSign )
                            {
                            // InternalTypesParser.g:3757:1: (lv_dynamic_2_0= PlusSign )
                            // InternalTypesParser.g:3758:3: lv_dynamic_2_0= PlusSign
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
                    // InternalTypesParser.g:3774:5: this_ConstructorTypeRef_3= ruleConstructorTypeRef
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
                    // InternalTypesParser.g:3784:5: this_ClassifierTypeRef_4= ruleClassifierTypeRef
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
                    // InternalTypesParser.g:3794:5: this_FunctionTypeExpression_5= ruleFunctionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getFunctionTypeExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_FunctionTypeExpression_5=ruleFunctionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_FunctionTypeExpression_5;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:3804:5: this_UnionTypeExpression_6= ruleUnionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getUnionTypeExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnionTypeExpression_6=ruleUnionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UnionTypeExpression_6;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:3814:5: this_IntersectionTypeExpression_7= ruleIntersectionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefWithoutModifiersAccess().getIntersectionTypeExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_IntersectionTypeExpression_7=ruleIntersectionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_IntersectionTypeExpression_7;
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
    // InternalTypesParser.g:3830:1: entryRuleTypeRefFunctionTypeExpression returns [EObject current=null] : iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF ;
    public final EObject entryRuleTypeRefFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefFunctionTypeExpression = null;


        try {
            // InternalTypesParser.g:3831:2: (iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF )
            // InternalTypesParser.g:3832:2: iv_ruleTypeRefFunctionTypeExpression= ruleTypeRefFunctionTypeExpression EOF
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
    // InternalTypesParser.g:3839:1: ruleTypeRefFunctionTypeExpression returns [EObject current=null] : (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ConstructorTypeRef_1= ruleConstructorTypeRef | this_ClassifierTypeRef_2= ruleClassifierTypeRef | this_UnionTypeExpression_3= ruleUnionTypeExpression | this_IntersectionTypeExpression_4= ruleIntersectionTypeExpression ) ;
    public final EObject ruleTypeRefFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRef_0 = null;

        EObject this_ConstructorTypeRef_1 = null;

        EObject this_ClassifierTypeRef_2 = null;

        EObject this_UnionTypeExpression_3 = null;

        EObject this_IntersectionTypeExpression_4 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3842:28: ( (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ConstructorTypeRef_1= ruleConstructorTypeRef | this_ClassifierTypeRef_2= ruleClassifierTypeRef | this_UnionTypeExpression_3= ruleUnionTypeExpression | this_IntersectionTypeExpression_4= ruleIntersectionTypeExpression ) )
            // InternalTypesParser.g:3843:1: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ConstructorTypeRef_1= ruleConstructorTypeRef | this_ClassifierTypeRef_2= ruleClassifierTypeRef | this_UnionTypeExpression_3= ruleUnionTypeExpression | this_IntersectionTypeExpression_4= ruleIntersectionTypeExpression )
            {
            // InternalTypesParser.g:3843:1: (this_ParameterizedTypeRef_0= ruleParameterizedTypeRef | this_ConstructorTypeRef_1= ruleConstructorTypeRef | this_ClassifierTypeRef_2= ruleClassifierTypeRef | this_UnionTypeExpression_3= ruleUnionTypeExpression | this_IntersectionTypeExpression_4= ruleIntersectionTypeExpression )
            int alt80=5;
            switch ( input.LA(1) ) {
            case Undefined:
            case Indexed:
            case Null:
            case Void:
            case Any:
            case Tilde:
            case RULE_IDENTIFIER:
                {
                alt80=1;
                }
                break;
            case Constructor:
                {
                alt80=2;
                }
                break;
            case Type:
                {
                alt80=3;
                }
                break;
            case Union:
                {
                alt80=4;
                }
                break;
            case Intersection:
                {
                alt80=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }

            switch (alt80) {
                case 1 :
                    // InternalTypesParser.g:3844:5: this_ParameterizedTypeRef_0= ruleParameterizedTypeRef
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
                    // InternalTypesParser.g:3854:5: this_ConstructorTypeRef_1= ruleConstructorTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getConstructorTypeRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ConstructorTypeRef_1=ruleConstructorTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ConstructorTypeRef_1;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:3864:5: this_ClassifierTypeRef_2= ruleClassifierTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getClassifierTypeRefParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_ClassifierTypeRef_2=ruleClassifierTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ClassifierTypeRef_2;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:3874:5: this_UnionTypeExpression_3= ruleUnionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getUnionTypeExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_UnionTypeExpression_3=ruleUnionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UnionTypeExpression_3;
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:3884:5: this_IntersectionTypeExpression_4= ruleIntersectionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeRefFunctionTypeExpressionAccess().getIntersectionTypeExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_IntersectionTypeExpression_4=ruleIntersectionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_IntersectionTypeExpression_4;
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
    // InternalTypesParser.g:3902:1: entryRuleTypeRefInClassifierType returns [EObject current=null] : iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF ;
    public final EObject entryRuleTypeRefInClassifierType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefInClassifierType = null;


        try {
            // InternalTypesParser.g:3903:2: (iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF )
            // InternalTypesParser.g:3904:2: iv_ruleTypeRefInClassifierType= ruleTypeRefInClassifierType EOF
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
    // InternalTypesParser.g:3911:1: ruleTypeRefInClassifierType returns [EObject current=null] : (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal ) ;
    public final EObject ruleTypeRefInClassifierType() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRefNominal_0 = null;

        EObject this_ThisTypeRefNominal_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3914:28: ( (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal ) )
            // InternalTypesParser.g:3915:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal )
            {
            // InternalTypesParser.g:3915:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ThisTypeRefNominal_1= ruleThisTypeRefNominal )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==Undefined||LA81_0==Indexed||LA81_0==Null||LA81_0==Void||LA81_0==Any||LA81_0==RULE_IDENTIFIER) ) {
                alt81=1;
            }
            else if ( (LA81_0==This_1) ) {
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
                    // InternalTypesParser.g:3916:5: this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal
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
                    // InternalTypesParser.g:3926:5: this_ThisTypeRefNominal_1= ruleThisTypeRefNominal
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
    // InternalTypesParser.g:3942:1: entryRuleThisTypeRef returns [EObject current=null] : iv_ruleThisTypeRef= ruleThisTypeRef EOF ;
    public final EObject entryRuleThisTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRef = null;


        try {
            // InternalTypesParser.g:3943:2: (iv_ruleThisTypeRef= ruleThisTypeRef EOF )
            // InternalTypesParser.g:3944:2: iv_ruleThisTypeRef= ruleThisTypeRef EOF
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
    // InternalTypesParser.g:3951:1: ruleThisTypeRef returns [EObject current=null] : (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural ) ;
    public final EObject ruleThisTypeRef() throws RecognitionException {
        EObject current = null;

        EObject this_ThisTypeRefNominal_0 = null;

        EObject this_ThisTypeRefStructural_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:3954:28: ( (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural ) )
            // InternalTypesParser.g:3955:1: (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural )
            {
            // InternalTypesParser.g:3955:1: (this_ThisTypeRefNominal_0= ruleThisTypeRefNominal | this_ThisTypeRefStructural_1= ruleThisTypeRefStructural )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==This_1) ) {
                alt82=1;
            }
            else if ( (LA82_0==Tilde) ) {
                alt82=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }
            switch (alt82) {
                case 1 :
                    // InternalTypesParser.g:3956:5: this_ThisTypeRefNominal_0= ruleThisTypeRefNominal
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
                    // InternalTypesParser.g:3966:5: this_ThisTypeRefStructural_1= ruleThisTypeRefStructural
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
    // InternalTypesParser.g:3982:1: entryRuleThisTypeRefNominal returns [EObject current=null] : iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF ;
    public final EObject entryRuleThisTypeRefNominal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRefNominal = null;


        try {
            // InternalTypesParser.g:3983:2: (iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF )
            // InternalTypesParser.g:3984:2: iv_ruleThisTypeRefNominal= ruleThisTypeRefNominal EOF
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
    // InternalTypesParser.g:3991:1: ruleThisTypeRefNominal returns [EObject current=null] : ( () otherlv_1= This_1 ) ;
    public final EObject ruleThisTypeRefNominal() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:3994:28: ( ( () otherlv_1= This_1 ) )
            // InternalTypesParser.g:3995:1: ( () otherlv_1= This_1 )
            {
            // InternalTypesParser.g:3995:1: ( () otherlv_1= This_1 )
            // InternalTypesParser.g:3995:2: () otherlv_1= This_1
            {
            // InternalTypesParser.g:3995:2: ()
            // InternalTypesParser.g:3996:5: 
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
    // InternalTypesParser.g:4014:1: entryRuleThisTypeRefStructural returns [EObject current=null] : iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF ;
    public final EObject entryRuleThisTypeRefStructural() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisTypeRefStructural = null;


        try {
            // InternalTypesParser.g:4015:2: (iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF )
            // InternalTypesParser.g:4016:2: iv_ruleThisTypeRefStructural= ruleThisTypeRefStructural EOF
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
    // InternalTypesParser.g:4023:1: ruleThisTypeRefStructural returns [EObject current=null] : ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? ) ;
    public final EObject ruleThisTypeRefStructural() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_definedTypingStrategy_0_0 = null;

        EObject this_TStructMemberList_3 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4026:28: ( ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? ) )
            // InternalTypesParser.g:4027:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? )
            {
            // InternalTypesParser.g:4027:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )? )
            // InternalTypesParser.g:4027:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) otherlv_1= This_1 (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )?
            {
            // InternalTypesParser.g:4027:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) )
            // InternalTypesParser.g:4028:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            {
            // InternalTypesParser.g:4028:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            // InternalTypesParser.g:4029:3: lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getThisTypeRefStructuralAccess().getDefinedTypingStrategyTypingStrategyUseSiteOperatorParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_67);
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

            otherlv_1=(Token)match(input,This_1,FOLLOW_68); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getThisTypeRefStructuralAccess().getThisKeyword_1());
                  
            }
            // InternalTypesParser.g:4050:1: (otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current] )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==With) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalTypesParser.g:4051:2: otherlv_2= With this_TStructMemberList_3= ruleTStructMemberList[$current]
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


    // $ANTLR start "entryRuleFunctionTypeExpression"
    // InternalTypesParser.g:4075:1: entryRuleFunctionTypeExpression returns [EObject current=null] : iv_ruleFunctionTypeExpression= ruleFunctionTypeExpression EOF ;
    public final EObject entryRuleFunctionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionTypeExpression = null;


        try {
            // InternalTypesParser.g:4076:2: (iv_ruleFunctionTypeExpression= ruleFunctionTypeExpression EOF )
            // InternalTypesParser.g:4077:2: iv_ruleFunctionTypeExpression= ruleFunctionTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionTypeExpression=ruleFunctionTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionTypeExpression; 
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
    // $ANTLR end "entryRuleFunctionTypeExpression"


    // $ANTLR start "ruleFunctionTypeExpression"
    // InternalTypesParser.g:4084:1: ruleFunctionTypeExpression returns [EObject current=null] : ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket ) ;
    public final EObject ruleFunctionTypeExpression() throws RecognitionException {
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
            // InternalTypesParser.g:4087:28: ( ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket ) )
            // InternalTypesParser.g:4088:1: ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket )
            {
            // InternalTypesParser.g:4088:1: ( () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket )
            // InternalTypesParser.g:4088:2: () otherlv_1= LeftCurlyBracket (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )? otherlv_7= Function (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )? otherlv_13= LeftParenthesis this_TAnonymousFormalParameterList_14= ruleTAnonymousFormalParameterList[$current] otherlv_15= RightParenthesis (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )? otherlv_18= RightCurlyBracket
            {
            // InternalTypesParser.g:4088:2: ()
            // InternalTypesParser.g:4089:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getFunctionTypeExpressionAccess().getFunctionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,LeftCurlyBracket,FOLLOW_69); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionTypeExpressionAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // InternalTypesParser.g:4099:1: (otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==CommercialAt) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalTypesParser.g:4100:2: otherlv_2= CommercialAt otherlv_3= This otherlv_4= LeftParenthesis ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) ) otherlv_6= RightParenthesis
                    {
                    otherlv_2=(Token)match(input,CommercialAt,FOLLOW_70); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getFunctionTypeExpressionAccess().getCommercialAtKeyword_2_0());
                          
                    }
                    otherlv_3=(Token)match(input,This,FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getFunctionTypeExpressionAccess().getThisKeyword_2_1());
                          
                    }
                    otherlv_4=(Token)match(input,LeftParenthesis,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getFunctionTypeExpressionAccess().getLeftParenthesisKeyword_2_2());
                          
                    }
                    // InternalTypesParser.g:4114:1: ( (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression ) )
                    // InternalTypesParser.g:4115:1: (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression )
                    {
                    // InternalTypesParser.g:4115:1: (lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression )
                    // InternalTypesParser.g:4116:3: lv_declaredThisType_5_0= ruleTypeRefFunctionTypeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionAccess().getDeclaredThisTypeTypeRefFunctionTypeExpressionParserRuleCall_2_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_57);
                    lv_declaredThisType_5_0=ruleTypeRefFunctionTypeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionRule());
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

                          	newLeafNode(otherlv_6, grammarAccess.getFunctionTypeExpressionAccess().getRightParenthesisKeyword_2_4());
                          
                    }

                    }
                    break;

            }

            otherlv_7=(Token)match(input,Function,FOLLOW_71); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getFunctionTypeExpressionAccess().getFunctionKeyword_3());
                  
            }
            // InternalTypesParser.g:4142:1: (otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==LessThanSign) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalTypesParser.g:4143:2: otherlv_8= LessThanSign ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) ) (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )* otherlv_12= GreaterThanSign
                    {
                    otherlv_8=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getFunctionTypeExpressionAccess().getLessThanSignKeyword_4_0());
                          
                    }
                    // InternalTypesParser.g:4147:1: ( (lv_ownedTypeVars_9_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:4148:1: (lv_ownedTypeVars_9_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:4148:1: (lv_ownedTypeVars_9_0= ruleTypeVariable )
                    // InternalTypesParser.g:4149:3: lv_ownedTypeVars_9_0= ruleTypeVariable
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionAccess().getOwnedTypeVarsTypeVariableParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
                    lv_ownedTypeVars_9_0=ruleTypeVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionRule());
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

                    // InternalTypesParser.g:4165:2: (otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==Comma) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // InternalTypesParser.g:4166:2: otherlv_10= Comma ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_10=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getFunctionTypeExpressionAccess().getCommaKeyword_4_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:4170:1: ( (lv_ownedTypeVars_11_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:4171:1: (lv_ownedTypeVars_11_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:4171:1: (lv_ownedTypeVars_11_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:4172:3: lv_ownedTypeVars_11_0= ruleTypeVariable
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionAccess().getOwnedTypeVarsTypeVariableParserRuleCall_4_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_28);
                    	    lv_ownedTypeVars_11_0=ruleTypeVariable();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionRule());
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
                    	    break loop85;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,GreaterThanSign,FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getFunctionTypeExpressionAccess().getGreaterThanSignKeyword_4_3());
                          
                    }

                    }
                    break;

            }

            otherlv_13=(Token)match(input,LeftParenthesis,FOLLOW_72); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_13, grammarAccess.getFunctionTypeExpressionAccess().getLeftParenthesisKeyword_5());
                  
            }
            if ( state.backtracking==0 ) {
               
              		if (current==null) {
              			current = createModelElement(grammarAccess.getFunctionTypeExpressionRule());
              		}
                      newCompositeNode(grammarAccess.getFunctionTypeExpressionAccess().getTAnonymousFormalParameterListParserRuleCall_6()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_TAnonymousFormalParameterList_14=ruleTAnonymousFormalParameterList(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TAnonymousFormalParameterList_14;
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_15=(Token)match(input,RightParenthesis,FOLLOW_73); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getFunctionTypeExpressionAccess().getRightParenthesisKeyword_7());
                  
            }
            // InternalTypesParser.g:4215:1: (otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==Colon) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalTypesParser.g:4216:2: otherlv_16= Colon ( (lv_returnTypeRef_17_0= ruleTypeRef ) )
                    {
                    otherlv_16=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getFunctionTypeExpressionAccess().getColonKeyword_8_0());
                          
                    }
                    // InternalTypesParser.g:4220:1: ( (lv_returnTypeRef_17_0= ruleTypeRef ) )
                    // InternalTypesParser.g:4221:1: (lv_returnTypeRef_17_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:4221:1: (lv_returnTypeRef_17_0= ruleTypeRef )
                    // InternalTypesParser.g:4222:3: lv_returnTypeRef_17_0= ruleTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionTypeExpressionAccess().getReturnTypeRefTypeRefParserRuleCall_8_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_19);
                    lv_returnTypeRef_17_0=ruleTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionTypeExpressionRule());
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

                  	newLeafNode(otherlv_18, grammarAccess.getFunctionTypeExpressionAccess().getRightCurlyBracketKeyword_9());
                  
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
    // $ANTLR end "ruleFunctionTypeExpression"


    // $ANTLR start "ruleTAnonymousFormalParameterList"
    // InternalTypesParser.g:4252:1: ruleTAnonymousFormalParameterList[EObject in_current] returns [EObject current=in_current] : ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )? ;
    public final EObject ruleTAnonymousFormalParameterList(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_1=null;
        EObject lv_fpars_0_0 = null;

        EObject lv_fpars_2_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4255:28: ( ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )? )
            // InternalTypesParser.g:4256:1: ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )?
            {
            // InternalTypesParser.g:4256:1: ( ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )* )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==AssignmnentCompatible||(LA89_0>=AutoboxedType && LA89_0<=Abstract)||LA89_0==Indexed||(LA89_0>=Private && LA89_0<=Static)||LA89_0==Union||(LA89_0>=From && LA89_0<=Void)||(LA89_0>=FullStopFullStopFullStop && LA89_0<=Get)||(LA89_0>=Set && LA89_0<=As)||LA89_0==LeftCurlyBracket||LA89_0==Tilde||LA89_0==RULE_IDENTIFIER) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // InternalTypesParser.g:4256:2: ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) ) (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )*
                    {
                    // InternalTypesParser.g:4256:2: ( (lv_fpars_0_0= ruleTAnonymousFormalParameter ) )
                    // InternalTypesParser.g:4257:1: (lv_fpars_0_0= ruleTAnonymousFormalParameter )
                    {
                    // InternalTypesParser.g:4257:1: (lv_fpars_0_0= ruleTAnonymousFormalParameter )
                    // InternalTypesParser.g:4258:3: lv_fpars_0_0= ruleTAnonymousFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterListAccess().getFparsTAnonymousFormalParameterParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_74);
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

                    // InternalTypesParser.g:4274:2: (otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) ) )*
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==Comma) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // InternalTypesParser.g:4275:2: otherlv_1= Comma ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) )
                    	    {
                    	    otherlv_1=(Token)match(input,Comma,FOLLOW_75); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_1, grammarAccess.getTAnonymousFormalParameterListAccess().getCommaKeyword_1_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:4279:1: ( (lv_fpars_2_0= ruleTAnonymousFormalParameter ) )
                    	    // InternalTypesParser.g:4280:1: (lv_fpars_2_0= ruleTAnonymousFormalParameter )
                    	    {
                    	    // InternalTypesParser.g:4280:1: (lv_fpars_2_0= ruleTAnonymousFormalParameter )
                    	    // InternalTypesParser.g:4281:3: lv_fpars_2_0= ruleTAnonymousFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTAnonymousFormalParameterListAccess().getFparsTAnonymousFormalParameterParserRuleCall_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_74);
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
                    	    break loop88;
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
    // InternalTypesParser.g:4305:1: entryRuleTAnonymousFormalParameter returns [EObject current=null] : iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF ;
    public final EObject entryRuleTAnonymousFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTAnonymousFormalParameter = null;


        try {
            // InternalTypesParser.g:4306:2: (iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF )
            // InternalTypesParser.g:4307:2: iv_ruleTAnonymousFormalParameter= ruleTAnonymousFormalParameter EOF
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
    // InternalTypesParser.g:4314:1: ruleTAnonymousFormalParameter returns [EObject current=null] : ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTAnonymousFormalParameter() throws RecognitionException {
        EObject current = null;

        Token lv_variadic_0_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_typeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4317:28: ( ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:4318:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:4318:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:4318:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )? ( (lv_typeRef_3_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:4318:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==FullStopFullStopFullStop) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalTypesParser.g:4319:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    {
                    // InternalTypesParser.g:4319:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    // InternalTypesParser.g:4320:3: lv_variadic_0_0= FullStopFullStopFullStop
                    {
                    lv_variadic_0_0=(Token)match(input,FullStopFullStopFullStop,FOLLOW_75); if (state.failed) return current;
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

            // InternalTypesParser.g:4334:3: ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )?
            int alt91=2;
            alt91 = dfa91.predict(input);
            switch (alt91) {
                case 1 :
                    // InternalTypesParser.g:4334:4: ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon
                    {
                    // InternalTypesParser.g:4334:4: ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) )
                    // InternalTypesParser.g:4334:5: ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier )
                    {
                    // InternalTypesParser.g:4339:1: (lv_name_1_0= ruleTIdentifier )
                    // InternalTypesParser.g:4340:3: lv_name_1_0= ruleTIdentifier
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

            // InternalTypesParser.g:4361:3: ( (lv_typeRef_3_0= ruleTypeRef ) )
            // InternalTypesParser.g:4362:1: (lv_typeRef_3_0= ruleTypeRef )
            {
            // InternalTypesParser.g:4362:1: (lv_typeRef_3_0= ruleTypeRef )
            // InternalTypesParser.g:4363:3: lv_typeRef_3_0= ruleTypeRef
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
    // InternalTypesParser.g:4387:1: entryRuleTFormalParameter returns [EObject current=null] : iv_ruleTFormalParameter= ruleTFormalParameter EOF ;
    public final EObject entryRuleTFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTFormalParameter = null;


        try {
            // InternalTypesParser.g:4388:2: (iv_ruleTFormalParameter= ruleTFormalParameter EOF )
            // InternalTypesParser.g:4389:2: iv_ruleTFormalParameter= ruleTFormalParameter EOF
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
    // InternalTypesParser.g:4396:1: ruleTFormalParameter returns [EObject current=null] : ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) ) ;
    public final EObject ruleTFormalParameter() throws RecognitionException {
        EObject current = null;

        Token lv_variadic_0_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_typeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4399:28: ( ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) ) )
            // InternalTypesParser.g:4400:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            {
            // InternalTypesParser.g:4400:1: ( ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) ) )
            // InternalTypesParser.g:4400:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )? ( (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon ( (lv_typeRef_3_0= ruleTypeRef ) )
            {
            // InternalTypesParser.g:4400:2: ( (lv_variadic_0_0= FullStopFullStopFullStop ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==FullStopFullStopFullStop) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalTypesParser.g:4401:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    {
                    // InternalTypesParser.g:4401:1: (lv_variadic_0_0= FullStopFullStopFullStop )
                    // InternalTypesParser.g:4402:3: lv_variadic_0_0= FullStopFullStopFullStop
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

            // InternalTypesParser.g:4416:3: ( (lv_name_1_0= ruleTIdentifier ) )
            // InternalTypesParser.g:4417:1: (lv_name_1_0= ruleTIdentifier )
            {
            // InternalTypesParser.g:4417:1: (lv_name_1_0= ruleTIdentifier )
            // InternalTypesParser.g:4418:3: lv_name_1_0= ruleTIdentifier
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
            // InternalTypesParser.g:4439:1: ( (lv_typeRef_3_0= ruleTypeRef ) )
            // InternalTypesParser.g:4440:1: (lv_typeRef_3_0= ruleTypeRef )
            {
            // InternalTypesParser.g:4440:1: (lv_typeRef_3_0= ruleTypeRef )
            // InternalTypesParser.g:4441:3: lv_typeRef_3_0= ruleTypeRef
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


    // $ANTLR start "entryRuleUnionTypeExpression"
    // InternalTypesParser.g:4465:1: entryRuleUnionTypeExpression returns [EObject current=null] : iv_ruleUnionTypeExpression= ruleUnionTypeExpression EOF ;
    public final EObject entryRuleUnionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnionTypeExpression = null;


        try {
            // InternalTypesParser.g:4466:2: (iv_ruleUnionTypeExpression= ruleUnionTypeExpression EOF )
            // InternalTypesParser.g:4467:2: iv_ruleUnionTypeExpression= ruleUnionTypeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnionTypeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnionTypeExpression=ruleUnionTypeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnionTypeExpression; 
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
    // $ANTLR end "entryRuleUnionTypeExpression"


    // $ANTLR start "ruleUnionTypeExpression"
    // InternalTypesParser.g:4474:1: ruleUnionTypeExpression returns [EObject current=null] : ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleUnionTypeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_typeRefs_3_0 = null;

        EObject lv_typeRefs_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4477:28: ( ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) )
            // InternalTypesParser.g:4478:1: ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            {
            // InternalTypesParser.g:4478:1: ( () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            // InternalTypesParser.g:4478:2: () otherlv_1= Union otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket
            {
            // InternalTypesParser.g:4478:2: ()
            // InternalTypesParser.g:4479:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getUnionTypeExpressionAccess().getUnionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Union,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUnionTypeExpressionAccess().getUnionKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getUnionTypeExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:4494:1: ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) )
            // InternalTypesParser.g:4495:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            {
            // InternalTypesParser.g:4495:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            // InternalTypesParser.g:4496:3: lv_typeRefs_3_0= ruleTypeRefWithoutModifiers
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnionTypeExpressionAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_typeRefs_3_0=ruleTypeRefWithoutModifiers();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnionTypeExpressionRule());
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

            // InternalTypesParser.g:4512:2: (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==Comma) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // InternalTypesParser.g:4513:2: otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    {
            	    otherlv_4=(Token)match(input,Comma,FOLLOW_8); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getUnionTypeExpressionAccess().getCommaKeyword_4_0());
            	          
            	    }
            	    // InternalTypesParser.g:4517:1: ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    // InternalTypesParser.g:4518:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    {
            	    // InternalTypesParser.g:4518:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    // InternalTypesParser.g:4519:3: lv_typeRefs_5_0= ruleTypeRefWithoutModifiers
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getUnionTypeExpressionAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_65);
            	    lv_typeRefs_5_0=ruleTypeRefWithoutModifiers();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getUnionTypeExpressionRule());
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
            	    break loop93;
                }
            } while (true);

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getUnionTypeExpressionAccess().getRightCurlyBracketKeyword_5());
                  
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
    // $ANTLR end "ruleUnionTypeExpression"


    // $ANTLR start "entryRuleIntersectionTypeExpression"
    // InternalTypesParser.g:4548:1: entryRuleIntersectionTypeExpression returns [EObject current=null] : iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF ;
    public final EObject entryRuleIntersectionTypeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntersectionTypeExpression = null;


        try {
            // InternalTypesParser.g:4549:2: (iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF )
            // InternalTypesParser.g:4550:2: iv_ruleIntersectionTypeExpression= ruleIntersectionTypeExpression EOF
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
    // InternalTypesParser.g:4557:1: ruleIntersectionTypeExpression returns [EObject current=null] : ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleIntersectionTypeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_typeRefs_3_0 = null;

        EObject lv_typeRefs_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4560:28: ( ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket ) )
            // InternalTypesParser.g:4561:1: ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            {
            // InternalTypesParser.g:4561:1: ( () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket )
            // InternalTypesParser.g:4561:2: () otherlv_1= Intersection otherlv_2= LeftCurlyBracket ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) ) (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )* otherlv_6= RightCurlyBracket
            {
            // InternalTypesParser.g:4561:2: ()
            // InternalTypesParser.g:4562:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getIntersectionTypeExpressionAccess().getIntersectionTypeExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,Intersection,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getIntersectionTypeExpressionAccess().getIntersectionKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIntersectionTypeExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:4577:1: ( (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers ) )
            // InternalTypesParser.g:4578:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            {
            // InternalTypesParser.g:4578:1: (lv_typeRefs_3_0= ruleTypeRefWithoutModifiers )
            // InternalTypesParser.g:4579:3: lv_typeRefs_3_0= ruleTypeRefWithoutModifiers
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntersectionTypeExpressionAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_65);
            lv_typeRefs_3_0=ruleTypeRefWithoutModifiers();

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
                      		"eu.numberfour.n4js.ts.TypeExpressions.TypeRefWithoutModifiers");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:4595:2: (otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) ) )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==Comma) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // InternalTypesParser.g:4596:2: otherlv_4= Comma ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    {
            	    otherlv_4=(Token)match(input,Comma,FOLLOW_8); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getIntersectionTypeExpressionAccess().getCommaKeyword_4_0());
            	          
            	    }
            	    // InternalTypesParser.g:4600:1: ( (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers ) )
            	    // InternalTypesParser.g:4601:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    {
            	    // InternalTypesParser.g:4601:1: (lv_typeRefs_5_0= ruleTypeRefWithoutModifiers )
            	    // InternalTypesParser.g:4602:3: lv_typeRefs_5_0= ruleTypeRefWithoutModifiers
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getIntersectionTypeExpressionAccess().getTypeRefsTypeRefWithoutModifiersParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_65);
            	    lv_typeRefs_5_0=ruleTypeRefWithoutModifiers();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getIntersectionTypeExpressionRule());
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
            	    break loop94;
                }
            } while (true);

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getIntersectionTypeExpressionAccess().getRightCurlyBracketKeyword_5());
                  
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


    // $ANTLR start "entryRuleParameterizedTypeRef"
    // InternalTypesParser.g:4631:1: entryRuleParameterizedTypeRef returns [EObject current=null] : iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF ;
    public final EObject entryRuleParameterizedTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRef = null;


        try {
            // InternalTypesParser.g:4632:2: (iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF )
            // InternalTypesParser.g:4633:2: iv_ruleParameterizedTypeRef= ruleParameterizedTypeRef EOF
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
    // InternalTypesParser.g:4640:1: ruleParameterizedTypeRef returns [EObject current=null] : (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural ) ;
    public final EObject ruleParameterizedTypeRef() throws RecognitionException {
        EObject current = null;

        EObject this_ParameterizedTypeRefNominal_0 = null;

        EObject this_ParameterizedTypeRefStructural_1 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4643:28: ( (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural ) )
            // InternalTypesParser.g:4644:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural )
            {
            // InternalTypesParser.g:4644:1: (this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal | this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==Undefined||LA95_0==Indexed||LA95_0==Null||LA95_0==Void||LA95_0==Any||LA95_0==RULE_IDENTIFIER) ) {
                alt95=1;
            }
            else if ( (LA95_0==Tilde) ) {
                alt95=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // InternalTypesParser.g:4645:5: this_ParameterizedTypeRefNominal_0= ruleParameterizedTypeRefNominal
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
                    // InternalTypesParser.g:4655:5: this_ParameterizedTypeRefStructural_1= ruleParameterizedTypeRefStructural
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


    // $ANTLR start "entryRuleParameterizedTypeRefStructural"
    // InternalTypesParser.g:4671:1: entryRuleParameterizedTypeRefStructural returns [EObject current=null] : iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF ;
    public final EObject entryRuleParameterizedTypeRefStructural() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRefStructural = null;


        try {
            // InternalTypesParser.g:4672:2: (iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF )
            // InternalTypesParser.g:4673:2: iv_ruleParameterizedTypeRefStructural= ruleParameterizedTypeRefStructural EOF
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
    // InternalTypesParser.g:4680:1: ruleParameterizedTypeRefStructural returns [EObject current=null] : ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? ) ;
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
            // InternalTypesParser.g:4683:28: ( ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? ) )
            // InternalTypesParser.g:4684:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? )
            {
            // InternalTypesParser.g:4684:1: ( ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )? )
            // InternalTypesParser.g:4684:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) ) ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )? (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )?
            {
            // InternalTypesParser.g:4684:2: ( (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator ) )
            // InternalTypesParser.g:4685:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            {
            // InternalTypesParser.g:4685:1: (lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator )
            // InternalTypesParser.g:4686:3: lv_definedTypingStrategy_0_0= ruleTypingStrategyUseSiteOperator
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

            // InternalTypesParser.g:4702:2: ( ( ruleTypeReferenceName ) )
            // InternalTypesParser.g:4703:1: ( ruleTypeReferenceName )
            {
            // InternalTypesParser.g:4703:1: ( ruleTypeReferenceName )
            // InternalTypesParser.g:4704:3: ruleTypeReferenceName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getParameterizedTypeRefStructuralRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getParameterizedTypeRefStructuralAccess().getDeclaredTypeTypeCrossReference_1_0()); 
              	    
            }
            pushFollow(FOLLOW_76);
            ruleTypeReferenceName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalTypesParser.g:4718:2: ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )?
            int alt97=2;
            alt97 = dfa97.predict(input);
            switch (alt97) {
                case 1 :
                    // InternalTypesParser.g:4718:3: ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign
                    {
                    // InternalTypesParser.g:4718:3: ( ( LessThanSign )=>otherlv_2= LessThanSign )
                    // InternalTypesParser.g:4718:4: ( LessThanSign )=>otherlv_2= LessThanSign
                    {
                    otherlv_2=(Token)match(input,LessThanSign,FOLLOW_77); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getParameterizedTypeRefStructuralAccess().getLessThanSignKeyword_2_0());
                          
                    }

                    }

                    // InternalTypesParser.g:4725:2: ( (lv_typeArgs_3_0= ruleTypeArgument ) )
                    // InternalTypesParser.g:4726:1: (lv_typeArgs_3_0= ruleTypeArgument )
                    {
                    // InternalTypesParser.g:4726:1: (lv_typeArgs_3_0= ruleTypeArgument )
                    // InternalTypesParser.g:4727:3: lv_typeArgs_3_0= ruleTypeArgument
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

                    // InternalTypesParser.g:4743:2: (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )*
                    loop96:
                    do {
                        int alt96=2;
                        int LA96_0 = input.LA(1);

                        if ( (LA96_0==Comma) ) {
                            alt96=1;
                        }


                        switch (alt96) {
                    	case 1 :
                    	    // InternalTypesParser.g:4744:2: otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_77); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_4, grammarAccess.getParameterizedTypeRefStructuralAccess().getCommaKeyword_2_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:4748:1: ( (lv_typeArgs_5_0= ruleTypeArgument ) )
                    	    // InternalTypesParser.g:4749:1: (lv_typeArgs_5_0= ruleTypeArgument )
                    	    {
                    	    // InternalTypesParser.g:4749:1: (lv_typeArgs_5_0= ruleTypeArgument )
                    	    // InternalTypesParser.g:4750:3: lv_typeArgs_5_0= ruleTypeArgument
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
                    	    break loop96;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,GreaterThanSign,FOLLOW_68); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getParameterizedTypeRefStructuralAccess().getGreaterThanSignKeyword_2_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:4771:3: (otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current] )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==With) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // InternalTypesParser.g:4772:2: otherlv_7= With this_TStructMemberList_8= ruleTStructMemberList[$current]
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
    // InternalTypesParser.g:4797:1: ruleTStructMemberList[EObject in_current] returns [EObject current=in_current] : (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleTStructMemberList(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_astStructuralMembers_1_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4800:28: ( (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:4801:1: (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:4801:1: (otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:4802:2: otherlv_0= LeftCurlyBracket ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )* otherlv_4= RightCurlyBracket
            {
            otherlv_0=(Token)match(input,LeftCurlyBracket,FOLLOW_78); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTStructMemberListAccess().getLeftCurlyBracketKeyword_0());
                  
            }
            // InternalTypesParser.g:4806:1: ( ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )? )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==AssignmnentCompatible||(LA100_0>=AutoboxedType && LA100_0<=VirtualBase)||LA100_0==Primitive||(LA100_0>=Undefined && LA100_0<=Abstract)||(LA100_0>=Project && LA100_0<=Object)||LA100_0==Union||(LA100_0>=From && LA100_0<=Null)||(LA100_0>=Type && LA100_0<=Void)||(LA100_0>=Any && LA100_0<=Get)||(LA100_0>=Set && LA100_0<=As)||LA100_0==LessThanSign||LA100_0==RULE_IDENTIFIER) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // InternalTypesParser.g:4806:2: ( (lv_astStructuralMembers_1_0= ruleTStructMember ) ) (otherlv_2= Semicolon | otherlv_3= Comma )?
            	    {
            	    // InternalTypesParser.g:4806:2: ( (lv_astStructuralMembers_1_0= ruleTStructMember ) )
            	    // InternalTypesParser.g:4807:1: (lv_astStructuralMembers_1_0= ruleTStructMember )
            	    {
            	    // InternalTypesParser.g:4807:1: (lv_astStructuralMembers_1_0= ruleTStructMember )
            	    // InternalTypesParser.g:4808:3: lv_astStructuralMembers_1_0= ruleTStructMember
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTStructMemberListAccess().getAstStructuralMembersTStructMemberParserRuleCall_1_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_79);
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

            	    // InternalTypesParser.g:4824:2: (otherlv_2= Semicolon | otherlv_3= Comma )?
            	    int alt99=3;
            	    int LA99_0 = input.LA(1);

            	    if ( (LA99_0==Semicolon) ) {
            	        alt99=1;
            	    }
            	    else if ( (LA99_0==Comma) ) {
            	        alt99=2;
            	    }
            	    switch (alt99) {
            	        case 1 :
            	            // InternalTypesParser.g:4825:2: otherlv_2= Semicolon
            	            {
            	            otherlv_2=(Token)match(input,Semicolon,FOLLOW_78); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_2, grammarAccess.getTStructMemberListAccess().getSemicolonKeyword_1_1_0());
            	                  
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalTypesParser.g:4831:2: otherlv_3= Comma
            	            {
            	            otherlv_3=(Token)match(input,Comma,FOLLOW_78); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_3, grammarAccess.getTStructMemberListAccess().getCommaKeyword_1_1_1());
            	                  
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop100;
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
    // InternalTypesParser.g:4848:1: entryRuleTStructMember returns [EObject current=null] : iv_ruleTStructMember= ruleTStructMember EOF ;
    public final EObject entryRuleTStructMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructMember = null;


        try {
            // InternalTypesParser.g:4849:2: (iv_ruleTStructMember= ruleTStructMember EOF )
            // InternalTypesParser.g:4850:2: iv_ruleTStructMember= ruleTStructMember EOF
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
    // InternalTypesParser.g:4857:1: ruleTStructMember returns [EObject current=null] : ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField ) ;
    public final EObject ruleTStructMember() throws RecognitionException {
        EObject current = null;

        EObject this_TStructGetter_0 = null;

        EObject this_TStructSetter_1 = null;

        EObject this_TStructMethod_2 = null;

        EObject this_TStructField_3 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:4860:28: ( ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField ) )
            // InternalTypesParser.g:4861:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )
            {
            // InternalTypesParser.g:4861:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )
            int alt101=4;
            alt101 = dfa101.predict(input);
            switch (alt101) {
                case 1 :
                    // InternalTypesParser.g:4861:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter )
                    {
                    // InternalTypesParser.g:4861:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter )
                    // InternalTypesParser.g:4861:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter
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
                    // InternalTypesParser.g:4878:6: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter )
                    {
                    // InternalTypesParser.g:4878:6: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter )
                    // InternalTypesParser.g:4878:7: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter
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
                    // InternalTypesParser.g:4895:6: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod )
                    {
                    // InternalTypesParser.g:4895:6: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod )
                    // InternalTypesParser.g:4895:7: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod
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
                    // InternalTypesParser.g:4927:5: this_TStructField_3= ruleTStructField
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
    // InternalTypesParser.g:4943:1: entryRuleTStructMethod returns [EObject current=null] : iv_ruleTStructMethod= ruleTStructMethod EOF ;
    public final EObject entryRuleTStructMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructMethod = null;


        try {
            // InternalTypesParser.g:4944:2: (iv_ruleTStructMethod= ruleTStructMethod EOF )
            // InternalTypesParser.g:4945:2: iv_ruleTStructMethod= ruleTStructMethod EOF
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
    // InternalTypesParser.g:4952:1: ruleTStructMethod returns [EObject current=null] : ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? ) ;
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
            // InternalTypesParser.g:4955:28: ( ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:4956:1: ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:4956:1: ( ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:4956:2: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) ) this_TAnonymousFormalParameterList_8= ruleTAnonymousFormalParameterList[$current] otherlv_9= RightParenthesis (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:4956:2: ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis ) )
            // InternalTypesParser.g:4956:3: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=> ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis )
            {
            // InternalTypesParser.g:4977:5: ( () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis )
            // InternalTypesParser.g:4977:6: () (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )? ( (lv_name_6_0= ruleTypesIdentifier ) ) otherlv_7= LeftParenthesis
            {
            // InternalTypesParser.g:4977:6: ()
            // InternalTypesParser.g:4978:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTStructMethodAccess().getTStructMethodAction_0_0_0(),
                          current);
                  
            }

            }

            // InternalTypesParser.g:4983:2: (otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==LessThanSign) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalTypesParser.g:4984:2: otherlv_1= LessThanSign ( (lv_typeVars_2_0= ruleTypeVariable ) ) (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )* otherlv_5= GreaterThanSign
                    {
                    otherlv_1=(Token)match(input,LessThanSign,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTStructMethodAccess().getLessThanSignKeyword_0_0_1_0());
                          
                    }
                    // InternalTypesParser.g:4988:1: ( (lv_typeVars_2_0= ruleTypeVariable ) )
                    // InternalTypesParser.g:4989:1: (lv_typeVars_2_0= ruleTypeVariable )
                    {
                    // InternalTypesParser.g:4989:1: (lv_typeVars_2_0= ruleTypeVariable )
                    // InternalTypesParser.g:4990:3: lv_typeVars_2_0= ruleTypeVariable
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

                    // InternalTypesParser.g:5006:2: (otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==Comma) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // InternalTypesParser.g:5007:2: otherlv_3= Comma ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTStructMethodAccess().getCommaKeyword_0_0_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5011:1: ( (lv_typeVars_4_0= ruleTypeVariable ) )
                    	    // InternalTypesParser.g:5012:1: (lv_typeVars_4_0= ruleTypeVariable )
                    	    {
                    	    // InternalTypesParser.g:5012:1: (lv_typeVars_4_0= ruleTypeVariable )
                    	    // InternalTypesParser.g:5013:3: lv_typeVars_4_0= ruleTypeVariable
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
                    	    break loop102;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,GreaterThanSign,FOLLOW_11); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTStructMethodAccess().getGreaterThanSignKeyword_0_0_1_3());
                          
                    }

                    }
                    break;

            }

            // InternalTypesParser.g:5034:3: ( (lv_name_6_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5035:1: (lv_name_6_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5035:1: (lv_name_6_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5036:3: lv_name_6_0= ruleTypesIdentifier
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

            otherlv_7=(Token)match(input,LeftParenthesis,FOLLOW_72); if (state.failed) return current;
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
            otherlv_9=(Token)match(input,RightParenthesis,FOLLOW_80); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getTStructMethodAccess().getRightParenthesisKeyword_2());
                  
            }
            // InternalTypesParser.g:5074:1: (otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==Colon) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // InternalTypesParser.g:5075:2: otherlv_10= Colon ( (lv_returnTypeRef_11_0= ruleTypeRef ) )
                    {
                    otherlv_10=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getTStructMethodAccess().getColonKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:5079:1: ( (lv_returnTypeRef_11_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5080:1: (lv_returnTypeRef_11_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5080:1: (lv_returnTypeRef_11_0= ruleTypeRef )
                    // InternalTypesParser.g:5081:3: lv_returnTypeRef_11_0= ruleTypeRef
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
    // InternalTypesParser.g:5105:1: entryRuleTStructField returns [EObject current=null] : iv_ruleTStructField= ruleTStructField EOF ;
    public final EObject entryRuleTStructField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructField = null;


        try {
            // InternalTypesParser.g:5106:2: (iv_ruleTStructField= ruleTStructField EOF )
            // InternalTypesParser.g:5107:2: iv_ruleTStructField= ruleTStructField EOF
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
    // InternalTypesParser.g:5114:1: ruleTStructField returns [EObject current=null] : ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? ) ;
    public final EObject ruleTStructField() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_typeRef_2_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5117:28: ( ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:5118:1: ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:5118:1: ( ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:5118:2: ( (lv_name_0_0= ruleTypesIdentifier ) ) (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:5118:2: ( (lv_name_0_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5119:1: (lv_name_0_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5119:1: (lv_name_0_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5120:3: lv_name_0_0= ruleTypesIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTStructFieldAccess().getNameTypesIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_80);
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

            // InternalTypesParser.g:5136:2: (otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==Colon) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalTypesParser.g:5137:2: otherlv_1= Colon ( (lv_typeRef_2_0= ruleTypeRef ) )
                    {
                    otherlv_1=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTStructFieldAccess().getColonKeyword_1_0());
                          
                    }
                    // InternalTypesParser.g:5141:1: ( (lv_typeRef_2_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5142:1: (lv_typeRef_2_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5142:1: (lv_typeRef_2_0= ruleTypeRef )
                    // InternalTypesParser.g:5143:3: lv_typeRef_2_0= ruleTypeRef
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
    // InternalTypesParser.g:5167:1: entryRuleTStructGetter returns [EObject current=null] : iv_ruleTStructGetter= ruleTStructGetter EOF ;
    public final EObject entryRuleTStructGetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructGetter = null;


        try {
            // InternalTypesParser.g:5168:2: (iv_ruleTStructGetter= ruleTStructGetter EOF )
            // InternalTypesParser.g:5169:2: iv_ruleTStructGetter= ruleTStructGetter EOF
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
    // InternalTypesParser.g:5176:1: ruleTStructGetter returns [EObject current=null] : ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? ) ;
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
            // InternalTypesParser.g:5179:28: ( ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? ) )
            // InternalTypesParser.g:5180:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? )
            {
            // InternalTypesParser.g:5180:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )? )
            // InternalTypesParser.g:5180:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis otherlv_4= RightParenthesis (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )?
            {
            // InternalTypesParser.g:5180:2: ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) ) )
            // InternalTypesParser.g:5180:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            {
            // InternalTypesParser.g:5187:6: ( () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            // InternalTypesParser.g:5187:7: () otherlv_1= Get ( (lv_name_2_0= ruleTypesIdentifier ) )
            {
            // InternalTypesParser.g:5187:7: ()
            // InternalTypesParser.g:5188:5: 
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
            // InternalTypesParser.g:5198:1: ( (lv_name_2_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5199:1: (lv_name_2_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5199:1: (lv_name_2_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5200:3: lv_name_2_0= ruleTypesIdentifier
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
            otherlv_4=(Token)match(input,RightParenthesis,FOLLOW_80); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTStructGetterAccess().getRightParenthesisKeyword_2());
                  
            }
            // InternalTypesParser.g:5226:1: (otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==Colon) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // InternalTypesParser.g:5227:2: otherlv_5= Colon ( (lv_declaredTypeRef_6_0= ruleTypeRef ) )
                    {
                    otherlv_5=(Token)match(input,Colon,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTStructGetterAccess().getColonKeyword_3_0());
                          
                    }
                    // InternalTypesParser.g:5231:1: ( (lv_declaredTypeRef_6_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5232:1: (lv_declaredTypeRef_6_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5232:1: (lv_declaredTypeRef_6_0= ruleTypeRef )
                    // InternalTypesParser.g:5233:3: lv_declaredTypeRef_6_0= ruleTypeRef
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
    // InternalTypesParser.g:5257:1: entryRuleTStructSetter returns [EObject current=null] : iv_ruleTStructSetter= ruleTStructSetter EOF ;
    public final EObject entryRuleTStructSetter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTStructSetter = null;


        try {
            // InternalTypesParser.g:5258:2: (iv_ruleTStructSetter= ruleTStructSetter EOF )
            // InternalTypesParser.g:5259:2: iv_ruleTStructSetter= ruleTStructSetter EOF
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
    // InternalTypesParser.g:5266:1: ruleTStructSetter returns [EObject current=null] : ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis ) ;
    public final EObject ruleTStructSetter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_fpar_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5269:28: ( ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis ) )
            // InternalTypesParser.g:5270:1: ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis )
            {
            // InternalTypesParser.g:5270:1: ( ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis )
            // InternalTypesParser.g:5270:2: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) ) otherlv_3= LeftParenthesis ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) ) otherlv_5= RightParenthesis
            {
            // InternalTypesParser.g:5270:2: ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) ) )
            // InternalTypesParser.g:5270:3: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=> ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            {
            // InternalTypesParser.g:5277:6: ( () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) ) )
            // InternalTypesParser.g:5277:7: () otherlv_1= Set ( (lv_name_2_0= ruleTypesIdentifier ) )
            {
            // InternalTypesParser.g:5277:7: ()
            // InternalTypesParser.g:5278:5: 
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
            // InternalTypesParser.g:5288:1: ( (lv_name_2_0= ruleTypesIdentifier ) )
            // InternalTypesParser.g:5289:1: (lv_name_2_0= ruleTypesIdentifier )
            {
            // InternalTypesParser.g:5289:1: (lv_name_2_0= ruleTypesIdentifier )
            // InternalTypesParser.g:5290:3: lv_name_2_0= ruleTypesIdentifier
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

            otherlv_3=(Token)match(input,LeftParenthesis,FOLLOW_75); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTStructSetterAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalTypesParser.g:5311:1: ( (lv_fpar_4_0= ruleTAnonymousFormalParameter ) )
            // InternalTypesParser.g:5312:1: (lv_fpar_4_0= ruleTAnonymousFormalParameter )
            {
            // InternalTypesParser.g:5312:1: (lv_fpar_4_0= ruleTAnonymousFormalParameter )
            // InternalTypesParser.g:5313:3: lv_fpar_4_0= ruleTAnonymousFormalParameter
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


    // $ANTLR start "entryRuleParameterizedTypeRefNominal"
    // InternalTypesParser.g:5342:1: entryRuleParameterizedTypeRefNominal returns [EObject current=null] : iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF ;
    public final EObject entryRuleParameterizedTypeRefNominal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterizedTypeRefNominal = null;


        try {
            // InternalTypesParser.g:5343:2: (iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF )
            // InternalTypesParser.g:5344:2: iv_ruleParameterizedTypeRefNominal= ruleParameterizedTypeRefNominal EOF
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
    // InternalTypesParser.g:5351:1: ruleParameterizedTypeRefNominal returns [EObject current=null] : ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? ) ;
    public final EObject ruleParameterizedTypeRefNominal() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_typeArgs_2_0 = null;

        EObject lv_typeArgs_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5354:28: ( ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? ) )
            // InternalTypesParser.g:5355:1: ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? )
            {
            // InternalTypesParser.g:5355:1: ( ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )? )
            // InternalTypesParser.g:5355:2: ( ( ruleTypeReferenceName ) ) ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?
            {
            // InternalTypesParser.g:5355:2: ( ( ruleTypeReferenceName ) )
            // InternalTypesParser.g:5356:1: ( ruleTypeReferenceName )
            {
            // InternalTypesParser.g:5356:1: ( ruleTypeReferenceName )
            // InternalTypesParser.g:5357:3: ruleTypeReferenceName
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

            // InternalTypesParser.g:5371:2: ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?
            int alt108=2;
            alt108 = dfa108.predict(input);
            switch (alt108) {
                case 1 :
                    // InternalTypesParser.g:5371:3: ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign
                    {
                    // InternalTypesParser.g:5371:3: ( ( LessThanSign )=>otherlv_1= LessThanSign )
                    // InternalTypesParser.g:5371:4: ( LessThanSign )=>otherlv_1= LessThanSign
                    {
                    otherlv_1=(Token)match(input,LessThanSign,FOLLOW_77); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getParameterizedTypeRefNominalAccess().getLessThanSignKeyword_1_0());
                          
                    }

                    }

                    // InternalTypesParser.g:5378:2: ( (lv_typeArgs_2_0= ruleTypeArgument ) )
                    // InternalTypesParser.g:5379:1: (lv_typeArgs_2_0= ruleTypeArgument )
                    {
                    // InternalTypesParser.g:5379:1: (lv_typeArgs_2_0= ruleTypeArgument )
                    // InternalTypesParser.g:5380:3: lv_typeArgs_2_0= ruleTypeArgument
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

                    // InternalTypesParser.g:5396:2: (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )*
                    loop107:
                    do {
                        int alt107=2;
                        int LA107_0 = input.LA(1);

                        if ( (LA107_0==Comma) ) {
                            alt107=1;
                        }


                        switch (alt107) {
                    	case 1 :
                    	    // InternalTypesParser.g:5397:2: otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_77); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getParameterizedTypeRefNominalAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5401:1: ( (lv_typeArgs_4_0= ruleTypeArgument ) )
                    	    // InternalTypesParser.g:5402:1: (lv_typeArgs_4_0= ruleTypeArgument )
                    	    {
                    	    // InternalTypesParser.g:5402:1: (lv_typeArgs_4_0= ruleTypeArgument )
                    	    // InternalTypesParser.g:5403:3: lv_typeArgs_4_0= ruleTypeArgument
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
                    	    break loop107;
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


    // $ANTLR start "entryRuleTypingStrategyUseSiteOperator"
    // InternalTypesParser.g:5432:1: entryRuleTypingStrategyUseSiteOperator returns [String current=null] : iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF ;
    public final String entryRuleTypingStrategyUseSiteOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypingStrategyUseSiteOperator = null;


        try {
            // InternalTypesParser.g:5433:1: (iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF )
            // InternalTypesParser.g:5434:2: iv_ruleTypingStrategyUseSiteOperator= ruleTypingStrategyUseSiteOperator EOF
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
    // InternalTypesParser.g:5441:1: ruleTypingStrategyUseSiteOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? ) ;
    public final AntlrDatatypeRuleToken ruleTypingStrategyUseSiteOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_STRUCTMODSUFFIX_2=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:5445:6: ( (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? ) )
            // InternalTypesParser.g:5446:1: (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? )
            {
            // InternalTypesParser.g:5446:1: (kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )? )
            // InternalTypesParser.g:5447:2: kw= Tilde (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )?
            {
            kw=(Token)match(input,Tilde,FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getTypingStrategyUseSiteOperatorAccess().getTildeKeyword_0()); 
                  
            }
            // InternalTypesParser.g:5452:1: (kw= Tilde | this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX )?
            int alt109=3;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==Tilde) ) {
                alt109=1;
            }
            else if ( (LA109_0==RULE_STRUCTMODSUFFIX) ) {
                alt109=2;
            }
            switch (alt109) {
                case 1 :
                    // InternalTypesParser.g:5453:2: kw= Tilde
                    {
                    kw=(Token)match(input,Tilde,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTypingStrategyUseSiteOperatorAccess().getTildeKeyword_1_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // InternalTypesParser.g:5459:10: this_STRUCTMODSUFFIX_2= RULE_STRUCTMODSUFFIX
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
    // InternalTypesParser.g:5474:1: entryRuleTypingStrategyDefSiteOperator returns [String current=null] : iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF ;
    public final String entryRuleTypingStrategyDefSiteOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTypingStrategyDefSiteOperator = null;


        try {
            // InternalTypesParser.g:5475:1: (iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF )
            // InternalTypesParser.g:5476:2: iv_ruleTypingStrategyDefSiteOperator= ruleTypingStrategyDefSiteOperator EOF
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
    // InternalTypesParser.g:5483:1: ruleTypingStrategyDefSiteOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= Tilde ;
    public final AntlrDatatypeRuleToken ruleTypingStrategyDefSiteOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalTypesParser.g:5487:6: (kw= Tilde )
            // InternalTypesParser.g:5489:2: kw= Tilde
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
    // InternalTypesParser.g:5502:1: entryRuleConstructorTypeRef returns [EObject current=null] : iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF ;
    public final EObject entryRuleConstructorTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorTypeRef = null;


        try {
            // InternalTypesParser.g:5503:2: (iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF )
            // InternalTypesParser.g:5504:2: iv_ruleConstructorTypeRef= ruleConstructorTypeRef EOF
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
    // InternalTypesParser.g:5511:1: ruleConstructorTypeRef returns [EObject current=null] : ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleConstructorTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_staticTypeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5514:28: ( ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:5515:1: ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:5515:1: ( () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:5515:2: () otherlv_1= Constructor otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket
            {
            // InternalTypesParser.g:5515:2: ()
            // InternalTypesParser.g:5516:5: 
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
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_83); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getConstructorTypeRefAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:5531:1: ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) )
            // InternalTypesParser.g:5532:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            {
            // InternalTypesParser.g:5532:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            // InternalTypesParser.g:5533:3: lv_staticTypeRef_3_0= ruleTypeRefInClassifierType
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
    // InternalTypesParser.g:5562:1: entryRuleClassifierTypeRef returns [EObject current=null] : iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF ;
    public final EObject entryRuleClassifierTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifierTypeRef = null;


        try {
            // InternalTypesParser.g:5563:2: (iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF )
            // InternalTypesParser.g:5564:2: iv_ruleClassifierTypeRef= ruleClassifierTypeRef EOF
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
    // InternalTypesParser.g:5571:1: ruleClassifierTypeRef returns [EObject current=null] : ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) ;
    public final EObject ruleClassifierTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_staticTypeRef_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5574:28: ( ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket ) )
            // InternalTypesParser.g:5575:1: ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            {
            // InternalTypesParser.g:5575:1: ( () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket )
            // InternalTypesParser.g:5575:2: () otherlv_1= Type otherlv_2= LeftCurlyBracket ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) ) otherlv_4= RightCurlyBracket
            {
            // InternalTypesParser.g:5575:2: ()
            // InternalTypesParser.g:5576:5: 
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
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_83); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getClassifierTypeRefAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalTypesParser.g:5591:1: ( (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType ) )
            // InternalTypesParser.g:5592:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            {
            // InternalTypesParser.g:5592:1: (lv_staticTypeRef_3_0= ruleTypeRefInClassifierType )
            // InternalTypesParser.g:5593:3: lv_staticTypeRef_3_0= ruleTypeRefInClassifierType
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
    // InternalTypesParser.g:5622:1: entryRuleTypeArgument returns [EObject current=null] : iv_ruleTypeArgument= ruleTypeArgument EOF ;
    public final EObject entryRuleTypeArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeArgument = null;


        try {
            // InternalTypesParser.g:5623:2: (iv_ruleTypeArgument= ruleTypeArgument EOF )
            // InternalTypesParser.g:5624:2: iv_ruleTypeArgument= ruleTypeArgument EOF
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
    // InternalTypesParser.g:5631:1: ruleTypeArgument returns [EObject current=null] : ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef ) ;
    public final EObject ruleTypeArgument() throws RecognitionException {
        EObject current = null;

        EObject this_Wildcard_0 = null;

        EObject this_WildcardNewNotation_1 = null;

        EObject this_TypeRef_2 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5634:28: ( ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef ) )
            // InternalTypesParser.g:5635:1: ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef )
            {
            // InternalTypesParser.g:5635:1: ( ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard ) | this_WildcardNewNotation_1= ruleWildcardNewNotation | this_TypeRef_2= ruleTypeRef )
            int alt110=3;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==QuestionMark) && (synpred22_InternalTypesParser())) {
                alt110=1;
            }
            else if ( (LA110_0==Out||LA110_0==In) ) {
                alt110=2;
            }
            else if ( ((LA110_0>=Intersection && LA110_0<=Constructor)||LA110_0==Undefined||LA110_0==Indexed||LA110_0==Union||(LA110_0>=Null && LA110_0<=Void)||LA110_0==Any||LA110_0==LeftCurlyBracket||LA110_0==Tilde||LA110_0==RULE_IDENTIFIER) ) {
                alt110=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }
            switch (alt110) {
                case 1 :
                    // InternalTypesParser.g:5635:2: ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard )
                    {
                    // InternalTypesParser.g:5635:2: ( ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard )
                    // InternalTypesParser.g:5635:3: ( ( () QuestionMark ) )=>this_Wildcard_0= ruleWildcard
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
                    // InternalTypesParser.g:5649:5: this_WildcardNewNotation_1= ruleWildcardNewNotation
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
                    // InternalTypesParser.g:5659:5: this_TypeRef_2= ruleTypeRef
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
    // InternalTypesParser.g:5675:1: entryRuleWildcard returns [EObject current=null] : iv_ruleWildcard= ruleWildcard EOF ;
    public final EObject entryRuleWildcard() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcard = null;


        try {
            // InternalTypesParser.g:5676:2: (iv_ruleWildcard= ruleWildcard EOF )
            // InternalTypesParser.g:5677:2: iv_ruleWildcard= ruleWildcard EOF
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
    // InternalTypesParser.g:5684:1: ruleWildcard returns [EObject current=null] : ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? ) ;
    public final EObject ruleWildcard() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_declaredUpperBound_3_0 = null;

        EObject lv_declaredLowerBound_5_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5687:28: ( ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? ) )
            // InternalTypesParser.g:5688:1: ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? )
            {
            // InternalTypesParser.g:5688:1: ( ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )? )
            // InternalTypesParser.g:5688:2: ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) ) ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )?
            {
            // InternalTypesParser.g:5688:2: ( ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark ) )
            // InternalTypesParser.g:5688:3: ( ( () QuestionMark ) )=> ( () otherlv_1= QuestionMark )
            {
            // InternalTypesParser.g:5691:5: ( () otherlv_1= QuestionMark )
            // InternalTypesParser.g:5691:6: () otherlv_1= QuestionMark
            {
            // InternalTypesParser.g:5691:6: ()
            // InternalTypesParser.g:5692:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getWildcardAccess().getWildcardAction_0_0_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,QuestionMark,FOLLOW_84); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getWildcardAccess().getQuestionMarkKeyword_0_0_1());
                  
            }

            }


            }

            // InternalTypesParser.g:5702:3: ( (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) ) | (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) ) )?
            int alt111=3;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==Extends) ) {
                alt111=1;
            }
            else if ( (LA111_0==Super) ) {
                alt111=2;
            }
            switch (alt111) {
                case 1 :
                    // InternalTypesParser.g:5702:4: (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:5702:4: (otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:5703:2: otherlv_2= Extends ( (lv_declaredUpperBound_3_0= ruleTypeRef ) )
                    {
                    otherlv_2=(Token)match(input,Extends,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardAccess().getExtendsKeyword_1_0_0());
                          
                    }
                    // InternalTypesParser.g:5707:1: ( (lv_declaredUpperBound_3_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5708:1: (lv_declaredUpperBound_3_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5708:1: (lv_declaredUpperBound_3_0= ruleTypeRef )
                    // InternalTypesParser.g:5709:3: lv_declaredUpperBound_3_0= ruleTypeRef
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
                    // InternalTypesParser.g:5726:6: (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:5726:6: (otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:5727:2: otherlv_4= Super ( (lv_declaredLowerBound_5_0= ruleTypeRef ) )
                    {
                    otherlv_4=(Token)match(input,Super,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getWildcardAccess().getSuperKeyword_1_1_0());
                          
                    }
                    // InternalTypesParser.g:5731:1: ( (lv_declaredLowerBound_5_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5732:1: (lv_declaredLowerBound_5_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5732:1: (lv_declaredLowerBound_5_0= ruleTypeRef )
                    // InternalTypesParser.g:5733:3: lv_declaredLowerBound_5_0= ruleTypeRef
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
    // InternalTypesParser.g:5757:1: entryRuleWildcardNewNotation returns [EObject current=null] : iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF ;
    public final EObject entryRuleWildcardNewNotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardNewNotation = null;


        try {
            // InternalTypesParser.g:5758:2: (iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF )
            // InternalTypesParser.g:5759:2: iv_ruleWildcardNewNotation= ruleWildcardNewNotation EOF
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
    // InternalTypesParser.g:5766:1: ruleWildcardNewNotation returns [EObject current=null] : ( (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) ) ;
    public final EObject ruleWildcardNewNotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_declaredUpperBound_1_0 = null;

        EObject lv_declaredLowerBound_3_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5769:28: ( ( (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) ) )
            // InternalTypesParser.g:5770:1: ( (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) )
            {
            // InternalTypesParser.g:5770:1: ( (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) ) | (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) ) )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==Out) ) {
                alt112=1;
            }
            else if ( (LA112_0==In) ) {
                alt112=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // InternalTypesParser.g:5770:2: (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:5770:2: (otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:5771:2: otherlv_0= Out ( (lv_declaredUpperBound_1_0= ruleTypeRef ) )
                    {
                    otherlv_0=(Token)match(input,Out,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getWildcardNewNotationAccess().getOutKeyword_0_0());
                          
                    }
                    // InternalTypesParser.g:5775:1: ( (lv_declaredUpperBound_1_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5776:1: (lv_declaredUpperBound_1_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5776:1: (lv_declaredUpperBound_1_0= ruleTypeRef )
                    // InternalTypesParser.g:5777:3: lv_declaredUpperBound_1_0= ruleTypeRef
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
                    // InternalTypesParser.g:5794:6: (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) )
                    {
                    // InternalTypesParser.g:5794:6: (otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) ) )
                    // InternalTypesParser.g:5795:2: otherlv_2= In ( (lv_declaredLowerBound_3_0= ruleTypeRef ) )
                    {
                    otherlv_2=(Token)match(input,In,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardNewNotationAccess().getInKeyword_1_0());
                          
                    }
                    // InternalTypesParser.g:5799:1: ( (lv_declaredLowerBound_3_0= ruleTypeRef ) )
                    // InternalTypesParser.g:5800:1: (lv_declaredLowerBound_3_0= ruleTypeRef )
                    {
                    // InternalTypesParser.g:5800:1: (lv_declaredLowerBound_3_0= ruleTypeRef )
                    // InternalTypesParser.g:5801:3: lv_declaredLowerBound_3_0= ruleTypeRef
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
    // InternalTypesParser.g:5825:1: entryRuleTypeVariable returns [EObject current=null] : iv_ruleTypeVariable= ruleTypeVariable EOF ;
    public final EObject entryRuleTypeVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeVariable = null;


        try {
            // InternalTypesParser.g:5826:2: (iv_ruleTypeVariable= ruleTypeVariable EOF )
            // InternalTypesParser.g:5827:2: iv_ruleTypeVariable= ruleTypeVariable EOF
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
    // InternalTypesParser.g:5834:1: ruleTypeVariable returns [EObject current=null] : ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? ) ;
    public final EObject ruleTypeVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_declaredUpperBounds_2_0 = null;

        EObject lv_declaredUpperBounds_4_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5837:28: ( ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? ) )
            // InternalTypesParser.g:5838:1: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? )
            {
            // InternalTypesParser.g:5838:1: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )? )
            // InternalTypesParser.g:5838:2: ( (lv_name_0_0= RULE_IDENTIFIER ) ) (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )?
            {
            // InternalTypesParser.g:5838:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalTypesParser.g:5839:1: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalTypesParser.g:5839:1: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalTypesParser.g:5840:3: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_85); if (state.failed) return current;
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

            // InternalTypesParser.g:5856:2: (otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )* )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==Extends) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalTypesParser.g:5857:2: otherlv_1= Extends ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) ) (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )*
                    {
                    otherlv_1=(Token)match(input,Extends,FOLLOW_86); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeVariableAccess().getExtendsKeyword_1_0());
                          
                    }
                    // InternalTypesParser.g:5861:1: ( (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef ) )
                    // InternalTypesParser.g:5862:1: (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef )
                    {
                    // InternalTypesParser.g:5862:1: (lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef )
                    // InternalTypesParser.g:5863:3: lv_declaredUpperBounds_2_0= ruleParameterizedTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeVariableAccess().getDeclaredUpperBoundsParameterizedTypeRefParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_87);
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

                    // InternalTypesParser.g:5879:2: (otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) ) )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==Ampersand) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // InternalTypesParser.g:5880:2: otherlv_3= Ampersand ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Ampersand,FOLLOW_86); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTypeVariableAccess().getAmpersandKeyword_1_2_0());
                    	          
                    	    }
                    	    // InternalTypesParser.g:5884:1: ( (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef ) )
                    	    // InternalTypesParser.g:5885:1: (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef )
                    	    {
                    	    // InternalTypesParser.g:5885:1: (lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef )
                    	    // InternalTypesParser.g:5886:3: lv_declaredUpperBounds_4_0= ruleParameterizedTypeRef
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTypeVariableAccess().getDeclaredUpperBoundsParameterizedTypeRefParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_87);
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
                    	    break loop113;
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
    // InternalTypesParser.g:5910:1: entryRuleTIdentifier returns [String current=null] : iv_ruleTIdentifier= ruleTIdentifier EOF ;
    public final String entryRuleTIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTIdentifier = null;


        try {
            // InternalTypesParser.g:5911:1: (iv_ruleTIdentifier= ruleTIdentifier EOF )
            // InternalTypesParser.g:5912:2: iv_ruleTIdentifier= ruleTIdentifier EOF
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
    // InternalTypesParser.g:5919:1: ruleTIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static ) ;
    public final AntlrDatatypeRuleToken ruleTIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_TypesIdentifier_0 = null;


         enterRule(); 
            
        try {
            // InternalTypesParser.g:5923:6: ( (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static ) )
            // InternalTypesParser.g:5924:1: (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static )
            {
            // InternalTypesParser.g:5924:1: (this_TypesIdentifier_0= ruleTypesIdentifier | kw= Implements | kw= Interface | kw= Private | kw= Protected | kw= Public | kw= Static )
            int alt115=7;
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
                alt115=1;
                }
                break;
            case Implements:
                {
                alt115=2;
                }
                break;
            case Interface:
                {
                alt115=3;
                }
                break;
            case Private:
                {
                alt115=4;
                }
                break;
            case Protected:
                {
                alt115=5;
                }
                break;
            case Public:
                {
                alt115=6;
                }
                break;
            case Static:
                {
                alt115=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }

            switch (alt115) {
                case 1 :
                    // InternalTypesParser.g:5925:5: this_TypesIdentifier_0= ruleTypesIdentifier
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
                    // InternalTypesParser.g:5937:2: kw= Implements
                    {
                    kw=(Token)match(input,Implements,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getImplementsKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // InternalTypesParser.g:5944:2: kw= Interface
                    {
                    kw=(Token)match(input,Interface,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getInterfaceKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // InternalTypesParser.g:5951:2: kw= Private
                    {
                    kw=(Token)match(input,Private,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getPrivateKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // InternalTypesParser.g:5958:2: kw= Protected
                    {
                    kw=(Token)match(input,Protected,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getProtectedKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // InternalTypesParser.g:5965:2: kw= Public
                    {
                    kw=(Token)match(input,Public,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getTIdentifierAccess().getPublicKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // InternalTypesParser.g:5972:2: kw= Static
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
    // InternalTypesParser.g:5985:1: ruleTypeAccessModifier returns [Enumerator current=null] : ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) ) ;
    public final Enumerator ruleTypeAccessModifier() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalTypesParser.g:5987:28: ( ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) ) )
            // InternalTypesParser.g:5988:1: ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) )
            {
            // InternalTypesParser.g:5988:1: ( (enumLiteral_0= Project ) | (enumLiteral_1= PublicInternal ) | (enumLiteral_2= Public ) )
            int alt116=3;
            switch ( input.LA(1) ) {
            case Project:
                {
                alt116=1;
                }
                break;
            case PublicInternal:
                {
                alt116=2;
                }
                break;
            case Public:
                {
                alt116=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;
            }

            switch (alt116) {
                case 1 :
                    // InternalTypesParser.g:5988:2: (enumLiteral_0= Project )
                    {
                    // InternalTypesParser.g:5988:2: (enumLiteral_0= Project )
                    // InternalTypesParser.g:5988:7: enumLiteral_0= Project
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
                    // InternalTypesParser.g:5994:6: (enumLiteral_1= PublicInternal )
                    {
                    // InternalTypesParser.g:5994:6: (enumLiteral_1= PublicInternal )
                    // InternalTypesParser.g:5994:11: enumLiteral_1= PublicInternal
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
                    // InternalTypesParser.g:6000:6: (enumLiteral_2= Public )
                    {
                    // InternalTypesParser.g:6000:6: (enumLiteral_2= Public )
                    // InternalTypesParser.g:6000:11: enumLiteral_2= Public
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
    // InternalTypesParser.g:6010:1: ruleMemberAccessModifier returns [Enumerator current=null] : ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) ) ;
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
            // InternalTypesParser.g:6012:28: ( ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) ) )
            // InternalTypesParser.g:6013:1: ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) )
            {
            // InternalTypesParser.g:6013:1: ( (enumLiteral_0= Private ) | (enumLiteral_1= Project ) | (enumLiteral_2= ProtectedInternal ) | (enumLiteral_3= Protected ) | (enumLiteral_4= PublicInternal ) | (enumLiteral_5= Public ) )
            int alt117=6;
            switch ( input.LA(1) ) {
            case Private:
                {
                alt117=1;
                }
                break;
            case Project:
                {
                alt117=2;
                }
                break;
            case ProtectedInternal:
                {
                alt117=3;
                }
                break;
            case Protected:
                {
                alt117=4;
                }
                break;
            case PublicInternal:
                {
                alt117=5;
                }
                break;
            case Public:
                {
                alt117=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }

            switch (alt117) {
                case 1 :
                    // InternalTypesParser.g:6013:2: (enumLiteral_0= Private )
                    {
                    // InternalTypesParser.g:6013:2: (enumLiteral_0= Private )
                    // InternalTypesParser.g:6013:7: enumLiteral_0= Private
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
                    // InternalTypesParser.g:6019:6: (enumLiteral_1= Project )
                    {
                    // InternalTypesParser.g:6019:6: (enumLiteral_1= Project )
                    // InternalTypesParser.g:6019:11: enumLiteral_1= Project
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
                    // InternalTypesParser.g:6025:6: (enumLiteral_2= ProtectedInternal )
                    {
                    // InternalTypesParser.g:6025:6: (enumLiteral_2= ProtectedInternal )
                    // InternalTypesParser.g:6025:11: enumLiteral_2= ProtectedInternal
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
                    // InternalTypesParser.g:6031:6: (enumLiteral_3= Protected )
                    {
                    // InternalTypesParser.g:6031:6: (enumLiteral_3= Protected )
                    // InternalTypesParser.g:6031:11: enumLiteral_3= Protected
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
                    // InternalTypesParser.g:6037:6: (enumLiteral_4= PublicInternal )
                    {
                    // InternalTypesParser.g:6037:6: (enumLiteral_4= PublicInternal )
                    // InternalTypesParser.g:6037:11: enumLiteral_4= PublicInternal
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
                    // InternalTypesParser.g:6043:6: (enumLiteral_5= Public )
                    {
                    // InternalTypesParser.g:6043:6: (enumLiteral_5= Public )
                    // InternalTypesParser.g:6043:11: enumLiteral_5= Public
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
        int alt118=3;
        int LA118_0 = input.LA(1);

        if ( (LA118_0==Abstract) ) {
            alt118=1;
        }
        else if ( (LA118_0==Static) ) {
            alt118=2;
        }
        switch (alt118) {
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
        int alt119=2;
        int LA119_0 = input.LA(1);

        if ( (LA119_0==AssignmnentCompatible||(LA119_0>=AutoboxedType && LA119_0<=VirtualBase)||LA119_0==Primitive||(LA119_0>=Undefined && LA119_0<=Abstract)||(LA119_0>=Project && LA119_0<=Object)||LA119_0==Union||(LA119_0>=From && LA119_0<=Null)||(LA119_0>=Type && LA119_0<=Void)||(LA119_0>=Any && LA119_0<=Get)||(LA119_0>=Set && LA119_0<=As)||LA119_0==RULE_IDENTIFIER) ) {
            alt119=1;
        }
        else if ( (LA119_0==LeftSquareBracket) ) {
            alt119=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 119, 0, input);

            throw nvae;
        }
        switch (alt119) {
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
        int alt120=3;
        int LA120_0 = input.LA(1);

        if ( (LA120_0==Abstract) ) {
            alt120=1;
        }
        else if ( (LA120_0==Static) ) {
            alt120=2;
        }
        switch (alt120) {
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
        int alt121=2;
        int LA121_0 = input.LA(1);

        if ( (LA121_0==AssignmnentCompatible||(LA121_0>=AutoboxedType && LA121_0<=VirtualBase)||LA121_0==Primitive||(LA121_0>=Undefined && LA121_0<=Abstract)||(LA121_0>=Project && LA121_0<=Object)||LA121_0==Union||(LA121_0>=From && LA121_0<=Null)||(LA121_0>=Type && LA121_0<=Void)||(LA121_0>=Any && LA121_0<=Get)||(LA121_0>=Set && LA121_0<=As)||LA121_0==RULE_IDENTIFIER) ) {
            alt121=1;
        }
        else if ( (LA121_0==LeftSquareBracket) ) {
            alt121=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 121, 0, input);

            throw nvae;
        }
        switch (alt121) {
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
        int alt122=3;
        int LA122_0 = input.LA(1);

        if ( (LA122_0==Static) ) {
            alt122=1;
        }
        else if ( (LA122_0==Abstract) ) {
            int LA122_2 = input.LA(2);

            if ( (LA122_2==AssignmnentCompatible||(LA122_2>=AutoboxedType && LA122_2<=VirtualBase)||LA122_2==Primitive||(LA122_2>=Undefined && LA122_2<=Abstract)||(LA122_2>=Project && LA122_2<=Object)||LA122_2==Union||(LA122_2>=From && LA122_2<=Null)||(LA122_2>=Type && LA122_2<=Void)||(LA122_2>=Any && LA122_2<=Get)||(LA122_2>=Set && LA122_2<=As)||LA122_2==LessThanSign||LA122_2==LeftSquareBracket||LA122_2==RULE_IDENTIFIER) ) {
                alt122=2;
            }
        }
        switch (alt122) {
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
        int alt124=2;
        int LA124_0 = input.LA(1);

        if ( (LA124_0==LessThanSign) ) {
            alt124=1;
        }
        switch (alt124) {
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
                loop123:
                do {
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==Comma) ) {
                        alt123=1;
                    }


                    switch (alt123) {
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
                	    break loop123;
                    }
                } while (true);

                match(input,GreaterThanSign,FOLLOW_51); if (state.failed) return ;

                }
                break;

        }

        // InternalTypesParser.g:2508:3: ( ( ( ruleTypesIdentifier ) ) | ( ( ruleTypesComputedPropertyName ) ) )
        int alt125=2;
        int LA125_0 = input.LA(1);

        if ( (LA125_0==AssignmnentCompatible||(LA125_0>=AutoboxedType && LA125_0<=VirtualBase)||LA125_0==Primitive||(LA125_0>=Undefined && LA125_0<=Abstract)||(LA125_0>=Project && LA125_0<=Object)||LA125_0==Union||(LA125_0>=From && LA125_0<=Null)||(LA125_0>=Type && LA125_0<=Void)||(LA125_0>=Any && LA125_0<=Get)||(LA125_0>=Set && LA125_0<=As)||LA125_0==RULE_IDENTIFIER) ) {
            alt125=1;
        }
        else if ( (LA125_0==LeftSquareBracket) ) {
            alt125=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 125, 0, input);

            throw nvae;
        }
        switch (alt125) {
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
        // InternalTypesParser.g:3749:3: ( ( PlusSign ) )
        // InternalTypesParser.g:3750:1: ( PlusSign )
        {
        // InternalTypesParser.g:3750:1: ( PlusSign )
        // InternalTypesParser.g:3752:1: PlusSign
        {
        match(input,PlusSign,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred12_InternalTypesParser

    // $ANTLR start synpred13_InternalTypesParser
    public final void synpred13_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4334:5: ( ( ruleTIdentifier ) )
        // InternalTypesParser.g:4335:1: ( ruleTIdentifier )
        {
        // InternalTypesParser.g:4335:1: ( ruleTIdentifier )
        // InternalTypesParser.g:4336:1: ruleTIdentifier
        {
        pushFollow(FOLLOW_2);
        ruleTIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred13_InternalTypesParser

    // $ANTLR start synpred14_InternalTypesParser
    public final void synpred14_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4718:4: ( LessThanSign )
        // InternalTypesParser.g:4719:1: LessThanSign
        {
        match(input,LessThanSign,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_InternalTypesParser

    // $ANTLR start synpred15_InternalTypesParser
    public final void synpred15_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4861:3: ( ( () Get ( ( ruleTypesIdentifier ) ) ) )
        // InternalTypesParser.g:4861:4: ( () Get ( ( ruleTypesIdentifier ) ) )
        {
        // InternalTypesParser.g:4861:4: ( () Get ( ( ruleTypesIdentifier ) ) )
        // InternalTypesParser.g:4861:5: () Get ( ( ruleTypesIdentifier ) )
        {
        // InternalTypesParser.g:4861:5: ()
        // InternalTypesParser.g:4862:1: 
        {
        }

        match(input,Get,FOLLOW_11); if (state.failed) return ;
        // InternalTypesParser.g:4864:1: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:4865:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:4865:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:4866:1: ruleTypesIdentifier
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
    // $ANTLR end synpred15_InternalTypesParser

    // $ANTLR start synpred16_InternalTypesParser
    public final void synpred16_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4878:7: ( ( () Set ( ( ruleTypesIdentifier ) ) ) )
        // InternalTypesParser.g:4878:8: ( () Set ( ( ruleTypesIdentifier ) ) )
        {
        // InternalTypesParser.g:4878:8: ( () Set ( ( ruleTypesIdentifier ) ) )
        // InternalTypesParser.g:4878:9: () Set ( ( ruleTypesIdentifier ) )
        {
        // InternalTypesParser.g:4878:9: ()
        // InternalTypesParser.g:4879:1: 
        {
        }

        match(input,Set,FOLLOW_11); if (state.failed) return ;
        // InternalTypesParser.g:4881:1: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:4882:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:4882:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:4883:1: ruleTypesIdentifier
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
    // $ANTLR end synpred16_InternalTypesParser

    // $ANTLR start synpred17_InternalTypesParser
    public final void synpred17_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:4895:7: ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )
        // InternalTypesParser.g:4895:8: ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis )
        {
        // InternalTypesParser.g:4895:8: ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis )
        // InternalTypesParser.g:4895:9: () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis
        {
        // InternalTypesParser.g:4895:9: ()
        // InternalTypesParser.g:4896:1: 
        {
        }

        // InternalTypesParser.g:4896:2: ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )?
        int alt135=2;
        int LA135_0 = input.LA(1);

        if ( (LA135_0==LessThanSign) ) {
            alt135=1;
        }
        switch (alt135) {
            case 1 :
                // InternalTypesParser.g:4897:1: LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign
                {
                match(input,LessThanSign,FOLLOW_4); if (state.failed) return ;
                // InternalTypesParser.g:4898:1: ( ( ruleTypeVariable ) )
                // InternalTypesParser.g:4899:1: ( ruleTypeVariable )
                {
                // InternalTypesParser.g:4899:1: ( ruleTypeVariable )
                // InternalTypesParser.g:4900:1: ruleTypeVariable
                {
                pushFollow(FOLLOW_28);
                ruleTypeVariable();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalTypesParser.g:4902:2: ( Comma ( ( ruleTypeVariable ) ) )*
                loop134:
                do {
                    int alt134=2;
                    int LA134_0 = input.LA(1);

                    if ( (LA134_0==Comma) ) {
                        alt134=1;
                    }


                    switch (alt134) {
                	case 1 :
                	    // InternalTypesParser.g:4903:1: Comma ( ( ruleTypeVariable ) )
                	    {
                	    match(input,Comma,FOLLOW_4); if (state.failed) return ;
                	    // InternalTypesParser.g:4904:1: ( ( ruleTypeVariable ) )
                	    // InternalTypesParser.g:4905:1: ( ruleTypeVariable )
                	    {
                	    // InternalTypesParser.g:4905:1: ( ruleTypeVariable )
                	    // InternalTypesParser.g:4906:1: ruleTypeVariable
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
                	    break loop134;
                    }
                } while (true);

                match(input,GreaterThanSign,FOLLOW_11); if (state.failed) return ;

                }
                break;

        }

        // InternalTypesParser.g:4910:3: ( ( ruleTypesIdentifier ) )
        // InternalTypesParser.g:4911:1: ( ruleTypesIdentifier )
        {
        // InternalTypesParser.g:4911:1: ( ruleTypesIdentifier )
        // InternalTypesParser.g:4912:1: ruleTypesIdentifier
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
    // $ANTLR end synpred17_InternalTypesParser

    // $ANTLR start synpred21_InternalTypesParser
    public final void synpred21_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5371:4: ( LessThanSign )
        // InternalTypesParser.g:5372:1: LessThanSign
        {
        match(input,LessThanSign,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred21_InternalTypesParser

    // $ANTLR start synpred22_InternalTypesParser
    public final void synpred22_InternalTypesParser_fragment() throws RecognitionException {   
        // InternalTypesParser.g:5635:3: ( ( () QuestionMark ) )
        // InternalTypesParser.g:5635:4: ( () QuestionMark )
        {
        // InternalTypesParser.g:5635:4: ( () QuestionMark )
        // InternalTypesParser.g:5635:5: () QuestionMark
        {
        // InternalTypesParser.g:5635:5: ()
        // InternalTypesParser.g:5636:1: 
        {
        }

        match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred22_InternalTypesParser

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
    public final boolean synpred22_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_InternalTypesParser_fragment(); // can never throw exception
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
    public final boolean synpred21_InternalTypesParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_InternalTypesParser_fragment(); // can never throw exception
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


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA97 dfa97 = new DFA97(this);
    protected DFA101 dfa101 = new DFA101(this);
    protected DFA108 dfa108 = new DFA108(this);
    static final String dfa_1s = "\22\uffff";
    static final String dfa_2s = "\1\7\3\6\7\uffff\1\15\1\uffff\1\31\4\uffff";
    static final String dfa_3s = "\1\112\3\42\7\uffff\1\42\1\uffff\1\34\4\uffff";
    static final String dfa_4s = "\4\uffff\1\5\1\6\1\7\1\10\1\11\1\13\1\14\1\uffff\1\4\1\uffff\1\1\1\12\1\2\1\3";
    static final String dfa_5s = "\22\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\3\uffff\1\12\2\uffff\1\10\1\uffff\1\6\7\uffff\1\1\1\uffff\1\3\11\uffff\1\7\2\uffff\1\5\2\uffff\1\4\37\uffff\1\11",
            "\1\13\6\uffff\1\21\3\uffff\1\20\1\17\6\uffff\1\16\2\uffff\1\20\1\uffff\1\15\3\uffff\1\14",
            "\1\13\6\uffff\1\21\3\uffff\1\20\1\17\6\uffff\1\16\2\uffff\1\20\1\uffff\1\15\3\uffff\1\14",
            "\1\13\6\uffff\1\21\3\uffff\1\20\1\17\6\uffff\1\16\2\uffff\1\20\1\uffff\1\15\3\uffff\1\14",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\21\3\uffff\1\20\1\17\6\uffff\1\16\2\uffff\1\20\1\uffff\1\15\3\uffff\1\14",
            "",
            "\1\16\2\uffff\1\20",
            "",
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
    static final String dfa_8s = "\1\5\10\4\1\uffff\1\62\2\4\20\62\1\4\1\uffff\1\62\1\4\1\uffff\1\4\75\uffff\24\66\1\77\1\4\1\62\24\77";
    static final String dfa_9s = "\1\32\10\112\1\uffff\1\70\2\112\20\70\1\112\1\uffff\1\70\1\112\1\uffff\1\112\75\uffff\24\66\1\77\1\112\1\70\24\77";
    static final String dfa_10s = "\11\uffff\1\3\24\uffff\1\4\2\uffff\1\3\1\uffff\23\3\25\1\25\2\53\uffff";
    static final String dfa_11s = "\1\uffff\1\35\1\3\1\7\1\13\1\20\1\26\1\25\1\23\1\uffff\1\33\1\2\1\10\1\34\1\36\1\0\1\4\1\5\1\6\1\11\1\12\1\14\1\16\1\17\1\21\1\24\1\27\1\30\1\31\2\uffff\1\32\1\15\1\uffff\1\22\123\uffff\1\1\24\uffff}>";
    static final String[] dfa_12s = {
            "\1\3\1\uffff\1\5\7\uffff\1\4\7\uffff\1\1\1\2\1\uffff\1\6",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\10\6\uffff\1\16\1\30\1\uffff\1\7\1\uffff\2\36\1\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\34\3\uffff\1\33\1\20\1\27\1\31\2\uffff\1\32\1\uffff\1\26\1\37\6\uffff\1\16\1\30\6\uffff\1\17\2\uffff\1\22\1\25\1\uffff\1\23\1\24\2\uffff\1\15\1\13\1\uffff\1\14\1\21\13\uffff\1\11\3\uffff\1\35\13\uffff\1\12",
            "\1\64\3\uffff\1\63\1\50\1\57\1\61\2\uffff\1\62\1\uffff\1\56\1\44\6\uffff\1\46\1\60\6\uffff\1\47\2\uffff\1\52\1\55\1\uffff\1\53\1\54\2\uffff\1\45\1\40\1\uffff\1\42\1\51\3\uffff\1\41\5\uffff\1\36\1\uffff\1\11\3\uffff\1\65\13\uffff\1\43",
            "",
            "\1\41\5\uffff\1\36",
            "\1\111\3\uffff\1\110\1\75\1\104\1\106\2\uffff\1\107\1\uffff\1\103\1\71\6\uffff\1\73\1\105\6\uffff\1\74\2\uffff\1\77\1\102\1\uffff\1\100\1\101\2\uffff\1\72\1\67\1\uffff\1\70\1\76\3\uffff\1\41\5\uffff\1\36\5\uffff\1\112\13\uffff\1\66",
            "\1\136\3\uffff\1\135\1\122\1\131\1\133\2\uffff\1\134\1\uffff\1\130\1\116\6\uffff\1\120\1\132\6\uffff\1\121\2\uffff\1\124\1\127\1\uffff\1\125\1\126\2\uffff\1\117\1\114\1\uffff\1\115\1\123\3\uffff\1\41\5\uffff\1\36\5\uffff\1\137\13\uffff\1\113",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\41\5\uffff\1\36",
            "\1\163\3\uffff\1\162\1\147\1\156\1\160\2\uffff\1\161\1\uffff\1\155\1\143\6\uffff\1\145\1\157\6\uffff\1\146\2\uffff\1\151\1\154\1\uffff\1\152\1\153\2\uffff\1\144\1\141\1\uffff\1\142\1\150\25\uffff\1\164\5\uffff\1\140",
            "",
            "\1\41\5\uffff\1\36",
            "\1\111\3\uffff\1\110\1\75\1\104\1\106\2\uffff\1\107\1\uffff\1\103\1\71\6\uffff\1\73\1\105\6\uffff\1\74\2\uffff\1\77\1\102\1\uffff\1\100\1\101\2\uffff\1\72\1\67\1\uffff\1\70\1\76\3\uffff\1\41\13\uffff\1\112\13\uffff\1\66",
            "",
            "\1\136\3\uffff\1\135\1\122\1\131\1\133\2\uffff\1\134\1\uffff\1\130\1\116\6\uffff\1\120\1\132\6\uffff\1\121\2\uffff\1\124\1\127\1\uffff\1\125\1\126\2\uffff\1\117\1\114\1\uffff\1\115\1\123\3\uffff\1\41\13\uffff\1\137\13\uffff\1\113",
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
            "\1\u008a\3\uffff\1\u0089\1\176\1\u0085\1\u0087\2\uffff\1\u0088\1\uffff\1\u0084\1\172\6\uffff\1\174\1\u0086\6\uffff\1\175\2\uffff\1\u0080\1\u0083\1\uffff\1\u0081\1\u0082\2\uffff\1\173\1\170\1\uffff\1\171\1\177\33\uffff\1\167",
            "\1\41\5\uffff\1\36",
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
                        int LA55_15 = input.LA(1);

                         
                        int index55_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_15==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_15==Colon) ) {s = 30;}

                         
                        input.seek(index55_15);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA55_118 = input.LA(1);

                         
                        int index55_118 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_118==Colon) ) {s = 30;}

                        else if ( (LA55_118==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_118);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA55_11 = input.LA(1);

                         
                        int index55_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_11==RULE_IDENTIFIER) && (synpred6_InternalTypesParser())) {s = 54;}

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

                        else if ( (LA55_11==Colon) ) {s = 30;}

                        else if ( (LA55_11==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_11);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA55_2 = input.LA(1);

                         
                        int index55_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_2==Static) ) {s = 7;}

                        else if ( ((LA55_2>=Const && LA55_2<=Final)) ) {s = 30;}

                        else if ( (LA55_2==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_2==Get) ) {s = 11;}

                        else if ( (LA55_2==Set) ) {s = 12;}

                        else if ( (LA55_2==Abstract) ) {s = 8;}

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

                        else if ( (LA55_2==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                         
                        input.seek(index55_2);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA55_16 = input.LA(1);

                         
                        int index55_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_16==Colon) ) {s = 30;}

                        else if ( (LA55_16==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_16);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA55_17 = input.LA(1);

                         
                        int index55_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_17==Colon) ) {s = 30;}

                        else if ( (LA55_17==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_17);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA55_18 = input.LA(1);

                         
                        int index55_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_18==Colon) ) {s = 30;}

                        else if ( (LA55_18==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_18);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA55_3 = input.LA(1);

                         
                        int index55_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_3==Static) ) {s = 7;}

                        else if ( (LA55_3==Abstract) ) {s = 8;}

                        else if ( (LA55_3==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_3==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_3==Get) ) {s = 11;}

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
                    case 8 : 
                        int LA55_12 = input.LA(1);

                         
                        int index55_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_12==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

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

                        else if ( (LA55_12==Colon) ) {s = 30;}

                         
                        input.seek(index55_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA55_19 = input.LA(1);

                         
                        int index55_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_19==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_19==Colon) ) {s = 30;}

                         
                        input.seek(index55_19);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA55_20 = input.LA(1);

                         
                        int index55_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_20==Colon) ) {s = 30;}

                        else if ( (LA55_20==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_20);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA55_4 = input.LA(1);

                         
                        int index55_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_4==Static) ) {s = 7;}

                        else if ( (LA55_4==Abstract) ) {s = 8;}

                        else if ( (LA55_4==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_4==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_4==Get) ) {s = 11;}

                        else if ( (LA55_4==Set) ) {s = 12;}

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

                        else if ( ((LA55_4>=Const && LA55_4<=Final)) ) {s = 30;}

                         
                        input.seek(index55_4);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA55_21 = input.LA(1);

                         
                        int index55_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_21==Colon) ) {s = 30;}

                        else if ( (LA55_21==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_21);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
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

                        else if ( (LA55_32==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_32);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA55_22 = input.LA(1);

                         
                        int index55_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_22==Colon) ) {s = 30;}

                        else if ( (LA55_22==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_22);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA55_23 = input.LA(1);

                         
                        int index55_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_23==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_23==Colon) ) {s = 30;}

                         
                        input.seek(index55_23);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA55_5 = input.LA(1);

                         
                        int index55_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_5==Abstract) ) {s = 8;}

                        else if ( (LA55_5==Static) ) {s = 7;}

                        else if ( (LA55_5==Get) ) {s = 11;}

                        else if ( (LA55_5==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_5==RULE_IDENTIFIER) ) {s = 10;}

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
                    case 17 : 
                        int LA55_24 = input.LA(1);

                         
                        int index55_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_24==Colon) ) {s = 30;}

                        else if ( (LA55_24==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_24);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA55_34 = input.LA(1);

                         
                        int index55_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_34==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_34==RULE_IDENTIFIER) && (synpred7_InternalTypesParser())) {s = 75;}

                        else if ( (LA55_34==Get) && (synpred7_InternalTypesParser())) {s = 76;}

                        else if ( (LA55_34==Set) && (synpred7_InternalTypesParser())) {s = 77;}

                        else if ( (LA55_34==Abstract) && (synpred7_InternalTypesParser())) {s = 78;}

                        else if ( (LA55_34==Any) && (synpred7_InternalTypesParser())) {s = 79;}

                        else if ( (LA55_34==Project) && (synpred7_InternalTypesParser())) {s = 80;}

                        else if ( (LA55_34==Union) && (synpred7_InternalTypesParser())) {s = 81;}

                        else if ( (LA55_34==Intersection) && (synpred7_InternalTypesParser())) {s = 82;}

                        else if ( (LA55_34==As) && (synpred7_InternalTypesParser())) {s = 83;}

                        else if ( (LA55_34==From) && (synpred7_InternalTypesParser())) {s = 84;}

                        else if ( (LA55_34==Type) && (synpred7_InternalTypesParser())) {s = 85;}

                        else if ( (LA55_34==Void) && (synpred7_InternalTypesParser())) {s = 86;}

                        else if ( (LA55_34==Null) && (synpred7_InternalTypesParser())) {s = 87;}

                        else if ( (LA55_34==Undefined) && (synpred7_InternalTypesParser())) {s = 88;}

                        else if ( (LA55_34==Constructor) && (synpred7_InternalTypesParser())) {s = 89;}

                        else if ( (LA55_34==Object) && (synpred7_InternalTypesParser())) {s = 90;}

                        else if ( (LA55_34==VirtualBase) && (synpred7_InternalTypesParser())) {s = 91;}

                        else if ( (LA55_34==Primitive) && (synpred7_InternalTypesParser())) {s = 92;}

                        else if ( (LA55_34==AutoboxedType) && (synpred7_InternalTypesParser())) {s = 93;}

                        else if ( (LA55_34==AssignmnentCompatible) && (synpred7_InternalTypesParser())) {s = 94;}

                        else if ( (LA55_34==LeftSquareBracket) && (synpred7_InternalTypesParser())) {s = 95;}

                         
                        input.seek(index55_34);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA55_8 = input.LA(1);

                         
                        int index55_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_8==Get) ) {s = 32;}

                        else if ( (LA55_8==Colon) ) {s = 30;}

                        else if ( (LA55_8==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_8==Set) ) {s = 34;}

                        else if ( (LA55_8==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                        else if ( (LA55_8==RULE_IDENTIFIER) && (synpred8_InternalTypesParser())) {s = 35;}

                        else if ( (LA55_8==Abstract) && (synpred8_InternalTypesParser())) {s = 36;}

                        else if ( (LA55_8==Any) && (synpred8_InternalTypesParser())) {s = 37;}

                        else if ( (LA55_8==Project) && (synpred8_InternalTypesParser())) {s = 38;}

                        else if ( (LA55_8==Union) && (synpred8_InternalTypesParser())) {s = 39;}

                        else if ( (LA55_8==Intersection) && (synpred8_InternalTypesParser())) {s = 40;}

                        else if ( (LA55_8==As) && (synpred8_InternalTypesParser())) {s = 41;}

                        else if ( (LA55_8==From) && (synpred8_InternalTypesParser())) {s = 42;}

                        else if ( (LA55_8==Type) && (synpred8_InternalTypesParser())) {s = 43;}

                        else if ( (LA55_8==Void) && (synpred8_InternalTypesParser())) {s = 44;}

                        else if ( (LA55_8==Null) && (synpred8_InternalTypesParser())) {s = 45;}

                        else if ( (LA55_8==Undefined) && (synpred8_InternalTypesParser())) {s = 46;}

                        else if ( (LA55_8==Constructor) && (synpred8_InternalTypesParser())) {s = 47;}

                        else if ( (LA55_8==Object) && (synpred8_InternalTypesParser())) {s = 48;}

                        else if ( (LA55_8==VirtualBase) && (synpred8_InternalTypesParser())) {s = 49;}

                        else if ( (LA55_8==Primitive) && (synpred8_InternalTypesParser())) {s = 50;}

                        else if ( (LA55_8==AutoboxedType) && (synpred8_InternalTypesParser())) {s = 51;}

                        else if ( (LA55_8==AssignmnentCompatible) && (synpred8_InternalTypesParser())) {s = 52;}

                        else if ( (LA55_8==LeftSquareBracket) && (synpred8_InternalTypesParser())) {s = 53;}

                         
                        input.seek(index55_8);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA55_25 = input.LA(1);

                         
                        int index55_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_25==Colon) ) {s = 30;}

                        else if ( (LA55_25==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_25);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
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
                    case 22 : 
                        int LA55_6 = input.LA(1);

                         
                        int index55_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_6==Abstract) ) {s = 8;}

                        else if ( (LA55_6==Static) ) {s = 7;}

                        else if ( (LA55_6==Set) ) {s = 12;}

                        else if ( ((LA55_6>=Const && LA55_6<=Final)) ) {s = 30;}

                        else if ( (LA55_6==RULE_IDENTIFIER) ) {s = 10;}

                        else if ( (LA55_6==Get) ) {s = 11;}

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

                        else if ( (LA55_6==LessThanSign) && (synpred8_InternalTypesParser())) {s = 9;}

                         
                        input.seek(index55_6);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA55_26 = input.LA(1);

                         
                        int index55_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_26==Colon) ) {s = 30;}

                        else if ( (LA55_26==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_26);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA55_27 = input.LA(1);

                         
                        int index55_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_27==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                        else if ( (LA55_27==Colon) ) {s = 30;}

                         
                        input.seek(index55_27);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA55_28 = input.LA(1);

                         
                        int index55_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_28==Colon) ) {s = 30;}

                        else if ( (LA55_28==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_28);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA55_31 = input.LA(1);

                         
                        int index55_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_31==Colon) ) {s = 30;}

                        else if ( (LA55_31==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_31);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA55_10 = input.LA(1);

                         
                        int index55_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_10==Colon) ) {s = 30;}

                        else if ( (LA55_10==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_10);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA55_13 = input.LA(1);

                         
                        int index55_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_13==Colon) ) {s = 30;}

                        else if ( (LA55_13==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_13);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
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
                    case 30 : 
                        int LA55_14 = input.LA(1);

                         
                        int index55_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA55_14==Colon) ) {s = 30;}

                        else if ( (LA55_14==LeftParenthesis) && (synpred8_InternalTypesParser())) {s = 33;}

                         
                        input.seek(index55_14);
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
    static final String dfa_13s = "\35\uffff";
    static final String dfa_14s = "\1\uffff\1\33\3\uffff\1\33\6\uffff\3\33\16\uffff";
    static final String dfa_15s = "\1\4\1\23\3\uffff\1\23\1\uffff\2\70\2\uffff\1\70\3\23\1\70\15\uffff";
    static final String dfa_16s = "\1\112\1\74\3\uffff\1\74\1\uffff\2\100\2\uffff\1\100\3\74\1\100\15\uffff";
    static final String dfa_17s = "\2\uffff\3\1\1\uffff\1\1\2\uffff\2\1\5\uffff\13\1\1\2\1\1";
    static final String dfa_18s = "\1\3\1\5\3\uffff\1\6\1\uffff\1\4\1\0\2\uffff\1\2\1\1\1\10\1\11\1\7\15\uffff}>";
    static final String[] dfa_19s = {
            "\1\24\3\uffff\1\23\1\10\1\17\1\21\1\25\1\26\1\22\1\30\1\16\1\4\3\uffff\1\33\1\uffff\1\27\1\6\1\20\1\31\1\32\4\uffff\1\7\2\uffff\1\12\1\15\1\33\1\13\1\14\2\uffff\1\5\1\2\1\uffff\1\3\1\11\21\uffff\1\33\1\uffff\1\33\7\uffff\1\1",
            "\1\33\2\uffff\1\33\31\uffff\1\33\2\uffff\3\33\1\uffff\1\33\1\34\1\uffff\1\33\1\uffff\1\33",
            "",
            "",
            "",
            "\1\33\2\uffff\1\33\31\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "",
            "\1\34\7\uffff\1\33",
            "\1\34\7\uffff\1\33",
            "",
            "",
            "\1\34\7\uffff\1\33",
            "\1\33\2\uffff\1\33\31\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "\1\33\2\uffff\1\33\31\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
            "\1\33\2\uffff\1\33\31\uffff\1\33\2\uffff\3\33\2\uffff\1\34\1\uffff\1\33\1\uffff\1\33",
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

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = dfa_13;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "4334:3: ( ( ( ( ruleTIdentifier ) )=> (lv_name_1_0= ruleTIdentifier ) ) otherlv_2= Colon )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA91_8 = input.LA(1);

                         
                        int index91_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_8==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_8==LeftCurlyBracket) ) {s = 27;}

                         
                        input.seek(index91_8);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA91_12 = input.LA(1);

                         
                        int index91_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_12==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_12==EOF||LA91_12==Nullable||LA91_12==Notnull||LA91_12==ExclamationMark||(LA91_12>=RightParenthesis && LA91_12<=Comma)||LA91_12==LessThanSign||LA91_12==QuestionMark) ) {s = 27;}

                         
                        input.seek(index91_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA91_11 = input.LA(1);

                         
                        int index91_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_11==LeftCurlyBracket) ) {s = 27;}

                        else if ( (LA91_11==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index91_11);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA91_0 = input.LA(1);

                         
                        int index91_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_0==RULE_IDENTIFIER) ) {s = 1;}

                        else if ( (LA91_0==Get) && (synpred13_InternalTypesParser())) {s = 2;}

                        else if ( (LA91_0==Set) && (synpred13_InternalTypesParser())) {s = 3;}

                        else if ( (LA91_0==Abstract) && (synpred13_InternalTypesParser())) {s = 4;}

                        else if ( (LA91_0==Any) ) {s = 5;}

                        else if ( (LA91_0==Project) && (synpred13_InternalTypesParser())) {s = 6;}

                        else if ( (LA91_0==Union) ) {s = 7;}

                        else if ( (LA91_0==Intersection) ) {s = 8;}

                        else if ( (LA91_0==As) && (synpred13_InternalTypesParser())) {s = 9;}

                        else if ( (LA91_0==From) && (synpred13_InternalTypesParser())) {s = 10;}

                        else if ( (LA91_0==Type) ) {s = 11;}

                        else if ( (LA91_0==Void) ) {s = 12;}

                        else if ( (LA91_0==Null) ) {s = 13;}

                        else if ( (LA91_0==Undefined) ) {s = 14;}

                        else if ( (LA91_0==Constructor) ) {s = 15;}

                        else if ( (LA91_0==Object) && (synpred13_InternalTypesParser())) {s = 16;}

                        else if ( (LA91_0==VirtualBase) && (synpred13_InternalTypesParser())) {s = 17;}

                        else if ( (LA91_0==Primitive) && (synpred13_InternalTypesParser())) {s = 18;}

                        else if ( (LA91_0==AutoboxedType) && (synpred13_InternalTypesParser())) {s = 19;}

                        else if ( (LA91_0==AssignmnentCompatible) && (synpred13_InternalTypesParser())) {s = 20;}

                        else if ( (LA91_0==Implements) && (synpred13_InternalTypesParser())) {s = 21;}

                        else if ( (LA91_0==Interface) && (synpred13_InternalTypesParser())) {s = 22;}

                        else if ( (LA91_0==Private) && (synpred13_InternalTypesParser())) {s = 23;}

                        else if ( (LA91_0==Protected) && (synpred13_InternalTypesParser())) {s = 24;}

                        else if ( (LA91_0==Public) && (synpred13_InternalTypesParser())) {s = 25;}

                        else if ( (LA91_0==Static) && (synpred13_InternalTypesParser())) {s = 26;}

                        else if ( (LA91_0==Indexed||LA91_0==This_1||LA91_0==LeftCurlyBracket||LA91_0==Tilde) ) {s = 27;}

                         
                        input.seek(index91_0);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA91_7 = input.LA(1);

                         
                        int index91_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_7==LeftCurlyBracket) ) {s = 27;}

                        else if ( (LA91_7==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index91_7);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA91_1 = input.LA(1);

                         
                        int index91_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_1==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_1==EOF||LA91_1==Nullable||LA91_1==Notnull||LA91_1==ExclamationMark||(LA91_1>=RightParenthesis && LA91_1<=Comma)||LA91_1==Solidus||LA91_1==LessThanSign||LA91_1==QuestionMark) ) {s = 27;}

                         
                        input.seek(index91_1);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA91_5 = input.LA(1);

                         
                        int index91_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_5==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_5==EOF||LA91_5==Nullable||LA91_5==Notnull||LA91_5==ExclamationMark||(LA91_5>=RightParenthesis && LA91_5<=Comma)||LA91_5==LessThanSign||LA91_5==QuestionMark) ) {s = 27;}

                         
                        input.seek(index91_5);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA91_15 = input.LA(1);

                         
                        int index91_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_15==LeftCurlyBracket) ) {s = 27;}

                        else if ( (LA91_15==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                         
                        input.seek(index91_15);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA91_13 = input.LA(1);

                         
                        int index91_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_13==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_13==EOF||LA91_13==Nullable||LA91_13==Notnull||LA91_13==ExclamationMark||(LA91_13>=RightParenthesis && LA91_13<=Comma)||LA91_13==LessThanSign||LA91_13==QuestionMark) ) {s = 27;}

                         
                        input.seek(index91_13);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA91_14 = input.LA(1);

                         
                        int index91_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA91_14==Colon) && (synpred13_InternalTypesParser())) {s = 28;}

                        else if ( (LA91_14==EOF||LA91_14==Nullable||LA91_14==Notnull||LA91_14==ExclamationMark||(LA91_14>=RightParenthesis && LA91_14<=Comma)||LA91_14==LessThanSign||LA91_14==QuestionMark) ) {s = 27;}

                         
                        input.seek(index91_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 91, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_20s = "\63\uffff";
    static final String dfa_21s = "\1\2\62\uffff";
    static final String dfa_22s = "\1\4\1\11\1\uffff\1\23\17\uffff\1\11\1\0\7\uffff\1\23\26\uffff";
    static final String dfa_23s = "\2\112\1\uffff\1\74\17\uffff\1\112\1\0\7\uffff\1\74\26\uffff";
    static final String dfa_24s = "\2\uffff\1\2\1\uffff\17\1\2\uffff\7\1\1\uffff\26\1";
    static final String dfa_25s = "\1\uffff\1\2\1\uffff\1\4\17\uffff\1\0\1\1\7\uffff\1\3\26\uffff}>";
    static final String[] dfa_26s = {
            "\2\2\1\uffff\5\2\2\uffff\4\2\1\uffff\1\2\2\uffff\5\2\5\uffff\1\2\2\uffff\2\2\1\uffff\3\2\1\uffff\2\2\1\uffff\2\2\1\uffff\6\2\3\uffff\1\2\1\1\2\2\4\uffff\1\2\10\uffff\1\2",
            "\1\22\1\16\5\uffff\1\11\4\uffff\1\13\12\uffff\1\21\3\uffff\1\12\1\15\1\17\1\7\2\uffff\1\10\1\uffff\1\5\2\uffff\1\6\14\uffff\1\4\3\uffff\1\20\1\uffff\1\14\7\uffff\1\3",
            "",
            "\1\33\1\2\1\uffff\1\32\31\uffff\1\31\3\uffff\1\27\1\23\1\uffff\1\25\2\uffff\1\26\1\24\1\30",
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
            "\1\53\1\47\5\uffff\1\42\4\uffff\1\44\12\uffff\1\52\3\uffff\1\43\1\46\1\50\1\40\2\uffff\1\41\1\uffff\1\36\2\uffff\1\37\14\uffff\1\35\3\uffff\1\51\1\uffff\1\45\7\uffff\1\34",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\2\1\uffff\1\61\31\uffff\1\60\3\uffff\1\56\1\23\1\uffff\1\54\2\uffff\1\55\1\24\1\57",
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

    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final short[] dfa_21 = DFA.unpackEncodedString(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final char[] dfa_23 = DFA.unpackEncodedStringToUnsignedChars(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[] dfa_25 = DFA.unpackEncodedString(dfa_25s);
    static final short[][] dfa_26 = unpackEncodedStringArray(dfa_26s);

    class DFA97 extends DFA {

        public DFA97(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 97;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_26;
        }
        public String getDescription() {
            return "4718:2: ( ( ( LessThanSign )=>otherlv_2= LessThanSign ) ( (lv_typeArgs_3_0= ruleTypeArgument ) ) (otherlv_4= Comma ( (lv_typeArgs_5_0= ruleTypeArgument ) ) )* otherlv_6= GreaterThanSign )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA97_19 = input.LA(1);

                         
                        int index97_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA97_19==RULE_IDENTIFIER) ) {s = 28;}

                        else if ( (LA97_19==QuestionMark) && (synpred14_InternalTypesParser())) {s = 29;}

                        else if ( (LA97_19==Out) && (synpred14_InternalTypesParser())) {s = 30;}

                        else if ( (LA97_19==In) && (synpred14_InternalTypesParser())) {s = 31;}

                        else if ( (LA97_19==Void) && (synpred14_InternalTypesParser())) {s = 32;}

                        else if ( (LA97_19==Any) && (synpred14_InternalTypesParser())) {s = 33;}

                        else if ( (LA97_19==Undefined) && (synpred14_InternalTypesParser())) {s = 34;}

                        else if ( (LA97_19==Null) && (synpred14_InternalTypesParser())) {s = 35;}

                        else if ( (LA97_19==Indexed) && (synpred14_InternalTypesParser())) {s = 36;}

                        else if ( (LA97_19==Tilde) && (synpred14_InternalTypesParser())) {s = 37;}

                        else if ( (LA97_19==This_1) && (synpred14_InternalTypesParser())) {s = 38;}

                        else if ( (LA97_19==Constructor) && (synpred14_InternalTypesParser())) {s = 39;}

                        else if ( (LA97_19==Type) && (synpred14_InternalTypesParser())) {s = 40;}

                        else if ( (LA97_19==LeftCurlyBracket) && (synpred14_InternalTypesParser())) {s = 41;}

                        else if ( (LA97_19==Union) && (synpred14_InternalTypesParser())) {s = 42;}

                        else if ( (LA97_19==Intersection) && (synpred14_InternalTypesParser())) {s = 43;}

                         
                        input.seek(index97_19);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA97_20 = input.LA(1);

                         
                        int index97_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_InternalTypesParser()) ) {s = 43;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index97_20);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA97_1 = input.LA(1);

                         
                        int index97_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA97_1==RULE_IDENTIFIER) ) {s = 3;}

                        else if ( (LA97_1==QuestionMark) && (synpred14_InternalTypesParser())) {s = 4;}

                        else if ( (LA97_1==Out) && (synpred14_InternalTypesParser())) {s = 5;}

                        else if ( (LA97_1==In) && (synpred14_InternalTypesParser())) {s = 6;}

                        else if ( (LA97_1==Void) && (synpred14_InternalTypesParser())) {s = 7;}

                        else if ( (LA97_1==Any) && (synpred14_InternalTypesParser())) {s = 8;}

                        else if ( (LA97_1==Undefined) && (synpred14_InternalTypesParser())) {s = 9;}

                        else if ( (LA97_1==Null) && (synpred14_InternalTypesParser())) {s = 10;}

                        else if ( (LA97_1==Indexed) && (synpred14_InternalTypesParser())) {s = 11;}

                        else if ( (LA97_1==Tilde) && (synpred14_InternalTypesParser())) {s = 12;}

                        else if ( (LA97_1==This_1) && (synpred14_InternalTypesParser())) {s = 13;}

                        else if ( (LA97_1==Constructor) && (synpred14_InternalTypesParser())) {s = 14;}

                        else if ( (LA97_1==Type) && (synpred14_InternalTypesParser())) {s = 15;}

                        else if ( (LA97_1==LeftCurlyBracket) && (synpred14_InternalTypesParser())) {s = 16;}

                        else if ( (LA97_1==Union) && (synpred14_InternalTypesParser())) {s = 17;}

                        else if ( (LA97_1==Intersection) && (synpred14_InternalTypesParser())) {s = 18;}

                         
                        input.seek(index97_1);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA97_28 = input.LA(1);

                         
                        int index97_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA97_28==Solidus) && (synpred14_InternalTypesParser())) {s = 44;}

                        else if ( (LA97_28==LessThanSign) && (synpred14_InternalTypesParser())) {s = 45;}

                        else if ( (LA97_28==PlusSign) && (synpred14_InternalTypesParser())) {s = 46;}

                        else if ( (LA97_28==QuestionMark) && (synpred14_InternalTypesParser())) {s = 47;}

                        else if ( (LA97_28==ExclamationMark) && (synpred14_InternalTypesParser())) {s = 48;}

                        else if ( (LA97_28==Notnull) && (synpred14_InternalTypesParser())) {s = 49;}

                        else if ( (LA97_28==Nullable) && (synpred14_InternalTypesParser())) {s = 50;}

                        else if ( (LA97_28==GreaterThanSign) ) {s = 20;}

                        else if ( (LA97_28==Comma) ) {s = 19;}

                        else if ( (LA97_28==Extends) ) {s = 2;}

                         
                        input.seek(index97_28);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA97_3 = input.LA(1);

                         
                        int index97_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA97_3==Extends) ) {s = 2;}

                        else if ( (LA97_3==Comma) ) {s = 19;}

                        else if ( (LA97_3==GreaterThanSign) ) {s = 20;}

                        else if ( (LA97_3==Solidus) && (synpred14_InternalTypesParser())) {s = 21;}

                        else if ( (LA97_3==LessThanSign) && (synpred14_InternalTypesParser())) {s = 22;}

                        else if ( (LA97_3==PlusSign) && (synpred14_InternalTypesParser())) {s = 23;}

                        else if ( (LA97_3==QuestionMark) && (synpred14_InternalTypesParser())) {s = 24;}

                        else if ( (LA97_3==ExclamationMark) && (synpred14_InternalTypesParser())) {s = 25;}

                        else if ( (LA97_3==Notnull) && (synpred14_InternalTypesParser())) {s = 26;}

                        else if ( (LA97_3==Nullable) && (synpred14_InternalTypesParser())) {s = 27;}

                         
                        input.seek(index97_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 97, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_27s = "\31\uffff";
    static final String dfa_28s = "\1\4\2\0\1\uffff\22\0\3\uffff";
    static final String dfa_29s = "\1\112\2\0\1\uffff\22\0\3\uffff";
    static final String dfa_30s = "\3\uffff\1\3\22\uffff\1\1\1\4\1\2";
    static final String dfa_31s = "\1\0\1\1\1\2\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\3\uffff}>";
    static final String[] dfa_32s = {
            "\1\25\3\uffff\1\24\1\11\1\20\1\22\2\uffff\1\23\1\uffff\1\17\1\5\6\uffff\1\7\1\21\6\uffff\1\10\2\uffff\1\13\1\16\1\uffff\1\14\1\15\2\uffff\1\6\1\1\1\uffff\1\2\1\12\13\uffff\1\3\17\uffff\1\4",
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

    static final short[] dfa_27 = DFA.unpackEncodedString(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final char[] dfa_29 = DFA.unpackEncodedStringToUnsignedChars(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[] dfa_31 = DFA.unpackEncodedString(dfa_31s);
    static final short[][] dfa_32 = unpackEncodedStringArray(dfa_32s);

    class DFA101 extends DFA {

        public DFA101(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 101;
            this.eot = dfa_27;
            this.eof = dfa_27;
            this.min = dfa_28;
            this.max = dfa_29;
            this.accept = dfa_30;
            this.special = dfa_31;
            this.transition = dfa_32;
        }
        public String getDescription() {
            return "4861:1: ( ( ( ( () Get ( ( ruleTypesIdentifier ) ) ) )=>this_TStructGetter_0= ruleTStructGetter ) | ( ( ( () Set ( ( ruleTypesIdentifier ) ) ) )=>this_TStructSetter_1= ruleTStructSetter ) | ( ( ( () ( LessThanSign ( ( ruleTypeVariable ) ) ( Comma ( ( ruleTypeVariable ) ) )* GreaterThanSign )? ( ( ruleTypesIdentifier ) ) LeftParenthesis ) )=>this_TStructMethod_2= ruleTStructMethod ) | this_TStructField_3= ruleTStructField )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA101_0 = input.LA(1);

                         
                        int index101_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA101_0==Get) ) {s = 1;}

                        else if ( (LA101_0==Set) ) {s = 2;}

                        else if ( (LA101_0==LessThanSign) && (synpred17_InternalTypesParser())) {s = 3;}

                        else if ( (LA101_0==RULE_IDENTIFIER) ) {s = 4;}

                        else if ( (LA101_0==Abstract) ) {s = 5;}

                        else if ( (LA101_0==Any) ) {s = 6;}

                        else if ( (LA101_0==Project) ) {s = 7;}

                        else if ( (LA101_0==Union) ) {s = 8;}

                        else if ( (LA101_0==Intersection) ) {s = 9;}

                        else if ( (LA101_0==As) ) {s = 10;}

                        else if ( (LA101_0==From) ) {s = 11;}

                        else if ( (LA101_0==Type) ) {s = 12;}

                        else if ( (LA101_0==Void) ) {s = 13;}

                        else if ( (LA101_0==Null) ) {s = 14;}

                        else if ( (LA101_0==Undefined) ) {s = 15;}

                        else if ( (LA101_0==Constructor) ) {s = 16;}

                        else if ( (LA101_0==Object) ) {s = 17;}

                        else if ( (LA101_0==VirtualBase) ) {s = 18;}

                        else if ( (LA101_0==Primitive) ) {s = 19;}

                        else if ( (LA101_0==AutoboxedType) ) {s = 20;}

                        else if ( (LA101_0==AssignmnentCompatible) ) {s = 21;}

                         
                        input.seek(index101_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA101_1 = input.LA(1);

                         
                        int index101_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred15_InternalTypesParser()) ) {s = 22;}

                        else if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA101_2 = input.LA(1);

                         
                        int index101_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalTypesParser()) ) {s = 24;}

                        else if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA101_4 = input.LA(1);

                         
                        int index101_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA101_5 = input.LA(1);

                         
                        int index101_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA101_6 = input.LA(1);

                         
                        int index101_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA101_7 = input.LA(1);

                         
                        int index101_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA101_8 = input.LA(1);

                         
                        int index101_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA101_9 = input.LA(1);

                         
                        int index101_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA101_10 = input.LA(1);

                         
                        int index101_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA101_11 = input.LA(1);

                         
                        int index101_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA101_12 = input.LA(1);

                         
                        int index101_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA101_13 = input.LA(1);

                         
                        int index101_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA101_14 = input.LA(1);

                         
                        int index101_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA101_15 = input.LA(1);

                         
                        int index101_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA101_16 = input.LA(1);

                         
                        int index101_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA101_17 = input.LA(1);

                         
                        int index101_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA101_18 = input.LA(1);

                         
                        int index101_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA101_19 = input.LA(1);

                         
                        int index101_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA101_20 = input.LA(1);

                         
                        int index101_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA101_21 = input.LA(1);

                         
                        int index101_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalTypesParser()) ) {s = 3;}

                        else if ( (true) ) {s = 23;}

                         
                        input.seek(index101_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 101, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_33s = "\1\uffff\1\4\1\uffff\1\1\17\uffff\1\3\1\2\7\uffff\1\0\26\uffff}>";
    static final String[] dfa_34s = {
            "\2\2\1\uffff\6\2\1\uffff\4\2\1\uffff\1\2\1\uffff\6\2\5\uffff\1\2\2\uffff\2\2\1\uffff\2\2\2\uffff\2\2\1\uffff\2\2\1\uffff\6\2\3\uffff\1\2\1\1\3\2\2\uffff\2\2\10\uffff\1\2",
            "\1\22\1\16\5\uffff\1\11\4\uffff\1\13\12\uffff\1\21\3\uffff\1\12\1\15\1\17\1\7\2\uffff\1\10\1\uffff\1\5\2\uffff\1\6\14\uffff\1\4\3\uffff\1\20\1\uffff\1\14\7\uffff\1\3",
            "",
            "\1\33\1\2\1\uffff\1\32\31\uffff\1\31\3\uffff\1\27\1\23\1\uffff\1\25\2\uffff\1\26\1\24\1\30",
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
            "\1\53\1\47\5\uffff\1\42\4\uffff\1\44\12\uffff\1\52\3\uffff\1\43\1\46\1\50\1\40\2\uffff\1\41\1\uffff\1\36\2\uffff\1\37\14\uffff\1\35\3\uffff\1\51\1\uffff\1\45\7\uffff\1\34",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\2\1\uffff\1\61\31\uffff\1\60\3\uffff\1\56\1\23\1\uffff\1\54\2\uffff\1\55\1\24\1\57",
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
    static final short[] dfa_33 = DFA.unpackEncodedString(dfa_33s);
    static final short[][] dfa_34 = unpackEncodedStringArray(dfa_34s);

    class DFA108 extends DFA {

        public DFA108(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 108;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_33;
            this.transition = dfa_34;
        }
        public String getDescription() {
            return "5371:2: ( ( ( LessThanSign )=>otherlv_1= LessThanSign ) ( (lv_typeArgs_2_0= ruleTypeArgument ) ) (otherlv_3= Comma ( (lv_typeArgs_4_0= ruleTypeArgument ) ) )* otherlv_5= GreaterThanSign )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA108_28 = input.LA(1);

                         
                        int index108_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_28==Solidus) && (synpred21_InternalTypesParser())) {s = 44;}

                        else if ( (LA108_28==LessThanSign) && (synpred21_InternalTypesParser())) {s = 45;}

                        else if ( (LA108_28==PlusSign) && (synpred21_InternalTypesParser())) {s = 46;}

                        else if ( (LA108_28==QuestionMark) && (synpred21_InternalTypesParser())) {s = 47;}

                        else if ( (LA108_28==ExclamationMark) && (synpred21_InternalTypesParser())) {s = 48;}

                        else if ( (LA108_28==Notnull) && (synpred21_InternalTypesParser())) {s = 49;}

                        else if ( (LA108_28==Nullable) && (synpred21_InternalTypesParser())) {s = 50;}

                        else if ( (LA108_28==GreaterThanSign) ) {s = 20;}

                        else if ( (LA108_28==Comma) ) {s = 19;}

                        else if ( (LA108_28==Extends) ) {s = 2;}

                         
                        input.seek(index108_28);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA108_3 = input.LA(1);

                         
                        int index108_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_3==Extends) ) {s = 2;}

                        else if ( (LA108_3==Comma) ) {s = 19;}

                        else if ( (LA108_3==GreaterThanSign) ) {s = 20;}

                        else if ( (LA108_3==Solidus) && (synpred21_InternalTypesParser())) {s = 21;}

                        else if ( (LA108_3==LessThanSign) && (synpred21_InternalTypesParser())) {s = 22;}

                        else if ( (LA108_3==PlusSign) && (synpred21_InternalTypesParser())) {s = 23;}

                        else if ( (LA108_3==QuestionMark) && (synpred21_InternalTypesParser())) {s = 24;}

                        else if ( (LA108_3==ExclamationMark) && (synpred21_InternalTypesParser())) {s = 25;}

                        else if ( (LA108_3==Notnull) && (synpred21_InternalTypesParser())) {s = 26;}

                        else if ( (LA108_3==Nullable) && (synpred21_InternalTypesParser())) {s = 27;}

                         
                        input.seek(index108_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA108_20 = input.LA(1);

                         
                        int index108_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred21_InternalTypesParser()) ) {s = 43;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index108_20);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA108_19 = input.LA(1);

                         
                        int index108_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_19==RULE_IDENTIFIER) ) {s = 28;}

                        else if ( (LA108_19==QuestionMark) && (synpred21_InternalTypesParser())) {s = 29;}

                        else if ( (LA108_19==Out) && (synpred21_InternalTypesParser())) {s = 30;}

                        else if ( (LA108_19==In) && (synpred21_InternalTypesParser())) {s = 31;}

                        else if ( (LA108_19==Void) && (synpred21_InternalTypesParser())) {s = 32;}

                        else if ( (LA108_19==Any) && (synpred21_InternalTypesParser())) {s = 33;}

                        else if ( (LA108_19==Undefined) && (synpred21_InternalTypesParser())) {s = 34;}

                        else if ( (LA108_19==Null) && (synpred21_InternalTypesParser())) {s = 35;}

                        else if ( (LA108_19==Indexed) && (synpred21_InternalTypesParser())) {s = 36;}

                        else if ( (LA108_19==Tilde) && (synpred21_InternalTypesParser())) {s = 37;}

                        else if ( (LA108_19==This_1) && (synpred21_InternalTypesParser())) {s = 38;}

                        else if ( (LA108_19==Constructor) && (synpred21_InternalTypesParser())) {s = 39;}

                        else if ( (LA108_19==Type) && (synpred21_InternalTypesParser())) {s = 40;}

                        else if ( (LA108_19==LeftCurlyBracket) && (synpred21_InternalTypesParser())) {s = 41;}

                        else if ( (LA108_19==Union) && (synpred21_InternalTypesParser())) {s = 42;}

                        else if ( (LA108_19==Intersection) && (synpred21_InternalTypesParser())) {s = 43;}

                         
                        input.seek(index108_19);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA108_1 = input.LA(1);

                         
                        int index108_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_1==RULE_IDENTIFIER) ) {s = 3;}

                        else if ( (LA108_1==QuestionMark) && (synpred21_InternalTypesParser())) {s = 4;}

                        else if ( (LA108_1==Out) && (synpred21_InternalTypesParser())) {s = 5;}

                        else if ( (LA108_1==In) && (synpred21_InternalTypesParser())) {s = 6;}

                        else if ( (LA108_1==Void) && (synpred21_InternalTypesParser())) {s = 7;}

                        else if ( (LA108_1==Any) && (synpred21_InternalTypesParser())) {s = 8;}

                        else if ( (LA108_1==Undefined) && (synpred21_InternalTypesParser())) {s = 9;}

                        else if ( (LA108_1==Null) && (synpred21_InternalTypesParser())) {s = 10;}

                        else if ( (LA108_1==Indexed) && (synpred21_InternalTypesParser())) {s = 11;}

                        else if ( (LA108_1==Tilde) && (synpred21_InternalTypesParser())) {s = 12;}

                        else if ( (LA108_1==This_1) && (synpred21_InternalTypesParser())) {s = 13;}

                        else if ( (LA108_1==Constructor) && (synpred21_InternalTypesParser())) {s = 14;}

                        else if ( (LA108_1==Type) && (synpred21_InternalTypesParser())) {s = 15;}

                        else if ( (LA108_1==LeftCurlyBracket) && (synpred21_InternalTypesParser())) {s = 16;}

                        else if ( (LA108_1==Union) && (synpred21_InternalTypesParser())) {s = 17;}

                        else if ( (LA108_1==Intersection) && (synpred21_InternalTypesParser())) {s = 18;}

                         
                        input.seek(index108_1);
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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000049005014882L,0x0000000000000400L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x000804F100210600L,0x0000000000000415L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0028000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000004F100210600L,0x0000000000000415L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x1001000000480002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000480002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00006CD903034F10L,0x0000000000000400L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0400000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000049000210000L,0x0000000000000400L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000110L,0x0000000000000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00006CD903034F10L,0x0000000000000410L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000042000040L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000042000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x2400000000300000L,0x0000000000000001L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0820000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x2000000000300000L,0x0000000000000001L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x2000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x2000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00040000058080A0L,0x0000000000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00000000058080A0L,0x0000000000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000050020040L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000050020000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00006CD903034F10L,0x0000000000000404L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x2400000000101000L,0x0000000000000001L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x2000000000101000L,0x0000000000000001L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x2000000000001000L,0x0000000000000001L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x2020000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x2400000000100000L,0x0000000000000001L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x2000000000100000L,0x0000000000000001L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x00086ED90F83FF10L,0x0000000000000400L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x00006ED90F83FF10L,0x0000000000000400L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x44006CD90B034F10L,0x0000000000000400L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x44006CD96B034F10L,0x0000000000000400L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000080008020000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000200008020000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000040040L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x04006ED90F83FF10L,0x0000000000000400L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000400000040L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x2000000000040000L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0404000000000000L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x00086EF90FA3FF10L,0x0000000000000415L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0100000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x00006EF90FA3FF10L,0x0000000000000415L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0400010000000002L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x100094F100210600L,0x0000000000000415L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x04006CD903034F10L,0x0000000000000402L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x06206CD903034F10L,0x0000000000000402L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000084L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x000004B000210000L,0x0000000000000400L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000080100002L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000049000210000L,0x0000000000000404L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0002000000000002L});

}