
/*
 * generated by Xtext
 */
lexer grammar InternalN4MFLexer;


@header {
package eu.numberfour.n4js.n4mf.ui.contentassist.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}




ExtendedRuntimeEnvironment : 'ExtendedRuntimeEnvironment';

ProvidedRuntimeLibraries : 'ProvidedRuntimeLibraries';

RequiredRuntimeLibraries : 'RequiredRuntimeLibraries';

ImplementedProjects : 'ImplementedProjects';

ProjectDependencies : 'ProjectDependencies';

RuntimeEnvironment : 'runtimeEnvironment';

ImplementationId : 'ImplementationId';

ProjectVersion : 'ProjectVersion';

TestedProjects : 'TestedProjects';

RuntimeLibrary : 'runtimeLibrary';

ModuleFilters : 'ModuleFilters';

ModuleLoader : 'ModuleLoader';

NoModuleWrap : 'noModuleWrap';

Node_builtin : 'node_builtin';

InitModules : 'InitModules';

ProjectName : 'ProjectName';

ProjectType : 'ProjectType';

Application : 'application';

ArtifactId : 'ArtifactId';

ExecModule : 'ExecModule';

MainModule : 'MainModule';

VendorName : 'VendorName';

NoValidate : 'noValidate';

Libraries : 'Libraries';

Resources : 'Resources';

Processor : 'processor';

VendorId : 'VendorId';

Commonjs : 'commonjs';

External : 'external';

Sources : 'Sources';

Compile : 'compile';

Content : 'content';

Library : 'library';

Output : 'Output';

Source : 'source';

KW_System : 'system';

N4js : 'n4js';

Test : 'test';

User : 'user';

API : 'API';

App : 'app';

Lib : 'lib';

In : 'in';

LeftParenthesis : '(';

RightParenthesis : ')';

Comma : ',';

HyphenMinus : '-';

FullStop : '.';

Colon : ':';

LeftSquareBracket : '[';

RightSquareBracket : ']';

LeftCurlyBracket : '{';

RightCurlyBracket : '}';



RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'-'|'.'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;



