package eu.numberfour.n4js.regex.ui.contentassist.antlr.internal;
import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import eu.numberfour.n4js.regex.services.RegularExpressionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalRegularExpressionParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ExclamationMark", "DollarSign", "LeftParenthesis", "RightParenthesis", "Asterisk", "PlusSign", "Comma", "HyphenMinus", "FullStop", "Solidus", "Colon", "EqualsSign", "QuestionMark", "LeftSquareBracket", "RightSquareBracket", "CircumflexAccent", "LeftCurlyBracket", "VerticalLine", "RightCurlyBracket", "RULE_WORD_BOUNDARY", "RULE_NOT_WORD_BOUNDARY", "RULE_CHARACTER_CLASS_ESCAPE", "RULE_CONTROL_ESCAPE", "RULE_CONTROL_LETTER_ESCAPE", "RULE_HEX_DIGIT", "RULE_HEX_ESCAPE", "RULE_UNICODE_ESCAPE", "RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT", "RULE_DECIMAL_ESCAPE", "RULE_IDENTITY_ESCAPE", "RULE_UNICODE_DIGIT_FRAGMENT", "RULE_UNICODE_DIGIT", "RULE_UNICODE_LETTER_FRAGMENT", "RULE_UNICODE_LETTER", "RULE_PATTERN_CHARACTER_NO_DASH", "RULE_DECIMAL_DIGIT_FRAGMENT", "RULE_ZWJ", "RULE_ZWNJ", "RULE_BOM", "RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT", "RULE_WHITESPACE_FRAGMENT", "RULE_LINE_TERMINATOR_FRAGMENT", "RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT", "RULE_SL_COMMENT_FRAGMENT", "RULE_ML_COMMENT_FRAGMENT", "RULE_UNICODE_COMBINING_MARK_FRAGMENT", "RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT", "RULE_ANY_OTHER"
    };
    public static final int RULE_WHITESPACE_FRAGMENT=44;
    public static final int LeftParenthesis=6;
    public static final int RULE_HEX_ESCAPE=29;
    public static final int RightSquareBracket=18;
    public static final int ExclamationMark=4;
    public static final int RULE_CONTROL_LETTER_ESCAPE=27;
    public static final int RULE_LINE_TERMINATOR_SEQUENCE_FRAGMENT=46;
    public static final int RightParenthesis=7;
    public static final int RULE_UNICODE_COMBINING_MARK_FRAGMENT=49;
    public static final int RULE_ZWNJ=41;
    public static final int VerticalLine=21;
    public static final int PlusSign=9;
    public static final int LeftSquareBracket=17;
    public static final int RULE_DECIMAL_ESCAPE=32;
    public static final int RULE_ML_COMMENT_FRAGMENT=48;
    public static final int RULE_PATTERN_CHARACTER_NO_DASH=38;
    public static final int RULE_WORD_BOUNDARY=23;
    public static final int RULE_UNICODE_ESCAPE=30;
    public static final int Comma=10;
    public static final int EqualsSign=15;
    public static final int RULE_ZWJ=40;
    public static final int RULE_SL_COMMENT_FRAGMENT=47;
    public static final int HyphenMinus=11;
    public static final int RULE_UNICODE_DIGIT_FRAGMENT=34;
    public static final int RULE_UNICODE_LETTER=37;
    public static final int RULE_CONTROL_ESCAPE=26;
    public static final int RULE_UNICODE_SPACE_SEPARATOR_FRAGMENT=43;
    public static final int Solidus=13;
    public static final int Colon=14;
    public static final int RightCurlyBracket=22;
    public static final int RULE_CHARACTER_CLASS_ESCAPE=25;
    public static final int EOF=-1;
    public static final int Asterisk=8;
    public static final int FullStop=12;
    public static final int RULE_UNICODE_DIGIT=35;
    public static final int RULE_BOM=42;
    public static final int LeftCurlyBracket=20;
    public static final int RULE_ANY_OTHER=51;
    public static final int CircumflexAccent=19;
    public static final int RULE_NOT_WORD_BOUNDARY=24;
    public static final int RULE_LINE_TERMINATOR_FRAGMENT=45;
    public static final int RULE_UNICODE_LETTER_FRAGMENT=36;
    public static final int RULE_DECIMAL_DIGIT_FRAGMENT=39;
    public static final int QuestionMark=16;
    public static final int DollarSign=5;
    public static final int RULE_HEX_DIGIT=28;
    public static final int RULE_DECIMAL_INTEGER_LITERAL_FRAGMENT=31;
    public static final int RULE_IDENTITY_ESCAPE=33;
    public static final int RULE_UNICODE_CONNECTOR_PUNCTUATION_FRAGMENT=50;

    // delegates
    // delegators


        public InternalRegularExpressionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalRegularExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalRegularExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "InternalRegularExpressionParser.g"; }


    	private RegularExpressionGrammarAccess grammarAccess;
    	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
    	
    	{
    		tokenNameToValue.put("ExclamationMark", "'!'");
    		tokenNameToValue.put("DollarSign", "'\\u0024'");
    		tokenNameToValue.put("LeftParenthesis", "'('");
    		tokenNameToValue.put("RightParenthesis", "')'");
    		tokenNameToValue.put("Asterisk", "'*'");
    		tokenNameToValue.put("PlusSign", "'+'");
    		tokenNameToValue.put("Comma", "','");
    		tokenNameToValue.put("HyphenMinus", "'-'");
    		tokenNameToValue.put("FullStop", "'.'");
    		tokenNameToValue.put("Solidus", "'/'");
    		tokenNameToValue.put("Colon", "':'");
    		tokenNameToValue.put("EqualsSign", "'='");
    		tokenNameToValue.put("QuestionMark", "'?'");
    		tokenNameToValue.put("LeftSquareBracket", "'['");
    		tokenNameToValue.put("RightSquareBracket", "']'");
    		tokenNameToValue.put("CircumflexAccent", "'^'");
    		tokenNameToValue.put("LeftCurlyBracket", "'{'");
    		tokenNameToValue.put("VerticalLine", "'|'");
    		tokenNameToValue.put("RightCurlyBracket", "'}'");
    	}

    	public void setGrammarAccess(RegularExpressionGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		String result = tokenNameToValue.get(tokenName);
    		if (result == null)
    			result = tokenName;
    		return result;
    	}



    // $ANTLR start "entryRuleRegularExpressionLiteral"
    // InternalRegularExpressionParser.g:75:1: entryRuleRegularExpressionLiteral : ruleRegularExpressionLiteral EOF ;
    public final void entryRuleRegularExpressionLiteral() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:76:1: ( ruleRegularExpressionLiteral EOF )
            // InternalRegularExpressionParser.g:77:1: ruleRegularExpressionLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleRegularExpressionLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRegularExpressionLiteral"


    // $ANTLR start "ruleRegularExpressionLiteral"
    // InternalRegularExpressionParser.g:84:1: ruleRegularExpressionLiteral : ( ( rule__RegularExpressionLiteral__Group__0 ) ) ;
    public final void ruleRegularExpressionLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:88:2: ( ( ( rule__RegularExpressionLiteral__Group__0 ) ) )
            // InternalRegularExpressionParser.g:89:2: ( ( rule__RegularExpressionLiteral__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:89:2: ( ( rule__RegularExpressionLiteral__Group__0 ) )
            // InternalRegularExpressionParser.g:90:3: ( rule__RegularExpressionLiteral__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:91:3: ( rule__RegularExpressionLiteral__Group__0 )
            // InternalRegularExpressionParser.g:91:4: rule__RegularExpressionLiteral__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRegularExpressionLiteral"


    // $ANTLR start "entryRuleRegularExpressionBody"
    // InternalRegularExpressionParser.g:100:1: entryRuleRegularExpressionBody : ruleRegularExpressionBody EOF ;
    public final void entryRuleRegularExpressionBody() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:101:1: ( ruleRegularExpressionBody EOF )
            // InternalRegularExpressionParser.g:102:1: ruleRegularExpressionBody EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionBodyRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleRegularExpressionBody();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionBodyRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRegularExpressionBody"


    // $ANTLR start "ruleRegularExpressionBody"
    // InternalRegularExpressionParser.g:109:1: ruleRegularExpressionBody : ( ( rule__RegularExpressionBody__PatternAssignment ) ) ;
    public final void ruleRegularExpressionBody() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:113:2: ( ( ( rule__RegularExpressionBody__PatternAssignment ) ) )
            // InternalRegularExpressionParser.g:114:2: ( ( rule__RegularExpressionBody__PatternAssignment ) )
            {
            // InternalRegularExpressionParser.g:114:2: ( ( rule__RegularExpressionBody__PatternAssignment ) )
            // InternalRegularExpressionParser.g:115:3: ( rule__RegularExpressionBody__PatternAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionBodyAccess().getPatternAssignment()); 
            }
            // InternalRegularExpressionParser.g:116:3: ( rule__RegularExpressionBody__PatternAssignment )
            // InternalRegularExpressionParser.g:116:4: rule__RegularExpressionBody__PatternAssignment
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionBody__PatternAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionBodyAccess().getPatternAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRegularExpressionBody"


    // $ANTLR start "entryRuleDisjunction"
    // InternalRegularExpressionParser.g:125:1: entryRuleDisjunction : ruleDisjunction EOF ;
    public final void entryRuleDisjunction() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:126:1: ( ruleDisjunction EOF )
            // InternalRegularExpressionParser.g:127:1: ruleDisjunction EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDisjunction"


    // $ANTLR start "ruleDisjunction"
    // InternalRegularExpressionParser.g:134:1: ruleDisjunction : ( ( rule__Disjunction__Alternatives ) ) ;
    public final void ruleDisjunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:138:2: ( ( ( rule__Disjunction__Alternatives ) ) )
            // InternalRegularExpressionParser.g:139:2: ( ( rule__Disjunction__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:139:2: ( ( rule__Disjunction__Alternatives ) )
            // InternalRegularExpressionParser.g:140:3: ( rule__Disjunction__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:141:3: ( rule__Disjunction__Alternatives )
            // InternalRegularExpressionParser.g:141:4: rule__Disjunction__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDisjunction"


    // $ANTLR start "entryRuleAlternative"
    // InternalRegularExpressionParser.g:150:1: entryRuleAlternative : ruleAlternative EOF ;
    public final void entryRuleAlternative() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:151:1: ( ruleAlternative EOF )
            // InternalRegularExpressionParser.g:152:1: ruleAlternative EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAlternative();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAlternative"


    // $ANTLR start "ruleAlternative"
    // InternalRegularExpressionParser.g:159:1: ruleAlternative : ( ( rule__Alternative__Group__0 ) ) ;
    public final void ruleAlternative() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:163:2: ( ( ( rule__Alternative__Group__0 ) ) )
            // InternalRegularExpressionParser.g:164:2: ( ( rule__Alternative__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:164:2: ( ( rule__Alternative__Group__0 ) )
            // InternalRegularExpressionParser.g:165:3: ( rule__Alternative__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:166:3: ( rule__Alternative__Group__0 )
            // InternalRegularExpressionParser.g:166:4: rule__Alternative__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Alternative__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAlternative"


    // $ANTLR start "entryRuleTerm"
    // InternalRegularExpressionParser.g:175:1: entryRuleTerm : ruleTerm EOF ;
    public final void entryRuleTerm() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:176:1: ( ruleTerm EOF )
            // InternalRegularExpressionParser.g:177:1: ruleTerm EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTermRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleTerm();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTermRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTerm"


    // $ANTLR start "ruleTerm"
    // InternalRegularExpressionParser.g:184:1: ruleTerm : ( ( rule__Term__Alternatives ) ) ;
    public final void ruleTerm() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:188:2: ( ( ( rule__Term__Alternatives ) ) )
            // InternalRegularExpressionParser.g:189:2: ( ( rule__Term__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:189:2: ( ( rule__Term__Alternatives ) )
            // InternalRegularExpressionParser.g:190:3: ( rule__Term__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTermAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:191:3: ( rule__Term__Alternatives )
            // InternalRegularExpressionParser.g:191:4: rule__Term__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Term__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTermAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTerm"


    // $ANTLR start "entryRuleAssertion"
    // InternalRegularExpressionParser.g:200:1: entryRuleAssertion : ruleAssertion EOF ;
    public final void entryRuleAssertion() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:201:1: ( ruleAssertion EOF )
            // InternalRegularExpressionParser.g:202:1: ruleAssertion EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssertionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAssertion();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssertionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAssertion"


    // $ANTLR start "ruleAssertion"
    // InternalRegularExpressionParser.g:209:1: ruleAssertion : ( ( rule__Assertion__Alternatives ) ) ;
    public final void ruleAssertion() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:213:2: ( ( ( rule__Assertion__Alternatives ) ) )
            // InternalRegularExpressionParser.g:214:2: ( ( rule__Assertion__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:214:2: ( ( rule__Assertion__Alternatives ) )
            // InternalRegularExpressionParser.g:215:3: ( rule__Assertion__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssertionAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:216:3: ( rule__Assertion__Alternatives )
            // InternalRegularExpressionParser.g:216:4: rule__Assertion__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Assertion__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssertionAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssertion"


    // $ANTLR start "entryRuleLineStart"
    // InternalRegularExpressionParser.g:225:1: entryRuleLineStart : ruleLineStart EOF ;
    public final void entryRuleLineStart() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:226:1: ( ruleLineStart EOF )
            // InternalRegularExpressionParser.g:227:1: ruleLineStart EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineStartRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLineStart();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineStartRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLineStart"


    // $ANTLR start "ruleLineStart"
    // InternalRegularExpressionParser.g:234:1: ruleLineStart : ( ( rule__LineStart__Group__0 ) ) ;
    public final void ruleLineStart() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:238:2: ( ( ( rule__LineStart__Group__0 ) ) )
            // InternalRegularExpressionParser.g:239:2: ( ( rule__LineStart__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:239:2: ( ( rule__LineStart__Group__0 ) )
            // InternalRegularExpressionParser.g:240:3: ( rule__LineStart__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineStartAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:241:3: ( rule__LineStart__Group__0 )
            // InternalRegularExpressionParser.g:241:4: rule__LineStart__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__LineStart__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineStartAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLineStart"


    // $ANTLR start "entryRuleLineEnd"
    // InternalRegularExpressionParser.g:250:1: entryRuleLineEnd : ruleLineEnd EOF ;
    public final void entryRuleLineEnd() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:251:1: ( ruleLineEnd EOF )
            // InternalRegularExpressionParser.g:252:1: ruleLineEnd EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineEndRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLineEnd();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineEndRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLineEnd"


    // $ANTLR start "ruleLineEnd"
    // InternalRegularExpressionParser.g:259:1: ruleLineEnd : ( ( rule__LineEnd__Group__0 ) ) ;
    public final void ruleLineEnd() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:263:2: ( ( ( rule__LineEnd__Group__0 ) ) )
            // InternalRegularExpressionParser.g:264:2: ( ( rule__LineEnd__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:264:2: ( ( rule__LineEnd__Group__0 ) )
            // InternalRegularExpressionParser.g:265:3: ( rule__LineEnd__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineEndAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:266:3: ( rule__LineEnd__Group__0 )
            // InternalRegularExpressionParser.g:266:4: rule__LineEnd__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__LineEnd__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineEndAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLineEnd"


    // $ANTLR start "entryRuleWordBoundary"
    // InternalRegularExpressionParser.g:275:1: entryRuleWordBoundary : ruleWordBoundary EOF ;
    public final void entryRuleWordBoundary() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:276:1: ( ruleWordBoundary EOF )
            // InternalRegularExpressionParser.g:277:1: ruleWordBoundary EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWordBoundaryRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleWordBoundary();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWordBoundaryRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWordBoundary"


    // $ANTLR start "ruleWordBoundary"
    // InternalRegularExpressionParser.g:284:1: ruleWordBoundary : ( ( rule__WordBoundary__Group__0 ) ) ;
    public final void ruleWordBoundary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:288:2: ( ( ( rule__WordBoundary__Group__0 ) ) )
            // InternalRegularExpressionParser.g:289:2: ( ( rule__WordBoundary__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:289:2: ( ( rule__WordBoundary__Group__0 ) )
            // InternalRegularExpressionParser.g:290:3: ( rule__WordBoundary__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWordBoundaryAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:291:3: ( rule__WordBoundary__Group__0 )
            // InternalRegularExpressionParser.g:291:4: rule__WordBoundary__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__WordBoundary__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWordBoundaryAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWordBoundary"


    // $ANTLR start "entryRuleLookAhead"
    // InternalRegularExpressionParser.g:300:1: entryRuleLookAhead : ruleLookAhead EOF ;
    public final void entryRuleLookAhead() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:301:1: ( ruleLookAhead EOF )
            // InternalRegularExpressionParser.g:302:1: ruleLookAhead EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLookAhead();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLookAhead"


    // $ANTLR start "ruleLookAhead"
    // InternalRegularExpressionParser.g:309:1: ruleLookAhead : ( ( rule__LookAhead__Group__0 ) ) ;
    public final void ruleLookAhead() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:313:2: ( ( ( rule__LookAhead__Group__0 ) ) )
            // InternalRegularExpressionParser.g:314:2: ( ( rule__LookAhead__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:314:2: ( ( rule__LookAhead__Group__0 ) )
            // InternalRegularExpressionParser.g:315:3: ( rule__LookAhead__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:316:3: ( rule__LookAhead__Group__0 )
            // InternalRegularExpressionParser.g:316:4: rule__LookAhead__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLookAhead"


    // $ANTLR start "entryRuleAtom"
    // InternalRegularExpressionParser.g:325:1: entryRuleAtom : ruleAtom EOF ;
    public final void entryRuleAtom() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:326:1: ( ruleAtom EOF )
            // InternalRegularExpressionParser.g:327:1: ruleAtom EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAtomRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAtomRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAtom"


    // $ANTLR start "ruleAtom"
    // InternalRegularExpressionParser.g:334:1: ruleAtom : ( ( rule__Atom__Alternatives ) ) ;
    public final void ruleAtom() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:338:2: ( ( ( rule__Atom__Alternatives ) ) )
            // InternalRegularExpressionParser.g:339:2: ( ( rule__Atom__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:339:2: ( ( rule__Atom__Alternatives ) )
            // InternalRegularExpressionParser.g:340:3: ( rule__Atom__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAtomAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:341:3: ( rule__Atom__Alternatives )
            // InternalRegularExpressionParser.g:341:4: rule__Atom__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Atom__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAtomAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAtom"


    // $ANTLR start "entryRulePatternCharacter"
    // InternalRegularExpressionParser.g:350:1: entryRulePatternCharacter : rulePatternCharacter EOF ;
    public final void entryRulePatternCharacter() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:351:1: ( rulePatternCharacter EOF )
            // InternalRegularExpressionParser.g:352:1: rulePatternCharacter EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPatternCharacterRule()); 
            }
            pushFollow(FOLLOW_1);
            rulePatternCharacter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPatternCharacterRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePatternCharacter"


    // $ANTLR start "rulePatternCharacter"
    // InternalRegularExpressionParser.g:359:1: rulePatternCharacter : ( ( rule__PatternCharacter__ValueAssignment ) ) ;
    public final void rulePatternCharacter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:363:2: ( ( ( rule__PatternCharacter__ValueAssignment ) ) )
            // InternalRegularExpressionParser.g:364:2: ( ( rule__PatternCharacter__ValueAssignment ) )
            {
            // InternalRegularExpressionParser.g:364:2: ( ( rule__PatternCharacter__ValueAssignment ) )
            // InternalRegularExpressionParser.g:365:3: ( rule__PatternCharacter__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPatternCharacterAccess().getValueAssignment()); 
            }
            // InternalRegularExpressionParser.g:366:3: ( rule__PatternCharacter__ValueAssignment )
            // InternalRegularExpressionParser.g:366:4: rule__PatternCharacter__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__PatternCharacter__ValueAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPatternCharacterAccess().getValueAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePatternCharacter"


    // $ANTLR start "entryRuleWildcard"
    // InternalRegularExpressionParser.g:375:1: entryRuleWildcard : ruleWildcard EOF ;
    public final void entryRuleWildcard() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:376:1: ( ruleWildcard EOF )
            // InternalRegularExpressionParser.g:377:1: ruleWildcard EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleWildcard();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWildcard"


    // $ANTLR start "ruleWildcard"
    // InternalRegularExpressionParser.g:384:1: ruleWildcard : ( ( rule__Wildcard__Group__0 ) ) ;
    public final void ruleWildcard() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:388:2: ( ( ( rule__Wildcard__Group__0 ) ) )
            // InternalRegularExpressionParser.g:389:2: ( ( rule__Wildcard__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:389:2: ( ( rule__Wildcard__Group__0 ) )
            // InternalRegularExpressionParser.g:390:3: ( rule__Wildcard__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:391:3: ( rule__Wildcard__Group__0 )
            // InternalRegularExpressionParser.g:391:4: rule__Wildcard__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Wildcard__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWildcard"


    // $ANTLR start "entryRuleAtomEscape"
    // InternalRegularExpressionParser.g:400:1: entryRuleAtomEscape : ruleAtomEscape EOF ;
    public final void entryRuleAtomEscape() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:401:1: ( ruleAtomEscape EOF )
            // InternalRegularExpressionParser.g:402:1: ruleAtomEscape EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAtomEscapeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleAtomEscape();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAtomEscapeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAtomEscape"


    // $ANTLR start "ruleAtomEscape"
    // InternalRegularExpressionParser.g:409:1: ruleAtomEscape : ( ( rule__AtomEscape__Alternatives ) ) ;
    public final void ruleAtomEscape() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:413:2: ( ( ( rule__AtomEscape__Alternatives ) ) )
            // InternalRegularExpressionParser.g:414:2: ( ( rule__AtomEscape__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:414:2: ( ( rule__AtomEscape__Alternatives ) )
            // InternalRegularExpressionParser.g:415:3: ( rule__AtomEscape__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAtomEscapeAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:416:3: ( rule__AtomEscape__Alternatives )
            // InternalRegularExpressionParser.g:416:4: rule__AtomEscape__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__AtomEscape__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAtomEscapeAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAtomEscape"


    // $ANTLR start "entryRuleCharacterClassEscapeSequence"
    // InternalRegularExpressionParser.g:425:1: entryRuleCharacterClassEscapeSequence : ruleCharacterClassEscapeSequence EOF ;
    public final void entryRuleCharacterClassEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:426:1: ( ruleCharacterClassEscapeSequence EOF )
            // InternalRegularExpressionParser.g:427:1: ruleCharacterClassEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCharacterClassEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacterClassEscapeSequence"


    // $ANTLR start "ruleCharacterClassEscapeSequence"
    // InternalRegularExpressionParser.g:434:1: ruleCharacterClassEscapeSequence : ( ( rule__CharacterClassEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleCharacterClassEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:438:2: ( ( ( rule__CharacterClassEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:439:2: ( ( rule__CharacterClassEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:439:2: ( ( rule__CharacterClassEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:440:3: ( rule__CharacterClassEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:441:3: ( rule__CharacterClassEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:441:4: rule__CharacterClassEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacterClassEscapeSequence"


    // $ANTLR start "entryRuleCharacterEscapeSequence"
    // InternalRegularExpressionParser.g:450:1: entryRuleCharacterEscapeSequence : ruleCharacterEscapeSequence EOF ;
    public final void entryRuleCharacterEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:451:1: ( ruleCharacterEscapeSequence EOF )
            // InternalRegularExpressionParser.g:452:1: ruleCharacterEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCharacterEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacterEscapeSequence"


    // $ANTLR start "ruleCharacterEscapeSequence"
    // InternalRegularExpressionParser.g:459:1: ruleCharacterEscapeSequence : ( ( rule__CharacterEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleCharacterEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:463:2: ( ( ( rule__CharacterEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:464:2: ( ( rule__CharacterEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:464:2: ( ( rule__CharacterEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:465:3: ( rule__CharacterEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:466:3: ( rule__CharacterEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:466:4: rule__CharacterEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__CharacterEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacterEscapeSequence"


    // $ANTLR start "entryRuleControlLetterEscapeSequence"
    // InternalRegularExpressionParser.g:475:1: entryRuleControlLetterEscapeSequence : ruleControlLetterEscapeSequence EOF ;
    public final void entryRuleControlLetterEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:476:1: ( ruleControlLetterEscapeSequence EOF )
            // InternalRegularExpressionParser.g:477:1: ruleControlLetterEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getControlLetterEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleControlLetterEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getControlLetterEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleControlLetterEscapeSequence"


    // $ANTLR start "ruleControlLetterEscapeSequence"
    // InternalRegularExpressionParser.g:484:1: ruleControlLetterEscapeSequence : ( ( rule__ControlLetterEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleControlLetterEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:488:2: ( ( ( rule__ControlLetterEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:489:2: ( ( rule__ControlLetterEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:489:2: ( ( rule__ControlLetterEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:490:3: ( rule__ControlLetterEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getControlLetterEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:491:3: ( rule__ControlLetterEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:491:4: rule__ControlLetterEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__ControlLetterEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getControlLetterEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleControlLetterEscapeSequence"


    // $ANTLR start "entryRuleHexEscapeSequence"
    // InternalRegularExpressionParser.g:500:1: entryRuleHexEscapeSequence : ruleHexEscapeSequence EOF ;
    public final void entryRuleHexEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:501:1: ( ruleHexEscapeSequence EOF )
            // InternalRegularExpressionParser.g:502:1: ruleHexEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHexEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleHexEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHexEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleHexEscapeSequence"


    // $ANTLR start "ruleHexEscapeSequence"
    // InternalRegularExpressionParser.g:509:1: ruleHexEscapeSequence : ( ( rule__HexEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleHexEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:513:2: ( ( ( rule__HexEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:514:2: ( ( rule__HexEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:514:2: ( ( rule__HexEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:515:3: ( rule__HexEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHexEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:516:3: ( rule__HexEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:516:4: rule__HexEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__HexEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHexEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleHexEscapeSequence"


    // $ANTLR start "entryRuleUnicodeEscapeSequence"
    // InternalRegularExpressionParser.g:525:1: entryRuleUnicodeEscapeSequence : ruleUnicodeEscapeSequence EOF ;
    public final void entryRuleUnicodeEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:526:1: ( ruleUnicodeEscapeSequence EOF )
            // InternalRegularExpressionParser.g:527:1: ruleUnicodeEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnicodeEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUnicodeEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnicodeEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnicodeEscapeSequence"


    // $ANTLR start "ruleUnicodeEscapeSequence"
    // InternalRegularExpressionParser.g:534:1: ruleUnicodeEscapeSequence : ( ( rule__UnicodeEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleUnicodeEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:538:2: ( ( ( rule__UnicodeEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:539:2: ( ( rule__UnicodeEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:539:2: ( ( rule__UnicodeEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:540:3: ( rule__UnicodeEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnicodeEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:541:3: ( rule__UnicodeEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:541:4: rule__UnicodeEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__UnicodeEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnicodeEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnicodeEscapeSequence"


    // $ANTLR start "entryRuleIdentityEscapeSequence"
    // InternalRegularExpressionParser.g:550:1: entryRuleIdentityEscapeSequence : ruleIdentityEscapeSequence EOF ;
    public final void entryRuleIdentityEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:551:1: ( ruleIdentityEscapeSequence EOF )
            // InternalRegularExpressionParser.g:552:1: ruleIdentityEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentityEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleIdentityEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentityEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIdentityEscapeSequence"


    // $ANTLR start "ruleIdentityEscapeSequence"
    // InternalRegularExpressionParser.g:559:1: ruleIdentityEscapeSequence : ( ( rule__IdentityEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleIdentityEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:563:2: ( ( ( rule__IdentityEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:564:2: ( ( rule__IdentityEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:564:2: ( ( rule__IdentityEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:565:3: ( rule__IdentityEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentityEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:566:3: ( rule__IdentityEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:566:4: rule__IdentityEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__IdentityEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentityEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIdentityEscapeSequence"


    // $ANTLR start "entryRuleDecimalEscapeSequence"
    // InternalRegularExpressionParser.g:575:1: entryRuleDecimalEscapeSequence : ruleDecimalEscapeSequence EOF ;
    public final void entryRuleDecimalEscapeSequence() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:576:1: ( ruleDecimalEscapeSequence EOF )
            // InternalRegularExpressionParser.g:577:1: ruleDecimalEscapeSequence EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDecimalEscapeSequenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleDecimalEscapeSequence();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDecimalEscapeSequenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDecimalEscapeSequence"


    // $ANTLR start "ruleDecimalEscapeSequence"
    // InternalRegularExpressionParser.g:584:1: ruleDecimalEscapeSequence : ( ( rule__DecimalEscapeSequence__SequenceAssignment ) ) ;
    public final void ruleDecimalEscapeSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:588:2: ( ( ( rule__DecimalEscapeSequence__SequenceAssignment ) ) )
            // InternalRegularExpressionParser.g:589:2: ( ( rule__DecimalEscapeSequence__SequenceAssignment ) )
            {
            // InternalRegularExpressionParser.g:589:2: ( ( rule__DecimalEscapeSequence__SequenceAssignment ) )
            // InternalRegularExpressionParser.g:590:3: ( rule__DecimalEscapeSequence__SequenceAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDecimalEscapeSequenceAccess().getSequenceAssignment()); 
            }
            // InternalRegularExpressionParser.g:591:3: ( rule__DecimalEscapeSequence__SequenceAssignment )
            // InternalRegularExpressionParser.g:591:4: rule__DecimalEscapeSequence__SequenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__DecimalEscapeSequence__SequenceAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDecimalEscapeSequenceAccess().getSequenceAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDecimalEscapeSequence"


    // $ANTLR start "entryRuleCharacterClass"
    // InternalRegularExpressionParser.g:600:1: entryRuleCharacterClass : ruleCharacterClass EOF ;
    public final void entryRuleCharacterClass() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:601:1: ( ruleCharacterClass EOF )
            // InternalRegularExpressionParser.g:602:1: ruleCharacterClass EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCharacterClass();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacterClass"


    // $ANTLR start "ruleCharacterClass"
    // InternalRegularExpressionParser.g:609:1: ruleCharacterClass : ( ( rule__CharacterClass__Group__0 ) ) ;
    public final void ruleCharacterClass() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:613:2: ( ( ( rule__CharacterClass__Group__0 ) ) )
            // InternalRegularExpressionParser.g:614:2: ( ( rule__CharacterClass__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:614:2: ( ( rule__CharacterClass__Group__0 ) )
            // InternalRegularExpressionParser.g:615:3: ( rule__CharacterClass__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:616:3: ( rule__CharacterClass__Group__0 )
            // InternalRegularExpressionParser.g:616:4: rule__CharacterClass__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacterClass"


    // $ANTLR start "entryRuleCharacterClassElement"
    // InternalRegularExpressionParser.g:625:1: entryRuleCharacterClassElement : ruleCharacterClassElement EOF ;
    public final void entryRuleCharacterClassElement() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:626:1: ( ruleCharacterClassElement EOF )
            // InternalRegularExpressionParser.g:627:1: ruleCharacterClassElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCharacterClassElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacterClassElement"


    // $ANTLR start "ruleCharacterClassElement"
    // InternalRegularExpressionParser.g:634:1: ruleCharacterClassElement : ( ( rule__CharacterClassElement__Group__0 ) ) ;
    public final void ruleCharacterClassElement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:638:2: ( ( ( rule__CharacterClassElement__Group__0 ) ) )
            // InternalRegularExpressionParser.g:639:2: ( ( rule__CharacterClassElement__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:639:2: ( ( rule__CharacterClassElement__Group__0 ) )
            // InternalRegularExpressionParser.g:640:3: ( rule__CharacterClassElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:641:3: ( rule__CharacterClassElement__Group__0 )
            // InternalRegularExpressionParser.g:641:4: rule__CharacterClassElement__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacterClassElement"


    // $ANTLR start "entryRuleCharacterClassAtom"
    // InternalRegularExpressionParser.g:650:1: entryRuleCharacterClassAtom : ruleCharacterClassAtom EOF ;
    public final void entryRuleCharacterClassAtom() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:651:1: ( ruleCharacterClassAtom EOF )
            // InternalRegularExpressionParser.g:652:1: ruleCharacterClassAtom EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAtomRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleCharacterClassAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAtomRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacterClassAtom"


    // $ANTLR start "ruleCharacterClassAtom"
    // InternalRegularExpressionParser.g:659:1: ruleCharacterClassAtom : ( ( rule__CharacterClassAtom__Alternatives ) ) ;
    public final void ruleCharacterClassAtom() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:663:2: ( ( ( rule__CharacterClassAtom__Alternatives ) ) )
            // InternalRegularExpressionParser.g:664:2: ( ( rule__CharacterClassAtom__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:664:2: ( ( rule__CharacterClassAtom__Alternatives ) )
            // InternalRegularExpressionParser.g:665:3: ( rule__CharacterClassAtom__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAtomAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:666:3: ( rule__CharacterClassAtom__Alternatives )
            // InternalRegularExpressionParser.g:666:4: rule__CharacterClassAtom__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassAtom__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAtomAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacterClassAtom"


    // $ANTLR start "entryRuleEscapedCharacterClassAtom"
    // InternalRegularExpressionParser.g:675:1: entryRuleEscapedCharacterClassAtom : ruleEscapedCharacterClassAtom EOF ;
    public final void entryRuleEscapedCharacterClassAtom() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:676:1: ( ruleEscapedCharacterClassAtom EOF )
            // InternalRegularExpressionParser.g:677:1: ruleEscapedCharacterClassAtom EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEscapedCharacterClassAtomRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleEscapedCharacterClassAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEscapedCharacterClassAtomRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEscapedCharacterClassAtom"


    // $ANTLR start "ruleEscapedCharacterClassAtom"
    // InternalRegularExpressionParser.g:684:1: ruleEscapedCharacterClassAtom : ( ( rule__EscapedCharacterClassAtom__Alternatives ) ) ;
    public final void ruleEscapedCharacterClassAtom() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:688:2: ( ( ( rule__EscapedCharacterClassAtom__Alternatives ) ) )
            // InternalRegularExpressionParser.g:689:2: ( ( rule__EscapedCharacterClassAtom__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:689:2: ( ( rule__EscapedCharacterClassAtom__Alternatives ) )
            // InternalRegularExpressionParser.g:690:3: ( rule__EscapedCharacterClassAtom__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEscapedCharacterClassAtomAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:691:3: ( rule__EscapedCharacterClassAtom__Alternatives )
            // InternalRegularExpressionParser.g:691:4: rule__EscapedCharacterClassAtom__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EscapedCharacterClassAtom__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEscapedCharacterClassAtomAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEscapedCharacterClassAtom"


    // $ANTLR start "entryRuleBackspace"
    // InternalRegularExpressionParser.g:700:1: entryRuleBackspace : ruleBackspace EOF ;
    public final void entryRuleBackspace() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:701:1: ( ruleBackspace EOF )
            // InternalRegularExpressionParser.g:702:1: ruleBackspace EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBackspaceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleBackspace();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBackspaceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBackspace"


    // $ANTLR start "ruleBackspace"
    // InternalRegularExpressionParser.g:709:1: ruleBackspace : ( ( rule__Backspace__Group__0 ) ) ;
    public final void ruleBackspace() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:713:2: ( ( ( rule__Backspace__Group__0 ) ) )
            // InternalRegularExpressionParser.g:714:2: ( ( rule__Backspace__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:714:2: ( ( rule__Backspace__Group__0 ) )
            // InternalRegularExpressionParser.g:715:3: ( rule__Backspace__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBackspaceAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:716:3: ( rule__Backspace__Group__0 )
            // InternalRegularExpressionParser.g:716:4: rule__Backspace__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Backspace__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBackspaceAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBackspace"


    // $ANTLR start "entryRuleGroup"
    // InternalRegularExpressionParser.g:725:1: entryRuleGroup : ruleGroup EOF ;
    public final void entryRuleGroup() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:726:1: ( ruleGroup EOF )
            // InternalRegularExpressionParser.g:727:1: ruleGroup EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleGroup();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGroup"


    // $ANTLR start "ruleGroup"
    // InternalRegularExpressionParser.g:734:1: ruleGroup : ( ( rule__Group__Group__0 ) ) ;
    public final void ruleGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:738:2: ( ( ( rule__Group__Group__0 ) ) )
            // InternalRegularExpressionParser.g:739:2: ( ( rule__Group__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:739:2: ( ( rule__Group__Group__0 ) )
            // InternalRegularExpressionParser.g:740:3: ( rule__Group__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:741:3: ( rule__Group__Group__0 )
            // InternalRegularExpressionParser.g:741:4: rule__Group__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Group__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGroup"


    // $ANTLR start "entryRuleQuantifier"
    // InternalRegularExpressionParser.g:750:1: entryRuleQuantifier : ruleQuantifier EOF ;
    public final void entryRuleQuantifier() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:751:1: ( ruleQuantifier EOF )
            // InternalRegularExpressionParser.g:752:1: ruleQuantifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQuantifierRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleQuantifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQuantifierRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQuantifier"


    // $ANTLR start "ruleQuantifier"
    // InternalRegularExpressionParser.g:759:1: ruleQuantifier : ( ( rule__Quantifier__Alternatives ) ) ;
    public final void ruleQuantifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:763:2: ( ( ( rule__Quantifier__Alternatives ) ) )
            // InternalRegularExpressionParser.g:764:2: ( ( rule__Quantifier__Alternatives ) )
            {
            // InternalRegularExpressionParser.g:764:2: ( ( rule__Quantifier__Alternatives ) )
            // InternalRegularExpressionParser.g:765:3: ( rule__Quantifier__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQuantifierAccess().getAlternatives()); 
            }
            // InternalRegularExpressionParser.g:766:3: ( rule__Quantifier__Alternatives )
            // InternalRegularExpressionParser.g:766:4: rule__Quantifier__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Quantifier__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQuantifierAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQuantifier"


    // $ANTLR start "entryRuleSimpleQuantifier"
    // InternalRegularExpressionParser.g:775:1: entryRuleSimpleQuantifier : ruleSimpleQuantifier EOF ;
    public final void entryRuleSimpleQuantifier() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:776:1: ( ruleSimpleQuantifier EOF )
            // InternalRegularExpressionParser.g:777:1: ruleSimpleQuantifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleSimpleQuantifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSimpleQuantifier"


    // $ANTLR start "ruleSimpleQuantifier"
    // InternalRegularExpressionParser.g:784:1: ruleSimpleQuantifier : ( ( rule__SimpleQuantifier__Group__0 ) ) ;
    public final void ruleSimpleQuantifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:788:2: ( ( ( rule__SimpleQuantifier__Group__0 ) ) )
            // InternalRegularExpressionParser.g:789:2: ( ( rule__SimpleQuantifier__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:789:2: ( ( rule__SimpleQuantifier__Group__0 ) )
            // InternalRegularExpressionParser.g:790:3: ( rule__SimpleQuantifier__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:791:3: ( rule__SimpleQuantifier__Group__0 )
            // InternalRegularExpressionParser.g:791:4: rule__SimpleQuantifier__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleQuantifier__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleQuantifier"


    // $ANTLR start "entryRuleExactQuantifier"
    // InternalRegularExpressionParser.g:800:1: entryRuleExactQuantifier : ruleExactQuantifier EOF ;
    public final void entryRuleExactQuantifier() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:801:1: ( ruleExactQuantifier EOF )
            // InternalRegularExpressionParser.g:802:1: ruleExactQuantifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleExactQuantifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExactQuantifier"


    // $ANTLR start "ruleExactQuantifier"
    // InternalRegularExpressionParser.g:809:1: ruleExactQuantifier : ( ( rule__ExactQuantifier__Group__0 ) ) ;
    public final void ruleExactQuantifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:813:2: ( ( ( rule__ExactQuantifier__Group__0 ) ) )
            // InternalRegularExpressionParser.g:814:2: ( ( rule__ExactQuantifier__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:814:2: ( ( rule__ExactQuantifier__Group__0 ) )
            // InternalRegularExpressionParser.g:815:3: ( rule__ExactQuantifier__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:816:3: ( rule__ExactQuantifier__Group__0 )
            // InternalRegularExpressionParser.g:816:4: rule__ExactQuantifier__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExactQuantifier"


    // $ANTLR start "entryRuleRegularExpressionFlags"
    // InternalRegularExpressionParser.g:825:1: entryRuleRegularExpressionFlags : ruleRegularExpressionFlags EOF ;
    public final void entryRuleRegularExpressionFlags() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:826:1: ( ruleRegularExpressionFlags EOF )
            // InternalRegularExpressionParser.g:827:1: ruleRegularExpressionFlags EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionFlagsRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleRegularExpressionFlags();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionFlagsRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRegularExpressionFlags"


    // $ANTLR start "ruleRegularExpressionFlags"
    // InternalRegularExpressionParser.g:834:1: ruleRegularExpressionFlags : ( ( rule__RegularExpressionFlags__Group__0 ) ) ;
    public final void ruleRegularExpressionFlags() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:838:2: ( ( ( rule__RegularExpressionFlags__Group__0 ) ) )
            // InternalRegularExpressionParser.g:839:2: ( ( rule__RegularExpressionFlags__Group__0 ) )
            {
            // InternalRegularExpressionParser.g:839:2: ( ( rule__RegularExpressionFlags__Group__0 ) )
            // InternalRegularExpressionParser.g:840:3: ( rule__RegularExpressionFlags__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionFlagsAccess().getGroup()); 
            }
            // InternalRegularExpressionParser.g:841:3: ( rule__RegularExpressionFlags__Group__0 )
            // InternalRegularExpressionParser.g:841:4: rule__RegularExpressionFlags__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionFlags__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionFlagsAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRegularExpressionFlags"


    // $ANTLR start "entryRuleINT"
    // InternalRegularExpressionParser.g:850:1: entryRuleINT : ruleINT EOF ;
    public final void entryRuleINT() throws RecognitionException {
        try {
            // InternalRegularExpressionParser.g:851:1: ( ruleINT EOF )
            // InternalRegularExpressionParser.g:852:1: ruleINT EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getINTRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleINT();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getINTRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleINT"


    // $ANTLR start "ruleINT"
    // InternalRegularExpressionParser.g:859:1: ruleINT : ( ( ( RULE_UNICODE_DIGIT ) ) ( ( RULE_UNICODE_DIGIT )* ) ) ;
    public final void ruleINT() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:863:2: ( ( ( ( RULE_UNICODE_DIGIT ) ) ( ( RULE_UNICODE_DIGIT )* ) ) )
            // InternalRegularExpressionParser.g:864:2: ( ( ( RULE_UNICODE_DIGIT ) ) ( ( RULE_UNICODE_DIGIT )* ) )
            {
            // InternalRegularExpressionParser.g:864:2: ( ( ( RULE_UNICODE_DIGIT ) ) ( ( RULE_UNICODE_DIGIT )* ) )
            // InternalRegularExpressionParser.g:865:3: ( ( RULE_UNICODE_DIGIT ) ) ( ( RULE_UNICODE_DIGIT )* )
            {
            // InternalRegularExpressionParser.g:865:3: ( ( RULE_UNICODE_DIGIT ) )
            // InternalRegularExpressionParser.g:866:4: ( RULE_UNICODE_DIGIT )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getINTAccess().getUNICODE_DIGITTerminalRuleCall()); 
            }
            // InternalRegularExpressionParser.g:867:4: ( RULE_UNICODE_DIGIT )
            // InternalRegularExpressionParser.g:867:5: RULE_UNICODE_DIGIT
            {
            match(input,RULE_UNICODE_DIGIT,FOLLOW_3); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getINTAccess().getUNICODE_DIGITTerminalRuleCall()); 
            }

            }

            // InternalRegularExpressionParser.g:870:3: ( ( RULE_UNICODE_DIGIT )* )
            // InternalRegularExpressionParser.g:871:4: ( RULE_UNICODE_DIGIT )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getINTAccess().getUNICODE_DIGITTerminalRuleCall()); 
            }
            // InternalRegularExpressionParser.g:872:4: ( RULE_UNICODE_DIGIT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_UNICODE_DIGIT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:872:5: RULE_UNICODE_DIGIT
            	    {
            	    match(input,RULE_UNICODE_DIGIT,FOLLOW_3); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getINTAccess().getUNICODE_DIGITTerminalRuleCall()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleINT"


    // $ANTLR start "rule__Disjunction__Alternatives"
    // InternalRegularExpressionParser.g:881:1: rule__Disjunction__Alternatives : ( ( ( rule__Disjunction__Group_0__0 ) ) | ( ( rule__Disjunction__Group_1__0 ) ) );
    public final void rule__Disjunction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:885:1: ( ( ( rule__Disjunction__Group_0__0 ) ) | ( ( rule__Disjunction__Group_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=ExclamationMark && LA2_0<=LeftParenthesis)||(LA2_0>=Comma && LA2_0<=FullStop)||(LA2_0>=Colon && LA2_0<=EqualsSign)||(LA2_0>=LeftSquareBracket && LA2_0<=LeftCurlyBracket)||(LA2_0>=RightCurlyBracket && LA2_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA2_0>=RULE_HEX_ESCAPE && LA2_0<=RULE_UNICODE_ESCAPE)||(LA2_0>=RULE_DECIMAL_ESCAPE && LA2_0<=RULE_IDENTITY_ESCAPE)||LA2_0==RULE_UNICODE_DIGIT||(LA2_0>=RULE_UNICODE_LETTER && LA2_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                alt2=1;
            }
            else if ( (LA2_0==EOF||LA2_0==RightParenthesis||LA2_0==Solidus||LA2_0==VerticalLine) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalRegularExpressionParser.g:886:2: ( ( rule__Disjunction__Group_0__0 ) )
                    {
                    // InternalRegularExpressionParser.g:886:2: ( ( rule__Disjunction__Group_0__0 ) )
                    // InternalRegularExpressionParser.g:887:3: ( rule__Disjunction__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getDisjunctionAccess().getGroup_0()); 
                    }
                    // InternalRegularExpressionParser.g:888:3: ( rule__Disjunction__Group_0__0 )
                    // InternalRegularExpressionParser.g:888:4: rule__Disjunction__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Disjunction__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getDisjunctionAccess().getGroup_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:892:2: ( ( rule__Disjunction__Group_1__0 ) )
                    {
                    // InternalRegularExpressionParser.g:892:2: ( ( rule__Disjunction__Group_1__0 ) )
                    // InternalRegularExpressionParser.g:893:3: ( rule__Disjunction__Group_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getDisjunctionAccess().getGroup_1()); 
                    }
                    // InternalRegularExpressionParser.g:894:3: ( rule__Disjunction__Group_1__0 )
                    // InternalRegularExpressionParser.g:894:4: rule__Disjunction__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Disjunction__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getDisjunctionAccess().getGroup_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Alternatives"


    // $ANTLR start "rule__Term__Alternatives"
    // InternalRegularExpressionParser.g:902:1: rule__Term__Alternatives : ( ( ruleAssertion ) | ( ( rule__Term__Group_1__0 ) ) );
    public final void rule__Term__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:906:1: ( ( ruleAssertion ) | ( ( rule__Term__Group_1__0 ) ) )
            int alt3=2;
            switch ( input.LA(1) ) {
            case DollarSign:
            case CircumflexAccent:
            case RULE_WORD_BOUNDARY:
            case RULE_NOT_WORD_BOUNDARY:
                {
                alt3=1;
                }
                break;
            case LeftParenthesis:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==QuestionMark) ) {
                    int LA3_4 = input.LA(3);

                    if ( (LA3_4==ExclamationMark||LA3_4==EqualsSign) ) {
                        alt3=1;
                    }
                    else if ( (LA3_4==Colon) ) {
                        alt3=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 4, input);

                        throw nvae;
                    }
                }
                else if ( ((LA3_2>=ExclamationMark && LA3_2<=RightParenthesis)||(LA3_2>=Comma && LA3_2<=FullStop)||(LA3_2>=Colon && LA3_2<=EqualsSign)||(LA3_2>=LeftSquareBracket && LA3_2<=RULE_CONTROL_LETTER_ESCAPE)||(LA3_2>=RULE_HEX_ESCAPE && LA3_2<=RULE_UNICODE_ESCAPE)||(LA3_2>=RULE_DECIMAL_ESCAPE && LA3_2<=RULE_IDENTITY_ESCAPE)||LA3_2==RULE_UNICODE_DIGIT||(LA3_2>=RULE_UNICODE_LETTER && LA3_2<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;
                }
                }
                break;
            case ExclamationMark:
            case Comma:
            case HyphenMinus:
            case FullStop:
            case Colon:
            case EqualsSign:
            case LeftSquareBracket:
            case RightSquareBracket:
            case LeftCurlyBracket:
            case RightCurlyBracket:
            case RULE_CHARACTER_CLASS_ESCAPE:
            case RULE_CONTROL_ESCAPE:
            case RULE_CONTROL_LETTER_ESCAPE:
            case RULE_HEX_ESCAPE:
            case RULE_UNICODE_ESCAPE:
            case RULE_DECIMAL_ESCAPE:
            case RULE_IDENTITY_ESCAPE:
            case RULE_UNICODE_DIGIT:
            case RULE_UNICODE_LETTER:
            case RULE_PATTERN_CHARACTER_NO_DASH:
                {
                alt3=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalRegularExpressionParser.g:907:2: ( ruleAssertion )
                    {
                    // InternalRegularExpressionParser.g:907:2: ( ruleAssertion )
                    // InternalRegularExpressionParser.g:908:3: ruleAssertion
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTermAccess().getAssertionParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleAssertion();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTermAccess().getAssertionParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:913:2: ( ( rule__Term__Group_1__0 ) )
                    {
                    // InternalRegularExpressionParser.g:913:2: ( ( rule__Term__Group_1__0 ) )
                    // InternalRegularExpressionParser.g:914:3: ( rule__Term__Group_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTermAccess().getGroup_1()); 
                    }
                    // InternalRegularExpressionParser.g:915:3: ( rule__Term__Group_1__0 )
                    // InternalRegularExpressionParser.g:915:4: rule__Term__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Term__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTermAccess().getGroup_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__Alternatives"


    // $ANTLR start "rule__Assertion__Alternatives"
    // InternalRegularExpressionParser.g:923:1: rule__Assertion__Alternatives : ( ( ruleLineStart ) | ( ruleLineEnd ) | ( ruleWordBoundary ) | ( ruleLookAhead ) );
    public final void rule__Assertion__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:927:1: ( ( ruleLineStart ) | ( ruleLineEnd ) | ( ruleWordBoundary ) | ( ruleLookAhead ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case CircumflexAccent:
                {
                alt4=1;
                }
                break;
            case DollarSign:
                {
                alt4=2;
                }
                break;
            case RULE_WORD_BOUNDARY:
            case RULE_NOT_WORD_BOUNDARY:
                {
                alt4=3;
                }
                break;
            case LeftParenthesis:
                {
                alt4=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalRegularExpressionParser.g:928:2: ( ruleLineStart )
                    {
                    // InternalRegularExpressionParser.g:928:2: ( ruleLineStart )
                    // InternalRegularExpressionParser.g:929:3: ruleLineStart
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAssertionAccess().getLineStartParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLineStart();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAssertionAccess().getLineStartParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:934:2: ( ruleLineEnd )
                    {
                    // InternalRegularExpressionParser.g:934:2: ( ruleLineEnd )
                    // InternalRegularExpressionParser.g:935:3: ruleLineEnd
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAssertionAccess().getLineEndParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLineEnd();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAssertionAccess().getLineEndParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:940:2: ( ruleWordBoundary )
                    {
                    // InternalRegularExpressionParser.g:940:2: ( ruleWordBoundary )
                    // InternalRegularExpressionParser.g:941:3: ruleWordBoundary
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAssertionAccess().getWordBoundaryParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleWordBoundary();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAssertionAccess().getWordBoundaryParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:946:2: ( ruleLookAhead )
                    {
                    // InternalRegularExpressionParser.g:946:2: ( ruleLookAhead )
                    // InternalRegularExpressionParser.g:947:3: ruleLookAhead
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAssertionAccess().getLookAheadParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLookAhead();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAssertionAccess().getLookAheadParserRuleCall_3()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assertion__Alternatives"


    // $ANTLR start "rule__WordBoundary__Alternatives_1"
    // InternalRegularExpressionParser.g:956:1: rule__WordBoundary__Alternatives_1 : ( ( RULE_WORD_BOUNDARY ) | ( ( rule__WordBoundary__NotAssignment_1_1 ) ) );
    public final void rule__WordBoundary__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:960:1: ( ( RULE_WORD_BOUNDARY ) | ( ( rule__WordBoundary__NotAssignment_1_1 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_WORD_BOUNDARY) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_NOT_WORD_BOUNDARY) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalRegularExpressionParser.g:961:2: ( RULE_WORD_BOUNDARY )
                    {
                    // InternalRegularExpressionParser.g:961:2: ( RULE_WORD_BOUNDARY )
                    // InternalRegularExpressionParser.g:962:3: RULE_WORD_BOUNDARY
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getWordBoundaryAccess().getWORD_BOUNDARYTerminalRuleCall_1_0()); 
                    }
                    match(input,RULE_WORD_BOUNDARY,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getWordBoundaryAccess().getWORD_BOUNDARYTerminalRuleCall_1_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:967:2: ( ( rule__WordBoundary__NotAssignment_1_1 ) )
                    {
                    // InternalRegularExpressionParser.g:967:2: ( ( rule__WordBoundary__NotAssignment_1_1 ) )
                    // InternalRegularExpressionParser.g:968:3: ( rule__WordBoundary__NotAssignment_1_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getWordBoundaryAccess().getNotAssignment_1_1()); 
                    }
                    // InternalRegularExpressionParser.g:969:3: ( rule__WordBoundary__NotAssignment_1_1 )
                    // InternalRegularExpressionParser.g:969:4: rule__WordBoundary__NotAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__WordBoundary__NotAssignment_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getWordBoundaryAccess().getNotAssignment_1_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__Alternatives_1"


    // $ANTLR start "rule__LookAhead__Alternatives_3"
    // InternalRegularExpressionParser.g:977:1: rule__LookAhead__Alternatives_3 : ( ( EqualsSign ) | ( ( rule__LookAhead__NotAssignment_3_1 ) ) );
    public final void rule__LookAhead__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:981:1: ( ( EqualsSign ) | ( ( rule__LookAhead__NotAssignment_3_1 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==EqualsSign) ) {
                alt6=1;
            }
            else if ( (LA6_0==ExclamationMark) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalRegularExpressionParser.g:982:2: ( EqualsSign )
                    {
                    // InternalRegularExpressionParser.g:982:2: ( EqualsSign )
                    // InternalRegularExpressionParser.g:983:3: EqualsSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLookAheadAccess().getEqualsSignKeyword_3_0()); 
                    }
                    match(input,EqualsSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLookAheadAccess().getEqualsSignKeyword_3_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:988:2: ( ( rule__LookAhead__NotAssignment_3_1 ) )
                    {
                    // InternalRegularExpressionParser.g:988:2: ( ( rule__LookAhead__NotAssignment_3_1 ) )
                    // InternalRegularExpressionParser.g:989:3: ( rule__LookAhead__NotAssignment_3_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLookAheadAccess().getNotAssignment_3_1()); 
                    }
                    // InternalRegularExpressionParser.g:990:3: ( rule__LookAhead__NotAssignment_3_1 )
                    // InternalRegularExpressionParser.g:990:4: rule__LookAhead__NotAssignment_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__LookAhead__NotAssignment_3_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLookAheadAccess().getNotAssignment_3_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Alternatives_3"


    // $ANTLR start "rule__Atom__Alternatives"
    // InternalRegularExpressionParser.g:998:1: rule__Atom__Alternatives : ( ( rulePatternCharacter ) | ( ruleWildcard ) | ( ruleAtomEscape ) | ( ruleCharacterClass ) | ( ruleGroup ) );
    public final void rule__Atom__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1002:1: ( ( rulePatternCharacter ) | ( ruleWildcard ) | ( ruleAtomEscape ) | ( ruleCharacterClass ) | ( ruleGroup ) )
            int alt7=5;
            switch ( input.LA(1) ) {
            case ExclamationMark:
            case Comma:
            case HyphenMinus:
            case Colon:
            case EqualsSign:
            case RightSquareBracket:
            case LeftCurlyBracket:
            case RightCurlyBracket:
            case RULE_UNICODE_DIGIT:
            case RULE_UNICODE_LETTER:
            case RULE_PATTERN_CHARACTER_NO_DASH:
                {
                alt7=1;
                }
                break;
            case FullStop:
                {
                alt7=2;
                }
                break;
            case RULE_CHARACTER_CLASS_ESCAPE:
            case RULE_CONTROL_ESCAPE:
            case RULE_CONTROL_LETTER_ESCAPE:
            case RULE_HEX_ESCAPE:
            case RULE_UNICODE_ESCAPE:
            case RULE_DECIMAL_ESCAPE:
            case RULE_IDENTITY_ESCAPE:
                {
                alt7=3;
                }
                break;
            case LeftSquareBracket:
                {
                alt7=4;
                }
                break;
            case LeftParenthesis:
                {
                alt7=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalRegularExpressionParser.g:1003:2: ( rulePatternCharacter )
                    {
                    // InternalRegularExpressionParser.g:1003:2: ( rulePatternCharacter )
                    // InternalRegularExpressionParser.g:1004:3: rulePatternCharacter
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomAccess().getPatternCharacterParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    rulePatternCharacter();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomAccess().getPatternCharacterParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1009:2: ( ruleWildcard )
                    {
                    // InternalRegularExpressionParser.g:1009:2: ( ruleWildcard )
                    // InternalRegularExpressionParser.g:1010:3: ruleWildcard
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomAccess().getWildcardParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleWildcard();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomAccess().getWildcardParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1015:2: ( ruleAtomEscape )
                    {
                    // InternalRegularExpressionParser.g:1015:2: ( ruleAtomEscape )
                    // InternalRegularExpressionParser.g:1016:3: ruleAtomEscape
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomAccess().getAtomEscapeParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleAtomEscape();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomAccess().getAtomEscapeParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:1021:2: ( ruleCharacterClass )
                    {
                    // InternalRegularExpressionParser.g:1021:2: ( ruleCharacterClass )
                    // InternalRegularExpressionParser.g:1022:3: ruleCharacterClass
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomAccess().getCharacterClassParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCharacterClass();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomAccess().getCharacterClassParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalRegularExpressionParser.g:1027:2: ( ruleGroup )
                    {
                    // InternalRegularExpressionParser.g:1027:2: ( ruleGroup )
                    // InternalRegularExpressionParser.g:1028:3: ruleGroup
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomAccess().getGroupParserRuleCall_4()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleGroup();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomAccess().getGroupParserRuleCall_4()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Atom__Alternatives"


    // $ANTLR start "rule__PatternCharacter__ValueAlternatives_0"
    // InternalRegularExpressionParser.g:1037:1: rule__PatternCharacter__ValueAlternatives_0 : ( ( RULE_PATTERN_CHARACTER_NO_DASH ) | ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_DIGIT ) | ( HyphenMinus ) | ( Comma ) | ( EqualsSign ) | ( Colon ) | ( ExclamationMark ) | ( LeftCurlyBracket ) | ( RightCurlyBracket ) | ( RightSquareBracket ) );
    public final void rule__PatternCharacter__ValueAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1041:1: ( ( RULE_PATTERN_CHARACTER_NO_DASH ) | ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_DIGIT ) | ( HyphenMinus ) | ( Comma ) | ( EqualsSign ) | ( Colon ) | ( ExclamationMark ) | ( LeftCurlyBracket ) | ( RightCurlyBracket ) | ( RightSquareBracket ) )
            int alt8=11;
            switch ( input.LA(1) ) {
            case RULE_PATTERN_CHARACTER_NO_DASH:
                {
                alt8=1;
                }
                break;
            case RULE_UNICODE_LETTER:
                {
                alt8=2;
                }
                break;
            case RULE_UNICODE_DIGIT:
                {
                alt8=3;
                }
                break;
            case HyphenMinus:
                {
                alt8=4;
                }
                break;
            case Comma:
                {
                alt8=5;
                }
                break;
            case EqualsSign:
                {
                alt8=6;
                }
                break;
            case Colon:
                {
                alt8=7;
                }
                break;
            case ExclamationMark:
                {
                alt8=8;
                }
                break;
            case LeftCurlyBracket:
                {
                alt8=9;
                }
                break;
            case RightCurlyBracket:
                {
                alt8=10;
                }
                break;
            case RightSquareBracket:
                {
                alt8=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalRegularExpressionParser.g:1042:2: ( RULE_PATTERN_CHARACTER_NO_DASH )
                    {
                    // InternalRegularExpressionParser.g:1042:2: ( RULE_PATTERN_CHARACTER_NO_DASH )
                    // InternalRegularExpressionParser.g:1043:3: RULE_PATTERN_CHARACTER_NO_DASH
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValuePATTERN_CHARACTER_NO_DASHTerminalRuleCall_0_0()); 
                    }
                    match(input,RULE_PATTERN_CHARACTER_NO_DASH,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValuePATTERN_CHARACTER_NO_DASHTerminalRuleCall_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1048:2: ( RULE_UNICODE_LETTER )
                    {
                    // InternalRegularExpressionParser.g:1048:2: ( RULE_UNICODE_LETTER )
                    // InternalRegularExpressionParser.g:1049:3: RULE_UNICODE_LETTER
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueUNICODE_LETTERTerminalRuleCall_0_1()); 
                    }
                    match(input,RULE_UNICODE_LETTER,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueUNICODE_LETTERTerminalRuleCall_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1054:2: ( RULE_UNICODE_DIGIT )
                    {
                    // InternalRegularExpressionParser.g:1054:2: ( RULE_UNICODE_DIGIT )
                    // InternalRegularExpressionParser.g:1055:3: RULE_UNICODE_DIGIT
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueUNICODE_DIGITTerminalRuleCall_0_2()); 
                    }
                    match(input,RULE_UNICODE_DIGIT,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueUNICODE_DIGITTerminalRuleCall_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:1060:2: ( HyphenMinus )
                    {
                    // InternalRegularExpressionParser.g:1060:2: ( HyphenMinus )
                    // InternalRegularExpressionParser.g:1061:3: HyphenMinus
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueHyphenMinusKeyword_0_3()); 
                    }
                    match(input,HyphenMinus,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueHyphenMinusKeyword_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalRegularExpressionParser.g:1066:2: ( Comma )
                    {
                    // InternalRegularExpressionParser.g:1066:2: ( Comma )
                    // InternalRegularExpressionParser.g:1067:3: Comma
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueCommaKeyword_0_4()); 
                    }
                    match(input,Comma,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueCommaKeyword_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalRegularExpressionParser.g:1072:2: ( EqualsSign )
                    {
                    // InternalRegularExpressionParser.g:1072:2: ( EqualsSign )
                    // InternalRegularExpressionParser.g:1073:3: EqualsSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueEqualsSignKeyword_0_5()); 
                    }
                    match(input,EqualsSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueEqualsSignKeyword_0_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalRegularExpressionParser.g:1078:2: ( Colon )
                    {
                    // InternalRegularExpressionParser.g:1078:2: ( Colon )
                    // InternalRegularExpressionParser.g:1079:3: Colon
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueColonKeyword_0_6()); 
                    }
                    match(input,Colon,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueColonKeyword_0_6()); 
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalRegularExpressionParser.g:1084:2: ( ExclamationMark )
                    {
                    // InternalRegularExpressionParser.g:1084:2: ( ExclamationMark )
                    // InternalRegularExpressionParser.g:1085:3: ExclamationMark
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueExclamationMarkKeyword_0_7()); 
                    }
                    match(input,ExclamationMark,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueExclamationMarkKeyword_0_7()); 
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalRegularExpressionParser.g:1090:2: ( LeftCurlyBracket )
                    {
                    // InternalRegularExpressionParser.g:1090:2: ( LeftCurlyBracket )
                    // InternalRegularExpressionParser.g:1091:3: LeftCurlyBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueLeftCurlyBracketKeyword_0_8()); 
                    }
                    match(input,LeftCurlyBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueLeftCurlyBracketKeyword_0_8()); 
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalRegularExpressionParser.g:1096:2: ( RightCurlyBracket )
                    {
                    // InternalRegularExpressionParser.g:1096:2: ( RightCurlyBracket )
                    // InternalRegularExpressionParser.g:1097:3: RightCurlyBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueRightCurlyBracketKeyword_0_9()); 
                    }
                    match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueRightCurlyBracketKeyword_0_9()); 
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalRegularExpressionParser.g:1102:2: ( RightSquareBracket )
                    {
                    // InternalRegularExpressionParser.g:1102:2: ( RightSquareBracket )
                    // InternalRegularExpressionParser.g:1103:3: RightSquareBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getPatternCharacterAccess().getValueRightSquareBracketKeyword_0_10()); 
                    }
                    match(input,RightSquareBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getPatternCharacterAccess().getValueRightSquareBracketKeyword_0_10()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternCharacter__ValueAlternatives_0"


    // $ANTLR start "rule__AtomEscape__Alternatives"
    // InternalRegularExpressionParser.g:1112:1: rule__AtomEscape__Alternatives : ( ( ruleDecimalEscapeSequence ) | ( ruleCharacterEscapeSequence ) | ( ruleControlLetterEscapeSequence ) | ( ruleHexEscapeSequence ) | ( ruleUnicodeEscapeSequence ) | ( ruleIdentityEscapeSequence ) | ( ruleCharacterClassEscapeSequence ) );
    public final void rule__AtomEscape__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1116:1: ( ( ruleDecimalEscapeSequence ) | ( ruleCharacterEscapeSequence ) | ( ruleControlLetterEscapeSequence ) | ( ruleHexEscapeSequence ) | ( ruleUnicodeEscapeSequence ) | ( ruleIdentityEscapeSequence ) | ( ruleCharacterClassEscapeSequence ) )
            int alt9=7;
            switch ( input.LA(1) ) {
            case RULE_DECIMAL_ESCAPE:
                {
                alt9=1;
                }
                break;
            case RULE_CONTROL_ESCAPE:
                {
                alt9=2;
                }
                break;
            case RULE_CONTROL_LETTER_ESCAPE:
                {
                alt9=3;
                }
                break;
            case RULE_HEX_ESCAPE:
                {
                alt9=4;
                }
                break;
            case RULE_UNICODE_ESCAPE:
                {
                alt9=5;
                }
                break;
            case RULE_IDENTITY_ESCAPE:
                {
                alt9=6;
                }
                break;
            case RULE_CHARACTER_CLASS_ESCAPE:
                {
                alt9=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalRegularExpressionParser.g:1117:2: ( ruleDecimalEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1117:2: ( ruleDecimalEscapeSequence )
                    // InternalRegularExpressionParser.g:1118:3: ruleDecimalEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getDecimalEscapeSequenceParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleDecimalEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getDecimalEscapeSequenceParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1123:2: ( ruleCharacterEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1123:2: ( ruleCharacterEscapeSequence )
                    // InternalRegularExpressionParser.g:1124:3: ruleCharacterEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getCharacterEscapeSequenceParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCharacterEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getCharacterEscapeSequenceParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1129:2: ( ruleControlLetterEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1129:2: ( ruleControlLetterEscapeSequence )
                    // InternalRegularExpressionParser.g:1130:3: ruleControlLetterEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getControlLetterEscapeSequenceParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleControlLetterEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getControlLetterEscapeSequenceParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:1135:2: ( ruleHexEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1135:2: ( ruleHexEscapeSequence )
                    // InternalRegularExpressionParser.g:1136:3: ruleHexEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getHexEscapeSequenceParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleHexEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getHexEscapeSequenceParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalRegularExpressionParser.g:1141:2: ( ruleUnicodeEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1141:2: ( ruleUnicodeEscapeSequence )
                    // InternalRegularExpressionParser.g:1142:3: ruleUnicodeEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getUnicodeEscapeSequenceParserRuleCall_4()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleUnicodeEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getUnicodeEscapeSequenceParserRuleCall_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalRegularExpressionParser.g:1147:2: ( ruleIdentityEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1147:2: ( ruleIdentityEscapeSequence )
                    // InternalRegularExpressionParser.g:1148:3: ruleIdentityEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getIdentityEscapeSequenceParserRuleCall_5()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleIdentityEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getIdentityEscapeSequenceParserRuleCall_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalRegularExpressionParser.g:1153:2: ( ruleCharacterClassEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1153:2: ( ruleCharacterClassEscapeSequence )
                    // InternalRegularExpressionParser.g:1154:3: ruleCharacterClassEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAtomEscapeAccess().getCharacterClassEscapeSequenceParserRuleCall_6()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCharacterClassEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAtomEscapeAccess().getCharacterClassEscapeSequenceParserRuleCall_6()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomEscape__Alternatives"


    // $ANTLR start "rule__CharacterClassAtom__Alternatives"
    // InternalRegularExpressionParser.g:1163:1: rule__CharacterClassAtom__Alternatives : ( ( ruleEscapedCharacterClassAtom ) | ( ( rule__CharacterClassAtom__CharacterAssignment_1 ) ) );
    public final void rule__CharacterClassAtom__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1167:1: ( ( ruleEscapedCharacterClassAtom ) | ( ( rule__CharacterClassAtom__CharacterAssignment_1 ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_WORD_BOUNDARY||(LA10_0>=RULE_CHARACTER_CLASS_ESCAPE && LA10_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA10_0>=RULE_HEX_ESCAPE && LA10_0<=RULE_UNICODE_ESCAPE)||(LA10_0>=RULE_DECIMAL_ESCAPE && LA10_0<=RULE_IDENTITY_ESCAPE)) ) {
                alt10=1;
            }
            else if ( ((LA10_0>=ExclamationMark && LA10_0<=LeftSquareBracket)||(LA10_0>=CircumflexAccent && LA10_0<=RightCurlyBracket)||LA10_0==RULE_UNICODE_DIGIT||(LA10_0>=RULE_UNICODE_LETTER && LA10_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalRegularExpressionParser.g:1168:2: ( ruleEscapedCharacterClassAtom )
                    {
                    // InternalRegularExpressionParser.g:1168:2: ( ruleEscapedCharacterClassAtom )
                    // InternalRegularExpressionParser.g:1169:3: ruleEscapedCharacterClassAtom
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getEscapedCharacterClassAtomParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleEscapedCharacterClassAtom();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getEscapedCharacterClassAtomParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1174:2: ( ( rule__CharacterClassAtom__CharacterAssignment_1 ) )
                    {
                    // InternalRegularExpressionParser.g:1174:2: ( ( rule__CharacterClassAtom__CharacterAssignment_1 ) )
                    // InternalRegularExpressionParser.g:1175:3: ( rule__CharacterClassAtom__CharacterAssignment_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterAssignment_1()); 
                    }
                    // InternalRegularExpressionParser.g:1176:3: ( rule__CharacterClassAtom__CharacterAssignment_1 )
                    // InternalRegularExpressionParser.g:1176:4: rule__CharacterClassAtom__CharacterAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__CharacterClassAtom__CharacterAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterAssignment_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassAtom__Alternatives"


    // $ANTLR start "rule__CharacterClassAtom__CharacterAlternatives_1_0"
    // InternalRegularExpressionParser.g:1184:1: rule__CharacterClassAtom__CharacterAlternatives_1_0 : ( ( Comma ) | ( EqualsSign ) | ( Colon ) | ( ExclamationMark ) | ( HyphenMinus ) | ( CircumflexAccent ) | ( DollarSign ) | ( FullStop ) | ( Asterisk ) | ( PlusSign ) | ( QuestionMark ) | ( LeftParenthesis ) | ( RightParenthesis ) | ( LeftSquareBracket ) | ( LeftCurlyBracket ) | ( RightCurlyBracket ) | ( VerticalLine ) | ( Solidus ) | ( RULE_PATTERN_CHARACTER_NO_DASH ) | ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_DIGIT ) );
    public final void rule__CharacterClassAtom__CharacterAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1188:1: ( ( Comma ) | ( EqualsSign ) | ( Colon ) | ( ExclamationMark ) | ( HyphenMinus ) | ( CircumflexAccent ) | ( DollarSign ) | ( FullStop ) | ( Asterisk ) | ( PlusSign ) | ( QuestionMark ) | ( LeftParenthesis ) | ( RightParenthesis ) | ( LeftSquareBracket ) | ( LeftCurlyBracket ) | ( RightCurlyBracket ) | ( VerticalLine ) | ( Solidus ) | ( RULE_PATTERN_CHARACTER_NO_DASH ) | ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_DIGIT ) )
            int alt11=21;
            switch ( input.LA(1) ) {
            case Comma:
                {
                alt11=1;
                }
                break;
            case EqualsSign:
                {
                alt11=2;
                }
                break;
            case Colon:
                {
                alt11=3;
                }
                break;
            case ExclamationMark:
                {
                alt11=4;
                }
                break;
            case HyphenMinus:
                {
                alt11=5;
                }
                break;
            case CircumflexAccent:
                {
                alt11=6;
                }
                break;
            case DollarSign:
                {
                alt11=7;
                }
                break;
            case FullStop:
                {
                alt11=8;
                }
                break;
            case Asterisk:
                {
                alt11=9;
                }
                break;
            case PlusSign:
                {
                alt11=10;
                }
                break;
            case QuestionMark:
                {
                alt11=11;
                }
                break;
            case LeftParenthesis:
                {
                alt11=12;
                }
                break;
            case RightParenthesis:
                {
                alt11=13;
                }
                break;
            case LeftSquareBracket:
                {
                alt11=14;
                }
                break;
            case LeftCurlyBracket:
                {
                alt11=15;
                }
                break;
            case RightCurlyBracket:
                {
                alt11=16;
                }
                break;
            case VerticalLine:
                {
                alt11=17;
                }
                break;
            case Solidus:
                {
                alt11=18;
                }
                break;
            case RULE_PATTERN_CHARACTER_NO_DASH:
                {
                alt11=19;
                }
                break;
            case RULE_UNICODE_LETTER:
                {
                alt11=20;
                }
                break;
            case RULE_UNICODE_DIGIT:
                {
                alt11=21;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalRegularExpressionParser.g:1189:2: ( Comma )
                    {
                    // InternalRegularExpressionParser.g:1189:2: ( Comma )
                    // InternalRegularExpressionParser.g:1190:3: Comma
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterCommaKeyword_1_0_0()); 
                    }
                    match(input,Comma,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterCommaKeyword_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1195:2: ( EqualsSign )
                    {
                    // InternalRegularExpressionParser.g:1195:2: ( EqualsSign )
                    // InternalRegularExpressionParser.g:1196:3: EqualsSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterEqualsSignKeyword_1_0_1()); 
                    }
                    match(input,EqualsSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterEqualsSignKeyword_1_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1201:2: ( Colon )
                    {
                    // InternalRegularExpressionParser.g:1201:2: ( Colon )
                    // InternalRegularExpressionParser.g:1202:3: Colon
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterColonKeyword_1_0_2()); 
                    }
                    match(input,Colon,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterColonKeyword_1_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:1207:2: ( ExclamationMark )
                    {
                    // InternalRegularExpressionParser.g:1207:2: ( ExclamationMark )
                    // InternalRegularExpressionParser.g:1208:3: ExclamationMark
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterExclamationMarkKeyword_1_0_3()); 
                    }
                    match(input,ExclamationMark,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterExclamationMarkKeyword_1_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalRegularExpressionParser.g:1213:2: ( HyphenMinus )
                    {
                    // InternalRegularExpressionParser.g:1213:2: ( HyphenMinus )
                    // InternalRegularExpressionParser.g:1214:3: HyphenMinus
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterHyphenMinusKeyword_1_0_4()); 
                    }
                    match(input,HyphenMinus,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterHyphenMinusKeyword_1_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalRegularExpressionParser.g:1219:2: ( CircumflexAccent )
                    {
                    // InternalRegularExpressionParser.g:1219:2: ( CircumflexAccent )
                    // InternalRegularExpressionParser.g:1220:3: CircumflexAccent
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterCircumflexAccentKeyword_1_0_5()); 
                    }
                    match(input,CircumflexAccent,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterCircumflexAccentKeyword_1_0_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalRegularExpressionParser.g:1225:2: ( DollarSign )
                    {
                    // InternalRegularExpressionParser.g:1225:2: ( DollarSign )
                    // InternalRegularExpressionParser.g:1226:3: DollarSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterDollarSignKeyword_1_0_6()); 
                    }
                    match(input,DollarSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterDollarSignKeyword_1_0_6()); 
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalRegularExpressionParser.g:1231:2: ( FullStop )
                    {
                    // InternalRegularExpressionParser.g:1231:2: ( FullStop )
                    // InternalRegularExpressionParser.g:1232:3: FullStop
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterFullStopKeyword_1_0_7()); 
                    }
                    match(input,FullStop,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterFullStopKeyword_1_0_7()); 
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalRegularExpressionParser.g:1237:2: ( Asterisk )
                    {
                    // InternalRegularExpressionParser.g:1237:2: ( Asterisk )
                    // InternalRegularExpressionParser.g:1238:3: Asterisk
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterAsteriskKeyword_1_0_8()); 
                    }
                    match(input,Asterisk,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterAsteriskKeyword_1_0_8()); 
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalRegularExpressionParser.g:1243:2: ( PlusSign )
                    {
                    // InternalRegularExpressionParser.g:1243:2: ( PlusSign )
                    // InternalRegularExpressionParser.g:1244:3: PlusSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterPlusSignKeyword_1_0_9()); 
                    }
                    match(input,PlusSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterPlusSignKeyword_1_0_9()); 
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalRegularExpressionParser.g:1249:2: ( QuestionMark )
                    {
                    // InternalRegularExpressionParser.g:1249:2: ( QuestionMark )
                    // InternalRegularExpressionParser.g:1250:3: QuestionMark
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterQuestionMarkKeyword_1_0_10()); 
                    }
                    match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterQuestionMarkKeyword_1_0_10()); 
                    }

                    }


                    }
                    break;
                case 12 :
                    // InternalRegularExpressionParser.g:1255:2: ( LeftParenthesis )
                    {
                    // InternalRegularExpressionParser.g:1255:2: ( LeftParenthesis )
                    // InternalRegularExpressionParser.g:1256:3: LeftParenthesis
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftParenthesisKeyword_1_0_11()); 
                    }
                    match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftParenthesisKeyword_1_0_11()); 
                    }

                    }


                    }
                    break;
                case 13 :
                    // InternalRegularExpressionParser.g:1261:2: ( RightParenthesis )
                    {
                    // InternalRegularExpressionParser.g:1261:2: ( RightParenthesis )
                    // InternalRegularExpressionParser.g:1262:3: RightParenthesis
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterRightParenthesisKeyword_1_0_12()); 
                    }
                    match(input,RightParenthesis,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterRightParenthesisKeyword_1_0_12()); 
                    }

                    }


                    }
                    break;
                case 14 :
                    // InternalRegularExpressionParser.g:1267:2: ( LeftSquareBracket )
                    {
                    // InternalRegularExpressionParser.g:1267:2: ( LeftSquareBracket )
                    // InternalRegularExpressionParser.g:1268:3: LeftSquareBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftSquareBracketKeyword_1_0_13()); 
                    }
                    match(input,LeftSquareBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftSquareBracketKeyword_1_0_13()); 
                    }

                    }


                    }
                    break;
                case 15 :
                    // InternalRegularExpressionParser.g:1273:2: ( LeftCurlyBracket )
                    {
                    // InternalRegularExpressionParser.g:1273:2: ( LeftCurlyBracket )
                    // InternalRegularExpressionParser.g:1274:3: LeftCurlyBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftCurlyBracketKeyword_1_0_14()); 
                    }
                    match(input,LeftCurlyBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterLeftCurlyBracketKeyword_1_0_14()); 
                    }

                    }


                    }
                    break;
                case 16 :
                    // InternalRegularExpressionParser.g:1279:2: ( RightCurlyBracket )
                    {
                    // InternalRegularExpressionParser.g:1279:2: ( RightCurlyBracket )
                    // InternalRegularExpressionParser.g:1280:3: RightCurlyBracket
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterRightCurlyBracketKeyword_1_0_15()); 
                    }
                    match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterRightCurlyBracketKeyword_1_0_15()); 
                    }

                    }


                    }
                    break;
                case 17 :
                    // InternalRegularExpressionParser.g:1285:2: ( VerticalLine )
                    {
                    // InternalRegularExpressionParser.g:1285:2: ( VerticalLine )
                    // InternalRegularExpressionParser.g:1286:3: VerticalLine
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterVerticalLineKeyword_1_0_16()); 
                    }
                    match(input,VerticalLine,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterVerticalLineKeyword_1_0_16()); 
                    }

                    }


                    }
                    break;
                case 18 :
                    // InternalRegularExpressionParser.g:1291:2: ( Solidus )
                    {
                    // InternalRegularExpressionParser.g:1291:2: ( Solidus )
                    // InternalRegularExpressionParser.g:1292:3: Solidus
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterSolidusKeyword_1_0_17()); 
                    }
                    match(input,Solidus,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterSolidusKeyword_1_0_17()); 
                    }

                    }


                    }
                    break;
                case 19 :
                    // InternalRegularExpressionParser.g:1297:2: ( RULE_PATTERN_CHARACTER_NO_DASH )
                    {
                    // InternalRegularExpressionParser.g:1297:2: ( RULE_PATTERN_CHARACTER_NO_DASH )
                    // InternalRegularExpressionParser.g:1298:3: RULE_PATTERN_CHARACTER_NO_DASH
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterPATTERN_CHARACTER_NO_DASHTerminalRuleCall_1_0_18()); 
                    }
                    match(input,RULE_PATTERN_CHARACTER_NO_DASH,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterPATTERN_CHARACTER_NO_DASHTerminalRuleCall_1_0_18()); 
                    }

                    }


                    }
                    break;
                case 20 :
                    // InternalRegularExpressionParser.g:1303:2: ( RULE_UNICODE_LETTER )
                    {
                    // InternalRegularExpressionParser.g:1303:2: ( RULE_UNICODE_LETTER )
                    // InternalRegularExpressionParser.g:1304:3: RULE_UNICODE_LETTER
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterUNICODE_LETTERTerminalRuleCall_1_0_19()); 
                    }
                    match(input,RULE_UNICODE_LETTER,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterUNICODE_LETTERTerminalRuleCall_1_0_19()); 
                    }

                    }


                    }
                    break;
                case 21 :
                    // InternalRegularExpressionParser.g:1309:2: ( RULE_UNICODE_DIGIT )
                    {
                    // InternalRegularExpressionParser.g:1309:2: ( RULE_UNICODE_DIGIT )
                    // InternalRegularExpressionParser.g:1310:3: RULE_UNICODE_DIGIT
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getCharacterClassAtomAccess().getCharacterUNICODE_DIGITTerminalRuleCall_1_0_20()); 
                    }
                    match(input,RULE_UNICODE_DIGIT,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getCharacterClassAtomAccess().getCharacterUNICODE_DIGITTerminalRuleCall_1_0_20()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassAtom__CharacterAlternatives_1_0"


    // $ANTLR start "rule__EscapedCharacterClassAtom__Alternatives"
    // InternalRegularExpressionParser.g:1319:1: rule__EscapedCharacterClassAtom__Alternatives : ( ( ruleDecimalEscapeSequence ) | ( ruleBackspace ) | ( ruleCharacterEscapeSequence ) | ( ruleControlLetterEscapeSequence ) | ( ruleHexEscapeSequence ) | ( ruleUnicodeEscapeSequence ) | ( ruleIdentityEscapeSequence ) | ( ruleCharacterClassEscapeSequence ) );
    public final void rule__EscapedCharacterClassAtom__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1323:1: ( ( ruleDecimalEscapeSequence ) | ( ruleBackspace ) | ( ruleCharacterEscapeSequence ) | ( ruleControlLetterEscapeSequence ) | ( ruleHexEscapeSequence ) | ( ruleUnicodeEscapeSequence ) | ( ruleIdentityEscapeSequence ) | ( ruleCharacterClassEscapeSequence ) )
            int alt12=8;
            switch ( input.LA(1) ) {
            case RULE_DECIMAL_ESCAPE:
                {
                alt12=1;
                }
                break;
            case RULE_WORD_BOUNDARY:
                {
                alt12=2;
                }
                break;
            case RULE_CONTROL_ESCAPE:
                {
                alt12=3;
                }
                break;
            case RULE_CONTROL_LETTER_ESCAPE:
                {
                alt12=4;
                }
                break;
            case RULE_HEX_ESCAPE:
                {
                alt12=5;
                }
                break;
            case RULE_UNICODE_ESCAPE:
                {
                alt12=6;
                }
                break;
            case RULE_IDENTITY_ESCAPE:
                {
                alt12=7;
                }
                break;
            case RULE_CHARACTER_CLASS_ESCAPE:
                {
                alt12=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalRegularExpressionParser.g:1324:2: ( ruleDecimalEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1324:2: ( ruleDecimalEscapeSequence )
                    // InternalRegularExpressionParser.g:1325:3: ruleDecimalEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getDecimalEscapeSequenceParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleDecimalEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getDecimalEscapeSequenceParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1330:2: ( ruleBackspace )
                    {
                    // InternalRegularExpressionParser.g:1330:2: ( ruleBackspace )
                    // InternalRegularExpressionParser.g:1331:3: ruleBackspace
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getBackspaceParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleBackspace();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getBackspaceParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1336:2: ( ruleCharacterEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1336:2: ( ruleCharacterEscapeSequence )
                    // InternalRegularExpressionParser.g:1337:3: ruleCharacterEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getCharacterEscapeSequenceParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCharacterEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getCharacterEscapeSequenceParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalRegularExpressionParser.g:1342:2: ( ruleControlLetterEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1342:2: ( ruleControlLetterEscapeSequence )
                    // InternalRegularExpressionParser.g:1343:3: ruleControlLetterEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getControlLetterEscapeSequenceParserRuleCall_3()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleControlLetterEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getControlLetterEscapeSequenceParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalRegularExpressionParser.g:1348:2: ( ruleHexEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1348:2: ( ruleHexEscapeSequence )
                    // InternalRegularExpressionParser.g:1349:3: ruleHexEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getHexEscapeSequenceParserRuleCall_4()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleHexEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getHexEscapeSequenceParserRuleCall_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalRegularExpressionParser.g:1354:2: ( ruleUnicodeEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1354:2: ( ruleUnicodeEscapeSequence )
                    // InternalRegularExpressionParser.g:1355:3: ruleUnicodeEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getUnicodeEscapeSequenceParserRuleCall_5()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleUnicodeEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getUnicodeEscapeSequenceParserRuleCall_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalRegularExpressionParser.g:1360:2: ( ruleIdentityEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1360:2: ( ruleIdentityEscapeSequence )
                    // InternalRegularExpressionParser.g:1361:3: ruleIdentityEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getIdentityEscapeSequenceParserRuleCall_6()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleIdentityEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getIdentityEscapeSequenceParserRuleCall_6()); 
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalRegularExpressionParser.g:1366:2: ( ruleCharacterClassEscapeSequence )
                    {
                    // InternalRegularExpressionParser.g:1366:2: ( ruleCharacterClassEscapeSequence )
                    // InternalRegularExpressionParser.g:1367:3: ruleCharacterClassEscapeSequence
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEscapedCharacterClassAtomAccess().getCharacterClassEscapeSequenceParserRuleCall_7()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleCharacterClassEscapeSequence();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEscapedCharacterClassAtomAccess().getCharacterClassEscapeSequenceParserRuleCall_7()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EscapedCharacterClassAtom__Alternatives"


    // $ANTLR start "rule__Quantifier__Alternatives"
    // InternalRegularExpressionParser.g:1376:1: rule__Quantifier__Alternatives : ( ( ruleSimpleQuantifier ) | ( ruleExactQuantifier ) );
    public final void rule__Quantifier__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1380:1: ( ( ruleSimpleQuantifier ) | ( ruleExactQuantifier ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=Asterisk && LA13_0<=PlusSign)||LA13_0==QuestionMark) ) {
                alt13=1;
            }
            else if ( (LA13_0==LeftCurlyBracket) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalRegularExpressionParser.g:1381:2: ( ruleSimpleQuantifier )
                    {
                    // InternalRegularExpressionParser.g:1381:2: ( ruleSimpleQuantifier )
                    // InternalRegularExpressionParser.g:1382:3: ruleSimpleQuantifier
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getQuantifierAccess().getSimpleQuantifierParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleSimpleQuantifier();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getQuantifierAccess().getSimpleQuantifierParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1387:2: ( ruleExactQuantifier )
                    {
                    // InternalRegularExpressionParser.g:1387:2: ( ruleExactQuantifier )
                    // InternalRegularExpressionParser.g:1388:3: ruleExactQuantifier
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getQuantifierAccess().getExactQuantifierParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleExactQuantifier();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getQuantifierAccess().getExactQuantifierParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Quantifier__Alternatives"


    // $ANTLR start "rule__SimpleQuantifier__QuantifierAlternatives_0_0"
    // InternalRegularExpressionParser.g:1397:1: rule__SimpleQuantifier__QuantifierAlternatives_0_0 : ( ( PlusSign ) | ( Asterisk ) | ( QuestionMark ) );
    public final void rule__SimpleQuantifier__QuantifierAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1401:1: ( ( PlusSign ) | ( Asterisk ) | ( QuestionMark ) )
            int alt14=3;
            switch ( input.LA(1) ) {
            case PlusSign:
                {
                alt14=1;
                }
                break;
            case Asterisk:
                {
                alt14=2;
                }
                break;
            case QuestionMark:
                {
                alt14=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalRegularExpressionParser.g:1402:2: ( PlusSign )
                    {
                    // InternalRegularExpressionParser.g:1402:2: ( PlusSign )
                    // InternalRegularExpressionParser.g:1403:3: PlusSign
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSimpleQuantifierAccess().getQuantifierPlusSignKeyword_0_0_0()); 
                    }
                    match(input,PlusSign,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSimpleQuantifierAccess().getQuantifierPlusSignKeyword_0_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1408:2: ( Asterisk )
                    {
                    // InternalRegularExpressionParser.g:1408:2: ( Asterisk )
                    // InternalRegularExpressionParser.g:1409:3: Asterisk
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSimpleQuantifierAccess().getQuantifierAsteriskKeyword_0_0_1()); 
                    }
                    match(input,Asterisk,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSimpleQuantifierAccess().getQuantifierAsteriskKeyword_0_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalRegularExpressionParser.g:1414:2: ( QuestionMark )
                    {
                    // InternalRegularExpressionParser.g:1414:2: ( QuestionMark )
                    // InternalRegularExpressionParser.g:1415:3: QuestionMark
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSimpleQuantifierAccess().getQuantifierQuestionMarkKeyword_0_0_2()); 
                    }
                    match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSimpleQuantifierAccess().getQuantifierQuestionMarkKeyword_0_0_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__QuantifierAlternatives_0_0"


    // $ANTLR start "rule__ExactQuantifier__Alternatives_3"
    // InternalRegularExpressionParser.g:1424:1: rule__ExactQuantifier__Alternatives_3 : ( ( ( rule__ExactQuantifier__Group_3_0__0 ) ) | ( ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 ) ) );
    public final void rule__ExactQuantifier__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1428:1: ( ( ( rule__ExactQuantifier__Group_3_0__0 ) ) | ( ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==Comma) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==RULE_UNICODE_DIGIT) ) {
                    alt15=1;
                }
                else if ( (LA15_1==EOF||LA15_1==RightCurlyBracket) ) {
                    alt15=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalRegularExpressionParser.g:1429:2: ( ( rule__ExactQuantifier__Group_3_0__0 ) )
                    {
                    // InternalRegularExpressionParser.g:1429:2: ( ( rule__ExactQuantifier__Group_3_0__0 ) )
                    // InternalRegularExpressionParser.g:1430:3: ( rule__ExactQuantifier__Group_3_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExactQuantifierAccess().getGroup_3_0()); 
                    }
                    // InternalRegularExpressionParser.g:1431:3: ( rule__ExactQuantifier__Group_3_0__0 )
                    // InternalRegularExpressionParser.g:1431:4: rule__ExactQuantifier__Group_3_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ExactQuantifier__Group_3_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExactQuantifierAccess().getGroup_3_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1435:2: ( ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 ) )
                    {
                    // InternalRegularExpressionParser.g:1435:2: ( ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 ) )
                    // InternalRegularExpressionParser.g:1436:3: ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExactQuantifierAccess().getUnboundedMaxAssignment_3_1()); 
                    }
                    // InternalRegularExpressionParser.g:1437:3: ( rule__ExactQuantifier__UnboundedMaxAssignment_3_1 )
                    // InternalRegularExpressionParser.g:1437:4: rule__ExactQuantifier__UnboundedMaxAssignment_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ExactQuantifier__UnboundedMaxAssignment_3_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExactQuantifierAccess().getUnboundedMaxAssignment_3_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Alternatives_3"


    // $ANTLR start "rule__RegularExpressionFlags__FlagsAlternatives_1_0"
    // InternalRegularExpressionParser.g:1445:1: rule__RegularExpressionFlags__FlagsAlternatives_1_0 : ( ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_ESCAPE ) );
    public final void rule__RegularExpressionFlags__FlagsAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1449:1: ( ( RULE_UNICODE_LETTER ) | ( RULE_UNICODE_ESCAPE ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_UNICODE_LETTER) ) {
                alt16=1;
            }
            else if ( (LA16_0==RULE_UNICODE_ESCAPE) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalRegularExpressionParser.g:1450:2: ( RULE_UNICODE_LETTER )
                    {
                    // InternalRegularExpressionParser.g:1450:2: ( RULE_UNICODE_LETTER )
                    // InternalRegularExpressionParser.g:1451:3: RULE_UNICODE_LETTER
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRegularExpressionFlagsAccess().getFlagsUNICODE_LETTERTerminalRuleCall_1_0_0()); 
                    }
                    match(input,RULE_UNICODE_LETTER,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRegularExpressionFlagsAccess().getFlagsUNICODE_LETTERTerminalRuleCall_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalRegularExpressionParser.g:1456:2: ( RULE_UNICODE_ESCAPE )
                    {
                    // InternalRegularExpressionParser.g:1456:2: ( RULE_UNICODE_ESCAPE )
                    // InternalRegularExpressionParser.g:1457:3: RULE_UNICODE_ESCAPE
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getRegularExpressionFlagsAccess().getFlagsUNICODE_ESCAPETerminalRuleCall_1_0_1()); 
                    }
                    match(input,RULE_UNICODE_ESCAPE,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getRegularExpressionFlagsAccess().getFlagsUNICODE_ESCAPETerminalRuleCall_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__FlagsAlternatives_1_0"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__0"
    // InternalRegularExpressionParser.g:1466:1: rule__RegularExpressionLiteral__Group__0 : rule__RegularExpressionLiteral__Group__0__Impl rule__RegularExpressionLiteral__Group__1 ;
    public final void rule__RegularExpressionLiteral__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1470:1: ( rule__RegularExpressionLiteral__Group__0__Impl rule__RegularExpressionLiteral__Group__1 )
            // InternalRegularExpressionParser.g:1471:2: rule__RegularExpressionLiteral__Group__0__Impl rule__RegularExpressionLiteral__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__RegularExpressionLiteral__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__0"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__0__Impl"
    // InternalRegularExpressionParser.g:1478:1: rule__RegularExpressionLiteral__Group__0__Impl : ( Solidus ) ;
    public final void rule__RegularExpressionLiteral__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1482:1: ( ( Solidus ) )
            // InternalRegularExpressionParser.g:1483:1: ( Solidus )
            {
            // InternalRegularExpressionParser.g:1483:1: ( Solidus )
            // InternalRegularExpressionParser.g:1484:2: Solidus
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getSolidusKeyword_0()); 
            }
            match(input,Solidus,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getSolidusKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__0__Impl"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__1"
    // InternalRegularExpressionParser.g:1493:1: rule__RegularExpressionLiteral__Group__1 : rule__RegularExpressionLiteral__Group__1__Impl rule__RegularExpressionLiteral__Group__2 ;
    public final void rule__RegularExpressionLiteral__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1497:1: ( rule__RegularExpressionLiteral__Group__1__Impl rule__RegularExpressionLiteral__Group__2 )
            // InternalRegularExpressionParser.g:1498:2: rule__RegularExpressionLiteral__Group__1__Impl rule__RegularExpressionLiteral__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__RegularExpressionLiteral__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__1"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__1__Impl"
    // InternalRegularExpressionParser.g:1505:1: rule__RegularExpressionLiteral__Group__1__Impl : ( ( rule__RegularExpressionLiteral__BodyAssignment_1 ) ) ;
    public final void rule__RegularExpressionLiteral__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1509:1: ( ( ( rule__RegularExpressionLiteral__BodyAssignment_1 ) ) )
            // InternalRegularExpressionParser.g:1510:1: ( ( rule__RegularExpressionLiteral__BodyAssignment_1 ) )
            {
            // InternalRegularExpressionParser.g:1510:1: ( ( rule__RegularExpressionLiteral__BodyAssignment_1 ) )
            // InternalRegularExpressionParser.g:1511:2: ( rule__RegularExpressionLiteral__BodyAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getBodyAssignment_1()); 
            }
            // InternalRegularExpressionParser.g:1512:2: ( rule__RegularExpressionLiteral__BodyAssignment_1 )
            // InternalRegularExpressionParser.g:1512:3: rule__RegularExpressionLiteral__BodyAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__BodyAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getBodyAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__1__Impl"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__2"
    // InternalRegularExpressionParser.g:1520:1: rule__RegularExpressionLiteral__Group__2 : rule__RegularExpressionLiteral__Group__2__Impl rule__RegularExpressionLiteral__Group__3 ;
    public final void rule__RegularExpressionLiteral__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1524:1: ( rule__RegularExpressionLiteral__Group__2__Impl rule__RegularExpressionLiteral__Group__3 )
            // InternalRegularExpressionParser.g:1525:2: rule__RegularExpressionLiteral__Group__2__Impl rule__RegularExpressionLiteral__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__RegularExpressionLiteral__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__2"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__2__Impl"
    // InternalRegularExpressionParser.g:1532:1: rule__RegularExpressionLiteral__Group__2__Impl : ( Solidus ) ;
    public final void rule__RegularExpressionLiteral__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1536:1: ( ( Solidus ) )
            // InternalRegularExpressionParser.g:1537:1: ( Solidus )
            {
            // InternalRegularExpressionParser.g:1537:1: ( Solidus )
            // InternalRegularExpressionParser.g:1538:2: Solidus
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getSolidusKeyword_2()); 
            }
            match(input,Solidus,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getSolidusKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__2__Impl"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__3"
    // InternalRegularExpressionParser.g:1547:1: rule__RegularExpressionLiteral__Group__3 : rule__RegularExpressionLiteral__Group__3__Impl ;
    public final void rule__RegularExpressionLiteral__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1551:1: ( rule__RegularExpressionLiteral__Group__3__Impl )
            // InternalRegularExpressionParser.g:1552:2: rule__RegularExpressionLiteral__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__3"


    // $ANTLR start "rule__RegularExpressionLiteral__Group__3__Impl"
    // InternalRegularExpressionParser.g:1558:1: rule__RegularExpressionLiteral__Group__3__Impl : ( ( rule__RegularExpressionLiteral__FlagsAssignment_3 ) ) ;
    public final void rule__RegularExpressionLiteral__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1562:1: ( ( ( rule__RegularExpressionLiteral__FlagsAssignment_3 ) ) )
            // InternalRegularExpressionParser.g:1563:1: ( ( rule__RegularExpressionLiteral__FlagsAssignment_3 ) )
            {
            // InternalRegularExpressionParser.g:1563:1: ( ( rule__RegularExpressionLiteral__FlagsAssignment_3 ) )
            // InternalRegularExpressionParser.g:1564:2: ( rule__RegularExpressionLiteral__FlagsAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getFlagsAssignment_3()); 
            }
            // InternalRegularExpressionParser.g:1565:2: ( rule__RegularExpressionLiteral__FlagsAssignment_3 )
            // InternalRegularExpressionParser.g:1565:3: rule__RegularExpressionLiteral__FlagsAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionLiteral__FlagsAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getFlagsAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__Group__3__Impl"


    // $ANTLR start "rule__Disjunction__Group_0__0"
    // InternalRegularExpressionParser.g:1574:1: rule__Disjunction__Group_0__0 : rule__Disjunction__Group_0__0__Impl rule__Disjunction__Group_0__1 ;
    public final void rule__Disjunction__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1578:1: ( rule__Disjunction__Group_0__0__Impl rule__Disjunction__Group_0__1 )
            // InternalRegularExpressionParser.g:1579:2: rule__Disjunction__Group_0__0__Impl rule__Disjunction__Group_0__1
            {
            pushFollow(FOLLOW_7);
            rule__Disjunction__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0__0"


    // $ANTLR start "rule__Disjunction__Group_0__0__Impl"
    // InternalRegularExpressionParser.g:1586:1: rule__Disjunction__Group_0__0__Impl : ( ruleAlternative ) ;
    public final void rule__Disjunction__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1590:1: ( ( ruleAlternative ) )
            // InternalRegularExpressionParser.g:1591:1: ( ruleAlternative )
            {
            // InternalRegularExpressionParser.g:1591:1: ( ruleAlternative )
            // InternalRegularExpressionParser.g:1592:2: ruleAlternative
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getAlternativeParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAlternative();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getAlternativeParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_0__1"
    // InternalRegularExpressionParser.g:1601:1: rule__Disjunction__Group_0__1 : rule__Disjunction__Group_0__1__Impl ;
    public final void rule__Disjunction__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1605:1: ( rule__Disjunction__Group_0__1__Impl )
            // InternalRegularExpressionParser.g:1606:2: rule__Disjunction__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0__1"


    // $ANTLR start "rule__Disjunction__Group_0__1__Impl"
    // InternalRegularExpressionParser.g:1612:1: rule__Disjunction__Group_0__1__Impl : ( ( rule__Disjunction__Group_0_1__0 )? ) ;
    public final void rule__Disjunction__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1616:1: ( ( ( rule__Disjunction__Group_0_1__0 )? ) )
            // InternalRegularExpressionParser.g:1617:1: ( ( rule__Disjunction__Group_0_1__0 )? )
            {
            // InternalRegularExpressionParser.g:1617:1: ( ( rule__Disjunction__Group_0_1__0 )? )
            // InternalRegularExpressionParser.g:1618:2: ( rule__Disjunction__Group_0_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_0_1()); 
            }
            // InternalRegularExpressionParser.g:1619:2: ( rule__Disjunction__Group_0_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==VerticalLine) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalRegularExpressionParser.g:1619:3: rule__Disjunction__Group_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Disjunction__Group_0_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_0_1__0"
    // InternalRegularExpressionParser.g:1628:1: rule__Disjunction__Group_0_1__0 : rule__Disjunction__Group_0_1__0__Impl rule__Disjunction__Group_0_1__1 ;
    public final void rule__Disjunction__Group_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1632:1: ( rule__Disjunction__Group_0_1__0__Impl rule__Disjunction__Group_0_1__1 )
            // InternalRegularExpressionParser.g:1633:2: rule__Disjunction__Group_0_1__0__Impl rule__Disjunction__Group_0_1__1
            {
            pushFollow(FOLLOW_7);
            rule__Disjunction__Group_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1__0"


    // $ANTLR start "rule__Disjunction__Group_0_1__0__Impl"
    // InternalRegularExpressionParser.g:1640:1: rule__Disjunction__Group_0_1__0__Impl : ( () ) ;
    public final void rule__Disjunction__Group_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1644:1: ( ( () ) )
            // InternalRegularExpressionParser.g:1645:1: ( () )
            {
            // InternalRegularExpressionParser.g:1645:1: ( () )
            // InternalRegularExpressionParser.g:1646:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getDisjunctionElementsAction_0_1_0()); 
            }
            // InternalRegularExpressionParser.g:1647:2: ()
            // InternalRegularExpressionParser.g:1647:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getDisjunctionElementsAction_0_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_0_1__1"
    // InternalRegularExpressionParser.g:1655:1: rule__Disjunction__Group_0_1__1 : rule__Disjunction__Group_0_1__1__Impl ;
    public final void rule__Disjunction__Group_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1659:1: ( rule__Disjunction__Group_0_1__1__Impl )
            // InternalRegularExpressionParser.g:1660:2: rule__Disjunction__Group_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1__1"


    // $ANTLR start "rule__Disjunction__Group_0_1__1__Impl"
    // InternalRegularExpressionParser.g:1666:1: rule__Disjunction__Group_0_1__1__Impl : ( ( ( rule__Disjunction__Group_0_1_1__0 ) ) ( ( rule__Disjunction__Group_0_1_1__0 )* ) ) ;
    public final void rule__Disjunction__Group_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1670:1: ( ( ( ( rule__Disjunction__Group_0_1_1__0 ) ) ( ( rule__Disjunction__Group_0_1_1__0 )* ) ) )
            // InternalRegularExpressionParser.g:1671:1: ( ( ( rule__Disjunction__Group_0_1_1__0 ) ) ( ( rule__Disjunction__Group_0_1_1__0 )* ) )
            {
            // InternalRegularExpressionParser.g:1671:1: ( ( ( rule__Disjunction__Group_0_1_1__0 ) ) ( ( rule__Disjunction__Group_0_1_1__0 )* ) )
            // InternalRegularExpressionParser.g:1672:2: ( ( rule__Disjunction__Group_0_1_1__0 ) ) ( ( rule__Disjunction__Group_0_1_1__0 )* )
            {
            // InternalRegularExpressionParser.g:1672:2: ( ( rule__Disjunction__Group_0_1_1__0 ) )
            // InternalRegularExpressionParser.g:1673:3: ( rule__Disjunction__Group_0_1_1__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_0_1_1()); 
            }
            // InternalRegularExpressionParser.g:1674:3: ( rule__Disjunction__Group_0_1_1__0 )
            // InternalRegularExpressionParser.g:1674:4: rule__Disjunction__Group_0_1_1__0
            {
            pushFollow(FOLLOW_8);
            rule__Disjunction__Group_0_1_1__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup_0_1_1()); 
            }

            }

            // InternalRegularExpressionParser.g:1677:2: ( ( rule__Disjunction__Group_0_1_1__0 )* )
            // InternalRegularExpressionParser.g:1678:3: ( rule__Disjunction__Group_0_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_0_1_1()); 
            }
            // InternalRegularExpressionParser.g:1679:3: ( rule__Disjunction__Group_0_1_1__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==VerticalLine) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:1679:4: rule__Disjunction__Group_0_1_1__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Disjunction__Group_0_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup_0_1_1()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_0_1_1__0"
    // InternalRegularExpressionParser.g:1689:1: rule__Disjunction__Group_0_1_1__0 : rule__Disjunction__Group_0_1_1__0__Impl rule__Disjunction__Group_0_1_1__1 ;
    public final void rule__Disjunction__Group_0_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1693:1: ( rule__Disjunction__Group_0_1_1__0__Impl rule__Disjunction__Group_0_1_1__1 )
            // InternalRegularExpressionParser.g:1694:2: rule__Disjunction__Group_0_1_1__0__Impl rule__Disjunction__Group_0_1_1__1
            {
            pushFollow(FOLLOW_9);
            rule__Disjunction__Group_0_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1_1__0"


    // $ANTLR start "rule__Disjunction__Group_0_1_1__0__Impl"
    // InternalRegularExpressionParser.g:1701:1: rule__Disjunction__Group_0_1_1__0__Impl : ( VerticalLine ) ;
    public final void rule__Disjunction__Group_0_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1705:1: ( ( VerticalLine ) )
            // InternalRegularExpressionParser.g:1706:1: ( VerticalLine )
            {
            // InternalRegularExpressionParser.g:1706:1: ( VerticalLine )
            // InternalRegularExpressionParser.g:1707:2: VerticalLine
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getVerticalLineKeyword_0_1_1_0()); 
            }
            match(input,VerticalLine,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getVerticalLineKeyword_0_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1_1__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_0_1_1__1"
    // InternalRegularExpressionParser.g:1716:1: rule__Disjunction__Group_0_1_1__1 : rule__Disjunction__Group_0_1_1__1__Impl ;
    public final void rule__Disjunction__Group_0_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1720:1: ( rule__Disjunction__Group_0_1_1__1__Impl )
            // InternalRegularExpressionParser.g:1721:2: rule__Disjunction__Group_0_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_0_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1_1__1"


    // $ANTLR start "rule__Disjunction__Group_0_1_1__1__Impl"
    // InternalRegularExpressionParser.g:1727:1: rule__Disjunction__Group_0_1_1__1__Impl : ( ( rule__Disjunction__ElementsAssignment_0_1_1_1 )? ) ;
    public final void rule__Disjunction__Group_0_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1731:1: ( ( ( rule__Disjunction__ElementsAssignment_0_1_1_1 )? ) )
            // InternalRegularExpressionParser.g:1732:1: ( ( rule__Disjunction__ElementsAssignment_0_1_1_1 )? )
            {
            // InternalRegularExpressionParser.g:1732:1: ( ( rule__Disjunction__ElementsAssignment_0_1_1_1 )? )
            // InternalRegularExpressionParser.g:1733:2: ( rule__Disjunction__ElementsAssignment_0_1_1_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getElementsAssignment_0_1_1_1()); 
            }
            // InternalRegularExpressionParser.g:1734:2: ( rule__Disjunction__ElementsAssignment_0_1_1_1 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=ExclamationMark && LA19_0<=LeftParenthesis)||(LA19_0>=Comma && LA19_0<=FullStop)||(LA19_0>=Colon && LA19_0<=EqualsSign)||(LA19_0>=LeftSquareBracket && LA19_0<=LeftCurlyBracket)||(LA19_0>=RightCurlyBracket && LA19_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA19_0>=RULE_HEX_ESCAPE && LA19_0<=RULE_UNICODE_ESCAPE)||(LA19_0>=RULE_DECIMAL_ESCAPE && LA19_0<=RULE_IDENTITY_ESCAPE)||LA19_0==RULE_UNICODE_DIGIT||(LA19_0>=RULE_UNICODE_LETTER && LA19_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalRegularExpressionParser.g:1734:3: rule__Disjunction__ElementsAssignment_0_1_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Disjunction__ElementsAssignment_0_1_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getElementsAssignment_0_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_0_1_1__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_1__0"
    // InternalRegularExpressionParser.g:1743:1: rule__Disjunction__Group_1__0 : rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 ;
    public final void rule__Disjunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1747:1: ( rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 )
            // InternalRegularExpressionParser.g:1748:2: rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__Disjunction__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__0"


    // $ANTLR start "rule__Disjunction__Group_1__0__Impl"
    // InternalRegularExpressionParser.g:1755:1: rule__Disjunction__Group_1__0__Impl : ( () ) ;
    public final void rule__Disjunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1759:1: ( ( () ) )
            // InternalRegularExpressionParser.g:1760:1: ( () )
            {
            // InternalRegularExpressionParser.g:1760:1: ( () )
            // InternalRegularExpressionParser.g:1761:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getDisjunctionAction_1_0()); 
            }
            // InternalRegularExpressionParser.g:1762:2: ()
            // InternalRegularExpressionParser.g:1762:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getDisjunctionAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_1__1"
    // InternalRegularExpressionParser.g:1770:1: rule__Disjunction__Group_1__1 : rule__Disjunction__Group_1__1__Impl ;
    public final void rule__Disjunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1774:1: ( rule__Disjunction__Group_1__1__Impl )
            // InternalRegularExpressionParser.g:1775:2: rule__Disjunction__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__1"


    // $ANTLR start "rule__Disjunction__Group_1__1__Impl"
    // InternalRegularExpressionParser.g:1781:1: rule__Disjunction__Group_1__1__Impl : ( ( rule__Disjunction__Group_1_1__0 )* ) ;
    public final void rule__Disjunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1785:1: ( ( ( rule__Disjunction__Group_1_1__0 )* ) )
            // InternalRegularExpressionParser.g:1786:1: ( ( rule__Disjunction__Group_1_1__0 )* )
            {
            // InternalRegularExpressionParser.g:1786:1: ( ( rule__Disjunction__Group_1_1__0 )* )
            // InternalRegularExpressionParser.g:1787:2: ( rule__Disjunction__Group_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_1_1()); 
            }
            // InternalRegularExpressionParser.g:1788:2: ( rule__Disjunction__Group_1_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==VerticalLine) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:1788:3: rule__Disjunction__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Disjunction__Group_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_1_1__0"
    // InternalRegularExpressionParser.g:1797:1: rule__Disjunction__Group_1_1__0 : rule__Disjunction__Group_1_1__0__Impl rule__Disjunction__Group_1_1__1 ;
    public final void rule__Disjunction__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1801:1: ( rule__Disjunction__Group_1_1__0__Impl rule__Disjunction__Group_1_1__1 )
            // InternalRegularExpressionParser.g:1802:2: rule__Disjunction__Group_1_1__0__Impl rule__Disjunction__Group_1_1__1
            {
            pushFollow(FOLLOW_9);
            rule__Disjunction__Group_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1_1__0"


    // $ANTLR start "rule__Disjunction__Group_1_1__0__Impl"
    // InternalRegularExpressionParser.g:1809:1: rule__Disjunction__Group_1_1__0__Impl : ( VerticalLine ) ;
    public final void rule__Disjunction__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1813:1: ( ( VerticalLine ) )
            // InternalRegularExpressionParser.g:1814:1: ( VerticalLine )
            {
            // InternalRegularExpressionParser.g:1814:1: ( VerticalLine )
            // InternalRegularExpressionParser.g:1815:2: VerticalLine
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getVerticalLineKeyword_1_1_0()); 
            }
            match(input,VerticalLine,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getVerticalLineKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1_1__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_1_1__1"
    // InternalRegularExpressionParser.g:1824:1: rule__Disjunction__Group_1_1__1 : rule__Disjunction__Group_1_1__1__Impl ;
    public final void rule__Disjunction__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1828:1: ( rule__Disjunction__Group_1_1__1__Impl )
            // InternalRegularExpressionParser.g:1829:2: rule__Disjunction__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1_1__1"


    // $ANTLR start "rule__Disjunction__Group_1_1__1__Impl"
    // InternalRegularExpressionParser.g:1835:1: rule__Disjunction__Group_1_1__1__Impl : ( ( rule__Disjunction__ElementsAssignment_1_1_1 )? ) ;
    public final void rule__Disjunction__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1839:1: ( ( ( rule__Disjunction__ElementsAssignment_1_1_1 )? ) )
            // InternalRegularExpressionParser.g:1840:1: ( ( rule__Disjunction__ElementsAssignment_1_1_1 )? )
            {
            // InternalRegularExpressionParser.g:1840:1: ( ( rule__Disjunction__ElementsAssignment_1_1_1 )? )
            // InternalRegularExpressionParser.g:1841:2: ( rule__Disjunction__ElementsAssignment_1_1_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getElementsAssignment_1_1_1()); 
            }
            // InternalRegularExpressionParser.g:1842:2: ( rule__Disjunction__ElementsAssignment_1_1_1 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=ExclamationMark && LA21_0<=LeftParenthesis)||(LA21_0>=Comma && LA21_0<=FullStop)||(LA21_0>=Colon && LA21_0<=EqualsSign)||(LA21_0>=LeftSquareBracket && LA21_0<=LeftCurlyBracket)||(LA21_0>=RightCurlyBracket && LA21_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA21_0>=RULE_HEX_ESCAPE && LA21_0<=RULE_UNICODE_ESCAPE)||(LA21_0>=RULE_DECIMAL_ESCAPE && LA21_0<=RULE_IDENTITY_ESCAPE)||LA21_0==RULE_UNICODE_DIGIT||(LA21_0>=RULE_UNICODE_LETTER && LA21_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalRegularExpressionParser.g:1842:3: rule__Disjunction__ElementsAssignment_1_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Disjunction__ElementsAssignment_1_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getElementsAssignment_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1_1__1__Impl"


    // $ANTLR start "rule__Alternative__Group__0"
    // InternalRegularExpressionParser.g:1851:1: rule__Alternative__Group__0 : rule__Alternative__Group__0__Impl rule__Alternative__Group__1 ;
    public final void rule__Alternative__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1855:1: ( rule__Alternative__Group__0__Impl rule__Alternative__Group__1 )
            // InternalRegularExpressionParser.g:1856:2: rule__Alternative__Group__0__Impl rule__Alternative__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Alternative__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Alternative__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group__0"


    // $ANTLR start "rule__Alternative__Group__0__Impl"
    // InternalRegularExpressionParser.g:1863:1: rule__Alternative__Group__0__Impl : ( ruleTerm ) ;
    public final void rule__Alternative__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1867:1: ( ( ruleTerm ) )
            // InternalRegularExpressionParser.g:1868:1: ( ruleTerm )
            {
            // InternalRegularExpressionParser.g:1868:1: ( ruleTerm )
            // InternalRegularExpressionParser.g:1869:2: ruleTerm
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getTermParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleTerm();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getTermParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group__0__Impl"


    // $ANTLR start "rule__Alternative__Group__1"
    // InternalRegularExpressionParser.g:1878:1: rule__Alternative__Group__1 : rule__Alternative__Group__1__Impl ;
    public final void rule__Alternative__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1882:1: ( rule__Alternative__Group__1__Impl )
            // InternalRegularExpressionParser.g:1883:2: rule__Alternative__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Alternative__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group__1"


    // $ANTLR start "rule__Alternative__Group__1__Impl"
    // InternalRegularExpressionParser.g:1889:1: rule__Alternative__Group__1__Impl : ( ( rule__Alternative__Group_1__0 )? ) ;
    public final void rule__Alternative__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1893:1: ( ( ( rule__Alternative__Group_1__0 )? ) )
            // InternalRegularExpressionParser.g:1894:1: ( ( rule__Alternative__Group_1__0 )? )
            {
            // InternalRegularExpressionParser.g:1894:1: ( ( rule__Alternative__Group_1__0 )? )
            // InternalRegularExpressionParser.g:1895:2: ( rule__Alternative__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getGroup_1()); 
            }
            // InternalRegularExpressionParser.g:1896:2: ( rule__Alternative__Group_1__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=ExclamationMark && LA22_0<=LeftParenthesis)||(LA22_0>=Comma && LA22_0<=FullStop)||(LA22_0>=Colon && LA22_0<=EqualsSign)||(LA22_0>=LeftSquareBracket && LA22_0<=LeftCurlyBracket)||(LA22_0>=RightCurlyBracket && LA22_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA22_0>=RULE_HEX_ESCAPE && LA22_0<=RULE_UNICODE_ESCAPE)||(LA22_0>=RULE_DECIMAL_ESCAPE && LA22_0<=RULE_IDENTITY_ESCAPE)||LA22_0==RULE_UNICODE_DIGIT||(LA22_0>=RULE_UNICODE_LETTER && LA22_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalRegularExpressionParser.g:1896:3: rule__Alternative__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Alternative__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group__1__Impl"


    // $ANTLR start "rule__Alternative__Group_1__0"
    // InternalRegularExpressionParser.g:1905:1: rule__Alternative__Group_1__0 : rule__Alternative__Group_1__0__Impl rule__Alternative__Group_1__1 ;
    public final void rule__Alternative__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1909:1: ( rule__Alternative__Group_1__0__Impl rule__Alternative__Group_1__1 )
            // InternalRegularExpressionParser.g:1910:2: rule__Alternative__Group_1__0__Impl rule__Alternative__Group_1__1
            {
            pushFollow(FOLLOW_9);
            rule__Alternative__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Alternative__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group_1__0"


    // $ANTLR start "rule__Alternative__Group_1__0__Impl"
    // InternalRegularExpressionParser.g:1917:1: rule__Alternative__Group_1__0__Impl : ( () ) ;
    public final void rule__Alternative__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1921:1: ( ( () ) )
            // InternalRegularExpressionParser.g:1922:1: ( () )
            {
            // InternalRegularExpressionParser.g:1922:1: ( () )
            // InternalRegularExpressionParser.g:1923:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getSequenceElementsAction_1_0()); 
            }
            // InternalRegularExpressionParser.g:1924:2: ()
            // InternalRegularExpressionParser.g:1924:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getSequenceElementsAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group_1__0__Impl"


    // $ANTLR start "rule__Alternative__Group_1__1"
    // InternalRegularExpressionParser.g:1932:1: rule__Alternative__Group_1__1 : rule__Alternative__Group_1__1__Impl ;
    public final void rule__Alternative__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1936:1: ( rule__Alternative__Group_1__1__Impl )
            // InternalRegularExpressionParser.g:1937:2: rule__Alternative__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Alternative__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group_1__1"


    // $ANTLR start "rule__Alternative__Group_1__1__Impl"
    // InternalRegularExpressionParser.g:1943:1: rule__Alternative__Group_1__1__Impl : ( ( ( rule__Alternative__ElementsAssignment_1_1 ) ) ( ( rule__Alternative__ElementsAssignment_1_1 )* ) ) ;
    public final void rule__Alternative__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1947:1: ( ( ( ( rule__Alternative__ElementsAssignment_1_1 ) ) ( ( rule__Alternative__ElementsAssignment_1_1 )* ) ) )
            // InternalRegularExpressionParser.g:1948:1: ( ( ( rule__Alternative__ElementsAssignment_1_1 ) ) ( ( rule__Alternative__ElementsAssignment_1_1 )* ) )
            {
            // InternalRegularExpressionParser.g:1948:1: ( ( ( rule__Alternative__ElementsAssignment_1_1 ) ) ( ( rule__Alternative__ElementsAssignment_1_1 )* ) )
            // InternalRegularExpressionParser.g:1949:2: ( ( rule__Alternative__ElementsAssignment_1_1 ) ) ( ( rule__Alternative__ElementsAssignment_1_1 )* )
            {
            // InternalRegularExpressionParser.g:1949:2: ( ( rule__Alternative__ElementsAssignment_1_1 ) )
            // InternalRegularExpressionParser.g:1950:3: ( rule__Alternative__ElementsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getElementsAssignment_1_1()); 
            }
            // InternalRegularExpressionParser.g:1951:3: ( rule__Alternative__ElementsAssignment_1_1 )
            // InternalRegularExpressionParser.g:1951:4: rule__Alternative__ElementsAssignment_1_1
            {
            pushFollow(FOLLOW_10);
            rule__Alternative__ElementsAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getElementsAssignment_1_1()); 
            }

            }

            // InternalRegularExpressionParser.g:1954:2: ( ( rule__Alternative__ElementsAssignment_1_1 )* )
            // InternalRegularExpressionParser.g:1955:3: ( rule__Alternative__ElementsAssignment_1_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getElementsAssignment_1_1()); 
            }
            // InternalRegularExpressionParser.g:1956:3: ( rule__Alternative__ElementsAssignment_1_1 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=ExclamationMark && LA23_0<=LeftParenthesis)||(LA23_0>=Comma && LA23_0<=FullStop)||(LA23_0>=Colon && LA23_0<=EqualsSign)||(LA23_0>=LeftSquareBracket && LA23_0<=LeftCurlyBracket)||(LA23_0>=RightCurlyBracket && LA23_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA23_0>=RULE_HEX_ESCAPE && LA23_0<=RULE_UNICODE_ESCAPE)||(LA23_0>=RULE_DECIMAL_ESCAPE && LA23_0<=RULE_IDENTITY_ESCAPE)||LA23_0==RULE_UNICODE_DIGIT||(LA23_0>=RULE_UNICODE_LETTER && LA23_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:1956:4: rule__Alternative__ElementsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__Alternative__ElementsAssignment_1_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getElementsAssignment_1_1()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__Group_1__1__Impl"


    // $ANTLR start "rule__Term__Group_1__0"
    // InternalRegularExpressionParser.g:1966:1: rule__Term__Group_1__0 : rule__Term__Group_1__0__Impl rule__Term__Group_1__1 ;
    public final void rule__Term__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1970:1: ( rule__Term__Group_1__0__Impl rule__Term__Group_1__1 )
            // InternalRegularExpressionParser.g:1971:2: rule__Term__Group_1__0__Impl rule__Term__Group_1__1
            {
            pushFollow(FOLLOW_11);
            rule__Term__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Term__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__Group_1__0"


    // $ANTLR start "rule__Term__Group_1__0__Impl"
    // InternalRegularExpressionParser.g:1978:1: rule__Term__Group_1__0__Impl : ( ruleAtom ) ;
    public final void rule__Term__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1982:1: ( ( ruleAtom ) )
            // InternalRegularExpressionParser.g:1983:1: ( ruleAtom )
            {
            // InternalRegularExpressionParser.g:1983:1: ( ruleAtom )
            // InternalRegularExpressionParser.g:1984:2: ruleAtom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTermAccess().getAtomParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTermAccess().getAtomParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__Group_1__0__Impl"


    // $ANTLR start "rule__Term__Group_1__1"
    // InternalRegularExpressionParser.g:1993:1: rule__Term__Group_1__1 : rule__Term__Group_1__1__Impl ;
    public final void rule__Term__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:1997:1: ( rule__Term__Group_1__1__Impl )
            // InternalRegularExpressionParser.g:1998:2: rule__Term__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Term__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__Group_1__1"


    // $ANTLR start "rule__Term__Group_1__1__Impl"
    // InternalRegularExpressionParser.g:2004:1: rule__Term__Group_1__1__Impl : ( ( rule__Term__QuantifierAssignment_1_1 )? ) ;
    public final void rule__Term__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2008:1: ( ( ( rule__Term__QuantifierAssignment_1_1 )? ) )
            // InternalRegularExpressionParser.g:2009:1: ( ( rule__Term__QuantifierAssignment_1_1 )? )
            {
            // InternalRegularExpressionParser.g:2009:1: ( ( rule__Term__QuantifierAssignment_1_1 )? )
            // InternalRegularExpressionParser.g:2010:2: ( rule__Term__QuantifierAssignment_1_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTermAccess().getQuantifierAssignment_1_1()); 
            }
            // InternalRegularExpressionParser.g:2011:2: ( rule__Term__QuantifierAssignment_1_1 )?
            int alt24=2;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // InternalRegularExpressionParser.g:2011:3: rule__Term__QuantifierAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Term__QuantifierAssignment_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTermAccess().getQuantifierAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__Group_1__1__Impl"


    // $ANTLR start "rule__LineStart__Group__0"
    // InternalRegularExpressionParser.g:2020:1: rule__LineStart__Group__0 : rule__LineStart__Group__0__Impl rule__LineStart__Group__1 ;
    public final void rule__LineStart__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2024:1: ( rule__LineStart__Group__0__Impl rule__LineStart__Group__1 )
            // InternalRegularExpressionParser.g:2025:2: rule__LineStart__Group__0__Impl rule__LineStart__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__LineStart__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LineStart__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineStart__Group__0"


    // $ANTLR start "rule__LineStart__Group__0__Impl"
    // InternalRegularExpressionParser.g:2032:1: rule__LineStart__Group__0__Impl : ( () ) ;
    public final void rule__LineStart__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2036:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2037:1: ( () )
            {
            // InternalRegularExpressionParser.g:2037:1: ( () )
            // InternalRegularExpressionParser.g:2038:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineStartAccess().getLineStartAction_0()); 
            }
            // InternalRegularExpressionParser.g:2039:2: ()
            // InternalRegularExpressionParser.g:2039:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineStartAccess().getLineStartAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineStart__Group__0__Impl"


    // $ANTLR start "rule__LineStart__Group__1"
    // InternalRegularExpressionParser.g:2047:1: rule__LineStart__Group__1 : rule__LineStart__Group__1__Impl ;
    public final void rule__LineStart__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2051:1: ( rule__LineStart__Group__1__Impl )
            // InternalRegularExpressionParser.g:2052:2: rule__LineStart__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LineStart__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineStart__Group__1"


    // $ANTLR start "rule__LineStart__Group__1__Impl"
    // InternalRegularExpressionParser.g:2058:1: rule__LineStart__Group__1__Impl : ( CircumflexAccent ) ;
    public final void rule__LineStart__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2062:1: ( ( CircumflexAccent ) )
            // InternalRegularExpressionParser.g:2063:1: ( CircumflexAccent )
            {
            // InternalRegularExpressionParser.g:2063:1: ( CircumflexAccent )
            // InternalRegularExpressionParser.g:2064:2: CircumflexAccent
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineStartAccess().getCircumflexAccentKeyword_1()); 
            }
            match(input,CircumflexAccent,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineStartAccess().getCircumflexAccentKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineStart__Group__1__Impl"


    // $ANTLR start "rule__LineEnd__Group__0"
    // InternalRegularExpressionParser.g:2074:1: rule__LineEnd__Group__0 : rule__LineEnd__Group__0__Impl rule__LineEnd__Group__1 ;
    public final void rule__LineEnd__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2078:1: ( rule__LineEnd__Group__0__Impl rule__LineEnd__Group__1 )
            // InternalRegularExpressionParser.g:2079:2: rule__LineEnd__Group__0__Impl rule__LineEnd__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__LineEnd__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LineEnd__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineEnd__Group__0"


    // $ANTLR start "rule__LineEnd__Group__0__Impl"
    // InternalRegularExpressionParser.g:2086:1: rule__LineEnd__Group__0__Impl : ( () ) ;
    public final void rule__LineEnd__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2090:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2091:1: ( () )
            {
            // InternalRegularExpressionParser.g:2091:1: ( () )
            // InternalRegularExpressionParser.g:2092:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineEndAccess().getLineEndAction_0()); 
            }
            // InternalRegularExpressionParser.g:2093:2: ()
            // InternalRegularExpressionParser.g:2093:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineEndAccess().getLineEndAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineEnd__Group__0__Impl"


    // $ANTLR start "rule__LineEnd__Group__1"
    // InternalRegularExpressionParser.g:2101:1: rule__LineEnd__Group__1 : rule__LineEnd__Group__1__Impl ;
    public final void rule__LineEnd__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2105:1: ( rule__LineEnd__Group__1__Impl )
            // InternalRegularExpressionParser.g:2106:2: rule__LineEnd__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LineEnd__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineEnd__Group__1"


    // $ANTLR start "rule__LineEnd__Group__1__Impl"
    // InternalRegularExpressionParser.g:2112:1: rule__LineEnd__Group__1__Impl : ( DollarSign ) ;
    public final void rule__LineEnd__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2116:1: ( ( DollarSign ) )
            // InternalRegularExpressionParser.g:2117:1: ( DollarSign )
            {
            // InternalRegularExpressionParser.g:2117:1: ( DollarSign )
            // InternalRegularExpressionParser.g:2118:2: DollarSign
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLineEndAccess().getDollarSignKeyword_1()); 
            }
            match(input,DollarSign,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLineEndAccess().getDollarSignKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LineEnd__Group__1__Impl"


    // $ANTLR start "rule__WordBoundary__Group__0"
    // InternalRegularExpressionParser.g:2128:1: rule__WordBoundary__Group__0 : rule__WordBoundary__Group__0__Impl rule__WordBoundary__Group__1 ;
    public final void rule__WordBoundary__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2132:1: ( rule__WordBoundary__Group__0__Impl rule__WordBoundary__Group__1 )
            // InternalRegularExpressionParser.g:2133:2: rule__WordBoundary__Group__0__Impl rule__WordBoundary__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__WordBoundary__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WordBoundary__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__Group__0"


    // $ANTLR start "rule__WordBoundary__Group__0__Impl"
    // InternalRegularExpressionParser.g:2140:1: rule__WordBoundary__Group__0__Impl : ( () ) ;
    public final void rule__WordBoundary__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2144:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2145:1: ( () )
            {
            // InternalRegularExpressionParser.g:2145:1: ( () )
            // InternalRegularExpressionParser.g:2146:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWordBoundaryAccess().getWordBoundaryAction_0()); 
            }
            // InternalRegularExpressionParser.g:2147:2: ()
            // InternalRegularExpressionParser.g:2147:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWordBoundaryAccess().getWordBoundaryAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__Group__0__Impl"


    // $ANTLR start "rule__WordBoundary__Group__1"
    // InternalRegularExpressionParser.g:2155:1: rule__WordBoundary__Group__1 : rule__WordBoundary__Group__1__Impl ;
    public final void rule__WordBoundary__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2159:1: ( rule__WordBoundary__Group__1__Impl )
            // InternalRegularExpressionParser.g:2160:2: rule__WordBoundary__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WordBoundary__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__Group__1"


    // $ANTLR start "rule__WordBoundary__Group__1__Impl"
    // InternalRegularExpressionParser.g:2166:1: rule__WordBoundary__Group__1__Impl : ( ( rule__WordBoundary__Alternatives_1 ) ) ;
    public final void rule__WordBoundary__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2170:1: ( ( ( rule__WordBoundary__Alternatives_1 ) ) )
            // InternalRegularExpressionParser.g:2171:1: ( ( rule__WordBoundary__Alternatives_1 ) )
            {
            // InternalRegularExpressionParser.g:2171:1: ( ( rule__WordBoundary__Alternatives_1 ) )
            // InternalRegularExpressionParser.g:2172:2: ( rule__WordBoundary__Alternatives_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWordBoundaryAccess().getAlternatives_1()); 
            }
            // InternalRegularExpressionParser.g:2173:2: ( rule__WordBoundary__Alternatives_1 )
            // InternalRegularExpressionParser.g:2173:3: rule__WordBoundary__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__WordBoundary__Alternatives_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWordBoundaryAccess().getAlternatives_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__Group__1__Impl"


    // $ANTLR start "rule__LookAhead__Group__0"
    // InternalRegularExpressionParser.g:2182:1: rule__LookAhead__Group__0 : rule__LookAhead__Group__0__Impl rule__LookAhead__Group__1 ;
    public final void rule__LookAhead__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2186:1: ( rule__LookAhead__Group__0__Impl rule__LookAhead__Group__1 )
            // InternalRegularExpressionParser.g:2187:2: rule__LookAhead__Group__0__Impl rule__LookAhead__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__LookAhead__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__0"


    // $ANTLR start "rule__LookAhead__Group__0__Impl"
    // InternalRegularExpressionParser.g:2194:1: rule__LookAhead__Group__0__Impl : ( () ) ;
    public final void rule__LookAhead__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2198:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2199:1: ( () )
            {
            // InternalRegularExpressionParser.g:2199:1: ( () )
            // InternalRegularExpressionParser.g:2200:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getLookAheadAction_0()); 
            }
            // InternalRegularExpressionParser.g:2201:2: ()
            // InternalRegularExpressionParser.g:2201:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getLookAheadAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__0__Impl"


    // $ANTLR start "rule__LookAhead__Group__1"
    // InternalRegularExpressionParser.g:2209:1: rule__LookAhead__Group__1 : rule__LookAhead__Group__1__Impl rule__LookAhead__Group__2 ;
    public final void rule__LookAhead__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2213:1: ( rule__LookAhead__Group__1__Impl rule__LookAhead__Group__2 )
            // InternalRegularExpressionParser.g:2214:2: rule__LookAhead__Group__1__Impl rule__LookAhead__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__LookAhead__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__1"


    // $ANTLR start "rule__LookAhead__Group__1__Impl"
    // InternalRegularExpressionParser.g:2221:1: rule__LookAhead__Group__1__Impl : ( LeftParenthesis ) ;
    public final void rule__LookAhead__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2225:1: ( ( LeftParenthesis ) )
            // InternalRegularExpressionParser.g:2226:1: ( LeftParenthesis )
            {
            // InternalRegularExpressionParser.g:2226:1: ( LeftParenthesis )
            // InternalRegularExpressionParser.g:2227:2: LeftParenthesis
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getLeftParenthesisKeyword_1()); 
            }
            match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getLeftParenthesisKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__1__Impl"


    // $ANTLR start "rule__LookAhead__Group__2"
    // InternalRegularExpressionParser.g:2236:1: rule__LookAhead__Group__2 : rule__LookAhead__Group__2__Impl rule__LookAhead__Group__3 ;
    public final void rule__LookAhead__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2240:1: ( rule__LookAhead__Group__2__Impl rule__LookAhead__Group__3 )
            // InternalRegularExpressionParser.g:2241:2: rule__LookAhead__Group__2__Impl rule__LookAhead__Group__3
            {
            pushFollow(FOLLOW_17);
            rule__LookAhead__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__2"


    // $ANTLR start "rule__LookAhead__Group__2__Impl"
    // InternalRegularExpressionParser.g:2248:1: rule__LookAhead__Group__2__Impl : ( QuestionMark ) ;
    public final void rule__LookAhead__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2252:1: ( ( QuestionMark ) )
            // InternalRegularExpressionParser.g:2253:1: ( QuestionMark )
            {
            // InternalRegularExpressionParser.g:2253:1: ( QuestionMark )
            // InternalRegularExpressionParser.g:2254:2: QuestionMark
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getQuestionMarkKeyword_2()); 
            }
            match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getQuestionMarkKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__2__Impl"


    // $ANTLR start "rule__LookAhead__Group__3"
    // InternalRegularExpressionParser.g:2263:1: rule__LookAhead__Group__3 : rule__LookAhead__Group__3__Impl rule__LookAhead__Group__4 ;
    public final void rule__LookAhead__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2267:1: ( rule__LookAhead__Group__3__Impl rule__LookAhead__Group__4 )
            // InternalRegularExpressionParser.g:2268:2: rule__LookAhead__Group__3__Impl rule__LookAhead__Group__4
            {
            pushFollow(FOLLOW_4);
            rule__LookAhead__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__3"


    // $ANTLR start "rule__LookAhead__Group__3__Impl"
    // InternalRegularExpressionParser.g:2275:1: rule__LookAhead__Group__3__Impl : ( ( rule__LookAhead__Alternatives_3 ) ) ;
    public final void rule__LookAhead__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2279:1: ( ( ( rule__LookAhead__Alternatives_3 ) ) )
            // InternalRegularExpressionParser.g:2280:1: ( ( rule__LookAhead__Alternatives_3 ) )
            {
            // InternalRegularExpressionParser.g:2280:1: ( ( rule__LookAhead__Alternatives_3 ) )
            // InternalRegularExpressionParser.g:2281:2: ( rule__LookAhead__Alternatives_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getAlternatives_3()); 
            }
            // InternalRegularExpressionParser.g:2282:2: ( rule__LookAhead__Alternatives_3 )
            // InternalRegularExpressionParser.g:2282:3: rule__LookAhead__Alternatives_3
            {
            pushFollow(FOLLOW_2);
            rule__LookAhead__Alternatives_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getAlternatives_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__3__Impl"


    // $ANTLR start "rule__LookAhead__Group__4"
    // InternalRegularExpressionParser.g:2290:1: rule__LookAhead__Group__4 : rule__LookAhead__Group__4__Impl rule__LookAhead__Group__5 ;
    public final void rule__LookAhead__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2294:1: ( rule__LookAhead__Group__4__Impl rule__LookAhead__Group__5 )
            // InternalRegularExpressionParser.g:2295:2: rule__LookAhead__Group__4__Impl rule__LookAhead__Group__5
            {
            pushFollow(FOLLOW_18);
            rule__LookAhead__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__4"


    // $ANTLR start "rule__LookAhead__Group__4__Impl"
    // InternalRegularExpressionParser.g:2302:1: rule__LookAhead__Group__4__Impl : ( ( rule__LookAhead__PatternAssignment_4 ) ) ;
    public final void rule__LookAhead__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2306:1: ( ( ( rule__LookAhead__PatternAssignment_4 ) ) )
            // InternalRegularExpressionParser.g:2307:1: ( ( rule__LookAhead__PatternAssignment_4 ) )
            {
            // InternalRegularExpressionParser.g:2307:1: ( ( rule__LookAhead__PatternAssignment_4 ) )
            // InternalRegularExpressionParser.g:2308:2: ( rule__LookAhead__PatternAssignment_4 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getPatternAssignment_4()); 
            }
            // InternalRegularExpressionParser.g:2309:2: ( rule__LookAhead__PatternAssignment_4 )
            // InternalRegularExpressionParser.g:2309:3: rule__LookAhead__PatternAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__LookAhead__PatternAssignment_4();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getPatternAssignment_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__4__Impl"


    // $ANTLR start "rule__LookAhead__Group__5"
    // InternalRegularExpressionParser.g:2317:1: rule__LookAhead__Group__5 : rule__LookAhead__Group__5__Impl ;
    public final void rule__LookAhead__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2321:1: ( rule__LookAhead__Group__5__Impl )
            // InternalRegularExpressionParser.g:2322:2: rule__LookAhead__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LookAhead__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__5"


    // $ANTLR start "rule__LookAhead__Group__5__Impl"
    // InternalRegularExpressionParser.g:2328:1: rule__LookAhead__Group__5__Impl : ( RightParenthesis ) ;
    public final void rule__LookAhead__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2332:1: ( ( RightParenthesis ) )
            // InternalRegularExpressionParser.g:2333:1: ( RightParenthesis )
            {
            // InternalRegularExpressionParser.g:2333:1: ( RightParenthesis )
            // InternalRegularExpressionParser.g:2334:2: RightParenthesis
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getRightParenthesisKeyword_5()); 
            }
            match(input,RightParenthesis,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getRightParenthesisKeyword_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__Group__5__Impl"


    // $ANTLR start "rule__Wildcard__Group__0"
    // InternalRegularExpressionParser.g:2344:1: rule__Wildcard__Group__0 : rule__Wildcard__Group__0__Impl rule__Wildcard__Group__1 ;
    public final void rule__Wildcard__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2348:1: ( rule__Wildcard__Group__0__Impl rule__Wildcard__Group__1 )
            // InternalRegularExpressionParser.g:2349:2: rule__Wildcard__Group__0__Impl rule__Wildcard__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__Wildcard__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Wildcard__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Wildcard__Group__0"


    // $ANTLR start "rule__Wildcard__Group__0__Impl"
    // InternalRegularExpressionParser.g:2356:1: rule__Wildcard__Group__0__Impl : ( () ) ;
    public final void rule__Wildcard__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2360:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2361:1: ( () )
            {
            // InternalRegularExpressionParser.g:2361:1: ( () )
            // InternalRegularExpressionParser.g:2362:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardAccess().getWildcardAction_0()); 
            }
            // InternalRegularExpressionParser.g:2363:2: ()
            // InternalRegularExpressionParser.g:2363:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardAccess().getWildcardAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Wildcard__Group__0__Impl"


    // $ANTLR start "rule__Wildcard__Group__1"
    // InternalRegularExpressionParser.g:2371:1: rule__Wildcard__Group__1 : rule__Wildcard__Group__1__Impl ;
    public final void rule__Wildcard__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2375:1: ( rule__Wildcard__Group__1__Impl )
            // InternalRegularExpressionParser.g:2376:2: rule__Wildcard__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Wildcard__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Wildcard__Group__1"


    // $ANTLR start "rule__Wildcard__Group__1__Impl"
    // InternalRegularExpressionParser.g:2382:1: rule__Wildcard__Group__1__Impl : ( FullStop ) ;
    public final void rule__Wildcard__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2386:1: ( ( FullStop ) )
            // InternalRegularExpressionParser.g:2387:1: ( FullStop )
            {
            // InternalRegularExpressionParser.g:2387:1: ( FullStop )
            // InternalRegularExpressionParser.g:2388:2: FullStop
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardAccess().getFullStopKeyword_1()); 
            }
            match(input,FullStop,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardAccess().getFullStopKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Wildcard__Group__1__Impl"


    // $ANTLR start "rule__CharacterClass__Group__0"
    // InternalRegularExpressionParser.g:2398:1: rule__CharacterClass__Group__0 : rule__CharacterClass__Group__0__Impl rule__CharacterClass__Group__1 ;
    public final void rule__CharacterClass__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2402:1: ( rule__CharacterClass__Group__0__Impl rule__CharacterClass__Group__1 )
            // InternalRegularExpressionParser.g:2403:2: rule__CharacterClass__Group__0__Impl rule__CharacterClass__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__CharacterClass__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__0"


    // $ANTLR start "rule__CharacterClass__Group__0__Impl"
    // InternalRegularExpressionParser.g:2410:1: rule__CharacterClass__Group__0__Impl : ( () ) ;
    public final void rule__CharacterClass__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2414:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2415:1: ( () )
            {
            // InternalRegularExpressionParser.g:2415:1: ( () )
            // InternalRegularExpressionParser.g:2416:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getCharacterClassAction_0()); 
            }
            // InternalRegularExpressionParser.g:2417:2: ()
            // InternalRegularExpressionParser.g:2417:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getCharacterClassAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__0__Impl"


    // $ANTLR start "rule__CharacterClass__Group__1"
    // InternalRegularExpressionParser.g:2425:1: rule__CharacterClass__Group__1 : rule__CharacterClass__Group__1__Impl rule__CharacterClass__Group__2 ;
    public final void rule__CharacterClass__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2429:1: ( rule__CharacterClass__Group__1__Impl rule__CharacterClass__Group__2 )
            // InternalRegularExpressionParser.g:2430:2: rule__CharacterClass__Group__1__Impl rule__CharacterClass__Group__2
            {
            pushFollow(FOLLOW_21);
            rule__CharacterClass__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__1"


    // $ANTLR start "rule__CharacterClass__Group__1__Impl"
    // InternalRegularExpressionParser.g:2437:1: rule__CharacterClass__Group__1__Impl : ( LeftSquareBracket ) ;
    public final void rule__CharacterClass__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2441:1: ( ( LeftSquareBracket ) )
            // InternalRegularExpressionParser.g:2442:1: ( LeftSquareBracket )
            {
            // InternalRegularExpressionParser.g:2442:1: ( LeftSquareBracket )
            // InternalRegularExpressionParser.g:2443:2: LeftSquareBracket
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getLeftSquareBracketKeyword_1()); 
            }
            match(input,LeftSquareBracket,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getLeftSquareBracketKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__1__Impl"


    // $ANTLR start "rule__CharacterClass__Group__2"
    // InternalRegularExpressionParser.g:2452:1: rule__CharacterClass__Group__2 : rule__CharacterClass__Group__2__Impl rule__CharacterClass__Group__3 ;
    public final void rule__CharacterClass__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2456:1: ( rule__CharacterClass__Group__2__Impl rule__CharacterClass__Group__3 )
            // InternalRegularExpressionParser.g:2457:2: rule__CharacterClass__Group__2__Impl rule__CharacterClass__Group__3
            {
            pushFollow(FOLLOW_21);
            rule__CharacterClass__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__2"


    // $ANTLR start "rule__CharacterClass__Group__2__Impl"
    // InternalRegularExpressionParser.g:2464:1: rule__CharacterClass__Group__2__Impl : ( ( rule__CharacterClass__Group_2__0 )? ) ;
    public final void rule__CharacterClass__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2468:1: ( ( ( rule__CharacterClass__Group_2__0 )? ) )
            // InternalRegularExpressionParser.g:2469:1: ( ( rule__CharacterClass__Group_2__0 )? )
            {
            // InternalRegularExpressionParser.g:2469:1: ( ( rule__CharacterClass__Group_2__0 )? )
            // InternalRegularExpressionParser.g:2470:2: ( rule__CharacterClass__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getGroup_2()); 
            }
            // InternalRegularExpressionParser.g:2471:2: ( rule__CharacterClass__Group_2__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==CircumflexAccent) ) {
                int LA25_1 = input.LA(2);

                if ( (synpred70_InternalRegularExpressionParser()) ) {
                    alt25=1;
                }
            }
            switch (alt25) {
                case 1 :
                    // InternalRegularExpressionParser.g:2471:3: rule__CharacterClass__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CharacterClass__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__2__Impl"


    // $ANTLR start "rule__CharacterClass__Group__3"
    // InternalRegularExpressionParser.g:2479:1: rule__CharacterClass__Group__3 : rule__CharacterClass__Group__3__Impl rule__CharacterClass__Group__4 ;
    public final void rule__CharacterClass__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2483:1: ( rule__CharacterClass__Group__3__Impl rule__CharacterClass__Group__4 )
            // InternalRegularExpressionParser.g:2484:2: rule__CharacterClass__Group__3__Impl rule__CharacterClass__Group__4
            {
            pushFollow(FOLLOW_21);
            rule__CharacterClass__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__3"


    // $ANTLR start "rule__CharacterClass__Group__3__Impl"
    // InternalRegularExpressionParser.g:2491:1: rule__CharacterClass__Group__3__Impl : ( ( rule__CharacterClass__ElementsAssignment_3 )* ) ;
    public final void rule__CharacterClass__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2495:1: ( ( ( rule__CharacterClass__ElementsAssignment_3 )* ) )
            // InternalRegularExpressionParser.g:2496:1: ( ( rule__CharacterClass__ElementsAssignment_3 )* )
            {
            // InternalRegularExpressionParser.g:2496:1: ( ( rule__CharacterClass__ElementsAssignment_3 )* )
            // InternalRegularExpressionParser.g:2497:2: ( rule__CharacterClass__ElementsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getElementsAssignment_3()); 
            }
            // InternalRegularExpressionParser.g:2498:2: ( rule__CharacterClass__ElementsAssignment_3 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=ExclamationMark && LA26_0<=LeftSquareBracket)||(LA26_0>=CircumflexAccent && LA26_0<=RULE_WORD_BOUNDARY)||(LA26_0>=RULE_CHARACTER_CLASS_ESCAPE && LA26_0<=RULE_CONTROL_LETTER_ESCAPE)||(LA26_0>=RULE_HEX_ESCAPE && LA26_0<=RULE_UNICODE_ESCAPE)||(LA26_0>=RULE_DECIMAL_ESCAPE && LA26_0<=RULE_IDENTITY_ESCAPE)||LA26_0==RULE_UNICODE_DIGIT||(LA26_0>=RULE_UNICODE_LETTER && LA26_0<=RULE_PATTERN_CHARACTER_NO_DASH)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:2498:3: rule__CharacterClass__ElementsAssignment_3
            	    {
            	    pushFollow(FOLLOW_22);
            	    rule__CharacterClass__ElementsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getElementsAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__3__Impl"


    // $ANTLR start "rule__CharacterClass__Group__4"
    // InternalRegularExpressionParser.g:2506:1: rule__CharacterClass__Group__4 : rule__CharacterClass__Group__4__Impl ;
    public final void rule__CharacterClass__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2510:1: ( rule__CharacterClass__Group__4__Impl )
            // InternalRegularExpressionParser.g:2511:2: rule__CharacterClass__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__4"


    // $ANTLR start "rule__CharacterClass__Group__4__Impl"
    // InternalRegularExpressionParser.g:2517:1: rule__CharacterClass__Group__4__Impl : ( RightSquareBracket ) ;
    public final void rule__CharacterClass__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2521:1: ( ( RightSquareBracket ) )
            // InternalRegularExpressionParser.g:2522:1: ( RightSquareBracket )
            {
            // InternalRegularExpressionParser.g:2522:1: ( RightSquareBracket )
            // InternalRegularExpressionParser.g:2523:2: RightSquareBracket
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getRightSquareBracketKeyword_4()); 
            }
            match(input,RightSquareBracket,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getRightSquareBracketKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group__4__Impl"


    // $ANTLR start "rule__CharacterClass__Group_2__0"
    // InternalRegularExpressionParser.g:2533:1: rule__CharacterClass__Group_2__0 : rule__CharacterClass__Group_2__0__Impl ;
    public final void rule__CharacterClass__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2537:1: ( rule__CharacterClass__Group_2__0__Impl )
            // InternalRegularExpressionParser.g:2538:2: rule__CharacterClass__Group_2__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClass__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group_2__0"


    // $ANTLR start "rule__CharacterClass__Group_2__0__Impl"
    // InternalRegularExpressionParser.g:2544:1: rule__CharacterClass__Group_2__0__Impl : ( ( rule__CharacterClass__NegatedAssignment_2_0 ) ) ;
    public final void rule__CharacterClass__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2548:1: ( ( ( rule__CharacterClass__NegatedAssignment_2_0 ) ) )
            // InternalRegularExpressionParser.g:2549:1: ( ( rule__CharacterClass__NegatedAssignment_2_0 ) )
            {
            // InternalRegularExpressionParser.g:2549:1: ( ( rule__CharacterClass__NegatedAssignment_2_0 ) )
            // InternalRegularExpressionParser.g:2550:2: ( rule__CharacterClass__NegatedAssignment_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getNegatedAssignment_2_0()); 
            }
            // InternalRegularExpressionParser.g:2551:2: ( rule__CharacterClass__NegatedAssignment_2_0 )
            // InternalRegularExpressionParser.g:2551:3: rule__CharacterClass__NegatedAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClass__NegatedAssignment_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getNegatedAssignment_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__Group_2__0__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group__0"
    // InternalRegularExpressionParser.g:2560:1: rule__CharacterClassElement__Group__0 : rule__CharacterClassElement__Group__0__Impl rule__CharacterClassElement__Group__1 ;
    public final void rule__CharacterClassElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2564:1: ( rule__CharacterClassElement__Group__0__Impl rule__CharacterClassElement__Group__1 )
            // InternalRegularExpressionParser.g:2565:2: rule__CharacterClassElement__Group__0__Impl rule__CharacterClassElement__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__CharacterClassElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group__0"


    // $ANTLR start "rule__CharacterClassElement__Group__0__Impl"
    // InternalRegularExpressionParser.g:2572:1: rule__CharacterClassElement__Group__0__Impl : ( ruleCharacterClassAtom ) ;
    public final void rule__CharacterClassElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2576:1: ( ( ruleCharacterClassAtom ) )
            // InternalRegularExpressionParser.g:2577:1: ( ruleCharacterClassAtom )
            {
            // InternalRegularExpressionParser.g:2577:1: ( ruleCharacterClassAtom )
            // InternalRegularExpressionParser.g:2578:2: ruleCharacterClassAtom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getCharacterClassAtomParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleCharacterClassAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getCharacterClassAtomParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group__0__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group__1"
    // InternalRegularExpressionParser.g:2587:1: rule__CharacterClassElement__Group__1 : rule__CharacterClassElement__Group__1__Impl ;
    public final void rule__CharacterClassElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2591:1: ( rule__CharacterClassElement__Group__1__Impl )
            // InternalRegularExpressionParser.g:2592:2: rule__CharacterClassElement__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group__1"


    // $ANTLR start "rule__CharacterClassElement__Group__1__Impl"
    // InternalRegularExpressionParser.g:2598:1: rule__CharacterClassElement__Group__1__Impl : ( ( rule__CharacterClassElement__Group_1__0 )? ) ;
    public final void rule__CharacterClassElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2602:1: ( ( ( rule__CharacterClassElement__Group_1__0 )? ) )
            // InternalRegularExpressionParser.g:2603:1: ( ( rule__CharacterClassElement__Group_1__0 )? )
            {
            // InternalRegularExpressionParser.g:2603:1: ( ( rule__CharacterClassElement__Group_1__0 )? )
            // InternalRegularExpressionParser.g:2604:2: ( rule__CharacterClassElement__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getGroup_1()); 
            }
            // InternalRegularExpressionParser.g:2605:2: ( rule__CharacterClassElement__Group_1__0 )?
            int alt27=2;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // InternalRegularExpressionParser.g:2605:3: rule__CharacterClassElement__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CharacterClassElement__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group__1__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group_1__0"
    // InternalRegularExpressionParser.g:2614:1: rule__CharacterClassElement__Group_1__0 : rule__CharacterClassElement__Group_1__0__Impl ;
    public final void rule__CharacterClassElement__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2618:1: ( rule__CharacterClassElement__Group_1__0__Impl )
            // InternalRegularExpressionParser.g:2619:2: rule__CharacterClassElement__Group_1__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1__0"


    // $ANTLR start "rule__CharacterClassElement__Group_1__0__Impl"
    // InternalRegularExpressionParser.g:2625:1: rule__CharacterClassElement__Group_1__0__Impl : ( ( rule__CharacterClassElement__Group_1_0__0 ) ) ;
    public final void rule__CharacterClassElement__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2629:1: ( ( ( rule__CharacterClassElement__Group_1_0__0 ) ) )
            // InternalRegularExpressionParser.g:2630:1: ( ( rule__CharacterClassElement__Group_1_0__0 ) )
            {
            // InternalRegularExpressionParser.g:2630:1: ( ( rule__CharacterClassElement__Group_1_0__0 ) )
            // InternalRegularExpressionParser.g:2631:2: ( rule__CharacterClassElement__Group_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getGroup_1_0()); 
            }
            // InternalRegularExpressionParser.g:2632:2: ( rule__CharacterClassElement__Group_1_0__0 )
            // InternalRegularExpressionParser.g:2632:3: rule__CharacterClassElement__Group_1_0__0
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group_1_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getGroup_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1__0__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__0"
    // InternalRegularExpressionParser.g:2641:1: rule__CharacterClassElement__Group_1_0__0 : rule__CharacterClassElement__Group_1_0__0__Impl rule__CharacterClassElement__Group_1_0__1 ;
    public final void rule__CharacterClassElement__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2645:1: ( rule__CharacterClassElement__Group_1_0__0__Impl rule__CharacterClassElement__Group_1_0__1 )
            // InternalRegularExpressionParser.g:2646:2: rule__CharacterClassElement__Group_1_0__0__Impl rule__CharacterClassElement__Group_1_0__1
            {
            pushFollow(FOLLOW_23);
            rule__CharacterClassElement__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group_1_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__0"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__0__Impl"
    // InternalRegularExpressionParser.g:2653:1: rule__CharacterClassElement__Group_1_0__0__Impl : ( () ) ;
    public final void rule__CharacterClassElement__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2657:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2658:1: ( () )
            {
            // InternalRegularExpressionParser.g:2658:1: ( () )
            // InternalRegularExpressionParser.g:2659:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getCharacterClassRangeLeftAction_1_0_0()); 
            }
            // InternalRegularExpressionParser.g:2660:2: ()
            // InternalRegularExpressionParser.g:2660:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getCharacterClassRangeLeftAction_1_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__0__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__1"
    // InternalRegularExpressionParser.g:2668:1: rule__CharacterClassElement__Group_1_0__1 : rule__CharacterClassElement__Group_1_0__1__Impl rule__CharacterClassElement__Group_1_0__2 ;
    public final void rule__CharacterClassElement__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2672:1: ( rule__CharacterClassElement__Group_1_0__1__Impl rule__CharacterClassElement__Group_1_0__2 )
            // InternalRegularExpressionParser.g:2673:2: rule__CharacterClassElement__Group_1_0__1__Impl rule__CharacterClassElement__Group_1_0__2
            {
            pushFollow(FOLLOW_24);
            rule__CharacterClassElement__Group_1_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group_1_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__1"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__1__Impl"
    // InternalRegularExpressionParser.g:2680:1: rule__CharacterClassElement__Group_1_0__1__Impl : ( HyphenMinus ) ;
    public final void rule__CharacterClassElement__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2684:1: ( ( HyphenMinus ) )
            // InternalRegularExpressionParser.g:2685:1: ( HyphenMinus )
            {
            // InternalRegularExpressionParser.g:2685:1: ( HyphenMinus )
            // InternalRegularExpressionParser.g:2686:2: HyphenMinus
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getHyphenMinusKeyword_1_0_1()); 
            }
            match(input,HyphenMinus,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getHyphenMinusKeyword_1_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__1__Impl"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__2"
    // InternalRegularExpressionParser.g:2695:1: rule__CharacterClassElement__Group_1_0__2 : rule__CharacterClassElement__Group_1_0__2__Impl ;
    public final void rule__CharacterClassElement__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2699:1: ( rule__CharacterClassElement__Group_1_0__2__Impl )
            // InternalRegularExpressionParser.g:2700:2: rule__CharacterClassElement__Group_1_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__Group_1_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__2"


    // $ANTLR start "rule__CharacterClassElement__Group_1_0__2__Impl"
    // InternalRegularExpressionParser.g:2706:1: rule__CharacterClassElement__Group_1_0__2__Impl : ( ( rule__CharacterClassElement__RightAssignment_1_0_2 ) ) ;
    public final void rule__CharacterClassElement__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2710:1: ( ( ( rule__CharacterClassElement__RightAssignment_1_0_2 ) ) )
            // InternalRegularExpressionParser.g:2711:1: ( ( rule__CharacterClassElement__RightAssignment_1_0_2 ) )
            {
            // InternalRegularExpressionParser.g:2711:1: ( ( rule__CharacterClassElement__RightAssignment_1_0_2 ) )
            // InternalRegularExpressionParser.g:2712:2: ( rule__CharacterClassElement__RightAssignment_1_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getRightAssignment_1_0_2()); 
            }
            // InternalRegularExpressionParser.g:2713:2: ( rule__CharacterClassElement__RightAssignment_1_0_2 )
            // InternalRegularExpressionParser.g:2713:3: rule__CharacterClassElement__RightAssignment_1_0_2
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassElement__RightAssignment_1_0_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getRightAssignment_1_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__Group_1_0__2__Impl"


    // $ANTLR start "rule__Backspace__Group__0"
    // InternalRegularExpressionParser.g:2722:1: rule__Backspace__Group__0 : rule__Backspace__Group__0__Impl rule__Backspace__Group__1 ;
    public final void rule__Backspace__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2726:1: ( rule__Backspace__Group__0__Impl rule__Backspace__Group__1 )
            // InternalRegularExpressionParser.g:2727:2: rule__Backspace__Group__0__Impl rule__Backspace__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__Backspace__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Backspace__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Backspace__Group__0"


    // $ANTLR start "rule__Backspace__Group__0__Impl"
    // InternalRegularExpressionParser.g:2734:1: rule__Backspace__Group__0__Impl : ( () ) ;
    public final void rule__Backspace__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2738:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2739:1: ( () )
            {
            // InternalRegularExpressionParser.g:2739:1: ( () )
            // InternalRegularExpressionParser.g:2740:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBackspaceAccess().getBackspaceAction_0()); 
            }
            // InternalRegularExpressionParser.g:2741:2: ()
            // InternalRegularExpressionParser.g:2741:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBackspaceAccess().getBackspaceAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Backspace__Group__0__Impl"


    // $ANTLR start "rule__Backspace__Group__1"
    // InternalRegularExpressionParser.g:2749:1: rule__Backspace__Group__1 : rule__Backspace__Group__1__Impl ;
    public final void rule__Backspace__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2753:1: ( rule__Backspace__Group__1__Impl )
            // InternalRegularExpressionParser.g:2754:2: rule__Backspace__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Backspace__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Backspace__Group__1"


    // $ANTLR start "rule__Backspace__Group__1__Impl"
    // InternalRegularExpressionParser.g:2760:1: rule__Backspace__Group__1__Impl : ( RULE_WORD_BOUNDARY ) ;
    public final void rule__Backspace__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2764:1: ( ( RULE_WORD_BOUNDARY ) )
            // InternalRegularExpressionParser.g:2765:1: ( RULE_WORD_BOUNDARY )
            {
            // InternalRegularExpressionParser.g:2765:1: ( RULE_WORD_BOUNDARY )
            // InternalRegularExpressionParser.g:2766:2: RULE_WORD_BOUNDARY
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBackspaceAccess().getWORD_BOUNDARYTerminalRuleCall_1()); 
            }
            match(input,RULE_WORD_BOUNDARY,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBackspaceAccess().getWORD_BOUNDARYTerminalRuleCall_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Backspace__Group__1__Impl"


    // $ANTLR start "rule__Group__Group__0"
    // InternalRegularExpressionParser.g:2776:1: rule__Group__Group__0 : rule__Group__Group__0__Impl rule__Group__Group__1 ;
    public final void rule__Group__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2780:1: ( rule__Group__Group__0__Impl rule__Group__Group__1 )
            // InternalRegularExpressionParser.g:2781:2: rule__Group__Group__0__Impl rule__Group__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Group__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Group__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__0"


    // $ANTLR start "rule__Group__Group__0__Impl"
    // InternalRegularExpressionParser.g:2788:1: rule__Group__Group__0__Impl : ( () ) ;
    public final void rule__Group__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2792:1: ( ( () ) )
            // InternalRegularExpressionParser.g:2793:1: ( () )
            {
            // InternalRegularExpressionParser.g:2793:1: ( () )
            // InternalRegularExpressionParser.g:2794:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getGroupAction_0()); 
            }
            // InternalRegularExpressionParser.g:2795:2: ()
            // InternalRegularExpressionParser.g:2795:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getGroupAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__0__Impl"


    // $ANTLR start "rule__Group__Group__1"
    // InternalRegularExpressionParser.g:2803:1: rule__Group__Group__1 : rule__Group__Group__1__Impl rule__Group__Group__2 ;
    public final void rule__Group__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2807:1: ( rule__Group__Group__1__Impl rule__Group__Group__2 )
            // InternalRegularExpressionParser.g:2808:2: rule__Group__Group__1__Impl rule__Group__Group__2
            {
            pushFollow(FOLLOW_26);
            rule__Group__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Group__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__1"


    // $ANTLR start "rule__Group__Group__1__Impl"
    // InternalRegularExpressionParser.g:2815:1: rule__Group__Group__1__Impl : ( LeftParenthesis ) ;
    public final void rule__Group__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2819:1: ( ( LeftParenthesis ) )
            // InternalRegularExpressionParser.g:2820:1: ( LeftParenthesis )
            {
            // InternalRegularExpressionParser.g:2820:1: ( LeftParenthesis )
            // InternalRegularExpressionParser.g:2821:2: LeftParenthesis
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getLeftParenthesisKeyword_1()); 
            }
            match(input,LeftParenthesis,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getLeftParenthesisKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__1__Impl"


    // $ANTLR start "rule__Group__Group__2"
    // InternalRegularExpressionParser.g:2830:1: rule__Group__Group__2 : rule__Group__Group__2__Impl rule__Group__Group__3 ;
    public final void rule__Group__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2834:1: ( rule__Group__Group__2__Impl rule__Group__Group__3 )
            // InternalRegularExpressionParser.g:2835:2: rule__Group__Group__2__Impl rule__Group__Group__3
            {
            pushFollow(FOLLOW_26);
            rule__Group__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Group__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__2"


    // $ANTLR start "rule__Group__Group__2__Impl"
    // InternalRegularExpressionParser.g:2842:1: rule__Group__Group__2__Impl : ( ( rule__Group__Group_2__0 )? ) ;
    public final void rule__Group__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2846:1: ( ( ( rule__Group__Group_2__0 )? ) )
            // InternalRegularExpressionParser.g:2847:1: ( ( rule__Group__Group_2__0 )? )
            {
            // InternalRegularExpressionParser.g:2847:1: ( ( rule__Group__Group_2__0 )? )
            // InternalRegularExpressionParser.g:2848:2: ( rule__Group__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getGroup_2()); 
            }
            // InternalRegularExpressionParser.g:2849:2: ( rule__Group__Group_2__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==QuestionMark) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalRegularExpressionParser.g:2849:3: rule__Group__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Group__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__2__Impl"


    // $ANTLR start "rule__Group__Group__3"
    // InternalRegularExpressionParser.g:2857:1: rule__Group__Group__3 : rule__Group__Group__3__Impl rule__Group__Group__4 ;
    public final void rule__Group__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2861:1: ( rule__Group__Group__3__Impl rule__Group__Group__4 )
            // InternalRegularExpressionParser.g:2862:2: rule__Group__Group__3__Impl rule__Group__Group__4
            {
            pushFollow(FOLLOW_18);
            rule__Group__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Group__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__3"


    // $ANTLR start "rule__Group__Group__3__Impl"
    // InternalRegularExpressionParser.g:2869:1: rule__Group__Group__3__Impl : ( ( rule__Group__PatternAssignment_3 ) ) ;
    public final void rule__Group__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2873:1: ( ( ( rule__Group__PatternAssignment_3 ) ) )
            // InternalRegularExpressionParser.g:2874:1: ( ( rule__Group__PatternAssignment_3 ) )
            {
            // InternalRegularExpressionParser.g:2874:1: ( ( rule__Group__PatternAssignment_3 ) )
            // InternalRegularExpressionParser.g:2875:2: ( rule__Group__PatternAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getPatternAssignment_3()); 
            }
            // InternalRegularExpressionParser.g:2876:2: ( rule__Group__PatternAssignment_3 )
            // InternalRegularExpressionParser.g:2876:3: rule__Group__PatternAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Group__PatternAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getPatternAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__3__Impl"


    // $ANTLR start "rule__Group__Group__4"
    // InternalRegularExpressionParser.g:2884:1: rule__Group__Group__4 : rule__Group__Group__4__Impl ;
    public final void rule__Group__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2888:1: ( rule__Group__Group__4__Impl )
            // InternalRegularExpressionParser.g:2889:2: rule__Group__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Group__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__4"


    // $ANTLR start "rule__Group__Group__4__Impl"
    // InternalRegularExpressionParser.g:2895:1: rule__Group__Group__4__Impl : ( RightParenthesis ) ;
    public final void rule__Group__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2899:1: ( ( RightParenthesis ) )
            // InternalRegularExpressionParser.g:2900:1: ( RightParenthesis )
            {
            // InternalRegularExpressionParser.g:2900:1: ( RightParenthesis )
            // InternalRegularExpressionParser.g:2901:2: RightParenthesis
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getRightParenthesisKeyword_4()); 
            }
            match(input,RightParenthesis,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getRightParenthesisKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group__4__Impl"


    // $ANTLR start "rule__Group__Group_2__0"
    // InternalRegularExpressionParser.g:2911:1: rule__Group__Group_2__0 : rule__Group__Group_2__0__Impl rule__Group__Group_2__1 ;
    public final void rule__Group__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2915:1: ( rule__Group__Group_2__0__Impl rule__Group__Group_2__1 )
            // InternalRegularExpressionParser.g:2916:2: rule__Group__Group_2__0__Impl rule__Group__Group_2__1
            {
            pushFollow(FOLLOW_27);
            rule__Group__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Group__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group_2__0"


    // $ANTLR start "rule__Group__Group_2__0__Impl"
    // InternalRegularExpressionParser.g:2923:1: rule__Group__Group_2__0__Impl : ( ( rule__Group__NonCapturingAssignment_2_0 ) ) ;
    public final void rule__Group__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2927:1: ( ( ( rule__Group__NonCapturingAssignment_2_0 ) ) )
            // InternalRegularExpressionParser.g:2928:1: ( ( rule__Group__NonCapturingAssignment_2_0 ) )
            {
            // InternalRegularExpressionParser.g:2928:1: ( ( rule__Group__NonCapturingAssignment_2_0 ) )
            // InternalRegularExpressionParser.g:2929:2: ( rule__Group__NonCapturingAssignment_2_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getNonCapturingAssignment_2_0()); 
            }
            // InternalRegularExpressionParser.g:2930:2: ( rule__Group__NonCapturingAssignment_2_0 )
            // InternalRegularExpressionParser.g:2930:3: rule__Group__NonCapturingAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Group__NonCapturingAssignment_2_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getNonCapturingAssignment_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group_2__0__Impl"


    // $ANTLR start "rule__Group__Group_2__1"
    // InternalRegularExpressionParser.g:2938:1: rule__Group__Group_2__1 : rule__Group__Group_2__1__Impl ;
    public final void rule__Group__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2942:1: ( rule__Group__Group_2__1__Impl )
            // InternalRegularExpressionParser.g:2943:2: rule__Group__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Group__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group_2__1"


    // $ANTLR start "rule__Group__Group_2__1__Impl"
    // InternalRegularExpressionParser.g:2949:1: rule__Group__Group_2__1__Impl : ( Colon ) ;
    public final void rule__Group__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2953:1: ( ( Colon ) )
            // InternalRegularExpressionParser.g:2954:1: ( Colon )
            {
            // InternalRegularExpressionParser.g:2954:1: ( Colon )
            // InternalRegularExpressionParser.g:2955:2: Colon
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getColonKeyword_2_1()); 
            }
            match(input,Colon,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getColonKeyword_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__Group_2__1__Impl"


    // $ANTLR start "rule__SimpleQuantifier__Group__0"
    // InternalRegularExpressionParser.g:2965:1: rule__SimpleQuantifier__Group__0 : rule__SimpleQuantifier__Group__0__Impl rule__SimpleQuantifier__Group__1 ;
    public final void rule__SimpleQuantifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2969:1: ( rule__SimpleQuantifier__Group__0__Impl rule__SimpleQuantifier__Group__1 )
            // InternalRegularExpressionParser.g:2970:2: rule__SimpleQuantifier__Group__0__Impl rule__SimpleQuantifier__Group__1
            {
            pushFollow(FOLLOW_16);
            rule__SimpleQuantifier__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__SimpleQuantifier__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__Group__0"


    // $ANTLR start "rule__SimpleQuantifier__Group__0__Impl"
    // InternalRegularExpressionParser.g:2977:1: rule__SimpleQuantifier__Group__0__Impl : ( ( rule__SimpleQuantifier__QuantifierAssignment_0 ) ) ;
    public final void rule__SimpleQuantifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2981:1: ( ( ( rule__SimpleQuantifier__QuantifierAssignment_0 ) ) )
            // InternalRegularExpressionParser.g:2982:1: ( ( rule__SimpleQuantifier__QuantifierAssignment_0 ) )
            {
            // InternalRegularExpressionParser.g:2982:1: ( ( rule__SimpleQuantifier__QuantifierAssignment_0 ) )
            // InternalRegularExpressionParser.g:2983:2: ( rule__SimpleQuantifier__QuantifierAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getQuantifierAssignment_0()); 
            }
            // InternalRegularExpressionParser.g:2984:2: ( rule__SimpleQuantifier__QuantifierAssignment_0 )
            // InternalRegularExpressionParser.g:2984:3: rule__SimpleQuantifier__QuantifierAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleQuantifier__QuantifierAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getQuantifierAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__Group__0__Impl"


    // $ANTLR start "rule__SimpleQuantifier__Group__1"
    // InternalRegularExpressionParser.g:2992:1: rule__SimpleQuantifier__Group__1 : rule__SimpleQuantifier__Group__1__Impl ;
    public final void rule__SimpleQuantifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:2996:1: ( rule__SimpleQuantifier__Group__1__Impl )
            // InternalRegularExpressionParser.g:2997:2: rule__SimpleQuantifier__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleQuantifier__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__Group__1"


    // $ANTLR start "rule__SimpleQuantifier__Group__1__Impl"
    // InternalRegularExpressionParser.g:3003:1: rule__SimpleQuantifier__Group__1__Impl : ( ( rule__SimpleQuantifier__NonGreedyAssignment_1 )? ) ;
    public final void rule__SimpleQuantifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3007:1: ( ( ( rule__SimpleQuantifier__NonGreedyAssignment_1 )? ) )
            // InternalRegularExpressionParser.g:3008:1: ( ( rule__SimpleQuantifier__NonGreedyAssignment_1 )? )
            {
            // InternalRegularExpressionParser.g:3008:1: ( ( rule__SimpleQuantifier__NonGreedyAssignment_1 )? )
            // InternalRegularExpressionParser.g:3009:2: ( rule__SimpleQuantifier__NonGreedyAssignment_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getNonGreedyAssignment_1()); 
            }
            // InternalRegularExpressionParser.g:3010:2: ( rule__SimpleQuantifier__NonGreedyAssignment_1 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==QuestionMark) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalRegularExpressionParser.g:3010:3: rule__SimpleQuantifier__NonGreedyAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__SimpleQuantifier__NonGreedyAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getNonGreedyAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__Group__1__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__0"
    // InternalRegularExpressionParser.g:3019:1: rule__ExactQuantifier__Group__0 : rule__ExactQuantifier__Group__0__Impl rule__ExactQuantifier__Group__1 ;
    public final void rule__ExactQuantifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3023:1: ( rule__ExactQuantifier__Group__0__Impl rule__ExactQuantifier__Group__1 )
            // InternalRegularExpressionParser.g:3024:2: rule__ExactQuantifier__Group__0__Impl rule__ExactQuantifier__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__ExactQuantifier__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__0"


    // $ANTLR start "rule__ExactQuantifier__Group__0__Impl"
    // InternalRegularExpressionParser.g:3031:1: rule__ExactQuantifier__Group__0__Impl : ( () ) ;
    public final void rule__ExactQuantifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3035:1: ( ( () ) )
            // InternalRegularExpressionParser.g:3036:1: ( () )
            {
            // InternalRegularExpressionParser.g:3036:1: ( () )
            // InternalRegularExpressionParser.g:3037:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getExactQuantifierAction_0()); 
            }
            // InternalRegularExpressionParser.g:3038:2: ()
            // InternalRegularExpressionParser.g:3038:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getExactQuantifierAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__0__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__1"
    // InternalRegularExpressionParser.g:3046:1: rule__ExactQuantifier__Group__1 : rule__ExactQuantifier__Group__1__Impl rule__ExactQuantifier__Group__2 ;
    public final void rule__ExactQuantifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3050:1: ( rule__ExactQuantifier__Group__1__Impl rule__ExactQuantifier__Group__2 )
            // InternalRegularExpressionParser.g:3051:2: rule__ExactQuantifier__Group__1__Impl rule__ExactQuantifier__Group__2
            {
            pushFollow(FOLLOW_28);
            rule__ExactQuantifier__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__1"


    // $ANTLR start "rule__ExactQuantifier__Group__1__Impl"
    // InternalRegularExpressionParser.g:3058:1: rule__ExactQuantifier__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ExactQuantifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3062:1: ( ( LeftCurlyBracket ) )
            // InternalRegularExpressionParser.g:3063:1: ( LeftCurlyBracket )
            {
            // InternalRegularExpressionParser.g:3063:1: ( LeftCurlyBracket )
            // InternalRegularExpressionParser.g:3064:2: LeftCurlyBracket
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getLeftCurlyBracketKeyword_1()); 
            }
            match(input,LeftCurlyBracket,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getLeftCurlyBracketKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__1__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__2"
    // InternalRegularExpressionParser.g:3073:1: rule__ExactQuantifier__Group__2 : rule__ExactQuantifier__Group__2__Impl rule__ExactQuantifier__Group__3 ;
    public final void rule__ExactQuantifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3077:1: ( rule__ExactQuantifier__Group__2__Impl rule__ExactQuantifier__Group__3 )
            // InternalRegularExpressionParser.g:3078:2: rule__ExactQuantifier__Group__2__Impl rule__ExactQuantifier__Group__3
            {
            pushFollow(FOLLOW_29);
            rule__ExactQuantifier__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__2"


    // $ANTLR start "rule__ExactQuantifier__Group__2__Impl"
    // InternalRegularExpressionParser.g:3085:1: rule__ExactQuantifier__Group__2__Impl : ( ( rule__ExactQuantifier__MinAssignment_2 ) ) ;
    public final void rule__ExactQuantifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3089:1: ( ( ( rule__ExactQuantifier__MinAssignment_2 ) ) )
            // InternalRegularExpressionParser.g:3090:1: ( ( rule__ExactQuantifier__MinAssignment_2 ) )
            {
            // InternalRegularExpressionParser.g:3090:1: ( ( rule__ExactQuantifier__MinAssignment_2 ) )
            // InternalRegularExpressionParser.g:3091:2: ( rule__ExactQuantifier__MinAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getMinAssignment_2()); 
            }
            // InternalRegularExpressionParser.g:3092:2: ( rule__ExactQuantifier__MinAssignment_2 )
            // InternalRegularExpressionParser.g:3092:3: rule__ExactQuantifier__MinAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__MinAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getMinAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__2__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__3"
    // InternalRegularExpressionParser.g:3100:1: rule__ExactQuantifier__Group__3 : rule__ExactQuantifier__Group__3__Impl rule__ExactQuantifier__Group__4 ;
    public final void rule__ExactQuantifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3104:1: ( rule__ExactQuantifier__Group__3__Impl rule__ExactQuantifier__Group__4 )
            // InternalRegularExpressionParser.g:3105:2: rule__ExactQuantifier__Group__3__Impl rule__ExactQuantifier__Group__4
            {
            pushFollow(FOLLOW_29);
            rule__ExactQuantifier__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__3"


    // $ANTLR start "rule__ExactQuantifier__Group__3__Impl"
    // InternalRegularExpressionParser.g:3112:1: rule__ExactQuantifier__Group__3__Impl : ( ( rule__ExactQuantifier__Alternatives_3 )? ) ;
    public final void rule__ExactQuantifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3116:1: ( ( ( rule__ExactQuantifier__Alternatives_3 )? ) )
            // InternalRegularExpressionParser.g:3117:1: ( ( rule__ExactQuantifier__Alternatives_3 )? )
            {
            // InternalRegularExpressionParser.g:3117:1: ( ( rule__ExactQuantifier__Alternatives_3 )? )
            // InternalRegularExpressionParser.g:3118:2: ( rule__ExactQuantifier__Alternatives_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getAlternatives_3()); 
            }
            // InternalRegularExpressionParser.g:3119:2: ( rule__ExactQuantifier__Alternatives_3 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==Comma) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalRegularExpressionParser.g:3119:3: rule__ExactQuantifier__Alternatives_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ExactQuantifier__Alternatives_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getAlternatives_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__3__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__4"
    // InternalRegularExpressionParser.g:3127:1: rule__ExactQuantifier__Group__4 : rule__ExactQuantifier__Group__4__Impl rule__ExactQuantifier__Group__5 ;
    public final void rule__ExactQuantifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3131:1: ( rule__ExactQuantifier__Group__4__Impl rule__ExactQuantifier__Group__5 )
            // InternalRegularExpressionParser.g:3132:2: rule__ExactQuantifier__Group__4__Impl rule__ExactQuantifier__Group__5
            {
            pushFollow(FOLLOW_16);
            rule__ExactQuantifier__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__4"


    // $ANTLR start "rule__ExactQuantifier__Group__4__Impl"
    // InternalRegularExpressionParser.g:3139:1: rule__ExactQuantifier__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ExactQuantifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3143:1: ( ( RightCurlyBracket ) )
            // InternalRegularExpressionParser.g:3144:1: ( RightCurlyBracket )
            {
            // InternalRegularExpressionParser.g:3144:1: ( RightCurlyBracket )
            // InternalRegularExpressionParser.g:3145:2: RightCurlyBracket
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getRightCurlyBracketKeyword_4()); 
            }
            match(input,RightCurlyBracket,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getRightCurlyBracketKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__4__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group__5"
    // InternalRegularExpressionParser.g:3154:1: rule__ExactQuantifier__Group__5 : rule__ExactQuantifier__Group__5__Impl ;
    public final void rule__ExactQuantifier__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3158:1: ( rule__ExactQuantifier__Group__5__Impl )
            // InternalRegularExpressionParser.g:3159:2: rule__ExactQuantifier__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__5"


    // $ANTLR start "rule__ExactQuantifier__Group__5__Impl"
    // InternalRegularExpressionParser.g:3165:1: rule__ExactQuantifier__Group__5__Impl : ( ( rule__ExactQuantifier__NonGreedyAssignment_5 )? ) ;
    public final void rule__ExactQuantifier__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3169:1: ( ( ( rule__ExactQuantifier__NonGreedyAssignment_5 )? ) )
            // InternalRegularExpressionParser.g:3170:1: ( ( rule__ExactQuantifier__NonGreedyAssignment_5 )? )
            {
            // InternalRegularExpressionParser.g:3170:1: ( ( rule__ExactQuantifier__NonGreedyAssignment_5 )? )
            // InternalRegularExpressionParser.g:3171:2: ( rule__ExactQuantifier__NonGreedyAssignment_5 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getNonGreedyAssignment_5()); 
            }
            // InternalRegularExpressionParser.g:3172:2: ( rule__ExactQuantifier__NonGreedyAssignment_5 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==QuestionMark) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalRegularExpressionParser.g:3172:3: rule__ExactQuantifier__NonGreedyAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__ExactQuantifier__NonGreedyAssignment_5();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getNonGreedyAssignment_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group__5__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group_3_0__0"
    // InternalRegularExpressionParser.g:3181:1: rule__ExactQuantifier__Group_3_0__0 : rule__ExactQuantifier__Group_3_0__0__Impl rule__ExactQuantifier__Group_3_0__1 ;
    public final void rule__ExactQuantifier__Group_3_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3185:1: ( rule__ExactQuantifier__Group_3_0__0__Impl rule__ExactQuantifier__Group_3_0__1 )
            // InternalRegularExpressionParser.g:3186:2: rule__ExactQuantifier__Group_3_0__0__Impl rule__ExactQuantifier__Group_3_0__1
            {
            pushFollow(FOLLOW_28);
            rule__ExactQuantifier__Group_3_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group_3_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group_3_0__0"


    // $ANTLR start "rule__ExactQuantifier__Group_3_0__0__Impl"
    // InternalRegularExpressionParser.g:3193:1: rule__ExactQuantifier__Group_3_0__0__Impl : ( Comma ) ;
    public final void rule__ExactQuantifier__Group_3_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3197:1: ( ( Comma ) )
            // InternalRegularExpressionParser.g:3198:1: ( Comma )
            {
            // InternalRegularExpressionParser.g:3198:1: ( Comma )
            // InternalRegularExpressionParser.g:3199:2: Comma
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getCommaKeyword_3_0_0()); 
            }
            match(input,Comma,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getCommaKeyword_3_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group_3_0__0__Impl"


    // $ANTLR start "rule__ExactQuantifier__Group_3_0__1"
    // InternalRegularExpressionParser.g:3208:1: rule__ExactQuantifier__Group_3_0__1 : rule__ExactQuantifier__Group_3_0__1__Impl ;
    public final void rule__ExactQuantifier__Group_3_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3212:1: ( rule__ExactQuantifier__Group_3_0__1__Impl )
            // InternalRegularExpressionParser.g:3213:2: rule__ExactQuantifier__Group_3_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__Group_3_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group_3_0__1"


    // $ANTLR start "rule__ExactQuantifier__Group_3_0__1__Impl"
    // InternalRegularExpressionParser.g:3219:1: rule__ExactQuantifier__Group_3_0__1__Impl : ( ( rule__ExactQuantifier__MaxAssignment_3_0_1 ) ) ;
    public final void rule__ExactQuantifier__Group_3_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3223:1: ( ( ( rule__ExactQuantifier__MaxAssignment_3_0_1 ) ) )
            // InternalRegularExpressionParser.g:3224:1: ( ( rule__ExactQuantifier__MaxAssignment_3_0_1 ) )
            {
            // InternalRegularExpressionParser.g:3224:1: ( ( rule__ExactQuantifier__MaxAssignment_3_0_1 ) )
            // InternalRegularExpressionParser.g:3225:2: ( rule__ExactQuantifier__MaxAssignment_3_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getMaxAssignment_3_0_1()); 
            }
            // InternalRegularExpressionParser.g:3226:2: ( rule__ExactQuantifier__MaxAssignment_3_0_1 )
            // InternalRegularExpressionParser.g:3226:3: rule__ExactQuantifier__MaxAssignment_3_0_1
            {
            pushFollow(FOLLOW_2);
            rule__ExactQuantifier__MaxAssignment_3_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getMaxAssignment_3_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__Group_3_0__1__Impl"


    // $ANTLR start "rule__RegularExpressionFlags__Group__0"
    // InternalRegularExpressionParser.g:3235:1: rule__RegularExpressionFlags__Group__0 : rule__RegularExpressionFlags__Group__0__Impl rule__RegularExpressionFlags__Group__1 ;
    public final void rule__RegularExpressionFlags__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3239:1: ( rule__RegularExpressionFlags__Group__0__Impl rule__RegularExpressionFlags__Group__1 )
            // InternalRegularExpressionParser.g:3240:2: rule__RegularExpressionFlags__Group__0__Impl rule__RegularExpressionFlags__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__RegularExpressionFlags__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__RegularExpressionFlags__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__Group__0"


    // $ANTLR start "rule__RegularExpressionFlags__Group__0__Impl"
    // InternalRegularExpressionParser.g:3247:1: rule__RegularExpressionFlags__Group__0__Impl : ( () ) ;
    public final void rule__RegularExpressionFlags__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3251:1: ( ( () ) )
            // InternalRegularExpressionParser.g:3252:1: ( () )
            {
            // InternalRegularExpressionParser.g:3252:1: ( () )
            // InternalRegularExpressionParser.g:3253:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionFlagsAccess().getRegularExpressionFlagsAction_0()); 
            }
            // InternalRegularExpressionParser.g:3254:2: ()
            // InternalRegularExpressionParser.g:3254:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionFlagsAccess().getRegularExpressionFlagsAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__Group__0__Impl"


    // $ANTLR start "rule__RegularExpressionFlags__Group__1"
    // InternalRegularExpressionParser.g:3262:1: rule__RegularExpressionFlags__Group__1 : rule__RegularExpressionFlags__Group__1__Impl ;
    public final void rule__RegularExpressionFlags__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3266:1: ( rule__RegularExpressionFlags__Group__1__Impl )
            // InternalRegularExpressionParser.g:3267:2: rule__RegularExpressionFlags__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionFlags__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__Group__1"


    // $ANTLR start "rule__RegularExpressionFlags__Group__1__Impl"
    // InternalRegularExpressionParser.g:3273:1: rule__RegularExpressionFlags__Group__1__Impl : ( ( rule__RegularExpressionFlags__FlagsAssignment_1 )* ) ;
    public final void rule__RegularExpressionFlags__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3277:1: ( ( ( rule__RegularExpressionFlags__FlagsAssignment_1 )* ) )
            // InternalRegularExpressionParser.g:3278:1: ( ( rule__RegularExpressionFlags__FlagsAssignment_1 )* )
            {
            // InternalRegularExpressionParser.g:3278:1: ( ( rule__RegularExpressionFlags__FlagsAssignment_1 )* )
            // InternalRegularExpressionParser.g:3279:2: ( rule__RegularExpressionFlags__FlagsAssignment_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionFlagsAccess().getFlagsAssignment_1()); 
            }
            // InternalRegularExpressionParser.g:3280:2: ( rule__RegularExpressionFlags__FlagsAssignment_1 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_UNICODE_ESCAPE||LA32_0==RULE_UNICODE_LETTER) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalRegularExpressionParser.g:3280:3: rule__RegularExpressionFlags__FlagsAssignment_1
            	    {
            	    pushFollow(FOLLOW_30);
            	    rule__RegularExpressionFlags__FlagsAssignment_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionFlagsAccess().getFlagsAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__Group__1__Impl"


    // $ANTLR start "rule__RegularExpressionLiteral__BodyAssignment_1"
    // InternalRegularExpressionParser.g:3289:1: rule__RegularExpressionLiteral__BodyAssignment_1 : ( ruleRegularExpressionBody ) ;
    public final void rule__RegularExpressionLiteral__BodyAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3293:1: ( ( ruleRegularExpressionBody ) )
            // InternalRegularExpressionParser.g:3294:2: ( ruleRegularExpressionBody )
            {
            // InternalRegularExpressionParser.g:3294:2: ( ruleRegularExpressionBody )
            // InternalRegularExpressionParser.g:3295:3: ruleRegularExpressionBody
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getBodyRegularExpressionBodyParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleRegularExpressionBody();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getBodyRegularExpressionBodyParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__BodyAssignment_1"


    // $ANTLR start "rule__RegularExpressionLiteral__FlagsAssignment_3"
    // InternalRegularExpressionParser.g:3304:1: rule__RegularExpressionLiteral__FlagsAssignment_3 : ( ruleRegularExpressionFlags ) ;
    public final void rule__RegularExpressionLiteral__FlagsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3308:1: ( ( ruleRegularExpressionFlags ) )
            // InternalRegularExpressionParser.g:3309:2: ( ruleRegularExpressionFlags )
            {
            // InternalRegularExpressionParser.g:3309:2: ( ruleRegularExpressionFlags )
            // InternalRegularExpressionParser.g:3310:3: ruleRegularExpressionFlags
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionLiteralAccess().getFlagsRegularExpressionFlagsParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleRegularExpressionFlags();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionLiteralAccess().getFlagsRegularExpressionFlagsParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionLiteral__FlagsAssignment_3"


    // $ANTLR start "rule__RegularExpressionBody__PatternAssignment"
    // InternalRegularExpressionParser.g:3319:1: rule__RegularExpressionBody__PatternAssignment : ( ruleDisjunction ) ;
    public final void rule__RegularExpressionBody__PatternAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3323:1: ( ( ruleDisjunction ) )
            // InternalRegularExpressionParser.g:3324:2: ( ruleDisjunction )
            {
            // InternalRegularExpressionParser.g:3324:2: ( ruleDisjunction )
            // InternalRegularExpressionParser.g:3325:3: ruleDisjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionBodyAccess().getPatternDisjunctionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionBodyAccess().getPatternDisjunctionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionBody__PatternAssignment"


    // $ANTLR start "rule__Disjunction__ElementsAssignment_0_1_1_1"
    // InternalRegularExpressionParser.g:3334:1: rule__Disjunction__ElementsAssignment_0_1_1_1 : ( ruleAlternative ) ;
    public final void rule__Disjunction__ElementsAssignment_0_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3338:1: ( ( ruleAlternative ) )
            // InternalRegularExpressionParser.g:3339:2: ( ruleAlternative )
            {
            // InternalRegularExpressionParser.g:3339:2: ( ruleAlternative )
            // InternalRegularExpressionParser.g:3340:3: ruleAlternative
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getElementsAlternativeParserRuleCall_0_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAlternative();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getElementsAlternativeParserRuleCall_0_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__ElementsAssignment_0_1_1_1"


    // $ANTLR start "rule__Disjunction__ElementsAssignment_1_1_1"
    // InternalRegularExpressionParser.g:3349:1: rule__Disjunction__ElementsAssignment_1_1_1 : ( ruleAlternative ) ;
    public final void rule__Disjunction__ElementsAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3353:1: ( ( ruleAlternative ) )
            // InternalRegularExpressionParser.g:3354:2: ( ruleAlternative )
            {
            // InternalRegularExpressionParser.g:3354:2: ( ruleAlternative )
            // InternalRegularExpressionParser.g:3355:3: ruleAlternative
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getElementsAlternativeParserRuleCall_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleAlternative();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getElementsAlternativeParserRuleCall_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__ElementsAssignment_1_1_1"


    // $ANTLR start "rule__Alternative__ElementsAssignment_1_1"
    // InternalRegularExpressionParser.g:3364:1: rule__Alternative__ElementsAssignment_1_1 : ( ruleTerm ) ;
    public final void rule__Alternative__ElementsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3368:1: ( ( ruleTerm ) )
            // InternalRegularExpressionParser.g:3369:2: ( ruleTerm )
            {
            // InternalRegularExpressionParser.g:3369:2: ( ruleTerm )
            // InternalRegularExpressionParser.g:3370:3: ruleTerm
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAlternativeAccess().getElementsTermParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleTerm();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAlternativeAccess().getElementsTermParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Alternative__ElementsAssignment_1_1"


    // $ANTLR start "rule__Term__QuantifierAssignment_1_1"
    // InternalRegularExpressionParser.g:3379:1: rule__Term__QuantifierAssignment_1_1 : ( ruleQuantifier ) ;
    public final void rule__Term__QuantifierAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3383:1: ( ( ruleQuantifier ) )
            // InternalRegularExpressionParser.g:3384:2: ( ruleQuantifier )
            {
            // InternalRegularExpressionParser.g:3384:2: ( ruleQuantifier )
            // InternalRegularExpressionParser.g:3385:3: ruleQuantifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTermAccess().getQuantifierQuantifierParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQuantifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTermAccess().getQuantifierQuantifierParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Term__QuantifierAssignment_1_1"


    // $ANTLR start "rule__WordBoundary__NotAssignment_1_1"
    // InternalRegularExpressionParser.g:3394:1: rule__WordBoundary__NotAssignment_1_1 : ( RULE_NOT_WORD_BOUNDARY ) ;
    public final void rule__WordBoundary__NotAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3398:1: ( ( RULE_NOT_WORD_BOUNDARY ) )
            // InternalRegularExpressionParser.g:3399:2: ( RULE_NOT_WORD_BOUNDARY )
            {
            // InternalRegularExpressionParser.g:3399:2: ( RULE_NOT_WORD_BOUNDARY )
            // InternalRegularExpressionParser.g:3400:3: RULE_NOT_WORD_BOUNDARY
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWordBoundaryAccess().getNotNOT_WORD_BOUNDARYTerminalRuleCall_1_1_0()); 
            }
            match(input,RULE_NOT_WORD_BOUNDARY,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWordBoundaryAccess().getNotNOT_WORD_BOUNDARYTerminalRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WordBoundary__NotAssignment_1_1"


    // $ANTLR start "rule__LookAhead__NotAssignment_3_1"
    // InternalRegularExpressionParser.g:3409:1: rule__LookAhead__NotAssignment_3_1 : ( ( ExclamationMark ) ) ;
    public final void rule__LookAhead__NotAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3413:1: ( ( ( ExclamationMark ) ) )
            // InternalRegularExpressionParser.g:3414:2: ( ( ExclamationMark ) )
            {
            // InternalRegularExpressionParser.g:3414:2: ( ( ExclamationMark ) )
            // InternalRegularExpressionParser.g:3415:3: ( ExclamationMark )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getNotExclamationMarkKeyword_3_1_0()); 
            }
            // InternalRegularExpressionParser.g:3416:3: ( ExclamationMark )
            // InternalRegularExpressionParser.g:3417:4: ExclamationMark
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getNotExclamationMarkKeyword_3_1_0()); 
            }
            match(input,ExclamationMark,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getNotExclamationMarkKeyword_3_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getNotExclamationMarkKeyword_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__NotAssignment_3_1"


    // $ANTLR start "rule__LookAhead__PatternAssignment_4"
    // InternalRegularExpressionParser.g:3428:1: rule__LookAhead__PatternAssignment_4 : ( ruleDisjunction ) ;
    public final void rule__LookAhead__PatternAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3432:1: ( ( ruleDisjunction ) )
            // InternalRegularExpressionParser.g:3433:2: ( ruleDisjunction )
            {
            // InternalRegularExpressionParser.g:3433:2: ( ruleDisjunction )
            // InternalRegularExpressionParser.g:3434:3: ruleDisjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLookAheadAccess().getPatternDisjunctionParserRuleCall_4_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLookAheadAccess().getPatternDisjunctionParserRuleCall_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LookAhead__PatternAssignment_4"


    // $ANTLR start "rule__PatternCharacter__ValueAssignment"
    // InternalRegularExpressionParser.g:3443:1: rule__PatternCharacter__ValueAssignment : ( ( rule__PatternCharacter__ValueAlternatives_0 ) ) ;
    public final void rule__PatternCharacter__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3447:1: ( ( ( rule__PatternCharacter__ValueAlternatives_0 ) ) )
            // InternalRegularExpressionParser.g:3448:2: ( ( rule__PatternCharacter__ValueAlternatives_0 ) )
            {
            // InternalRegularExpressionParser.g:3448:2: ( ( rule__PatternCharacter__ValueAlternatives_0 ) )
            // InternalRegularExpressionParser.g:3449:3: ( rule__PatternCharacter__ValueAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPatternCharacterAccess().getValueAlternatives_0()); 
            }
            // InternalRegularExpressionParser.g:3450:3: ( rule__PatternCharacter__ValueAlternatives_0 )
            // InternalRegularExpressionParser.g:3450:4: rule__PatternCharacter__ValueAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__PatternCharacter__ValueAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPatternCharacterAccess().getValueAlternatives_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternCharacter__ValueAssignment"


    // $ANTLR start "rule__CharacterClassEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3458:1: rule__CharacterClassEscapeSequence__SequenceAssignment : ( RULE_CHARACTER_CLASS_ESCAPE ) ;
    public final void rule__CharacterClassEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3462:1: ( ( RULE_CHARACTER_CLASS_ESCAPE ) )
            // InternalRegularExpressionParser.g:3463:2: ( RULE_CHARACTER_CLASS_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3463:2: ( RULE_CHARACTER_CLASS_ESCAPE )
            // InternalRegularExpressionParser.g:3464:3: RULE_CHARACTER_CLASS_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassEscapeSequenceAccess().getSequenceCHARACTER_CLASS_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_CHARACTER_CLASS_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassEscapeSequenceAccess().getSequenceCHARACTER_CLASS_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__CharacterEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3473:1: rule__CharacterEscapeSequence__SequenceAssignment : ( RULE_CONTROL_ESCAPE ) ;
    public final void rule__CharacterEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3477:1: ( ( RULE_CONTROL_ESCAPE ) )
            // InternalRegularExpressionParser.g:3478:2: ( RULE_CONTROL_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3478:2: ( RULE_CONTROL_ESCAPE )
            // InternalRegularExpressionParser.g:3479:3: RULE_CONTROL_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterEscapeSequenceAccess().getSequenceCONTROL_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_CONTROL_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterEscapeSequenceAccess().getSequenceCONTROL_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__ControlLetterEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3488:1: rule__ControlLetterEscapeSequence__SequenceAssignment : ( RULE_CONTROL_LETTER_ESCAPE ) ;
    public final void rule__ControlLetterEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3492:1: ( ( RULE_CONTROL_LETTER_ESCAPE ) )
            // InternalRegularExpressionParser.g:3493:2: ( RULE_CONTROL_LETTER_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3493:2: ( RULE_CONTROL_LETTER_ESCAPE )
            // InternalRegularExpressionParser.g:3494:3: RULE_CONTROL_LETTER_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getControlLetterEscapeSequenceAccess().getSequenceCONTROL_LETTER_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_CONTROL_LETTER_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getControlLetterEscapeSequenceAccess().getSequenceCONTROL_LETTER_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ControlLetterEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__HexEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3503:1: rule__HexEscapeSequence__SequenceAssignment : ( RULE_HEX_ESCAPE ) ;
    public final void rule__HexEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3507:1: ( ( RULE_HEX_ESCAPE ) )
            // InternalRegularExpressionParser.g:3508:2: ( RULE_HEX_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3508:2: ( RULE_HEX_ESCAPE )
            // InternalRegularExpressionParser.g:3509:3: RULE_HEX_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHexEscapeSequenceAccess().getSequenceHEX_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_HEX_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHexEscapeSequenceAccess().getSequenceHEX_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HexEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__UnicodeEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3518:1: rule__UnicodeEscapeSequence__SequenceAssignment : ( RULE_UNICODE_ESCAPE ) ;
    public final void rule__UnicodeEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3522:1: ( ( RULE_UNICODE_ESCAPE ) )
            // InternalRegularExpressionParser.g:3523:2: ( RULE_UNICODE_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3523:2: ( RULE_UNICODE_ESCAPE )
            // InternalRegularExpressionParser.g:3524:3: RULE_UNICODE_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnicodeEscapeSequenceAccess().getSequenceUNICODE_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_UNICODE_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnicodeEscapeSequenceAccess().getSequenceUNICODE_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnicodeEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__IdentityEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3533:1: rule__IdentityEscapeSequence__SequenceAssignment : ( RULE_IDENTITY_ESCAPE ) ;
    public final void rule__IdentityEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3537:1: ( ( RULE_IDENTITY_ESCAPE ) )
            // InternalRegularExpressionParser.g:3538:2: ( RULE_IDENTITY_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3538:2: ( RULE_IDENTITY_ESCAPE )
            // InternalRegularExpressionParser.g:3539:3: RULE_IDENTITY_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentityEscapeSequenceAccess().getSequenceIDENTITY_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_IDENTITY_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentityEscapeSequenceAccess().getSequenceIDENTITY_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdentityEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__DecimalEscapeSequence__SequenceAssignment"
    // InternalRegularExpressionParser.g:3548:1: rule__DecimalEscapeSequence__SequenceAssignment : ( RULE_DECIMAL_ESCAPE ) ;
    public final void rule__DecimalEscapeSequence__SequenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3552:1: ( ( RULE_DECIMAL_ESCAPE ) )
            // InternalRegularExpressionParser.g:3553:2: ( RULE_DECIMAL_ESCAPE )
            {
            // InternalRegularExpressionParser.g:3553:2: ( RULE_DECIMAL_ESCAPE )
            // InternalRegularExpressionParser.g:3554:3: RULE_DECIMAL_ESCAPE
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDecimalEscapeSequenceAccess().getSequenceDECIMAL_ESCAPETerminalRuleCall_0()); 
            }
            match(input,RULE_DECIMAL_ESCAPE,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDecimalEscapeSequenceAccess().getSequenceDECIMAL_ESCAPETerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DecimalEscapeSequence__SequenceAssignment"


    // $ANTLR start "rule__CharacterClass__NegatedAssignment_2_0"
    // InternalRegularExpressionParser.g:3563:1: rule__CharacterClass__NegatedAssignment_2_0 : ( ( CircumflexAccent ) ) ;
    public final void rule__CharacterClass__NegatedAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3567:1: ( ( ( CircumflexAccent ) ) )
            // InternalRegularExpressionParser.g:3568:2: ( ( CircumflexAccent ) )
            {
            // InternalRegularExpressionParser.g:3568:2: ( ( CircumflexAccent ) )
            // InternalRegularExpressionParser.g:3569:3: ( CircumflexAccent )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getNegatedCircumflexAccentKeyword_2_0_0()); 
            }
            // InternalRegularExpressionParser.g:3570:3: ( CircumflexAccent )
            // InternalRegularExpressionParser.g:3571:4: CircumflexAccent
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getNegatedCircumflexAccentKeyword_2_0_0()); 
            }
            match(input,CircumflexAccent,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getNegatedCircumflexAccentKeyword_2_0_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getNegatedCircumflexAccentKeyword_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__NegatedAssignment_2_0"


    // $ANTLR start "rule__CharacterClass__ElementsAssignment_3"
    // InternalRegularExpressionParser.g:3582:1: rule__CharacterClass__ElementsAssignment_3 : ( ruleCharacterClassElement ) ;
    public final void rule__CharacterClass__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3586:1: ( ( ruleCharacterClassElement ) )
            // InternalRegularExpressionParser.g:3587:2: ( ruleCharacterClassElement )
            {
            // InternalRegularExpressionParser.g:3587:2: ( ruleCharacterClassElement )
            // InternalRegularExpressionParser.g:3588:3: ruleCharacterClassElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAccess().getElementsCharacterClassElementParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleCharacterClassElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAccess().getElementsCharacterClassElementParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClass__ElementsAssignment_3"


    // $ANTLR start "rule__CharacterClassElement__RightAssignment_1_0_2"
    // InternalRegularExpressionParser.g:3597:1: rule__CharacterClassElement__RightAssignment_1_0_2 : ( ruleCharacterClassAtom ) ;
    public final void rule__CharacterClassElement__RightAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3601:1: ( ( ruleCharacterClassAtom ) )
            // InternalRegularExpressionParser.g:3602:2: ( ruleCharacterClassAtom )
            {
            // InternalRegularExpressionParser.g:3602:2: ( ruleCharacterClassAtom )
            // InternalRegularExpressionParser.g:3603:3: ruleCharacterClassAtom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassElementAccess().getRightCharacterClassAtomParserRuleCall_1_0_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleCharacterClassAtom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassElementAccess().getRightCharacterClassAtomParserRuleCall_1_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassElement__RightAssignment_1_0_2"


    // $ANTLR start "rule__CharacterClassAtom__CharacterAssignment_1"
    // InternalRegularExpressionParser.g:3612:1: rule__CharacterClassAtom__CharacterAssignment_1 : ( ( rule__CharacterClassAtom__CharacterAlternatives_1_0 ) ) ;
    public final void rule__CharacterClassAtom__CharacterAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3616:1: ( ( ( rule__CharacterClassAtom__CharacterAlternatives_1_0 ) ) )
            // InternalRegularExpressionParser.g:3617:2: ( ( rule__CharacterClassAtom__CharacterAlternatives_1_0 ) )
            {
            // InternalRegularExpressionParser.g:3617:2: ( ( rule__CharacterClassAtom__CharacterAlternatives_1_0 ) )
            // InternalRegularExpressionParser.g:3618:3: ( rule__CharacterClassAtom__CharacterAlternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCharacterClassAtomAccess().getCharacterAlternatives_1_0()); 
            }
            // InternalRegularExpressionParser.g:3619:3: ( rule__CharacterClassAtom__CharacterAlternatives_1_0 )
            // InternalRegularExpressionParser.g:3619:4: rule__CharacterClassAtom__CharacterAlternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__CharacterClassAtom__CharacterAlternatives_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCharacterClassAtomAccess().getCharacterAlternatives_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CharacterClassAtom__CharacterAssignment_1"


    // $ANTLR start "rule__Group__NonCapturingAssignment_2_0"
    // InternalRegularExpressionParser.g:3627:1: rule__Group__NonCapturingAssignment_2_0 : ( ( QuestionMark ) ) ;
    public final void rule__Group__NonCapturingAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3631:1: ( ( ( QuestionMark ) ) )
            // InternalRegularExpressionParser.g:3632:2: ( ( QuestionMark ) )
            {
            // InternalRegularExpressionParser.g:3632:2: ( ( QuestionMark ) )
            // InternalRegularExpressionParser.g:3633:3: ( QuestionMark )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getNonCapturingQuestionMarkKeyword_2_0_0()); 
            }
            // InternalRegularExpressionParser.g:3634:3: ( QuestionMark )
            // InternalRegularExpressionParser.g:3635:4: QuestionMark
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getNonCapturingQuestionMarkKeyword_2_0_0()); 
            }
            match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getNonCapturingQuestionMarkKeyword_2_0_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getNonCapturingQuestionMarkKeyword_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__NonCapturingAssignment_2_0"


    // $ANTLR start "rule__Group__PatternAssignment_3"
    // InternalRegularExpressionParser.g:3646:1: rule__Group__PatternAssignment_3 : ( ruleDisjunction ) ;
    public final void rule__Group__PatternAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3650:1: ( ( ruleDisjunction ) )
            // InternalRegularExpressionParser.g:3651:2: ( ruleDisjunction )
            {
            // InternalRegularExpressionParser.g:3651:2: ( ruleDisjunction )
            // InternalRegularExpressionParser.g:3652:3: ruleDisjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGroupAccess().getPatternDisjunctionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGroupAccess().getPatternDisjunctionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Group__PatternAssignment_3"


    // $ANTLR start "rule__SimpleQuantifier__QuantifierAssignment_0"
    // InternalRegularExpressionParser.g:3661:1: rule__SimpleQuantifier__QuantifierAssignment_0 : ( ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 ) ) ;
    public final void rule__SimpleQuantifier__QuantifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3665:1: ( ( ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 ) ) )
            // InternalRegularExpressionParser.g:3666:2: ( ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 ) )
            {
            // InternalRegularExpressionParser.g:3666:2: ( ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 ) )
            // InternalRegularExpressionParser.g:3667:3: ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getQuantifierAlternatives_0_0()); 
            }
            // InternalRegularExpressionParser.g:3668:3: ( rule__SimpleQuantifier__QuantifierAlternatives_0_0 )
            // InternalRegularExpressionParser.g:3668:4: rule__SimpleQuantifier__QuantifierAlternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleQuantifier__QuantifierAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getQuantifierAlternatives_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__QuantifierAssignment_0"


    // $ANTLR start "rule__SimpleQuantifier__NonGreedyAssignment_1"
    // InternalRegularExpressionParser.g:3676:1: rule__SimpleQuantifier__NonGreedyAssignment_1 : ( ( QuestionMark ) ) ;
    public final void rule__SimpleQuantifier__NonGreedyAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3680:1: ( ( ( QuestionMark ) ) )
            // InternalRegularExpressionParser.g:3681:2: ( ( QuestionMark ) )
            {
            // InternalRegularExpressionParser.g:3681:2: ( ( QuestionMark ) )
            // InternalRegularExpressionParser.g:3682:3: ( QuestionMark )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getNonGreedyQuestionMarkKeyword_1_0()); 
            }
            // InternalRegularExpressionParser.g:3683:3: ( QuestionMark )
            // InternalRegularExpressionParser.g:3684:4: QuestionMark
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSimpleQuantifierAccess().getNonGreedyQuestionMarkKeyword_1_0()); 
            }
            match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getNonGreedyQuestionMarkKeyword_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSimpleQuantifierAccess().getNonGreedyQuestionMarkKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleQuantifier__NonGreedyAssignment_1"


    // $ANTLR start "rule__ExactQuantifier__MinAssignment_2"
    // InternalRegularExpressionParser.g:3695:1: rule__ExactQuantifier__MinAssignment_2 : ( ruleINT ) ;
    public final void rule__ExactQuantifier__MinAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3699:1: ( ( ruleINT ) )
            // InternalRegularExpressionParser.g:3700:2: ( ruleINT )
            {
            // InternalRegularExpressionParser.g:3700:2: ( ruleINT )
            // InternalRegularExpressionParser.g:3701:3: ruleINT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getMinINTParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleINT();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getMinINTParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__MinAssignment_2"


    // $ANTLR start "rule__ExactQuantifier__MaxAssignment_3_0_1"
    // InternalRegularExpressionParser.g:3710:1: rule__ExactQuantifier__MaxAssignment_3_0_1 : ( ruleINT ) ;
    public final void rule__ExactQuantifier__MaxAssignment_3_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3714:1: ( ( ruleINT ) )
            // InternalRegularExpressionParser.g:3715:2: ( ruleINT )
            {
            // InternalRegularExpressionParser.g:3715:2: ( ruleINT )
            // InternalRegularExpressionParser.g:3716:3: ruleINT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getMaxINTParserRuleCall_3_0_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleINT();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getMaxINTParserRuleCall_3_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__MaxAssignment_3_0_1"


    // $ANTLR start "rule__ExactQuantifier__UnboundedMaxAssignment_3_1"
    // InternalRegularExpressionParser.g:3725:1: rule__ExactQuantifier__UnboundedMaxAssignment_3_1 : ( ( Comma ) ) ;
    public final void rule__ExactQuantifier__UnboundedMaxAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3729:1: ( ( ( Comma ) ) )
            // InternalRegularExpressionParser.g:3730:2: ( ( Comma ) )
            {
            // InternalRegularExpressionParser.g:3730:2: ( ( Comma ) )
            // InternalRegularExpressionParser.g:3731:3: ( Comma )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getUnboundedMaxCommaKeyword_3_1_0()); 
            }
            // InternalRegularExpressionParser.g:3732:3: ( Comma )
            // InternalRegularExpressionParser.g:3733:4: Comma
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getUnboundedMaxCommaKeyword_3_1_0()); 
            }
            match(input,Comma,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getUnboundedMaxCommaKeyword_3_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getUnboundedMaxCommaKeyword_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__UnboundedMaxAssignment_3_1"


    // $ANTLR start "rule__ExactQuantifier__NonGreedyAssignment_5"
    // InternalRegularExpressionParser.g:3744:1: rule__ExactQuantifier__NonGreedyAssignment_5 : ( ( QuestionMark ) ) ;
    public final void rule__ExactQuantifier__NonGreedyAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3748:1: ( ( ( QuestionMark ) ) )
            // InternalRegularExpressionParser.g:3749:2: ( ( QuestionMark ) )
            {
            // InternalRegularExpressionParser.g:3749:2: ( ( QuestionMark ) )
            // InternalRegularExpressionParser.g:3750:3: ( QuestionMark )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getNonGreedyQuestionMarkKeyword_5_0()); 
            }
            // InternalRegularExpressionParser.g:3751:3: ( QuestionMark )
            // InternalRegularExpressionParser.g:3752:4: QuestionMark
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExactQuantifierAccess().getNonGreedyQuestionMarkKeyword_5_0()); 
            }
            match(input,QuestionMark,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getNonGreedyQuestionMarkKeyword_5_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExactQuantifierAccess().getNonGreedyQuestionMarkKeyword_5_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExactQuantifier__NonGreedyAssignment_5"


    // $ANTLR start "rule__RegularExpressionFlags__FlagsAssignment_1"
    // InternalRegularExpressionParser.g:3763:1: rule__RegularExpressionFlags__FlagsAssignment_1 : ( ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 ) ) ;
    public final void rule__RegularExpressionFlags__FlagsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRegularExpressionParser.g:3767:1: ( ( ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 ) ) )
            // InternalRegularExpressionParser.g:3768:2: ( ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 ) )
            {
            // InternalRegularExpressionParser.g:3768:2: ( ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 ) )
            // InternalRegularExpressionParser.g:3769:3: ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRegularExpressionFlagsAccess().getFlagsAlternatives_1_0()); 
            }
            // InternalRegularExpressionParser.g:3770:3: ( rule__RegularExpressionFlags__FlagsAlternatives_1_0 )
            // InternalRegularExpressionParser.g:3770:4: rule__RegularExpressionFlags__FlagsAlternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__RegularExpressionFlags__FlagsAlternatives_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRegularExpressionFlagsAccess().getFlagsAlternatives_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RegularExpressionFlags__FlagsAssignment_1"

    // $ANTLR start synpred69_InternalRegularExpressionParser
    public final void synpred69_InternalRegularExpressionParser_fragment() throws RecognitionException {   
        // InternalRegularExpressionParser.g:2011:3: ( rule__Term__QuantifierAssignment_1_1 )
        // InternalRegularExpressionParser.g:2011:3: rule__Term__QuantifierAssignment_1_1
        {
        pushFollow(FOLLOW_2);
        rule__Term__QuantifierAssignment_1_1();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred69_InternalRegularExpressionParser

    // $ANTLR start synpred70_InternalRegularExpressionParser
    public final void synpred70_InternalRegularExpressionParser_fragment() throws RecognitionException {   
        // InternalRegularExpressionParser.g:2471:3: ( rule__CharacterClass__Group_2__0 )
        // InternalRegularExpressionParser.g:2471:3: rule__CharacterClass__Group_2__0
        {
        pushFollow(FOLLOW_2);
        rule__CharacterClass__Group_2__0();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred70_InternalRegularExpressionParser

    // $ANTLR start synpred72_InternalRegularExpressionParser
    public final void synpred72_InternalRegularExpressionParser_fragment() throws RecognitionException {   
        // InternalRegularExpressionParser.g:2605:3: ( rule__CharacterClassElement__Group_1__0 )
        // InternalRegularExpressionParser.g:2605:3: rule__CharacterClassElement__Group_1__0
        {
        pushFollow(FOLLOW_2);
        rule__CharacterClassElement__Group_1__0();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred72_InternalRegularExpressionParser

    // Delegated rules

    public final boolean synpred72_InternalRegularExpressionParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred72_InternalRegularExpressionParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred69_InternalRegularExpressionParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_InternalRegularExpressionParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_InternalRegularExpressionParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_InternalRegularExpressionParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA24 dfa24 = new DFA24(this);
    protected DFA27 dfa27 = new DFA27(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\3\1\uffff\1\3\1\uffff\3\3\1\uffff\2\3";
    static final String dfa_3s = "\1\4\1\uffff\1\4\1\uffff\3\4\1\0\2\4";
    static final String dfa_4s = "\1\46\1\uffff\1\46\1\uffff\3\46\1\0\2\46";
    static final String dfa_5s = "\1\uffff\1\1\1\uffff\1\2\6\uffff";
    static final String dfa_6s = "\7\uffff\1\0\2\uffff}>";
    static final String[] dfa_7s = {
            "\4\3\2\1\6\3\1\1\3\3\1\2\7\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\3\1\uffff\2\3",
            "",
            "\30\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\4\1\uffff\2\3",
            "",
            "\6\3\1\6\13\3\1\7\5\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\5\1\uffff\2\3",
            "\6\3\1\6\13\3\1\7\5\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\5\1\uffff\2\3",
            "\22\3\1\7\5\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\10\1\uffff\2\3",
            "\1\uffff",
            "\22\3\1\7\5\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\11\1\uffff\2\3",
            "\22\3\1\7\5\3\1\uffff\2\3\1\uffff\2\3\1\uffff\1\11\1\uffff\2\3"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "2011:2: ( rule__Term__QuantifierAssignment_1_1 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA24_7 = input.LA(1);

                         
                        int index24_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred69_InternalRegularExpressionParser()) ) {s = 1;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index24_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 24, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_8s = "\41\uffff";
    static final String dfa_9s = "\1\2\40\uffff";
    static final String dfa_10s = "\2\4\1\uffff\35\0\1\uffff";
    static final String dfa_11s = "\2\46\1\uffff\35\0\1\uffff";
    static final String dfa_12s = "\2\uffff\1\2\35\uffff\1\1";
    static final String dfa_13s = "\3\uffff\1\24\1\6\1\15\1\30\1\11\1\7\1\22\1\2\1\25\1\12\1\31\1\16\1\3\1\1\1\21\1\5\1\10\1\27\1\14\1\34\1\0\1\20\1\4\1\23\1\26\1\13\1\32\1\17\1\33\1\uffff}>";
    static final String[] dfa_14s = {
            "\7\2\1\1\14\2\1\uffff\3\2\1\uffff\2\2\1\uffff\2\2\1\uffff\1\2\1\uffff\2\2",
            "\1\16\1\21\1\26\1\27\1\23\1\24\1\13\1\17\1\22\1\34\1\15\1\14\1\25\1\30\1\2\1\20\1\31\1\33\1\32\1\4\1\uffff\1\12\1\5\1\6\1\uffff\1\7\1\10\1\uffff\1\3\1\11\1\uffff\1\37\1\uffff\1\36\1\35",
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
            ""
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "2605:2: ( rule__CharacterClassElement__Group_1__0 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA27_23 = input.LA(1);

                         
                        int index27_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_23);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA27_16 = input.LA(1);

                         
                        int index27_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_16);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA27_10 = input.LA(1);

                         
                        int index27_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_10);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA27_15 = input.LA(1);

                         
                        int index27_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_15);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA27_25 = input.LA(1);

                         
                        int index27_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_25);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA27_18 = input.LA(1);

                         
                        int index27_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_18);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA27_4 = input.LA(1);

                         
                        int index27_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_4);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA27_8 = input.LA(1);

                         
                        int index27_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA27_19 = input.LA(1);

                         
                        int index27_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_19);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA27_7 = input.LA(1);

                         
                        int index27_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_7);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA27_12 = input.LA(1);

                         
                        int index27_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_12);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA27_28 = input.LA(1);

                         
                        int index27_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_28);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA27_21 = input.LA(1);

                         
                        int index27_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_21);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA27_5 = input.LA(1);

                         
                        int index27_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_5);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA27_14 = input.LA(1);

                         
                        int index27_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA27_30 = input.LA(1);

                         
                        int index27_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_30);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA27_24 = input.LA(1);

                         
                        int index27_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_24);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA27_17 = input.LA(1);

                         
                        int index27_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_17);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA27_9 = input.LA(1);

                         
                        int index27_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_9);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA27_26 = input.LA(1);

                         
                        int index27_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_26);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA27_3 = input.LA(1);

                         
                        int index27_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_3);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA27_11 = input.LA(1);

                         
                        int index27_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_11);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA27_27 = input.LA(1);

                         
                        int index27_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_27);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA27_20 = input.LA(1);

                         
                        int index27_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_20);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA27_6 = input.LA(1);

                         
                        int index27_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_6);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA27_13 = input.LA(1);

                         
                        int index27_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_13);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA27_29 = input.LA(1);

                         
                        int index27_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_29);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA27_31 = input.LA(1);

                         
                        int index27_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA27_22 = input.LA(1);

                         
                        int index27_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_InternalRegularExpressionParser()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index27_22);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 27, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000006B6FFEDC70L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000002040000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000006B6FDEDC70L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000006B6FDEDC72L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000110300L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001880060L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000006B6EFFFFF0L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000006B6EFBFFF2L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000006B6EFBFFF0L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000006B6FFFDC70L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000400400L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000002040000002L});

}