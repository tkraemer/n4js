/*
 */
package eu.numberfour.n4js.formatting2;

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.AbstractCaseClause
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.AwaitExpression
import eu.numberfour.n4js.n4JS.BinaryBitwiseExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.CastExpression
import eu.numberfour.n4js.n4JS.CatchBlock
import eu.numberfour.n4js.n4JS.CommaExpression
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.EqualityExpression
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.FinallyBlock
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.IfStatement
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.IndexedAccessExpression
import eu.numberfour.n4js.n4JS.IntLiteral
import eu.numberfour.n4js.n4JS.MultiplicativeExpression
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4EnumDeclaration
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.NullLiteral
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.PostfixExpression
import eu.numberfour.n4js.n4JS.PromisifyExpression
import eu.numberfour.n4js.n4JS.RelationalExpression
import eu.numberfour.n4js.n4JS.ReturnStatement
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.ShiftExpression
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.SwitchStatement
import eu.numberfour.n4js.n4JS.TaggedTemplateString
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.VariableBinding
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.VariableStatement
import eu.numberfour.n4js.n4JS.YieldExpression
import eu.numberfour.n4js.services.N4JSGrammarAccess
import eu.numberfour.n4js.ts.formatting2.TypeExpressionsFormatter
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.formatting2.ITextReplacer
import org.eclipse.xtext.formatting2.internal.SinglelineCodeCommentReplacer
import org.eclipse.xtext.formatting2.internal.SinglelineDocCommentReplacer
import org.eclipse.xtext.formatting2.regionaccess.IComment
import org.eclipse.xtext.xtext.generator.parser.antlr.splitting.simpleExpressions.NumberLiteral

import static eu.numberfour.n4js.formatting2.N4JSFormatterPreferenceKeys.*
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.RegularExpressionLiteral
import eu.numberfour.n4js.n4JS.UnaryOperator
import eu.numberfour.n4js.services.N4JSGrammarAccess.ArrowExpressionElements
import org.eclipse.xtext.formatting2.IAutowrapFormatter
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment
import org.eclipse.xtext.formatting2.IHiddenRegionFormatting
import org.eclipse.xtext.formatting2.internal.HiddenRegionFormatting
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter
import eu.numberfour.n4js.n4JS.AnnotableN4MemberDeclaration
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor
import eu.numberfour.n4js.n4JS.AnnotationList
import eu.numberfour.n4js.n4JS.N4MemberAnnotationList
import eu.numberfour.n4js.n4JS.AbstractAnnotationList
import eu.numberfour.n4js.n4JS.AnnotableScriptElement
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.AnnotableExpression
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion
import org.eclipse.emf.common.util.EList
import eu.numberfour.n4js.n4JS.FormalParameter
import org.codehaus.jackson.map.deser.FromStringDeserializer
import eu.numberfour.n4js.n4JS.N4SetterDeclaration

class N4JSFormatter extends TypeExpressionsFormatter {

	@Inject extension N4JSGrammarAccess
	
	static val PRIO_1 = N4JSGenericFormatter.PRIO_1;
	static val PRIO_2 = N4JSGenericFormatter.PRIO_2;
	static val PRIO_3 = N4JSGenericFormatter.PRIO_3;
	static val PRIO_4 = PRIO_3+1;

	
	private def maxEmptyLines() { 
		getPreference(FORMAT_MAX_CONSECUTIVE_NEWLINES); 
	}


	def dispatch void format(Script script, extension IFormattableDocument document) {

		val extension generic = new N4JSGenericFormatter(_n4JSGrammarAccess, textRegionExtensions)
		if (getPreference(FORMAT_PARENTHESIS)) {
//			script.formatParenthesisBracketsAndBraces(document)
		}
		script.formatSemicolons(document)
		script.formatColon(document)

		for (element : script.scriptElements) {
			element.append[setNewLines(1, 1, maxEmptyLines); autowrap]
			element.format
		}

		// format last import, overrides default newLines:
		script.scriptElements.filter(ImportDeclaration).last?.append[setNewLines(2, 2, 3); highPriority];

	}

	def dispatch format(N4ClassDeclaration clazz, extension IFormattableDocument document) {
		clazz.insertSpaceInFrontOfCurlyBlockOpener(document);
		clazz.interior[indent];
		// TODO revise the following pattern of call-back implementations.
		// Define lambda for callback & normal use:
		val twolinesBeforeFirstMember = [int prio | clazz.ownedMembersRaw.head.prepend[newLines=2;priority = prio]];
		// Defines CallBack for autoWrap:
		val callBackOnAutoWrap = new IAutowrapFormatter{ // callback for Autowrapping with implements
				var didReconfigure = false; // track to only execute once.
				override format(ITextSegment region, IHiddenRegionFormatting wrapped, IFormattableDocument document) {
					if( !didReconfigure ) { 
						println("wrapped here:");
						println( region );
						twolinesBeforeFirstMember.apply(IHiddenRegionFormatter.HIGH_PRIORITY); // reformat with higher priority
						didReconfigure=true; // keep state.
					}
				}
			};		
		// 2nd version of implementing it:
		val IAutowrapFormatter callBackOnAutoWrap2 = [a,b,c|
			twolinesBeforeFirstMember.apply(IHiddenRegionFormatter.HIGH_PRIORITY)
		]
		
		// Allow for lineBreaks in front of keywords:
		clazz.regionFor.keyword("extends").prepend[
			setNewLines(0,0,1); // allow line break in front.
			autowrap; // required if the following debug-code will be 
			onAutowrap = [region,wrapped,doc| 
				// FIXME remove this debug-Callback.
				println("Formatting extends here:")
				println(region)
				println("Wrapped: ")
				println(wrapped)
			]; // Adding the callback implicitly turns on autoWrap !
//			it.autowrap(-1); // TODO autowrap turned off.
		];
		clazz.regionFor.keyword("implements").prepend[
			setNewLines(0,0,1);
			autowrap;
			priority = IHiddenRegionFormatter::LOW_PRIORITY;
			onAutowrap=callBackOnAutoWrap;
		];
		clazz.implementedInterfaceRefs.tail.forEach[prepend[
			autowrap;
			priority = IHiddenRegionFormatter::LOW_PRIORITY;
			onAutowrap=callBackOnAutoWrap;
		]];
		
		// special case if the header of the class spans multiple lines, then insert extra linebreak.
		val kwClass = clazz.regionFor.keyword("class");
		val kwBrace = clazz.regionFor.keyword("{"); // Autowrap-listener ?
		if( ! kwClass.lineRegions.head.contains( kwBrace ) ) { 
			twolinesBeforeFirstMember.apply(IHiddenRegionFormatter::NORMAL_PRIORITY);	
		} else {
			clazz.ownedMembersRaw.head.prepend[setNewLines(1,1,maxEmptyLines);autowrap;];
		}
		for (member : clazz.ownedMembersRaw) {
			member.append[setNewLines(1, 1, maxEmptyLines)]
			member.format
		}
	}

	def dispatch format(N4InterfaceDeclaration clazz, extension IFormattableDocument document) {
		clazz.insertSpaceInFrontOfCurlyBlockOpener(document);
		clazz.interior[indent];

		clazz.ownedMembersRaw.head.prepend[setNewLines(1, 1, maxEmptyLines)]
		for (member : clazz.ownedMembersRaw) {
			member.append[setNewLines(1, 1, maxEmptyLines)]
			member.format
		}
	}

//	def dispatch void format(N4MemberDeclaration member, extension IFormattableDocument document) {
//		member.regionFor.keyword("(").prepend[noSpace; newLines=0].append[noSpace]
//		
//		member.insertSpaceInfrontOfPropertyNames(document);
//		for (c : member.eContents) {
//			c.format;
//		}
//	}

	def dispatch void format(N4FieldDeclaration field, extension IFormattableDocument document) {
		field.configureAnnotations(document);
		field.insertSpaceInfrontOfPropertyNames(document);

		field.regionFor.keyword("=").prepend[oneSpace].append[oneSpace];
		field.expression.format;
	}

//	def dispatch format(N4MethodDeclaration method, extension IFormattableDocument document) {
//		method.configureAnnotations(document);
//		method.insertSpaceInfrontOfPropertyNames(document);
//		
//		method.regionFor.keyword("(").prepend[noSpace; newLines=0]
//
//		method.body.regionFor.keyword("{").prepend[oneSpace; newLines = 0]
//		for (child : method.eContents) {
//			child.format;
//		}
//	}
//	
	def dispatch void format(FunctionExpression funE, extension IFormattableDocument document) {
		funE.configureAnnotations(document);
		funE.insertSpaceInfrontOfPropertyNames(document);
		if (funE.isArrowFunction) {
			throw new IllegalStateException("Arrow functions should be formated differently.")
		}
		
		funE.body.format;
	}
		
	def dispatch format(FunctionOrFieldAccessor fDecl, extension IFormattableDocument document) {
		fDecl.configureAnnotations(document);
		
		val state = new StateTrack; // use state to only trigger one change, even if called multiple times.
		val (ITextSegment , IHiddenRegionFormatting , IFormattableDocument )=>void cbInsertEmptyLineInBody  = [
			if(state.doThenDone){
				fDecl.body?.statements?.head.prepend[ setNewLines(2,2,maxEmptyLines); highPriority];
			}
		];
		
		switch( fDecl) {
			FunctionDefinition :
				fDecl.fpars.configureFormalParameters(document, cbInsertEmptyLineInBody )
			N4SetterDeclaration:	
				fDecl.fpar.prepend[noSpace].append[noSpace] /* no autowrap for setters: cbInsertEmptyLineInBody */
		}
//		fDecl.fpars.configureFormalParameters(document, cbInsertEmptyLineInBody );
		
		val parenPair = fDecl.regionFor.keywordPairs("(",")").head;
		parenPair.key.prepend[noSpace; newLines = 0].append[noSpace];
		parenPair.interior[indent];
		if( parenPair.isMultiLine ) {
			// it is already a multiline, insert the newLine immediately.
			cbInsertEmptyLineInBody.apply(null,null,null);
		} else {
			// single line parameter block
		}
		parenPair.value.prepend[noSpace]
		
		for (child : fDecl.eContents) {
			child.format;
		}
	}
	
	/** to be used by FunctionDefintiions and SetterDeclarations */
	def void configureFormalParameters(EList<FormalParameter> list, extension IFormattableDocument document, (ITextSegment , IHiddenRegionFormatting , IFormattableDocument )=>void x){
		if( list === null ||  list.isEmpty ) return;
		list.forEach[it,idx|
			if(idx !== 0) it.prepend[oneSpace;setNewLines(0,0,1);onAutowrap=x];
			it.append[noSpace];
		];
	}
	
	/** Check if key and value are in different lines. Defined for non-overlapping Regions, e.g. Keyword-pairs.*/
	def static boolean isMultiLine(Pair<ISemanticRegion, ISemanticRegion> pair) {
		! pair.key.lineRegions.last.contains(  pair.value )
	}
	
//	def dispatch format(FunctionOrFieldAccessor fofAccessor, extension IFormattableDocument document) {
//		val begin = fofAccessor.body.semanticRegions.head
//		val end = fofAccessor.body.semanticRegions.last
//		if (begin?.lineRegions?.head?.contains(end?.endOffset)) {
//			// same line
//		} else {
//			// body spans multiple lines
//			begin.append[newLine;];
//			end.prepend[newLine;];
//			// fofAccessor.body.interior[indent]; // already by parenthesis? 
//		}
//		
//		fofAccessor.body?.format;
//
//	}

	def dispatch void format(N4EnumDeclaration enumDecl, extension IFormattableDocument document) {
		enumDecl.configureAnnotations(document);
		enumDecl.insertSpaceInFrontOfCurlyBlockOpener(document);
		enumDecl.interior[indent];
		
		val braces = enumDecl.regionFor.keywordPairs("{","}").head;
		
		val multiLine = enumDecl.isMultiline;
		
		enumDecl.literals.forEach[
			format;
			if( multiLine ) {
				if( it.regionForEObject.previousHiddenRegion.containsComment )
				{ // comment above
					it.prepend[newLines=2];
				} else { // no comment above
					it.prepend[newLine];
				}
			}
		];
		if( multiLine ) {
			braces.value.prepend[newLine];
		}
	}

	def dispatch void format(ParameterizedPropertyAccessExpression exp, extension IFormattableDocument document) {
		val dotKW = exp.regionFor.keyword(".");
		dotKW.prepend[noSpace; autowrap; setNewLines(0,0,1)].append[noSpace;];
		if( exp.eContainer instanceof ExpressionStatement) {
			// top-level PPA, indent one level.
			exp.interior[
				indent
			];
		}
		exp.target.format;
	}

	def dispatch void format(ParameterizedCallExpression exp, extension IFormattableDocument document) {
		val dotKW = exp.regionFor.keyword(".");
		dotKW.prepend[noSpace; autowrap;].append[noSpace;]
		exp.regionFor.keyword("(").prepend[noSpace].append[noSpace];
		exp.regionFor.keyword(")").prepend[noSpace].append[noSpace];
		
		exp.arguments.tail.forEach[prepend[oneSpace]];
		exp.arguments.forEach[format];
		
		if( exp.eContainer instanceof ExpressionStatement) {
			// top-level PPA, indent one level.
			exp.interior[
				indent
			];
		}
		exp.target.format;
		
		
		exp.target.format;
	}

	def dispatch void format(ImportDeclaration decl, extension IFormattableDocument document) {
		decl.regionFor.keyword("{").prepend[oneSpace].append[noSpace]
		decl.regionFor.keyword("}").prepend[noSpace].append[oneSpace; newLines = 0]
	}

	def dispatch void format(IfStatement stmt, extension IFormattableDocument document) {
		val parenPair = stmt.regionFor.keywordPairs("(",")").head;
		parenPair.interior[noSpace;indent];
		parenPair.key.prepend[oneSpace];
		parenPair.value.append[oneSpace];
		
		stmt.regionFor.keyword("else").prepend[autowrap;oneSpace].append[oneSpace];

		stmt.elseStmt.prepend[oneSpace; newLines = 0];
		
//		stmt.ifStmt.interior[indent];
//		stmt.elseStmt.interior[indent];
		
		stmt.expression.format;
		stmt.ifStmt.format;
		stmt.elseStmt.format;
	}

	def dispatch void format(SwitchStatement swStmt, extension IFormattableDocument document) {
		swStmt.insertSpaceInFrontOfCurlyBlockOpener(document);
		swStmt.interior[indent]; 
		swStmt.expression.format;
		swStmt.cases.head.prepend[newLine];
		swStmt.cases.forEach[format];
	}

	/** Fromats DefaultCaseClause + CaseClause */
	def dispatch void format(AbstractCaseClause caseClause, extension IFormattableDocument document) {
		caseClause.insertNewlineAfterColon(document,getPreference(FORMAT_SWITCH_CASES_HAVE_SPACE_IN_FRONT_OF_COLON));
		caseClause.interior[indent];
		caseClause.statements.last.append[setNewLines(1, 1, 2);]
	}

	def dispatch void format(CastExpression expr, extension IFormattableDocument document) {
		expr.regionFor.keyword("as").prepend[newLines = 0; oneSpace].append[newLines = 0; oneSpace];
		expr.expression.format;
		expr.targetTypeRef.format;
	}

	def dispatch void format(Block block, extension IFormattableDocument document) {
		println("Formatting block "+containmentStructure(block))
		
		// Beware there are blocks in the grammer, that are not surrounded by curly braces. (e.g. FunctionExpression)
		
		// Block not nested in other blocks usually are bodies. We want them separated by a space:
		if (! (block.eContainer instanceof Block)) {
			block.regionFor.keyword("{").prepend[oneSpace];
		}

//		val begin = block.semanticRegions.head; // '{' 
//		val end = block.semanticRegions.last; // '}'
//		if (begin !== null) {
//			// begin.interior(end)[indent];
//			if (begin.lineRegions.head.contains(end)) {
//				// same line
//				if (block.statements.size == 0 // no statement
//					|| block.statements.size > 1 
//					|| !( block.statements.get(0) instanceof Block )
//				) {
//					// insert spaces 
//					begin.append[oneSpace]
//					end.prepend[oneSpace]
//				}
//			} else { // not in same line.
//				if (block.statements.size >	1 
//				 ||	( block.statements.size==1 && !( block.statements.get(0) instanceof Block ) )  ) {
//					begin.append[newLine; autowrap];
//					end.prepend[newLine; autowrap];
//					block.statements.head.prepend[newLine];
//					block.statements.forEach[append[newLine;autowrap]];
//				}
//			}
//		}

		block.interior[indent];
		
		block.statements.head.prepend[setNewLines(1,1,maxEmptyLines)];
		block.statements.forEach[append[setNewLines(1,1,maxEmptyLines)]];
		
		block.statements.forEach[format];

		// Format empty curly blocks, necessary for comments inside:
		val braces = block.regionFor.keywordPairs("{","}").head
		if( braces !== null 
			&& braces.key.nextSemanticRegion == braces.value
		) {
			// empty block:
			braces.key.append[setNewLines(1,1,maxEmptyLines)];
		}
	}
	

	def dispatch void format(ReturnStatement ret, extension IFormattableDocument document) {
		ret.interior[indent;]
		ret.expression.prepend[oneSpace; newLines = 0];
		ret.expression.format;
	}

	def dispatch void format(AdditiveExpression add, extension IFormattableDocument document) {
		add.regionFor.feature(N4JSPackage.Literals.ADDITIVE_EXPRESSION__OP).surround[oneSpace];
		add.lhs.format
		add.rhs.format
	}
	def dispatch void format(MultiplicativeExpression mul, extension IFormattableDocument document) {
		mul.regionFor.feature(N4JSPackage.Literals.MULTIPLICATIVE_EXPRESSION__OP).surround[oneSpace];
		mul.lhs.format
		mul.rhs.format
	}
	def dispatch void format(BinaryBitwiseExpression binbit, extension IFormattableDocument document) {
		binbit.regionFor.feature(N4JSPackage.Literals.BINARY_BITWISE_EXPRESSION__OP).surround[oneSpace];
		binbit.lhs.format
		binbit.rhs.format
	}
	def dispatch void format(BinaryLogicalExpression binLog, extension IFormattableDocument document) {
		val opReg = binLog.regionFor.feature(N4JSPackage.Literals.BINARY_LOGICAL_EXPRESSION__OP);
		opReg.surround[oneSpace];
		binLog.lhs.format
		binLog.rhs.format
		// auto-wrap:
		val autoWrapInFront = getPreference(FORMAT_AUTO_WRAP_IN_FRONT_OF_LOGICAL_OPERATOR);
		if( autoWrapInFront ) {
			opReg.prepend[autowrap; lowPriority; setNewLines(0,0,1);]
		} else {
			opReg.append[autowrap; lowPriority; setNewLines(0,0,1);]
		};
	}
	def dispatch void format(EqualityExpression eqExpr, extension IFormattableDocument document) {
		eqExpr.regionFor.feature(N4JSPackage.Literals.EQUALITY_EXPRESSION__OP).surround[oneSpace];
		eqExpr.lhs.format
		eqExpr.rhs.format
	}
	def dispatch void format(RelationalExpression relExpr, extension IFormattableDocument document) {
		relExpr.regionFor.feature(N4JSPackage.Literals.RELATIONAL_EXPRESSION__OP).surround[oneSpace];
		relExpr.lhs.format
		relExpr.rhs.format
	}
	def dispatch void format(ShiftExpression shiftExpr, extension IFormattableDocument document) {
		shiftExpr.regionFor.feature(N4JSPackage.Literals.SHIFT_EXPRESSION__OP).surround[oneSpace];
		shiftExpr.lhs.format
		shiftExpr.rhs.format
	}
	def dispatch void format(CommaExpression comma, extension IFormattableDocument document) {
		comma.configureCommas(document);
		comma.eContents.forEach[format];
	}
	def dispatch void format(ConditionalExpression cond, extension IFormattableDocument document) {
		cond.regionFor.keyword("?").surround[oneSpace].append[autowrap; lowPriority; setNewLines(0,0,1);];
		cond.regionFor.keyword(":").surround[oneSpace].append[autowrap; lowPriority; setNewLines(0,0,1);];
		cond.expression.format;
		cond.trueExpression.format;
		cond.falseExpression.format;		
	}
	def dispatch void format(AwaitExpression await, extension IFormattableDocument document) {
		await.regionFor.keyword("await").prepend[oneSpace].append[oneSpace; newLines = 0];
		await.format
	}
	def dispatch void format(PromisifyExpression promify, extension IFormattableDocument document) {
		promify.noSpaceAfterAT(document);
		promify.regionFor.keyword("Promisify").append[oneSpace];
		promify.expression.format
	}
	def dispatch void format(IndexedAccessExpression idxAcc, extension IFormattableDocument document) {
		val indexRegion = idxAcc.index.regionForEObject();
		indexRegion.previousSemanticRegion.prepend[noSpace;newLines=0].append[noSpace;newLines = 0];
		indexRegion.nextSemanticRegion.prepend[noSpace];
		
		idxAcc.index.format;
		idxAcc.target.format;
	}
	def dispatch void format(NewExpression newExp, extension IFormattableDocument document) {
		newExp.regionFor.keyword("new").prepend[oneSpace].append[oneSpace;newLines=0];
		newExp.callee.format;
		// val typeArgsAngle = newExp.regionFor.keywordPairs("<",">").head;
		// Watch out, commas are used in Type-args and in argument list ! If necessary distinguish by offset.
		val commas = newExp.regionFor.keyword(",");
		commas.prepend[noSpace].append[oneSpace];
		
		newExp.typeArgs.forEach[format];
		
		if( newExp.isWithArgs ) {
			val argParen = newExp.regionFor.keywordPairs("(",")").head;
			argParen.key.prepend[newLines=0;noSpace].append[noSpace];
			argParen.value.prepend[noSpace];
			newExp.arguments.forEach[format];	
		}
	}
	
	def dispatch void format(PostfixExpression postFix, extension IFormattableDocument document) {
		// no line break allowed between Expression and operator ! 
		postFix.regionFor.feature(N4JSPackage.Literals.POSTFIX_EXPRESSION__OP)
			.prepend[newLines=0;noSpace;].append[oneSpace;];
		postFix.expression.format;
	}
	
	def dispatch void format(TaggedTemplateString taggedTemplate, extension IFormattableDocument document) {
		// TODO TaggedTemplateString
		taggedTemplate.genericTODOformat(document);
	}
	
	def dispatch void format(UnaryExpression unaryExpr, extension IFormattableDocument document) {
		// The operators 'void' 'delete' and 'typeof' must be separetd from operand.
		val boolean requireSpace=(unaryExpr.op.ordinal <= UnaryOperator.TYPEOF_VALUE);
		unaryExpr.regionFor.feature(N4JSPackage.Literals.UNARY_EXPRESSION__OP)
			.append[if(requireSpace) oneSpace else noSpace; newLines = 0;];
		unaryExpr.expression.format;
	}
	
	def dispatch void format(YieldExpression yieldExpr, extension IFormattableDocument document) {
		// " yield " or " yield* "
		yieldExpr.regionFor.keyword("yield")
		.prepend[oneSpace;]
		.append[if( yieldExpr.isMany ) noSpace else oneSpace];
		if( yieldExpr.isMany ){
			yieldExpr.regionFor.keyword("*").prepend[noSpace;newLines=0].append[oneSpace]
		}
		yieldExpr.expression.format;
	}
	
	def dispatch void format(ParenExpression parenE, extension IFormattableDocument document) {
		parenE.interior[indent];
		parenE.expression.format;
	}
	
	def dispatch void format(ArrowFunction arrowF, extension IFormattableDocument document) {
		arrowF.configureCommas(document);
		arrowF.regionFor.keyword("=>").surround[oneSpace];
		arrowF.regionFor.keywordPairs("(",")").head?.interior[noSpace];
		if( arrowF.isHasBracesAroundBody ) {
			// format body as block. NOTE: this block differs from other blocks, since the curly braces are defined in the ArrowExpression.
			arrowF.body.format;
			// special handling of indentation in inside the braces. 
			if( arrowF.hasBracesAroundBody ) {
				val bracesPair = arrowF.regionFor.keywordPairs("{","}").head;
				bracesPair.interior[indent];				
			}
		} else {
			// no braces Around the implicit return statement.
			arrowF.body?.statements.head.format;
		}
	}
	
	
	def dispatch void format(ArrayLiteral al, extension IFormattableDocument document) {
		val bracketPair = al.regionFor.keywordPairs("[","]").head;
		bracketPair.interior[indent];
		val sameLine = bracketPair.key.lineRegions.head.contains( bracketPair.value );
		// auto wrap in front of AL-Elements, to preserve comma at end.
		if( ! sameLine) {
			al.elements.last.append[autowrap];
			al.elements.forEach[it,num|prepend[autowrap;setNewLines(0,0,1);if(num!==0)oneSpace;].append[noSpace]];
			// format last bracket if in single.line.
			if( ! bracketPair.value.previousSemanticRegion.lineRegions.last.contains( bracketPair.value ) ) {
				bracketPair.value.prepend[newLine];
			}	
		} else {
			al.elements.forEach[it,num|prepend[autowrap;if(num!==0)oneSpace;]];
		}
	}

	def dispatch void format(ObjectLiteral ol, extension IFormattableDocument document) {
		val bracePair = ol.regionFor.keywordPairs("{","}").head;
		bracePair.interior[indent];
		val sameLine = bracePair.key.lineRegions.head.contains( bracePair.value );
		if( ! sameLine) {
			bracePair.value.prepend[newLine]; // format WS in front of closing brace
			ol.propertyAssignments.forEach[it,num|prepend[newLine]];
			if( bracePair.key.nextSemanticRegion == bracePair.value ) {
				// empty multiline, trigger formatting:
				bracePair.key.append[newLine];
			}	
		} else { // in one line
			ol.propertyAssignments.forEach[it,num|prepend[autowrap;if(num!==0)oneSpace]];
		}
		
	}
	
	def dispatch void format(Expression exp, extension IFormattableDocument document) {
		switch(exp) {
			// Things not to format:
			BooleanLiteral,
			IdentifierRef,
			IntLiteral,
			NullLiteral,
			NumberLiteral,
			RegularExpressionLiteral,
			StringLiteral,
			ThisLiteral
			: return
		}
		throw new UnsupportedOperationException("expression "+exp.class.simpleName+" not yet implemented.");
	}
	def void genericTODOformat(Expression exp, extension IFormattableDocument document) {
		throw new UnsupportedOperationException("expression "+exp.class.simpleName+" not yet implemented.");
	}

	/** simply formats all content */
	def void genericFormat(Expression exp, extension IFormattableDocument document) {
		exp.eContents.forEach[format];
	}
	
	
	
	def dispatch void format(AssignmentExpression ass, extension IFormattableDocument document) {
		ass.lhs.append[oneSpace]
		ass.rhs.prepend[oneSpace]
		ass.lhs.format;
		ass.rhs.format;
	}
	
	def dispatch void format( ExpressionStatement eStmt, extension IFormattableDocument docuemt){
		println( " fmt "+eStmt.expression.class.simpleName );
		eStmt.expression.format;
	}

	/** var,let,const  */
	def dispatch void format(VariableStatement vStmt, extension IFormattableDocument document) {
		
		vStmt.regionFor.feature(
			N4JSPackage.Literals.VARIABLE_DECLARATION_CONTAINER__VAR_STMT_KEYWORD).append [
			oneSpace;
		]; // "let", "var" or "const"

		vStmt.configureCommas(document);
		
		vStmt.interior[indent];
		val lastIdx = vStmt.varDeclsOrBindings.size - 1;

		vStmt.varDeclsOrBindings.forEach [ e, int i |
			e.format;
			if (i > 0) { // assignments start in separate lines. 
				if (e instanceof VariableDeclaration) {
					if (e.expression !== null) e.prepend[newLine]
					else e.prepend[setNewLines(0,1,1); lowPriority];
				} else if (e instanceof VariableBinding) {
					if (e.expression !== null) e.prepend[newLine]
					else e.prepend[setNewLines(0,1,1); lowPriority];
				}
			}
			if (i < lastIdx) { // assignments start let following continue in separate lines. 
				if (e instanceof VariableDeclaration) {
					if (e.expression !== null) e.immediatelyFollowing.keyword(",").append[newLine]
					else e.prepend[setNewLines(0,1,1); lowPriority];
				} else if (e instanceof VariableBinding) {
					if (e.expression !== null) e.immediatelyFollowing.keyword(",").append[newLine]
					else e.prepend[setNewLines(0,1,1); lowPriority];
				}

			}
		];
	}

	def dispatch void format(VariableDeclaration vDecl, extension IFormattableDocument document) {
		vDecl.previousHiddenRegion.set[oneSpace];
		vDecl.regionFor.keyword("=").surround[oneSpace];
		vDecl.expression.format;
	}

	def dispatch void format(VariableBinding vBind, extension IFormattableDocument document) {
		vBind.previousHiddenRegion.set[oneSpace];
		vBind.regionFor.keyword("=").surround[oneSpace];
		vBind.pattern.format;
		vBind.expression.format;
	}

	/** */
	def dispatch void format(CatchBlock ctch, extension IFormattableDocument document) {
		ctch.prepend[setNewLines(0, 0, 0); oneSpace];
		ctch.catchVariable.format;
		ctch.block.format;
	}

	def dispatch void format(FinallyBlock finlly, extension IFormattableDocument document) {
		finlly.previousHiddenRegion.set[newLines = 0; oneSpace];
		finlly.block.format;
	}

	/** Elements implementing PropertyNameOwner may have symbols or computed names given in brackets. 
	 * Inserts a spcace in front of the opening bracket.*/
	private def void insertSpaceInfrontOfPropertyNames(EObject field, extension IFormattableDocument document) {
		// Space in front of name, esp. for names like "[@sdfljks]"
		val nameRegion = field.regionFor.feature(
			N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME);
		if (nameRegion === null) return;

		val precBracket = nameRegion.immediatelyPreceding.keyword("[");
		precBracket.prepend[oneSpace];
	}

	/** Insert one space in front of first '{' in the direct content of the element. 
	 * semEObject is a semanticObject, e.g. N4EnumDecl, N4Classifier ...*/
	private def void insertSpaceInFrontOfCurlyBlockOpener(EObject semEObject, extension IFormattableDocument document) {
		semEObject.regionFor.keyword("{").prepend[oneSpace];
	}
	
	/** force: " @" and no newLine after '@' */
	private def void noSpaceAfterAT(EObject semEObject, extension IFormattableDocument document) {
		semEObject.regionFor.keyword("@").append[noSpace;newLines=0].prepend[oneSpace];
	}

	/** Insert 'new line'+'no space' after first colon (':') directly contained in the semantic object*/
	private def void insertNewlineAfterColon(EObject semEObject, extension IFormattableDocument document,boolean leadingSpace) {
		semEObject.regionFor.keyword(":").append[newLine; noSpace;].prepend[ if(leadingSpace) oneSpace else noSpace; ];
	}
	
	/** On the direct level of an semantic Object enforce commas to ", " with autoWrap option. */
	private def void configureCommas(EObject semEObject, extension IFormattableDocument document) {
		semEObject.regionFor.keywords(",").forEach [
			prepend[noSpace];
			append[oneSpace; autowrap];
		];
	}

	
	private def dispatch void configureAnnotations(AnnotableN4MemberDeclaration semEObject, extension IFormattableDocument document) {
		configureAnnotations( semEObject.annotationList, document );
	}

	private def dispatch void configureAnnotations(AnnotableScriptElement semEObject, extension IFormattableDocument document) {
		configureAnnotations( semEObject.annotationList, document );
	}
	private def dispatch void configureAnnotations(AnnotableExpression semEObject, extension IFormattableDocument document) {
		configureAnnotations( semEObject.annotationList, document );
	}
	
	private def dispatch void configureAnnotations(AbstractAnnotationList aList, extension IFormattableDocument document) {
		if( aList === null || aList.annotations.isEmpty ) return;
		
		aList.prepend[setNewLines(2,2,2);highPriority];
		aList.append[newLine]; // TODO special annotations like @Internal ? --> together with public, reorder to be in same line?
		aList.annotations.forEach[it,idx|
			// configure arguments 
			val parens = it.regionFor.keywordPairs("(",")").head;
			if( parens !== null ) {
				parens=>[ 
					it.key.prepend[noSpace].append[noSpace];
					it.value.prepend[noSpace].append[noSpace;newLines=1];
					it.interior[indent];
					// line break before "@":
					if( idx !==  0) {
						it.key.previousSemanticRegion.previousSemanticRegion.prepend[newLines = 1];
					}
				];
				it.configureCommas(document);
			}
			// Configure @-Syntax
			it.semanticRegions.head.append[noSpace; newLines=0];			
		];
	}
	
	private def dispatch void configureAnnotations(Object semEObject, extension IFormattableDocument document) {
		// no annotaitons to be configured.
	}

	private def dispatch void configureAnnotations(Void x, extension IFormattableDocument document) {
		// no annotaitons to be configured.
	}

	public override ITextReplacer createCommentReplacer(IComment comment) {
		// Overridden to distinguish between JSDOC-style, standard ML, formatter-off ML-comment.
		val EObject grammarElement = comment.getGrammarElement();
		if (grammarElement instanceof AbstractRule) {
			val String ruleName = (/*(AbstractRule)*/ grammarElement).getName();
			if (ruleName.startsWith("ML")) {
				val cText = comment.text;
				if (cText.startsWith("/**") && !cText.startsWith("/***")) { // JSDOC
					return new N4MultilineCommentReplacer(comment, '*');
				} else if (cText.startsWith("/*-")) { // Turn-off formatting. 
					return new OffMultilineCommentReplacer(comment, !comment.isNotFirstInLine);
				} else { // All other
					return new FixedMultilineCommentReplacer(comment);
				}	
			}
			if (ruleName.startsWith("SL")) {
				if (comment.isNotFirstInLine) {
					return new SinglelineDocCommentReplacer(comment, "//");
				} else {
					return new SinglelineCodeCommentReplacer(comment, "//");
				}
			}
		}

		// fall back to super-impl.
		super.createCommentReplacer(comment);
	}

	private static def boolean isNotFirstInLine(IComment comment) {
		val lineRegion = comment.getLineRegions().get(0);
		
		return !comment.contains( lineRegion.offset );
	}


	public override createTextReplacerMerger(){
		return new IndentHandlingTextReplaceMerger(this);
	}
	
	
	/** DEBUG-helper */
	private def static String containmentStructure(EObject eo) {
		val name = eo.class.simpleName;
		if( eo.eContainer !== null )
		return '''«eo.eContainer.containmentStructure».«eo.eContainingFeature.name»-> «name»'''
		return name
	}
	
	
	private final static class StateTrack {
		private boolean done=false;
		/** returns true if not done, switches done to true; returns false if already done. */
		def boolean doThenDone(){
			val ret = !done;
			done = true;
			return ret;
		} 	
	}
	
}
