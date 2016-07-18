package eu.numberfour.n4js.n4mf.parser.antlr.internal; 

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
import eu.numberfour.n4js.n4mf.services.N4MFGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalN4MFParser extends AbstractInternalAntlrParser {
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
    	 	
    	public InternalN4MFParser(TokenStream input, N4MFGrammarAccess grammarAccess) {
    		this(input);
    		this.grammarAccess = grammarAccess;
    		registerRules(grammarAccess.getGrammar());
    	}
    	
    	@Override
    	protected String getFirstRuleName() {
    		return "ProjectDescription";	
    	} 
    	   	   	
    	@Override
    	protected N4MFGrammarAccess getGrammarAccess() {
    		return grammarAccess;
    	}



    // $ANTLR start "entryRuleProjectDescription"
    // InternalN4MFParser.g:62:1: entryRuleProjectDescription returns [EObject current=null] : iv_ruleProjectDescription= ruleProjectDescription EOF ;
    public final EObject entryRuleProjectDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectDescription = null;


        try {
            // InternalN4MFParser.g:63:2: (iv_ruleProjectDescription= ruleProjectDescription EOF )
            // InternalN4MFParser.g:64:2: iv_ruleProjectDescription= ruleProjectDescription EOF
            {
             newCompositeNode(grammarAccess.getProjectDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProjectDescription=ruleProjectDescription();

            state._fsp--;

             current =iv_ruleProjectDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProjectDescription"


    // $ANTLR start "ruleProjectDescription"
    // InternalN4MFParser.g:71:1: ruleProjectDescription returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleProjectDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token this_STRING_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token lv_vendorName_19_0=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token lv_mainModule_22_0=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        Token lv_outputPath_35_0=null;
        Token otherlv_36=null;
        Token otherlv_37=null;
        Token lv_libraryPaths_38_0=null;
        Token otherlv_39=null;
        Token lv_libraryPaths_40_0=null;
        Token otherlv_41=null;
        Token otherlv_42=null;
        Token otherlv_43=null;
        Token lv_resourcePaths_44_0=null;
        Token otherlv_45=null;
        Token lv_resourcePaths_46_0=null;
        Token otherlv_47=null;
        Token otherlv_48=null;
        Token otherlv_49=null;
        Token otherlv_51=null;
        Token otherlv_52=null;
        Token otherlv_53=null;
        Token otherlv_55=null;
        Token otherlv_57=null;
        Token otherlv_58=null;
        AntlrDatatypeRuleToken lv_projectId_4_0 = null;

        Enumerator lv_projectType_10_0 = null;

        EObject lv_projectVersion_13_0 = null;

        AntlrDatatypeRuleToken lv_declaredVendorId_16_0 = null;

        EObject lv_extendedRuntimeEnvironment_23_0 = null;

        EObject lv_providedRuntimeLibraries_24_0 = null;

        EObject lv_requiredRuntimeLibraries_25_0 = null;

        EObject lv_projectDependencies_26_0 = null;

        AntlrDatatypeRuleToken lv_implementationId_29_0 = null;

        EObject lv_implementedProjects_30_0 = null;

        EObject lv_initModules_31_0 = null;

        EObject lv_execModule_32_0 = null;

        EObject lv_sourceFragment_50_0 = null;

        EObject lv_moduleFilters_54_0 = null;

        EObject lv_testedProjects_56_0 = null;

        Enumerator lv_moduleLoader_59_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:74:28: ( ( ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalN4MFParser.g:75:1: ( ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) )
            {
            // InternalN4MFParser.g:75:1: ( ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) )
            // InternalN4MFParser.g:77:1: ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalN4MFParser.g:77:1: ( ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) )
            // InternalN4MFParser.g:78:2: ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?)
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	
            // InternalN4MFParser.g:81:2: ( ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?)
            // InternalN4MFParser.g:82:3: ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?
            {
            // InternalN4MFParser.g:82:3: ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=23;
                alt6 = dfa6.predict(input);
                switch (alt6) {
            	case 1 :
            	    // InternalN4MFParser.g:84:4: ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:84:4: ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:85:5: {...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalN4MFParser.g:85:113: ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:86:6: ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0);
            	    	 				
            	    // InternalN4MFParser.g:89:6: ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:89:7: {...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:89:16: ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:89:17: (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) )
            	    {
            	    // InternalN4MFParser.g:89:17: (otherlv_1= ArtifactId | otherlv_2= ProjectId )
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
            	            // InternalN4MFParser.g:90:2: otherlv_1= ArtifactId
            	            {
            	            otherlv_1=(Token)match(input,ArtifactId,FOLLOW_3); 

            	                	newLeafNode(otherlv_1, grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0_0());
            	                

            	            }
            	            break;
            	        case 2 :
            	            // InternalN4MFParser.g:96:2: otherlv_2= ProjectId
            	            {
            	            otherlv_2=(Token)match(input,ProjectId,FOLLOW_3); 

            	                	newLeafNode(otherlv_2, grammarAccess.getProjectDescriptionAccess().getProjectIdKeyword_0_0_1());
            	                

            	            }
            	            break;

            	    }

            	    otherlv_3=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_3, grammarAccess.getProjectDescriptionAccess().getColonKeyword_0_1());
            	        
            	    // InternalN4MFParser.g:105:1: ( (lv_projectId_4_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:106:1: (lv_projectId_4_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:106:1: (lv_projectId_4_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:107:3: lv_projectId_4_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_0_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectId_4_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectId",
            	            		lv_projectId_4_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalN4MFParser.g:130:4: ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) )
            	    {
            	    // InternalN4MFParser.g:130:4: ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:131:5: {...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalN4MFParser.g:131:113: ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:132:6: ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1);
            	    	 				
            	    // InternalN4MFParser.g:135:6: ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) )
            	    // InternalN4MFParser.g:135:7: {...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:135:16: (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING )
            	    // InternalN4MFParser.g:136:2: otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING
            	    {
            	    otherlv_5=(Token)match(input,ProjectName,FOLLOW_3); 

            	        	newLeafNode(otherlv_5, grammarAccess.getProjectDescriptionAccess().getProjectNameKeyword_1_0());
            	        
            	    otherlv_6=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_6, grammarAccess.getProjectDescriptionAccess().getColonKeyword_1_1());
            	        
            	    this_STRING_7=(Token)match(input,RULE_STRING,FOLLOW_5); 
            	     
            	        newLeafNode(this_STRING_7, grammarAccess.getProjectDescriptionAccess().getSTRINGTerminalRuleCall_1_2()); 
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalN4MFParser.g:156:4: ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:156:4: ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) )
            	    // InternalN4MFParser.g:157:5: {...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalN4MFParser.g:157:113: ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) )
            	    // InternalN4MFParser.g:158:6: ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2);
            	    	 				
            	    // InternalN4MFParser.g:161:6: ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) )
            	    // InternalN4MFParser.g:161:7: {...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:161:16: (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) )
            	    // InternalN4MFParser.g:162:2: otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) )
            	    {
            	    otherlv_8=(Token)match(input,ProjectType,FOLLOW_3); 

            	        	newLeafNode(otherlv_8, grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_2_0());
            	        
            	    otherlv_9=(Token)match(input,Colon,FOLLOW_7); 

            	        	newLeafNode(otherlv_9, grammarAccess.getProjectDescriptionAccess().getColonKeyword_2_1());
            	        
            	    // InternalN4MFParser.g:171:1: ( (lv_projectType_10_0= ruleProjectType ) )
            	    // InternalN4MFParser.g:172:1: (lv_projectType_10_0= ruleProjectType )
            	    {
            	    // InternalN4MFParser.g:172:1: (lv_projectType_10_0= ruleProjectType )
            	    // InternalN4MFParser.g:173:3: lv_projectType_10_0= ruleProjectType
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_2_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectType_10_0=ruleProjectType();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectType",
            	            		lv_projectType_10_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ProjectType");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalN4MFParser.g:196:4: ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:196:4: ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) )
            	    // InternalN4MFParser.g:197:5: {...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalN4MFParser.g:197:113: ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) )
            	    // InternalN4MFParser.g:198:6: ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3);
            	    	 				
            	    // InternalN4MFParser.g:201:6: ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) )
            	    // InternalN4MFParser.g:201:7: {...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:201:16: (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) )
            	    // InternalN4MFParser.g:202:2: otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) )
            	    {
            	    otherlv_11=(Token)match(input,ProjectVersion,FOLLOW_3); 

            	        	newLeafNode(otherlv_11, grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_3_0());
            	        
            	    otherlv_12=(Token)match(input,Colon,FOLLOW_8); 

            	        	newLeafNode(otherlv_12, grammarAccess.getProjectDescriptionAccess().getColonKeyword_3_1());
            	        
            	    // InternalN4MFParser.g:211:1: ( (lv_projectVersion_13_0= ruleDeclaredVersion ) )
            	    // InternalN4MFParser.g:212:1: (lv_projectVersion_13_0= ruleDeclaredVersion )
            	    {
            	    // InternalN4MFParser.g:212:1: (lv_projectVersion_13_0= ruleDeclaredVersion )
            	    // InternalN4MFParser.g:213:3: lv_projectVersion_13_0= ruleDeclaredVersion
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_3_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectVersion_13_0=ruleDeclaredVersion();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectVersion",
            	            		lv_projectVersion_13_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.DeclaredVersion");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalN4MFParser.g:236:4: ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:236:4: ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:237:5: {...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalN4MFParser.g:237:113: ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:238:6: ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4);
            	    	 				
            	    // InternalN4MFParser.g:241:6: ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:241:7: {...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:241:16: (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:242:2: otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) )
            	    {
            	    otherlv_14=(Token)match(input,VendorId,FOLLOW_3); 

            	        	newLeafNode(otherlv_14, grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_4_0());
            	        
            	    otherlv_15=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_15, grammarAccess.getProjectDescriptionAccess().getColonKeyword_4_1());
            	        
            	    // InternalN4MFParser.g:251:1: ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:252:1: (lv_declaredVendorId_16_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:252:1: (lv_declaredVendorId_16_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:253:3: lv_declaredVendorId_16_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_4_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_declaredVendorId_16_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"declaredVendorId",
            	            		lv_declaredVendorId_16_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalN4MFParser.g:276:4: ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:276:4: ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:277:5: {...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalN4MFParser.g:277:113: ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:278:6: ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5);
            	    	 				
            	    // InternalN4MFParser.g:281:6: ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:281:7: {...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:281:16: (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:282:2: otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) )
            	    {
            	    otherlv_17=(Token)match(input,VendorName,FOLLOW_3); 

            	        	newLeafNode(otherlv_17, grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_5_0());
            	        
            	    otherlv_18=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_18, grammarAccess.getProjectDescriptionAccess().getColonKeyword_5_1());
            	        
            	    // InternalN4MFParser.g:291:1: ( (lv_vendorName_19_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:292:1: (lv_vendorName_19_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:292:1: (lv_vendorName_19_0= RULE_STRING )
            	    // InternalN4MFParser.g:293:3: lv_vendorName_19_0= RULE_STRING
            	    {
            	    lv_vendorName_19_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_vendorName_19_0, grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_5_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"vendorName",
            	            		lv_vendorName_19_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalN4MFParser.g:316:4: ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:316:4: ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:317:5: {...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6)");
            	    }
            	    // InternalN4MFParser.g:317:113: ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:318:6: ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6);
            	    	 				
            	    // InternalN4MFParser.g:321:6: ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:321:7: {...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:321:16: (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:322:2: otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) )
            	    {
            	    otherlv_20=(Token)match(input,MainModule,FOLLOW_3); 

            	        	newLeafNode(otherlv_20, grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_6_0());
            	        
            	    otherlv_21=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_21, grammarAccess.getProjectDescriptionAccess().getColonKeyword_6_1());
            	        
            	    // InternalN4MFParser.g:331:1: ( (lv_mainModule_22_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:332:1: (lv_mainModule_22_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:332:1: (lv_mainModule_22_0= RULE_STRING )
            	    // InternalN4MFParser.g:333:3: lv_mainModule_22_0= RULE_STRING
            	    {
            	    lv_mainModule_22_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_mainModule_22_0, grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_6_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"mainModule",
            	            		lv_mainModule_22_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalN4MFParser.g:356:4: ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:356:4: ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) )
            	    // InternalN4MFParser.g:357:5: {...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7)");
            	    }
            	    // InternalN4MFParser.g:357:113: ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) )
            	    // InternalN4MFParser.g:358:6: ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7);
            	    	 				
            	    // InternalN4MFParser.g:361:6: ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) )
            	    // InternalN4MFParser.g:361:7: {...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:361:16: ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) )
            	    // InternalN4MFParser.g:362:1: (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment )
            	    {
            	    // InternalN4MFParser.g:362:1: (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment )
            	    // InternalN4MFParser.g:363:3: lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_extendedRuntimeEnvironment_23_0=ruleExtendedRuntimeEnvironment();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"extendedRuntimeEnvironment",
            	            		lv_extendedRuntimeEnvironment_23_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ExtendedRuntimeEnvironment");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 9 :
            	    // InternalN4MFParser.g:386:4: ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:386:4: ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) )
            	    // InternalN4MFParser.g:387:5: {...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8)");
            	    }
            	    // InternalN4MFParser.g:387:113: ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) )
            	    // InternalN4MFParser.g:388:6: ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8);
            	    	 				
            	    // InternalN4MFParser.g:391:6: ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) )
            	    // InternalN4MFParser.g:391:7: {...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:391:16: ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) )
            	    // InternalN4MFParser.g:392:1: (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries )
            	    {
            	    // InternalN4MFParser.g:392:1: (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries )
            	    // InternalN4MFParser.g:393:3: lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_providedRuntimeLibraries_24_0=ruleProvidedRuntimeLibraries();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"providedRuntimeLibraries",
            	            		lv_providedRuntimeLibraries_24_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ProvidedRuntimeLibraries");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 10 :
            	    // InternalN4MFParser.g:416:4: ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:416:4: ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) )
            	    // InternalN4MFParser.g:417:5: {...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9)");
            	    }
            	    // InternalN4MFParser.g:417:113: ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) )
            	    // InternalN4MFParser.g:418:6: ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9);
            	    	 				
            	    // InternalN4MFParser.g:421:6: ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) )
            	    // InternalN4MFParser.g:421:7: {...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:421:16: ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) )
            	    // InternalN4MFParser.g:422:1: (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries )
            	    {
            	    // InternalN4MFParser.g:422:1: (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries )
            	    // InternalN4MFParser.g:423:3: lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_requiredRuntimeLibraries_25_0=ruleRequiredRuntimeLibraries();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"requiredRuntimeLibraries",
            	            		lv_requiredRuntimeLibraries_25_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.RequiredRuntimeLibraries");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 11 :
            	    // InternalN4MFParser.g:446:4: ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:446:4: ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) )
            	    // InternalN4MFParser.g:447:5: {...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10)");
            	    }
            	    // InternalN4MFParser.g:447:114: ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) )
            	    // InternalN4MFParser.g:448:6: ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10);
            	    	 				
            	    // InternalN4MFParser.g:451:6: ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) )
            	    // InternalN4MFParser.g:451:7: {...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:451:16: ( (lv_projectDependencies_26_0= ruleProjectDependencies ) )
            	    // InternalN4MFParser.g:452:1: (lv_projectDependencies_26_0= ruleProjectDependencies )
            	    {
            	    // InternalN4MFParser.g:452:1: (lv_projectDependencies_26_0= ruleProjectDependencies )
            	    // InternalN4MFParser.g:453:3: lv_projectDependencies_26_0= ruleProjectDependencies
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectDependencies_26_0=ruleProjectDependencies();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectDependencies",
            	            		lv_projectDependencies_26_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ProjectDependencies");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 12 :
            	    // InternalN4MFParser.g:476:4: ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:476:4: ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:477:5: {...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11)");
            	    }
            	    // InternalN4MFParser.g:477:114: ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:478:6: ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11);
            	    	 				
            	    // InternalN4MFParser.g:481:6: ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:481:7: {...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:481:16: (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:482:2: otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) )
            	    {
            	    otherlv_27=(Token)match(input,ImplementationId,FOLLOW_3); 

            	        	newLeafNode(otherlv_27, grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_11_0());
            	        
            	    otherlv_28=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_28, grammarAccess.getProjectDescriptionAccess().getColonKeyword_11_1());
            	        
            	    // InternalN4MFParser.g:491:1: ( (lv_implementationId_29_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:492:1: (lv_implementationId_29_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:492:1: (lv_implementationId_29_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:493:3: lv_implementationId_29_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_11_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_implementationId_29_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"implementationId",
            	            		lv_implementationId_29_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 13 :
            	    // InternalN4MFParser.g:516:4: ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:516:4: ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) )
            	    // InternalN4MFParser.g:517:5: {...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12)");
            	    }
            	    // InternalN4MFParser.g:517:114: ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) )
            	    // InternalN4MFParser.g:518:6: ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12);
            	    	 				
            	    // InternalN4MFParser.g:521:6: ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) )
            	    // InternalN4MFParser.g:521:7: {...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:521:16: ( (lv_implementedProjects_30_0= ruleImplementedProjects ) )
            	    // InternalN4MFParser.g:522:1: (lv_implementedProjects_30_0= ruleImplementedProjects )
            	    {
            	    // InternalN4MFParser.g:522:1: (lv_implementedProjects_30_0= ruleImplementedProjects )
            	    // InternalN4MFParser.g:523:3: lv_implementedProjects_30_0= ruleImplementedProjects
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_12_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_implementedProjects_30_0=ruleImplementedProjects();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"implementedProjects",
            	            		lv_implementedProjects_30_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ImplementedProjects");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 14 :
            	    // InternalN4MFParser.g:546:4: ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:546:4: ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) )
            	    // InternalN4MFParser.g:547:5: {...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13)");
            	    }
            	    // InternalN4MFParser.g:547:114: ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) )
            	    // InternalN4MFParser.g:548:6: ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13);
            	    	 				
            	    // InternalN4MFParser.g:551:6: ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) )
            	    // InternalN4MFParser.g:551:7: {...}? => ( (lv_initModules_31_0= ruleInitModules ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:551:16: ( (lv_initModules_31_0= ruleInitModules ) )
            	    // InternalN4MFParser.g:552:1: (lv_initModules_31_0= ruleInitModules )
            	    {
            	    // InternalN4MFParser.g:552:1: (lv_initModules_31_0= ruleInitModules )
            	    // InternalN4MFParser.g:553:3: lv_initModules_31_0= ruleInitModules
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_initModules_31_0=ruleInitModules();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"initModules",
            	            		lv_initModules_31_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.InitModules");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 15 :
            	    // InternalN4MFParser.g:576:4: ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:576:4: ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) )
            	    // InternalN4MFParser.g:577:5: {...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14)");
            	    }
            	    // InternalN4MFParser.g:577:114: ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) )
            	    // InternalN4MFParser.g:578:6: ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14);
            	    	 				
            	    // InternalN4MFParser.g:581:6: ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) )
            	    // InternalN4MFParser.g:581:7: {...}? => ( (lv_execModule_32_0= ruleExecModule ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:581:16: ( (lv_execModule_32_0= ruleExecModule ) )
            	    // InternalN4MFParser.g:582:1: (lv_execModule_32_0= ruleExecModule )
            	    {
            	    // InternalN4MFParser.g:582:1: (lv_execModule_32_0= ruleExecModule )
            	    // InternalN4MFParser.g:583:3: lv_execModule_32_0= ruleExecModule
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_14_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_execModule_32_0=ruleExecModule();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"execModule",
            	            		lv_execModule_32_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ExecModule");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 16 :
            	    // InternalN4MFParser.g:606:4: ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:606:4: ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:607:5: {...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15)");
            	    }
            	    // InternalN4MFParser.g:607:114: ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:608:6: ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15);
            	    	 				
            	    // InternalN4MFParser.g:611:6: ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:611:7: {...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:611:16: (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:612:2: otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) )
            	    {
            	    otherlv_33=(Token)match(input,Output,FOLLOW_3); 

            	        	newLeafNode(otherlv_33, grammarAccess.getProjectDescriptionAccess().getOutputKeyword_15_0());
            	        
            	    otherlv_34=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_34, grammarAccess.getProjectDescriptionAccess().getColonKeyword_15_1());
            	        
            	    // InternalN4MFParser.g:621:1: ( (lv_outputPath_35_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:622:1: (lv_outputPath_35_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:622:1: (lv_outputPath_35_0= RULE_STRING )
            	    // InternalN4MFParser.g:623:3: lv_outputPath_35_0= RULE_STRING
            	    {
            	    lv_outputPath_35_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_outputPath_35_0, grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_15_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"outputPath",
            	            		lv_outputPath_35_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 17 :
            	    // InternalN4MFParser.g:646:4: ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:646:4: ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:647:5: {...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16)");
            	    }
            	    // InternalN4MFParser.g:647:114: ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:648:6: ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16);
            	    	 				
            	    // InternalN4MFParser.g:651:6: ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:651:7: {...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:651:16: (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket )
            	    // InternalN4MFParser.g:652:2: otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket
            	    {
            	    otherlv_36=(Token)match(input,Libraries,FOLLOW_9); 

            	        	newLeafNode(otherlv_36, grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_16_0());
            	        
            	    otherlv_37=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

            	        	newLeafNode(otherlv_37, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_16_1());
            	        
            	    // InternalN4MFParser.g:661:1: ( (lv_libraryPaths_38_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:662:1: (lv_libraryPaths_38_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:662:1: (lv_libraryPaths_38_0= RULE_STRING )
            	    // InternalN4MFParser.g:663:3: lv_libraryPaths_38_0= RULE_STRING
            	    {
            	    lv_libraryPaths_38_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    			newLeafNode(lv_libraryPaths_38_0, grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"libraryPaths",
            	            		lv_libraryPaths_38_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }

            	    // InternalN4MFParser.g:679:2: (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )*
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==Comma) ) {
            	            alt2=1;
            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:680:2: otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) )
            	    	    {
            	    	    otherlv_39=(Token)match(input,Comma,FOLLOW_6); 

            	    	        	newLeafNode(otherlv_39, grammarAccess.getProjectDescriptionAccess().getCommaKeyword_16_3_0());
            	    	        
            	    	    // InternalN4MFParser.g:684:1: ( (lv_libraryPaths_40_0= RULE_STRING ) )
            	    	    // InternalN4MFParser.g:685:1: (lv_libraryPaths_40_0= RULE_STRING )
            	    	    {
            	    	    // InternalN4MFParser.g:685:1: (lv_libraryPaths_40_0= RULE_STRING )
            	    	    // InternalN4MFParser.g:686:3: lv_libraryPaths_40_0= RULE_STRING
            	    	    {
            	    	    lv_libraryPaths_40_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    	    			newLeafNode(lv_libraryPaths_40_0, grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_3_1_0()); 
            	    	    		

            	    	    	        if (current==null) {
            	    	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		addWithLastConsumed(
            	    	           			current, 
            	    	           			"libraryPaths",
            	    	            		lv_libraryPaths_40_0, 
            	    	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    	    

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop2;
            	        }
            	    } while (true);

            	    otherlv_41=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_41, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_16_4());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 18 :
            	    // InternalN4MFParser.g:714:4: ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:714:4: ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:715:5: {...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17)");
            	    }
            	    // InternalN4MFParser.g:715:114: ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:716:6: ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17);
            	    	 				
            	    // InternalN4MFParser.g:719:6: ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:719:7: {...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:719:16: (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket )
            	    // InternalN4MFParser.g:720:2: otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket
            	    {
            	    otherlv_42=(Token)match(input,Resources,FOLLOW_9); 

            	        	newLeafNode(otherlv_42, grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_17_0());
            	        
            	    otherlv_43=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

            	        	newLeafNode(otherlv_43, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_17_1());
            	        
            	    // InternalN4MFParser.g:729:1: ( (lv_resourcePaths_44_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:730:1: (lv_resourcePaths_44_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:730:1: (lv_resourcePaths_44_0= RULE_STRING )
            	    // InternalN4MFParser.g:731:3: lv_resourcePaths_44_0= RULE_STRING
            	    {
            	    lv_resourcePaths_44_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    			newLeafNode(lv_resourcePaths_44_0, grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"resourcePaths",
            	            		lv_resourcePaths_44_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }

            	    // InternalN4MFParser.g:747:2: (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )*
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==Comma) ) {
            	            alt3=1;
            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:748:2: otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) )
            	    	    {
            	    	    otherlv_45=(Token)match(input,Comma,FOLLOW_6); 

            	    	        	newLeafNode(otherlv_45, grammarAccess.getProjectDescriptionAccess().getCommaKeyword_17_3_0());
            	    	        
            	    	    // InternalN4MFParser.g:752:1: ( (lv_resourcePaths_46_0= RULE_STRING ) )
            	    	    // InternalN4MFParser.g:753:1: (lv_resourcePaths_46_0= RULE_STRING )
            	    	    {
            	    	    // InternalN4MFParser.g:753:1: (lv_resourcePaths_46_0= RULE_STRING )
            	    	    // InternalN4MFParser.g:754:3: lv_resourcePaths_46_0= RULE_STRING
            	    	    {
            	    	    lv_resourcePaths_46_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    	    			newLeafNode(lv_resourcePaths_46_0, grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_3_1_0()); 
            	    	    		

            	    	    	        if (current==null) {
            	    	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		addWithLastConsumed(
            	    	           			current, 
            	    	           			"resourcePaths",
            	    	            		lv_resourcePaths_46_0, 
            	    	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    	    

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop3;
            	        }
            	    } while (true);

            	    otherlv_47=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_47, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_4());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 19 :
            	    // InternalN4MFParser.g:782:4: ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:782:4: ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:783:5: {...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18)");
            	    }
            	    // InternalN4MFParser.g:783:114: ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:784:6: ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18);
            	    	 				
            	    // InternalN4MFParser.g:787:6: ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:787:7: {...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:787:16: (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket )
            	    // InternalN4MFParser.g:788:2: otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket
            	    {
            	    otherlv_48=(Token)match(input,Sources,FOLLOW_9); 

            	        	newLeafNode(otherlv_48, grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_18_0());
            	        
            	    otherlv_49=(Token)match(input,LeftCurlyBracket,FOLLOW_11); 

            	        	newLeafNode(otherlv_49, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_18_1());
            	        
            	    // InternalN4MFParser.g:797:1: ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+
            	    int cnt4=0;
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==External||LA4_0==Source||LA4_0==Test) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:798:1: (lv_sourceFragment_50_0= ruleSourceFragment )
            	    	    {
            	    	    // InternalN4MFParser.g:798:1: (lv_sourceFragment_50_0= ruleSourceFragment )
            	    	    // InternalN4MFParser.g:799:3: lv_sourceFragment_50_0= ruleSourceFragment
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_18_2_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_12);
            	    	    lv_sourceFragment_50_0=ruleSourceFragment();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"sourceFragment",
            	    	            		lv_sourceFragment_50_0, 
            	    	            		"eu.numberfour.n4js.n4mf.N4MF.SourceFragment");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt4 >= 1 ) break loop4;
            	                EarlyExitException eee =
            	                    new EarlyExitException(4, input);
            	                throw eee;
            	        }
            	        cnt4++;
            	    } while (true);

            	    otherlv_51=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_51, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_18_3());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 20 :
            	    // InternalN4MFParser.g:827:4: ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:827:4: ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:828:5: {...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19)");
            	    }
            	    // InternalN4MFParser.g:828:114: ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:829:6: ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19);
            	    	 				
            	    // InternalN4MFParser.g:832:6: ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:832:7: {...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:832:16: (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket )
            	    // InternalN4MFParser.g:833:2: otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket
            	    {
            	    otherlv_52=(Token)match(input,ModuleFilters,FOLLOW_9); 

            	        	newLeafNode(otherlv_52, grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_19_0());
            	        
            	    otherlv_53=(Token)match(input,LeftCurlyBracket,FOLLOW_13); 

            	        	newLeafNode(otherlv_53, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_19_1());
            	        
            	    // InternalN4MFParser.g:842:1: ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+
            	    int cnt5=0;
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( (LA5_0==NoModuleWrap||LA5_0==NoValidate) ) {
            	            alt5=1;
            	        }


            	        switch (alt5) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:843:1: (lv_moduleFilters_54_0= ruleModuleFilter )
            	    	    {
            	    	    // InternalN4MFParser.g:843:1: (lv_moduleFilters_54_0= ruleModuleFilter )
            	    	    // InternalN4MFParser.g:844:3: lv_moduleFilters_54_0= ruleModuleFilter
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_19_2_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_14);
            	    	    lv_moduleFilters_54_0=ruleModuleFilter();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"moduleFilters",
            	    	            		lv_moduleFilters_54_0, 
            	    	            		"eu.numberfour.n4js.n4mf.N4MF.ModuleFilter");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt5 >= 1 ) break loop5;
            	                EarlyExitException eee =
            	                    new EarlyExitException(5, input);
            	                throw eee;
            	        }
            	        cnt5++;
            	    } while (true);

            	    otherlv_55=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_55, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_19_3());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 21 :
            	    // InternalN4MFParser.g:872:4: ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:872:4: ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) )
            	    // InternalN4MFParser.g:873:5: {...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20)");
            	    }
            	    // InternalN4MFParser.g:873:114: ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) )
            	    // InternalN4MFParser.g:874:6: ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20);
            	    	 				
            	    // InternalN4MFParser.g:877:6: ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) )
            	    // InternalN4MFParser.g:877:7: {...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:877:16: ( (lv_testedProjects_56_0= ruleTestedProjects ) )
            	    // InternalN4MFParser.g:878:1: (lv_testedProjects_56_0= ruleTestedProjects )
            	    {
            	    // InternalN4MFParser.g:878:1: (lv_testedProjects_56_0= ruleTestedProjects )
            	    // InternalN4MFParser.g:879:3: lv_testedProjects_56_0= ruleTestedProjects
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_20_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_testedProjects_56_0=ruleTestedProjects();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"testedProjects",
            	            		lv_testedProjects_56_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.TestedProjects");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 22 :
            	    // InternalN4MFParser.g:902:4: ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:902:4: ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) )
            	    // InternalN4MFParser.g:903:5: {...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21)");
            	    }
            	    // InternalN4MFParser.g:903:114: ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) )
            	    // InternalN4MFParser.g:904:6: ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21);
            	    	 				
            	    // InternalN4MFParser.g:907:6: ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) )
            	    // InternalN4MFParser.g:907:7: {...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:907:16: (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) )
            	    // InternalN4MFParser.g:908:2: otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) )
            	    {
            	    otherlv_57=(Token)match(input,ModuleLoader,FOLLOW_3); 

            	        	newLeafNode(otherlv_57, grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_21_0());
            	        
            	    otherlv_58=(Token)match(input,Colon,FOLLOW_15); 

            	        	newLeafNode(otherlv_58, grammarAccess.getProjectDescriptionAccess().getColonKeyword_21_1());
            	        
            	    // InternalN4MFParser.g:917:1: ( (lv_moduleLoader_59_0= ruleModuleLoader ) )
            	    // InternalN4MFParser.g:918:1: (lv_moduleLoader_59_0= ruleModuleLoader )
            	    {
            	    // InternalN4MFParser.g:918:1: (lv_moduleLoader_59_0= ruleModuleLoader )
            	    // InternalN4MFParser.g:919:3: lv_moduleLoader_59_0= ruleModuleLoader
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_21_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_moduleLoader_59_0=ruleModuleLoader();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"moduleLoader",
            	            		lv_moduleLoader_59_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ModuleLoader");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canLeave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup())");
            }

            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectDescription"


    // $ANTLR start "entryRuleExecModule"
    // InternalN4MFParser.g:958:1: entryRuleExecModule returns [EObject current=null] : iv_ruleExecModule= ruleExecModule EOF ;
    public final EObject entryRuleExecModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecModule = null;


        try {
            // InternalN4MFParser.g:959:2: (iv_ruleExecModule= ruleExecModule EOF )
            // InternalN4MFParser.g:960:2: iv_ruleExecModule= ruleExecModule EOF
            {
             newCompositeNode(grammarAccess.getExecModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExecModule=ruleExecModule();

            state._fsp--;

             current =iv_ruleExecModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExecModule"


    // $ANTLR start "ruleExecModule"
    // InternalN4MFParser.g:967:1: ruleExecModule returns [EObject current=null] : ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) ) ;
    public final EObject ruleExecModule() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_execModule_3_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:970:28: ( ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) ) )
            // InternalN4MFParser.g:971:1: ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) )
            {
            // InternalN4MFParser.g:971:1: ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) )
            // InternalN4MFParser.g:971:2: () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) )
            {
            // InternalN4MFParser.g:971:2: ()
            // InternalN4MFParser.g:972:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExecModuleAccess().getExecModuleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ExecModule,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getExecModuleAccess().getExecModuleKeyword_1());
                
            otherlv_2=(Token)match(input,Colon,FOLLOW_6); 

                	newLeafNode(otherlv_2, grammarAccess.getExecModuleAccess().getColonKeyword_2());
                
            // InternalN4MFParser.g:987:1: ( (lv_execModule_3_0= ruleBootstrapModule ) )
            // InternalN4MFParser.g:988:1: (lv_execModule_3_0= ruleBootstrapModule )
            {
            // InternalN4MFParser.g:988:1: (lv_execModule_3_0= ruleBootstrapModule )
            // InternalN4MFParser.g:989:3: lv_execModule_3_0= ruleBootstrapModule
            {
             
            	        newCompositeNode(grammarAccess.getExecModuleAccess().getExecModuleBootstrapModuleParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_execModule_3_0=ruleBootstrapModule();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExecModuleRule());
            	        }
                   		set(
                   			current, 
                   			"execModule",
                    		lv_execModule_3_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.BootstrapModule");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExecModule"


    // $ANTLR start "entryRuleTestedProjects"
    // InternalN4MFParser.g:1013:1: entryRuleTestedProjects returns [EObject current=null] : iv_ruleTestedProjects= ruleTestedProjects EOF ;
    public final EObject entryRuleTestedProjects() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestedProjects = null;


        try {
            // InternalN4MFParser.g:1014:2: (iv_ruleTestedProjects= ruleTestedProjects EOF )
            // InternalN4MFParser.g:1015:2: iv_ruleTestedProjects= ruleTestedProjects EOF
            {
             newCompositeNode(grammarAccess.getTestedProjectsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestedProjects=ruleTestedProjects();

            state._fsp--;

             current =iv_ruleTestedProjects; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestedProjects"


    // $ANTLR start "ruleTestedProjects"
    // InternalN4MFParser.g:1022:1: ruleTestedProjects returns [EObject current=null] : ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleTestedProjects() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_testedProjects_3_0 = null;

        EObject lv_testedProjects_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1025:28: ( ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1026:1: ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1026:1: ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1026:2: () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1026:2: ()
            // InternalN4MFParser.g:1027:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,TestedProjects,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getTestedProjectsAccess().getTestedProjectsKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getTestedProjectsAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1042:1: ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ProjectDependencies||LA8_0==ProjectVersion||LA8_0==ModuleFilters||(LA8_0>=ProjectName && LA8_0<=ArtifactId)||LA8_0==VendorName||(LA8_0>=Libraries && LA8_0<=VendorId)||LA8_0==Sources||LA8_0==Content||LA8_0==Output||(LA8_0>=Test && LA8_0<=API)||LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalN4MFParser.g:1042:2: ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )*
                    {
                    // InternalN4MFParser.g:1042:2: ( (lv_testedProjects_3_0= ruleTestedProject ) )
                    // InternalN4MFParser.g:1043:1: (lv_testedProjects_3_0= ruleTestedProject )
                    {
                    // InternalN4MFParser.g:1043:1: (lv_testedProjects_3_0= ruleTestedProject )
                    // InternalN4MFParser.g:1044:3: lv_testedProjects_3_0= ruleTestedProject
                    {
                     
                    	        newCompositeNode(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_testedProjects_3_0=ruleTestedProject();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTestedProjectsRule());
                    	        }
                           		add(
                           			current, 
                           			"testedProjects",
                            		lv_testedProjects_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.TestedProject");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1060:2: (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==Comma) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1061:2: otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getTestedProjectsAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1065:1: ( (lv_testedProjects_5_0= ruleTestedProject ) )
                    	    // InternalN4MFParser.g:1066:1: (lv_testedProjects_5_0= ruleTestedProject )
                    	    {
                    	    // InternalN4MFParser.g:1066:1: (lv_testedProjects_5_0= ruleTestedProject )
                    	    // InternalN4MFParser.g:1067:3: lv_testedProjects_5_0= ruleTestedProject
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTestedProjectsAccess().getTestedProjectsTestedProjectParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_testedProjects_5_0=ruleTestedProject();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getTestedProjectsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"testedProjects",
                    	            		lv_testedProjects_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.TestedProject");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getTestedProjectsAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTestedProjects"


    // $ANTLR start "entryRuleInitModules"
    // InternalN4MFParser.g:1096:1: entryRuleInitModules returns [EObject current=null] : iv_ruleInitModules= ruleInitModules EOF ;
    public final EObject entryRuleInitModules() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInitModules = null;


        try {
            // InternalN4MFParser.g:1097:2: (iv_ruleInitModules= ruleInitModules EOF )
            // InternalN4MFParser.g:1098:2: iv_ruleInitModules= ruleInitModules EOF
            {
             newCompositeNode(grammarAccess.getInitModulesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInitModules=ruleInitModules();

            state._fsp--;

             current =iv_ruleInitModules; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInitModules"


    // $ANTLR start "ruleInitModules"
    // InternalN4MFParser.g:1105:1: ruleInitModules returns [EObject current=null] : ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleInitModules() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_initModules_3_0 = null;

        EObject lv_initModules_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1108:28: ( ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1109:1: ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1109:1: ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1109:2: () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1109:2: ()
            // InternalN4MFParser.g:1110:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInitModulesAccess().getInitModulesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,InitModules,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getInitModulesAccess().getInitModulesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_17); 

                	newLeafNode(otherlv_2, grammarAccess.getInitModulesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1125:1: ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_STRING) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalN4MFParser.g:1125:2: ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )*
                    {
                    // InternalN4MFParser.g:1125:2: ( (lv_initModules_3_0= ruleBootstrapModule ) )
                    // InternalN4MFParser.g:1126:1: (lv_initModules_3_0= ruleBootstrapModule )
                    {
                    // InternalN4MFParser.g:1126:1: (lv_initModules_3_0= ruleBootstrapModule )
                    // InternalN4MFParser.g:1127:3: lv_initModules_3_0= ruleBootstrapModule
                    {
                     
                    	        newCompositeNode(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_initModules_3_0=ruleBootstrapModule();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInitModulesRule());
                    	        }
                           		add(
                           			current, 
                           			"initModules",
                            		lv_initModules_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.BootstrapModule");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1143:2: (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==Comma) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1144:2: otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_6); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getInitModulesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1148:1: ( (lv_initModules_5_0= ruleBootstrapModule ) )
                    	    // InternalN4MFParser.g:1149:1: (lv_initModules_5_0= ruleBootstrapModule )
                    	    {
                    	    // InternalN4MFParser.g:1149:1: (lv_initModules_5_0= ruleBootstrapModule )
                    	    // InternalN4MFParser.g:1150:3: lv_initModules_5_0= ruleBootstrapModule
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getInitModulesAccess().getInitModulesBootstrapModuleParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_initModules_5_0=ruleBootstrapModule();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getInitModulesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"initModules",
                    	            		lv_initModules_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.BootstrapModule");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getInitModulesAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInitModules"


    // $ANTLR start "entryRuleImplementedProjects"
    // InternalN4MFParser.g:1179:1: entryRuleImplementedProjects returns [EObject current=null] : iv_ruleImplementedProjects= ruleImplementedProjects EOF ;
    public final EObject entryRuleImplementedProjects() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImplementedProjects = null;


        try {
            // InternalN4MFParser.g:1180:2: (iv_ruleImplementedProjects= ruleImplementedProjects EOF )
            // InternalN4MFParser.g:1181:2: iv_ruleImplementedProjects= ruleImplementedProjects EOF
            {
             newCompositeNode(grammarAccess.getImplementedProjectsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImplementedProjects=ruleImplementedProjects();

            state._fsp--;

             current =iv_ruleImplementedProjects; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImplementedProjects"


    // $ANTLR start "ruleImplementedProjects"
    // InternalN4MFParser.g:1188:1: ruleImplementedProjects returns [EObject current=null] : ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleImplementedProjects() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_implementedProjects_3_0 = null;

        EObject lv_implementedProjects_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1191:28: ( ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1192:1: ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1192:1: ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1192:2: () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1192:2: ()
            // InternalN4MFParser.g:1193:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ImplementedProjects,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getImplementedProjectsAccess().getImplementedProjectsKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getImplementedProjectsAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1208:1: ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ProjectDependencies||LA12_0==ProjectVersion||LA12_0==ModuleFilters||(LA12_0>=ProjectName && LA12_0<=ArtifactId)||LA12_0==VendorName||(LA12_0>=Libraries && LA12_0<=VendorId)||LA12_0==Sources||LA12_0==Content||LA12_0==Output||(LA12_0>=Test && LA12_0<=API)||LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalN4MFParser.g:1208:2: ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )*
                    {
                    // InternalN4MFParser.g:1208:2: ( (lv_implementedProjects_3_0= ruleProjectReference ) )
                    // InternalN4MFParser.g:1209:1: (lv_implementedProjects_3_0= ruleProjectReference )
                    {
                    // InternalN4MFParser.g:1209:1: (lv_implementedProjects_3_0= ruleProjectReference )
                    // InternalN4MFParser.g:1210:3: lv_implementedProjects_3_0= ruleProjectReference
                    {
                     
                    	        newCompositeNode(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_implementedProjects_3_0=ruleProjectReference();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getImplementedProjectsRule());
                    	        }
                           		add(
                           			current, 
                           			"implementedProjects",
                            		lv_implementedProjects_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.ProjectReference");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1226:2: (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==Comma) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1227:2: otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getImplementedProjectsAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1231:1: ( (lv_implementedProjects_5_0= ruleProjectReference ) )
                    	    // InternalN4MFParser.g:1232:1: (lv_implementedProjects_5_0= ruleProjectReference )
                    	    {
                    	    // InternalN4MFParser.g:1232:1: (lv_implementedProjects_5_0= ruleProjectReference )
                    	    // InternalN4MFParser.g:1233:3: lv_implementedProjects_5_0= ruleProjectReference
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getImplementedProjectsAccess().getImplementedProjectsProjectReferenceParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_implementedProjects_5_0=ruleProjectReference();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getImplementedProjectsRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"implementedProjects",
                    	            		lv_implementedProjects_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.ProjectReference");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getImplementedProjectsAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImplementedProjects"


    // $ANTLR start "entryRuleProjectDependencies"
    // InternalN4MFParser.g:1262:1: entryRuleProjectDependencies returns [EObject current=null] : iv_ruleProjectDependencies= ruleProjectDependencies EOF ;
    public final EObject entryRuleProjectDependencies() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectDependencies = null;


        try {
            // InternalN4MFParser.g:1263:2: (iv_ruleProjectDependencies= ruleProjectDependencies EOF )
            // InternalN4MFParser.g:1264:2: iv_ruleProjectDependencies= ruleProjectDependencies EOF
            {
             newCompositeNode(grammarAccess.getProjectDependenciesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProjectDependencies=ruleProjectDependencies();

            state._fsp--;

             current =iv_ruleProjectDependencies; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProjectDependencies"


    // $ANTLR start "ruleProjectDependencies"
    // InternalN4MFParser.g:1271:1: ruleProjectDependencies returns [EObject current=null] : ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleProjectDependencies() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_projectDependencies_3_0 = null;

        EObject lv_projectDependencies_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1274:28: ( ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1275:1: ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1275:1: ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1275:2: () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1275:2: ()
            // InternalN4MFParser.g:1276:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ProjectDependencies,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getProjectDependenciesAccess().getProjectDependenciesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getProjectDependenciesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1291:1: ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ProjectDependencies||LA14_0==ProjectVersion||LA14_0==ModuleFilters||(LA14_0>=ProjectName && LA14_0<=ArtifactId)||LA14_0==VendorName||(LA14_0>=Libraries && LA14_0<=VendorId)||LA14_0==Sources||LA14_0==Content||LA14_0==Output||(LA14_0>=Test && LA14_0<=API)||LA14_0==RULE_ID) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalN4MFParser.g:1291:2: ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1291:2: ( (lv_projectDependencies_3_0= ruleProjectDependency ) )
                    // InternalN4MFParser.g:1292:1: (lv_projectDependencies_3_0= ruleProjectDependency )
                    {
                    // InternalN4MFParser.g:1292:1: (lv_projectDependencies_3_0= ruleProjectDependency )
                    // InternalN4MFParser.g:1293:3: lv_projectDependencies_3_0= ruleProjectDependency
                    {
                     
                    	        newCompositeNode(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_projectDependencies_3_0=ruleProjectDependency();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProjectDependenciesRule());
                    	        }
                           		add(
                           			current, 
                           			"projectDependencies",
                            		lv_projectDependencies_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.ProjectDependency");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1309:2: (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==Comma) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1310:2: otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getProjectDependenciesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1314:1: ( (lv_projectDependencies_5_0= ruleProjectDependency ) )
                    	    // InternalN4MFParser.g:1315:1: (lv_projectDependencies_5_0= ruleProjectDependency )
                    	    {
                    	    // InternalN4MFParser.g:1315:1: (lv_projectDependencies_5_0= ruleProjectDependency )
                    	    // InternalN4MFParser.g:1316:3: lv_projectDependencies_5_0= ruleProjectDependency
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProjectDependenciesAccess().getProjectDependenciesProjectDependencyParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_projectDependencies_5_0=ruleProjectDependency();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProjectDependenciesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"projectDependencies",
                    	            		lv_projectDependencies_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.ProjectDependency");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getProjectDependenciesAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectDependencies"


    // $ANTLR start "entryRuleProvidedRuntimeLibraries"
    // InternalN4MFParser.g:1345:1: entryRuleProvidedRuntimeLibraries returns [EObject current=null] : iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF ;
    public final EObject entryRuleProvidedRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedRuntimeLibraries = null;


        try {
            // InternalN4MFParser.g:1346:2: (iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF )
            // InternalN4MFParser.g:1347:2: iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF
            {
             newCompositeNode(grammarAccess.getProvidedRuntimeLibrariesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProvidedRuntimeLibraries=ruleProvidedRuntimeLibraries();

            state._fsp--;

             current =iv_ruleProvidedRuntimeLibraries; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProvidedRuntimeLibraries"


    // $ANTLR start "ruleProvidedRuntimeLibraries"
    // InternalN4MFParser.g:1354:1: ruleProvidedRuntimeLibraries returns [EObject current=null] : ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleProvidedRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_providedRuntimeLibraries_3_0 = null;

        EObject lv_providedRuntimeLibraries_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1357:28: ( ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1358:1: ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1358:1: ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1358:2: () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1358:2: ()
            // InternalN4MFParser.g:1359:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ProvidedRuntimeLibraries,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getProvidedRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1374:1: ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ProjectDependencies||LA16_0==ProjectVersion||LA16_0==ModuleFilters||(LA16_0>=ProjectName && LA16_0<=ArtifactId)||LA16_0==VendorName||(LA16_0>=Libraries && LA16_0<=VendorId)||LA16_0==Sources||LA16_0==Content||LA16_0==Output||(LA16_0>=Test && LA16_0<=API)||LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalN4MFParser.g:1374:2: ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1374:2: ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) )
                    // InternalN4MFParser.g:1375:1: (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency )
                    {
                    // InternalN4MFParser.g:1375:1: (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency )
                    // InternalN4MFParser.g:1376:3: lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency
                    {
                     
                    	        newCompositeNode(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_providedRuntimeLibraries_3_0=ruleProvidedRuntimeLibraryDependency();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProvidedRuntimeLibrariesRule());
                    	        }
                           		add(
                           			current, 
                           			"providedRuntimeLibraries",
                            		lv_providedRuntimeLibraries_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.ProvidedRuntimeLibraryDependency");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1392:2: (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==Comma) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1393:2: otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getProvidedRuntimeLibrariesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1397:1: ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) )
                    	    // InternalN4MFParser.g:1398:1: (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency )
                    	    {
                    	    // InternalN4MFParser.g:1398:1: (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency )
                    	    // InternalN4MFParser.g:1399:3: lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_providedRuntimeLibraries_5_0=ruleProvidedRuntimeLibraryDependency();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProvidedRuntimeLibrariesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"providedRuntimeLibraries",
                    	            		lv_providedRuntimeLibraries_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.ProvidedRuntimeLibraryDependency");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getProvidedRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProvidedRuntimeLibraries"


    // $ANTLR start "entryRuleRequiredRuntimeLibraries"
    // InternalN4MFParser.g:1428:1: entryRuleRequiredRuntimeLibraries returns [EObject current=null] : iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF ;
    public final EObject entryRuleRequiredRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredRuntimeLibraries = null;


        try {
            // InternalN4MFParser.g:1429:2: (iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF )
            // InternalN4MFParser.g:1430:2: iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF
            {
             newCompositeNode(grammarAccess.getRequiredRuntimeLibrariesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRequiredRuntimeLibraries=ruleRequiredRuntimeLibraries();

            state._fsp--;

             current =iv_ruleRequiredRuntimeLibraries; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRequiredRuntimeLibraries"


    // $ANTLR start "ruleRequiredRuntimeLibraries"
    // InternalN4MFParser.g:1437:1: ruleRequiredRuntimeLibraries returns [EObject current=null] : ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
    public final EObject ruleRequiredRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_requiredRuntimeLibraries_3_0 = null;

        EObject lv_requiredRuntimeLibraries_5_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1440:28: ( ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1441:1: ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1441:1: ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1441:2: () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1441:2: ()
            // InternalN4MFParser.g:1442:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,RequiredRuntimeLibraries,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getRequiredRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1457:1: ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ProjectDependencies||LA18_0==ProjectVersion||LA18_0==ModuleFilters||(LA18_0>=ProjectName && LA18_0<=ArtifactId)||LA18_0==VendorName||(LA18_0>=Libraries && LA18_0<=VendorId)||LA18_0==Sources||LA18_0==Content||LA18_0==Output||(LA18_0>=Test && LA18_0<=API)||LA18_0==RULE_ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalN4MFParser.g:1457:2: ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1457:2: ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) )
                    // InternalN4MFParser.g:1458:1: (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency )
                    {
                    // InternalN4MFParser.g:1458:1: (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency )
                    // InternalN4MFParser.g:1459:3: lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency
                    {
                     
                    	        newCompositeNode(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_10);
                    lv_requiredRuntimeLibraries_3_0=ruleRequiredRuntimeLibraryDependency();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRequiredRuntimeLibrariesRule());
                    	        }
                           		add(
                           			current, 
                           			"requiredRuntimeLibraries",
                            		lv_requiredRuntimeLibraries_3_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.RequiredRuntimeLibraryDependency");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1475:2: (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==Comma) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1476:2: otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getRequiredRuntimeLibrariesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1480:1: ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) )
                    	    // InternalN4MFParser.g:1481:1: (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency )
                    	    {
                    	    // InternalN4MFParser.g:1481:1: (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency )
                    	    // InternalN4MFParser.g:1482:3: lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibraryDependencyParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_10);
                    	    lv_requiredRuntimeLibraries_5_0=ruleRequiredRuntimeLibraryDependency();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getRequiredRuntimeLibrariesRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"requiredRuntimeLibraries",
                    	            		lv_requiredRuntimeLibraries_5_0, 
                    	            		"eu.numberfour.n4js.n4mf.N4MF.RequiredRuntimeLibraryDependency");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_6, grammarAccess.getRequiredRuntimeLibrariesAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRequiredRuntimeLibraries"


    // $ANTLR start "entryRuleExtendedRuntimeEnvironment"
    // InternalN4MFParser.g:1511:1: entryRuleExtendedRuntimeEnvironment returns [EObject current=null] : iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF ;
    public final EObject entryRuleExtendedRuntimeEnvironment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtendedRuntimeEnvironment = null;


        try {
            // InternalN4MFParser.g:1512:2: (iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF )
            // InternalN4MFParser.g:1513:2: iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF
            {
             newCompositeNode(grammarAccess.getExtendedRuntimeEnvironmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtendedRuntimeEnvironment=ruleExtendedRuntimeEnvironment();

            state._fsp--;

             current =iv_ruleExtendedRuntimeEnvironment; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtendedRuntimeEnvironment"


    // $ANTLR start "ruleExtendedRuntimeEnvironment"
    // InternalN4MFParser.g:1520:1: ruleExtendedRuntimeEnvironment returns [EObject current=null] : ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) ) ;
    public final EObject ruleExtendedRuntimeEnvironment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_extendedRuntimeEnvironment_3_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1523:28: ( ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) ) )
            // InternalN4MFParser.g:1524:1: ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) )
            {
            // InternalN4MFParser.g:1524:1: ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) )
            // InternalN4MFParser.g:1524:2: () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) )
            {
            // InternalN4MFParser.g:1524:2: ()
            // InternalN4MFParser.g:1525:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ExtendedRuntimeEnvironment,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentKeyword_1());
                
            otherlv_2=(Token)match(input,Colon,FOLLOW_4); 

                	newLeafNode(otherlv_2, grammarAccess.getExtendedRuntimeEnvironmentAccess().getColonKeyword_2());
                
            // InternalN4MFParser.g:1540:1: ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) )
            // InternalN4MFParser.g:1541:1: (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference )
            {
            // InternalN4MFParser.g:1541:1: (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference )
            // InternalN4MFParser.g:1542:3: lv_extendedRuntimeEnvironment_3_0= ruleProjectReference
            {
             
            	        newCompositeNode(grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentProjectReferenceParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_extendedRuntimeEnvironment_3_0=ruleProjectReference();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExtendedRuntimeEnvironmentRule());
            	        }
                   		set(
                   			current, 
                   			"extendedRuntimeEnvironment",
                    		lv_extendedRuntimeEnvironment_3_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.ProjectReference");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtendedRuntimeEnvironment"


    // $ANTLR start "entryRuleDeclaredVersion"
    // InternalN4MFParser.g:1566:1: entryRuleDeclaredVersion returns [EObject current=null] : iv_ruleDeclaredVersion= ruleDeclaredVersion EOF ;
    public final EObject entryRuleDeclaredVersion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeclaredVersion = null;


        try {
            // InternalN4MFParser.g:1567:2: (iv_ruleDeclaredVersion= ruleDeclaredVersion EOF )
            // InternalN4MFParser.g:1568:2: iv_ruleDeclaredVersion= ruleDeclaredVersion EOF
            {
             newCompositeNode(grammarAccess.getDeclaredVersionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDeclaredVersion=ruleDeclaredVersion();

            state._fsp--;

             current =iv_ruleDeclaredVersion; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDeclaredVersion"


    // $ANTLR start "ruleDeclaredVersion"
    // InternalN4MFParser.g:1575:1: ruleDeclaredVersion returns [EObject current=null] : ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? ) ;
    public final EObject ruleDeclaredVersion() throws RecognitionException {
        EObject current = null;

        Token lv_major_0_0=null;
        Token otherlv_1=null;
        Token lv_minor_2_0=null;
        Token otherlv_3=null;
        Token lv_micro_4_0=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_qualifier_6_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1578:28: ( ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? ) )
            // InternalN4MFParser.g:1579:1: ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? )
            {
            // InternalN4MFParser.g:1579:1: ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? )
            // InternalN4MFParser.g:1579:2: ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )?
            {
            // InternalN4MFParser.g:1579:2: ( (lv_major_0_0= RULE_INT ) )
            // InternalN4MFParser.g:1580:1: (lv_major_0_0= RULE_INT )
            {
            // InternalN4MFParser.g:1580:1: (lv_major_0_0= RULE_INT )
            // InternalN4MFParser.g:1581:3: lv_major_0_0= RULE_INT
            {
            lv_major_0_0=(Token)match(input,RULE_INT,FOLLOW_18); 

            			newLeafNode(lv_major_0_0, grammarAccess.getDeclaredVersionAccess().getMajorINTTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDeclaredVersionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"major",
                    		lv_major_0_0, 
                    		"org.eclipse.xtext.common.Terminals.INT");
            	    

            }


            }

            // InternalN4MFParser.g:1597:2: (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==FullStop) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalN4MFParser.g:1598:2: otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )?
                    {
                    otherlv_1=(Token)match(input,FullStop,FOLLOW_8); 

                        	newLeafNode(otherlv_1, grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_0());
                        
                    // InternalN4MFParser.g:1602:1: ( (lv_minor_2_0= RULE_INT ) )
                    // InternalN4MFParser.g:1603:1: (lv_minor_2_0= RULE_INT )
                    {
                    // InternalN4MFParser.g:1603:1: (lv_minor_2_0= RULE_INT )
                    // InternalN4MFParser.g:1604:3: lv_minor_2_0= RULE_INT
                    {
                    lv_minor_2_0=(Token)match(input,RULE_INT,FOLLOW_18); 

                    			newLeafNode(lv_minor_2_0, grammarAccess.getDeclaredVersionAccess().getMinorINTTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getDeclaredVersionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"minor",
                            		lv_minor_2_0, 
                            		"org.eclipse.xtext.common.Terminals.INT");
                    	    

                    }


                    }

                    // InternalN4MFParser.g:1620:2: (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==FullStop) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // InternalN4MFParser.g:1621:2: otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) )
                            {
                            otherlv_3=(Token)match(input,FullStop,FOLLOW_8); 

                                	newLeafNode(otherlv_3, grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_2_0());
                                
                            // InternalN4MFParser.g:1625:1: ( (lv_micro_4_0= RULE_INT ) )
                            // InternalN4MFParser.g:1626:1: (lv_micro_4_0= RULE_INT )
                            {
                            // InternalN4MFParser.g:1626:1: (lv_micro_4_0= RULE_INT )
                            // InternalN4MFParser.g:1627:3: lv_micro_4_0= RULE_INT
                            {
                            lv_micro_4_0=(Token)match(input,RULE_INT,FOLLOW_19); 

                            			newLeafNode(lv_micro_4_0, grammarAccess.getDeclaredVersionAccess().getMicroINTTerminalRuleCall_1_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getDeclaredVersionRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"micro",
                                    		lv_micro_4_0, 
                                    		"org.eclipse.xtext.common.Terminals.INT");
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalN4MFParser.g:1643:6: (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==HyphenMinus) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalN4MFParser.g:1644:2: otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,HyphenMinus,FOLLOW_4); 

                        	newLeafNode(otherlv_5, grammarAccess.getDeclaredVersionAccess().getHyphenMinusKeyword_2_0());
                        
                    // InternalN4MFParser.g:1648:1: ( (lv_qualifier_6_0= ruleN4mfIdentifier ) )
                    // InternalN4MFParser.g:1649:1: (lv_qualifier_6_0= ruleN4mfIdentifier )
                    {
                    // InternalN4MFParser.g:1649:1: (lv_qualifier_6_0= ruleN4mfIdentifier )
                    // InternalN4MFParser.g:1650:3: lv_qualifier_6_0= ruleN4mfIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getDeclaredVersionAccess().getQualifierN4mfIdentifierParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_qualifier_6_0=ruleN4mfIdentifier();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getDeclaredVersionRule());
                    	        }
                           		set(
                           			current, 
                           			"qualifier",
                            		lv_qualifier_6_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDeclaredVersion"


    // $ANTLR start "entryRuleSourceFragment"
    // InternalN4MFParser.g:1674:1: entryRuleSourceFragment returns [EObject current=null] : iv_ruleSourceFragment= ruleSourceFragment EOF ;
    public final EObject entryRuleSourceFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSourceFragment = null;


        try {
            // InternalN4MFParser.g:1675:2: (iv_ruleSourceFragment= ruleSourceFragment EOF )
            // InternalN4MFParser.g:1676:2: iv_ruleSourceFragment= ruleSourceFragment EOF
            {
             newCompositeNode(grammarAccess.getSourceFragmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSourceFragment=ruleSourceFragment();

            state._fsp--;

             current =iv_ruleSourceFragment; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSourceFragment"


    // $ANTLR start "ruleSourceFragment"
    // InternalN4MFParser.g:1683:1: ruleSourceFragment returns [EObject current=null] : ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket ) ;
    public final EObject ruleSourceFragment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_paths_2_0=null;
        Token otherlv_3=null;
        Token lv_paths_4_0=null;
        Token otherlv_5=null;
        Enumerator lv_sourceFragmentType_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1686:28: ( ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket ) )
            // InternalN4MFParser.g:1687:1: ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1687:1: ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket )
            // InternalN4MFParser.g:1687:2: ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket
            {
            // InternalN4MFParser.g:1687:2: ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) )
            // InternalN4MFParser.g:1688:1: (lv_sourceFragmentType_0_0= ruleSourceFragmentType )
            {
            // InternalN4MFParser.g:1688:1: (lv_sourceFragmentType_0_0= ruleSourceFragmentType )
            // InternalN4MFParser.g:1689:3: lv_sourceFragmentType_0_0= ruleSourceFragmentType
            {
             
            	        newCompositeNode(grammarAccess.getSourceFragmentAccess().getSourceFragmentTypeSourceFragmentTypeEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_9);
            lv_sourceFragmentType_0_0=ruleSourceFragmentType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSourceFragmentRule());
            	        }
                   		set(
                   			current, 
                   			"sourceFragmentType",
                    		lv_sourceFragmentType_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SourceFragmentType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

                	newLeafNode(otherlv_1, grammarAccess.getSourceFragmentAccess().getLeftCurlyBracketKeyword_1());
                
            // InternalN4MFParser.g:1710:1: ( (lv_paths_2_0= RULE_STRING ) )
            // InternalN4MFParser.g:1711:1: (lv_paths_2_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1711:1: (lv_paths_2_0= RULE_STRING )
            // InternalN4MFParser.g:1712:3: lv_paths_2_0= RULE_STRING
            {
            lv_paths_2_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            			newLeafNode(lv_paths_2_0, grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSourceFragmentRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"paths",
                    		lv_paths_2_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalN4MFParser.g:1728:2: (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==Comma) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalN4MFParser.g:1729:2: otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) )
            	    {
            	    otherlv_3=(Token)match(input,Comma,FOLLOW_6); 

            	        	newLeafNode(otherlv_3, grammarAccess.getSourceFragmentAccess().getCommaKeyword_3_0());
            	        
            	    // InternalN4MFParser.g:1733:1: ( (lv_paths_4_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:1734:1: (lv_paths_4_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:1734:1: (lv_paths_4_0= RULE_STRING )
            	    // InternalN4MFParser.g:1735:3: lv_paths_4_0= RULE_STRING
            	    {
            	    lv_paths_4_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    			newLeafNode(lv_paths_4_0, grammarAccess.getSourceFragmentAccess().getPathsSTRINGTerminalRuleCall_3_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getSourceFragmentRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"paths",
            	            		lv_paths_4_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_5=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_5, grammarAccess.getSourceFragmentAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSourceFragment"


    // $ANTLR start "entryRuleModuleFilter"
    // InternalN4MFParser.g:1764:1: entryRuleModuleFilter returns [EObject current=null] : iv_ruleModuleFilter= ruleModuleFilter EOF ;
    public final EObject entryRuleModuleFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleFilter = null;


        try {
            // InternalN4MFParser.g:1765:2: (iv_ruleModuleFilter= ruleModuleFilter EOF )
            // InternalN4MFParser.g:1766:2: iv_ruleModuleFilter= ruleModuleFilter EOF
            {
             newCompositeNode(grammarAccess.getModuleFilterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModuleFilter=ruleModuleFilter();

            state._fsp--;

             current =iv_ruleModuleFilter; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModuleFilter"


    // $ANTLR start "ruleModuleFilter"
    // InternalN4MFParser.g:1773:1: ruleModuleFilter returns [EObject current=null] : ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket ) ;
    public final EObject ruleModuleFilter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_moduleFilterType_0_0 = null;

        EObject lv_moduleSpecifiers_2_0 = null;

        EObject lv_moduleSpecifiers_4_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1776:28: ( ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket ) )
            // InternalN4MFParser.g:1777:1: ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1777:1: ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket )
            // InternalN4MFParser.g:1777:2: ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket
            {
            // InternalN4MFParser.g:1777:2: ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) )
            // InternalN4MFParser.g:1778:1: (lv_moduleFilterType_0_0= ruleModuleFilterType )
            {
            // InternalN4MFParser.g:1778:1: (lv_moduleFilterType_0_0= ruleModuleFilterType )
            // InternalN4MFParser.g:1779:3: lv_moduleFilterType_0_0= ruleModuleFilterType
            {
             
            	        newCompositeNode(grammarAccess.getModuleFilterAccess().getModuleFilterTypeModuleFilterTypeEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_9);
            lv_moduleFilterType_0_0=ruleModuleFilterType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModuleFilterRule());
            	        }
                   		set(
                   			current, 
                   			"moduleFilterType",
                    		lv_moduleFilterType_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.ModuleFilterType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

                	newLeafNode(otherlv_1, grammarAccess.getModuleFilterAccess().getLeftCurlyBracketKeyword_1());
                
            // InternalN4MFParser.g:1800:1: ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:1801:1: (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:1801:1: (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:1802:3: lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier
            {
             
            	        newCompositeNode(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_10);
            lv_moduleSpecifiers_2_0=ruleModuleFilterSpecifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModuleFilterRule());
            	        }
                   		add(
                   			current, 
                   			"moduleSpecifiers",
                    		lv_moduleSpecifiers_2_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.ModuleFilterSpecifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalN4MFParser.g:1818:2: (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Comma) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalN4MFParser.g:1819:2: otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) )
            	    {
            	    otherlv_3=(Token)match(input,Comma,FOLLOW_6); 

            	        	newLeafNode(otherlv_3, grammarAccess.getModuleFilterAccess().getCommaKeyword_3_0());
            	        
            	    // InternalN4MFParser.g:1823:1: ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) )
            	    // InternalN4MFParser.g:1824:1: (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier )
            	    {
            	    // InternalN4MFParser.g:1824:1: (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier )
            	    // InternalN4MFParser.g:1825:3: lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModuleFilterAccess().getModuleSpecifiersModuleFilterSpecifierParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_10);
            	    lv_moduleSpecifiers_4_0=ruleModuleFilterSpecifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModuleFilterRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"moduleSpecifiers",
            	            		lv_moduleSpecifiers_4_0, 
            	            		"eu.numberfour.n4js.n4mf.N4MF.ModuleFilterSpecifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            otherlv_5=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

                	newLeafNode(otherlv_5, grammarAccess.getModuleFilterAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleFilter"


    // $ANTLR start "entryRuleBootstrapModule"
    // InternalN4MFParser.g:1854:1: entryRuleBootstrapModule returns [EObject current=null] : iv_ruleBootstrapModule= ruleBootstrapModule EOF ;
    public final EObject entryRuleBootstrapModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBootstrapModule = null;


        try {
            // InternalN4MFParser.g:1855:2: (iv_ruleBootstrapModule= ruleBootstrapModule EOF )
            // InternalN4MFParser.g:1856:2: iv_ruleBootstrapModule= ruleBootstrapModule EOF
            {
             newCompositeNode(grammarAccess.getBootstrapModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBootstrapModule=ruleBootstrapModule();

            state._fsp--;

             current =iv_ruleBootstrapModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBootstrapModule"


    // $ANTLR start "ruleBootstrapModule"
    // InternalN4MFParser.g:1863:1: ruleBootstrapModule returns [EObject current=null] : ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleBootstrapModule() throws RecognitionException {
        EObject current = null;

        Token lv_moduleSpecifierWithWildcard_0_0=null;
        Token otherlv_1=null;
        Token lv_sourcePath_2_0=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1866:28: ( ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) )
            // InternalN4MFParser.g:1867:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            {
            // InternalN4MFParser.g:1867:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            // InternalN4MFParser.g:1867:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            {
            // InternalN4MFParser.g:1867:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) )
            // InternalN4MFParser.g:1868:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1868:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            // InternalN4MFParser.g:1869:3: lv_moduleSpecifierWithWildcard_0_0= RULE_STRING
            {
            lv_moduleSpecifierWithWildcard_0_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            			newLeafNode(lv_moduleSpecifierWithWildcard_0_0, grammarAccess.getBootstrapModuleAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getBootstrapModuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"moduleSpecifierWithWildcard",
                    		lv_moduleSpecifierWithWildcard_0_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalN4MFParser.g:1885:2: (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==In) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalN4MFParser.g:1886:2: otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,In,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getBootstrapModuleAccess().getInKeyword_1_0());
                        
                    // InternalN4MFParser.g:1890:1: ( (lv_sourcePath_2_0= RULE_STRING ) )
                    // InternalN4MFParser.g:1891:1: (lv_sourcePath_2_0= RULE_STRING )
                    {
                    // InternalN4MFParser.g:1891:1: (lv_sourcePath_2_0= RULE_STRING )
                    // InternalN4MFParser.g:1892:3: lv_sourcePath_2_0= RULE_STRING
                    {
                    lv_sourcePath_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			newLeafNode(lv_sourcePath_2_0, grammarAccess.getBootstrapModuleAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getBootstrapModuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sourcePath",
                            		lv_sourcePath_2_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBootstrapModule"


    // $ANTLR start "entryRuleModuleFilterSpecifier"
    // InternalN4MFParser.g:1916:1: entryRuleModuleFilterSpecifier returns [EObject current=null] : iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF ;
    public final EObject entryRuleModuleFilterSpecifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleFilterSpecifier = null;


        try {
            // InternalN4MFParser.g:1917:2: (iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF )
            // InternalN4MFParser.g:1918:2: iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF
            {
             newCompositeNode(grammarAccess.getModuleFilterSpecifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModuleFilterSpecifier=ruleModuleFilterSpecifier();

            state._fsp--;

             current =iv_ruleModuleFilterSpecifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModuleFilterSpecifier"


    // $ANTLR start "ruleModuleFilterSpecifier"
    // InternalN4MFParser.g:1925:1: ruleModuleFilterSpecifier returns [EObject current=null] : ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleModuleFilterSpecifier() throws RecognitionException {
        EObject current = null;

        Token lv_moduleSpecifierWithWildcard_0_0=null;
        Token otherlv_1=null;
        Token lv_sourcePath_2_0=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1928:28: ( ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) )
            // InternalN4MFParser.g:1929:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            {
            // InternalN4MFParser.g:1929:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            // InternalN4MFParser.g:1929:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            {
            // InternalN4MFParser.g:1929:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) )
            // InternalN4MFParser.g:1930:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1930:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            // InternalN4MFParser.g:1931:3: lv_moduleSpecifierWithWildcard_0_0= RULE_STRING
            {
            lv_moduleSpecifierWithWildcard_0_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            			newLeafNode(lv_moduleSpecifierWithWildcard_0_0, grammarAccess.getModuleFilterSpecifierAccess().getModuleSpecifierWithWildcardSTRINGTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getModuleFilterSpecifierRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"moduleSpecifierWithWildcard",
                    		lv_moduleSpecifierWithWildcard_0_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalN4MFParser.g:1947:2: (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==In) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalN4MFParser.g:1948:2: otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,In,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getModuleFilterSpecifierAccess().getInKeyword_1_0());
                        
                    // InternalN4MFParser.g:1952:1: ( (lv_sourcePath_2_0= RULE_STRING ) )
                    // InternalN4MFParser.g:1953:1: (lv_sourcePath_2_0= RULE_STRING )
                    {
                    // InternalN4MFParser.g:1953:1: (lv_sourcePath_2_0= RULE_STRING )
                    // InternalN4MFParser.g:1954:3: lv_sourcePath_2_0= RULE_STRING
                    {
                    lv_sourcePath_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			newLeafNode(lv_sourcePath_2_0, grammarAccess.getModuleFilterSpecifierAccess().getSourcePathSTRINGTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getModuleFilterSpecifierRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sourcePath",
                            		lv_sourcePath_2_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleFilterSpecifier"


    // $ANTLR start "entryRuleProvidedRuntimeLibraryDependency"
    // InternalN4MFParser.g:1978:1: entryRuleProvidedRuntimeLibraryDependency returns [EObject current=null] : iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF ;
    public final EObject entryRuleProvidedRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedRuntimeLibraryDependency = null;


        try {
            // InternalN4MFParser.g:1979:2: (iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:1980:2: iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF
            {
             newCompositeNode(grammarAccess.getProvidedRuntimeLibraryDependencyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProvidedRuntimeLibraryDependency=ruleProvidedRuntimeLibraryDependency();

            state._fsp--;

             current =iv_ruleProvidedRuntimeLibraryDependency; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProvidedRuntimeLibraryDependency"


    // $ANTLR start "ruleProvidedRuntimeLibraryDependency"
    // InternalN4MFParser.g:1987:1: ruleProvidedRuntimeLibraryDependency returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleProvidedRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1990:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:1991:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:1991:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:1992:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:1992:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:1993:3: lv_project_0_0= ruleSimpleProjectDescription
            {
             
            	        newCompositeNode(grammarAccess.getProvidedRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_project_0_0=ruleSimpleProjectDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getProvidedRuntimeLibraryDependencyRule());
            	        }
                   		set(
                   			current, 
                   			"project",
                    		lv_project_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SimpleProjectDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProvidedRuntimeLibraryDependency"


    // $ANTLR start "entryRuleRequiredRuntimeLibraryDependency"
    // InternalN4MFParser.g:2017:1: entryRuleRequiredRuntimeLibraryDependency returns [EObject current=null] : iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF ;
    public final EObject entryRuleRequiredRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredRuntimeLibraryDependency = null;


        try {
            // InternalN4MFParser.g:2018:2: (iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:2019:2: iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF
            {
             newCompositeNode(grammarAccess.getRequiredRuntimeLibraryDependencyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRequiredRuntimeLibraryDependency=ruleRequiredRuntimeLibraryDependency();

            state._fsp--;

             current =iv_ruleRequiredRuntimeLibraryDependency; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRequiredRuntimeLibraryDependency"


    // $ANTLR start "ruleRequiredRuntimeLibraryDependency"
    // InternalN4MFParser.g:2026:1: ruleRequiredRuntimeLibraryDependency returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleRequiredRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2029:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2030:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2030:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2031:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2031:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2032:3: lv_project_0_0= ruleSimpleProjectDescription
            {
             
            	        newCompositeNode(grammarAccess.getRequiredRuntimeLibraryDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_project_0_0=ruleSimpleProjectDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRequiredRuntimeLibraryDependencyRule());
            	        }
                   		set(
                   			current, 
                   			"project",
                    		lv_project_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SimpleProjectDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRequiredRuntimeLibraryDependency"


    // $ANTLR start "entryRuleTestedProject"
    // InternalN4MFParser.g:2056:1: entryRuleTestedProject returns [EObject current=null] : iv_ruleTestedProject= ruleTestedProject EOF ;
    public final EObject entryRuleTestedProject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestedProject = null;


        try {
            // InternalN4MFParser.g:2057:2: (iv_ruleTestedProject= ruleTestedProject EOF )
            // InternalN4MFParser.g:2058:2: iv_ruleTestedProject= ruleTestedProject EOF
            {
             newCompositeNode(grammarAccess.getTestedProjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestedProject=ruleTestedProject();

            state._fsp--;

             current =iv_ruleTestedProject; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestedProject"


    // $ANTLR start "ruleTestedProject"
    // InternalN4MFParser.g:2065:1: ruleTestedProject returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleTestedProject() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2068:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2069:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2069:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2070:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2070:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2071:3: lv_project_0_0= ruleSimpleProjectDescription
            {
             
            	        newCompositeNode(grammarAccess.getTestedProjectAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_project_0_0=ruleSimpleProjectDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTestedProjectRule());
            	        }
                   		set(
                   			current, 
                   			"project",
                    		lv_project_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SimpleProjectDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTestedProject"


    // $ANTLR start "entryRuleProjectReference"
    // InternalN4MFParser.g:2095:1: entryRuleProjectReference returns [EObject current=null] : iv_ruleProjectReference= ruleProjectReference EOF ;
    public final EObject entryRuleProjectReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectReference = null;


        try {
            // InternalN4MFParser.g:2096:2: (iv_ruleProjectReference= ruleProjectReference EOF )
            // InternalN4MFParser.g:2097:2: iv_ruleProjectReference= ruleProjectReference EOF
            {
             newCompositeNode(grammarAccess.getProjectReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProjectReference=ruleProjectReference();

            state._fsp--;

             current =iv_ruleProjectReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProjectReference"


    // $ANTLR start "ruleProjectReference"
    // InternalN4MFParser.g:2104:1: ruleProjectReference returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleProjectReference() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2107:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2108:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2108:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2109:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2109:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2110:3: lv_project_0_0= ruleSimpleProjectDescription
            {
             
            	        newCompositeNode(grammarAccess.getProjectReferenceAccess().getProjectSimpleProjectDescriptionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_project_0_0=ruleSimpleProjectDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getProjectReferenceRule());
            	        }
                   		set(
                   			current, 
                   			"project",
                    		lv_project_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SimpleProjectDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectReference"


    // $ANTLR start "entryRuleProjectDependency"
    // InternalN4MFParser.g:2134:1: entryRuleProjectDependency returns [EObject current=null] : iv_ruleProjectDependency= ruleProjectDependency EOF ;
    public final EObject entryRuleProjectDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectDependency = null;


        try {
            // InternalN4MFParser.g:2135:2: (iv_ruleProjectDependency= ruleProjectDependency EOF )
            // InternalN4MFParser.g:2136:2: iv_ruleProjectDependency= ruleProjectDependency EOF
            {
             newCompositeNode(grammarAccess.getProjectDependencyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProjectDependency=ruleProjectDependency();

            state._fsp--;

             current =iv_ruleProjectDependency; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProjectDependency"


    // $ANTLR start "ruleProjectDependency"
    // InternalN4MFParser.g:2143:1: ruleProjectDependency returns [EObject current=null] : ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? ) ;
    public final EObject ruleProjectDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;

        EObject lv_versionConstraint_1_0 = null;

        Enumerator lv_declaredScope_2_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2146:28: ( ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? ) )
            // InternalN4MFParser.g:2147:1: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? )
            {
            // InternalN4MFParser.g:2147:1: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? )
            // InternalN4MFParser.g:2147:2: ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )?
            {
            // InternalN4MFParser.g:2147:2: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2148:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2148:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2149:3: lv_project_0_0= ruleSimpleProjectDescription
            {
             
            	        newCompositeNode(grammarAccess.getProjectDependencyAccess().getProjectSimpleProjectDescriptionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_21);
            lv_project_0_0=ruleSimpleProjectDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getProjectDependencyRule());
            	        }
                   		set(
                   			current, 
                   			"project",
                    		lv_project_0_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.SimpleProjectDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalN4MFParser.g:2165:2: ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==LeftParenthesis||LA26_0==LeftSquareBracket||LA26_0==RULE_INT) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalN4MFParser.g:2166:1: (lv_versionConstraint_1_0= ruleVersionConstraint )
                    {
                    // InternalN4MFParser.g:2166:1: (lv_versionConstraint_1_0= ruleVersionConstraint )
                    // InternalN4MFParser.g:2167:3: lv_versionConstraint_1_0= ruleVersionConstraint
                    {
                     
                    	        newCompositeNode(grammarAccess.getProjectDependencyAccess().getVersionConstraintVersionConstraintParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_22);
                    lv_versionConstraint_1_0=ruleVersionConstraint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProjectDependencyRule());
                    	        }
                           		set(
                           			current, 
                           			"versionConstraint",
                            		lv_versionConstraint_1_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.VersionConstraint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalN4MFParser.g:2183:3: ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==Compile||LA27_0==Test) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalN4MFParser.g:2184:1: (lv_declaredScope_2_0= ruleProjectDependencyScope )
                    {
                    // InternalN4MFParser.g:2184:1: (lv_declaredScope_2_0= ruleProjectDependencyScope )
                    // InternalN4MFParser.g:2185:3: lv_declaredScope_2_0= ruleProjectDependencyScope
                    {
                     
                    	        newCompositeNode(grammarAccess.getProjectDependencyAccess().getDeclaredScopeProjectDependencyScopeEnumRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_declaredScope_2_0=ruleProjectDependencyScope();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProjectDependencyRule());
                    	        }
                           		set(
                           			current, 
                           			"declaredScope",
                            		lv_declaredScope_2_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.ProjectDependencyScope");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectDependency"


    // $ANTLR start "entryRuleSimpleProjectDescription"
    // InternalN4MFParser.g:2209:1: entryRuleSimpleProjectDescription returns [EObject current=null] : iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF ;
    public final EObject entryRuleSimpleProjectDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleProjectDescription = null;


        try {
            // InternalN4MFParser.g:2210:2: (iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF )
            // InternalN4MFParser.g:2211:2: iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF
            {
             newCompositeNode(grammarAccess.getSimpleProjectDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleProjectDescription=ruleSimpleProjectDescription();

            state._fsp--;

             current =iv_ruleSimpleProjectDescription; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleProjectDescription"


    // $ANTLR start "ruleSimpleProjectDescription"
    // InternalN4MFParser.g:2218:1: ruleSimpleProjectDescription returns [EObject current=null] : ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_projectId_2_0= ruleN4mfIdentifier ) ) ) ;
    public final EObject ruleSimpleProjectDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_declaredVendorId_0_0 = null;

        AntlrDatatypeRuleToken lv_projectId_2_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2221:28: ( ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_projectId_2_0= ruleN4mfIdentifier ) ) ) )
            // InternalN4MFParser.g:2222:1: ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_projectId_2_0= ruleN4mfIdentifier ) ) )
            {
            // InternalN4MFParser.g:2222:1: ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_projectId_2_0= ruleN4mfIdentifier ) ) )
            // InternalN4MFParser.g:2222:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_projectId_2_0= ruleN4mfIdentifier ) )
            {
            // InternalN4MFParser.g:2222:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )?
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // InternalN4MFParser.g:2222:3: ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon
                    {
                    // InternalN4MFParser.g:2222:3: ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) )
                    // InternalN4MFParser.g:2223:1: (lv_declaredVendorId_0_0= ruleN4mfIdentifier )
                    {
                    // InternalN4MFParser.g:2223:1: (lv_declaredVendorId_0_0= ruleN4mfIdentifier )
                    // InternalN4MFParser.g:2224:3: lv_declaredVendorId_0_0= ruleN4mfIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getSimpleProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_3);
                    lv_declaredVendorId_0_0=ruleN4mfIdentifier();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSimpleProjectDescriptionRule());
                    	        }
                           		set(
                           			current, 
                           			"declaredVendorId",
                            		lv_declaredVendorId_0_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_1=(Token)match(input,Colon,FOLLOW_4); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimpleProjectDescriptionAccess().getColonKeyword_0_1());
                        

                    }
                    break;

            }

            // InternalN4MFParser.g:2245:3: ( (lv_projectId_2_0= ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:2246:1: (lv_projectId_2_0= ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:2246:1: (lv_projectId_2_0= ruleN4mfIdentifier )
            // InternalN4MFParser.g:2247:3: lv_projectId_2_0= ruleN4mfIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getSimpleProjectDescriptionAccess().getProjectIdN4mfIdentifierParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_projectId_2_0=ruleN4mfIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSimpleProjectDescriptionRule());
            	        }
                   		set(
                   			current, 
                   			"projectId",
                    		lv_projectId_2_0, 
                    		"eu.numberfour.n4js.n4mf.N4MF.N4mfIdentifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleProjectDescription"


    // $ANTLR start "entryRuleVersionConstraint"
    // InternalN4MFParser.g:2271:1: entryRuleVersionConstraint returns [EObject current=null] : iv_ruleVersionConstraint= ruleVersionConstraint EOF ;
    public final EObject entryRuleVersionConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVersionConstraint = null;


        try {
            // InternalN4MFParser.g:2272:2: (iv_ruleVersionConstraint= ruleVersionConstraint EOF )
            // InternalN4MFParser.g:2273:2: iv_ruleVersionConstraint= ruleVersionConstraint EOF
            {
             newCompositeNode(grammarAccess.getVersionConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVersionConstraint=ruleVersionConstraint();

            state._fsp--;

             current =iv_ruleVersionConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVersionConstraint"


    // $ANTLR start "ruleVersionConstraint"
    // InternalN4MFParser.g:2280:1: ruleVersionConstraint returns [EObject current=null] : ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) ) ;
    public final EObject ruleVersionConstraint() throws RecognitionException {
        EObject current = null;

        Token lv_exclLowerBound_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_exclUpperBound_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_lowerVersion_2_0 = null;

        EObject lv_upperVersion_4_0 = null;

        EObject lv_lowerVersion_8_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2283:28: ( ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) ) )
            // InternalN4MFParser.g:2284:1: ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) )
            {
            // InternalN4MFParser.g:2284:1: ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LeftParenthesis||LA33_0==LeftSquareBracket) ) {
                alt33=1;
            }
            else if ( (LA33_0==RULE_INT) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // InternalN4MFParser.g:2284:2: ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) )
                    {
                    // InternalN4MFParser.g:2284:2: ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) )
                    // InternalN4MFParser.g:2284:3: ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis )
                    {
                    // InternalN4MFParser.g:2284:3: ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==LeftParenthesis) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0==LeftSquareBracket) ) {
                        alt29=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // InternalN4MFParser.g:2284:4: ( (lv_exclLowerBound_0_0= LeftParenthesis ) )
                            {
                            // InternalN4MFParser.g:2284:4: ( (lv_exclLowerBound_0_0= LeftParenthesis ) )
                            // InternalN4MFParser.g:2285:1: (lv_exclLowerBound_0_0= LeftParenthesis )
                            {
                            // InternalN4MFParser.g:2285:1: (lv_exclLowerBound_0_0= LeftParenthesis )
                            // InternalN4MFParser.g:2286:3: lv_exclLowerBound_0_0= LeftParenthesis
                            {
                            lv_exclLowerBound_0_0=(Token)match(input,LeftParenthesis,FOLLOW_8); 

                                    newLeafNode(lv_exclLowerBound_0_0, grammarAccess.getVersionConstraintAccess().getExclLowerBoundLeftParenthesisKeyword_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getVersionConstraintRule());
                            	        }
                                   		setWithLastConsumed(current, "exclLowerBound", true, "(");
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalN4MFParser.g:2302:2: otherlv_1= LeftSquareBracket
                            {
                            otherlv_1=(Token)match(input,LeftSquareBracket,FOLLOW_8); 

                                	newLeafNode(otherlv_1, grammarAccess.getVersionConstraintAccess().getLeftSquareBracketKeyword_0_0_1());
                                

                            }
                            break;

                    }

                    // InternalN4MFParser.g:2306:2: ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) )
                    // InternalN4MFParser.g:2307:1: (lv_lowerVersion_2_0= ruleDeclaredVersion )
                    {
                    // InternalN4MFParser.g:2307:1: (lv_lowerVersion_2_0= ruleDeclaredVersion )
                    // InternalN4MFParser.g:2308:3: lv_lowerVersion_2_0= ruleDeclaredVersion
                    {
                     
                    	        newCompositeNode(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_23);
                    lv_lowerVersion_2_0=ruleDeclaredVersion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getVersionConstraintRule());
                    	        }
                           		set(
                           			current, 
                           			"lowerVersion",
                            		lv_lowerVersion_2_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.DeclaredVersion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalN4MFParser.g:2324:2: ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis )
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==EOF||LA32_0==Compile||LA32_0==Test||LA32_0==Comma||LA32_0==RightCurlyBracket) ) {
                        alt32=1;
                    }
                    else if ( (LA32_0==RightParenthesis) ) {
                        alt32=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 0, input);

                        throw nvae;
                    }
                    switch (alt32) {
                        case 1 :
                            // InternalN4MFParser.g:2324:3: (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )?
                            {
                            // InternalN4MFParser.g:2324:3: (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )?
                            int alt31=2;
                            int LA31_0 = input.LA(1);

                            if ( (LA31_0==Comma) ) {
                                int LA31_1 = input.LA(2);

                                if ( (LA31_1==RULE_INT) ) {
                                    alt31=1;
                                }
                            }
                            switch (alt31) {
                                case 1 :
                                    // InternalN4MFParser.g:2325:2: otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket )
                                    {
                                    otherlv_3=(Token)match(input,Comma,FOLLOW_8); 

                                        	newLeafNode(otherlv_3, grammarAccess.getVersionConstraintAccess().getCommaKeyword_0_2_0_0());
                                        
                                    // InternalN4MFParser.g:2329:1: ( (lv_upperVersion_4_0= ruleDeclaredVersion ) )
                                    // InternalN4MFParser.g:2330:1: (lv_upperVersion_4_0= ruleDeclaredVersion )
                                    {
                                    // InternalN4MFParser.g:2330:1: (lv_upperVersion_4_0= ruleDeclaredVersion )
                                    // InternalN4MFParser.g:2331:3: lv_upperVersion_4_0= ruleDeclaredVersion
                                    {
                                     
                                    	        newCompositeNode(grammarAccess.getVersionConstraintAccess().getUpperVersionDeclaredVersionParserRuleCall_0_2_0_1_0()); 
                                    	    
                                    pushFollow(FOLLOW_24);
                                    lv_upperVersion_4_0=ruleDeclaredVersion();

                                    state._fsp--;


                                    	        if (current==null) {
                                    	            current = createModelElementForParent(grammarAccess.getVersionConstraintRule());
                                    	        }
                                           		set(
                                           			current, 
                                           			"upperVersion",
                                            		lv_upperVersion_4_0, 
                                            		"eu.numberfour.n4js.n4mf.N4MF.DeclaredVersion");
                                    	        afterParserOrEnumRuleCall();
                                    	    

                                    }


                                    }

                                    // InternalN4MFParser.g:2347:2: ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket )
                                    int alt30=2;
                                    int LA30_0 = input.LA(1);

                                    if ( (LA30_0==RightParenthesis) ) {
                                        alt30=1;
                                    }
                                    else if ( (LA30_0==RightSquareBracket) ) {
                                        alt30=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 30, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt30) {
                                        case 1 :
                                            // InternalN4MFParser.g:2347:3: ( (lv_exclUpperBound_5_0= RightParenthesis ) )
                                            {
                                            // InternalN4MFParser.g:2347:3: ( (lv_exclUpperBound_5_0= RightParenthesis ) )
                                            // InternalN4MFParser.g:2348:1: (lv_exclUpperBound_5_0= RightParenthesis )
                                            {
                                            // InternalN4MFParser.g:2348:1: (lv_exclUpperBound_5_0= RightParenthesis )
                                            // InternalN4MFParser.g:2349:3: lv_exclUpperBound_5_0= RightParenthesis
                                            {
                                            lv_exclUpperBound_5_0=(Token)match(input,RightParenthesis,FOLLOW_2); 

                                                    newLeafNode(lv_exclUpperBound_5_0, grammarAccess.getVersionConstraintAccess().getExclUpperBoundRightParenthesisKeyword_0_2_0_2_0_0());
                                                

                                            	        if (current==null) {
                                            	            current = createModelElement(grammarAccess.getVersionConstraintRule());
                                            	        }
                                                   		setWithLastConsumed(current, "exclUpperBound", true, ")");
                                            	    

                                            }


                                            }


                                            }
                                            break;
                                        case 2 :
                                            // InternalN4MFParser.g:2365:2: otherlv_6= RightSquareBracket
                                            {
                                            otherlv_6=(Token)match(input,RightSquareBracket,FOLLOW_2); 

                                                	newLeafNode(otherlv_6, grammarAccess.getVersionConstraintAccess().getRightSquareBracketKeyword_0_2_0_2_1());
                                                

                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // InternalN4MFParser.g:2371:2: otherlv_7= RightParenthesis
                            {
                            otherlv_7=(Token)match(input,RightParenthesis,FOLLOW_2); 

                                	newLeafNode(otherlv_7, grammarAccess.getVersionConstraintAccess().getRightParenthesisKeyword_0_2_1());
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2376:6: ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) )
                    {
                    // InternalN4MFParser.g:2376:6: ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) )
                    // InternalN4MFParser.g:2377:1: (lv_lowerVersion_8_0= ruleDeclaredVersion )
                    {
                    // InternalN4MFParser.g:2377:1: (lv_lowerVersion_8_0= ruleDeclaredVersion )
                    // InternalN4MFParser.g:2378:3: lv_lowerVersion_8_0= ruleDeclaredVersion
                    {
                     
                    	        newCompositeNode(grammarAccess.getVersionConstraintAccess().getLowerVersionDeclaredVersionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_lowerVersion_8_0=ruleDeclaredVersion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getVersionConstraintRule());
                    	        }
                           		set(
                           			current, 
                           			"lowerVersion",
                            		lv_lowerVersion_8_0, 
                            		"eu.numberfour.n4js.n4mf.N4MF.DeclaredVersion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVersionConstraint"


    // $ANTLR start "entryRuleN4mfIdentifier"
    // InternalN4MFParser.g:2402:1: entryRuleN4mfIdentifier returns [String current=null] : iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF ;
    public final String entryRuleN4mfIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleN4mfIdentifier = null;


        try {
            // InternalN4MFParser.g:2403:1: (iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF )
            // InternalN4MFParser.g:2404:2: iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF
            {
             newCompositeNode(grammarAccess.getN4mfIdentifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleN4mfIdentifier=ruleN4mfIdentifier();

            state._fsp--;

             current =iv_ruleN4mfIdentifier.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleN4mfIdentifier"


    // $ANTLR start "ruleN4mfIdentifier"
    // InternalN4MFParser.g:2411:1: ruleN4mfIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | kw= ArtifactId | kw= ProjectId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test ) ;
    public final AntlrDatatypeRuleToken ruleN4mfIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2415:6: ( (this_ID_0= RULE_ID | kw= ArtifactId | kw= ProjectId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test ) )
            // InternalN4MFParser.g:2416:1: (this_ID_0= RULE_ID | kw= ArtifactId | kw= ProjectId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test )
            {
            // InternalN4MFParser.g:2416:1: (this_ID_0= RULE_ID | kw= ArtifactId | kw= ProjectId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test )
            int alt34=20;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt34=1;
                }
                break;
            case ArtifactId:
                {
                alt34=2;
                }
                break;
            case ProjectId:
                {
                alt34=3;
                }
                break;
            case VendorId:
                {
                alt34=4;
                }
                break;
            case ProjectName:
                {
                alt34=5;
                }
                break;
            case VendorName:
                {
                alt34=6;
                }
                break;
            case ProjectType:
                {
                alt34=7;
                }
                break;
            case ProjectVersion:
                {
                alt34=8;
                }
                break;
            case Output:
                {
                alt34=9;
                }
                break;
            case Libraries:
                {
                alt34=10;
                }
                break;
            case Resources:
                {
                alt34=11;
                }
                break;
            case Sources:
                {
                alt34=12;
                }
                break;
            case ModuleFilters:
                {
                alt34=13;
                }
                break;
            case ProjectDependencies:
                {
                alt34=14;
                }
                break;
            case API:
                {
                alt34=15;
                }
                break;
            case User:
                {
                alt34=16;
                }
                break;
            case Application:
                {
                alt34=17;
                }
                break;
            case Processor:
                {
                alt34=18;
                }
                break;
            case Content:
                {
                alt34=19;
                }
                break;
            case Test:
                {
                alt34=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // InternalN4MFParser.g:2416:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2425:2: kw= ArtifactId
                    {
                    kw=(Token)match(input,ArtifactId,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2432:2: kw= ProjectId
                    {
                    kw=(Token)match(input,ProjectId,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectIdKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:2439:2: kw= VendorId
                    {
                    kw=(Token)match(input,VendorId,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:2446:2: kw= ProjectName
                    {
                    kw=(Token)match(input,ProjectName,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:2453:2: kw= VendorName
                    {
                    kw=(Token)match(input,VendorName,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_5()); 
                        

                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:2460:2: kw= ProjectType
                    {
                    kw=(Token)match(input,ProjectType,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_6()); 
                        

                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:2467:2: kw= ProjectVersion
                    {
                    kw=(Token)match(input,ProjectVersion,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_7()); 
                        

                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:2474:2: kw= Output
                    {
                    kw=(Token)match(input,Output,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_8()); 
                        

                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:2481:2: kw= Libraries
                    {
                    kw=(Token)match(input,Libraries,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_9()); 
                        

                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:2488:2: kw= Resources
                    {
                    kw=(Token)match(input,Resources,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_10()); 
                        

                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:2495:2: kw= Sources
                    {
                    kw=(Token)match(input,Sources,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_11()); 
                        

                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:2502:2: kw= ModuleFilters
                    {
                    kw=(Token)match(input,ModuleFilters,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_12()); 
                        

                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:2508:6: (kw= ProjectDependencies kw= KW_System )
                    {
                    // InternalN4MFParser.g:2508:6: (kw= ProjectDependencies kw= KW_System )
                    // InternalN4MFParser.g:2509:2: kw= ProjectDependencies kw= KW_System
                    {
                    kw=(Token)match(input,ProjectDependencies,FOLLOW_25); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_13_0()); 
                        
                    kw=(Token)match(input,KW_System,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_13_1()); 
                        

                    }


                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:2522:2: kw= API
                    {
                    kw=(Token)match(input,API,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_14()); 
                        

                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:2529:2: kw= User
                    {
                    kw=(Token)match(input,User,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getUserKeyword_15()); 
                        

                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:2536:2: kw= Application
                    {
                    kw=(Token)match(input,Application,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_16()); 
                        

                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:2542:6: (kw= Processor kw= Source )
                    {
                    // InternalN4MFParser.g:2542:6: (kw= Processor kw= Source )
                    // InternalN4MFParser.g:2543:2: kw= Processor kw= Source
                    {
                    kw=(Token)match(input,Processor,FOLLOW_26); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_17_0()); 
                        
                    kw=(Token)match(input,Source,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_17_1()); 
                        

                    }


                    }
                    break;
                case 19 :
                    // InternalN4MFParser.g:2556:2: kw= Content
                    {
                    kw=(Token)match(input,Content,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getContentKeyword_18()); 
                        

                    }
                    break;
                case 20 :
                    // InternalN4MFParser.g:2563:2: kw= Test
                    {
                    kw=(Token)match(input,Test,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getTestKeyword_19()); 
                        

                    }
                    break;

            }


            }

             leaveRule();
                
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleN4mfIdentifier"


    // $ANTLR start "ruleProjectType"
    // InternalN4MFParser.g:2576:1: ruleProjectType returns [Enumerator current=null] : ( (enumLiteral_0= Application ) | (enumLiteral_1= Processor ) | (enumLiteral_2= Library ) | (enumLiteral_3= API ) | (enumLiteral_4= RuntimeEnvironment ) | (enumLiteral_5= RuntimeLibrary ) | (enumLiteral_6= Test ) ) ;
    public final Enumerator ruleProjectType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2578:28: ( ( (enumLiteral_0= Application ) | (enumLiteral_1= Processor ) | (enumLiteral_2= Library ) | (enumLiteral_3= API ) | (enumLiteral_4= RuntimeEnvironment ) | (enumLiteral_5= RuntimeLibrary ) | (enumLiteral_6= Test ) ) )
            // InternalN4MFParser.g:2579:1: ( (enumLiteral_0= Application ) | (enumLiteral_1= Processor ) | (enumLiteral_2= Library ) | (enumLiteral_3= API ) | (enumLiteral_4= RuntimeEnvironment ) | (enumLiteral_5= RuntimeLibrary ) | (enumLiteral_6= Test ) )
            {
            // InternalN4MFParser.g:2579:1: ( (enumLiteral_0= Application ) | (enumLiteral_1= Processor ) | (enumLiteral_2= Library ) | (enumLiteral_3= API ) | (enumLiteral_4= RuntimeEnvironment ) | (enumLiteral_5= RuntimeLibrary ) | (enumLiteral_6= Test ) )
            int alt35=7;
            switch ( input.LA(1) ) {
            case Application:
                {
                alt35=1;
                }
                break;
            case Processor:
                {
                alt35=2;
                }
                break;
            case Library:
                {
                alt35=3;
                }
                break;
            case API:
                {
                alt35=4;
                }
                break;
            case RuntimeEnvironment:
                {
                alt35=5;
                }
                break;
            case RuntimeLibrary:
                {
                alt35=6;
                }
                break;
            case Test:
                {
                alt35=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // InternalN4MFParser.g:2579:2: (enumLiteral_0= Application )
                    {
                    // InternalN4MFParser.g:2579:2: (enumLiteral_0= Application )
                    // InternalN4MFParser.g:2579:7: enumLiteral_0= Application
                    {
                    enumLiteral_0=(Token)match(input,Application,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2585:6: (enumLiteral_1= Processor )
                    {
                    // InternalN4MFParser.g:2585:6: (enumLiteral_1= Processor )
                    // InternalN4MFParser.g:2585:11: enumLiteral_1= Processor
                    {
                    enumLiteral_1=(Token)match(input,Processor,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2591:6: (enumLiteral_2= Library )
                    {
                    // InternalN4MFParser.g:2591:6: (enumLiteral_2= Library )
                    // InternalN4MFParser.g:2591:11: enumLiteral_2= Library
                    {
                    enumLiteral_2=(Token)match(input,Library,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:2597:6: (enumLiteral_3= API )
                    {
                    // InternalN4MFParser.g:2597:6: (enumLiteral_3= API )
                    // InternalN4MFParser.g:2597:11: enumLiteral_3= API
                    {
                    enumLiteral_3=(Token)match(input,API,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:2603:6: (enumLiteral_4= RuntimeEnvironment )
                    {
                    // InternalN4MFParser.g:2603:6: (enumLiteral_4= RuntimeEnvironment )
                    // InternalN4MFParser.g:2603:11: enumLiteral_4= RuntimeEnvironment
                    {
                    enumLiteral_4=(Token)match(input,RuntimeEnvironment,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:2609:6: (enumLiteral_5= RuntimeLibrary )
                    {
                    // InternalN4MFParser.g:2609:6: (enumLiteral_5= RuntimeLibrary )
                    // InternalN4MFParser.g:2609:11: enumLiteral_5= RuntimeLibrary
                    {
                    enumLiteral_5=(Token)match(input,RuntimeLibrary,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:2615:6: (enumLiteral_6= Test )
                    {
                    // InternalN4MFParser.g:2615:6: (enumLiteral_6= Test )
                    // InternalN4MFParser.g:2615:11: enumLiteral_6= Test
                    {
                    enumLiteral_6=(Token)match(input,Test,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectType"


    // $ANTLR start "ruleSourceFragmentType"
    // InternalN4MFParser.g:2625:1: ruleSourceFragmentType returns [Enumerator current=null] : ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) ) ;
    public final Enumerator ruleSourceFragmentType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2627:28: ( ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) ) )
            // InternalN4MFParser.g:2628:1: ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) )
            {
            // InternalN4MFParser.g:2628:1: ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) )
            int alt36=3;
            switch ( input.LA(1) ) {
            case Source:
                {
                alt36=1;
                }
                break;
            case External:
                {
                alt36=2;
                }
                break;
            case Test:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // InternalN4MFParser.g:2628:2: (enumLiteral_0= Source )
                    {
                    // InternalN4MFParser.g:2628:2: (enumLiteral_0= Source )
                    // InternalN4MFParser.g:2628:7: enumLiteral_0= Source
                    {
                    enumLiteral_0=(Token)match(input,Source,FOLLOW_2); 

                            current = grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2634:6: (enumLiteral_1= External )
                    {
                    // InternalN4MFParser.g:2634:6: (enumLiteral_1= External )
                    // InternalN4MFParser.g:2634:11: enumLiteral_1= External
                    {
                    enumLiteral_1=(Token)match(input,External,FOLLOW_2); 

                            current = grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2640:6: (enumLiteral_2= Test )
                    {
                    // InternalN4MFParser.g:2640:6: (enumLiteral_2= Test )
                    // InternalN4MFParser.g:2640:11: enumLiteral_2= Test
                    {
                    enumLiteral_2=(Token)match(input,Test,FOLLOW_2); 

                            current = grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getSourceFragmentTypeAccess().getTESTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSourceFragmentType"


    // $ANTLR start "ruleModuleFilterType"
    // InternalN4MFParser.g:2650:1: ruleModuleFilterType returns [Enumerator current=null] : ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) ) ;
    public final Enumerator ruleModuleFilterType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2652:28: ( ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) ) )
            // InternalN4MFParser.g:2653:1: ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) )
            {
            // InternalN4MFParser.g:2653:1: ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==NoValidate) ) {
                alt37=1;
            }
            else if ( (LA37_0==NoModuleWrap) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalN4MFParser.g:2653:2: (enumLiteral_0= NoValidate )
                    {
                    // InternalN4MFParser.g:2653:2: (enumLiteral_0= NoValidate )
                    // InternalN4MFParser.g:2653:7: enumLiteral_0= NoValidate
                    {
                    enumLiteral_0=(Token)match(input,NoValidate,FOLLOW_2); 

                            current = grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2659:6: (enumLiteral_1= NoModuleWrap )
                    {
                    // InternalN4MFParser.g:2659:6: (enumLiteral_1= NoModuleWrap )
                    // InternalN4MFParser.g:2659:11: enumLiteral_1= NoModuleWrap
                    {
                    enumLiteral_1=(Token)match(input,NoModuleWrap,FOLLOW_2); 

                            current = grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getModuleFilterTypeAccess().getNO_MODULE_WRAPPINGEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleFilterType"


    // $ANTLR start "ruleProjectDependencyScope"
    // InternalN4MFParser.g:2669:1: ruleProjectDependencyScope returns [Enumerator current=null] : ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) ) ;
    public final Enumerator ruleProjectDependencyScope() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2671:28: ( ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) ) )
            // InternalN4MFParser.g:2672:1: ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) )
            {
            // InternalN4MFParser.g:2672:1: ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==Compile) ) {
                alt38=1;
            }
            else if ( (LA38_0==Test) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalN4MFParser.g:2672:2: (enumLiteral_0= Compile )
                    {
                    // InternalN4MFParser.g:2672:2: (enumLiteral_0= Compile )
                    // InternalN4MFParser.g:2672:7: enumLiteral_0= Compile
                    {
                    enumLiteral_0=(Token)match(input,Compile,FOLLOW_2); 

                            current = grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2678:6: (enumLiteral_1= Test )
                    {
                    // InternalN4MFParser.g:2678:6: (enumLiteral_1= Test )
                    // InternalN4MFParser.g:2678:11: enumLiteral_1= Test
                    {
                    enumLiteral_1=(Token)match(input,Test,FOLLOW_2); 

                            current = grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getProjectDependencyScopeAccess().getTESTEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProjectDependencyScope"


    // $ANTLR start "ruleModuleLoader"
    // InternalN4MFParser.g:2688:1: ruleModuleLoader returns [Enumerator current=null] : ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) ) ;
    public final Enumerator ruleModuleLoader() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2690:28: ( ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) ) )
            // InternalN4MFParser.g:2691:1: ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) )
            {
            // InternalN4MFParser.g:2691:1: ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) )
            int alt39=3;
            switch ( input.LA(1) ) {
            case N4js:
                {
                alt39=1;
                }
                break;
            case Commonjs:
                {
                alt39=2;
                }
                break;
            case Node_builtin:
                {
                alt39=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // InternalN4MFParser.g:2691:2: (enumLiteral_0= N4js )
                    {
                    // InternalN4MFParser.g:2691:2: (enumLiteral_0= N4js )
                    // InternalN4MFParser.g:2691:7: enumLiteral_0= N4js
                    {
                    enumLiteral_0=(Token)match(input,N4js,FOLLOW_2); 

                            current = grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2697:6: (enumLiteral_1= Commonjs )
                    {
                    // InternalN4MFParser.g:2697:6: (enumLiteral_1= Commonjs )
                    // InternalN4MFParser.g:2697:11: enumLiteral_1= Commonjs
                    {
                    enumLiteral_1=(Token)match(input,Commonjs,FOLLOW_2); 

                            current = grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2703:6: (enumLiteral_2= Node_builtin )
                    {
                    // InternalN4MFParser.g:2703:6: (enumLiteral_2= Node_builtin )
                    // InternalN4MFParser.g:2703:11: enumLiteral_2= Node_builtin
                    {
                    enumLiteral_2=(Token)match(input,Node_builtin,FOLLOW_2); 

                            current = grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getModuleLoaderAccess().getNODE_BUILTINEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
        	catch (RecognitionException re) { 
        	    recover(input,re); 
        	    appendSkippedTokens();
        	}
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleLoader"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA28 dfa28 = new DFA28(this);
    static final String dfa_1s = "\30\uffff";
    static final String dfa_2s = "\1\1\27\uffff";
    static final String dfa_3s = "\1\4\27\uffff";
    static final String dfa_4s = "\1\46\27\uffff";
    static final String dfa_5s = "\1\uffff\1\27\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26";
    static final String dfa_6s = "\1\0\27\uffff}>";
    static final String[] dfa_7s = {
            "\1\11\1\12\1\13\1\16\1\14\1\uffff\1\15\1\5\1\26\1\uffff\1\25\1\27\2\uffff\1\17\1\3\1\4\1\uffff\1\2\1\20\1\10\1\7\1\uffff\1\22\1\2\1\23\1\uffff\1\6\2\uffff\1\24\3\uffff\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 82:3: ( ({...}? => ( ({...}? => ( (otherlv_1= ArtifactId | otherlv_2= ProjectId ) otherlv_3= Colon ( (lv_projectId_4_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= ProjectName otherlv_6= Colon this_STRING_7= RULE_STRING ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= ProjectType otherlv_9= Colon ( (lv_projectType_10_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= ProjectVersion otherlv_12= Colon ( (lv_projectVersion_13_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= VendorId otherlv_15= Colon ( (lv_declaredVendorId_16_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= VendorName otherlv_18= Colon ( (lv_vendorName_19_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= MainModule otherlv_21= Colon ( (lv_mainModule_22_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_23_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_24_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_25_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_26_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_27= ImplementationId otherlv_28= Colon ( (lv_implementationId_29_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_30_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_31_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_32_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_33= Output otherlv_34= Colon ( (lv_outputPath_35_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_36= Libraries otherlv_37= LeftCurlyBracket ( (lv_libraryPaths_38_0= RULE_STRING ) ) (otherlv_39= Comma ( (lv_libraryPaths_40_0= RULE_STRING ) ) )* otherlv_41= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_42= Resources otherlv_43= LeftCurlyBracket ( (lv_resourcePaths_44_0= RULE_STRING ) ) (otherlv_45= Comma ( (lv_resourcePaths_46_0= RULE_STRING ) ) )* otherlv_47= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_48= Sources otherlv_49= LeftCurlyBracket ( (lv_sourceFragment_50_0= ruleSourceFragment ) )+ otherlv_51= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_52= ModuleFilters otherlv_53= LeftCurlyBracket ( (lv_moduleFilters_54_0= ruleModuleFilter ) )+ otherlv_55= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_56_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_57= ModuleLoader otherlv_58= Colon ( (lv_moduleLoader_59_0= ruleModuleLoader ) ) ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA6_0 = input.LA(1);

                         
                        int index6_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA6_0==EOF) ) {s = 1;}

                        else if ( ( LA6_0 == ArtifactId || LA6_0 == ProjectId ) && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 2;}

                        else if ( LA6_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 3;}

                        else if ( LA6_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 4;}

                        else if ( LA6_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 5;}

                        else if ( LA6_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 6;}

                        else if ( LA6_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 7;}

                        else if ( LA6_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 8;}

                        else if ( LA6_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 9;}

                        else if ( LA6_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 10;}

                        else if ( LA6_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 11;}

                        else if ( LA6_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 12;}

                        else if ( LA6_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 13;}

                        else if ( LA6_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 14;}

                        else if ( LA6_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 15;}

                        else if ( LA6_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 16;}

                        else if ( LA6_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 17;}

                        else if ( LA6_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 18;}

                        else if ( LA6_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 19;}

                        else if ( LA6_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 20;}

                        else if ( LA6_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 21;}

                        else if ( LA6_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 22;}

                        else if ( LA6_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 23;}

                         
                        input.seek(index6_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 6, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_8s = "\31\uffff";
    static final String dfa_9s = "\1\uffff\15\26\1\uffff\3\26\1\uffff\2\26\2\uffff\2\26";
    static final String dfa_10s = "\1\10\15\4\1\50\3\4\1\47\2\4\2\uffff\2\4";
    static final String dfa_11s = "\1\70\15\71\1\50\3\71\1\47\2\71\2\uffff\2\71";
    static final String dfa_12s = "\25\uffff\1\1\1\2\2\uffff";
    static final String dfa_13s = "\31\uffff}>";
    static final String[] dfa_14s = {
            "\1\16\2\uffff\1\10\2\uffff\1\15\4\uffff\1\5\1\7\1\21\1\2\2\uffff\1\6\1\uffff\1\12\1\3\1\13\1\22\1\4\2\uffff\1\14\1\uffff\1\23\1\uffff\1\11\3\uffff\1\24\1\20\1\17\13\uffff\1\1",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\1\27",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\1\30",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "",
            "",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26",
            "\5\26\1\uffff\3\26\1\uffff\2\26\2\uffff\3\26\1\uffff\4\26\1\uffff\3\26\1\uffff\1\26\2\uffff\2\26\2\uffff\1\26\3\uffff\1\26\3\uffff\1\26\1\uffff\1\26\2\uffff\1\25\1\26\2\uffff\1\26\1\uffff\1\26"
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "2222:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )?";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x01001C54FA784900L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000044BBDCDDF2L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000142040202200L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0081000000000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000048200000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0080048200000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000004010000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0080000004010000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000020100020000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x01801C54FA784900L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0480000000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0210440800000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000040800000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0001800000000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0020800000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000008000000000L});

}
