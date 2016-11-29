package eu.numberfour.n4js.n4mf.ui.contentassist.antlr.internal;
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
import eu.numberfour.n4js.n4mf.services.N4MFGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalN4MFParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ExtendedRuntimeEnvironment", "ProvidedRuntimeLibraries", "RequiredRuntimeLibraries", "ImplementedProjects", "ProjectDependencies", "RuntimeEnvironment", "ImplementationId", "ProjectVersion", "TestedProjects", "RuntimeLibrary", "ModuleFilters", "ModuleLoader", "NoModuleWrap", "Node_builtin", "InitModules", "ProjectType", "Application", "ExecModule", "MainModule", "VendorName", "NoValidate", "Libraries", "ProjectId", "Resources", "Processor", "VendorId", "Commonjs", "External", "Sources", "Compile", "Content", "Library", "Output", "Source", "KW_System", "N4js", "Test", "User", "API", "In", "LeftParenthesis", "RightParenthesis", "Comma", "HyphenMinus", "FullStop", "Colon", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int TestedProjects=12;
    public static final int KW_System=38;
    public static final int ProjectDependencies=8;
    public static final int ExecModule=21;
    public static final int LeftParenthesis=44;
    public static final int Test=40;
    public static final int ProjectVersion=11;
    public static final int Libraries=25;
    public static final int ModuleFilters=14;
    public static final int RightSquareBracket=51;
    public static final int VendorName=23;
    public static final int RuntimeEnvironment=9;
    public static final int RULE_ID=54;
    public static final int NoValidate=24;
    public static final int NoModuleWrap=16;
    public static final int RightParenthesis=45;
    public static final int Sources=32;
    public static final int Content=34;
    public static final int RULE_INT=55;
    public static final int ProjectType=19;
    public static final int External=31;
    public static final int RULE_ML_COMMENT=57;
    public static final int LeftSquareBracket=50;
    public static final int Resources=27;
    public static final int Library=35;
    public static final int Application=20;
    public static final int ImplementedProjects=7;
    public static final int Processor=28;
    public static final int User=41;
    public static final int In=43;
    public static final int VendorId=29;
    public static final int RULE_STRING=56;
    public static final int Node_builtin=17;
    public static final int N4js=39;
    public static final int Compile=33;
    public static final int Source=37;
    public static final int RULE_SL_COMMENT=58;
    public static final int ImplementationId=10;
    public static final int Comma=46;
    public static final int HyphenMinus=47;
    public static final int Output=36;
    public static final int MainModule=22;
    public static final int Colon=49;
    public static final int RightCurlyBracket=53;
    public static final int EOF=-1;
    public static final int ExtendedRuntimeEnvironment=4;
    public static final int FullStop=48;
    public static final int ModuleLoader=15;
    public static final int Commonjs=30;
    public static final int RULE_WS=59;
    public static final int ProjectId=26;
    public static final int LeftCurlyBracket=52;
    public static final int ProvidedRuntimeLibraries=5;
    public static final int RULE_ANY_OTHER=60;
    public static final int RequiredRuntimeLibraries=6;
    public static final int InitModules=18;
    public static final int API=42;
    public static final int RuntimeLibrary=13;

    // delegates
    // delegators


        public InternalN4MFParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalN4MFParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalN4MFParser.tokenNames; }
    public String getGrammarFileName() { return "InternalN4MFParser.g"; }


    	private N4MFGrammarAccess grammarAccess;
    	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
    	
    	{
    		tokenNameToValue.put("LeftParenthesis", "'('");
    		tokenNameToValue.put("RightParenthesis", "')'");
    		tokenNameToValue.put("Comma", "','");
    		tokenNameToValue.put("HyphenMinus", "'-'");
    		tokenNameToValue.put("FullStop", "'.'");
    		tokenNameToValue.put("Colon", "':'");
    		tokenNameToValue.put("LeftSquareBracket", "'['");
    		tokenNameToValue.put("RightSquareBracket", "']'");
    		tokenNameToValue.put("LeftCurlyBracket", "'{'");
    		tokenNameToValue.put("RightCurlyBracket", "'}'");
    		tokenNameToValue.put("In", "'in'");
    		tokenNameToValue.put("API", "'API'");
    		tokenNameToValue.put("N4js", "'n4js'");
    		tokenNameToValue.put("Test", "'test'");
    		tokenNameToValue.put("User", "'user'");
    		tokenNameToValue.put("Output", "'Output'");
    		tokenNameToValue.put("Source", "'source'");
    		tokenNameToValue.put("KW_System", "'system'");
    		tokenNameToValue.put("Sources", "'Sources'");
    		tokenNameToValue.put("Compile", "'compile'");
    		tokenNameToValue.put("Content", "'content'");
    		tokenNameToValue.put("Library", "'library'");
    		tokenNameToValue.put("VendorId", "'VendorId'");
    		tokenNameToValue.put("Commonjs", "'commonjs'");
    		tokenNameToValue.put("External", "'external'");
    		tokenNameToValue.put("Libraries", "'Libraries'");
    		tokenNameToValue.put("ProjectId", "'ProjectId'");
    		tokenNameToValue.put("Resources", "'Resources'");
    		tokenNameToValue.put("Processor", "'processor'");
    		tokenNameToValue.put("ExecModule", "'ExecModule'");
    		tokenNameToValue.put("MainModule", "'MainModule'");
    		tokenNameToValue.put("VendorName", "'VendorName'");
    		tokenNameToValue.put("NoValidate", "'noValidate'");
    		tokenNameToValue.put("InitModules", "'InitModules'");
    		tokenNameToValue.put("ProjectType", "'ProjectType'");
    		tokenNameToValue.put("Application", "'application'");
    		tokenNameToValue.put("ModuleLoader", "'ModuleLoader'");
    		tokenNameToValue.put("NoModuleWrap", "'noModuleWrap'");
    		tokenNameToValue.put("Node_builtin", "'node_builtin'");
    		tokenNameToValue.put("ModuleFilters", "'ModuleFilters'");
    		tokenNameToValue.put("ProjectVersion", "'ProjectVersion'");
    		tokenNameToValue.put("TestedProjects", "'TestedProjects'");
    		tokenNameToValue.put("RuntimeLibrary", "'runtimeLibrary'");
    		tokenNameToValue.put("ImplementationId", "'ImplementationId'");
    		tokenNameToValue.put("RuntimeEnvironment", "'runtimeEnvironment'");
    		tokenNameToValue.put("ImplementedProjects", "'ImplementedProjects'");
    		tokenNameToValue.put("ProjectDependencies", "'ProjectDependencies'");
    		tokenNameToValue.put("ProvidedRuntimeLibraries", "'ProvidedRuntimeLibraries'");
    		tokenNameToValue.put("RequiredRuntimeLibraries", "'RequiredRuntimeLibraries'");
    		tokenNameToValue.put("ExtendedRuntimeEnvironment", "'ExtendedRuntimeEnvironment'");
    	}

    	public void setGrammarAccess(N4MFGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleProjectDescription"
    // InternalN4MFParser.g:105:1: entryRuleProjectDescription : ruleProjectDescription EOF ;
    public final void entryRuleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:106:1: ( ruleProjectDescription EOF )
            // InternalN4MFParser.g:107:1: ruleProjectDescription EOF
            {
             before(grammarAccess.getProjectDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleProjectDescription();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProjectDescription"


    // $ANTLR start "ruleProjectDescription"
    // InternalN4MFParser.g:114:1: ruleProjectDescription : ( ( rule__ProjectDescription__UnorderedGroup ) ) ;
    public final void ruleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:118:2: ( ( ( rule__ProjectDescription__UnorderedGroup ) ) )
            // InternalN4MFParser.g:119:2: ( ( rule__ProjectDescription__UnorderedGroup ) )
            {
            // InternalN4MFParser.g:119:2: ( ( rule__ProjectDescription__UnorderedGroup ) )
            // InternalN4MFParser.g:120:3: ( rule__ProjectDescription__UnorderedGroup )
            {
             before(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()); 
            // InternalN4MFParser.g:121:3: ( rule__ProjectDescription__UnorderedGroup )
            // InternalN4MFParser.g:121:4: rule__ProjectDescription__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()); 

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
    // $ANTLR end "ruleProjectDescription"


    // $ANTLR start "entryRuleExecModule"
    // InternalN4MFParser.g:130:1: entryRuleExecModule : ruleExecModule EOF ;
    public final void entryRuleExecModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:131:1: ( ruleExecModule EOF )
            // InternalN4MFParser.g:132:1: ruleExecModule EOF
            {
             before(grammarAccess.getExecModuleRule()); 
            pushFollow(FOLLOW_1);
            ruleExecModule();

            state._fsp--;

             after(grammarAccess.getExecModuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleExecModule"


    // $ANTLR start "ruleExecModule"
    // InternalN4MFParser.g:139:1: ruleExecModule : ( ( rule__ExecModule__Group__0 ) ) ;
    public final void ruleExecModule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:143:2: ( ( ( rule__ExecModule__Group__0 ) ) )
            // InternalN4MFParser.g:144:2: ( ( rule__ExecModule__Group__0 ) )
            {
            // InternalN4MFParser.g:144:2: ( ( rule__ExecModule__Group__0 ) )
            // InternalN4MFParser.g:145:3: ( rule__ExecModule__Group__0 )
            {
             before(grammarAccess.getExecModuleAccess().getGroup()); 
            // InternalN4MFParser.g:146:3: ( rule__ExecModule__Group__0 )
            // InternalN4MFParser.g:146:4: rule__ExecModule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ExecModule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExecModuleAccess().getGroup()); 

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
    // $ANTLR end "ruleExecModule"


    // $ANTLR start "entryRuleTestedProjects"
    // InternalN4MFParser.g:155:1: entryRuleTestedProjects : ruleTestedProjects EOF ;
    public final void entryRuleTestedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:156:1: ( ruleTestedProjects EOF )
            // InternalN4MFParser.g:157:1: ruleTestedProjects EOF
            {
             before(grammarAccess.getTestedProjectsRule()); 
            pushFollow(FOLLOW_1);
            ruleTestedProjects();

            state._fsp--;

             after(grammarAccess.getTestedProjectsRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleTestedProjects"


    // $ANTLR start "ruleTestedProjects"
    // InternalN4MFParser.g:164:1: ruleTestedProjects : ( ( rule__TestedProjects__Group__0 ) ) ;
    public final void ruleTestedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:168:2: ( ( ( rule__TestedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:169:2: ( ( rule__TestedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:169:2: ( ( rule__TestedProjects__Group__0 ) )
            // InternalN4MFParser.g:170:3: ( rule__TestedProjects__Group__0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:171:3: ( rule__TestedProjects__Group__0 )
            // InternalN4MFParser.g:171:4: rule__TestedProjects__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTestedProjectsAccess().getGroup()); 

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
    // $ANTLR end "ruleTestedProjects"


    // $ANTLR start "entryRuleInitModules"
    // InternalN4MFParser.g:180:1: entryRuleInitModules : ruleInitModules EOF ;
    public final void entryRuleInitModules() throws RecognitionException {
        try {
            // InternalN4MFParser.g:181:1: ( ruleInitModules EOF )
            // InternalN4MFParser.g:182:1: ruleInitModules EOF
            {
             before(grammarAccess.getInitModulesRule()); 
            pushFollow(FOLLOW_1);
            ruleInitModules();

            state._fsp--;

             after(grammarAccess.getInitModulesRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleInitModules"


    // $ANTLR start "ruleInitModules"
    // InternalN4MFParser.g:189:1: ruleInitModules : ( ( rule__InitModules__Group__0 ) ) ;
    public final void ruleInitModules() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:193:2: ( ( ( rule__InitModules__Group__0 ) ) )
            // InternalN4MFParser.g:194:2: ( ( rule__InitModules__Group__0 ) )
            {
            // InternalN4MFParser.g:194:2: ( ( rule__InitModules__Group__0 ) )
            // InternalN4MFParser.g:195:3: ( rule__InitModules__Group__0 )
            {
             before(grammarAccess.getInitModulesAccess().getGroup()); 
            // InternalN4MFParser.g:196:3: ( rule__InitModules__Group__0 )
            // InternalN4MFParser.g:196:4: rule__InitModules__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInitModulesAccess().getGroup()); 

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
    // $ANTLR end "ruleInitModules"


    // $ANTLR start "entryRuleImplementedProjects"
    // InternalN4MFParser.g:205:1: entryRuleImplementedProjects : ruleImplementedProjects EOF ;
    public final void entryRuleImplementedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:206:1: ( ruleImplementedProjects EOF )
            // InternalN4MFParser.g:207:1: ruleImplementedProjects EOF
            {
             before(grammarAccess.getImplementedProjectsRule()); 
            pushFollow(FOLLOW_1);
            ruleImplementedProjects();

            state._fsp--;

             after(grammarAccess.getImplementedProjectsRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleImplementedProjects"


    // $ANTLR start "ruleImplementedProjects"
    // InternalN4MFParser.g:214:1: ruleImplementedProjects : ( ( rule__ImplementedProjects__Group__0 ) ) ;
    public final void ruleImplementedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:218:2: ( ( ( rule__ImplementedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:219:2: ( ( rule__ImplementedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:219:2: ( ( rule__ImplementedProjects__Group__0 ) )
            // InternalN4MFParser.g:220:3: ( rule__ImplementedProjects__Group__0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:221:3: ( rule__ImplementedProjects__Group__0 )
            // InternalN4MFParser.g:221:4: rule__ImplementedProjects__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImplementedProjectsAccess().getGroup()); 

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
    // $ANTLR end "ruleImplementedProjects"


    // $ANTLR start "entryRuleProjectDependencies"
    // InternalN4MFParser.g:230:1: entryRuleProjectDependencies : ruleProjectDependencies EOF ;
    public final void entryRuleProjectDependencies() throws RecognitionException {
        try {
            // InternalN4MFParser.g:231:1: ( ruleProjectDependencies EOF )
            // InternalN4MFParser.g:232:1: ruleProjectDependencies EOF
            {
             before(grammarAccess.getProjectDependenciesRule()); 
            pushFollow(FOLLOW_1);
            ruleProjectDependencies();

            state._fsp--;

             after(grammarAccess.getProjectDependenciesRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProjectDependencies"


    // $ANTLR start "ruleProjectDependencies"
    // InternalN4MFParser.g:239:1: ruleProjectDependencies : ( ( rule__ProjectDependencies__Group__0 ) ) ;
    public final void ruleProjectDependencies() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:243:2: ( ( ( rule__ProjectDependencies__Group__0 ) ) )
            // InternalN4MFParser.g:244:2: ( ( rule__ProjectDependencies__Group__0 ) )
            {
            // InternalN4MFParser.g:244:2: ( ( rule__ProjectDependencies__Group__0 ) )
            // InternalN4MFParser.g:245:3: ( rule__ProjectDependencies__Group__0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup()); 
            // InternalN4MFParser.g:246:3: ( rule__ProjectDependencies__Group__0 )
            // InternalN4MFParser.g:246:4: rule__ProjectDependencies__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependenciesAccess().getGroup()); 

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
    // $ANTLR end "ruleProjectDependencies"


    // $ANTLR start "entryRuleProvidedRuntimeLibraries"
    // InternalN4MFParser.g:255:1: entryRuleProvidedRuntimeLibraries : ruleProvidedRuntimeLibraries EOF ;
    public final void entryRuleProvidedRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:256:1: ( ruleProvidedRuntimeLibraries EOF )
            // InternalN4MFParser.g:257:1: ruleProvidedRuntimeLibraries EOF
            {
             before(grammarAccess.getProvidedRuntimeLibrariesRule()); 
            pushFollow(FOLLOW_1);
            ruleProvidedRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getProvidedRuntimeLibrariesRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProvidedRuntimeLibraries"


    // $ANTLR start "ruleProvidedRuntimeLibraries"
    // InternalN4MFParser.g:264:1: ruleProvidedRuntimeLibraries : ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) ;
    public final void ruleProvidedRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:268:2: ( ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:269:2: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:269:2: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:270:3: ( rule__ProvidedRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:271:3: ( rule__ProvidedRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:271:4: rule__ProvidedRuntimeLibraries__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup()); 

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
    // $ANTLR end "ruleProvidedRuntimeLibraries"


    // $ANTLR start "entryRuleRequiredRuntimeLibraries"
    // InternalN4MFParser.g:280:1: entryRuleRequiredRuntimeLibraries : ruleRequiredRuntimeLibraries EOF ;
    public final void entryRuleRequiredRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:281:1: ( ruleRequiredRuntimeLibraries EOF )
            // InternalN4MFParser.g:282:1: ruleRequiredRuntimeLibraries EOF
            {
             before(grammarAccess.getRequiredRuntimeLibrariesRule()); 
            pushFollow(FOLLOW_1);
            ruleRequiredRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getRequiredRuntimeLibrariesRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRequiredRuntimeLibraries"


    // $ANTLR start "ruleRequiredRuntimeLibraries"
    // InternalN4MFParser.g:289:1: ruleRequiredRuntimeLibraries : ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) ;
    public final void ruleRequiredRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:293:2: ( ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:294:2: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:294:2: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:295:3: ( rule__RequiredRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:296:3: ( rule__RequiredRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:296:4: rule__RequiredRuntimeLibraries__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup()); 

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
    // $ANTLR end "ruleRequiredRuntimeLibraries"


    // $ANTLR start "entryRuleExtendedRuntimeEnvironment"
    // InternalN4MFParser.g:305:1: entryRuleExtendedRuntimeEnvironment : ruleExtendedRuntimeEnvironment EOF ;
    public final void entryRuleExtendedRuntimeEnvironment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:306:1: ( ruleExtendedRuntimeEnvironment EOF )
            // InternalN4MFParser.g:307:1: ruleExtendedRuntimeEnvironment EOF
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentRule()); 
            pushFollow(FOLLOW_1);
            ruleExtendedRuntimeEnvironment();

            state._fsp--;

             after(grammarAccess.getExtendedRuntimeEnvironmentRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleExtendedRuntimeEnvironment"


    // $ANTLR start "ruleExtendedRuntimeEnvironment"
    // InternalN4MFParser.g:314:1: ruleExtendedRuntimeEnvironment : ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) ;
    public final void ruleExtendedRuntimeEnvironment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:318:2: ( ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) )
            // InternalN4MFParser.g:319:2: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            {
            // InternalN4MFParser.g:319:2: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            // InternalN4MFParser.g:320:3: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getGroup()); 
            // InternalN4MFParser.g:321:3: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            // InternalN4MFParser.g:321:4: rule__ExtendedRuntimeEnvironment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getGroup()); 

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
    // $ANTLR end "ruleExtendedRuntimeEnvironment"


    // $ANTLR start "entryRuleDeclaredVersion"
    // InternalN4MFParser.g:330:1: entryRuleDeclaredVersion : ruleDeclaredVersion EOF ;
    public final void entryRuleDeclaredVersion() throws RecognitionException {
        try {
            // InternalN4MFParser.g:331:1: ( ruleDeclaredVersion EOF )
            // InternalN4MFParser.g:332:1: ruleDeclaredVersion EOF
            {
             before(grammarAccess.getDeclaredVersionRule()); 
            pushFollow(FOLLOW_1);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getDeclaredVersionRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleDeclaredVersion"


    // $ANTLR start "ruleDeclaredVersion"
    // InternalN4MFParser.g:339:1: ruleDeclaredVersion : ( ( rule__DeclaredVersion__Group__0 ) ) ;
    public final void ruleDeclaredVersion() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:343:2: ( ( ( rule__DeclaredVersion__Group__0 ) ) )
            // InternalN4MFParser.g:344:2: ( ( rule__DeclaredVersion__Group__0 ) )
            {
            // InternalN4MFParser.g:344:2: ( ( rule__DeclaredVersion__Group__0 ) )
            // InternalN4MFParser.g:345:3: ( rule__DeclaredVersion__Group__0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup()); 
            // InternalN4MFParser.g:346:3: ( rule__DeclaredVersion__Group__0 )
            // InternalN4MFParser.g:346:4: rule__DeclaredVersion__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDeclaredVersionAccess().getGroup()); 

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
    // $ANTLR end "ruleDeclaredVersion"


    // $ANTLR start "entryRuleSourceFragment"
    // InternalN4MFParser.g:355:1: entryRuleSourceFragment : ruleSourceFragment EOF ;
    public final void entryRuleSourceFragment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:356:1: ( ruleSourceFragment EOF )
            // InternalN4MFParser.g:357:1: ruleSourceFragment EOF
            {
             before(grammarAccess.getSourceFragmentRule()); 
            pushFollow(FOLLOW_1);
            ruleSourceFragment();

            state._fsp--;

             after(grammarAccess.getSourceFragmentRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleSourceFragment"


    // $ANTLR start "ruleSourceFragment"
    // InternalN4MFParser.g:364:1: ruleSourceFragment : ( ( rule__SourceFragment__Group__0 ) ) ;
    public final void ruleSourceFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:368:2: ( ( ( rule__SourceFragment__Group__0 ) ) )
            // InternalN4MFParser.g:369:2: ( ( rule__SourceFragment__Group__0 ) )
            {
            // InternalN4MFParser.g:369:2: ( ( rule__SourceFragment__Group__0 ) )
            // InternalN4MFParser.g:370:3: ( rule__SourceFragment__Group__0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup()); 
            // InternalN4MFParser.g:371:3: ( rule__SourceFragment__Group__0 )
            // InternalN4MFParser.g:371:4: rule__SourceFragment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSourceFragmentAccess().getGroup()); 

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
    // $ANTLR end "ruleSourceFragment"


    // $ANTLR start "entryRuleModuleFilter"
    // InternalN4MFParser.g:380:1: entryRuleModuleFilter : ruleModuleFilter EOF ;
    public final void entryRuleModuleFilter() throws RecognitionException {
        try {
            // InternalN4MFParser.g:381:1: ( ruleModuleFilter EOF )
            // InternalN4MFParser.g:382:1: ruleModuleFilter EOF
            {
             before(grammarAccess.getModuleFilterRule()); 
            pushFollow(FOLLOW_1);
            ruleModuleFilter();

            state._fsp--;

             after(grammarAccess.getModuleFilterRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleModuleFilter"


    // $ANTLR start "ruleModuleFilter"
    // InternalN4MFParser.g:389:1: ruleModuleFilter : ( ( rule__ModuleFilter__Group__0 ) ) ;
    public final void ruleModuleFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:393:2: ( ( ( rule__ModuleFilter__Group__0 ) ) )
            // InternalN4MFParser.g:394:2: ( ( rule__ModuleFilter__Group__0 ) )
            {
            // InternalN4MFParser.g:394:2: ( ( rule__ModuleFilter__Group__0 ) )
            // InternalN4MFParser.g:395:3: ( rule__ModuleFilter__Group__0 )
            {
             before(grammarAccess.getModuleFilterAccess().getGroup()); 
            // InternalN4MFParser.g:396:3: ( rule__ModuleFilter__Group__0 )
            // InternalN4MFParser.g:396:4: rule__ModuleFilter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterAccess().getGroup()); 

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
    // $ANTLR end "ruleModuleFilter"


    // $ANTLR start "entryRuleBootstrapModule"
    // InternalN4MFParser.g:405:1: entryRuleBootstrapModule : ruleBootstrapModule EOF ;
    public final void entryRuleBootstrapModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:406:1: ( ruleBootstrapModule EOF )
            // InternalN4MFParser.g:407:1: ruleBootstrapModule EOF
            {
             before(grammarAccess.getBootstrapModuleRule()); 
            pushFollow(FOLLOW_1);
            ruleBootstrapModule();

            state._fsp--;

             after(grammarAccess.getBootstrapModuleRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleBootstrapModule"


    // $ANTLR start "ruleBootstrapModule"
    // InternalN4MFParser.g:414:1: ruleBootstrapModule : ( ( rule__BootstrapModule__Group__0 ) ) ;
    public final void ruleBootstrapModule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:418:2: ( ( ( rule__BootstrapModule__Group__0 ) ) )
            // InternalN4MFParser.g:419:2: ( ( rule__BootstrapModule__Group__0 ) )
            {
            // InternalN4MFParser.g:419:2: ( ( rule__BootstrapModule__Group__0 ) )
            // InternalN4MFParser.g:420:3: ( rule__BootstrapModule__Group__0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup()); 
            // InternalN4MFParser.g:421:3: ( rule__BootstrapModule__Group__0 )
            // InternalN4MFParser.g:421:4: rule__BootstrapModule__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BootstrapModule__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBootstrapModuleAccess().getGroup()); 

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
    // $ANTLR end "ruleBootstrapModule"


    // $ANTLR start "entryRuleModuleFilterSpecifier"
    // InternalN4MFParser.g:430:1: entryRuleModuleFilterSpecifier : ruleModuleFilterSpecifier EOF ;
    public final void entryRuleModuleFilterSpecifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:431:1: ( ruleModuleFilterSpecifier EOF )
            // InternalN4MFParser.g:432:1: ruleModuleFilterSpecifier EOF
            {
             before(grammarAccess.getModuleFilterSpecifierRule()); 
            pushFollow(FOLLOW_1);
            ruleModuleFilterSpecifier();

            state._fsp--;

             after(grammarAccess.getModuleFilterSpecifierRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleModuleFilterSpecifier"


    // $ANTLR start "ruleModuleFilterSpecifier"
    // InternalN4MFParser.g:439:1: ruleModuleFilterSpecifier : ( ( rule__ModuleFilterSpecifier__Group__0 ) ) ;
    public final void ruleModuleFilterSpecifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:443:2: ( ( ( rule__ModuleFilterSpecifier__Group__0 ) ) )
            // InternalN4MFParser.g:444:2: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            {
            // InternalN4MFParser.g:444:2: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            // InternalN4MFParser.g:445:3: ( rule__ModuleFilterSpecifier__Group__0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup()); 
            // InternalN4MFParser.g:446:3: ( rule__ModuleFilterSpecifier__Group__0 )
            // InternalN4MFParser.g:446:4: rule__ModuleFilterSpecifier__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterSpecifierAccess().getGroup()); 

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
    // $ANTLR end "ruleModuleFilterSpecifier"


    // $ANTLR start "entryRuleProvidedRuntimeLibraryDependency"
    // InternalN4MFParser.g:455:1: entryRuleProvidedRuntimeLibraryDependency : ruleProvidedRuntimeLibraryDependency EOF ;
    public final void entryRuleProvidedRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:456:1: ( ruleProvidedRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:457:1: ruleProvidedRuntimeLibraryDependency EOF
            {
             before(grammarAccess.getProvidedRuntimeLibraryDependencyRule()); 
            pushFollow(FOLLOW_1);
            ruleProvidedRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getProvidedRuntimeLibraryDependencyRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProvidedRuntimeLibraryDependency"


    // $ANTLR start "ruleProvidedRuntimeLibraryDependency"
    // InternalN4MFParser.g:464:1: ruleProvidedRuntimeLibraryDependency : ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleProvidedRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:468:2: ( ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:469:2: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:469:2: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:470:3: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:471:3: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:471:4: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraryDependency__ProjectAssignment();

            state._fsp--;


            }

             after(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectAssignment()); 

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
    // $ANTLR end "ruleProvidedRuntimeLibraryDependency"


    // $ANTLR start "entryRuleRequiredRuntimeLibraryDependency"
    // InternalN4MFParser.g:480:1: entryRuleRequiredRuntimeLibraryDependency : ruleRequiredRuntimeLibraryDependency EOF ;
    public final void entryRuleRequiredRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:481:1: ( ruleRequiredRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:482:1: ruleRequiredRuntimeLibraryDependency EOF
            {
             before(grammarAccess.getRequiredRuntimeLibraryDependencyRule()); 
            pushFollow(FOLLOW_1);
            ruleRequiredRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getRequiredRuntimeLibraryDependencyRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRequiredRuntimeLibraryDependency"


    // $ANTLR start "ruleRequiredRuntimeLibraryDependency"
    // InternalN4MFParser.g:489:1: ruleRequiredRuntimeLibraryDependency : ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleRequiredRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:493:2: ( ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:494:2: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:494:2: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:495:3: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:496:3: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:496:4: rule__RequiredRuntimeLibraryDependency__ProjectAssignment
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraryDependency__ProjectAssignment();

            state._fsp--;


            }

             after(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectAssignment()); 

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
    // $ANTLR end "ruleRequiredRuntimeLibraryDependency"


    // $ANTLR start "entryRuleTestedProject"
    // InternalN4MFParser.g:505:1: entryRuleTestedProject : ruleTestedProject EOF ;
    public final void entryRuleTestedProject() throws RecognitionException {
        try {
            // InternalN4MFParser.g:506:1: ( ruleTestedProject EOF )
            // InternalN4MFParser.g:507:1: ruleTestedProject EOF
            {
             before(grammarAccess.getTestedProjectRule()); 
            pushFollow(FOLLOW_1);
            ruleTestedProject();

            state._fsp--;

             after(grammarAccess.getTestedProjectRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleTestedProject"


    // $ANTLR start "ruleTestedProject"
    // InternalN4MFParser.g:514:1: ruleTestedProject : ( ( rule__TestedProject__ProjectAssignment ) ) ;
    public final void ruleTestedProject() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:518:2: ( ( ( rule__TestedProject__ProjectAssignment ) ) )
            // InternalN4MFParser.g:519:2: ( ( rule__TestedProject__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:519:2: ( ( rule__TestedProject__ProjectAssignment ) )
            // InternalN4MFParser.g:520:3: ( rule__TestedProject__ProjectAssignment )
            {
             before(grammarAccess.getTestedProjectAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:521:3: ( rule__TestedProject__ProjectAssignment )
            // InternalN4MFParser.g:521:4: rule__TestedProject__ProjectAssignment
            {
            pushFollow(FOLLOW_2);
            rule__TestedProject__ProjectAssignment();

            state._fsp--;


            }

             after(grammarAccess.getTestedProjectAccess().getProjectAssignment()); 

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
    // $ANTLR end "ruleTestedProject"


    // $ANTLR start "entryRuleProjectReference"
    // InternalN4MFParser.g:530:1: entryRuleProjectReference : ruleProjectReference EOF ;
    public final void entryRuleProjectReference() throws RecognitionException {
        try {
            // InternalN4MFParser.g:531:1: ( ruleProjectReference EOF )
            // InternalN4MFParser.g:532:1: ruleProjectReference EOF
            {
             before(grammarAccess.getProjectReferenceRule()); 
            pushFollow(FOLLOW_1);
            ruleProjectReference();

            state._fsp--;

             after(grammarAccess.getProjectReferenceRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProjectReference"


    // $ANTLR start "ruleProjectReference"
    // InternalN4MFParser.g:539:1: ruleProjectReference : ( ( rule__ProjectReference__ProjectAssignment ) ) ;
    public final void ruleProjectReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:543:2: ( ( ( rule__ProjectReference__ProjectAssignment ) ) )
            // InternalN4MFParser.g:544:2: ( ( rule__ProjectReference__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:544:2: ( ( rule__ProjectReference__ProjectAssignment ) )
            // InternalN4MFParser.g:545:3: ( rule__ProjectReference__ProjectAssignment )
            {
             before(grammarAccess.getProjectReferenceAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:546:3: ( rule__ProjectReference__ProjectAssignment )
            // InternalN4MFParser.g:546:4: rule__ProjectReference__ProjectAssignment
            {
            pushFollow(FOLLOW_2);
            rule__ProjectReference__ProjectAssignment();

            state._fsp--;


            }

             after(grammarAccess.getProjectReferenceAccess().getProjectAssignment()); 

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
    // $ANTLR end "ruleProjectReference"


    // $ANTLR start "entryRuleProjectDependency"
    // InternalN4MFParser.g:555:1: entryRuleProjectDependency : ruleProjectDependency EOF ;
    public final void entryRuleProjectDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:556:1: ( ruleProjectDependency EOF )
            // InternalN4MFParser.g:557:1: ruleProjectDependency EOF
            {
             before(grammarAccess.getProjectDependencyRule()); 
            pushFollow(FOLLOW_1);
            ruleProjectDependency();

            state._fsp--;

             after(grammarAccess.getProjectDependencyRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProjectDependency"


    // $ANTLR start "ruleProjectDependency"
    // InternalN4MFParser.g:564:1: ruleProjectDependency : ( ( rule__ProjectDependency__Group__0 ) ) ;
    public final void ruleProjectDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:568:2: ( ( ( rule__ProjectDependency__Group__0 ) ) )
            // InternalN4MFParser.g:569:2: ( ( rule__ProjectDependency__Group__0 ) )
            {
            // InternalN4MFParser.g:569:2: ( ( rule__ProjectDependency__Group__0 ) )
            // InternalN4MFParser.g:570:3: ( rule__ProjectDependency__Group__0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getGroup()); 
            // InternalN4MFParser.g:571:3: ( rule__ProjectDependency__Group__0 )
            // InternalN4MFParser.g:571:4: rule__ProjectDependency__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependency__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependencyAccess().getGroup()); 

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
    // $ANTLR end "ruleProjectDependency"


    // $ANTLR start "entryRuleSimpleProjectDescription"
    // InternalN4MFParser.g:580:1: entryRuleSimpleProjectDescription : ruleSimpleProjectDescription EOF ;
    public final void entryRuleSimpleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:581:1: ( ruleSimpleProjectDescription EOF )
            // InternalN4MFParser.g:582:1: ruleSimpleProjectDescription EOF
            {
             before(grammarAccess.getSimpleProjectDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getSimpleProjectDescriptionRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleSimpleProjectDescription"


    // $ANTLR start "ruleSimpleProjectDescription"
    // InternalN4MFParser.g:589:1: ruleSimpleProjectDescription : ( ( rule__SimpleProjectDescription__Group__0 ) ) ;
    public final void ruleSimpleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:593:2: ( ( ( rule__SimpleProjectDescription__Group__0 ) ) )
            // InternalN4MFParser.g:594:2: ( ( rule__SimpleProjectDescription__Group__0 ) )
            {
            // InternalN4MFParser.g:594:2: ( ( rule__SimpleProjectDescription__Group__0 ) )
            // InternalN4MFParser.g:595:3: ( rule__SimpleProjectDescription__Group__0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup()); 
            // InternalN4MFParser.g:596:3: ( rule__SimpleProjectDescription__Group__0 )
            // InternalN4MFParser.g:596:4: rule__SimpleProjectDescription__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleProjectDescriptionAccess().getGroup()); 

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
    // $ANTLR end "ruleSimpleProjectDescription"


    // $ANTLR start "entryRuleVersionConstraint"
    // InternalN4MFParser.g:605:1: entryRuleVersionConstraint : ruleVersionConstraint EOF ;
    public final void entryRuleVersionConstraint() throws RecognitionException {
        try {
            // InternalN4MFParser.g:606:1: ( ruleVersionConstraint EOF )
            // InternalN4MFParser.g:607:1: ruleVersionConstraint EOF
            {
             before(grammarAccess.getVersionConstraintRule()); 
            pushFollow(FOLLOW_1);
            ruleVersionConstraint();

            state._fsp--;

             after(grammarAccess.getVersionConstraintRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleVersionConstraint"


    // $ANTLR start "ruleVersionConstraint"
    // InternalN4MFParser.g:614:1: ruleVersionConstraint : ( ( rule__VersionConstraint__Alternatives ) ) ;
    public final void ruleVersionConstraint() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:618:2: ( ( ( rule__VersionConstraint__Alternatives ) ) )
            // InternalN4MFParser.g:619:2: ( ( rule__VersionConstraint__Alternatives ) )
            {
            // InternalN4MFParser.g:619:2: ( ( rule__VersionConstraint__Alternatives ) )
            // InternalN4MFParser.g:620:3: ( rule__VersionConstraint__Alternatives )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives()); 
            // InternalN4MFParser.g:621:3: ( rule__VersionConstraint__Alternatives )
            // InternalN4MFParser.g:621:4: rule__VersionConstraint__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getAlternatives()); 

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
    // $ANTLR end "ruleVersionConstraint"


    // $ANTLR start "entryRuleN4mfIdentifier"
    // InternalN4MFParser.g:630:1: entryRuleN4mfIdentifier : ruleN4mfIdentifier EOF ;
    public final void entryRuleN4mfIdentifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:631:1: ( ruleN4mfIdentifier EOF )
            // InternalN4MFParser.g:632:1: ruleN4mfIdentifier EOF
            {
             before(grammarAccess.getN4mfIdentifierRule()); 
            pushFollow(FOLLOW_1);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getN4mfIdentifierRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleN4mfIdentifier"


    // $ANTLR start "ruleN4mfIdentifier"
    // InternalN4MFParser.g:639:1: ruleN4mfIdentifier : ( ( rule__N4mfIdentifier__Alternatives ) ) ;
    public final void ruleN4mfIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:643:2: ( ( ( rule__N4mfIdentifier__Alternatives ) ) )
            // InternalN4MFParser.g:644:2: ( ( rule__N4mfIdentifier__Alternatives ) )
            {
            // InternalN4MFParser.g:644:2: ( ( rule__N4mfIdentifier__Alternatives ) )
            // InternalN4MFParser.g:645:3: ( rule__N4mfIdentifier__Alternatives )
            {
             before(grammarAccess.getN4mfIdentifierAccess().getAlternatives()); 
            // InternalN4MFParser.g:646:3: ( rule__N4mfIdentifier__Alternatives )
            // InternalN4MFParser.g:646:4: rule__N4mfIdentifier__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getN4mfIdentifierAccess().getAlternatives()); 

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
    // $ANTLR end "ruleN4mfIdentifier"


    // $ANTLR start "ruleProjectType"
    // InternalN4MFParser.g:655:1: ruleProjectType : ( ( rule__ProjectType__Alternatives ) ) ;
    public final void ruleProjectType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:659:1: ( ( ( rule__ProjectType__Alternatives ) ) )
            // InternalN4MFParser.g:660:2: ( ( rule__ProjectType__Alternatives ) )
            {
            // InternalN4MFParser.g:660:2: ( ( rule__ProjectType__Alternatives ) )
            // InternalN4MFParser.g:661:3: ( rule__ProjectType__Alternatives )
            {
             before(grammarAccess.getProjectTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:662:3: ( rule__ProjectType__Alternatives )
            // InternalN4MFParser.g:662:4: rule__ProjectType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ProjectType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getProjectTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleProjectType"


    // $ANTLR start "ruleSourceFragmentType"
    // InternalN4MFParser.g:671:1: ruleSourceFragmentType : ( ( rule__SourceFragmentType__Alternatives ) ) ;
    public final void ruleSourceFragmentType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:675:1: ( ( ( rule__SourceFragmentType__Alternatives ) ) )
            // InternalN4MFParser.g:676:2: ( ( rule__SourceFragmentType__Alternatives ) )
            {
            // InternalN4MFParser.g:676:2: ( ( rule__SourceFragmentType__Alternatives ) )
            // InternalN4MFParser.g:677:3: ( rule__SourceFragmentType__Alternatives )
            {
             before(grammarAccess.getSourceFragmentTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:678:3: ( rule__SourceFragmentType__Alternatives )
            // InternalN4MFParser.g:678:4: rule__SourceFragmentType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragmentType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSourceFragmentTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleSourceFragmentType"


    // $ANTLR start "ruleModuleFilterType"
    // InternalN4MFParser.g:687:1: ruleModuleFilterType : ( ( rule__ModuleFilterType__Alternatives ) ) ;
    public final void ruleModuleFilterType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:691:1: ( ( ( rule__ModuleFilterType__Alternatives ) ) )
            // InternalN4MFParser.g:692:2: ( ( rule__ModuleFilterType__Alternatives ) )
            {
            // InternalN4MFParser.g:692:2: ( ( rule__ModuleFilterType__Alternatives ) )
            // InternalN4MFParser.g:693:3: ( rule__ModuleFilterType__Alternatives )
            {
             before(grammarAccess.getModuleFilterTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:694:3: ( rule__ModuleFilterType__Alternatives )
            // InternalN4MFParser.g:694:4: rule__ModuleFilterType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleModuleFilterType"


    // $ANTLR start "ruleProjectDependencyScope"
    // InternalN4MFParser.g:703:1: ruleProjectDependencyScope : ( ( rule__ProjectDependencyScope__Alternatives ) ) ;
    public final void ruleProjectDependencyScope() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:707:1: ( ( ( rule__ProjectDependencyScope__Alternatives ) ) )
            // InternalN4MFParser.g:708:2: ( ( rule__ProjectDependencyScope__Alternatives ) )
            {
            // InternalN4MFParser.g:708:2: ( ( rule__ProjectDependencyScope__Alternatives ) )
            // InternalN4MFParser.g:709:3: ( rule__ProjectDependencyScope__Alternatives )
            {
             before(grammarAccess.getProjectDependencyScopeAccess().getAlternatives()); 
            // InternalN4MFParser.g:710:3: ( rule__ProjectDependencyScope__Alternatives )
            // InternalN4MFParser.g:710:4: rule__ProjectDependencyScope__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencyScope__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependencyScopeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleProjectDependencyScope"


    // $ANTLR start "ruleModuleLoader"
    // InternalN4MFParser.g:719:1: ruleModuleLoader : ( ( rule__ModuleLoader__Alternatives ) ) ;
    public final void ruleModuleLoader() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:723:1: ( ( ( rule__ModuleLoader__Alternatives ) ) )
            // InternalN4MFParser.g:724:2: ( ( rule__ModuleLoader__Alternatives ) )
            {
            // InternalN4MFParser.g:724:2: ( ( rule__ModuleLoader__Alternatives ) )
            // InternalN4MFParser.g:725:3: ( rule__ModuleLoader__Alternatives )
            {
             before(grammarAccess.getModuleLoaderAccess().getAlternatives()); 
            // InternalN4MFParser.g:726:3: ( rule__ModuleLoader__Alternatives )
            // InternalN4MFParser.g:726:4: rule__ModuleLoader__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ModuleLoader__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModuleLoaderAccess().getAlternatives()); 

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
    // $ANTLR end "ruleModuleLoader"


    // $ANTLR start "rule__VersionConstraint__Alternatives"
    // InternalN4MFParser.g:734:1: rule__VersionConstraint__Alternatives : ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) );
    public final void rule__VersionConstraint__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:738:1: ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==LeftParenthesis||LA1_0==LeftSquareBracket) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_INT) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalN4MFParser.g:739:2: ( ( rule__VersionConstraint__Group_0__0 ) )
                    {
                    // InternalN4MFParser.g:739:2: ( ( rule__VersionConstraint__Group_0__0 ) )
                    // InternalN4MFParser.g:740:3: ( rule__VersionConstraint__Group_0__0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0()); 
                    // InternalN4MFParser.g:741:3: ( rule__VersionConstraint__Group_0__0 )
                    // InternalN4MFParser.g:741:4: rule__VersionConstraint__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VersionConstraint__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVersionConstraintAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:745:2: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    {
                    // InternalN4MFParser.g:745:2: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    // InternalN4MFParser.g:746:3: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_1()); 
                    // InternalN4MFParser.g:747:3: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    // InternalN4MFParser.g:747:4: rule__VersionConstraint__LowerVersionAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__VersionConstraint__LowerVersionAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Alternatives"


    // $ANTLR start "rule__VersionConstraint__Alternatives_0_0"
    // InternalN4MFParser.g:755:1: rule__VersionConstraint__Alternatives_0_0 : ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:759:1: ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LeftParenthesis) ) {
                alt2=1;
            }
            else if ( (LA2_0==LeftSquareBracket) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalN4MFParser.g:760:2: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    {
                    // InternalN4MFParser.g:760:2: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    // InternalN4MFParser.g:761:3: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundAssignment_0_0_0()); 
                    // InternalN4MFParser.g:762:3: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    // InternalN4MFParser.g:762:4: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVersionConstraintAccess().getExclLowerBoundAssignment_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:766:2: ( LeftSquareBracket )
                    {
                    // InternalN4MFParser.g:766:2: ( LeftSquareBracket )
                    // InternalN4MFParser.g:767:3: LeftSquareBracket
                    {
                     before(grammarAccess.getVersionConstraintAccess().getLeftSquareBracketKeyword_0_0_1()); 
                    match(input,LeftSquareBracket,FOLLOW_2); 
                     after(grammarAccess.getVersionConstraintAccess().getLeftSquareBracketKeyword_0_0_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Alternatives_0_0"


    // $ANTLR start "rule__VersionConstraint__Alternatives_0_2"
    // InternalN4MFParser.g:776:1: rule__VersionConstraint__Alternatives_0_2 : ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) );
    public final void rule__VersionConstraint__Alternatives_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:780:1: ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==EOF||LA4_0==Compile||LA4_0==Test||LA4_0==Comma||LA4_0==RightCurlyBracket) ) {
                alt4=1;
            }
            else if ( (LA4_0==RightParenthesis) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalN4MFParser.g:781:2: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    {
                    // InternalN4MFParser.g:781:2: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    // InternalN4MFParser.g:782:3: ( rule__VersionConstraint__Group_0_2_0__0 )?
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0_2_0()); 
                    // InternalN4MFParser.g:783:3: ( rule__VersionConstraint__Group_0_2_0__0 )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==Comma) ) {
                        int LA3_1 = input.LA(2);

                        if ( (LA3_1==RULE_INT) ) {
                            alt3=1;
                        }
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalN4MFParser.g:783:4: rule__VersionConstraint__Group_0_2_0__0
                            {
                            pushFollow(FOLLOW_2);
                            rule__VersionConstraint__Group_0_2_0__0();

                            state._fsp--;


                            }
                            break;

                    }

                     after(grammarAccess.getVersionConstraintAccess().getGroup_0_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:787:2: ( RightParenthesis )
                    {
                    // InternalN4MFParser.g:787:2: ( RightParenthesis )
                    // InternalN4MFParser.g:788:3: RightParenthesis
                    {
                     before(grammarAccess.getVersionConstraintAccess().getRightParenthesisKeyword_0_2_1()); 
                    match(input,RightParenthesis,FOLLOW_2); 
                     after(grammarAccess.getVersionConstraintAccess().getRightParenthesisKeyword_0_2_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Alternatives_0_2"


    // $ANTLR start "rule__VersionConstraint__Alternatives_0_2_0_2"
    // InternalN4MFParser.g:797:1: rule__VersionConstraint__Alternatives_0_2_0_2 : ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:801:1: ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RightParenthesis) ) {
                alt5=1;
            }
            else if ( (LA5_0==RightSquareBracket) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalN4MFParser.g:802:2: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    {
                    // InternalN4MFParser.g:802:2: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    // InternalN4MFParser.g:803:3: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundAssignment_0_2_0_2_0()); 
                    // InternalN4MFParser.g:804:3: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    // InternalN4MFParser.g:804:4: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVersionConstraintAccess().getExclUpperBoundAssignment_0_2_0_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:808:2: ( RightSquareBracket )
                    {
                    // InternalN4MFParser.g:808:2: ( RightSquareBracket )
                    // InternalN4MFParser.g:809:3: RightSquareBracket
                    {
                     before(grammarAccess.getVersionConstraintAccess().getRightSquareBracketKeyword_0_2_0_2_1()); 
                    match(input,RightSquareBracket,FOLLOW_2); 
                     after(grammarAccess.getVersionConstraintAccess().getRightSquareBracketKeyword_0_2_0_2_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Alternatives_0_2_0_2"


    // $ANTLR start "rule__N4mfIdentifier__Alternatives"
    // InternalN4MFParser.g:818:1: rule__N4mfIdentifier__Alternatives : ( ( RULE_ID ) | ( ProjectId ) | ( ProjectType ) | ( ProjectVersion ) | ( VendorId ) | ( VendorName ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_11__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_15__0 ) ) | ( Content ) | ( Test ) );
    public final void rule__N4mfIdentifier__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:822:1: ( ( RULE_ID ) | ( ProjectId ) | ( ProjectType ) | ( ProjectVersion ) | ( VendorId ) | ( VendorName ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_11__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_15__0 ) ) | ( Content ) | ( Test ) )
            int alt6=18;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt6=1;
                }
                break;
            case ProjectId:
                {
                alt6=2;
                }
                break;
            case ProjectType:
                {
                alt6=3;
                }
                break;
            case ProjectVersion:
                {
                alt6=4;
                }
                break;
            case VendorId:
                {
                alt6=5;
                }
                break;
            case VendorName:
                {
                alt6=6;
                }
                break;
            case Output:
                {
                alt6=7;
                }
                break;
            case Libraries:
                {
                alt6=8;
                }
                break;
            case Resources:
                {
                alt6=9;
                }
                break;
            case Sources:
                {
                alt6=10;
                }
                break;
            case ModuleFilters:
                {
                alt6=11;
                }
                break;
            case ProjectDependencies:
                {
                alt6=12;
                }
                break;
            case API:
                {
                alt6=13;
                }
                break;
            case User:
                {
                alt6=14;
                }
                break;
            case Application:
                {
                alt6=15;
                }
                break;
            case Processor:
                {
                alt6=16;
                }
                break;
            case Content:
                {
                alt6=17;
                }
                break;
            case Test:
                {
                alt6=18;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalN4MFParser.g:823:2: ( RULE_ID )
                    {
                    // InternalN4MFParser.g:823:2: ( RULE_ID )
                    // InternalN4MFParser.g:824:3: RULE_ID
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:829:2: ( ProjectId )
                    {
                    // InternalN4MFParser.g:829:2: ( ProjectId )
                    // InternalN4MFParser.g:830:3: ProjectId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectIdKeyword_1()); 
                    match(input,ProjectId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectIdKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:835:2: ( ProjectType )
                    {
                    // InternalN4MFParser.g:835:2: ( ProjectType )
                    // InternalN4MFParser.g:836:3: ProjectType
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_2()); 
                    match(input,ProjectType,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:841:2: ( ProjectVersion )
                    {
                    // InternalN4MFParser.g:841:2: ( ProjectVersion )
                    // InternalN4MFParser.g:842:3: ProjectVersion
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_3()); 
                    match(input,ProjectVersion,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:847:2: ( VendorId )
                    {
                    // InternalN4MFParser.g:847:2: ( VendorId )
                    // InternalN4MFParser.g:848:3: VendorId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_4()); 
                    match(input,VendorId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:853:2: ( VendorName )
                    {
                    // InternalN4MFParser.g:853:2: ( VendorName )
                    // InternalN4MFParser.g:854:3: VendorName
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_5()); 
                    match(input,VendorName,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:859:2: ( Output )
                    {
                    // InternalN4MFParser.g:859:2: ( Output )
                    // InternalN4MFParser.g:860:3: Output
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_6()); 
                    match(input,Output,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:865:2: ( Libraries )
                    {
                    // InternalN4MFParser.g:865:2: ( Libraries )
                    // InternalN4MFParser.g:866:3: Libraries
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_7()); 
                    match(input,Libraries,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:871:2: ( Resources )
                    {
                    // InternalN4MFParser.g:871:2: ( Resources )
                    // InternalN4MFParser.g:872:3: Resources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_8()); 
                    match(input,Resources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:877:2: ( Sources )
                    {
                    // InternalN4MFParser.g:877:2: ( Sources )
                    // InternalN4MFParser.g:878:3: Sources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_9()); 
                    match(input,Sources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:883:2: ( ModuleFilters )
                    {
                    // InternalN4MFParser.g:883:2: ( ModuleFilters )
                    // InternalN4MFParser.g:884:3: ModuleFilters
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_10()); 
                    match(input,ModuleFilters,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:889:2: ( ( rule__N4mfIdentifier__Group_11__0 ) )
                    {
                    // InternalN4MFParser.g:889:2: ( ( rule__N4mfIdentifier__Group_11__0 ) )
                    // InternalN4MFParser.g:890:3: ( rule__N4mfIdentifier__Group_11__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_11()); 
                    // InternalN4MFParser.g:891:3: ( rule__N4mfIdentifier__Group_11__0 )
                    // InternalN4MFParser.g:891:4: rule__N4mfIdentifier__Group_11__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_11__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_11()); 

                    }


                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:895:2: ( API )
                    {
                    // InternalN4MFParser.g:895:2: ( API )
                    // InternalN4MFParser.g:896:3: API
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_12()); 
                    match(input,API,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_12()); 

                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:901:2: ( User )
                    {
                    // InternalN4MFParser.g:901:2: ( User )
                    // InternalN4MFParser.g:902:3: User
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_13()); 
                    match(input,User,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_13()); 

                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:907:2: ( Application )
                    {
                    // InternalN4MFParser.g:907:2: ( Application )
                    // InternalN4MFParser.g:908:3: Application
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_14()); 
                    match(input,Application,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:913:2: ( ( rule__N4mfIdentifier__Group_15__0 ) )
                    {
                    // InternalN4MFParser.g:913:2: ( ( rule__N4mfIdentifier__Group_15__0 ) )
                    // InternalN4MFParser.g:914:3: ( rule__N4mfIdentifier__Group_15__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_15()); 
                    // InternalN4MFParser.g:915:3: ( rule__N4mfIdentifier__Group_15__0 )
                    // InternalN4MFParser.g:915:4: rule__N4mfIdentifier__Group_15__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_15__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_15()); 

                    }


                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:919:2: ( Content )
                    {
                    // InternalN4MFParser.g:919:2: ( Content )
                    // InternalN4MFParser.g:920:3: Content
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_16()); 
                    match(input,Content,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_16()); 

                    }


                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:925:2: ( Test )
                    {
                    // InternalN4MFParser.g:925:2: ( Test )
                    // InternalN4MFParser.g:926:3: Test
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_17()); 
                    match(input,Test,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_17()); 

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
    // $ANTLR end "rule__N4mfIdentifier__Alternatives"


    // $ANTLR start "rule__ProjectType__Alternatives"
    // InternalN4MFParser.g:935:1: rule__ProjectType__Alternatives : ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) );
    public final void rule__ProjectType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:939:1: ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) )
            int alt7=7;
            switch ( input.LA(1) ) {
            case Application:
                {
                alt7=1;
                }
                break;
            case Processor:
                {
                alt7=2;
                }
                break;
            case Library:
                {
                alt7=3;
                }
                break;
            case API:
                {
                alt7=4;
                }
                break;
            case RuntimeEnvironment:
                {
                alt7=5;
                }
                break;
            case RuntimeLibrary:
                {
                alt7=6;
                }
                break;
            case Test:
                {
                alt7=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalN4MFParser.g:940:2: ( ( Application ) )
                    {
                    // InternalN4MFParser.g:940:2: ( ( Application ) )
                    // InternalN4MFParser.g:941:3: ( Application )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:942:3: ( Application )
                    // InternalN4MFParser.g:942:4: Application
                    {
                    match(input,Application,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:946:2: ( ( Processor ) )
                    {
                    // InternalN4MFParser.g:946:2: ( ( Processor ) )
                    // InternalN4MFParser.g:947:3: ( Processor )
                    {
                     before(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:948:3: ( Processor )
                    // InternalN4MFParser.g:948:4: Processor
                    {
                    match(input,Processor,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:952:2: ( ( Library ) )
                    {
                    // InternalN4MFParser.g:952:2: ( ( Library ) )
                    // InternalN4MFParser.g:953:3: ( Library )
                    {
                     before(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:954:3: ( Library )
                    // InternalN4MFParser.g:954:4: Library
                    {
                    match(input,Library,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:958:2: ( ( API ) )
                    {
                    // InternalN4MFParser.g:958:2: ( ( API ) )
                    // InternalN4MFParser.g:959:3: ( API )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 
                    // InternalN4MFParser.g:960:3: ( API )
                    // InternalN4MFParser.g:960:4: API
                    {
                    match(input,API,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:964:2: ( ( RuntimeEnvironment ) )
                    {
                    // InternalN4MFParser.g:964:2: ( ( RuntimeEnvironment ) )
                    // InternalN4MFParser.g:965:3: ( RuntimeEnvironment )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 
                    // InternalN4MFParser.g:966:3: ( RuntimeEnvironment )
                    // InternalN4MFParser.g:966:4: RuntimeEnvironment
                    {
                    match(input,RuntimeEnvironment,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:970:2: ( ( RuntimeLibrary ) )
                    {
                    // InternalN4MFParser.g:970:2: ( ( RuntimeLibrary ) )
                    // InternalN4MFParser.g:971:3: ( RuntimeLibrary )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 
                    // InternalN4MFParser.g:972:3: ( RuntimeLibrary )
                    // InternalN4MFParser.g:972:4: RuntimeLibrary
                    {
                    match(input,RuntimeLibrary,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:976:2: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:976:2: ( ( Test ) )
                    // InternalN4MFParser.g:977:3: ( Test )
                    {
                     before(grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6()); 
                    // InternalN4MFParser.g:978:3: ( Test )
                    // InternalN4MFParser.g:978:4: Test
                    {
                    match(input,Test,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6()); 

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
    // $ANTLR end "rule__ProjectType__Alternatives"


    // $ANTLR start "rule__SourceFragmentType__Alternatives"
    // InternalN4MFParser.g:986:1: rule__SourceFragmentType__Alternatives : ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) );
    public final void rule__SourceFragmentType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:990:1: ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) )
            int alt8=3;
            switch ( input.LA(1) ) {
            case Source:
                {
                alt8=1;
                }
                break;
            case External:
                {
                alt8=2;
                }
                break;
            case Test:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalN4MFParser.g:991:2: ( ( Source ) )
                    {
                    // InternalN4MFParser.g:991:2: ( ( Source ) )
                    // InternalN4MFParser.g:992:3: ( Source )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:993:3: ( Source )
                    // InternalN4MFParser.g:993:4: Source
                    {
                    match(input,Source,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:997:2: ( ( External ) )
                    {
                    // InternalN4MFParser.g:997:2: ( ( External ) )
                    // InternalN4MFParser.g:998:3: ( External )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:999:3: ( External )
                    // InternalN4MFParser.g:999:4: External
                    {
                    match(input,External,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1003:2: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1003:2: ( ( Test ) )
                    // InternalN4MFParser.g:1004:3: ( Test )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1005:3: ( Test )
                    // InternalN4MFParser.g:1005:4: Test
                    {
                    match(input,Test,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2()); 

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
    // $ANTLR end "rule__SourceFragmentType__Alternatives"


    // $ANTLR start "rule__ModuleFilterType__Alternatives"
    // InternalN4MFParser.g:1013:1: rule__ModuleFilterType__Alternatives : ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) );
    public final void rule__ModuleFilterType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1017:1: ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NoValidate) ) {
                alt9=1;
            }
            else if ( (LA9_0==NoModuleWrap) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalN4MFParser.g:1018:2: ( ( NoValidate ) )
                    {
                    // InternalN4MFParser.g:1018:2: ( ( NoValidate ) )
                    // InternalN4MFParser.g:1019:3: ( NoValidate )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1020:3: ( NoValidate )
                    // InternalN4MFParser.g:1020:4: NoValidate
                    {
                    match(input,NoValidate,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1024:2: ( ( NoModuleWrap ) )
                    {
                    // InternalN4MFParser.g:1024:2: ( ( NoModuleWrap ) )
                    // InternalN4MFParser.g:1025:3: ( NoModuleWrap )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1026:3: ( NoModuleWrap )
                    // InternalN4MFParser.g:1026:4: NoModuleWrap
                    {
                    match(input,NoModuleWrap,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__ModuleFilterType__Alternatives"


    // $ANTLR start "rule__ProjectDependencyScope__Alternatives"
    // InternalN4MFParser.g:1034:1: rule__ProjectDependencyScope__Alternatives : ( ( ( Compile ) ) | ( ( Test ) ) );
    public final void rule__ProjectDependencyScope__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1038:1: ( ( ( Compile ) ) | ( ( Test ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==Compile) ) {
                alt10=1;
            }
            else if ( (LA10_0==Test) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalN4MFParser.g:1039:2: ( ( Compile ) )
                    {
                    // InternalN4MFParser.g:1039:2: ( ( Compile ) )
                    // InternalN4MFParser.g:1040:3: ( Compile )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1041:3: ( Compile )
                    // InternalN4MFParser.g:1041:4: Compile
                    {
                    match(input,Compile,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1045:2: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1045:2: ( ( Test ) )
                    // InternalN4MFParser.g:1046:3: ( Test )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1047:3: ( Test )
                    // InternalN4MFParser.g:1047:4: Test
                    {
                    match(input,Test,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__ProjectDependencyScope__Alternatives"


    // $ANTLR start "rule__ModuleLoader__Alternatives"
    // InternalN4MFParser.g:1055:1: rule__ModuleLoader__Alternatives : ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) );
    public final void rule__ModuleLoader__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1059:1: ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) )
            int alt11=3;
            switch ( input.LA(1) ) {
            case N4js:
                {
                alt11=1;
                }
                break;
            case Commonjs:
                {
                alt11=2;
                }
                break;
            case Node_builtin:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalN4MFParser.g:1060:2: ( ( N4js ) )
                    {
                    // InternalN4MFParser.g:1060:2: ( ( N4js ) )
                    // InternalN4MFParser.g:1061:3: ( N4js )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1062:3: ( N4js )
                    // InternalN4MFParser.g:1062:4: N4js
                    {
                    match(input,N4js,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1066:2: ( ( Commonjs ) )
                    {
                    // InternalN4MFParser.g:1066:2: ( ( Commonjs ) )
                    // InternalN4MFParser.g:1067:3: ( Commonjs )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1068:3: ( Commonjs )
                    // InternalN4MFParser.g:1068:4: Commonjs
                    {
                    match(input,Commonjs,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1072:2: ( ( Node_builtin ) )
                    {
                    // InternalN4MFParser.g:1072:2: ( ( Node_builtin ) )
                    // InternalN4MFParser.g:1073:3: ( Node_builtin )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1074:3: ( Node_builtin )
                    // InternalN4MFParser.g:1074:4: Node_builtin
                    {
                    match(input,Node_builtin,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2()); 

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
    // $ANTLR end "rule__ModuleLoader__Alternatives"


    // $ANTLR start "rule__ProjectDescription__Group_0__0"
    // InternalN4MFParser.g:1082:1: rule__ProjectDescription__Group_0__0 : rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 ;
    public final void rule__ProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1086:1: ( rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:1087:2: rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_0__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_0__0"


    // $ANTLR start "rule__ProjectDescription__Group_0__0__Impl"
    // InternalN4MFParser.g:1094:1: rule__ProjectDescription__Group_0__0__Impl : ( ProjectId ) ;
    public final void rule__ProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1098:1: ( ( ProjectId ) )
            // InternalN4MFParser.g:1099:1: ( ProjectId )
            {
            // InternalN4MFParser.g:1099:1: ( ProjectId )
            // InternalN4MFParser.g:1100:2: ProjectId
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectIdKeyword_0_0()); 
            match(input,ProjectId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectIdKeyword_0_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_0__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_0__1"
    // InternalN4MFParser.g:1109:1: rule__ProjectDescription__Group_0__1 : rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 ;
    public final void rule__ProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1113:1: ( rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 )
            // InternalN4MFParser.g:1114:2: rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2
            {
            pushFollow(FOLLOW_4);
            rule__ProjectDescription__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_0__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_0__1"


    // $ANTLR start "rule__ProjectDescription__Group_0__1__Impl"
    // InternalN4MFParser.g:1121:1: rule__ProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1125:1: ( ( Colon ) )
            // InternalN4MFParser.g:1126:1: ( Colon )
            {
            // InternalN4MFParser.g:1126:1: ( Colon )
            // InternalN4MFParser.g:1127:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_0_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_0_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_0__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_0__2"
    // InternalN4MFParser.g:1136:1: rule__ProjectDescription__Group_0__2 : rule__ProjectDescription__Group_0__2__Impl ;
    public final void rule__ProjectDescription__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1140:1: ( rule__ProjectDescription__Group_0__2__Impl )
            // InternalN4MFParser.g:1141:2: rule__ProjectDescription__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_0__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_0__2"


    // $ANTLR start "rule__ProjectDescription__Group_0__2__Impl"
    // InternalN4MFParser.g:1147:1: rule__ProjectDescription__Group_0__2__Impl : ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) ) ;
    public final void rule__ProjectDescription__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1151:1: ( ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) ) )
            // InternalN4MFParser.g:1152:1: ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) )
            {
            // InternalN4MFParser.g:1152:1: ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) )
            // InternalN4MFParser.g:1153:2: ( rule__ProjectDescription__ProjectIdAssignment_0_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectIdAssignment_0_2()); 
            // InternalN4MFParser.g:1154:2: ( rule__ProjectDescription__ProjectIdAssignment_0_2 )
            // InternalN4MFParser.g:1154:3: rule__ProjectDescription__ProjectIdAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectIdAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectIdAssignment_0_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_0__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_1__0"
    // InternalN4MFParser.g:1163:1: rule__ProjectDescription__Group_1__0 : rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 ;
    public final void rule__ProjectDescription__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1167:1: ( rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 )
            // InternalN4MFParser.g:1168:2: rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_1__0"


    // $ANTLR start "rule__ProjectDescription__Group_1__0__Impl"
    // InternalN4MFParser.g:1175:1: rule__ProjectDescription__Group_1__0__Impl : ( ProjectType ) ;
    public final void rule__ProjectDescription__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1179:1: ( ( ProjectType ) )
            // InternalN4MFParser.g:1180:1: ( ProjectType )
            {
            // InternalN4MFParser.g:1180:1: ( ProjectType )
            // InternalN4MFParser.g:1181:2: ProjectType
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_1_0()); 
            match(input,ProjectType,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_1_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_1__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_1__1"
    // InternalN4MFParser.g:1190:1: rule__ProjectDescription__Group_1__1 : rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 ;
    public final void rule__ProjectDescription__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1194:1: ( rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 )
            // InternalN4MFParser.g:1195:2: rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__ProjectDescription__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_1__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_1__1"


    // $ANTLR start "rule__ProjectDescription__Group_1__1__Impl"
    // InternalN4MFParser.g:1202:1: rule__ProjectDescription__Group_1__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1206:1: ( ( Colon ) )
            // InternalN4MFParser.g:1207:1: ( Colon )
            {
            // InternalN4MFParser.g:1207:1: ( Colon )
            // InternalN4MFParser.g:1208:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_1_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_1_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_1__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_1__2"
    // InternalN4MFParser.g:1217:1: rule__ProjectDescription__Group_1__2 : rule__ProjectDescription__Group_1__2__Impl ;
    public final void rule__ProjectDescription__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1221:1: ( rule__ProjectDescription__Group_1__2__Impl )
            // InternalN4MFParser.g:1222:2: rule__ProjectDescription__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_1__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_1__2"


    // $ANTLR start "rule__ProjectDescription__Group_1__2__Impl"
    // InternalN4MFParser.g:1228:1: rule__ProjectDescription__Group_1__2__Impl : ( ( rule__ProjectDescription__ProjectTypeAssignment_1_2 ) ) ;
    public final void rule__ProjectDescription__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1232:1: ( ( ( rule__ProjectDescription__ProjectTypeAssignment_1_2 ) ) )
            // InternalN4MFParser.g:1233:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_1_2 ) )
            {
            // InternalN4MFParser.g:1233:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_1_2 ) )
            // InternalN4MFParser.g:1234:2: ( rule__ProjectDescription__ProjectTypeAssignment_1_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeAssignment_1_2()); 
            // InternalN4MFParser.g:1235:2: ( rule__ProjectDescription__ProjectTypeAssignment_1_2 )
            // InternalN4MFParser.g:1235:3: rule__ProjectDescription__ProjectTypeAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectTypeAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeAssignment_1_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_1__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_2__0"
    // InternalN4MFParser.g:1244:1: rule__ProjectDescription__Group_2__0 : rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 ;
    public final void rule__ProjectDescription__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1248:1: ( rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 )
            // InternalN4MFParser.g:1249:2: rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_2__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_2__0"


    // $ANTLR start "rule__ProjectDescription__Group_2__0__Impl"
    // InternalN4MFParser.g:1256:1: rule__ProjectDescription__Group_2__0__Impl : ( ProjectVersion ) ;
    public final void rule__ProjectDescription__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1260:1: ( ( ProjectVersion ) )
            // InternalN4MFParser.g:1261:1: ( ProjectVersion )
            {
            // InternalN4MFParser.g:1261:1: ( ProjectVersion )
            // InternalN4MFParser.g:1262:2: ProjectVersion
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_2_0()); 
            match(input,ProjectVersion,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_2__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_2__1"
    // InternalN4MFParser.g:1271:1: rule__ProjectDescription__Group_2__1 : rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 ;
    public final void rule__ProjectDescription__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1275:1: ( rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 )
            // InternalN4MFParser.g:1276:2: rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2
            {
            pushFollow(FOLLOW_6);
            rule__ProjectDescription__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_2__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_2__1"


    // $ANTLR start "rule__ProjectDescription__Group_2__1__Impl"
    // InternalN4MFParser.g:1283:1: rule__ProjectDescription__Group_2__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1287:1: ( ( Colon ) )
            // InternalN4MFParser.g:1288:1: ( Colon )
            {
            // InternalN4MFParser.g:1288:1: ( Colon )
            // InternalN4MFParser.g:1289:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_2_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_2_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_2__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_2__2"
    // InternalN4MFParser.g:1298:1: rule__ProjectDescription__Group_2__2 : rule__ProjectDescription__Group_2__2__Impl ;
    public final void rule__ProjectDescription__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1302:1: ( rule__ProjectDescription__Group_2__2__Impl )
            // InternalN4MFParser.g:1303:2: rule__ProjectDescription__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_2__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_2__2"


    // $ANTLR start "rule__ProjectDescription__Group_2__2__Impl"
    // InternalN4MFParser.g:1309:1: rule__ProjectDescription__Group_2__2__Impl : ( ( rule__ProjectDescription__ProjectVersionAssignment_2_2 ) ) ;
    public final void rule__ProjectDescription__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1313:1: ( ( ( rule__ProjectDescription__ProjectVersionAssignment_2_2 ) ) )
            // InternalN4MFParser.g:1314:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_2_2 ) )
            {
            // InternalN4MFParser.g:1314:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_2_2 ) )
            // InternalN4MFParser.g:1315:2: ( rule__ProjectDescription__ProjectVersionAssignment_2_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionAssignment_2_2()); 
            // InternalN4MFParser.g:1316:2: ( rule__ProjectDescription__ProjectVersionAssignment_2_2 )
            // InternalN4MFParser.g:1316:3: rule__ProjectDescription__ProjectVersionAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectVersionAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionAssignment_2_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_2__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_3__0"
    // InternalN4MFParser.g:1325:1: rule__ProjectDescription__Group_3__0 : rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 ;
    public final void rule__ProjectDescription__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1329:1: ( rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 )
            // InternalN4MFParser.g:1330:2: rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_3__0"


    // $ANTLR start "rule__ProjectDescription__Group_3__0__Impl"
    // InternalN4MFParser.g:1337:1: rule__ProjectDescription__Group_3__0__Impl : ( VendorId ) ;
    public final void rule__ProjectDescription__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1341:1: ( ( VendorId ) )
            // InternalN4MFParser.g:1342:1: ( VendorId )
            {
            // InternalN4MFParser.g:1342:1: ( VendorId )
            // InternalN4MFParser.g:1343:2: VendorId
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_3_0()); 
            match(input,VendorId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_3_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_3__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_3__1"
    // InternalN4MFParser.g:1352:1: rule__ProjectDescription__Group_3__1 : rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 ;
    public final void rule__ProjectDescription__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1356:1: ( rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 )
            // InternalN4MFParser.g:1357:2: rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2
            {
            pushFollow(FOLLOW_4);
            rule__ProjectDescription__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_3__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_3__1"


    // $ANTLR start "rule__ProjectDescription__Group_3__1__Impl"
    // InternalN4MFParser.g:1364:1: rule__ProjectDescription__Group_3__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1368:1: ( ( Colon ) )
            // InternalN4MFParser.g:1369:1: ( Colon )
            {
            // InternalN4MFParser.g:1369:1: ( Colon )
            // InternalN4MFParser.g:1370:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_3_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_3_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_3__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_3__2"
    // InternalN4MFParser.g:1379:1: rule__ProjectDescription__Group_3__2 : rule__ProjectDescription__Group_3__2__Impl ;
    public final void rule__ProjectDescription__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1383:1: ( rule__ProjectDescription__Group_3__2__Impl )
            // InternalN4MFParser.g:1384:2: rule__ProjectDescription__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_3__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_3__2"


    // $ANTLR start "rule__ProjectDescription__Group_3__2__Impl"
    // InternalN4MFParser.g:1390:1: rule__ProjectDescription__Group_3__2__Impl : ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 ) ) ;
    public final void rule__ProjectDescription__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1394:1: ( ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 ) ) )
            // InternalN4MFParser.g:1395:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 ) )
            {
            // InternalN4MFParser.g:1395:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 ) )
            // InternalN4MFParser.g:1396:2: ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdAssignment_3_2()); 
            // InternalN4MFParser.g:1397:2: ( rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 )
            // InternalN4MFParser.g:1397:3: rule__ProjectDescription__DeclaredVendorIdAssignment_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__DeclaredVendorIdAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdAssignment_3_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_3__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_4__0"
    // InternalN4MFParser.g:1406:1: rule__ProjectDescription__Group_4__0 : rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 ;
    public final void rule__ProjectDescription__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1410:1: ( rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 )
            // InternalN4MFParser.g:1411:2: rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_4__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_4__0"


    // $ANTLR start "rule__ProjectDescription__Group_4__0__Impl"
    // InternalN4MFParser.g:1418:1: rule__ProjectDescription__Group_4__0__Impl : ( VendorName ) ;
    public final void rule__ProjectDescription__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1422:1: ( ( VendorName ) )
            // InternalN4MFParser.g:1423:1: ( VendorName )
            {
            // InternalN4MFParser.g:1423:1: ( VendorName )
            // InternalN4MFParser.g:1424:2: VendorName
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_4_0()); 
            match(input,VendorName,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_4_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_4__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_4__1"
    // InternalN4MFParser.g:1433:1: rule__ProjectDescription__Group_4__1 : rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 ;
    public final void rule__ProjectDescription__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1437:1: ( rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 )
            // InternalN4MFParser.g:1438:2: rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_4__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_4__1"


    // $ANTLR start "rule__ProjectDescription__Group_4__1__Impl"
    // InternalN4MFParser.g:1445:1: rule__ProjectDescription__Group_4__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1449:1: ( ( Colon ) )
            // InternalN4MFParser.g:1450:1: ( Colon )
            {
            // InternalN4MFParser.g:1450:1: ( Colon )
            // InternalN4MFParser.g:1451:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_4_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_4_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_4__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_4__2"
    // InternalN4MFParser.g:1460:1: rule__ProjectDescription__Group_4__2 : rule__ProjectDescription__Group_4__2__Impl ;
    public final void rule__ProjectDescription__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1464:1: ( rule__ProjectDescription__Group_4__2__Impl )
            // InternalN4MFParser.g:1465:2: rule__ProjectDescription__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_4__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_4__2"


    // $ANTLR start "rule__ProjectDescription__Group_4__2__Impl"
    // InternalN4MFParser.g:1471:1: rule__ProjectDescription__Group_4__2__Impl : ( ( rule__ProjectDescription__VendorNameAssignment_4_2 ) ) ;
    public final void rule__ProjectDescription__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1475:1: ( ( ( rule__ProjectDescription__VendorNameAssignment_4_2 ) ) )
            // InternalN4MFParser.g:1476:1: ( ( rule__ProjectDescription__VendorNameAssignment_4_2 ) )
            {
            // InternalN4MFParser.g:1476:1: ( ( rule__ProjectDescription__VendorNameAssignment_4_2 ) )
            // InternalN4MFParser.g:1477:2: ( rule__ProjectDescription__VendorNameAssignment_4_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameAssignment_4_2()); 
            // InternalN4MFParser.g:1478:2: ( rule__ProjectDescription__VendorNameAssignment_4_2 )
            // InternalN4MFParser.g:1478:3: rule__ProjectDescription__VendorNameAssignment_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__VendorNameAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getVendorNameAssignment_4_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_4__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_5__0"
    // InternalN4MFParser.g:1487:1: rule__ProjectDescription__Group_5__0 : rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 ;
    public final void rule__ProjectDescription__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1491:1: ( rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 )
            // InternalN4MFParser.g:1492:2: rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_5__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_5__0"


    // $ANTLR start "rule__ProjectDescription__Group_5__0__Impl"
    // InternalN4MFParser.g:1499:1: rule__ProjectDescription__Group_5__0__Impl : ( MainModule ) ;
    public final void rule__ProjectDescription__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1503:1: ( ( MainModule ) )
            // InternalN4MFParser.g:1504:1: ( MainModule )
            {
            // InternalN4MFParser.g:1504:1: ( MainModule )
            // InternalN4MFParser.g:1505:2: MainModule
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_5_0()); 
            match(input,MainModule,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_5_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_5__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_5__1"
    // InternalN4MFParser.g:1514:1: rule__ProjectDescription__Group_5__1 : rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 ;
    public final void rule__ProjectDescription__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1518:1: ( rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 )
            // InternalN4MFParser.g:1519:2: rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_5__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_5__1"


    // $ANTLR start "rule__ProjectDescription__Group_5__1__Impl"
    // InternalN4MFParser.g:1526:1: rule__ProjectDescription__Group_5__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1530:1: ( ( Colon ) )
            // InternalN4MFParser.g:1531:1: ( Colon )
            {
            // InternalN4MFParser.g:1531:1: ( Colon )
            // InternalN4MFParser.g:1532:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_5_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_5_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_5__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_5__2"
    // InternalN4MFParser.g:1541:1: rule__ProjectDescription__Group_5__2 : rule__ProjectDescription__Group_5__2__Impl ;
    public final void rule__ProjectDescription__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1545:1: ( rule__ProjectDescription__Group_5__2__Impl )
            // InternalN4MFParser.g:1546:2: rule__ProjectDescription__Group_5__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_5__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_5__2"


    // $ANTLR start "rule__ProjectDescription__Group_5__2__Impl"
    // InternalN4MFParser.g:1552:1: rule__ProjectDescription__Group_5__2__Impl : ( ( rule__ProjectDescription__MainModuleAssignment_5_2 ) ) ;
    public final void rule__ProjectDescription__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1556:1: ( ( ( rule__ProjectDescription__MainModuleAssignment_5_2 ) ) )
            // InternalN4MFParser.g:1557:1: ( ( rule__ProjectDescription__MainModuleAssignment_5_2 ) )
            {
            // InternalN4MFParser.g:1557:1: ( ( rule__ProjectDescription__MainModuleAssignment_5_2 ) )
            // InternalN4MFParser.g:1558:2: ( rule__ProjectDescription__MainModuleAssignment_5_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleAssignment_5_2()); 
            // InternalN4MFParser.g:1559:2: ( rule__ProjectDescription__MainModuleAssignment_5_2 )
            // InternalN4MFParser.g:1559:3: rule__ProjectDescription__MainModuleAssignment_5_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__MainModuleAssignment_5_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getMainModuleAssignment_5_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_5__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_10__0"
    // InternalN4MFParser.g:1568:1: rule__ProjectDescription__Group_10__0 : rule__ProjectDescription__Group_10__0__Impl rule__ProjectDescription__Group_10__1 ;
    public final void rule__ProjectDescription__Group_10__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1572:1: ( rule__ProjectDescription__Group_10__0__Impl rule__ProjectDescription__Group_10__1 )
            // InternalN4MFParser.g:1573:2: rule__ProjectDescription__Group_10__0__Impl rule__ProjectDescription__Group_10__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_10__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_10__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_10__0"


    // $ANTLR start "rule__ProjectDescription__Group_10__0__Impl"
    // InternalN4MFParser.g:1580:1: rule__ProjectDescription__Group_10__0__Impl : ( ImplementationId ) ;
    public final void rule__ProjectDescription__Group_10__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1584:1: ( ( ImplementationId ) )
            // InternalN4MFParser.g:1585:1: ( ImplementationId )
            {
            // InternalN4MFParser.g:1585:1: ( ImplementationId )
            // InternalN4MFParser.g:1586:2: ImplementationId
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_10_0()); 
            match(input,ImplementationId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_10_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_10__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_10__1"
    // InternalN4MFParser.g:1595:1: rule__ProjectDescription__Group_10__1 : rule__ProjectDescription__Group_10__1__Impl rule__ProjectDescription__Group_10__2 ;
    public final void rule__ProjectDescription__Group_10__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1599:1: ( rule__ProjectDescription__Group_10__1__Impl rule__ProjectDescription__Group_10__2 )
            // InternalN4MFParser.g:1600:2: rule__ProjectDescription__Group_10__1__Impl rule__ProjectDescription__Group_10__2
            {
            pushFollow(FOLLOW_4);
            rule__ProjectDescription__Group_10__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_10__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_10__1"


    // $ANTLR start "rule__ProjectDescription__Group_10__1__Impl"
    // InternalN4MFParser.g:1607:1: rule__ProjectDescription__Group_10__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_10__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1611:1: ( ( Colon ) )
            // InternalN4MFParser.g:1612:1: ( Colon )
            {
            // InternalN4MFParser.g:1612:1: ( Colon )
            // InternalN4MFParser.g:1613:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_10_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_10_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_10__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_10__2"
    // InternalN4MFParser.g:1622:1: rule__ProjectDescription__Group_10__2 : rule__ProjectDescription__Group_10__2__Impl ;
    public final void rule__ProjectDescription__Group_10__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1626:1: ( rule__ProjectDescription__Group_10__2__Impl )
            // InternalN4MFParser.g:1627:2: rule__ProjectDescription__Group_10__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_10__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_10__2"


    // $ANTLR start "rule__ProjectDescription__Group_10__2__Impl"
    // InternalN4MFParser.g:1633:1: rule__ProjectDescription__Group_10__2__Impl : ( ( rule__ProjectDescription__ImplementationIdAssignment_10_2 ) ) ;
    public final void rule__ProjectDescription__Group_10__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1637:1: ( ( ( rule__ProjectDescription__ImplementationIdAssignment_10_2 ) ) )
            // InternalN4MFParser.g:1638:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_10_2 ) )
            {
            // InternalN4MFParser.g:1638:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_10_2 ) )
            // InternalN4MFParser.g:1639:2: ( rule__ProjectDescription__ImplementationIdAssignment_10_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdAssignment_10_2()); 
            // InternalN4MFParser.g:1640:2: ( rule__ProjectDescription__ImplementationIdAssignment_10_2 )
            // InternalN4MFParser.g:1640:3: rule__ProjectDescription__ImplementationIdAssignment_10_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ImplementationIdAssignment_10_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdAssignment_10_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_10__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_14__0"
    // InternalN4MFParser.g:1649:1: rule__ProjectDescription__Group_14__0 : rule__ProjectDescription__Group_14__0__Impl rule__ProjectDescription__Group_14__1 ;
    public final void rule__ProjectDescription__Group_14__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1653:1: ( rule__ProjectDescription__Group_14__0__Impl rule__ProjectDescription__Group_14__1 )
            // InternalN4MFParser.g:1654:2: rule__ProjectDescription__Group_14__0__Impl rule__ProjectDescription__Group_14__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_14__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_14__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_14__0"


    // $ANTLR start "rule__ProjectDescription__Group_14__0__Impl"
    // InternalN4MFParser.g:1661:1: rule__ProjectDescription__Group_14__0__Impl : ( Output ) ;
    public final void rule__ProjectDescription__Group_14__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1665:1: ( ( Output ) )
            // InternalN4MFParser.g:1666:1: ( Output )
            {
            // InternalN4MFParser.g:1666:1: ( Output )
            // InternalN4MFParser.g:1667:2: Output
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputKeyword_14_0()); 
            match(input,Output,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getOutputKeyword_14_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_14__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_14__1"
    // InternalN4MFParser.g:1676:1: rule__ProjectDescription__Group_14__1 : rule__ProjectDescription__Group_14__1__Impl rule__ProjectDescription__Group_14__2 ;
    public final void rule__ProjectDescription__Group_14__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1680:1: ( rule__ProjectDescription__Group_14__1__Impl rule__ProjectDescription__Group_14__2 )
            // InternalN4MFParser.g:1681:2: rule__ProjectDescription__Group_14__1__Impl rule__ProjectDescription__Group_14__2
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_14__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_14__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_14__1"


    // $ANTLR start "rule__ProjectDescription__Group_14__1__Impl"
    // InternalN4MFParser.g:1688:1: rule__ProjectDescription__Group_14__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_14__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1692:1: ( ( Colon ) )
            // InternalN4MFParser.g:1693:1: ( Colon )
            {
            // InternalN4MFParser.g:1693:1: ( Colon )
            // InternalN4MFParser.g:1694:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_14_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_14_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_14__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_14__2"
    // InternalN4MFParser.g:1703:1: rule__ProjectDescription__Group_14__2 : rule__ProjectDescription__Group_14__2__Impl ;
    public final void rule__ProjectDescription__Group_14__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1707:1: ( rule__ProjectDescription__Group_14__2__Impl )
            // InternalN4MFParser.g:1708:2: rule__ProjectDescription__Group_14__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_14__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_14__2"


    // $ANTLR start "rule__ProjectDescription__Group_14__2__Impl"
    // InternalN4MFParser.g:1714:1: rule__ProjectDescription__Group_14__2__Impl : ( ( rule__ProjectDescription__OutputPathAssignment_14_2 ) ) ;
    public final void rule__ProjectDescription__Group_14__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1718:1: ( ( ( rule__ProjectDescription__OutputPathAssignment_14_2 ) ) )
            // InternalN4MFParser.g:1719:1: ( ( rule__ProjectDescription__OutputPathAssignment_14_2 ) )
            {
            // InternalN4MFParser.g:1719:1: ( ( rule__ProjectDescription__OutputPathAssignment_14_2 ) )
            // InternalN4MFParser.g:1720:2: ( rule__ProjectDescription__OutputPathAssignment_14_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputPathAssignment_14_2()); 
            // InternalN4MFParser.g:1721:2: ( rule__ProjectDescription__OutputPathAssignment_14_2 )
            // InternalN4MFParser.g:1721:3: rule__ProjectDescription__OutputPathAssignment_14_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__OutputPathAssignment_14_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getOutputPathAssignment_14_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_14__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__0"
    // InternalN4MFParser.g:1730:1: rule__ProjectDescription__Group_15__0 : rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 ;
    public final void rule__ProjectDescription__Group_15__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1734:1: ( rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 )
            // InternalN4MFParser.g:1735:2: rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDescription__Group_15__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15__0"


    // $ANTLR start "rule__ProjectDescription__Group_15__0__Impl"
    // InternalN4MFParser.g:1742:1: rule__ProjectDescription__Group_15__0__Impl : ( Libraries ) ;
    public final void rule__ProjectDescription__Group_15__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1746:1: ( ( Libraries ) )
            // InternalN4MFParser.g:1747:1: ( Libraries )
            {
            // InternalN4MFParser.g:1747:1: ( Libraries )
            // InternalN4MFParser.g:1748:2: Libraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_15_0()); 
            match(input,Libraries,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_15_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__1"
    // InternalN4MFParser.g:1757:1: rule__ProjectDescription__Group_15__1 : rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 ;
    public final void rule__ProjectDescription__Group_15__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1761:1: ( rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 )
            // InternalN4MFParser.g:1762:2: rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_15__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15__1"


    // $ANTLR start "rule__ProjectDescription__Group_15__1__Impl"
    // InternalN4MFParser.g:1769:1: rule__ProjectDescription__Group_15__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_15__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1773:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:1774:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:1774:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:1775:2: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_15_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_15_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__2"
    // InternalN4MFParser.g:1784:1: rule__ProjectDescription__Group_15__2 : rule__ProjectDescription__Group_15__2__Impl rule__ProjectDescription__Group_15__3 ;
    public final void rule__ProjectDescription__Group_15__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1788:1: ( rule__ProjectDescription__Group_15__2__Impl rule__ProjectDescription__Group_15__3 )
            // InternalN4MFParser.g:1789:2: rule__ProjectDescription__Group_15__2__Impl rule__ProjectDescription__Group_15__3
            {
            pushFollow(FOLLOW_9);
            rule__ProjectDescription__Group_15__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15__2"


    // $ANTLR start "rule__ProjectDescription__Group_15__2__Impl"
    // InternalN4MFParser.g:1796:1: rule__ProjectDescription__Group_15__2__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_15_2 ) ) ;
    public final void rule__ProjectDescription__Group_15__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1800:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_15_2 ) ) )
            // InternalN4MFParser.g:1801:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_15_2 ) )
            {
            // InternalN4MFParser.g:1801:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_15_2 ) )
            // InternalN4MFParser.g:1802:2: ( rule__ProjectDescription__LibraryPathsAssignment_15_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_15_2()); 
            // InternalN4MFParser.g:1803:2: ( rule__ProjectDescription__LibraryPathsAssignment_15_2 )
            // InternalN4MFParser.g:1803:3: rule__ProjectDescription__LibraryPathsAssignment_15_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__LibraryPathsAssignment_15_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_15_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__3"
    // InternalN4MFParser.g:1811:1: rule__ProjectDescription__Group_15__3 : rule__ProjectDescription__Group_15__3__Impl rule__ProjectDescription__Group_15__4 ;
    public final void rule__ProjectDescription__Group_15__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1815:1: ( rule__ProjectDescription__Group_15__3__Impl rule__ProjectDescription__Group_15__4 )
            // InternalN4MFParser.g:1816:2: rule__ProjectDescription__Group_15__3__Impl rule__ProjectDescription__Group_15__4
            {
            pushFollow(FOLLOW_9);
            rule__ProjectDescription__Group_15__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__4();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15__3"


    // $ANTLR start "rule__ProjectDescription__Group_15__3__Impl"
    // InternalN4MFParser.g:1823:1: rule__ProjectDescription__Group_15__3__Impl : ( ( rule__ProjectDescription__Group_15_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_15__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1827:1: ( ( ( rule__ProjectDescription__Group_15_3__0 )* ) )
            // InternalN4MFParser.g:1828:1: ( ( rule__ProjectDescription__Group_15_3__0 )* )
            {
            // InternalN4MFParser.g:1828:1: ( ( rule__ProjectDescription__Group_15_3__0 )* )
            // InternalN4MFParser.g:1829:2: ( rule__ProjectDescription__Group_15_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_15_3()); 
            // InternalN4MFParser.g:1830:2: ( rule__ProjectDescription__Group_15_3__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==Comma) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalN4MFParser.g:1830:3: rule__ProjectDescription__Group_15_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDescription__Group_15_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getGroup_15_3()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15__3__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__4"
    // InternalN4MFParser.g:1838:1: rule__ProjectDescription__Group_15__4 : rule__ProjectDescription__Group_15__4__Impl ;
    public final void rule__ProjectDescription__Group_15__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1842:1: ( rule__ProjectDescription__Group_15__4__Impl )
            // InternalN4MFParser.g:1843:2: rule__ProjectDescription__Group_15__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15__4"


    // $ANTLR start "rule__ProjectDescription__Group_15__4__Impl"
    // InternalN4MFParser.g:1849:1: rule__ProjectDescription__Group_15__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_15__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1853:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:1854:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:1854:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:1855:2: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_15_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_15_4()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15__4__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15_3__0"
    // InternalN4MFParser.g:1865:1: rule__ProjectDescription__Group_15_3__0 : rule__ProjectDescription__Group_15_3__0__Impl rule__ProjectDescription__Group_15_3__1 ;
    public final void rule__ProjectDescription__Group_15_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1869:1: ( rule__ProjectDescription__Group_15_3__0__Impl rule__ProjectDescription__Group_15_3__1 )
            // InternalN4MFParser.g:1870:2: rule__ProjectDescription__Group_15_3__0__Impl rule__ProjectDescription__Group_15_3__1
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_15_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15_3__0"


    // $ANTLR start "rule__ProjectDescription__Group_15_3__0__Impl"
    // InternalN4MFParser.g:1877:1: rule__ProjectDescription__Group_15_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_15_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1881:1: ( ( Comma ) )
            // InternalN4MFParser.g:1882:1: ( Comma )
            {
            // InternalN4MFParser.g:1882:1: ( Comma )
            // InternalN4MFParser.g:1883:2: Comma
            {
             before(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_15_3_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_15_3_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15_3__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15_3__1"
    // InternalN4MFParser.g:1892:1: rule__ProjectDescription__Group_15_3__1 : rule__ProjectDescription__Group_15_3__1__Impl ;
    public final void rule__ProjectDescription__Group_15_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1896:1: ( rule__ProjectDescription__Group_15_3__1__Impl )
            // InternalN4MFParser.g:1897:2: rule__ProjectDescription__Group_15_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_15_3__1"


    // $ANTLR start "rule__ProjectDescription__Group_15_3__1__Impl"
    // InternalN4MFParser.g:1903:1: rule__ProjectDescription__Group_15_3__1__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_15_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1907:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 ) ) )
            // InternalN4MFParser.g:1908:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 ) )
            {
            // InternalN4MFParser.g:1908:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 ) )
            // InternalN4MFParser.g:1909:2: ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_15_3_1()); 
            // InternalN4MFParser.g:1910:2: ( rule__ProjectDescription__LibraryPathsAssignment_15_3_1 )
            // InternalN4MFParser.g:1910:3: rule__ProjectDescription__LibraryPathsAssignment_15_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__LibraryPathsAssignment_15_3_1();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_15_3_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_15_3__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16__0"
    // InternalN4MFParser.g:1919:1: rule__ProjectDescription__Group_16__0 : rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 ;
    public final void rule__ProjectDescription__Group_16__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1923:1: ( rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 )
            // InternalN4MFParser.g:1924:2: rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDescription__Group_16__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16__0"


    // $ANTLR start "rule__ProjectDescription__Group_16__0__Impl"
    // InternalN4MFParser.g:1931:1: rule__ProjectDescription__Group_16__0__Impl : ( Resources ) ;
    public final void rule__ProjectDescription__Group_16__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1935:1: ( ( Resources ) )
            // InternalN4MFParser.g:1936:1: ( Resources )
            {
            // InternalN4MFParser.g:1936:1: ( Resources )
            // InternalN4MFParser.g:1937:2: Resources
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_16_0()); 
            match(input,Resources,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_16_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16__1"
    // InternalN4MFParser.g:1946:1: rule__ProjectDescription__Group_16__1 : rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 ;
    public final void rule__ProjectDescription__Group_16__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1950:1: ( rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 )
            // InternalN4MFParser.g:1951:2: rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_16__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16__1"


    // $ANTLR start "rule__ProjectDescription__Group_16__1__Impl"
    // InternalN4MFParser.g:1958:1: rule__ProjectDescription__Group_16__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1962:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:1963:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:1963:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:1964:2: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_16_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_16_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16__2"
    // InternalN4MFParser.g:1973:1: rule__ProjectDescription__Group_16__2 : rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 ;
    public final void rule__ProjectDescription__Group_16__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1977:1: ( rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 )
            // InternalN4MFParser.g:1978:2: rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3
            {
            pushFollow(FOLLOW_9);
            rule__ProjectDescription__Group_16__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16__2"


    // $ANTLR start "rule__ProjectDescription__Group_16__2__Impl"
    // InternalN4MFParser.g:1985:1: rule__ProjectDescription__Group_16__2__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_16_2 ) ) ;
    public final void rule__ProjectDescription__Group_16__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:1989:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_16_2 ) ) )
            // InternalN4MFParser.g:1990:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_16_2 ) )
            {
            // InternalN4MFParser.g:1990:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_16_2 ) )
            // InternalN4MFParser.g:1991:2: ( rule__ProjectDescription__ResourcePathsAssignment_16_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_16_2()); 
            // InternalN4MFParser.g:1992:2: ( rule__ProjectDescription__ResourcePathsAssignment_16_2 )
            // InternalN4MFParser.g:1992:3: rule__ProjectDescription__ResourcePathsAssignment_16_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ResourcePathsAssignment_16_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_16_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16__3"
    // InternalN4MFParser.g:2000:1: rule__ProjectDescription__Group_16__3 : rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 ;
    public final void rule__ProjectDescription__Group_16__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2004:1: ( rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 )
            // InternalN4MFParser.g:2005:2: rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4
            {
            pushFollow(FOLLOW_9);
            rule__ProjectDescription__Group_16__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16__4();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16__3"


    // $ANTLR start "rule__ProjectDescription__Group_16__3__Impl"
    // InternalN4MFParser.g:2012:1: rule__ProjectDescription__Group_16__3__Impl : ( ( rule__ProjectDescription__Group_16_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_16__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2016:1: ( ( ( rule__ProjectDescription__Group_16_3__0 )* ) )
            // InternalN4MFParser.g:2017:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            {
            // InternalN4MFParser.g:2017:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            // InternalN4MFParser.g:2018:2: ( rule__ProjectDescription__Group_16_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_16_3()); 
            // InternalN4MFParser.g:2019:2: ( rule__ProjectDescription__Group_16_3__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Comma) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalN4MFParser.g:2019:3: rule__ProjectDescription__Group_16_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDescription__Group_16_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getGroup_16_3()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16__3__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16__4"
    // InternalN4MFParser.g:2027:1: rule__ProjectDescription__Group_16__4 : rule__ProjectDescription__Group_16__4__Impl ;
    public final void rule__ProjectDescription__Group_16__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2031:1: ( rule__ProjectDescription__Group_16__4__Impl )
            // InternalN4MFParser.g:2032:2: rule__ProjectDescription__Group_16__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16__4"


    // $ANTLR start "rule__ProjectDescription__Group_16__4__Impl"
    // InternalN4MFParser.g:2038:1: rule__ProjectDescription__Group_16__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2042:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2043:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2043:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2044:2: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_16_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_16_4()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16__4__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16_3__0"
    // InternalN4MFParser.g:2054:1: rule__ProjectDescription__Group_16_3__0 : rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 ;
    public final void rule__ProjectDescription__Group_16_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2058:1: ( rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 )
            // InternalN4MFParser.g:2059:2: rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1
            {
            pushFollow(FOLLOW_7);
            rule__ProjectDescription__Group_16_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16_3__0"


    // $ANTLR start "rule__ProjectDescription__Group_16_3__0__Impl"
    // InternalN4MFParser.g:2066:1: rule__ProjectDescription__Group_16_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_16_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2070:1: ( ( Comma ) )
            // InternalN4MFParser.g:2071:1: ( Comma )
            {
            // InternalN4MFParser.g:2071:1: ( Comma )
            // InternalN4MFParser.g:2072:2: Comma
            {
             before(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_16_3_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_16_3_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16_3__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_16_3__1"
    // InternalN4MFParser.g:2081:1: rule__ProjectDescription__Group_16_3__1 : rule__ProjectDescription__Group_16_3__1__Impl ;
    public final void rule__ProjectDescription__Group_16_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2085:1: ( rule__ProjectDescription__Group_16_3__1__Impl )
            // InternalN4MFParser.g:2086:2: rule__ProjectDescription__Group_16_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_16_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_16_3__1"


    // $ANTLR start "rule__ProjectDescription__Group_16_3__1__Impl"
    // InternalN4MFParser.g:2092:1: rule__ProjectDescription__Group_16_3__1__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_16_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2096:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 ) ) )
            // InternalN4MFParser.g:2097:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 ) )
            {
            // InternalN4MFParser.g:2097:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 ) )
            // InternalN4MFParser.g:2098:2: ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_16_3_1()); 
            // InternalN4MFParser.g:2099:2: ( rule__ProjectDescription__ResourcePathsAssignment_16_3_1 )
            // InternalN4MFParser.g:2099:3: rule__ProjectDescription__ResourcePathsAssignment_16_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ResourcePathsAssignment_16_3_1();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_16_3_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_16_3__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17__0"
    // InternalN4MFParser.g:2108:1: rule__ProjectDescription__Group_17__0 : rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 ;
    public final void rule__ProjectDescription__Group_17__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2112:1: ( rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 )
            // InternalN4MFParser.g:2113:2: rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDescription__Group_17__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_17__0"


    // $ANTLR start "rule__ProjectDescription__Group_17__0__Impl"
    // InternalN4MFParser.g:2120:1: rule__ProjectDescription__Group_17__0__Impl : ( Sources ) ;
    public final void rule__ProjectDescription__Group_17__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2124:1: ( ( Sources ) )
            // InternalN4MFParser.g:2125:1: ( Sources )
            {
            // InternalN4MFParser.g:2125:1: ( Sources )
            // InternalN4MFParser.g:2126:2: Sources
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_17_0()); 
            match(input,Sources,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_17_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_17__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17__1"
    // InternalN4MFParser.g:2135:1: rule__ProjectDescription__Group_17__1 : rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 ;
    public final void rule__ProjectDescription__Group_17__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2139:1: ( rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 )
            // InternalN4MFParser.g:2140:2: rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2
            {
            pushFollow(FOLLOW_11);
            rule__ProjectDescription__Group_17__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_17__1"


    // $ANTLR start "rule__ProjectDescription__Group_17__1__Impl"
    // InternalN4MFParser.g:2147:1: rule__ProjectDescription__Group_17__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2151:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2152:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2152:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2153:2: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_17_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_17_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_17__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17__2"
    // InternalN4MFParser.g:2162:1: rule__ProjectDescription__Group_17__2 : rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 ;
    public final void rule__ProjectDescription__Group_17__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2166:1: ( rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 )
            // InternalN4MFParser.g:2167:2: rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3
            {
            pushFollow(FOLLOW_12);
            rule__ProjectDescription__Group_17__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_17__2"


    // $ANTLR start "rule__ProjectDescription__Group_17__2__Impl"
    // InternalN4MFParser.g:2174:1: rule__ProjectDescription__Group_17__2__Impl : ( ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_17__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2178:1: ( ( ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* ) ) )
            // InternalN4MFParser.g:2179:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* ) )
            {
            // InternalN4MFParser.g:2179:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* ) )
            // InternalN4MFParser.g:2180:2: ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* )
            {
            // InternalN4MFParser.g:2180:2: ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 ) )
            // InternalN4MFParser.g:2181:3: ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_17_2()); 
            // InternalN4MFParser.g:2182:3: ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )
            // InternalN4MFParser.g:2182:4: rule__ProjectDescription__SourceFragmentAssignment_17_2
            {
            pushFollow(FOLLOW_13);
            rule__ProjectDescription__SourceFragmentAssignment_17_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_17_2()); 

            }

            // InternalN4MFParser.g:2185:2: ( ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )* )
            // InternalN4MFParser.g:2186:3: ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_17_2()); 
            // InternalN4MFParser.g:2187:3: ( rule__ProjectDescription__SourceFragmentAssignment_17_2 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==External||LA14_0==Source||LA14_0==Test) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalN4MFParser.g:2187:4: rule__ProjectDescription__SourceFragmentAssignment_17_2
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__ProjectDescription__SourceFragmentAssignment_17_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_17_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_17__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17__3"
    // InternalN4MFParser.g:2196:1: rule__ProjectDescription__Group_17__3 : rule__ProjectDescription__Group_17__3__Impl ;
    public final void rule__ProjectDescription__Group_17__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2200:1: ( rule__ProjectDescription__Group_17__3__Impl )
            // InternalN4MFParser.g:2201:2: rule__ProjectDescription__Group_17__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__3__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_17__3"


    // $ANTLR start "rule__ProjectDescription__Group_17__3__Impl"
    // InternalN4MFParser.g:2207:1: rule__ProjectDescription__Group_17__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2211:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2212:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2212:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2213:2: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_3()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_3()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_17__3__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_18__0"
    // InternalN4MFParser.g:2223:1: rule__ProjectDescription__Group_18__0 : rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 ;
    public final void rule__ProjectDescription__Group_18__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2227:1: ( rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 )
            // InternalN4MFParser.g:2228:2: rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDescription__Group_18__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_18__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_18__0"


    // $ANTLR start "rule__ProjectDescription__Group_18__0__Impl"
    // InternalN4MFParser.g:2235:1: rule__ProjectDescription__Group_18__0__Impl : ( ModuleFilters ) ;
    public final void rule__ProjectDescription__Group_18__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2239:1: ( ( ModuleFilters ) )
            // InternalN4MFParser.g:2240:1: ( ModuleFilters )
            {
            // InternalN4MFParser.g:2240:1: ( ModuleFilters )
            // InternalN4MFParser.g:2241:2: ModuleFilters
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_18_0()); 
            match(input,ModuleFilters,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_18_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_18__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_18__1"
    // InternalN4MFParser.g:2250:1: rule__ProjectDescription__Group_18__1 : rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 ;
    public final void rule__ProjectDescription__Group_18__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2254:1: ( rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 )
            // InternalN4MFParser.g:2255:2: rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2
            {
            pushFollow(FOLLOW_14);
            rule__ProjectDescription__Group_18__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_18__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_18__1"


    // $ANTLR start "rule__ProjectDescription__Group_18__1__Impl"
    // InternalN4MFParser.g:2262:1: rule__ProjectDescription__Group_18__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2266:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2267:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2267:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2268:2: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_18_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_18_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_18__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_18__2"
    // InternalN4MFParser.g:2277:1: rule__ProjectDescription__Group_18__2 : rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 ;
    public final void rule__ProjectDescription__Group_18__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2281:1: ( rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 )
            // InternalN4MFParser.g:2282:2: rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3
            {
            pushFollow(FOLLOW_12);
            rule__ProjectDescription__Group_18__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_18__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_18__2"


    // $ANTLR start "rule__ProjectDescription__Group_18__2__Impl"
    // InternalN4MFParser.g:2289:1: rule__ProjectDescription__Group_18__2__Impl : ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_18__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2293:1: ( ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* ) ) )
            // InternalN4MFParser.g:2294:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* ) )
            {
            // InternalN4MFParser.g:2294:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* ) )
            // InternalN4MFParser.g:2295:2: ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* )
            {
            // InternalN4MFParser.g:2295:2: ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 ) )
            // InternalN4MFParser.g:2296:3: ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_18_2()); 
            // InternalN4MFParser.g:2297:3: ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )
            // InternalN4MFParser.g:2297:4: rule__ProjectDescription__ModuleFiltersAssignment_18_2
            {
            pushFollow(FOLLOW_15);
            rule__ProjectDescription__ModuleFiltersAssignment_18_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_18_2()); 

            }

            // InternalN4MFParser.g:2300:2: ( ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )* )
            // InternalN4MFParser.g:2301:3: ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_18_2()); 
            // InternalN4MFParser.g:2302:3: ( rule__ProjectDescription__ModuleFiltersAssignment_18_2 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==NoModuleWrap||LA15_0==NoValidate) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalN4MFParser.g:2302:4: rule__ProjectDescription__ModuleFiltersAssignment_18_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__ProjectDescription__ModuleFiltersAssignment_18_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_18_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_18__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_18__3"
    // InternalN4MFParser.g:2311:1: rule__ProjectDescription__Group_18__3 : rule__ProjectDescription__Group_18__3__Impl ;
    public final void rule__ProjectDescription__Group_18__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2315:1: ( rule__ProjectDescription__Group_18__3__Impl )
            // InternalN4MFParser.g:2316:2: rule__ProjectDescription__Group_18__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_18__3__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_18__3"


    // $ANTLR start "rule__ProjectDescription__Group_18__3__Impl"
    // InternalN4MFParser.g:2322:1: rule__ProjectDescription__Group_18__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2326:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2327:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2327:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2328:2: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_18_3()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_18_3()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_18__3__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_20__0"
    // InternalN4MFParser.g:2338:1: rule__ProjectDescription__Group_20__0 : rule__ProjectDescription__Group_20__0__Impl rule__ProjectDescription__Group_20__1 ;
    public final void rule__ProjectDescription__Group_20__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2342:1: ( rule__ProjectDescription__Group_20__0__Impl rule__ProjectDescription__Group_20__1 )
            // InternalN4MFParser.g:2343:2: rule__ProjectDescription__Group_20__0__Impl rule__ProjectDescription__Group_20__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_20__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_20__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_20__0"


    // $ANTLR start "rule__ProjectDescription__Group_20__0__Impl"
    // InternalN4MFParser.g:2350:1: rule__ProjectDescription__Group_20__0__Impl : ( ModuleLoader ) ;
    public final void rule__ProjectDescription__Group_20__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2354:1: ( ( ModuleLoader ) )
            // InternalN4MFParser.g:2355:1: ( ModuleLoader )
            {
            // InternalN4MFParser.g:2355:1: ( ModuleLoader )
            // InternalN4MFParser.g:2356:2: ModuleLoader
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_20_0()); 
            match(input,ModuleLoader,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_20_0()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_20__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_20__1"
    // InternalN4MFParser.g:2365:1: rule__ProjectDescription__Group_20__1 : rule__ProjectDescription__Group_20__1__Impl rule__ProjectDescription__Group_20__2 ;
    public final void rule__ProjectDescription__Group_20__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2369:1: ( rule__ProjectDescription__Group_20__1__Impl rule__ProjectDescription__Group_20__2 )
            // InternalN4MFParser.g:2370:2: rule__ProjectDescription__Group_20__1__Impl rule__ProjectDescription__Group_20__2
            {
            pushFollow(FOLLOW_16);
            rule__ProjectDescription__Group_20__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_20__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_20__1"


    // $ANTLR start "rule__ProjectDescription__Group_20__1__Impl"
    // InternalN4MFParser.g:2377:1: rule__ProjectDescription__Group_20__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_20__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2381:1: ( ( Colon ) )
            // InternalN4MFParser.g:2382:1: ( Colon )
            {
            // InternalN4MFParser.g:2382:1: ( Colon )
            // InternalN4MFParser.g:2383:2: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_20_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_20_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_20__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_20__2"
    // InternalN4MFParser.g:2392:1: rule__ProjectDescription__Group_20__2 : rule__ProjectDescription__Group_20__2__Impl ;
    public final void rule__ProjectDescription__Group_20__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2396:1: ( rule__ProjectDescription__Group_20__2__Impl )
            // InternalN4MFParser.g:2397:2: rule__ProjectDescription__Group_20__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_20__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__Group_20__2"


    // $ANTLR start "rule__ProjectDescription__Group_20__2__Impl"
    // InternalN4MFParser.g:2403:1: rule__ProjectDescription__Group_20__2__Impl : ( ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 ) ) ;
    public final void rule__ProjectDescription__Group_20__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2407:1: ( ( ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 ) ) )
            // InternalN4MFParser.g:2408:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 ) )
            {
            // InternalN4MFParser.g:2408:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 ) )
            // InternalN4MFParser.g:2409:2: ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderAssignment_20_2()); 
            // InternalN4MFParser.g:2410:2: ( rule__ProjectDescription__ModuleLoaderAssignment_20_2 )
            // InternalN4MFParser.g:2410:3: rule__ProjectDescription__ModuleLoaderAssignment_20_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ModuleLoaderAssignment_20_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderAssignment_20_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_20__2__Impl"


    // $ANTLR start "rule__ExecModule__Group__0"
    // InternalN4MFParser.g:2419:1: rule__ExecModule__Group__0 : rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 ;
    public final void rule__ExecModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2423:1: ( rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 )
            // InternalN4MFParser.g:2424:2: rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__ExecModule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExecModule__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ExecModule__Group__0"


    // $ANTLR start "rule__ExecModule__Group__0__Impl"
    // InternalN4MFParser.g:2431:1: rule__ExecModule__Group__0__Impl : ( () ) ;
    public final void rule__ExecModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2435:1: ( ( () ) )
            // InternalN4MFParser.g:2436:1: ( () )
            {
            // InternalN4MFParser.g:2436:1: ( () )
            // InternalN4MFParser.g:2437:2: ()
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAction_0()); 
            // InternalN4MFParser.g:2438:2: ()
            // InternalN4MFParser.g:2438:3: 
            {
            }

             after(grammarAccess.getExecModuleAccess().getExecModuleAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExecModule__Group__0__Impl"


    // $ANTLR start "rule__ExecModule__Group__1"
    // InternalN4MFParser.g:2446:1: rule__ExecModule__Group__1 : rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 ;
    public final void rule__ExecModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2450:1: ( rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 )
            // InternalN4MFParser.g:2451:2: rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__ExecModule__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExecModule__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ExecModule__Group__1"


    // $ANTLR start "rule__ExecModule__Group__1__Impl"
    // InternalN4MFParser.g:2458:1: rule__ExecModule__Group__1__Impl : ( ExecModule ) ;
    public final void rule__ExecModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2462:1: ( ( ExecModule ) )
            // InternalN4MFParser.g:2463:1: ( ExecModule )
            {
            // InternalN4MFParser.g:2463:1: ( ExecModule )
            // InternalN4MFParser.g:2464:2: ExecModule
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleKeyword_1()); 
            match(input,ExecModule,FOLLOW_2); 
             after(grammarAccess.getExecModuleAccess().getExecModuleKeyword_1()); 

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
    // $ANTLR end "rule__ExecModule__Group__1__Impl"


    // $ANTLR start "rule__ExecModule__Group__2"
    // InternalN4MFParser.g:2473:1: rule__ExecModule__Group__2 : rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 ;
    public final void rule__ExecModule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2477:1: ( rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 )
            // InternalN4MFParser.g:2478:2: rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__ExecModule__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExecModule__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ExecModule__Group__2"


    // $ANTLR start "rule__ExecModule__Group__2__Impl"
    // InternalN4MFParser.g:2485:1: rule__ExecModule__Group__2__Impl : ( Colon ) ;
    public final void rule__ExecModule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2489:1: ( ( Colon ) )
            // InternalN4MFParser.g:2490:1: ( Colon )
            {
            // InternalN4MFParser.g:2490:1: ( Colon )
            // InternalN4MFParser.g:2491:2: Colon
            {
             before(grammarAccess.getExecModuleAccess().getColonKeyword_2()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getExecModuleAccess().getColonKeyword_2()); 

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
    // $ANTLR end "rule__ExecModule__Group__2__Impl"


    // $ANTLR start "rule__ExecModule__Group__3"
    // InternalN4MFParser.g:2500:1: rule__ExecModule__Group__3 : rule__ExecModule__Group__3__Impl ;
    public final void rule__ExecModule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2504:1: ( rule__ExecModule__Group__3__Impl )
            // InternalN4MFParser.g:2505:2: rule__ExecModule__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ExecModule__Group__3__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ExecModule__Group__3"


    // $ANTLR start "rule__ExecModule__Group__3__Impl"
    // InternalN4MFParser.g:2511:1: rule__ExecModule__Group__3__Impl : ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) ;
    public final void rule__ExecModule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2515:1: ( ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) )
            // InternalN4MFParser.g:2516:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            {
            // InternalN4MFParser.g:2516:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            // InternalN4MFParser.g:2517:2: ( rule__ExecModule__ExecModuleAssignment_3 )
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAssignment_3()); 
            // InternalN4MFParser.g:2518:2: ( rule__ExecModule__ExecModuleAssignment_3 )
            // InternalN4MFParser.g:2518:3: rule__ExecModule__ExecModuleAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ExecModule__ExecModuleAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getExecModuleAccess().getExecModuleAssignment_3()); 

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
    // $ANTLR end "rule__ExecModule__Group__3__Impl"


    // $ANTLR start "rule__TestedProjects__Group__0"
    // InternalN4MFParser.g:2527:1: rule__TestedProjects__Group__0 : rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 ;
    public final void rule__TestedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2531:1: ( rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 )
            // InternalN4MFParser.g:2532:2: rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__TestedProjects__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group__0"


    // $ANTLR start "rule__TestedProjects__Group__0__Impl"
    // InternalN4MFParser.g:2539:1: rule__TestedProjects__Group__0__Impl : ( () ) ;
    public final void rule__TestedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2543:1: ( ( () ) )
            // InternalN4MFParser.g:2544:1: ( () )
            {
            // InternalN4MFParser.g:2544:1: ( () )
            // InternalN4MFParser.g:2545:2: ()
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0()); 
            // InternalN4MFParser.g:2546:2: ()
            // InternalN4MFParser.g:2546:3: 
            {
            }

             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TestedProjects__Group__0__Impl"


    // $ANTLR start "rule__TestedProjects__Group__1"
    // InternalN4MFParser.g:2554:1: rule__TestedProjects__Group__1 : rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 ;
    public final void rule__TestedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2558:1: ( rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 )
            // InternalN4MFParser.g:2559:2: rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__TestedProjects__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group__1"


    // $ANTLR start "rule__TestedProjects__Group__1__Impl"
    // InternalN4MFParser.g:2566:1: rule__TestedProjects__Group__1__Impl : ( TestedProjects ) ;
    public final void rule__TestedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2570:1: ( ( TestedProjects ) )
            // InternalN4MFParser.g:2571:1: ( TestedProjects )
            {
            // InternalN4MFParser.g:2571:1: ( TestedProjects )
            // InternalN4MFParser.g:2572:2: TestedProjects
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsKeyword_1()); 
            match(input,TestedProjects,FOLLOW_2); 
             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsKeyword_1()); 

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
    // $ANTLR end "rule__TestedProjects__Group__1__Impl"


    // $ANTLR start "rule__TestedProjects__Group__2"
    // InternalN4MFParser.g:2581:1: rule__TestedProjects__Group__2 : rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 ;
    public final void rule__TestedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2585:1: ( rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 )
            // InternalN4MFParser.g:2586:2: rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__TestedProjects__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group__2"


    // $ANTLR start "rule__TestedProjects__Group__2__Impl"
    // InternalN4MFParser.g:2593:1: rule__TestedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__TestedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2597:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2598:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2598:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2599:2: LeftCurlyBracket
            {
             before(grammarAccess.getTestedProjectsAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getTestedProjectsAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__TestedProjects__Group__2__Impl"


    // $ANTLR start "rule__TestedProjects__Group__3"
    // InternalN4MFParser.g:2608:1: rule__TestedProjects__Group__3 : rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 ;
    public final void rule__TestedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2612:1: ( rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 )
            // InternalN4MFParser.g:2613:2: rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__TestedProjects__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group__3"


    // $ANTLR start "rule__TestedProjects__Group__3__Impl"
    // InternalN4MFParser.g:2620:1: rule__TestedProjects__Group__3__Impl : ( ( rule__TestedProjects__Group_3__0 )? ) ;
    public final void rule__TestedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2624:1: ( ( ( rule__TestedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:2625:1: ( ( rule__TestedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:2625:1: ( ( rule__TestedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:2626:2: ( rule__TestedProjects__Group_3__0 )?
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:2627:2: ( rule__TestedProjects__Group_3__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ProjectDependencies||LA16_0==ProjectVersion||LA16_0==ModuleFilters||(LA16_0>=ProjectType && LA16_0<=Application)||LA16_0==VendorName||(LA16_0>=Libraries && LA16_0<=VendorId)||LA16_0==Sources||LA16_0==Content||LA16_0==Output||(LA16_0>=Test && LA16_0<=API)||LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalN4MFParser.g:2627:3: rule__TestedProjects__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__TestedProjects__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTestedProjectsAccess().getGroup_3()); 

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
    // $ANTLR end "rule__TestedProjects__Group__3__Impl"


    // $ANTLR start "rule__TestedProjects__Group__4"
    // InternalN4MFParser.g:2635:1: rule__TestedProjects__Group__4 : rule__TestedProjects__Group__4__Impl ;
    public final void rule__TestedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2639:1: ( rule__TestedProjects__Group__4__Impl )
            // InternalN4MFParser.g:2640:2: rule__TestedProjects__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group__4"


    // $ANTLR start "rule__TestedProjects__Group__4__Impl"
    // InternalN4MFParser.g:2646:1: rule__TestedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__TestedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2650:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2651:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2651:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2652:2: RightCurlyBracket
            {
             before(grammarAccess.getTestedProjectsAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getTestedProjectsAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__TestedProjects__Group__4__Impl"


    // $ANTLR start "rule__TestedProjects__Group_3__0"
    // InternalN4MFParser.g:2662:1: rule__TestedProjects__Group_3__0 : rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 ;
    public final void rule__TestedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2666:1: ( rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 )
            // InternalN4MFParser.g:2667:2: rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__TestedProjects__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group_3__0"


    // $ANTLR start "rule__TestedProjects__Group_3__0__Impl"
    // InternalN4MFParser.g:2674:1: rule__TestedProjects__Group_3__0__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) ;
    public final void rule__TestedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2678:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:2679:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:2679:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:2680:2: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:2681:2: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:2681:3: rule__TestedProjects__TestedProjectsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__TestedProjectsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_0()); 

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
    // $ANTLR end "rule__TestedProjects__Group_3__0__Impl"


    // $ANTLR start "rule__TestedProjects__Group_3__1"
    // InternalN4MFParser.g:2689:1: rule__TestedProjects__Group_3__1 : rule__TestedProjects__Group_3__1__Impl ;
    public final void rule__TestedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2693:1: ( rule__TestedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:2694:2: rule__TestedProjects__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group_3__1"


    // $ANTLR start "rule__TestedProjects__Group_3__1__Impl"
    // InternalN4MFParser.g:2700:1: rule__TestedProjects__Group_3__1__Impl : ( ( rule__TestedProjects__Group_3_1__0 )* ) ;
    public final void rule__TestedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2704:1: ( ( ( rule__TestedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:2705:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:2705:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:2706:2: ( rule__TestedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:2707:2: ( rule__TestedProjects__Group_3_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Comma) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalN4MFParser.g:2707:3: rule__TestedProjects__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__TestedProjects__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getTestedProjectsAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__TestedProjects__Group_3__1__Impl"


    // $ANTLR start "rule__TestedProjects__Group_3_1__0"
    // InternalN4MFParser.g:2716:1: rule__TestedProjects__Group_3_1__0 : rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 ;
    public final void rule__TestedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2720:1: ( rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:2721:2: rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__TestedProjects__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group_3_1__0"


    // $ANTLR start "rule__TestedProjects__Group_3_1__0__Impl"
    // InternalN4MFParser.g:2728:1: rule__TestedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__TestedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2732:1: ( ( Comma ) )
            // InternalN4MFParser.g:2733:1: ( Comma )
            {
            // InternalN4MFParser.g:2733:1: ( Comma )
            // InternalN4MFParser.g:2734:2: Comma
            {
             before(grammarAccess.getTestedProjectsAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getTestedProjectsAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__TestedProjects__Group_3_1__0__Impl"


    // $ANTLR start "rule__TestedProjects__Group_3_1__1"
    // InternalN4MFParser.g:2743:1: rule__TestedProjects__Group_3_1__1 : rule__TestedProjects__Group_3_1__1__Impl ;
    public final void rule__TestedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2747:1: ( rule__TestedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:2748:2: rule__TestedProjects__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__TestedProjects__Group_3_1__1"


    // $ANTLR start "rule__TestedProjects__Group_3_1__1__Impl"
    // InternalN4MFParser.g:2754:1: rule__TestedProjects__Group_3_1__1__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__TestedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2758:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:2759:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:2759:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:2760:2: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:2761:2: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:2761:3: rule__TestedProjects__TestedProjectsAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__TestedProjects__TestedProjectsAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_1_1()); 

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
    // $ANTLR end "rule__TestedProjects__Group_3_1__1__Impl"


    // $ANTLR start "rule__InitModules__Group__0"
    // InternalN4MFParser.g:2770:1: rule__InitModules__Group__0 : rule__InitModules__Group__0__Impl rule__InitModules__Group__1 ;
    public final void rule__InitModules__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2774:1: ( rule__InitModules__Group__0__Impl rule__InitModules__Group__1 )
            // InternalN4MFParser.g:2775:2: rule__InitModules__Group__0__Impl rule__InitModules__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__InitModules__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group__0"


    // $ANTLR start "rule__InitModules__Group__0__Impl"
    // InternalN4MFParser.g:2782:1: rule__InitModules__Group__0__Impl : ( () ) ;
    public final void rule__InitModules__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2786:1: ( ( () ) )
            // InternalN4MFParser.g:2787:1: ( () )
            {
            // InternalN4MFParser.g:2787:1: ( () )
            // InternalN4MFParser.g:2788:2: ()
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAction_0()); 
            // InternalN4MFParser.g:2789:2: ()
            // InternalN4MFParser.g:2789:3: 
            {
            }

             after(grammarAccess.getInitModulesAccess().getInitModulesAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InitModules__Group__0__Impl"


    // $ANTLR start "rule__InitModules__Group__1"
    // InternalN4MFParser.g:2797:1: rule__InitModules__Group__1 : rule__InitModules__Group__1__Impl rule__InitModules__Group__2 ;
    public final void rule__InitModules__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2801:1: ( rule__InitModules__Group__1__Impl rule__InitModules__Group__2 )
            // InternalN4MFParser.g:2802:2: rule__InitModules__Group__1__Impl rule__InitModules__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__InitModules__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group__1"


    // $ANTLR start "rule__InitModules__Group__1__Impl"
    // InternalN4MFParser.g:2809:1: rule__InitModules__Group__1__Impl : ( InitModules ) ;
    public final void rule__InitModules__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2813:1: ( ( InitModules ) )
            // InternalN4MFParser.g:2814:1: ( InitModules )
            {
            // InternalN4MFParser.g:2814:1: ( InitModules )
            // InternalN4MFParser.g:2815:2: InitModules
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesKeyword_1()); 
            match(input,InitModules,FOLLOW_2); 
             after(grammarAccess.getInitModulesAccess().getInitModulesKeyword_1()); 

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
    // $ANTLR end "rule__InitModules__Group__1__Impl"


    // $ANTLR start "rule__InitModules__Group__2"
    // InternalN4MFParser.g:2824:1: rule__InitModules__Group__2 : rule__InitModules__Group__2__Impl rule__InitModules__Group__3 ;
    public final void rule__InitModules__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2828:1: ( rule__InitModules__Group__2__Impl rule__InitModules__Group__3 )
            // InternalN4MFParser.g:2829:2: rule__InitModules__Group__2__Impl rule__InitModules__Group__3
            {
            pushFollow(FOLLOW_22);
            rule__InitModules__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group__2"


    // $ANTLR start "rule__InitModules__Group__2__Impl"
    // InternalN4MFParser.g:2836:1: rule__InitModules__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__InitModules__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2840:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2841:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2841:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2842:2: LeftCurlyBracket
            {
             before(grammarAccess.getInitModulesAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getInitModulesAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__InitModules__Group__2__Impl"


    // $ANTLR start "rule__InitModules__Group__3"
    // InternalN4MFParser.g:2851:1: rule__InitModules__Group__3 : rule__InitModules__Group__3__Impl rule__InitModules__Group__4 ;
    public final void rule__InitModules__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2855:1: ( rule__InitModules__Group__3__Impl rule__InitModules__Group__4 )
            // InternalN4MFParser.g:2856:2: rule__InitModules__Group__3__Impl rule__InitModules__Group__4
            {
            pushFollow(FOLLOW_22);
            rule__InitModules__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group__3"


    // $ANTLR start "rule__InitModules__Group__3__Impl"
    // InternalN4MFParser.g:2863:1: rule__InitModules__Group__3__Impl : ( ( rule__InitModules__Group_3__0 )? ) ;
    public final void rule__InitModules__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2867:1: ( ( ( rule__InitModules__Group_3__0 )? ) )
            // InternalN4MFParser.g:2868:1: ( ( rule__InitModules__Group_3__0 )? )
            {
            // InternalN4MFParser.g:2868:1: ( ( rule__InitModules__Group_3__0 )? )
            // InternalN4MFParser.g:2869:2: ( rule__InitModules__Group_3__0 )?
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3()); 
            // InternalN4MFParser.g:2870:2: ( rule__InitModules__Group_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_STRING) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalN4MFParser.g:2870:3: rule__InitModules__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InitModules__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInitModulesAccess().getGroup_3()); 

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
    // $ANTLR end "rule__InitModules__Group__3__Impl"


    // $ANTLR start "rule__InitModules__Group__4"
    // InternalN4MFParser.g:2878:1: rule__InitModules__Group__4 : rule__InitModules__Group__4__Impl ;
    public final void rule__InitModules__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2882:1: ( rule__InitModules__Group__4__Impl )
            // InternalN4MFParser.g:2883:2: rule__InitModules__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group__4"


    // $ANTLR start "rule__InitModules__Group__4__Impl"
    // InternalN4MFParser.g:2889:1: rule__InitModules__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__InitModules__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2893:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2894:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2894:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2895:2: RightCurlyBracket
            {
             before(grammarAccess.getInitModulesAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getInitModulesAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__InitModules__Group__4__Impl"


    // $ANTLR start "rule__InitModules__Group_3__0"
    // InternalN4MFParser.g:2905:1: rule__InitModules__Group_3__0 : rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 ;
    public final void rule__InitModules__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2909:1: ( rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 )
            // InternalN4MFParser.g:2910:2: rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__InitModules__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group_3__0"


    // $ANTLR start "rule__InitModules__Group_3__0__Impl"
    // InternalN4MFParser.g:2917:1: rule__InitModules__Group_3__0__Impl : ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) ;
    public final void rule__InitModules__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2921:1: ( ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:2922:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:2922:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            // InternalN4MFParser.g:2923:2: ( rule__InitModules__InitModulesAssignment_3_0 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_0()); 
            // InternalN4MFParser.g:2924:2: ( rule__InitModules__InitModulesAssignment_3_0 )
            // InternalN4MFParser.g:2924:3: rule__InitModules__InitModulesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__InitModulesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_0()); 

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
    // $ANTLR end "rule__InitModules__Group_3__0__Impl"


    // $ANTLR start "rule__InitModules__Group_3__1"
    // InternalN4MFParser.g:2932:1: rule__InitModules__Group_3__1 : rule__InitModules__Group_3__1__Impl ;
    public final void rule__InitModules__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2936:1: ( rule__InitModules__Group_3__1__Impl )
            // InternalN4MFParser.g:2937:2: rule__InitModules__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group_3__1"


    // $ANTLR start "rule__InitModules__Group_3__1__Impl"
    // InternalN4MFParser.g:2943:1: rule__InitModules__Group_3__1__Impl : ( ( rule__InitModules__Group_3_1__0 )* ) ;
    public final void rule__InitModules__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2947:1: ( ( ( rule__InitModules__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:2948:1: ( ( rule__InitModules__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:2948:1: ( ( rule__InitModules__Group_3_1__0 )* )
            // InternalN4MFParser.g:2949:2: ( rule__InitModules__Group_3_1__0 )*
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:2950:2: ( rule__InitModules__Group_3_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Comma) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalN4MFParser.g:2950:3: rule__InitModules__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__InitModules__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getInitModulesAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__InitModules__Group_3__1__Impl"


    // $ANTLR start "rule__InitModules__Group_3_1__0"
    // InternalN4MFParser.g:2959:1: rule__InitModules__Group_3_1__0 : rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 ;
    public final void rule__InitModules__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2963:1: ( rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 )
            // InternalN4MFParser.g:2964:2: rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1
            {
            pushFollow(FOLLOW_7);
            rule__InitModules__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InitModules__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group_3_1__0"


    // $ANTLR start "rule__InitModules__Group_3_1__0__Impl"
    // InternalN4MFParser.g:2971:1: rule__InitModules__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__InitModules__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2975:1: ( ( Comma ) )
            // InternalN4MFParser.g:2976:1: ( Comma )
            {
            // InternalN4MFParser.g:2976:1: ( Comma )
            // InternalN4MFParser.g:2977:2: Comma
            {
             before(grammarAccess.getInitModulesAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getInitModulesAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__InitModules__Group_3_1__0__Impl"


    // $ANTLR start "rule__InitModules__Group_3_1__1"
    // InternalN4MFParser.g:2986:1: rule__InitModules__Group_3_1__1 : rule__InitModules__Group_3_1__1__Impl ;
    public final void rule__InitModules__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:2990:1: ( rule__InitModules__Group_3_1__1__Impl )
            // InternalN4MFParser.g:2991:2: rule__InitModules__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__InitModules__Group_3_1__1"


    // $ANTLR start "rule__InitModules__Group_3_1__1__Impl"
    // InternalN4MFParser.g:2997:1: rule__InitModules__Group_3_1__1__Impl : ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) ;
    public final void rule__InitModules__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3001:1: ( ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3002:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3002:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3003:2: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3004:2: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            // InternalN4MFParser.g:3004:3: rule__InitModules__InitModulesAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__InitModules__InitModulesAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_1_1()); 

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
    // $ANTLR end "rule__InitModules__Group_3_1__1__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group__0"
    // InternalN4MFParser.g:3013:1: rule__ImplementedProjects__Group__0 : rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 ;
    public final void rule__ImplementedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3017:1: ( rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 )
            // InternalN4MFParser.g:3018:2: rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__ImplementedProjects__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group__0"


    // $ANTLR start "rule__ImplementedProjects__Group__0__Impl"
    // InternalN4MFParser.g:3025:1: rule__ImplementedProjects__Group__0__Impl : ( () ) ;
    public final void rule__ImplementedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3029:1: ( ( () ) )
            // InternalN4MFParser.g:3030:1: ( () )
            {
            // InternalN4MFParser.g:3030:1: ( () )
            // InternalN4MFParser.g:3031:2: ()
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0()); 
            // InternalN4MFParser.g:3032:2: ()
            // InternalN4MFParser.g:3032:3: 
            {
            }

             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImplementedProjects__Group__0__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group__1"
    // InternalN4MFParser.g:3040:1: rule__ImplementedProjects__Group__1 : rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 ;
    public final void rule__ImplementedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3044:1: ( rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 )
            // InternalN4MFParser.g:3045:2: rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__ImplementedProjects__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group__1"


    // $ANTLR start "rule__ImplementedProjects__Group__1__Impl"
    // InternalN4MFParser.g:3052:1: rule__ImplementedProjects__Group__1__Impl : ( ImplementedProjects ) ;
    public final void rule__ImplementedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3056:1: ( ( ImplementedProjects ) )
            // InternalN4MFParser.g:3057:1: ( ImplementedProjects )
            {
            // InternalN4MFParser.g:3057:1: ( ImplementedProjects )
            // InternalN4MFParser.g:3058:2: ImplementedProjects
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsKeyword_1()); 
            match(input,ImplementedProjects,FOLLOW_2); 
             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsKeyword_1()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group__1__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group__2"
    // InternalN4MFParser.g:3067:1: rule__ImplementedProjects__Group__2 : rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 ;
    public final void rule__ImplementedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3071:1: ( rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 )
            // InternalN4MFParser.g:3072:2: rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__ImplementedProjects__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group__2"


    // $ANTLR start "rule__ImplementedProjects__Group__2__Impl"
    // InternalN4MFParser.g:3079:1: rule__ImplementedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3083:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3084:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3084:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3085:2: LeftCurlyBracket
            {
             before(grammarAccess.getImplementedProjectsAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getImplementedProjectsAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group__2__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group__3"
    // InternalN4MFParser.g:3094:1: rule__ImplementedProjects__Group__3 : rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 ;
    public final void rule__ImplementedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3098:1: ( rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 )
            // InternalN4MFParser.g:3099:2: rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__ImplementedProjects__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group__3"


    // $ANTLR start "rule__ImplementedProjects__Group__3__Impl"
    // InternalN4MFParser.g:3106:1: rule__ImplementedProjects__Group__3__Impl : ( ( rule__ImplementedProjects__Group_3__0 )? ) ;
    public final void rule__ImplementedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3110:1: ( ( ( rule__ImplementedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:3111:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3111:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:3112:2: ( rule__ImplementedProjects__Group_3__0 )?
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:3113:2: ( rule__ImplementedProjects__Group_3__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ProjectDependencies||LA20_0==ProjectVersion||LA20_0==ModuleFilters||(LA20_0>=ProjectType && LA20_0<=Application)||LA20_0==VendorName||(LA20_0>=Libraries && LA20_0<=VendorId)||LA20_0==Sources||LA20_0==Content||LA20_0==Output||(LA20_0>=Test && LA20_0<=API)||LA20_0==RULE_ID) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalN4MFParser.g:3113:3: rule__ImplementedProjects__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImplementedProjects__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImplementedProjectsAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group__3__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group__4"
    // InternalN4MFParser.g:3121:1: rule__ImplementedProjects__Group__4 : rule__ImplementedProjects__Group__4__Impl ;
    public final void rule__ImplementedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3125:1: ( rule__ImplementedProjects__Group__4__Impl )
            // InternalN4MFParser.g:3126:2: rule__ImplementedProjects__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group__4"


    // $ANTLR start "rule__ImplementedProjects__Group__4__Impl"
    // InternalN4MFParser.g:3132:1: rule__ImplementedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3136:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3137:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3137:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3138:2: RightCurlyBracket
            {
             before(grammarAccess.getImplementedProjectsAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getImplementedProjectsAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group__4__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group_3__0"
    // InternalN4MFParser.g:3148:1: rule__ImplementedProjects__Group_3__0 : rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 ;
    public final void rule__ImplementedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3152:1: ( rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 )
            // InternalN4MFParser.g:3153:2: rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__ImplementedProjects__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group_3__0"


    // $ANTLR start "rule__ImplementedProjects__Group_3__0__Impl"
    // InternalN4MFParser.g:3160:1: rule__ImplementedProjects__Group_3__0__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) ;
    public final void rule__ImplementedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3164:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3165:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3165:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:3166:2: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:3167:2: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:3167:3: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__ImplementedProjectsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_0()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group_3__0__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group_3__1"
    // InternalN4MFParser.g:3175:1: rule__ImplementedProjects__Group_3__1 : rule__ImplementedProjects__Group_3__1__Impl ;
    public final void rule__ImplementedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3179:1: ( rule__ImplementedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:3180:2: rule__ImplementedProjects__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group_3__1"


    // $ANTLR start "rule__ImplementedProjects__Group_3__1__Impl"
    // InternalN4MFParser.g:3186:1: rule__ImplementedProjects__Group_3__1__Impl : ( ( rule__ImplementedProjects__Group_3_1__0 )* ) ;
    public final void rule__ImplementedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3190:1: ( ( ( rule__ImplementedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3191:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3191:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:3192:2: ( rule__ImplementedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3193:2: ( rule__ImplementedProjects__Group_3_1__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==Comma) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalN4MFParser.g:3193:3: rule__ImplementedProjects__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ImplementedProjects__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getImplementedProjectsAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group_3__1__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group_3_1__0"
    // InternalN4MFParser.g:3202:1: rule__ImplementedProjects__Group_3_1__0 : rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 ;
    public final void rule__ImplementedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3206:1: ( rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:3207:2: rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ImplementedProjects__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group_3_1__0"


    // $ANTLR start "rule__ImplementedProjects__Group_3_1__0__Impl"
    // InternalN4MFParser.g:3214:1: rule__ImplementedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ImplementedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3218:1: ( ( Comma ) )
            // InternalN4MFParser.g:3219:1: ( Comma )
            {
            // InternalN4MFParser.g:3219:1: ( Comma )
            // InternalN4MFParser.g:3220:2: Comma
            {
             before(grammarAccess.getImplementedProjectsAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getImplementedProjectsAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group_3_1__0__Impl"


    // $ANTLR start "rule__ImplementedProjects__Group_3_1__1"
    // InternalN4MFParser.g:3229:1: rule__ImplementedProjects__Group_3_1__1 : rule__ImplementedProjects__Group_3_1__1__Impl ;
    public final void rule__ImplementedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3233:1: ( rule__ImplementedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3234:2: rule__ImplementedProjects__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ImplementedProjects__Group_3_1__1"


    // $ANTLR start "rule__ImplementedProjects__Group_3_1__1__Impl"
    // InternalN4MFParser.g:3240:1: rule__ImplementedProjects__Group_3_1__1__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__ImplementedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3244:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3245:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3245:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3246:2: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:3247:2: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:3247:3: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_1_1()); 

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
    // $ANTLR end "rule__ImplementedProjects__Group_3_1__1__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group__0"
    // InternalN4MFParser.g:3256:1: rule__ProjectDependencies__Group__0 : rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 ;
    public final void rule__ProjectDependencies__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3260:1: ( rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 )
            // InternalN4MFParser.g:3261:2: rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1
            {
            pushFollow(FOLLOW_24);
            rule__ProjectDependencies__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group__0"


    // $ANTLR start "rule__ProjectDependencies__Group__0__Impl"
    // InternalN4MFParser.g:3268:1: rule__ProjectDependencies__Group__0__Impl : ( () ) ;
    public final void rule__ProjectDependencies__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3272:1: ( ( () ) )
            // InternalN4MFParser.g:3273:1: ( () )
            {
            // InternalN4MFParser.g:3273:1: ( () )
            // InternalN4MFParser.g:3274:2: ()
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0()); 
            // InternalN4MFParser.g:3275:2: ()
            // InternalN4MFParser.g:3275:3: 
            {
            }

             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDependencies__Group__0__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group__1"
    // InternalN4MFParser.g:3283:1: rule__ProjectDependencies__Group__1 : rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 ;
    public final void rule__ProjectDependencies__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3287:1: ( rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 )
            // InternalN4MFParser.g:3288:2: rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDependencies__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group__1"


    // $ANTLR start "rule__ProjectDependencies__Group__1__Impl"
    // InternalN4MFParser.g:3295:1: rule__ProjectDependencies__Group__1__Impl : ( ProjectDependencies ) ;
    public final void rule__ProjectDependencies__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3299:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:3300:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:3300:1: ( ProjectDependencies )
            // InternalN4MFParser.g:3301:2: ProjectDependencies
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesKeyword_1()); 
            match(input,ProjectDependencies,FOLLOW_2); 
             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesKeyword_1()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group__1__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group__2"
    // InternalN4MFParser.g:3310:1: rule__ProjectDependencies__Group__2 : rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 ;
    public final void rule__ProjectDependencies__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3314:1: ( rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 )
            // InternalN4MFParser.g:3315:2: rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__ProjectDependencies__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group__2"


    // $ANTLR start "rule__ProjectDependencies__Group__2__Impl"
    // InternalN4MFParser.g:3322:1: rule__ProjectDependencies__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3326:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3327:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3327:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3328:2: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDependenciesAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDependenciesAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group__2__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group__3"
    // InternalN4MFParser.g:3337:1: rule__ProjectDependencies__Group__3 : rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 ;
    public final void rule__ProjectDependencies__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3341:1: ( rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 )
            // InternalN4MFParser.g:3342:2: rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__ProjectDependencies__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group__3"


    // $ANTLR start "rule__ProjectDependencies__Group__3__Impl"
    // InternalN4MFParser.g:3349:1: rule__ProjectDependencies__Group__3__Impl : ( ( rule__ProjectDependencies__Group_3__0 )? ) ;
    public final void rule__ProjectDependencies__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3353:1: ( ( ( rule__ProjectDependencies__Group_3__0 )? ) )
            // InternalN4MFParser.g:3354:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3354:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            // InternalN4MFParser.g:3355:2: ( rule__ProjectDependencies__Group_3__0 )?
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3()); 
            // InternalN4MFParser.g:3356:2: ( rule__ProjectDependencies__Group_3__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==ProjectDependencies||LA22_0==ProjectVersion||LA22_0==ModuleFilters||(LA22_0>=ProjectType && LA22_0<=Application)||LA22_0==VendorName||(LA22_0>=Libraries && LA22_0<=VendorId)||LA22_0==Sources||LA22_0==Content||LA22_0==Output||(LA22_0>=Test && LA22_0<=API)||LA22_0==RULE_ID) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalN4MFParser.g:3356:3: rule__ProjectDependencies__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDependencies__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProjectDependenciesAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group__3__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group__4"
    // InternalN4MFParser.g:3364:1: rule__ProjectDependencies__Group__4 : rule__ProjectDependencies__Group__4__Impl ;
    public final void rule__ProjectDependencies__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3368:1: ( rule__ProjectDependencies__Group__4__Impl )
            // InternalN4MFParser.g:3369:2: rule__ProjectDependencies__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group__4"


    // $ANTLR start "rule__ProjectDependencies__Group__4__Impl"
    // InternalN4MFParser.g:3375:1: rule__ProjectDependencies__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3379:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3380:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3380:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3381:2: RightCurlyBracket
            {
             before(grammarAccess.getProjectDependenciesAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDependenciesAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group__4__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group_3__0"
    // InternalN4MFParser.g:3391:1: rule__ProjectDependencies__Group_3__0 : rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 ;
    public final void rule__ProjectDependencies__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3395:1: ( rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 )
            // InternalN4MFParser.g:3396:2: rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__ProjectDependencies__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group_3__0"


    // $ANTLR start "rule__ProjectDependencies__Group_3__0__Impl"
    // InternalN4MFParser.g:3403:1: rule__ProjectDependencies__Group_3__0__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) ;
    public final void rule__ProjectDependencies__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3407:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3408:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3408:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            // InternalN4MFParser.g:3409:2: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_0()); 
            // InternalN4MFParser.g:3410:2: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            // InternalN4MFParser.g:3410:3: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__ProjectDependenciesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_0()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group_3__0__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group_3__1"
    // InternalN4MFParser.g:3418:1: rule__ProjectDependencies__Group_3__1 : rule__ProjectDependencies__Group_3__1__Impl ;
    public final void rule__ProjectDependencies__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3422:1: ( rule__ProjectDependencies__Group_3__1__Impl )
            // InternalN4MFParser.g:3423:2: rule__ProjectDependencies__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group_3__1"


    // $ANTLR start "rule__ProjectDependencies__Group_3__1__Impl"
    // InternalN4MFParser.g:3429:1: rule__ProjectDependencies__Group_3__1__Impl : ( ( rule__ProjectDependencies__Group_3_1__0 )* ) ;
    public final void rule__ProjectDependencies__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3433:1: ( ( ( rule__ProjectDependencies__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3434:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3434:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            // InternalN4MFParser.g:3435:2: ( rule__ProjectDependencies__Group_3_1__0 )*
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3436:2: ( rule__ProjectDependencies__Group_3_1__0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Comma) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalN4MFParser.g:3436:3: rule__ProjectDependencies__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDependencies__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getProjectDependenciesAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group_3__1__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group_3_1__0"
    // InternalN4MFParser.g:3445:1: rule__ProjectDependencies__Group_3_1__0 : rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 ;
    public final void rule__ProjectDependencies__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3449:1: ( rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 )
            // InternalN4MFParser.g:3450:2: rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ProjectDependencies__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group_3_1__0"


    // $ANTLR start "rule__ProjectDependencies__Group_3_1__0__Impl"
    // InternalN4MFParser.g:3457:1: rule__ProjectDependencies__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProjectDependencies__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3461:1: ( ( Comma ) )
            // InternalN4MFParser.g:3462:1: ( Comma )
            {
            // InternalN4MFParser.g:3462:1: ( Comma )
            // InternalN4MFParser.g:3463:2: Comma
            {
             before(grammarAccess.getProjectDependenciesAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getProjectDependenciesAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group_3_1__0__Impl"


    // $ANTLR start "rule__ProjectDependencies__Group_3_1__1"
    // InternalN4MFParser.g:3472:1: rule__ProjectDependencies__Group_3_1__1 : rule__ProjectDependencies__Group_3_1__1__Impl ;
    public final void rule__ProjectDependencies__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3476:1: ( rule__ProjectDependencies__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3477:2: rule__ProjectDependencies__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependencies__Group_3_1__1"


    // $ANTLR start "rule__ProjectDependencies__Group_3_1__1__Impl"
    // InternalN4MFParser.g:3483:1: rule__ProjectDependencies__Group_3_1__1__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) ;
    public final void rule__ProjectDependencies__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3487:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3488:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3488:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3489:2: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3490:2: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            // InternalN4MFParser.g:3490:3: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_1_1()); 

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
    // $ANTLR end "rule__ProjectDependencies__Group_3_1__1__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__0"
    // InternalN4MFParser.g:3499:1: rule__ProvidedRuntimeLibraries__Group__0 : rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3503:1: ( rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:3504:2: rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__ProvidedRuntimeLibraries__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__0"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__0__Impl"
    // InternalN4MFParser.g:3511:1: rule__ProvidedRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3515:1: ( ( () ) )
            // InternalN4MFParser.g:3516:1: ( () )
            {
            // InternalN4MFParser.g:3516:1: ( () )
            // InternalN4MFParser.g:3517:2: ()
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:3518:2: ()
            // InternalN4MFParser.g:3518:3: 
            {
            }

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__0__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__1"
    // InternalN4MFParser.g:3526:1: rule__ProvidedRuntimeLibraries__Group__1 : rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 ;
    public final void rule__ProvidedRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3530:1: ( rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:3531:2: rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__ProvidedRuntimeLibraries__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__1"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__1__Impl"
    // InternalN4MFParser.g:3538:1: rule__ProvidedRuntimeLibraries__Group__1__Impl : ( ProvidedRuntimeLibraries ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3542:1: ( ( ProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:3543:1: ( ProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:3543:1: ( ProvidedRuntimeLibraries )
            // InternalN4MFParser.g:3544:2: ProvidedRuntimeLibraries
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesKeyword_1()); 
            match(input,ProvidedRuntimeLibraries,FOLLOW_2); 
             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesKeyword_1()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__1__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__2"
    // InternalN4MFParser.g:3553:1: rule__ProvidedRuntimeLibraries__Group__2 : rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 ;
    public final void rule__ProvidedRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3557:1: ( rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:3558:2: rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__ProvidedRuntimeLibraries__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__2"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__2__Impl"
    // InternalN4MFParser.g:3565:1: rule__ProvidedRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3569:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3570:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3570:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3571:2: LeftCurlyBracket
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__2__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__3"
    // InternalN4MFParser.g:3580:1: rule__ProvidedRuntimeLibraries__Group__3 : rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 ;
    public final void rule__ProvidedRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3584:1: ( rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:3585:2: rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__ProvidedRuntimeLibraries__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__3"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__3__Impl"
    // InternalN4MFParser.g:3592:1: rule__ProvidedRuntimeLibraries__Group__3__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3596:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:3597:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3597:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:3598:2: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:3599:2: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==ProjectDependencies||LA24_0==ProjectVersion||LA24_0==ModuleFilters||(LA24_0>=ProjectType && LA24_0<=Application)||LA24_0==VendorName||(LA24_0>=Libraries && LA24_0<=VendorId)||LA24_0==Sources||LA24_0==Content||LA24_0==Output||(LA24_0>=Test && LA24_0<=API)||LA24_0==RULE_ID) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalN4MFParser.g:3599:3: rule__ProvidedRuntimeLibraries__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProvidedRuntimeLibraries__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__3__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__4"
    // InternalN4MFParser.g:3607:1: rule__ProvidedRuntimeLibraries__Group__4 : rule__ProvidedRuntimeLibraries__Group__4__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3611:1: ( rule__ProvidedRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:3612:2: rule__ProvidedRuntimeLibraries__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__4"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group__4__Impl"
    // InternalN4MFParser.g:3618:1: rule__ProvidedRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3622:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3623:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3623:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3624:2: RightCurlyBracket
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group__4__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3__0"
    // InternalN4MFParser.g:3634:1: rule__ProvidedRuntimeLibraries__Group_3__0 : rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3638:1: ( rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:3639:2: rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__ProvidedRuntimeLibraries__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3__0"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3__0__Impl"
    // InternalN4MFParser.g:3646:1: rule__ProvidedRuntimeLibraries__Group_3__0__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3650:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3651:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3651:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:3652:2: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:3653:2: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:3653:3: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_0()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3__0__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3__1"
    // InternalN4MFParser.g:3661:1: rule__ProvidedRuntimeLibraries__Group_3__1 : rule__ProvidedRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3665:1: ( rule__ProvidedRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:3666:2: rule__ProvidedRuntimeLibraries__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3__1"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3__1__Impl"
    // InternalN4MFParser.g:3672:1: rule__ProvidedRuntimeLibraries__Group_3__1__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3676:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3677:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3677:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:3678:2: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3679:2: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==Comma) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalN4MFParser.g:3679:3: rule__ProvidedRuntimeLibraries__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProvidedRuntimeLibraries__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3__1__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3_1__0"
    // InternalN4MFParser.g:3688:1: rule__ProvidedRuntimeLibraries__Group_3_1__0 : rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3692:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:3693:2: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3_1__0"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl"
    // InternalN4MFParser.g:3700:1: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3704:1: ( ( Comma ) )
            // InternalN4MFParser.g:3705:1: ( Comma )
            {
            // InternalN4MFParser.g:3705:1: ( Comma )
            // InternalN4MFParser.g:3706:2: Comma
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3_1__1"
    // InternalN4MFParser.g:3715:1: rule__ProvidedRuntimeLibraries__Group_3_1__1 : rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3719:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3720:2: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3_1__1"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl"
    // InternalN4MFParser.g:3726:1: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3730:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3731:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3731:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3732:2: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3733:2: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:3733:3: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_1_1()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__0"
    // InternalN4MFParser.g:3742:1: rule__RequiredRuntimeLibraries__Group__0 : rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 ;
    public final void rule__RequiredRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3746:1: ( rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:3747:2: rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__RequiredRuntimeLibraries__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__0"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__0__Impl"
    // InternalN4MFParser.g:3754:1: rule__RequiredRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__RequiredRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3758:1: ( ( () ) )
            // InternalN4MFParser.g:3759:1: ( () )
            {
            // InternalN4MFParser.g:3759:1: ( () )
            // InternalN4MFParser.g:3760:2: ()
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:3761:2: ()
            // InternalN4MFParser.g:3761:3: 
            {
            }

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__0__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__1"
    // InternalN4MFParser.g:3769:1: rule__RequiredRuntimeLibraries__Group__1 : rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 ;
    public final void rule__RequiredRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3773:1: ( rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:3774:2: rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__RequiredRuntimeLibraries__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__1"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__1__Impl"
    // InternalN4MFParser.g:3781:1: rule__RequiredRuntimeLibraries__Group__1__Impl : ( RequiredRuntimeLibraries ) ;
    public final void rule__RequiredRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3785:1: ( ( RequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:3786:1: ( RequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:3786:1: ( RequiredRuntimeLibraries )
            // InternalN4MFParser.g:3787:2: RequiredRuntimeLibraries
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesKeyword_1()); 
            match(input,RequiredRuntimeLibraries,FOLLOW_2); 
             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesKeyword_1()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__1__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__2"
    // InternalN4MFParser.g:3796:1: rule__RequiredRuntimeLibraries__Group__2 : rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 ;
    public final void rule__RequiredRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3800:1: ( rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:3801:2: rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__RequiredRuntimeLibraries__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__2"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__2__Impl"
    // InternalN4MFParser.g:3808:1: rule__RequiredRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3812:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3813:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3813:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3814:2: LeftCurlyBracket
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__2__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__3"
    // InternalN4MFParser.g:3823:1: rule__RequiredRuntimeLibraries__Group__3 : rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 ;
    public final void rule__RequiredRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3827:1: ( rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:3828:2: rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__RequiredRuntimeLibraries__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__3"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__3__Impl"
    // InternalN4MFParser.g:3835:1: rule__RequiredRuntimeLibraries__Group__3__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__RequiredRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3839:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:3840:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3840:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:3841:2: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:3842:2: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==ProjectDependencies||LA26_0==ProjectVersion||LA26_0==ModuleFilters||(LA26_0>=ProjectType && LA26_0<=Application)||LA26_0==VendorName||(LA26_0>=Libraries && LA26_0<=VendorId)||LA26_0==Sources||LA26_0==Content||LA26_0==Output||(LA26_0>=Test && LA26_0<=API)||LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalN4MFParser.g:3842:3: rule__RequiredRuntimeLibraries__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RequiredRuntimeLibraries__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__3__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__4"
    // InternalN4MFParser.g:3850:1: rule__RequiredRuntimeLibraries__Group__4 : rule__RequiredRuntimeLibraries__Group__4__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3854:1: ( rule__RequiredRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:3855:2: rule__RequiredRuntimeLibraries__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__4"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group__4__Impl"
    // InternalN4MFParser.g:3861:1: rule__RequiredRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3865:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3866:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3866:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3867:2: RightCurlyBracket
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group__4__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3__0"
    // InternalN4MFParser.g:3877:1: rule__RequiredRuntimeLibraries__Group_3__0 : rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3881:1: ( rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:3882:2: rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__RequiredRuntimeLibraries__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3__0"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3__0__Impl"
    // InternalN4MFParser.g:3889:1: rule__RequiredRuntimeLibraries__Group_3__0__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3893:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3894:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3894:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:3895:2: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:3896:2: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:3896:3: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_0()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3__0__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3__1"
    // InternalN4MFParser.g:3904:1: rule__RequiredRuntimeLibraries__Group_3__1 : rule__RequiredRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3908:1: ( rule__RequiredRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:3909:2: rule__RequiredRuntimeLibraries__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3__1"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3__1__Impl"
    // InternalN4MFParser.g:3915:1: rule__RequiredRuntimeLibraries__Group_3__1__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3919:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3920:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3920:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:3921:2: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3922:2: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==Comma) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalN4MFParser.g:3922:3: rule__RequiredRuntimeLibraries__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__RequiredRuntimeLibraries__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3__1__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3_1__0"
    // InternalN4MFParser.g:3931:1: rule__RequiredRuntimeLibraries__Group_3_1__0 : rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3935:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:3936:2: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__RequiredRuntimeLibraries__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group_3_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3_1__0"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3_1__0__Impl"
    // InternalN4MFParser.g:3943:1: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3947:1: ( ( Comma ) )
            // InternalN4MFParser.g:3948:1: ( Comma )
            {
            // InternalN4MFParser.g:3948:1: ( Comma )
            // InternalN4MFParser.g:3949:2: Comma
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getCommaKeyword_3_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3_1__0__Impl"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3_1__1"
    // InternalN4MFParser.g:3958:1: rule__RequiredRuntimeLibraries__Group_3_1__1 : rule__RequiredRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3962:1: ( rule__RequiredRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3963:2: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__Group_3_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3_1__1"


    // $ANTLR start "rule__RequiredRuntimeLibraries__Group_3_1__1__Impl"
    // InternalN4MFParser.g:3969:1: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3973:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3974:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3974:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3975:2: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3976:2: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:3976:3: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_1_1()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__Group_3_1__1__Impl"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__0"
    // InternalN4MFParser.g:3985:1: rule__ExtendedRuntimeEnvironment__Group__0 : rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:3989:1: ( rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 )
            // InternalN4MFParser.g:3990:2: rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__ExtendedRuntimeEnvironment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__0"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__0__Impl"
    // InternalN4MFParser.g:3997:1: rule__ExtendedRuntimeEnvironment__Group__0__Impl : ( () ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4001:1: ( ( () ) )
            // InternalN4MFParser.g:4002:1: ( () )
            {
            // InternalN4MFParser.g:4002:1: ( () )
            // InternalN4MFParser.g:4003:2: ()
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0()); 
            // InternalN4MFParser.g:4004:2: ()
            // InternalN4MFParser.g:4004:3: 
            {
            }

             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__0__Impl"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__1"
    // InternalN4MFParser.g:4012:1: rule__ExtendedRuntimeEnvironment__Group__1 : rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4016:1: ( rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 )
            // InternalN4MFParser.g:4017:2: rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__ExtendedRuntimeEnvironment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__1"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__1__Impl"
    // InternalN4MFParser.g:4024:1: rule__ExtendedRuntimeEnvironment__Group__1__Impl : ( ExtendedRuntimeEnvironment ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4028:1: ( ( ExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:4029:1: ( ExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:4029:1: ( ExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:4030:2: ExtendedRuntimeEnvironment
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentKeyword_1()); 
            match(input,ExtendedRuntimeEnvironment,FOLLOW_2); 
             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentKeyword_1()); 

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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__1__Impl"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__2"
    // InternalN4MFParser.g:4039:1: rule__ExtendedRuntimeEnvironment__Group__2 : rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4043:1: ( rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 )
            // InternalN4MFParser.g:4044:2: rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__ExtendedRuntimeEnvironment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__2"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__2__Impl"
    // InternalN4MFParser.g:4051:1: rule__ExtendedRuntimeEnvironment__Group__2__Impl : ( Colon ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4055:1: ( ( Colon ) )
            // InternalN4MFParser.g:4056:1: ( Colon )
            {
            // InternalN4MFParser.g:4056:1: ( Colon )
            // InternalN4MFParser.g:4057:2: Colon
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getColonKeyword_2()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getColonKeyword_2()); 

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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__2__Impl"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__3"
    // InternalN4MFParser.g:4066:1: rule__ExtendedRuntimeEnvironment__Group__3 : rule__ExtendedRuntimeEnvironment__Group__3__Impl ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4070:1: ( rule__ExtendedRuntimeEnvironment__Group__3__Impl )
            // InternalN4MFParser.g:4071:2: rule__ExtendedRuntimeEnvironment__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__Group__3__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__3"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__Group__3__Impl"
    // InternalN4MFParser.g:4077:1: rule__ExtendedRuntimeEnvironment__Group__3__Impl : ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4081:1: ( ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) )
            // InternalN4MFParser.g:4082:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            {
            // InternalN4MFParser.g:4082:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            // InternalN4MFParser.g:4083:2: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAssignment_3()); 
            // InternalN4MFParser.g:4084:2: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            // InternalN4MFParser.g:4084:3: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAssignment_3()); 

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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__Group__3__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group__0"
    // InternalN4MFParser.g:4093:1: rule__DeclaredVersion__Group__0 : rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 ;
    public final void rule__DeclaredVersion__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4097:1: ( rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 )
            // InternalN4MFParser.g:4098:2: rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1
            {
            pushFollow(FOLLOW_28);
            rule__DeclaredVersion__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group__0"


    // $ANTLR start "rule__DeclaredVersion__Group__0__Impl"
    // InternalN4MFParser.g:4105:1: rule__DeclaredVersion__Group__0__Impl : ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) ;
    public final void rule__DeclaredVersion__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4109:1: ( ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) )
            // InternalN4MFParser.g:4110:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            {
            // InternalN4MFParser.g:4110:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            // InternalN4MFParser.g:4111:2: ( rule__DeclaredVersion__MajorAssignment_0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMajorAssignment_0()); 
            // InternalN4MFParser.g:4112:2: ( rule__DeclaredVersion__MajorAssignment_0 )
            // InternalN4MFParser.g:4112:3: rule__DeclaredVersion__MajorAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__MajorAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDeclaredVersionAccess().getMajorAssignment_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group__0__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group__1"
    // InternalN4MFParser.g:4120:1: rule__DeclaredVersion__Group__1 : rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 ;
    public final void rule__DeclaredVersion__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4124:1: ( rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 )
            // InternalN4MFParser.g:4125:2: rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2
            {
            pushFollow(FOLLOW_28);
            rule__DeclaredVersion__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group__1"


    // $ANTLR start "rule__DeclaredVersion__Group__1__Impl"
    // InternalN4MFParser.g:4132:1: rule__DeclaredVersion__Group__1__Impl : ( ( rule__DeclaredVersion__Group_1__0 )? ) ;
    public final void rule__DeclaredVersion__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4136:1: ( ( ( rule__DeclaredVersion__Group_1__0 )? ) )
            // InternalN4MFParser.g:4137:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            {
            // InternalN4MFParser.g:4137:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            // InternalN4MFParser.g:4138:2: ( rule__DeclaredVersion__Group_1__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1()); 
            // InternalN4MFParser.g:4139:2: ( rule__DeclaredVersion__Group_1__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==FullStop) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalN4MFParser.g:4139:3: rule__DeclaredVersion__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DeclaredVersion__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDeclaredVersionAccess().getGroup_1()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group__1__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group__2"
    // InternalN4MFParser.g:4147:1: rule__DeclaredVersion__Group__2 : rule__DeclaredVersion__Group__2__Impl ;
    public final void rule__DeclaredVersion__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4151:1: ( rule__DeclaredVersion__Group__2__Impl )
            // InternalN4MFParser.g:4152:2: rule__DeclaredVersion__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group__2"


    // $ANTLR start "rule__DeclaredVersion__Group__2__Impl"
    // InternalN4MFParser.g:4158:1: rule__DeclaredVersion__Group__2__Impl : ( ( rule__DeclaredVersion__Group_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4162:1: ( ( ( rule__DeclaredVersion__Group_2__0 )? ) )
            // InternalN4MFParser.g:4163:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            {
            // InternalN4MFParser.g:4163:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            // InternalN4MFParser.g:4164:2: ( rule__DeclaredVersion__Group_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_2()); 
            // InternalN4MFParser.g:4165:2: ( rule__DeclaredVersion__Group_2__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==HyphenMinus) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalN4MFParser.g:4165:3: rule__DeclaredVersion__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DeclaredVersion__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDeclaredVersionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group__2__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_1__0"
    // InternalN4MFParser.g:4174:1: rule__DeclaredVersion__Group_1__0 : rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 ;
    public final void rule__DeclaredVersion__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4178:1: ( rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 )
            // InternalN4MFParser.g:4179:2: rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1
            {
            pushFollow(FOLLOW_6);
            rule__DeclaredVersion__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_1__0"


    // $ANTLR start "rule__DeclaredVersion__Group_1__0__Impl"
    // InternalN4MFParser.g:4186:1: rule__DeclaredVersion__Group_1__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4190:1: ( ( FullStop ) )
            // InternalN4MFParser.g:4191:1: ( FullStop )
            {
            // InternalN4MFParser.g:4191:1: ( FullStop )
            // InternalN4MFParser.g:4192:2: FullStop
            {
             before(grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_0()); 
            match(input,FullStop,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_1__0__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_1__1"
    // InternalN4MFParser.g:4201:1: rule__DeclaredVersion__Group_1__1 : rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 ;
    public final void rule__DeclaredVersion__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4205:1: ( rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 )
            // InternalN4MFParser.g:4206:2: rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2
            {
            pushFollow(FOLLOW_29);
            rule__DeclaredVersion__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_1__2();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_1__1"


    // $ANTLR start "rule__DeclaredVersion__Group_1__1__Impl"
    // InternalN4MFParser.g:4213:1: rule__DeclaredVersion__Group_1__1__Impl : ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4217:1: ( ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) )
            // InternalN4MFParser.g:4218:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:4218:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            // InternalN4MFParser.g:4219:2: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMinorAssignment_1_1()); 
            // InternalN4MFParser.g:4220:2: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            // InternalN4MFParser.g:4220:3: rule__DeclaredVersion__MinorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__MinorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getDeclaredVersionAccess().getMinorAssignment_1_1()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_1__1__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_1__2"
    // InternalN4MFParser.g:4228:1: rule__DeclaredVersion__Group_1__2 : rule__DeclaredVersion__Group_1__2__Impl ;
    public final void rule__DeclaredVersion__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4232:1: ( rule__DeclaredVersion__Group_1__2__Impl )
            // InternalN4MFParser.g:4233:2: rule__DeclaredVersion__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_1__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_1__2"


    // $ANTLR start "rule__DeclaredVersion__Group_1__2__Impl"
    // InternalN4MFParser.g:4239:1: rule__DeclaredVersion__Group_1__2__Impl : ( ( rule__DeclaredVersion__Group_1_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4243:1: ( ( ( rule__DeclaredVersion__Group_1_2__0 )? ) )
            // InternalN4MFParser.g:4244:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            {
            // InternalN4MFParser.g:4244:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            // InternalN4MFParser.g:4245:2: ( rule__DeclaredVersion__Group_1_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1_2()); 
            // InternalN4MFParser.g:4246:2: ( rule__DeclaredVersion__Group_1_2__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==FullStop) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalN4MFParser.g:4246:3: rule__DeclaredVersion__Group_1_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DeclaredVersion__Group_1_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDeclaredVersionAccess().getGroup_1_2()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_1__2__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_1_2__0"
    // InternalN4MFParser.g:4255:1: rule__DeclaredVersion__Group_1_2__0 : rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 ;
    public final void rule__DeclaredVersion__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4259:1: ( rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 )
            // InternalN4MFParser.g:4260:2: rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1
            {
            pushFollow(FOLLOW_6);
            rule__DeclaredVersion__Group_1_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_1_2__1();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_1_2__0"


    // $ANTLR start "rule__DeclaredVersion__Group_1_2__0__Impl"
    // InternalN4MFParser.g:4267:1: rule__DeclaredVersion__Group_1_2__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4271:1: ( ( FullStop ) )
            // InternalN4MFParser.g:4272:1: ( FullStop )
            {
            // InternalN4MFParser.g:4272:1: ( FullStop )
            // InternalN4MFParser.g:4273:2: FullStop
            {
             before(grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_2_0()); 
            match(input,FullStop,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_2_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_1_2__0__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_1_2__1"
    // InternalN4MFParser.g:4282:1: rule__DeclaredVersion__Group_1_2__1 : rule__DeclaredVersion__Group_1_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4286:1: ( rule__DeclaredVersion__Group_1_2__1__Impl )
            // InternalN4MFParser.g:4287:2: rule__DeclaredVersion__Group_1_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_1_2__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_1_2__1"


    // $ANTLR start "rule__DeclaredVersion__Group_1_2__1__Impl"
    // InternalN4MFParser.g:4293:1: rule__DeclaredVersion__Group_1_2__1__Impl : ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4297:1: ( ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) )
            // InternalN4MFParser.g:4298:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            {
            // InternalN4MFParser.g:4298:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            // InternalN4MFParser.g:4299:2: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMicroAssignment_1_2_1()); 
            // InternalN4MFParser.g:4300:2: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            // InternalN4MFParser.g:4300:3: rule__DeclaredVersion__MicroAssignment_1_2_1
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__MicroAssignment_1_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDeclaredVersionAccess().getMicroAssignment_1_2_1()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_1_2__1__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_2__0"
    // InternalN4MFParser.g:4309:1: rule__DeclaredVersion__Group_2__0 : rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 ;
    public final void rule__DeclaredVersion__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4313:1: ( rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 )
            // InternalN4MFParser.g:4314:2: rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__DeclaredVersion__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_2__1();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_2__0"


    // $ANTLR start "rule__DeclaredVersion__Group_2__0__Impl"
    // InternalN4MFParser.g:4321:1: rule__DeclaredVersion__Group_2__0__Impl : ( HyphenMinus ) ;
    public final void rule__DeclaredVersion__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4325:1: ( ( HyphenMinus ) )
            // InternalN4MFParser.g:4326:1: ( HyphenMinus )
            {
            // InternalN4MFParser.g:4326:1: ( HyphenMinus )
            // InternalN4MFParser.g:4327:2: HyphenMinus
            {
             before(grammarAccess.getDeclaredVersionAccess().getHyphenMinusKeyword_2_0()); 
            match(input,HyphenMinus,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getHyphenMinusKeyword_2_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_2__0__Impl"


    // $ANTLR start "rule__DeclaredVersion__Group_2__1"
    // InternalN4MFParser.g:4336:1: rule__DeclaredVersion__Group_2__1 : rule__DeclaredVersion__Group_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4340:1: ( rule__DeclaredVersion__Group_2__1__Impl )
            // InternalN4MFParser.g:4341:2: rule__DeclaredVersion__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__Group_2__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__DeclaredVersion__Group_2__1"


    // $ANTLR start "rule__DeclaredVersion__Group_2__1__Impl"
    // InternalN4MFParser.g:4347:1: rule__DeclaredVersion__Group_2__1__Impl : ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4351:1: ( ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) )
            // InternalN4MFParser.g:4352:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            {
            // InternalN4MFParser.g:4352:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            // InternalN4MFParser.g:4353:2: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getQualifierAssignment_2_1()); 
            // InternalN4MFParser.g:4354:2: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            // InternalN4MFParser.g:4354:3: rule__DeclaredVersion__QualifierAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__DeclaredVersion__QualifierAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDeclaredVersionAccess().getQualifierAssignment_2_1()); 

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
    // $ANTLR end "rule__DeclaredVersion__Group_2__1__Impl"


    // $ANTLR start "rule__SourceFragment__Group__0"
    // InternalN4MFParser.g:4363:1: rule__SourceFragment__Group__0 : rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 ;
    public final void rule__SourceFragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4367:1: ( rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 )
            // InternalN4MFParser.g:4368:2: rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__SourceFragment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group__0"


    // $ANTLR start "rule__SourceFragment__Group__0__Impl"
    // InternalN4MFParser.g:4375:1: rule__SourceFragment__Group__0__Impl : ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) ;
    public final void rule__SourceFragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4379:1: ( ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:4380:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:4380:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            // InternalN4MFParser.g:4381:2: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeAssignment_0()); 
            // InternalN4MFParser.g:4382:2: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            // InternalN4MFParser.g:4382:3: rule__SourceFragment__SourceFragmentTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__SourceFragmentTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeAssignment_0()); 

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
    // $ANTLR end "rule__SourceFragment__Group__0__Impl"


    // $ANTLR start "rule__SourceFragment__Group__1"
    // InternalN4MFParser.g:4390:1: rule__SourceFragment__Group__1 : rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 ;
    public final void rule__SourceFragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4394:1: ( rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 )
            // InternalN4MFParser.g:4395:2: rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__SourceFragment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group__1"


    // $ANTLR start "rule__SourceFragment__Group__1__Impl"
    // InternalN4MFParser.g:4402:1: rule__SourceFragment__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__SourceFragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4406:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4407:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4407:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4408:2: LeftCurlyBracket
            {
             before(grammarAccess.getSourceFragmentAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getSourceFragmentAccess().getLeftCurlyBracketKeyword_1()); 

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
    // $ANTLR end "rule__SourceFragment__Group__1__Impl"


    // $ANTLR start "rule__SourceFragment__Group__2"
    // InternalN4MFParser.g:4417:1: rule__SourceFragment__Group__2 : rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 ;
    public final void rule__SourceFragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4421:1: ( rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 )
            // InternalN4MFParser.g:4422:2: rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__SourceFragment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group__2"


    // $ANTLR start "rule__SourceFragment__Group__2__Impl"
    // InternalN4MFParser.g:4429:1: rule__SourceFragment__Group__2__Impl : ( ( rule__SourceFragment__PathsAssignment_2 ) ) ;
    public final void rule__SourceFragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4433:1: ( ( ( rule__SourceFragment__PathsAssignment_2 ) ) )
            // InternalN4MFParser.g:4434:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            {
            // InternalN4MFParser.g:4434:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            // InternalN4MFParser.g:4435:2: ( rule__SourceFragment__PathsAssignment_2 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_2()); 
            // InternalN4MFParser.g:4436:2: ( rule__SourceFragment__PathsAssignment_2 )
            // InternalN4MFParser.g:4436:3: rule__SourceFragment__PathsAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__PathsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSourceFragmentAccess().getPathsAssignment_2()); 

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
    // $ANTLR end "rule__SourceFragment__Group__2__Impl"


    // $ANTLR start "rule__SourceFragment__Group__3"
    // InternalN4MFParser.g:4444:1: rule__SourceFragment__Group__3 : rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 ;
    public final void rule__SourceFragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4448:1: ( rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 )
            // InternalN4MFParser.g:4449:2: rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__SourceFragment__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group__3"


    // $ANTLR start "rule__SourceFragment__Group__3__Impl"
    // InternalN4MFParser.g:4456:1: rule__SourceFragment__Group__3__Impl : ( ( rule__SourceFragment__Group_3__0 )* ) ;
    public final void rule__SourceFragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4460:1: ( ( ( rule__SourceFragment__Group_3__0 )* ) )
            // InternalN4MFParser.g:4461:1: ( ( rule__SourceFragment__Group_3__0 )* )
            {
            // InternalN4MFParser.g:4461:1: ( ( rule__SourceFragment__Group_3__0 )* )
            // InternalN4MFParser.g:4462:2: ( rule__SourceFragment__Group_3__0 )*
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup_3()); 
            // InternalN4MFParser.g:4463:2: ( rule__SourceFragment__Group_3__0 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==Comma) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalN4MFParser.g:4463:3: rule__SourceFragment__Group_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__SourceFragment__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getSourceFragmentAccess().getGroup_3()); 

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
    // $ANTLR end "rule__SourceFragment__Group__3__Impl"


    // $ANTLR start "rule__SourceFragment__Group__4"
    // InternalN4MFParser.g:4471:1: rule__SourceFragment__Group__4 : rule__SourceFragment__Group__4__Impl ;
    public final void rule__SourceFragment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4475:1: ( rule__SourceFragment__Group__4__Impl )
            // InternalN4MFParser.g:4476:2: rule__SourceFragment__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group__4"


    // $ANTLR start "rule__SourceFragment__Group__4__Impl"
    // InternalN4MFParser.g:4482:1: rule__SourceFragment__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__SourceFragment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4486:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4487:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4487:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4488:2: RightCurlyBracket
            {
             before(grammarAccess.getSourceFragmentAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getSourceFragmentAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__SourceFragment__Group__4__Impl"


    // $ANTLR start "rule__SourceFragment__Group_3__0"
    // InternalN4MFParser.g:4498:1: rule__SourceFragment__Group_3__0 : rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 ;
    public final void rule__SourceFragment__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4502:1: ( rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 )
            // InternalN4MFParser.g:4503:2: rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1
            {
            pushFollow(FOLLOW_7);
            rule__SourceFragment__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group_3__0"


    // $ANTLR start "rule__SourceFragment__Group_3__0__Impl"
    // InternalN4MFParser.g:4510:1: rule__SourceFragment__Group_3__0__Impl : ( Comma ) ;
    public final void rule__SourceFragment__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4514:1: ( ( Comma ) )
            // InternalN4MFParser.g:4515:1: ( Comma )
            {
            // InternalN4MFParser.g:4515:1: ( Comma )
            // InternalN4MFParser.g:4516:2: Comma
            {
             before(grammarAccess.getSourceFragmentAccess().getCommaKeyword_3_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getSourceFragmentAccess().getCommaKeyword_3_0()); 

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
    // $ANTLR end "rule__SourceFragment__Group_3__0__Impl"


    // $ANTLR start "rule__SourceFragment__Group_3__1"
    // InternalN4MFParser.g:4525:1: rule__SourceFragment__Group_3__1 : rule__SourceFragment__Group_3__1__Impl ;
    public final void rule__SourceFragment__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4529:1: ( rule__SourceFragment__Group_3__1__Impl )
            // InternalN4MFParser.g:4530:2: rule__SourceFragment__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__SourceFragment__Group_3__1"


    // $ANTLR start "rule__SourceFragment__Group_3__1__Impl"
    // InternalN4MFParser.g:4536:1: rule__SourceFragment__Group_3__1__Impl : ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) ;
    public final void rule__SourceFragment__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4540:1: ( ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) )
            // InternalN4MFParser.g:4541:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:4541:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            // InternalN4MFParser.g:4542:2: ( rule__SourceFragment__PathsAssignment_3_1 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_3_1()); 
            // InternalN4MFParser.g:4543:2: ( rule__SourceFragment__PathsAssignment_3_1 )
            // InternalN4MFParser.g:4543:3: rule__SourceFragment__PathsAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__SourceFragment__PathsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getSourceFragmentAccess().getPathsAssignment_3_1()); 

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
    // $ANTLR end "rule__SourceFragment__Group_3__1__Impl"


    // $ANTLR start "rule__ModuleFilter__Group__0"
    // InternalN4MFParser.g:4552:1: rule__ModuleFilter__Group__0 : rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 ;
    public final void rule__ModuleFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4556:1: ( rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 )
            // InternalN4MFParser.g:4557:2: rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ModuleFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group__0"


    // $ANTLR start "rule__ModuleFilter__Group__0__Impl"
    // InternalN4MFParser.g:4564:1: rule__ModuleFilter__Group__0__Impl : ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) ;
    public final void rule__ModuleFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4568:1: ( ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:4569:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:4569:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            // InternalN4MFParser.g:4570:2: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleFilterTypeAssignment_0()); 
            // InternalN4MFParser.g:4571:2: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            // InternalN4MFParser.g:4571:3: rule__ModuleFilter__ModuleFilterTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__ModuleFilterTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterAccess().getModuleFilterTypeAssignment_0()); 

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
    // $ANTLR end "rule__ModuleFilter__Group__0__Impl"


    // $ANTLR start "rule__ModuleFilter__Group__1"
    // InternalN4MFParser.g:4579:1: rule__ModuleFilter__Group__1 : rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 ;
    public final void rule__ModuleFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4583:1: ( rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 )
            // InternalN4MFParser.g:4584:2: rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__ModuleFilter__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group__1"


    // $ANTLR start "rule__ModuleFilter__Group__1__Impl"
    // InternalN4MFParser.g:4591:1: rule__ModuleFilter__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4595:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4596:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4596:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4597:2: LeftCurlyBracket
            {
             before(grammarAccess.getModuleFilterAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getModuleFilterAccess().getLeftCurlyBracketKeyword_1()); 

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
    // $ANTLR end "rule__ModuleFilter__Group__1__Impl"


    // $ANTLR start "rule__ModuleFilter__Group__2"
    // InternalN4MFParser.g:4606:1: rule__ModuleFilter__Group__2 : rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 ;
    public final void rule__ModuleFilter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4610:1: ( rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 )
            // InternalN4MFParser.g:4611:2: rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__ModuleFilter__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__3();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group__2"


    // $ANTLR start "rule__ModuleFilter__Group__2__Impl"
    // InternalN4MFParser.g:4618:1: rule__ModuleFilter__Group__2__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) ;
    public final void rule__ModuleFilter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4622:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) )
            // InternalN4MFParser.g:4623:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            {
            // InternalN4MFParser.g:4623:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            // InternalN4MFParser.g:4624:2: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_2()); 
            // InternalN4MFParser.g:4625:2: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            // InternalN4MFParser.g:4625:3: rule__ModuleFilter__ModuleSpecifiersAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__ModuleSpecifiersAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_2()); 

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
    // $ANTLR end "rule__ModuleFilter__Group__2__Impl"


    // $ANTLR start "rule__ModuleFilter__Group__3"
    // InternalN4MFParser.g:4633:1: rule__ModuleFilter__Group__3 : rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 ;
    public final void rule__ModuleFilter__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4637:1: ( rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 )
            // InternalN4MFParser.g:4638:2: rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__ModuleFilter__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__4();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group__3"


    // $ANTLR start "rule__ModuleFilter__Group__3__Impl"
    // InternalN4MFParser.g:4645:1: rule__ModuleFilter__Group__3__Impl : ( ( rule__ModuleFilter__Group_3__0 )* ) ;
    public final void rule__ModuleFilter__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4649:1: ( ( ( rule__ModuleFilter__Group_3__0 )* ) )
            // InternalN4MFParser.g:4650:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            {
            // InternalN4MFParser.g:4650:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            // InternalN4MFParser.g:4651:2: ( rule__ModuleFilter__Group_3__0 )*
            {
             before(grammarAccess.getModuleFilterAccess().getGroup_3()); 
            // InternalN4MFParser.g:4652:2: ( rule__ModuleFilter__Group_3__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==Comma) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalN4MFParser.g:4652:3: rule__ModuleFilter__Group_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ModuleFilter__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getModuleFilterAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ModuleFilter__Group__3__Impl"


    // $ANTLR start "rule__ModuleFilter__Group__4"
    // InternalN4MFParser.g:4660:1: rule__ModuleFilter__Group__4 : rule__ModuleFilter__Group__4__Impl ;
    public final void rule__ModuleFilter__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4664:1: ( rule__ModuleFilter__Group__4__Impl )
            // InternalN4MFParser.g:4665:2: rule__ModuleFilter__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group__4__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group__4"


    // $ANTLR start "rule__ModuleFilter__Group__4__Impl"
    // InternalN4MFParser.g:4671:1: rule__ModuleFilter__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4675:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4676:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4676:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4677:2: RightCurlyBracket
            {
             before(grammarAccess.getModuleFilterAccess().getRightCurlyBracketKeyword_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getModuleFilterAccess().getRightCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__ModuleFilter__Group__4__Impl"


    // $ANTLR start "rule__ModuleFilter__Group_3__0"
    // InternalN4MFParser.g:4687:1: rule__ModuleFilter__Group_3__0 : rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 ;
    public final void rule__ModuleFilter__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4691:1: ( rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 )
            // InternalN4MFParser.g:4692:2: rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1
            {
            pushFollow(FOLLOW_7);
            rule__ModuleFilter__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group_3__1();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group_3__0"


    // $ANTLR start "rule__ModuleFilter__Group_3__0__Impl"
    // InternalN4MFParser.g:4699:1: rule__ModuleFilter__Group_3__0__Impl : ( Comma ) ;
    public final void rule__ModuleFilter__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4703:1: ( ( Comma ) )
            // InternalN4MFParser.g:4704:1: ( Comma )
            {
            // InternalN4MFParser.g:4704:1: ( Comma )
            // InternalN4MFParser.g:4705:2: Comma
            {
             before(grammarAccess.getModuleFilterAccess().getCommaKeyword_3_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getModuleFilterAccess().getCommaKeyword_3_0()); 

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
    // $ANTLR end "rule__ModuleFilter__Group_3__0__Impl"


    // $ANTLR start "rule__ModuleFilter__Group_3__1"
    // InternalN4MFParser.g:4714:1: rule__ModuleFilter__Group_3__1 : rule__ModuleFilter__Group_3__1__Impl ;
    public final void rule__ModuleFilter__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4718:1: ( rule__ModuleFilter__Group_3__1__Impl )
            // InternalN4MFParser.g:4719:2: rule__ModuleFilter__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__Group_3__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilter__Group_3__1"


    // $ANTLR start "rule__ModuleFilter__Group_3__1__Impl"
    // InternalN4MFParser.g:4725:1: rule__ModuleFilter__Group_3__1__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) ;
    public final void rule__ModuleFilter__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4729:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) )
            // InternalN4MFParser.g:4730:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:4730:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            // InternalN4MFParser.g:4731:2: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_3_1()); 
            // InternalN4MFParser.g:4732:2: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            // InternalN4MFParser.g:4732:3: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilter__ModuleSpecifiersAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_3_1()); 

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
    // $ANTLR end "rule__ModuleFilter__Group_3__1__Impl"


    // $ANTLR start "rule__BootstrapModule__Group__0"
    // InternalN4MFParser.g:4741:1: rule__BootstrapModule__Group__0 : rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 ;
    public final void rule__BootstrapModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4745:1: ( rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 )
            // InternalN4MFParser.g:4746:2: rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__BootstrapModule__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BootstrapModule__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__BootstrapModule__Group__0"


    // $ANTLR start "rule__BootstrapModule__Group__0__Impl"
    // InternalN4MFParser.g:4753:1: rule__BootstrapModule__Group__0__Impl : ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__BootstrapModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4757:1: ( ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:4758:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:4758:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:4759:2: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:4760:2: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:4760:3: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardAssignment_0()); 

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
    // $ANTLR end "rule__BootstrapModule__Group__0__Impl"


    // $ANTLR start "rule__BootstrapModule__Group__1"
    // InternalN4MFParser.g:4768:1: rule__BootstrapModule__Group__1 : rule__BootstrapModule__Group__1__Impl ;
    public final void rule__BootstrapModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4772:1: ( rule__BootstrapModule__Group__1__Impl )
            // InternalN4MFParser.g:4773:2: rule__BootstrapModule__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BootstrapModule__Group__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__BootstrapModule__Group__1"


    // $ANTLR start "rule__BootstrapModule__Group__1__Impl"
    // InternalN4MFParser.g:4779:1: rule__BootstrapModule__Group__1__Impl : ( ( rule__BootstrapModule__Group_1__0 )? ) ;
    public final void rule__BootstrapModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4783:1: ( ( ( rule__BootstrapModule__Group_1__0 )? ) )
            // InternalN4MFParser.g:4784:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            {
            // InternalN4MFParser.g:4784:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            // InternalN4MFParser.g:4785:2: ( rule__BootstrapModule__Group_1__0 )?
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup_1()); 
            // InternalN4MFParser.g:4786:2: ( rule__BootstrapModule__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==In) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalN4MFParser.g:4786:3: rule__BootstrapModule__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__BootstrapModule__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getBootstrapModuleAccess().getGroup_1()); 

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
    // $ANTLR end "rule__BootstrapModule__Group__1__Impl"


    // $ANTLR start "rule__BootstrapModule__Group_1__0"
    // InternalN4MFParser.g:4795:1: rule__BootstrapModule__Group_1__0 : rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 ;
    public final void rule__BootstrapModule__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4799:1: ( rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 )
            // InternalN4MFParser.g:4800:2: rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__BootstrapModule__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BootstrapModule__Group_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__BootstrapModule__Group_1__0"


    // $ANTLR start "rule__BootstrapModule__Group_1__0__Impl"
    // InternalN4MFParser.g:4807:1: rule__BootstrapModule__Group_1__0__Impl : ( In ) ;
    public final void rule__BootstrapModule__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4811:1: ( ( In ) )
            // InternalN4MFParser.g:4812:1: ( In )
            {
            // InternalN4MFParser.g:4812:1: ( In )
            // InternalN4MFParser.g:4813:2: In
            {
             before(grammarAccess.getBootstrapModuleAccess().getInKeyword_1_0()); 
            match(input,In,FOLLOW_2); 
             after(grammarAccess.getBootstrapModuleAccess().getInKeyword_1_0()); 

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
    // $ANTLR end "rule__BootstrapModule__Group_1__0__Impl"


    // $ANTLR start "rule__BootstrapModule__Group_1__1"
    // InternalN4MFParser.g:4822:1: rule__BootstrapModule__Group_1__1 : rule__BootstrapModule__Group_1__1__Impl ;
    public final void rule__BootstrapModule__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4826:1: ( rule__BootstrapModule__Group_1__1__Impl )
            // InternalN4MFParser.g:4827:2: rule__BootstrapModule__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BootstrapModule__Group_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__BootstrapModule__Group_1__1"


    // $ANTLR start "rule__BootstrapModule__Group_1__1__Impl"
    // InternalN4MFParser.g:4833:1: rule__BootstrapModule__Group_1__1__Impl : ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) ;
    public final void rule__BootstrapModule__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4837:1: ( ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:4838:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:4838:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:4839:2: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:4840:2: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:4840:3: rule__BootstrapModule__SourcePathAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__BootstrapModule__SourcePathAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getBootstrapModuleAccess().getSourcePathAssignment_1_1()); 

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
    // $ANTLR end "rule__BootstrapModule__Group_1__1__Impl"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group__0"
    // InternalN4MFParser.g:4849:1: rule__ModuleFilterSpecifier__Group__0 : rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 ;
    public final void rule__ModuleFilterSpecifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4853:1: ( rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 )
            // InternalN4MFParser.g:4854:2: rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__ModuleFilterSpecifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group__0"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group__0__Impl"
    // InternalN4MFParser.g:4861:1: rule__ModuleFilterSpecifier__Group__0__Impl : ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4865:1: ( ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:4866:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:4866:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:4867:2: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:4868:2: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:4868:3: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardAssignment_0()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group__0__Impl"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group__1"
    // InternalN4MFParser.g:4876:1: rule__ModuleFilterSpecifier__Group__1 : rule__ModuleFilterSpecifier__Group__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4880:1: ( rule__ModuleFilterSpecifier__Group__1__Impl )
            // InternalN4MFParser.g:4881:2: rule__ModuleFilterSpecifier__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__Group__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group__1"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group__1__Impl"
    // InternalN4MFParser.g:4887:1: rule__ModuleFilterSpecifier__Group__1__Impl : ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) ;
    public final void rule__ModuleFilterSpecifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4891:1: ( ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) )
            // InternalN4MFParser.g:4892:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            {
            // InternalN4MFParser.g:4892:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            // InternalN4MFParser.g:4893:2: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup_1()); 
            // InternalN4MFParser.g:4894:2: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==In) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalN4MFParser.g:4894:3: rule__ModuleFilterSpecifier__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ModuleFilterSpecifier__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModuleFilterSpecifierAccess().getGroup_1()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group__1__Impl"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group_1__0"
    // InternalN4MFParser.g:4903:1: rule__ModuleFilterSpecifier__Group_1__0 : rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 ;
    public final void rule__ModuleFilterSpecifier__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4907:1: ( rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 )
            // InternalN4MFParser.g:4908:2: rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__ModuleFilterSpecifier__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__Group_1__1();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group_1__0"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group_1__0__Impl"
    // InternalN4MFParser.g:4915:1: rule__ModuleFilterSpecifier__Group_1__0__Impl : ( In ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4919:1: ( ( In ) )
            // InternalN4MFParser.g:4920:1: ( In )
            {
            // InternalN4MFParser.g:4920:1: ( In )
            // InternalN4MFParser.g:4921:2: In
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getInKeyword_1_0()); 
            match(input,In,FOLLOW_2); 
             after(grammarAccess.getModuleFilterSpecifierAccess().getInKeyword_1_0()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group_1__0__Impl"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group_1__1"
    // InternalN4MFParser.g:4930:1: rule__ModuleFilterSpecifier__Group_1__1 : rule__ModuleFilterSpecifier__Group_1__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4934:1: ( rule__ModuleFilterSpecifier__Group_1__1__Impl )
            // InternalN4MFParser.g:4935:2: rule__ModuleFilterSpecifier__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__Group_1__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group_1__1"


    // $ANTLR start "rule__ModuleFilterSpecifier__Group_1__1__Impl"
    // InternalN4MFParser.g:4941:1: rule__ModuleFilterSpecifier__Group_1__1__Impl : ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4945:1: ( ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:4946:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:4946:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:4947:2: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:4948:2: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:4948:3: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ModuleFilterSpecifier__SourcePathAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathAssignment_1_1()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__Group_1__1__Impl"


    // $ANTLR start "rule__ProjectDependency__Group__0"
    // InternalN4MFParser.g:4957:1: rule__ProjectDependency__Group__0 : rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 ;
    public final void rule__ProjectDependency__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4961:1: ( rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 )
            // InternalN4MFParser.g:4962:2: rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__ProjectDependency__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependency__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependency__Group__0"


    // $ANTLR start "rule__ProjectDependency__Group__0__Impl"
    // InternalN4MFParser.g:4969:1: rule__ProjectDependency__Group__0__Impl : ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) ;
    public final void rule__ProjectDependency__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4973:1: ( ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) )
            // InternalN4MFParser.g:4974:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            {
            // InternalN4MFParser.g:4974:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            // InternalN4MFParser.g:4975:2: ( rule__ProjectDependency__ProjectAssignment_0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getProjectAssignment_0()); 
            // InternalN4MFParser.g:4976:2: ( rule__ProjectDependency__ProjectAssignment_0 )
            // InternalN4MFParser.g:4976:3: rule__ProjectDependency__ProjectAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependency__ProjectAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getProjectDependencyAccess().getProjectAssignment_0()); 

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
    // $ANTLR end "rule__ProjectDependency__Group__0__Impl"


    // $ANTLR start "rule__ProjectDependency__Group__1"
    // InternalN4MFParser.g:4984:1: rule__ProjectDependency__Group__1 : rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 ;
    public final void rule__ProjectDependency__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:4988:1: ( rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 )
            // InternalN4MFParser.g:4989:2: rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2
            {
            pushFollow(FOLLOW_31);
            rule__ProjectDependency__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDependency__Group__2();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependency__Group__1"


    // $ANTLR start "rule__ProjectDependency__Group__1__Impl"
    // InternalN4MFParser.g:4996:1: rule__ProjectDependency__Group__1__Impl : ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) ;
    public final void rule__ProjectDependency__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5000:1: ( ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) )
            // InternalN4MFParser.g:5001:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            {
            // InternalN4MFParser.g:5001:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            // InternalN4MFParser.g:5002:2: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getVersionConstraintAssignment_1()); 
            // InternalN4MFParser.g:5003:2: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LeftParenthesis||LA35_0==LeftSquareBracket||LA35_0==RULE_INT) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalN4MFParser.g:5003:3: rule__ProjectDependency__VersionConstraintAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDependency__VersionConstraintAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProjectDependencyAccess().getVersionConstraintAssignment_1()); 

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
    // $ANTLR end "rule__ProjectDependency__Group__1__Impl"


    // $ANTLR start "rule__ProjectDependency__Group__2"
    // InternalN4MFParser.g:5011:1: rule__ProjectDependency__Group__2 : rule__ProjectDependency__Group__2__Impl ;
    public final void rule__ProjectDependency__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5015:1: ( rule__ProjectDependency__Group__2__Impl )
            // InternalN4MFParser.g:5016:2: rule__ProjectDependency__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDependency__Group__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDependency__Group__2"


    // $ANTLR start "rule__ProjectDependency__Group__2__Impl"
    // InternalN4MFParser.g:5022:1: rule__ProjectDependency__Group__2__Impl : ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) ;
    public final void rule__ProjectDependency__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5026:1: ( ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) )
            // InternalN4MFParser.g:5027:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            {
            // InternalN4MFParser.g:5027:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            // InternalN4MFParser.g:5028:2: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getDeclaredScopeAssignment_2()); 
            // InternalN4MFParser.g:5029:2: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Compile||LA36_0==Test) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalN4MFParser.g:5029:3: rule__ProjectDependency__DeclaredScopeAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDependency__DeclaredScopeAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProjectDependencyAccess().getDeclaredScopeAssignment_2()); 

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
    // $ANTLR end "rule__ProjectDependency__Group__2__Impl"


    // $ANTLR start "rule__SimpleProjectDescription__Group__0"
    // InternalN4MFParser.g:5038:1: rule__SimpleProjectDescription__Group__0 : rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 ;
    public final void rule__SimpleProjectDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5042:1: ( rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 )
            // InternalN4MFParser.g:5043:2: rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__SimpleProjectDescription__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__Group__1();

            state._fsp--;


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
    // $ANTLR end "rule__SimpleProjectDescription__Group__0"


    // $ANTLR start "rule__SimpleProjectDescription__Group__0__Impl"
    // InternalN4MFParser.g:5050:1: rule__SimpleProjectDescription__Group__0__Impl : ( ( rule__SimpleProjectDescription__Group_0__0 )? ) ;
    public final void rule__SimpleProjectDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5054:1: ( ( ( rule__SimpleProjectDescription__Group_0__0 )? ) )
            // InternalN4MFParser.g:5055:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            {
            // InternalN4MFParser.g:5055:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            // InternalN4MFParser.g:5056:2: ( rule__SimpleProjectDescription__Group_0__0 )?
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup_0()); 
            // InternalN4MFParser.g:5057:2: ( rule__SimpleProjectDescription__Group_0__0 )?
            int alt37=2;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // InternalN4MFParser.g:5057:3: rule__SimpleProjectDescription__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SimpleProjectDescription__Group_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSimpleProjectDescriptionAccess().getGroup_0()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__Group__0__Impl"


    // $ANTLR start "rule__SimpleProjectDescription__Group__1"
    // InternalN4MFParser.g:5065:1: rule__SimpleProjectDescription__Group__1 : rule__SimpleProjectDescription__Group__1__Impl ;
    public final void rule__SimpleProjectDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5069:1: ( rule__SimpleProjectDescription__Group__1__Impl )
            // InternalN4MFParser.g:5070:2: rule__SimpleProjectDescription__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__Group__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__SimpleProjectDescription__Group__1"


    // $ANTLR start "rule__SimpleProjectDescription__Group__1__Impl"
    // InternalN4MFParser.g:5076:1: rule__SimpleProjectDescription__Group__1__Impl : ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) ) ;
    public final void rule__SimpleProjectDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5080:1: ( ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) ) )
            // InternalN4MFParser.g:5081:1: ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) )
            {
            // InternalN4MFParser.g:5081:1: ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) )
            // InternalN4MFParser.g:5082:2: ( rule__SimpleProjectDescription__ProjectIdAssignment_1 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdAssignment_1()); 
            // InternalN4MFParser.g:5083:2: ( rule__SimpleProjectDescription__ProjectIdAssignment_1 )
            // InternalN4MFParser.g:5083:3: rule__SimpleProjectDescription__ProjectIdAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__ProjectIdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdAssignment_1()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__Group__1__Impl"


    // $ANTLR start "rule__SimpleProjectDescription__Group_0__0"
    // InternalN4MFParser.g:5092:1: rule__SimpleProjectDescription__Group_0__0 : rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 ;
    public final void rule__SimpleProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5096:1: ( rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:5097:2: rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1
            {
            pushFollow(FOLLOW_3);
            rule__SimpleProjectDescription__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__Group_0__1();

            state._fsp--;


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
    // $ANTLR end "rule__SimpleProjectDescription__Group_0__0"


    // $ANTLR start "rule__SimpleProjectDescription__Group_0__0__Impl"
    // InternalN4MFParser.g:5104:1: rule__SimpleProjectDescription__Group_0__0__Impl : ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) ;
    public final void rule__SimpleProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5108:1: ( ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) )
            // InternalN4MFParser.g:5109:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            {
            // InternalN4MFParser.g:5109:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            // InternalN4MFParser.g:5110:2: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdAssignment_0_0()); 
            // InternalN4MFParser.g:5111:2: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            // InternalN4MFParser.g:5111:3: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdAssignment_0_0()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__Group_0__0__Impl"


    // $ANTLR start "rule__SimpleProjectDescription__Group_0__1"
    // InternalN4MFParser.g:5119:1: rule__SimpleProjectDescription__Group_0__1 : rule__SimpleProjectDescription__Group_0__1__Impl ;
    public final void rule__SimpleProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5123:1: ( rule__SimpleProjectDescription__Group_0__1__Impl )
            // InternalN4MFParser.g:5124:2: rule__SimpleProjectDescription__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__Group_0__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__SimpleProjectDescription__Group_0__1"


    // $ANTLR start "rule__SimpleProjectDescription__Group_0__1__Impl"
    // InternalN4MFParser.g:5130:1: rule__SimpleProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__SimpleProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5134:1: ( ( Colon ) )
            // InternalN4MFParser.g:5135:1: ( Colon )
            {
            // InternalN4MFParser.g:5135:1: ( Colon )
            // InternalN4MFParser.g:5136:2: Colon
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getColonKeyword_0_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getSimpleProjectDescriptionAccess().getColonKeyword_0_1()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__Group_0__1__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0__0"
    // InternalN4MFParser.g:5146:1: rule__VersionConstraint__Group_0__0 : rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 ;
    public final void rule__VersionConstraint__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5150:1: ( rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 )
            // InternalN4MFParser.g:5151:2: rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1
            {
            pushFollow(FOLLOW_6);
            rule__VersionConstraint__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0__1();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0__0"


    // $ANTLR start "rule__VersionConstraint__Group_0__0__Impl"
    // InternalN4MFParser.g:5158:1: rule__VersionConstraint__Group_0__0__Impl : ( ( rule__VersionConstraint__Alternatives_0_0 ) ) ;
    public final void rule__VersionConstraint__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5162:1: ( ( ( rule__VersionConstraint__Alternatives_0_0 ) ) )
            // InternalN4MFParser.g:5163:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            {
            // InternalN4MFParser.g:5163:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            // InternalN4MFParser.g:5164:2: ( rule__VersionConstraint__Alternatives_0_0 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_0()); 
            // InternalN4MFParser.g:5165:2: ( rule__VersionConstraint__Alternatives_0_0 )
            // InternalN4MFParser.g:5165:3: rule__VersionConstraint__Alternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Alternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getAlternatives_0_0()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0__0__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0__1"
    // InternalN4MFParser.g:5173:1: rule__VersionConstraint__Group_0__1 : rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 ;
    public final void rule__VersionConstraint__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5177:1: ( rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 )
            // InternalN4MFParser.g:5178:2: rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2
            {
            pushFollow(FOLLOW_32);
            rule__VersionConstraint__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0__2();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0__1"


    // $ANTLR start "rule__VersionConstraint__Group_0__1__Impl"
    // InternalN4MFParser.g:5185:1: rule__VersionConstraint__Group_0__1__Impl : ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5189:1: ( ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) )
            // InternalN4MFParser.g:5190:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            {
            // InternalN4MFParser.g:5190:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            // InternalN4MFParser.g:5191:2: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_0_1()); 
            // InternalN4MFParser.g:5192:2: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            // InternalN4MFParser.g:5192:3: rule__VersionConstraint__LowerVersionAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__LowerVersionAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_0_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0__1__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0__2"
    // InternalN4MFParser.g:5200:1: rule__VersionConstraint__Group_0__2 : rule__VersionConstraint__Group_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5204:1: ( rule__VersionConstraint__Group_0__2__Impl )
            // InternalN4MFParser.g:5205:2: rule__VersionConstraint__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0__2"


    // $ANTLR start "rule__VersionConstraint__Group_0__2__Impl"
    // InternalN4MFParser.g:5211:1: rule__VersionConstraint__Group_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5215:1: ( ( ( rule__VersionConstraint__Alternatives_0_2 ) ) )
            // InternalN4MFParser.g:5216:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            {
            // InternalN4MFParser.g:5216:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            // InternalN4MFParser.g:5217:2: ( rule__VersionConstraint__Alternatives_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2()); 
            // InternalN4MFParser.g:5218:2: ( rule__VersionConstraint__Alternatives_0_2 )
            // InternalN4MFParser.g:5218:3: rule__VersionConstraint__Alternatives_0_2
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Alternatives_0_2();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0__2__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__0"
    // InternalN4MFParser.g:5227:1: rule__VersionConstraint__Group_0_2_0__0 : rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 ;
    public final void rule__VersionConstraint__Group_0_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5231:1: ( rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 )
            // InternalN4MFParser.g:5232:2: rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1
            {
            pushFollow(FOLLOW_6);
            rule__VersionConstraint__Group_0_2_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0_2_0__1();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__0"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__0__Impl"
    // InternalN4MFParser.g:5239:1: rule__VersionConstraint__Group_0_2_0__0__Impl : ( Comma ) ;
    public final void rule__VersionConstraint__Group_0_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5243:1: ( ( Comma ) )
            // InternalN4MFParser.g:5244:1: ( Comma )
            {
            // InternalN4MFParser.g:5244:1: ( Comma )
            // InternalN4MFParser.g:5245:2: Comma
            {
             before(grammarAccess.getVersionConstraintAccess().getCommaKeyword_0_2_0_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getVersionConstraintAccess().getCommaKeyword_0_2_0_0()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__0__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__1"
    // InternalN4MFParser.g:5254:1: rule__VersionConstraint__Group_0_2_0__1 : rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 ;
    public final void rule__VersionConstraint__Group_0_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5258:1: ( rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 )
            // InternalN4MFParser.g:5259:2: rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2
            {
            pushFollow(FOLLOW_33);
            rule__VersionConstraint__Group_0_2_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0_2_0__2();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__1"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__1__Impl"
    // InternalN4MFParser.g:5266:1: rule__VersionConstraint__Group_0_2_0__1__Impl : ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5270:1: ( ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) )
            // InternalN4MFParser.g:5271:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            {
            // InternalN4MFParser.g:5271:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            // InternalN4MFParser.g:5272:2: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getUpperVersionAssignment_0_2_0_1()); 
            // InternalN4MFParser.g:5273:2: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            // InternalN4MFParser.g:5273:3: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__UpperVersionAssignment_0_2_0_1();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getUpperVersionAssignment_0_2_0_1()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__1__Impl"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__2"
    // InternalN4MFParser.g:5281:1: rule__VersionConstraint__Group_0_2_0__2 : rule__VersionConstraint__Group_0_2_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5285:1: ( rule__VersionConstraint__Group_0_2_0__2__Impl )
            // InternalN4MFParser.g:5286:2: rule__VersionConstraint__Group_0_2_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Group_0_2_0__2__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__2"


    // $ANTLR start "rule__VersionConstraint__Group_0_2_0__2__Impl"
    // InternalN4MFParser.g:5292:1: rule__VersionConstraint__Group_0_2_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5296:1: ( ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) )
            // InternalN4MFParser.g:5297:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            {
            // InternalN4MFParser.g:5297:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            // InternalN4MFParser.g:5298:2: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2_0_2()); 
            // InternalN4MFParser.g:5299:2: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            // InternalN4MFParser.g:5299:3: rule__VersionConstraint__Alternatives_0_2_0_2
            {
            pushFollow(FOLLOW_2);
            rule__VersionConstraint__Alternatives_0_2_0_2();

            state._fsp--;


            }

             after(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2_0_2()); 

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
    // $ANTLR end "rule__VersionConstraint__Group_0_2_0__2__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_11__0"
    // InternalN4MFParser.g:5308:1: rule__N4mfIdentifier__Group_11__0 : rule__N4mfIdentifier__Group_11__0__Impl rule__N4mfIdentifier__Group_11__1 ;
    public final void rule__N4mfIdentifier__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5312:1: ( rule__N4mfIdentifier__Group_11__0__Impl rule__N4mfIdentifier__Group_11__1 )
            // InternalN4MFParser.g:5313:2: rule__N4mfIdentifier__Group_11__0__Impl rule__N4mfIdentifier__Group_11__1
            {
            pushFollow(FOLLOW_34);
            rule__N4mfIdentifier__Group_11__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_11__1();

            state._fsp--;


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
    // $ANTLR end "rule__N4mfIdentifier__Group_11__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_11__0__Impl"
    // InternalN4MFParser.g:5320:1: rule__N4mfIdentifier__Group_11__0__Impl : ( ProjectDependencies ) ;
    public final void rule__N4mfIdentifier__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5324:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:5325:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:5325:1: ( ProjectDependencies )
            // InternalN4MFParser.g:5326:2: ProjectDependencies
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_11_0()); 
            match(input,ProjectDependencies,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_11_0()); 

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
    // $ANTLR end "rule__N4mfIdentifier__Group_11__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_11__1"
    // InternalN4MFParser.g:5335:1: rule__N4mfIdentifier__Group_11__1 : rule__N4mfIdentifier__Group_11__1__Impl ;
    public final void rule__N4mfIdentifier__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5339:1: ( rule__N4mfIdentifier__Group_11__1__Impl )
            // InternalN4MFParser.g:5340:2: rule__N4mfIdentifier__Group_11__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_11__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__N4mfIdentifier__Group_11__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_11__1__Impl"
    // InternalN4MFParser.g:5346:1: rule__N4mfIdentifier__Group_11__1__Impl : ( KW_System ) ;
    public final void rule__N4mfIdentifier__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5350:1: ( ( KW_System ) )
            // InternalN4MFParser.g:5351:1: ( KW_System )
            {
            // InternalN4MFParser.g:5351:1: ( KW_System )
            // InternalN4MFParser.g:5352:2: KW_System
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_11_1()); 
            match(input,KW_System,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_11_1()); 

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
    // $ANTLR end "rule__N4mfIdentifier__Group_11__1__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_15__0"
    // InternalN4MFParser.g:5362:1: rule__N4mfIdentifier__Group_15__0 : rule__N4mfIdentifier__Group_15__0__Impl rule__N4mfIdentifier__Group_15__1 ;
    public final void rule__N4mfIdentifier__Group_15__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5366:1: ( rule__N4mfIdentifier__Group_15__0__Impl rule__N4mfIdentifier__Group_15__1 )
            // InternalN4MFParser.g:5367:2: rule__N4mfIdentifier__Group_15__0__Impl rule__N4mfIdentifier__Group_15__1
            {
            pushFollow(FOLLOW_35);
            rule__N4mfIdentifier__Group_15__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_15__1();

            state._fsp--;


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
    // $ANTLR end "rule__N4mfIdentifier__Group_15__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_15__0__Impl"
    // InternalN4MFParser.g:5374:1: rule__N4mfIdentifier__Group_15__0__Impl : ( Processor ) ;
    public final void rule__N4mfIdentifier__Group_15__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5378:1: ( ( Processor ) )
            // InternalN4MFParser.g:5379:1: ( Processor )
            {
            // InternalN4MFParser.g:5379:1: ( Processor )
            // InternalN4MFParser.g:5380:2: Processor
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_15_0()); 
            match(input,Processor,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_15_0()); 

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
    // $ANTLR end "rule__N4mfIdentifier__Group_15__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_15__1"
    // InternalN4MFParser.g:5389:1: rule__N4mfIdentifier__Group_15__1 : rule__N4mfIdentifier__Group_15__1__Impl ;
    public final void rule__N4mfIdentifier__Group_15__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5393:1: ( rule__N4mfIdentifier__Group_15__1__Impl )
            // InternalN4MFParser.g:5394:2: rule__N4mfIdentifier__Group_15__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_15__1__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__N4mfIdentifier__Group_15__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_15__1__Impl"
    // InternalN4MFParser.g:5400:1: rule__N4mfIdentifier__Group_15__1__Impl : ( Source ) ;
    public final void rule__N4mfIdentifier__Group_15__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5404:1: ( ( Source ) )
            // InternalN4MFParser.g:5405:1: ( Source )
            {
            // InternalN4MFParser.g:5405:1: ( Source )
            // InternalN4MFParser.g:5406:2: Source
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_15_1()); 
            match(input,Source,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_15_1()); 

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
    // $ANTLR end "rule__N4mfIdentifier__Group_15__1__Impl"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup"
    // InternalN4MFParser.g:5416:1: rule__ProjectDescription__UnorderedGroup : rule__ProjectDescription__UnorderedGroup__0 {...}?;
    public final void rule__ProjectDescription__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
        	
        try {
            // InternalN4MFParser.g:5421:1: ( rule__ProjectDescription__UnorderedGroup__0 {...}?)
            // InternalN4MFParser.g:5422:2: rule__ProjectDescription__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__Impl"
    // InternalN4MFParser.g:5430:1: rule__ProjectDescription__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) ) ) ;
    public final void rule__ProjectDescription__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalN4MFParser.g:5435:1: ( ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) ) ) )
            // InternalN4MFParser.g:5436:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) ) )
            {
            // InternalN4MFParser.g:5436:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) ) )
            int alt38=21;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // InternalN4MFParser.g:5437:3: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5437:3: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    // InternalN4MFParser.g:5438:4: {...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalN4MFParser.g:5438:112: ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    // InternalN4MFParser.g:5439:5: ( ( rule__ProjectDescription__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5445:5: ( ( rule__ProjectDescription__Group_0__0 ) )
                    // InternalN4MFParser.g:5446:6: ( rule__ProjectDescription__Group_0__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_0()); 
                    // InternalN4MFParser.g:5447:6: ( rule__ProjectDescription__Group_0__0 )
                    // InternalN4MFParser.g:5447:7: rule__ProjectDescription__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:5452:3: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5452:3: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    // InternalN4MFParser.g:5453:4: {...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalN4MFParser.g:5453:112: ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    // InternalN4MFParser.g:5454:5: ( ( rule__ProjectDescription__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5460:5: ( ( rule__ProjectDescription__Group_1__0 ) )
                    // InternalN4MFParser.g:5461:6: ( rule__ProjectDescription__Group_1__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_1()); 
                    // InternalN4MFParser.g:5462:6: ( rule__ProjectDescription__Group_1__0 )
                    // InternalN4MFParser.g:5462:7: rule__ProjectDescription__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:5467:3: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5467:3: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    // InternalN4MFParser.g:5468:4: {...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalN4MFParser.g:5468:112: ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    // InternalN4MFParser.g:5469:5: ( ( rule__ProjectDescription__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5475:5: ( ( rule__ProjectDescription__Group_2__0 ) )
                    // InternalN4MFParser.g:5476:6: ( rule__ProjectDescription__Group_2__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_2()); 
                    // InternalN4MFParser.g:5477:6: ( rule__ProjectDescription__Group_2__0 )
                    // InternalN4MFParser.g:5477:7: rule__ProjectDescription__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:5482:3: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5482:3: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    // InternalN4MFParser.g:5483:4: {...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalN4MFParser.g:5483:112: ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    // InternalN4MFParser.g:5484:5: ( ( rule__ProjectDescription__Group_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5490:5: ( ( rule__ProjectDescription__Group_3__0 ) )
                    // InternalN4MFParser.g:5491:6: ( rule__ProjectDescription__Group_3__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_3()); 
                    // InternalN4MFParser.g:5492:6: ( rule__ProjectDescription__Group_3__0 )
                    // InternalN4MFParser.g:5492:7: rule__ProjectDescription__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:5497:3: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5497:3: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    // InternalN4MFParser.g:5498:4: {...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalN4MFParser.g:5498:112: ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    // InternalN4MFParser.g:5499:5: ( ( rule__ProjectDescription__Group_4__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5505:5: ( ( rule__ProjectDescription__Group_4__0 ) )
                    // InternalN4MFParser.g:5506:6: ( rule__ProjectDescription__Group_4__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_4()); 
                    // InternalN4MFParser.g:5507:6: ( rule__ProjectDescription__Group_4__0 )
                    // InternalN4MFParser.g:5507:7: rule__ProjectDescription__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_4()); 

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:5512:3: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5512:3: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    // InternalN4MFParser.g:5513:4: {...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalN4MFParser.g:5513:112: ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    // InternalN4MFParser.g:5514:5: ( ( rule__ProjectDescription__Group_5__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5520:5: ( ( rule__ProjectDescription__Group_5__0 ) )
                    // InternalN4MFParser.g:5521:6: ( rule__ProjectDescription__Group_5__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_5()); 
                    // InternalN4MFParser.g:5522:6: ( rule__ProjectDescription__Group_5__0 )
                    // InternalN4MFParser.g:5522:7: rule__ProjectDescription__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_5()); 

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:5527:3: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) )
                    {
                    // InternalN4MFParser.g:5527:3: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) )
                    // InternalN4MFParser.g:5528:4: {...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6)");
                    }
                    // InternalN4MFParser.g:5528:112: ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) )
                    // InternalN4MFParser.g:5529:5: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5535:5: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) )
                    // InternalN4MFParser.g:5536:6: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentAssignment_6()); 
                    // InternalN4MFParser.g:5537:6: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 )
                    // InternalN4MFParser.g:5537:7: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentAssignment_6()); 

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:5542:3: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) )
                    {
                    // InternalN4MFParser.g:5542:3: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) )
                    // InternalN4MFParser.g:5543:4: {...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7)");
                    }
                    // InternalN4MFParser.g:5543:112: ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) )
                    // InternalN4MFParser.g:5544:5: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5550:5: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) )
                    // InternalN4MFParser.g:5551:6: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesAssignment_7()); 
                    // InternalN4MFParser.g:5552:6: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 )
                    // InternalN4MFParser.g:5552:7: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesAssignment_7()); 

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:5557:3: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) )
                    {
                    // InternalN4MFParser.g:5557:3: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) )
                    // InternalN4MFParser.g:5558:4: {...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8)");
                    }
                    // InternalN4MFParser.g:5558:112: ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) )
                    // InternalN4MFParser.g:5559:5: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5565:5: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) )
                    // InternalN4MFParser.g:5566:6: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesAssignment_8()); 
                    // InternalN4MFParser.g:5567:6: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 )
                    // InternalN4MFParser.g:5567:7: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesAssignment_8()); 

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:5572:3: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) )
                    {
                    // InternalN4MFParser.g:5572:3: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) )
                    // InternalN4MFParser.g:5573:4: {...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9)");
                    }
                    // InternalN4MFParser.g:5573:112: ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) )
                    // InternalN4MFParser.g:5574:5: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5580:5: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) )
                    // InternalN4MFParser.g:5581:6: ( rule__ProjectDescription__ProjectDependenciesAssignment_9 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesAssignment_9()); 
                    // InternalN4MFParser.g:5582:6: ( rule__ProjectDescription__ProjectDependenciesAssignment_9 )
                    // InternalN4MFParser.g:5582:7: rule__ProjectDescription__ProjectDependenciesAssignment_9
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ProjectDependenciesAssignment_9();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesAssignment_9()); 

                    }


                    }


                    }


                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:5587:3: ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5587:3: ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) )
                    // InternalN4MFParser.g:5588:4: {...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10)");
                    }
                    // InternalN4MFParser.g:5588:113: ( ( ( rule__ProjectDescription__Group_10__0 ) ) )
                    // InternalN4MFParser.g:5589:5: ( ( rule__ProjectDescription__Group_10__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5595:5: ( ( rule__ProjectDescription__Group_10__0 ) )
                    // InternalN4MFParser.g:5596:6: ( rule__ProjectDescription__Group_10__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_10()); 
                    // InternalN4MFParser.g:5597:6: ( rule__ProjectDescription__Group_10__0 )
                    // InternalN4MFParser.g:5597:7: rule__ProjectDescription__Group_10__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_10__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_10()); 

                    }


                    }


                    }


                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:5602:3: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) )
                    {
                    // InternalN4MFParser.g:5602:3: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) )
                    // InternalN4MFParser.g:5603:4: {...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11)");
                    }
                    // InternalN4MFParser.g:5603:113: ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) )
                    // InternalN4MFParser.g:5604:5: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5610:5: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) )
                    // InternalN4MFParser.g:5611:6: ( rule__ProjectDescription__ImplementedProjectsAssignment_11 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsAssignment_11()); 
                    // InternalN4MFParser.g:5612:6: ( rule__ProjectDescription__ImplementedProjectsAssignment_11 )
                    // InternalN4MFParser.g:5612:7: rule__ProjectDescription__ImplementedProjectsAssignment_11
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ImplementedProjectsAssignment_11();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsAssignment_11()); 

                    }


                    }


                    }


                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:5617:3: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) )
                    {
                    // InternalN4MFParser.g:5617:3: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) )
                    // InternalN4MFParser.g:5618:4: {...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12)");
                    }
                    // InternalN4MFParser.g:5618:113: ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) )
                    // InternalN4MFParser.g:5619:5: ( ( rule__ProjectDescription__InitModulesAssignment_12 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5625:5: ( ( rule__ProjectDescription__InitModulesAssignment_12 ) )
                    // InternalN4MFParser.g:5626:6: ( rule__ProjectDescription__InitModulesAssignment_12 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getInitModulesAssignment_12()); 
                    // InternalN4MFParser.g:5627:6: ( rule__ProjectDescription__InitModulesAssignment_12 )
                    // InternalN4MFParser.g:5627:7: rule__ProjectDescription__InitModulesAssignment_12
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__InitModulesAssignment_12();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getInitModulesAssignment_12()); 

                    }


                    }


                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:5632:3: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) )
                    {
                    // InternalN4MFParser.g:5632:3: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) )
                    // InternalN4MFParser.g:5633:4: {...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13)");
                    }
                    // InternalN4MFParser.g:5633:113: ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) )
                    // InternalN4MFParser.g:5634:5: ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5640:5: ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) )
                    // InternalN4MFParser.g:5641:6: ( rule__ProjectDescription__ExecModuleAssignment_13 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExecModuleAssignment_13()); 
                    // InternalN4MFParser.g:5642:6: ( rule__ProjectDescription__ExecModuleAssignment_13 )
                    // InternalN4MFParser.g:5642:7: rule__ProjectDescription__ExecModuleAssignment_13
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ExecModuleAssignment_13();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getExecModuleAssignment_13()); 

                    }


                    }


                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:5647:3: ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5647:3: ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) )
                    // InternalN4MFParser.g:5648:4: {...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14)");
                    }
                    // InternalN4MFParser.g:5648:113: ( ( ( rule__ProjectDescription__Group_14__0 ) ) )
                    // InternalN4MFParser.g:5649:5: ( ( rule__ProjectDescription__Group_14__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5655:5: ( ( rule__ProjectDescription__Group_14__0 ) )
                    // InternalN4MFParser.g:5656:6: ( rule__ProjectDescription__Group_14__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_14()); 
                    // InternalN4MFParser.g:5657:6: ( rule__ProjectDescription__Group_14__0 )
                    // InternalN4MFParser.g:5657:7: rule__ProjectDescription__Group_14__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_14__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_14()); 

                    }


                    }


                    }


                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:5662:3: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5662:3: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    // InternalN4MFParser.g:5663:4: {...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15)");
                    }
                    // InternalN4MFParser.g:5663:113: ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    // InternalN4MFParser.g:5664:5: ( ( rule__ProjectDescription__Group_15__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5670:5: ( ( rule__ProjectDescription__Group_15__0 ) )
                    // InternalN4MFParser.g:5671:6: ( rule__ProjectDescription__Group_15__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_15()); 
                    // InternalN4MFParser.g:5672:6: ( rule__ProjectDescription__Group_15__0 )
                    // InternalN4MFParser.g:5672:7: rule__ProjectDescription__Group_15__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_15__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_15()); 

                    }


                    }


                    }


                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:5677:3: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5677:3: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    // InternalN4MFParser.g:5678:4: {...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16)");
                    }
                    // InternalN4MFParser.g:5678:113: ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    // InternalN4MFParser.g:5679:5: ( ( rule__ProjectDescription__Group_16__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5685:5: ( ( rule__ProjectDescription__Group_16__0 ) )
                    // InternalN4MFParser.g:5686:6: ( rule__ProjectDescription__Group_16__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_16()); 
                    // InternalN4MFParser.g:5687:6: ( rule__ProjectDescription__Group_16__0 )
                    // InternalN4MFParser.g:5687:7: rule__ProjectDescription__Group_16__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_16__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_16()); 

                    }


                    }


                    }


                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:5692:3: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5692:3: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    // InternalN4MFParser.g:5693:4: {...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17)");
                    }
                    // InternalN4MFParser.g:5693:113: ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    // InternalN4MFParser.g:5694:5: ( ( rule__ProjectDescription__Group_17__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5700:5: ( ( rule__ProjectDescription__Group_17__0 ) )
                    // InternalN4MFParser.g:5701:6: ( rule__ProjectDescription__Group_17__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_17()); 
                    // InternalN4MFParser.g:5702:6: ( rule__ProjectDescription__Group_17__0 )
                    // InternalN4MFParser.g:5702:7: rule__ProjectDescription__Group_17__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_17__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_17()); 

                    }


                    }


                    }


                    }
                    break;
                case 19 :
                    // InternalN4MFParser.g:5707:3: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5707:3: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    // InternalN4MFParser.g:5708:4: {...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18)");
                    }
                    // InternalN4MFParser.g:5708:113: ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    // InternalN4MFParser.g:5709:5: ( ( rule__ProjectDescription__Group_18__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5715:5: ( ( rule__ProjectDescription__Group_18__0 ) )
                    // InternalN4MFParser.g:5716:6: ( rule__ProjectDescription__Group_18__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_18()); 
                    // InternalN4MFParser.g:5717:6: ( rule__ProjectDescription__Group_18__0 )
                    // InternalN4MFParser.g:5717:7: rule__ProjectDescription__Group_18__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_18__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_18()); 

                    }


                    }


                    }


                    }
                    break;
                case 20 :
                    // InternalN4MFParser.g:5722:3: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) )
                    {
                    // InternalN4MFParser.g:5722:3: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) )
                    // InternalN4MFParser.g:5723:4: {...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19)");
                    }
                    // InternalN4MFParser.g:5723:113: ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) )
                    // InternalN4MFParser.g:5724:5: ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5730:5: ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) )
                    // InternalN4MFParser.g:5731:6: ( rule__ProjectDescription__TestedProjectsAssignment_19 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getTestedProjectsAssignment_19()); 
                    // InternalN4MFParser.g:5732:6: ( rule__ProjectDescription__TestedProjectsAssignment_19 )
                    // InternalN4MFParser.g:5732:7: rule__ProjectDescription__TestedProjectsAssignment_19
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__TestedProjectsAssignment_19();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getTestedProjectsAssignment_19()); 

                    }


                    }


                    }


                    }
                    break;
                case 21 :
                    // InternalN4MFParser.g:5737:3: ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:5737:3: ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) )
                    // InternalN4MFParser.g:5738:4: {...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20)");
                    }
                    // InternalN4MFParser.g:5738:113: ( ( ( rule__ProjectDescription__Group_20__0 ) ) )
                    // InternalN4MFParser.g:5739:5: ( ( rule__ProjectDescription__Group_20__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20);
                    				

                    					selected = true;
                    				
                    // InternalN4MFParser.g:5745:5: ( ( rule__ProjectDescription__Group_20__0 ) )
                    // InternalN4MFParser.g:5746:6: ( rule__ProjectDescription__Group_20__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_20()); 
                    // InternalN4MFParser.g:5747:6: ( rule__ProjectDescription__Group_20__0 )
                    // InternalN4MFParser.g:5747:7: rule__ProjectDescription__Group_20__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_20__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_20()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__Impl"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__0"
    // InternalN4MFParser.g:5760:1: rule__ProjectDescription__UnorderedGroup__0 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5764:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? )
            // InternalN4MFParser.g:5765:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5766:2: ( rule__ProjectDescription__UnorderedGroup__1 )?
            int alt39=2;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // InternalN4MFParser.g:5766:2: rule__ProjectDescription__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__0"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__1"
    // InternalN4MFParser.g:5772:1: rule__ProjectDescription__UnorderedGroup__1 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5776:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? )
            // InternalN4MFParser.g:5777:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5778:2: ( rule__ProjectDescription__UnorderedGroup__2 )?
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // InternalN4MFParser.g:5778:2: rule__ProjectDescription__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__2();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__1"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__2"
    // InternalN4MFParser.g:5784:1: rule__ProjectDescription__UnorderedGroup__2 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5788:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? )
            // InternalN4MFParser.g:5789:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5790:2: ( rule__ProjectDescription__UnorderedGroup__3 )?
            int alt41=2;
            alt41 = dfa41.predict(input);
            switch (alt41) {
                case 1 :
                    // InternalN4MFParser.g:5790:2: rule__ProjectDescription__UnorderedGroup__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__3();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__2"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__3"
    // InternalN4MFParser.g:5796:1: rule__ProjectDescription__UnorderedGroup__3 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5800:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? )
            // InternalN4MFParser.g:5801:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5802:2: ( rule__ProjectDescription__UnorderedGroup__4 )?
            int alt42=2;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // InternalN4MFParser.g:5802:2: rule__ProjectDescription__UnorderedGroup__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__4();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__3"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__4"
    // InternalN4MFParser.g:5808:1: rule__ProjectDescription__UnorderedGroup__4 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5812:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? )
            // InternalN4MFParser.g:5813:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5814:2: ( rule__ProjectDescription__UnorderedGroup__5 )?
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // InternalN4MFParser.g:5814:2: rule__ProjectDescription__UnorderedGroup__5
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__5();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__4"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__5"
    // InternalN4MFParser.g:5820:1: rule__ProjectDescription__UnorderedGroup__5 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5824:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? )
            // InternalN4MFParser.g:5825:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5826:2: ( rule__ProjectDescription__UnorderedGroup__6 )?
            int alt44=2;
            alt44 = dfa44.predict(input);
            switch (alt44) {
                case 1 :
                    // InternalN4MFParser.g:5826:2: rule__ProjectDescription__UnorderedGroup__6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__6();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__5"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__6"
    // InternalN4MFParser.g:5832:1: rule__ProjectDescription__UnorderedGroup__6 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5836:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? )
            // InternalN4MFParser.g:5837:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5838:2: ( rule__ProjectDescription__UnorderedGroup__7 )?
            int alt45=2;
            alt45 = dfa45.predict(input);
            switch (alt45) {
                case 1 :
                    // InternalN4MFParser.g:5838:2: rule__ProjectDescription__UnorderedGroup__7
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__7();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__6"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__7"
    // InternalN4MFParser.g:5844:1: rule__ProjectDescription__UnorderedGroup__7 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5848:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? )
            // InternalN4MFParser.g:5849:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5850:2: ( rule__ProjectDescription__UnorderedGroup__8 )?
            int alt46=2;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // InternalN4MFParser.g:5850:2: rule__ProjectDescription__UnorderedGroup__8
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__8();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__7"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__8"
    // InternalN4MFParser.g:5856:1: rule__ProjectDescription__UnorderedGroup__8 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5860:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? )
            // InternalN4MFParser.g:5861:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5862:2: ( rule__ProjectDescription__UnorderedGroup__9 )?
            int alt47=2;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // InternalN4MFParser.g:5862:2: rule__ProjectDescription__UnorderedGroup__9
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__9();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__8"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__9"
    // InternalN4MFParser.g:5868:1: rule__ProjectDescription__UnorderedGroup__9 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5872:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? )
            // InternalN4MFParser.g:5873:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5874:2: ( rule__ProjectDescription__UnorderedGroup__10 )?
            int alt48=2;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // InternalN4MFParser.g:5874:2: rule__ProjectDescription__UnorderedGroup__10
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__10();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__9"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__10"
    // InternalN4MFParser.g:5880:1: rule__ProjectDescription__UnorderedGroup__10 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5884:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? )
            // InternalN4MFParser.g:5885:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5886:2: ( rule__ProjectDescription__UnorderedGroup__11 )?
            int alt49=2;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // InternalN4MFParser.g:5886:2: rule__ProjectDescription__UnorderedGroup__11
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__11();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__10"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__11"
    // InternalN4MFParser.g:5892:1: rule__ProjectDescription__UnorderedGroup__11 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5896:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? )
            // InternalN4MFParser.g:5897:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5898:2: ( rule__ProjectDescription__UnorderedGroup__12 )?
            int alt50=2;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1 :
                    // InternalN4MFParser.g:5898:2: rule__ProjectDescription__UnorderedGroup__12
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__12();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__11"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__12"
    // InternalN4MFParser.g:5904:1: rule__ProjectDescription__UnorderedGroup__12 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5908:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? )
            // InternalN4MFParser.g:5909:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5910:2: ( rule__ProjectDescription__UnorderedGroup__13 )?
            int alt51=2;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // InternalN4MFParser.g:5910:2: rule__ProjectDescription__UnorderedGroup__13
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__13();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__12"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__13"
    // InternalN4MFParser.g:5916:1: rule__ProjectDescription__UnorderedGroup__13 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5920:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? )
            // InternalN4MFParser.g:5921:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5922:2: ( rule__ProjectDescription__UnorderedGroup__14 )?
            int alt52=2;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // InternalN4MFParser.g:5922:2: rule__ProjectDescription__UnorderedGroup__14
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__14();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__13"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__14"
    // InternalN4MFParser.g:5928:1: rule__ProjectDescription__UnorderedGroup__14 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5932:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? )
            // InternalN4MFParser.g:5933:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5934:2: ( rule__ProjectDescription__UnorderedGroup__15 )?
            int alt53=2;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // InternalN4MFParser.g:5934:2: rule__ProjectDescription__UnorderedGroup__15
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__15();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__14"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__15"
    // InternalN4MFParser.g:5940:1: rule__ProjectDescription__UnorderedGroup__15 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5944:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? )
            // InternalN4MFParser.g:5945:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5946:2: ( rule__ProjectDescription__UnorderedGroup__16 )?
            int alt54=2;
            alt54 = dfa54.predict(input);
            switch (alt54) {
                case 1 :
                    // InternalN4MFParser.g:5946:2: rule__ProjectDescription__UnorderedGroup__16
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__16();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__15"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__16"
    // InternalN4MFParser.g:5952:1: rule__ProjectDescription__UnorderedGroup__16 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__16() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5956:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? )
            // InternalN4MFParser.g:5957:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5958:2: ( rule__ProjectDescription__UnorderedGroup__17 )?
            int alt55=2;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // InternalN4MFParser.g:5958:2: rule__ProjectDescription__UnorderedGroup__17
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__17();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__16"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__17"
    // InternalN4MFParser.g:5964:1: rule__ProjectDescription__UnorderedGroup__17 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__17() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5968:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? )
            // InternalN4MFParser.g:5969:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5970:2: ( rule__ProjectDescription__UnorderedGroup__18 )?
            int alt56=2;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // InternalN4MFParser.g:5970:2: rule__ProjectDescription__UnorderedGroup__18
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__18();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__17"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__18"
    // InternalN4MFParser.g:5976:1: rule__ProjectDescription__UnorderedGroup__18 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__18() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5980:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? )
            // InternalN4MFParser.g:5981:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5982:2: ( rule__ProjectDescription__UnorderedGroup__19 )?
            int alt57=2;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // InternalN4MFParser.g:5982:2: rule__ProjectDescription__UnorderedGroup__19
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__19();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__18"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__19"
    // InternalN4MFParser.g:5988:1: rule__ProjectDescription__UnorderedGroup__19 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__19() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:5992:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? )
            // InternalN4MFParser.g:5993:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:5994:2: ( rule__ProjectDescription__UnorderedGroup__20 )?
            int alt58=2;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // InternalN4MFParser.g:5994:2: rule__ProjectDescription__UnorderedGroup__20
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__20();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__19"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__20"
    // InternalN4MFParser.g:6000:1: rule__ProjectDescription__UnorderedGroup__20 : rule__ProjectDescription__UnorderedGroup__Impl ;
    public final void rule__ProjectDescription__UnorderedGroup__20() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6004:1: ( rule__ProjectDescription__UnorderedGroup__Impl )
            // InternalN4MFParser.g:6005:2: rule__ProjectDescription__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;


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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__20"


    // $ANTLR start "rule__ProjectDescription__ProjectIdAssignment_0_2"
    // InternalN4MFParser.g:6012:1: rule__ProjectDescription__ProjectIdAssignment_0_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ProjectIdAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6016:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6017:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6017:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6018:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_0_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ProjectIdAssignment_0_2"


    // $ANTLR start "rule__ProjectDescription__ProjectTypeAssignment_1_2"
    // InternalN4MFParser.g:6027:1: rule__ProjectDescription__ProjectTypeAssignment_1_2 : ( ruleProjectType ) ;
    public final void rule__ProjectDescription__ProjectTypeAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6031:1: ( ( ruleProjectType ) )
            // InternalN4MFParser.g:6032:2: ( ruleProjectType )
            {
            // InternalN4MFParser.g:6032:2: ( ruleProjectType )
            // InternalN4MFParser.g:6033:3: ruleProjectType
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectType();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ProjectTypeAssignment_1_2"


    // $ANTLR start "rule__ProjectDescription__ProjectVersionAssignment_2_2"
    // InternalN4MFParser.g:6042:1: rule__ProjectDescription__ProjectVersionAssignment_2_2 : ( ruleDeclaredVersion ) ;
    public final void rule__ProjectDescription__ProjectVersionAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6046:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:6047:2: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:6047:2: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:6048:3: ruleDeclaredVersion
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_2_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ProjectVersionAssignment_2_2"


    // $ANTLR start "rule__ProjectDescription__DeclaredVendorIdAssignment_3_2"
    // InternalN4MFParser.g:6057:1: rule__ProjectDescription__DeclaredVendorIdAssignment_3_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__DeclaredVendorIdAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6061:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6062:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6062:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6063:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_3_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__DeclaredVendorIdAssignment_3_2"


    // $ANTLR start "rule__ProjectDescription__VendorNameAssignment_4_2"
    // InternalN4MFParser.g:6072:1: rule__ProjectDescription__VendorNameAssignment_4_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__VendorNameAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6076:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6077:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6077:2: ( RULE_STRING )
            // InternalN4MFParser.g:6078:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_4_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_4_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__VendorNameAssignment_4_2"


    // $ANTLR start "rule__ProjectDescription__MainModuleAssignment_5_2"
    // InternalN4MFParser.g:6087:1: rule__ProjectDescription__MainModuleAssignment_5_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__MainModuleAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6091:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6092:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6092:2: ( RULE_STRING )
            // InternalN4MFParser.g:6093:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_5_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_5_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__MainModuleAssignment_5_2"


    // $ANTLR start "rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6"
    // InternalN4MFParser.g:6102:1: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 : ( ruleExtendedRuntimeEnvironment ) ;
    public final void rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6106:1: ( ( ruleExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:6107:2: ( ruleExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:6107:2: ( ruleExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:6108:3: ruleExtendedRuntimeEnvironment
            {
             before(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleExtendedRuntimeEnvironment();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6"


    // $ANTLR start "rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7"
    // InternalN4MFParser.g:6117:1: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 : ( ruleProvidedRuntimeLibraries ) ;
    public final void rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6121:1: ( ( ruleProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:6122:2: ( ruleProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:6122:2: ( ruleProvidedRuntimeLibraries )
            // InternalN4MFParser.g:6123:3: ruleProvidedRuntimeLibraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleProvidedRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_7_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7"


    // $ANTLR start "rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8"
    // InternalN4MFParser.g:6132:1: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 : ( ruleRequiredRuntimeLibraries ) ;
    public final void rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6136:1: ( ( ruleRequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:6137:2: ( ruleRequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:6137:2: ( ruleRequiredRuntimeLibraries )
            // InternalN4MFParser.g:6138:3: ruleRequiredRuntimeLibraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleRequiredRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_8_0()); 

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
    // $ANTLR end "rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8"


    // $ANTLR start "rule__ProjectDescription__ProjectDependenciesAssignment_9"
    // InternalN4MFParser.g:6147:1: rule__ProjectDescription__ProjectDependenciesAssignment_9 : ( ruleProjectDependencies ) ;
    public final void rule__ProjectDescription__ProjectDependenciesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6151:1: ( ( ruleProjectDependencies ) )
            // InternalN4MFParser.g:6152:2: ( ruleProjectDependencies )
            {
            // InternalN4MFParser.g:6152:2: ( ruleProjectDependencies )
            // InternalN4MFParser.g:6153:3: ruleProjectDependencies
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_9_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectDependencies();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_9_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ProjectDependenciesAssignment_9"


    // $ANTLR start "rule__ProjectDescription__ImplementationIdAssignment_10_2"
    // InternalN4MFParser.g:6162:1: rule__ProjectDescription__ImplementationIdAssignment_10_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ImplementationIdAssignment_10_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6166:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6167:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6167:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6168:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_10_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_10_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ImplementationIdAssignment_10_2"


    // $ANTLR start "rule__ProjectDescription__ImplementedProjectsAssignment_11"
    // InternalN4MFParser.g:6177:1: rule__ProjectDescription__ImplementedProjectsAssignment_11 : ( ruleImplementedProjects ) ;
    public final void rule__ProjectDescription__ImplementedProjectsAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6181:1: ( ( ruleImplementedProjects ) )
            // InternalN4MFParser.g:6182:2: ( ruleImplementedProjects )
            {
            // InternalN4MFParser.g:6182:2: ( ruleImplementedProjects )
            // InternalN4MFParser.g:6183:3: ruleImplementedProjects
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_11_0()); 
            pushFollow(FOLLOW_2);
            ruleImplementedProjects();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_11_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ImplementedProjectsAssignment_11"


    // $ANTLR start "rule__ProjectDescription__InitModulesAssignment_12"
    // InternalN4MFParser.g:6192:1: rule__ProjectDescription__InitModulesAssignment_12 : ( ruleInitModules ) ;
    public final void rule__ProjectDescription__InitModulesAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6196:1: ( ( ruleInitModules ) )
            // InternalN4MFParser.g:6197:2: ( ruleInitModules )
            {
            // InternalN4MFParser.g:6197:2: ( ruleInitModules )
            // InternalN4MFParser.g:6198:3: ruleInitModules
            {
             before(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_12_0()); 
            pushFollow(FOLLOW_2);
            ruleInitModules();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_12_0()); 

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
    // $ANTLR end "rule__ProjectDescription__InitModulesAssignment_12"


    // $ANTLR start "rule__ProjectDescription__ExecModuleAssignment_13"
    // InternalN4MFParser.g:6207:1: rule__ProjectDescription__ExecModuleAssignment_13 : ( ruleExecModule ) ;
    public final void rule__ProjectDescription__ExecModuleAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6211:1: ( ( ruleExecModule ) )
            // InternalN4MFParser.g:6212:2: ( ruleExecModule )
            {
            // InternalN4MFParser.g:6212:2: ( ruleExecModule )
            // InternalN4MFParser.g:6213:3: ruleExecModule
            {
             before(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_13_0()); 
            pushFollow(FOLLOW_2);
            ruleExecModule();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_13_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ExecModuleAssignment_13"


    // $ANTLR start "rule__ProjectDescription__OutputPathAssignment_14_2"
    // InternalN4MFParser.g:6222:1: rule__ProjectDescription__OutputPathAssignment_14_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__OutputPathAssignment_14_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6226:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6227:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6227:2: ( RULE_STRING )
            // InternalN4MFParser.g:6228:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_14_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_14_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__OutputPathAssignment_14_2"


    // $ANTLR start "rule__ProjectDescription__LibraryPathsAssignment_15_2"
    // InternalN4MFParser.g:6237:1: rule__ProjectDescription__LibraryPathsAssignment_15_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_15_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6241:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6242:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6242:2: ( RULE_STRING )
            // InternalN4MFParser.g:6243:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_15_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_15_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__LibraryPathsAssignment_15_2"


    // $ANTLR start "rule__ProjectDescription__LibraryPathsAssignment_15_3_1"
    // InternalN4MFParser.g:6252:1: rule__ProjectDescription__LibraryPathsAssignment_15_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_15_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6256:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6257:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6257:2: ( RULE_STRING )
            // InternalN4MFParser.g:6258:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_15_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_15_3_1_0()); 

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
    // $ANTLR end "rule__ProjectDescription__LibraryPathsAssignment_15_3_1"


    // $ANTLR start "rule__ProjectDescription__ResourcePathsAssignment_16_2"
    // InternalN4MFParser.g:6267:1: rule__ProjectDescription__ResourcePathsAssignment_16_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_16_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6271:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6272:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6272:2: ( RULE_STRING )
            // InternalN4MFParser.g:6273:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_16_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_16_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ResourcePathsAssignment_16_2"


    // $ANTLR start "rule__ProjectDescription__ResourcePathsAssignment_16_3_1"
    // InternalN4MFParser.g:6282:1: rule__ProjectDescription__ResourcePathsAssignment_16_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_16_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6286:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6287:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6287:2: ( RULE_STRING )
            // InternalN4MFParser.g:6288:3: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_16_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_16_3_1_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ResourcePathsAssignment_16_3_1"


    // $ANTLR start "rule__ProjectDescription__SourceFragmentAssignment_17_2"
    // InternalN4MFParser.g:6297:1: rule__ProjectDescription__SourceFragmentAssignment_17_2 : ( ruleSourceFragment ) ;
    public final void rule__ProjectDescription__SourceFragmentAssignment_17_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6301:1: ( ( ruleSourceFragment ) )
            // InternalN4MFParser.g:6302:2: ( ruleSourceFragment )
            {
            // InternalN4MFParser.g:6302:2: ( ruleSourceFragment )
            // InternalN4MFParser.g:6303:3: ruleSourceFragment
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_17_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSourceFragment();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_17_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__SourceFragmentAssignment_17_2"


    // $ANTLR start "rule__ProjectDescription__ModuleFiltersAssignment_18_2"
    // InternalN4MFParser.g:6312:1: rule__ProjectDescription__ModuleFiltersAssignment_18_2 : ( ruleModuleFilter ) ;
    public final void rule__ProjectDescription__ModuleFiltersAssignment_18_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6316:1: ( ( ruleModuleFilter ) )
            // InternalN4MFParser.g:6317:2: ( ruleModuleFilter )
            {
            // InternalN4MFParser.g:6317:2: ( ruleModuleFilter )
            // InternalN4MFParser.g:6318:3: ruleModuleFilter
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_18_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleFilter();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_18_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ModuleFiltersAssignment_18_2"


    // $ANTLR start "rule__ProjectDescription__TestedProjectsAssignment_19"
    // InternalN4MFParser.g:6327:1: rule__ProjectDescription__TestedProjectsAssignment_19 : ( ruleTestedProjects ) ;
    public final void rule__ProjectDescription__TestedProjectsAssignment_19() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6331:1: ( ( ruleTestedProjects ) )
            // InternalN4MFParser.g:6332:2: ( ruleTestedProjects )
            {
            // InternalN4MFParser.g:6332:2: ( ruleTestedProjects )
            // InternalN4MFParser.g:6333:3: ruleTestedProjects
            {
             before(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_19_0()); 
            pushFollow(FOLLOW_2);
            ruleTestedProjects();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_19_0()); 

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
    // $ANTLR end "rule__ProjectDescription__TestedProjectsAssignment_19"


    // $ANTLR start "rule__ProjectDescription__ModuleLoaderAssignment_20_2"
    // InternalN4MFParser.g:6342:1: rule__ProjectDescription__ModuleLoaderAssignment_20_2 : ( ruleModuleLoader ) ;
    public final void rule__ProjectDescription__ModuleLoaderAssignment_20_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6346:1: ( ( ruleModuleLoader ) )
            // InternalN4MFParser.g:6347:2: ( ruleModuleLoader )
            {
            // InternalN4MFParser.g:6347:2: ( ruleModuleLoader )
            // InternalN4MFParser.g:6348:3: ruleModuleLoader
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_20_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleLoader();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_20_2_0()); 

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
    // $ANTLR end "rule__ProjectDescription__ModuleLoaderAssignment_20_2"


    // $ANTLR start "rule__ExecModule__ExecModuleAssignment_3"
    // InternalN4MFParser.g:6357:1: rule__ExecModule__ExecModuleAssignment_3 : ( ruleBootstrapModule ) ;
    public final void rule__ExecModule__ExecModuleAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6361:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:6362:2: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:6362:2: ( ruleBootstrapModule )
            // InternalN4MFParser.g:6363:3: ruleBootstrapModule
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleBootstrapModuleParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleBootstrapModule();

            state._fsp--;

             after(grammarAccess.getExecModuleAccess().getExecModuleBootstrapModuleParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__ExecModule__ExecModuleAssignment_3"


    // $ANTLR start "rule__TestedProjects__TestedProjectsAssignment_3_0"
    // InternalN4MFParser.g:6372:1: rule__TestedProjects__TestedProjectsAssignment_3_0 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6376:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:6377:2: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:6377:2: ( ruleTestedProject )
            // InternalN4MFParser.g:6378:3: ruleTestedProject
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleTestedProject();

            state._fsp--;

             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__TestedProjects__TestedProjectsAssignment_3_0"


    // $ANTLR start "rule__TestedProjects__TestedProjectsAssignment_3_1_1"
    // InternalN4MFParser.g:6387:1: rule__TestedProjects__TestedProjectsAssignment_3_1_1 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6391:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:6392:2: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:6392:2: ( ruleTestedProject )
            // InternalN4MFParser.g:6393:3: ruleTestedProject
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleTestedProject();

            state._fsp--;

             after(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__TestedProjects__TestedProjectsAssignment_3_1_1"


    // $ANTLR start "rule__InitModules__InitModulesAssignment_3_0"
    // InternalN4MFParser.g:6402:1: rule__InitModules__InitModulesAssignment_3_0 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6406:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:6407:2: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:6407:2: ( ruleBootstrapModule )
            // InternalN4MFParser.g:6408:3: ruleBootstrapModule
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleBootstrapModule();

            state._fsp--;

             after(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__InitModules__InitModulesAssignment_3_0"


    // $ANTLR start "rule__InitModules__InitModulesAssignment_3_1_1"
    // InternalN4MFParser.g:6417:1: rule__InitModules__InitModulesAssignment_3_1_1 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6421:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:6422:2: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:6422:2: ( ruleBootstrapModule )
            // InternalN4MFParser.g:6423:3: ruleBootstrapModule
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBootstrapModule();

            state._fsp--;

             after(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__InitModules__InitModulesAssignment_3_1_1"


    // $ANTLR start "rule__ImplementedProjects__ImplementedProjectsAssignment_3_0"
    // InternalN4MFParser.g:6432:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6436:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:6437:2: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:6437:2: ( ruleProjectReference )
            // InternalN4MFParser.g:6438:3: ruleProjectReference
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectReference();

            state._fsp--;

             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__ImplementedProjects__ImplementedProjectsAssignment_3_0"


    // $ANTLR start "rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1"
    // InternalN4MFParser.g:6447:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6451:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:6452:2: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:6452:2: ( ruleProjectReference )
            // InternalN4MFParser.g:6453:3: ruleProjectReference
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectReference();

            state._fsp--;

             after(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1"


    // $ANTLR start "rule__ProjectDependencies__ProjectDependenciesAssignment_3_0"
    // InternalN4MFParser.g:6462:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6466:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:6467:2: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:6467:2: ( ruleProjectDependency )
            // InternalN4MFParser.g:6468:3: ruleProjectDependency
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectDependency();

            state._fsp--;

             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__ProjectDependencies__ProjectDependenciesAssignment_3_0"


    // $ANTLR start "rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1"
    // InternalN4MFParser.g:6477:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6481:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:6482:2: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:6482:2: ( ruleProjectDependency )
            // InternalN4MFParser.g:6483:3: ruleProjectDependency
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectDependency();

            state._fsp--;

             after(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0"
    // InternalN4MFParser.g:6492:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6496:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:6497:2: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:6497:2: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:6498:3: ruleProvidedRuntimeLibraryDependency
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleProvidedRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0"


    // $ANTLR start "rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1"
    // InternalN4MFParser.g:6507:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6511:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:6512:2: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:6512:2: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:6513:3: ruleProvidedRuntimeLibraryDependency
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleProvidedRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1"


    // $ANTLR start "rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0"
    // InternalN4MFParser.g:6522:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6526:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:6527:2: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:6527:2: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:6528:3: ruleRequiredRuntimeLibraryDependency
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleRequiredRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0"


    // $ANTLR start "rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1"
    // InternalN4MFParser.g:6537:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6541:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:6542:2: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:6542:2: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:6543:3: ruleRequiredRuntimeLibraryDependency
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleRequiredRuntimeLibraryDependency();

            state._fsp--;

             after(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1"


    // $ANTLR start "rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3"
    // InternalN4MFParser.g:6552:1: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 : ( ruleProjectReference ) ;
    public final void rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6556:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:6557:2: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:6557:2: ( ruleProjectReference )
            // InternalN4MFParser.g:6558:3: ruleProjectReference
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentProjectReferenceParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectReference();

            state._fsp--;

             after(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentProjectReferenceParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3"


    // $ANTLR start "rule__DeclaredVersion__MajorAssignment_0"
    // InternalN4MFParser.g:6567:1: rule__DeclaredVersion__MajorAssignment_0 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MajorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6571:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:6572:2: ( RULE_INT )
            {
            // InternalN4MFParser.g:6572:2: ( RULE_INT )
            // InternalN4MFParser.g:6573:3: RULE_INT
            {
             before(grammarAccess.getDeclaredVersionAccess().getMajorINTTerminalRuleCall_0_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getMajorINTTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__MajorAssignment_0"


    // $ANTLR start "rule__DeclaredVersion__MinorAssignment_1_1"
    // InternalN4MFParser.g:6582:1: rule__DeclaredVersion__MinorAssignment_1_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MinorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6586:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:6587:2: ( RULE_INT )
            {
            // InternalN4MFParser.g:6587:2: ( RULE_INT )
            // InternalN4MFParser.g:6588:3: RULE_INT
            {
             before(grammarAccess.getDeclaredVersionAccess().getMinorINTTerminalRuleCall_1_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getMinorINTTerminalRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__MinorAssignment_1_1"


    // $ANTLR start "rule__DeclaredVersion__MicroAssignment_1_2_1"
    // InternalN4MFParser.g:6597:1: rule__DeclaredVersion__MicroAssignment_1_2_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MicroAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6601:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:6602:2: ( RULE_INT )
            {
            // InternalN4MFParser.g:6602:2: ( RULE_INT )
            // InternalN4MFParser.g:6603:3: RULE_INT
            {
             before(grammarAccess.getDeclaredVersionAccess().getMicroINTTerminalRuleCall_1_2_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDeclaredVersionAccess().getMicroINTTerminalRuleCall_1_2_1_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__MicroAssignment_1_2_1"


    // $ANTLR start "rule__DeclaredVersion__QualifierAssignment_2_1"
    // InternalN4MFParser.g:6612:1: rule__DeclaredVersion__QualifierAssignment_2_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__DeclaredVersion__QualifierAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6616:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6617:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6617:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6618:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getDeclaredVersionAccess().getQualifierN4mfIdentifierParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getDeclaredVersionAccess().getQualifierN4mfIdentifierParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__DeclaredVersion__QualifierAssignment_2_1"


    // $ANTLR start "rule__SourceFragment__SourceFragmentTypeAssignment_0"
    // InternalN4MFParser.g:6627:1: rule__SourceFragment__SourceFragmentTypeAssignment_0 : ( ruleSourceFragmentType ) ;
    public final void rule__SourceFragment__SourceFragmentTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6631:1: ( ( ruleSourceFragmentType ) )
            // InternalN4MFParser.g:6632:2: ( ruleSourceFragmentType )
            {
            // InternalN4MFParser.g:6632:2: ( ruleSourceFragmentType )
            // InternalN4MFParser.g:6633:3: ruleSourceFragmentType
            {
             before(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeSourceFragmentTypeEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleSourceFragmentType();

            state._fsp--;

             after(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeSourceFragmentTypeEnumRuleCall_0_0()); 

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
    // $ANTLR end "rule__SourceFragment__SourceFragmentTypeAssignment_0"


    // $ANTLR start "rule__SourceFragment__PathsAssignment_2"
    // InternalN4MFParser.g:6642:1: rule__SourceFragment__PathsAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6646:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6647:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6647:2: ( RULE_STRING )
            // InternalN4MFParser.g:6648:3: RULE_STRING
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__SourceFragment__PathsAssignment_2"


    // $ANTLR start "rule__SourceFragment__PathsAssignment_3_1"
    // InternalN4MFParser.g:6657:1: rule__SourceFragment__PathsAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6661:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6662:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6662:2: ( RULE_STRING )
            // InternalN4MFParser.g:6663:3: RULE_STRING
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__SourceFragment__PathsAssignment_3_1"


    // $ANTLR start "rule__ModuleFilter__ModuleFilterTypeAssignment_0"
    // InternalN4MFParser.g:6672:1: rule__ModuleFilter__ModuleFilterTypeAssignment_0 : ( ruleModuleFilterType ) ;
    public final void rule__ModuleFilter__ModuleFilterTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6676:1: ( ( ruleModuleFilterType ) )
            // InternalN4MFParser.g:6677:2: ( ruleModuleFilterType )
            {
            // InternalN4MFParser.g:6677:2: ( ruleModuleFilterType )
            // InternalN4MFParser.g:6678:3: ruleModuleFilterType
            {
             before(grammarAccess.getModuleFilterAccess().getModuleFilterTypeModuleFilterTypeEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleFilterType();

            state._fsp--;

             after(grammarAccess.getModuleFilterAccess().getModuleFilterTypeModuleFilterTypeEnumRuleCall_0_0()); 

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
    // $ANTLR end "rule__ModuleFilter__ModuleFilterTypeAssignment_0"


    // $ANTLR start "rule__ModuleFilter__ModuleSpecifiersAssignment_2"
    // InternalN4MFParser.g:6687:1: rule__ModuleFilter__ModuleSpecifiersAssignment_2 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6691:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:6692:2: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:6692:2: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:6693:3: ruleModuleFilterSpecifier
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleFilterSpecifier();

            state._fsp--;

             after(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__ModuleFilter__ModuleSpecifiersAssignment_2"


    // $ANTLR start "rule__ModuleFilter__ModuleSpecifiersAssignment_3_1"
    // InternalN4MFParser.g:6702:1: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6706:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:6707:2: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:6707:2: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:6708:3: ruleModuleFilterSpecifier
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleFilterSpecifier();

            state._fsp--;

             after(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__ModuleFilter__ModuleSpecifiersAssignment_3_1"


    // $ANTLR start "rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0"
    // InternalN4MFParser.g:6717:1: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6721:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6722:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6722:2: ( RULE_STRING )
            // InternalN4MFParser.g:6723:3: RULE_STRING
            {
             before(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0"


    // $ANTLR start "rule__BootstrapModule__SourcePathAssignment_1_1"
    // InternalN4MFParser.g:6732:1: rule__BootstrapModule__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6736:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6737:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6737:2: ( RULE_STRING )
            // InternalN4MFParser.g:6738:3: RULE_STRING
            {
             before(grammarAccess.getBootstrapModuleAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getBootstrapModuleAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__BootstrapModule__SourcePathAssignment_1_1"


    // $ANTLR start "rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0"
    // InternalN4MFParser.g:6747:1: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6751:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6752:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6752:2: ( RULE_STRING )
            // InternalN4MFParser.g:6753:3: RULE_STRING
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0"


    // $ANTLR start "rule__ModuleFilterSpecifier__SourcePathAssignment_1_1"
    // InternalN4MFParser.g:6762:1: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6766:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:6767:2: ( RULE_STRING )
            {
            // InternalN4MFParser.g:6767:2: ( RULE_STRING )
            // InternalN4MFParser.g:6768:3: RULE_STRING
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__ModuleFilterSpecifier__SourcePathAssignment_1_1"


    // $ANTLR start "rule__ProvidedRuntimeLibraryDependency__ProjectAssignment"
    // InternalN4MFParser.g:6777:1: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProvidedRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6781:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:6782:2: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:6782:2: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:6783:3: ruleSimpleProjectDescription
            {
             before(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 

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
    // $ANTLR end "rule__ProvidedRuntimeLibraryDependency__ProjectAssignment"


    // $ANTLR start "rule__RequiredRuntimeLibraryDependency__ProjectAssignment"
    // InternalN4MFParser.g:6792:1: rule__RequiredRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__RequiredRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6796:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:6797:2: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:6797:2: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:6798:3: ruleSimpleProjectDescription
            {
             before(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 

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
    // $ANTLR end "rule__RequiredRuntimeLibraryDependency__ProjectAssignment"


    // $ANTLR start "rule__TestedProject__ProjectAssignment"
    // InternalN4MFParser.g:6807:1: rule__TestedProject__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__TestedProject__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6811:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:6812:2: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:6812:2: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:6813:3: ruleSimpleProjectDescription
            {
             before(grammarAccess.getTestedProjectAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getTestedProjectAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 

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
    // $ANTLR end "rule__TestedProject__ProjectAssignment"


    // $ANTLR start "rule__ProjectReference__ProjectAssignment"
    // InternalN4MFParser.g:6822:1: rule__ProjectReference__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectReference__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6826:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:6827:2: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:6827:2: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:6828:3: ruleSimpleProjectDescription
            {
             before(grammarAccess.getProjectReferenceAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getProjectReferenceAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 

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
    // $ANTLR end "rule__ProjectReference__ProjectAssignment"


    // $ANTLR start "rule__ProjectDependency__ProjectAssignment_0"
    // InternalN4MFParser.g:6837:1: rule__ProjectDependency__ProjectAssignment_0 : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectDependency__ProjectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6841:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:6842:2: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:6842:2: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:6843:3: ruleSimpleProjectDescription
            {
             before(grammarAccess.getProjectDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleProjectDescription();

            state._fsp--;

             after(grammarAccess.getProjectDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__ProjectDependency__ProjectAssignment_0"


    // $ANTLR start "rule__ProjectDependency__VersionConstraintAssignment_1"
    // InternalN4MFParser.g:6852:1: rule__ProjectDependency__VersionConstraintAssignment_1 : ( ruleVersionConstraint ) ;
    public final void rule__ProjectDependency__VersionConstraintAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6856:1: ( ( ruleVersionConstraint ) )
            // InternalN4MFParser.g:6857:2: ( ruleVersionConstraint )
            {
            // InternalN4MFParser.g:6857:2: ( ruleVersionConstraint )
            // InternalN4MFParser.g:6858:3: ruleVersionConstraint
            {
             before(grammarAccess.getProjectDependencyAccess().getVersionConstraintVersionConstraintParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVersionConstraint();

            state._fsp--;

             after(grammarAccess.getProjectDependencyAccess().getVersionConstraintVersionConstraintParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ProjectDependency__VersionConstraintAssignment_1"


    // $ANTLR start "rule__ProjectDependency__DeclaredScopeAssignment_2"
    // InternalN4MFParser.g:6867:1: rule__ProjectDependency__DeclaredScopeAssignment_2 : ( ruleProjectDependencyScope ) ;
    public final void rule__ProjectDependency__DeclaredScopeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6871:1: ( ( ruleProjectDependencyScope ) )
            // InternalN4MFParser.g:6872:2: ( ruleProjectDependencyScope )
            {
            // InternalN4MFParser.g:6872:2: ( ruleProjectDependencyScope )
            // InternalN4MFParser.g:6873:3: ruleProjectDependencyScope
            {
             before(grammarAccess.getProjectDependencyAccess().getDeclaredScopeProjectDependencyScopeEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectDependencyScope();

            state._fsp--;

             after(grammarAccess.getProjectDependencyAccess().getDeclaredScopeProjectDependencyScopeEnumRuleCall_2_0()); 

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
    // $ANTLR end "rule__ProjectDependency__DeclaredScopeAssignment_2"


    // $ANTLR start "rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0"
    // InternalN4MFParser.g:6882:1: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6886:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6887:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6887:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6888:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_0_0_0()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0"


    // $ANTLR start "rule__SimpleProjectDescription__ProjectIdAssignment_1"
    // InternalN4MFParser.g:6897:1: rule__SimpleProjectDescription__ProjectIdAssignment_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__ProjectIdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6901:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:6902:2: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:6902:2: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:6903:3: ruleN4mfIdentifier
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__SimpleProjectDescription__ProjectIdAssignment_1"


    // $ANTLR start "rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0"
    // InternalN4MFParser.g:6912:1: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 : ( ( LeftParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6916:1: ( ( ( LeftParenthesis ) ) )
            // InternalN4MFParser.g:6917:2: ( ( LeftParenthesis ) )
            {
            // InternalN4MFParser.g:6917:2: ( ( LeftParenthesis ) )
            // InternalN4MFParser.g:6918:3: ( LeftParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 
            // InternalN4MFParser.g:6919:3: ( LeftParenthesis )
            // InternalN4MFParser.g:6920:4: LeftParenthesis
            {
             before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 
            match(input,LeftParenthesis,FOLLOW_2); 
             after(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 

            }

             after(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 

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
    // $ANTLR end "rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0"


    // $ANTLR start "rule__VersionConstraint__LowerVersionAssignment_0_1"
    // InternalN4MFParser.g:6931:1: rule__VersionConstraint__LowerVersionAssignment_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6935:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:6936:2: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:6936:2: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:6937:3: ruleDeclaredVersion
            {
             before(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__VersionConstraint__LowerVersionAssignment_0_1"


    // $ANTLR start "rule__VersionConstraint__UpperVersionAssignment_0_2_0_1"
    // InternalN4MFParser.g:6946:1: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__UpperVersionAssignment_0_2_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6950:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:6951:2: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:6951:2: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:6952:3: ruleDeclaredVersion
            {
             before(grammarAccess.getVersionConstraintAccess().getUpperVersionDeclaredVersionParserRuleCall_0_2_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getVersionConstraintAccess().getUpperVersionDeclaredVersionParserRuleCall_0_2_0_1_0()); 

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
    // $ANTLR end "rule__VersionConstraint__UpperVersionAssignment_0_2_0_1"


    // $ANTLR start "rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0"
    // InternalN4MFParser.g:6961:1: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 : ( ( RightParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6965:1: ( ( ( RightParenthesis ) ) )
            // InternalN4MFParser.g:6966:2: ( ( RightParenthesis ) )
            {
            // InternalN4MFParser.g:6966:2: ( ( RightParenthesis ) )
            // InternalN4MFParser.g:6967:3: ( RightParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 
            // InternalN4MFParser.g:6968:3: ( RightParenthesis )
            // InternalN4MFParser.g:6969:4: RightParenthesis
            {
             before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 
            match(input,RightParenthesis,FOLLOW_2); 
             after(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 

            }

             after(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 

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
    // $ANTLR end "rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0"


    // $ANTLR start "rule__VersionConstraint__LowerVersionAssignment_1"
    // InternalN4MFParser.g:6980:1: rule__VersionConstraint__LowerVersionAssignment_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalN4MFParser.g:6984:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:6985:2: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:6985:2: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:6986:3: ruleDeclaredVersion
            {
             before(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__VersionConstraint__LowerVersionAssignment_1"

    // Delegated rules


    protected DFA37 dfa37 = new DFA37(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA50 dfa50 = new DFA50(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA53 dfa53 = new DFA53(this);
    protected DFA54 dfa54 = new DFA54(this);
    protected DFA55 dfa55 = new DFA55(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA58 dfa58 = new DFA58(this);
    static final String dfa_1s = "\27\uffff";
    static final String dfa_2s = "\1\uffff\13\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24";
    static final String dfa_3s = "\1\10\13\4\1\46\3\4\1\45\2\4\2\uffff\2\4";
    static final String dfa_4s = "\1\66\13\67\1\46\3\67\1\45\2\67\2\uffff\2\67";
    static final String dfa_5s = "\23\uffff\1\1\1\2\2\uffff";
    static final String dfa_6s = "\27\uffff}>";
    static final String[] dfa_7s = {
            "\1\14\2\uffff\1\4\2\uffff\1\13\4\uffff\1\3\1\17\2\uffff\1\6\1\uffff\1\10\1\2\1\11\1\20\1\5\2\uffff\1\12\1\uffff\1\21\1\uffff\1\7\3\uffff\1\22\1\16\1\15\13\uffff\1\1",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\1\25",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\1\26",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "",
            "",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24\1\uffff\3\24\1\uffff\3\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\23\1\24\2\uffff\1\24\1\uffff\1\24"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "5057:2: ( rule__SimpleProjectDescription__Group_0__0 )?";
        }
    }
    static final String dfa_8s = "\26\uffff";
    static final String dfa_9s = "\1\4\25\uffff";
    static final String dfa_10s = "\1\44\25\uffff";
    static final String dfa_11s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25";
    static final String dfa_12s = "\1\0\25\uffff}>";
    static final String[] dfa_13s = {
            "\1\7\1\10\1\11\1\14\1\12\1\uffff\1\13\1\3\1\24\1\uffff\1\23\1\25\2\uffff\1\15\1\2\1\uffff\1\16\1\6\1\5\1\uffff\1\20\1\1\1\21\1\uffff\1\4\2\uffff\1\22\3\uffff\1\17",
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

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = dfa_8;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "5436:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_6 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_10__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_11 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_14__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_19 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_20__0 ) ) ) ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA38_0 = input.LA(1);

                         
                        int index38_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA38_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA38_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA38_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA38_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA38_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA38_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA38_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA38_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA38_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA38_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA38_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA38_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA38_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA38_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA38_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA38_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA38_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA38_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA38_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA38_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA38_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                         
                        input.seek(index38_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 38, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_14s = "\1\26\26\uffff";
    static final String dfa_15s = "\1\4\26\uffff";
    static final String dfa_16s = "\1\44\26\uffff";
    static final String dfa_17s = "\1\uffff\25\1\1\2";
    static final String dfa_18s = "\1\0\26\uffff}>";
    static final String[] dfa_19s = {
            "\1\7\1\10\1\11\1\14\1\12\1\uffff\1\13\1\3\1\24\1\uffff\1\23\1\25\2\uffff\1\15\1\2\1\uffff\1\16\1\6\1\5\1\uffff\1\20\1\1\1\21\1\uffff\1\4\2\uffff\1\22\3\uffff\1\17",
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
    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5766:2: ( rule__ProjectDescription__UnorderedGroup__1 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA39_0 = input.LA(1);

                         
                        int index39_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA39_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA39_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA39_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA39_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA39_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA39_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA39_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA39_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA39_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA39_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA39_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA39_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA39_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA39_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA39_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA39_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA39_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA39_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA39_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA39_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA39_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA39_0==EOF) ) {s = 22;}

                         
                        input.seek(index39_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 39, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5778:2: ( rule__ProjectDescription__UnorderedGroup__2 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA40_0 = input.LA(1);

                         
                        int index40_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA40_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA40_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA40_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA40_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA40_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA40_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA40_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA40_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA40_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA40_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA40_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA40_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA40_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA40_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA40_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA40_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA40_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA40_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA40_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA40_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA40_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA40_0==EOF) ) {s = 22;}

                         
                        input.seek(index40_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 40, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5790:2: ( rule__ProjectDescription__UnorderedGroup__3 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA41_0 = input.LA(1);

                         
                        int index41_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA41_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA41_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA41_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA41_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA41_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA41_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA41_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA41_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA41_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA41_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA41_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA41_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA41_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA41_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA41_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA41_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA41_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA41_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA41_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA41_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA41_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA41_0==EOF) ) {s = 22;}

                         
                        input.seek(index41_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 41, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5802:2: ( rule__ProjectDescription__UnorderedGroup__4 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_0 = input.LA(1);

                         
                        int index42_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA42_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA42_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA42_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA42_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA42_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA42_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA42_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA42_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA42_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA42_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA42_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA42_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA42_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA42_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA42_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA42_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA42_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA42_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA42_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA42_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA42_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA42_0==EOF) ) {s = 22;}

                         
                        input.seek(index42_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5814:2: ( rule__ProjectDescription__UnorderedGroup__5 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA43_0 = input.LA(1);

                         
                        int index43_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA43_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA43_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA43_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA43_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA43_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA43_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA43_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA43_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA43_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA43_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA43_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA43_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA43_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA43_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA43_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA43_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA43_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA43_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA43_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA43_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA43_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA43_0==EOF) ) {s = 22;}

                         
                        input.seek(index43_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 43, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA44 extends DFA {

        public DFA44(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 44;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5826:2: ( rule__ProjectDescription__UnorderedGroup__6 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA44_0 = input.LA(1);

                         
                        int index44_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA44_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA44_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA44_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA44_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA44_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA44_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA44_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA44_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA44_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA44_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA44_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA44_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA44_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA44_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA44_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA44_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA44_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA44_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA44_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA44_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA44_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA44_0==EOF) ) {s = 22;}

                         
                        input.seek(index44_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 44, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA45 extends DFA {

        public DFA45(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 45;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5838:2: ( rule__ProjectDescription__UnorderedGroup__7 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA45_0 = input.LA(1);

                         
                        int index45_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA45_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA45_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA45_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA45_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA45_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA45_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA45_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA45_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA45_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA45_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA45_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA45_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA45_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA45_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA45_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA45_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA45_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA45_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA45_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA45_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA45_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA45_0==EOF) ) {s = 22;}

                         
                        input.seek(index45_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 45, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5850:2: ( rule__ProjectDescription__UnorderedGroup__8 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA46_0 = input.LA(1);

                         
                        int index46_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA46_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA46_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA46_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA46_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA46_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA46_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA46_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA46_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA46_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA46_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA46_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA46_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA46_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA46_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA46_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA46_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA46_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA46_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA46_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA46_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA46_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA46_0==EOF) ) {s = 22;}

                         
                        input.seek(index46_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 46, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5862:2: ( rule__ProjectDescription__UnorderedGroup__9 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA47_0 = input.LA(1);

                         
                        int index47_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA47_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA47_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA47_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA47_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA47_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA47_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA47_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA47_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA47_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA47_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA47_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA47_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA47_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA47_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA47_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA47_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA47_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA47_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA47_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA47_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA47_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA47_0==EOF) ) {s = 22;}

                         
                        input.seek(index47_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 47, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA48 extends DFA {

        public DFA48(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 48;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5874:2: ( rule__ProjectDescription__UnorderedGroup__10 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA48_0 = input.LA(1);

                         
                        int index48_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA48_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA48_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA48_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA48_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA48_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA48_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA48_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA48_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA48_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA48_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA48_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA48_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA48_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA48_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA48_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA48_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA48_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA48_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA48_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA48_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA48_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA48_0==EOF) ) {s = 22;}

                         
                        input.seek(index48_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 48, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5886:2: ( rule__ProjectDescription__UnorderedGroup__11 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA49_0 = input.LA(1);

                         
                        int index49_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA49_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA49_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA49_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA49_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA49_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA49_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA49_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA49_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA49_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA49_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA49_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA49_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA49_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA49_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA49_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA49_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA49_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA49_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA49_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA49_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA49_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA49_0==EOF) ) {s = 22;}

                         
                        input.seek(index49_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 49, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5898:2: ( rule__ProjectDescription__UnorderedGroup__12 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA50_0 = input.LA(1);

                         
                        int index50_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA50_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA50_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA50_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA50_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA50_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA50_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA50_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA50_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA50_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA50_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA50_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA50_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA50_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA50_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA50_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA50_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA50_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA50_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA50_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA50_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA50_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA50_0==EOF) ) {s = 22;}

                         
                        input.seek(index50_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 50, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5910:2: ( rule__ProjectDescription__UnorderedGroup__13 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA51_0 = input.LA(1);

                         
                        int index51_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA51_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA51_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA51_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA51_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA51_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA51_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA51_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA51_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA51_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA51_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA51_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA51_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA51_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA51_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA51_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA51_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA51_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA51_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA51_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA51_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA51_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA51_0==EOF) ) {s = 22;}

                         
                        input.seek(index51_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 51, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5922:2: ( rule__ProjectDescription__UnorderedGroup__14 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA52_0 = input.LA(1);

                         
                        int index52_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA52_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA52_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA52_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA52_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA52_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA52_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA52_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA52_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA52_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA52_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA52_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA52_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA52_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA52_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA52_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA52_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA52_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA52_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA52_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA52_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA52_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA52_0==EOF) ) {s = 22;}

                         
                        input.seek(index52_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 52, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA53 extends DFA {

        public DFA53(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 53;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5934:2: ( rule__ProjectDescription__UnorderedGroup__15 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA53_0 = input.LA(1);

                         
                        int index53_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA53_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA53_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA53_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA53_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA53_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA53_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA53_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA53_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA53_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA53_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA53_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA53_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA53_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA53_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA53_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA53_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA53_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA53_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA53_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA53_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA53_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA53_0==EOF) ) {s = 22;}

                         
                        input.seek(index53_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 53, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA54 extends DFA {

        public DFA54(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 54;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5946:2: ( rule__ProjectDescription__UnorderedGroup__16 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA54_0 = input.LA(1);

                         
                        int index54_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA54_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA54_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA54_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA54_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA54_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA54_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA54_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA54_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA54_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA54_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA54_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA54_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA54_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA54_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA54_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA54_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA54_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA54_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA54_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA54_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA54_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA54_0==EOF) ) {s = 22;}

                         
                        input.seek(index54_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 54, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA55 extends DFA {

        public DFA55(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 55;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5958:2: ( rule__ProjectDescription__UnorderedGroup__17 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA55_0 = input.LA(1);

                         
                        int index55_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA55_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA55_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA55_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA55_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA55_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA55_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA55_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA55_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA55_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA55_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA55_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA55_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA55_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA55_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA55_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA55_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA55_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA55_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA55_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA55_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA55_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA55_0==EOF) ) {s = 22;}

                         
                        input.seek(index55_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 55, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA56 extends DFA {

        public DFA56(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 56;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5970:2: ( rule__ProjectDescription__UnorderedGroup__18 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA56_0 = input.LA(1);

                         
                        int index56_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA56_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA56_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA56_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA56_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA56_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA56_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA56_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA56_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA56_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA56_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA56_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA56_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA56_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA56_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA56_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA56_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA56_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA56_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA56_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA56_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA56_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA56_0==EOF) ) {s = 22;}

                         
                        input.seek(index56_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 56, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5982:2: ( rule__ProjectDescription__UnorderedGroup__19 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA57_0 = input.LA(1);

                         
                        int index57_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA57_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA57_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA57_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA57_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA57_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA57_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA57_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA57_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA57_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA57_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA57_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA57_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA57_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA57_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA57_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA57_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA57_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA57_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA57_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA57_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA57_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA57_0==EOF) ) {s = 22;}

                         
                        input.seek(index57_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 57, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA58 extends DFA {

        public DFA58(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 58;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "5994:2: ( rule__ProjectDescription__UnorderedGroup__20 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA58_0 = input.LA(1);

                         
                        int index58_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA58_0 == ProjectId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA58_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA58_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA58_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA58_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA58_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA58_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA58_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA58_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA58_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA58_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA58_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA58_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA58_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA58_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA58_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA58_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA58_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA58_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA58_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA58_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( (LA58_0==EOF) ) {s = 22;}

                         
                        input.seek(index58_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 58, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x004007153E984900L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000050810102200L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0020400000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000012080000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000012080000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001010000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001010002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000008040020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x006007153E984900L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0120000000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0084110200000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0008200000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000112EECDDF2L});

}
