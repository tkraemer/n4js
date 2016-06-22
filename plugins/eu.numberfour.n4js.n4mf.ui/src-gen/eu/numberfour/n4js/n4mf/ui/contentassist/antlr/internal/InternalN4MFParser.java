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
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import eu.numberfour.n4js.n4mf.services.N4MFGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalN4MFParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ExtendedRuntimeEnvironment", "ProvidedRuntimeLibraries", "RequiredRuntimeLibraries", "ImplementedProjects", "ProjectDependencies", "RuntimeEnvironment", "ImplementationId", "ProjectVersion", "TestedProjects", "RuntimeLibrary", "ModuleFilters", "ModuleLoader", "NoModuleWrap", "Node_builtin", "InitModules", "ProjectName", "ProjectType", "Application", "ArtifactId", "ExecModule", "MainModule", "VendorName", "NoValidate", "Libraries", "Resources", "Processor", "VendorId", "Commonjs", "External", "Sources", "Compile", "Content", "Library", "Output", "Source", "KW_System", "N4js", "Test", "User", "API", "In", "LeftParenthesis", "RightParenthesis", "Comma", "HyphenMinus", "FullStop", "Colon", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int ArtifactId=22;
    public static final int TestedProjects=12;
    public static final int KW_System=39;
    public static final int ProjectDependencies=8;
    public static final int ExecModule=23;
    public static final int LeftParenthesis=45;
    public static final int Test=41;
    public static final int ProjectVersion=11;
    public static final int Libraries=27;
    public static final int ModuleFilters=14;
    public static final int RightSquareBracket=52;
    public static final int VendorName=25;
    public static final int RuntimeEnvironment=9;
    public static final int RULE_ID=55;
    public static final int NoValidate=26;
    public static final int NoModuleWrap=16;
    public static final int RightParenthesis=46;
    public static final int Sources=33;
    public static final int Content=35;
    public static final int RULE_INT=56;
    public static final int ProjectType=20;
    public static final int External=32;
    public static final int RULE_ML_COMMENT=58;
    public static final int LeftSquareBracket=51;
    public static final int Resources=28;
    public static final int Library=36;
    public static final int Application=21;
    public static final int ImplementedProjects=7;
    public static final int Processor=29;
    public static final int User=42;
    public static final int ProjectName=19;
    public static final int In=44;
    public static final int VendorId=30;
    public static final int RULE_STRING=57;
    public static final int Node_builtin=17;
    public static final int N4js=40;
    public static final int Compile=34;
    public static final int Source=38;
    public static final int RULE_SL_COMMENT=59;
    public static final int ImplementationId=10;
    public static final int Comma=47;
    public static final int HyphenMinus=48;
    public static final int Output=37;
    public static final int MainModule=24;
    public static final int Colon=50;
    public static final int RightCurlyBracket=54;
    public static final int EOF=-1;
    public static final int ExtendedRuntimeEnvironment=4;
    public static final int FullStop=49;
    public static final int ModuleLoader=15;
    public static final int Commonjs=31;
    public static final int RULE_WS=60;
    public static final int LeftCurlyBracket=53;
    public static final int ProvidedRuntimeLibraries=5;
    public static final int RULE_ANY_OTHER=61;
    public static final int RequiredRuntimeLibraries=6;
    public static final int InitModules=18;
    public static final int API=43;
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
    		tokenNameToValue.put("Resources", "'Resources'");
    		tokenNameToValue.put("Processor", "'processor'");
    		tokenNameToValue.put("ArtifactId", "'ArtifactId'");
    		tokenNameToValue.put("ExecModule", "'ExecModule'");
    		tokenNameToValue.put("MainModule", "'MainModule'");
    		tokenNameToValue.put("VendorName", "'VendorName'");
    		tokenNameToValue.put("NoValidate", "'noValidate'");
    		tokenNameToValue.put("InitModules", "'InitModules'");
    		tokenNameToValue.put("ProjectName", "'ProjectName'");
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
    // InternalN4MFParser.g:114:1: entryRuleProjectDescription : ruleProjectDescription EOF ;
    public final void entryRuleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:115:1: ( ruleProjectDescription EOF )
            // InternalN4MFParser.g:116:1: ruleProjectDescription EOF
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
    // InternalN4MFParser.g:123:1: ruleProjectDescription : ( ( rule__ProjectDescription__UnorderedGroup ) ) ;
    public final void ruleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:127:5: ( ( ( rule__ProjectDescription__UnorderedGroup ) ) )
            // InternalN4MFParser.g:128:1: ( ( rule__ProjectDescription__UnorderedGroup ) )
            {
            // InternalN4MFParser.g:128:1: ( ( rule__ProjectDescription__UnorderedGroup ) )
            // InternalN4MFParser.g:129:1: ( rule__ProjectDescription__UnorderedGroup )
            {
             before(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()); 
            // InternalN4MFParser.g:130:1: ( rule__ProjectDescription__UnorderedGroup )
            // InternalN4MFParser.g:130:2: rule__ProjectDescription__UnorderedGroup
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
    // InternalN4MFParser.g:142:1: entryRuleExecModule : ruleExecModule EOF ;
    public final void entryRuleExecModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:143:1: ( ruleExecModule EOF )
            // InternalN4MFParser.g:144:1: ruleExecModule EOF
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
    // InternalN4MFParser.g:151:1: ruleExecModule : ( ( rule__ExecModule__Group__0 ) ) ;
    public final void ruleExecModule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:155:5: ( ( ( rule__ExecModule__Group__0 ) ) )
            // InternalN4MFParser.g:156:1: ( ( rule__ExecModule__Group__0 ) )
            {
            // InternalN4MFParser.g:156:1: ( ( rule__ExecModule__Group__0 ) )
            // InternalN4MFParser.g:157:1: ( rule__ExecModule__Group__0 )
            {
             before(grammarAccess.getExecModuleAccess().getGroup()); 
            // InternalN4MFParser.g:158:1: ( rule__ExecModule__Group__0 )
            // InternalN4MFParser.g:158:2: rule__ExecModule__Group__0
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
    // InternalN4MFParser.g:170:1: entryRuleTestedProjects : ruleTestedProjects EOF ;
    public final void entryRuleTestedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:171:1: ( ruleTestedProjects EOF )
            // InternalN4MFParser.g:172:1: ruleTestedProjects EOF
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
    // InternalN4MFParser.g:179:1: ruleTestedProjects : ( ( rule__TestedProjects__Group__0 ) ) ;
    public final void ruleTestedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:183:5: ( ( ( rule__TestedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:184:1: ( ( rule__TestedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:184:1: ( ( rule__TestedProjects__Group__0 ) )
            // InternalN4MFParser.g:185:1: ( rule__TestedProjects__Group__0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:186:1: ( rule__TestedProjects__Group__0 )
            // InternalN4MFParser.g:186:2: rule__TestedProjects__Group__0
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
    // InternalN4MFParser.g:198:1: entryRuleInitModules : ruleInitModules EOF ;
    public final void entryRuleInitModules() throws RecognitionException {
        try {
            // InternalN4MFParser.g:199:1: ( ruleInitModules EOF )
            // InternalN4MFParser.g:200:1: ruleInitModules EOF
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
    // InternalN4MFParser.g:207:1: ruleInitModules : ( ( rule__InitModules__Group__0 ) ) ;
    public final void ruleInitModules() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:211:5: ( ( ( rule__InitModules__Group__0 ) ) )
            // InternalN4MFParser.g:212:1: ( ( rule__InitModules__Group__0 ) )
            {
            // InternalN4MFParser.g:212:1: ( ( rule__InitModules__Group__0 ) )
            // InternalN4MFParser.g:213:1: ( rule__InitModules__Group__0 )
            {
             before(grammarAccess.getInitModulesAccess().getGroup()); 
            // InternalN4MFParser.g:214:1: ( rule__InitModules__Group__0 )
            // InternalN4MFParser.g:214:2: rule__InitModules__Group__0
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
    // InternalN4MFParser.g:226:1: entryRuleImplementedProjects : ruleImplementedProjects EOF ;
    public final void entryRuleImplementedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:227:1: ( ruleImplementedProjects EOF )
            // InternalN4MFParser.g:228:1: ruleImplementedProjects EOF
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
    // InternalN4MFParser.g:235:1: ruleImplementedProjects : ( ( rule__ImplementedProjects__Group__0 ) ) ;
    public final void ruleImplementedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:239:5: ( ( ( rule__ImplementedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:240:1: ( ( rule__ImplementedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:240:1: ( ( rule__ImplementedProjects__Group__0 ) )
            // InternalN4MFParser.g:241:1: ( rule__ImplementedProjects__Group__0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:242:1: ( rule__ImplementedProjects__Group__0 )
            // InternalN4MFParser.g:242:2: rule__ImplementedProjects__Group__0
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
    // InternalN4MFParser.g:254:1: entryRuleProjectDependencies : ruleProjectDependencies EOF ;
    public final void entryRuleProjectDependencies() throws RecognitionException {
        try {
            // InternalN4MFParser.g:255:1: ( ruleProjectDependencies EOF )
            // InternalN4MFParser.g:256:1: ruleProjectDependencies EOF
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
    // InternalN4MFParser.g:263:1: ruleProjectDependencies : ( ( rule__ProjectDependencies__Group__0 ) ) ;
    public final void ruleProjectDependencies() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:267:5: ( ( ( rule__ProjectDependencies__Group__0 ) ) )
            // InternalN4MFParser.g:268:1: ( ( rule__ProjectDependencies__Group__0 ) )
            {
            // InternalN4MFParser.g:268:1: ( ( rule__ProjectDependencies__Group__0 ) )
            // InternalN4MFParser.g:269:1: ( rule__ProjectDependencies__Group__0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup()); 
            // InternalN4MFParser.g:270:1: ( rule__ProjectDependencies__Group__0 )
            // InternalN4MFParser.g:270:2: rule__ProjectDependencies__Group__0
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
    // InternalN4MFParser.g:282:1: entryRuleProvidedRuntimeLibraries : ruleProvidedRuntimeLibraries EOF ;
    public final void entryRuleProvidedRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:283:1: ( ruleProvidedRuntimeLibraries EOF )
            // InternalN4MFParser.g:284:1: ruleProvidedRuntimeLibraries EOF
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
    // InternalN4MFParser.g:291:1: ruleProvidedRuntimeLibraries : ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) ;
    public final void ruleProvidedRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:295:5: ( ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:296:1: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:296:1: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:297:1: ( rule__ProvidedRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:298:1: ( rule__ProvidedRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:298:2: rule__ProvidedRuntimeLibraries__Group__0
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
    // InternalN4MFParser.g:310:1: entryRuleRequiredRuntimeLibraries : ruleRequiredRuntimeLibraries EOF ;
    public final void entryRuleRequiredRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:311:1: ( ruleRequiredRuntimeLibraries EOF )
            // InternalN4MFParser.g:312:1: ruleRequiredRuntimeLibraries EOF
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
    // InternalN4MFParser.g:319:1: ruleRequiredRuntimeLibraries : ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) ;
    public final void ruleRequiredRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:323:5: ( ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:324:1: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:324:1: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:325:1: ( rule__RequiredRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:326:1: ( rule__RequiredRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:326:2: rule__RequiredRuntimeLibraries__Group__0
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
    // InternalN4MFParser.g:338:1: entryRuleExtendedRuntimeEnvironment : ruleExtendedRuntimeEnvironment EOF ;
    public final void entryRuleExtendedRuntimeEnvironment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:339:1: ( ruleExtendedRuntimeEnvironment EOF )
            // InternalN4MFParser.g:340:1: ruleExtendedRuntimeEnvironment EOF
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
    // InternalN4MFParser.g:347:1: ruleExtendedRuntimeEnvironment : ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) ;
    public final void ruleExtendedRuntimeEnvironment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:351:5: ( ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) )
            // InternalN4MFParser.g:352:1: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            {
            // InternalN4MFParser.g:352:1: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            // InternalN4MFParser.g:353:1: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getGroup()); 
            // InternalN4MFParser.g:354:1: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            // InternalN4MFParser.g:354:2: rule__ExtendedRuntimeEnvironment__Group__0
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
    // InternalN4MFParser.g:366:1: entryRuleDeclaredVersion : ruleDeclaredVersion EOF ;
    public final void entryRuleDeclaredVersion() throws RecognitionException {
        try {
            // InternalN4MFParser.g:367:1: ( ruleDeclaredVersion EOF )
            // InternalN4MFParser.g:368:1: ruleDeclaredVersion EOF
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
    // InternalN4MFParser.g:375:1: ruleDeclaredVersion : ( ( rule__DeclaredVersion__Group__0 ) ) ;
    public final void ruleDeclaredVersion() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:379:5: ( ( ( rule__DeclaredVersion__Group__0 ) ) )
            // InternalN4MFParser.g:380:1: ( ( rule__DeclaredVersion__Group__0 ) )
            {
            // InternalN4MFParser.g:380:1: ( ( rule__DeclaredVersion__Group__0 ) )
            // InternalN4MFParser.g:381:1: ( rule__DeclaredVersion__Group__0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup()); 
            // InternalN4MFParser.g:382:1: ( rule__DeclaredVersion__Group__0 )
            // InternalN4MFParser.g:382:2: rule__DeclaredVersion__Group__0
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
    // InternalN4MFParser.g:394:1: entryRuleSourceFragment : ruleSourceFragment EOF ;
    public final void entryRuleSourceFragment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:395:1: ( ruleSourceFragment EOF )
            // InternalN4MFParser.g:396:1: ruleSourceFragment EOF
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
    // InternalN4MFParser.g:403:1: ruleSourceFragment : ( ( rule__SourceFragment__Group__0 ) ) ;
    public final void ruleSourceFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:407:5: ( ( ( rule__SourceFragment__Group__0 ) ) )
            // InternalN4MFParser.g:408:1: ( ( rule__SourceFragment__Group__0 ) )
            {
            // InternalN4MFParser.g:408:1: ( ( rule__SourceFragment__Group__0 ) )
            // InternalN4MFParser.g:409:1: ( rule__SourceFragment__Group__0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup()); 
            // InternalN4MFParser.g:410:1: ( rule__SourceFragment__Group__0 )
            // InternalN4MFParser.g:410:2: rule__SourceFragment__Group__0
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
    // InternalN4MFParser.g:422:1: entryRuleModuleFilter : ruleModuleFilter EOF ;
    public final void entryRuleModuleFilter() throws RecognitionException {
        try {
            // InternalN4MFParser.g:423:1: ( ruleModuleFilter EOF )
            // InternalN4MFParser.g:424:1: ruleModuleFilter EOF
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
    // InternalN4MFParser.g:431:1: ruleModuleFilter : ( ( rule__ModuleFilter__Group__0 ) ) ;
    public final void ruleModuleFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:435:5: ( ( ( rule__ModuleFilter__Group__0 ) ) )
            // InternalN4MFParser.g:436:1: ( ( rule__ModuleFilter__Group__0 ) )
            {
            // InternalN4MFParser.g:436:1: ( ( rule__ModuleFilter__Group__0 ) )
            // InternalN4MFParser.g:437:1: ( rule__ModuleFilter__Group__0 )
            {
             before(grammarAccess.getModuleFilterAccess().getGroup()); 
            // InternalN4MFParser.g:438:1: ( rule__ModuleFilter__Group__0 )
            // InternalN4MFParser.g:438:2: rule__ModuleFilter__Group__0
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
    // InternalN4MFParser.g:450:1: entryRuleBootstrapModule : ruleBootstrapModule EOF ;
    public final void entryRuleBootstrapModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:451:1: ( ruleBootstrapModule EOF )
            // InternalN4MFParser.g:452:1: ruleBootstrapModule EOF
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
    // InternalN4MFParser.g:459:1: ruleBootstrapModule : ( ( rule__BootstrapModule__Group__0 ) ) ;
    public final void ruleBootstrapModule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:463:5: ( ( ( rule__BootstrapModule__Group__0 ) ) )
            // InternalN4MFParser.g:464:1: ( ( rule__BootstrapModule__Group__0 ) )
            {
            // InternalN4MFParser.g:464:1: ( ( rule__BootstrapModule__Group__0 ) )
            // InternalN4MFParser.g:465:1: ( rule__BootstrapModule__Group__0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup()); 
            // InternalN4MFParser.g:466:1: ( rule__BootstrapModule__Group__0 )
            // InternalN4MFParser.g:466:2: rule__BootstrapModule__Group__0
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
    // InternalN4MFParser.g:478:1: entryRuleModuleFilterSpecifier : ruleModuleFilterSpecifier EOF ;
    public final void entryRuleModuleFilterSpecifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:479:1: ( ruleModuleFilterSpecifier EOF )
            // InternalN4MFParser.g:480:1: ruleModuleFilterSpecifier EOF
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
    // InternalN4MFParser.g:487:1: ruleModuleFilterSpecifier : ( ( rule__ModuleFilterSpecifier__Group__0 ) ) ;
    public final void ruleModuleFilterSpecifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:491:5: ( ( ( rule__ModuleFilterSpecifier__Group__0 ) ) )
            // InternalN4MFParser.g:492:1: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            {
            // InternalN4MFParser.g:492:1: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            // InternalN4MFParser.g:493:1: ( rule__ModuleFilterSpecifier__Group__0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup()); 
            // InternalN4MFParser.g:494:1: ( rule__ModuleFilterSpecifier__Group__0 )
            // InternalN4MFParser.g:494:2: rule__ModuleFilterSpecifier__Group__0
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
    // InternalN4MFParser.g:506:1: entryRuleProvidedRuntimeLibraryDependency : ruleProvidedRuntimeLibraryDependency EOF ;
    public final void entryRuleProvidedRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:507:1: ( ruleProvidedRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:508:1: ruleProvidedRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:515:1: ruleProvidedRuntimeLibraryDependency : ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleProvidedRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:519:5: ( ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:520:1: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:520:1: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:521:1: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:522:1: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:522:2: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment
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
    // InternalN4MFParser.g:534:1: entryRuleRequiredRuntimeLibraryDependency : ruleRequiredRuntimeLibraryDependency EOF ;
    public final void entryRuleRequiredRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:535:1: ( ruleRequiredRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:536:1: ruleRequiredRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:543:1: ruleRequiredRuntimeLibraryDependency : ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleRequiredRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:547:5: ( ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:548:1: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:548:1: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:549:1: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:550:1: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:550:2: rule__RequiredRuntimeLibraryDependency__ProjectAssignment
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
    // InternalN4MFParser.g:562:1: entryRuleTestedProject : ruleTestedProject EOF ;
    public final void entryRuleTestedProject() throws RecognitionException {
        try {
            // InternalN4MFParser.g:563:1: ( ruleTestedProject EOF )
            // InternalN4MFParser.g:564:1: ruleTestedProject EOF
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
    // InternalN4MFParser.g:571:1: ruleTestedProject : ( ( rule__TestedProject__ProjectAssignment ) ) ;
    public final void ruleTestedProject() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:575:5: ( ( ( rule__TestedProject__ProjectAssignment ) ) )
            // InternalN4MFParser.g:576:1: ( ( rule__TestedProject__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:576:1: ( ( rule__TestedProject__ProjectAssignment ) )
            // InternalN4MFParser.g:577:1: ( rule__TestedProject__ProjectAssignment )
            {
             before(grammarAccess.getTestedProjectAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:578:1: ( rule__TestedProject__ProjectAssignment )
            // InternalN4MFParser.g:578:2: rule__TestedProject__ProjectAssignment
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
    // InternalN4MFParser.g:590:1: entryRuleProjectReference : ruleProjectReference EOF ;
    public final void entryRuleProjectReference() throws RecognitionException {
        try {
            // InternalN4MFParser.g:591:1: ( ruleProjectReference EOF )
            // InternalN4MFParser.g:592:1: ruleProjectReference EOF
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
    // InternalN4MFParser.g:599:1: ruleProjectReference : ( ( rule__ProjectReference__ProjectAssignment ) ) ;
    public final void ruleProjectReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:603:5: ( ( ( rule__ProjectReference__ProjectAssignment ) ) )
            // InternalN4MFParser.g:604:1: ( ( rule__ProjectReference__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:604:1: ( ( rule__ProjectReference__ProjectAssignment ) )
            // InternalN4MFParser.g:605:1: ( rule__ProjectReference__ProjectAssignment )
            {
             before(grammarAccess.getProjectReferenceAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:606:1: ( rule__ProjectReference__ProjectAssignment )
            // InternalN4MFParser.g:606:2: rule__ProjectReference__ProjectAssignment
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
    // InternalN4MFParser.g:618:1: entryRuleProjectDependency : ruleProjectDependency EOF ;
    public final void entryRuleProjectDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:619:1: ( ruleProjectDependency EOF )
            // InternalN4MFParser.g:620:1: ruleProjectDependency EOF
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
    // InternalN4MFParser.g:627:1: ruleProjectDependency : ( ( rule__ProjectDependency__Group__0 ) ) ;
    public final void ruleProjectDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:631:5: ( ( ( rule__ProjectDependency__Group__0 ) ) )
            // InternalN4MFParser.g:632:1: ( ( rule__ProjectDependency__Group__0 ) )
            {
            // InternalN4MFParser.g:632:1: ( ( rule__ProjectDependency__Group__0 ) )
            // InternalN4MFParser.g:633:1: ( rule__ProjectDependency__Group__0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getGroup()); 
            // InternalN4MFParser.g:634:1: ( rule__ProjectDependency__Group__0 )
            // InternalN4MFParser.g:634:2: rule__ProjectDependency__Group__0
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
    // InternalN4MFParser.g:646:1: entryRuleSimpleProjectDescription : ruleSimpleProjectDescription EOF ;
    public final void entryRuleSimpleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:647:1: ( ruleSimpleProjectDescription EOF )
            // InternalN4MFParser.g:648:1: ruleSimpleProjectDescription EOF
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
    // InternalN4MFParser.g:655:1: ruleSimpleProjectDescription : ( ( rule__SimpleProjectDescription__Group__0 ) ) ;
    public final void ruleSimpleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:659:5: ( ( ( rule__SimpleProjectDescription__Group__0 ) ) )
            // InternalN4MFParser.g:660:1: ( ( rule__SimpleProjectDescription__Group__0 ) )
            {
            // InternalN4MFParser.g:660:1: ( ( rule__SimpleProjectDescription__Group__0 ) )
            // InternalN4MFParser.g:661:1: ( rule__SimpleProjectDescription__Group__0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup()); 
            // InternalN4MFParser.g:662:1: ( rule__SimpleProjectDescription__Group__0 )
            // InternalN4MFParser.g:662:2: rule__SimpleProjectDescription__Group__0
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
    // InternalN4MFParser.g:674:1: entryRuleVersionConstraint : ruleVersionConstraint EOF ;
    public final void entryRuleVersionConstraint() throws RecognitionException {
        try {
            // InternalN4MFParser.g:675:1: ( ruleVersionConstraint EOF )
            // InternalN4MFParser.g:676:1: ruleVersionConstraint EOF
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
    // InternalN4MFParser.g:683:1: ruleVersionConstraint : ( ( rule__VersionConstraint__Alternatives ) ) ;
    public final void ruleVersionConstraint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:687:5: ( ( ( rule__VersionConstraint__Alternatives ) ) )
            // InternalN4MFParser.g:688:1: ( ( rule__VersionConstraint__Alternatives ) )
            {
            // InternalN4MFParser.g:688:1: ( ( rule__VersionConstraint__Alternatives ) )
            // InternalN4MFParser.g:689:1: ( rule__VersionConstraint__Alternatives )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives()); 
            // InternalN4MFParser.g:690:1: ( rule__VersionConstraint__Alternatives )
            // InternalN4MFParser.g:690:2: rule__VersionConstraint__Alternatives
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
    // InternalN4MFParser.g:702:1: entryRuleN4mfIdentifier : ruleN4mfIdentifier EOF ;
    public final void entryRuleN4mfIdentifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:703:1: ( ruleN4mfIdentifier EOF )
            // InternalN4MFParser.g:704:1: ruleN4mfIdentifier EOF
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
    // InternalN4MFParser.g:711:1: ruleN4mfIdentifier : ( ( rule__N4mfIdentifier__Alternatives ) ) ;
    public final void ruleN4mfIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:715:5: ( ( ( rule__N4mfIdentifier__Alternatives ) ) )
            // InternalN4MFParser.g:716:1: ( ( rule__N4mfIdentifier__Alternatives ) )
            {
            // InternalN4MFParser.g:716:1: ( ( rule__N4mfIdentifier__Alternatives ) )
            // InternalN4MFParser.g:717:1: ( rule__N4mfIdentifier__Alternatives )
            {
             before(grammarAccess.getN4mfIdentifierAccess().getAlternatives()); 
            // InternalN4MFParser.g:718:1: ( rule__N4mfIdentifier__Alternatives )
            // InternalN4MFParser.g:718:2: rule__N4mfIdentifier__Alternatives
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
    // InternalN4MFParser.g:731:1: ruleProjectType : ( ( rule__ProjectType__Alternatives ) ) ;
    public final void ruleProjectType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:735:1: ( ( ( rule__ProjectType__Alternatives ) ) )
            // InternalN4MFParser.g:736:1: ( ( rule__ProjectType__Alternatives ) )
            {
            // InternalN4MFParser.g:736:1: ( ( rule__ProjectType__Alternatives ) )
            // InternalN4MFParser.g:737:1: ( rule__ProjectType__Alternatives )
            {
             before(grammarAccess.getProjectTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:738:1: ( rule__ProjectType__Alternatives )
            // InternalN4MFParser.g:738:2: rule__ProjectType__Alternatives
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
    // InternalN4MFParser.g:750:1: ruleSourceFragmentType : ( ( rule__SourceFragmentType__Alternatives ) ) ;
    public final void ruleSourceFragmentType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:754:1: ( ( ( rule__SourceFragmentType__Alternatives ) ) )
            // InternalN4MFParser.g:755:1: ( ( rule__SourceFragmentType__Alternatives ) )
            {
            // InternalN4MFParser.g:755:1: ( ( rule__SourceFragmentType__Alternatives ) )
            // InternalN4MFParser.g:756:1: ( rule__SourceFragmentType__Alternatives )
            {
             before(grammarAccess.getSourceFragmentTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:757:1: ( rule__SourceFragmentType__Alternatives )
            // InternalN4MFParser.g:757:2: rule__SourceFragmentType__Alternatives
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
    // InternalN4MFParser.g:769:1: ruleModuleFilterType : ( ( rule__ModuleFilterType__Alternatives ) ) ;
    public final void ruleModuleFilterType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:773:1: ( ( ( rule__ModuleFilterType__Alternatives ) ) )
            // InternalN4MFParser.g:774:1: ( ( rule__ModuleFilterType__Alternatives ) )
            {
            // InternalN4MFParser.g:774:1: ( ( rule__ModuleFilterType__Alternatives ) )
            // InternalN4MFParser.g:775:1: ( rule__ModuleFilterType__Alternatives )
            {
             before(grammarAccess.getModuleFilterTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:776:1: ( rule__ModuleFilterType__Alternatives )
            // InternalN4MFParser.g:776:2: rule__ModuleFilterType__Alternatives
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
    // InternalN4MFParser.g:788:1: ruleProjectDependencyScope : ( ( rule__ProjectDependencyScope__Alternatives ) ) ;
    public final void ruleProjectDependencyScope() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:792:1: ( ( ( rule__ProjectDependencyScope__Alternatives ) ) )
            // InternalN4MFParser.g:793:1: ( ( rule__ProjectDependencyScope__Alternatives ) )
            {
            // InternalN4MFParser.g:793:1: ( ( rule__ProjectDependencyScope__Alternatives ) )
            // InternalN4MFParser.g:794:1: ( rule__ProjectDependencyScope__Alternatives )
            {
             before(grammarAccess.getProjectDependencyScopeAccess().getAlternatives()); 
            // InternalN4MFParser.g:795:1: ( rule__ProjectDependencyScope__Alternatives )
            // InternalN4MFParser.g:795:2: rule__ProjectDependencyScope__Alternatives
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
    // InternalN4MFParser.g:807:1: ruleModuleLoader : ( ( rule__ModuleLoader__Alternatives ) ) ;
    public final void ruleModuleLoader() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:811:1: ( ( ( rule__ModuleLoader__Alternatives ) ) )
            // InternalN4MFParser.g:812:1: ( ( rule__ModuleLoader__Alternatives ) )
            {
            // InternalN4MFParser.g:812:1: ( ( rule__ModuleLoader__Alternatives ) )
            // InternalN4MFParser.g:813:1: ( rule__ModuleLoader__Alternatives )
            {
             before(grammarAccess.getModuleLoaderAccess().getAlternatives()); 
            // InternalN4MFParser.g:814:1: ( rule__ModuleLoader__Alternatives )
            // InternalN4MFParser.g:814:2: rule__ModuleLoader__Alternatives
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
    // InternalN4MFParser.g:825:1: rule__VersionConstraint__Alternatives : ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) );
    public final void rule__VersionConstraint__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:829:1: ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) )
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
                    // InternalN4MFParser.g:830:1: ( ( rule__VersionConstraint__Group_0__0 ) )
                    {
                    // InternalN4MFParser.g:830:1: ( ( rule__VersionConstraint__Group_0__0 ) )
                    // InternalN4MFParser.g:831:1: ( rule__VersionConstraint__Group_0__0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0()); 
                    // InternalN4MFParser.g:832:1: ( rule__VersionConstraint__Group_0__0 )
                    // InternalN4MFParser.g:832:2: rule__VersionConstraint__Group_0__0
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
                    // InternalN4MFParser.g:836:6: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    {
                    // InternalN4MFParser.g:836:6: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    // InternalN4MFParser.g:837:1: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_1()); 
                    // InternalN4MFParser.g:838:1: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    // InternalN4MFParser.g:838:2: rule__VersionConstraint__LowerVersionAssignment_1
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
    // InternalN4MFParser.g:847:1: rule__VersionConstraint__Alternatives_0_0 : ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:851:1: ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) )
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
                    // InternalN4MFParser.g:852:1: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    {
                    // InternalN4MFParser.g:852:1: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    // InternalN4MFParser.g:853:1: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundAssignment_0_0_0()); 
                    // InternalN4MFParser.g:854:1: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    // InternalN4MFParser.g:854:2: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0
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
                    // InternalN4MFParser.g:858:6: ( LeftSquareBracket )
                    {
                    // InternalN4MFParser.g:858:6: ( LeftSquareBracket )
                    // InternalN4MFParser.g:859:1: LeftSquareBracket
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
    // InternalN4MFParser.g:871:1: rule__VersionConstraint__Alternatives_0_2 : ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) );
    public final void rule__VersionConstraint__Alternatives_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:875:1: ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) )
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
                    // InternalN4MFParser.g:876:1: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    {
                    // InternalN4MFParser.g:876:1: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    // InternalN4MFParser.g:877:1: ( rule__VersionConstraint__Group_0_2_0__0 )?
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0_2_0()); 
                    // InternalN4MFParser.g:878:1: ( rule__VersionConstraint__Group_0_2_0__0 )?
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
                            // InternalN4MFParser.g:878:2: rule__VersionConstraint__Group_0_2_0__0
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
                    // InternalN4MFParser.g:882:6: ( RightParenthesis )
                    {
                    // InternalN4MFParser.g:882:6: ( RightParenthesis )
                    // InternalN4MFParser.g:883:1: RightParenthesis
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
    // InternalN4MFParser.g:895:1: rule__VersionConstraint__Alternatives_0_2_0_2 : ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:899:1: ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) )
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
                    // InternalN4MFParser.g:900:1: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    {
                    // InternalN4MFParser.g:900:1: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    // InternalN4MFParser.g:901:1: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundAssignment_0_2_0_2_0()); 
                    // InternalN4MFParser.g:902:1: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    // InternalN4MFParser.g:902:2: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0
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
                    // InternalN4MFParser.g:906:6: ( RightSquareBracket )
                    {
                    // InternalN4MFParser.g:906:6: ( RightSquareBracket )
                    // InternalN4MFParser.g:907:1: RightSquareBracket
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
    // InternalN4MFParser.g:919:1: rule__N4mfIdentifier__Alternatives : ( ( RULE_ID ) | ( ArtifactId ) | ( VendorId ) | ( ProjectName ) | ( VendorName ) | ( ProjectType ) | ( ProjectVersion ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_12__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_16__0 ) ) | ( Content ) | ( Test ) );
    public final void rule__N4mfIdentifier__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:923:1: ( ( RULE_ID ) | ( ArtifactId ) | ( VendorId ) | ( ProjectName ) | ( VendorName ) | ( ProjectType ) | ( ProjectVersion ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_12__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_16__0 ) ) | ( Content ) | ( Test ) )
            int alt6=19;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt6=1;
                }
                break;
            case ArtifactId:
                {
                alt6=2;
                }
                break;
            case VendorId:
                {
                alt6=3;
                }
                break;
            case ProjectName:
                {
                alt6=4;
                }
                break;
            case VendorName:
                {
                alt6=5;
                }
                break;
            case ProjectType:
                {
                alt6=6;
                }
                break;
            case ProjectVersion:
                {
                alt6=7;
                }
                break;
            case Output:
                {
                alt6=8;
                }
                break;
            case Libraries:
                {
                alt6=9;
                }
                break;
            case Resources:
                {
                alt6=10;
                }
                break;
            case Sources:
                {
                alt6=11;
                }
                break;
            case ModuleFilters:
                {
                alt6=12;
                }
                break;
            case ProjectDependencies:
                {
                alt6=13;
                }
                break;
            case API:
                {
                alt6=14;
                }
                break;
            case User:
                {
                alt6=15;
                }
                break;
            case Application:
                {
                alt6=16;
                }
                break;
            case Processor:
                {
                alt6=17;
                }
                break;
            case Content:
                {
                alt6=18;
                }
                break;
            case Test:
                {
                alt6=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalN4MFParser.g:924:1: ( RULE_ID )
                    {
                    // InternalN4MFParser.g:924:1: ( RULE_ID )
                    // InternalN4MFParser.g:925:1: RULE_ID
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:930:6: ( ArtifactId )
                    {
                    // InternalN4MFParser.g:930:6: ( ArtifactId )
                    // InternalN4MFParser.g:931:1: ArtifactId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 
                    match(input,ArtifactId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:938:6: ( VendorId )
                    {
                    // InternalN4MFParser.g:938:6: ( VendorId )
                    // InternalN4MFParser.g:939:1: VendorId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_2()); 
                    match(input,VendorId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:946:6: ( ProjectName )
                    {
                    // InternalN4MFParser.g:946:6: ( ProjectName )
                    // InternalN4MFParser.g:947:1: ProjectName
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_3()); 
                    match(input,ProjectName,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:954:6: ( VendorName )
                    {
                    // InternalN4MFParser.g:954:6: ( VendorName )
                    // InternalN4MFParser.g:955:1: VendorName
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_4()); 
                    match(input,VendorName,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:962:6: ( ProjectType )
                    {
                    // InternalN4MFParser.g:962:6: ( ProjectType )
                    // InternalN4MFParser.g:963:1: ProjectType
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_5()); 
                    match(input,ProjectType,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:970:6: ( ProjectVersion )
                    {
                    // InternalN4MFParser.g:970:6: ( ProjectVersion )
                    // InternalN4MFParser.g:971:1: ProjectVersion
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_6()); 
                    match(input,ProjectVersion,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:978:6: ( Output )
                    {
                    // InternalN4MFParser.g:978:6: ( Output )
                    // InternalN4MFParser.g:979:1: Output
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_7()); 
                    match(input,Output,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:986:6: ( Libraries )
                    {
                    // InternalN4MFParser.g:986:6: ( Libraries )
                    // InternalN4MFParser.g:987:1: Libraries
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_8()); 
                    match(input,Libraries,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:994:6: ( Resources )
                    {
                    // InternalN4MFParser.g:994:6: ( Resources )
                    // InternalN4MFParser.g:995:1: Resources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_9()); 
                    match(input,Resources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:1002:6: ( Sources )
                    {
                    // InternalN4MFParser.g:1002:6: ( Sources )
                    // InternalN4MFParser.g:1003:1: Sources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_10()); 
                    match(input,Sources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:1010:6: ( ModuleFilters )
                    {
                    // InternalN4MFParser.g:1010:6: ( ModuleFilters )
                    // InternalN4MFParser.g:1011:1: ModuleFilters
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_11()); 
                    match(input,ModuleFilters,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_11()); 

                    }


                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:1018:6: ( ( rule__N4mfIdentifier__Group_12__0 ) )
                    {
                    // InternalN4MFParser.g:1018:6: ( ( rule__N4mfIdentifier__Group_12__0 ) )
                    // InternalN4MFParser.g:1019:1: ( rule__N4mfIdentifier__Group_12__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_12()); 
                    // InternalN4MFParser.g:1020:1: ( rule__N4mfIdentifier__Group_12__0 )
                    // InternalN4MFParser.g:1020:2: rule__N4mfIdentifier__Group_12__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_12__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_12()); 

                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:1024:6: ( API )
                    {
                    // InternalN4MFParser.g:1024:6: ( API )
                    // InternalN4MFParser.g:1025:1: API
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_13()); 
                    match(input,API,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_13()); 

                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:1032:6: ( User )
                    {
                    // InternalN4MFParser.g:1032:6: ( User )
                    // InternalN4MFParser.g:1033:1: User
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_14()); 
                    match(input,User,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:1040:6: ( Application )
                    {
                    // InternalN4MFParser.g:1040:6: ( Application )
                    // InternalN4MFParser.g:1041:1: Application
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_15()); 
                    match(input,Application,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_15()); 

                    }


                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:1048:6: ( ( rule__N4mfIdentifier__Group_16__0 ) )
                    {
                    // InternalN4MFParser.g:1048:6: ( ( rule__N4mfIdentifier__Group_16__0 ) )
                    // InternalN4MFParser.g:1049:1: ( rule__N4mfIdentifier__Group_16__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_16()); 
                    // InternalN4MFParser.g:1050:1: ( rule__N4mfIdentifier__Group_16__0 )
                    // InternalN4MFParser.g:1050:2: rule__N4mfIdentifier__Group_16__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_16__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_16()); 

                    }


                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:1054:6: ( Content )
                    {
                    // InternalN4MFParser.g:1054:6: ( Content )
                    // InternalN4MFParser.g:1055:1: Content
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_17()); 
                    match(input,Content,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_17()); 

                    }


                    }
                    break;
                case 19 :
                    // InternalN4MFParser.g:1062:6: ( Test )
                    {
                    // InternalN4MFParser.g:1062:6: ( Test )
                    // InternalN4MFParser.g:1063:1: Test
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_18()); 
                    match(input,Test,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_18()); 

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
    // InternalN4MFParser.g:1075:1: rule__ProjectType__Alternatives : ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) );
    public final void rule__ProjectType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1079:1: ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) )
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
                    // InternalN4MFParser.g:1080:1: ( ( Application ) )
                    {
                    // InternalN4MFParser.g:1080:1: ( ( Application ) )
                    // InternalN4MFParser.g:1081:1: ( Application )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1082:1: ( Application )
                    // InternalN4MFParser.g:1082:3: Application
                    {
                    match(input,Application,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1087:6: ( ( Processor ) )
                    {
                    // InternalN4MFParser.g:1087:6: ( ( Processor ) )
                    // InternalN4MFParser.g:1088:1: ( Processor )
                    {
                     before(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1089:1: ( Processor )
                    // InternalN4MFParser.g:1089:3: Processor
                    {
                    match(input,Processor,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1094:6: ( ( Library ) )
                    {
                    // InternalN4MFParser.g:1094:6: ( ( Library ) )
                    // InternalN4MFParser.g:1095:1: ( Library )
                    {
                     before(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1096:1: ( Library )
                    // InternalN4MFParser.g:1096:3: Library
                    {
                    match(input,Library,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:1101:6: ( ( API ) )
                    {
                    // InternalN4MFParser.g:1101:6: ( ( API ) )
                    // InternalN4MFParser.g:1102:1: ( API )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 
                    // InternalN4MFParser.g:1103:1: ( API )
                    // InternalN4MFParser.g:1103:3: API
                    {
                    match(input,API,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:1108:6: ( ( RuntimeEnvironment ) )
                    {
                    // InternalN4MFParser.g:1108:6: ( ( RuntimeEnvironment ) )
                    // InternalN4MFParser.g:1109:1: ( RuntimeEnvironment )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 
                    // InternalN4MFParser.g:1110:1: ( RuntimeEnvironment )
                    // InternalN4MFParser.g:1110:3: RuntimeEnvironment
                    {
                    match(input,RuntimeEnvironment,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:1115:6: ( ( RuntimeLibrary ) )
                    {
                    // InternalN4MFParser.g:1115:6: ( ( RuntimeLibrary ) )
                    // InternalN4MFParser.g:1116:1: ( RuntimeLibrary )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 
                    // InternalN4MFParser.g:1117:1: ( RuntimeLibrary )
                    // InternalN4MFParser.g:1117:3: RuntimeLibrary
                    {
                    match(input,RuntimeLibrary,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:1122:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1122:6: ( ( Test ) )
                    // InternalN4MFParser.g:1123:1: ( Test )
                    {
                     before(grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6()); 
                    // InternalN4MFParser.g:1124:1: ( Test )
                    // InternalN4MFParser.g:1124:3: Test
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
    // InternalN4MFParser.g:1134:1: rule__SourceFragmentType__Alternatives : ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) );
    public final void rule__SourceFragmentType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1138:1: ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) )
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
                    // InternalN4MFParser.g:1139:1: ( ( Source ) )
                    {
                    // InternalN4MFParser.g:1139:1: ( ( Source ) )
                    // InternalN4MFParser.g:1140:1: ( Source )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1141:1: ( Source )
                    // InternalN4MFParser.g:1141:3: Source
                    {
                    match(input,Source,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1146:6: ( ( External ) )
                    {
                    // InternalN4MFParser.g:1146:6: ( ( External ) )
                    // InternalN4MFParser.g:1147:1: ( External )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1148:1: ( External )
                    // InternalN4MFParser.g:1148:3: External
                    {
                    match(input,External,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1153:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1153:6: ( ( Test ) )
                    // InternalN4MFParser.g:1154:1: ( Test )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1155:1: ( Test )
                    // InternalN4MFParser.g:1155:3: Test
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
    // InternalN4MFParser.g:1165:1: rule__ModuleFilterType__Alternatives : ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) );
    public final void rule__ModuleFilterType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1169:1: ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) )
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
                    // InternalN4MFParser.g:1170:1: ( ( NoValidate ) )
                    {
                    // InternalN4MFParser.g:1170:1: ( ( NoValidate ) )
                    // InternalN4MFParser.g:1171:1: ( NoValidate )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1172:1: ( NoValidate )
                    // InternalN4MFParser.g:1172:3: NoValidate
                    {
                    match(input,NoValidate,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1177:6: ( ( NoModuleWrap ) )
                    {
                    // InternalN4MFParser.g:1177:6: ( ( NoModuleWrap ) )
                    // InternalN4MFParser.g:1178:1: ( NoModuleWrap )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1179:1: ( NoModuleWrap )
                    // InternalN4MFParser.g:1179:3: NoModuleWrap
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
    // InternalN4MFParser.g:1189:1: rule__ProjectDependencyScope__Alternatives : ( ( ( Compile ) ) | ( ( Test ) ) );
    public final void rule__ProjectDependencyScope__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1193:1: ( ( ( Compile ) ) | ( ( Test ) ) )
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
                    // InternalN4MFParser.g:1194:1: ( ( Compile ) )
                    {
                    // InternalN4MFParser.g:1194:1: ( ( Compile ) )
                    // InternalN4MFParser.g:1195:1: ( Compile )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1196:1: ( Compile )
                    // InternalN4MFParser.g:1196:3: Compile
                    {
                    match(input,Compile,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1201:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1201:6: ( ( Test ) )
                    // InternalN4MFParser.g:1202:1: ( Test )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1203:1: ( Test )
                    // InternalN4MFParser.g:1203:3: Test
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
    // InternalN4MFParser.g:1213:1: rule__ModuleLoader__Alternatives : ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) );
    public final void rule__ModuleLoader__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1217:1: ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) )
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
                    // InternalN4MFParser.g:1218:1: ( ( N4js ) )
                    {
                    // InternalN4MFParser.g:1218:1: ( ( N4js ) )
                    // InternalN4MFParser.g:1219:1: ( N4js )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1220:1: ( N4js )
                    // InternalN4MFParser.g:1220:3: N4js
                    {
                    match(input,N4js,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1225:6: ( ( Commonjs ) )
                    {
                    // InternalN4MFParser.g:1225:6: ( ( Commonjs ) )
                    // InternalN4MFParser.g:1226:1: ( Commonjs )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1227:1: ( Commonjs )
                    // InternalN4MFParser.g:1227:3: Commonjs
                    {
                    match(input,Commonjs,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1232:6: ( ( Node_builtin ) )
                    {
                    // InternalN4MFParser.g:1232:6: ( ( Node_builtin ) )
                    // InternalN4MFParser.g:1233:1: ( Node_builtin )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1234:1: ( Node_builtin )
                    // InternalN4MFParser.g:1234:3: Node_builtin
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
    // InternalN4MFParser.g:1246:1: rule__ProjectDescription__Group_0__0 : rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 ;
    public final void rule__ProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1250:1: ( rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:1251:2: rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1
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
    // InternalN4MFParser.g:1258:1: rule__ProjectDescription__Group_0__0__Impl : ( ArtifactId ) ;
    public final void rule__ProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1262:1: ( ( ArtifactId ) )
            // InternalN4MFParser.g:1263:1: ( ArtifactId )
            {
            // InternalN4MFParser.g:1263:1: ( ArtifactId )
            // InternalN4MFParser.g:1264:1: ArtifactId
            {
             before(grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0()); 
            match(input,ArtifactId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1277:1: rule__ProjectDescription__Group_0__1 : rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 ;
    public final void rule__ProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1281:1: ( rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 )
            // InternalN4MFParser.g:1282:2: rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2
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
    // InternalN4MFParser.g:1289:1: rule__ProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1293:1: ( ( Colon ) )
            // InternalN4MFParser.g:1294:1: ( Colon )
            {
            // InternalN4MFParser.g:1294:1: ( Colon )
            // InternalN4MFParser.g:1295:1: Colon
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
    // InternalN4MFParser.g:1308:1: rule__ProjectDescription__Group_0__2 : rule__ProjectDescription__Group_0__2__Impl ;
    public final void rule__ProjectDescription__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1312:1: ( rule__ProjectDescription__Group_0__2__Impl )
            // InternalN4MFParser.g:1313:2: rule__ProjectDescription__Group_0__2__Impl
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
    // InternalN4MFParser.g:1319:1: rule__ProjectDescription__Group_0__2__Impl : ( ( rule__ProjectDescription__ArtifactIdAssignment_0_2 ) ) ;
    public final void rule__ProjectDescription__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1323:1: ( ( ( rule__ProjectDescription__ArtifactIdAssignment_0_2 ) ) )
            // InternalN4MFParser.g:1324:1: ( ( rule__ProjectDescription__ArtifactIdAssignment_0_2 ) )
            {
            // InternalN4MFParser.g:1324:1: ( ( rule__ProjectDescription__ArtifactIdAssignment_0_2 ) )
            // InternalN4MFParser.g:1325:1: ( rule__ProjectDescription__ArtifactIdAssignment_0_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getArtifactIdAssignment_0_2()); 
            // InternalN4MFParser.g:1326:1: ( rule__ProjectDescription__ArtifactIdAssignment_0_2 )
            // InternalN4MFParser.g:1326:2: rule__ProjectDescription__ArtifactIdAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ArtifactIdAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getArtifactIdAssignment_0_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1342:1: rule__ProjectDescription__Group_1__0 : rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 ;
    public final void rule__ProjectDescription__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1346:1: ( rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 )
            // InternalN4MFParser.g:1347:2: rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1
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
    // InternalN4MFParser.g:1354:1: rule__ProjectDescription__Group_1__0__Impl : ( ProjectName ) ;
    public final void rule__ProjectDescription__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1358:1: ( ( ProjectName ) )
            // InternalN4MFParser.g:1359:1: ( ProjectName )
            {
            // InternalN4MFParser.g:1359:1: ( ProjectName )
            // InternalN4MFParser.g:1360:1: ProjectName
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectNameKeyword_1_0()); 
            match(input,ProjectName,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectNameKeyword_1_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1373:1: rule__ProjectDescription__Group_1__1 : rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 ;
    public final void rule__ProjectDescription__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1377:1: ( rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 )
            // InternalN4MFParser.g:1378:2: rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2
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
    // InternalN4MFParser.g:1385:1: rule__ProjectDescription__Group_1__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1389:1: ( ( Colon ) )
            // InternalN4MFParser.g:1390:1: ( Colon )
            {
            // InternalN4MFParser.g:1390:1: ( Colon )
            // InternalN4MFParser.g:1391:1: Colon
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
    // InternalN4MFParser.g:1404:1: rule__ProjectDescription__Group_1__2 : rule__ProjectDescription__Group_1__2__Impl ;
    public final void rule__ProjectDescription__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1408:1: ( rule__ProjectDescription__Group_1__2__Impl )
            // InternalN4MFParser.g:1409:2: rule__ProjectDescription__Group_1__2__Impl
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
    // InternalN4MFParser.g:1415:1: rule__ProjectDescription__Group_1__2__Impl : ( ( rule__ProjectDescription__ProjectNameAssignment_1_2 ) ) ;
    public final void rule__ProjectDescription__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1419:1: ( ( ( rule__ProjectDescription__ProjectNameAssignment_1_2 ) ) )
            // InternalN4MFParser.g:1420:1: ( ( rule__ProjectDescription__ProjectNameAssignment_1_2 ) )
            {
            // InternalN4MFParser.g:1420:1: ( ( rule__ProjectDescription__ProjectNameAssignment_1_2 ) )
            // InternalN4MFParser.g:1421:1: ( rule__ProjectDescription__ProjectNameAssignment_1_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectNameAssignment_1_2()); 
            // InternalN4MFParser.g:1422:1: ( rule__ProjectDescription__ProjectNameAssignment_1_2 )
            // InternalN4MFParser.g:1422:2: rule__ProjectDescription__ProjectNameAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectNameAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectNameAssignment_1_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1438:1: rule__ProjectDescription__Group_2__0 : rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 ;
    public final void rule__ProjectDescription__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1442:1: ( rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 )
            // InternalN4MFParser.g:1443:2: rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1
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
    // InternalN4MFParser.g:1450:1: rule__ProjectDescription__Group_2__0__Impl : ( ProjectType ) ;
    public final void rule__ProjectDescription__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1454:1: ( ( ProjectType ) )
            // InternalN4MFParser.g:1455:1: ( ProjectType )
            {
            // InternalN4MFParser.g:1455:1: ( ProjectType )
            // InternalN4MFParser.g:1456:1: ProjectType
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_2_0()); 
            match(input,ProjectType,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_2_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1469:1: rule__ProjectDescription__Group_2__1 : rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 ;
    public final void rule__ProjectDescription__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1473:1: ( rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 )
            // InternalN4MFParser.g:1474:2: rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2
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
    // InternalN4MFParser.g:1481:1: rule__ProjectDescription__Group_2__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1485:1: ( ( Colon ) )
            // InternalN4MFParser.g:1486:1: ( Colon )
            {
            // InternalN4MFParser.g:1486:1: ( Colon )
            // InternalN4MFParser.g:1487:1: Colon
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
    // InternalN4MFParser.g:1500:1: rule__ProjectDescription__Group_2__2 : rule__ProjectDescription__Group_2__2__Impl ;
    public final void rule__ProjectDescription__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1504:1: ( rule__ProjectDescription__Group_2__2__Impl )
            // InternalN4MFParser.g:1505:2: rule__ProjectDescription__Group_2__2__Impl
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
    // InternalN4MFParser.g:1511:1: rule__ProjectDescription__Group_2__2__Impl : ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) ) ;
    public final void rule__ProjectDescription__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1515:1: ( ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) ) )
            // InternalN4MFParser.g:1516:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) )
            {
            // InternalN4MFParser.g:1516:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) )
            // InternalN4MFParser.g:1517:1: ( rule__ProjectDescription__ProjectTypeAssignment_2_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeAssignment_2_2()); 
            // InternalN4MFParser.g:1518:1: ( rule__ProjectDescription__ProjectTypeAssignment_2_2 )
            // InternalN4MFParser.g:1518:2: rule__ProjectDescription__ProjectTypeAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectTypeAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeAssignment_2_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1534:1: rule__ProjectDescription__Group_3__0 : rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 ;
    public final void rule__ProjectDescription__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1538:1: ( rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 )
            // InternalN4MFParser.g:1539:2: rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1
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
    // InternalN4MFParser.g:1546:1: rule__ProjectDescription__Group_3__0__Impl : ( ProjectVersion ) ;
    public final void rule__ProjectDescription__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1550:1: ( ( ProjectVersion ) )
            // InternalN4MFParser.g:1551:1: ( ProjectVersion )
            {
            // InternalN4MFParser.g:1551:1: ( ProjectVersion )
            // InternalN4MFParser.g:1552:1: ProjectVersion
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_3_0()); 
            match(input,ProjectVersion,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_3_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1565:1: rule__ProjectDescription__Group_3__1 : rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 ;
    public final void rule__ProjectDescription__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1569:1: ( rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 )
            // InternalN4MFParser.g:1570:2: rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2
            {
            pushFollow(FOLLOW_7);
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
    // InternalN4MFParser.g:1577:1: rule__ProjectDescription__Group_3__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1581:1: ( ( Colon ) )
            // InternalN4MFParser.g:1582:1: ( Colon )
            {
            // InternalN4MFParser.g:1582:1: ( Colon )
            // InternalN4MFParser.g:1583:1: Colon
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
    // InternalN4MFParser.g:1596:1: rule__ProjectDescription__Group_3__2 : rule__ProjectDescription__Group_3__2__Impl ;
    public final void rule__ProjectDescription__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1600:1: ( rule__ProjectDescription__Group_3__2__Impl )
            // InternalN4MFParser.g:1601:2: rule__ProjectDescription__Group_3__2__Impl
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
    // InternalN4MFParser.g:1607:1: rule__ProjectDescription__Group_3__2__Impl : ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) ) ;
    public final void rule__ProjectDescription__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1611:1: ( ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) ) )
            // InternalN4MFParser.g:1612:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) )
            {
            // InternalN4MFParser.g:1612:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) )
            // InternalN4MFParser.g:1613:1: ( rule__ProjectDescription__ProjectVersionAssignment_3_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionAssignment_3_2()); 
            // InternalN4MFParser.g:1614:1: ( rule__ProjectDescription__ProjectVersionAssignment_3_2 )
            // InternalN4MFParser.g:1614:2: rule__ProjectDescription__ProjectVersionAssignment_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ProjectVersionAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionAssignment_3_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1630:1: rule__ProjectDescription__Group_4__0 : rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 ;
    public final void rule__ProjectDescription__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1634:1: ( rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 )
            // InternalN4MFParser.g:1635:2: rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1
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
    // InternalN4MFParser.g:1642:1: rule__ProjectDescription__Group_4__0__Impl : ( VendorId ) ;
    public final void rule__ProjectDescription__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1646:1: ( ( VendorId ) )
            // InternalN4MFParser.g:1647:1: ( VendorId )
            {
            // InternalN4MFParser.g:1647:1: ( VendorId )
            // InternalN4MFParser.g:1648:1: VendorId
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_4_0()); 
            match(input,VendorId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_4_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1661:1: rule__ProjectDescription__Group_4__1 : rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 ;
    public final void rule__ProjectDescription__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1665:1: ( rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 )
            // InternalN4MFParser.g:1666:2: rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2
            {
            pushFollow(FOLLOW_4);
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
    // InternalN4MFParser.g:1673:1: rule__ProjectDescription__Group_4__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1677:1: ( ( Colon ) )
            // InternalN4MFParser.g:1678:1: ( Colon )
            {
            // InternalN4MFParser.g:1678:1: ( Colon )
            // InternalN4MFParser.g:1679:1: Colon
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
    // InternalN4MFParser.g:1692:1: rule__ProjectDescription__Group_4__2 : rule__ProjectDescription__Group_4__2__Impl ;
    public final void rule__ProjectDescription__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1696:1: ( rule__ProjectDescription__Group_4__2__Impl )
            // InternalN4MFParser.g:1697:2: rule__ProjectDescription__Group_4__2__Impl
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
    // InternalN4MFParser.g:1703:1: rule__ProjectDescription__Group_4__2__Impl : ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) ) ;
    public final void rule__ProjectDescription__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1707:1: ( ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) ) )
            // InternalN4MFParser.g:1708:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) )
            {
            // InternalN4MFParser.g:1708:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) )
            // InternalN4MFParser.g:1709:1: ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdAssignment_4_2()); 
            // InternalN4MFParser.g:1710:1: ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 )
            // InternalN4MFParser.g:1710:2: rule__ProjectDescription__DeclaredVendorIdAssignment_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__DeclaredVendorIdAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdAssignment_4_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1726:1: rule__ProjectDescription__Group_5__0 : rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 ;
    public final void rule__ProjectDescription__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1730:1: ( rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 )
            // InternalN4MFParser.g:1731:2: rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1
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
    // InternalN4MFParser.g:1738:1: rule__ProjectDescription__Group_5__0__Impl : ( VendorName ) ;
    public final void rule__ProjectDescription__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1742:1: ( ( VendorName ) )
            // InternalN4MFParser.g:1743:1: ( VendorName )
            {
            // InternalN4MFParser.g:1743:1: ( VendorName )
            // InternalN4MFParser.g:1744:1: VendorName
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_5_0()); 
            match(input,VendorName,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_5_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1757:1: rule__ProjectDescription__Group_5__1 : rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 ;
    public final void rule__ProjectDescription__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1761:1: ( rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 )
            // InternalN4MFParser.g:1762:2: rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:1769:1: rule__ProjectDescription__Group_5__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1773:1: ( ( Colon ) )
            // InternalN4MFParser.g:1774:1: ( Colon )
            {
            // InternalN4MFParser.g:1774:1: ( Colon )
            // InternalN4MFParser.g:1775:1: Colon
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
    // InternalN4MFParser.g:1788:1: rule__ProjectDescription__Group_5__2 : rule__ProjectDescription__Group_5__2__Impl ;
    public final void rule__ProjectDescription__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1792:1: ( rule__ProjectDescription__Group_5__2__Impl )
            // InternalN4MFParser.g:1793:2: rule__ProjectDescription__Group_5__2__Impl
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
    // InternalN4MFParser.g:1799:1: rule__ProjectDescription__Group_5__2__Impl : ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) ) ;
    public final void rule__ProjectDescription__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1803:1: ( ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) ) )
            // InternalN4MFParser.g:1804:1: ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) )
            {
            // InternalN4MFParser.g:1804:1: ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) )
            // InternalN4MFParser.g:1805:1: ( rule__ProjectDescription__VendorNameAssignment_5_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameAssignment_5_2()); 
            // InternalN4MFParser.g:1806:1: ( rule__ProjectDescription__VendorNameAssignment_5_2 )
            // InternalN4MFParser.g:1806:2: rule__ProjectDescription__VendorNameAssignment_5_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__VendorNameAssignment_5_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getVendorNameAssignment_5_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ProjectDescription__Group_6__0"
    // InternalN4MFParser.g:1822:1: rule__ProjectDescription__Group_6__0 : rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1 ;
    public final void rule__ProjectDescription__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1826:1: ( rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1 )
            // InternalN4MFParser.g:1827:2: rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_6__1();

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
    // $ANTLR end "rule__ProjectDescription__Group_6__0"


    // $ANTLR start "rule__ProjectDescription__Group_6__0__Impl"
    // InternalN4MFParser.g:1834:1: rule__ProjectDescription__Group_6__0__Impl : ( MainModule ) ;
    public final void rule__ProjectDescription__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1838:1: ( ( MainModule ) )
            // InternalN4MFParser.g:1839:1: ( MainModule )
            {
            // InternalN4MFParser.g:1839:1: ( MainModule )
            // InternalN4MFParser.g:1840:1: MainModule
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_6_0()); 
            match(input,MainModule,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_6__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_6__1"
    // InternalN4MFParser.g:1853:1: rule__ProjectDescription__Group_6__1 : rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2 ;
    public final void rule__ProjectDescription__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1857:1: ( rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2 )
            // InternalN4MFParser.g:1858:2: rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2
            {
            pushFollow(FOLLOW_5);
            rule__ProjectDescription__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_6__2();

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
    // $ANTLR end "rule__ProjectDescription__Group_6__1"


    // $ANTLR start "rule__ProjectDescription__Group_6__1__Impl"
    // InternalN4MFParser.g:1865:1: rule__ProjectDescription__Group_6__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1869:1: ( ( Colon ) )
            // InternalN4MFParser.g:1870:1: ( Colon )
            {
            // InternalN4MFParser.g:1870:1: ( Colon )
            // InternalN4MFParser.g:1871:1: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_6_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_6__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_6__2"
    // InternalN4MFParser.g:1884:1: rule__ProjectDescription__Group_6__2 : rule__ProjectDescription__Group_6__2__Impl ;
    public final void rule__ProjectDescription__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1888:1: ( rule__ProjectDescription__Group_6__2__Impl )
            // InternalN4MFParser.g:1889:2: rule__ProjectDescription__Group_6__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_6__2__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_6__2"


    // $ANTLR start "rule__ProjectDescription__Group_6__2__Impl"
    // InternalN4MFParser.g:1895:1: rule__ProjectDescription__Group_6__2__Impl : ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) ) ;
    public final void rule__ProjectDescription__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1899:1: ( ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) ) )
            // InternalN4MFParser.g:1900:1: ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) )
            {
            // InternalN4MFParser.g:1900:1: ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) )
            // InternalN4MFParser.g:1901:1: ( rule__ProjectDescription__MainModuleAssignment_6_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleAssignment_6_2()); 
            // InternalN4MFParser.g:1902:1: ( rule__ProjectDescription__MainModuleAssignment_6_2 )
            // InternalN4MFParser.g:1902:2: rule__ProjectDescription__MainModuleAssignment_6_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__MainModuleAssignment_6_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getMainModuleAssignment_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_6__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_11__0"
    // InternalN4MFParser.g:1918:1: rule__ProjectDescription__Group_11__0 : rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1 ;
    public final void rule__ProjectDescription__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1922:1: ( rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1 )
            // InternalN4MFParser.g:1923:2: rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_11__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_11__1();

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
    // $ANTLR end "rule__ProjectDescription__Group_11__0"


    // $ANTLR start "rule__ProjectDescription__Group_11__0__Impl"
    // InternalN4MFParser.g:1930:1: rule__ProjectDescription__Group_11__0__Impl : ( ImplementationId ) ;
    public final void rule__ProjectDescription__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1934:1: ( ( ImplementationId ) )
            // InternalN4MFParser.g:1935:1: ( ImplementationId )
            {
            // InternalN4MFParser.g:1935:1: ( ImplementationId )
            // InternalN4MFParser.g:1936:1: ImplementationId
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_11_0()); 
            match(input,ImplementationId,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_11_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_11__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_11__1"
    // InternalN4MFParser.g:1949:1: rule__ProjectDescription__Group_11__1 : rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2 ;
    public final void rule__ProjectDescription__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1953:1: ( rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2 )
            // InternalN4MFParser.g:1954:2: rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2
            {
            pushFollow(FOLLOW_4);
            rule__ProjectDescription__Group_11__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_11__2();

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
    // $ANTLR end "rule__ProjectDescription__Group_11__1"


    // $ANTLR start "rule__ProjectDescription__Group_11__1__Impl"
    // InternalN4MFParser.g:1961:1: rule__ProjectDescription__Group_11__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1965:1: ( ( Colon ) )
            // InternalN4MFParser.g:1966:1: ( Colon )
            {
            // InternalN4MFParser.g:1966:1: ( Colon )
            // InternalN4MFParser.g:1967:1: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_11_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_11_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_11__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_11__2"
    // InternalN4MFParser.g:1980:1: rule__ProjectDescription__Group_11__2 : rule__ProjectDescription__Group_11__2__Impl ;
    public final void rule__ProjectDescription__Group_11__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1984:1: ( rule__ProjectDescription__Group_11__2__Impl )
            // InternalN4MFParser.g:1985:2: rule__ProjectDescription__Group_11__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_11__2__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_11__2"


    // $ANTLR start "rule__ProjectDescription__Group_11__2__Impl"
    // InternalN4MFParser.g:1991:1: rule__ProjectDescription__Group_11__2__Impl : ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) ) ;
    public final void rule__ProjectDescription__Group_11__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1995:1: ( ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) ) )
            // InternalN4MFParser.g:1996:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) )
            {
            // InternalN4MFParser.g:1996:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) )
            // InternalN4MFParser.g:1997:1: ( rule__ProjectDescription__ImplementationIdAssignment_11_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdAssignment_11_2()); 
            // InternalN4MFParser.g:1998:1: ( rule__ProjectDescription__ImplementationIdAssignment_11_2 )
            // InternalN4MFParser.g:1998:2: rule__ProjectDescription__ImplementationIdAssignment_11_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ImplementationIdAssignment_11_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdAssignment_11_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_11__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_15__0"
    // InternalN4MFParser.g:2014:1: rule__ProjectDescription__Group_15__0 : rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 ;
    public final void rule__ProjectDescription__Group_15__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2018:1: ( rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 )
            // InternalN4MFParser.g:2019:2: rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1
            {
            pushFollow(FOLLOW_3);
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
    // InternalN4MFParser.g:2026:1: rule__ProjectDescription__Group_15__0__Impl : ( Output ) ;
    public final void rule__ProjectDescription__Group_15__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2030:1: ( ( Output ) )
            // InternalN4MFParser.g:2031:1: ( Output )
            {
            // InternalN4MFParser.g:2031:1: ( Output )
            // InternalN4MFParser.g:2032:1: Output
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputKeyword_15_0()); 
            match(input,Output,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getOutputKeyword_15_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2045:1: rule__ProjectDescription__Group_15__1 : rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 ;
    public final void rule__ProjectDescription__Group_15__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2049:1: ( rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 )
            // InternalN4MFParser.g:2050:2: rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:2057:1: rule__ProjectDescription__Group_15__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_15__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2061:1: ( ( Colon ) )
            // InternalN4MFParser.g:2062:1: ( Colon )
            {
            // InternalN4MFParser.g:2062:1: ( Colon )
            // InternalN4MFParser.g:2063:1: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_15_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_15_1()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2076:1: rule__ProjectDescription__Group_15__2 : rule__ProjectDescription__Group_15__2__Impl ;
    public final void rule__ProjectDescription__Group_15__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2080:1: ( rule__ProjectDescription__Group_15__2__Impl )
            // InternalN4MFParser.g:2081:2: rule__ProjectDescription__Group_15__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_15__2__Impl();

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
    // InternalN4MFParser.g:2087:1: rule__ProjectDescription__Group_15__2__Impl : ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) ) ;
    public final void rule__ProjectDescription__Group_15__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2091:1: ( ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) ) )
            // InternalN4MFParser.g:2092:1: ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) )
            {
            // InternalN4MFParser.g:2092:1: ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) )
            // InternalN4MFParser.g:2093:1: ( rule__ProjectDescription__OutputPathAssignment_15_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputPathAssignment_15_2()); 
            // InternalN4MFParser.g:2094:1: ( rule__ProjectDescription__OutputPathAssignment_15_2 )
            // InternalN4MFParser.g:2094:2: rule__ProjectDescription__OutputPathAssignment_15_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__OutputPathAssignment_15_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getOutputPathAssignment_15_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ProjectDescription__Group_16__0"
    // InternalN4MFParser.g:2110:1: rule__ProjectDescription__Group_16__0 : rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 ;
    public final void rule__ProjectDescription__Group_16__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2114:1: ( rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 )
            // InternalN4MFParser.g:2115:2: rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1
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
    // InternalN4MFParser.g:2122:1: rule__ProjectDescription__Group_16__0__Impl : ( Libraries ) ;
    public final void rule__ProjectDescription__Group_16__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2126:1: ( ( Libraries ) )
            // InternalN4MFParser.g:2127:1: ( Libraries )
            {
            // InternalN4MFParser.g:2127:1: ( Libraries )
            // InternalN4MFParser.g:2128:1: Libraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_16_0()); 
            match(input,Libraries,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_16_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2141:1: rule__ProjectDescription__Group_16__1 : rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 ;
    public final void rule__ProjectDescription__Group_16__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2145:1: ( rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 )
            // InternalN4MFParser.g:2146:2: rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:2153:1: rule__ProjectDescription__Group_16__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2157:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2158:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2158:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2159:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2172:1: rule__ProjectDescription__Group_16__2 : rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 ;
    public final void rule__ProjectDescription__Group_16__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2176:1: ( rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 )
            // InternalN4MFParser.g:2177:2: rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3
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
    // InternalN4MFParser.g:2184:1: rule__ProjectDescription__Group_16__2__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) ) ;
    public final void rule__ProjectDescription__Group_16__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2188:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) ) )
            // InternalN4MFParser.g:2189:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) )
            {
            // InternalN4MFParser.g:2189:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) )
            // InternalN4MFParser.g:2190:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_2()); 
            // InternalN4MFParser.g:2191:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_2 )
            // InternalN4MFParser.g:2191:2: rule__ProjectDescription__LibraryPathsAssignment_16_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__LibraryPathsAssignment_16_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2201:1: rule__ProjectDescription__Group_16__3 : rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 ;
    public final void rule__ProjectDescription__Group_16__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2205:1: ( rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 )
            // InternalN4MFParser.g:2206:2: rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4
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
    // InternalN4MFParser.g:2213:1: rule__ProjectDescription__Group_16__3__Impl : ( ( rule__ProjectDescription__Group_16_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_16__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2217:1: ( ( ( rule__ProjectDescription__Group_16_3__0 )* ) )
            // InternalN4MFParser.g:2218:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            {
            // InternalN4MFParser.g:2218:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            // InternalN4MFParser.g:2219:1: ( rule__ProjectDescription__Group_16_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_16_3()); 
            // InternalN4MFParser.g:2220:1: ( rule__ProjectDescription__Group_16_3__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==Comma) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalN4MFParser.g:2220:2: rule__ProjectDescription__Group_16_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDescription__Group_16_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // InternalN4MFParser.g:2230:1: rule__ProjectDescription__Group_16__4 : rule__ProjectDescription__Group_16__4__Impl ;
    public final void rule__ProjectDescription__Group_16__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2234:1: ( rule__ProjectDescription__Group_16__4__Impl )
            // InternalN4MFParser.g:2235:2: rule__ProjectDescription__Group_16__4__Impl
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
    // InternalN4MFParser.g:2241:1: rule__ProjectDescription__Group_16__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2245:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2246:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2246:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2247:1: RightCurlyBracket
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
    // InternalN4MFParser.g:2270:1: rule__ProjectDescription__Group_16_3__0 : rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 ;
    public final void rule__ProjectDescription__Group_16_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2274:1: ( rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 )
            // InternalN4MFParser.g:2275:2: rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:2282:1: rule__ProjectDescription__Group_16_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_16_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2286:1: ( ( Comma ) )
            // InternalN4MFParser.g:2287:1: ( Comma )
            {
            // InternalN4MFParser.g:2287:1: ( Comma )
            // InternalN4MFParser.g:2288:1: Comma
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
    // InternalN4MFParser.g:2301:1: rule__ProjectDescription__Group_16_3__1 : rule__ProjectDescription__Group_16_3__1__Impl ;
    public final void rule__ProjectDescription__Group_16_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2305:1: ( rule__ProjectDescription__Group_16_3__1__Impl )
            // InternalN4MFParser.g:2306:2: rule__ProjectDescription__Group_16_3__1__Impl
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
    // InternalN4MFParser.g:2312:1: rule__ProjectDescription__Group_16_3__1__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_16_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2316:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) ) )
            // InternalN4MFParser.g:2317:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) )
            {
            // InternalN4MFParser.g:2317:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) )
            // InternalN4MFParser.g:2318:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_3_1()); 
            // InternalN4MFParser.g:2319:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 )
            // InternalN4MFParser.g:2319:2: rule__ProjectDescription__LibraryPathsAssignment_16_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__LibraryPathsAssignment_16_3_1();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_3_1()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2333:1: rule__ProjectDescription__Group_17__0 : rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 ;
    public final void rule__ProjectDescription__Group_17__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2337:1: ( rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 )
            // InternalN4MFParser.g:2338:2: rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1
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
    // InternalN4MFParser.g:2345:1: rule__ProjectDescription__Group_17__0__Impl : ( Resources ) ;
    public final void rule__ProjectDescription__Group_17__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2349:1: ( ( Resources ) )
            // InternalN4MFParser.g:2350:1: ( Resources )
            {
            // InternalN4MFParser.g:2350:1: ( Resources )
            // InternalN4MFParser.g:2351:1: Resources
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_17_0()); 
            match(input,Resources,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_17_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2364:1: rule__ProjectDescription__Group_17__1 : rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 ;
    public final void rule__ProjectDescription__Group_17__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2368:1: ( rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 )
            // InternalN4MFParser.g:2369:2: rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:2376:1: rule__ProjectDescription__Group_17__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2380:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2381:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2381:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2382:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2395:1: rule__ProjectDescription__Group_17__2 : rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 ;
    public final void rule__ProjectDescription__Group_17__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2399:1: ( rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 )
            // InternalN4MFParser.g:2400:2: rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3
            {
            pushFollow(FOLLOW_9);
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
    // InternalN4MFParser.g:2407:1: rule__ProjectDescription__Group_17__2__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) ) ;
    public final void rule__ProjectDescription__Group_17__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2411:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) ) )
            // InternalN4MFParser.g:2412:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) )
            {
            // InternalN4MFParser.g:2412:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) )
            // InternalN4MFParser.g:2413:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_2()); 
            // InternalN4MFParser.g:2414:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_2 )
            // InternalN4MFParser.g:2414:2: rule__ProjectDescription__ResourcePathsAssignment_17_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ResourcePathsAssignment_17_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2424:1: rule__ProjectDescription__Group_17__3 : rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4 ;
    public final void rule__ProjectDescription__Group_17__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2428:1: ( rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4 )
            // InternalN4MFParser.g:2429:2: rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4
            {
            pushFollow(FOLLOW_9);
            rule__ProjectDescription__Group_17__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__4();

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
    // InternalN4MFParser.g:2436:1: rule__ProjectDescription__Group_17__3__Impl : ( ( rule__ProjectDescription__Group_17_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_17__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2440:1: ( ( ( rule__ProjectDescription__Group_17_3__0 )* ) )
            // InternalN4MFParser.g:2441:1: ( ( rule__ProjectDescription__Group_17_3__0 )* )
            {
            // InternalN4MFParser.g:2441:1: ( ( rule__ProjectDescription__Group_17_3__0 )* )
            // InternalN4MFParser.g:2442:1: ( rule__ProjectDescription__Group_17_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_17_3()); 
            // InternalN4MFParser.g:2443:1: ( rule__ProjectDescription__Group_17_3__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Comma) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalN4MFParser.g:2443:2: rule__ProjectDescription__Group_17_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDescription__Group_17_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getGroup_17_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__ProjectDescription__Group_17__4"
    // InternalN4MFParser.g:2453:1: rule__ProjectDescription__Group_17__4 : rule__ProjectDescription__Group_17__4__Impl ;
    public final void rule__ProjectDescription__Group_17__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2457:1: ( rule__ProjectDescription__Group_17__4__Impl )
            // InternalN4MFParser.g:2458:2: rule__ProjectDescription__Group_17__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17__4__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_17__4"


    // $ANTLR start "rule__ProjectDescription__Group_17__4__Impl"
    // InternalN4MFParser.g:2464:1: rule__ProjectDescription__Group_17__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2468:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2469:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2469:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2470:1: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_4()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_17__4__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17_3__0"
    // InternalN4MFParser.g:2493:1: rule__ProjectDescription__Group_17_3__0 : rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1 ;
    public final void rule__ProjectDescription__Group_17_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2497:1: ( rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1 )
            // InternalN4MFParser.g:2498:2: rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1
            {
            pushFollow(FOLLOW_5);
            rule__ProjectDescription__Group_17_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17_3__1();

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
    // $ANTLR end "rule__ProjectDescription__Group_17_3__0"


    // $ANTLR start "rule__ProjectDescription__Group_17_3__0__Impl"
    // InternalN4MFParser.g:2505:1: rule__ProjectDescription__Group_17_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_17_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2509:1: ( ( Comma ) )
            // InternalN4MFParser.g:2510:1: ( Comma )
            {
            // InternalN4MFParser.g:2510:1: ( Comma )
            // InternalN4MFParser.g:2511:1: Comma
            {
             before(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_17_3_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getCommaKeyword_17_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_17_3__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_17_3__1"
    // InternalN4MFParser.g:2524:1: rule__ProjectDescription__Group_17_3__1 : rule__ProjectDescription__Group_17_3__1__Impl ;
    public final void rule__ProjectDescription__Group_17_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2528:1: ( rule__ProjectDescription__Group_17_3__1__Impl )
            // InternalN4MFParser.g:2529:2: rule__ProjectDescription__Group_17_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_17_3__1__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_17_3__1"


    // $ANTLR start "rule__ProjectDescription__Group_17_3__1__Impl"
    // InternalN4MFParser.g:2535:1: rule__ProjectDescription__Group_17_3__1__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_17_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2539:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) ) )
            // InternalN4MFParser.g:2540:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) )
            {
            // InternalN4MFParser.g:2540:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) )
            // InternalN4MFParser.g:2541:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_3_1()); 
            // InternalN4MFParser.g:2542:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 )
            // InternalN4MFParser.g:2542:2: rule__ProjectDescription__ResourcePathsAssignment_17_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ResourcePathsAssignment_17_3_1();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_17_3__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_18__0"
    // InternalN4MFParser.g:2556:1: rule__ProjectDescription__Group_18__0 : rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 ;
    public final void rule__ProjectDescription__Group_18__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2560:1: ( rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 )
            // InternalN4MFParser.g:2561:2: rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1
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
    // InternalN4MFParser.g:2568:1: rule__ProjectDescription__Group_18__0__Impl : ( Sources ) ;
    public final void rule__ProjectDescription__Group_18__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2572:1: ( ( Sources ) )
            // InternalN4MFParser.g:2573:1: ( Sources )
            {
            // InternalN4MFParser.g:2573:1: ( Sources )
            // InternalN4MFParser.g:2574:1: Sources
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_18_0()); 
            match(input,Sources,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_18_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:2587:1: rule__ProjectDescription__Group_18__1 : rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 ;
    public final void rule__ProjectDescription__Group_18__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2591:1: ( rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 )
            // InternalN4MFParser.g:2592:2: rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2
            {
            pushFollow(FOLLOW_11);
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
    // InternalN4MFParser.g:2599:1: rule__ProjectDescription__Group_18__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2603:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2604:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2604:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2605:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2618:1: rule__ProjectDescription__Group_18__2 : rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 ;
    public final void rule__ProjectDescription__Group_18__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2622:1: ( rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 )
            // InternalN4MFParser.g:2623:2: rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3
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
    // InternalN4MFParser.g:2630:1: rule__ProjectDescription__Group_18__2__Impl : ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_18__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2634:1: ( ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) ) )
            // InternalN4MFParser.g:2635:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) )
            {
            // InternalN4MFParser.g:2635:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) )
            // InternalN4MFParser.g:2636:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* )
            {
            // InternalN4MFParser.g:2636:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) )
            // InternalN4MFParser.g:2637:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 
            // InternalN4MFParser.g:2638:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )
            // InternalN4MFParser.g:2638:2: rule__ProjectDescription__SourceFragmentAssignment_18_2
            {
            pushFollow(FOLLOW_13);
            rule__ProjectDescription__SourceFragmentAssignment_18_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 

            }

            // InternalN4MFParser.g:2641:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* )
            // InternalN4MFParser.g:2642:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 
            // InternalN4MFParser.g:2643:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==External||LA14_0==Source||LA14_0==Test) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalN4MFParser.g:2643:2: rule__ProjectDescription__SourceFragmentAssignment_18_2
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__ProjectDescription__SourceFragmentAssignment_18_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 

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
    // InternalN4MFParser.g:2654:1: rule__ProjectDescription__Group_18__3 : rule__ProjectDescription__Group_18__3__Impl ;
    public final void rule__ProjectDescription__Group_18__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2658:1: ( rule__ProjectDescription__Group_18__3__Impl )
            // InternalN4MFParser.g:2659:2: rule__ProjectDescription__Group_18__3__Impl
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
    // InternalN4MFParser.g:2665:1: rule__ProjectDescription__Group_18__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2669:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2670:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2670:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2671:1: RightCurlyBracket
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


    // $ANTLR start "rule__ProjectDescription__Group_19__0"
    // InternalN4MFParser.g:2692:1: rule__ProjectDescription__Group_19__0 : rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1 ;
    public final void rule__ProjectDescription__Group_19__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2696:1: ( rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1 )
            // InternalN4MFParser.g:2697:2: rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1
            {
            pushFollow(FOLLOW_8);
            rule__ProjectDescription__Group_19__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_19__1();

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
    // $ANTLR end "rule__ProjectDescription__Group_19__0"


    // $ANTLR start "rule__ProjectDescription__Group_19__0__Impl"
    // InternalN4MFParser.g:2704:1: rule__ProjectDescription__Group_19__0__Impl : ( ModuleFilters ) ;
    public final void rule__ProjectDescription__Group_19__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2708:1: ( ( ModuleFilters ) )
            // InternalN4MFParser.g:2709:1: ( ModuleFilters )
            {
            // InternalN4MFParser.g:2709:1: ( ModuleFilters )
            // InternalN4MFParser.g:2710:1: ModuleFilters
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_19_0()); 
            match(input,ModuleFilters,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_19_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_19__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_19__1"
    // InternalN4MFParser.g:2723:1: rule__ProjectDescription__Group_19__1 : rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2 ;
    public final void rule__ProjectDescription__Group_19__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2727:1: ( rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2 )
            // InternalN4MFParser.g:2728:2: rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2
            {
            pushFollow(FOLLOW_14);
            rule__ProjectDescription__Group_19__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_19__2();

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
    // $ANTLR end "rule__ProjectDescription__Group_19__1"


    // $ANTLR start "rule__ProjectDescription__Group_19__1__Impl"
    // InternalN4MFParser.g:2735:1: rule__ProjectDescription__Group_19__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_19__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2739:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2740:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2740:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2741:1: LeftCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_19_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_19_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_19__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_19__2"
    // InternalN4MFParser.g:2754:1: rule__ProjectDescription__Group_19__2 : rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3 ;
    public final void rule__ProjectDescription__Group_19__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2758:1: ( rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3 )
            // InternalN4MFParser.g:2759:2: rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3
            {
            pushFollow(FOLLOW_12);
            rule__ProjectDescription__Group_19__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_19__3();

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
    // $ANTLR end "rule__ProjectDescription__Group_19__2"


    // $ANTLR start "rule__ProjectDescription__Group_19__2__Impl"
    // InternalN4MFParser.g:2766:1: rule__ProjectDescription__Group_19__2__Impl : ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_19__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2770:1: ( ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) ) )
            // InternalN4MFParser.g:2771:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) )
            {
            // InternalN4MFParser.g:2771:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) )
            // InternalN4MFParser.g:2772:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* )
            {
            // InternalN4MFParser.g:2772:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) )
            // InternalN4MFParser.g:2773:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 
            // InternalN4MFParser.g:2774:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )
            // InternalN4MFParser.g:2774:2: rule__ProjectDescription__ModuleFiltersAssignment_19_2
            {
            pushFollow(FOLLOW_15);
            rule__ProjectDescription__ModuleFiltersAssignment_19_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 

            }

            // InternalN4MFParser.g:2777:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* )
            // InternalN4MFParser.g:2778:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 
            // InternalN4MFParser.g:2779:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==NoModuleWrap||LA15_0==NoValidate) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalN4MFParser.g:2779:2: rule__ProjectDescription__ModuleFiltersAssignment_19_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__ProjectDescription__ModuleFiltersAssignment_19_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 

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
    // $ANTLR end "rule__ProjectDescription__Group_19__2__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_19__3"
    // InternalN4MFParser.g:2790:1: rule__ProjectDescription__Group_19__3 : rule__ProjectDescription__Group_19__3__Impl ;
    public final void rule__ProjectDescription__Group_19__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2794:1: ( rule__ProjectDescription__Group_19__3__Impl )
            // InternalN4MFParser.g:2795:2: rule__ProjectDescription__Group_19__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_19__3__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_19__3"


    // $ANTLR start "rule__ProjectDescription__Group_19__3__Impl"
    // InternalN4MFParser.g:2801:1: rule__ProjectDescription__Group_19__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_19__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2805:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2806:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2806:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2807:1: RightCurlyBracket
            {
             before(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_19_3()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_19_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_19__3__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_21__0"
    // InternalN4MFParser.g:2828:1: rule__ProjectDescription__Group_21__0 : rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1 ;
    public final void rule__ProjectDescription__Group_21__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2832:1: ( rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1 )
            // InternalN4MFParser.g:2833:2: rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1
            {
            pushFollow(FOLLOW_3);
            rule__ProjectDescription__Group_21__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_21__1();

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
    // $ANTLR end "rule__ProjectDescription__Group_21__0"


    // $ANTLR start "rule__ProjectDescription__Group_21__0__Impl"
    // InternalN4MFParser.g:2840:1: rule__ProjectDescription__Group_21__0__Impl : ( ModuleLoader ) ;
    public final void rule__ProjectDescription__Group_21__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2844:1: ( ( ModuleLoader ) )
            // InternalN4MFParser.g:2845:1: ( ModuleLoader )
            {
            // InternalN4MFParser.g:2845:1: ( ModuleLoader )
            // InternalN4MFParser.g:2846:1: ModuleLoader
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_21_0()); 
            match(input,ModuleLoader,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_21_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_21__0__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_21__1"
    // InternalN4MFParser.g:2859:1: rule__ProjectDescription__Group_21__1 : rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2 ;
    public final void rule__ProjectDescription__Group_21__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2863:1: ( rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2 )
            // InternalN4MFParser.g:2864:2: rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2
            {
            pushFollow(FOLLOW_16);
            rule__ProjectDescription__Group_21__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_21__2();

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
    // $ANTLR end "rule__ProjectDescription__Group_21__1"


    // $ANTLR start "rule__ProjectDescription__Group_21__1__Impl"
    // InternalN4MFParser.g:2871:1: rule__ProjectDescription__Group_21__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_21__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2875:1: ( ( Colon ) )
            // InternalN4MFParser.g:2876:1: ( Colon )
            {
            // InternalN4MFParser.g:2876:1: ( Colon )
            // InternalN4MFParser.g:2877:1: Colon
            {
             before(grammarAccess.getProjectDescriptionAccess().getColonKeyword_21_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getColonKeyword_21_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_21__1__Impl"


    // $ANTLR start "rule__ProjectDescription__Group_21__2"
    // InternalN4MFParser.g:2890:1: rule__ProjectDescription__Group_21__2 : rule__ProjectDescription__Group_21__2__Impl ;
    public final void rule__ProjectDescription__Group_21__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2894:1: ( rule__ProjectDescription__Group_21__2__Impl )
            // InternalN4MFParser.g:2895:2: rule__ProjectDescription__Group_21__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Group_21__2__Impl();

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
    // $ANTLR end "rule__ProjectDescription__Group_21__2"


    // $ANTLR start "rule__ProjectDescription__Group_21__2__Impl"
    // InternalN4MFParser.g:2901:1: rule__ProjectDescription__Group_21__2__Impl : ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) ) ;
    public final void rule__ProjectDescription__Group_21__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2905:1: ( ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) ) )
            // InternalN4MFParser.g:2906:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) )
            {
            // InternalN4MFParser.g:2906:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) )
            // InternalN4MFParser.g:2907:1: ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderAssignment_21_2()); 
            // InternalN4MFParser.g:2908:1: ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 )
            // InternalN4MFParser.g:2908:2: rule__ProjectDescription__ModuleLoaderAssignment_21_2
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__ModuleLoaderAssignment_21_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderAssignment_21_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__Group_21__2__Impl"


    // $ANTLR start "rule__ExecModule__Group__0"
    // InternalN4MFParser.g:2924:1: rule__ExecModule__Group__0 : rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 ;
    public final void rule__ExecModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2928:1: ( rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 )
            // InternalN4MFParser.g:2929:2: rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1
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
    // InternalN4MFParser.g:2936:1: rule__ExecModule__Group__0__Impl : ( () ) ;
    public final void rule__ExecModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2940:1: ( ( () ) )
            // InternalN4MFParser.g:2941:1: ( () )
            {
            // InternalN4MFParser.g:2941:1: ( () )
            // InternalN4MFParser.g:2942:1: ()
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAction_0()); 
            // InternalN4MFParser.g:2943:1: ()
            // InternalN4MFParser.g:2945:1: 
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
    // InternalN4MFParser.g:2955:1: rule__ExecModule__Group__1 : rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 ;
    public final void rule__ExecModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2959:1: ( rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 )
            // InternalN4MFParser.g:2960:2: rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2
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
    // InternalN4MFParser.g:2967:1: rule__ExecModule__Group__1__Impl : ( ExecModule ) ;
    public final void rule__ExecModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2971:1: ( ( ExecModule ) )
            // InternalN4MFParser.g:2972:1: ( ExecModule )
            {
            // InternalN4MFParser.g:2972:1: ( ExecModule )
            // InternalN4MFParser.g:2973:1: ExecModule
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
    // InternalN4MFParser.g:2986:1: rule__ExecModule__Group__2 : rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 ;
    public final void rule__ExecModule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2990:1: ( rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 )
            // InternalN4MFParser.g:2991:2: rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:2998:1: rule__ExecModule__Group__2__Impl : ( Colon ) ;
    public final void rule__ExecModule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3002:1: ( ( Colon ) )
            // InternalN4MFParser.g:3003:1: ( Colon )
            {
            // InternalN4MFParser.g:3003:1: ( Colon )
            // InternalN4MFParser.g:3004:1: Colon
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
    // InternalN4MFParser.g:3017:1: rule__ExecModule__Group__3 : rule__ExecModule__Group__3__Impl ;
    public final void rule__ExecModule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3021:1: ( rule__ExecModule__Group__3__Impl )
            // InternalN4MFParser.g:3022:2: rule__ExecModule__Group__3__Impl
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
    // InternalN4MFParser.g:3028:1: rule__ExecModule__Group__3__Impl : ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) ;
    public final void rule__ExecModule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3032:1: ( ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) )
            // InternalN4MFParser.g:3033:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            {
            // InternalN4MFParser.g:3033:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            // InternalN4MFParser.g:3034:1: ( rule__ExecModule__ExecModuleAssignment_3 )
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAssignment_3()); 
            // InternalN4MFParser.g:3035:1: ( rule__ExecModule__ExecModuleAssignment_3 )
            // InternalN4MFParser.g:3035:2: rule__ExecModule__ExecModuleAssignment_3
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
    // InternalN4MFParser.g:3053:1: rule__TestedProjects__Group__0 : rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 ;
    public final void rule__TestedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3057:1: ( rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 )
            // InternalN4MFParser.g:3058:2: rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1
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
    // InternalN4MFParser.g:3065:1: rule__TestedProjects__Group__0__Impl : ( () ) ;
    public final void rule__TestedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3069:1: ( ( () ) )
            // InternalN4MFParser.g:3070:1: ( () )
            {
            // InternalN4MFParser.g:3070:1: ( () )
            // InternalN4MFParser.g:3071:1: ()
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0()); 
            // InternalN4MFParser.g:3072:1: ()
            // InternalN4MFParser.g:3074:1: 
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
    // InternalN4MFParser.g:3084:1: rule__TestedProjects__Group__1 : rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 ;
    public final void rule__TestedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3088:1: ( rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 )
            // InternalN4MFParser.g:3089:2: rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2
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
    // InternalN4MFParser.g:3096:1: rule__TestedProjects__Group__1__Impl : ( TestedProjects ) ;
    public final void rule__TestedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3100:1: ( ( TestedProjects ) )
            // InternalN4MFParser.g:3101:1: ( TestedProjects )
            {
            // InternalN4MFParser.g:3101:1: ( TestedProjects )
            // InternalN4MFParser.g:3102:1: TestedProjects
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
    // InternalN4MFParser.g:3115:1: rule__TestedProjects__Group__2 : rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 ;
    public final void rule__TestedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3119:1: ( rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 )
            // InternalN4MFParser.g:3120:2: rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3
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
    // InternalN4MFParser.g:3127:1: rule__TestedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__TestedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3131:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3132:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3132:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3133:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3146:1: rule__TestedProjects__Group__3 : rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 ;
    public final void rule__TestedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3150:1: ( rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 )
            // InternalN4MFParser.g:3151:2: rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4
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
    // InternalN4MFParser.g:3158:1: rule__TestedProjects__Group__3__Impl : ( ( rule__TestedProjects__Group_3__0 )? ) ;
    public final void rule__TestedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3162:1: ( ( ( rule__TestedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:3163:1: ( ( rule__TestedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3163:1: ( ( rule__TestedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:3164:1: ( rule__TestedProjects__Group_3__0 )?
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:3165:1: ( rule__TestedProjects__Group_3__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ProjectDependencies||LA16_0==ProjectVersion||LA16_0==ModuleFilters||(LA16_0>=ProjectName && LA16_0<=ArtifactId)||LA16_0==VendorName||(LA16_0>=Libraries && LA16_0<=VendorId)||LA16_0==Sources||LA16_0==Content||LA16_0==Output||(LA16_0>=Test && LA16_0<=API)||LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalN4MFParser.g:3165:2: rule__TestedProjects__Group_3__0
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
    // InternalN4MFParser.g:3175:1: rule__TestedProjects__Group__4 : rule__TestedProjects__Group__4__Impl ;
    public final void rule__TestedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3179:1: ( rule__TestedProjects__Group__4__Impl )
            // InternalN4MFParser.g:3180:2: rule__TestedProjects__Group__4__Impl
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
    // InternalN4MFParser.g:3186:1: rule__TestedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__TestedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3190:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3191:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3191:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3192:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3215:1: rule__TestedProjects__Group_3__0 : rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 ;
    public final void rule__TestedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3219:1: ( rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 )
            // InternalN4MFParser.g:3220:2: rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1
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
    // InternalN4MFParser.g:3227:1: rule__TestedProjects__Group_3__0__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) ;
    public final void rule__TestedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3231:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3232:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3232:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:3233:1: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:3234:1: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:3234:2: rule__TestedProjects__TestedProjectsAssignment_3_0
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
    // InternalN4MFParser.g:3244:1: rule__TestedProjects__Group_3__1 : rule__TestedProjects__Group_3__1__Impl ;
    public final void rule__TestedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3248:1: ( rule__TestedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:3249:2: rule__TestedProjects__Group_3__1__Impl
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
    // InternalN4MFParser.g:3255:1: rule__TestedProjects__Group_3__1__Impl : ( ( rule__TestedProjects__Group_3_1__0 )* ) ;
    public final void rule__TestedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3259:1: ( ( ( rule__TestedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3260:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3260:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:3261:1: ( rule__TestedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3262:1: ( rule__TestedProjects__Group_3_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Comma) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalN4MFParser.g:3262:2: rule__TestedProjects__Group_3_1__0
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
    // InternalN4MFParser.g:3276:1: rule__TestedProjects__Group_3_1__0 : rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 ;
    public final void rule__TestedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3280:1: ( rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:3281:2: rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1
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
    // InternalN4MFParser.g:3288:1: rule__TestedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__TestedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3292:1: ( ( Comma ) )
            // InternalN4MFParser.g:3293:1: ( Comma )
            {
            // InternalN4MFParser.g:3293:1: ( Comma )
            // InternalN4MFParser.g:3294:1: Comma
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
    // InternalN4MFParser.g:3307:1: rule__TestedProjects__Group_3_1__1 : rule__TestedProjects__Group_3_1__1__Impl ;
    public final void rule__TestedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3311:1: ( rule__TestedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3312:2: rule__TestedProjects__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3318:1: rule__TestedProjects__Group_3_1__1__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__TestedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3322:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3323:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3323:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3324:1: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:3325:1: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:3325:2: rule__TestedProjects__TestedProjectsAssignment_3_1_1
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
    // InternalN4MFParser.g:3339:1: rule__InitModules__Group__0 : rule__InitModules__Group__0__Impl rule__InitModules__Group__1 ;
    public final void rule__InitModules__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3343:1: ( rule__InitModules__Group__0__Impl rule__InitModules__Group__1 )
            // InternalN4MFParser.g:3344:2: rule__InitModules__Group__0__Impl rule__InitModules__Group__1
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
    // InternalN4MFParser.g:3351:1: rule__InitModules__Group__0__Impl : ( () ) ;
    public final void rule__InitModules__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3355:1: ( ( () ) )
            // InternalN4MFParser.g:3356:1: ( () )
            {
            // InternalN4MFParser.g:3356:1: ( () )
            // InternalN4MFParser.g:3357:1: ()
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAction_0()); 
            // InternalN4MFParser.g:3358:1: ()
            // InternalN4MFParser.g:3360:1: 
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
    // InternalN4MFParser.g:3370:1: rule__InitModules__Group__1 : rule__InitModules__Group__1__Impl rule__InitModules__Group__2 ;
    public final void rule__InitModules__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3374:1: ( rule__InitModules__Group__1__Impl rule__InitModules__Group__2 )
            // InternalN4MFParser.g:3375:2: rule__InitModules__Group__1__Impl rule__InitModules__Group__2
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
    // InternalN4MFParser.g:3382:1: rule__InitModules__Group__1__Impl : ( InitModules ) ;
    public final void rule__InitModules__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3386:1: ( ( InitModules ) )
            // InternalN4MFParser.g:3387:1: ( InitModules )
            {
            // InternalN4MFParser.g:3387:1: ( InitModules )
            // InternalN4MFParser.g:3388:1: InitModules
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
    // InternalN4MFParser.g:3401:1: rule__InitModules__Group__2 : rule__InitModules__Group__2__Impl rule__InitModules__Group__3 ;
    public final void rule__InitModules__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3405:1: ( rule__InitModules__Group__2__Impl rule__InitModules__Group__3 )
            // InternalN4MFParser.g:3406:2: rule__InitModules__Group__2__Impl rule__InitModules__Group__3
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
    // InternalN4MFParser.g:3413:1: rule__InitModules__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__InitModules__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3417:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3418:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3418:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3419:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3432:1: rule__InitModules__Group__3 : rule__InitModules__Group__3__Impl rule__InitModules__Group__4 ;
    public final void rule__InitModules__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3436:1: ( rule__InitModules__Group__3__Impl rule__InitModules__Group__4 )
            // InternalN4MFParser.g:3437:2: rule__InitModules__Group__3__Impl rule__InitModules__Group__4
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
    // InternalN4MFParser.g:3444:1: rule__InitModules__Group__3__Impl : ( ( rule__InitModules__Group_3__0 )? ) ;
    public final void rule__InitModules__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3448:1: ( ( ( rule__InitModules__Group_3__0 )? ) )
            // InternalN4MFParser.g:3449:1: ( ( rule__InitModules__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3449:1: ( ( rule__InitModules__Group_3__0 )? )
            // InternalN4MFParser.g:3450:1: ( rule__InitModules__Group_3__0 )?
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3()); 
            // InternalN4MFParser.g:3451:1: ( rule__InitModules__Group_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_STRING) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalN4MFParser.g:3451:2: rule__InitModules__Group_3__0
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
    // InternalN4MFParser.g:3461:1: rule__InitModules__Group__4 : rule__InitModules__Group__4__Impl ;
    public final void rule__InitModules__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3465:1: ( rule__InitModules__Group__4__Impl )
            // InternalN4MFParser.g:3466:2: rule__InitModules__Group__4__Impl
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
    // InternalN4MFParser.g:3472:1: rule__InitModules__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__InitModules__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3476:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3477:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3477:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3478:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3501:1: rule__InitModules__Group_3__0 : rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 ;
    public final void rule__InitModules__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3505:1: ( rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 )
            // InternalN4MFParser.g:3506:2: rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1
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
    // InternalN4MFParser.g:3513:1: rule__InitModules__Group_3__0__Impl : ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) ;
    public final void rule__InitModules__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3517:1: ( ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3518:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3518:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            // InternalN4MFParser.g:3519:1: ( rule__InitModules__InitModulesAssignment_3_0 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_0()); 
            // InternalN4MFParser.g:3520:1: ( rule__InitModules__InitModulesAssignment_3_0 )
            // InternalN4MFParser.g:3520:2: rule__InitModules__InitModulesAssignment_3_0
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
    // InternalN4MFParser.g:3530:1: rule__InitModules__Group_3__1 : rule__InitModules__Group_3__1__Impl ;
    public final void rule__InitModules__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3534:1: ( rule__InitModules__Group_3__1__Impl )
            // InternalN4MFParser.g:3535:2: rule__InitModules__Group_3__1__Impl
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
    // InternalN4MFParser.g:3541:1: rule__InitModules__Group_3__1__Impl : ( ( rule__InitModules__Group_3_1__0 )* ) ;
    public final void rule__InitModules__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3545:1: ( ( ( rule__InitModules__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3546:1: ( ( rule__InitModules__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3546:1: ( ( rule__InitModules__Group_3_1__0 )* )
            // InternalN4MFParser.g:3547:1: ( rule__InitModules__Group_3_1__0 )*
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3548:1: ( rule__InitModules__Group_3_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Comma) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalN4MFParser.g:3548:2: rule__InitModules__Group_3_1__0
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
    // InternalN4MFParser.g:3562:1: rule__InitModules__Group_3_1__0 : rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 ;
    public final void rule__InitModules__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3566:1: ( rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 )
            // InternalN4MFParser.g:3567:2: rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:3574:1: rule__InitModules__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__InitModules__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3578:1: ( ( Comma ) )
            // InternalN4MFParser.g:3579:1: ( Comma )
            {
            // InternalN4MFParser.g:3579:1: ( Comma )
            // InternalN4MFParser.g:3580:1: Comma
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
    // InternalN4MFParser.g:3593:1: rule__InitModules__Group_3_1__1 : rule__InitModules__Group_3_1__1__Impl ;
    public final void rule__InitModules__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3597:1: ( rule__InitModules__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3598:2: rule__InitModules__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3604:1: rule__InitModules__Group_3_1__1__Impl : ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) ;
    public final void rule__InitModules__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3608:1: ( ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3609:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3609:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3610:1: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3611:1: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            // InternalN4MFParser.g:3611:2: rule__InitModules__InitModulesAssignment_3_1_1
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
    // InternalN4MFParser.g:3625:1: rule__ImplementedProjects__Group__0 : rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 ;
    public final void rule__ImplementedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3629:1: ( rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 )
            // InternalN4MFParser.g:3630:2: rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1
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
    // InternalN4MFParser.g:3637:1: rule__ImplementedProjects__Group__0__Impl : ( () ) ;
    public final void rule__ImplementedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3641:1: ( ( () ) )
            // InternalN4MFParser.g:3642:1: ( () )
            {
            // InternalN4MFParser.g:3642:1: ( () )
            // InternalN4MFParser.g:3643:1: ()
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0()); 
            // InternalN4MFParser.g:3644:1: ()
            // InternalN4MFParser.g:3646:1: 
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
    // InternalN4MFParser.g:3656:1: rule__ImplementedProjects__Group__1 : rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 ;
    public final void rule__ImplementedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3660:1: ( rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 )
            // InternalN4MFParser.g:3661:2: rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2
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
    // InternalN4MFParser.g:3668:1: rule__ImplementedProjects__Group__1__Impl : ( ImplementedProjects ) ;
    public final void rule__ImplementedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3672:1: ( ( ImplementedProjects ) )
            // InternalN4MFParser.g:3673:1: ( ImplementedProjects )
            {
            // InternalN4MFParser.g:3673:1: ( ImplementedProjects )
            // InternalN4MFParser.g:3674:1: ImplementedProjects
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
    // InternalN4MFParser.g:3687:1: rule__ImplementedProjects__Group__2 : rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 ;
    public final void rule__ImplementedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3691:1: ( rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 )
            // InternalN4MFParser.g:3692:2: rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3
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
    // InternalN4MFParser.g:3699:1: rule__ImplementedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3703:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3704:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3704:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3705:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3718:1: rule__ImplementedProjects__Group__3 : rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 ;
    public final void rule__ImplementedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3722:1: ( rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 )
            // InternalN4MFParser.g:3723:2: rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4
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
    // InternalN4MFParser.g:3730:1: rule__ImplementedProjects__Group__3__Impl : ( ( rule__ImplementedProjects__Group_3__0 )? ) ;
    public final void rule__ImplementedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3734:1: ( ( ( rule__ImplementedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:3735:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3735:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:3736:1: ( rule__ImplementedProjects__Group_3__0 )?
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:3737:1: ( rule__ImplementedProjects__Group_3__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ProjectDependencies||LA20_0==ProjectVersion||LA20_0==ModuleFilters||(LA20_0>=ProjectName && LA20_0<=ArtifactId)||LA20_0==VendorName||(LA20_0>=Libraries && LA20_0<=VendorId)||LA20_0==Sources||LA20_0==Content||LA20_0==Output||(LA20_0>=Test && LA20_0<=API)||LA20_0==RULE_ID) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalN4MFParser.g:3737:2: rule__ImplementedProjects__Group_3__0
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
    // InternalN4MFParser.g:3747:1: rule__ImplementedProjects__Group__4 : rule__ImplementedProjects__Group__4__Impl ;
    public final void rule__ImplementedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3751:1: ( rule__ImplementedProjects__Group__4__Impl )
            // InternalN4MFParser.g:3752:2: rule__ImplementedProjects__Group__4__Impl
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
    // InternalN4MFParser.g:3758:1: rule__ImplementedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3762:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3763:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3763:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3764:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3787:1: rule__ImplementedProjects__Group_3__0 : rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 ;
    public final void rule__ImplementedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3791:1: ( rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 )
            // InternalN4MFParser.g:3792:2: rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1
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
    // InternalN4MFParser.g:3799:1: rule__ImplementedProjects__Group_3__0__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) ;
    public final void rule__ImplementedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3803:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3804:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3804:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:3805:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:3806:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:3806:2: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0
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
    // InternalN4MFParser.g:3816:1: rule__ImplementedProjects__Group_3__1 : rule__ImplementedProjects__Group_3__1__Impl ;
    public final void rule__ImplementedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3820:1: ( rule__ImplementedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:3821:2: rule__ImplementedProjects__Group_3__1__Impl
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
    // InternalN4MFParser.g:3827:1: rule__ImplementedProjects__Group_3__1__Impl : ( ( rule__ImplementedProjects__Group_3_1__0 )* ) ;
    public final void rule__ImplementedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3831:1: ( ( ( rule__ImplementedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3832:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3832:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:3833:1: ( rule__ImplementedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3834:1: ( rule__ImplementedProjects__Group_3_1__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==Comma) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalN4MFParser.g:3834:2: rule__ImplementedProjects__Group_3_1__0
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
    // InternalN4MFParser.g:3848:1: rule__ImplementedProjects__Group_3_1__0 : rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 ;
    public final void rule__ImplementedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3852:1: ( rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:3853:2: rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1
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
    // InternalN4MFParser.g:3860:1: rule__ImplementedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ImplementedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3864:1: ( ( Comma ) )
            // InternalN4MFParser.g:3865:1: ( Comma )
            {
            // InternalN4MFParser.g:3865:1: ( Comma )
            // InternalN4MFParser.g:3866:1: Comma
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
    // InternalN4MFParser.g:3879:1: rule__ImplementedProjects__Group_3_1__1 : rule__ImplementedProjects__Group_3_1__1__Impl ;
    public final void rule__ImplementedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3883:1: ( rule__ImplementedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3884:2: rule__ImplementedProjects__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3890:1: rule__ImplementedProjects__Group_3_1__1__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__ImplementedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3894:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3895:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3895:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3896:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:3897:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:3897:2: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1
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
    // InternalN4MFParser.g:3911:1: rule__ProjectDependencies__Group__0 : rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 ;
    public final void rule__ProjectDependencies__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3915:1: ( rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 )
            // InternalN4MFParser.g:3916:2: rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1
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
    // InternalN4MFParser.g:3923:1: rule__ProjectDependencies__Group__0__Impl : ( () ) ;
    public final void rule__ProjectDependencies__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3927:1: ( ( () ) )
            // InternalN4MFParser.g:3928:1: ( () )
            {
            // InternalN4MFParser.g:3928:1: ( () )
            // InternalN4MFParser.g:3929:1: ()
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0()); 
            // InternalN4MFParser.g:3930:1: ()
            // InternalN4MFParser.g:3932:1: 
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
    // InternalN4MFParser.g:3942:1: rule__ProjectDependencies__Group__1 : rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 ;
    public final void rule__ProjectDependencies__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3946:1: ( rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 )
            // InternalN4MFParser.g:3947:2: rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2
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
    // InternalN4MFParser.g:3954:1: rule__ProjectDependencies__Group__1__Impl : ( ProjectDependencies ) ;
    public final void rule__ProjectDependencies__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3958:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:3959:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:3959:1: ( ProjectDependencies )
            // InternalN4MFParser.g:3960:1: ProjectDependencies
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
    // InternalN4MFParser.g:3973:1: rule__ProjectDependencies__Group__2 : rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 ;
    public final void rule__ProjectDependencies__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3977:1: ( rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 )
            // InternalN4MFParser.g:3978:2: rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3
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
    // InternalN4MFParser.g:3985:1: rule__ProjectDependencies__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3989:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3990:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3990:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3991:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4004:1: rule__ProjectDependencies__Group__3 : rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 ;
    public final void rule__ProjectDependencies__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4008:1: ( rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 )
            // InternalN4MFParser.g:4009:2: rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4
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
    // InternalN4MFParser.g:4016:1: rule__ProjectDependencies__Group__3__Impl : ( ( rule__ProjectDependencies__Group_3__0 )? ) ;
    public final void rule__ProjectDependencies__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4020:1: ( ( ( rule__ProjectDependencies__Group_3__0 )? ) )
            // InternalN4MFParser.g:4021:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4021:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            // InternalN4MFParser.g:4022:1: ( rule__ProjectDependencies__Group_3__0 )?
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4023:1: ( rule__ProjectDependencies__Group_3__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==ProjectDependencies||LA22_0==ProjectVersion||LA22_0==ModuleFilters||(LA22_0>=ProjectName && LA22_0<=ArtifactId)||LA22_0==VendorName||(LA22_0>=Libraries && LA22_0<=VendorId)||LA22_0==Sources||LA22_0==Content||LA22_0==Output||(LA22_0>=Test && LA22_0<=API)||LA22_0==RULE_ID) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalN4MFParser.g:4023:2: rule__ProjectDependencies__Group_3__0
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
    // InternalN4MFParser.g:4033:1: rule__ProjectDependencies__Group__4 : rule__ProjectDependencies__Group__4__Impl ;
    public final void rule__ProjectDependencies__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4037:1: ( rule__ProjectDependencies__Group__4__Impl )
            // InternalN4MFParser.g:4038:2: rule__ProjectDependencies__Group__4__Impl
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
    // InternalN4MFParser.g:4044:1: rule__ProjectDependencies__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4048:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4049:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4049:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4050:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4073:1: rule__ProjectDependencies__Group_3__0 : rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 ;
    public final void rule__ProjectDependencies__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4077:1: ( rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 )
            // InternalN4MFParser.g:4078:2: rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1
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
    // InternalN4MFParser.g:4085:1: rule__ProjectDependencies__Group_3__0__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) ;
    public final void rule__ProjectDependencies__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4089:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4090:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4090:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            // InternalN4MFParser.g:4091:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_0()); 
            // InternalN4MFParser.g:4092:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            // InternalN4MFParser.g:4092:2: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0
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
    // InternalN4MFParser.g:4102:1: rule__ProjectDependencies__Group_3__1 : rule__ProjectDependencies__Group_3__1__Impl ;
    public final void rule__ProjectDependencies__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4106:1: ( rule__ProjectDependencies__Group_3__1__Impl )
            // InternalN4MFParser.g:4107:2: rule__ProjectDependencies__Group_3__1__Impl
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
    // InternalN4MFParser.g:4113:1: rule__ProjectDependencies__Group_3__1__Impl : ( ( rule__ProjectDependencies__Group_3_1__0 )* ) ;
    public final void rule__ProjectDependencies__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4117:1: ( ( ( rule__ProjectDependencies__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4118:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4118:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            // InternalN4MFParser.g:4119:1: ( rule__ProjectDependencies__Group_3_1__0 )*
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4120:1: ( rule__ProjectDependencies__Group_3_1__0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Comma) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalN4MFParser.g:4120:2: rule__ProjectDependencies__Group_3_1__0
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
    // InternalN4MFParser.g:4134:1: rule__ProjectDependencies__Group_3_1__0 : rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 ;
    public final void rule__ProjectDependencies__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4138:1: ( rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 )
            // InternalN4MFParser.g:4139:2: rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1
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
    // InternalN4MFParser.g:4146:1: rule__ProjectDependencies__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProjectDependencies__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4150:1: ( ( Comma ) )
            // InternalN4MFParser.g:4151:1: ( Comma )
            {
            // InternalN4MFParser.g:4151:1: ( Comma )
            // InternalN4MFParser.g:4152:1: Comma
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
    // InternalN4MFParser.g:4165:1: rule__ProjectDependencies__Group_3_1__1 : rule__ProjectDependencies__Group_3_1__1__Impl ;
    public final void rule__ProjectDependencies__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4169:1: ( rule__ProjectDependencies__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4170:2: rule__ProjectDependencies__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4176:1: rule__ProjectDependencies__Group_3_1__1__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) ;
    public final void rule__ProjectDependencies__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4180:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4181:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4181:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4182:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4183:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            // InternalN4MFParser.g:4183:2: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1
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
    // InternalN4MFParser.g:4197:1: rule__ProvidedRuntimeLibraries__Group__0 : rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4201:1: ( rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:4202:2: rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1
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
    // InternalN4MFParser.g:4209:1: rule__ProvidedRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4213:1: ( ( () ) )
            // InternalN4MFParser.g:4214:1: ( () )
            {
            // InternalN4MFParser.g:4214:1: ( () )
            // InternalN4MFParser.g:4215:1: ()
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:4216:1: ()
            // InternalN4MFParser.g:4218:1: 
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
    // InternalN4MFParser.g:4228:1: rule__ProvidedRuntimeLibraries__Group__1 : rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 ;
    public final void rule__ProvidedRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4232:1: ( rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:4233:2: rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2
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
    // InternalN4MFParser.g:4240:1: rule__ProvidedRuntimeLibraries__Group__1__Impl : ( ProvidedRuntimeLibraries ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4244:1: ( ( ProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:4245:1: ( ProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:4245:1: ( ProvidedRuntimeLibraries )
            // InternalN4MFParser.g:4246:1: ProvidedRuntimeLibraries
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
    // InternalN4MFParser.g:4259:1: rule__ProvidedRuntimeLibraries__Group__2 : rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 ;
    public final void rule__ProvidedRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4263:1: ( rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:4264:2: rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3
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
    // InternalN4MFParser.g:4271:1: rule__ProvidedRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4275:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4276:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4276:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4277:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4290:1: rule__ProvidedRuntimeLibraries__Group__3 : rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 ;
    public final void rule__ProvidedRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4294:1: ( rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:4295:2: rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4
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
    // InternalN4MFParser.g:4302:1: rule__ProvidedRuntimeLibraries__Group__3__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4306:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:4307:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4307:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:4308:1: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4309:1: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==ProjectDependencies||LA24_0==ProjectVersion||LA24_0==ModuleFilters||(LA24_0>=ProjectName && LA24_0<=ArtifactId)||LA24_0==VendorName||(LA24_0>=Libraries && LA24_0<=VendorId)||LA24_0==Sources||LA24_0==Content||LA24_0==Output||(LA24_0>=Test && LA24_0<=API)||LA24_0==RULE_ID) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalN4MFParser.g:4309:2: rule__ProvidedRuntimeLibraries__Group_3__0
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
    // InternalN4MFParser.g:4319:1: rule__ProvidedRuntimeLibraries__Group__4 : rule__ProvidedRuntimeLibraries__Group__4__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4323:1: ( rule__ProvidedRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:4324:2: rule__ProvidedRuntimeLibraries__Group__4__Impl
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
    // InternalN4MFParser.g:4330:1: rule__ProvidedRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4334:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4335:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4335:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4336:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4359:1: rule__ProvidedRuntimeLibraries__Group_3__0 : rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4363:1: ( rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:4364:2: rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1
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
    // InternalN4MFParser.g:4371:1: rule__ProvidedRuntimeLibraries__Group_3__0__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4375:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4376:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4376:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:4377:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:4378:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:4378:2: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0
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
    // InternalN4MFParser.g:4388:1: rule__ProvidedRuntimeLibraries__Group_3__1 : rule__ProvidedRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4392:1: ( rule__ProvidedRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:4393:2: rule__ProvidedRuntimeLibraries__Group_3__1__Impl
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
    // InternalN4MFParser.g:4399:1: rule__ProvidedRuntimeLibraries__Group_3__1__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4403:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4404:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4404:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:4405:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4406:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==Comma) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalN4MFParser.g:4406:2: rule__ProvidedRuntimeLibraries__Group_3_1__0
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
    // InternalN4MFParser.g:4420:1: rule__ProvidedRuntimeLibraries__Group_3_1__0 : rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4424:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:4425:2: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1
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
    // InternalN4MFParser.g:4432:1: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4436:1: ( ( Comma ) )
            // InternalN4MFParser.g:4437:1: ( Comma )
            {
            // InternalN4MFParser.g:4437:1: ( Comma )
            // InternalN4MFParser.g:4438:1: Comma
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
    // InternalN4MFParser.g:4451:1: rule__ProvidedRuntimeLibraries__Group_3_1__1 : rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4455:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4456:2: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4462:1: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4466:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4467:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4467:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4468:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4469:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:4469:2: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1
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
    // InternalN4MFParser.g:4483:1: rule__RequiredRuntimeLibraries__Group__0 : rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 ;
    public final void rule__RequiredRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4487:1: ( rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:4488:2: rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1
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
    // InternalN4MFParser.g:4495:1: rule__RequiredRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__RequiredRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4499:1: ( ( () ) )
            // InternalN4MFParser.g:4500:1: ( () )
            {
            // InternalN4MFParser.g:4500:1: ( () )
            // InternalN4MFParser.g:4501:1: ()
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:4502:1: ()
            // InternalN4MFParser.g:4504:1: 
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
    // InternalN4MFParser.g:4514:1: rule__RequiredRuntimeLibraries__Group__1 : rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 ;
    public final void rule__RequiredRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4518:1: ( rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:4519:2: rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2
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
    // InternalN4MFParser.g:4526:1: rule__RequiredRuntimeLibraries__Group__1__Impl : ( RequiredRuntimeLibraries ) ;
    public final void rule__RequiredRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4530:1: ( ( RequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:4531:1: ( RequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:4531:1: ( RequiredRuntimeLibraries )
            // InternalN4MFParser.g:4532:1: RequiredRuntimeLibraries
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
    // InternalN4MFParser.g:4545:1: rule__RequiredRuntimeLibraries__Group__2 : rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 ;
    public final void rule__RequiredRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4549:1: ( rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:4550:2: rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3
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
    // InternalN4MFParser.g:4557:1: rule__RequiredRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4561:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4562:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4562:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4563:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4576:1: rule__RequiredRuntimeLibraries__Group__3 : rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 ;
    public final void rule__RequiredRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4580:1: ( rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:4581:2: rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4
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
    // InternalN4MFParser.g:4588:1: rule__RequiredRuntimeLibraries__Group__3__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__RequiredRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4592:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:4593:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4593:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:4594:1: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4595:1: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==ProjectDependencies||LA26_0==ProjectVersion||LA26_0==ModuleFilters||(LA26_0>=ProjectName && LA26_0<=ArtifactId)||LA26_0==VendorName||(LA26_0>=Libraries && LA26_0<=VendorId)||LA26_0==Sources||LA26_0==Content||LA26_0==Output||(LA26_0>=Test && LA26_0<=API)||LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalN4MFParser.g:4595:2: rule__RequiredRuntimeLibraries__Group_3__0
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
    // InternalN4MFParser.g:4605:1: rule__RequiredRuntimeLibraries__Group__4 : rule__RequiredRuntimeLibraries__Group__4__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4609:1: ( rule__RequiredRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:4610:2: rule__RequiredRuntimeLibraries__Group__4__Impl
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
    // InternalN4MFParser.g:4616:1: rule__RequiredRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4620:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4621:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4621:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4622:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4645:1: rule__RequiredRuntimeLibraries__Group_3__0 : rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4649:1: ( rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:4650:2: rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1
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
    // InternalN4MFParser.g:4657:1: rule__RequiredRuntimeLibraries__Group_3__0__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4661:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4662:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4662:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:4663:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:4664:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:4664:2: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0
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
    // InternalN4MFParser.g:4674:1: rule__RequiredRuntimeLibraries__Group_3__1 : rule__RequiredRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4678:1: ( rule__RequiredRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:4679:2: rule__RequiredRuntimeLibraries__Group_3__1__Impl
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
    // InternalN4MFParser.g:4685:1: rule__RequiredRuntimeLibraries__Group_3__1__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4689:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4690:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4690:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:4691:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4692:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==Comma) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalN4MFParser.g:4692:2: rule__RequiredRuntimeLibraries__Group_3_1__0
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
    // InternalN4MFParser.g:4706:1: rule__RequiredRuntimeLibraries__Group_3_1__0 : rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4710:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:4711:2: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1
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
    // InternalN4MFParser.g:4718:1: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4722:1: ( ( Comma ) )
            // InternalN4MFParser.g:4723:1: ( Comma )
            {
            // InternalN4MFParser.g:4723:1: ( Comma )
            // InternalN4MFParser.g:4724:1: Comma
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
    // InternalN4MFParser.g:4737:1: rule__RequiredRuntimeLibraries__Group_3_1__1 : rule__RequiredRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4741:1: ( rule__RequiredRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4742:2: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4748:1: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4752:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4753:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4753:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4754:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4755:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:4755:2: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1
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
    // InternalN4MFParser.g:4769:1: rule__ExtendedRuntimeEnvironment__Group__0 : rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4773:1: ( rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 )
            // InternalN4MFParser.g:4774:2: rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1
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
    // InternalN4MFParser.g:4781:1: rule__ExtendedRuntimeEnvironment__Group__0__Impl : ( () ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4785:1: ( ( () ) )
            // InternalN4MFParser.g:4786:1: ( () )
            {
            // InternalN4MFParser.g:4786:1: ( () )
            // InternalN4MFParser.g:4787:1: ()
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0()); 
            // InternalN4MFParser.g:4788:1: ()
            // InternalN4MFParser.g:4790:1: 
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
    // InternalN4MFParser.g:4800:1: rule__ExtendedRuntimeEnvironment__Group__1 : rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4804:1: ( rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 )
            // InternalN4MFParser.g:4805:2: rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2
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
    // InternalN4MFParser.g:4812:1: rule__ExtendedRuntimeEnvironment__Group__1__Impl : ( ExtendedRuntimeEnvironment ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4816:1: ( ( ExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:4817:1: ( ExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:4817:1: ( ExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:4818:1: ExtendedRuntimeEnvironment
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
    // InternalN4MFParser.g:4831:1: rule__ExtendedRuntimeEnvironment__Group__2 : rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4835:1: ( rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 )
            // InternalN4MFParser.g:4836:2: rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3
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
    // InternalN4MFParser.g:4843:1: rule__ExtendedRuntimeEnvironment__Group__2__Impl : ( Colon ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4847:1: ( ( Colon ) )
            // InternalN4MFParser.g:4848:1: ( Colon )
            {
            // InternalN4MFParser.g:4848:1: ( Colon )
            // InternalN4MFParser.g:4849:1: Colon
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
    // InternalN4MFParser.g:4862:1: rule__ExtendedRuntimeEnvironment__Group__3 : rule__ExtendedRuntimeEnvironment__Group__3__Impl ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4866:1: ( rule__ExtendedRuntimeEnvironment__Group__3__Impl )
            // InternalN4MFParser.g:4867:2: rule__ExtendedRuntimeEnvironment__Group__3__Impl
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
    // InternalN4MFParser.g:4873:1: rule__ExtendedRuntimeEnvironment__Group__3__Impl : ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4877:1: ( ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) )
            // InternalN4MFParser.g:4878:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            {
            // InternalN4MFParser.g:4878:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            // InternalN4MFParser.g:4879:1: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAssignment_3()); 
            // InternalN4MFParser.g:4880:1: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            // InternalN4MFParser.g:4880:2: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3
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
    // InternalN4MFParser.g:4898:1: rule__DeclaredVersion__Group__0 : rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 ;
    public final void rule__DeclaredVersion__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4902:1: ( rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 )
            // InternalN4MFParser.g:4903:2: rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1
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
    // InternalN4MFParser.g:4910:1: rule__DeclaredVersion__Group__0__Impl : ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) ;
    public final void rule__DeclaredVersion__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4914:1: ( ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) )
            // InternalN4MFParser.g:4915:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            {
            // InternalN4MFParser.g:4915:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            // InternalN4MFParser.g:4916:1: ( rule__DeclaredVersion__MajorAssignment_0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMajorAssignment_0()); 
            // InternalN4MFParser.g:4917:1: ( rule__DeclaredVersion__MajorAssignment_0 )
            // InternalN4MFParser.g:4917:2: rule__DeclaredVersion__MajorAssignment_0
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
    // InternalN4MFParser.g:4927:1: rule__DeclaredVersion__Group__1 : rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 ;
    public final void rule__DeclaredVersion__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4931:1: ( rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 )
            // InternalN4MFParser.g:4932:2: rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2
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
    // InternalN4MFParser.g:4939:1: rule__DeclaredVersion__Group__1__Impl : ( ( rule__DeclaredVersion__Group_1__0 )? ) ;
    public final void rule__DeclaredVersion__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4943:1: ( ( ( rule__DeclaredVersion__Group_1__0 )? ) )
            // InternalN4MFParser.g:4944:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            {
            // InternalN4MFParser.g:4944:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            // InternalN4MFParser.g:4945:1: ( rule__DeclaredVersion__Group_1__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1()); 
            // InternalN4MFParser.g:4946:1: ( rule__DeclaredVersion__Group_1__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==FullStop) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalN4MFParser.g:4946:2: rule__DeclaredVersion__Group_1__0
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
    // InternalN4MFParser.g:4956:1: rule__DeclaredVersion__Group__2 : rule__DeclaredVersion__Group__2__Impl ;
    public final void rule__DeclaredVersion__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4960:1: ( rule__DeclaredVersion__Group__2__Impl )
            // InternalN4MFParser.g:4961:2: rule__DeclaredVersion__Group__2__Impl
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
    // InternalN4MFParser.g:4967:1: rule__DeclaredVersion__Group__2__Impl : ( ( rule__DeclaredVersion__Group_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4971:1: ( ( ( rule__DeclaredVersion__Group_2__0 )? ) )
            // InternalN4MFParser.g:4972:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            {
            // InternalN4MFParser.g:4972:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            // InternalN4MFParser.g:4973:1: ( rule__DeclaredVersion__Group_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_2()); 
            // InternalN4MFParser.g:4974:1: ( rule__DeclaredVersion__Group_2__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==HyphenMinus) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalN4MFParser.g:4974:2: rule__DeclaredVersion__Group_2__0
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
    // InternalN4MFParser.g:4990:1: rule__DeclaredVersion__Group_1__0 : rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 ;
    public final void rule__DeclaredVersion__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4994:1: ( rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 )
            // InternalN4MFParser.g:4995:2: rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalN4MFParser.g:5002:1: rule__DeclaredVersion__Group_1__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5006:1: ( ( FullStop ) )
            // InternalN4MFParser.g:5007:1: ( FullStop )
            {
            // InternalN4MFParser.g:5007:1: ( FullStop )
            // InternalN4MFParser.g:5008:1: FullStop
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
    // InternalN4MFParser.g:5021:1: rule__DeclaredVersion__Group_1__1 : rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 ;
    public final void rule__DeclaredVersion__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5025:1: ( rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 )
            // InternalN4MFParser.g:5026:2: rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2
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
    // InternalN4MFParser.g:5033:1: rule__DeclaredVersion__Group_1__1__Impl : ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5037:1: ( ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5038:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5038:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            // InternalN4MFParser.g:5039:1: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMinorAssignment_1_1()); 
            // InternalN4MFParser.g:5040:1: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            // InternalN4MFParser.g:5040:2: rule__DeclaredVersion__MinorAssignment_1_1
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
    // InternalN4MFParser.g:5050:1: rule__DeclaredVersion__Group_1__2 : rule__DeclaredVersion__Group_1__2__Impl ;
    public final void rule__DeclaredVersion__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5054:1: ( rule__DeclaredVersion__Group_1__2__Impl )
            // InternalN4MFParser.g:5055:2: rule__DeclaredVersion__Group_1__2__Impl
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
    // InternalN4MFParser.g:5061:1: rule__DeclaredVersion__Group_1__2__Impl : ( ( rule__DeclaredVersion__Group_1_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5065:1: ( ( ( rule__DeclaredVersion__Group_1_2__0 )? ) )
            // InternalN4MFParser.g:5066:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            {
            // InternalN4MFParser.g:5066:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            // InternalN4MFParser.g:5067:1: ( rule__DeclaredVersion__Group_1_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1_2()); 
            // InternalN4MFParser.g:5068:1: ( rule__DeclaredVersion__Group_1_2__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==FullStop) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalN4MFParser.g:5068:2: rule__DeclaredVersion__Group_1_2__0
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
    // InternalN4MFParser.g:5084:1: rule__DeclaredVersion__Group_1_2__0 : rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 ;
    public final void rule__DeclaredVersion__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5088:1: ( rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 )
            // InternalN4MFParser.g:5089:2: rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalN4MFParser.g:5096:1: rule__DeclaredVersion__Group_1_2__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5100:1: ( ( FullStop ) )
            // InternalN4MFParser.g:5101:1: ( FullStop )
            {
            // InternalN4MFParser.g:5101:1: ( FullStop )
            // InternalN4MFParser.g:5102:1: FullStop
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
    // InternalN4MFParser.g:5115:1: rule__DeclaredVersion__Group_1_2__1 : rule__DeclaredVersion__Group_1_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5119:1: ( rule__DeclaredVersion__Group_1_2__1__Impl )
            // InternalN4MFParser.g:5120:2: rule__DeclaredVersion__Group_1_2__1__Impl
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
    // InternalN4MFParser.g:5126:1: rule__DeclaredVersion__Group_1_2__1__Impl : ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5130:1: ( ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) )
            // InternalN4MFParser.g:5131:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            {
            // InternalN4MFParser.g:5131:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            // InternalN4MFParser.g:5132:1: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMicroAssignment_1_2_1()); 
            // InternalN4MFParser.g:5133:1: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            // InternalN4MFParser.g:5133:2: rule__DeclaredVersion__MicroAssignment_1_2_1
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
    // InternalN4MFParser.g:5147:1: rule__DeclaredVersion__Group_2__0 : rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 ;
    public final void rule__DeclaredVersion__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5151:1: ( rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 )
            // InternalN4MFParser.g:5152:2: rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1
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
    // InternalN4MFParser.g:5159:1: rule__DeclaredVersion__Group_2__0__Impl : ( HyphenMinus ) ;
    public final void rule__DeclaredVersion__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5163:1: ( ( HyphenMinus ) )
            // InternalN4MFParser.g:5164:1: ( HyphenMinus )
            {
            // InternalN4MFParser.g:5164:1: ( HyphenMinus )
            // InternalN4MFParser.g:5165:1: HyphenMinus
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
    // InternalN4MFParser.g:5178:1: rule__DeclaredVersion__Group_2__1 : rule__DeclaredVersion__Group_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5182:1: ( rule__DeclaredVersion__Group_2__1__Impl )
            // InternalN4MFParser.g:5183:2: rule__DeclaredVersion__Group_2__1__Impl
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
    // InternalN4MFParser.g:5189:1: rule__DeclaredVersion__Group_2__1__Impl : ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5193:1: ( ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) )
            // InternalN4MFParser.g:5194:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            {
            // InternalN4MFParser.g:5194:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            // InternalN4MFParser.g:5195:1: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getQualifierAssignment_2_1()); 
            // InternalN4MFParser.g:5196:1: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            // InternalN4MFParser.g:5196:2: rule__DeclaredVersion__QualifierAssignment_2_1
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
    // InternalN4MFParser.g:5210:1: rule__SourceFragment__Group__0 : rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 ;
    public final void rule__SourceFragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5214:1: ( rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 )
            // InternalN4MFParser.g:5215:2: rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1
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
    // InternalN4MFParser.g:5222:1: rule__SourceFragment__Group__0__Impl : ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) ;
    public final void rule__SourceFragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5226:1: ( ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:5227:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:5227:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            // InternalN4MFParser.g:5228:1: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeAssignment_0()); 
            // InternalN4MFParser.g:5229:1: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            // InternalN4MFParser.g:5229:2: rule__SourceFragment__SourceFragmentTypeAssignment_0
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
    // InternalN4MFParser.g:5239:1: rule__SourceFragment__Group__1 : rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 ;
    public final void rule__SourceFragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5243:1: ( rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 )
            // InternalN4MFParser.g:5244:2: rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5251:1: rule__SourceFragment__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__SourceFragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5255:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:5256:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:5256:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:5257:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:5270:1: rule__SourceFragment__Group__2 : rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 ;
    public final void rule__SourceFragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5274:1: ( rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 )
            // InternalN4MFParser.g:5275:2: rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3
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
    // InternalN4MFParser.g:5282:1: rule__SourceFragment__Group__2__Impl : ( ( rule__SourceFragment__PathsAssignment_2 ) ) ;
    public final void rule__SourceFragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5286:1: ( ( ( rule__SourceFragment__PathsAssignment_2 ) ) )
            // InternalN4MFParser.g:5287:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            {
            // InternalN4MFParser.g:5287:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            // InternalN4MFParser.g:5288:1: ( rule__SourceFragment__PathsAssignment_2 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_2()); 
            // InternalN4MFParser.g:5289:1: ( rule__SourceFragment__PathsAssignment_2 )
            // InternalN4MFParser.g:5289:2: rule__SourceFragment__PathsAssignment_2
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
    // InternalN4MFParser.g:5299:1: rule__SourceFragment__Group__3 : rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 ;
    public final void rule__SourceFragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5303:1: ( rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 )
            // InternalN4MFParser.g:5304:2: rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4
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
    // InternalN4MFParser.g:5311:1: rule__SourceFragment__Group__3__Impl : ( ( rule__SourceFragment__Group_3__0 )* ) ;
    public final void rule__SourceFragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5315:1: ( ( ( rule__SourceFragment__Group_3__0 )* ) )
            // InternalN4MFParser.g:5316:1: ( ( rule__SourceFragment__Group_3__0 )* )
            {
            // InternalN4MFParser.g:5316:1: ( ( rule__SourceFragment__Group_3__0 )* )
            // InternalN4MFParser.g:5317:1: ( rule__SourceFragment__Group_3__0 )*
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup_3()); 
            // InternalN4MFParser.g:5318:1: ( rule__SourceFragment__Group_3__0 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==Comma) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalN4MFParser.g:5318:2: rule__SourceFragment__Group_3__0
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
    // InternalN4MFParser.g:5328:1: rule__SourceFragment__Group__4 : rule__SourceFragment__Group__4__Impl ;
    public final void rule__SourceFragment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5332:1: ( rule__SourceFragment__Group__4__Impl )
            // InternalN4MFParser.g:5333:2: rule__SourceFragment__Group__4__Impl
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
    // InternalN4MFParser.g:5339:1: rule__SourceFragment__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__SourceFragment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5343:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:5344:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:5344:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:5345:1: RightCurlyBracket
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
    // InternalN4MFParser.g:5368:1: rule__SourceFragment__Group_3__0 : rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 ;
    public final void rule__SourceFragment__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5372:1: ( rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 )
            // InternalN4MFParser.g:5373:2: rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5380:1: rule__SourceFragment__Group_3__0__Impl : ( Comma ) ;
    public final void rule__SourceFragment__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5384:1: ( ( Comma ) )
            // InternalN4MFParser.g:5385:1: ( Comma )
            {
            // InternalN4MFParser.g:5385:1: ( Comma )
            // InternalN4MFParser.g:5386:1: Comma
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
    // InternalN4MFParser.g:5399:1: rule__SourceFragment__Group_3__1 : rule__SourceFragment__Group_3__1__Impl ;
    public final void rule__SourceFragment__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5403:1: ( rule__SourceFragment__Group_3__1__Impl )
            // InternalN4MFParser.g:5404:2: rule__SourceFragment__Group_3__1__Impl
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
    // InternalN4MFParser.g:5410:1: rule__SourceFragment__Group_3__1__Impl : ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) ;
    public final void rule__SourceFragment__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5414:1: ( ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) )
            // InternalN4MFParser.g:5415:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:5415:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            // InternalN4MFParser.g:5416:1: ( rule__SourceFragment__PathsAssignment_3_1 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_3_1()); 
            // InternalN4MFParser.g:5417:1: ( rule__SourceFragment__PathsAssignment_3_1 )
            // InternalN4MFParser.g:5417:2: rule__SourceFragment__PathsAssignment_3_1
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
    // InternalN4MFParser.g:5431:1: rule__ModuleFilter__Group__0 : rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 ;
    public final void rule__ModuleFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5435:1: ( rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 )
            // InternalN4MFParser.g:5436:2: rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1
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
    // InternalN4MFParser.g:5443:1: rule__ModuleFilter__Group__0__Impl : ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) ;
    public final void rule__ModuleFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5447:1: ( ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:5448:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:5448:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            // InternalN4MFParser.g:5449:1: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleFilterTypeAssignment_0()); 
            // InternalN4MFParser.g:5450:1: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            // InternalN4MFParser.g:5450:2: rule__ModuleFilter__ModuleFilterTypeAssignment_0
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
    // InternalN4MFParser.g:5460:1: rule__ModuleFilter__Group__1 : rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 ;
    public final void rule__ModuleFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5464:1: ( rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 )
            // InternalN4MFParser.g:5465:2: rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5472:1: rule__ModuleFilter__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5476:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:5477:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:5477:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:5478:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:5491:1: rule__ModuleFilter__Group__2 : rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 ;
    public final void rule__ModuleFilter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5495:1: ( rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 )
            // InternalN4MFParser.g:5496:2: rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3
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
    // InternalN4MFParser.g:5503:1: rule__ModuleFilter__Group__2__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) ;
    public final void rule__ModuleFilter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5507:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) )
            // InternalN4MFParser.g:5508:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            {
            // InternalN4MFParser.g:5508:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            // InternalN4MFParser.g:5509:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_2()); 
            // InternalN4MFParser.g:5510:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            // InternalN4MFParser.g:5510:2: rule__ModuleFilter__ModuleSpecifiersAssignment_2
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
    // InternalN4MFParser.g:5520:1: rule__ModuleFilter__Group__3 : rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 ;
    public final void rule__ModuleFilter__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5524:1: ( rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 )
            // InternalN4MFParser.g:5525:2: rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4
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
    // InternalN4MFParser.g:5532:1: rule__ModuleFilter__Group__3__Impl : ( ( rule__ModuleFilter__Group_3__0 )* ) ;
    public final void rule__ModuleFilter__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5536:1: ( ( ( rule__ModuleFilter__Group_3__0 )* ) )
            // InternalN4MFParser.g:5537:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            {
            // InternalN4MFParser.g:5537:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            // InternalN4MFParser.g:5538:1: ( rule__ModuleFilter__Group_3__0 )*
            {
             before(grammarAccess.getModuleFilterAccess().getGroup_3()); 
            // InternalN4MFParser.g:5539:1: ( rule__ModuleFilter__Group_3__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==Comma) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalN4MFParser.g:5539:2: rule__ModuleFilter__Group_3__0
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
    // InternalN4MFParser.g:5549:1: rule__ModuleFilter__Group__4 : rule__ModuleFilter__Group__4__Impl ;
    public final void rule__ModuleFilter__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5553:1: ( rule__ModuleFilter__Group__4__Impl )
            // InternalN4MFParser.g:5554:2: rule__ModuleFilter__Group__4__Impl
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
    // InternalN4MFParser.g:5560:1: rule__ModuleFilter__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5564:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:5565:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:5565:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:5566:1: RightCurlyBracket
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
    // InternalN4MFParser.g:5589:1: rule__ModuleFilter__Group_3__0 : rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 ;
    public final void rule__ModuleFilter__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5593:1: ( rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 )
            // InternalN4MFParser.g:5594:2: rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5601:1: rule__ModuleFilter__Group_3__0__Impl : ( Comma ) ;
    public final void rule__ModuleFilter__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5605:1: ( ( Comma ) )
            // InternalN4MFParser.g:5606:1: ( Comma )
            {
            // InternalN4MFParser.g:5606:1: ( Comma )
            // InternalN4MFParser.g:5607:1: Comma
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
    // InternalN4MFParser.g:5620:1: rule__ModuleFilter__Group_3__1 : rule__ModuleFilter__Group_3__1__Impl ;
    public final void rule__ModuleFilter__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5624:1: ( rule__ModuleFilter__Group_3__1__Impl )
            // InternalN4MFParser.g:5625:2: rule__ModuleFilter__Group_3__1__Impl
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
    // InternalN4MFParser.g:5631:1: rule__ModuleFilter__Group_3__1__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) ;
    public final void rule__ModuleFilter__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5635:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) )
            // InternalN4MFParser.g:5636:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:5636:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            // InternalN4MFParser.g:5637:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_3_1()); 
            // InternalN4MFParser.g:5638:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            // InternalN4MFParser.g:5638:2: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1
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
    // InternalN4MFParser.g:5652:1: rule__BootstrapModule__Group__0 : rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 ;
    public final void rule__BootstrapModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5656:1: ( rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 )
            // InternalN4MFParser.g:5657:2: rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1
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
    // InternalN4MFParser.g:5664:1: rule__BootstrapModule__Group__0__Impl : ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__BootstrapModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5668:1: ( ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:5669:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:5669:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:5670:1: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:5671:1: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:5671:2: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0
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
    // InternalN4MFParser.g:5681:1: rule__BootstrapModule__Group__1 : rule__BootstrapModule__Group__1__Impl ;
    public final void rule__BootstrapModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5685:1: ( rule__BootstrapModule__Group__1__Impl )
            // InternalN4MFParser.g:5686:2: rule__BootstrapModule__Group__1__Impl
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
    // InternalN4MFParser.g:5692:1: rule__BootstrapModule__Group__1__Impl : ( ( rule__BootstrapModule__Group_1__0 )? ) ;
    public final void rule__BootstrapModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5696:1: ( ( ( rule__BootstrapModule__Group_1__0 )? ) )
            // InternalN4MFParser.g:5697:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            {
            // InternalN4MFParser.g:5697:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            // InternalN4MFParser.g:5698:1: ( rule__BootstrapModule__Group_1__0 )?
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup_1()); 
            // InternalN4MFParser.g:5699:1: ( rule__BootstrapModule__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==In) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalN4MFParser.g:5699:2: rule__BootstrapModule__Group_1__0
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
    // InternalN4MFParser.g:5713:1: rule__BootstrapModule__Group_1__0 : rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 ;
    public final void rule__BootstrapModule__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5717:1: ( rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 )
            // InternalN4MFParser.g:5718:2: rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5725:1: rule__BootstrapModule__Group_1__0__Impl : ( In ) ;
    public final void rule__BootstrapModule__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5729:1: ( ( In ) )
            // InternalN4MFParser.g:5730:1: ( In )
            {
            // InternalN4MFParser.g:5730:1: ( In )
            // InternalN4MFParser.g:5731:1: In
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
    // InternalN4MFParser.g:5744:1: rule__BootstrapModule__Group_1__1 : rule__BootstrapModule__Group_1__1__Impl ;
    public final void rule__BootstrapModule__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5748:1: ( rule__BootstrapModule__Group_1__1__Impl )
            // InternalN4MFParser.g:5749:2: rule__BootstrapModule__Group_1__1__Impl
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
    // InternalN4MFParser.g:5755:1: rule__BootstrapModule__Group_1__1__Impl : ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) ;
    public final void rule__BootstrapModule__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5759:1: ( ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5760:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5760:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:5761:1: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:5762:1: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:5762:2: rule__BootstrapModule__SourcePathAssignment_1_1
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
    // InternalN4MFParser.g:5776:1: rule__ModuleFilterSpecifier__Group__0 : rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 ;
    public final void rule__ModuleFilterSpecifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5780:1: ( rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 )
            // InternalN4MFParser.g:5781:2: rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1
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
    // InternalN4MFParser.g:5788:1: rule__ModuleFilterSpecifier__Group__0__Impl : ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5792:1: ( ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:5793:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:5793:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:5794:1: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:5795:1: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:5795:2: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0
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
    // InternalN4MFParser.g:5805:1: rule__ModuleFilterSpecifier__Group__1 : rule__ModuleFilterSpecifier__Group__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5809:1: ( rule__ModuleFilterSpecifier__Group__1__Impl )
            // InternalN4MFParser.g:5810:2: rule__ModuleFilterSpecifier__Group__1__Impl
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
    // InternalN4MFParser.g:5816:1: rule__ModuleFilterSpecifier__Group__1__Impl : ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) ;
    public final void rule__ModuleFilterSpecifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5820:1: ( ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) )
            // InternalN4MFParser.g:5821:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            {
            // InternalN4MFParser.g:5821:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            // InternalN4MFParser.g:5822:1: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup_1()); 
            // InternalN4MFParser.g:5823:1: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==In) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalN4MFParser.g:5823:2: rule__ModuleFilterSpecifier__Group_1__0
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
    // InternalN4MFParser.g:5837:1: rule__ModuleFilterSpecifier__Group_1__0 : rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 ;
    public final void rule__ModuleFilterSpecifier__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5841:1: ( rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 )
            // InternalN4MFParser.g:5842:2: rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1
            {
            pushFollow(FOLLOW_5);
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
    // InternalN4MFParser.g:5849:1: rule__ModuleFilterSpecifier__Group_1__0__Impl : ( In ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5853:1: ( ( In ) )
            // InternalN4MFParser.g:5854:1: ( In )
            {
            // InternalN4MFParser.g:5854:1: ( In )
            // InternalN4MFParser.g:5855:1: In
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
    // InternalN4MFParser.g:5868:1: rule__ModuleFilterSpecifier__Group_1__1 : rule__ModuleFilterSpecifier__Group_1__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5872:1: ( rule__ModuleFilterSpecifier__Group_1__1__Impl )
            // InternalN4MFParser.g:5873:2: rule__ModuleFilterSpecifier__Group_1__1__Impl
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
    // InternalN4MFParser.g:5879:1: rule__ModuleFilterSpecifier__Group_1__1__Impl : ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5883:1: ( ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5884:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5884:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:5885:1: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:5886:1: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:5886:2: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1
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
    // InternalN4MFParser.g:5900:1: rule__ProjectDependency__Group__0 : rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 ;
    public final void rule__ProjectDependency__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5904:1: ( rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 )
            // InternalN4MFParser.g:5905:2: rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1
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
    // InternalN4MFParser.g:5912:1: rule__ProjectDependency__Group__0__Impl : ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) ;
    public final void rule__ProjectDependency__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5916:1: ( ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) )
            // InternalN4MFParser.g:5917:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            {
            // InternalN4MFParser.g:5917:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            // InternalN4MFParser.g:5918:1: ( rule__ProjectDependency__ProjectAssignment_0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getProjectAssignment_0()); 
            // InternalN4MFParser.g:5919:1: ( rule__ProjectDependency__ProjectAssignment_0 )
            // InternalN4MFParser.g:5919:2: rule__ProjectDependency__ProjectAssignment_0
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
    // InternalN4MFParser.g:5929:1: rule__ProjectDependency__Group__1 : rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 ;
    public final void rule__ProjectDependency__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5933:1: ( rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 )
            // InternalN4MFParser.g:5934:2: rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2
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
    // InternalN4MFParser.g:5941:1: rule__ProjectDependency__Group__1__Impl : ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) ;
    public final void rule__ProjectDependency__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5945:1: ( ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) )
            // InternalN4MFParser.g:5946:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            {
            // InternalN4MFParser.g:5946:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            // InternalN4MFParser.g:5947:1: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getVersionConstraintAssignment_1()); 
            // InternalN4MFParser.g:5948:1: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LeftParenthesis||LA35_0==LeftSquareBracket||LA35_0==RULE_INT) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalN4MFParser.g:5948:2: rule__ProjectDependency__VersionConstraintAssignment_1
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
    // InternalN4MFParser.g:5958:1: rule__ProjectDependency__Group__2 : rule__ProjectDependency__Group__2__Impl ;
    public final void rule__ProjectDependency__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5962:1: ( rule__ProjectDependency__Group__2__Impl )
            // InternalN4MFParser.g:5963:2: rule__ProjectDependency__Group__2__Impl
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
    // InternalN4MFParser.g:5969:1: rule__ProjectDependency__Group__2__Impl : ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) ;
    public final void rule__ProjectDependency__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5973:1: ( ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) )
            // InternalN4MFParser.g:5974:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            {
            // InternalN4MFParser.g:5974:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            // InternalN4MFParser.g:5975:1: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getDeclaredScopeAssignment_2()); 
            // InternalN4MFParser.g:5976:1: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Compile||LA36_0==Test) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalN4MFParser.g:5976:2: rule__ProjectDependency__DeclaredScopeAssignment_2
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
    // InternalN4MFParser.g:5992:1: rule__SimpleProjectDescription__Group__0 : rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 ;
    public final void rule__SimpleProjectDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5996:1: ( rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 )
            // InternalN4MFParser.g:5997:2: rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1
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
    // InternalN4MFParser.g:6004:1: rule__SimpleProjectDescription__Group__0__Impl : ( ( rule__SimpleProjectDescription__Group_0__0 )? ) ;
    public final void rule__SimpleProjectDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6008:1: ( ( ( rule__SimpleProjectDescription__Group_0__0 )? ) )
            // InternalN4MFParser.g:6009:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            {
            // InternalN4MFParser.g:6009:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            // InternalN4MFParser.g:6010:1: ( rule__SimpleProjectDescription__Group_0__0 )?
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup_0()); 
            // InternalN4MFParser.g:6011:1: ( rule__SimpleProjectDescription__Group_0__0 )?
            int alt37=2;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // InternalN4MFParser.g:6011:2: rule__SimpleProjectDescription__Group_0__0
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
    // InternalN4MFParser.g:6021:1: rule__SimpleProjectDescription__Group__1 : rule__SimpleProjectDescription__Group__1__Impl ;
    public final void rule__SimpleProjectDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6025:1: ( rule__SimpleProjectDescription__Group__1__Impl )
            // InternalN4MFParser.g:6026:2: rule__SimpleProjectDescription__Group__1__Impl
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
    // InternalN4MFParser.g:6032:1: rule__SimpleProjectDescription__Group__1__Impl : ( ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 ) ) ;
    public final void rule__SimpleProjectDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6036:1: ( ( ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 ) ) )
            // InternalN4MFParser.g:6037:1: ( ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 ) )
            {
            // InternalN4MFParser.g:6037:1: ( ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 ) )
            // InternalN4MFParser.g:6038:1: ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getArtifactIdAssignment_1()); 
            // InternalN4MFParser.g:6039:1: ( rule__SimpleProjectDescription__ArtifactIdAssignment_1 )
            // InternalN4MFParser.g:6039:2: rule__SimpleProjectDescription__ArtifactIdAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__SimpleProjectDescription__ArtifactIdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSimpleProjectDescriptionAccess().getArtifactIdAssignment_1()); 

            }


            }

        }
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
    // InternalN4MFParser.g:6053:1: rule__SimpleProjectDescription__Group_0__0 : rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 ;
    public final void rule__SimpleProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6057:1: ( rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:6058:2: rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1
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
    // InternalN4MFParser.g:6065:1: rule__SimpleProjectDescription__Group_0__0__Impl : ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) ;
    public final void rule__SimpleProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6069:1: ( ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) )
            // InternalN4MFParser.g:6070:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            {
            // InternalN4MFParser.g:6070:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            // InternalN4MFParser.g:6071:1: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdAssignment_0_0()); 
            // InternalN4MFParser.g:6072:1: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            // InternalN4MFParser.g:6072:2: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0
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
    // InternalN4MFParser.g:6082:1: rule__SimpleProjectDescription__Group_0__1 : rule__SimpleProjectDescription__Group_0__1__Impl ;
    public final void rule__SimpleProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6086:1: ( rule__SimpleProjectDescription__Group_0__1__Impl )
            // InternalN4MFParser.g:6087:2: rule__SimpleProjectDescription__Group_0__1__Impl
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
    // InternalN4MFParser.g:6093:1: rule__SimpleProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__SimpleProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6097:1: ( ( Colon ) )
            // InternalN4MFParser.g:6098:1: ( Colon )
            {
            // InternalN4MFParser.g:6098:1: ( Colon )
            // InternalN4MFParser.g:6099:1: Colon
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
    // InternalN4MFParser.g:6116:1: rule__VersionConstraint__Group_0__0 : rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 ;
    public final void rule__VersionConstraint__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6120:1: ( rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 )
            // InternalN4MFParser.g:6121:2: rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalN4MFParser.g:6128:1: rule__VersionConstraint__Group_0__0__Impl : ( ( rule__VersionConstraint__Alternatives_0_0 ) ) ;
    public final void rule__VersionConstraint__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6132:1: ( ( ( rule__VersionConstraint__Alternatives_0_0 ) ) )
            // InternalN4MFParser.g:6133:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            {
            // InternalN4MFParser.g:6133:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            // InternalN4MFParser.g:6134:1: ( rule__VersionConstraint__Alternatives_0_0 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_0()); 
            // InternalN4MFParser.g:6135:1: ( rule__VersionConstraint__Alternatives_0_0 )
            // InternalN4MFParser.g:6135:2: rule__VersionConstraint__Alternatives_0_0
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
    // InternalN4MFParser.g:6145:1: rule__VersionConstraint__Group_0__1 : rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 ;
    public final void rule__VersionConstraint__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6149:1: ( rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 )
            // InternalN4MFParser.g:6150:2: rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2
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
    // InternalN4MFParser.g:6157:1: rule__VersionConstraint__Group_0__1__Impl : ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6161:1: ( ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) )
            // InternalN4MFParser.g:6162:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            {
            // InternalN4MFParser.g:6162:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            // InternalN4MFParser.g:6163:1: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_0_1()); 
            // InternalN4MFParser.g:6164:1: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            // InternalN4MFParser.g:6164:2: rule__VersionConstraint__LowerVersionAssignment_0_1
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
    // InternalN4MFParser.g:6174:1: rule__VersionConstraint__Group_0__2 : rule__VersionConstraint__Group_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6178:1: ( rule__VersionConstraint__Group_0__2__Impl )
            // InternalN4MFParser.g:6179:2: rule__VersionConstraint__Group_0__2__Impl
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
    // InternalN4MFParser.g:6185:1: rule__VersionConstraint__Group_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6189:1: ( ( ( rule__VersionConstraint__Alternatives_0_2 ) ) )
            // InternalN4MFParser.g:6190:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            {
            // InternalN4MFParser.g:6190:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            // InternalN4MFParser.g:6191:1: ( rule__VersionConstraint__Alternatives_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2()); 
            // InternalN4MFParser.g:6192:1: ( rule__VersionConstraint__Alternatives_0_2 )
            // InternalN4MFParser.g:6192:2: rule__VersionConstraint__Alternatives_0_2
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
    // InternalN4MFParser.g:6208:1: rule__VersionConstraint__Group_0_2_0__0 : rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 ;
    public final void rule__VersionConstraint__Group_0_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6212:1: ( rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 )
            // InternalN4MFParser.g:6213:2: rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalN4MFParser.g:6220:1: rule__VersionConstraint__Group_0_2_0__0__Impl : ( Comma ) ;
    public final void rule__VersionConstraint__Group_0_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6224:1: ( ( Comma ) )
            // InternalN4MFParser.g:6225:1: ( Comma )
            {
            // InternalN4MFParser.g:6225:1: ( Comma )
            // InternalN4MFParser.g:6226:1: Comma
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
    // InternalN4MFParser.g:6239:1: rule__VersionConstraint__Group_0_2_0__1 : rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 ;
    public final void rule__VersionConstraint__Group_0_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6243:1: ( rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 )
            // InternalN4MFParser.g:6244:2: rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2
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
    // InternalN4MFParser.g:6251:1: rule__VersionConstraint__Group_0_2_0__1__Impl : ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6255:1: ( ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) )
            // InternalN4MFParser.g:6256:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            {
            // InternalN4MFParser.g:6256:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            // InternalN4MFParser.g:6257:1: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getUpperVersionAssignment_0_2_0_1()); 
            // InternalN4MFParser.g:6258:1: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            // InternalN4MFParser.g:6258:2: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1
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
    // InternalN4MFParser.g:6268:1: rule__VersionConstraint__Group_0_2_0__2 : rule__VersionConstraint__Group_0_2_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6272:1: ( rule__VersionConstraint__Group_0_2_0__2__Impl )
            // InternalN4MFParser.g:6273:2: rule__VersionConstraint__Group_0_2_0__2__Impl
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
    // InternalN4MFParser.g:6279:1: rule__VersionConstraint__Group_0_2_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6283:1: ( ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) )
            // InternalN4MFParser.g:6284:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            {
            // InternalN4MFParser.g:6284:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            // InternalN4MFParser.g:6285:1: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2_0_2()); 
            // InternalN4MFParser.g:6286:1: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            // InternalN4MFParser.g:6286:2: rule__VersionConstraint__Alternatives_0_2_0_2
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


    // $ANTLR start "rule__N4mfIdentifier__Group_12__0"
    // InternalN4MFParser.g:6302:1: rule__N4mfIdentifier__Group_12__0 : rule__N4mfIdentifier__Group_12__0__Impl rule__N4mfIdentifier__Group_12__1 ;
    public final void rule__N4mfIdentifier__Group_12__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6306:1: ( rule__N4mfIdentifier__Group_12__0__Impl rule__N4mfIdentifier__Group_12__1 )
            // InternalN4MFParser.g:6307:2: rule__N4mfIdentifier__Group_12__0__Impl rule__N4mfIdentifier__Group_12__1
            {
            pushFollow(FOLLOW_34);
            rule__N4mfIdentifier__Group_12__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_12__1();

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
    // $ANTLR end "rule__N4mfIdentifier__Group_12__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_12__0__Impl"
    // InternalN4MFParser.g:6314:1: rule__N4mfIdentifier__Group_12__0__Impl : ( ProjectDependencies ) ;
    public final void rule__N4mfIdentifier__Group_12__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6318:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:6319:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:6319:1: ( ProjectDependencies )
            // InternalN4MFParser.g:6320:1: ProjectDependencies
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_12_0()); 
            match(input,ProjectDependencies,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_12__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_12__1"
    // InternalN4MFParser.g:6333:1: rule__N4mfIdentifier__Group_12__1 : rule__N4mfIdentifier__Group_12__1__Impl ;
    public final void rule__N4mfIdentifier__Group_12__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6337:1: ( rule__N4mfIdentifier__Group_12__1__Impl )
            // InternalN4MFParser.g:6338:2: rule__N4mfIdentifier__Group_12__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_12__1__Impl();

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
    // $ANTLR end "rule__N4mfIdentifier__Group_12__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_12__1__Impl"
    // InternalN4MFParser.g:6344:1: rule__N4mfIdentifier__Group_12__1__Impl : ( KW_System ) ;
    public final void rule__N4mfIdentifier__Group_12__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6348:1: ( ( KW_System ) )
            // InternalN4MFParser.g:6349:1: ( KW_System )
            {
            // InternalN4MFParser.g:6349:1: ( KW_System )
            // InternalN4MFParser.g:6350:1: KW_System
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_12_1()); 
            match(input,KW_System,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_12_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_12__1__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_16__0"
    // InternalN4MFParser.g:6367:1: rule__N4mfIdentifier__Group_16__0 : rule__N4mfIdentifier__Group_16__0__Impl rule__N4mfIdentifier__Group_16__1 ;
    public final void rule__N4mfIdentifier__Group_16__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6371:1: ( rule__N4mfIdentifier__Group_16__0__Impl rule__N4mfIdentifier__Group_16__1 )
            // InternalN4MFParser.g:6372:2: rule__N4mfIdentifier__Group_16__0__Impl rule__N4mfIdentifier__Group_16__1
            {
            pushFollow(FOLLOW_35);
            rule__N4mfIdentifier__Group_16__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_16__1();

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
    // $ANTLR end "rule__N4mfIdentifier__Group_16__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_16__0__Impl"
    // InternalN4MFParser.g:6379:1: rule__N4mfIdentifier__Group_16__0__Impl : ( Processor ) ;
    public final void rule__N4mfIdentifier__Group_16__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6383:1: ( ( Processor ) )
            // InternalN4MFParser.g:6384:1: ( Processor )
            {
            // InternalN4MFParser.g:6384:1: ( Processor )
            // InternalN4MFParser.g:6385:1: Processor
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_16_0()); 
            match(input,Processor,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_16_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_16__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_16__1"
    // InternalN4MFParser.g:6398:1: rule__N4mfIdentifier__Group_16__1 : rule__N4mfIdentifier__Group_16__1__Impl ;
    public final void rule__N4mfIdentifier__Group_16__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6402:1: ( rule__N4mfIdentifier__Group_16__1__Impl )
            // InternalN4MFParser.g:6403:2: rule__N4mfIdentifier__Group_16__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_16__1__Impl();

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
    // $ANTLR end "rule__N4mfIdentifier__Group_16__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_16__1__Impl"
    // InternalN4MFParser.g:6409:1: rule__N4mfIdentifier__Group_16__1__Impl : ( Source ) ;
    public final void rule__N4mfIdentifier__Group_16__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6413:1: ( ( Source ) )
            // InternalN4MFParser.g:6414:1: ( Source )
            {
            // InternalN4MFParser.g:6414:1: ( Source )
            // InternalN4MFParser.g:6415:1: Source
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_16_1()); 
            match(input,Source,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_16_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_16__1__Impl"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup"
    // InternalN4MFParser.g:6433:1: rule__ProjectDescription__UnorderedGroup : rule__ProjectDescription__UnorderedGroup__0 {...}?;
    public final void rule__ProjectDescription__UnorderedGroup() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            
        try {
            // InternalN4MFParser.g:6438:1: ( rule__ProjectDescription__UnorderedGroup__0 {...}?)
            // InternalN4MFParser.g:6439:2: rule__ProjectDescription__UnorderedGroup__0 {...}?
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
    // InternalN4MFParser.g:6450:1: rule__ProjectDescription__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) ) ;
    public final void rule__ProjectDescription__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalN4MFParser.g:6455:1: ( ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) ) )
            // InternalN4MFParser.g:6456:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )
            {
            // InternalN4MFParser.g:6456:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )
            int alt38=22;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // InternalN4MFParser.g:6458:4: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6458:4: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    // InternalN4MFParser.g:6459:5: {...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalN4MFParser.g:6459:113: ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    // InternalN4MFParser.g:6460:6: ( ( rule__ProjectDescription__Group_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6466:6: ( ( rule__ProjectDescription__Group_0__0 ) )
                    // InternalN4MFParser.g:6468:7: ( rule__ProjectDescription__Group_0__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_0()); 
                    // InternalN4MFParser.g:6469:7: ( rule__ProjectDescription__Group_0__0 )
                    // InternalN4MFParser.g:6469:8: rule__ProjectDescription__Group_0__0
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
                    // InternalN4MFParser.g:6475:4: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6475:4: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    // InternalN4MFParser.g:6476:5: {...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalN4MFParser.g:6476:113: ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    // InternalN4MFParser.g:6477:6: ( ( rule__ProjectDescription__Group_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6483:6: ( ( rule__ProjectDescription__Group_1__0 ) )
                    // InternalN4MFParser.g:6485:7: ( rule__ProjectDescription__Group_1__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_1()); 
                    // InternalN4MFParser.g:6486:7: ( rule__ProjectDescription__Group_1__0 )
                    // InternalN4MFParser.g:6486:8: rule__ProjectDescription__Group_1__0
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
                    // InternalN4MFParser.g:6492:4: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6492:4: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    // InternalN4MFParser.g:6493:5: {...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalN4MFParser.g:6493:113: ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    // InternalN4MFParser.g:6494:6: ( ( rule__ProjectDescription__Group_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6500:6: ( ( rule__ProjectDescription__Group_2__0 ) )
                    // InternalN4MFParser.g:6502:7: ( rule__ProjectDescription__Group_2__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_2()); 
                    // InternalN4MFParser.g:6503:7: ( rule__ProjectDescription__Group_2__0 )
                    // InternalN4MFParser.g:6503:8: rule__ProjectDescription__Group_2__0
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
                    // InternalN4MFParser.g:6509:4: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6509:4: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    // InternalN4MFParser.g:6510:5: {...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalN4MFParser.g:6510:113: ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    // InternalN4MFParser.g:6511:6: ( ( rule__ProjectDescription__Group_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6517:6: ( ( rule__ProjectDescription__Group_3__0 ) )
                    // InternalN4MFParser.g:6519:7: ( rule__ProjectDescription__Group_3__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_3()); 
                    // InternalN4MFParser.g:6520:7: ( rule__ProjectDescription__Group_3__0 )
                    // InternalN4MFParser.g:6520:8: rule__ProjectDescription__Group_3__0
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
                    // InternalN4MFParser.g:6526:4: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6526:4: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    // InternalN4MFParser.g:6527:5: {...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalN4MFParser.g:6527:113: ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    // InternalN4MFParser.g:6528:6: ( ( rule__ProjectDescription__Group_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6534:6: ( ( rule__ProjectDescription__Group_4__0 ) )
                    // InternalN4MFParser.g:6536:7: ( rule__ProjectDescription__Group_4__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_4()); 
                    // InternalN4MFParser.g:6537:7: ( rule__ProjectDescription__Group_4__0 )
                    // InternalN4MFParser.g:6537:8: rule__ProjectDescription__Group_4__0
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
                    // InternalN4MFParser.g:6543:4: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6543:4: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    // InternalN4MFParser.g:6544:5: {...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalN4MFParser.g:6544:113: ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    // InternalN4MFParser.g:6545:6: ( ( rule__ProjectDescription__Group_5__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6551:6: ( ( rule__ProjectDescription__Group_5__0 ) )
                    // InternalN4MFParser.g:6553:7: ( rule__ProjectDescription__Group_5__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_5()); 
                    // InternalN4MFParser.g:6554:7: ( rule__ProjectDescription__Group_5__0 )
                    // InternalN4MFParser.g:6554:8: rule__ProjectDescription__Group_5__0
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
                    // InternalN4MFParser.g:6560:4: ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6560:4: ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) )
                    // InternalN4MFParser.g:6561:5: {...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6)");
                    }
                    // InternalN4MFParser.g:6561:113: ( ( ( rule__ProjectDescription__Group_6__0 ) ) )
                    // InternalN4MFParser.g:6562:6: ( ( rule__ProjectDescription__Group_6__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6568:6: ( ( rule__ProjectDescription__Group_6__0 ) )
                    // InternalN4MFParser.g:6570:7: ( rule__ProjectDescription__Group_6__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_6()); 
                    // InternalN4MFParser.g:6571:7: ( rule__ProjectDescription__Group_6__0 )
                    // InternalN4MFParser.g:6571:8: rule__ProjectDescription__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_6()); 

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:6577:4: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) )
                    {
                    // InternalN4MFParser.g:6577:4: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) )
                    // InternalN4MFParser.g:6578:5: {...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7)");
                    }
                    // InternalN4MFParser.g:6578:113: ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) )
                    // InternalN4MFParser.g:6579:6: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6585:6: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) )
                    // InternalN4MFParser.g:6587:7: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentAssignment_7()); 
                    // InternalN4MFParser.g:6588:7: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 )
                    // InternalN4MFParser.g:6588:8: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentAssignment_7()); 

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:6594:4: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) )
                    {
                    // InternalN4MFParser.g:6594:4: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) )
                    // InternalN4MFParser.g:6595:5: {...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8)");
                    }
                    // InternalN4MFParser.g:6595:113: ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) )
                    // InternalN4MFParser.g:6596:6: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6602:6: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) )
                    // InternalN4MFParser.g:6604:7: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesAssignment_8()); 
                    // InternalN4MFParser.g:6605:7: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 )
                    // InternalN4MFParser.g:6605:8: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesAssignment_8()); 

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:6611:4: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) )
                    {
                    // InternalN4MFParser.g:6611:4: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) )
                    // InternalN4MFParser.g:6612:5: {...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9)");
                    }
                    // InternalN4MFParser.g:6612:113: ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) )
                    // InternalN4MFParser.g:6613:6: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6619:6: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) )
                    // InternalN4MFParser.g:6621:7: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesAssignment_9()); 
                    // InternalN4MFParser.g:6622:7: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 )
                    // InternalN4MFParser.g:6622:8: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesAssignment_9()); 

                    }


                    }


                    }


                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:6628:4: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) )
                    {
                    // InternalN4MFParser.g:6628:4: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) )
                    // InternalN4MFParser.g:6629:5: {...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10)");
                    }
                    // InternalN4MFParser.g:6629:114: ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) )
                    // InternalN4MFParser.g:6630:6: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6636:6: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) )
                    // InternalN4MFParser.g:6638:7: ( rule__ProjectDescription__ProjectDependenciesAssignment_10 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesAssignment_10()); 
                    // InternalN4MFParser.g:6639:7: ( rule__ProjectDescription__ProjectDependenciesAssignment_10 )
                    // InternalN4MFParser.g:6639:8: rule__ProjectDescription__ProjectDependenciesAssignment_10
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ProjectDependenciesAssignment_10();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesAssignment_10()); 

                    }


                    }


                    }


                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:6645:4: ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6645:4: ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) )
                    // InternalN4MFParser.g:6646:5: {...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11)");
                    }
                    // InternalN4MFParser.g:6646:114: ( ( ( rule__ProjectDescription__Group_11__0 ) ) )
                    // InternalN4MFParser.g:6647:6: ( ( rule__ProjectDescription__Group_11__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6653:6: ( ( rule__ProjectDescription__Group_11__0 ) )
                    // InternalN4MFParser.g:6655:7: ( rule__ProjectDescription__Group_11__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_11()); 
                    // InternalN4MFParser.g:6656:7: ( rule__ProjectDescription__Group_11__0 )
                    // InternalN4MFParser.g:6656:8: rule__ProjectDescription__Group_11__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_11__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_11()); 

                    }


                    }


                    }


                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:6662:4: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) )
                    {
                    // InternalN4MFParser.g:6662:4: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) )
                    // InternalN4MFParser.g:6663:5: {...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12)");
                    }
                    // InternalN4MFParser.g:6663:114: ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) )
                    // InternalN4MFParser.g:6664:6: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6670:6: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) )
                    // InternalN4MFParser.g:6672:7: ( rule__ProjectDescription__ImplementedProjectsAssignment_12 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsAssignment_12()); 
                    // InternalN4MFParser.g:6673:7: ( rule__ProjectDescription__ImplementedProjectsAssignment_12 )
                    // InternalN4MFParser.g:6673:8: rule__ProjectDescription__ImplementedProjectsAssignment_12
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ImplementedProjectsAssignment_12();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsAssignment_12()); 

                    }


                    }


                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:6679:4: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) )
                    {
                    // InternalN4MFParser.g:6679:4: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) )
                    // InternalN4MFParser.g:6680:5: {...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13)");
                    }
                    // InternalN4MFParser.g:6680:114: ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) )
                    // InternalN4MFParser.g:6681:6: ( ( rule__ProjectDescription__InitModulesAssignment_13 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6687:6: ( ( rule__ProjectDescription__InitModulesAssignment_13 ) )
                    // InternalN4MFParser.g:6689:7: ( rule__ProjectDescription__InitModulesAssignment_13 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getInitModulesAssignment_13()); 
                    // InternalN4MFParser.g:6690:7: ( rule__ProjectDescription__InitModulesAssignment_13 )
                    // InternalN4MFParser.g:6690:8: rule__ProjectDescription__InitModulesAssignment_13
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__InitModulesAssignment_13();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getInitModulesAssignment_13()); 

                    }


                    }


                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:6696:4: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) )
                    {
                    // InternalN4MFParser.g:6696:4: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) )
                    // InternalN4MFParser.g:6697:5: {...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14)");
                    }
                    // InternalN4MFParser.g:6697:114: ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) )
                    // InternalN4MFParser.g:6698:6: ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6704:6: ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) )
                    // InternalN4MFParser.g:6706:7: ( rule__ProjectDescription__ExecModuleAssignment_14 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExecModuleAssignment_14()); 
                    // InternalN4MFParser.g:6707:7: ( rule__ProjectDescription__ExecModuleAssignment_14 )
                    // InternalN4MFParser.g:6707:8: rule__ProjectDescription__ExecModuleAssignment_14
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__ExecModuleAssignment_14();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getExecModuleAssignment_14()); 

                    }


                    }


                    }


                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:6713:4: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6713:4: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    // InternalN4MFParser.g:6714:5: {...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15)");
                    }
                    // InternalN4MFParser.g:6714:114: ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    // InternalN4MFParser.g:6715:6: ( ( rule__ProjectDescription__Group_15__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6721:6: ( ( rule__ProjectDescription__Group_15__0 ) )
                    // InternalN4MFParser.g:6723:7: ( rule__ProjectDescription__Group_15__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_15()); 
                    // InternalN4MFParser.g:6724:7: ( rule__ProjectDescription__Group_15__0 )
                    // InternalN4MFParser.g:6724:8: rule__ProjectDescription__Group_15__0
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
                    // InternalN4MFParser.g:6730:4: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6730:4: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    // InternalN4MFParser.g:6731:5: {...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16)");
                    }
                    // InternalN4MFParser.g:6731:114: ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    // InternalN4MFParser.g:6732:6: ( ( rule__ProjectDescription__Group_16__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6738:6: ( ( rule__ProjectDescription__Group_16__0 ) )
                    // InternalN4MFParser.g:6740:7: ( rule__ProjectDescription__Group_16__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_16()); 
                    // InternalN4MFParser.g:6741:7: ( rule__ProjectDescription__Group_16__0 )
                    // InternalN4MFParser.g:6741:8: rule__ProjectDescription__Group_16__0
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
                    // InternalN4MFParser.g:6747:4: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6747:4: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    // InternalN4MFParser.g:6748:5: {...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17)");
                    }
                    // InternalN4MFParser.g:6748:114: ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    // InternalN4MFParser.g:6749:6: ( ( rule__ProjectDescription__Group_17__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6755:6: ( ( rule__ProjectDescription__Group_17__0 ) )
                    // InternalN4MFParser.g:6757:7: ( rule__ProjectDescription__Group_17__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_17()); 
                    // InternalN4MFParser.g:6758:7: ( rule__ProjectDescription__Group_17__0 )
                    // InternalN4MFParser.g:6758:8: rule__ProjectDescription__Group_17__0
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
                    // InternalN4MFParser.g:6764:4: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6764:4: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    // InternalN4MFParser.g:6765:5: {...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18)");
                    }
                    // InternalN4MFParser.g:6765:114: ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    // InternalN4MFParser.g:6766:6: ( ( rule__ProjectDescription__Group_18__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6772:6: ( ( rule__ProjectDescription__Group_18__0 ) )
                    // InternalN4MFParser.g:6774:7: ( rule__ProjectDescription__Group_18__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_18()); 
                    // InternalN4MFParser.g:6775:7: ( rule__ProjectDescription__Group_18__0 )
                    // InternalN4MFParser.g:6775:8: rule__ProjectDescription__Group_18__0
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
                    // InternalN4MFParser.g:6781:4: ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6781:4: ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) )
                    // InternalN4MFParser.g:6782:5: {...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19)");
                    }
                    // InternalN4MFParser.g:6782:114: ( ( ( rule__ProjectDescription__Group_19__0 ) ) )
                    // InternalN4MFParser.g:6783:6: ( ( rule__ProjectDescription__Group_19__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6789:6: ( ( rule__ProjectDescription__Group_19__0 ) )
                    // InternalN4MFParser.g:6791:7: ( rule__ProjectDescription__Group_19__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_19()); 
                    // InternalN4MFParser.g:6792:7: ( rule__ProjectDescription__Group_19__0 )
                    // InternalN4MFParser.g:6792:8: rule__ProjectDescription__Group_19__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_19__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_19()); 

                    }


                    }


                    }


                    }
                    break;
                case 21 :
                    // InternalN4MFParser.g:6798:4: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) )
                    {
                    // InternalN4MFParser.g:6798:4: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) )
                    // InternalN4MFParser.g:6799:5: {...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20)");
                    }
                    // InternalN4MFParser.g:6799:114: ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) )
                    // InternalN4MFParser.g:6800:6: ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6806:6: ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) )
                    // InternalN4MFParser.g:6808:7: ( rule__ProjectDescription__TestedProjectsAssignment_20 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getTestedProjectsAssignment_20()); 
                    // InternalN4MFParser.g:6809:7: ( rule__ProjectDescription__TestedProjectsAssignment_20 )
                    // InternalN4MFParser.g:6809:8: rule__ProjectDescription__TestedProjectsAssignment_20
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__TestedProjectsAssignment_20();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getTestedProjectsAssignment_20()); 

                    }


                    }


                    }


                    }
                    break;
                case 22 :
                    // InternalN4MFParser.g:6815:4: ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6815:4: ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) )
                    // InternalN4MFParser.g:6816:5: {...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21)");
                    }
                    // InternalN4MFParser.g:6816:114: ( ( ( rule__ProjectDescription__Group_21__0 ) ) )
                    // InternalN4MFParser.g:6817:6: ( ( rule__ProjectDescription__Group_21__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6823:6: ( ( rule__ProjectDescription__Group_21__0 ) )
                    // InternalN4MFParser.g:6825:7: ( rule__ProjectDescription__Group_21__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_21()); 
                    // InternalN4MFParser.g:6826:7: ( rule__ProjectDescription__Group_21__0 )
                    // InternalN4MFParser.g:6826:8: rule__ProjectDescription__Group_21__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__Group_21__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProjectDescriptionAccess().getGroup_21()); 

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
    // InternalN4MFParser.g:6841:1: rule__ProjectDescription__UnorderedGroup__0 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6845:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? )
            // InternalN4MFParser.g:6846:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6847:2: ( rule__ProjectDescription__UnorderedGroup__1 )?
            int alt39=2;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // InternalN4MFParser.g:6847:2: rule__ProjectDescription__UnorderedGroup__1
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
    // InternalN4MFParser.g:6854:1: rule__ProjectDescription__UnorderedGroup__1 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6858:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? )
            // InternalN4MFParser.g:6859:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6860:2: ( rule__ProjectDescription__UnorderedGroup__2 )?
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // InternalN4MFParser.g:6860:2: rule__ProjectDescription__UnorderedGroup__2
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
    // InternalN4MFParser.g:6867:1: rule__ProjectDescription__UnorderedGroup__2 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6871:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? )
            // InternalN4MFParser.g:6872:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6873:2: ( rule__ProjectDescription__UnorderedGroup__3 )?
            int alt41=2;
            alt41 = dfa41.predict(input);
            switch (alt41) {
                case 1 :
                    // InternalN4MFParser.g:6873:2: rule__ProjectDescription__UnorderedGroup__3
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
    // InternalN4MFParser.g:6880:1: rule__ProjectDescription__UnorderedGroup__3 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6884:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? )
            // InternalN4MFParser.g:6885:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6886:2: ( rule__ProjectDescription__UnorderedGroup__4 )?
            int alt42=2;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // InternalN4MFParser.g:6886:2: rule__ProjectDescription__UnorderedGroup__4
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
    // InternalN4MFParser.g:6893:1: rule__ProjectDescription__UnorderedGroup__4 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6897:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? )
            // InternalN4MFParser.g:6898:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6899:2: ( rule__ProjectDescription__UnorderedGroup__5 )?
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // InternalN4MFParser.g:6899:2: rule__ProjectDescription__UnorderedGroup__5
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
    // InternalN4MFParser.g:6906:1: rule__ProjectDescription__UnorderedGroup__5 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6910:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? )
            // InternalN4MFParser.g:6911:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6912:2: ( rule__ProjectDescription__UnorderedGroup__6 )?
            int alt44=2;
            alt44 = dfa44.predict(input);
            switch (alt44) {
                case 1 :
                    // InternalN4MFParser.g:6912:2: rule__ProjectDescription__UnorderedGroup__6
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
    // InternalN4MFParser.g:6919:1: rule__ProjectDescription__UnorderedGroup__6 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6923:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? )
            // InternalN4MFParser.g:6924:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6925:2: ( rule__ProjectDescription__UnorderedGroup__7 )?
            int alt45=2;
            alt45 = dfa45.predict(input);
            switch (alt45) {
                case 1 :
                    // InternalN4MFParser.g:6925:2: rule__ProjectDescription__UnorderedGroup__7
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
    // InternalN4MFParser.g:6932:1: rule__ProjectDescription__UnorderedGroup__7 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6936:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? )
            // InternalN4MFParser.g:6937:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6938:2: ( rule__ProjectDescription__UnorderedGroup__8 )?
            int alt46=2;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // InternalN4MFParser.g:6938:2: rule__ProjectDescription__UnorderedGroup__8
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
    // InternalN4MFParser.g:6945:1: rule__ProjectDescription__UnorderedGroup__8 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6949:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? )
            // InternalN4MFParser.g:6950:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6951:2: ( rule__ProjectDescription__UnorderedGroup__9 )?
            int alt47=2;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // InternalN4MFParser.g:6951:2: rule__ProjectDescription__UnorderedGroup__9
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
    // InternalN4MFParser.g:6958:1: rule__ProjectDescription__UnorderedGroup__9 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6962:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? )
            // InternalN4MFParser.g:6963:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6964:2: ( rule__ProjectDescription__UnorderedGroup__10 )?
            int alt48=2;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // InternalN4MFParser.g:6964:2: rule__ProjectDescription__UnorderedGroup__10
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
    // InternalN4MFParser.g:6971:1: rule__ProjectDescription__UnorderedGroup__10 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6975:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? )
            // InternalN4MFParser.g:6976:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6977:2: ( rule__ProjectDescription__UnorderedGroup__11 )?
            int alt49=2;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // InternalN4MFParser.g:6977:2: rule__ProjectDescription__UnorderedGroup__11
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
    // InternalN4MFParser.g:6984:1: rule__ProjectDescription__UnorderedGroup__11 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6988:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? )
            // InternalN4MFParser.g:6989:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6990:2: ( rule__ProjectDescription__UnorderedGroup__12 )?
            int alt50=2;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1 :
                    // InternalN4MFParser.g:6990:2: rule__ProjectDescription__UnorderedGroup__12
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
    // InternalN4MFParser.g:6997:1: rule__ProjectDescription__UnorderedGroup__12 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7001:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? )
            // InternalN4MFParser.g:7002:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7003:2: ( rule__ProjectDescription__UnorderedGroup__13 )?
            int alt51=2;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // InternalN4MFParser.g:7003:2: rule__ProjectDescription__UnorderedGroup__13
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
    // InternalN4MFParser.g:7010:1: rule__ProjectDescription__UnorderedGroup__13 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7014:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? )
            // InternalN4MFParser.g:7015:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7016:2: ( rule__ProjectDescription__UnorderedGroup__14 )?
            int alt52=2;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // InternalN4MFParser.g:7016:2: rule__ProjectDescription__UnorderedGroup__14
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
    // InternalN4MFParser.g:7023:1: rule__ProjectDescription__UnorderedGroup__14 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7027:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? )
            // InternalN4MFParser.g:7028:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7029:2: ( rule__ProjectDescription__UnorderedGroup__15 )?
            int alt53=2;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // InternalN4MFParser.g:7029:2: rule__ProjectDescription__UnorderedGroup__15
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
    // InternalN4MFParser.g:7036:1: rule__ProjectDescription__UnorderedGroup__15 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7040:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? )
            // InternalN4MFParser.g:7041:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7042:2: ( rule__ProjectDescription__UnorderedGroup__16 )?
            int alt54=2;
            alt54 = dfa54.predict(input);
            switch (alt54) {
                case 1 :
                    // InternalN4MFParser.g:7042:2: rule__ProjectDescription__UnorderedGroup__16
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
    // InternalN4MFParser.g:7049:1: rule__ProjectDescription__UnorderedGroup__16 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__16() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7053:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? )
            // InternalN4MFParser.g:7054:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7055:2: ( rule__ProjectDescription__UnorderedGroup__17 )?
            int alt55=2;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // InternalN4MFParser.g:7055:2: rule__ProjectDescription__UnorderedGroup__17
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
    // InternalN4MFParser.g:7062:1: rule__ProjectDescription__UnorderedGroup__17 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__17() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7066:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? )
            // InternalN4MFParser.g:7067:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7068:2: ( rule__ProjectDescription__UnorderedGroup__18 )?
            int alt56=2;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // InternalN4MFParser.g:7068:2: rule__ProjectDescription__UnorderedGroup__18
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
    // InternalN4MFParser.g:7075:1: rule__ProjectDescription__UnorderedGroup__18 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__18() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7079:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? )
            // InternalN4MFParser.g:7080:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7081:2: ( rule__ProjectDescription__UnorderedGroup__19 )?
            int alt57=2;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // InternalN4MFParser.g:7081:2: rule__ProjectDescription__UnorderedGroup__19
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
    // InternalN4MFParser.g:7088:1: rule__ProjectDescription__UnorderedGroup__19 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__19() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7092:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? )
            // InternalN4MFParser.g:7093:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7094:2: ( rule__ProjectDescription__UnorderedGroup__20 )?
            int alt58=2;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // InternalN4MFParser.g:7094:2: rule__ProjectDescription__UnorderedGroup__20
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
    // InternalN4MFParser.g:7101:1: rule__ProjectDescription__UnorderedGroup__20 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__20() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7105:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )? )
            // InternalN4MFParser.g:7106:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7107:2: ( rule__ProjectDescription__UnorderedGroup__21 )?
            int alt59=2;
            alt59 = dfa59.predict(input);
            switch (alt59) {
                case 1 :
                    // InternalN4MFParser.g:7107:2: rule__ProjectDescription__UnorderedGroup__21
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProjectDescription__UnorderedGroup__21();

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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__20"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup__21"
    // InternalN4MFParser.g:7114:1: rule__ProjectDescription__UnorderedGroup__21 : rule__ProjectDescription__UnorderedGroup__Impl ;
    public final void rule__ProjectDescription__UnorderedGroup__21() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7118:1: ( rule__ProjectDescription__UnorderedGroup__Impl )
            // InternalN4MFParser.g:7119:2: rule__ProjectDescription__UnorderedGroup__Impl
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
    // $ANTLR end "rule__ProjectDescription__UnorderedGroup__21"


    // $ANTLR start "rule__ProjectDescription__ArtifactIdAssignment_0_2"
    // InternalN4MFParser.g:7170:1: rule__ProjectDescription__ArtifactIdAssignment_0_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ArtifactIdAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7174:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7175:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7175:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7176:1: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ArtifactIdAssignment_0_2"


    // $ANTLR start "rule__ProjectDescription__ProjectNameAssignment_1_2"
    // InternalN4MFParser.g:7185:1: rule__ProjectDescription__ProjectNameAssignment_1_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ProjectNameAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7189:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7190:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7190:1: ( RULE_STRING )
            // InternalN4MFParser.g:7191:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectNameSTRINGTerminalRuleCall_1_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getProjectNameSTRINGTerminalRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ProjectNameAssignment_1_2"


    // $ANTLR start "rule__ProjectDescription__ProjectTypeAssignment_2_2"
    // InternalN4MFParser.g:7200:1: rule__ProjectDescription__ProjectTypeAssignment_2_2 : ( ruleProjectType ) ;
    public final void rule__ProjectDescription__ProjectTypeAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7204:1: ( ( ruleProjectType ) )
            // InternalN4MFParser.g:7205:1: ( ruleProjectType )
            {
            // InternalN4MFParser.g:7205:1: ( ruleProjectType )
            // InternalN4MFParser.g:7206:1: ruleProjectType
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectType();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ProjectTypeAssignment_2_2"


    // $ANTLR start "rule__ProjectDescription__ProjectVersionAssignment_3_2"
    // InternalN4MFParser.g:7215:1: rule__ProjectDescription__ProjectVersionAssignment_3_2 : ( ruleDeclaredVersion ) ;
    public final void rule__ProjectDescription__ProjectVersionAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7219:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:7220:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:7220:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:7221:1: ruleDeclaredVersion
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleDeclaredVersion();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ProjectVersionAssignment_3_2"


    // $ANTLR start "rule__ProjectDescription__DeclaredVendorIdAssignment_4_2"
    // InternalN4MFParser.g:7230:1: rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__DeclaredVendorIdAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7234:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7235:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7235:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7236:1: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__DeclaredVendorIdAssignment_4_2"


    // $ANTLR start "rule__ProjectDescription__VendorNameAssignment_5_2"
    // InternalN4MFParser.g:7245:1: rule__ProjectDescription__VendorNameAssignment_5_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__VendorNameAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7249:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7250:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7250:1: ( RULE_STRING )
            // InternalN4MFParser.g:7251:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_5_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_5_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__VendorNameAssignment_5_2"


    // $ANTLR start "rule__ProjectDescription__MainModuleAssignment_6_2"
    // InternalN4MFParser.g:7260:1: rule__ProjectDescription__MainModuleAssignment_6_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__MainModuleAssignment_6_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7264:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7265:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7265:1: ( RULE_STRING )
            // InternalN4MFParser.g:7266:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_6_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_6_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__MainModuleAssignment_6_2"


    // $ANTLR start "rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7"
    // InternalN4MFParser.g:7275:1: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 : ( ruleExtendedRuntimeEnvironment ) ;
    public final void rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7279:1: ( ( ruleExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:7280:1: ( ruleExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:7280:1: ( ruleExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:7281:1: ruleExtendedRuntimeEnvironment
            {
             before(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleExtendedRuntimeEnvironment();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7"


    // $ANTLR start "rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8"
    // InternalN4MFParser.g:7290:1: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 : ( ruleProvidedRuntimeLibraries ) ;
    public final void rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7294:1: ( ( ruleProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:7295:1: ( ruleProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:7295:1: ( ruleProvidedRuntimeLibraries )
            // InternalN4MFParser.g:7296:1: ruleProvidedRuntimeLibraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleProvidedRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8"


    // $ANTLR start "rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9"
    // InternalN4MFParser.g:7305:1: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 : ( ruleRequiredRuntimeLibraries ) ;
    public final void rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7309:1: ( ( ruleRequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:7310:1: ( ruleRequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:7310:1: ( ruleRequiredRuntimeLibraries )
            // InternalN4MFParser.g:7311:1: ruleRequiredRuntimeLibraries
            {
             before(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_9_0()); 
            pushFollow(FOLLOW_2);
            ruleRequiredRuntimeLibraries();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9"


    // $ANTLR start "rule__ProjectDescription__ProjectDependenciesAssignment_10"
    // InternalN4MFParser.g:7320:1: rule__ProjectDescription__ProjectDependenciesAssignment_10 : ( ruleProjectDependencies ) ;
    public final void rule__ProjectDescription__ProjectDependenciesAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7324:1: ( ( ruleProjectDependencies ) )
            // InternalN4MFParser.g:7325:1: ( ruleProjectDependencies )
            {
            // InternalN4MFParser.g:7325:1: ( ruleProjectDependencies )
            // InternalN4MFParser.g:7326:1: ruleProjectDependencies
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleProjectDependencies();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ProjectDependenciesAssignment_10"


    // $ANTLR start "rule__ProjectDescription__ImplementationIdAssignment_11_2"
    // InternalN4MFParser.g:7335:1: rule__ProjectDescription__ImplementationIdAssignment_11_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ImplementationIdAssignment_11_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7339:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7340:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7340:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7341:1: ruleN4mfIdentifier
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_11_2_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_11_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ImplementationIdAssignment_11_2"


    // $ANTLR start "rule__ProjectDescription__ImplementedProjectsAssignment_12"
    // InternalN4MFParser.g:7350:1: rule__ProjectDescription__ImplementedProjectsAssignment_12 : ( ruleImplementedProjects ) ;
    public final void rule__ProjectDescription__ImplementedProjectsAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7354:1: ( ( ruleImplementedProjects ) )
            // InternalN4MFParser.g:7355:1: ( ruleImplementedProjects )
            {
            // InternalN4MFParser.g:7355:1: ( ruleImplementedProjects )
            // InternalN4MFParser.g:7356:1: ruleImplementedProjects
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_12_0()); 
            pushFollow(FOLLOW_2);
            ruleImplementedProjects();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ImplementedProjectsAssignment_12"


    // $ANTLR start "rule__ProjectDescription__InitModulesAssignment_13"
    // InternalN4MFParser.g:7365:1: rule__ProjectDescription__InitModulesAssignment_13 : ( ruleInitModules ) ;
    public final void rule__ProjectDescription__InitModulesAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7369:1: ( ( ruleInitModules ) )
            // InternalN4MFParser.g:7370:1: ( ruleInitModules )
            {
            // InternalN4MFParser.g:7370:1: ( ruleInitModules )
            // InternalN4MFParser.g:7371:1: ruleInitModules
            {
             before(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_13_0()); 
            pushFollow(FOLLOW_2);
            ruleInitModules();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_13_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__InitModulesAssignment_13"


    // $ANTLR start "rule__ProjectDescription__ExecModuleAssignment_14"
    // InternalN4MFParser.g:7380:1: rule__ProjectDescription__ExecModuleAssignment_14 : ( ruleExecModule ) ;
    public final void rule__ProjectDescription__ExecModuleAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7384:1: ( ( ruleExecModule ) )
            // InternalN4MFParser.g:7385:1: ( ruleExecModule )
            {
            // InternalN4MFParser.g:7385:1: ( ruleExecModule )
            // InternalN4MFParser.g:7386:1: ruleExecModule
            {
             before(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_14_0()); 
            pushFollow(FOLLOW_2);
            ruleExecModule();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_14_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ExecModuleAssignment_14"


    // $ANTLR start "rule__ProjectDescription__OutputPathAssignment_15_2"
    // InternalN4MFParser.g:7395:1: rule__ProjectDescription__OutputPathAssignment_15_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__OutputPathAssignment_15_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7399:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7400:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7400:1: ( RULE_STRING )
            // InternalN4MFParser.g:7401:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_15_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_15_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__OutputPathAssignment_15_2"


    // $ANTLR start "rule__ProjectDescription__LibraryPathsAssignment_16_2"
    // InternalN4MFParser.g:7410:1: rule__ProjectDescription__LibraryPathsAssignment_16_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_16_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7414:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7415:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7415:1: ( RULE_STRING )
            // InternalN4MFParser.g:7416:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__LibraryPathsAssignment_16_2"


    // $ANTLR start "rule__ProjectDescription__LibraryPathsAssignment_16_3_1"
    // InternalN4MFParser.g:7425:1: rule__ProjectDescription__LibraryPathsAssignment_16_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_16_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7429:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7430:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7430:1: ( RULE_STRING )
            // InternalN4MFParser.g:7431:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__LibraryPathsAssignment_16_3_1"


    // $ANTLR start "rule__ProjectDescription__ResourcePathsAssignment_17_2"
    // InternalN4MFParser.g:7440:1: rule__ProjectDescription__ResourcePathsAssignment_17_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_17_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7444:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7445:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7445:1: ( RULE_STRING )
            // InternalN4MFParser.g:7446:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ResourcePathsAssignment_17_2"


    // $ANTLR start "rule__ProjectDescription__ResourcePathsAssignment_17_3_1"
    // InternalN4MFParser.g:7455:1: rule__ProjectDescription__ResourcePathsAssignment_17_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_17_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7459:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7460:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7460:1: ( RULE_STRING )
            // InternalN4MFParser.g:7461:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ResourcePathsAssignment_17_3_1"


    // $ANTLR start "rule__ProjectDescription__SourceFragmentAssignment_18_2"
    // InternalN4MFParser.g:7470:1: rule__ProjectDescription__SourceFragmentAssignment_18_2 : ( ruleSourceFragment ) ;
    public final void rule__ProjectDescription__SourceFragmentAssignment_18_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7474:1: ( ( ruleSourceFragment ) )
            // InternalN4MFParser.g:7475:1: ( ruleSourceFragment )
            {
            // InternalN4MFParser.g:7475:1: ( ruleSourceFragment )
            // InternalN4MFParser.g:7476:1: ruleSourceFragment
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_18_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSourceFragment();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_18_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__SourceFragmentAssignment_18_2"


    // $ANTLR start "rule__ProjectDescription__ModuleFiltersAssignment_19_2"
    // InternalN4MFParser.g:7485:1: rule__ProjectDescription__ModuleFiltersAssignment_19_2 : ( ruleModuleFilter ) ;
    public final void rule__ProjectDescription__ModuleFiltersAssignment_19_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7489:1: ( ( ruleModuleFilter ) )
            // InternalN4MFParser.g:7490:1: ( ruleModuleFilter )
            {
            // InternalN4MFParser.g:7490:1: ( ruleModuleFilter )
            // InternalN4MFParser.g:7491:1: ruleModuleFilter
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_19_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleFilter();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_19_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ModuleFiltersAssignment_19_2"


    // $ANTLR start "rule__ProjectDescription__TestedProjectsAssignment_20"
    // InternalN4MFParser.g:7500:1: rule__ProjectDescription__TestedProjectsAssignment_20 : ( ruleTestedProjects ) ;
    public final void rule__ProjectDescription__TestedProjectsAssignment_20() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7504:1: ( ( ruleTestedProjects ) )
            // InternalN4MFParser.g:7505:1: ( ruleTestedProjects )
            {
            // InternalN4MFParser.g:7505:1: ( ruleTestedProjects )
            // InternalN4MFParser.g:7506:1: ruleTestedProjects
            {
             before(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_20_0()); 
            pushFollow(FOLLOW_2);
            ruleTestedProjects();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_20_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__TestedProjectsAssignment_20"


    // $ANTLR start "rule__ProjectDescription__ModuleLoaderAssignment_21_2"
    // InternalN4MFParser.g:7515:1: rule__ProjectDescription__ModuleLoaderAssignment_21_2 : ( ruleModuleLoader ) ;
    public final void rule__ProjectDescription__ModuleLoaderAssignment_21_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7519:1: ( ( ruleModuleLoader ) )
            // InternalN4MFParser.g:7520:1: ( ruleModuleLoader )
            {
            // InternalN4MFParser.g:7520:1: ( ruleModuleLoader )
            // InternalN4MFParser.g:7521:1: ruleModuleLoader
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_21_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModuleLoader();

            state._fsp--;

             after(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_21_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProjectDescription__ModuleLoaderAssignment_21_2"


    // $ANTLR start "rule__ExecModule__ExecModuleAssignment_3"
    // InternalN4MFParser.g:7530:1: rule__ExecModule__ExecModuleAssignment_3 : ( ruleBootstrapModule ) ;
    public final void rule__ExecModule__ExecModuleAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7534:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7535:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7535:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7536:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7545:1: rule__TestedProjects__TestedProjectsAssignment_3_0 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7549:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:7550:1: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:7550:1: ( ruleTestedProject )
            // InternalN4MFParser.g:7551:1: ruleTestedProject
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
    // InternalN4MFParser.g:7560:1: rule__TestedProjects__TestedProjectsAssignment_3_1_1 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7564:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:7565:1: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:7565:1: ( ruleTestedProject )
            // InternalN4MFParser.g:7566:1: ruleTestedProject
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
    // InternalN4MFParser.g:7575:1: rule__InitModules__InitModulesAssignment_3_0 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7579:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7580:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7580:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7581:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7590:1: rule__InitModules__InitModulesAssignment_3_1_1 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7594:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7595:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7595:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7596:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7605:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7609:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7610:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7610:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7611:1: ruleProjectReference
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
    // InternalN4MFParser.g:7620:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7624:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7625:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7625:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7626:1: ruleProjectReference
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
    // InternalN4MFParser.g:7635:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7639:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:7640:1: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:7640:1: ( ruleProjectDependency )
            // InternalN4MFParser.g:7641:1: ruleProjectDependency
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
    // InternalN4MFParser.g:7650:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7654:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:7655:1: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:7655:1: ( ruleProjectDependency )
            // InternalN4MFParser.g:7656:1: ruleProjectDependency
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
    // InternalN4MFParser.g:7665:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7669:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7670:1: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7670:1: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:7671:1: ruleProvidedRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7680:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7684:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7685:1: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7685:1: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:7686:1: ruleProvidedRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7695:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7699:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7700:1: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7700:1: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:7701:1: ruleRequiredRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7710:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7714:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7715:1: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7715:1: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:7716:1: ruleRequiredRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7725:1: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 : ( ruleProjectReference ) ;
    public final void rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7729:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7730:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7730:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7731:1: ruleProjectReference
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
    // InternalN4MFParser.g:7740:1: rule__DeclaredVersion__MajorAssignment_0 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MajorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7744:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7745:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7745:1: ( RULE_INT )
            // InternalN4MFParser.g:7746:1: RULE_INT
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
    // InternalN4MFParser.g:7755:1: rule__DeclaredVersion__MinorAssignment_1_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MinorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7759:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7760:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7760:1: ( RULE_INT )
            // InternalN4MFParser.g:7761:1: RULE_INT
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
    // InternalN4MFParser.g:7770:1: rule__DeclaredVersion__MicroAssignment_1_2_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MicroAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7774:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7775:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7775:1: ( RULE_INT )
            // InternalN4MFParser.g:7776:1: RULE_INT
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
    // InternalN4MFParser.g:7785:1: rule__DeclaredVersion__QualifierAssignment_2_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__DeclaredVersion__QualifierAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7789:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7790:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7790:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7791:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:7800:1: rule__SourceFragment__SourceFragmentTypeAssignment_0 : ( ruleSourceFragmentType ) ;
    public final void rule__SourceFragment__SourceFragmentTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7804:1: ( ( ruleSourceFragmentType ) )
            // InternalN4MFParser.g:7805:1: ( ruleSourceFragmentType )
            {
            // InternalN4MFParser.g:7805:1: ( ruleSourceFragmentType )
            // InternalN4MFParser.g:7806:1: ruleSourceFragmentType
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
    // InternalN4MFParser.g:7815:1: rule__SourceFragment__PathsAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7819:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7820:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7820:1: ( RULE_STRING )
            // InternalN4MFParser.g:7821:1: RULE_STRING
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
    // InternalN4MFParser.g:7830:1: rule__SourceFragment__PathsAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7834:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7835:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7835:1: ( RULE_STRING )
            // InternalN4MFParser.g:7836:1: RULE_STRING
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
    // InternalN4MFParser.g:7845:1: rule__ModuleFilter__ModuleFilterTypeAssignment_0 : ( ruleModuleFilterType ) ;
    public final void rule__ModuleFilter__ModuleFilterTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7849:1: ( ( ruleModuleFilterType ) )
            // InternalN4MFParser.g:7850:1: ( ruleModuleFilterType )
            {
            // InternalN4MFParser.g:7850:1: ( ruleModuleFilterType )
            // InternalN4MFParser.g:7851:1: ruleModuleFilterType
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
    // InternalN4MFParser.g:7860:1: rule__ModuleFilter__ModuleSpecifiersAssignment_2 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7864:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:7865:1: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:7865:1: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:7866:1: ruleModuleFilterSpecifier
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
    // InternalN4MFParser.g:7875:1: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7879:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:7880:1: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:7880:1: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:7881:1: ruleModuleFilterSpecifier
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
    // InternalN4MFParser.g:7890:1: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7894:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7895:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7895:1: ( RULE_STRING )
            // InternalN4MFParser.g:7896:1: RULE_STRING
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
    // InternalN4MFParser.g:7905:1: rule__BootstrapModule__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7909:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7910:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7910:1: ( RULE_STRING )
            // InternalN4MFParser.g:7911:1: RULE_STRING
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
    // InternalN4MFParser.g:7920:1: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7924:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7925:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7925:1: ( RULE_STRING )
            // InternalN4MFParser.g:7926:1: RULE_STRING
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
    // InternalN4MFParser.g:7935:1: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7939:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7940:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7940:1: ( RULE_STRING )
            // InternalN4MFParser.g:7941:1: RULE_STRING
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
    // InternalN4MFParser.g:7950:1: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProvidedRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7954:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:7955:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:7955:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:7956:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:7965:1: rule__RequiredRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__RequiredRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7969:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:7970:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:7970:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:7971:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:7980:1: rule__TestedProject__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__TestedProject__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7984:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:7985:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:7985:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:7986:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:7995:1: rule__ProjectReference__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectReference__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7999:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:8000:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:8000:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:8001:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:8010:1: rule__ProjectDependency__ProjectAssignment_0 : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectDependency__ProjectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8014:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:8015:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:8015:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:8016:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:8025:1: rule__ProjectDependency__VersionConstraintAssignment_1 : ( ruleVersionConstraint ) ;
    public final void rule__ProjectDependency__VersionConstraintAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8029:1: ( ( ruleVersionConstraint ) )
            // InternalN4MFParser.g:8030:1: ( ruleVersionConstraint )
            {
            // InternalN4MFParser.g:8030:1: ( ruleVersionConstraint )
            // InternalN4MFParser.g:8031:1: ruleVersionConstraint
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
    // InternalN4MFParser.g:8040:1: rule__ProjectDependency__DeclaredScopeAssignment_2 : ( ruleProjectDependencyScope ) ;
    public final void rule__ProjectDependency__DeclaredScopeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8044:1: ( ( ruleProjectDependencyScope ) )
            // InternalN4MFParser.g:8045:1: ( ruleProjectDependencyScope )
            {
            // InternalN4MFParser.g:8045:1: ( ruleProjectDependencyScope )
            // InternalN4MFParser.g:8046:1: ruleProjectDependencyScope
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
    // InternalN4MFParser.g:8055:1: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8059:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:8060:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:8060:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:8061:1: ruleN4mfIdentifier
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


    // $ANTLR start "rule__SimpleProjectDescription__ArtifactIdAssignment_1"
    // InternalN4MFParser.g:8070:1: rule__SimpleProjectDescription__ArtifactIdAssignment_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__ArtifactIdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8074:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:8075:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:8075:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:8076:1: ruleN4mfIdentifier
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleN4mfIdentifier();

            state._fsp--;

             after(grammarAccess.getSimpleProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleProjectDescription__ArtifactIdAssignment_1"


    // $ANTLR start "rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0"
    // InternalN4MFParser.g:8085:1: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 : ( ( LeftParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8089:1: ( ( ( LeftParenthesis ) ) )
            // InternalN4MFParser.g:8090:1: ( ( LeftParenthesis ) )
            {
            // InternalN4MFParser.g:8090:1: ( ( LeftParenthesis ) )
            // InternalN4MFParser.g:8091:1: ( LeftParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 
            // InternalN4MFParser.g:8092:1: ( LeftParenthesis )
            // InternalN4MFParser.g:8093:1: LeftParenthesis
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
    // InternalN4MFParser.g:8108:1: rule__VersionConstraint__LowerVersionAssignment_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8112:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8113:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8113:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8114:1: ruleDeclaredVersion
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
    // InternalN4MFParser.g:8123:1: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__UpperVersionAssignment_0_2_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8127:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8128:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8128:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8129:1: ruleDeclaredVersion
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
    // InternalN4MFParser.g:8138:1: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 : ( ( RightParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8142:1: ( ( ( RightParenthesis ) ) )
            // InternalN4MFParser.g:8143:1: ( ( RightParenthesis ) )
            {
            // InternalN4MFParser.g:8143:1: ( ( RightParenthesis ) )
            // InternalN4MFParser.g:8144:1: ( RightParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 
            // InternalN4MFParser.g:8145:1: ( RightParenthesis )
            // InternalN4MFParser.g:8146:1: RightParenthesis
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
    // InternalN4MFParser.g:8161:1: rule__VersionConstraint__LowerVersionAssignment_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8165:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8166:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8166:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8167:1: ruleDeclaredVersion
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
    protected DFA59 dfa59 = new DFA59(this);
    static final String dfa_1s = "\30\uffff";
    static final String dfa_2s = "\1\uffff\14\24\1\uffff\3\24\1\uffff\2\24\2\uffff\2\24";
    static final String dfa_3s = "\1\10\14\4\1\47\3\4\1\46\2\4\2\uffff\2\4";
    static final String dfa_4s = "\1\67\14\70\1\47\3\70\1\46\2\70\2\uffff\2\70";
    static final String dfa_5s = "\24\uffff\1\2\1\1\2\uffff";
    static final String dfa_6s = "\30\uffff}>";
    static final String[] dfa_7s = {
            "\1\15\2\uffff\1\7\2\uffff\1\14\4\uffff\1\4\1\6\1\20\1\2\2\uffff\1\5\1\uffff\1\11\1\12\1\21\1\3\2\uffff\1\13\1\uffff\1\22\1\uffff\1\10\3\uffff\1\23\1\17\1\16\13\uffff\1\1",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\1\26",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\1\27",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "",
            "",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24",
            "\5\24\1\uffff\3\24\1\uffff\2\24\2\uffff\3\24\1\uffff\4\24\1\uffff\2\24\1\uffff\1\24\2\uffff\2\24\2\uffff\1\24\3\uffff\1\24\3\uffff\1\24\1\uffff\1\24\2\uffff\1\25\1\24\2\uffff\1\24\1\uffff\1\24"
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
            return "6011:1: ( rule__SimpleProjectDescription__Group_0__0 )?";
        }
    }
    static final String dfa_8s = "\27\uffff";
    static final String dfa_9s = "\1\4\26\uffff";
    static final String dfa_10s = "\1\45\26\uffff";
    static final String dfa_11s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26";
    static final String dfa_12s = "\1\0\26\uffff}>";
    static final String[] dfa_13s = {
            "\1\10\1\11\1\12\1\15\1\13\1\uffff\1\14\1\4\1\25\1\uffff\1\24\1\26\2\uffff\1\16\1\2\1\3\1\uffff\1\1\1\17\1\7\1\6\1\uffff\1\21\1\22\1\uffff\1\5\2\uffff\1\23\3\uffff\1\20",
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
            return "6456:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )";
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
                        if ( LA38_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA38_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA38_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA38_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA38_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA38_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA38_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA38_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA38_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA38_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA38_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA38_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA38_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA38_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA38_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA38_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA38_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA38_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA38_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA38_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA38_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA38_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                         
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
    static final String dfa_14s = "\1\27\27\uffff";
    static final String dfa_15s = "\1\4\27\uffff";
    static final String dfa_16s = "\1\45\27\uffff";
    static final String dfa_17s = "\1\uffff\26\1\1\2";
    static final String dfa_18s = "\1\0\27\uffff}>";
    static final String[] dfa_19s = {
            "\1\10\1\11\1\12\1\15\1\13\1\uffff\1\14\1\4\1\25\1\uffff\1\24\1\26\2\uffff\1\16\1\2\1\3\1\uffff\1\1\1\17\1\7\1\6\1\uffff\1\21\1\22\1\uffff\1\5\2\uffff\1\23\3\uffff\1\20",
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
            return "6847:2: ( rule__ProjectDescription__UnorderedGroup__1 )?";
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
                        if ( LA39_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA39_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA39_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA39_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA39_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA39_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA39_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA39_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA39_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA39_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA39_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA39_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA39_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA39_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA39_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA39_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA39_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA39_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA39_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA39_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA39_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA39_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA39_0==EOF) ) {s = 23;}

                         
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
            return "6860:2: ( rule__ProjectDescription__UnorderedGroup__2 )?";
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
                        if ( LA40_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA40_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA40_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA40_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA40_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA40_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA40_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA40_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA40_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA40_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA40_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA40_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA40_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA40_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA40_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA40_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA40_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA40_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA40_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA40_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA40_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA40_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA40_0==EOF) ) {s = 23;}

                         
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
            return "6873:2: ( rule__ProjectDescription__UnorderedGroup__3 )?";
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
                        if ( LA41_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA41_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA41_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA41_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA41_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA41_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA41_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA41_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA41_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA41_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA41_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA41_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA41_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA41_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA41_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA41_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA41_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA41_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA41_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA41_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA41_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA41_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA41_0==EOF) ) {s = 23;}

                         
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
            return "6886:2: ( rule__ProjectDescription__UnorderedGroup__4 )?";
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
                        if ( LA42_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA42_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA42_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA42_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA42_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA42_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA42_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA42_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA42_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA42_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA42_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA42_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA42_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA42_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA42_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA42_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA42_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA42_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA42_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA42_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA42_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA42_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA42_0==EOF) ) {s = 23;}

                         
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
            return "6899:2: ( rule__ProjectDescription__UnorderedGroup__5 )?";
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
                        if ( LA43_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA43_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA43_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA43_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA43_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA43_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA43_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA43_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA43_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA43_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA43_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA43_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA43_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA43_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA43_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA43_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA43_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA43_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA43_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA43_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA43_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA43_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA43_0==EOF) ) {s = 23;}

                         
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
            return "6912:2: ( rule__ProjectDescription__UnorderedGroup__6 )?";
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
                        if ( LA44_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA44_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA44_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA44_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA44_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA44_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA44_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA44_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA44_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA44_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA44_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA44_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA44_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA44_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA44_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA44_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA44_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA44_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA44_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA44_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA44_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA44_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA44_0==EOF) ) {s = 23;}

                         
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
            return "6925:2: ( rule__ProjectDescription__UnorderedGroup__7 )?";
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
                        if ( LA45_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA45_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA45_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA45_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA45_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA45_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA45_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA45_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA45_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA45_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA45_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA45_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA45_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA45_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA45_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA45_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA45_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA45_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA45_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA45_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA45_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA45_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA45_0==EOF) ) {s = 23;}

                         
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
            return "6938:2: ( rule__ProjectDescription__UnorderedGroup__8 )?";
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
                        if ( LA46_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA46_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA46_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA46_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA46_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA46_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA46_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA46_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA46_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA46_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA46_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA46_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA46_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA46_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA46_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA46_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA46_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA46_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA46_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA46_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA46_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA46_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA46_0==EOF) ) {s = 23;}

                         
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
            return "6951:2: ( rule__ProjectDescription__UnorderedGroup__9 )?";
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
                        if ( LA47_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA47_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA47_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA47_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA47_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA47_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA47_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA47_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA47_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA47_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA47_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA47_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA47_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA47_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA47_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA47_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA47_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA47_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA47_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA47_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA47_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA47_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA47_0==EOF) ) {s = 23;}

                         
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
            return "6964:2: ( rule__ProjectDescription__UnorderedGroup__10 )?";
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
                        if ( LA48_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA48_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA48_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA48_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA48_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA48_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA48_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA48_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA48_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA48_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA48_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA48_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA48_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA48_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA48_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA48_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA48_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA48_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA48_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA48_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA48_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA48_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA48_0==EOF) ) {s = 23;}

                         
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
            return "6977:2: ( rule__ProjectDescription__UnorderedGroup__11 )?";
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
                        if ( LA49_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA49_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA49_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA49_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA49_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA49_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA49_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA49_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA49_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA49_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA49_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA49_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA49_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA49_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA49_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA49_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA49_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA49_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA49_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA49_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA49_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA49_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA49_0==EOF) ) {s = 23;}

                         
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
            return "6990:2: ( rule__ProjectDescription__UnorderedGroup__12 )?";
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
                        if ( LA50_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA50_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA50_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA50_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA50_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA50_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA50_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA50_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA50_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA50_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA50_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA50_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA50_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA50_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA50_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA50_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA50_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA50_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA50_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA50_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA50_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA50_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA50_0==EOF) ) {s = 23;}

                         
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
            return "7003:2: ( rule__ProjectDescription__UnorderedGroup__13 )?";
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
                        if ( LA51_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA51_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA51_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA51_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA51_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA51_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA51_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA51_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA51_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA51_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA51_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA51_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA51_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA51_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA51_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA51_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA51_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA51_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA51_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA51_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA51_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA51_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA51_0==EOF) ) {s = 23;}

                         
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
            return "7016:2: ( rule__ProjectDescription__UnorderedGroup__14 )?";
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
                        if ( LA52_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA52_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA52_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA52_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA52_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA52_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA52_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA52_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA52_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA52_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA52_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA52_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA52_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA52_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA52_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA52_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA52_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA52_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA52_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA52_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA52_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA52_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA52_0==EOF) ) {s = 23;}

                         
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
            return "7029:2: ( rule__ProjectDescription__UnorderedGroup__15 )?";
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
                        if ( LA53_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA53_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA53_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA53_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA53_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA53_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA53_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA53_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA53_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA53_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA53_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA53_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA53_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA53_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA53_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA53_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA53_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA53_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA53_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA53_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA53_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA53_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA53_0==EOF) ) {s = 23;}

                         
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
            return "7042:2: ( rule__ProjectDescription__UnorderedGroup__16 )?";
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
                        if ( LA54_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA54_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA54_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA54_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA54_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA54_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA54_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA54_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA54_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA54_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA54_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA54_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA54_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA54_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA54_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA54_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA54_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA54_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA54_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA54_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA54_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA54_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA54_0==EOF) ) {s = 23;}

                         
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
            return "7055:2: ( rule__ProjectDescription__UnorderedGroup__17 )?";
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
                        if ( LA55_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA55_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA55_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA55_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA55_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA55_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA55_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA55_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA55_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA55_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA55_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA55_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA55_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA55_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA55_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA55_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA55_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA55_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA55_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA55_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA55_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA55_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA55_0==EOF) ) {s = 23;}

                         
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
            return "7068:2: ( rule__ProjectDescription__UnorderedGroup__18 )?";
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
                        if ( LA56_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA56_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA56_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA56_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA56_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA56_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA56_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA56_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA56_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA56_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA56_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA56_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA56_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA56_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA56_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA56_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA56_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA56_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA56_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA56_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA56_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA56_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA56_0==EOF) ) {s = 23;}

                         
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
            return "7081:2: ( rule__ProjectDescription__UnorderedGroup__19 )?";
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
                        if ( LA57_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA57_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA57_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA57_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA57_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA57_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA57_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA57_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA57_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA57_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA57_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA57_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA57_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA57_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA57_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA57_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA57_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA57_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA57_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA57_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA57_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA57_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA57_0==EOF) ) {s = 23;}

                         
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
            return "7094:2: ( rule__ProjectDescription__UnorderedGroup__20 )?";
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
                        if ( LA58_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA58_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA58_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA58_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA58_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA58_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA58_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA58_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA58_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA58_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA58_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA58_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA58_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA58_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA58_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA58_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA58_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA58_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA58_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA58_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA58_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA58_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA58_0==EOF) ) {s = 23;}

                         
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

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = dfa_1;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "7107:2: ( rule__ProjectDescription__UnorderedGroup__21 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA59_0 = input.LA(1);

                         
                        int index59_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA59_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA59_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA59_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA59_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA59_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA59_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA59_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA59_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA59_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA59_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA59_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA59_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA59_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA59_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA59_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA59_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA59_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA59_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA59_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA59_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA59_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA59_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA59_0==EOF) ) {s = 23;}

                         
                        input.seek(index59_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 59, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00800E2A7A784900L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000A1020202200L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0040800000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000024100000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000024100000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004010000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004010002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000010080020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00C00E2A7A784900L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0240000000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0108220400000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0010400000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000225BDCDDF2L});

}
