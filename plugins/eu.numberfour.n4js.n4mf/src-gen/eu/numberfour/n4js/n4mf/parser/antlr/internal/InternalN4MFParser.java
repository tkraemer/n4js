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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ExtendedRuntimeEnvironment", "ProvidedRuntimeLibraries", "RequiredRuntimeLibraries", "ImplementedProjects", "ProjectDependencies", "RuntimeEnvironment", "ImplementationId", "ProjectVersion", "TestedProjects", "RuntimeLibrary", "ModuleFilters", "ModuleLoader", "NoModuleWrap", "Node_builtin", "InitModules", "ProjectName", "ProjectType", "Application", "ArtifactId", "ExecModule", "MainModule", "VendorName", "NoValidate", "Libraries", "Resources", "Processor", "VendorId", "Commonjs", "External", "Sources", "Compile", "Content", "Library", "Output", "Source", "KW_System", "N4js", "Test", "User", "API", "App", "Lib", "In", "LeftParenthesis", "RightParenthesis", "Comma", "HyphenMinus", "FullStop", "Colon", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int App=44;
    public static final int ArtifactId=22;
    public static final int TestedProjects=12;
    public static final int KW_System=39;
    public static final int ProjectDependencies=8;
    public static final int ExecModule=23;
    public static final int LeftParenthesis=47;
    public static final int Test=41;
    public static final int ProjectVersion=11;
    public static final int Libraries=27;
    public static final int ModuleFilters=14;
    public static final int RightSquareBracket=54;
    public static final int VendorName=25;
    public static final int RuntimeEnvironment=9;
    public static final int RULE_ID=57;
    public static final int NoValidate=26;
    public static final int NoModuleWrap=16;
    public static final int RightParenthesis=48;
    public static final int Sources=33;
    public static final int Content=35;
    public static final int RULE_INT=58;
    public static final int ProjectType=20;
    public static final int External=32;
    public static final int RULE_ML_COMMENT=60;
    public static final int LeftSquareBracket=53;
    public static final int Resources=28;
    public static final int Library=36;
    public static final int Application=21;
    public static final int ImplementedProjects=7;
    public static final int Processor=29;
    public static final int User=42;
    public static final int ProjectName=19;
    public static final int In=46;
    public static final int VendorId=30;
    public static final int RULE_STRING=59;
    public static final int Node_builtin=17;
    public static final int N4js=40;
    public static final int Compile=34;
    public static final int Source=38;
    public static final int RULE_SL_COMMENT=61;
    public static final int ImplementationId=10;
    public static final int Comma=49;
    public static final int HyphenMinus=50;
    public static final int Output=37;
    public static final int MainModule=24;
    public static final int Colon=52;
    public static final int RightCurlyBracket=56;
    public static final int EOF=-1;
    public static final int ExtendedRuntimeEnvironment=4;
    public static final int FullStop=51;
    public static final int ModuleLoader=15;
    public static final int Commonjs=31;
    public static final int Lib=45;
    public static final int RULE_WS=62;
    public static final int LeftCurlyBracket=55;
    public static final int ProvidedRuntimeLibraries=5;
    public static final int RULE_ANY_OTHER=63;
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
    // InternalN4MFParser.g:71:1: ruleProjectDescription returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleProjectDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_projectName_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token lv_vendorName_18_0=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token lv_mainModule_21_0=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_32=null;
        Token otherlv_33=null;
        Token lv_outputPath_34_0=null;
        Token otherlv_35=null;
        Token otherlv_36=null;
        Token lv_libraryPaths_37_0=null;
        Token otherlv_38=null;
        Token lv_libraryPaths_39_0=null;
        Token otherlv_40=null;
        Token otherlv_41=null;
        Token otherlv_42=null;
        Token lv_resourcePaths_43_0=null;
        Token otherlv_44=null;
        Token lv_resourcePaths_45_0=null;
        Token otherlv_46=null;
        Token otherlv_47=null;
        Token otherlv_48=null;
        Token otherlv_50=null;
        Token otherlv_51=null;
        Token otherlv_52=null;
        Token otherlv_54=null;
        Token otherlv_56=null;
        Token otherlv_57=null;
        AntlrDatatypeRuleToken lv_artifactId_3_0 = null;

        Enumerator lv_projectType_9_0 = null;

        EObject lv_projectVersion_12_0 = null;

        AntlrDatatypeRuleToken lv_declaredVendorId_15_0 = null;

        EObject lv_extendedRuntimeEnvironment_22_0 = null;

        EObject lv_providedRuntimeLibraries_23_0 = null;

        EObject lv_requiredRuntimeLibraries_24_0 = null;

        EObject lv_projectDependencies_25_0 = null;

        AntlrDatatypeRuleToken lv_implementationId_28_0 = null;

        EObject lv_implementedProjects_29_0 = null;

        EObject lv_initModules_30_0 = null;

        EObject lv_execModule_31_0 = null;

        EObject lv_sourceFragment_49_0 = null;

        EObject lv_moduleFilters_53_0 = null;

        EObject lv_testedProjects_55_0 = null;

        Enumerator lv_moduleLoader_58_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:74:28: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalN4MFParser.g:75:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) )
            {
            // InternalN4MFParser.g:75:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) ) )
            // InternalN4MFParser.g:77:1: ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalN4MFParser.g:77:1: ( ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?) )
            // InternalN4MFParser.g:78:2: ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?)
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	
            // InternalN4MFParser.g:81:2: ( ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?)
            // InternalN4MFParser.g:82:3: ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+ {...}?
            {
            // InternalN4MFParser.g:82:3: ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=23;
                alt5 = dfa5.predict(input);
                switch (alt5) {
            	case 1 :
            	    // InternalN4MFParser.g:84:4: ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:84:4: ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:85:5: {...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalN4MFParser.g:85:113: ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:86:6: ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0);
            	    	 				
            	    // InternalN4MFParser.g:89:6: ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:89:7: {...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:89:16: (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:90:2: otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,ArtifactId,FOLLOW_3); 

            	        	newLeafNode(otherlv_1, grammarAccess.getProjectDescriptionAccess().getArtifactIdKeyword_0_0());
            	        
            	    otherlv_2=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_2, grammarAccess.getProjectDescriptionAccess().getColonKeyword_0_1());
            	        
            	    // InternalN4MFParser.g:99:1: ( (lv_artifactId_3_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:100:1: (lv_artifactId_3_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:100:1: (lv_artifactId_3_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:101:3: lv_artifactId_3_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_0_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_artifactId_3_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"artifactId",
            	            		lv_artifactId_3_0, 
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
            	    // InternalN4MFParser.g:124:4: ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:124:4: ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:125:5: {...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalN4MFParser.g:125:113: ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:126:6: ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1);
            	    	 				
            	    // InternalN4MFParser.g:129:6: ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:129:7: {...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:129:16: (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:130:2: otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) )
            	    {
            	    otherlv_4=(Token)match(input,ProjectName,FOLLOW_3); 

            	        	newLeafNode(otherlv_4, grammarAccess.getProjectDescriptionAccess().getProjectNameKeyword_1_0());
            	        
            	    otherlv_5=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_5, grammarAccess.getProjectDescriptionAccess().getColonKeyword_1_1());
            	        
            	    // InternalN4MFParser.g:139:1: ( (lv_projectName_6_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:140:1: (lv_projectName_6_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:140:1: (lv_projectName_6_0= RULE_STRING )
            	    // InternalN4MFParser.g:141:3: lv_projectName_6_0= RULE_STRING
            	    {
            	    lv_projectName_6_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_projectName_6_0, grammarAccess.getProjectDescriptionAccess().getProjectNameSTRINGTerminalRuleCall_1_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"projectName",
            	            		lv_projectName_6_0, 
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
            	case 3 :
            	    // InternalN4MFParser.g:164:4: ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:164:4: ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) )
            	    // InternalN4MFParser.g:165:5: {...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalN4MFParser.g:165:113: ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) )
            	    // InternalN4MFParser.g:166:6: ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2);
            	    	 				
            	    // InternalN4MFParser.g:169:6: ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) )
            	    // InternalN4MFParser.g:169:7: {...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:169:16: (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) )
            	    // InternalN4MFParser.g:170:2: otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) )
            	    {
            	    otherlv_7=(Token)match(input,ProjectType,FOLLOW_3); 

            	        	newLeafNode(otherlv_7, grammarAccess.getProjectDescriptionAccess().getProjectTypeKeyword_2_0());
            	        
            	    otherlv_8=(Token)match(input,Colon,FOLLOW_7); 

            	        	newLeafNode(otherlv_8, grammarAccess.getProjectDescriptionAccess().getColonKeyword_2_1());
            	        
            	    // InternalN4MFParser.g:179:1: ( (lv_projectType_9_0= ruleProjectType ) )
            	    // InternalN4MFParser.g:180:1: (lv_projectType_9_0= ruleProjectType )
            	    {
            	    // InternalN4MFParser.g:180:1: (lv_projectType_9_0= ruleProjectType )
            	    // InternalN4MFParser.g:181:3: lv_projectType_9_0= ruleProjectType
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectTypeProjectTypeEnumRuleCall_2_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectType_9_0=ruleProjectType();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectType",
            	            		lv_projectType_9_0, 
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
            	    // InternalN4MFParser.g:204:4: ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:204:4: ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) )
            	    // InternalN4MFParser.g:205:5: {...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalN4MFParser.g:205:113: ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) )
            	    // InternalN4MFParser.g:206:6: ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3);
            	    	 				
            	    // InternalN4MFParser.g:209:6: ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) )
            	    // InternalN4MFParser.g:209:7: {...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:209:16: (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) )
            	    // InternalN4MFParser.g:210:2: otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) )
            	    {
            	    otherlv_10=(Token)match(input,ProjectVersion,FOLLOW_3); 

            	        	newLeafNode(otherlv_10, grammarAccess.getProjectDescriptionAccess().getProjectVersionKeyword_3_0());
            	        
            	    otherlv_11=(Token)match(input,Colon,FOLLOW_8); 

            	        	newLeafNode(otherlv_11, grammarAccess.getProjectDescriptionAccess().getColonKeyword_3_1());
            	        
            	    // InternalN4MFParser.g:219:1: ( (lv_projectVersion_12_0= ruleDeclaredVersion ) )
            	    // InternalN4MFParser.g:220:1: (lv_projectVersion_12_0= ruleDeclaredVersion )
            	    {
            	    // InternalN4MFParser.g:220:1: (lv_projectVersion_12_0= ruleDeclaredVersion )
            	    // InternalN4MFParser.g:221:3: lv_projectVersion_12_0= ruleDeclaredVersion
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectVersionDeclaredVersionParserRuleCall_3_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectVersion_12_0=ruleDeclaredVersion();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectVersion",
            	            		lv_projectVersion_12_0, 
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
            	    // InternalN4MFParser.g:244:4: ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:244:4: ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:245:5: {...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalN4MFParser.g:245:113: ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:246:6: ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4);
            	    	 				
            	    // InternalN4MFParser.g:249:6: ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:249:7: {...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:249:16: (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:250:2: otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) )
            	    {
            	    otherlv_13=(Token)match(input,VendorId,FOLLOW_3); 

            	        	newLeafNode(otherlv_13, grammarAccess.getProjectDescriptionAccess().getVendorIdKeyword_4_0());
            	        
            	    otherlv_14=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_14, grammarAccess.getProjectDescriptionAccess().getColonKeyword_4_1());
            	        
            	    // InternalN4MFParser.g:259:1: ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:260:1: (lv_declaredVendorId_15_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:260:1: (lv_declaredVendorId_15_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:261:3: lv_declaredVendorId_15_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getDeclaredVendorIdN4mfIdentifierParserRuleCall_4_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_declaredVendorId_15_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"declaredVendorId",
            	            		lv_declaredVendorId_15_0, 
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
            	    // InternalN4MFParser.g:284:4: ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:284:4: ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:285:5: {...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalN4MFParser.g:285:113: ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:286:6: ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5);
            	    	 				
            	    // InternalN4MFParser.g:289:6: ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:289:7: {...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:289:16: (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:290:2: otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) )
            	    {
            	    otherlv_16=(Token)match(input,VendorName,FOLLOW_3); 

            	        	newLeafNode(otherlv_16, grammarAccess.getProjectDescriptionAccess().getVendorNameKeyword_5_0());
            	        
            	    otherlv_17=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_17, grammarAccess.getProjectDescriptionAccess().getColonKeyword_5_1());
            	        
            	    // InternalN4MFParser.g:299:1: ( (lv_vendorName_18_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:300:1: (lv_vendorName_18_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:300:1: (lv_vendorName_18_0= RULE_STRING )
            	    // InternalN4MFParser.g:301:3: lv_vendorName_18_0= RULE_STRING
            	    {
            	    lv_vendorName_18_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_vendorName_18_0, grammarAccess.getProjectDescriptionAccess().getVendorNameSTRINGTerminalRuleCall_5_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"vendorName",
            	            		lv_vendorName_18_0, 
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
            	    // InternalN4MFParser.g:324:4: ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:324:4: ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:325:5: {...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6)");
            	    }
            	    // InternalN4MFParser.g:325:113: ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:326:6: ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6);
            	    	 				
            	    // InternalN4MFParser.g:329:6: ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:329:7: {...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:329:16: (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:330:2: otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) )
            	    {
            	    otherlv_19=(Token)match(input,MainModule,FOLLOW_3); 

            	        	newLeafNode(otherlv_19, grammarAccess.getProjectDescriptionAccess().getMainModuleKeyword_6_0());
            	        
            	    otherlv_20=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_20, grammarAccess.getProjectDescriptionAccess().getColonKeyword_6_1());
            	        
            	    // InternalN4MFParser.g:339:1: ( (lv_mainModule_21_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:340:1: (lv_mainModule_21_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:340:1: (lv_mainModule_21_0= RULE_STRING )
            	    // InternalN4MFParser.g:341:3: lv_mainModule_21_0= RULE_STRING
            	    {
            	    lv_mainModule_21_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_mainModule_21_0, grammarAccess.getProjectDescriptionAccess().getMainModuleSTRINGTerminalRuleCall_6_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"mainModule",
            	            		lv_mainModule_21_0, 
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
            	    // InternalN4MFParser.g:364:4: ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:364:4: ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) )
            	    // InternalN4MFParser.g:365:5: {...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7)");
            	    }
            	    // InternalN4MFParser.g:365:113: ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) )
            	    // InternalN4MFParser.g:366:6: ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7);
            	    	 				
            	    // InternalN4MFParser.g:369:6: ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) )
            	    // InternalN4MFParser.g:369:7: {...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:369:16: ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) )
            	    // InternalN4MFParser.g:370:1: (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment )
            	    {
            	    // InternalN4MFParser.g:370:1: (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment )
            	    // InternalN4MFParser.g:371:3: lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getExtendedRuntimeEnvironmentExtendedRuntimeEnvironmentParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_extendedRuntimeEnvironment_22_0=ruleExtendedRuntimeEnvironment();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"extendedRuntimeEnvironment",
            	            		lv_extendedRuntimeEnvironment_22_0, 
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
            	    // InternalN4MFParser.g:394:4: ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:394:4: ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) )
            	    // InternalN4MFParser.g:395:5: {...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8)");
            	    }
            	    // InternalN4MFParser.g:395:113: ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) )
            	    // InternalN4MFParser.g:396:6: ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8);
            	    	 				
            	    // InternalN4MFParser.g:399:6: ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) )
            	    // InternalN4MFParser.g:399:7: {...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:399:16: ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) )
            	    // InternalN4MFParser.g:400:1: (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries )
            	    {
            	    // InternalN4MFParser.g:400:1: (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries )
            	    // InternalN4MFParser.g:401:3: lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProvidedRuntimeLibrariesProvidedRuntimeLibrariesParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_providedRuntimeLibraries_23_0=ruleProvidedRuntimeLibraries();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"providedRuntimeLibraries",
            	            		lv_providedRuntimeLibraries_23_0, 
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
            	    // InternalN4MFParser.g:424:4: ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:424:4: ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) )
            	    // InternalN4MFParser.g:425:5: {...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9)");
            	    }
            	    // InternalN4MFParser.g:425:113: ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) )
            	    // InternalN4MFParser.g:426:6: ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9);
            	    	 				
            	    // InternalN4MFParser.g:429:6: ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) )
            	    // InternalN4MFParser.g:429:7: {...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:429:16: ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) )
            	    // InternalN4MFParser.g:430:1: (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries )
            	    {
            	    // InternalN4MFParser.g:430:1: (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries )
            	    // InternalN4MFParser.g:431:3: lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getRequiredRuntimeLibrariesRequiredRuntimeLibrariesParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_requiredRuntimeLibraries_24_0=ruleRequiredRuntimeLibraries();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"requiredRuntimeLibraries",
            	            		lv_requiredRuntimeLibraries_24_0, 
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
            	    // InternalN4MFParser.g:454:4: ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:454:4: ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) )
            	    // InternalN4MFParser.g:455:5: {...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10)");
            	    }
            	    // InternalN4MFParser.g:455:114: ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) )
            	    // InternalN4MFParser.g:456:6: ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10);
            	    	 				
            	    // InternalN4MFParser.g:459:6: ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) )
            	    // InternalN4MFParser.g:459:7: {...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:459:16: ( (lv_projectDependencies_25_0= ruleProjectDependencies ) )
            	    // InternalN4MFParser.g:460:1: (lv_projectDependencies_25_0= ruleProjectDependencies )
            	    {
            	    // InternalN4MFParser.g:460:1: (lv_projectDependencies_25_0= ruleProjectDependencies )
            	    // InternalN4MFParser.g:461:3: lv_projectDependencies_25_0= ruleProjectDependencies
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getProjectDependenciesProjectDependenciesParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_projectDependencies_25_0=ruleProjectDependencies();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"projectDependencies",
            	            		lv_projectDependencies_25_0, 
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
            	    // InternalN4MFParser.g:484:4: ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:484:4: ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) )
            	    // InternalN4MFParser.g:485:5: {...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11)");
            	    }
            	    // InternalN4MFParser.g:485:114: ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) )
            	    // InternalN4MFParser.g:486:6: ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11);
            	    	 				
            	    // InternalN4MFParser.g:489:6: ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) )
            	    // InternalN4MFParser.g:489:7: {...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:489:16: (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) )
            	    // InternalN4MFParser.g:490:2: otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) )
            	    {
            	    otherlv_26=(Token)match(input,ImplementationId,FOLLOW_3); 

            	        	newLeafNode(otherlv_26, grammarAccess.getProjectDescriptionAccess().getImplementationIdKeyword_11_0());
            	        
            	    otherlv_27=(Token)match(input,Colon,FOLLOW_4); 

            	        	newLeafNode(otherlv_27, grammarAccess.getProjectDescriptionAccess().getColonKeyword_11_1());
            	        
            	    // InternalN4MFParser.g:499:1: ( (lv_implementationId_28_0= ruleN4mfIdentifier ) )
            	    // InternalN4MFParser.g:500:1: (lv_implementationId_28_0= ruleN4mfIdentifier )
            	    {
            	    // InternalN4MFParser.g:500:1: (lv_implementationId_28_0= ruleN4mfIdentifier )
            	    // InternalN4MFParser.g:501:3: lv_implementationId_28_0= ruleN4mfIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getImplementationIdN4mfIdentifierParserRuleCall_11_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_implementationId_28_0=ruleN4mfIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"implementationId",
            	            		lv_implementationId_28_0, 
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
            	    // InternalN4MFParser.g:524:4: ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:524:4: ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) )
            	    // InternalN4MFParser.g:525:5: {...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12)");
            	    }
            	    // InternalN4MFParser.g:525:114: ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) )
            	    // InternalN4MFParser.g:526:6: ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12);
            	    	 				
            	    // InternalN4MFParser.g:529:6: ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) )
            	    // InternalN4MFParser.g:529:7: {...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:529:16: ( (lv_implementedProjects_29_0= ruleImplementedProjects ) )
            	    // InternalN4MFParser.g:530:1: (lv_implementedProjects_29_0= ruleImplementedProjects )
            	    {
            	    // InternalN4MFParser.g:530:1: (lv_implementedProjects_29_0= ruleImplementedProjects )
            	    // InternalN4MFParser.g:531:3: lv_implementedProjects_29_0= ruleImplementedProjects
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getImplementedProjectsImplementedProjectsParserRuleCall_12_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_implementedProjects_29_0=ruleImplementedProjects();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"implementedProjects",
            	            		lv_implementedProjects_29_0, 
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
            	    // InternalN4MFParser.g:554:4: ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:554:4: ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) )
            	    // InternalN4MFParser.g:555:5: {...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13)");
            	    }
            	    // InternalN4MFParser.g:555:114: ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) )
            	    // InternalN4MFParser.g:556:6: ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13);
            	    	 				
            	    // InternalN4MFParser.g:559:6: ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) )
            	    // InternalN4MFParser.g:559:7: {...}? => ( (lv_initModules_30_0= ruleInitModules ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:559:16: ( (lv_initModules_30_0= ruleInitModules ) )
            	    // InternalN4MFParser.g:560:1: (lv_initModules_30_0= ruleInitModules )
            	    {
            	    // InternalN4MFParser.g:560:1: (lv_initModules_30_0= ruleInitModules )
            	    // InternalN4MFParser.g:561:3: lv_initModules_30_0= ruleInitModules
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getInitModulesInitModulesParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_initModules_30_0=ruleInitModules();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"initModules",
            	            		lv_initModules_30_0, 
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
            	    // InternalN4MFParser.g:584:4: ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:584:4: ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) )
            	    // InternalN4MFParser.g:585:5: {...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14)");
            	    }
            	    // InternalN4MFParser.g:585:114: ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) )
            	    // InternalN4MFParser.g:586:6: ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14);
            	    	 				
            	    // InternalN4MFParser.g:589:6: ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) )
            	    // InternalN4MFParser.g:589:7: {...}? => ( (lv_execModule_31_0= ruleExecModule ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:589:16: ( (lv_execModule_31_0= ruleExecModule ) )
            	    // InternalN4MFParser.g:590:1: (lv_execModule_31_0= ruleExecModule )
            	    {
            	    // InternalN4MFParser.g:590:1: (lv_execModule_31_0= ruleExecModule )
            	    // InternalN4MFParser.g:591:3: lv_execModule_31_0= ruleExecModule
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getExecModuleExecModuleParserRuleCall_14_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_execModule_31_0=ruleExecModule();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"execModule",
            	            		lv_execModule_31_0, 
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
            	    // InternalN4MFParser.g:614:4: ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:614:4: ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) )
            	    // InternalN4MFParser.g:615:5: {...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15)");
            	    }
            	    // InternalN4MFParser.g:615:114: ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) )
            	    // InternalN4MFParser.g:616:6: ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15);
            	    	 				
            	    // InternalN4MFParser.g:619:6: ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) )
            	    // InternalN4MFParser.g:619:7: {...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:619:16: (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) )
            	    // InternalN4MFParser.g:620:2: otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) )
            	    {
            	    otherlv_32=(Token)match(input,Output,FOLLOW_3); 

            	        	newLeafNode(otherlv_32, grammarAccess.getProjectDescriptionAccess().getOutputKeyword_15_0());
            	        
            	    otherlv_33=(Token)match(input,Colon,FOLLOW_6); 

            	        	newLeafNode(otherlv_33, grammarAccess.getProjectDescriptionAccess().getColonKeyword_15_1());
            	        
            	    // InternalN4MFParser.g:629:1: ( (lv_outputPath_34_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:630:1: (lv_outputPath_34_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:630:1: (lv_outputPath_34_0= RULE_STRING )
            	    // InternalN4MFParser.g:631:3: lv_outputPath_34_0= RULE_STRING
            	    {
            	    lv_outputPath_34_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    			newLeafNode(lv_outputPath_34_0, grammarAccess.getProjectDescriptionAccess().getOutputPathSTRINGTerminalRuleCall_15_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		setWithLastConsumed(
            	           			current, 
            	           			"outputPath",
            	            		lv_outputPath_34_0, 
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
            	    // InternalN4MFParser.g:654:4: ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:654:4: ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:655:5: {...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16)");
            	    }
            	    // InternalN4MFParser.g:655:114: ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:656:6: ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16);
            	    	 				
            	    // InternalN4MFParser.g:659:6: ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:659:7: {...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:659:16: (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket )
            	    // InternalN4MFParser.g:660:2: otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket
            	    {
            	    otherlv_35=(Token)match(input,Libraries,FOLLOW_9); 

            	        	newLeafNode(otherlv_35, grammarAccess.getProjectDescriptionAccess().getLibrariesKeyword_16_0());
            	        
            	    otherlv_36=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

            	        	newLeafNode(otherlv_36, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_16_1());
            	        
            	    // InternalN4MFParser.g:669:1: ( (lv_libraryPaths_37_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:670:1: (lv_libraryPaths_37_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:670:1: (lv_libraryPaths_37_0= RULE_STRING )
            	    // InternalN4MFParser.g:671:3: lv_libraryPaths_37_0= RULE_STRING
            	    {
            	    lv_libraryPaths_37_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    			newLeafNode(lv_libraryPaths_37_0, grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"libraryPaths",
            	            		lv_libraryPaths_37_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }

            	    // InternalN4MFParser.g:687:2: (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )*
            	    loop1:
            	    do {
            	        int alt1=2;
            	        int LA1_0 = input.LA(1);

            	        if ( (LA1_0==Comma) ) {
            	            alt1=1;
            	        }


            	        switch (alt1) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:688:2: otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) )
            	    	    {
            	    	    otherlv_38=(Token)match(input,Comma,FOLLOW_6); 

            	    	        	newLeafNode(otherlv_38, grammarAccess.getProjectDescriptionAccess().getCommaKeyword_16_3_0());
            	    	        
            	    	    // InternalN4MFParser.g:692:1: ( (lv_libraryPaths_39_0= RULE_STRING ) )
            	    	    // InternalN4MFParser.g:693:1: (lv_libraryPaths_39_0= RULE_STRING )
            	    	    {
            	    	    // InternalN4MFParser.g:693:1: (lv_libraryPaths_39_0= RULE_STRING )
            	    	    // InternalN4MFParser.g:694:3: lv_libraryPaths_39_0= RULE_STRING
            	    	    {
            	    	    lv_libraryPaths_39_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    	    			newLeafNode(lv_libraryPaths_39_0, grammarAccess.getProjectDescriptionAccess().getLibraryPathsSTRINGTerminalRuleCall_16_3_1_0()); 
            	    	    		

            	    	    	        if (current==null) {
            	    	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		addWithLastConsumed(
            	    	           			current, 
            	    	           			"libraryPaths",
            	    	            		lv_libraryPaths_39_0, 
            	    	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    	    

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop1;
            	        }
            	    } while (true);

            	    otherlv_40=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_40, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_16_4());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 18 :
            	    // InternalN4MFParser.g:722:4: ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:722:4: ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:723:5: {...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17)");
            	    }
            	    // InternalN4MFParser.g:723:114: ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:724:6: ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17);
            	    	 				
            	    // InternalN4MFParser.g:727:6: ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:727:7: {...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:727:16: (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket )
            	    // InternalN4MFParser.g:728:2: otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket
            	    {
            	    otherlv_41=(Token)match(input,Resources,FOLLOW_9); 

            	        	newLeafNode(otherlv_41, grammarAccess.getProjectDescriptionAccess().getResourcesKeyword_17_0());
            	        
            	    otherlv_42=(Token)match(input,LeftCurlyBracket,FOLLOW_6); 

            	        	newLeafNode(otherlv_42, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_17_1());
            	        
            	    // InternalN4MFParser.g:737:1: ( (lv_resourcePaths_43_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:738:1: (lv_resourcePaths_43_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:738:1: (lv_resourcePaths_43_0= RULE_STRING )
            	    // InternalN4MFParser.g:739:3: lv_resourcePaths_43_0= RULE_STRING
            	    {
            	    lv_resourcePaths_43_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    			newLeafNode(lv_resourcePaths_43_0, grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"resourcePaths",
            	            		lv_resourcePaths_43_0, 
            	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    

            	    }


            	    }

            	    // InternalN4MFParser.g:755:2: (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )*
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==Comma) ) {
            	            alt2=1;
            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:756:2: otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) )
            	    	    {
            	    	    otherlv_44=(Token)match(input,Comma,FOLLOW_6); 

            	    	        	newLeafNode(otherlv_44, grammarAccess.getProjectDescriptionAccess().getCommaKeyword_17_3_0());
            	    	        
            	    	    // InternalN4MFParser.g:760:1: ( (lv_resourcePaths_45_0= RULE_STRING ) )
            	    	    // InternalN4MFParser.g:761:1: (lv_resourcePaths_45_0= RULE_STRING )
            	    	    {
            	    	    // InternalN4MFParser.g:761:1: (lv_resourcePaths_45_0= RULE_STRING )
            	    	    // InternalN4MFParser.g:762:3: lv_resourcePaths_45_0= RULE_STRING
            	    	    {
            	    	    lv_resourcePaths_45_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            	    	    			newLeafNode(lv_resourcePaths_45_0, grammarAccess.getProjectDescriptionAccess().getResourcePathsSTRINGTerminalRuleCall_17_3_1_0()); 
            	    	    		

            	    	    	        if (current==null) {
            	    	    	            current = createModelElement(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		addWithLastConsumed(
            	    	           			current, 
            	    	           			"resourcePaths",
            	    	            		lv_resourcePaths_45_0, 
            	    	            		"org.eclipse.xtext.common.Terminals.STRING");
            	    	    	    

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop2;
            	        }
            	    } while (true);

            	    otherlv_46=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_46, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_17_4());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 19 :
            	    // InternalN4MFParser.g:790:4: ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:790:4: ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:791:5: {...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18)");
            	    }
            	    // InternalN4MFParser.g:791:114: ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:792:6: ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18);
            	    	 				
            	    // InternalN4MFParser.g:795:6: ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:795:7: {...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:795:16: (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket )
            	    // InternalN4MFParser.g:796:2: otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket
            	    {
            	    otherlv_47=(Token)match(input,Sources,FOLLOW_9); 

            	        	newLeafNode(otherlv_47, grammarAccess.getProjectDescriptionAccess().getSourcesKeyword_18_0());
            	        
            	    otherlv_48=(Token)match(input,LeftCurlyBracket,FOLLOW_11); 

            	        	newLeafNode(otherlv_48, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_18_1());
            	        
            	    // InternalN4MFParser.g:805:1: ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+
            	    int cnt3=0;
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==External||LA3_0==Source||LA3_0==Test) ) {
            	            alt3=1;
            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:806:1: (lv_sourceFragment_49_0= ruleSourceFragment )
            	    	    {
            	    	    // InternalN4MFParser.g:806:1: (lv_sourceFragment_49_0= ruleSourceFragment )
            	    	    // InternalN4MFParser.g:807:3: lv_sourceFragment_49_0= ruleSourceFragment
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getSourceFragmentSourceFragmentParserRuleCall_18_2_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_12);
            	    	    lv_sourceFragment_49_0=ruleSourceFragment();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"sourceFragment",
            	    	            		lv_sourceFragment_49_0, 
            	    	            		"eu.numberfour.n4js.n4mf.N4MF.SourceFragment");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt3 >= 1 ) break loop3;
            	                EarlyExitException eee =
            	                    new EarlyExitException(3, input);
            	                throw eee;
            	        }
            	        cnt3++;
            	    } while (true);

            	    otherlv_50=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_50, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_18_3());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 20 :
            	    // InternalN4MFParser.g:835:4: ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) )
            	    {
            	    // InternalN4MFParser.g:835:4: ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) )
            	    // InternalN4MFParser.g:836:5: {...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19)");
            	    }
            	    // InternalN4MFParser.g:836:114: ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) )
            	    // InternalN4MFParser.g:837:6: ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19);
            	    	 				
            	    // InternalN4MFParser.g:840:6: ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) )
            	    // InternalN4MFParser.g:840:7: {...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:840:16: (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket )
            	    // InternalN4MFParser.g:841:2: otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket
            	    {
            	    otherlv_51=(Token)match(input,ModuleFilters,FOLLOW_9); 

            	        	newLeafNode(otherlv_51, grammarAccess.getProjectDescriptionAccess().getModuleFiltersKeyword_19_0());
            	        
            	    otherlv_52=(Token)match(input,LeftCurlyBracket,FOLLOW_13); 

            	        	newLeafNode(otherlv_52, grammarAccess.getProjectDescriptionAccess().getLeftCurlyBracketKeyword_19_1());
            	        
            	    // InternalN4MFParser.g:850:1: ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+
            	    int cnt4=0;
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==NoModuleWrap||LA4_0==NoValidate) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // InternalN4MFParser.g:851:1: (lv_moduleFilters_53_0= ruleModuleFilter )
            	    	    {
            	    	    // InternalN4MFParser.g:851:1: (lv_moduleFilters_53_0= ruleModuleFilter )
            	    	    // InternalN4MFParser.g:852:3: lv_moduleFilters_53_0= ruleModuleFilter
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getModuleFiltersModuleFilterParserRuleCall_19_2_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_14);
            	    	    lv_moduleFilters_53_0=ruleModuleFilter();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"moduleFilters",
            	    	            		lv_moduleFilters_53_0, 
            	    	            		"eu.numberfour.n4js.n4mf.N4MF.ModuleFilter");
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

            	    otherlv_54=(Token)match(input,RightCurlyBracket,FOLLOW_5); 

            	        	newLeafNode(otherlv_54, grammarAccess.getProjectDescriptionAccess().getRightCurlyBracketKeyword_19_3());
            	        

            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 21 :
            	    // InternalN4MFParser.g:880:4: ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:880:4: ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) )
            	    // InternalN4MFParser.g:881:5: {...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20)");
            	    }
            	    // InternalN4MFParser.g:881:114: ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) )
            	    // InternalN4MFParser.g:882:6: ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20);
            	    	 				
            	    // InternalN4MFParser.g:885:6: ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) )
            	    // InternalN4MFParser.g:885:7: {...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:885:16: ( (lv_testedProjects_55_0= ruleTestedProjects ) )
            	    // InternalN4MFParser.g:886:1: (lv_testedProjects_55_0= ruleTestedProjects )
            	    {
            	    // InternalN4MFParser.g:886:1: (lv_testedProjects_55_0= ruleTestedProjects )
            	    // InternalN4MFParser.g:887:3: lv_testedProjects_55_0= ruleTestedProjects
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getTestedProjectsTestedProjectsParserRuleCall_20_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_testedProjects_55_0=ruleTestedProjects();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"testedProjects",
            	            		lv_testedProjects_55_0, 
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
            	    // InternalN4MFParser.g:910:4: ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) )
            	    {
            	    // InternalN4MFParser.g:910:4: ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) )
            	    // InternalN4MFParser.g:911:5: {...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21)");
            	    }
            	    // InternalN4MFParser.g:911:114: ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) )
            	    // InternalN4MFParser.g:912:6: ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21);
            	    	 				
            	    // InternalN4MFParser.g:915:6: ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) )
            	    // InternalN4MFParser.g:915:7: {...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProjectDescription", "true");
            	    }
            	    // InternalN4MFParser.g:915:16: (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) )
            	    // InternalN4MFParser.g:916:2: otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) )
            	    {
            	    otherlv_56=(Token)match(input,ModuleLoader,FOLLOW_3); 

            	        	newLeafNode(otherlv_56, grammarAccess.getProjectDescriptionAccess().getModuleLoaderKeyword_21_0());
            	        
            	    otherlv_57=(Token)match(input,Colon,FOLLOW_15); 

            	        	newLeafNode(otherlv_57, grammarAccess.getProjectDescriptionAccess().getColonKeyword_21_1());
            	        
            	    // InternalN4MFParser.g:925:1: ( (lv_moduleLoader_58_0= ruleModuleLoader ) )
            	    // InternalN4MFParser.g:926:1: (lv_moduleLoader_58_0= ruleModuleLoader )
            	    {
            	    // InternalN4MFParser.g:926:1: (lv_moduleLoader_58_0= ruleModuleLoader )
            	    // InternalN4MFParser.g:927:3: lv_moduleLoader_58_0= ruleModuleLoader
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProjectDescriptionAccess().getModuleLoaderModuleLoaderEnumRuleCall_21_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_5);
            	    lv_moduleLoader_58_0=ruleModuleLoader();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProjectDescriptionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"moduleLoader",
            	            		lv_moduleLoader_58_0, 
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
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
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
    // InternalN4MFParser.g:966:1: entryRuleExecModule returns [EObject current=null] : iv_ruleExecModule= ruleExecModule EOF ;
    public final EObject entryRuleExecModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecModule = null;


        try {
            // InternalN4MFParser.g:967:2: (iv_ruleExecModule= ruleExecModule EOF )
            // InternalN4MFParser.g:968:2: iv_ruleExecModule= ruleExecModule EOF
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
    // InternalN4MFParser.g:975:1: ruleExecModule returns [EObject current=null] : ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) ) ;
    public final EObject ruleExecModule() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_execModule_3_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:978:28: ( ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) ) )
            // InternalN4MFParser.g:979:1: ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) )
            {
            // InternalN4MFParser.g:979:1: ( () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) ) )
            // InternalN4MFParser.g:979:2: () otherlv_1= ExecModule otherlv_2= Colon ( (lv_execModule_3_0= ruleBootstrapModule ) )
            {
            // InternalN4MFParser.g:979:2: ()
            // InternalN4MFParser.g:980:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExecModuleAccess().getExecModuleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ExecModule,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getExecModuleAccess().getExecModuleKeyword_1());
                
            otherlv_2=(Token)match(input,Colon,FOLLOW_6); 

                	newLeafNode(otherlv_2, grammarAccess.getExecModuleAccess().getColonKeyword_2());
                
            // InternalN4MFParser.g:995:1: ( (lv_execModule_3_0= ruleBootstrapModule ) )
            // InternalN4MFParser.g:996:1: (lv_execModule_3_0= ruleBootstrapModule )
            {
            // InternalN4MFParser.g:996:1: (lv_execModule_3_0= ruleBootstrapModule )
            // InternalN4MFParser.g:997:3: lv_execModule_3_0= ruleBootstrapModule
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
    // InternalN4MFParser.g:1021:1: entryRuleTestedProjects returns [EObject current=null] : iv_ruleTestedProjects= ruleTestedProjects EOF ;
    public final EObject entryRuleTestedProjects() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestedProjects = null;


        try {
            // InternalN4MFParser.g:1022:2: (iv_ruleTestedProjects= ruleTestedProjects EOF )
            // InternalN4MFParser.g:1023:2: iv_ruleTestedProjects= ruleTestedProjects EOF
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
    // InternalN4MFParser.g:1030:1: ruleTestedProjects returns [EObject current=null] : ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1033:28: ( ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1034:1: ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1034:1: ( () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1034:2: () otherlv_1= TestedProjects otherlv_2= LeftCurlyBracket ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1034:2: ()
            // InternalN4MFParser.g:1035:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestedProjectsAccess().getTestedProjectsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,TestedProjects,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getTestedProjectsAccess().getTestedProjectsKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getTestedProjectsAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1050:1: ( ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ProjectDependencies||LA7_0==ProjectVersion||LA7_0==ModuleFilters||(LA7_0>=ProjectName && LA7_0<=ArtifactId)||LA7_0==VendorName||(LA7_0>=Libraries && LA7_0<=VendorId)||LA7_0==Sources||LA7_0==Content||LA7_0==Output||(LA7_0>=Test && LA7_0<=API)||LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalN4MFParser.g:1050:2: ( (lv_testedProjects_3_0= ruleTestedProject ) ) (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )*
                    {
                    // InternalN4MFParser.g:1050:2: ( (lv_testedProjects_3_0= ruleTestedProject ) )
                    // InternalN4MFParser.g:1051:1: (lv_testedProjects_3_0= ruleTestedProject )
                    {
                    // InternalN4MFParser.g:1051:1: (lv_testedProjects_3_0= ruleTestedProject )
                    // InternalN4MFParser.g:1052:3: lv_testedProjects_3_0= ruleTestedProject
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

                    // InternalN4MFParser.g:1068:2: (otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==Comma) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1069:2: otherlv_4= Comma ( (lv_testedProjects_5_0= ruleTestedProject ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getTestedProjectsAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1073:1: ( (lv_testedProjects_5_0= ruleTestedProject ) )
                    	    // InternalN4MFParser.g:1074:1: (lv_testedProjects_5_0= ruleTestedProject )
                    	    {
                    	    // InternalN4MFParser.g:1074:1: (lv_testedProjects_5_0= ruleTestedProject )
                    	    // InternalN4MFParser.g:1075:3: lv_testedProjects_5_0= ruleTestedProject
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
                    	    break loop6;
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
    // InternalN4MFParser.g:1104:1: entryRuleInitModules returns [EObject current=null] : iv_ruleInitModules= ruleInitModules EOF ;
    public final EObject entryRuleInitModules() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInitModules = null;


        try {
            // InternalN4MFParser.g:1105:2: (iv_ruleInitModules= ruleInitModules EOF )
            // InternalN4MFParser.g:1106:2: iv_ruleInitModules= ruleInitModules EOF
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
    // InternalN4MFParser.g:1113:1: ruleInitModules returns [EObject current=null] : ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1116:28: ( ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1117:1: ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1117:1: ( () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1117:2: () otherlv_1= InitModules otherlv_2= LeftCurlyBracket ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1117:2: ()
            // InternalN4MFParser.g:1118:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInitModulesAccess().getInitModulesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,InitModules,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getInitModulesAccess().getInitModulesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_17); 

                	newLeafNode(otherlv_2, grammarAccess.getInitModulesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1133:1: ( ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_STRING) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalN4MFParser.g:1133:2: ( (lv_initModules_3_0= ruleBootstrapModule ) ) (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )*
                    {
                    // InternalN4MFParser.g:1133:2: ( (lv_initModules_3_0= ruleBootstrapModule ) )
                    // InternalN4MFParser.g:1134:1: (lv_initModules_3_0= ruleBootstrapModule )
                    {
                    // InternalN4MFParser.g:1134:1: (lv_initModules_3_0= ruleBootstrapModule )
                    // InternalN4MFParser.g:1135:3: lv_initModules_3_0= ruleBootstrapModule
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

                    // InternalN4MFParser.g:1151:2: (otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==Comma) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1152:2: otherlv_4= Comma ( (lv_initModules_5_0= ruleBootstrapModule ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_6); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getInitModulesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1156:1: ( (lv_initModules_5_0= ruleBootstrapModule ) )
                    	    // InternalN4MFParser.g:1157:1: (lv_initModules_5_0= ruleBootstrapModule )
                    	    {
                    	    // InternalN4MFParser.g:1157:1: (lv_initModules_5_0= ruleBootstrapModule )
                    	    // InternalN4MFParser.g:1158:3: lv_initModules_5_0= ruleBootstrapModule
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
                    	    break loop8;
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
    // InternalN4MFParser.g:1187:1: entryRuleImplementedProjects returns [EObject current=null] : iv_ruleImplementedProjects= ruleImplementedProjects EOF ;
    public final EObject entryRuleImplementedProjects() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImplementedProjects = null;


        try {
            // InternalN4MFParser.g:1188:2: (iv_ruleImplementedProjects= ruleImplementedProjects EOF )
            // InternalN4MFParser.g:1189:2: iv_ruleImplementedProjects= ruleImplementedProjects EOF
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
    // InternalN4MFParser.g:1196:1: ruleImplementedProjects returns [EObject current=null] : ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1199:28: ( ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1200:1: ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1200:1: ( () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1200:2: () otherlv_1= ImplementedProjects otherlv_2= LeftCurlyBracket ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1200:2: ()
            // InternalN4MFParser.g:1201:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getImplementedProjectsAccess().getImplementedProjectsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ImplementedProjects,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getImplementedProjectsAccess().getImplementedProjectsKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getImplementedProjectsAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1216:1: ( ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )* )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ProjectDependencies||LA11_0==ProjectVersion||LA11_0==ModuleFilters||(LA11_0>=ProjectName && LA11_0<=ArtifactId)||LA11_0==VendorName||(LA11_0>=Libraries && LA11_0<=VendorId)||LA11_0==Sources||LA11_0==Content||LA11_0==Output||(LA11_0>=Test && LA11_0<=API)||LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalN4MFParser.g:1216:2: ( (lv_implementedProjects_3_0= ruleProjectReference ) ) (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )*
                    {
                    // InternalN4MFParser.g:1216:2: ( (lv_implementedProjects_3_0= ruleProjectReference ) )
                    // InternalN4MFParser.g:1217:1: (lv_implementedProjects_3_0= ruleProjectReference )
                    {
                    // InternalN4MFParser.g:1217:1: (lv_implementedProjects_3_0= ruleProjectReference )
                    // InternalN4MFParser.g:1218:3: lv_implementedProjects_3_0= ruleProjectReference
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

                    // InternalN4MFParser.g:1234:2: (otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==Comma) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1235:2: otherlv_4= Comma ( (lv_implementedProjects_5_0= ruleProjectReference ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getImplementedProjectsAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1239:1: ( (lv_implementedProjects_5_0= ruleProjectReference ) )
                    	    // InternalN4MFParser.g:1240:1: (lv_implementedProjects_5_0= ruleProjectReference )
                    	    {
                    	    // InternalN4MFParser.g:1240:1: (lv_implementedProjects_5_0= ruleProjectReference )
                    	    // InternalN4MFParser.g:1241:3: lv_implementedProjects_5_0= ruleProjectReference
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
                    	    break loop10;
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
    // InternalN4MFParser.g:1270:1: entryRuleProjectDependencies returns [EObject current=null] : iv_ruleProjectDependencies= ruleProjectDependencies EOF ;
    public final EObject entryRuleProjectDependencies() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectDependencies = null;


        try {
            // InternalN4MFParser.g:1271:2: (iv_ruleProjectDependencies= ruleProjectDependencies EOF )
            // InternalN4MFParser.g:1272:2: iv_ruleProjectDependencies= ruleProjectDependencies EOF
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
    // InternalN4MFParser.g:1279:1: ruleProjectDependencies returns [EObject current=null] : ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1282:28: ( ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1283:1: ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1283:1: ( () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1283:2: () otherlv_1= ProjectDependencies otherlv_2= LeftCurlyBracket ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1283:2: ()
            // InternalN4MFParser.g:1284:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getProjectDependenciesAccess().getProjectDependenciesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ProjectDependencies,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getProjectDependenciesAccess().getProjectDependenciesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getProjectDependenciesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1299:1: ( ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ProjectDependencies||LA13_0==ProjectVersion||LA13_0==ModuleFilters||(LA13_0>=ProjectName && LA13_0<=ArtifactId)||LA13_0==VendorName||(LA13_0>=Libraries && LA13_0<=VendorId)||LA13_0==Sources||LA13_0==Content||LA13_0==Output||(LA13_0>=Test && LA13_0<=API)||LA13_0==RULE_ID) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalN4MFParser.g:1299:2: ( (lv_projectDependencies_3_0= ruleProjectDependency ) ) (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1299:2: ( (lv_projectDependencies_3_0= ruleProjectDependency ) )
                    // InternalN4MFParser.g:1300:1: (lv_projectDependencies_3_0= ruleProjectDependency )
                    {
                    // InternalN4MFParser.g:1300:1: (lv_projectDependencies_3_0= ruleProjectDependency )
                    // InternalN4MFParser.g:1301:3: lv_projectDependencies_3_0= ruleProjectDependency
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

                    // InternalN4MFParser.g:1317:2: (otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==Comma) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1318:2: otherlv_4= Comma ( (lv_projectDependencies_5_0= ruleProjectDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getProjectDependenciesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1322:1: ( (lv_projectDependencies_5_0= ruleProjectDependency ) )
                    	    // InternalN4MFParser.g:1323:1: (lv_projectDependencies_5_0= ruleProjectDependency )
                    	    {
                    	    // InternalN4MFParser.g:1323:1: (lv_projectDependencies_5_0= ruleProjectDependency )
                    	    // InternalN4MFParser.g:1324:3: lv_projectDependencies_5_0= ruleProjectDependency
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
                    	    break loop12;
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
    // InternalN4MFParser.g:1353:1: entryRuleProvidedRuntimeLibraries returns [EObject current=null] : iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF ;
    public final EObject entryRuleProvidedRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedRuntimeLibraries = null;


        try {
            // InternalN4MFParser.g:1354:2: (iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF )
            // InternalN4MFParser.g:1355:2: iv_ruleProvidedRuntimeLibraries= ruleProvidedRuntimeLibraries EOF
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
    // InternalN4MFParser.g:1362:1: ruleProvidedRuntimeLibraries returns [EObject current=null] : ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1365:28: ( ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1366:1: ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1366:1: ( () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1366:2: () otherlv_1= ProvidedRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1366:2: ()
            // InternalN4MFParser.g:1367:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ProvidedRuntimeLibraries,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getProvidedRuntimeLibrariesAccess().getProvidedRuntimeLibrariesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getProvidedRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1382:1: ( ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ProjectDependencies||LA15_0==ProjectVersion||LA15_0==ModuleFilters||(LA15_0>=ProjectName && LA15_0<=ArtifactId)||LA15_0==VendorName||(LA15_0>=Libraries && LA15_0<=VendorId)||LA15_0==Sources||LA15_0==Content||LA15_0==Output||(LA15_0>=Test && LA15_0<=API)||LA15_0==RULE_ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalN4MFParser.g:1382:2: ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1382:2: ( (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency ) )
                    // InternalN4MFParser.g:1383:1: (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency )
                    {
                    // InternalN4MFParser.g:1383:1: (lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency )
                    // InternalN4MFParser.g:1384:3: lv_providedRuntimeLibraries_3_0= ruleProvidedRuntimeLibraryDependency
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

                    // InternalN4MFParser.g:1400:2: (otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==Comma) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1401:2: otherlv_4= Comma ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getProvidedRuntimeLibrariesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1405:1: ( (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency ) )
                    	    // InternalN4MFParser.g:1406:1: (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency )
                    	    {
                    	    // InternalN4MFParser.g:1406:1: (lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency )
                    	    // InternalN4MFParser.g:1407:3: lv_providedRuntimeLibraries_5_0= ruleProvidedRuntimeLibraryDependency
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
                    	    break loop14;
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
    // InternalN4MFParser.g:1436:1: entryRuleRequiredRuntimeLibraries returns [EObject current=null] : iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF ;
    public final EObject entryRuleRequiredRuntimeLibraries() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredRuntimeLibraries = null;


        try {
            // InternalN4MFParser.g:1437:2: (iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF )
            // InternalN4MFParser.g:1438:2: iv_ruleRequiredRuntimeLibraries= ruleRequiredRuntimeLibraries EOF
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
    // InternalN4MFParser.g:1445:1: ruleRequiredRuntimeLibraries returns [EObject current=null] : ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1448:28: ( ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket ) )
            // InternalN4MFParser.g:1449:1: ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1449:1: ( () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket )
            // InternalN4MFParser.g:1449:2: () otherlv_1= RequiredRuntimeLibraries otherlv_2= LeftCurlyBracket ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )? otherlv_6= RightCurlyBracket
            {
            // InternalN4MFParser.g:1449:2: ()
            // InternalN4MFParser.g:1450:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,RequiredRuntimeLibraries,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getRequiredRuntimeLibrariesAccess().getRequiredRuntimeLibrariesKeyword_1());
                
            otherlv_2=(Token)match(input,LeftCurlyBracket,FOLLOW_16); 

                	newLeafNode(otherlv_2, grammarAccess.getRequiredRuntimeLibrariesAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalN4MFParser.g:1465:1: ( ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ProjectDependencies||LA17_0==ProjectVersion||LA17_0==ModuleFilters||(LA17_0>=ProjectName && LA17_0<=ArtifactId)||LA17_0==VendorName||(LA17_0>=Libraries && LA17_0<=VendorId)||LA17_0==Sources||LA17_0==Content||LA17_0==Output||(LA17_0>=Test && LA17_0<=API)||LA17_0==RULE_ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalN4MFParser.g:1465:2: ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) ) (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )*
                    {
                    // InternalN4MFParser.g:1465:2: ( (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency ) )
                    // InternalN4MFParser.g:1466:1: (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency )
                    {
                    // InternalN4MFParser.g:1466:1: (lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency )
                    // InternalN4MFParser.g:1467:3: lv_requiredRuntimeLibraries_3_0= ruleRequiredRuntimeLibraryDependency
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

                    // InternalN4MFParser.g:1483:2: (otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==Comma) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // InternalN4MFParser.g:1484:2: otherlv_4= Comma ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_4); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getRequiredRuntimeLibrariesAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // InternalN4MFParser.g:1488:1: ( (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency ) )
                    	    // InternalN4MFParser.g:1489:1: (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency )
                    	    {
                    	    // InternalN4MFParser.g:1489:1: (lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency )
                    	    // InternalN4MFParser.g:1490:3: lv_requiredRuntimeLibraries_5_0= ruleRequiredRuntimeLibraryDependency
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
                    	    break loop16;
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
    // InternalN4MFParser.g:1519:1: entryRuleExtendedRuntimeEnvironment returns [EObject current=null] : iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF ;
    public final EObject entryRuleExtendedRuntimeEnvironment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtendedRuntimeEnvironment = null;


        try {
            // InternalN4MFParser.g:1520:2: (iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF )
            // InternalN4MFParser.g:1521:2: iv_ruleExtendedRuntimeEnvironment= ruleExtendedRuntimeEnvironment EOF
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
    // InternalN4MFParser.g:1528:1: ruleExtendedRuntimeEnvironment returns [EObject current=null] : ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) ) ;
    public final EObject ruleExtendedRuntimeEnvironment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_extendedRuntimeEnvironment_3_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1531:28: ( ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) ) )
            // InternalN4MFParser.g:1532:1: ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) )
            {
            // InternalN4MFParser.g:1532:1: ( () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) ) )
            // InternalN4MFParser.g:1532:2: () otherlv_1= ExtendedRuntimeEnvironment otherlv_2= Colon ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) )
            {
            // InternalN4MFParser.g:1532:2: ()
            // InternalN4MFParser.g:1533:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,ExtendedRuntimeEnvironment,FOLLOW_3); 

                	newLeafNode(otherlv_1, grammarAccess.getExtendedRuntimeEnvironmentAccess().getExtendedRuntimeEnvironmentKeyword_1());
                
            otherlv_2=(Token)match(input,Colon,FOLLOW_4); 

                	newLeafNode(otherlv_2, grammarAccess.getExtendedRuntimeEnvironmentAccess().getColonKeyword_2());
                
            // InternalN4MFParser.g:1548:1: ( (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference ) )
            // InternalN4MFParser.g:1549:1: (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference )
            {
            // InternalN4MFParser.g:1549:1: (lv_extendedRuntimeEnvironment_3_0= ruleProjectReference )
            // InternalN4MFParser.g:1550:3: lv_extendedRuntimeEnvironment_3_0= ruleProjectReference
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
    // InternalN4MFParser.g:1574:1: entryRuleDeclaredVersion returns [EObject current=null] : iv_ruleDeclaredVersion= ruleDeclaredVersion EOF ;
    public final EObject entryRuleDeclaredVersion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeclaredVersion = null;


        try {
            // InternalN4MFParser.g:1575:2: (iv_ruleDeclaredVersion= ruleDeclaredVersion EOF )
            // InternalN4MFParser.g:1576:2: iv_ruleDeclaredVersion= ruleDeclaredVersion EOF
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
    // InternalN4MFParser.g:1583:1: ruleDeclaredVersion returns [EObject current=null] : ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? ) ;
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
            // InternalN4MFParser.g:1586:28: ( ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? ) )
            // InternalN4MFParser.g:1587:1: ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? )
            {
            // InternalN4MFParser.g:1587:1: ( ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )? )
            // InternalN4MFParser.g:1587:2: ( (lv_major_0_0= RULE_INT ) ) (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )? (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )?
            {
            // InternalN4MFParser.g:1587:2: ( (lv_major_0_0= RULE_INT ) )
            // InternalN4MFParser.g:1588:1: (lv_major_0_0= RULE_INT )
            {
            // InternalN4MFParser.g:1588:1: (lv_major_0_0= RULE_INT )
            // InternalN4MFParser.g:1589:3: lv_major_0_0= RULE_INT
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

            // InternalN4MFParser.g:1605:2: (otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )? )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FullStop) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalN4MFParser.g:1606:2: otherlv_1= FullStop ( (lv_minor_2_0= RULE_INT ) ) (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )?
                    {
                    otherlv_1=(Token)match(input,FullStop,FOLLOW_8); 

                        	newLeafNode(otherlv_1, grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_0());
                        
                    // InternalN4MFParser.g:1610:1: ( (lv_minor_2_0= RULE_INT ) )
                    // InternalN4MFParser.g:1611:1: (lv_minor_2_0= RULE_INT )
                    {
                    // InternalN4MFParser.g:1611:1: (lv_minor_2_0= RULE_INT )
                    // InternalN4MFParser.g:1612:3: lv_minor_2_0= RULE_INT
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

                    // InternalN4MFParser.g:1628:2: (otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==FullStop) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // InternalN4MFParser.g:1629:2: otherlv_3= FullStop ( (lv_micro_4_0= RULE_INT ) )
                            {
                            otherlv_3=(Token)match(input,FullStop,FOLLOW_8); 

                                	newLeafNode(otherlv_3, grammarAccess.getDeclaredVersionAccess().getFullStopKeyword_1_2_0());
                                
                            // InternalN4MFParser.g:1633:1: ( (lv_micro_4_0= RULE_INT ) )
                            // InternalN4MFParser.g:1634:1: (lv_micro_4_0= RULE_INT )
                            {
                            // InternalN4MFParser.g:1634:1: (lv_micro_4_0= RULE_INT )
                            // InternalN4MFParser.g:1635:3: lv_micro_4_0= RULE_INT
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

            // InternalN4MFParser.g:1651:6: (otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==HyphenMinus) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalN4MFParser.g:1652:2: otherlv_5= HyphenMinus ( (lv_qualifier_6_0= ruleN4mfIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,HyphenMinus,FOLLOW_4); 

                        	newLeafNode(otherlv_5, grammarAccess.getDeclaredVersionAccess().getHyphenMinusKeyword_2_0());
                        
                    // InternalN4MFParser.g:1656:1: ( (lv_qualifier_6_0= ruleN4mfIdentifier ) )
                    // InternalN4MFParser.g:1657:1: (lv_qualifier_6_0= ruleN4mfIdentifier )
                    {
                    // InternalN4MFParser.g:1657:1: (lv_qualifier_6_0= ruleN4mfIdentifier )
                    // InternalN4MFParser.g:1658:3: lv_qualifier_6_0= ruleN4mfIdentifier
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
    // InternalN4MFParser.g:1682:1: entryRuleSourceFragment returns [EObject current=null] : iv_ruleSourceFragment= ruleSourceFragment EOF ;
    public final EObject entryRuleSourceFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSourceFragment = null;


        try {
            // InternalN4MFParser.g:1683:2: (iv_ruleSourceFragment= ruleSourceFragment EOF )
            // InternalN4MFParser.g:1684:2: iv_ruleSourceFragment= ruleSourceFragment EOF
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
    // InternalN4MFParser.g:1691:1: ruleSourceFragment returns [EObject current=null] : ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1694:28: ( ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket ) )
            // InternalN4MFParser.g:1695:1: ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1695:1: ( ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket )
            // InternalN4MFParser.g:1695:2: ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) ) otherlv_1= LeftCurlyBracket ( (lv_paths_2_0= RULE_STRING ) ) (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )* otherlv_5= RightCurlyBracket
            {
            // InternalN4MFParser.g:1695:2: ( (lv_sourceFragmentType_0_0= ruleSourceFragmentType ) )
            // InternalN4MFParser.g:1696:1: (lv_sourceFragmentType_0_0= ruleSourceFragmentType )
            {
            // InternalN4MFParser.g:1696:1: (lv_sourceFragmentType_0_0= ruleSourceFragmentType )
            // InternalN4MFParser.g:1697:3: lv_sourceFragmentType_0_0= ruleSourceFragmentType
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
                
            // InternalN4MFParser.g:1718:1: ( (lv_paths_2_0= RULE_STRING ) )
            // InternalN4MFParser.g:1719:1: (lv_paths_2_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1719:1: (lv_paths_2_0= RULE_STRING )
            // InternalN4MFParser.g:1720:3: lv_paths_2_0= RULE_STRING
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

            // InternalN4MFParser.g:1736:2: (otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==Comma) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalN4MFParser.g:1737:2: otherlv_3= Comma ( (lv_paths_4_0= RULE_STRING ) )
            	    {
            	    otherlv_3=(Token)match(input,Comma,FOLLOW_6); 

            	        	newLeafNode(otherlv_3, grammarAccess.getSourceFragmentAccess().getCommaKeyword_3_0());
            	        
            	    // InternalN4MFParser.g:1741:1: ( (lv_paths_4_0= RULE_STRING ) )
            	    // InternalN4MFParser.g:1742:1: (lv_paths_4_0= RULE_STRING )
            	    {
            	    // InternalN4MFParser.g:1742:1: (lv_paths_4_0= RULE_STRING )
            	    // InternalN4MFParser.g:1743:3: lv_paths_4_0= RULE_STRING
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
            	    break loop21;
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
    // InternalN4MFParser.g:1772:1: entryRuleModuleFilter returns [EObject current=null] : iv_ruleModuleFilter= ruleModuleFilter EOF ;
    public final EObject entryRuleModuleFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleFilter = null;


        try {
            // InternalN4MFParser.g:1773:2: (iv_ruleModuleFilter= ruleModuleFilter EOF )
            // InternalN4MFParser.g:1774:2: iv_ruleModuleFilter= ruleModuleFilter EOF
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
    // InternalN4MFParser.g:1781:1: ruleModuleFilter returns [EObject current=null] : ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket ) ;
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
            // InternalN4MFParser.g:1784:28: ( ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket ) )
            // InternalN4MFParser.g:1785:1: ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket )
            {
            // InternalN4MFParser.g:1785:1: ( ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket )
            // InternalN4MFParser.g:1785:2: ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) ) otherlv_1= LeftCurlyBracket ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) ) (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )* otherlv_5= RightCurlyBracket
            {
            // InternalN4MFParser.g:1785:2: ( (lv_moduleFilterType_0_0= ruleModuleFilterType ) )
            // InternalN4MFParser.g:1786:1: (lv_moduleFilterType_0_0= ruleModuleFilterType )
            {
            // InternalN4MFParser.g:1786:1: (lv_moduleFilterType_0_0= ruleModuleFilterType )
            // InternalN4MFParser.g:1787:3: lv_moduleFilterType_0_0= ruleModuleFilterType
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
                
            // InternalN4MFParser.g:1808:1: ( (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier ) )
            // InternalN4MFParser.g:1809:1: (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier )
            {
            // InternalN4MFParser.g:1809:1: (lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier )
            // InternalN4MFParser.g:1810:3: lv_moduleSpecifiers_2_0= ruleModuleFilterSpecifier
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

            // InternalN4MFParser.g:1826:2: (otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==Comma) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalN4MFParser.g:1827:2: otherlv_3= Comma ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) )
            	    {
            	    otherlv_3=(Token)match(input,Comma,FOLLOW_6); 

            	        	newLeafNode(otherlv_3, grammarAccess.getModuleFilterAccess().getCommaKeyword_3_0());
            	        
            	    // InternalN4MFParser.g:1831:1: ( (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier ) )
            	    // InternalN4MFParser.g:1832:1: (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier )
            	    {
            	    // InternalN4MFParser.g:1832:1: (lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier )
            	    // InternalN4MFParser.g:1833:3: lv_moduleSpecifiers_4_0= ruleModuleFilterSpecifier
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
            	    break loop22;
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
    // InternalN4MFParser.g:1862:1: entryRuleBootstrapModule returns [EObject current=null] : iv_ruleBootstrapModule= ruleBootstrapModule EOF ;
    public final EObject entryRuleBootstrapModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBootstrapModule = null;


        try {
            // InternalN4MFParser.g:1863:2: (iv_ruleBootstrapModule= ruleBootstrapModule EOF )
            // InternalN4MFParser.g:1864:2: iv_ruleBootstrapModule= ruleBootstrapModule EOF
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
    // InternalN4MFParser.g:1871:1: ruleBootstrapModule returns [EObject current=null] : ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleBootstrapModule() throws RecognitionException {
        EObject current = null;

        Token lv_moduleSpecifierWithWildcard_0_0=null;
        Token otherlv_1=null;
        Token lv_sourcePath_2_0=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1874:28: ( ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) )
            // InternalN4MFParser.g:1875:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            {
            // InternalN4MFParser.g:1875:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            // InternalN4MFParser.g:1875:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            {
            // InternalN4MFParser.g:1875:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) )
            // InternalN4MFParser.g:1876:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1876:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            // InternalN4MFParser.g:1877:3: lv_moduleSpecifierWithWildcard_0_0= RULE_STRING
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

            // InternalN4MFParser.g:1893:2: (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==In) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalN4MFParser.g:1894:2: otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,In,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getBootstrapModuleAccess().getInKeyword_1_0());
                        
                    // InternalN4MFParser.g:1898:1: ( (lv_sourcePath_2_0= RULE_STRING ) )
                    // InternalN4MFParser.g:1899:1: (lv_sourcePath_2_0= RULE_STRING )
                    {
                    // InternalN4MFParser.g:1899:1: (lv_sourcePath_2_0= RULE_STRING )
                    // InternalN4MFParser.g:1900:3: lv_sourcePath_2_0= RULE_STRING
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
    // InternalN4MFParser.g:1924:1: entryRuleModuleFilterSpecifier returns [EObject current=null] : iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF ;
    public final EObject entryRuleModuleFilterSpecifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleFilterSpecifier = null;


        try {
            // InternalN4MFParser.g:1925:2: (iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF )
            // InternalN4MFParser.g:1926:2: iv_ruleModuleFilterSpecifier= ruleModuleFilterSpecifier EOF
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
    // InternalN4MFParser.g:1933:1: ruleModuleFilterSpecifier returns [EObject current=null] : ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleModuleFilterSpecifier() throws RecognitionException {
        EObject current = null;

        Token lv_moduleSpecifierWithWildcard_0_0=null;
        Token otherlv_1=null;
        Token lv_sourcePath_2_0=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1936:28: ( ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? ) )
            // InternalN4MFParser.g:1937:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            {
            // InternalN4MFParser.g:1937:1: ( ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )? )
            // InternalN4MFParser.g:1937:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) ) (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            {
            // InternalN4MFParser.g:1937:2: ( (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING ) )
            // InternalN4MFParser.g:1938:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            {
            // InternalN4MFParser.g:1938:1: (lv_moduleSpecifierWithWildcard_0_0= RULE_STRING )
            // InternalN4MFParser.g:1939:3: lv_moduleSpecifierWithWildcard_0_0= RULE_STRING
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

            // InternalN4MFParser.g:1955:2: (otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==In) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalN4MFParser.g:1956:2: otherlv_1= In ( (lv_sourcePath_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,In,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getModuleFilterSpecifierAccess().getInKeyword_1_0());
                        
                    // InternalN4MFParser.g:1960:1: ( (lv_sourcePath_2_0= RULE_STRING ) )
                    // InternalN4MFParser.g:1961:1: (lv_sourcePath_2_0= RULE_STRING )
                    {
                    // InternalN4MFParser.g:1961:1: (lv_sourcePath_2_0= RULE_STRING )
                    // InternalN4MFParser.g:1962:3: lv_sourcePath_2_0= RULE_STRING
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
    // InternalN4MFParser.g:1986:1: entryRuleProvidedRuntimeLibraryDependency returns [EObject current=null] : iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF ;
    public final EObject entryRuleProvidedRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedRuntimeLibraryDependency = null;


        try {
            // InternalN4MFParser.g:1987:2: (iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:1988:2: iv_ruleProvidedRuntimeLibraryDependency= ruleProvidedRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:1995:1: ruleProvidedRuntimeLibraryDependency returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleProvidedRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:1998:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:1999:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:1999:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2000:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2000:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2001:3: lv_project_0_0= ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:2025:1: entryRuleRequiredRuntimeLibraryDependency returns [EObject current=null] : iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF ;
    public final EObject entryRuleRequiredRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredRuntimeLibraryDependency = null;


        try {
            // InternalN4MFParser.g:2026:2: (iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF )
            // InternalN4MFParser.g:2027:2: iv_ruleRequiredRuntimeLibraryDependency= ruleRequiredRuntimeLibraryDependency EOF
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
    // InternalN4MFParser.g:2034:1: ruleRequiredRuntimeLibraryDependency returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleRequiredRuntimeLibraryDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2037:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2038:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2038:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2039:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2039:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2040:3: lv_project_0_0= ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:2064:1: entryRuleTestedProject returns [EObject current=null] : iv_ruleTestedProject= ruleTestedProject EOF ;
    public final EObject entryRuleTestedProject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestedProject = null;


        try {
            // InternalN4MFParser.g:2065:2: (iv_ruleTestedProject= ruleTestedProject EOF )
            // InternalN4MFParser.g:2066:2: iv_ruleTestedProject= ruleTestedProject EOF
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
    // InternalN4MFParser.g:2073:1: ruleTestedProject returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleTestedProject() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2076:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2077:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2077:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2078:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2078:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2079:3: lv_project_0_0= ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:2103:1: entryRuleProjectReference returns [EObject current=null] : iv_ruleProjectReference= ruleProjectReference EOF ;
    public final EObject entryRuleProjectReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectReference = null;


        try {
            // InternalN4MFParser.g:2104:2: (iv_ruleProjectReference= ruleProjectReference EOF )
            // InternalN4MFParser.g:2105:2: iv_ruleProjectReference= ruleProjectReference EOF
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
    // InternalN4MFParser.g:2112:1: ruleProjectReference returns [EObject current=null] : ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ;
    public final EObject ruleProjectReference() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2115:28: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) )
            // InternalN4MFParser.g:2116:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            {
            // InternalN4MFParser.g:2116:1: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2117:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2117:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2118:3: lv_project_0_0= ruleSimpleProjectDescription
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
    // InternalN4MFParser.g:2142:1: entryRuleProjectDependency returns [EObject current=null] : iv_ruleProjectDependency= ruleProjectDependency EOF ;
    public final EObject entryRuleProjectDependency() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProjectDependency = null;


        try {
            // InternalN4MFParser.g:2143:2: (iv_ruleProjectDependency= ruleProjectDependency EOF )
            // InternalN4MFParser.g:2144:2: iv_ruleProjectDependency= ruleProjectDependency EOF
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
    // InternalN4MFParser.g:2151:1: ruleProjectDependency returns [EObject current=null] : ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? ) ;
    public final EObject ruleProjectDependency() throws RecognitionException {
        EObject current = null;

        EObject lv_project_0_0 = null;

        EObject lv_versionConstraint_1_0 = null;

        Enumerator lv_declaredScope_2_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2154:28: ( ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? ) )
            // InternalN4MFParser.g:2155:1: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? )
            {
            // InternalN4MFParser.g:2155:1: ( ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )? )
            // InternalN4MFParser.g:2155:2: ( (lv_project_0_0= ruleSimpleProjectDescription ) ) ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )? ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )?
            {
            // InternalN4MFParser.g:2155:2: ( (lv_project_0_0= ruleSimpleProjectDescription ) )
            // InternalN4MFParser.g:2156:1: (lv_project_0_0= ruleSimpleProjectDescription )
            {
            // InternalN4MFParser.g:2156:1: (lv_project_0_0= ruleSimpleProjectDescription )
            // InternalN4MFParser.g:2157:3: lv_project_0_0= ruleSimpleProjectDescription
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

            // InternalN4MFParser.g:2173:2: ( (lv_versionConstraint_1_0= ruleVersionConstraint ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LeftParenthesis||LA25_0==LeftSquareBracket||LA25_0==RULE_INT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalN4MFParser.g:2174:1: (lv_versionConstraint_1_0= ruleVersionConstraint )
                    {
                    // InternalN4MFParser.g:2174:1: (lv_versionConstraint_1_0= ruleVersionConstraint )
                    // InternalN4MFParser.g:2175:3: lv_versionConstraint_1_0= ruleVersionConstraint
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

            // InternalN4MFParser.g:2191:3: ( (lv_declaredScope_2_0= ruleProjectDependencyScope ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==Compile||LA26_0==Test) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalN4MFParser.g:2192:1: (lv_declaredScope_2_0= ruleProjectDependencyScope )
                    {
                    // InternalN4MFParser.g:2192:1: (lv_declaredScope_2_0= ruleProjectDependencyScope )
                    // InternalN4MFParser.g:2193:3: lv_declaredScope_2_0= ruleProjectDependencyScope
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
    // InternalN4MFParser.g:2217:1: entryRuleSimpleProjectDescription returns [EObject current=null] : iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF ;
    public final EObject entryRuleSimpleProjectDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleProjectDescription = null;


        try {
            // InternalN4MFParser.g:2218:2: (iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF )
            // InternalN4MFParser.g:2219:2: iv_ruleSimpleProjectDescription= ruleSimpleProjectDescription EOF
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
    // InternalN4MFParser.g:2226:1: ruleSimpleProjectDescription returns [EObject current=null] : ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_artifactId_2_0= ruleN4mfIdentifier ) ) ) ;
    public final EObject ruleSimpleProjectDescription() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_declaredVendorId_0_0 = null;

        AntlrDatatypeRuleToken lv_artifactId_2_0 = null;


         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2229:28: ( ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_artifactId_2_0= ruleN4mfIdentifier ) ) ) )
            // InternalN4MFParser.g:2230:1: ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_artifactId_2_0= ruleN4mfIdentifier ) ) )
            {
            // InternalN4MFParser.g:2230:1: ( ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_artifactId_2_0= ruleN4mfIdentifier ) ) )
            // InternalN4MFParser.g:2230:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )? ( (lv_artifactId_2_0= ruleN4mfIdentifier ) )
            {
            // InternalN4MFParser.g:2230:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )?
            int alt27=2;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // InternalN4MFParser.g:2230:3: ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon
                    {
                    // InternalN4MFParser.g:2230:3: ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) )
                    // InternalN4MFParser.g:2231:1: (lv_declaredVendorId_0_0= ruleN4mfIdentifier )
                    {
                    // InternalN4MFParser.g:2231:1: (lv_declaredVendorId_0_0= ruleN4mfIdentifier )
                    // InternalN4MFParser.g:2232:3: lv_declaredVendorId_0_0= ruleN4mfIdentifier
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

            // InternalN4MFParser.g:2253:3: ( (lv_artifactId_2_0= ruleN4mfIdentifier ) )
            // InternalN4MFParser.g:2254:1: (lv_artifactId_2_0= ruleN4mfIdentifier )
            {
            // InternalN4MFParser.g:2254:1: (lv_artifactId_2_0= ruleN4mfIdentifier )
            // InternalN4MFParser.g:2255:3: lv_artifactId_2_0= ruleN4mfIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getSimpleProjectDescriptionAccess().getArtifactIdN4mfIdentifierParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_artifactId_2_0=ruleN4mfIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSimpleProjectDescriptionRule());
            	        }
                   		set(
                   			current, 
                   			"artifactId",
                    		lv_artifactId_2_0, 
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
    // InternalN4MFParser.g:2279:1: entryRuleVersionConstraint returns [EObject current=null] : iv_ruleVersionConstraint= ruleVersionConstraint EOF ;
    public final EObject entryRuleVersionConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVersionConstraint = null;


        try {
            // InternalN4MFParser.g:2280:2: (iv_ruleVersionConstraint= ruleVersionConstraint EOF )
            // InternalN4MFParser.g:2281:2: iv_ruleVersionConstraint= ruleVersionConstraint EOF
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
    // InternalN4MFParser.g:2288:1: ruleVersionConstraint returns [EObject current=null] : ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) ) ;
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
            // InternalN4MFParser.g:2291:28: ( ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) ) )
            // InternalN4MFParser.g:2292:1: ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) )
            {
            // InternalN4MFParser.g:2292:1: ( ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) ) | ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LeftParenthesis||LA32_0==LeftSquareBracket) ) {
                alt32=1;
            }
            else if ( (LA32_0==RULE_INT) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalN4MFParser.g:2292:2: ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) )
                    {
                    // InternalN4MFParser.g:2292:2: ( ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis ) )
                    // InternalN4MFParser.g:2292:3: ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket ) ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) ) ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis )
                    {
                    // InternalN4MFParser.g:2292:3: ( ( (lv_exclLowerBound_0_0= LeftParenthesis ) ) | otherlv_1= LeftSquareBracket )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==LeftParenthesis) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==LeftSquareBracket) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalN4MFParser.g:2292:4: ( (lv_exclLowerBound_0_0= LeftParenthesis ) )
                            {
                            // InternalN4MFParser.g:2292:4: ( (lv_exclLowerBound_0_0= LeftParenthesis ) )
                            // InternalN4MFParser.g:2293:1: (lv_exclLowerBound_0_0= LeftParenthesis )
                            {
                            // InternalN4MFParser.g:2293:1: (lv_exclLowerBound_0_0= LeftParenthesis )
                            // InternalN4MFParser.g:2294:3: lv_exclLowerBound_0_0= LeftParenthesis
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
                            // InternalN4MFParser.g:2310:2: otherlv_1= LeftSquareBracket
                            {
                            otherlv_1=(Token)match(input,LeftSquareBracket,FOLLOW_8); 

                                	newLeafNode(otherlv_1, grammarAccess.getVersionConstraintAccess().getLeftSquareBracketKeyword_0_0_1());
                                

                            }
                            break;

                    }

                    // InternalN4MFParser.g:2314:2: ( (lv_lowerVersion_2_0= ruleDeclaredVersion ) )
                    // InternalN4MFParser.g:2315:1: (lv_lowerVersion_2_0= ruleDeclaredVersion )
                    {
                    // InternalN4MFParser.g:2315:1: (lv_lowerVersion_2_0= ruleDeclaredVersion )
                    // InternalN4MFParser.g:2316:3: lv_lowerVersion_2_0= ruleDeclaredVersion
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

                    // InternalN4MFParser.g:2332:2: ( (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )? | otherlv_7= RightParenthesis )
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==EOF||LA31_0==Compile||LA31_0==Test||LA31_0==Comma||LA31_0==RightCurlyBracket) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==RightParenthesis) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 0, input);

                        throw nvae;
                    }
                    switch (alt31) {
                        case 1 :
                            // InternalN4MFParser.g:2332:3: (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )?
                            {
                            // InternalN4MFParser.g:2332:3: (otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket ) )?
                            int alt30=2;
                            int LA30_0 = input.LA(1);

                            if ( (LA30_0==Comma) ) {
                                int LA30_1 = input.LA(2);

                                if ( (LA30_1==RULE_INT) ) {
                                    alt30=1;
                                }
                            }
                            switch (alt30) {
                                case 1 :
                                    // InternalN4MFParser.g:2333:2: otherlv_3= Comma ( (lv_upperVersion_4_0= ruleDeclaredVersion ) ) ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket )
                                    {
                                    otherlv_3=(Token)match(input,Comma,FOLLOW_8); 

                                        	newLeafNode(otherlv_3, grammarAccess.getVersionConstraintAccess().getCommaKeyword_0_2_0_0());
                                        
                                    // InternalN4MFParser.g:2337:1: ( (lv_upperVersion_4_0= ruleDeclaredVersion ) )
                                    // InternalN4MFParser.g:2338:1: (lv_upperVersion_4_0= ruleDeclaredVersion )
                                    {
                                    // InternalN4MFParser.g:2338:1: (lv_upperVersion_4_0= ruleDeclaredVersion )
                                    // InternalN4MFParser.g:2339:3: lv_upperVersion_4_0= ruleDeclaredVersion
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

                                    // InternalN4MFParser.g:2355:2: ( ( (lv_exclUpperBound_5_0= RightParenthesis ) ) | otherlv_6= RightSquareBracket )
                                    int alt29=2;
                                    int LA29_0 = input.LA(1);

                                    if ( (LA29_0==RightParenthesis) ) {
                                        alt29=1;
                                    }
                                    else if ( (LA29_0==RightSquareBracket) ) {
                                        alt29=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 29, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt29) {
                                        case 1 :
                                            // InternalN4MFParser.g:2355:3: ( (lv_exclUpperBound_5_0= RightParenthesis ) )
                                            {
                                            // InternalN4MFParser.g:2355:3: ( (lv_exclUpperBound_5_0= RightParenthesis ) )
                                            // InternalN4MFParser.g:2356:1: (lv_exclUpperBound_5_0= RightParenthesis )
                                            {
                                            // InternalN4MFParser.g:2356:1: (lv_exclUpperBound_5_0= RightParenthesis )
                                            // InternalN4MFParser.g:2357:3: lv_exclUpperBound_5_0= RightParenthesis
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
                                            // InternalN4MFParser.g:2373:2: otherlv_6= RightSquareBracket
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
                            // InternalN4MFParser.g:2379:2: otherlv_7= RightParenthesis
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
                    // InternalN4MFParser.g:2384:6: ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) )
                    {
                    // InternalN4MFParser.g:2384:6: ( (lv_lowerVersion_8_0= ruleDeclaredVersion ) )
                    // InternalN4MFParser.g:2385:1: (lv_lowerVersion_8_0= ruleDeclaredVersion )
                    {
                    // InternalN4MFParser.g:2385:1: (lv_lowerVersion_8_0= ruleDeclaredVersion )
                    // InternalN4MFParser.g:2386:3: lv_lowerVersion_8_0= ruleDeclaredVersion
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
    // InternalN4MFParser.g:2410:1: entryRuleN4mfIdentifier returns [String current=null] : iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF ;
    public final String entryRuleN4mfIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleN4mfIdentifier = null;


        try {
            // InternalN4MFParser.g:2411:1: (iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF )
            // InternalN4MFParser.g:2412:2: iv_ruleN4mfIdentifier= ruleN4mfIdentifier EOF
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
    // InternalN4MFParser.g:2419:1: ruleN4mfIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | kw= ArtifactId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test ) ;
    public final AntlrDatatypeRuleToken ruleN4mfIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // InternalN4MFParser.g:2423:6: ( (this_ID_0= RULE_ID | kw= ArtifactId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test ) )
            // InternalN4MFParser.g:2424:1: (this_ID_0= RULE_ID | kw= ArtifactId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test )
            {
            // InternalN4MFParser.g:2424:1: (this_ID_0= RULE_ID | kw= ArtifactId | kw= VendorId | kw= ProjectName | kw= VendorName | kw= ProjectType | kw= ProjectVersion | kw= Output | kw= Libraries | kw= Resources | kw= Sources | kw= ModuleFilters | (kw= ProjectDependencies kw= KW_System ) | kw= API | kw= User | kw= Application | (kw= Processor kw= Source ) | kw= Content | kw= Test )
            int alt33=19;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt33=1;
                }
                break;
            case ArtifactId:
                {
                alt33=2;
                }
                break;
            case VendorId:
                {
                alt33=3;
                }
                break;
            case ProjectName:
                {
                alt33=4;
                }
                break;
            case VendorName:
                {
                alt33=5;
                }
                break;
            case ProjectType:
                {
                alt33=6;
                }
                break;
            case ProjectVersion:
                {
                alt33=7;
                }
                break;
            case Output:
                {
                alt33=8;
                }
                break;
            case Libraries:
                {
                alt33=9;
                }
                break;
            case Resources:
                {
                alt33=10;
                }
                break;
            case Sources:
                {
                alt33=11;
                }
                break;
            case ModuleFilters:
                {
                alt33=12;
                }
                break;
            case ProjectDependencies:
                {
                alt33=13;
                }
                break;
            case API:
                {
                alt33=14;
                }
                break;
            case User:
                {
                alt33=15;
                }
                break;
            case Application:
                {
                alt33=16;
                }
                break;
            case Processor:
                {
                alt33=17;
                }
                break;
            case Content:
                {
                alt33=18;
                }
                break;
            case Test:
                {
                alt33=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // InternalN4MFParser.g:2424:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getN4mfIdentifierAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2433:2: kw= ArtifactId
                    {
                    kw=(Token)match(input,ArtifactId,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getArtifactIdKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2440:2: kw= VendorId
                    {
                    kw=(Token)match(input,VendorId,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getVendorIdKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:2447:2: kw= ProjectName
                    {
                    kw=(Token)match(input,ProjectName,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectNameKeyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:2454:2: kw= VendorName
                    {
                    kw=(Token)match(input,VendorName,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getVendorNameKeyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:2461:2: kw= ProjectType
                    {
                    kw=(Token)match(input,ProjectType,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectTypeKeyword_5()); 
                        

                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:2468:2: kw= ProjectVersion
                    {
                    kw=(Token)match(input,ProjectVersion,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectVersionKeyword_6()); 
                        

                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:2475:2: kw= Output
                    {
                    kw=(Token)match(input,Output,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getOutputKeyword_7()); 
                        

                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:2482:2: kw= Libraries
                    {
                    kw=(Token)match(input,Libraries,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getLibrariesKeyword_8()); 
                        

                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:2489:2: kw= Resources
                    {
                    kw=(Token)match(input,Resources,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getResourcesKeyword_9()); 
                        

                    }
                    break;
                case 11 :
                    // InternalN4MFParser.g:2496:2: kw= Sources
                    {
                    kw=(Token)match(input,Sources,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSourcesKeyword_10()); 
                        

                    }
                    break;
                case 12 :
                    // InternalN4MFParser.g:2503:2: kw= ModuleFilters
                    {
                    kw=(Token)match(input,ModuleFilters,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getModuleFiltersKeyword_11()); 
                        

                    }
                    break;
                case 13 :
                    // InternalN4MFParser.g:2509:6: (kw= ProjectDependencies kw= KW_System )
                    {
                    // InternalN4MFParser.g:2509:6: (kw= ProjectDependencies kw= KW_System )
                    // InternalN4MFParser.g:2510:2: kw= ProjectDependencies kw= KW_System
                    {
                    kw=(Token)match(input,ProjectDependencies,FOLLOW_25); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProjectDependenciesKeyword_12_0()); 
                        
                    kw=(Token)match(input,KW_System,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSystemKeyword_12_1()); 
                        

                    }


                    }
                    break;
                case 14 :
                    // InternalN4MFParser.g:2523:2: kw= API
                    {
                    kw=(Token)match(input,API,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getAPIKeyword_13()); 
                        

                    }
                    break;
                case 15 :
                    // InternalN4MFParser.g:2530:2: kw= User
                    {
                    kw=(Token)match(input,User,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getUserKeyword_14()); 
                        

                    }
                    break;
                case 16 :
                    // InternalN4MFParser.g:2537:2: kw= Application
                    {
                    kw=(Token)match(input,Application,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getApplicationKeyword_15()); 
                        

                    }
                    break;
                case 17 :
                    // InternalN4MFParser.g:2543:6: (kw= Processor kw= Source )
                    {
                    // InternalN4MFParser.g:2543:6: (kw= Processor kw= Source )
                    // InternalN4MFParser.g:2544:2: kw= Processor kw= Source
                    {
                    kw=(Token)match(input,Processor,FOLLOW_26); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getProcessorKeyword_16_0()); 
                        
                    kw=(Token)match(input,Source,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getSourceKeyword_16_1()); 
                        

                    }


                    }
                    break;
                case 18 :
                    // InternalN4MFParser.g:2557:2: kw= Content
                    {
                    kw=(Token)match(input,Content,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getContentKeyword_17()); 
                        

                    }
                    break;
                case 19 :
                    // InternalN4MFParser.g:2564:2: kw= Test
                    {
                    kw=(Token)match(input,Test,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getN4mfIdentifierAccess().getTestKeyword_18()); 
                        

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
    // InternalN4MFParser.g:2577:1: ruleProjectType returns [Enumerator current=null] : ( (enumLiteral_0= Application ) | (enumLiteral_1= App ) | (enumLiteral_2= Processor ) | (enumLiteral_3= Library ) | (enumLiteral_4= Lib ) | (enumLiteral_5= KW_System ) | (enumLiteral_6= API ) | (enumLiteral_7= RuntimeEnvironment ) | (enumLiteral_8= RuntimeLibrary ) | (enumLiteral_9= Test ) ) ;
    public final Enumerator ruleProjectType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2579:28: ( ( (enumLiteral_0= Application ) | (enumLiteral_1= App ) | (enumLiteral_2= Processor ) | (enumLiteral_3= Library ) | (enumLiteral_4= Lib ) | (enumLiteral_5= KW_System ) | (enumLiteral_6= API ) | (enumLiteral_7= RuntimeEnvironment ) | (enumLiteral_8= RuntimeLibrary ) | (enumLiteral_9= Test ) ) )
            // InternalN4MFParser.g:2580:1: ( (enumLiteral_0= Application ) | (enumLiteral_1= App ) | (enumLiteral_2= Processor ) | (enumLiteral_3= Library ) | (enumLiteral_4= Lib ) | (enumLiteral_5= KW_System ) | (enumLiteral_6= API ) | (enumLiteral_7= RuntimeEnvironment ) | (enumLiteral_8= RuntimeLibrary ) | (enumLiteral_9= Test ) )
            {
            // InternalN4MFParser.g:2580:1: ( (enumLiteral_0= Application ) | (enumLiteral_1= App ) | (enumLiteral_2= Processor ) | (enumLiteral_3= Library ) | (enumLiteral_4= Lib ) | (enumLiteral_5= KW_System ) | (enumLiteral_6= API ) | (enumLiteral_7= RuntimeEnvironment ) | (enumLiteral_8= RuntimeLibrary ) | (enumLiteral_9= Test ) )
            int alt34=10;
            switch ( input.LA(1) ) {
            case Application:
                {
                alt34=1;
                }
                break;
            case App:
                {
                alt34=2;
                }
                break;
            case Processor:
                {
                alt34=3;
                }
                break;
            case Library:
                {
                alt34=4;
                }
                break;
            case Lib:
                {
                alt34=5;
                }
                break;
            case KW_System:
                {
                alt34=6;
                }
                break;
            case API:
                {
                alt34=7;
                }
                break;
            case RuntimeEnvironment:
                {
                alt34=8;
                }
                break;
            case RuntimeLibrary:
                {
                alt34=9;
                }
                break;
            case Test:
                {
                alt34=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // InternalN4MFParser.g:2580:2: (enumLiteral_0= Application )
                    {
                    // InternalN4MFParser.g:2580:2: (enumLiteral_0= Application )
                    // InternalN4MFParser.g:2580:7: enumLiteral_0= Application
                    {
                    enumLiteral_0=(Token)match(input,Application,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2586:6: (enumLiteral_1= App )
                    {
                    // InternalN4MFParser.g:2586:6: (enumLiteral_1= App )
                    // InternalN4MFParser.g:2586:11: enumLiteral_1= App
                    {
                    enumLiteral_1=(Token)match(input,App,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getProjectTypeAccess().getAPPLICATIONEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2592:6: (enumLiteral_2= Processor )
                    {
                    // InternalN4MFParser.g:2592:6: (enumLiteral_2= Processor )
                    // InternalN4MFParser.g:2592:11: enumLiteral_2= Processor
                    {
                    enumLiteral_2=(Token)match(input,Processor,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getProjectTypeAccess().getPROCESSOREnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalN4MFParser.g:2598:6: (enumLiteral_3= Library )
                    {
                    // InternalN4MFParser.g:2598:6: (enumLiteral_3= Library )
                    // InternalN4MFParser.g:2598:11: enumLiteral_3= Library
                    {
                    enumLiteral_3=(Token)match(input,Library,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalN4MFParser.g:2604:6: (enumLiteral_4= Lib )
                    {
                    // InternalN4MFParser.g:2604:6: (enumLiteral_4= Lib )
                    // InternalN4MFParser.g:2604:11: enumLiteral_4= Lib
                    {
                    enumLiteral_4=(Token)match(input,Lib,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getProjectTypeAccess().getLIBRARYEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalN4MFParser.g:2610:6: (enumLiteral_5= KW_System )
                    {
                    // InternalN4MFParser.g:2610:6: (enumLiteral_5= KW_System )
                    // InternalN4MFParser.g:2610:11: enumLiteral_5= KW_System
                    {
                    enumLiteral_5=(Token)match(input,KW_System,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getSYSTEMEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getProjectTypeAccess().getSYSTEMEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // InternalN4MFParser.g:2616:6: (enumLiteral_6= API )
                    {
                    // InternalN4MFParser.g:2616:6: (enumLiteral_6= API )
                    // InternalN4MFParser.g:2616:11: enumLiteral_6= API
                    {
                    enumLiteral_6=(Token)match(input,API,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getProjectTypeAccess().getAPIEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // InternalN4MFParser.g:2622:6: (enumLiteral_7= RuntimeEnvironment )
                    {
                    // InternalN4MFParser.g:2622:6: (enumLiteral_7= RuntimeEnvironment )
                    // InternalN4MFParser.g:2622:11: enumLiteral_7= RuntimeEnvironment
                    {
                    enumLiteral_7=(Token)match(input,RuntimeEnvironment,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getProjectTypeAccess().getRUNTIME_ENVIRONMENTEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // InternalN4MFParser.g:2628:6: (enumLiteral_8= RuntimeLibrary )
                    {
                    // InternalN4MFParser.g:2628:6: (enumLiteral_8= RuntimeLibrary )
                    // InternalN4MFParser.g:2628:11: enumLiteral_8= RuntimeLibrary
                    {
                    enumLiteral_8=(Token)match(input,RuntimeLibrary,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getProjectTypeAccess().getRUNTIME_LIBRARYEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // InternalN4MFParser.g:2634:6: (enumLiteral_9= Test )
                    {
                    // InternalN4MFParser.g:2634:6: (enumLiteral_9= Test )
                    // InternalN4MFParser.g:2634:11: enumLiteral_9= Test
                    {
                    enumLiteral_9=(Token)match(input,Test,FOLLOW_2); 

                            current = grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getProjectTypeAccess().getTESTEnumLiteralDeclaration_9()); 
                        

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
    // InternalN4MFParser.g:2644:1: ruleSourceFragmentType returns [Enumerator current=null] : ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) ) ;
    public final Enumerator ruleSourceFragmentType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2646:28: ( ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) ) )
            // InternalN4MFParser.g:2647:1: ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) )
            {
            // InternalN4MFParser.g:2647:1: ( (enumLiteral_0= Source ) | (enumLiteral_1= External ) | (enumLiteral_2= Test ) )
            int alt35=3;
            switch ( input.LA(1) ) {
            case Source:
                {
                alt35=1;
                }
                break;
            case External:
                {
                alt35=2;
                }
                break;
            case Test:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // InternalN4MFParser.g:2647:2: (enumLiteral_0= Source )
                    {
                    // InternalN4MFParser.g:2647:2: (enumLiteral_0= Source )
                    // InternalN4MFParser.g:2647:7: enumLiteral_0= Source
                    {
                    enumLiteral_0=(Token)match(input,Source,FOLLOW_2); 

                            current = grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSourceFragmentTypeAccess().getSOURCEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2653:6: (enumLiteral_1= External )
                    {
                    // InternalN4MFParser.g:2653:6: (enumLiteral_1= External )
                    // InternalN4MFParser.g:2653:11: enumLiteral_1= External
                    {
                    enumLiteral_1=(Token)match(input,External,FOLLOW_2); 

                            current = grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getSourceFragmentTypeAccess().getEXTERNALEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2659:6: (enumLiteral_2= Test )
                    {
                    // InternalN4MFParser.g:2659:6: (enumLiteral_2= Test )
                    // InternalN4MFParser.g:2659:11: enumLiteral_2= Test
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
    // InternalN4MFParser.g:2669:1: ruleModuleFilterType returns [Enumerator current=null] : ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) ) ;
    public final Enumerator ruleModuleFilterType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2671:28: ( ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) ) )
            // InternalN4MFParser.g:2672:1: ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) )
            {
            // InternalN4MFParser.g:2672:1: ( (enumLiteral_0= NoValidate ) | (enumLiteral_1= NoModuleWrap ) )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==NoValidate) ) {
                alt36=1;
            }
            else if ( (LA36_0==NoModuleWrap) ) {
                alt36=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // InternalN4MFParser.g:2672:2: (enumLiteral_0= NoValidate )
                    {
                    // InternalN4MFParser.g:2672:2: (enumLiteral_0= NoValidate )
                    // InternalN4MFParser.g:2672:7: enumLiteral_0= NoValidate
                    {
                    enumLiteral_0=(Token)match(input,NoValidate,FOLLOW_2); 

                            current = grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getModuleFilterTypeAccess().getNO_VALIDATEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2678:6: (enumLiteral_1= NoModuleWrap )
                    {
                    // InternalN4MFParser.g:2678:6: (enumLiteral_1= NoModuleWrap )
                    // InternalN4MFParser.g:2678:11: enumLiteral_1= NoModuleWrap
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
    // InternalN4MFParser.g:2688:1: ruleProjectDependencyScope returns [Enumerator current=null] : ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) ) ;
    public final Enumerator ruleProjectDependencyScope() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2690:28: ( ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) ) )
            // InternalN4MFParser.g:2691:1: ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) )
            {
            // InternalN4MFParser.g:2691:1: ( (enumLiteral_0= Compile ) | (enumLiteral_1= Test ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==Compile) ) {
                alt37=1;
            }
            else if ( (LA37_0==Test) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalN4MFParser.g:2691:2: (enumLiteral_0= Compile )
                    {
                    // InternalN4MFParser.g:2691:2: (enumLiteral_0= Compile )
                    // InternalN4MFParser.g:2691:7: enumLiteral_0= Compile
                    {
                    enumLiteral_0=(Token)match(input,Compile,FOLLOW_2); 

                            current = grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getProjectDependencyScopeAccess().getCOMPILEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2697:6: (enumLiteral_1= Test )
                    {
                    // InternalN4MFParser.g:2697:6: (enumLiteral_1= Test )
                    // InternalN4MFParser.g:2697:11: enumLiteral_1= Test
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
    // InternalN4MFParser.g:2707:1: ruleModuleLoader returns [Enumerator current=null] : ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) ) ;
    public final Enumerator ruleModuleLoader() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalN4MFParser.g:2709:28: ( ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) ) )
            // InternalN4MFParser.g:2710:1: ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) )
            {
            // InternalN4MFParser.g:2710:1: ( (enumLiteral_0= N4js ) | (enumLiteral_1= Commonjs ) | (enumLiteral_2= Node_builtin ) )
            int alt38=3;
            switch ( input.LA(1) ) {
            case N4js:
                {
                alt38=1;
                }
                break;
            case Commonjs:
                {
                alt38=2;
                }
                break;
            case Node_builtin:
                {
                alt38=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // InternalN4MFParser.g:2710:2: (enumLiteral_0= N4js )
                    {
                    // InternalN4MFParser.g:2710:2: (enumLiteral_0= N4js )
                    // InternalN4MFParser.g:2710:7: enumLiteral_0= N4js
                    {
                    enumLiteral_0=(Token)match(input,N4js,FOLLOW_2); 

                            current = grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getModuleLoaderAccess().getN4JSEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalN4MFParser.g:2716:6: (enumLiteral_1= Commonjs )
                    {
                    // InternalN4MFParser.g:2716:6: (enumLiteral_1= Commonjs )
                    // InternalN4MFParser.g:2716:11: enumLiteral_1= Commonjs
                    {
                    enumLiteral_1=(Token)match(input,Commonjs,FOLLOW_2); 

                            current = grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getModuleLoaderAccess().getCOMMONJSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalN4MFParser.g:2722:6: (enumLiteral_2= Node_builtin )
                    {
                    // InternalN4MFParser.g:2722:6: (enumLiteral_2= Node_builtin )
                    // InternalN4MFParser.g:2722:11: enumLiteral_2= Node_builtin
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


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA27 dfa27 = new DFA27(this);
    static final String dfa_1s = "\30\uffff";
    static final String dfa_2s = "\1\1\27\uffff";
    static final String dfa_3s = "\1\4\27\uffff";
    static final String dfa_4s = "\1\45\27\uffff";
    static final String dfa_5s = "\1\uffff\1\27\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26";
    static final String dfa_6s = "\1\0\27\uffff}>";
    static final String[] dfa_7s = {
            "\1\11\1\12\1\13\1\16\1\14\1\uffff\1\15\1\5\1\26\1\uffff\1\25\1\27\2\uffff\1\17\1\3\1\4\1\uffff\1\2\1\20\1\10\1\7\1\uffff\1\22\1\23\1\uffff\1\6\2\uffff\1\24\3\uffff\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 82:3: ( ({...}? => ( ({...}? => (otherlv_1= ArtifactId otherlv_2= Colon ( (lv_artifactId_3_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= ProjectName otherlv_5= Colon ( (lv_projectName_6_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= ProjectType otherlv_8= Colon ( (lv_projectType_9_0= ruleProjectType ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= ProjectVersion otherlv_11= Colon ( (lv_projectVersion_12_0= ruleDeclaredVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= VendorId otherlv_14= Colon ( (lv_declaredVendorId_15_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= VendorName otherlv_17= Colon ( (lv_vendorName_18_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= MainModule otherlv_20= Colon ( (lv_mainModule_21_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_extendedRuntimeEnvironment_22_0= ruleExtendedRuntimeEnvironment ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_providedRuntimeLibraries_23_0= ruleProvidedRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_requiredRuntimeLibraries_24_0= ruleRequiredRuntimeLibraries ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_projectDependencies_25_0= ruleProjectDependencies ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= ImplementationId otherlv_27= Colon ( (lv_implementationId_28_0= ruleN4mfIdentifier ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_implementedProjects_29_0= ruleImplementedProjects ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_initModules_30_0= ruleInitModules ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_execModule_31_0= ruleExecModule ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_32= Output otherlv_33= Colon ( (lv_outputPath_34_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_35= Libraries otherlv_36= LeftCurlyBracket ( (lv_libraryPaths_37_0= RULE_STRING ) ) (otherlv_38= Comma ( (lv_libraryPaths_39_0= RULE_STRING ) ) )* otherlv_40= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_41= Resources otherlv_42= LeftCurlyBracket ( (lv_resourcePaths_43_0= RULE_STRING ) ) (otherlv_44= Comma ( (lv_resourcePaths_45_0= RULE_STRING ) ) )* otherlv_46= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_47= Sources otherlv_48= LeftCurlyBracket ( (lv_sourceFragment_49_0= ruleSourceFragment ) )+ otherlv_50= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => (otherlv_51= ModuleFilters otherlv_52= LeftCurlyBracket ( (lv_moduleFilters_53_0= ruleModuleFilter ) )+ otherlv_54= RightCurlyBracket ) ) ) ) | ({...}? => ( ({...}? => ( (lv_testedProjects_55_0= ruleTestedProjects ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_56= ModuleLoader otherlv_57= Colon ( (lv_moduleLoader_58_0= ruleModuleLoader ) ) ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_0 = input.LA(1);

                         
                        int index5_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA5_0==EOF) ) {s = 1;}

                        else if ( LA5_0 == ArtifactId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 0) ) {s = 2;}

                        else if ( LA5_0 == ProjectName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 1) ) {s = 3;}

                        else if ( LA5_0 == ProjectType && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 2) ) {s = 4;}

                        else if ( LA5_0 == ProjectVersion && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 3) ) {s = 5;}

                        else if ( LA5_0 == VendorId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 4) ) {s = 6;}

                        else if ( LA5_0 == VendorName && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 5) ) {s = 7;}

                        else if ( LA5_0 == MainModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 6) ) {s = 8;}

                        else if ( LA5_0 == ExtendedRuntimeEnvironment && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 7) ) {s = 9;}

                        else if ( LA5_0 == ProvidedRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 8) ) {s = 10;}

                        else if ( LA5_0 == RequiredRuntimeLibraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 9) ) {s = 11;}

                        else if ( LA5_0 == ProjectDependencies && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 10) ) {s = 12;}

                        else if ( LA5_0 == ImplementationId && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 11) ) {s = 13;}

                        else if ( LA5_0 == ImplementedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 12) ) {s = 14;}

                        else if ( LA5_0 == InitModules && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 13) ) {s = 15;}

                        else if ( LA5_0 == ExecModule && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 14) ) {s = 16;}

                        else if ( LA5_0 == Output && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 15) ) {s = 17;}

                        else if ( LA5_0 == Libraries && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 16) ) {s = 18;}

                        else if ( LA5_0 == Resources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 17) ) {s = 19;}

                        else if ( LA5_0 == Sources && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 18) ) {s = 20;}

                        else if ( LA5_0 == ModuleFilters && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 19) ) {s = 21;}

                        else if ( LA5_0 == TestedProjects && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 20) ) {s = 22;}

                        else if ( LA5_0 == ModuleLoader && getUnorderedGroupHelper().canSelect(grammarAccess.getProjectDescriptionAccess().getUnorderedGroup(), 21) ) {s = 23;}

                         
                        input.seek(index5_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_8s = "\1\uffff\14\25\1\uffff\3\25\1\uffff\2\25\2\uffff\2\25";
    static final String dfa_9s = "\1\10\14\4\1\47\3\4\1\46\2\4\2\uffff\2\4";
    static final String dfa_10s = "\1\71\14\72\1\47\3\72\1\46\2\72\2\uffff\2\72";
    static final String dfa_11s = "\24\uffff\1\1\1\2\2\uffff";
    static final String dfa_12s = "\30\uffff}>";
    static final String[] dfa_13s = {
            "\1\15\2\uffff\1\7\2\uffff\1\14\4\uffff\1\4\1\6\1\20\1\2\2\uffff\1\5\1\uffff\1\11\1\12\1\21\1\3\2\uffff\1\13\1\uffff\1\22\1\uffff\1\10\3\uffff\1\23\1\17\1\16\15\uffff\1\1",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\1\26",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\1\27",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "",
            "",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25",
            "\5\25\1\uffff\3\25\1\uffff\2\25\2\uffff\3\25\1\uffff\4\25\1\uffff\2\25\1\uffff\1\25\2\uffff\2\25\2\uffff\1\25\3\uffff\1\25\5\uffff\1\25\1\uffff\1\25\2\uffff\1\24\1\25\2\uffff\1\25\1\uffff\1\25"
    };
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = dfa_1;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "2230:2: ( ( (lv_declaredVendorId_0_0= ruleN4mfIdentifier ) ) otherlv_1= Colon )?";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x02000E2A7A784900L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000225BDCDDF2L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00003A9020202200L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0102000000000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000024100000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0100024100000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000004010000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0100000004010000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000010080020000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x03000E2A7A784900L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0900000000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000C000000000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0420820400000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000020400000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0041000000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000004000000000L});

}
