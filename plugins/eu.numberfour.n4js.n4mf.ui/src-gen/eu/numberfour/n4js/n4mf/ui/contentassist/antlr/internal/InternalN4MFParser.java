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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ExtendedRuntimeEnvironment", "ProvidedRuntimeLibraries", "RequiredRuntimeLibraries", "ImplementedProjects", "ProjectDependencies", "RuntimeEnvironment", "ImplementationId", "ProjectVersion", "TestedProjects", "RuntimeLibrary", "ModuleFilters", "ModuleLoader", "NoModuleWrap", "Node_builtin", "InitModules", "ProjectName", "ProjectType", "Application", "ArtifactId", "ExecModule", "MainModule", "VendorName", "NoValidate", "Libraries", "ProjectId", "Resources", "Processor", "VendorId", "Commonjs", "External", "Sources", "Compile", "Content", "Library", "Output", "Source", "KW_System", "N4js", "Test", "User", "API", "In", "LeftParenthesis", "RightParenthesis", "Comma", "HyphenMinus", "FullStop", "Colon", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int ArtifactId=22;
    public static final int TestedProjects=12;
    public static final int KW_System=40;
    public static final int ProjectDependencies=8;
    public static final int ExecModule=23;
    public static final int LeftParenthesis=46;
    public static final int Test=42;
    public static final int ProjectVersion=11;
    public static final int Libraries=27;
    public static final int ModuleFilters=14;
    public static final int RightSquareBracket=53;
    public static final int VendorName=25;
    public static final int RuntimeEnvironment=9;
    public static final int RULE_ID=56;
    public static final int NoValidate=26;
    public static final int NoModuleWrap=16;
    public static final int RightParenthesis=47;
    public static final int Sources=34;
    public static final int Content=36;
    public static final int RULE_INT=57;
    public static final int ProjectType=20;
    public static final int External=33;
    public static final int RULE_ML_COMMENT=59;
    public static final int LeftSquareBracket=52;
    public static final int Resources=29;
    public static final int Library=37;
    public static final int Application=21;
    public static final int ImplementedProjects=7;
    public static final int Processor=30;
    public static final int User=43;
    public static final int ProjectName=19;
    public static final int In=45;
    public static final int VendorId=31;
    public static final int RULE_STRING=58;
    public static final int Node_builtin=17;
    public static final int N4js=41;
    public static final int Compile=35;
    public static final int Source=39;
    public static final int RULE_SL_COMMENT=60;
    public static final int ImplementationId=10;
    public static final int Comma=48;
    public static final int HyphenMinus=49;
    public static final int Output=38;
    public static final int MainModule=24;
    public static final int Colon=51;
    public static final int RightCurlyBracket=55;
    public static final int EOF=-1;
    public static final int ExtendedRuntimeEnvironment=4;
    public static final int FullStop=50;
    public static final int ModuleLoader=15;
    public static final int Commonjs=32;
    public static final int RULE_WS=61;
    public static final int ProjectId=28;
    public static final int LeftCurlyBracket=54;
    public static final int ProvidedRuntimeLibraries=5;
    public static final int RULE_ANY_OTHER=62;
    public static final int RequiredRuntimeLibraries=6;
    public static final int InitModules=18;
    public static final int API=44;
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
    // InternalN4MFParser.g:115:1: entryRuleProjectDescription : ruleProjectDescription EOF ;
    public final void entryRuleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:116:1: ( ruleProjectDescription EOF )
            // InternalN4MFParser.g:117:1: ruleProjectDescription EOF
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
    // InternalN4MFParser.g:124:1: ruleProjectDescription : ( ( rule__ProjectDescription__UnorderedGroup ) ) ;
    public final void ruleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:128:5: ( ( ( rule__ProjectDescription__UnorderedGroup ) ) )
            // InternalN4MFParser.g:129:1: ( ( rule__ProjectDescription__UnorderedGroup ) )
            {
            // InternalN4MFParser.g:129:1: ( ( rule__ProjectDescription__UnorderedGroup ) )
            // InternalN4MFParser.g:130:1: ( rule__ProjectDescription__UnorderedGroup )
            {
             before(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()); 
            // InternalN4MFParser.g:131:1: ( rule__ProjectDescription__UnorderedGroup )
            // InternalN4MFParser.g:131:2: rule__ProjectDescription__UnorderedGroup
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
    // InternalN4MFParser.g:143:1: entryRuleExecModule : ruleExecModule EOF ;
    public final void entryRuleExecModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:144:1: ( ruleExecModule EOF )
            // InternalN4MFParser.g:145:1: ruleExecModule EOF
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
    // InternalN4MFParser.g:152:1: ruleExecModule : ( ( rule__ExecModule__Group__0 ) ) ;
    public final void ruleExecModule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:156:5: ( ( ( rule__ExecModule__Group__0 ) ) )
            // InternalN4MFParser.g:157:1: ( ( rule__ExecModule__Group__0 ) )
            {
            // InternalN4MFParser.g:157:1: ( ( rule__ExecModule__Group__0 ) )
            // InternalN4MFParser.g:158:1: ( rule__ExecModule__Group__0 )
            {
             before(grammarAccess.getExecModuleAccess().getGroup()); 
            // InternalN4MFParser.g:159:1: ( rule__ExecModule__Group__0 )
            // InternalN4MFParser.g:159:2: rule__ExecModule__Group__0
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
    // InternalN4MFParser.g:171:1: entryRuleTestedProjects : ruleTestedProjects EOF ;
    public final void entryRuleTestedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:172:1: ( ruleTestedProjects EOF )
            // InternalN4MFParser.g:173:1: ruleTestedProjects EOF
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
    // InternalN4MFParser.g:180:1: ruleTestedProjects : ( ( rule__TestedProjects__Group__0 ) ) ;
    public final void ruleTestedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:184:5: ( ( ( rule__TestedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:185:1: ( ( rule__TestedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:185:1: ( ( rule__TestedProjects__Group__0 ) )
            // InternalN4MFParser.g:186:1: ( rule__TestedProjects__Group__0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:187:1: ( rule__TestedProjects__Group__0 )
            // InternalN4MFParser.g:187:2: rule__TestedProjects__Group__0
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
    // InternalN4MFParser.g:199:1: entryRuleInitModules : ruleInitModules EOF ;
    public final void entryRuleInitModules() throws RecognitionException {
        try {
            // InternalN4MFParser.g:200:1: ( ruleInitModules EOF )
            // InternalN4MFParser.g:201:1: ruleInitModules EOF
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
    // InternalN4MFParser.g:208:1: ruleInitModules : ( ( rule__InitModules__Group__0 ) ) ;
    public final void ruleInitModules() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:212:5: ( ( ( rule__InitModules__Group__0 ) ) )
            // InternalN4MFParser.g:213:1: ( ( rule__InitModules__Group__0 ) )
            {
            // InternalN4MFParser.g:213:1: ( ( rule__InitModules__Group__0 ) )
            // InternalN4MFParser.g:214:1: ( rule__InitModules__Group__0 )
            {
             before(grammarAccess.getInitModulesAccess().getGroup()); 
            // InternalN4MFParser.g:215:1: ( rule__InitModules__Group__0 )
            // InternalN4MFParser.g:215:2: rule__InitModules__Group__0
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
    // InternalN4MFParser.g:227:1: entryRuleImplementedProjects : ruleImplementedProjects EOF ;
    public final void entryRuleImplementedProjects() throws RecognitionException {
        try {
            // InternalN4MFParser.g:228:1: ( ruleImplementedProjects EOF )
            // InternalN4MFParser.g:229:1: ruleImplementedProjects EOF
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
    // InternalN4MFParser.g:236:1: ruleImplementedProjects : ( ( rule__ImplementedProjects__Group__0 ) ) ;
    public final void ruleImplementedProjects() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:240:5: ( ( ( rule__ImplementedProjects__Group__0 ) ) )
            // InternalN4MFParser.g:241:1: ( ( rule__ImplementedProjects__Group__0 ) )
            {
            // InternalN4MFParser.g:241:1: ( ( rule__ImplementedProjects__Group__0 ) )
            // InternalN4MFParser.g:242:1: ( rule__ImplementedProjects__Group__0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup()); 
            // InternalN4MFParser.g:243:1: ( rule__ImplementedProjects__Group__0 )
            // InternalN4MFParser.g:243:2: rule__ImplementedProjects__Group__0
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
    // InternalN4MFParser.g:255:1: entryRuleProjectDependencies : ruleProjectDependencies EOF ;
    public final void entryRuleProjectDependencies() throws RecognitionException {
        try {
            // InternalN4MFParser.g:256:1: ( ruleProjectDependencies EOF )
            // InternalN4MFParser.g:257:1: ruleProjectDependencies EOF
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
    // InternalN4MFParser.g:264:1: ruleProjectDependencies : ( ( rule__ProjectDependencies__Group__0 ) ) ;
    public final void ruleProjectDependencies() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:268:5: ( ( ( rule__ProjectDependencies__Group__0 ) ) )
            // InternalN4MFParser.g:269:1: ( ( rule__ProjectDependencies__Group__0 ) )
            {
            // InternalN4MFParser.g:269:1: ( ( rule__ProjectDependencies__Group__0 ) )
            // InternalN4MFParser.g:270:1: ( rule__ProjectDependencies__Group__0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup()); 
            // InternalN4MFParser.g:271:1: ( rule__ProjectDependencies__Group__0 )
            // InternalN4MFParser.g:271:2: rule__ProjectDependencies__Group__0
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
    // InternalN4MFParser.g:283:1: entryRuleProvidedRuntimeLibraries : ruleProvidedRuntimeLibraries EOF ;
    public final void entryRuleProvidedRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:284:1: ( ruleProvidedRuntimeLibraries EOF )
            // InternalN4MFParser.g:285:1: ruleProvidedRuntimeLibraries EOF
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
    // InternalN4MFParser.g:292:1: ruleProvidedRuntimeLibraries : ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) ;
    public final void ruleProvidedRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:296:5: ( ( ( rule__ProvidedRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:297:1: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:297:1: ( ( rule__ProvidedRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:298:1: ( rule__ProvidedRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:299:1: ( rule__ProvidedRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:299:2: rule__ProvidedRuntimeLibraries__Group__0
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
    // InternalN4MFParser.g:311:1: entryRuleRequiredRuntimeLibraries : ruleRequiredRuntimeLibraries EOF ;
    public final void entryRuleRequiredRuntimeLibraries() throws RecognitionException {
        try {
            // InternalN4MFParser.g:312:1: ( ruleRequiredRuntimeLibraries EOF )
            // InternalN4MFParser.g:313:1: ruleRequiredRuntimeLibraries EOF
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
    // InternalN4MFParser.g:320:1: ruleRequiredRuntimeLibraries : ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) ;
    public final void ruleRequiredRuntimeLibraries() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:324:5: ( ( ( rule__RequiredRuntimeLibraries__Group__0 ) ) )
            // InternalN4MFParser.g:325:1: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            {
            // InternalN4MFParser.g:325:1: ( ( rule__RequiredRuntimeLibraries__Group__0 ) )
            // InternalN4MFParser.g:326:1: ( rule__RequiredRuntimeLibraries__Group__0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup()); 
            // InternalN4MFParser.g:327:1: ( rule__RequiredRuntimeLibraries__Group__0 )
            // InternalN4MFParser.g:327:2: rule__RequiredRuntimeLibraries__Group__0
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
    // InternalN4MFParser.g:339:1: entryRuleExtendedRuntimeEnvironment : ruleExtendedRuntimeEnvironment EOF ;
    public final void entryRuleExtendedRuntimeEnvironment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:340:1: ( ruleExtendedRuntimeEnvironment EOF )
            // InternalN4MFParser.g:341:1: ruleExtendedRuntimeEnvironment EOF
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
    // InternalN4MFParser.g:348:1: ruleExtendedRuntimeEnvironment : ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) ;
    public final void ruleExtendedRuntimeEnvironment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:352:5: ( ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) ) )
            // InternalN4MFParser.g:353:1: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            {
            // InternalN4MFParser.g:353:1: ( ( rule__ExtendedRuntimeEnvironment__Group__0 ) )
            // InternalN4MFParser.g:354:1: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getGroup()); 
            // InternalN4MFParser.g:355:1: ( rule__ExtendedRuntimeEnvironment__Group__0 )
            // InternalN4MFParser.g:355:2: rule__ExtendedRuntimeEnvironment__Group__0
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
    // InternalN4MFParser.g:367:1: entryRuleDeclaredVersion : ruleDeclaredVersion EOF ;
    public final void entryRuleDeclaredVersion() throws RecognitionException {
        try {
            // InternalN4MFParser.g:368:1: ( ruleDeclaredVersion EOF )
            // InternalN4MFParser.g:369:1: ruleDeclaredVersion EOF
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
    // InternalN4MFParser.g:376:1: ruleDeclaredVersion : ( ( rule__DeclaredVersion__Group__0 ) ) ;
    public final void ruleDeclaredVersion() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:380:5: ( ( ( rule__DeclaredVersion__Group__0 ) ) )
            // InternalN4MFParser.g:381:1: ( ( rule__DeclaredVersion__Group__0 ) )
            {
            // InternalN4MFParser.g:381:1: ( ( rule__DeclaredVersion__Group__0 ) )
            // InternalN4MFParser.g:382:1: ( rule__DeclaredVersion__Group__0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup()); 
            // InternalN4MFParser.g:383:1: ( rule__DeclaredVersion__Group__0 )
            // InternalN4MFParser.g:383:2: rule__DeclaredVersion__Group__0
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
    // InternalN4MFParser.g:395:1: entryRuleSourceFragment : ruleSourceFragment EOF ;
    public final void entryRuleSourceFragment() throws RecognitionException {
        try {
            // InternalN4MFParser.g:396:1: ( ruleSourceFragment EOF )
            // InternalN4MFParser.g:397:1: ruleSourceFragment EOF
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
    // InternalN4MFParser.g:404:1: ruleSourceFragment : ( ( rule__SourceFragment__Group__0 ) ) ;
    public final void ruleSourceFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:408:5: ( ( ( rule__SourceFragment__Group__0 ) ) )
            // InternalN4MFParser.g:409:1: ( ( rule__SourceFragment__Group__0 ) )
            {
            // InternalN4MFParser.g:409:1: ( ( rule__SourceFragment__Group__0 ) )
            // InternalN4MFParser.g:410:1: ( rule__SourceFragment__Group__0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup()); 
            // InternalN4MFParser.g:411:1: ( rule__SourceFragment__Group__0 )
            // InternalN4MFParser.g:411:2: rule__SourceFragment__Group__0
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
    // InternalN4MFParser.g:423:1: entryRuleModuleFilter : ruleModuleFilter EOF ;
    public final void entryRuleModuleFilter() throws RecognitionException {
        try {
            // InternalN4MFParser.g:424:1: ( ruleModuleFilter EOF )
            // InternalN4MFParser.g:425:1: ruleModuleFilter EOF
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
    // InternalN4MFParser.g:432:1: ruleModuleFilter : ( ( rule__ModuleFilter__Group__0 ) ) ;
    public final void ruleModuleFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:436:5: ( ( ( rule__ModuleFilter__Group__0 ) ) )
            // InternalN4MFParser.g:437:1: ( ( rule__ModuleFilter__Group__0 ) )
            {
            // InternalN4MFParser.g:437:1: ( ( rule__ModuleFilter__Group__0 ) )
            // InternalN4MFParser.g:438:1: ( rule__ModuleFilter__Group__0 )
            {
             before(grammarAccess.getModuleFilterAccess().getGroup()); 
            // InternalN4MFParser.g:439:1: ( rule__ModuleFilter__Group__0 )
            // InternalN4MFParser.g:439:2: rule__ModuleFilter__Group__0
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
    // InternalN4MFParser.g:451:1: entryRuleBootstrapModule : ruleBootstrapModule EOF ;
    public final void entryRuleBootstrapModule() throws RecognitionException {
        try {
            // InternalN4MFParser.g:452:1: ( ruleBootstrapModule EOF )
            // InternalN4MFParser.g:453:1: ruleBootstrapModule EOF
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
    // InternalN4MFParser.g:460:1: ruleBootstrapModule : ( ( rule__BootstrapModule__Group__0 ) ) ;
    public final void ruleBootstrapModule() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:464:5: ( ( ( rule__BootstrapModule__Group__0 ) ) )
            // InternalN4MFParser.g:465:1: ( ( rule__BootstrapModule__Group__0 ) )
            {
            // InternalN4MFParser.g:465:1: ( ( rule__BootstrapModule__Group__0 ) )
            // InternalN4MFParser.g:466:1: ( rule__BootstrapModule__Group__0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup()); 
            // InternalN4MFParser.g:467:1: ( rule__BootstrapModule__Group__0 )
            // InternalN4MFParser.g:467:2: rule__BootstrapModule__Group__0
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
    // InternalN4MFParser.g:479:1: entryRuleModuleFilterSpecifier : ruleModuleFilterSpecifier EOF ;
    public final void entryRuleModuleFilterSpecifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:480:1: ( ruleModuleFilterSpecifier EOF )
            // InternalN4MFParser.g:481:1: ruleModuleFilterSpecifier EOF
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
    // InternalN4MFParser.g:488:1: ruleModuleFilterSpecifier : ( ( rule__ModuleFilterSpecifier__Group__0 ) ) ;
    public final void ruleModuleFilterSpecifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:492:5: ( ( ( rule__ModuleFilterSpecifier__Group__0 ) ) )
            // InternalN4MFParser.g:493:1: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            {
            // InternalN4MFParser.g:493:1: ( ( rule__ModuleFilterSpecifier__Group__0 ) )
            // InternalN4MFParser.g:494:1: ( rule__ModuleFilterSpecifier__Group__0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup()); 
            // InternalN4MFParser.g:495:1: ( rule__ModuleFilterSpecifier__Group__0 )
            // InternalN4MFParser.g:495:2: rule__ModuleFilterSpecifier__Group__0
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
    // InternalN4MFParser.g:507:1: entryRuleProvidedRuntimeLibraryDependency : ruleProvidedRuntimeLibraryDependency EOF ;
    public final void entryRuleProvidedRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:508:1: ( ruleProvidedRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:509:1: ruleProvidedRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:516:1: ruleProvidedRuntimeLibraryDependency : ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleProvidedRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:520:5: ( ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:521:1: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:521:1: ( ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:522:1: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:523:1: ( rule__ProvidedRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:523:2: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment
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
    // InternalN4MFParser.g:535:1: entryRuleRequiredRuntimeLibraryDependency : ruleRequiredRuntimeLibraryDependency EOF ;
    public final void entryRuleRequiredRuntimeLibraryDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:536:1: ( ruleRequiredRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:537:1: ruleRequiredRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:544:1: ruleRequiredRuntimeLibraryDependency : ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) ;
    public final void ruleRequiredRuntimeLibraryDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:548:5: ( ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) ) )
            // InternalN4MFParser.g:549:1: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:549:1: ( ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment ) )
            // InternalN4MFParser.g:550:1: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            {
             before(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:551:1: ( rule__RequiredRuntimeLibraryDependency__ProjectAssignment )
            // InternalN4MFParser.g:551:2: rule__RequiredRuntimeLibraryDependency__ProjectAssignment
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
    // InternalN4MFParser.g:563:1: entryRuleTestedProject : ruleTestedProject EOF ;
    public final void entryRuleTestedProject() throws RecognitionException {
        try {
            // InternalN4MFParser.g:564:1: ( ruleTestedProject EOF )
            // InternalN4MFParser.g:565:1: ruleTestedProject EOF
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
    // InternalN4MFParser.g:572:1: ruleTestedProject : ( ( rule__TestedProject__ProjectAssignment ) ) ;
    public final void ruleTestedProject() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:576:5: ( ( ( rule__TestedProject__ProjectAssignment ) ) )
            // InternalN4MFParser.g:577:1: ( ( rule__TestedProject__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:577:1: ( ( rule__TestedProject__ProjectAssignment ) )
            // InternalN4MFParser.g:578:1: ( rule__TestedProject__ProjectAssignment )
            {
             before(grammarAccess.getTestedProjectAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:579:1: ( rule__TestedProject__ProjectAssignment )
            // InternalN4MFParser.g:579:2: rule__TestedProject__ProjectAssignment
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
    // InternalN4MFParser.g:591:1: entryRuleProjectReference : ruleProjectReference EOF ;
    public final void entryRuleProjectReference() throws RecognitionException {
        try {
            // InternalN4MFParser.g:592:1: ( ruleProjectReference EOF )
            // InternalN4MFParser.g:593:1: ruleProjectReference EOF
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
    // InternalN4MFParser.g:600:1: ruleProjectReference : ( ( rule__ProjectReference__ProjectAssignment ) ) ;
    public final void ruleProjectReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:604:5: ( ( ( rule__ProjectReference__ProjectAssignment ) ) )
            // InternalN4MFParser.g:605:1: ( ( rule__ProjectReference__ProjectAssignment ) )
            {
            // InternalN4MFParser.g:605:1: ( ( rule__ProjectReference__ProjectAssignment ) )
            // InternalN4MFParser.g:606:1: ( rule__ProjectReference__ProjectAssignment )
            {
             before(grammarAccess.getProjectReferenceAccess().getProjectAssignment()); 
            // InternalN4MFParser.g:607:1: ( rule__ProjectReference__ProjectAssignment )
            // InternalN4MFParser.g:607:2: rule__ProjectReference__ProjectAssignment
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
    // InternalN4MFParser.g:619:1: entryRuleProjectDependency : ruleProjectDependency EOF ;
    public final void entryRuleProjectDependency() throws RecognitionException {
        try {
            // InternalN4MFParser.g:620:1: ( ruleProjectDependency EOF )
            // InternalN4MFParser.g:621:1: ruleProjectDependency EOF
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
    // InternalN4MFParser.g:628:1: ruleProjectDependency : ( ( rule__ProjectDependency__Group__0 ) ) ;
    public final void ruleProjectDependency() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:632:5: ( ( ( rule__ProjectDependency__Group__0 ) ) )
            // InternalN4MFParser.g:633:1: ( ( rule__ProjectDependency__Group__0 ) )
            {
            // InternalN4MFParser.g:633:1: ( ( rule__ProjectDependency__Group__0 ) )
            // InternalN4MFParser.g:634:1: ( rule__ProjectDependency__Group__0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getGroup()); 
            // InternalN4MFParser.g:635:1: ( rule__ProjectDependency__Group__0 )
            // InternalN4MFParser.g:635:2: rule__ProjectDependency__Group__0
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
    // InternalN4MFParser.g:647:1: entryRuleSimpleProjectDescription : ruleSimpleProjectDescription EOF ;
    public final void entryRuleSimpleProjectDescription() throws RecognitionException {
        try {
            // InternalN4MFParser.g:648:1: ( ruleSimpleProjectDescription EOF )
            // InternalN4MFParser.g:649:1: ruleSimpleProjectDescription EOF
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
    // InternalN4MFParser.g:656:1: ruleSimpleProjectDescription : ( ( rule__SimpleProjectDescription__Group__0 ) ) ;
    public final void ruleSimpleProjectDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:660:5: ( ( ( rule__SimpleProjectDescription__Group__0 ) ) )
            // InternalN4MFParser.g:661:1: ( ( rule__SimpleProjectDescription__Group__0 ) )
            {
            // InternalN4MFParser.g:661:1: ( ( rule__SimpleProjectDescription__Group__0 ) )
            // InternalN4MFParser.g:662:1: ( rule__SimpleProjectDescription__Group__0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup()); 
            // InternalN4MFParser.g:663:1: ( rule__SimpleProjectDescription__Group__0 )
            // InternalN4MFParser.g:663:2: rule__SimpleProjectDescription__Group__0
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
    // InternalN4MFParser.g:675:1: entryRuleVersionConstraint : ruleVersionConstraint EOF ;
    public final void entryRuleVersionConstraint() throws RecognitionException {
        try {
            // InternalN4MFParser.g:676:1: ( ruleVersionConstraint EOF )
            // InternalN4MFParser.g:677:1: ruleVersionConstraint EOF
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
    // InternalN4MFParser.g:684:1: ruleVersionConstraint : ( ( rule__VersionConstraint__Alternatives ) ) ;
    public final void ruleVersionConstraint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:688:5: ( ( ( rule__VersionConstraint__Alternatives ) ) )
            // InternalN4MFParser.g:689:1: ( ( rule__VersionConstraint__Alternatives ) )
            {
            // InternalN4MFParser.g:689:1: ( ( rule__VersionConstraint__Alternatives ) )
            // InternalN4MFParser.g:690:1: ( rule__VersionConstraint__Alternatives )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives()); 
            // InternalN4MFParser.g:691:1: ( rule__VersionConstraint__Alternatives )
            // InternalN4MFParser.g:691:2: rule__VersionConstraint__Alternatives
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
    // InternalN4MFParser.g:703:1: entryRuleN4mfIdentifier : ruleN4mfIdentifier EOF ;
    public final void entryRuleN4mfIdentifier() throws RecognitionException {
        try {
            // InternalN4MFParser.g:704:1: ( ruleN4mfIdentifier EOF )
            // InternalN4MFParser.g:705:1: ruleN4mfIdentifier EOF
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
    // InternalN4MFParser.g:712:1: ruleN4mfIdentifier : ( ( rule__N4mfIdentifier__Alternatives ) ) ;
    public final void ruleN4mfIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:716:5: ( ( ( rule__N4mfIdentifier__Alternatives ) ) )
            // InternalN4MFParser.g:717:1: ( ( rule__N4mfIdentifier__Alternatives ) )
            {
            // InternalN4MFParser.g:717:1: ( ( rule__N4mfIdentifier__Alternatives ) )
            // InternalN4MFParser.g:718:1: ( rule__N4mfIdentifier__Alternatives )
            {
             before(grammarAccess.getN4mfIdentifierAccess().getAlternatives()); 
            // InternalN4MFParser.g:719:1: ( rule__N4mfIdentifier__Alternatives )
            // InternalN4MFParser.g:719:2: rule__N4mfIdentifier__Alternatives
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
    // InternalN4MFParser.g:732:1: ruleProjectType : ( ( rule__ProjectType__Alternatives ) ) ;
    public final void ruleProjectType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:736:1: ( ( ( rule__ProjectType__Alternatives ) ) )
            // InternalN4MFParser.g:737:1: ( ( rule__ProjectType__Alternatives ) )
            {
            // InternalN4MFParser.g:737:1: ( ( rule__ProjectType__Alternatives ) )
            // InternalN4MFParser.g:738:1: ( rule__ProjectType__Alternatives )
            {
             before(grammarAccess.getProjectTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:739:1: ( rule__ProjectType__Alternatives )
            // InternalN4MFParser.g:739:2: rule__ProjectType__Alternatives
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
    // InternalN4MFParser.g:751:1: ruleSourceFragmentType : ( ( rule__SourceFragmentType__Alternatives ) ) ;
    public final void ruleSourceFragmentType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:755:1: ( ( ( rule__SourceFragmentType__Alternatives ) ) )
            // InternalN4MFParser.g:756:1: ( ( rule__SourceFragmentType__Alternatives ) )
            {
            // InternalN4MFParser.g:756:1: ( ( rule__SourceFragmentType__Alternatives ) )
            // InternalN4MFParser.g:757:1: ( rule__SourceFragmentType__Alternatives )
            {
             before(grammarAccess.getSourceFragmentTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:758:1: ( rule__SourceFragmentType__Alternatives )
            // InternalN4MFParser.g:758:2: rule__SourceFragmentType__Alternatives
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
    // InternalN4MFParser.g:770:1: ruleModuleFilterType : ( ( rule__ModuleFilterType__Alternatives ) ) ;
    public final void ruleModuleFilterType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:774:1: ( ( ( rule__ModuleFilterType__Alternatives ) ) )
            // InternalN4MFParser.g:775:1: ( ( rule__ModuleFilterType__Alternatives ) )
            {
            // InternalN4MFParser.g:775:1: ( ( rule__ModuleFilterType__Alternatives ) )
            // InternalN4MFParser.g:776:1: ( rule__ModuleFilterType__Alternatives )
            {
             before(grammarAccess.getModuleFilterTypeAccess().getAlternatives()); 
            // InternalN4MFParser.g:777:1: ( rule__ModuleFilterType__Alternatives )
            // InternalN4MFParser.g:777:2: rule__ModuleFilterType__Alternatives
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
    // InternalN4MFParser.g:789:1: ruleProjectDependencyScope : ( ( rule__ProjectDependencyScope__Alternatives ) ) ;
    public final void ruleProjectDependencyScope() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:793:1: ( ( ( rule__ProjectDependencyScope__Alternatives ) ) )
            // InternalN4MFParser.g:794:1: ( ( rule__ProjectDependencyScope__Alternatives ) )
            {
            // InternalN4MFParser.g:794:1: ( ( rule__ProjectDependencyScope__Alternatives ) )
            // InternalN4MFParser.g:795:1: ( rule__ProjectDependencyScope__Alternatives )
            {
             before(grammarAccess.getProjectDependencyScopeAccess().getAlternatives()); 
            // InternalN4MFParser.g:796:1: ( rule__ProjectDependencyScope__Alternatives )
            // InternalN4MFParser.g:796:2: rule__ProjectDependencyScope__Alternatives
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
    // InternalN4MFParser.g:808:1: ruleModuleLoader : ( ( rule__ModuleLoader__Alternatives ) ) ;
    public final void ruleModuleLoader() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:812:1: ( ( ( rule__ModuleLoader__Alternatives ) ) )
            // InternalN4MFParser.g:813:1: ( ( rule__ModuleLoader__Alternatives ) )
            {
            // InternalN4MFParser.g:813:1: ( ( rule__ModuleLoader__Alternatives ) )
            // InternalN4MFParser.g:814:1: ( rule__ModuleLoader__Alternatives )
            {
             before(grammarAccess.getModuleLoaderAccess().getAlternatives()); 
            // InternalN4MFParser.g:815:1: ( rule__ModuleLoader__Alternatives )
            // InternalN4MFParser.g:815:2: rule__ModuleLoader__Alternatives
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


    // $ANTLR start "rule__ProjectDescription__Alternatives_0_0"
    // InternalN4MFParser.g:826:1: rule__ProjectDescription__Alternatives_0_0 : ( ( ArtifactId ) | ( ProjectId ) );
    public final void rule__ProjectDescription__Alternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:830:1: ( ( ArtifactId ) | ( ProjectId ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==ArtifactId) ) {
                alt1=1;
            }
            else if ( (LA1_0==ProjectId) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalN4MFParser.g:831:1: ( ArtifactId )
                    {
                    // InternalN4MFParser.g:831:1: ( ArtifactId )
                    // InternalN4MFParser.g:832:1: ArtifactId
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0_0()); 
                    match(input,ArtifactId,FOLLOW_2); 
                     after(grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:839:6: ( ProjectId )
                    {
                    // InternalN4MFParser.g:839:6: ( ProjectId )
                    // InternalN4MFParser.g:840:1: ProjectId
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProjectIdKeyword_0_0_1()); 
                    match(input,ProjectId,FOLLOW_2); 
                     after(grammarAccess.getProjectDescriptionAccess().getProjectIdKeyword_0_0_1()); 

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
    // $ANTLR end "rule__ProjectDescription__Alternatives_0_0"


    // $ANTLR start "rule__VersionConstraint__Alternatives"
    // InternalN4MFParser.g:852:1: rule__VersionConstraint__Alternatives : ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) );
    public final void rule__VersionConstraint__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:856:1: ( ( ( rule__VersionConstraint__Group_0__0 ) ) | ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LeftParenthesis||LA2_0==LeftSquareBracket) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_INT) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalN4MFParser.g:857:1: ( ( rule__VersionConstraint__Group_0__0 ) )
                    {
                    // InternalN4MFParser.g:857:1: ( ( rule__VersionConstraint__Group_0__0 ) )
                    // InternalN4MFParser.g:858:1: ( rule__VersionConstraint__Group_0__0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0()); 
                    // InternalN4MFParser.g:859:1: ( rule__VersionConstraint__Group_0__0 )
                    // InternalN4MFParser.g:859:2: rule__VersionConstraint__Group_0__0
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
                    // InternalN4MFParser.g:863:6: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    {
                    // InternalN4MFParser.g:863:6: ( ( rule__VersionConstraint__LowerVersionAssignment_1 ) )
                    // InternalN4MFParser.g:864:1: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_1()); 
                    // InternalN4MFParser.g:865:1: ( rule__VersionConstraint__LowerVersionAssignment_1 )
                    // InternalN4MFParser.g:865:2: rule__VersionConstraint__LowerVersionAssignment_1
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
    // InternalN4MFParser.g:874:1: rule__VersionConstraint__Alternatives_0_0 : ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:878:1: ( ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) ) | ( LeftSquareBracket ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LeftParenthesis) ) {
                alt3=1;
            }
            else if ( (LA3_0==LeftSquareBracket) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalN4MFParser.g:879:1: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    {
                    // InternalN4MFParser.g:879:1: ( ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 ) )
                    // InternalN4MFParser.g:880:1: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundAssignment_0_0_0()); 
                    // InternalN4MFParser.g:881:1: ( rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 )
                    // InternalN4MFParser.g:881:2: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0
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
                    // InternalN4MFParser.g:885:6: ( LeftSquareBracket )
                    {
                    // InternalN4MFParser.g:885:6: ( LeftSquareBracket )
                    // InternalN4MFParser.g:886:1: LeftSquareBracket
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
    // InternalN4MFParser.g:898:1: rule__VersionConstraint__Alternatives_0_2 : ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) );
    public final void rule__VersionConstraint__Alternatives_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:902:1: ( ( ( rule__VersionConstraint__Group_0_2_0__0 )? ) | ( RightParenthesis ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==EOF||LA5_0==Compile||LA5_0==Test||LA5_0==Comma||LA5_0==RightCurlyBracket) ) {
                alt5=1;
            }
            else if ( (LA5_0==RightParenthesis) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalN4MFParser.g:903:1: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    {
                    // InternalN4MFParser.g:903:1: ( ( rule__VersionConstraint__Group_0_2_0__0 )? )
                    // InternalN4MFParser.g:904:1: ( rule__VersionConstraint__Group_0_2_0__0 )?
                    {
                     before(grammarAccess.getVersionConstraintAccess().getGroup_0_2_0()); 
                    // InternalN4MFParser.g:905:1: ( rule__VersionConstraint__Group_0_2_0__0 )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Comma) ) {
                        int LA4_1 = input.LA(2);

                        if ( (LA4_1==RULE_INT) ) {
                            alt4=1;
                        }
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalN4MFParser.g:905:2: rule__VersionConstraint__Group_0_2_0__0
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
                    // InternalN4MFParser.g:909:6: ( RightParenthesis )
                    {
                    // InternalN4MFParser.g:909:6: ( RightParenthesis )
                    // InternalN4MFParser.g:910:1: RightParenthesis
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
    // InternalN4MFParser.g:922:1: rule__VersionConstraint__Alternatives_0_2_0_2 : ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) );
    public final void rule__VersionConstraint__Alternatives_0_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:926:1: ( ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) ) | ( RightSquareBracket ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RightParenthesis) ) {
                alt6=1;
            }
            else if ( (LA6_0==RightSquareBracket) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalN4MFParser.g:927:1: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    {
                    // InternalN4MFParser.g:927:1: ( ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 ) )
                    // InternalN4MFParser.g:928:1: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    {
                     before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundAssignment_0_2_0_2_0()); 
                    // InternalN4MFParser.g:929:1: ( rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 )
                    // InternalN4MFParser.g:929:2: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0
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
                    // InternalN4MFParser.g:933:6: ( RightSquareBracket )
                    {
                    // InternalN4MFParser.g:933:6: ( RightSquareBracket )
                    // InternalN4MFParser.g:934:1: RightSquareBracket
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
    // InternalN4MFParser.g:946:1: rule__N4mfIdentifier__Alternatives : ( ( RULE_ID ) | ( ArtifactId ) | ( ProjectId ) | ( VendorId ) | ( ProjectName ) | ( VendorName ) | ( ProjectType ) | ( ProjectVersion ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_13__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_17__0 ) ) | ( Content ) | ( Test ) );
    public final void rule__N4mfIdentifier__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:950:1: ( ( RULE_ID ) | ( ArtifactId ) | ( ProjectId ) | ( VendorId ) | ( ProjectName ) | ( VendorName ) | ( ProjectType ) | ( ProjectVersion ) | ( Output ) | ( Libraries ) | ( Resources ) | ( Sources ) | ( ModuleFilters ) | ( ( rule__N4mfIdentifier__Group_13__0 ) ) | ( API ) | ( User ) | ( Application ) | ( ( rule__N4mfIdentifier__Group_17__0 ) ) | ( Content ) | ( Test ) )
            int alt7=20;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt7=1;
                }
                break;
            case ArtifactId:
                {
                alt7=2;
                }
                break;
            case ProjectId:
                {
                alt7=3;
                }
                break;
            case VendorId:
                {
                alt7=4;
                }
                break;
            case ProjectName:
                {
                alt7=5;
                }
                break;
            case VendorName:
                {
                alt7=6;
                }
                break;
            case ProjectType:
                {
                alt7=7;
                }
                break;
            case ProjectVersion:
                {
                alt7=8;
                }
                break;
            case Output:
                {
                alt7=9;
                }
                break;
            case Libraries:
                {
                alt7=10;
                }
                break;
            case Resources:
                {
                alt7=11;
                }
                break;
            case Sources:
                {
                alt7=12;
                }
                break;
            case ModuleFilters:
                {
                alt7=13;
                }
                break;
            case ProjectDependencies:
                {
                alt7=14;
                }
                break;
            case API:
                {
                alt7=15;
                }
                break;
            case User:
                {
                alt7=16;
                }
                break;
            case Application:
                {
                alt7=17;
                }
                break;
            case Processor:
                {
                alt7=18;
                }
                break;
            case Content:
                {
                alt7=19;
                }
                break;
            case Test:
                {
                alt7=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalN4MFParser.g:951:1: ( RULE_ID )
                    {
                    // InternalN4MFParser.g:951:1: ( RULE_ID )
                    // InternalN4MFParser.g:952:1: RULE_ID
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:957:6: ( ArtifactId )
                    {
                    // InternalN4MFParser.g:957:6: ( ArtifactId )
                    // InternalN4MFParser.g:958:1: ArtifactId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 
                    match(input,ArtifactId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:965:6: ( ProjectId )
                    {
                    // InternalN4MFParser.g:965:6: ( ProjectId )
                    // InternalN4MFParser.g:966:1: ProjectId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectIdKeyword_2()); 
                    match(input,ProjectId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectIdKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:973:6: ( VendorId )
                    {
                    // InternalN4MFParser.g:973:6: ( VendorId )
                    // InternalN4MFParser.g:974:1: VendorId
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_3()); 
                    match(input,VendorId,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:981:6: ( ProjectName )
                    {
                    // InternalN4MFParser.g:981:6: ( ProjectName )
                    // InternalN4MFParser.g:982:1: ProjectName
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_4()); 
                    match(input,ProjectName,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:989:6: ( VendorName )
                    {
                    // InternalN4MFParser.g:989:6: ( VendorName )
                    // InternalN4MFParser.g:990:1: VendorName
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_5()); 
                    match(input,VendorName,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:997:6: ( ProjectType )
                    {
                    // InternalN4MFParser.g:997:6: ( ProjectType )
                    // InternalN4MFParser.g:998:1: ProjectType
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_6()); 
                    match(input,ProjectType,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:1005:6: ( ProjectVersion )
                    {
                    // InternalN4MFParser.g:1005:6: ( ProjectVersion )
                    // InternalN4MFParser.g:1006:1: ProjectVersion
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_7()); 
                    match(input,ProjectVersion,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:1013:6: ( Output )
                    {
                    // InternalN4MFParser.g:1013:6: ( Output )
                    // InternalN4MFParser.g:1014:1: Output
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_8()); 
                    match(input,Output,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:1021:6: ( Libraries )
                    {
                    // InternalN4MFParser.g:1021:6: ( Libraries )
                    // InternalN4MFParser.g:1022:1: Libraries
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_9()); 
                    match(input,Libraries,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:1029:6: ( Resources )
                    {
                    // InternalN4MFParser.g:1029:6: ( Resources )
                    // InternalN4MFParser.g:1030:1: Resources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_10()); 
                    match(input,Resources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:1037:6: ( Sources )
                    {
                    // InternalN4MFParser.g:1037:6: ( Sources )
                    // InternalN4MFParser.g:1038:1: Sources
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_11()); 
                    match(input,Sources,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_11()); 

                    }


                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:1045:6: ( ModuleFilters )
                    {
                    // InternalN4MFParser.g:1045:6: ( ModuleFilters )
                    // InternalN4MFParser.g:1046:1: ModuleFilters
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_12()); 
                    match(input,ModuleFilters,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_12()); 

                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:1053:6: ( ( rule__N4mfIdentifier__Group_13__0 ) )
                    {
                    // InternalN4MFParser.g:1053:6: ( ( rule__N4mfIdentifier__Group_13__0 ) )
                    // InternalN4MFParser.g:1054:1: ( rule__N4mfIdentifier__Group_13__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_13()); 
                    // InternalN4MFParser.g:1055:1: ( rule__N4mfIdentifier__Group_13__0 )
                    // InternalN4MFParser.g:1055:2: rule__N4mfIdentifier__Group_13__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_13__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_13()); 

                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:1059:6: ( API )
                    {
                    // InternalN4MFParser.g:1059:6: ( API )
                    // InternalN4MFParser.g:1060:1: API
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_14()); 
                    match(input,API,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:1067:6: ( User )
                    {
                    // InternalN4MFParser.g:1067:6: ( User )
                    // InternalN4MFParser.g:1068:1: User
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_15()); 
                    match(input,User,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getUserKeyword_15()); 

                    }


                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:1075:6: ( Application )
                    {
                    // InternalN4MFParser.g:1075:6: ( Application )
                    // InternalN4MFParser.g:1076:1: Application
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_16()); 
                    match(input,Application,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_16()); 

                    }


                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:1083:6: ( ( rule__N4mfIdentifier__Group_17__0 ) )
                    {
                    // InternalN4MFParser.g:1083:6: ( ( rule__N4mfIdentifier__Group_17__0 ) )
                    // InternalN4MFParser.g:1084:1: ( rule__N4mfIdentifier__Group_17__0 )
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getGroup_17()); 
                    // InternalN4MFParser.g:1085:1: ( rule__N4mfIdentifier__Group_17__0 )
                    // InternalN4MFParser.g:1085:2: rule__N4mfIdentifier__Group_17__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__N4mfIdentifier__Group_17__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getN4mfIdentifierAccess().getGroup_17()); 

                    }


                    }
                    break;
                case 19 :
                    // InternalN4MFParser.g:1089:6: ( Content )
                    {
                    // InternalN4MFParser.g:1089:6: ( Content )
                    // InternalN4MFParser.g:1090:1: Content
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_18()); 
                    match(input,Content,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getContentKeyword_18()); 

                    }


                    }
                    break;
                case 20 :
                    // InternalN4MFParser.g:1097:6: ( Test )
                    {
                    // InternalN4MFParser.g:1097:6: ( Test )
                    // InternalN4MFParser.g:1098:1: Test
                    {
                     before(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_19()); 
                    match(input,Test,FOLLOW_2); 
                     after(grammarAccess.getN4mfIdentifierAccess().getTestKeyword_19()); 

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
    // InternalN4MFParser.g:1110:1: rule__ProjectType__Alternatives : ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) );
    public final void rule__ProjectType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1114:1: ( ( ( Application ) ) | ( ( Processor ) ) | ( ( Library ) ) | ( ( API ) ) | ( ( RuntimeEnvironment ) ) | ( ( RuntimeLibrary ) ) | ( ( Test ) ) )
            int alt8=7;
            switch ( input.LA(1) ) {
            case Application:
                {
                alt8=1;
                }
                break;
            case Processor:
                {
                alt8=2;
                }
                break;
            case Library:
                {
                alt8=3;
                }
                break;
            case API:
                {
                alt8=4;
                }
                break;
            case RuntimeEnvironment:
                {
                alt8=5;
                }
                break;
            case RuntimeLibrary:
                {
                alt8=6;
                }
                break;
            case Test:
                {
                alt8=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalN4MFParser.g:1115:1: ( ( Application ) )
                    {
                    // InternalN4MFParser.g:1115:1: ( ( Application ) )
                    // InternalN4MFParser.g:1116:1: ( Application )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1117:1: ( Application )
                    // InternalN4MFParser.g:1117:3: Application
                    {
                    match(input,Application,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1122:6: ( ( Processor ) )
                    {
                    // InternalN4MFParser.g:1122:6: ( ( Processor ) )
                    // InternalN4MFParser.g:1123:1: ( Processor )
                    {
                     before(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1124:1: ( Processor )
                    // InternalN4MFParser.g:1124:3: Processor
                    {
                    match(input,Processor,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1129:6: ( ( Library ) )
                    {
                    // InternalN4MFParser.g:1129:6: ( ( Library ) )
                    // InternalN4MFParser.g:1130:1: ( Library )
                    {
                     before(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1131:1: ( Library )
                    // InternalN4MFParser.g:1131:3: Library
                    {
                    match(input,Library,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:1136:6: ( ( API ) )
                    {
                    // InternalN4MFParser.g:1136:6: ( ( API ) )
                    // InternalN4MFParser.g:1137:1: ( API )
                    {
                     before(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 
                    // InternalN4MFParser.g:1138:1: ( API )
                    // InternalN4MFParser.g:1138:3: API
                    {
                    match(input,API,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:1143:6: ( ( RuntimeEnvironment ) )
                    {
                    // InternalN4MFParser.g:1143:6: ( ( RuntimeEnvironment ) )
                    // InternalN4MFParser.g:1144:1: ( RuntimeEnvironment )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 
                    // InternalN4MFParser.g:1145:1: ( RuntimeEnvironment )
                    // InternalN4MFParser.g:1145:3: RuntimeEnvironment
                    {
                    match(input,RuntimeEnvironment,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:1150:6: ( ( RuntimeLibrary ) )
                    {
                    // InternalN4MFParser.g:1150:6: ( ( RuntimeLibrary ) )
                    // InternalN4MFParser.g:1151:1: ( RuntimeLibrary )
                    {
                     before(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 
                    // InternalN4MFParser.g:1152:1: ( RuntimeLibrary )
                    // InternalN4MFParser.g:1152:3: RuntimeLibrary
                    {
                    match(input,RuntimeLibrary,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:1157:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1157:6: ( ( Test ) )
                    // InternalN4MFParser.g:1158:1: ( Test )
                    {
                     before(grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6()); 
                    // InternalN4MFParser.g:1159:1: ( Test )
                    // InternalN4MFParser.g:1159:3: Test
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
    // InternalN4MFParser.g:1169:1: rule__SourceFragmentType__Alternatives : ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) );
    public final void rule__SourceFragmentType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1173:1: ( ( ( Source ) ) | ( ( External ) ) | ( ( Test ) ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case Source:
                {
                alt9=1;
                }
                break;
            case External:
                {
                alt9=2;
                }
                break;
            case Test:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalN4MFParser.g:1174:1: ( ( Source ) )
                    {
                    // InternalN4MFParser.g:1174:1: ( ( Source ) )
                    // InternalN4MFParser.g:1175:1: ( Source )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1176:1: ( Source )
                    // InternalN4MFParser.g:1176:3: Source
                    {
                    match(input,Source,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1181:6: ( ( External ) )
                    {
                    // InternalN4MFParser.g:1181:6: ( ( External ) )
                    // InternalN4MFParser.g:1182:1: ( External )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1183:1: ( External )
                    // InternalN4MFParser.g:1183:3: External
                    {
                    match(input,External,FOLLOW_2); 

                    }

                     after(grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1188:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1188:6: ( ( Test ) )
                    // InternalN4MFParser.g:1189:1: ( Test )
                    {
                     before(grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1190:1: ( Test )
                    // InternalN4MFParser.g:1190:3: Test
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
    // InternalN4MFParser.g:1200:1: rule__ModuleFilterType__Alternatives : ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) );
    public final void rule__ModuleFilterType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1204:1: ( ( ( NoValidate ) ) | ( ( NoModuleWrap ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NoValidate) ) {
                alt10=1;
            }
            else if ( (LA10_0==NoModuleWrap) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalN4MFParser.g:1205:1: ( ( NoValidate ) )
                    {
                    // InternalN4MFParser.g:1205:1: ( ( NoValidate ) )
                    // InternalN4MFParser.g:1206:1: ( NoValidate )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1207:1: ( NoValidate )
                    // InternalN4MFParser.g:1207:3: NoValidate
                    {
                    match(input,NoValidate,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1212:6: ( ( NoModuleWrap ) )
                    {
                    // InternalN4MFParser.g:1212:6: ( ( NoModuleWrap ) )
                    // InternalN4MFParser.g:1213:1: ( NoModuleWrap )
                    {
                     before(grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1214:1: ( NoModuleWrap )
                    // InternalN4MFParser.g:1214:3: NoModuleWrap
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
    // InternalN4MFParser.g:1224:1: rule__ProjectDependencyScope__Alternatives : ( ( ( Compile ) ) | ( ( Test ) ) );
    public final void rule__ProjectDependencyScope__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1228:1: ( ( ( Compile ) ) | ( ( Test ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==Compile) ) {
                alt11=1;
            }
            else if ( (LA11_0==Test) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalN4MFParser.g:1229:1: ( ( Compile ) )
                    {
                    // InternalN4MFParser.g:1229:1: ( ( Compile ) )
                    // InternalN4MFParser.g:1230:1: ( Compile )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1231:1: ( Compile )
                    // InternalN4MFParser.g:1231:3: Compile
                    {
                    match(input,Compile,FOLLOW_2); 

                    }

                     after(grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1236:6: ( ( Test ) )
                    {
                    // InternalN4MFParser.g:1236:6: ( ( Test ) )
                    // InternalN4MFParser.g:1237:1: ( Test )
                    {
                     before(grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1238:1: ( Test )
                    // InternalN4MFParser.g:1238:3: Test
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
    // InternalN4MFParser.g:1248:1: rule__ModuleLoader__Alternatives : ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) );
    public final void rule__ModuleLoader__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1252:1: ( ( ( N4js ) ) | ( ( Commonjs ) ) | ( ( Node_builtin ) ) )
            int alt12=3;
            switch ( input.LA(1) ) {
            case N4js:
                {
                alt12=1;
                }
                break;
            case Commonjs:
                {
                alt12=2;
                }
                break;
            case Node_builtin:
                {
                alt12=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalN4MFParser.g:1253:1: ( ( N4js ) )
                    {
                    // InternalN4MFParser.g:1253:1: ( ( N4js ) )
                    // InternalN4MFParser.g:1254:1: ( N4js )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 
                    // InternalN4MFParser.g:1255:1: ( N4js )
                    // InternalN4MFParser.g:1255:3: N4js
                    {
                    match(input,N4js,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:1260:6: ( ( Commonjs ) )
                    {
                    // InternalN4MFParser.g:1260:6: ( ( Commonjs ) )
                    // InternalN4MFParser.g:1261:1: ( Commonjs )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 
                    // InternalN4MFParser.g:1262:1: ( Commonjs )
                    // InternalN4MFParser.g:1262:3: Commonjs
                    {
                    match(input,Commonjs,FOLLOW_2); 

                    }

                     after(grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:1267:6: ( ( Node_builtin ) )
                    {
                    // InternalN4MFParser.g:1267:6: ( ( Node_builtin ) )
                    // InternalN4MFParser.g:1268:1: ( Node_builtin )
                    {
                     before(grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2()); 
                    // InternalN4MFParser.g:1269:1: ( Node_builtin )
                    // InternalN4MFParser.g:1269:3: Node_builtin
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
    // InternalN4MFParser.g:1281:1: rule__ProjectDescription__Group_0__0 : rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 ;
    public final void rule__ProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1285:1: ( rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:1286:2: rule__ProjectDescription__Group_0__0__Impl rule__ProjectDescription__Group_0__1
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
    // InternalN4MFParser.g:1293:1: rule__ProjectDescription__Group_0__0__Impl : ( ( rule__ProjectDescription__Alternatives_0_0 ) ) ;
    public final void rule__ProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1297:1: ( ( ( rule__ProjectDescription__Alternatives_0_0 ) ) )
            // InternalN4MFParser.g:1298:1: ( ( rule__ProjectDescription__Alternatives_0_0 ) )
            {
            // InternalN4MFParser.g:1298:1: ( ( rule__ProjectDescription__Alternatives_0_0 ) )
            // InternalN4MFParser.g:1299:1: ( rule__ProjectDescription__Alternatives_0_0 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getAlternatives_0_0()); 
            // InternalN4MFParser.g:1300:1: ( rule__ProjectDescription__Alternatives_0_0 )
            // InternalN4MFParser.g:1300:2: rule__ProjectDescription__Alternatives_0_0
            {
            pushFollow(FOLLOW_2);
            rule__ProjectDescription__Alternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getAlternatives_0_0()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1310:1: rule__ProjectDescription__Group_0__1 : rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 ;
    public final void rule__ProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1314:1: ( rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2 )
            // InternalN4MFParser.g:1315:2: rule__ProjectDescription__Group_0__1__Impl rule__ProjectDescription__Group_0__2
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
    // InternalN4MFParser.g:1322:1: rule__ProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1326:1: ( ( Colon ) )
            // InternalN4MFParser.g:1327:1: ( Colon )
            {
            // InternalN4MFParser.g:1327:1: ( Colon )
            // InternalN4MFParser.g:1328:1: Colon
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
    // InternalN4MFParser.g:1341:1: rule__ProjectDescription__Group_0__2 : rule__ProjectDescription__Group_0__2__Impl ;
    public final void rule__ProjectDescription__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1345:1: ( rule__ProjectDescription__Group_0__2__Impl )
            // InternalN4MFParser.g:1346:2: rule__ProjectDescription__Group_0__2__Impl
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
    // InternalN4MFParser.g:1352:1: rule__ProjectDescription__Group_0__2__Impl : ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) ) ;
    public final void rule__ProjectDescription__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1356:1: ( ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) ) )
            // InternalN4MFParser.g:1357:1: ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) )
            {
            // InternalN4MFParser.g:1357:1: ( ( rule__ProjectDescription__ProjectIdAssignment_0_2 ) )
            // InternalN4MFParser.g:1358:1: ( rule__ProjectDescription__ProjectIdAssignment_0_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectIdAssignment_0_2()); 
            // InternalN4MFParser.g:1359:1: ( rule__ProjectDescription__ProjectIdAssignment_0_2 )
            // InternalN4MFParser.g:1359:2: rule__ProjectDescription__ProjectIdAssignment_0_2
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
    // InternalN4MFParser.g:1375:1: rule__ProjectDescription__Group_1__0 : rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 ;
    public final void rule__ProjectDescription__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1379:1: ( rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1 )
            // InternalN4MFParser.g:1380:2: rule__ProjectDescription__Group_1__0__Impl rule__ProjectDescription__Group_1__1
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
    // InternalN4MFParser.g:1387:1: rule__ProjectDescription__Group_1__0__Impl : ( ProjectName ) ;
    public final void rule__ProjectDescription__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1391:1: ( ( ProjectName ) )
            // InternalN4MFParser.g:1392:1: ( ProjectName )
            {
            // InternalN4MFParser.g:1392:1: ( ProjectName )
            // InternalN4MFParser.g:1393:1: ProjectName
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
    // InternalN4MFParser.g:1406:1: rule__ProjectDescription__Group_1__1 : rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 ;
    public final void rule__ProjectDescription__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1410:1: ( rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2 )
            // InternalN4MFParser.g:1411:2: rule__ProjectDescription__Group_1__1__Impl rule__ProjectDescription__Group_1__2
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
    // InternalN4MFParser.g:1418:1: rule__ProjectDescription__Group_1__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1422:1: ( ( Colon ) )
            // InternalN4MFParser.g:1423:1: ( Colon )
            {
            // InternalN4MFParser.g:1423:1: ( Colon )
            // InternalN4MFParser.g:1424:1: Colon
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
    // InternalN4MFParser.g:1437:1: rule__ProjectDescription__Group_1__2 : rule__ProjectDescription__Group_1__2__Impl ;
    public final void rule__ProjectDescription__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1441:1: ( rule__ProjectDescription__Group_1__2__Impl )
            // InternalN4MFParser.g:1442:2: rule__ProjectDescription__Group_1__2__Impl
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
    // InternalN4MFParser.g:1448:1: rule__ProjectDescription__Group_1__2__Impl : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1452:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:1453:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:1453:1: ( RULE_STRING )
            // InternalN4MFParser.g:1454:1: RULE_STRING
            {
             before(grammarAccess.getProjectDescriptionAccess().getSTRINGTerminalRuleCall_1_2()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getProjectDescriptionAccess().getSTRINGTerminalRuleCall_1_2()); 

            }


            }

        }
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
    // InternalN4MFParser.g:1471:1: rule__ProjectDescription__Group_2__0 : rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 ;
    public final void rule__ProjectDescription__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1475:1: ( rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1 )
            // InternalN4MFParser.g:1476:2: rule__ProjectDescription__Group_2__0__Impl rule__ProjectDescription__Group_2__1
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
    // InternalN4MFParser.g:1483:1: rule__ProjectDescription__Group_2__0__Impl : ( ProjectType ) ;
    public final void rule__ProjectDescription__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1487:1: ( ( ProjectType ) )
            // InternalN4MFParser.g:1488:1: ( ProjectType )
            {
            // InternalN4MFParser.g:1488:1: ( ProjectType )
            // InternalN4MFParser.g:1489:1: ProjectType
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
    // InternalN4MFParser.g:1502:1: rule__ProjectDescription__Group_2__1 : rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 ;
    public final void rule__ProjectDescription__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1506:1: ( rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2 )
            // InternalN4MFParser.g:1507:2: rule__ProjectDescription__Group_2__1__Impl rule__ProjectDescription__Group_2__2
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
    // InternalN4MFParser.g:1514:1: rule__ProjectDescription__Group_2__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1518:1: ( ( Colon ) )
            // InternalN4MFParser.g:1519:1: ( Colon )
            {
            // InternalN4MFParser.g:1519:1: ( Colon )
            // InternalN4MFParser.g:1520:1: Colon
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
    // InternalN4MFParser.g:1533:1: rule__ProjectDescription__Group_2__2 : rule__ProjectDescription__Group_2__2__Impl ;
    public final void rule__ProjectDescription__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1537:1: ( rule__ProjectDescription__Group_2__2__Impl )
            // InternalN4MFParser.g:1538:2: rule__ProjectDescription__Group_2__2__Impl
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
    // InternalN4MFParser.g:1544:1: rule__ProjectDescription__Group_2__2__Impl : ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) ) ;
    public final void rule__ProjectDescription__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1548:1: ( ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) ) )
            // InternalN4MFParser.g:1549:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) )
            {
            // InternalN4MFParser.g:1549:1: ( ( rule__ProjectDescription__ProjectTypeAssignment_2_2 ) )
            // InternalN4MFParser.g:1550:1: ( rule__ProjectDescription__ProjectTypeAssignment_2_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectTypeAssignment_2_2()); 
            // InternalN4MFParser.g:1551:1: ( rule__ProjectDescription__ProjectTypeAssignment_2_2 )
            // InternalN4MFParser.g:1551:2: rule__ProjectDescription__ProjectTypeAssignment_2_2
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
    // InternalN4MFParser.g:1567:1: rule__ProjectDescription__Group_3__0 : rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 ;
    public final void rule__ProjectDescription__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1571:1: ( rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1 )
            // InternalN4MFParser.g:1572:2: rule__ProjectDescription__Group_3__0__Impl rule__ProjectDescription__Group_3__1
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
    // InternalN4MFParser.g:1579:1: rule__ProjectDescription__Group_3__0__Impl : ( ProjectVersion ) ;
    public final void rule__ProjectDescription__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1583:1: ( ( ProjectVersion ) )
            // InternalN4MFParser.g:1584:1: ( ProjectVersion )
            {
            // InternalN4MFParser.g:1584:1: ( ProjectVersion )
            // InternalN4MFParser.g:1585:1: ProjectVersion
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
    // InternalN4MFParser.g:1598:1: rule__ProjectDescription__Group_3__1 : rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 ;
    public final void rule__ProjectDescription__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1602:1: ( rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2 )
            // InternalN4MFParser.g:1603:2: rule__ProjectDescription__Group_3__1__Impl rule__ProjectDescription__Group_3__2
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
    // InternalN4MFParser.g:1610:1: rule__ProjectDescription__Group_3__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1614:1: ( ( Colon ) )
            // InternalN4MFParser.g:1615:1: ( Colon )
            {
            // InternalN4MFParser.g:1615:1: ( Colon )
            // InternalN4MFParser.g:1616:1: Colon
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
    // InternalN4MFParser.g:1629:1: rule__ProjectDescription__Group_3__2 : rule__ProjectDescription__Group_3__2__Impl ;
    public final void rule__ProjectDescription__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1633:1: ( rule__ProjectDescription__Group_3__2__Impl )
            // InternalN4MFParser.g:1634:2: rule__ProjectDescription__Group_3__2__Impl
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
    // InternalN4MFParser.g:1640:1: rule__ProjectDescription__Group_3__2__Impl : ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) ) ;
    public final void rule__ProjectDescription__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1644:1: ( ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) ) )
            // InternalN4MFParser.g:1645:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) )
            {
            // InternalN4MFParser.g:1645:1: ( ( rule__ProjectDescription__ProjectVersionAssignment_3_2 ) )
            // InternalN4MFParser.g:1646:1: ( rule__ProjectDescription__ProjectVersionAssignment_3_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getProjectVersionAssignment_3_2()); 
            // InternalN4MFParser.g:1647:1: ( rule__ProjectDescription__ProjectVersionAssignment_3_2 )
            // InternalN4MFParser.g:1647:2: rule__ProjectDescription__ProjectVersionAssignment_3_2
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
    // InternalN4MFParser.g:1663:1: rule__ProjectDescription__Group_4__0 : rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 ;
    public final void rule__ProjectDescription__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1667:1: ( rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1 )
            // InternalN4MFParser.g:1668:2: rule__ProjectDescription__Group_4__0__Impl rule__ProjectDescription__Group_4__1
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
    // InternalN4MFParser.g:1675:1: rule__ProjectDescription__Group_4__0__Impl : ( VendorId ) ;
    public final void rule__ProjectDescription__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1679:1: ( ( VendorId ) )
            // InternalN4MFParser.g:1680:1: ( VendorId )
            {
            // InternalN4MFParser.g:1680:1: ( VendorId )
            // InternalN4MFParser.g:1681:1: VendorId
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
    // InternalN4MFParser.g:1694:1: rule__ProjectDescription__Group_4__1 : rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 ;
    public final void rule__ProjectDescription__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1698:1: ( rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2 )
            // InternalN4MFParser.g:1699:2: rule__ProjectDescription__Group_4__1__Impl rule__ProjectDescription__Group_4__2
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
    // InternalN4MFParser.g:1706:1: rule__ProjectDescription__Group_4__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1710:1: ( ( Colon ) )
            // InternalN4MFParser.g:1711:1: ( Colon )
            {
            // InternalN4MFParser.g:1711:1: ( Colon )
            // InternalN4MFParser.g:1712:1: Colon
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
    // InternalN4MFParser.g:1725:1: rule__ProjectDescription__Group_4__2 : rule__ProjectDescription__Group_4__2__Impl ;
    public final void rule__ProjectDescription__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1729:1: ( rule__ProjectDescription__Group_4__2__Impl )
            // InternalN4MFParser.g:1730:2: rule__ProjectDescription__Group_4__2__Impl
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
    // InternalN4MFParser.g:1736:1: rule__ProjectDescription__Group_4__2__Impl : ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) ) ;
    public final void rule__ProjectDescription__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1740:1: ( ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) ) )
            // InternalN4MFParser.g:1741:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) )
            {
            // InternalN4MFParser.g:1741:1: ( ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 ) )
            // InternalN4MFParser.g:1742:1: ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdAssignment_4_2()); 
            // InternalN4MFParser.g:1743:1: ( rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 )
            // InternalN4MFParser.g:1743:2: rule__ProjectDescription__DeclaredVendorIdAssignment_4_2
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
    // InternalN4MFParser.g:1759:1: rule__ProjectDescription__Group_5__0 : rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 ;
    public final void rule__ProjectDescription__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1763:1: ( rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1 )
            // InternalN4MFParser.g:1764:2: rule__ProjectDescription__Group_5__0__Impl rule__ProjectDescription__Group_5__1
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
    // InternalN4MFParser.g:1771:1: rule__ProjectDescription__Group_5__0__Impl : ( VendorName ) ;
    public final void rule__ProjectDescription__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1775:1: ( ( VendorName ) )
            // InternalN4MFParser.g:1776:1: ( VendorName )
            {
            // InternalN4MFParser.g:1776:1: ( VendorName )
            // InternalN4MFParser.g:1777:1: VendorName
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
    // InternalN4MFParser.g:1790:1: rule__ProjectDescription__Group_5__1 : rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 ;
    public final void rule__ProjectDescription__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1794:1: ( rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2 )
            // InternalN4MFParser.g:1795:2: rule__ProjectDescription__Group_5__1__Impl rule__ProjectDescription__Group_5__2
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
    // InternalN4MFParser.g:1802:1: rule__ProjectDescription__Group_5__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1806:1: ( ( Colon ) )
            // InternalN4MFParser.g:1807:1: ( Colon )
            {
            // InternalN4MFParser.g:1807:1: ( Colon )
            // InternalN4MFParser.g:1808:1: Colon
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
    // InternalN4MFParser.g:1821:1: rule__ProjectDescription__Group_5__2 : rule__ProjectDescription__Group_5__2__Impl ;
    public final void rule__ProjectDescription__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1825:1: ( rule__ProjectDescription__Group_5__2__Impl )
            // InternalN4MFParser.g:1826:2: rule__ProjectDescription__Group_5__2__Impl
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
    // InternalN4MFParser.g:1832:1: rule__ProjectDescription__Group_5__2__Impl : ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) ) ;
    public final void rule__ProjectDescription__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1836:1: ( ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) ) )
            // InternalN4MFParser.g:1837:1: ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) )
            {
            // InternalN4MFParser.g:1837:1: ( ( rule__ProjectDescription__VendorNameAssignment_5_2 ) )
            // InternalN4MFParser.g:1838:1: ( rule__ProjectDescription__VendorNameAssignment_5_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getVendorNameAssignment_5_2()); 
            // InternalN4MFParser.g:1839:1: ( rule__ProjectDescription__VendorNameAssignment_5_2 )
            // InternalN4MFParser.g:1839:2: rule__ProjectDescription__VendorNameAssignment_5_2
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
    // InternalN4MFParser.g:1855:1: rule__ProjectDescription__Group_6__0 : rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1 ;
    public final void rule__ProjectDescription__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1859:1: ( rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1 )
            // InternalN4MFParser.g:1860:2: rule__ProjectDescription__Group_6__0__Impl rule__ProjectDescription__Group_6__1
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
    // InternalN4MFParser.g:1867:1: rule__ProjectDescription__Group_6__0__Impl : ( MainModule ) ;
    public final void rule__ProjectDescription__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1871:1: ( ( MainModule ) )
            // InternalN4MFParser.g:1872:1: ( MainModule )
            {
            // InternalN4MFParser.g:1872:1: ( MainModule )
            // InternalN4MFParser.g:1873:1: MainModule
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
    // InternalN4MFParser.g:1886:1: rule__ProjectDescription__Group_6__1 : rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2 ;
    public final void rule__ProjectDescription__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1890:1: ( rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2 )
            // InternalN4MFParser.g:1891:2: rule__ProjectDescription__Group_6__1__Impl rule__ProjectDescription__Group_6__2
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
    // InternalN4MFParser.g:1898:1: rule__ProjectDescription__Group_6__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1902:1: ( ( Colon ) )
            // InternalN4MFParser.g:1903:1: ( Colon )
            {
            // InternalN4MFParser.g:1903:1: ( Colon )
            // InternalN4MFParser.g:1904:1: Colon
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
    // InternalN4MFParser.g:1917:1: rule__ProjectDescription__Group_6__2 : rule__ProjectDescription__Group_6__2__Impl ;
    public final void rule__ProjectDescription__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1921:1: ( rule__ProjectDescription__Group_6__2__Impl )
            // InternalN4MFParser.g:1922:2: rule__ProjectDescription__Group_6__2__Impl
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
    // InternalN4MFParser.g:1928:1: rule__ProjectDescription__Group_6__2__Impl : ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) ) ;
    public final void rule__ProjectDescription__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1932:1: ( ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) ) )
            // InternalN4MFParser.g:1933:1: ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) )
            {
            // InternalN4MFParser.g:1933:1: ( ( rule__ProjectDescription__MainModuleAssignment_6_2 ) )
            // InternalN4MFParser.g:1934:1: ( rule__ProjectDescription__MainModuleAssignment_6_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getMainModuleAssignment_6_2()); 
            // InternalN4MFParser.g:1935:1: ( rule__ProjectDescription__MainModuleAssignment_6_2 )
            // InternalN4MFParser.g:1935:2: rule__ProjectDescription__MainModuleAssignment_6_2
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
    // InternalN4MFParser.g:1951:1: rule__ProjectDescription__Group_11__0 : rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1 ;
    public final void rule__ProjectDescription__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1955:1: ( rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1 )
            // InternalN4MFParser.g:1956:2: rule__ProjectDescription__Group_11__0__Impl rule__ProjectDescription__Group_11__1
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
    // InternalN4MFParser.g:1963:1: rule__ProjectDescription__Group_11__0__Impl : ( ImplementationId ) ;
    public final void rule__ProjectDescription__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1967:1: ( ( ImplementationId ) )
            // InternalN4MFParser.g:1968:1: ( ImplementationId )
            {
            // InternalN4MFParser.g:1968:1: ( ImplementationId )
            // InternalN4MFParser.g:1969:1: ImplementationId
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
    // InternalN4MFParser.g:1982:1: rule__ProjectDescription__Group_11__1 : rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2 ;
    public final void rule__ProjectDescription__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1986:1: ( rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2 )
            // InternalN4MFParser.g:1987:2: rule__ProjectDescription__Group_11__1__Impl rule__ProjectDescription__Group_11__2
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
    // InternalN4MFParser.g:1994:1: rule__ProjectDescription__Group_11__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:1998:1: ( ( Colon ) )
            // InternalN4MFParser.g:1999:1: ( Colon )
            {
            // InternalN4MFParser.g:1999:1: ( Colon )
            // InternalN4MFParser.g:2000:1: Colon
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
    // InternalN4MFParser.g:2013:1: rule__ProjectDescription__Group_11__2 : rule__ProjectDescription__Group_11__2__Impl ;
    public final void rule__ProjectDescription__Group_11__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2017:1: ( rule__ProjectDescription__Group_11__2__Impl )
            // InternalN4MFParser.g:2018:2: rule__ProjectDescription__Group_11__2__Impl
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
    // InternalN4MFParser.g:2024:1: rule__ProjectDescription__Group_11__2__Impl : ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) ) ;
    public final void rule__ProjectDescription__Group_11__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2028:1: ( ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) ) )
            // InternalN4MFParser.g:2029:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) )
            {
            // InternalN4MFParser.g:2029:1: ( ( rule__ProjectDescription__ImplementationIdAssignment_11_2 ) )
            // InternalN4MFParser.g:2030:1: ( rule__ProjectDescription__ImplementationIdAssignment_11_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getImplementationIdAssignment_11_2()); 
            // InternalN4MFParser.g:2031:1: ( rule__ProjectDescription__ImplementationIdAssignment_11_2 )
            // InternalN4MFParser.g:2031:2: rule__ProjectDescription__ImplementationIdAssignment_11_2
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
    // InternalN4MFParser.g:2047:1: rule__ProjectDescription__Group_15__0 : rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 ;
    public final void rule__ProjectDescription__Group_15__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2051:1: ( rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1 )
            // InternalN4MFParser.g:2052:2: rule__ProjectDescription__Group_15__0__Impl rule__ProjectDescription__Group_15__1
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
    // InternalN4MFParser.g:2059:1: rule__ProjectDescription__Group_15__0__Impl : ( Output ) ;
    public final void rule__ProjectDescription__Group_15__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2063:1: ( ( Output ) )
            // InternalN4MFParser.g:2064:1: ( Output )
            {
            // InternalN4MFParser.g:2064:1: ( Output )
            // InternalN4MFParser.g:2065:1: Output
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
    // InternalN4MFParser.g:2078:1: rule__ProjectDescription__Group_15__1 : rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 ;
    public final void rule__ProjectDescription__Group_15__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2082:1: ( rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2 )
            // InternalN4MFParser.g:2083:2: rule__ProjectDescription__Group_15__1__Impl rule__ProjectDescription__Group_15__2
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
    // InternalN4MFParser.g:2090:1: rule__ProjectDescription__Group_15__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_15__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2094:1: ( ( Colon ) )
            // InternalN4MFParser.g:2095:1: ( Colon )
            {
            // InternalN4MFParser.g:2095:1: ( Colon )
            // InternalN4MFParser.g:2096:1: Colon
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
    // InternalN4MFParser.g:2109:1: rule__ProjectDescription__Group_15__2 : rule__ProjectDescription__Group_15__2__Impl ;
    public final void rule__ProjectDescription__Group_15__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2113:1: ( rule__ProjectDescription__Group_15__2__Impl )
            // InternalN4MFParser.g:2114:2: rule__ProjectDescription__Group_15__2__Impl
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
    // InternalN4MFParser.g:2120:1: rule__ProjectDescription__Group_15__2__Impl : ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) ) ;
    public final void rule__ProjectDescription__Group_15__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2124:1: ( ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) ) )
            // InternalN4MFParser.g:2125:1: ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) )
            {
            // InternalN4MFParser.g:2125:1: ( ( rule__ProjectDescription__OutputPathAssignment_15_2 ) )
            // InternalN4MFParser.g:2126:1: ( rule__ProjectDescription__OutputPathAssignment_15_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getOutputPathAssignment_15_2()); 
            // InternalN4MFParser.g:2127:1: ( rule__ProjectDescription__OutputPathAssignment_15_2 )
            // InternalN4MFParser.g:2127:2: rule__ProjectDescription__OutputPathAssignment_15_2
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
    // InternalN4MFParser.g:2143:1: rule__ProjectDescription__Group_16__0 : rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 ;
    public final void rule__ProjectDescription__Group_16__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2147:1: ( rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1 )
            // InternalN4MFParser.g:2148:2: rule__ProjectDescription__Group_16__0__Impl rule__ProjectDescription__Group_16__1
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
    // InternalN4MFParser.g:2155:1: rule__ProjectDescription__Group_16__0__Impl : ( Libraries ) ;
    public final void rule__ProjectDescription__Group_16__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2159:1: ( ( Libraries ) )
            // InternalN4MFParser.g:2160:1: ( Libraries )
            {
            // InternalN4MFParser.g:2160:1: ( Libraries )
            // InternalN4MFParser.g:2161:1: Libraries
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
    // InternalN4MFParser.g:2174:1: rule__ProjectDescription__Group_16__1 : rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 ;
    public final void rule__ProjectDescription__Group_16__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2178:1: ( rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2 )
            // InternalN4MFParser.g:2179:2: rule__ProjectDescription__Group_16__1__Impl rule__ProjectDescription__Group_16__2
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
    // InternalN4MFParser.g:2186:1: rule__ProjectDescription__Group_16__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2190:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2191:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2191:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2192:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2205:1: rule__ProjectDescription__Group_16__2 : rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 ;
    public final void rule__ProjectDescription__Group_16__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2209:1: ( rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3 )
            // InternalN4MFParser.g:2210:2: rule__ProjectDescription__Group_16__2__Impl rule__ProjectDescription__Group_16__3
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
    // InternalN4MFParser.g:2217:1: rule__ProjectDescription__Group_16__2__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) ) ;
    public final void rule__ProjectDescription__Group_16__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2221:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) ) )
            // InternalN4MFParser.g:2222:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) )
            {
            // InternalN4MFParser.g:2222:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_2 ) )
            // InternalN4MFParser.g:2223:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_2()); 
            // InternalN4MFParser.g:2224:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_2 )
            // InternalN4MFParser.g:2224:2: rule__ProjectDescription__LibraryPathsAssignment_16_2
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
    // InternalN4MFParser.g:2234:1: rule__ProjectDescription__Group_16__3 : rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 ;
    public final void rule__ProjectDescription__Group_16__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2238:1: ( rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4 )
            // InternalN4MFParser.g:2239:2: rule__ProjectDescription__Group_16__3__Impl rule__ProjectDescription__Group_16__4
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
    // InternalN4MFParser.g:2246:1: rule__ProjectDescription__Group_16__3__Impl : ( ( rule__ProjectDescription__Group_16_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_16__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2250:1: ( ( ( rule__ProjectDescription__Group_16_3__0 )* ) )
            // InternalN4MFParser.g:2251:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            {
            // InternalN4MFParser.g:2251:1: ( ( rule__ProjectDescription__Group_16_3__0 )* )
            // InternalN4MFParser.g:2252:1: ( rule__ProjectDescription__Group_16_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_16_3()); 
            // InternalN4MFParser.g:2253:1: ( rule__ProjectDescription__Group_16_3__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Comma) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalN4MFParser.g:2253:2: rule__ProjectDescription__Group_16_3__0
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
    // InternalN4MFParser.g:2263:1: rule__ProjectDescription__Group_16__4 : rule__ProjectDescription__Group_16__4__Impl ;
    public final void rule__ProjectDescription__Group_16__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2267:1: ( rule__ProjectDescription__Group_16__4__Impl )
            // InternalN4MFParser.g:2268:2: rule__ProjectDescription__Group_16__4__Impl
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
    // InternalN4MFParser.g:2274:1: rule__ProjectDescription__Group_16__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_16__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2278:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2279:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2279:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2280:1: RightCurlyBracket
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
    // InternalN4MFParser.g:2303:1: rule__ProjectDescription__Group_16_3__0 : rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 ;
    public final void rule__ProjectDescription__Group_16_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2307:1: ( rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1 )
            // InternalN4MFParser.g:2308:2: rule__ProjectDescription__Group_16_3__0__Impl rule__ProjectDescription__Group_16_3__1
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
    // InternalN4MFParser.g:2315:1: rule__ProjectDescription__Group_16_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_16_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2319:1: ( ( Comma ) )
            // InternalN4MFParser.g:2320:1: ( Comma )
            {
            // InternalN4MFParser.g:2320:1: ( Comma )
            // InternalN4MFParser.g:2321:1: Comma
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
    // InternalN4MFParser.g:2334:1: rule__ProjectDescription__Group_16_3__1 : rule__ProjectDescription__Group_16_3__1__Impl ;
    public final void rule__ProjectDescription__Group_16_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2338:1: ( rule__ProjectDescription__Group_16_3__1__Impl )
            // InternalN4MFParser.g:2339:2: rule__ProjectDescription__Group_16_3__1__Impl
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
    // InternalN4MFParser.g:2345:1: rule__ProjectDescription__Group_16_3__1__Impl : ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_16_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2349:1: ( ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) ) )
            // InternalN4MFParser.g:2350:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) )
            {
            // InternalN4MFParser.g:2350:1: ( ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 ) )
            // InternalN4MFParser.g:2351:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getLibraryPathsAssignment_16_3_1()); 
            // InternalN4MFParser.g:2352:1: ( rule__ProjectDescription__LibraryPathsAssignment_16_3_1 )
            // InternalN4MFParser.g:2352:2: rule__ProjectDescription__LibraryPathsAssignment_16_3_1
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
    // InternalN4MFParser.g:2366:1: rule__ProjectDescription__Group_17__0 : rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 ;
    public final void rule__ProjectDescription__Group_17__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2370:1: ( rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1 )
            // InternalN4MFParser.g:2371:2: rule__ProjectDescription__Group_17__0__Impl rule__ProjectDescription__Group_17__1
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
    // InternalN4MFParser.g:2378:1: rule__ProjectDescription__Group_17__0__Impl : ( Resources ) ;
    public final void rule__ProjectDescription__Group_17__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2382:1: ( ( Resources ) )
            // InternalN4MFParser.g:2383:1: ( Resources )
            {
            // InternalN4MFParser.g:2383:1: ( Resources )
            // InternalN4MFParser.g:2384:1: Resources
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
    // InternalN4MFParser.g:2397:1: rule__ProjectDescription__Group_17__1 : rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 ;
    public final void rule__ProjectDescription__Group_17__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2401:1: ( rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2 )
            // InternalN4MFParser.g:2402:2: rule__ProjectDescription__Group_17__1__Impl rule__ProjectDescription__Group_17__2
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
    // InternalN4MFParser.g:2409:1: rule__ProjectDescription__Group_17__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2413:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2414:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2414:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2415:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2428:1: rule__ProjectDescription__Group_17__2 : rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 ;
    public final void rule__ProjectDescription__Group_17__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2432:1: ( rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3 )
            // InternalN4MFParser.g:2433:2: rule__ProjectDescription__Group_17__2__Impl rule__ProjectDescription__Group_17__3
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
    // InternalN4MFParser.g:2440:1: rule__ProjectDescription__Group_17__2__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) ) ;
    public final void rule__ProjectDescription__Group_17__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2444:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) ) )
            // InternalN4MFParser.g:2445:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) )
            {
            // InternalN4MFParser.g:2445:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_2 ) )
            // InternalN4MFParser.g:2446:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_2()); 
            // InternalN4MFParser.g:2447:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_2 )
            // InternalN4MFParser.g:2447:2: rule__ProjectDescription__ResourcePathsAssignment_17_2
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
    // InternalN4MFParser.g:2457:1: rule__ProjectDescription__Group_17__3 : rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4 ;
    public final void rule__ProjectDescription__Group_17__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2461:1: ( rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4 )
            // InternalN4MFParser.g:2462:2: rule__ProjectDescription__Group_17__3__Impl rule__ProjectDescription__Group_17__4
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
    // InternalN4MFParser.g:2469:1: rule__ProjectDescription__Group_17__3__Impl : ( ( rule__ProjectDescription__Group_17_3__0 )* ) ;
    public final void rule__ProjectDescription__Group_17__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2473:1: ( ( ( rule__ProjectDescription__Group_17_3__0 )* ) )
            // InternalN4MFParser.g:2474:1: ( ( rule__ProjectDescription__Group_17_3__0 )* )
            {
            // InternalN4MFParser.g:2474:1: ( ( rule__ProjectDescription__Group_17_3__0 )* )
            // InternalN4MFParser.g:2475:1: ( rule__ProjectDescription__Group_17_3__0 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getGroup_17_3()); 
            // InternalN4MFParser.g:2476:1: ( rule__ProjectDescription__Group_17_3__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Comma) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalN4MFParser.g:2476:2: rule__ProjectDescription__Group_17_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDescription__Group_17_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
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
    // InternalN4MFParser.g:2486:1: rule__ProjectDescription__Group_17__4 : rule__ProjectDescription__Group_17__4__Impl ;
    public final void rule__ProjectDescription__Group_17__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2490:1: ( rule__ProjectDescription__Group_17__4__Impl )
            // InternalN4MFParser.g:2491:2: rule__ProjectDescription__Group_17__4__Impl
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
    // InternalN4MFParser.g:2497:1: rule__ProjectDescription__Group_17__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_17__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2501:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2502:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2502:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2503:1: RightCurlyBracket
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
    // InternalN4MFParser.g:2526:1: rule__ProjectDescription__Group_17_3__0 : rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1 ;
    public final void rule__ProjectDescription__Group_17_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2530:1: ( rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1 )
            // InternalN4MFParser.g:2531:2: rule__ProjectDescription__Group_17_3__0__Impl rule__ProjectDescription__Group_17_3__1
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
    // InternalN4MFParser.g:2538:1: rule__ProjectDescription__Group_17_3__0__Impl : ( Comma ) ;
    public final void rule__ProjectDescription__Group_17_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2542:1: ( ( Comma ) )
            // InternalN4MFParser.g:2543:1: ( Comma )
            {
            // InternalN4MFParser.g:2543:1: ( Comma )
            // InternalN4MFParser.g:2544:1: Comma
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
    // InternalN4MFParser.g:2557:1: rule__ProjectDescription__Group_17_3__1 : rule__ProjectDescription__Group_17_3__1__Impl ;
    public final void rule__ProjectDescription__Group_17_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2561:1: ( rule__ProjectDescription__Group_17_3__1__Impl )
            // InternalN4MFParser.g:2562:2: rule__ProjectDescription__Group_17_3__1__Impl
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
    // InternalN4MFParser.g:2568:1: rule__ProjectDescription__Group_17_3__1__Impl : ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) ) ;
    public final void rule__ProjectDescription__Group_17_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2572:1: ( ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) ) )
            // InternalN4MFParser.g:2573:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) )
            {
            // InternalN4MFParser.g:2573:1: ( ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 ) )
            // InternalN4MFParser.g:2574:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getResourcePathsAssignment_17_3_1()); 
            // InternalN4MFParser.g:2575:1: ( rule__ProjectDescription__ResourcePathsAssignment_17_3_1 )
            // InternalN4MFParser.g:2575:2: rule__ProjectDescription__ResourcePathsAssignment_17_3_1
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
    // InternalN4MFParser.g:2589:1: rule__ProjectDescription__Group_18__0 : rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 ;
    public final void rule__ProjectDescription__Group_18__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2593:1: ( rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1 )
            // InternalN4MFParser.g:2594:2: rule__ProjectDescription__Group_18__0__Impl rule__ProjectDescription__Group_18__1
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
    // InternalN4MFParser.g:2601:1: rule__ProjectDescription__Group_18__0__Impl : ( Sources ) ;
    public final void rule__ProjectDescription__Group_18__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2605:1: ( ( Sources ) )
            // InternalN4MFParser.g:2606:1: ( Sources )
            {
            // InternalN4MFParser.g:2606:1: ( Sources )
            // InternalN4MFParser.g:2607:1: Sources
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
    // InternalN4MFParser.g:2620:1: rule__ProjectDescription__Group_18__1 : rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 ;
    public final void rule__ProjectDescription__Group_18__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2624:1: ( rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2 )
            // InternalN4MFParser.g:2625:2: rule__ProjectDescription__Group_18__1__Impl rule__ProjectDescription__Group_18__2
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
    // InternalN4MFParser.g:2632:1: rule__ProjectDescription__Group_18__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2636:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2637:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2637:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2638:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2651:1: rule__ProjectDescription__Group_18__2 : rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 ;
    public final void rule__ProjectDescription__Group_18__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2655:1: ( rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3 )
            // InternalN4MFParser.g:2656:2: rule__ProjectDescription__Group_18__2__Impl rule__ProjectDescription__Group_18__3
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
    // InternalN4MFParser.g:2663:1: rule__ProjectDescription__Group_18__2__Impl : ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_18__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2667:1: ( ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) ) )
            // InternalN4MFParser.g:2668:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) )
            {
            // InternalN4MFParser.g:2668:1: ( ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* ) )
            // InternalN4MFParser.g:2669:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) ) ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* )
            {
            // InternalN4MFParser.g:2669:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 ) )
            // InternalN4MFParser.g:2670:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 
            // InternalN4MFParser.g:2671:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )
            // InternalN4MFParser.g:2671:2: rule__ProjectDescription__SourceFragmentAssignment_18_2
            {
            pushFollow(FOLLOW_13);
            rule__ProjectDescription__SourceFragmentAssignment_18_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 

            }

            // InternalN4MFParser.g:2674:1: ( ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )* )
            // InternalN4MFParser.g:2675:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getSourceFragmentAssignment_18_2()); 
            // InternalN4MFParser.g:2676:1: ( rule__ProjectDescription__SourceFragmentAssignment_18_2 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==External||LA15_0==Source||LA15_0==Test) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalN4MFParser.g:2676:2: rule__ProjectDescription__SourceFragmentAssignment_18_2
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__ProjectDescription__SourceFragmentAssignment_18_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // InternalN4MFParser.g:2687:1: rule__ProjectDescription__Group_18__3 : rule__ProjectDescription__Group_18__3__Impl ;
    public final void rule__ProjectDescription__Group_18__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2691:1: ( rule__ProjectDescription__Group_18__3__Impl )
            // InternalN4MFParser.g:2692:2: rule__ProjectDescription__Group_18__3__Impl
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
    // InternalN4MFParser.g:2698:1: rule__ProjectDescription__Group_18__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_18__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2702:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2703:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2703:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2704:1: RightCurlyBracket
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
    // InternalN4MFParser.g:2725:1: rule__ProjectDescription__Group_19__0 : rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1 ;
    public final void rule__ProjectDescription__Group_19__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2729:1: ( rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1 )
            // InternalN4MFParser.g:2730:2: rule__ProjectDescription__Group_19__0__Impl rule__ProjectDescription__Group_19__1
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
    // InternalN4MFParser.g:2737:1: rule__ProjectDescription__Group_19__0__Impl : ( ModuleFilters ) ;
    public final void rule__ProjectDescription__Group_19__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2741:1: ( ( ModuleFilters ) )
            // InternalN4MFParser.g:2742:1: ( ModuleFilters )
            {
            // InternalN4MFParser.g:2742:1: ( ModuleFilters )
            // InternalN4MFParser.g:2743:1: ModuleFilters
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
    // InternalN4MFParser.g:2756:1: rule__ProjectDescription__Group_19__1 : rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2 ;
    public final void rule__ProjectDescription__Group_19__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2760:1: ( rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2 )
            // InternalN4MFParser.g:2761:2: rule__ProjectDescription__Group_19__1__Impl rule__ProjectDescription__Group_19__2
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
    // InternalN4MFParser.g:2768:1: rule__ProjectDescription__Group_19__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_19__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2772:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:2773:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:2773:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:2774:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:2787:1: rule__ProjectDescription__Group_19__2 : rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3 ;
    public final void rule__ProjectDescription__Group_19__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2791:1: ( rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3 )
            // InternalN4MFParser.g:2792:2: rule__ProjectDescription__Group_19__2__Impl rule__ProjectDescription__Group_19__3
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
    // InternalN4MFParser.g:2799:1: rule__ProjectDescription__Group_19__2__Impl : ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) ) ;
    public final void rule__ProjectDescription__Group_19__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2803:1: ( ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) ) )
            // InternalN4MFParser.g:2804:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) )
            {
            // InternalN4MFParser.g:2804:1: ( ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* ) )
            // InternalN4MFParser.g:2805:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) ) ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* )
            {
            // InternalN4MFParser.g:2805:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 ) )
            // InternalN4MFParser.g:2806:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 
            // InternalN4MFParser.g:2807:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )
            // InternalN4MFParser.g:2807:2: rule__ProjectDescription__ModuleFiltersAssignment_19_2
            {
            pushFollow(FOLLOW_15);
            rule__ProjectDescription__ModuleFiltersAssignment_19_2();

            state._fsp--;


            }

             after(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 

            }

            // InternalN4MFParser.g:2810:1: ( ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )* )
            // InternalN4MFParser.g:2811:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )*
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleFiltersAssignment_19_2()); 
            // InternalN4MFParser.g:2812:1: ( rule__ProjectDescription__ModuleFiltersAssignment_19_2 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==NoModuleWrap||LA16_0==NoValidate) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalN4MFParser.g:2812:2: rule__ProjectDescription__ModuleFiltersAssignment_19_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__ProjectDescription__ModuleFiltersAssignment_19_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // InternalN4MFParser.g:2823:1: rule__ProjectDescription__Group_19__3 : rule__ProjectDescription__Group_19__3__Impl ;
    public final void rule__ProjectDescription__Group_19__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2827:1: ( rule__ProjectDescription__Group_19__3__Impl )
            // InternalN4MFParser.g:2828:2: rule__ProjectDescription__Group_19__3__Impl
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
    // InternalN4MFParser.g:2834:1: rule__ProjectDescription__Group_19__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDescription__Group_19__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2838:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:2839:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:2839:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:2840:1: RightCurlyBracket
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
    // InternalN4MFParser.g:2861:1: rule__ProjectDescription__Group_21__0 : rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1 ;
    public final void rule__ProjectDescription__Group_21__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2865:1: ( rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1 )
            // InternalN4MFParser.g:2866:2: rule__ProjectDescription__Group_21__0__Impl rule__ProjectDescription__Group_21__1
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
    // InternalN4MFParser.g:2873:1: rule__ProjectDescription__Group_21__0__Impl : ( ModuleLoader ) ;
    public final void rule__ProjectDescription__Group_21__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2877:1: ( ( ModuleLoader ) )
            // InternalN4MFParser.g:2878:1: ( ModuleLoader )
            {
            // InternalN4MFParser.g:2878:1: ( ModuleLoader )
            // InternalN4MFParser.g:2879:1: ModuleLoader
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
    // InternalN4MFParser.g:2892:1: rule__ProjectDescription__Group_21__1 : rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2 ;
    public final void rule__ProjectDescription__Group_21__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2896:1: ( rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2 )
            // InternalN4MFParser.g:2897:2: rule__ProjectDescription__Group_21__1__Impl rule__ProjectDescription__Group_21__2
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
    // InternalN4MFParser.g:2904:1: rule__ProjectDescription__Group_21__1__Impl : ( Colon ) ;
    public final void rule__ProjectDescription__Group_21__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2908:1: ( ( Colon ) )
            // InternalN4MFParser.g:2909:1: ( Colon )
            {
            // InternalN4MFParser.g:2909:1: ( Colon )
            // InternalN4MFParser.g:2910:1: Colon
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
    // InternalN4MFParser.g:2923:1: rule__ProjectDescription__Group_21__2 : rule__ProjectDescription__Group_21__2__Impl ;
    public final void rule__ProjectDescription__Group_21__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2927:1: ( rule__ProjectDescription__Group_21__2__Impl )
            // InternalN4MFParser.g:2928:2: rule__ProjectDescription__Group_21__2__Impl
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
    // InternalN4MFParser.g:2934:1: rule__ProjectDescription__Group_21__2__Impl : ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) ) ;
    public final void rule__ProjectDescription__Group_21__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2938:1: ( ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) ) )
            // InternalN4MFParser.g:2939:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) )
            {
            // InternalN4MFParser.g:2939:1: ( ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 ) )
            // InternalN4MFParser.g:2940:1: ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 )
            {
             before(grammarAccess.getProjectDescriptionAccess().getModuleLoaderAssignment_21_2()); 
            // InternalN4MFParser.g:2941:1: ( rule__ProjectDescription__ModuleLoaderAssignment_21_2 )
            // InternalN4MFParser.g:2941:2: rule__ProjectDescription__ModuleLoaderAssignment_21_2
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
    // InternalN4MFParser.g:2957:1: rule__ExecModule__Group__0 : rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 ;
    public final void rule__ExecModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2961:1: ( rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1 )
            // InternalN4MFParser.g:2962:2: rule__ExecModule__Group__0__Impl rule__ExecModule__Group__1
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
    // InternalN4MFParser.g:2969:1: rule__ExecModule__Group__0__Impl : ( () ) ;
    public final void rule__ExecModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2973:1: ( ( () ) )
            // InternalN4MFParser.g:2974:1: ( () )
            {
            // InternalN4MFParser.g:2974:1: ( () )
            // InternalN4MFParser.g:2975:1: ()
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAction_0()); 
            // InternalN4MFParser.g:2976:1: ()
            // InternalN4MFParser.g:2978:1: 
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
    // InternalN4MFParser.g:2988:1: rule__ExecModule__Group__1 : rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 ;
    public final void rule__ExecModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:2992:1: ( rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2 )
            // InternalN4MFParser.g:2993:2: rule__ExecModule__Group__1__Impl rule__ExecModule__Group__2
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
    // InternalN4MFParser.g:3000:1: rule__ExecModule__Group__1__Impl : ( ExecModule ) ;
    public final void rule__ExecModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3004:1: ( ( ExecModule ) )
            // InternalN4MFParser.g:3005:1: ( ExecModule )
            {
            // InternalN4MFParser.g:3005:1: ( ExecModule )
            // InternalN4MFParser.g:3006:1: ExecModule
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
    // InternalN4MFParser.g:3019:1: rule__ExecModule__Group__2 : rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 ;
    public final void rule__ExecModule__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3023:1: ( rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3 )
            // InternalN4MFParser.g:3024:2: rule__ExecModule__Group__2__Impl rule__ExecModule__Group__3
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
    // InternalN4MFParser.g:3031:1: rule__ExecModule__Group__2__Impl : ( Colon ) ;
    public final void rule__ExecModule__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3035:1: ( ( Colon ) )
            // InternalN4MFParser.g:3036:1: ( Colon )
            {
            // InternalN4MFParser.g:3036:1: ( Colon )
            // InternalN4MFParser.g:3037:1: Colon
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
    // InternalN4MFParser.g:3050:1: rule__ExecModule__Group__3 : rule__ExecModule__Group__3__Impl ;
    public final void rule__ExecModule__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3054:1: ( rule__ExecModule__Group__3__Impl )
            // InternalN4MFParser.g:3055:2: rule__ExecModule__Group__3__Impl
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
    // InternalN4MFParser.g:3061:1: rule__ExecModule__Group__3__Impl : ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) ;
    public final void rule__ExecModule__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3065:1: ( ( ( rule__ExecModule__ExecModuleAssignment_3 ) ) )
            // InternalN4MFParser.g:3066:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            {
            // InternalN4MFParser.g:3066:1: ( ( rule__ExecModule__ExecModuleAssignment_3 ) )
            // InternalN4MFParser.g:3067:1: ( rule__ExecModule__ExecModuleAssignment_3 )
            {
             before(grammarAccess.getExecModuleAccess().getExecModuleAssignment_3()); 
            // InternalN4MFParser.g:3068:1: ( rule__ExecModule__ExecModuleAssignment_3 )
            // InternalN4MFParser.g:3068:2: rule__ExecModule__ExecModuleAssignment_3
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
    // InternalN4MFParser.g:3086:1: rule__TestedProjects__Group__0 : rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 ;
    public final void rule__TestedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3090:1: ( rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1 )
            // InternalN4MFParser.g:3091:2: rule__TestedProjects__Group__0__Impl rule__TestedProjects__Group__1
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
    // InternalN4MFParser.g:3098:1: rule__TestedProjects__Group__0__Impl : ( () ) ;
    public final void rule__TestedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3102:1: ( ( () ) )
            // InternalN4MFParser.g:3103:1: ( () )
            {
            // InternalN4MFParser.g:3103:1: ( () )
            // InternalN4MFParser.g:3104:1: ()
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0()); 
            // InternalN4MFParser.g:3105:1: ()
            // InternalN4MFParser.g:3107:1: 
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
    // InternalN4MFParser.g:3117:1: rule__TestedProjects__Group__1 : rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 ;
    public final void rule__TestedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3121:1: ( rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2 )
            // InternalN4MFParser.g:3122:2: rule__TestedProjects__Group__1__Impl rule__TestedProjects__Group__2
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
    // InternalN4MFParser.g:3129:1: rule__TestedProjects__Group__1__Impl : ( TestedProjects ) ;
    public final void rule__TestedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3133:1: ( ( TestedProjects ) )
            // InternalN4MFParser.g:3134:1: ( TestedProjects )
            {
            // InternalN4MFParser.g:3134:1: ( TestedProjects )
            // InternalN4MFParser.g:3135:1: TestedProjects
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
    // InternalN4MFParser.g:3148:1: rule__TestedProjects__Group__2 : rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 ;
    public final void rule__TestedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3152:1: ( rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3 )
            // InternalN4MFParser.g:3153:2: rule__TestedProjects__Group__2__Impl rule__TestedProjects__Group__3
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
    // InternalN4MFParser.g:3160:1: rule__TestedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__TestedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3164:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3165:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3165:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3166:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3179:1: rule__TestedProjects__Group__3 : rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 ;
    public final void rule__TestedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3183:1: ( rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4 )
            // InternalN4MFParser.g:3184:2: rule__TestedProjects__Group__3__Impl rule__TestedProjects__Group__4
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
    // InternalN4MFParser.g:3191:1: rule__TestedProjects__Group__3__Impl : ( ( rule__TestedProjects__Group_3__0 )? ) ;
    public final void rule__TestedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3195:1: ( ( ( rule__TestedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:3196:1: ( ( rule__TestedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3196:1: ( ( rule__TestedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:3197:1: ( rule__TestedProjects__Group_3__0 )?
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:3198:1: ( rule__TestedProjects__Group_3__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ProjectDependencies||LA17_0==ProjectVersion||LA17_0==ModuleFilters||(LA17_0>=ProjectName && LA17_0<=ArtifactId)||LA17_0==VendorName||(LA17_0>=Libraries && LA17_0<=VendorId)||LA17_0==Sources||LA17_0==Content||LA17_0==Output||(LA17_0>=Test && LA17_0<=API)||LA17_0==RULE_ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalN4MFParser.g:3198:2: rule__TestedProjects__Group_3__0
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
    // InternalN4MFParser.g:3208:1: rule__TestedProjects__Group__4 : rule__TestedProjects__Group__4__Impl ;
    public final void rule__TestedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3212:1: ( rule__TestedProjects__Group__4__Impl )
            // InternalN4MFParser.g:3213:2: rule__TestedProjects__Group__4__Impl
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
    // InternalN4MFParser.g:3219:1: rule__TestedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__TestedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3223:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3224:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3224:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3225:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3248:1: rule__TestedProjects__Group_3__0 : rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 ;
    public final void rule__TestedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3252:1: ( rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1 )
            // InternalN4MFParser.g:3253:2: rule__TestedProjects__Group_3__0__Impl rule__TestedProjects__Group_3__1
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
    // InternalN4MFParser.g:3260:1: rule__TestedProjects__Group_3__0__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) ;
    public final void rule__TestedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3264:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3265:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3265:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:3266:1: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:3267:1: ( rule__TestedProjects__TestedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:3267:2: rule__TestedProjects__TestedProjectsAssignment_3_0
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
    // InternalN4MFParser.g:3277:1: rule__TestedProjects__Group_3__1 : rule__TestedProjects__Group_3__1__Impl ;
    public final void rule__TestedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3281:1: ( rule__TestedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:3282:2: rule__TestedProjects__Group_3__1__Impl
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
    // InternalN4MFParser.g:3288:1: rule__TestedProjects__Group_3__1__Impl : ( ( rule__TestedProjects__Group_3_1__0 )* ) ;
    public final void rule__TestedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3292:1: ( ( ( rule__TestedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3293:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3293:1: ( ( rule__TestedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:3294:1: ( rule__TestedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getTestedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3295:1: ( rule__TestedProjects__Group_3_1__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==Comma) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalN4MFParser.g:3295:2: rule__TestedProjects__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__TestedProjects__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
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
    // InternalN4MFParser.g:3309:1: rule__TestedProjects__Group_3_1__0 : rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 ;
    public final void rule__TestedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3313:1: ( rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:3314:2: rule__TestedProjects__Group_3_1__0__Impl rule__TestedProjects__Group_3_1__1
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
    // InternalN4MFParser.g:3321:1: rule__TestedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__TestedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3325:1: ( ( Comma ) )
            // InternalN4MFParser.g:3326:1: ( Comma )
            {
            // InternalN4MFParser.g:3326:1: ( Comma )
            // InternalN4MFParser.g:3327:1: Comma
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
    // InternalN4MFParser.g:3340:1: rule__TestedProjects__Group_3_1__1 : rule__TestedProjects__Group_3_1__1__Impl ;
    public final void rule__TestedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3344:1: ( rule__TestedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3345:2: rule__TestedProjects__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3351:1: rule__TestedProjects__Group_3_1__1__Impl : ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__TestedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3355:1: ( ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3356:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3356:1: ( ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3357:1: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getTestedProjectsAccess().getTestedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:3358:1: ( rule__TestedProjects__TestedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:3358:2: rule__TestedProjects__TestedProjectsAssignment_3_1_1
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
    // InternalN4MFParser.g:3372:1: rule__InitModules__Group__0 : rule__InitModules__Group__0__Impl rule__InitModules__Group__1 ;
    public final void rule__InitModules__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3376:1: ( rule__InitModules__Group__0__Impl rule__InitModules__Group__1 )
            // InternalN4MFParser.g:3377:2: rule__InitModules__Group__0__Impl rule__InitModules__Group__1
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
    // InternalN4MFParser.g:3384:1: rule__InitModules__Group__0__Impl : ( () ) ;
    public final void rule__InitModules__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3388:1: ( ( () ) )
            // InternalN4MFParser.g:3389:1: ( () )
            {
            // InternalN4MFParser.g:3389:1: ( () )
            // InternalN4MFParser.g:3390:1: ()
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAction_0()); 
            // InternalN4MFParser.g:3391:1: ()
            // InternalN4MFParser.g:3393:1: 
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
    // InternalN4MFParser.g:3403:1: rule__InitModules__Group__1 : rule__InitModules__Group__1__Impl rule__InitModules__Group__2 ;
    public final void rule__InitModules__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3407:1: ( rule__InitModules__Group__1__Impl rule__InitModules__Group__2 )
            // InternalN4MFParser.g:3408:2: rule__InitModules__Group__1__Impl rule__InitModules__Group__2
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
    // InternalN4MFParser.g:3415:1: rule__InitModules__Group__1__Impl : ( InitModules ) ;
    public final void rule__InitModules__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3419:1: ( ( InitModules ) )
            // InternalN4MFParser.g:3420:1: ( InitModules )
            {
            // InternalN4MFParser.g:3420:1: ( InitModules )
            // InternalN4MFParser.g:3421:1: InitModules
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
    // InternalN4MFParser.g:3434:1: rule__InitModules__Group__2 : rule__InitModules__Group__2__Impl rule__InitModules__Group__3 ;
    public final void rule__InitModules__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3438:1: ( rule__InitModules__Group__2__Impl rule__InitModules__Group__3 )
            // InternalN4MFParser.g:3439:2: rule__InitModules__Group__2__Impl rule__InitModules__Group__3
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
    // InternalN4MFParser.g:3446:1: rule__InitModules__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__InitModules__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3450:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3451:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3451:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3452:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3465:1: rule__InitModules__Group__3 : rule__InitModules__Group__3__Impl rule__InitModules__Group__4 ;
    public final void rule__InitModules__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3469:1: ( rule__InitModules__Group__3__Impl rule__InitModules__Group__4 )
            // InternalN4MFParser.g:3470:2: rule__InitModules__Group__3__Impl rule__InitModules__Group__4
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
    // InternalN4MFParser.g:3477:1: rule__InitModules__Group__3__Impl : ( ( rule__InitModules__Group_3__0 )? ) ;
    public final void rule__InitModules__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3481:1: ( ( ( rule__InitModules__Group_3__0 )? ) )
            // InternalN4MFParser.g:3482:1: ( ( rule__InitModules__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3482:1: ( ( rule__InitModules__Group_3__0 )? )
            // InternalN4MFParser.g:3483:1: ( rule__InitModules__Group_3__0 )?
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3()); 
            // InternalN4MFParser.g:3484:1: ( rule__InitModules__Group_3__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_STRING) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalN4MFParser.g:3484:2: rule__InitModules__Group_3__0
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
    // InternalN4MFParser.g:3494:1: rule__InitModules__Group__4 : rule__InitModules__Group__4__Impl ;
    public final void rule__InitModules__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3498:1: ( rule__InitModules__Group__4__Impl )
            // InternalN4MFParser.g:3499:2: rule__InitModules__Group__4__Impl
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
    // InternalN4MFParser.g:3505:1: rule__InitModules__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__InitModules__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3509:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3510:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3510:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3511:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3534:1: rule__InitModules__Group_3__0 : rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 ;
    public final void rule__InitModules__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3538:1: ( rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1 )
            // InternalN4MFParser.g:3539:2: rule__InitModules__Group_3__0__Impl rule__InitModules__Group_3__1
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
    // InternalN4MFParser.g:3546:1: rule__InitModules__Group_3__0__Impl : ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) ;
    public final void rule__InitModules__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3550:1: ( ( ( rule__InitModules__InitModulesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3551:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3551:1: ( ( rule__InitModules__InitModulesAssignment_3_0 ) )
            // InternalN4MFParser.g:3552:1: ( rule__InitModules__InitModulesAssignment_3_0 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_0()); 
            // InternalN4MFParser.g:3553:1: ( rule__InitModules__InitModulesAssignment_3_0 )
            // InternalN4MFParser.g:3553:2: rule__InitModules__InitModulesAssignment_3_0
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
    // InternalN4MFParser.g:3563:1: rule__InitModules__Group_3__1 : rule__InitModules__Group_3__1__Impl ;
    public final void rule__InitModules__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3567:1: ( rule__InitModules__Group_3__1__Impl )
            // InternalN4MFParser.g:3568:2: rule__InitModules__Group_3__1__Impl
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
    // InternalN4MFParser.g:3574:1: rule__InitModules__Group_3__1__Impl : ( ( rule__InitModules__Group_3_1__0 )* ) ;
    public final void rule__InitModules__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3578:1: ( ( ( rule__InitModules__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3579:1: ( ( rule__InitModules__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3579:1: ( ( rule__InitModules__Group_3_1__0 )* )
            // InternalN4MFParser.g:3580:1: ( rule__InitModules__Group_3_1__0 )*
            {
             before(grammarAccess.getInitModulesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3581:1: ( rule__InitModules__Group_3_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==Comma) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalN4MFParser.g:3581:2: rule__InitModules__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__InitModules__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalN4MFParser.g:3595:1: rule__InitModules__Group_3_1__0 : rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 ;
    public final void rule__InitModules__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3599:1: ( rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1 )
            // InternalN4MFParser.g:3600:2: rule__InitModules__Group_3_1__0__Impl rule__InitModules__Group_3_1__1
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
    // InternalN4MFParser.g:3607:1: rule__InitModules__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__InitModules__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3611:1: ( ( Comma ) )
            // InternalN4MFParser.g:3612:1: ( Comma )
            {
            // InternalN4MFParser.g:3612:1: ( Comma )
            // InternalN4MFParser.g:3613:1: Comma
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
    // InternalN4MFParser.g:3626:1: rule__InitModules__Group_3_1__1 : rule__InitModules__Group_3_1__1__Impl ;
    public final void rule__InitModules__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3630:1: ( rule__InitModules__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3631:2: rule__InitModules__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3637:1: rule__InitModules__Group_3_1__1__Impl : ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) ;
    public final void rule__InitModules__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3641:1: ( ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3642:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3642:1: ( ( rule__InitModules__InitModulesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3643:1: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            {
             before(grammarAccess.getInitModulesAccess().getInitModulesAssignment_3_1_1()); 
            // InternalN4MFParser.g:3644:1: ( rule__InitModules__InitModulesAssignment_3_1_1 )
            // InternalN4MFParser.g:3644:2: rule__InitModules__InitModulesAssignment_3_1_1
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
    // InternalN4MFParser.g:3658:1: rule__ImplementedProjects__Group__0 : rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 ;
    public final void rule__ImplementedProjects__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3662:1: ( rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1 )
            // InternalN4MFParser.g:3663:2: rule__ImplementedProjects__Group__0__Impl rule__ImplementedProjects__Group__1
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
    // InternalN4MFParser.g:3670:1: rule__ImplementedProjects__Group__0__Impl : ( () ) ;
    public final void rule__ImplementedProjects__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3674:1: ( ( () ) )
            // InternalN4MFParser.g:3675:1: ( () )
            {
            // InternalN4MFParser.g:3675:1: ( () )
            // InternalN4MFParser.g:3676:1: ()
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0()); 
            // InternalN4MFParser.g:3677:1: ()
            // InternalN4MFParser.g:3679:1: 
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
    // InternalN4MFParser.g:3689:1: rule__ImplementedProjects__Group__1 : rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 ;
    public final void rule__ImplementedProjects__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3693:1: ( rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2 )
            // InternalN4MFParser.g:3694:2: rule__ImplementedProjects__Group__1__Impl rule__ImplementedProjects__Group__2
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
    // InternalN4MFParser.g:3701:1: rule__ImplementedProjects__Group__1__Impl : ( ImplementedProjects ) ;
    public final void rule__ImplementedProjects__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3705:1: ( ( ImplementedProjects ) )
            // InternalN4MFParser.g:3706:1: ( ImplementedProjects )
            {
            // InternalN4MFParser.g:3706:1: ( ImplementedProjects )
            // InternalN4MFParser.g:3707:1: ImplementedProjects
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
    // InternalN4MFParser.g:3720:1: rule__ImplementedProjects__Group__2 : rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 ;
    public final void rule__ImplementedProjects__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3724:1: ( rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3 )
            // InternalN4MFParser.g:3725:2: rule__ImplementedProjects__Group__2__Impl rule__ImplementedProjects__Group__3
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
    // InternalN4MFParser.g:3732:1: rule__ImplementedProjects__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3736:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:3737:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:3737:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:3738:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:3751:1: rule__ImplementedProjects__Group__3 : rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 ;
    public final void rule__ImplementedProjects__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3755:1: ( rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4 )
            // InternalN4MFParser.g:3756:2: rule__ImplementedProjects__Group__3__Impl rule__ImplementedProjects__Group__4
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
    // InternalN4MFParser.g:3763:1: rule__ImplementedProjects__Group__3__Impl : ( ( rule__ImplementedProjects__Group_3__0 )? ) ;
    public final void rule__ImplementedProjects__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3767:1: ( ( ( rule__ImplementedProjects__Group_3__0 )? ) )
            // InternalN4MFParser.g:3768:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            {
            // InternalN4MFParser.g:3768:1: ( ( rule__ImplementedProjects__Group_3__0 )? )
            // InternalN4MFParser.g:3769:1: ( rule__ImplementedProjects__Group_3__0 )?
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3()); 
            // InternalN4MFParser.g:3770:1: ( rule__ImplementedProjects__Group_3__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==ProjectDependencies||LA21_0==ProjectVersion||LA21_0==ModuleFilters||(LA21_0>=ProjectName && LA21_0<=ArtifactId)||LA21_0==VendorName||(LA21_0>=Libraries && LA21_0<=VendorId)||LA21_0==Sources||LA21_0==Content||LA21_0==Output||(LA21_0>=Test && LA21_0<=API)||LA21_0==RULE_ID) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalN4MFParser.g:3770:2: rule__ImplementedProjects__Group_3__0
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
    // InternalN4MFParser.g:3780:1: rule__ImplementedProjects__Group__4 : rule__ImplementedProjects__Group__4__Impl ;
    public final void rule__ImplementedProjects__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3784:1: ( rule__ImplementedProjects__Group__4__Impl )
            // InternalN4MFParser.g:3785:2: rule__ImplementedProjects__Group__4__Impl
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
    // InternalN4MFParser.g:3791:1: rule__ImplementedProjects__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ImplementedProjects__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3795:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:3796:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:3796:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:3797:1: RightCurlyBracket
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
    // InternalN4MFParser.g:3820:1: rule__ImplementedProjects__Group_3__0 : rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 ;
    public final void rule__ImplementedProjects__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3824:1: ( rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1 )
            // InternalN4MFParser.g:3825:2: rule__ImplementedProjects__Group_3__0__Impl rule__ImplementedProjects__Group_3__1
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
    // InternalN4MFParser.g:3832:1: rule__ImplementedProjects__Group_3__0__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) ;
    public final void rule__ImplementedProjects__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3836:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) ) )
            // InternalN4MFParser.g:3837:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:3837:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 ) )
            // InternalN4MFParser.g:3838:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_0()); 
            // InternalN4MFParser.g:3839:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 )
            // InternalN4MFParser.g:3839:2: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0
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
    // InternalN4MFParser.g:3849:1: rule__ImplementedProjects__Group_3__1 : rule__ImplementedProjects__Group_3__1__Impl ;
    public final void rule__ImplementedProjects__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3853:1: ( rule__ImplementedProjects__Group_3__1__Impl )
            // InternalN4MFParser.g:3854:2: rule__ImplementedProjects__Group_3__1__Impl
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
    // InternalN4MFParser.g:3860:1: rule__ImplementedProjects__Group_3__1__Impl : ( ( rule__ImplementedProjects__Group_3_1__0 )* ) ;
    public final void rule__ImplementedProjects__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3864:1: ( ( ( rule__ImplementedProjects__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:3865:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:3865:1: ( ( rule__ImplementedProjects__Group_3_1__0 )* )
            // InternalN4MFParser.g:3866:1: ( rule__ImplementedProjects__Group_3_1__0 )*
            {
             before(grammarAccess.getImplementedProjectsAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:3867:1: ( rule__ImplementedProjects__Group_3_1__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==Comma) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalN4MFParser.g:3867:2: rule__ImplementedProjects__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ImplementedProjects__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // InternalN4MFParser.g:3881:1: rule__ImplementedProjects__Group_3_1__0 : rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 ;
    public final void rule__ImplementedProjects__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3885:1: ( rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1 )
            // InternalN4MFParser.g:3886:2: rule__ImplementedProjects__Group_3_1__0__Impl rule__ImplementedProjects__Group_3_1__1
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
    // InternalN4MFParser.g:3893:1: rule__ImplementedProjects__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ImplementedProjects__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3897:1: ( ( Comma ) )
            // InternalN4MFParser.g:3898:1: ( Comma )
            {
            // InternalN4MFParser.g:3898:1: ( Comma )
            // InternalN4MFParser.g:3899:1: Comma
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
    // InternalN4MFParser.g:3912:1: rule__ImplementedProjects__Group_3_1__1 : rule__ImplementedProjects__Group_3_1__1__Impl ;
    public final void rule__ImplementedProjects__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3916:1: ( rule__ImplementedProjects__Group_3_1__1__Impl )
            // InternalN4MFParser.g:3917:2: rule__ImplementedProjects__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:3923:1: rule__ImplementedProjects__Group_3_1__1__Impl : ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) ;
    public final void rule__ImplementedProjects__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3927:1: ( ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:3928:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:3928:1: ( ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 ) )
            // InternalN4MFParser.g:3929:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            {
             before(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAssignment_3_1_1()); 
            // InternalN4MFParser.g:3930:1: ( rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 )
            // InternalN4MFParser.g:3930:2: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1
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
    // InternalN4MFParser.g:3944:1: rule__ProjectDependencies__Group__0 : rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 ;
    public final void rule__ProjectDependencies__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3948:1: ( rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1 )
            // InternalN4MFParser.g:3949:2: rule__ProjectDependencies__Group__0__Impl rule__ProjectDependencies__Group__1
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
    // InternalN4MFParser.g:3956:1: rule__ProjectDependencies__Group__0__Impl : ( () ) ;
    public final void rule__ProjectDependencies__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3960:1: ( ( () ) )
            // InternalN4MFParser.g:3961:1: ( () )
            {
            // InternalN4MFParser.g:3961:1: ( () )
            // InternalN4MFParser.g:3962:1: ()
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0()); 
            // InternalN4MFParser.g:3963:1: ()
            // InternalN4MFParser.g:3965:1: 
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
    // InternalN4MFParser.g:3975:1: rule__ProjectDependencies__Group__1 : rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 ;
    public final void rule__ProjectDependencies__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3979:1: ( rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2 )
            // InternalN4MFParser.g:3980:2: rule__ProjectDependencies__Group__1__Impl rule__ProjectDependencies__Group__2
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
    // InternalN4MFParser.g:3987:1: rule__ProjectDependencies__Group__1__Impl : ( ProjectDependencies ) ;
    public final void rule__ProjectDependencies__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:3991:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:3992:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:3992:1: ( ProjectDependencies )
            // InternalN4MFParser.g:3993:1: ProjectDependencies
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
    // InternalN4MFParser.g:4006:1: rule__ProjectDependencies__Group__2 : rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 ;
    public final void rule__ProjectDependencies__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4010:1: ( rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3 )
            // InternalN4MFParser.g:4011:2: rule__ProjectDependencies__Group__2__Impl rule__ProjectDependencies__Group__3
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
    // InternalN4MFParser.g:4018:1: rule__ProjectDependencies__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4022:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4023:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4023:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4024:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4037:1: rule__ProjectDependencies__Group__3 : rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 ;
    public final void rule__ProjectDependencies__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4041:1: ( rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4 )
            // InternalN4MFParser.g:4042:2: rule__ProjectDependencies__Group__3__Impl rule__ProjectDependencies__Group__4
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
    // InternalN4MFParser.g:4049:1: rule__ProjectDependencies__Group__3__Impl : ( ( rule__ProjectDependencies__Group_3__0 )? ) ;
    public final void rule__ProjectDependencies__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4053:1: ( ( ( rule__ProjectDependencies__Group_3__0 )? ) )
            // InternalN4MFParser.g:4054:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4054:1: ( ( rule__ProjectDependencies__Group_3__0 )? )
            // InternalN4MFParser.g:4055:1: ( rule__ProjectDependencies__Group_3__0 )?
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4056:1: ( rule__ProjectDependencies__Group_3__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==ProjectDependencies||LA23_0==ProjectVersion||LA23_0==ModuleFilters||(LA23_0>=ProjectName && LA23_0<=ArtifactId)||LA23_0==VendorName||(LA23_0>=Libraries && LA23_0<=VendorId)||LA23_0==Sources||LA23_0==Content||LA23_0==Output||(LA23_0>=Test && LA23_0<=API)||LA23_0==RULE_ID) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalN4MFParser.g:4056:2: rule__ProjectDependencies__Group_3__0
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
    // InternalN4MFParser.g:4066:1: rule__ProjectDependencies__Group__4 : rule__ProjectDependencies__Group__4__Impl ;
    public final void rule__ProjectDependencies__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4070:1: ( rule__ProjectDependencies__Group__4__Impl )
            // InternalN4MFParser.g:4071:2: rule__ProjectDependencies__Group__4__Impl
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
    // InternalN4MFParser.g:4077:1: rule__ProjectDependencies__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProjectDependencies__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4081:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4082:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4082:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4083:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4106:1: rule__ProjectDependencies__Group_3__0 : rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 ;
    public final void rule__ProjectDependencies__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4110:1: ( rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1 )
            // InternalN4MFParser.g:4111:2: rule__ProjectDependencies__Group_3__0__Impl rule__ProjectDependencies__Group_3__1
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
    // InternalN4MFParser.g:4118:1: rule__ProjectDependencies__Group_3__0__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) ;
    public final void rule__ProjectDependencies__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4122:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4123:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4123:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 ) )
            // InternalN4MFParser.g:4124:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_0()); 
            // InternalN4MFParser.g:4125:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 )
            // InternalN4MFParser.g:4125:2: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0
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
    // InternalN4MFParser.g:4135:1: rule__ProjectDependencies__Group_3__1 : rule__ProjectDependencies__Group_3__1__Impl ;
    public final void rule__ProjectDependencies__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4139:1: ( rule__ProjectDependencies__Group_3__1__Impl )
            // InternalN4MFParser.g:4140:2: rule__ProjectDependencies__Group_3__1__Impl
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
    // InternalN4MFParser.g:4146:1: rule__ProjectDependencies__Group_3__1__Impl : ( ( rule__ProjectDependencies__Group_3_1__0 )* ) ;
    public final void rule__ProjectDependencies__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4150:1: ( ( ( rule__ProjectDependencies__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4151:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4151:1: ( ( rule__ProjectDependencies__Group_3_1__0 )* )
            // InternalN4MFParser.g:4152:1: ( rule__ProjectDependencies__Group_3_1__0 )*
            {
             before(grammarAccess.getProjectDependenciesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4153:1: ( rule__ProjectDependencies__Group_3_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==Comma) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalN4MFParser.g:4153:2: rule__ProjectDependencies__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProjectDependencies__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // InternalN4MFParser.g:4167:1: rule__ProjectDependencies__Group_3_1__0 : rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 ;
    public final void rule__ProjectDependencies__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4171:1: ( rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1 )
            // InternalN4MFParser.g:4172:2: rule__ProjectDependencies__Group_3_1__0__Impl rule__ProjectDependencies__Group_3_1__1
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
    // InternalN4MFParser.g:4179:1: rule__ProjectDependencies__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProjectDependencies__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4183:1: ( ( Comma ) )
            // InternalN4MFParser.g:4184:1: ( Comma )
            {
            // InternalN4MFParser.g:4184:1: ( Comma )
            // InternalN4MFParser.g:4185:1: Comma
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
    // InternalN4MFParser.g:4198:1: rule__ProjectDependencies__Group_3_1__1 : rule__ProjectDependencies__Group_3_1__1__Impl ;
    public final void rule__ProjectDependencies__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4202:1: ( rule__ProjectDependencies__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4203:2: rule__ProjectDependencies__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4209:1: rule__ProjectDependencies__Group_3_1__1__Impl : ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) ;
    public final void rule__ProjectDependencies__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4213:1: ( ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4214:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4214:1: ( ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4215:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            {
             before(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4216:1: ( rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 )
            // InternalN4MFParser.g:4216:2: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1
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
    // InternalN4MFParser.g:4230:1: rule__ProvidedRuntimeLibraries__Group__0 : rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4234:1: ( rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:4235:2: rule__ProvidedRuntimeLibraries__Group__0__Impl rule__ProvidedRuntimeLibraries__Group__1
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
    // InternalN4MFParser.g:4242:1: rule__ProvidedRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4246:1: ( ( () ) )
            // InternalN4MFParser.g:4247:1: ( () )
            {
            // InternalN4MFParser.g:4247:1: ( () )
            // InternalN4MFParser.g:4248:1: ()
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:4249:1: ()
            // InternalN4MFParser.g:4251:1: 
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
    // InternalN4MFParser.g:4261:1: rule__ProvidedRuntimeLibraries__Group__1 : rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 ;
    public final void rule__ProvidedRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4265:1: ( rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:4266:2: rule__ProvidedRuntimeLibraries__Group__1__Impl rule__ProvidedRuntimeLibraries__Group__2
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
    // InternalN4MFParser.g:4273:1: rule__ProvidedRuntimeLibraries__Group__1__Impl : ( ProvidedRuntimeLibraries ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4277:1: ( ( ProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:4278:1: ( ProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:4278:1: ( ProvidedRuntimeLibraries )
            // InternalN4MFParser.g:4279:1: ProvidedRuntimeLibraries
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
    // InternalN4MFParser.g:4292:1: rule__ProvidedRuntimeLibraries__Group__2 : rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 ;
    public final void rule__ProvidedRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4296:1: ( rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:4297:2: rule__ProvidedRuntimeLibraries__Group__2__Impl rule__ProvidedRuntimeLibraries__Group__3
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
    // InternalN4MFParser.g:4304:1: rule__ProvidedRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4308:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4309:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4309:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4310:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4323:1: rule__ProvidedRuntimeLibraries__Group__3 : rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 ;
    public final void rule__ProvidedRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4327:1: ( rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:4328:2: rule__ProvidedRuntimeLibraries__Group__3__Impl rule__ProvidedRuntimeLibraries__Group__4
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
    // InternalN4MFParser.g:4335:1: rule__ProvidedRuntimeLibraries__Group__3__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4339:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:4340:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4340:1: ( ( rule__ProvidedRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:4341:1: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4342:1: ( rule__ProvidedRuntimeLibraries__Group_3__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==ProjectDependencies||LA25_0==ProjectVersion||LA25_0==ModuleFilters||(LA25_0>=ProjectName && LA25_0<=ArtifactId)||LA25_0==VendorName||(LA25_0>=Libraries && LA25_0<=VendorId)||LA25_0==Sources||LA25_0==Content||LA25_0==Output||(LA25_0>=Test && LA25_0<=API)||LA25_0==RULE_ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalN4MFParser.g:4342:2: rule__ProvidedRuntimeLibraries__Group_3__0
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
    // InternalN4MFParser.g:4352:1: rule__ProvidedRuntimeLibraries__Group__4 : rule__ProvidedRuntimeLibraries__Group__4__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4356:1: ( rule__ProvidedRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:4357:2: rule__ProvidedRuntimeLibraries__Group__4__Impl
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
    // InternalN4MFParser.g:4363:1: rule__ProvidedRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ProvidedRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4367:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4368:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4368:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4369:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4392:1: rule__ProvidedRuntimeLibraries__Group_3__0 : rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4396:1: ( rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:4397:2: rule__ProvidedRuntimeLibraries__Group_3__0__Impl rule__ProvidedRuntimeLibraries__Group_3__1
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
    // InternalN4MFParser.g:4404:1: rule__ProvidedRuntimeLibraries__Group_3__0__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4408:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4409:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4409:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:4410:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:4411:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:4411:2: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0
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
    // InternalN4MFParser.g:4421:1: rule__ProvidedRuntimeLibraries__Group_3__1 : rule__ProvidedRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4425:1: ( rule__ProvidedRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:4426:2: rule__ProvidedRuntimeLibraries__Group_3__1__Impl
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
    // InternalN4MFParser.g:4432:1: rule__ProvidedRuntimeLibraries__Group_3__1__Impl : ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4436:1: ( ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4437:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4437:1: ( ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:4438:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4439:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==Comma) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalN4MFParser.g:4439:2: rule__ProvidedRuntimeLibraries__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ProvidedRuntimeLibraries__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
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
    // InternalN4MFParser.g:4453:1: rule__ProvidedRuntimeLibraries__Group_3_1__0 : rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4457:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:4458:2: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl rule__ProvidedRuntimeLibraries__Group_3_1__1
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
    // InternalN4MFParser.g:4465:1: rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4469:1: ( ( Comma ) )
            // InternalN4MFParser.g:4470:1: ( Comma )
            {
            // InternalN4MFParser.g:4470:1: ( Comma )
            // InternalN4MFParser.g:4471:1: Comma
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
    // InternalN4MFParser.g:4484:1: rule__ProvidedRuntimeLibraries__Group_3_1__1 : rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4488:1: ( rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4489:2: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4495:1: rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__ProvidedRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4499:1: ( ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4500:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4500:1: ( ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4501:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4502:1: ( rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:4502:2: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1
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
    // InternalN4MFParser.g:4516:1: rule__RequiredRuntimeLibraries__Group__0 : rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 ;
    public final void rule__RequiredRuntimeLibraries__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4520:1: ( rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1 )
            // InternalN4MFParser.g:4521:2: rule__RequiredRuntimeLibraries__Group__0__Impl rule__RequiredRuntimeLibraries__Group__1
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
    // InternalN4MFParser.g:4528:1: rule__RequiredRuntimeLibraries__Group__0__Impl : ( () ) ;
    public final void rule__RequiredRuntimeLibraries__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4532:1: ( ( () ) )
            // InternalN4MFParser.g:4533:1: ( () )
            {
            // InternalN4MFParser.g:4533:1: ( () )
            // InternalN4MFParser.g:4534:1: ()
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0()); 
            // InternalN4MFParser.g:4535:1: ()
            // InternalN4MFParser.g:4537:1: 
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
    // InternalN4MFParser.g:4547:1: rule__RequiredRuntimeLibraries__Group__1 : rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 ;
    public final void rule__RequiredRuntimeLibraries__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4551:1: ( rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2 )
            // InternalN4MFParser.g:4552:2: rule__RequiredRuntimeLibraries__Group__1__Impl rule__RequiredRuntimeLibraries__Group__2
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
    // InternalN4MFParser.g:4559:1: rule__RequiredRuntimeLibraries__Group__1__Impl : ( RequiredRuntimeLibraries ) ;
    public final void rule__RequiredRuntimeLibraries__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4563:1: ( ( RequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:4564:1: ( RequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:4564:1: ( RequiredRuntimeLibraries )
            // InternalN4MFParser.g:4565:1: RequiredRuntimeLibraries
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
    // InternalN4MFParser.g:4578:1: rule__RequiredRuntimeLibraries__Group__2 : rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 ;
    public final void rule__RequiredRuntimeLibraries__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4582:1: ( rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3 )
            // InternalN4MFParser.g:4583:2: rule__RequiredRuntimeLibraries__Group__2__Impl rule__RequiredRuntimeLibraries__Group__3
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
    // InternalN4MFParser.g:4590:1: rule__RequiredRuntimeLibraries__Group__2__Impl : ( LeftCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4594:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:4595:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:4595:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:4596:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:4609:1: rule__RequiredRuntimeLibraries__Group__3 : rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 ;
    public final void rule__RequiredRuntimeLibraries__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4613:1: ( rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4 )
            // InternalN4MFParser.g:4614:2: rule__RequiredRuntimeLibraries__Group__3__Impl rule__RequiredRuntimeLibraries__Group__4
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
    // InternalN4MFParser.g:4621:1: rule__RequiredRuntimeLibraries__Group__3__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) ;
    public final void rule__RequiredRuntimeLibraries__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4625:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? ) )
            // InternalN4MFParser.g:4626:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            {
            // InternalN4MFParser.g:4626:1: ( ( rule__RequiredRuntimeLibraries__Group_3__0 )? )
            // InternalN4MFParser.g:4627:1: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3()); 
            // InternalN4MFParser.g:4628:1: ( rule__RequiredRuntimeLibraries__Group_3__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ProjectDependencies||LA27_0==ProjectVersion||LA27_0==ModuleFilters||(LA27_0>=ProjectName && LA27_0<=ArtifactId)||LA27_0==VendorName||(LA27_0>=Libraries && LA27_0<=VendorId)||LA27_0==Sources||LA27_0==Content||LA27_0==Output||(LA27_0>=Test && LA27_0<=API)||LA27_0==RULE_ID) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalN4MFParser.g:4628:2: rule__RequiredRuntimeLibraries__Group_3__0
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
    // InternalN4MFParser.g:4638:1: rule__RequiredRuntimeLibraries__Group__4 : rule__RequiredRuntimeLibraries__Group__4__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4642:1: ( rule__RequiredRuntimeLibraries__Group__4__Impl )
            // InternalN4MFParser.g:4643:2: rule__RequiredRuntimeLibraries__Group__4__Impl
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
    // InternalN4MFParser.g:4649:1: rule__RequiredRuntimeLibraries__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__RequiredRuntimeLibraries__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4653:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:4654:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:4654:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:4655:1: RightCurlyBracket
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
    // InternalN4MFParser.g:4678:1: rule__RequiredRuntimeLibraries__Group_3__0 : rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4682:1: ( rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1 )
            // InternalN4MFParser.g:4683:2: rule__RequiredRuntimeLibraries__Group_3__0__Impl rule__RequiredRuntimeLibraries__Group_3__1
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
    // InternalN4MFParser.g:4690:1: rule__RequiredRuntimeLibraries__Group_3__0__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4694:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) ) )
            // InternalN4MFParser.g:4695:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            {
            // InternalN4MFParser.g:4695:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 ) )
            // InternalN4MFParser.g:4696:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_0()); 
            // InternalN4MFParser.g:4697:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 )
            // InternalN4MFParser.g:4697:2: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0
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
    // InternalN4MFParser.g:4707:1: rule__RequiredRuntimeLibraries__Group_3__1 : rule__RequiredRuntimeLibraries__Group_3__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4711:1: ( rule__RequiredRuntimeLibraries__Group_3__1__Impl )
            // InternalN4MFParser.g:4712:2: rule__RequiredRuntimeLibraries__Group_3__1__Impl
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
    // InternalN4MFParser.g:4718:1: rule__RequiredRuntimeLibraries__Group_3__1__Impl : ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4722:1: ( ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* ) )
            // InternalN4MFParser.g:4723:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            {
            // InternalN4MFParser.g:4723:1: ( ( rule__RequiredRuntimeLibraries__Group_3_1__0 )* )
            // InternalN4MFParser.g:4724:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getGroup_3_1()); 
            // InternalN4MFParser.g:4725:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==Comma) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalN4MFParser.g:4725:2: rule__RequiredRuntimeLibraries__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__RequiredRuntimeLibraries__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
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
    // InternalN4MFParser.g:4739:1: rule__RequiredRuntimeLibraries__Group_3_1__0 : rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4743:1: ( rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1 )
            // InternalN4MFParser.g:4744:2: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl rule__RequiredRuntimeLibraries__Group_3_1__1
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
    // InternalN4MFParser.g:4751:1: rule__RequiredRuntimeLibraries__Group_3_1__0__Impl : ( Comma ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4755:1: ( ( Comma ) )
            // InternalN4MFParser.g:4756:1: ( Comma )
            {
            // InternalN4MFParser.g:4756:1: ( Comma )
            // InternalN4MFParser.g:4757:1: Comma
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
    // InternalN4MFParser.g:4770:1: rule__RequiredRuntimeLibraries__Group_3_1__1 : rule__RequiredRuntimeLibraries__Group_3_1__1__Impl ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4774:1: ( rule__RequiredRuntimeLibraries__Group_3_1__1__Impl )
            // InternalN4MFParser.g:4775:2: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl
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
    // InternalN4MFParser.g:4781:1: rule__RequiredRuntimeLibraries__Group_3_1__1__Impl : ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) ;
    public final void rule__RequiredRuntimeLibraries__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4785:1: ( ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) ) )
            // InternalN4MFParser.g:4786:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            {
            // InternalN4MFParser.g:4786:1: ( ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 ) )
            // InternalN4MFParser.g:4787:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            {
             before(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAssignment_3_1_1()); 
            // InternalN4MFParser.g:4788:1: ( rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 )
            // InternalN4MFParser.g:4788:2: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1
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
    // InternalN4MFParser.g:4802:1: rule__ExtendedRuntimeEnvironment__Group__0 : rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4806:1: ( rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1 )
            // InternalN4MFParser.g:4807:2: rule__ExtendedRuntimeEnvironment__Group__0__Impl rule__ExtendedRuntimeEnvironment__Group__1
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
    // InternalN4MFParser.g:4814:1: rule__ExtendedRuntimeEnvironment__Group__0__Impl : ( () ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4818:1: ( ( () ) )
            // InternalN4MFParser.g:4819:1: ( () )
            {
            // InternalN4MFParser.g:4819:1: ( () )
            // InternalN4MFParser.g:4820:1: ()
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0()); 
            // InternalN4MFParser.g:4821:1: ()
            // InternalN4MFParser.g:4823:1: 
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
    // InternalN4MFParser.g:4833:1: rule__ExtendedRuntimeEnvironment__Group__1 : rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4837:1: ( rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2 )
            // InternalN4MFParser.g:4838:2: rule__ExtendedRuntimeEnvironment__Group__1__Impl rule__ExtendedRuntimeEnvironment__Group__2
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
    // InternalN4MFParser.g:4845:1: rule__ExtendedRuntimeEnvironment__Group__1__Impl : ( ExtendedRuntimeEnvironment ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4849:1: ( ( ExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:4850:1: ( ExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:4850:1: ( ExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:4851:1: ExtendedRuntimeEnvironment
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
    // InternalN4MFParser.g:4864:1: rule__ExtendedRuntimeEnvironment__Group__2 : rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4868:1: ( rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3 )
            // InternalN4MFParser.g:4869:2: rule__ExtendedRuntimeEnvironment__Group__2__Impl rule__ExtendedRuntimeEnvironment__Group__3
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
    // InternalN4MFParser.g:4876:1: rule__ExtendedRuntimeEnvironment__Group__2__Impl : ( Colon ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4880:1: ( ( Colon ) )
            // InternalN4MFParser.g:4881:1: ( Colon )
            {
            // InternalN4MFParser.g:4881:1: ( Colon )
            // InternalN4MFParser.g:4882:1: Colon
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
    // InternalN4MFParser.g:4895:1: rule__ExtendedRuntimeEnvironment__Group__3 : rule__ExtendedRuntimeEnvironment__Group__3__Impl ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4899:1: ( rule__ExtendedRuntimeEnvironment__Group__3__Impl )
            // InternalN4MFParser.g:4900:2: rule__ExtendedRuntimeEnvironment__Group__3__Impl
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
    // InternalN4MFParser.g:4906:1: rule__ExtendedRuntimeEnvironment__Group__3__Impl : ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) ;
    public final void rule__ExtendedRuntimeEnvironment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4910:1: ( ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) ) )
            // InternalN4MFParser.g:4911:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            {
            // InternalN4MFParser.g:4911:1: ( ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 ) )
            // InternalN4MFParser.g:4912:1: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            {
             before(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAssignment_3()); 
            // InternalN4MFParser.g:4913:1: ( rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 )
            // InternalN4MFParser.g:4913:2: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3
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
    // InternalN4MFParser.g:4931:1: rule__DeclaredVersion__Group__0 : rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 ;
    public final void rule__DeclaredVersion__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4935:1: ( rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1 )
            // InternalN4MFParser.g:4936:2: rule__DeclaredVersion__Group__0__Impl rule__DeclaredVersion__Group__1
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
    // InternalN4MFParser.g:4943:1: rule__DeclaredVersion__Group__0__Impl : ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) ;
    public final void rule__DeclaredVersion__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4947:1: ( ( ( rule__DeclaredVersion__MajorAssignment_0 ) ) )
            // InternalN4MFParser.g:4948:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            {
            // InternalN4MFParser.g:4948:1: ( ( rule__DeclaredVersion__MajorAssignment_0 ) )
            // InternalN4MFParser.g:4949:1: ( rule__DeclaredVersion__MajorAssignment_0 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMajorAssignment_0()); 
            // InternalN4MFParser.g:4950:1: ( rule__DeclaredVersion__MajorAssignment_0 )
            // InternalN4MFParser.g:4950:2: rule__DeclaredVersion__MajorAssignment_0
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
    // InternalN4MFParser.g:4960:1: rule__DeclaredVersion__Group__1 : rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 ;
    public final void rule__DeclaredVersion__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4964:1: ( rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2 )
            // InternalN4MFParser.g:4965:2: rule__DeclaredVersion__Group__1__Impl rule__DeclaredVersion__Group__2
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
    // InternalN4MFParser.g:4972:1: rule__DeclaredVersion__Group__1__Impl : ( ( rule__DeclaredVersion__Group_1__0 )? ) ;
    public final void rule__DeclaredVersion__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4976:1: ( ( ( rule__DeclaredVersion__Group_1__0 )? ) )
            // InternalN4MFParser.g:4977:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            {
            // InternalN4MFParser.g:4977:1: ( ( rule__DeclaredVersion__Group_1__0 )? )
            // InternalN4MFParser.g:4978:1: ( rule__DeclaredVersion__Group_1__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1()); 
            // InternalN4MFParser.g:4979:1: ( rule__DeclaredVersion__Group_1__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==FullStop) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalN4MFParser.g:4979:2: rule__DeclaredVersion__Group_1__0
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
    // InternalN4MFParser.g:4989:1: rule__DeclaredVersion__Group__2 : rule__DeclaredVersion__Group__2__Impl ;
    public final void rule__DeclaredVersion__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:4993:1: ( rule__DeclaredVersion__Group__2__Impl )
            // InternalN4MFParser.g:4994:2: rule__DeclaredVersion__Group__2__Impl
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
    // InternalN4MFParser.g:5000:1: rule__DeclaredVersion__Group__2__Impl : ( ( rule__DeclaredVersion__Group_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5004:1: ( ( ( rule__DeclaredVersion__Group_2__0 )? ) )
            // InternalN4MFParser.g:5005:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            {
            // InternalN4MFParser.g:5005:1: ( ( rule__DeclaredVersion__Group_2__0 )? )
            // InternalN4MFParser.g:5006:1: ( rule__DeclaredVersion__Group_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_2()); 
            // InternalN4MFParser.g:5007:1: ( rule__DeclaredVersion__Group_2__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==HyphenMinus) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalN4MFParser.g:5007:2: rule__DeclaredVersion__Group_2__0
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
    // InternalN4MFParser.g:5023:1: rule__DeclaredVersion__Group_1__0 : rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 ;
    public final void rule__DeclaredVersion__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5027:1: ( rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1 )
            // InternalN4MFParser.g:5028:2: rule__DeclaredVersion__Group_1__0__Impl rule__DeclaredVersion__Group_1__1
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
    // InternalN4MFParser.g:5035:1: rule__DeclaredVersion__Group_1__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5039:1: ( ( FullStop ) )
            // InternalN4MFParser.g:5040:1: ( FullStop )
            {
            // InternalN4MFParser.g:5040:1: ( FullStop )
            // InternalN4MFParser.g:5041:1: FullStop
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
    // InternalN4MFParser.g:5054:1: rule__DeclaredVersion__Group_1__1 : rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 ;
    public final void rule__DeclaredVersion__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5058:1: ( rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2 )
            // InternalN4MFParser.g:5059:2: rule__DeclaredVersion__Group_1__1__Impl rule__DeclaredVersion__Group_1__2
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
    // InternalN4MFParser.g:5066:1: rule__DeclaredVersion__Group_1__1__Impl : ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5070:1: ( ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5071:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5071:1: ( ( rule__DeclaredVersion__MinorAssignment_1_1 ) )
            // InternalN4MFParser.g:5072:1: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMinorAssignment_1_1()); 
            // InternalN4MFParser.g:5073:1: ( rule__DeclaredVersion__MinorAssignment_1_1 )
            // InternalN4MFParser.g:5073:2: rule__DeclaredVersion__MinorAssignment_1_1
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
    // InternalN4MFParser.g:5083:1: rule__DeclaredVersion__Group_1__2 : rule__DeclaredVersion__Group_1__2__Impl ;
    public final void rule__DeclaredVersion__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5087:1: ( rule__DeclaredVersion__Group_1__2__Impl )
            // InternalN4MFParser.g:5088:2: rule__DeclaredVersion__Group_1__2__Impl
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
    // InternalN4MFParser.g:5094:1: rule__DeclaredVersion__Group_1__2__Impl : ( ( rule__DeclaredVersion__Group_1_2__0 )? ) ;
    public final void rule__DeclaredVersion__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5098:1: ( ( ( rule__DeclaredVersion__Group_1_2__0 )? ) )
            // InternalN4MFParser.g:5099:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            {
            // InternalN4MFParser.g:5099:1: ( ( rule__DeclaredVersion__Group_1_2__0 )? )
            // InternalN4MFParser.g:5100:1: ( rule__DeclaredVersion__Group_1_2__0 )?
            {
             before(grammarAccess.getDeclaredVersionAccess().getGroup_1_2()); 
            // InternalN4MFParser.g:5101:1: ( rule__DeclaredVersion__Group_1_2__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==FullStop) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalN4MFParser.g:5101:2: rule__DeclaredVersion__Group_1_2__0
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
    // InternalN4MFParser.g:5117:1: rule__DeclaredVersion__Group_1_2__0 : rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 ;
    public final void rule__DeclaredVersion__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5121:1: ( rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1 )
            // InternalN4MFParser.g:5122:2: rule__DeclaredVersion__Group_1_2__0__Impl rule__DeclaredVersion__Group_1_2__1
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
    // InternalN4MFParser.g:5129:1: rule__DeclaredVersion__Group_1_2__0__Impl : ( FullStop ) ;
    public final void rule__DeclaredVersion__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5133:1: ( ( FullStop ) )
            // InternalN4MFParser.g:5134:1: ( FullStop )
            {
            // InternalN4MFParser.g:5134:1: ( FullStop )
            // InternalN4MFParser.g:5135:1: FullStop
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
    // InternalN4MFParser.g:5148:1: rule__DeclaredVersion__Group_1_2__1 : rule__DeclaredVersion__Group_1_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5152:1: ( rule__DeclaredVersion__Group_1_2__1__Impl )
            // InternalN4MFParser.g:5153:2: rule__DeclaredVersion__Group_1_2__1__Impl
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
    // InternalN4MFParser.g:5159:1: rule__DeclaredVersion__Group_1_2__1__Impl : ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5163:1: ( ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) ) )
            // InternalN4MFParser.g:5164:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            {
            // InternalN4MFParser.g:5164:1: ( ( rule__DeclaredVersion__MicroAssignment_1_2_1 ) )
            // InternalN4MFParser.g:5165:1: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getMicroAssignment_1_2_1()); 
            // InternalN4MFParser.g:5166:1: ( rule__DeclaredVersion__MicroAssignment_1_2_1 )
            // InternalN4MFParser.g:5166:2: rule__DeclaredVersion__MicroAssignment_1_2_1
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
    // InternalN4MFParser.g:5180:1: rule__DeclaredVersion__Group_2__0 : rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 ;
    public final void rule__DeclaredVersion__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5184:1: ( rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1 )
            // InternalN4MFParser.g:5185:2: rule__DeclaredVersion__Group_2__0__Impl rule__DeclaredVersion__Group_2__1
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
    // InternalN4MFParser.g:5192:1: rule__DeclaredVersion__Group_2__0__Impl : ( HyphenMinus ) ;
    public final void rule__DeclaredVersion__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5196:1: ( ( HyphenMinus ) )
            // InternalN4MFParser.g:5197:1: ( HyphenMinus )
            {
            // InternalN4MFParser.g:5197:1: ( HyphenMinus )
            // InternalN4MFParser.g:5198:1: HyphenMinus
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
    // InternalN4MFParser.g:5211:1: rule__DeclaredVersion__Group_2__1 : rule__DeclaredVersion__Group_2__1__Impl ;
    public final void rule__DeclaredVersion__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5215:1: ( rule__DeclaredVersion__Group_2__1__Impl )
            // InternalN4MFParser.g:5216:2: rule__DeclaredVersion__Group_2__1__Impl
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
    // InternalN4MFParser.g:5222:1: rule__DeclaredVersion__Group_2__1__Impl : ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) ;
    public final void rule__DeclaredVersion__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5226:1: ( ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) ) )
            // InternalN4MFParser.g:5227:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            {
            // InternalN4MFParser.g:5227:1: ( ( rule__DeclaredVersion__QualifierAssignment_2_1 ) )
            // InternalN4MFParser.g:5228:1: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            {
             before(grammarAccess.getDeclaredVersionAccess().getQualifierAssignment_2_1()); 
            // InternalN4MFParser.g:5229:1: ( rule__DeclaredVersion__QualifierAssignment_2_1 )
            // InternalN4MFParser.g:5229:2: rule__DeclaredVersion__QualifierAssignment_2_1
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
    // InternalN4MFParser.g:5243:1: rule__SourceFragment__Group__0 : rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 ;
    public final void rule__SourceFragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5247:1: ( rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1 )
            // InternalN4MFParser.g:5248:2: rule__SourceFragment__Group__0__Impl rule__SourceFragment__Group__1
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
    // InternalN4MFParser.g:5255:1: rule__SourceFragment__Group__0__Impl : ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) ;
    public final void rule__SourceFragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5259:1: ( ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:5260:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:5260:1: ( ( rule__SourceFragment__SourceFragmentTypeAssignment_0 ) )
            // InternalN4MFParser.g:5261:1: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            {
             before(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeAssignment_0()); 
            // InternalN4MFParser.g:5262:1: ( rule__SourceFragment__SourceFragmentTypeAssignment_0 )
            // InternalN4MFParser.g:5262:2: rule__SourceFragment__SourceFragmentTypeAssignment_0
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
    // InternalN4MFParser.g:5272:1: rule__SourceFragment__Group__1 : rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 ;
    public final void rule__SourceFragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5276:1: ( rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2 )
            // InternalN4MFParser.g:5277:2: rule__SourceFragment__Group__1__Impl rule__SourceFragment__Group__2
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
    // InternalN4MFParser.g:5284:1: rule__SourceFragment__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__SourceFragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5288:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:5289:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:5289:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:5290:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:5303:1: rule__SourceFragment__Group__2 : rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 ;
    public final void rule__SourceFragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5307:1: ( rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3 )
            // InternalN4MFParser.g:5308:2: rule__SourceFragment__Group__2__Impl rule__SourceFragment__Group__3
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
    // InternalN4MFParser.g:5315:1: rule__SourceFragment__Group__2__Impl : ( ( rule__SourceFragment__PathsAssignment_2 ) ) ;
    public final void rule__SourceFragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5319:1: ( ( ( rule__SourceFragment__PathsAssignment_2 ) ) )
            // InternalN4MFParser.g:5320:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            {
            // InternalN4MFParser.g:5320:1: ( ( rule__SourceFragment__PathsAssignment_2 ) )
            // InternalN4MFParser.g:5321:1: ( rule__SourceFragment__PathsAssignment_2 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_2()); 
            // InternalN4MFParser.g:5322:1: ( rule__SourceFragment__PathsAssignment_2 )
            // InternalN4MFParser.g:5322:2: rule__SourceFragment__PathsAssignment_2
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
    // InternalN4MFParser.g:5332:1: rule__SourceFragment__Group__3 : rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 ;
    public final void rule__SourceFragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5336:1: ( rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4 )
            // InternalN4MFParser.g:5337:2: rule__SourceFragment__Group__3__Impl rule__SourceFragment__Group__4
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
    // InternalN4MFParser.g:5344:1: rule__SourceFragment__Group__3__Impl : ( ( rule__SourceFragment__Group_3__0 )* ) ;
    public final void rule__SourceFragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5348:1: ( ( ( rule__SourceFragment__Group_3__0 )* ) )
            // InternalN4MFParser.g:5349:1: ( ( rule__SourceFragment__Group_3__0 )* )
            {
            // InternalN4MFParser.g:5349:1: ( ( rule__SourceFragment__Group_3__0 )* )
            // InternalN4MFParser.g:5350:1: ( rule__SourceFragment__Group_3__0 )*
            {
             before(grammarAccess.getSourceFragmentAccess().getGroup_3()); 
            // InternalN4MFParser.g:5351:1: ( rule__SourceFragment__Group_3__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==Comma) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalN4MFParser.g:5351:2: rule__SourceFragment__Group_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__SourceFragment__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
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
    // InternalN4MFParser.g:5361:1: rule__SourceFragment__Group__4 : rule__SourceFragment__Group__4__Impl ;
    public final void rule__SourceFragment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5365:1: ( rule__SourceFragment__Group__4__Impl )
            // InternalN4MFParser.g:5366:2: rule__SourceFragment__Group__4__Impl
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
    // InternalN4MFParser.g:5372:1: rule__SourceFragment__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__SourceFragment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5376:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:5377:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:5377:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:5378:1: RightCurlyBracket
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
    // InternalN4MFParser.g:5401:1: rule__SourceFragment__Group_3__0 : rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 ;
    public final void rule__SourceFragment__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5405:1: ( rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1 )
            // InternalN4MFParser.g:5406:2: rule__SourceFragment__Group_3__0__Impl rule__SourceFragment__Group_3__1
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
    // InternalN4MFParser.g:5413:1: rule__SourceFragment__Group_3__0__Impl : ( Comma ) ;
    public final void rule__SourceFragment__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5417:1: ( ( Comma ) )
            // InternalN4MFParser.g:5418:1: ( Comma )
            {
            // InternalN4MFParser.g:5418:1: ( Comma )
            // InternalN4MFParser.g:5419:1: Comma
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
    // InternalN4MFParser.g:5432:1: rule__SourceFragment__Group_3__1 : rule__SourceFragment__Group_3__1__Impl ;
    public final void rule__SourceFragment__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5436:1: ( rule__SourceFragment__Group_3__1__Impl )
            // InternalN4MFParser.g:5437:2: rule__SourceFragment__Group_3__1__Impl
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
    // InternalN4MFParser.g:5443:1: rule__SourceFragment__Group_3__1__Impl : ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) ;
    public final void rule__SourceFragment__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5447:1: ( ( ( rule__SourceFragment__PathsAssignment_3_1 ) ) )
            // InternalN4MFParser.g:5448:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:5448:1: ( ( rule__SourceFragment__PathsAssignment_3_1 ) )
            // InternalN4MFParser.g:5449:1: ( rule__SourceFragment__PathsAssignment_3_1 )
            {
             before(grammarAccess.getSourceFragmentAccess().getPathsAssignment_3_1()); 
            // InternalN4MFParser.g:5450:1: ( rule__SourceFragment__PathsAssignment_3_1 )
            // InternalN4MFParser.g:5450:2: rule__SourceFragment__PathsAssignment_3_1
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
    // InternalN4MFParser.g:5464:1: rule__ModuleFilter__Group__0 : rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 ;
    public final void rule__ModuleFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5468:1: ( rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1 )
            // InternalN4MFParser.g:5469:2: rule__ModuleFilter__Group__0__Impl rule__ModuleFilter__Group__1
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
    // InternalN4MFParser.g:5476:1: rule__ModuleFilter__Group__0__Impl : ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) ;
    public final void rule__ModuleFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5480:1: ( ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) ) )
            // InternalN4MFParser.g:5481:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            {
            // InternalN4MFParser.g:5481:1: ( ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 ) )
            // InternalN4MFParser.g:5482:1: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleFilterTypeAssignment_0()); 
            // InternalN4MFParser.g:5483:1: ( rule__ModuleFilter__ModuleFilterTypeAssignment_0 )
            // InternalN4MFParser.g:5483:2: rule__ModuleFilter__ModuleFilterTypeAssignment_0
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
    // InternalN4MFParser.g:5493:1: rule__ModuleFilter__Group__1 : rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 ;
    public final void rule__ModuleFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5497:1: ( rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2 )
            // InternalN4MFParser.g:5498:2: rule__ModuleFilter__Group__1__Impl rule__ModuleFilter__Group__2
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
    // InternalN4MFParser.g:5505:1: rule__ModuleFilter__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5509:1: ( ( LeftCurlyBracket ) )
            // InternalN4MFParser.g:5510:1: ( LeftCurlyBracket )
            {
            // InternalN4MFParser.g:5510:1: ( LeftCurlyBracket )
            // InternalN4MFParser.g:5511:1: LeftCurlyBracket
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
    // InternalN4MFParser.g:5524:1: rule__ModuleFilter__Group__2 : rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 ;
    public final void rule__ModuleFilter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5528:1: ( rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3 )
            // InternalN4MFParser.g:5529:2: rule__ModuleFilter__Group__2__Impl rule__ModuleFilter__Group__3
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
    // InternalN4MFParser.g:5536:1: rule__ModuleFilter__Group__2__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) ;
    public final void rule__ModuleFilter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5540:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) ) )
            // InternalN4MFParser.g:5541:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            {
            // InternalN4MFParser.g:5541:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 ) )
            // InternalN4MFParser.g:5542:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_2()); 
            // InternalN4MFParser.g:5543:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_2 )
            // InternalN4MFParser.g:5543:2: rule__ModuleFilter__ModuleSpecifiersAssignment_2
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
    // InternalN4MFParser.g:5553:1: rule__ModuleFilter__Group__3 : rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 ;
    public final void rule__ModuleFilter__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5557:1: ( rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4 )
            // InternalN4MFParser.g:5558:2: rule__ModuleFilter__Group__3__Impl rule__ModuleFilter__Group__4
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
    // InternalN4MFParser.g:5565:1: rule__ModuleFilter__Group__3__Impl : ( ( rule__ModuleFilter__Group_3__0 )* ) ;
    public final void rule__ModuleFilter__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5569:1: ( ( ( rule__ModuleFilter__Group_3__0 )* ) )
            // InternalN4MFParser.g:5570:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            {
            // InternalN4MFParser.g:5570:1: ( ( rule__ModuleFilter__Group_3__0 )* )
            // InternalN4MFParser.g:5571:1: ( rule__ModuleFilter__Group_3__0 )*
            {
             before(grammarAccess.getModuleFilterAccess().getGroup_3()); 
            // InternalN4MFParser.g:5572:1: ( rule__ModuleFilter__Group_3__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==Comma) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalN4MFParser.g:5572:2: rule__ModuleFilter__Group_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ModuleFilter__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // InternalN4MFParser.g:5582:1: rule__ModuleFilter__Group__4 : rule__ModuleFilter__Group__4__Impl ;
    public final void rule__ModuleFilter__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5586:1: ( rule__ModuleFilter__Group__4__Impl )
            // InternalN4MFParser.g:5587:2: rule__ModuleFilter__Group__4__Impl
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
    // InternalN4MFParser.g:5593:1: rule__ModuleFilter__Group__4__Impl : ( RightCurlyBracket ) ;
    public final void rule__ModuleFilter__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5597:1: ( ( RightCurlyBracket ) )
            // InternalN4MFParser.g:5598:1: ( RightCurlyBracket )
            {
            // InternalN4MFParser.g:5598:1: ( RightCurlyBracket )
            // InternalN4MFParser.g:5599:1: RightCurlyBracket
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
    // InternalN4MFParser.g:5622:1: rule__ModuleFilter__Group_3__0 : rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 ;
    public final void rule__ModuleFilter__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5626:1: ( rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1 )
            // InternalN4MFParser.g:5627:2: rule__ModuleFilter__Group_3__0__Impl rule__ModuleFilter__Group_3__1
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
    // InternalN4MFParser.g:5634:1: rule__ModuleFilter__Group_3__0__Impl : ( Comma ) ;
    public final void rule__ModuleFilter__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5638:1: ( ( Comma ) )
            // InternalN4MFParser.g:5639:1: ( Comma )
            {
            // InternalN4MFParser.g:5639:1: ( Comma )
            // InternalN4MFParser.g:5640:1: Comma
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
    // InternalN4MFParser.g:5653:1: rule__ModuleFilter__Group_3__1 : rule__ModuleFilter__Group_3__1__Impl ;
    public final void rule__ModuleFilter__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5657:1: ( rule__ModuleFilter__Group_3__1__Impl )
            // InternalN4MFParser.g:5658:2: rule__ModuleFilter__Group_3__1__Impl
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
    // InternalN4MFParser.g:5664:1: rule__ModuleFilter__Group_3__1__Impl : ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) ;
    public final void rule__ModuleFilter__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5668:1: ( ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) ) )
            // InternalN4MFParser.g:5669:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            {
            // InternalN4MFParser.g:5669:1: ( ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 ) )
            // InternalN4MFParser.g:5670:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            {
             before(grammarAccess.getModuleFilterAccess().getModuleSpecifiersAssignment_3_1()); 
            // InternalN4MFParser.g:5671:1: ( rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 )
            // InternalN4MFParser.g:5671:2: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1
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
    // InternalN4MFParser.g:5685:1: rule__BootstrapModule__Group__0 : rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 ;
    public final void rule__BootstrapModule__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5689:1: ( rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1 )
            // InternalN4MFParser.g:5690:2: rule__BootstrapModule__Group__0__Impl rule__BootstrapModule__Group__1
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
    // InternalN4MFParser.g:5697:1: rule__BootstrapModule__Group__0__Impl : ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__BootstrapModule__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5701:1: ( ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:5702:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:5702:1: ( ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:5703:1: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:5704:1: ( rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:5704:2: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0
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
    // InternalN4MFParser.g:5714:1: rule__BootstrapModule__Group__1 : rule__BootstrapModule__Group__1__Impl ;
    public final void rule__BootstrapModule__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5718:1: ( rule__BootstrapModule__Group__1__Impl )
            // InternalN4MFParser.g:5719:2: rule__BootstrapModule__Group__1__Impl
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
    // InternalN4MFParser.g:5725:1: rule__BootstrapModule__Group__1__Impl : ( ( rule__BootstrapModule__Group_1__0 )? ) ;
    public final void rule__BootstrapModule__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5729:1: ( ( ( rule__BootstrapModule__Group_1__0 )? ) )
            // InternalN4MFParser.g:5730:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            {
            // InternalN4MFParser.g:5730:1: ( ( rule__BootstrapModule__Group_1__0 )? )
            // InternalN4MFParser.g:5731:1: ( rule__BootstrapModule__Group_1__0 )?
            {
             before(grammarAccess.getBootstrapModuleAccess().getGroup_1()); 
            // InternalN4MFParser.g:5732:1: ( rule__BootstrapModule__Group_1__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==In) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalN4MFParser.g:5732:2: rule__BootstrapModule__Group_1__0
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
    // InternalN4MFParser.g:5746:1: rule__BootstrapModule__Group_1__0 : rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 ;
    public final void rule__BootstrapModule__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5750:1: ( rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1 )
            // InternalN4MFParser.g:5751:2: rule__BootstrapModule__Group_1__0__Impl rule__BootstrapModule__Group_1__1
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
    // InternalN4MFParser.g:5758:1: rule__BootstrapModule__Group_1__0__Impl : ( In ) ;
    public final void rule__BootstrapModule__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5762:1: ( ( In ) )
            // InternalN4MFParser.g:5763:1: ( In )
            {
            // InternalN4MFParser.g:5763:1: ( In )
            // InternalN4MFParser.g:5764:1: In
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
    // InternalN4MFParser.g:5777:1: rule__BootstrapModule__Group_1__1 : rule__BootstrapModule__Group_1__1__Impl ;
    public final void rule__BootstrapModule__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5781:1: ( rule__BootstrapModule__Group_1__1__Impl )
            // InternalN4MFParser.g:5782:2: rule__BootstrapModule__Group_1__1__Impl
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
    // InternalN4MFParser.g:5788:1: rule__BootstrapModule__Group_1__1__Impl : ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) ;
    public final void rule__BootstrapModule__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5792:1: ( ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5793:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5793:1: ( ( rule__BootstrapModule__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:5794:1: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getBootstrapModuleAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:5795:1: ( rule__BootstrapModule__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:5795:2: rule__BootstrapModule__SourcePathAssignment_1_1
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
    // InternalN4MFParser.g:5809:1: rule__ModuleFilterSpecifier__Group__0 : rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 ;
    public final void rule__ModuleFilterSpecifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5813:1: ( rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1 )
            // InternalN4MFParser.g:5814:2: rule__ModuleFilterSpecifier__Group__0__Impl rule__ModuleFilterSpecifier__Group__1
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
    // InternalN4MFParser.g:5821:1: rule__ModuleFilterSpecifier__Group__0__Impl : ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5825:1: ( ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) ) )
            // InternalN4MFParser.g:5826:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            {
            // InternalN4MFParser.g:5826:1: ( ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 ) )
            // InternalN4MFParser.g:5827:1: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardAssignment_0()); 
            // InternalN4MFParser.g:5828:1: ( rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 )
            // InternalN4MFParser.g:5828:2: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0
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
    // InternalN4MFParser.g:5838:1: rule__ModuleFilterSpecifier__Group__1 : rule__ModuleFilterSpecifier__Group__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5842:1: ( rule__ModuleFilterSpecifier__Group__1__Impl )
            // InternalN4MFParser.g:5843:2: rule__ModuleFilterSpecifier__Group__1__Impl
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
    // InternalN4MFParser.g:5849:1: rule__ModuleFilterSpecifier__Group__1__Impl : ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) ;
    public final void rule__ModuleFilterSpecifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5853:1: ( ( ( rule__ModuleFilterSpecifier__Group_1__0 )? ) )
            // InternalN4MFParser.g:5854:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            {
            // InternalN4MFParser.g:5854:1: ( ( rule__ModuleFilterSpecifier__Group_1__0 )? )
            // InternalN4MFParser.g:5855:1: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getGroup_1()); 
            // InternalN4MFParser.g:5856:1: ( rule__ModuleFilterSpecifier__Group_1__0 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==In) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalN4MFParser.g:5856:2: rule__ModuleFilterSpecifier__Group_1__0
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
    // InternalN4MFParser.g:5870:1: rule__ModuleFilterSpecifier__Group_1__0 : rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 ;
    public final void rule__ModuleFilterSpecifier__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5874:1: ( rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1 )
            // InternalN4MFParser.g:5875:2: rule__ModuleFilterSpecifier__Group_1__0__Impl rule__ModuleFilterSpecifier__Group_1__1
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
    // InternalN4MFParser.g:5882:1: rule__ModuleFilterSpecifier__Group_1__0__Impl : ( In ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5886:1: ( ( In ) )
            // InternalN4MFParser.g:5887:1: ( In )
            {
            // InternalN4MFParser.g:5887:1: ( In )
            // InternalN4MFParser.g:5888:1: In
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
    // InternalN4MFParser.g:5901:1: rule__ModuleFilterSpecifier__Group_1__1 : rule__ModuleFilterSpecifier__Group_1__1__Impl ;
    public final void rule__ModuleFilterSpecifier__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5905:1: ( rule__ModuleFilterSpecifier__Group_1__1__Impl )
            // InternalN4MFParser.g:5906:2: rule__ModuleFilterSpecifier__Group_1__1__Impl
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
    // InternalN4MFParser.g:5912:1: rule__ModuleFilterSpecifier__Group_1__1__Impl : ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) ;
    public final void rule__ModuleFilterSpecifier__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5916:1: ( ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) ) )
            // InternalN4MFParser.g:5917:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            {
            // InternalN4MFParser.g:5917:1: ( ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 ) )
            // InternalN4MFParser.g:5918:1: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            {
             before(grammarAccess.getModuleFilterSpecifierAccess().getSourcePathAssignment_1_1()); 
            // InternalN4MFParser.g:5919:1: ( rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 )
            // InternalN4MFParser.g:5919:2: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1
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
    // InternalN4MFParser.g:5933:1: rule__ProjectDependency__Group__0 : rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 ;
    public final void rule__ProjectDependency__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5937:1: ( rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1 )
            // InternalN4MFParser.g:5938:2: rule__ProjectDependency__Group__0__Impl rule__ProjectDependency__Group__1
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
    // InternalN4MFParser.g:5945:1: rule__ProjectDependency__Group__0__Impl : ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) ;
    public final void rule__ProjectDependency__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5949:1: ( ( ( rule__ProjectDependency__ProjectAssignment_0 ) ) )
            // InternalN4MFParser.g:5950:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            {
            // InternalN4MFParser.g:5950:1: ( ( rule__ProjectDependency__ProjectAssignment_0 ) )
            // InternalN4MFParser.g:5951:1: ( rule__ProjectDependency__ProjectAssignment_0 )
            {
             before(grammarAccess.getProjectDependencyAccess().getProjectAssignment_0()); 
            // InternalN4MFParser.g:5952:1: ( rule__ProjectDependency__ProjectAssignment_0 )
            // InternalN4MFParser.g:5952:2: rule__ProjectDependency__ProjectAssignment_0
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
    // InternalN4MFParser.g:5962:1: rule__ProjectDependency__Group__1 : rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 ;
    public final void rule__ProjectDependency__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5966:1: ( rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2 )
            // InternalN4MFParser.g:5967:2: rule__ProjectDependency__Group__1__Impl rule__ProjectDependency__Group__2
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
    // InternalN4MFParser.g:5974:1: rule__ProjectDependency__Group__1__Impl : ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) ;
    public final void rule__ProjectDependency__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5978:1: ( ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? ) )
            // InternalN4MFParser.g:5979:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            {
            // InternalN4MFParser.g:5979:1: ( ( rule__ProjectDependency__VersionConstraintAssignment_1 )? )
            // InternalN4MFParser.g:5980:1: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getVersionConstraintAssignment_1()); 
            // InternalN4MFParser.g:5981:1: ( rule__ProjectDependency__VersionConstraintAssignment_1 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==LeftParenthesis||LA36_0==LeftSquareBracket||LA36_0==RULE_INT) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalN4MFParser.g:5981:2: rule__ProjectDependency__VersionConstraintAssignment_1
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
    // InternalN4MFParser.g:5991:1: rule__ProjectDependency__Group__2 : rule__ProjectDependency__Group__2__Impl ;
    public final void rule__ProjectDependency__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:5995:1: ( rule__ProjectDependency__Group__2__Impl )
            // InternalN4MFParser.g:5996:2: rule__ProjectDependency__Group__2__Impl
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
    // InternalN4MFParser.g:6002:1: rule__ProjectDependency__Group__2__Impl : ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) ;
    public final void rule__ProjectDependency__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6006:1: ( ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? ) )
            // InternalN4MFParser.g:6007:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            {
            // InternalN4MFParser.g:6007:1: ( ( rule__ProjectDependency__DeclaredScopeAssignment_2 )? )
            // InternalN4MFParser.g:6008:1: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            {
             before(grammarAccess.getProjectDependencyAccess().getDeclaredScopeAssignment_2()); 
            // InternalN4MFParser.g:6009:1: ( rule__ProjectDependency__DeclaredScopeAssignment_2 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==Compile||LA37_0==Test) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalN4MFParser.g:6009:2: rule__ProjectDependency__DeclaredScopeAssignment_2
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
    // InternalN4MFParser.g:6025:1: rule__SimpleProjectDescription__Group__0 : rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 ;
    public final void rule__SimpleProjectDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6029:1: ( rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1 )
            // InternalN4MFParser.g:6030:2: rule__SimpleProjectDescription__Group__0__Impl rule__SimpleProjectDescription__Group__1
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
    // InternalN4MFParser.g:6037:1: rule__SimpleProjectDescription__Group__0__Impl : ( ( rule__SimpleProjectDescription__Group_0__0 )? ) ;
    public final void rule__SimpleProjectDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6041:1: ( ( ( rule__SimpleProjectDescription__Group_0__0 )? ) )
            // InternalN4MFParser.g:6042:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            {
            // InternalN4MFParser.g:6042:1: ( ( rule__SimpleProjectDescription__Group_0__0 )? )
            // InternalN4MFParser.g:6043:1: ( rule__SimpleProjectDescription__Group_0__0 )?
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getGroup_0()); 
            // InternalN4MFParser.g:6044:1: ( rule__SimpleProjectDescription__Group_0__0 )?
            int alt38=2;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // InternalN4MFParser.g:6044:2: rule__SimpleProjectDescription__Group_0__0
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
    // InternalN4MFParser.g:6054:1: rule__SimpleProjectDescription__Group__1 : rule__SimpleProjectDescription__Group__1__Impl ;
    public final void rule__SimpleProjectDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6058:1: ( rule__SimpleProjectDescription__Group__1__Impl )
            // InternalN4MFParser.g:6059:2: rule__SimpleProjectDescription__Group__1__Impl
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
    // InternalN4MFParser.g:6065:1: rule__SimpleProjectDescription__Group__1__Impl : ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) ) ;
    public final void rule__SimpleProjectDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6069:1: ( ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) ) )
            // InternalN4MFParser.g:6070:1: ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) )
            {
            // InternalN4MFParser.g:6070:1: ( ( rule__SimpleProjectDescription__ProjectIdAssignment_1 ) )
            // InternalN4MFParser.g:6071:1: ( rule__SimpleProjectDescription__ProjectIdAssignment_1 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdAssignment_1()); 
            // InternalN4MFParser.g:6072:1: ( rule__SimpleProjectDescription__ProjectIdAssignment_1 )
            // InternalN4MFParser.g:6072:2: rule__SimpleProjectDescription__ProjectIdAssignment_1
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
    // InternalN4MFParser.g:6086:1: rule__SimpleProjectDescription__Group_0__0 : rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 ;
    public final void rule__SimpleProjectDescription__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6090:1: ( rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1 )
            // InternalN4MFParser.g:6091:2: rule__SimpleProjectDescription__Group_0__0__Impl rule__SimpleProjectDescription__Group_0__1
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
    // InternalN4MFParser.g:6098:1: rule__SimpleProjectDescription__Group_0__0__Impl : ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) ;
    public final void rule__SimpleProjectDescription__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6102:1: ( ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) ) )
            // InternalN4MFParser.g:6103:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            {
            // InternalN4MFParser.g:6103:1: ( ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 ) )
            // InternalN4MFParser.g:6104:1: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            {
             before(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdAssignment_0_0()); 
            // InternalN4MFParser.g:6105:1: ( rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 )
            // InternalN4MFParser.g:6105:2: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0
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
    // InternalN4MFParser.g:6115:1: rule__SimpleProjectDescription__Group_0__1 : rule__SimpleProjectDescription__Group_0__1__Impl ;
    public final void rule__SimpleProjectDescription__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6119:1: ( rule__SimpleProjectDescription__Group_0__1__Impl )
            // InternalN4MFParser.g:6120:2: rule__SimpleProjectDescription__Group_0__1__Impl
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
    // InternalN4MFParser.g:6126:1: rule__SimpleProjectDescription__Group_0__1__Impl : ( Colon ) ;
    public final void rule__SimpleProjectDescription__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6130:1: ( ( Colon ) )
            // InternalN4MFParser.g:6131:1: ( Colon )
            {
            // InternalN4MFParser.g:6131:1: ( Colon )
            // InternalN4MFParser.g:6132:1: Colon
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
    // InternalN4MFParser.g:6149:1: rule__VersionConstraint__Group_0__0 : rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 ;
    public final void rule__VersionConstraint__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6153:1: ( rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1 )
            // InternalN4MFParser.g:6154:2: rule__VersionConstraint__Group_0__0__Impl rule__VersionConstraint__Group_0__1
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
    // InternalN4MFParser.g:6161:1: rule__VersionConstraint__Group_0__0__Impl : ( ( rule__VersionConstraint__Alternatives_0_0 ) ) ;
    public final void rule__VersionConstraint__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6165:1: ( ( ( rule__VersionConstraint__Alternatives_0_0 ) ) )
            // InternalN4MFParser.g:6166:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            {
            // InternalN4MFParser.g:6166:1: ( ( rule__VersionConstraint__Alternatives_0_0 ) )
            // InternalN4MFParser.g:6167:1: ( rule__VersionConstraint__Alternatives_0_0 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_0()); 
            // InternalN4MFParser.g:6168:1: ( rule__VersionConstraint__Alternatives_0_0 )
            // InternalN4MFParser.g:6168:2: rule__VersionConstraint__Alternatives_0_0
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
    // InternalN4MFParser.g:6178:1: rule__VersionConstraint__Group_0__1 : rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 ;
    public final void rule__VersionConstraint__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6182:1: ( rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2 )
            // InternalN4MFParser.g:6183:2: rule__VersionConstraint__Group_0__1__Impl rule__VersionConstraint__Group_0__2
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
    // InternalN4MFParser.g:6190:1: rule__VersionConstraint__Group_0__1__Impl : ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6194:1: ( ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) ) )
            // InternalN4MFParser.g:6195:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            {
            // InternalN4MFParser.g:6195:1: ( ( rule__VersionConstraint__LowerVersionAssignment_0_1 ) )
            // InternalN4MFParser.g:6196:1: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getLowerVersionAssignment_0_1()); 
            // InternalN4MFParser.g:6197:1: ( rule__VersionConstraint__LowerVersionAssignment_0_1 )
            // InternalN4MFParser.g:6197:2: rule__VersionConstraint__LowerVersionAssignment_0_1
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
    // InternalN4MFParser.g:6207:1: rule__VersionConstraint__Group_0__2 : rule__VersionConstraint__Group_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6211:1: ( rule__VersionConstraint__Group_0__2__Impl )
            // InternalN4MFParser.g:6212:2: rule__VersionConstraint__Group_0__2__Impl
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
    // InternalN4MFParser.g:6218:1: rule__VersionConstraint__Group_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6222:1: ( ( ( rule__VersionConstraint__Alternatives_0_2 ) ) )
            // InternalN4MFParser.g:6223:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            {
            // InternalN4MFParser.g:6223:1: ( ( rule__VersionConstraint__Alternatives_0_2 ) )
            // InternalN4MFParser.g:6224:1: ( rule__VersionConstraint__Alternatives_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2()); 
            // InternalN4MFParser.g:6225:1: ( rule__VersionConstraint__Alternatives_0_2 )
            // InternalN4MFParser.g:6225:2: rule__VersionConstraint__Alternatives_0_2
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
    // InternalN4MFParser.g:6241:1: rule__VersionConstraint__Group_0_2_0__0 : rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 ;
    public final void rule__VersionConstraint__Group_0_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6245:1: ( rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1 )
            // InternalN4MFParser.g:6246:2: rule__VersionConstraint__Group_0_2_0__0__Impl rule__VersionConstraint__Group_0_2_0__1
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
    // InternalN4MFParser.g:6253:1: rule__VersionConstraint__Group_0_2_0__0__Impl : ( Comma ) ;
    public final void rule__VersionConstraint__Group_0_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6257:1: ( ( Comma ) )
            // InternalN4MFParser.g:6258:1: ( Comma )
            {
            // InternalN4MFParser.g:6258:1: ( Comma )
            // InternalN4MFParser.g:6259:1: Comma
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
    // InternalN4MFParser.g:6272:1: rule__VersionConstraint__Group_0_2_0__1 : rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 ;
    public final void rule__VersionConstraint__Group_0_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6276:1: ( rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2 )
            // InternalN4MFParser.g:6277:2: rule__VersionConstraint__Group_0_2_0__1__Impl rule__VersionConstraint__Group_0_2_0__2
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
    // InternalN4MFParser.g:6284:1: rule__VersionConstraint__Group_0_2_0__1__Impl : ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6288:1: ( ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) ) )
            // InternalN4MFParser.g:6289:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            {
            // InternalN4MFParser.g:6289:1: ( ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 ) )
            // InternalN4MFParser.g:6290:1: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            {
             before(grammarAccess.getVersionConstraintAccess().getUpperVersionAssignment_0_2_0_1()); 
            // InternalN4MFParser.g:6291:1: ( rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 )
            // InternalN4MFParser.g:6291:2: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1
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
    // InternalN4MFParser.g:6301:1: rule__VersionConstraint__Group_0_2_0__2 : rule__VersionConstraint__Group_0_2_0__2__Impl ;
    public final void rule__VersionConstraint__Group_0_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6305:1: ( rule__VersionConstraint__Group_0_2_0__2__Impl )
            // InternalN4MFParser.g:6306:2: rule__VersionConstraint__Group_0_2_0__2__Impl
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
    // InternalN4MFParser.g:6312:1: rule__VersionConstraint__Group_0_2_0__2__Impl : ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) ;
    public final void rule__VersionConstraint__Group_0_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6316:1: ( ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) ) )
            // InternalN4MFParser.g:6317:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            {
            // InternalN4MFParser.g:6317:1: ( ( rule__VersionConstraint__Alternatives_0_2_0_2 ) )
            // InternalN4MFParser.g:6318:1: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            {
             before(grammarAccess.getVersionConstraintAccess().getAlternatives_0_2_0_2()); 
            // InternalN4MFParser.g:6319:1: ( rule__VersionConstraint__Alternatives_0_2_0_2 )
            // InternalN4MFParser.g:6319:2: rule__VersionConstraint__Alternatives_0_2_0_2
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


    // $ANTLR start "rule__N4mfIdentifier__Group_13__0"
    // InternalN4MFParser.g:6335:1: rule__N4mfIdentifier__Group_13__0 : rule__N4mfIdentifier__Group_13__0__Impl rule__N4mfIdentifier__Group_13__1 ;
    public final void rule__N4mfIdentifier__Group_13__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6339:1: ( rule__N4mfIdentifier__Group_13__0__Impl rule__N4mfIdentifier__Group_13__1 )
            // InternalN4MFParser.g:6340:2: rule__N4mfIdentifier__Group_13__0__Impl rule__N4mfIdentifier__Group_13__1
            {
            pushFollow(FOLLOW_34);
            rule__N4mfIdentifier__Group_13__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_13__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_13__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_13__0__Impl"
    // InternalN4MFParser.g:6347:1: rule__N4mfIdentifier__Group_13__0__Impl : ( ProjectDependencies ) ;
    public final void rule__N4mfIdentifier__Group_13__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6351:1: ( ( ProjectDependencies ) )
            // InternalN4MFParser.g:6352:1: ( ProjectDependencies )
            {
            // InternalN4MFParser.g:6352:1: ( ProjectDependencies )
            // InternalN4MFParser.g:6353:1: ProjectDependencies
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_13_0()); 
            match(input,ProjectDependencies,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_13_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_13__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_13__1"
    // InternalN4MFParser.g:6366:1: rule__N4mfIdentifier__Group_13__1 : rule__N4mfIdentifier__Group_13__1__Impl ;
    public final void rule__N4mfIdentifier__Group_13__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6370:1: ( rule__N4mfIdentifier__Group_13__1__Impl )
            // InternalN4MFParser.g:6371:2: rule__N4mfIdentifier__Group_13__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_13__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_13__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_13__1__Impl"
    // InternalN4MFParser.g:6377:1: rule__N4mfIdentifier__Group_13__1__Impl : ( KW_System ) ;
    public final void rule__N4mfIdentifier__Group_13__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6381:1: ( ( KW_System ) )
            // InternalN4MFParser.g:6382:1: ( KW_System )
            {
            // InternalN4MFParser.g:6382:1: ( KW_System )
            // InternalN4MFParser.g:6383:1: KW_System
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_13_1()); 
            match(input,KW_System,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_13_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_13__1__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_17__0"
    // InternalN4MFParser.g:6400:1: rule__N4mfIdentifier__Group_17__0 : rule__N4mfIdentifier__Group_17__0__Impl rule__N4mfIdentifier__Group_17__1 ;
    public final void rule__N4mfIdentifier__Group_17__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6404:1: ( rule__N4mfIdentifier__Group_17__0__Impl rule__N4mfIdentifier__Group_17__1 )
            // InternalN4MFParser.g:6405:2: rule__N4mfIdentifier__Group_17__0__Impl rule__N4mfIdentifier__Group_17__1
            {
            pushFollow(FOLLOW_35);
            rule__N4mfIdentifier__Group_17__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_17__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_17__0"


    // $ANTLR start "rule__N4mfIdentifier__Group_17__0__Impl"
    // InternalN4MFParser.g:6412:1: rule__N4mfIdentifier__Group_17__0__Impl : ( Processor ) ;
    public final void rule__N4mfIdentifier__Group_17__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6416:1: ( ( Processor ) )
            // InternalN4MFParser.g:6417:1: ( Processor )
            {
            // InternalN4MFParser.g:6417:1: ( Processor )
            // InternalN4MFParser.g:6418:1: Processor
            {
             before(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_17_0()); 
            match(input,Processor,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_17_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_17__0__Impl"


    // $ANTLR start "rule__N4mfIdentifier__Group_17__1"
    // InternalN4MFParser.g:6431:1: rule__N4mfIdentifier__Group_17__1 : rule__N4mfIdentifier__Group_17__1__Impl ;
    public final void rule__N4mfIdentifier__Group_17__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6435:1: ( rule__N4mfIdentifier__Group_17__1__Impl )
            // InternalN4MFParser.g:6436:2: rule__N4mfIdentifier__Group_17__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__N4mfIdentifier__Group_17__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_17__1"


    // $ANTLR start "rule__N4mfIdentifier__Group_17__1__Impl"
    // InternalN4MFParser.g:6442:1: rule__N4mfIdentifier__Group_17__1__Impl : ( Source ) ;
    public final void rule__N4mfIdentifier__Group_17__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6446:1: ( ( Source ) )
            // InternalN4MFParser.g:6447:1: ( Source )
            {
            // InternalN4MFParser.g:6447:1: ( Source )
            // InternalN4MFParser.g:6448:1: Source
            {
             before(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_17_1()); 
            match(input,Source,FOLLOW_2); 
             after(grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_17_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__N4mfIdentifier__Group_17__1__Impl"


    // $ANTLR start "rule__ProjectDescription__UnorderedGroup"
    // InternalN4MFParser.g:6466:1: rule__ProjectDescription__UnorderedGroup : rule__ProjectDescription__UnorderedGroup__0 {...}?;
    public final void rule__ProjectDescription__UnorderedGroup() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            
        try {
            // InternalN4MFParser.g:6471:1: ( rule__ProjectDescription__UnorderedGroup__0 {...}?)
            // InternalN4MFParser.g:6472:2: rule__ProjectDescription__UnorderedGroup__0 {...}?
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
    // InternalN4MFParser.g:6483:1: rule__ProjectDescription__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) ) ;
    public final void rule__ProjectDescription__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalN4MFParser.g:6488:1: ( ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) ) )
            // InternalN4MFParser.g:6489:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )
            {
            // InternalN4MFParser.g:6489:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )
            int alt39=22;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // InternalN4MFParser.g:6491:4: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6491:4: ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) )
                    // InternalN4MFParser.g:6492:5: {...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalN4MFParser.g:6492:113: ( ( ( rule__ProjectDescription__Group_0__0 ) ) )
                    // InternalN4MFParser.g:6493:6: ( ( rule__ProjectDescription__Group_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6499:6: ( ( rule__ProjectDescription__Group_0__0 ) )
                    // InternalN4MFParser.g:6501:7: ( rule__ProjectDescription__Group_0__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_0()); 
                    // InternalN4MFParser.g:6502:7: ( rule__ProjectDescription__Group_0__0 )
                    // InternalN4MFParser.g:6502:8: rule__ProjectDescription__Group_0__0
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
                    // InternalN4MFParser.g:6508:4: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6508:4: ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) )
                    // InternalN4MFParser.g:6509:5: {...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalN4MFParser.g:6509:113: ( ( ( rule__ProjectDescription__Group_1__0 ) ) )
                    // InternalN4MFParser.g:6510:6: ( ( rule__ProjectDescription__Group_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6516:6: ( ( rule__ProjectDescription__Group_1__0 ) )
                    // InternalN4MFParser.g:6518:7: ( rule__ProjectDescription__Group_1__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_1()); 
                    // InternalN4MFParser.g:6519:7: ( rule__ProjectDescription__Group_1__0 )
                    // InternalN4MFParser.g:6519:8: rule__ProjectDescription__Group_1__0
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
                    // InternalN4MFParser.g:6525:4: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6525:4: ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) )
                    // InternalN4MFParser.g:6526:5: {...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalN4MFParser.g:6526:113: ( ( ( rule__ProjectDescription__Group_2__0 ) ) )
                    // InternalN4MFParser.g:6527:6: ( ( rule__ProjectDescription__Group_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6533:6: ( ( rule__ProjectDescription__Group_2__0 ) )
                    // InternalN4MFParser.g:6535:7: ( rule__ProjectDescription__Group_2__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_2()); 
                    // InternalN4MFParser.g:6536:7: ( rule__ProjectDescription__Group_2__0 )
                    // InternalN4MFParser.g:6536:8: rule__ProjectDescription__Group_2__0
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
                    // InternalN4MFParser.g:6542:4: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6542:4: ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) )
                    // InternalN4MFParser.g:6543:5: {...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalN4MFParser.g:6543:113: ( ( ( rule__ProjectDescription__Group_3__0 ) ) )
                    // InternalN4MFParser.g:6544:6: ( ( rule__ProjectDescription__Group_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6550:6: ( ( rule__ProjectDescription__Group_3__0 ) )
                    // InternalN4MFParser.g:6552:7: ( rule__ProjectDescription__Group_3__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_3()); 
                    // InternalN4MFParser.g:6553:7: ( rule__ProjectDescription__Group_3__0 )
                    // InternalN4MFParser.g:6553:8: rule__ProjectDescription__Group_3__0
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
                    // InternalN4MFParser.g:6559:4: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6559:4: ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) )
                    // InternalN4MFParser.g:6560:5: {...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalN4MFParser.g:6560:113: ( ( ( rule__ProjectDescription__Group_4__0 ) ) )
                    // InternalN4MFParser.g:6561:6: ( ( rule__ProjectDescription__Group_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6567:6: ( ( rule__ProjectDescription__Group_4__0 ) )
                    // InternalN4MFParser.g:6569:7: ( rule__ProjectDescription__Group_4__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_4()); 
                    // InternalN4MFParser.g:6570:7: ( rule__ProjectDescription__Group_4__0 )
                    // InternalN4MFParser.g:6570:8: rule__ProjectDescription__Group_4__0
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
                    // InternalN4MFParser.g:6576:4: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6576:4: ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) )
                    // InternalN4MFParser.g:6577:5: {...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalN4MFParser.g:6577:113: ( ( ( rule__ProjectDescription__Group_5__0 ) ) )
                    // InternalN4MFParser.g:6578:6: ( ( rule__ProjectDescription__Group_5__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6584:6: ( ( rule__ProjectDescription__Group_5__0 ) )
                    // InternalN4MFParser.g:6586:7: ( rule__ProjectDescription__Group_5__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_5()); 
                    // InternalN4MFParser.g:6587:7: ( rule__ProjectDescription__Group_5__0 )
                    // InternalN4MFParser.g:6587:8: rule__ProjectDescription__Group_5__0
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
                    // InternalN4MFParser.g:6593:4: ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6593:4: ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) )
                    // InternalN4MFParser.g:6594:5: {...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6)");
                    }
                    // InternalN4MFParser.g:6594:113: ( ( ( rule__ProjectDescription__Group_6__0 ) ) )
                    // InternalN4MFParser.g:6595:6: ( ( rule__ProjectDescription__Group_6__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6601:6: ( ( rule__ProjectDescription__Group_6__0 ) )
                    // InternalN4MFParser.g:6603:7: ( rule__ProjectDescription__Group_6__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_6()); 
                    // InternalN4MFParser.g:6604:7: ( rule__ProjectDescription__Group_6__0 )
                    // InternalN4MFParser.g:6604:8: rule__ProjectDescription__Group_6__0
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
                    // InternalN4MFParser.g:6610:4: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) )
                    {
                    // InternalN4MFParser.g:6610:4: ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) )
                    // InternalN4MFParser.g:6611:5: {...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7)");
                    }
                    // InternalN4MFParser.g:6611:113: ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) )
                    // InternalN4MFParser.g:6612:6: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6618:6: ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) )
                    // InternalN4MFParser.g:6620:7: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentAssignment_7()); 
                    // InternalN4MFParser.g:6621:7: ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 )
                    // InternalN4MFParser.g:6621:8: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7
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
                    // InternalN4MFParser.g:6627:4: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) )
                    {
                    // InternalN4MFParser.g:6627:4: ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) )
                    // InternalN4MFParser.g:6628:5: {...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8)");
                    }
                    // InternalN4MFParser.g:6628:113: ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) )
                    // InternalN4MFParser.g:6629:6: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6635:6: ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) )
                    // InternalN4MFParser.g:6637:7: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesAssignment_8()); 
                    // InternalN4MFParser.g:6638:7: ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 )
                    // InternalN4MFParser.g:6638:8: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8
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
                    // InternalN4MFParser.g:6644:4: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) )
                    {
                    // InternalN4MFParser.g:6644:4: ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) )
                    // InternalN4MFParser.g:6645:5: {...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9)");
                    }
                    // InternalN4MFParser.g:6645:113: ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) )
                    // InternalN4MFParser.g:6646:6: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6652:6: ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) )
                    // InternalN4MFParser.g:6654:7: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesAssignment_9()); 
                    // InternalN4MFParser.g:6655:7: ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 )
                    // InternalN4MFParser.g:6655:8: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9
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
                    // InternalN4MFParser.g:6661:4: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) )
                    {
                    // InternalN4MFParser.g:6661:4: ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) )
                    // InternalN4MFParser.g:6662:5: {...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10)");
                    }
                    // InternalN4MFParser.g:6662:114: ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) )
                    // InternalN4MFParser.g:6663:6: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6669:6: ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) )
                    // InternalN4MFParser.g:6671:7: ( rule__ProjectDescription__ProjectDependenciesAssignment_10 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesAssignment_10()); 
                    // InternalN4MFParser.g:6672:7: ( rule__ProjectDescription__ProjectDependenciesAssignment_10 )
                    // InternalN4MFParser.g:6672:8: rule__ProjectDescription__ProjectDependenciesAssignment_10
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
                    // InternalN4MFParser.g:6678:4: ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6678:4: ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) )
                    // InternalN4MFParser.g:6679:5: {...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11)");
                    }
                    // InternalN4MFParser.g:6679:114: ( ( ( rule__ProjectDescription__Group_11__0 ) ) )
                    // InternalN4MFParser.g:6680:6: ( ( rule__ProjectDescription__Group_11__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6686:6: ( ( rule__ProjectDescription__Group_11__0 ) )
                    // InternalN4MFParser.g:6688:7: ( rule__ProjectDescription__Group_11__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_11()); 
                    // InternalN4MFParser.g:6689:7: ( rule__ProjectDescription__Group_11__0 )
                    // InternalN4MFParser.g:6689:8: rule__ProjectDescription__Group_11__0
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
                    // InternalN4MFParser.g:6695:4: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) )
                    {
                    // InternalN4MFParser.g:6695:4: ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) )
                    // InternalN4MFParser.g:6696:5: {...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12)");
                    }
                    // InternalN4MFParser.g:6696:114: ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) )
                    // InternalN4MFParser.g:6697:6: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6703:6: ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) )
                    // InternalN4MFParser.g:6705:7: ( rule__ProjectDescription__ImplementedProjectsAssignment_12 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsAssignment_12()); 
                    // InternalN4MFParser.g:6706:7: ( rule__ProjectDescription__ImplementedProjectsAssignment_12 )
                    // InternalN4MFParser.g:6706:8: rule__ProjectDescription__ImplementedProjectsAssignment_12
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
                    // InternalN4MFParser.g:6712:4: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) )
                    {
                    // InternalN4MFParser.g:6712:4: ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) )
                    // InternalN4MFParser.g:6713:5: {...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13)");
                    }
                    // InternalN4MFParser.g:6713:114: ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) )
                    // InternalN4MFParser.g:6714:6: ( ( rule__ProjectDescription__InitModulesAssignment_13 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6720:6: ( ( rule__ProjectDescription__InitModulesAssignment_13 ) )
                    // InternalN4MFParser.g:6722:7: ( rule__ProjectDescription__InitModulesAssignment_13 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getInitModulesAssignment_13()); 
                    // InternalN4MFParser.g:6723:7: ( rule__ProjectDescription__InitModulesAssignment_13 )
                    // InternalN4MFParser.g:6723:8: rule__ProjectDescription__InitModulesAssignment_13
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
                    // InternalN4MFParser.g:6729:4: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) )
                    {
                    // InternalN4MFParser.g:6729:4: ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) )
                    // InternalN4MFParser.g:6730:5: {...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14)");
                    }
                    // InternalN4MFParser.g:6730:114: ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) )
                    // InternalN4MFParser.g:6731:6: ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6737:6: ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) )
                    // InternalN4MFParser.g:6739:7: ( rule__ProjectDescription__ExecModuleAssignment_14 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getExecModuleAssignment_14()); 
                    // InternalN4MFParser.g:6740:7: ( rule__ProjectDescription__ExecModuleAssignment_14 )
                    // InternalN4MFParser.g:6740:8: rule__ProjectDescription__ExecModuleAssignment_14
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
                    // InternalN4MFParser.g:6746:4: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6746:4: ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) )
                    // InternalN4MFParser.g:6747:5: {...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15)");
                    }
                    // InternalN4MFParser.g:6747:114: ( ( ( rule__ProjectDescription__Group_15__0 ) ) )
                    // InternalN4MFParser.g:6748:6: ( ( rule__ProjectDescription__Group_15__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6754:6: ( ( rule__ProjectDescription__Group_15__0 ) )
                    // InternalN4MFParser.g:6756:7: ( rule__ProjectDescription__Group_15__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_15()); 
                    // InternalN4MFParser.g:6757:7: ( rule__ProjectDescription__Group_15__0 )
                    // InternalN4MFParser.g:6757:8: rule__ProjectDescription__Group_15__0
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
                    // InternalN4MFParser.g:6763:4: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6763:4: ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) )
                    // InternalN4MFParser.g:6764:5: {...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16)");
                    }
                    // InternalN4MFParser.g:6764:114: ( ( ( rule__ProjectDescription__Group_16__0 ) ) )
                    // InternalN4MFParser.g:6765:6: ( ( rule__ProjectDescription__Group_16__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6771:6: ( ( rule__ProjectDescription__Group_16__0 ) )
                    // InternalN4MFParser.g:6773:7: ( rule__ProjectDescription__Group_16__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_16()); 
                    // InternalN4MFParser.g:6774:7: ( rule__ProjectDescription__Group_16__0 )
                    // InternalN4MFParser.g:6774:8: rule__ProjectDescription__Group_16__0
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
                    // InternalN4MFParser.g:6780:4: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6780:4: ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) )
                    // InternalN4MFParser.g:6781:5: {...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17)");
                    }
                    // InternalN4MFParser.g:6781:114: ( ( ( rule__ProjectDescription__Group_17__0 ) ) )
                    // InternalN4MFParser.g:6782:6: ( ( rule__ProjectDescription__Group_17__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6788:6: ( ( rule__ProjectDescription__Group_17__0 ) )
                    // InternalN4MFParser.g:6790:7: ( rule__ProjectDescription__Group_17__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_17()); 
                    // InternalN4MFParser.g:6791:7: ( rule__ProjectDescription__Group_17__0 )
                    // InternalN4MFParser.g:6791:8: rule__ProjectDescription__Group_17__0
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
                    // InternalN4MFParser.g:6797:4: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6797:4: ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) )
                    // InternalN4MFParser.g:6798:5: {...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18)");
                    }
                    // InternalN4MFParser.g:6798:114: ( ( ( rule__ProjectDescription__Group_18__0 ) ) )
                    // InternalN4MFParser.g:6799:6: ( ( rule__ProjectDescription__Group_18__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6805:6: ( ( rule__ProjectDescription__Group_18__0 ) )
                    // InternalN4MFParser.g:6807:7: ( rule__ProjectDescription__Group_18__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_18()); 
                    // InternalN4MFParser.g:6808:7: ( rule__ProjectDescription__Group_18__0 )
                    // InternalN4MFParser.g:6808:8: rule__ProjectDescription__Group_18__0
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
                    // InternalN4MFParser.g:6814:4: ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6814:4: ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) )
                    // InternalN4MFParser.g:6815:5: {...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19)");
                    }
                    // InternalN4MFParser.g:6815:114: ( ( ( rule__ProjectDescription__Group_19__0 ) ) )
                    // InternalN4MFParser.g:6816:6: ( ( rule__ProjectDescription__Group_19__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6822:6: ( ( rule__ProjectDescription__Group_19__0 ) )
                    // InternalN4MFParser.g:6824:7: ( rule__ProjectDescription__Group_19__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_19()); 
                    // InternalN4MFParser.g:6825:7: ( rule__ProjectDescription__Group_19__0 )
                    // InternalN4MFParser.g:6825:8: rule__ProjectDescription__Group_19__0
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
                    // InternalN4MFParser.g:6831:4: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) )
                    {
                    // InternalN4MFParser.g:6831:4: ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) )
                    // InternalN4MFParser.g:6832:5: {...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20)");
                    }
                    // InternalN4MFParser.g:6832:114: ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) )
                    // InternalN4MFParser.g:6833:6: ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6839:6: ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) )
                    // InternalN4MFParser.g:6841:7: ( rule__ProjectDescription__TestedProjectsAssignment_20 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getTestedProjectsAssignment_20()); 
                    // InternalN4MFParser.g:6842:7: ( rule__ProjectDescription__TestedProjectsAssignment_20 )
                    // InternalN4MFParser.g:6842:8: rule__ProjectDescription__TestedProjectsAssignment_20
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
                    // InternalN4MFParser.g:6848:4: ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) )
                    {
                    // InternalN4MFParser.g:6848:4: ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) )
                    // InternalN4MFParser.g:6849:5: {...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {
                        throw new FailedPredicateException(input, "rule__ProjectDescription__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21)");
                    }
                    // InternalN4MFParser.g:6849:114: ( ( ( rule__ProjectDescription__Group_21__0 ) ) )
                    // InternalN4MFParser.g:6850:6: ( ( rule__ProjectDescription__Group_21__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalN4MFParser.g:6856:6: ( ( rule__ProjectDescription__Group_21__0 ) )
                    // InternalN4MFParser.g:6858:7: ( rule__ProjectDescription__Group_21__0 )
                    {
                     before(grammarAccess.getProjectDescriptionAccess().getGroup_21()); 
                    // InternalN4MFParser.g:6859:7: ( rule__ProjectDescription__Group_21__0 )
                    // InternalN4MFParser.g:6859:8: rule__ProjectDescription__Group_21__0
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
    // InternalN4MFParser.g:6874:1: rule__ProjectDescription__UnorderedGroup__0 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6878:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )? )
            // InternalN4MFParser.g:6879:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6880:2: ( rule__ProjectDescription__UnorderedGroup__1 )?
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // InternalN4MFParser.g:6880:2: rule__ProjectDescription__UnorderedGroup__1
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
    // InternalN4MFParser.g:6887:1: rule__ProjectDescription__UnorderedGroup__1 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6891:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )? )
            // InternalN4MFParser.g:6892:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6893:2: ( rule__ProjectDescription__UnorderedGroup__2 )?
            int alt41=2;
            alt41 = dfa41.predict(input);
            switch (alt41) {
                case 1 :
                    // InternalN4MFParser.g:6893:2: rule__ProjectDescription__UnorderedGroup__2
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
    // InternalN4MFParser.g:6900:1: rule__ProjectDescription__UnorderedGroup__2 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6904:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )? )
            // InternalN4MFParser.g:6905:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6906:2: ( rule__ProjectDescription__UnorderedGroup__3 )?
            int alt42=2;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // InternalN4MFParser.g:6906:2: rule__ProjectDescription__UnorderedGroup__3
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
    // InternalN4MFParser.g:6913:1: rule__ProjectDescription__UnorderedGroup__3 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6917:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )? )
            // InternalN4MFParser.g:6918:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6919:2: ( rule__ProjectDescription__UnorderedGroup__4 )?
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // InternalN4MFParser.g:6919:2: rule__ProjectDescription__UnorderedGroup__4
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
    // InternalN4MFParser.g:6926:1: rule__ProjectDescription__UnorderedGroup__4 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6930:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )? )
            // InternalN4MFParser.g:6931:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6932:2: ( rule__ProjectDescription__UnorderedGroup__5 )?
            int alt44=2;
            alt44 = dfa44.predict(input);
            switch (alt44) {
                case 1 :
                    // InternalN4MFParser.g:6932:2: rule__ProjectDescription__UnorderedGroup__5
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
    // InternalN4MFParser.g:6939:1: rule__ProjectDescription__UnorderedGroup__5 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6943:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )? )
            // InternalN4MFParser.g:6944:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__6 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6945:2: ( rule__ProjectDescription__UnorderedGroup__6 )?
            int alt45=2;
            alt45 = dfa45.predict(input);
            switch (alt45) {
                case 1 :
                    // InternalN4MFParser.g:6945:2: rule__ProjectDescription__UnorderedGroup__6
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
    // InternalN4MFParser.g:6952:1: rule__ProjectDescription__UnorderedGroup__6 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6956:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )? )
            // InternalN4MFParser.g:6957:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__7 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6958:2: ( rule__ProjectDescription__UnorderedGroup__7 )?
            int alt46=2;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // InternalN4MFParser.g:6958:2: rule__ProjectDescription__UnorderedGroup__7
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
    // InternalN4MFParser.g:6965:1: rule__ProjectDescription__UnorderedGroup__7 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6969:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )? )
            // InternalN4MFParser.g:6970:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__8 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6971:2: ( rule__ProjectDescription__UnorderedGroup__8 )?
            int alt47=2;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // InternalN4MFParser.g:6971:2: rule__ProjectDescription__UnorderedGroup__8
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
    // InternalN4MFParser.g:6978:1: rule__ProjectDescription__UnorderedGroup__8 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6982:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )? )
            // InternalN4MFParser.g:6983:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__9 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6984:2: ( rule__ProjectDescription__UnorderedGroup__9 )?
            int alt48=2;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // InternalN4MFParser.g:6984:2: rule__ProjectDescription__UnorderedGroup__9
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
    // InternalN4MFParser.g:6991:1: rule__ProjectDescription__UnorderedGroup__9 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:6995:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )? )
            // InternalN4MFParser.g:6996:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__10 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:6997:2: ( rule__ProjectDescription__UnorderedGroup__10 )?
            int alt49=2;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // InternalN4MFParser.g:6997:2: rule__ProjectDescription__UnorderedGroup__10
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
    // InternalN4MFParser.g:7004:1: rule__ProjectDescription__UnorderedGroup__10 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7008:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )? )
            // InternalN4MFParser.g:7009:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__11 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7010:2: ( rule__ProjectDescription__UnorderedGroup__11 )?
            int alt50=2;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1 :
                    // InternalN4MFParser.g:7010:2: rule__ProjectDescription__UnorderedGroup__11
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
    // InternalN4MFParser.g:7017:1: rule__ProjectDescription__UnorderedGroup__11 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7021:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )? )
            // InternalN4MFParser.g:7022:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__12 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7023:2: ( rule__ProjectDescription__UnorderedGroup__12 )?
            int alt51=2;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // InternalN4MFParser.g:7023:2: rule__ProjectDescription__UnorderedGroup__12
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
    // InternalN4MFParser.g:7030:1: rule__ProjectDescription__UnorderedGroup__12 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7034:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )? )
            // InternalN4MFParser.g:7035:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__13 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7036:2: ( rule__ProjectDescription__UnorderedGroup__13 )?
            int alt52=2;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // InternalN4MFParser.g:7036:2: rule__ProjectDescription__UnorderedGroup__13
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
    // InternalN4MFParser.g:7043:1: rule__ProjectDescription__UnorderedGroup__13 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7047:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )? )
            // InternalN4MFParser.g:7048:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__14 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7049:2: ( rule__ProjectDescription__UnorderedGroup__14 )?
            int alt53=2;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // InternalN4MFParser.g:7049:2: rule__ProjectDescription__UnorderedGroup__14
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
    // InternalN4MFParser.g:7056:1: rule__ProjectDescription__UnorderedGroup__14 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7060:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )? )
            // InternalN4MFParser.g:7061:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__15 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7062:2: ( rule__ProjectDescription__UnorderedGroup__15 )?
            int alt54=2;
            alt54 = dfa54.predict(input);
            switch (alt54) {
                case 1 :
                    // InternalN4MFParser.g:7062:2: rule__ProjectDescription__UnorderedGroup__15
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
    // InternalN4MFParser.g:7069:1: rule__ProjectDescription__UnorderedGroup__15 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7073:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )? )
            // InternalN4MFParser.g:7074:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__16 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7075:2: ( rule__ProjectDescription__UnorderedGroup__16 )?
            int alt55=2;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // InternalN4MFParser.g:7075:2: rule__ProjectDescription__UnorderedGroup__16
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
    // InternalN4MFParser.g:7082:1: rule__ProjectDescription__UnorderedGroup__16 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__16() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7086:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )? )
            // InternalN4MFParser.g:7087:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__17 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7088:2: ( rule__ProjectDescription__UnorderedGroup__17 )?
            int alt56=2;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // InternalN4MFParser.g:7088:2: rule__ProjectDescription__UnorderedGroup__17
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
    // InternalN4MFParser.g:7095:1: rule__ProjectDescription__UnorderedGroup__17 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__17() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7099:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )? )
            // InternalN4MFParser.g:7100:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__18 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7101:2: ( rule__ProjectDescription__UnorderedGroup__18 )?
            int alt57=2;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // InternalN4MFParser.g:7101:2: rule__ProjectDescription__UnorderedGroup__18
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
    // InternalN4MFParser.g:7108:1: rule__ProjectDescription__UnorderedGroup__18 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__18() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7112:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )? )
            // InternalN4MFParser.g:7113:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__19 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7114:2: ( rule__ProjectDescription__UnorderedGroup__19 )?
            int alt58=2;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // InternalN4MFParser.g:7114:2: rule__ProjectDescription__UnorderedGroup__19
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
    // InternalN4MFParser.g:7121:1: rule__ProjectDescription__UnorderedGroup__19 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__19() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7125:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )? )
            // InternalN4MFParser.g:7126:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__20 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7127:2: ( rule__ProjectDescription__UnorderedGroup__20 )?
            int alt59=2;
            alt59 = dfa59.predict(input);
            switch (alt59) {
                case 1 :
                    // InternalN4MFParser.g:7127:2: rule__ProjectDescription__UnorderedGroup__20
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
    // InternalN4MFParser.g:7134:1: rule__ProjectDescription__UnorderedGroup__20 : rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )? ;
    public final void rule__ProjectDescription__UnorderedGroup__20() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7138:1: ( rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )? )
            // InternalN4MFParser.g:7139:2: rule__ProjectDescription__UnorderedGroup__Impl ( rule__ProjectDescription__UnorderedGroup__21 )?
            {
            pushFollow(FOLLOW_36);
            rule__ProjectDescription__UnorderedGroup__Impl();

            state._fsp--;

            // InternalN4MFParser.g:7140:2: ( rule__ProjectDescription__UnorderedGroup__21 )?
            int alt60=2;
            alt60 = dfa60.predict(input);
            switch (alt60) {
                case 1 :
                    // InternalN4MFParser.g:7140:2: rule__ProjectDescription__UnorderedGroup__21
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
    // InternalN4MFParser.g:7147:1: rule__ProjectDescription__UnorderedGroup__21 : rule__ProjectDescription__UnorderedGroup__Impl ;
    public final void rule__ProjectDescription__UnorderedGroup__21() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7151:1: ( rule__ProjectDescription__UnorderedGroup__Impl )
            // InternalN4MFParser.g:7152:2: rule__ProjectDescription__UnorderedGroup__Impl
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


    // $ANTLR start "rule__ProjectDescription__ProjectIdAssignment_0_2"
    // InternalN4MFParser.g:7203:1: rule__ProjectDescription__ProjectIdAssignment_0_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ProjectIdAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7207:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7208:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7208:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7209:1: ruleN4mfIdentifier
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


    // $ANTLR start "rule__ProjectDescription__ProjectTypeAssignment_2_2"
    // InternalN4MFParser.g:7218:1: rule__ProjectDescription__ProjectTypeAssignment_2_2 : ( ruleProjectType ) ;
    public final void rule__ProjectDescription__ProjectTypeAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7222:1: ( ( ruleProjectType ) )
            // InternalN4MFParser.g:7223:1: ( ruleProjectType )
            {
            // InternalN4MFParser.g:7223:1: ( ruleProjectType )
            // InternalN4MFParser.g:7224:1: ruleProjectType
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
    // InternalN4MFParser.g:7233:1: rule__ProjectDescription__ProjectVersionAssignment_3_2 : ( ruleDeclaredVersion ) ;
    public final void rule__ProjectDescription__ProjectVersionAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7237:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:7238:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:7238:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:7239:1: ruleDeclaredVersion
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
    // InternalN4MFParser.g:7248:1: rule__ProjectDescription__DeclaredVendorIdAssignment_4_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__DeclaredVendorIdAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7252:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7253:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7253:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7254:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:7263:1: rule__ProjectDescription__VendorNameAssignment_5_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__VendorNameAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7267:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7268:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7268:1: ( RULE_STRING )
            // InternalN4MFParser.g:7269:1: RULE_STRING
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
    // InternalN4MFParser.g:7278:1: rule__ProjectDescription__MainModuleAssignment_6_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__MainModuleAssignment_6_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7282:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7283:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7283:1: ( RULE_STRING )
            // InternalN4MFParser.g:7284:1: RULE_STRING
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
    // InternalN4MFParser.g:7293:1: rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 : ( ruleExtendedRuntimeEnvironment ) ;
    public final void rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7297:1: ( ( ruleExtendedRuntimeEnvironment ) )
            // InternalN4MFParser.g:7298:1: ( ruleExtendedRuntimeEnvironment )
            {
            // InternalN4MFParser.g:7298:1: ( ruleExtendedRuntimeEnvironment )
            // InternalN4MFParser.g:7299:1: ruleExtendedRuntimeEnvironment
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
    // InternalN4MFParser.g:7308:1: rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 : ( ruleProvidedRuntimeLibraries ) ;
    public final void rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7312:1: ( ( ruleProvidedRuntimeLibraries ) )
            // InternalN4MFParser.g:7313:1: ( ruleProvidedRuntimeLibraries )
            {
            // InternalN4MFParser.g:7313:1: ( ruleProvidedRuntimeLibraries )
            // InternalN4MFParser.g:7314:1: ruleProvidedRuntimeLibraries
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
    // InternalN4MFParser.g:7323:1: rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 : ( ruleRequiredRuntimeLibraries ) ;
    public final void rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7327:1: ( ( ruleRequiredRuntimeLibraries ) )
            // InternalN4MFParser.g:7328:1: ( ruleRequiredRuntimeLibraries )
            {
            // InternalN4MFParser.g:7328:1: ( ruleRequiredRuntimeLibraries )
            // InternalN4MFParser.g:7329:1: ruleRequiredRuntimeLibraries
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
    // InternalN4MFParser.g:7338:1: rule__ProjectDescription__ProjectDependenciesAssignment_10 : ( ruleProjectDependencies ) ;
    public final void rule__ProjectDescription__ProjectDependenciesAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7342:1: ( ( ruleProjectDependencies ) )
            // InternalN4MFParser.g:7343:1: ( ruleProjectDependencies )
            {
            // InternalN4MFParser.g:7343:1: ( ruleProjectDependencies )
            // InternalN4MFParser.g:7344:1: ruleProjectDependencies
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
    // InternalN4MFParser.g:7353:1: rule__ProjectDescription__ImplementationIdAssignment_11_2 : ( ruleN4mfIdentifier ) ;
    public final void rule__ProjectDescription__ImplementationIdAssignment_11_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7357:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7358:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7358:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7359:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:7368:1: rule__ProjectDescription__ImplementedProjectsAssignment_12 : ( ruleImplementedProjects ) ;
    public final void rule__ProjectDescription__ImplementedProjectsAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7372:1: ( ( ruleImplementedProjects ) )
            // InternalN4MFParser.g:7373:1: ( ruleImplementedProjects )
            {
            // InternalN4MFParser.g:7373:1: ( ruleImplementedProjects )
            // InternalN4MFParser.g:7374:1: ruleImplementedProjects
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
    // InternalN4MFParser.g:7383:1: rule__ProjectDescription__InitModulesAssignment_13 : ( ruleInitModules ) ;
    public final void rule__ProjectDescription__InitModulesAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7387:1: ( ( ruleInitModules ) )
            // InternalN4MFParser.g:7388:1: ( ruleInitModules )
            {
            // InternalN4MFParser.g:7388:1: ( ruleInitModules )
            // InternalN4MFParser.g:7389:1: ruleInitModules
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
    // InternalN4MFParser.g:7398:1: rule__ProjectDescription__ExecModuleAssignment_14 : ( ruleExecModule ) ;
    public final void rule__ProjectDescription__ExecModuleAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7402:1: ( ( ruleExecModule ) )
            // InternalN4MFParser.g:7403:1: ( ruleExecModule )
            {
            // InternalN4MFParser.g:7403:1: ( ruleExecModule )
            // InternalN4MFParser.g:7404:1: ruleExecModule
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
    // InternalN4MFParser.g:7413:1: rule__ProjectDescription__OutputPathAssignment_15_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__OutputPathAssignment_15_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7417:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7418:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7418:1: ( RULE_STRING )
            // InternalN4MFParser.g:7419:1: RULE_STRING
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
    // InternalN4MFParser.g:7428:1: rule__ProjectDescription__LibraryPathsAssignment_16_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_16_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7432:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7433:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7433:1: ( RULE_STRING )
            // InternalN4MFParser.g:7434:1: RULE_STRING
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
    // InternalN4MFParser.g:7443:1: rule__ProjectDescription__LibraryPathsAssignment_16_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__LibraryPathsAssignment_16_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7447:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7448:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7448:1: ( RULE_STRING )
            // InternalN4MFParser.g:7449:1: RULE_STRING
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
    // InternalN4MFParser.g:7458:1: rule__ProjectDescription__ResourcePathsAssignment_17_2 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_17_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7462:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7463:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7463:1: ( RULE_STRING )
            // InternalN4MFParser.g:7464:1: RULE_STRING
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
    // InternalN4MFParser.g:7473:1: rule__ProjectDescription__ResourcePathsAssignment_17_3_1 : ( RULE_STRING ) ;
    public final void rule__ProjectDescription__ResourcePathsAssignment_17_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7477:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7478:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7478:1: ( RULE_STRING )
            // InternalN4MFParser.g:7479:1: RULE_STRING
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
    // InternalN4MFParser.g:7488:1: rule__ProjectDescription__SourceFragmentAssignment_18_2 : ( ruleSourceFragment ) ;
    public final void rule__ProjectDescription__SourceFragmentAssignment_18_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7492:1: ( ( ruleSourceFragment ) )
            // InternalN4MFParser.g:7493:1: ( ruleSourceFragment )
            {
            // InternalN4MFParser.g:7493:1: ( ruleSourceFragment )
            // InternalN4MFParser.g:7494:1: ruleSourceFragment
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
    // InternalN4MFParser.g:7503:1: rule__ProjectDescription__ModuleFiltersAssignment_19_2 : ( ruleModuleFilter ) ;
    public final void rule__ProjectDescription__ModuleFiltersAssignment_19_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7507:1: ( ( ruleModuleFilter ) )
            // InternalN4MFParser.g:7508:1: ( ruleModuleFilter )
            {
            // InternalN4MFParser.g:7508:1: ( ruleModuleFilter )
            // InternalN4MFParser.g:7509:1: ruleModuleFilter
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
    // InternalN4MFParser.g:7518:1: rule__ProjectDescription__TestedProjectsAssignment_20 : ( ruleTestedProjects ) ;
    public final void rule__ProjectDescription__TestedProjectsAssignment_20() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7522:1: ( ( ruleTestedProjects ) )
            // InternalN4MFParser.g:7523:1: ( ruleTestedProjects )
            {
            // InternalN4MFParser.g:7523:1: ( ruleTestedProjects )
            // InternalN4MFParser.g:7524:1: ruleTestedProjects
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
    // InternalN4MFParser.g:7533:1: rule__ProjectDescription__ModuleLoaderAssignment_21_2 : ( ruleModuleLoader ) ;
    public final void rule__ProjectDescription__ModuleLoaderAssignment_21_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7537:1: ( ( ruleModuleLoader ) )
            // InternalN4MFParser.g:7538:1: ( ruleModuleLoader )
            {
            // InternalN4MFParser.g:7538:1: ( ruleModuleLoader )
            // InternalN4MFParser.g:7539:1: ruleModuleLoader
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
    // InternalN4MFParser.g:7548:1: rule__ExecModule__ExecModuleAssignment_3 : ( ruleBootstrapModule ) ;
    public final void rule__ExecModule__ExecModuleAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7552:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7553:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7553:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7554:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7563:1: rule__TestedProjects__TestedProjectsAssignment_3_0 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7567:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:7568:1: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:7568:1: ( ruleTestedProject )
            // InternalN4MFParser.g:7569:1: ruleTestedProject
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
    // InternalN4MFParser.g:7578:1: rule__TestedProjects__TestedProjectsAssignment_3_1_1 : ( ruleTestedProject ) ;
    public final void rule__TestedProjects__TestedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7582:1: ( ( ruleTestedProject ) )
            // InternalN4MFParser.g:7583:1: ( ruleTestedProject )
            {
            // InternalN4MFParser.g:7583:1: ( ruleTestedProject )
            // InternalN4MFParser.g:7584:1: ruleTestedProject
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
    // InternalN4MFParser.g:7593:1: rule__InitModules__InitModulesAssignment_3_0 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7597:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7598:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7598:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7599:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7608:1: rule__InitModules__InitModulesAssignment_3_1_1 : ( ruleBootstrapModule ) ;
    public final void rule__InitModules__InitModulesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7612:1: ( ( ruleBootstrapModule ) )
            // InternalN4MFParser.g:7613:1: ( ruleBootstrapModule )
            {
            // InternalN4MFParser.g:7613:1: ( ruleBootstrapModule )
            // InternalN4MFParser.g:7614:1: ruleBootstrapModule
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
    // InternalN4MFParser.g:7623:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_0 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7627:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7628:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7628:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7629:1: ruleProjectReference
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
    // InternalN4MFParser.g:7638:1: rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1 : ( ruleProjectReference ) ;
    public final void rule__ImplementedProjects__ImplementedProjectsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7642:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7643:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7643:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7644:1: ruleProjectReference
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
    // InternalN4MFParser.g:7653:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_0 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7657:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:7658:1: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:7658:1: ( ruleProjectDependency )
            // InternalN4MFParser.g:7659:1: ruleProjectDependency
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
    // InternalN4MFParser.g:7668:1: rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1 : ( ruleProjectDependency ) ;
    public final void rule__ProjectDependencies__ProjectDependenciesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7672:1: ( ( ruleProjectDependency ) )
            // InternalN4MFParser.g:7673:1: ( ruleProjectDependency )
            {
            // InternalN4MFParser.g:7673:1: ( ruleProjectDependency )
            // InternalN4MFParser.g:7674:1: ruleProjectDependency
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
    // InternalN4MFParser.g:7683:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7687:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7688:1: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7688:1: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:7689:1: ruleProvidedRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7698:1: rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1 : ( ruleProvidedRuntimeLibraryDependency ) ;
    public final void rule__ProvidedRuntimeLibraries__ProvidedRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7702:1: ( ( ruleProvidedRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7703:1: ( ruleProvidedRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7703:1: ( ruleProvidedRuntimeLibraryDependency )
            // InternalN4MFParser.g:7704:1: ruleProvidedRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7713:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7717:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7718:1: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7718:1: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:7719:1: ruleRequiredRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7728:1: rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1 : ( ruleRequiredRuntimeLibraryDependency ) ;
    public final void rule__RequiredRuntimeLibraries__RequiredRuntimeLibrariesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7732:1: ( ( ruleRequiredRuntimeLibraryDependency ) )
            // InternalN4MFParser.g:7733:1: ( ruleRequiredRuntimeLibraryDependency )
            {
            // InternalN4MFParser.g:7733:1: ( ruleRequiredRuntimeLibraryDependency )
            // InternalN4MFParser.g:7734:1: ruleRequiredRuntimeLibraryDependency
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
    // InternalN4MFParser.g:7743:1: rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3 : ( ruleProjectReference ) ;
    public final void rule__ExtendedRuntimeEnvironment__ExtendedRuntimeEnvironmentAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7747:1: ( ( ruleProjectReference ) )
            // InternalN4MFParser.g:7748:1: ( ruleProjectReference )
            {
            // InternalN4MFParser.g:7748:1: ( ruleProjectReference )
            // InternalN4MFParser.g:7749:1: ruleProjectReference
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
    // InternalN4MFParser.g:7758:1: rule__DeclaredVersion__MajorAssignment_0 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MajorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7762:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7763:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7763:1: ( RULE_INT )
            // InternalN4MFParser.g:7764:1: RULE_INT
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
    // InternalN4MFParser.g:7773:1: rule__DeclaredVersion__MinorAssignment_1_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MinorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7777:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7778:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7778:1: ( RULE_INT )
            // InternalN4MFParser.g:7779:1: RULE_INT
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
    // InternalN4MFParser.g:7788:1: rule__DeclaredVersion__MicroAssignment_1_2_1 : ( RULE_INT ) ;
    public final void rule__DeclaredVersion__MicroAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7792:1: ( ( RULE_INT ) )
            // InternalN4MFParser.g:7793:1: ( RULE_INT )
            {
            // InternalN4MFParser.g:7793:1: ( RULE_INT )
            // InternalN4MFParser.g:7794:1: RULE_INT
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
    // InternalN4MFParser.g:7803:1: rule__DeclaredVersion__QualifierAssignment_2_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__DeclaredVersion__QualifierAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7807:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:7808:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:7808:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:7809:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:7818:1: rule__SourceFragment__SourceFragmentTypeAssignment_0 : ( ruleSourceFragmentType ) ;
    public final void rule__SourceFragment__SourceFragmentTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7822:1: ( ( ruleSourceFragmentType ) )
            // InternalN4MFParser.g:7823:1: ( ruleSourceFragmentType )
            {
            // InternalN4MFParser.g:7823:1: ( ruleSourceFragmentType )
            // InternalN4MFParser.g:7824:1: ruleSourceFragmentType
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
    // InternalN4MFParser.g:7833:1: rule__SourceFragment__PathsAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7837:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7838:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7838:1: ( RULE_STRING )
            // InternalN4MFParser.g:7839:1: RULE_STRING
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
    // InternalN4MFParser.g:7848:1: rule__SourceFragment__PathsAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__SourceFragment__PathsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7852:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7853:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7853:1: ( RULE_STRING )
            // InternalN4MFParser.g:7854:1: RULE_STRING
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
    // InternalN4MFParser.g:7863:1: rule__ModuleFilter__ModuleFilterTypeAssignment_0 : ( ruleModuleFilterType ) ;
    public final void rule__ModuleFilter__ModuleFilterTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7867:1: ( ( ruleModuleFilterType ) )
            // InternalN4MFParser.g:7868:1: ( ruleModuleFilterType )
            {
            // InternalN4MFParser.g:7868:1: ( ruleModuleFilterType )
            // InternalN4MFParser.g:7869:1: ruleModuleFilterType
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
    // InternalN4MFParser.g:7878:1: rule__ModuleFilter__ModuleSpecifiersAssignment_2 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7882:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:7883:1: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:7883:1: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:7884:1: ruleModuleFilterSpecifier
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
    // InternalN4MFParser.g:7893:1: rule__ModuleFilter__ModuleSpecifiersAssignment_3_1 : ( ruleModuleFilterSpecifier ) ;
    public final void rule__ModuleFilter__ModuleSpecifiersAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7897:1: ( ( ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:7898:1: ( ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:7898:1: ( ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:7899:1: ruleModuleFilterSpecifier
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
    // InternalN4MFParser.g:7908:1: rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7912:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7913:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7913:1: ( RULE_STRING )
            // InternalN4MFParser.g:7914:1: RULE_STRING
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
    // InternalN4MFParser.g:7923:1: rule__BootstrapModule__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__BootstrapModule__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7927:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7928:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7928:1: ( RULE_STRING )
            // InternalN4MFParser.g:7929:1: RULE_STRING
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
    // InternalN4MFParser.g:7938:1: rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__ModuleSpecifierWithWildcardAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7942:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7943:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7943:1: ( RULE_STRING )
            // InternalN4MFParser.g:7944:1: RULE_STRING
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
    // InternalN4MFParser.g:7953:1: rule__ModuleFilterSpecifier__SourcePathAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__ModuleFilterSpecifier__SourcePathAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7957:1: ( ( RULE_STRING ) )
            // InternalN4MFParser.g:7958:1: ( RULE_STRING )
            {
            // InternalN4MFParser.g:7958:1: ( RULE_STRING )
            // InternalN4MFParser.g:7959:1: RULE_STRING
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
    // InternalN4MFParser.g:7968:1: rule__ProvidedRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProvidedRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7972:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:7973:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:7973:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:7974:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:7983:1: rule__RequiredRuntimeLibraryDependency__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__RequiredRuntimeLibraryDependency__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:7987:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:7988:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:7988:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:7989:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:7998:1: rule__TestedProject__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__TestedProject__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8002:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:8003:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:8003:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:8004:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:8013:1: rule__ProjectReference__ProjectAssignment : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectReference__ProjectAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8017:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:8018:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:8018:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:8019:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:8028:1: rule__ProjectDependency__ProjectAssignment_0 : ( ruleSimpleProjectDescription ) ;
    public final void rule__ProjectDependency__ProjectAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8032:1: ( ( ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:8033:1: ( ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:8033:1: ( ruleSimpleProjectDescription )
            // InternalN4MFParser.g:8034:1: ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:8043:1: rule__ProjectDependency__VersionConstraintAssignment_1 : ( ruleVersionConstraint ) ;
    public final void rule__ProjectDependency__VersionConstraintAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8047:1: ( ( ruleVersionConstraint ) )
            // InternalN4MFParser.g:8048:1: ( ruleVersionConstraint )
            {
            // InternalN4MFParser.g:8048:1: ( ruleVersionConstraint )
            // InternalN4MFParser.g:8049:1: ruleVersionConstraint
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
    // InternalN4MFParser.g:8058:1: rule__ProjectDependency__DeclaredScopeAssignment_2 : ( ruleProjectDependencyScope ) ;
    public final void rule__ProjectDependency__DeclaredScopeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8062:1: ( ( ruleProjectDependencyScope ) )
            // InternalN4MFParser.g:8063:1: ( ruleProjectDependencyScope )
            {
            // InternalN4MFParser.g:8063:1: ( ruleProjectDependencyScope )
            // InternalN4MFParser.g:8064:1: ruleProjectDependencyScope
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
    // InternalN4MFParser.g:8073:1: rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__DeclaredVendorIdAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8077:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:8078:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:8078:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:8079:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:8088:1: rule__SimpleProjectDescription__ProjectIdAssignment_1 : ( ruleN4mfIdentifier ) ;
    public final void rule__SimpleProjectDescription__ProjectIdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8092:1: ( ( ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:8093:1: ( ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:8093:1: ( ruleN4mfIdentifier )
            // InternalN4MFParser.g:8094:1: ruleN4mfIdentifier
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
    // InternalN4MFParser.g:8103:1: rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0 : ( ( LeftParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclLowerBoundAssignment_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8107:1: ( ( ( LeftParenthesis ) ) )
            // InternalN4MFParser.g:8108:1: ( ( LeftParenthesis ) )
            {
            // InternalN4MFParser.g:8108:1: ( ( LeftParenthesis ) )
            // InternalN4MFParser.g:8109:1: ( LeftParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0()); 
            // InternalN4MFParser.g:8110:1: ( LeftParenthesis )
            // InternalN4MFParser.g:8111:1: LeftParenthesis
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
    // InternalN4MFParser.g:8126:1: rule__VersionConstraint__LowerVersionAssignment_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8130:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8131:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8131:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8132:1: ruleDeclaredVersion
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
    // InternalN4MFParser.g:8141:1: rule__VersionConstraint__UpperVersionAssignment_0_2_0_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__UpperVersionAssignment_0_2_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8145:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8146:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8146:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8147:1: ruleDeclaredVersion
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
    // InternalN4MFParser.g:8156:1: rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0 : ( ( RightParenthesis ) ) ;
    public final void rule__VersionConstraint__ExclUpperBoundAssignment_0_2_0_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8160:1: ( ( ( RightParenthesis ) ) )
            // InternalN4MFParser.g:8161:1: ( ( RightParenthesis ) )
            {
            // InternalN4MFParser.g:8161:1: ( ( RightParenthesis ) )
            // InternalN4MFParser.g:8162:1: ( RightParenthesis )
            {
             before(grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0()); 
            // InternalN4MFParser.g:8163:1: ( RightParenthesis )
            // InternalN4MFParser.g:8164:1: RightParenthesis
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
    // InternalN4MFParser.g:8179:1: rule__VersionConstraint__LowerVersionAssignment_1 : ( ruleDeclaredVersion ) ;
    public final void rule__VersionConstraint__LowerVersionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalN4MFParser.g:8183:1: ( ( ruleDeclaredVersion ) )
            // InternalN4MFParser.g:8184:1: ( ruleDeclaredVersion )
            {
            // InternalN4MFParser.g:8184:1: ( ruleDeclaredVersion )
            // InternalN4MFParser.g:8185:1: ruleDeclaredVersion
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
    protected DFA60 dfa60 = new DFA60(this);
    static final String dfa_1s = "\31\uffff";
    static final String dfa_2s = "\1\uffff\15\25\1\uffff\3\25\1\uffff\2\25\2\uffff\2\25";
    static final String dfa_3s = "\1\10\15\4\1\50\3\4\1\47\2\4\2\uffff\2\4";
    static final String dfa_4s = "\1\70\15\71\1\50\3\71\1\47\2\71\2\uffff\2\71";
    static final String dfa_5s = "\25\uffff\1\2\1\1\2\uffff";
    static final String dfa_6s = "\31\uffff}>";
    static final String[] dfa_7s = {
            "\1\16\2\uffff\1\10\2\uffff\1\15\4\uffff\1\5\1\7\1\21\1\2\2\uffff\1\6\1\uffff\1\12\1\3\1\13\1\22\1\4\2\uffff\1\14\1\uffff\1\23\1\uffff\1\11\3\uffff\1\24\1\20\1\17\13\uffff\1\1",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\1\27",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\1\30",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "",
            "",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\3\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\3\uffff\1\25\1\uffff\1\25\2\uffff\1\26\1\25\2\uffff\1\25\1\uffff\1\25"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "6044:1: ( rule__SimpleProjectDescription__Group_0__0 )?";
        }
    }
    static final String dfa_8s = "\27\uffff";
    static final String dfa_9s = "\1\4\26\uffff";
    static final String dfa_10s = "\1\46\26\uffff";
    static final String dfa_11s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26";
    static final String dfa_12s = "\1\0\26\uffff}>";
    static final String[] dfa_13s = {
            "\1\10\1\11\1\12\1\15\1\13\1\uffff\1\14\1\4\1\25\1\uffff\1\24\1\26\2\uffff\1\16\1\2\1\3\1\uffff\1\1\1\17\1\7\1\6\1\uffff\1\21\1\1\1\22\1\uffff\1\5\2\uffff\1\23\3\uffff\1\20",
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

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = dfa_8;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "6489:3: ( ({...}? => ( ( ( rule__ProjectDescription__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExtendedRuntimeEnvironmentAssignment_7 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProvidedRuntimeLibrariesAssignment_8 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__RequiredRuntimeLibrariesAssignment_9 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ProjectDependenciesAssignment_10 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_11__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ImplementedProjectsAssignment_12 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__InitModulesAssignment_13 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__ExecModuleAssignment_14 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_15__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_16__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_17__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_18__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_19__0 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__TestedProjectsAssignment_20 ) ) ) ) | ({...}? => ( ( ( rule__ProjectDescription__Group_21__0 ) ) ) ) )";
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
                        if ( ( LA39_0 == ArtifactId || LA39_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
    static final String dfa_14s = "\30\uffff";
    static final String dfa_15s = "\1\27\27\uffff";
    static final String dfa_16s = "\1\4\27\uffff";
    static final String dfa_17s = "\1\46\27\uffff";
    static final String dfa_18s = "\1\uffff\26\1\1\2";
    static final String dfa_19s = "\1\0\27\uffff}>";
    static final String[] dfa_20s = {
            "\1\10\1\11\1\12\1\15\1\13\1\uffff\1\14\1\4\1\25\1\uffff\1\24\1\26\2\uffff\1\16\1\2\1\3\1\uffff\1\1\1\17\1\7\1\6\1\uffff\1\21\1\1\1\22\1\uffff\1\5\2\uffff\1\23\3\uffff\1\20",
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
    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[][] dfa_20 = unpackEncodedStringArray(dfa_20s);

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6880:2: ( rule__ProjectDescription__UnorderedGroup__1 )?";
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
                        if ( ( LA40_0 == ArtifactId || LA40_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6893:2: ( rule__ProjectDescription__UnorderedGroup__2 )?";
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
                        if ( ( LA41_0 == ArtifactId || LA41_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6906:2: ( rule__ProjectDescription__UnorderedGroup__3 )?";
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
                        if ( ( LA42_0 == ArtifactId || LA42_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6919:2: ( rule__ProjectDescription__UnorderedGroup__4 )?";
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
                        if ( ( LA43_0 == ArtifactId || LA43_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6932:2: ( rule__ProjectDescription__UnorderedGroup__5 )?";
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
                        if ( ( LA44_0 == ArtifactId || LA44_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6945:2: ( rule__ProjectDescription__UnorderedGroup__6 )?";
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
                        if ( ( LA45_0 == ArtifactId || LA45_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6958:2: ( rule__ProjectDescription__UnorderedGroup__7 )?";
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
                        if ( ( LA46_0 == ArtifactId || LA46_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6971:2: ( rule__ProjectDescription__UnorderedGroup__8 )?";
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
                        if ( ( LA47_0 == ArtifactId || LA47_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6984:2: ( rule__ProjectDescription__UnorderedGroup__9 )?";
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
                        if ( ( LA48_0 == ArtifactId || LA48_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "6997:2: ( rule__ProjectDescription__UnorderedGroup__10 )?";
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
                        if ( ( LA49_0 == ArtifactId || LA49_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7010:2: ( rule__ProjectDescription__UnorderedGroup__11 )?";
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
                        if ( ( LA50_0 == ArtifactId || LA50_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7023:2: ( rule__ProjectDescription__UnorderedGroup__12 )?";
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
                        if ( ( LA51_0 == ArtifactId || LA51_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7036:2: ( rule__ProjectDescription__UnorderedGroup__13 )?";
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
                        if ( ( LA52_0 == ArtifactId || LA52_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7049:2: ( rule__ProjectDescription__UnorderedGroup__14 )?";
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
                        if ( ( LA53_0 == ArtifactId || LA53_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7062:2: ( rule__ProjectDescription__UnorderedGroup__15 )?";
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
                        if ( ( LA54_0 == ArtifactId || LA54_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7075:2: ( rule__ProjectDescription__UnorderedGroup__16 )?";
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
                        if ( ( LA55_0 == ArtifactId || LA55_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7088:2: ( rule__ProjectDescription__UnorderedGroup__17 )?";
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
                        if ( ( LA56_0 == ArtifactId || LA56_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7101:2: ( rule__ProjectDescription__UnorderedGroup__18 )?";
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
                        if ( ( LA57_0 == ArtifactId || LA57_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7114:2: ( rule__ProjectDescription__UnorderedGroup__19 )?";
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
                        if ( ( LA58_0 == ArtifactId || LA58_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7127:2: ( rule__ProjectDescription__UnorderedGroup__20 )?";
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
                        if ( ( LA59_0 == ArtifactId || LA59_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

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

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "7140:2: ( rule__ProjectDescription__UnorderedGroup__21 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA60_0 = input.LA(1);

                         
                        int index60_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( LA60_0 == ArtifactId || LA60_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA60_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA60_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA60_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA60_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA60_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA60_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( LA60_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( LA60_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 9;}

                        else if ( LA60_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 10;}

                        else if ( LA60_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 11;}

                        else if ( LA60_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 12;}

                        else if ( LA60_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 13;}

                        else if ( LA60_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 14;}

                        else if ( LA60_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 15;}

                        else if ( LA60_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 16;}

                        else if ( LA60_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 17;}

                        else if ( LA60_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 18;}

                        else if ( LA60_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 19;}

                        else if ( LA60_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 20;}

                        else if ( LA60_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 21;}

                        else if ( LA60_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 22;}

                        else if ( (LA60_0==EOF) ) {s = 23;}

                         
                        input.seek(index60_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 60, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x01001C54FA784900L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000142040202200L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0081000000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000048200000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000048200000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004010000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004010002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000020100020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x01801C54FA784900L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0480000000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0006000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0210440800000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00000044BBDCDDF2L});

}
