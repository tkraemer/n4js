package eu.numberfour.n4js.xsemantics;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.numberfour.n4js.AnnotationDefinition;
import eu.numberfour.n4js.misc.DestructureHelper;
import eu.numberfour.n4js.n4JS.AdditiveExpression;
import eu.numberfour.n4js.n4JS.AdditiveOperator;
import eu.numberfour.n4js.n4JS.Argument;
import eu.numberfour.n4js.n4JS.ArrayElement;
import eu.numberfour.n4js.n4JS.ArrayLiteral;
import eu.numberfour.n4js.n4JS.ArrayPadding;
import eu.numberfour.n4js.n4JS.ArrowFunction;
import eu.numberfour.n4js.n4JS.AssignmentExpression;
import eu.numberfour.n4js.n4JS.AssignmentOperator;
import eu.numberfour.n4js.n4JS.AwaitExpression;
import eu.numberfour.n4js.n4JS.BinaryBitwiseExpression;
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression;
import eu.numberfour.n4js.n4JS.BindingElement;
import eu.numberfour.n4js.n4JS.BooleanLiteral;
import eu.numberfour.n4js.n4JS.CastExpression;
import eu.numberfour.n4js.n4JS.CatchVariable;
import eu.numberfour.n4js.n4JS.CommaExpression;
import eu.numberfour.n4js.n4JS.ConditionalExpression;
import eu.numberfour.n4js.n4JS.EqualityExpression;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.ForStatement;
import eu.numberfour.n4js.n4JS.FormalParameter;
import eu.numberfour.n4js.n4JS.FunctionDefinition;
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor;
import eu.numberfour.n4js.n4JS.GetterDeclaration;
import eu.numberfour.n4js.n4JS.IdentifierRef;
import eu.numberfour.n4js.n4JS.IndexedAccessExpression;
import eu.numberfour.n4js.n4JS.IntLiteral;
import eu.numberfour.n4js.n4JS.LocalArgumentsVariable;
import eu.numberfour.n4js.n4JS.MultiplicativeExpression;
import eu.numberfour.n4js.n4JS.N4ClassDeclaration;
import eu.numberfour.n4js.n4JS.N4ClassifierDefinition;
import eu.numberfour.n4js.n4JS.N4EnumLiteral;
import eu.numberfour.n4js.n4JS.N4FieldDeclaration;
import eu.numberfour.n4js.n4JS.N4GetterDeclaration;
import eu.numberfour.n4js.n4JS.N4JSASTUtils;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.N4MemberDeclaration;
import eu.numberfour.n4js.n4JS.N4MethodDeclaration;
import eu.numberfour.n4js.n4JS.N4SetterDeclaration;
import eu.numberfour.n4js.n4JS.NewExpression;
import eu.numberfour.n4js.n4JS.NewTarget;
import eu.numberfour.n4js.n4JS.NullLiteral;
import eu.numberfour.n4js.n4JS.NumericLiteral;
import eu.numberfour.n4js.n4JS.ObjectLiteral;
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.n4JS.ParenExpression;
import eu.numberfour.n4js.n4JS.PostfixExpression;
import eu.numberfour.n4js.n4JS.PromisifyExpression;
import eu.numberfour.n4js.n4JS.PropertyNameValuePair;
import eu.numberfour.n4js.n4JS.RegularExpressionLiteral;
import eu.numberfour.n4js.n4JS.RelationalExpression;
import eu.numberfour.n4js.n4JS.RelationalOperator;
import eu.numberfour.n4js.n4JS.ReturnStatement;
import eu.numberfour.n4js.n4JS.SetterDeclaration;
import eu.numberfour.n4js.n4JS.ShiftExpression;
import eu.numberfour.n4js.n4JS.StringLiteral;
import eu.numberfour.n4js.n4JS.SuperLiteral;
import eu.numberfour.n4js.n4JS.TaggedTemplateString;
import eu.numberfour.n4js.n4JS.TemplateLiteral;
import eu.numberfour.n4js.n4JS.TemplateSegment;
import eu.numberfour.n4js.n4JS.ThisLiteral;
import eu.numberfour.n4js.n4JS.ThisTarget;
import eu.numberfour.n4js.n4JS.TypeDefiningElement;
import eu.numberfour.n4js.n4JS.UnaryExpression;
import eu.numberfour.n4js.n4JS.UnaryOperator;
import eu.numberfour.n4js.n4JS.VariableBinding;
import eu.numberfour.n4js.n4JS.VariableDeclaration;
import eu.numberfour.n4js.n4JS.YieldExpression;
import eu.numberfour.n4js.scoping.members.MemberScopingHelper;
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;
import eu.numberfour.n4js.ts.typeRefs.EnumTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef;
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.StaticBaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.StructuralTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;
import eu.numberfour.n4js.ts.types.AnyType;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.ts.types.ModuleNamespaceVirtualType;
import eu.numberfour.n4js.ts.types.NullType;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TEnum;
import eu.numberfour.n4js.ts.types.TEnumLiteral;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TInterface;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TObjectPrototype;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.types.TStructuralType;
import eu.numberfour.n4js.ts.types.TVariable;
import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypingStrategy;
import eu.numberfour.n4js.ts.types.UndefinedType;
import eu.numberfour.n4js.ts.types.VoidType;
import eu.numberfour.n4js.ts.types.util.AllSuperTypeRefsCollector;
import eu.numberfour.n4js.ts.types.util.Variance;
import eu.numberfour.n4js.ts.utils.TypeExtensions;
import eu.numberfour.n4js.ts.utils.TypeHelper;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesbuilder.N4JSFunctionDefinitionTypesBuilder;
import eu.numberfour.n4js.typesystem.PredefinedTypes;
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.typesystem.StructuralTypingResult;
import eu.numberfour.n4js.typesystem.TypeSystemErrorExtensions;
import eu.numberfour.n4js.typesystem.TypeSystemHelper;
import eu.numberfour.n4js.utils.ContainerTypesHelper;
import eu.numberfour.n4js.utils.N4JSLanguageUtils;
import eu.numberfour.n4js.utils.PromisifyHelper;
import eu.numberfour.n4js.validation.JavaScriptVariant;
import it.xsemantics.runtime.ErrorInformation;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleApplicationTrace;
import it.xsemantics.runtime.RuleEnvironment;
import it.xsemantics.runtime.RuleFailedException;
import it.xsemantics.runtime.XsemanticsRuntimeSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * N4JS Type System.
 * 
 * <h3>Naming conventions</h3>
 * 
 * Rules and axioms are named using the following pattern:
 * <pre>
 * &lt;relationName>&lt;InputType(s)>&lt;Additional_description>
 * </pre>
 * If a rule has multiple input parameters, either only the first one is used (in case
 * the second is not that important), or all types are used (without any separator).
 * <p>
 * The rules/axioms are ordered by judgment, and then according to the ("major" or first) input type,
 * if possible the order is similar as the order used in the N4JS specification.
 * 
 * <h3>Changes to the Rule Environment</h3>
 * 
 * By convention, rules should <em>not</em> change the rule environment and can (and should) rely
 * on other rules not changing the rule environment in case of nested inference. Thus:
 * <ul>
 * <li>before modifying the rule environment a new, derived rule environment must be created,
 *     usually by using extension method #wrap(). For example:
 *     <pre>
 *     val G2 = G.wrap;
 *     G2.add("MY_KEY",someValue);
 *     G2.addSubstitutions(someTypeRef);
 *     </pre>
 * <li>when doing nested inference within a rule, no new rule environment should be created (because
 *     we assume it won't be changed by the nested rule calls)
 * </ul>
 * There are two ways of creating a new rule environment: create empty environment or wrap existing
 * rule environment. When passing the newly created environment on to a nested inference, it is important
 * to always derive, because otherwise recursion guards will be lost.
 * 
 * <h3>Bibliography</h3>
 * <dl>
 * <dt><a name="N4JS">[N4JS]</a></dt>
 * <dd>Pilgrim, Jens von et al.: N4JS Specification / NumberFour AG. Berlin, September 2013.
 * 			Specification. <a href="https://github.com/NumberFour/specs/">https://github.com/NumberFour/specs/</a></dd>
 * </dl>
 */
@SuppressWarnings("all")
public class N4JSTypeSystem extends XsemanticsRuntimeSystem {
  public final static String TYPETYPE = "eu.numberfour.n4js.xsemantics.TypeType";
  
  public final static String TYPETENUMLITERAL = "eu.numberfour.n4js.xsemantics.TypeTEnumLiteral";
  
  public final static String TYPETFIELD = "eu.numberfour.n4js.xsemantics.TypeTField";
  
  public final static String TYPETGETTER = "eu.numberfour.n4js.xsemantics.TypeTGetter";
  
  public final static String TYPETSETTER = "eu.numberfour.n4js.xsemantics.TypeTSetter";
  
  public final static String TYPETVARIABLE = "eu.numberfour.n4js.xsemantics.TypeTVariable";
  
  public final static String TYPETYPEDEFININGELEMENT = "eu.numberfour.n4js.xsemantics.TypeTypeDefiningElement";
  
  public final static String TYPEOBJECTLITERAL = "eu.numberfour.n4js.xsemantics.TypeObjectLiteral";
  
  public final static String TYPETHISKEYWORD = "eu.numberfour.n4js.xsemantics.TypeThisKeyword";
  
  public final static String TYPESUPERLITERAL = "eu.numberfour.n4js.xsemantics.TypeSuperLiteral";
  
  public final static String TYPEIDENTIFIERREF = "eu.numberfour.n4js.xsemantics.TypeIdentifierRef";
  
  public final static String TYPENULLLITERAL = "eu.numberfour.n4js.xsemantics.TypeNullLiteral";
  
  public final static String TYPEBOOLEANLITERAL = "eu.numberfour.n4js.xsemantics.TypeBooleanLiteral";
  
  public final static String TYPENUMERICLITERAL = "eu.numberfour.n4js.xsemantics.TypeNumericLiteral";
  
  public final static String TYPESTRINGLITERAL = "eu.numberfour.n4js.xsemantics.TypeStringLiteral";
  
  public final static String TYPEREGEXPLITERAL = "eu.numberfour.n4js.xsemantics.TypeRegExpLiteral";
  
  public final static String TYPETAGGEDTEMPLATESTRING = "eu.numberfour.n4js.xsemantics.TypeTaggedTemplateString";
  
  public final static String TYPETEMPLATELITERAL = "eu.numberfour.n4js.xsemantics.TypeTemplateLiteral";
  
  public final static String TYPETEMPLATESEGMENT = "eu.numberfour.n4js.xsemantics.TypeTemplateSegment";
  
  public final static String TYPEN4ENUMLITERAL = "eu.numberfour.n4js.xsemantics.TypeN4EnumLiteral";
  
  public final static String TYPEARRAYLITERAL = "eu.numberfour.n4js.xsemantics.TypeArrayLiteral";
  
  public final static String TYPEARRAYPADDING = "eu.numberfour.n4js.xsemantics.TypeArrayPadding";
  
  public final static String TYPEARRAYELEMENT = "eu.numberfour.n4js.xsemantics.TypeArrayElement";
  
  public final static String TYPEPROPERTYNAMEVALUEPAIR = "eu.numberfour.n4js.xsemantics.TypePropertyNameValuePair";
  
  public final static String TYPEN4FIELDDECLARATION = "eu.numberfour.n4js.xsemantics.TypeN4FieldDeclaration";
  
  public final static String TYPEGETTERDECLARATION = "eu.numberfour.n4js.xsemantics.TypeGetterDeclaration";
  
  public final static String TYPESETTERDECLARATION = "eu.numberfour.n4js.xsemantics.TypeSetterDeclaration";
  
  public final static String TYPEPARENEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeParenExpression";
  
  public final static String TYPEYIELDEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeYieldExpression";
  
  public final static String TYPEAWAITEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeAwaitExpression";
  
  public final static String TYPEPROMISIFYEXPRESSION = "eu.numberfour.n4js.xsemantics.TypePromisifyExpression";
  
  public final static String TYPEINDEXEDACCESSEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeIndexedAccessExpression";
  
  public final static String TYPEPROPERTYACCESSEXPRESSION = "eu.numberfour.n4js.xsemantics.TypePropertyAccessExpression";
  
  public final static String TYPENEWEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeNewExpression";
  
  public final static String TYPENEWTARGET = "eu.numberfour.n4js.xsemantics.TypeNewTarget";
  
  public final static String TYPECALLEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeCallExpression";
  
  public final static String TYPEARGUMENT = "eu.numberfour.n4js.xsemantics.TypeArgument";
  
  public final static String TYPEFUNCTIONDEFINITION = "eu.numberfour.n4js.xsemantics.TypeFunctionDefinition";
  
  public final static String TYPEPOSTFIXEXPRESSION = "eu.numberfour.n4js.xsemantics.TypePostfixExpression";
  
  public final static String TYPEUNARYEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeUnaryExpression";
  
  public final static String TYPEMULTIPLICATIVEEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeMultiplicativeExpression";
  
  public final static String TYPEADDITIVEEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeAdditiveExpression";
  
  public final static String TYPESHIFTEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeShiftExpression";
  
  public final static String TYPERELATIONALEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeRelationalExpression";
  
  public final static String TYPEEQUALITYEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeEqualityExpression";
  
  public final static String TYPEBINARYBITWISEEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeBinaryBitwiseExpression";
  
  public final static String TYPEBINARYLOGICALEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeBinaryLogicalExpression";
  
  public final static String TYPECONDITIONALEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeConditionalExpression";
  
  public final static String TYPEASSIGNMENTEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeAssignmentExpression";
  
  public final static String TYPECOMMAEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeCommaExpression";
  
  public final static String TYPECASTEXPRESSION = "eu.numberfour.n4js.xsemantics.TypeCastExpression";
  
  public final static String TYPEVARIABLEDECLARATION = "eu.numberfour.n4js.xsemantics.TypeVariableDeclaration";
  
  public final static String TYPEFORMALPARAMETER = "eu.numberfour.n4js.xsemantics.TypeFormalParameter";
  
  public final static String TYPECATCHVARIABLE = "eu.numberfour.n4js.xsemantics.TypeCatchVariable";
  
  public final static String TYPELOCALARGUMENTSVARIABLE = "eu.numberfour.n4js.xsemantics.TypeLocalArgumentsVariable";
  
  public final static String TYPEMODULENAMESPACE = "eu.numberfour.n4js.xsemantics.TypeModuleNamespace";
  
  public final static String SUBTYPETYPEARGUMENT = "eu.numberfour.n4js.xsemantics.SubtypeTypeArgument";
  
  public final static String SUBTYPETYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeTypeRef";
  
  public final static String SUBTYPEUNKNOWNTYPEREF_LEFT = "eu.numberfour.n4js.xsemantics.SubtypeUnknownTypeRef_Left";
  
  public final static String SUBTYPEUNKNOWNTYPEREF_RIGHT = "eu.numberfour.n4js.xsemantics.SubtypeUnknownTypeRef_Right";
  
  public final static String SUBTYPEENUMTYPEREFN4ENUM = "eu.numberfour.n4js.xsemantics.SubtypeEnumTypeRefN4Enum";
  
  public final static String SUBTYPEENUMTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeEnumTypeRef";
  
  public final static String SUBTYPEPARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeParameterizedTypeRef";
  
  public final static String SUBTYPEUNION_LEFT = "eu.numberfour.n4js.xsemantics.SubtypeUnion_Left";
  
  public final static String SUBTYPEUNION_RIGHT = "eu.numberfour.n4js.xsemantics.SubtypeUnion_Right";
  
  public final static String SUBTYPEINTERSECTION_LEFTRIGHT = "eu.numberfour.n4js.xsemantics.SubtypeIntersection_LeftRight";
  
  public final static String SUBTYPEINTERSECTION_LEFT = "eu.numberfour.n4js.xsemantics.SubtypeIntersection_Left";
  
  public final static String SUBTYPEINTERSECTION_RIGHT = "eu.numberfour.n4js.xsemantics.SubtypeIntersection_Right";
  
  public final static String SUBTYPEBOUNDTHISTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeBoundThisTypeRef";
  
  public final static String SUBTYPEBOUNDTHISTYPEREFTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeBoundThisTypeRefTypeRef";
  
  public final static String SUBTYPETYPEREFBOUNDTHISTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeTypeRefBoundThisTypeRef";
  
  public final static String SUBTYPEEXISTENTIALTYPEREF_RIGHT = "eu.numberfour.n4js.xsemantics.SubtypeExistentialTypeRef_Right";
  
  public final static String SUBTYPEEXISTENTIALTYPEREF_LEFT = "eu.numberfour.n4js.xsemantics.SubtypeExistentialTypeRef_Left";
  
  public final static String SUBTYPECONSTRUCTORTYPEREF__FUNCTION = "eu.numberfour.n4js.xsemantics.SubtypeConstructorTypeRef__Function";
  
  public final static String SUBTYPECONSTRUCTORTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeConstructorTypeRef";
  
  public final static String SUBTYPECLASSIFIERTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeClassifierTypeRef";
  
  public final static String SUBTYPECLASSIFIERTYPEREFOBJECT = "eu.numberfour.n4js.xsemantics.SubtypeClassifierTypeRefObject";
  
  public final static String SUBTYPEFUNCTIONTYPEEXPRORREF = "eu.numberfour.n4js.xsemantics.SubtypeFunctionTypeExprOrRef";
  
  public final static String SUBTYPEFUNCTIONTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeFunctionTypeRef";
  
  public final static String SUBTYPEFUNCTIONTYPEEXPRESSION_PARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.SubtypeFunctionTypeExpression_ParameterizedTypeRef";
  
  public final static String SUPERTYPETYPEREF = "eu.numberfour.n4js.xsemantics.SupertypeTypeRef";
  
  public final static String EQUALTYPETYPEREF = "eu.numberfour.n4js.xsemantics.EqualTypeTypeRef";
  
  public final static String EXPECTEDTYPENONE = "eu.numberfour.n4js.xsemantics.ExpectedTypeNone";
  
  public final static String EXPECTEDTYPEOFARGUMENT = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfArgument";
  
  public final static String EXPECTEDTYPEINPOSTFIXEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInPostfixExpression";
  
  public final static String EXPECTEDTYPEINUNARYEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInUnaryExpression";
  
  public final static String EXPECTEDTYPEINMULTIPLICATIVEEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInMultiplicativeExpression";
  
  public final static String EXPECTEDTYPEINADDITIVEEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInAdditiveExpression";
  
  public final static String EXPECTEDTYPEINSHIFTEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInShiftExpression";
  
  public final static String EXPECTEDTYPEINRELATIONALEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInRelationalExpression";
  
  public final static String EXPECTEDTYPEINEQUALITYEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInEqualityExpression";
  
  public final static String EXPECTEDTYPEINBINARYBITWISEEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInBinaryBitwiseExpression";
  
  public final static String EXPECTEDTYPEINBINARYLOGICALEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInBinaryLogicalExpression";
  
  public final static String EXPECTEDTYPEOFOPERANDINASSIGNMENTEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfOperandInAssignmentExpression";
  
  public final static String EXPECTEDTYPEOFRIGHTSIDEINVARIABLEDECLARATION = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfRightSideInVariableDeclaration";
  
  public final static String EXPECTEDTYPEOFRIGHTSIDEINVARIABLEBINDING = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfRightSideInVariableBinding";
  
  public final static String EXPECTEDTYPEOFRIGHTSIDEINN4FIELDDECLARATION = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfRightSideInN4FieldDeclaration";
  
  public final static String EXPECTEDTYPEOFRIGHTSIDEINPROPERTYNAMEVALUEPAIR = "eu.numberfour.n4js.xsemantics.ExpectedTypeOfRightSideInPropertyNameValuePair";
  
  public final static String EXPECTEDTYPEINRETURNSTATEMENT = "eu.numberfour.n4js.xsemantics.ExpectedTypeInReturnStatement";
  
  public final static String EXPECTEDTYPEINFORSTATEMENT = "eu.numberfour.n4js.xsemantics.ExpectedTypeInForStatement";
  
  public final static String EXPECTEDTYPEINAWAITEXPRESSION = "eu.numberfour.n4js.xsemantics.ExpectedTypeInAwaitExpression";
  
  public final static String UPPERBOUNDTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundTypeRef";
  
  public final static String UPPERBOUNDWILDCARDTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundWildcardTypeRef";
  
  public final static String UPPERBOUNDEXISTENTIALTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundExistentialTypeRef";
  
  public final static String UPPERBOUNDUNIONTYPEEXPRESSION = "eu.numberfour.n4js.xsemantics.UpperBoundUnionTypeExpression";
  
  public final static String UPPERBOUNDINTERSECTIONTYPEEXPRESSION = "eu.numberfour.n4js.xsemantics.UpperBoundIntersectionTypeExpression";
  
  public final static String UPPERBOUNDPARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundParameterizedTypeRef";
  
  public final static String UPPERBOUNDFUNCTIONTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundFunctionTypeRef";
  
  public final static String UPPERBOUNDFUNCTIONTYPEEXPRORREF = "eu.numberfour.n4js.xsemantics.UpperBoundFunctionTypeExprOrRef";
  
  public final static String UPPERBOUNDTHISTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundThisTypeRef";
  
  public final static String UPPERBOUNDCLASSIFIERTYPEREF = "eu.numberfour.n4js.xsemantics.UpperBoundClassifierTypeRef";
  
  public final static String LOWERBOUNDTYPEREF = "eu.numberfour.n4js.xsemantics.LowerBoundTypeRef";
  
  public final static String LOWERBOUNDWILDCARD = "eu.numberfour.n4js.xsemantics.LowerBoundWildcard";
  
  public final static String LOWERBOUNDEXISTENTIALTYPEREF = "eu.numberfour.n4js.xsemantics.LowerBoundExistentialTypeRef";
  
  public final static String LOWERBOUNDUNIONTYPEEXPRESSION = "eu.numberfour.n4js.xsemantics.LowerBoundUnionTypeExpression";
  
  public final static String LOWERBOUNDINTERSECTIONTYPEEXPRESSION = "eu.numberfour.n4js.xsemantics.LowerBoundIntersectionTypeExpression";
  
  public final static String LOWERBOUNDPARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.LowerBoundParameterizedTypeRef";
  
  public final static String LOWERBOUNDFUNCTIONTYPEREF = "eu.numberfour.n4js.xsemantics.LowerBoundFunctionTypeRef";
  
  public final static String LOWERBOUNDFUNCTIONTYPEEXPRORREF = "eu.numberfour.n4js.xsemantics.LowerBoundFunctionTypeExprOrRef";
  
  public final static String LOWERBOUNDTHISTYPEREF = "eu.numberfour.n4js.xsemantics.LowerBoundThisTypeRef";
  
  public final static String SUBSTTYPEVARIABLESBASECASE = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesBaseCase";
  
  public final static String SUBSTTYPEVARIABLESWILDCARD = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesWildcard";
  
  public final static String SUBSTTYPEVARIABLESTHISTYPEREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesThisTypeRef";
  
  public final static String SUBSTTYPEVARIABLESTHISTYPEREFSTRUCTURAL = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesThisTypeRefStructural";
  
  public final static String SUBSTTYPEVARIABLESINFUNCTIONTYPEREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesInFunctionTypeRef";
  
  public final static String SUBSTTYPEVARIABLESINFUNCTIONTYPEEXPRORREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesInFunctionTypeExprOrRef";
  
  public final static String SUBSTTYPEVARIABLESINCOMPOSEDTYPEREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesInComposedTypeRef";
  
  public final static String SUBSTTYPEVARIABLESINCLASSIFIERTYPEREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesInClassifierTypeRef";
  
  public final static String SUBSTTYPEVARIABLESINPARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.SubstTypeVariablesInParameterizedTypeRef";
  
  public final static String THISTYPEREFPARAMETERIZEDTYPEREF = "eu.numberfour.n4js.xsemantics.ThisTypeRefParameterizedTypeRef";
  
  public final static String THISTYPEREFEOBJECT = "eu.numberfour.n4js.xsemantics.ThisTypeRefEObject";
  
  @Inject
  private TypeSystemHelper typeSystemHelper;
  
  @Inject
  private N4JSFunctionDefinitionTypesBuilder functionDefinitionTypesBuilder;
  
  @Inject
  private TypeHelper typeHelper;
  
  @Inject
  private ContainerTypesHelper containerTypesHelper;
  
  @Inject
  private DestructureHelper destructureHelper;
  
  @Inject
  private MemberScopingHelper memberScopingHelper;
  
  @Inject
  private PromisifyHelper promisifyHelper;
  
  private PolymorphicDispatcher<Result<TypeRef>> typeDispatcher;
  
  private PolymorphicDispatcher<Result<Boolean>> subtypeDispatcher;
  
  private PolymorphicDispatcher<Result<Boolean>> supertypeDispatcher;
  
  private PolymorphicDispatcher<Result<Boolean>> equaltypeDispatcher;
  
  private PolymorphicDispatcher<Result<TypeRef>> expectedTypeInDispatcher;
  
  private PolymorphicDispatcher<Result<TypeRef>> upperBoundDispatcher;
  
  private PolymorphicDispatcher<Result<TypeRef>> lowerBoundDispatcher;
  
  private PolymorphicDispatcher<Result<TypeArgument>> substTypeVariablesDispatcher;
  
  private PolymorphicDispatcher<Result<TypeRef>> thisTypeRefDispatcher;
  
  public N4JSTypeSystem() {
    init();
  }
  
  public void init() {
    typeDispatcher = buildPolymorphicDispatcher1(
    	"typeImpl", 3, "|-", ":");
    subtypeDispatcher = buildPolymorphicDispatcher1(
    	"subtypeImpl", 4, "|-", "<:");
    supertypeDispatcher = buildPolymorphicDispatcher1(
    	"supertypeImpl", 4, "|-", ":>");
    equaltypeDispatcher = buildPolymorphicDispatcher1(
    	"equaltypeImpl", 4, "|-", "~~");
    expectedTypeInDispatcher = buildPolymorphicDispatcher1(
    	"expectedTypeInImpl", 4, "|-", "|>", ":");
    upperBoundDispatcher = buildPolymorphicDispatcher1(
    	"upperBoundImpl", 3, "|~", "/\\");
    lowerBoundDispatcher = buildPolymorphicDispatcher1(
    	"lowerBoundImpl", 3, "|~", "\\/");
    substTypeVariablesDispatcher = buildPolymorphicDispatcher1(
    	"substTypeVariablesImpl", 3, "|-", "~>");
    thisTypeRefDispatcher = buildPolymorphicDispatcher1(
    	"thisTypeRefImpl", 3, "|~", "~>");
  }
  
  public TypeSystemHelper getTypeSystemHelper() {
    return this.typeSystemHelper;
  }
  
  public void setTypeSystemHelper(final TypeSystemHelper typeSystemHelper) {
    this.typeSystemHelper = typeSystemHelper;
  }
  
  public N4JSFunctionDefinitionTypesBuilder getFunctionDefinitionTypesBuilder() {
    return this.functionDefinitionTypesBuilder;
  }
  
  public void setFunctionDefinitionTypesBuilder(final N4JSFunctionDefinitionTypesBuilder functionDefinitionTypesBuilder) {
    this.functionDefinitionTypesBuilder = functionDefinitionTypesBuilder;
  }
  
  public TypeHelper getTypeHelper() {
    return this.typeHelper;
  }
  
  public void setTypeHelper(final TypeHelper typeHelper) {
    this.typeHelper = typeHelper;
  }
  
  public ContainerTypesHelper getContainerTypesHelper() {
    return this.containerTypesHelper;
  }
  
  public void setContainerTypesHelper(final ContainerTypesHelper containerTypesHelper) {
    this.containerTypesHelper = containerTypesHelper;
  }
  
  public DestructureHelper getDestructureHelper() {
    return this.destructureHelper;
  }
  
  public void setDestructureHelper(final DestructureHelper destructureHelper) {
    this.destructureHelper = destructureHelper;
  }
  
  public MemberScopingHelper getMemberScopingHelper() {
    return this.memberScopingHelper;
  }
  
  public void setMemberScopingHelper(final MemberScopingHelper memberScopingHelper) {
    this.memberScopingHelper = memberScopingHelper;
  }
  
  public PromisifyHelper getPromisifyHelper() {
    return this.promisifyHelper;
  }
  
  public void setPromisifyHelper(final PromisifyHelper promisifyHelper) {
    this.promisifyHelper = promisifyHelper;
  }
  
  public Result<TypeRef> type(final TypableElement element) {
    return type(new RuleEnvironment(), null, element);
  }
  
  public Result<TypeRef> type(final RuleEnvironment _environment_, final TypableElement element) {
    return type(_environment_, null, element);
  }
  
  public Result<TypeRef> type(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypableElement element) {
    try {
    	return typeInternal(_environment_, _trace_, element);
    } catch (Exception _e_type) {
    	return resultForFailure(_e_type);
    }
  }
  
  public Result<Boolean> subtype(final TypeArgument left, final TypeArgument right) {
    return subtype(new RuleEnvironment(), null, left, right);
  }
  
  public Result<Boolean> subtype(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return subtype(_environment_, null, left, right);
  }
  
  public Result<Boolean> subtype(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	return subtypeInternal(_environment_, _trace_, left, right);
    } catch (Exception _e_subtype) {
    	return resultForFailure(_e_subtype);
    }
  }
  
  public Boolean subtypeSucceeded(final TypeArgument left, final TypeArgument right) {
    return subtypeSucceeded(new RuleEnvironment(), null, left, right);
  }
  
  public Boolean subtypeSucceeded(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return subtypeSucceeded(_environment_, null, left, right);
  }
  
  public Boolean subtypeSucceeded(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	subtypeInternal(_environment_, _trace_, left, right);
    	return true;
    } catch (Exception _e_subtype) {
    	return false;
    }
  }
  
  public Result<Boolean> supertype(final TypeArgument left, final TypeArgument right) {
    return supertype(new RuleEnvironment(), null, left, right);
  }
  
  public Result<Boolean> supertype(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return supertype(_environment_, null, left, right);
  }
  
  public Result<Boolean> supertype(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	return supertypeInternal(_environment_, _trace_, left, right);
    } catch (Exception _e_supertype) {
    	return resultForFailure(_e_supertype);
    }
  }
  
  public Boolean supertypeSucceeded(final TypeArgument left, final TypeArgument right) {
    return supertypeSucceeded(new RuleEnvironment(), null, left, right);
  }
  
  public Boolean supertypeSucceeded(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return supertypeSucceeded(_environment_, null, left, right);
  }
  
  public Boolean supertypeSucceeded(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	supertypeInternal(_environment_, _trace_, left, right);
    	return true;
    } catch (Exception _e_supertype) {
    	return false;
    }
  }
  
  public Result<Boolean> equaltype(final TypeArgument left, final TypeArgument right) {
    return equaltype(new RuleEnvironment(), null, left, right);
  }
  
  public Result<Boolean> equaltype(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return equaltype(_environment_, null, left, right);
  }
  
  public Result<Boolean> equaltype(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	return equaltypeInternal(_environment_, _trace_, left, right);
    } catch (Exception _e_equaltype) {
    	return resultForFailure(_e_equaltype);
    }
  }
  
  public Boolean equaltypeSucceeded(final TypeArgument left, final TypeArgument right) {
    return equaltypeSucceeded(new RuleEnvironment(), null, left, right);
  }
  
  public Boolean equaltypeSucceeded(final RuleEnvironment _environment_, final TypeArgument left, final TypeArgument right) {
    return equaltypeSucceeded(_environment_, null, left, right);
  }
  
  public Boolean equaltypeSucceeded(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	equaltypeInternal(_environment_, _trace_, left, right);
    	return true;
    } catch (Exception _e_equaltype) {
    	return false;
    }
  }
  
  public Result<TypeRef> expectedTypeIn(final EObject container, final Expression expression) {
    return expectedTypeIn(new RuleEnvironment(), null, container, expression);
  }
  
  public Result<TypeRef> expectedTypeIn(final RuleEnvironment _environment_, final EObject container, final Expression expression) {
    return expectedTypeIn(_environment_, null, container, expression);
  }
  
  public Result<TypeRef> expectedTypeIn(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EObject container, final Expression expression) {
    try {
    	return expectedTypeInInternal(_environment_, _trace_, container, expression);
    } catch (Exception _e_expectedTypeIn) {
    	return resultForFailure(_e_expectedTypeIn);
    }
  }
  
  public Result<TypeRef> upperBound(final TypeArgument typeArgument) {
    return upperBound(new RuleEnvironment(), null, typeArgument);
  }
  
  public Result<TypeRef> upperBound(final RuleEnvironment _environment_, final TypeArgument typeArgument) {
    return upperBound(_environment_, null, typeArgument);
  }
  
  public Result<TypeRef> upperBound(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArgument) {
    try {
    	return upperBoundInternal(_environment_, _trace_, typeArgument);
    } catch (Exception _e_upperBound) {
    	return resultForFailure(_e_upperBound);
    }
  }
  
  public Result<TypeRef> lowerBound(final TypeArgument typeArgument) {
    return lowerBound(new RuleEnvironment(), null, typeArgument);
  }
  
  public Result<TypeRef> lowerBound(final RuleEnvironment _environment_, final TypeArgument typeArgument) {
    return lowerBound(_environment_, null, typeArgument);
  }
  
  public Result<TypeRef> lowerBound(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArgument) {
    try {
    	return lowerBoundInternal(_environment_, _trace_, typeArgument);
    } catch (Exception _e_lowerBound) {
    	return resultForFailure(_e_lowerBound);
    }
  }
  
  public Result<TypeArgument> substTypeVariables(final TypeArgument typeArg) {
    return substTypeVariables(new RuleEnvironment(), null, typeArg);
  }
  
  public Result<TypeArgument> substTypeVariables(final RuleEnvironment _environment_, final TypeArgument typeArg) {
    return substTypeVariables(_environment_, null, typeArg);
  }
  
  public Result<TypeArgument> substTypeVariables(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArg) {
    try {
    	return substTypeVariablesInternal(_environment_, _trace_, typeArg);
    } catch (Exception _e_substTypeVariables) {
    	return resultForFailure(_e_substTypeVariables);
    }
  }
  
  public Result<TypeRef> thisTypeRef(final EObject location) {
    return thisTypeRef(new RuleEnvironment(), null, location);
  }
  
  public Result<TypeRef> thisTypeRef(final RuleEnvironment _environment_, final EObject location) {
    return thisTypeRef(_environment_, null, location);
  }
  
  public Result<TypeRef> thisTypeRef(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EObject location) {
    try {
    	return thisTypeRefInternal(_environment_, _trace_, location);
    } catch (Exception _e_thisTypeRef) {
    	return resultForFailure(_e_thisTypeRef);
    }
  }
  
  protected Result<TypeRef> typeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypableElement element) {
    try {
    	checkParamsNotNull(element);
    	return typeDispatcher.invoke(_environment_, _trace_, element);
    } catch (Exception _e_type) {
    	sneakyThrowRuleFailedException(_e_type);
    	return null;
    }
  }
  
  protected void typeThrowException(final String _error, final String _issue, final Exception _ex, final TypableElement element, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    EClass _eClass = null;
    if (element!=null) {
      _eClass=element.eClass();
    }
    String _name = null;
    if (_eClass!=null) {
      _name=_eClass.getName();
    }
    String _plus = ("cannot type " + _name);
    String _plus_1 = (_plus + " ");
    String _stringRep = this.stringRep(element);
    String _plus_2 = (_plus_1 + _stringRep);
    String error = _plus_2;
    EObject source = element;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(source, null));
  }
  
  protected Result<Boolean> subtypeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	checkParamsNotNull(left, right);
    	return subtypeDispatcher.invoke(_environment_, _trace_, left, right);
    } catch (Exception _e_subtype) {
    	sneakyThrowRuleFailedException(_e_subtype);
    	return null;
    }
  }
  
  protected void subtypeThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument left, final TypeArgument right, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String _stringRep = this.stringRep(left);
    String _plus = (_stringRep + " is not a subtype of ");
    String _stringRep_1 = this.stringRep(right);
    String _plus_1 = (_plus + _stringRep_1);
    String error = _plus_1;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(null, null));
  }
  
  protected Result<Boolean> supertypeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	checkParamsNotNull(left, right);
    	return supertypeDispatcher.invoke(_environment_, _trace_, left, right);
    } catch (Exception _e_supertype) {
    	sneakyThrowRuleFailedException(_e_supertype);
    	return null;
    }
  }
  
  protected void supertypeThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument left, final TypeArgument right, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String _stringRep = this.stringRep(left);
    String _plus = (_stringRep + " is not a super type of ");
    String _stringRep_1 = this.stringRep(right);
    String _plus_1 = (_plus + _stringRep_1);
    String error = _plus_1;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(null, null));
  }
  
  protected Result<Boolean> equaltypeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) {
    try {
    	checkParamsNotNull(left, right);
    	return equaltypeDispatcher.invoke(_environment_, _trace_, left, right);
    } catch (Exception _e_equaltype) {
    	sneakyThrowRuleFailedException(_e_equaltype);
    	return null;
    }
  }
  
  protected void equaltypeThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument left, final TypeArgument right, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    String _stringRep = this.stringRep(left);
    String _plus = (_stringRep + " is not equal to ");
    String _stringRep_1 = this.stringRep(right);
    String _plus_1 = (_plus + _stringRep_1);
    String error = _plus_1;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(null, null));
  }
  
  protected Result<TypeRef> expectedTypeInInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EObject container, final Expression expression) {
    try {
    	checkParamsNotNull(container, expression);
    	return expectedTypeInDispatcher.invoke(_environment_, _trace_, container, expression);
    } catch (Exception _e_expectedTypeIn) {
    	sneakyThrowRuleFailedException(_e_expectedTypeIn);
    	return null;
    }
  }
  
  protected void expectedTypeInThrowException(final String _error, final String _issue, final Exception _ex, final EObject container, final Expression expression, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeRef> upperBoundInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArgument) {
    try {
    	checkParamsNotNull(typeArgument);
    	return upperBoundDispatcher.invoke(_environment_, _trace_, typeArgument);
    } catch (Exception _e_upperBound) {
    	sneakyThrowRuleFailedException(_e_upperBound);
    	return null;
    }
  }
  
  protected void upperBoundThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument typeArgument, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeRef> lowerBoundInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArgument) {
    try {
    	checkParamsNotNull(typeArgument);
    	return lowerBoundDispatcher.invoke(_environment_, _trace_, typeArgument);
    } catch (Exception _e_lowerBound) {
    	sneakyThrowRuleFailedException(_e_lowerBound);
    	return null;
    }
  }
  
  protected void lowerBoundThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument typeArgument, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeArgument> substTypeVariablesInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final TypeArgument typeArg) {
    try {
    	checkParamsNotNull(typeArg);
    	return substTypeVariablesDispatcher.invoke(_environment_, _trace_, typeArg);
    } catch (Exception _e_substTypeVariables) {
    	sneakyThrowRuleFailedException(_e_substTypeVariables);
    	return null;
    }
  }
  
  protected void substTypeVariablesThrowException(final String _error, final String _issue, final Exception _ex, final TypeArgument typeArg, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeRef> thisTypeRefInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EObject location) {
    try {
    	checkParamsNotNull(location);
    	return thisTypeRefDispatcher.invoke(_environment_, _trace_, location);
    } catch (Exception _e_thisTypeRef) {
    	sneakyThrowRuleFailedException(_e_thisTypeRef);
    	return null;
    }
  }
  
  protected void thisTypeRefThrowException(final String _error, final String _issue, final Exception _ex, final EObject location, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Type type) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeType(G, _subtrace_, type);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeType") + stringRepForEnv(G) + " |- " + stringRep(type) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeType) {
    	typeThrowException(ruleName("typeType") + stringRepForEnv(G) + " |- " + stringRep(type) + " : " + "TypeRef",
    		TYPETYPE,
    		e_applyRuleTypeType, type, new ErrorInformation[] {new ErrorInformation(type)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeType(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Type type) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeType_1(G, type));
  }
  
  private TypeRef _applyRuleTypeType_1(final RuleEnvironment G, final Type type) throws RuleFailedException {
    TypeRef _wrapTypeInTypeRef = TypeUtils.wrapTypeInTypeRef(type);
    return _wrapTypeInTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TEnumLiteral enumLiteral) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTEnumLiteral(G, _subtrace_, enumLiteral);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTEnumLiteral") + stringRepForEnv(G) + " |- " + stringRep(enumLiteral) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTEnumLiteral) {
    	typeThrowException(ruleName("typeTEnumLiteral") + stringRepForEnv(G) + " |- " + stringRep(enumLiteral) + " : " + "TypeRef",
    		TYPETENUMLITERAL,
    		e_applyRuleTypeTEnumLiteral, enumLiteral, new ErrorInformation[] {new ErrorInformation(enumLiteral)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTEnumLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TEnumLiteral enumLiteral) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeTEnumLiteral_1(G, enumLiteral));
  }
  
  private TypeRef _applyRuleTypeTEnumLiteral_1(final RuleEnvironment G, final TEnumLiteral enumLiteral) throws RuleFailedException {
    EObject _eContainer = enumLiteral.eContainer();
    TypeRef _ref = TypeExtensions.ref(((TEnum) _eContainer));
    return _ref;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TField tfield) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTField(G, _subtrace_, tfield);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTField") + stringRepForEnv(G) + " |- " + stringRep(tfield) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTField) {
    	typeThrowException(ruleName("typeTField") + stringRepForEnv(G) + " |- " + stringRep(tfield) + " : " + "TypeRef",
    		TYPETFIELD,
    		e_applyRuleTypeTField, tfield, new ErrorInformation[] {new ErrorInformation(tfield)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTField(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TField tfield) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _typeRef = tfield.getTypeRef();
    T = _typeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TGetter tgetter) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTGetter(G, _subtrace_, tgetter);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTGetter") + stringRepForEnv(G) + " |- " + stringRep(tgetter) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTGetter) {
    	typeThrowException(ruleName("typeTGetter") + stringRepForEnv(G) + " |- " + stringRep(tgetter) + " : " + "TypeRef",
    		TYPETGETTER,
    		e_applyRuleTypeTGetter, tgetter, new ErrorInformation[] {new ErrorInformation(tgetter)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTGetter(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TGetter tgetter) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _elvis = null;
    TypeRef _declaredTypeRef = tgetter.getDeclaredTypeRef();
    if (_declaredTypeRef != null) {
      _elvis = _declaredTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      _elvis = _anyTypeRef;
    }
    T = _elvis;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TSetter tsetter) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTSetter(G, _subtrace_, tsetter);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTSetter") + stringRepForEnv(G) + " |- " + stringRep(tsetter) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTSetter) {
    	typeThrowException(ruleName("typeTSetter") + stringRepForEnv(G) + " |- " + stringRep(tsetter) + " : " + "TypeRef",
    		TYPETSETTER,
    		e_applyRuleTypeTSetter, tsetter, new ErrorInformation[] {new ErrorInformation(tsetter)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTSetter(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TSetter tsetter) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _elvis = null;
    TypeRef _declaredTypeRef = tsetter.getDeclaredTypeRef();
    if (_declaredTypeRef != null) {
      _elvis = _declaredTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      _elvis = _anyTypeRef;
    }
    T = _elvis;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TVariable tvariable) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTVariable(G, _subtrace_, tvariable);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTVariable") + stringRepForEnv(G) + " |- " + stringRep(tvariable) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTVariable) {
    	typeThrowException(ruleName("typeTVariable") + stringRepForEnv(G) + " |- " + stringRep(tvariable) + " : " + "TypeRef",
    		TYPETVARIABLE,
    		e_applyRuleTypeTVariable, tvariable, new ErrorInformation[] {new ErrorInformation(tvariable)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTVariable(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TVariable tvariable) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _typeRef = tvariable.getTypeRef();
    T = _typeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeDefiningElement elem) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTypeDefiningElement(G, _subtrace_, elem);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTypeDefiningElement") + stringRepForEnv(G) + " |- " + stringRep(elem) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTypeDefiningElement) {
    	typeThrowException(ruleName("typeTypeDefiningElement") + stringRepForEnv(G) + " |- " + stringRep(elem) + " : " + "TypeRef",
    		TYPETYPEDEFININGELEMENT,
    		e_applyRuleTypeTypeDefiningElement, elem, new ErrorInformation[] {new ErrorInformation(elem)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTypeDefiningElement(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeDefiningElement elem) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeTypeDefiningElement_1(G, elem));
  }
  
  private TypeRef _applyRuleTypeTypeDefiningElement_1(final RuleEnvironment G, final TypeDefiningElement elem) throws RuleFailedException {
    Type _definedType = elem.getDefinedType();
    TypeRef _wrapTypeInTypeRef = TypeUtils.wrapTypeInTypeRef(_definedType);
    return _wrapTypeInTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ObjectLiteral ol) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeObjectLiteral(G, _subtrace_, ol);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeObjectLiteral") + stringRepForEnv(G) + " |- " + stringRep(ol) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeObjectLiteral) {
    	typeThrowException(ruleName("typeObjectLiteral") + stringRepForEnv(G) + " |- " + stringRep(ol) + " : " + "TypeRef",
    		TYPEOBJECTLITERAL,
    		e_applyRuleTypeObjectLiteral, ol, new ErrorInformation[] {new ErrorInformation(ol)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeObjectLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ObjectLiteral ol) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final ParameterizedTypeRefStructural ptr = TypeRefsFactory.eINSTANCE.createParameterizedTypeRefStructural();
    TClassifier _objectType = RuleEnvironmentExtensions.objectType(G);
    ptr.setDeclaredType(_objectType);
    Type _definedType = ol.getDefinedType();
    ptr.setStructuralType(((TStructuralType) _definedType));
    ptr.setDefinedTypingStrategy(TypingStrategy.STRUCTURAL);
    T = ptr;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisLiteral t) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeThisKeyword(G, _subtrace_, t);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeThisKeyword") + stringRepForEnv(G) + " |- " + stringRep(t) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeThisKeyword) {
    	typeThrowException(ruleName("typeThisKeyword") + stringRepForEnv(G) + " |- " + stringRep(t) + " : " + "TypeRef",
    		TYPETHISKEYWORD,
    		e_applyRuleTypeThisKeyword, t, new ErrorInformation[] {new ErrorInformation(t)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeThisKeyword(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisLiteral t) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef rawT = null;
    /* G |~ t ~> rawT */
    Result<TypeRef> result = thisTypeRefInternal(G, _trace_, t);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    rawT = (TypeRef) result.getFirst();
    
    TypeRef _enforceNominalTyping = TypeUtils.enforceNominalTyping(rawT);
    T = _enforceNominalTyping;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SuperLiteral superLiteral) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeSuperLiteral(G, _subtrace_, superLiteral);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeSuperLiteral") + stringRepForEnv(G) + " |- " + stringRep(superLiteral) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeSuperLiteral) {
    	typeThrowException(ruleName("typeSuperLiteral") + stringRepForEnv(G) + " |- " + stringRep(superLiteral) + " : " + "TypeRef",
    		TYPESUPERLITERAL,
    		e_applyRuleTypeSuperLiteral, superLiteral, new ErrorInformation[] {new ErrorInformation(superLiteral)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeSuperLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SuperLiteral superLiteral) throws RuleFailedException {
    TypeRef T = null; // output parameter
    EObject _eContainer = superLiteral.eContainer();
    final N4MemberDeclaration containingMemberDecl = EcoreUtil2.<N4MemberDeclaration>getContainerOfType(_eContainer, N4MemberDeclaration.class);
    /* if (containingMemberDecl === null) { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } else { val containingClass = (containingMemberDecl.eContainer as N4ClassDeclaration).definedType as TClass; val superClass = G.getDeclaredOrImplicitSuperType(containingClass) var effectiveSuperClass = superClass if( containingClass.isStaticPolyfill ) { effectiveSuperClass = G.getDeclaredOrImplicitSuperType( superClass as TClass ) } { superLiteral.eContainer instanceof ParameterizedPropertyAccessExpression || superLiteral.eContainer instanceof IndexedAccessExpression if(containingMemberDecl.static) T = effectiveSuperClass?.createClassifierTypeRef else T = effectiveSuperClass?.createTypeRef if (T !== null) T = TypeUtils.enforceNominalTyping(T) } or { superLiteral.eContainer instanceof ParameterizedCallExpression if(containingMemberDecl instanceof N4MethodDeclaration && containingMemberDecl.name == 'constructor') { val ctor = containerTypesHelper.fromContext(superLiteral.eResource).findConstructor(effectiveSuperClass); T = ctor?.createTypeRef } else { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } } or { superLiteral.eContainer instanceof NewExpression } } or { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } */
    {
      RuleFailedException previousFailure = null;
      try {
        if ((containingMemberDecl == null)) {
          UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
          T = _createUnknownTypeRef;
        } else {
          EObject _eContainer_1 = containingMemberDecl.eContainer();
          Type _definedType = ((N4ClassDeclaration) _eContainer_1).getDefinedType();
          final TClass containingClass = ((TClass) _definedType);
          final TClassifier superClass = RuleEnvironmentExtensions.getDeclaredOrImplicitSuperType(G, containingClass);
          TClassifier effectiveSuperClass = superClass;
          boolean _isStaticPolyfill = containingClass.isStaticPolyfill();
          if (_isStaticPolyfill) {
            TClassifier _declaredOrImplicitSuperType = RuleEnvironmentExtensions.getDeclaredOrImplicitSuperType(G, ((TClass) superClass));
            effectiveSuperClass = _declaredOrImplicitSuperType;
          }
          /* { superLiteral.eContainer instanceof ParameterizedPropertyAccessExpression || superLiteral.eContainer instanceof IndexedAccessExpression if(containingMemberDecl.static) T = effectiveSuperClass?.createClassifierTypeRef else T = effectiveSuperClass?.createTypeRef if (T !== null) T = TypeUtils.enforceNominalTyping(T) } or { superLiteral.eContainer instanceof ParameterizedCallExpression if(containingMemberDecl instanceof N4MethodDeclaration && containingMemberDecl.name == 'constructor') { val ctor = containerTypesHelper.fromContext(superLiteral.eResource).findConstructor(effectiveSuperClass); T = ctor?.createTypeRef } else { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } } or { superLiteral.eContainer instanceof NewExpression } */
          {
            try {
              /* superLiteral.eContainer instanceof ParameterizedPropertyAccessExpression || superLiteral.eContainer instanceof IndexedAccessExpression */
              if (!((superLiteral.eContainer() instanceof ParameterizedPropertyAccessExpression) || (superLiteral.eContainer() instanceof IndexedAccessExpression))) {
                sneakyThrowRuleFailedException("superLiteral.eContainer instanceof ParameterizedPropertyAccessExpression || superLiteral.eContainer instanceof IndexedAccessExpression");
              }
              boolean _isStatic = containingMemberDecl.isStatic();
              if (_isStatic) {
                TypeRef _createClassifierTypeRef = null;
                if (effectiveSuperClass!=null) {
                  _createClassifierTypeRef=TypeUtils.createClassifierTypeRef(effectiveSuperClass);
                }
                T = _createClassifierTypeRef;
              } else {
                ParameterizedTypeRef _createTypeRef = null;
                if (effectiveSuperClass!=null) {
                  _createTypeRef=TypeUtils.createTypeRef(effectiveSuperClass);
                }
                T = _createTypeRef;
              }
              if ((T != null)) {
                TypeRef _enforceNominalTyping = TypeUtils.enforceNominalTyping(T);
                T = _enforceNominalTyping;
              }
            } catch (Exception e) {
              previousFailure = extractRuleFailedException(e);
              /* { superLiteral.eContainer instanceof ParameterizedCallExpression if(containingMemberDecl instanceof N4MethodDeclaration && containingMemberDecl.name == 'constructor') { val ctor = containerTypesHelper.fromContext(superLiteral.eResource).findConstructor(effectiveSuperClass); T = ctor?.createTypeRef } else { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } } or { superLiteral.eContainer instanceof NewExpression } */
              {
                try {
                  EObject _eContainer_2 = superLiteral.eContainer();
                  /* superLiteral.eContainer instanceof ParameterizedCallExpression */
                  if (!(_eContainer_2 instanceof ParameterizedCallExpression)) {
                    sneakyThrowRuleFailedException("superLiteral.eContainer instanceof ParameterizedCallExpression");
                  }
                  if (((containingMemberDecl instanceof N4MethodDeclaration) && Objects.equal(containingMemberDecl.getName(), "constructor"))) {
                    Resource _eResource = superLiteral.eResource();
                    ContainerTypesHelper.MemberCollector _fromContext = this.containerTypesHelper.fromContext(_eResource);
                    final TMethod ctor = _fromContext.findConstructor(effectiveSuperClass);
                    ParameterizedTypeRef _createTypeRef_1 = null;
                    if (ctor!=null) {
                      _createTypeRef_1=TypeUtils.createTypeRef(ctor);
                    }
                    T = _createTypeRef_1;
                  } else {
                    UnknownTypeRef _createUnknownTypeRef_1 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
                    T = _createUnknownTypeRef_1;
                  }
                } catch (Exception e_1) {
                  previousFailure = extractRuleFailedException(e_1);
                  EObject _eContainer_3 = superLiteral.eContainer();
                  /* superLiteral.eContainer instanceof NewExpression */
                  if (!(_eContainer_3 instanceof NewExpression)) {
                    sneakyThrowRuleFailedException("superLiteral.eContainer instanceof NewExpression");
                  }
                }
              }
            }
          }
        }
      } catch (Exception e_2) {
        previousFailure = extractRuleFailedException(e_2);
        UnknownTypeRef _createUnknownTypeRef_2 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
        T = _createUnknownTypeRef_2;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IdentifierRef idref) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeIdentifierRef(G, _subtrace_, idref);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeIdentifierRef") + stringRepForEnv(G) + " |- " + stringRep(idref) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeIdentifierRef) {
    	typeThrowException(ruleName("typeIdentifierRef") + stringRepForEnv(G) + " |- " + stringRep(idref) + " : " + "TypeRef",
    		TYPEIDENTIFIERREF,
    		e_applyRuleTypeIdentifierRef, idref, new ErrorInformation[] {new ErrorInformation(idref)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeIdentifierRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IdentifierRef idref) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- idref.id : T */
    IdentifiableElement _id = idref.getId();
    Result<TypeRef> result = typeInternal(G, _trace_, _id);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    if (((idref.eContainer() instanceof ParameterizedCallExpression) && (idref.eContainmentFeature() == N4JSPackage.eINSTANCE.getParameterizedCallExpression_Target()))) {
      final TMethod callableCtorFunction = this.typeSystemHelper.getCallableClassConstructorFunction(T);
      if ((callableCtorFunction != null)) {
        FunctionTypeRef _ref = TypeExtensions.ref(callableCtorFunction);
        T = _ref;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NullLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeNullLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeNullLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeNullLiteral) {
    	typeThrowException(ruleName("typeNullLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPENULLLITERAL,
    		e_applyRuleTypeNullLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeNullLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NullLiteral l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeNullLiteral_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeNullLiteral_1(final RuleEnvironment G, final NullLiteral l) throws RuleFailedException {
    ParameterizedTypeRef _nullTypeRef = RuleEnvironmentExtensions.nullTypeRef(G);
    return _nullTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeBooleanLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeBooleanLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeBooleanLiteral) {
    	typeThrowException(ruleName("typeBooleanLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPEBOOLEANLITERAL,
    		e_applyRuleTypeBooleanLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeBooleanLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeBooleanLiteral_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeBooleanLiteral_1(final RuleEnvironment G, final BooleanLiteral l) throws RuleFailedException {
    ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
    return _booleanTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NumericLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeNumericLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeNumericLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeNumericLiteral) {
    	typeThrowException(ruleName("typeNumericLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "TypeRef",
    		TYPENUMERICLITERAL,
    		e_applyRuleTypeNumericLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeNumericLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NumericLiteral l) throws RuleFailedException {
    TypeRef T = null; // output parameter
    ParameterizedTypeRef _xifexpression = null;
    boolean _isIntLiteral = N4JSLanguageUtils.isIntLiteral(l);
    if (_isIntLiteral) {
      _xifexpression = RuleEnvironmentExtensions.intTypeRef(G);
    } else {
      _xifexpression = RuleEnvironmentExtensions.numberTypeRef(G);
    }
    T = _xifexpression;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeStringLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeStringLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeStringLiteral) {
    	typeThrowException(ruleName("typeStringLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPESTRINGLITERAL,
    		e_applyRuleTypeStringLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeStringLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeStringLiteral_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeStringLiteral_1(final RuleEnvironment G, final StringLiteral l) throws RuleFailedException {
    ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
    return _stringTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RegularExpressionLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeRegExpLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeRegExpLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeRegExpLiteral) {
    	typeThrowException(ruleName("typeRegExpLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPEREGEXPLITERAL,
    		e_applyRuleTypeRegExpLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeRegExpLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RegularExpressionLiteral l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeRegExpLiteral_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeRegExpLiteral_1(final RuleEnvironment G, final RegularExpressionLiteral l) throws RuleFailedException {
    ParameterizedTypeRef _regexpTypeRef = RuleEnvironmentExtensions.regexpTypeRef(G);
    return _regexpTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TaggedTemplateString l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTaggedTemplateString(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTaggedTemplateString") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTaggedTemplateString) {
    	typeThrowException(ruleName("typeTaggedTemplateString") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPETAGGEDTEMPLATESTRING,
    		e_applyRuleTypeTaggedTemplateString, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTaggedTemplateString(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TaggedTemplateString l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeTaggedTemplateString_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeTaggedTemplateString_1(final RuleEnvironment G, final TaggedTemplateString l) throws RuleFailedException {
    ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
    return _stringTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TemplateLiteral l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTemplateLiteral(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTemplateLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTemplateLiteral) {
    	typeThrowException(ruleName("typeTemplateLiteral") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPETEMPLATELITERAL,
    		e_applyRuleTypeTemplateLiteral, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTemplateLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TemplateLiteral l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeTemplateLiteral_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeTemplateLiteral_1(final RuleEnvironment G, final TemplateLiteral l) throws RuleFailedException {
    ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
    return _stringTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TemplateSegment l) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeTemplateSegment(G, _subtrace_, l);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeTemplateSegment") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeTemplateSegment) {
    	typeThrowException(ruleName("typeTemplateSegment") + stringRepForEnv(G) + " |- " + stringRep(l) + " : " + "ParameterizedTypeRef",
    		TYPETEMPLATESEGMENT,
    		e_applyRuleTypeTemplateSegment, l, new ErrorInformation[] {new ErrorInformation(l)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeTemplateSegment(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TemplateSegment l) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeTemplateSegment_1(G, l));
  }
  
  private ParameterizedTypeRef _applyRuleTypeTemplateSegment_1(final RuleEnvironment G, final TemplateSegment l) throws RuleFailedException {
    ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
    return _stringTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4EnumLiteral enumLiteral) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeN4EnumLiteral(G, _subtrace_, enumLiteral);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeN4EnumLiteral") + stringRepForEnv(G) + " |- " + stringRep(enumLiteral) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeN4EnumLiteral) {
    	typeThrowException(ruleName("typeN4EnumLiteral") + stringRepForEnv(G) + " |- " + stringRep(enumLiteral) + " : " + "TypeRef",
    		TYPEN4ENUMLITERAL,
    		e_applyRuleTypeN4EnumLiteral, enumLiteral, new ErrorInformation[] {new ErrorInformation(enumLiteral)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeN4EnumLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4EnumLiteral enumLiteral) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeN4EnumLiteral_1(G, enumLiteral));
  }
  
  private TypeRef _applyRuleTypeN4EnumLiteral_1(final RuleEnvironment G, final N4EnumLiteral enumLiteral) throws RuleFailedException {
    TEnumLiteral _definedLiteral = enumLiteral.getDefinedLiteral();
    EObject _eContainer = _definedLiteral.eContainer();
    TypeRef _ref = TypeExtensions.ref(((TEnum) _eContainer));
    return _ref;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayLiteral al) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeArrayLiteral(G, _subtrace_, al);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeArrayLiteral") + stringRepForEnv(G) + " |- " + stringRep(al) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeArrayLiteral) {
    	typeThrowException(ruleName("typeArrayLiteral") + stringRepForEnv(G) + " |- " + stringRep(al) + " : " + "TypeRef",
    		TYPEARRAYLITERAL,
    		e_applyRuleTypeArrayLiteral, al, new ErrorInformation[] {new ErrorInformation(al)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeArrayLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayLiteral al) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* fail error "rule typeArrayLiteral should never be invoked (PolyComputer is responsible for typing ArrayLiterals)" */
    String error = "rule typeArrayLiteral should never be invoked (PolyComputer is responsible for typing ArrayLiterals)";
    throwForExplicitFail(error, new ErrorInformation(null, null));
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayPadding p) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeArrayPadding(G, _subtrace_, p);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeArrayPadding") + stringRepForEnv(G) + " |- " + stringRep(p) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeArrayPadding) {
    	typeThrowException(ruleName("typeArrayPadding") + stringRepForEnv(G) + " |- " + stringRep(p) + " : " + "ParameterizedTypeRef",
    		TYPEARRAYPADDING,
    		e_applyRuleTypeArrayPadding, p, new ErrorInformation[] {new ErrorInformation(p)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeArrayPadding(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayPadding p) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeArrayPadding_1(G, p));
  }
  
  private ParameterizedTypeRef _applyRuleTypeArrayPadding_1(final RuleEnvironment G, final ArrayPadding p) throws RuleFailedException {
    ParameterizedTypeRef _undefinedTypeRef = RuleEnvironmentExtensions.undefinedTypeRef(G);
    return _undefinedTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayElement e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeArrayElement(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeArrayElement") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeArrayElement) {
    	typeThrowException(ruleName("typeArrayElement") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEARRAYELEMENT,
    		e_applyRuleTypeArrayElement, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeArrayElement(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArrayElement e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- e.expression: T */
    Expression _expression = e.getExpression();
    Result<TypeRef> result = typeInternal(G, _trace_, _expression);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PropertyNameValuePair property) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypePropertyNameValuePair(G, _subtrace_, property);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typePropertyNameValuePair") + stringRepForEnv(G) + " |- " + stringRep(property) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypePropertyNameValuePair) {
    	typeThrowException(ruleName("typePropertyNameValuePair") + stringRepForEnv(G) + " |- " + stringRep(property) + " : " + "TypeRef",
    		TYPEPROPERTYNAMEVALUEPAIR,
    		e_applyRuleTypePropertyNameValuePair, property, new ErrorInformation[] {new ErrorInformation(property)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypePropertyNameValuePair(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PropertyNameValuePair property) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = property.getDeclaredTypeRef();
    boolean _tripleNotEquals = (_declaredTypeRef != null);
    if (_tripleNotEquals) {
      TypeRef _declaredTypeRef_1 = property.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      Expression _expression = property.getExpression();
      boolean _tripleNotEquals_1 = (_expression != null);
      if (_tripleNotEquals_1) {
        /* G |- property.expression: var TypeRef E */
        Expression _expression_1 = property.getExpression();
        TypeRef E = null;
        Result<TypeRef> result = typeInternal(G, _trace_, _expression_1);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        /* G |~ E /\ E */
        Result<TypeRef> result_1 = upperBoundInternal(G, _trace_, E);
        checkAssignableTo(result_1.getFirst(), TypeRef.class);
        E = (TypeRef) result_1.getFirst();
        
        if ((((E.getDeclaredType() == RuleEnvironmentExtensions.undefinedType(G)) || (E.getDeclaredType() == RuleEnvironmentExtensions.nullType(G))) || (E.getDeclaredType() == RuleEnvironmentExtensions.voidType(G)))) {
          ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
          T = _anyTypeRef;
        } else {
          T = E;
        }
      } else {
        ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
        T = _anyTypeRef_1;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4FieldDeclaration fieldDecl) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeN4FieldDeclaration(G, _subtrace_, fieldDecl);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeN4FieldDeclaration") + stringRepForEnv(G) + " |- " + stringRep(fieldDecl) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeN4FieldDeclaration) {
    	typeThrowException(ruleName("typeN4FieldDeclaration") + stringRepForEnv(G) + " |- " + stringRep(fieldDecl) + " : " + "TypeRef",
    		TYPEN4FIELDDECLARATION,
    		e_applyRuleTypeN4FieldDeclaration, fieldDecl, new ErrorInformation[] {new ErrorInformation(fieldDecl)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeN4FieldDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4FieldDeclaration fieldDecl) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = fieldDecl.getDeclaredTypeRef();
    boolean _tripleNotEquals = (_declaredTypeRef != null);
    if (_tripleNotEquals) {
      TypeRef _declaredTypeRef_1 = fieldDecl.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      Expression _expression = fieldDecl.getExpression();
      boolean _tripleNotEquals_1 = (_expression != null);
      if (_tripleNotEquals_1) {
        /* G |- fieldDecl.expression : var TypeRef E */
        Expression _expression_1 = fieldDecl.getExpression();
        TypeRef E = null;
        Result<TypeRef> result = typeInternal(G, _trace_, _expression_1);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        /* G |~ E /\ E */
        Result<TypeRef> result_1 = upperBoundInternal(G, _trace_, E);
        checkAssignableTo(result_1.getFirst(), TypeRef.class);
        E = (TypeRef) result_1.getFirst();
        
        if ((((E.getDeclaredType() == RuleEnvironmentExtensions.undefinedType(G)) || (E.getDeclaredType() == RuleEnvironmentExtensions.nullType(G))) || (E.getDeclaredType() == RuleEnvironmentExtensions.voidType(G)))) {
          ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
          T = _anyTypeRef;
        } else {
          T = E;
        }
      } else {
        ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
        T = _anyTypeRef_1;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final GetterDeclaration getter) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeGetterDeclaration(G, _subtrace_, getter);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeGetterDeclaration") + stringRepForEnv(G) + " |- " + stringRep(getter) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeGetterDeclaration) {
    	typeThrowException(ruleName("typeGetterDeclaration") + stringRepForEnv(G) + " |- " + stringRep(getter) + " : " + "TypeRef",
    		TYPEGETTERDECLARATION,
    		e_applyRuleTypeGetterDeclaration, getter, new ErrorInformation[] {new ErrorInformation(getter)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeGetterDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final GetterDeclaration getter) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = getter.getDeclaredTypeRef();
    boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
    if (_notEquals) {
      TypeRef _declaredTypeRef_1 = getter.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      TGetter _definedGetter = getter.getDefinedGetter();
      TypeRef _declaredTypeRef_2 = _definedGetter.getDeclaredTypeRef();
      boolean _notEquals_1 = (!Objects.equal(_declaredTypeRef_2, null));
      if (_notEquals_1) {
        TGetter _definedGetter_1 = getter.getDefinedGetter();
        TypeRef _declaredTypeRef_3 = _definedGetter_1.getDeclaredTypeRef();
        T = _declaredTypeRef_3;
      } else {
        ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
        T = _anyTypeRef;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SetterDeclaration setter) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeSetterDeclaration(G, _subtrace_, setter);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeSetterDeclaration") + stringRepForEnv(G) + " |- " + stringRep(setter) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeSetterDeclaration) {
    	typeThrowException(ruleName("typeSetterDeclaration") + stringRepForEnv(G) + " |- " + stringRep(setter) + " : " + "TypeRef",
    		TYPESETTERDECLARATION,
    		e_applyRuleTypeSetterDeclaration, setter, new ErrorInformation[] {new ErrorInformation(setter)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeSetterDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final SetterDeclaration setter) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = setter.getDeclaredTypeRef();
    boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
    if (_notEquals) {
      TypeRef _declaredTypeRef_1 = setter.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    }
    if ((T == null)) {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParenExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeParenExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeParenExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeParenExpression) {
    	typeThrowException(ruleName("typeParenExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEPARENEXPRESSION,
    		e_applyRuleTypeParenExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeParenExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParenExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- e.expression: T */
    Expression _expression = e.getExpression();
    Result<TypeRef> result = typeInternal(G, _trace_, _expression);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final YieldExpression y) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeYieldExpression(G, _subtrace_, y);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeYieldExpression") + stringRepForEnv(G) + " |- " + stringRep(y) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeYieldExpression) {
    	typeThrowException(ruleName("typeYieldExpression") + stringRepForEnv(G) + " |- " + stringRep(y) + " : " + "TypeRef",
    		TYPEYIELDEXPRESSION,
    		e_applyRuleTypeYieldExpression, y, new ErrorInformation[] {new ErrorInformation(y)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeYieldExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final YieldExpression y) throws RuleFailedException {
    TypeRef T = null; // output parameter
    UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
    T = _createUnknownTypeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AwaitExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeAwaitExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeAwaitExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeAwaitExpression) {
    	typeThrowException(ruleName("typeAwaitExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEAWAITEXPRESSION,
    		e_applyRuleTypeAwaitExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeAwaitExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AwaitExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- e.expression : var TypeRef exprType */
    Expression _expression = e.getExpression();
    TypeRef exprType = null;
    Result<TypeRef> result = typeInternal(G, _trace_, _expression);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    exprType = (TypeRef) result.getFirst();
    
    Type _declaredType = exprType.getDeclaredType();
    TClass _promiseType = RuleEnvironmentExtensions.promiseType(G);
    boolean _tripleEquals = (_declaredType == _promiseType);
    if (_tripleEquals) {
      /* G |~ exprType.typeArgs.get(0) /\ T */
      EList<TypeArgument> _typeArgs = exprType.getTypeArgs();
      TypeArgument _get = _typeArgs.get(0);
      Result<TypeRef> result_1 = upperBoundInternal(G, _trace_, _get);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      T = (TypeRef) result_1.getFirst();
      
    } else {
      Expression _expression_1 = e.getExpression();
      boolean _isPromisifiableExpression = this.promisifyHelper.isPromisifiableExpression(_expression_1);
      if (_isPromisifiableExpression) {
        Expression _expression_2 = e.getExpression();
        final TypeRef promisifiedReturnTypeRef = this.promisifyHelper.extractPromisifiedReturnType(_expression_2);
        Type _declaredType_1 = promisifiedReturnTypeRef.getDeclaredType();
        TClass _promiseType_1 = RuleEnvironmentExtensions.promiseType(G);
        boolean _tripleEquals_1 = (_declaredType_1 == _promiseType_1);
        if (_tripleEquals_1) {
          /* G |~ promisifiedReturnTypeRef.typeArgs.get(0) /\ T */
          EList<TypeArgument> _typeArgs_1 = promisifiedReturnTypeRef.getTypeArgs();
          TypeArgument _get_1 = _typeArgs_1.get(0);
          Result<TypeRef> result_2 = upperBoundInternal(G, _trace_, _get_1);
          checkAssignableTo(result_2.getFirst(), TypeRef.class);
          T = (TypeRef) result_2.getFirst();
          
        } else {
          T = promisifiedReturnTypeRef;
        }
      } else {
        T = exprType;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PromisifyExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypePromisifyExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typePromisifyExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypePromisifyExpression) {
    	typeThrowException(ruleName("typePromisifyExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEPROMISIFYEXPRESSION,
    		e_applyRuleTypePromisifyExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypePromisifyExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PromisifyExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    Expression _expression = e.getExpression();
    TypeRef _extractPromisifiedReturnType = this.promisifyHelper.extractPromisifiedReturnType(_expression);
    T = _extractPromisifiedReturnType;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IndexedAccessExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeIndexedAccessExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeIndexedAccessExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeIndexedAccessExpression) {
    	typeThrowException(ruleName("typeIndexedAccessExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPEINDEXEDACCESSEXPRESSION,
    		e_applyRuleTypeIndexedAccessExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeIndexedAccessExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IndexedAccessExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* { expr.target instanceof SuperLiteral T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } or { G |- expr.target : var TypeRef targetTypeRef G |~ targetTypeRef /\ targetTypeRef val accessedBuiltInSymbol = G.getAccessedBuiltInSymbol(expr.index); val elementType = targetTypeRef.declaredType?.elementType if (accessedBuiltInSymbol!==null) { val declType = targetTypeRef.declaredType; if(declType instanceof ContainerType<?>) { val memberName = '#' + accessedBuiltInSymbol.name; val member = containerTypesHelper.fromContext(expr).findMember(declType,memberName,false,false); if(member!==null) { G |- member : var TypeRef memberTypeRef val G2 = G.wrap typeSystemHelper.addSubstitutions(G2,targetTypeRef) G2.addThisType(targetTypeRef) G2 |- memberTypeRef ~> T } else { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } } else { T = G.anyTypeRef } } else if (elementType !== null) { val declaredType = targetTypeRef.declaredType if (declaredType.generic && targetTypeRef.typeArgs.isEmpty) { T = G.anyTypeRef } else { val G2 = G.wrap typeSystemHelper.addSubstitutions(G2, targetTypeRef) G2.addThisType(targetTypeRef) G2 |- elementType ~> T } } else if (expr.index instanceof StringLiteral) { val staticAccess = (targetTypeRef instanceof ClassifierTypeRef) val checkVisibility = false val scope = memberScopingHelper.createMemberScopeFor(targetTypeRef, expr, checkVisibility, staticAccess) val memberName = (expr.index as StringLiteral).value; val member = memberScopingHelper.findUniqueMemberForName(scope, memberName, staticAccess) if(member != null) { G |- member : var TypeRef memberTypeRef val G2 = G.wrap typeSystemHelper.addSubstitutions(G2,targetTypeRef) G2.addThisType(targetTypeRef) G2 |- memberTypeRef ~> T } else { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } } else if (targetTypeRef.dynamic) { T = G.anyTypeRefDynamic } else { T = G.anyTypeRef } } */
    {
      RuleFailedException previousFailure = null;
      try {
        Expression _target = expr.getTarget();
        /* expr.target instanceof SuperLiteral */
        if (!(_target instanceof SuperLiteral)) {
          sneakyThrowRuleFailedException("expr.target instanceof SuperLiteral");
        }
        UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
        T = _createUnknownTypeRef;
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* G |- expr.target : var TypeRef targetTypeRef */
        Expression _target_1 = expr.getTarget();
        TypeRef targetTypeRef = null;
        Result<TypeRef> result = typeInternal(G, _trace_, _target_1);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        targetTypeRef = (TypeRef) result.getFirst();
        
        /* G |~ targetTypeRef /\ targetTypeRef */
        Result<TypeRef> result_1 = upperBoundInternal(G, _trace_, targetTypeRef);
        checkAssignableTo(result_1.getFirst(), TypeRef.class);
        targetTypeRef = (TypeRef) result_1.getFirst();
        
        Expression _index = expr.getIndex();
        final TField accessedBuiltInSymbol = RuleEnvironmentExtensions.getAccessedBuiltInSymbol(G, _index);
        Type _declaredType = targetTypeRef.getDeclaredType();
        TypeRef _elementType = null;
        if (_declaredType!=null) {
          _elementType=_declaredType.getElementType();
        }
        final TypeRef elementType = _elementType;
        if ((accessedBuiltInSymbol != null)) {
          final Type declType = targetTypeRef.getDeclaredType();
          if ((declType instanceof ContainerType<?>)) {
            String _name = accessedBuiltInSymbol.getName();
            final String memberName = ("#" + _name);
            ContainerTypesHelper.MemberCollector _fromContext = this.containerTypesHelper.fromContext(expr);
            final TMember member = _fromContext.findMember(((ContainerType<?>)declType), memberName, false, false);
            if ((member != null)) {
              /* G |- member : var TypeRef memberTypeRef */
              TypeRef memberTypeRef = null;
              Result<TypeRef> result_2 = typeInternal(G, _trace_, member);
              checkAssignableTo(result_2.getFirst(), TypeRef.class);
              memberTypeRef = (TypeRef) result_2.getFirst();
              
              final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
              this.typeSystemHelper.addSubstitutions(G2, targetTypeRef);
              RuleEnvironmentExtensions.addThisType(G2, targetTypeRef);
              /* G2 |- memberTypeRef ~> T */
              Result<TypeArgument> result_3 = substTypeVariablesInternal(G2, _trace_, memberTypeRef);
              checkAssignableTo(result_3.getFirst(), TypeRef.class);
              T = (TypeRef) result_3.getFirst();
              
            } else {
              UnknownTypeRef _createUnknownTypeRef_1 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
              T = _createUnknownTypeRef_1;
            }
          } else {
            ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
            T = _anyTypeRef;
          }
        } else {
          if ((elementType != null)) {
            final Type declaredType = targetTypeRef.getDeclaredType();
            if ((declaredType.isGeneric() && targetTypeRef.getTypeArgs().isEmpty())) {
              ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef_1;
            } else {
              final RuleEnvironment G2_1 = RuleEnvironmentExtensions.wrap(G);
              this.typeSystemHelper.addSubstitutions(G2_1, targetTypeRef);
              RuleEnvironmentExtensions.addThisType(G2_1, targetTypeRef);
              /* G2 |- elementType ~> T */
              Result<TypeArgument> result_4 = substTypeVariablesInternal(G2_1, _trace_, elementType);
              checkAssignableTo(result_4.getFirst(), TypeRef.class);
              T = (TypeRef) result_4.getFirst();
              
            }
          } else {
            Expression _index_1 = expr.getIndex();
            if ((_index_1 instanceof StringLiteral)) {
              final boolean staticAccess = (targetTypeRef instanceof ClassifierTypeRef);
              final boolean checkVisibility = false;
              final IScope scope = this.memberScopingHelper.createMemberScopeFor(targetTypeRef, expr, checkVisibility, staticAccess);
              Expression _index_2 = expr.getIndex();
              final String memberName_1 = ((StringLiteral) _index_2).getValue();
              final TMember member_1 = this.memberScopingHelper.findUniqueMemberForName(scope, memberName_1, staticAccess);
              boolean _notEquals = (!Objects.equal(member_1, null));
              if (_notEquals) {
                /* G |- member : var TypeRef memberTypeRef */
                TypeRef memberTypeRef_1 = null;
                Result<TypeRef> result_5 = typeInternal(G, _trace_, member_1);
                checkAssignableTo(result_5.getFirst(), TypeRef.class);
                memberTypeRef_1 = (TypeRef) result_5.getFirst();
                
                final RuleEnvironment G2_2 = RuleEnvironmentExtensions.wrap(G);
                this.typeSystemHelper.addSubstitutions(G2_2, targetTypeRef);
                RuleEnvironmentExtensions.addThisType(G2_2, targetTypeRef);
                /* G2 |- memberTypeRef ~> T */
                Result<TypeArgument> result_6 = substTypeVariablesInternal(G2_2, _trace_, memberTypeRef_1);
                checkAssignableTo(result_6.getFirst(), TypeRef.class);
                T = (TypeRef) result_6.getFirst();
                
              } else {
                UnknownTypeRef _createUnknownTypeRef_2 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
                T = _createUnknownTypeRef_2;
              }
            } else {
              boolean _isDynamic = targetTypeRef.isDynamic();
              if (_isDynamic) {
                ParameterizedTypeRef _anyTypeRefDynamic = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
                T = _anyTypeRefDynamic;
              } else {
                ParameterizedTypeRef _anyTypeRef_2 = RuleEnvironmentExtensions.anyTypeRef(G);
                T = _anyTypeRef_2;
              }
            }
          }
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedPropertyAccessExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypePropertyAccessExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typePropertyAccessExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypePropertyAccessExpression) {
    	typeThrowException(ruleName("typePropertyAccessExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPEPROPERTYACCESSEXPRESSION,
    		e_applyRuleTypePropertyAccessExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypePropertyAccessExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedPropertyAccessExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* { T = env(G, GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION -> expr, TypeRef) } or { val G2 = G.wrap G2.add(GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION -> expr, G2.anyTypeRef) G2 |- expr.target : var TypeRef receiverTypeRef typeSystemHelper.addSubstitutions(G2,receiverTypeRef) G2.addThisType(receiverTypeRef) if (! (receiverTypeRef instanceof UnknownTypeRef) && (expr.target instanceof SuperLiteral || expr.target instanceof ThisLiteral) ) { var containingClass = EcoreUtil2.getContainerOfType(expr,N4ClassDeclaration)?.definedType; if (containingClass instanceof TClass) { if (containingClass.isStaticPolyfill) { containingClass = containingClass.superClassRef?.declaredType } if (containingClass instanceof TClass) { if (containingClass?.superClassRef!==null) { typeSystemHelper.addSubstitutions(G2, containingClass.superClassRef) } } } } var TypeRef propTypeRef; if((receiverTypeRef instanceof ClassifierTypeRef || receiverTypeRef instanceof BoundThisTypeRef || receiverTypeRef instanceof ParameterizedTypeRef) && expr.property instanceof TMethod && expr.property.name == 'constructor') { var Type receiverType = switch(receiverTypeRef) { ClassifierTypeRef: receiverTypeRef.staticType BoundThisTypeRef: receiverTypeRef.actualThisTypeRef?.declaredType default: receiverTypeRef.declaredType }; propTypeRef = if(receiverType!==null) TypeUtils.createConstructorTypeRef(receiverType) else TypeRefsFactory.eINSTANCE.createUnknownTypeRef; } else if(receiverTypeRef.dynamic && expr.property!==null && expr.property.eIsProxy) { propTypeRef = G.anyTypeRefDynamic; } else { G2.wrap |- expr.property : propTypeRef if(expr.parameterized) typeSystemHelper.addSubstitutions(G2,expr); } G2 |- propTypeRef ~> T } */
    {
      RuleFailedException previousFailure = null;
      try {
        Pair<String, ParameterizedPropertyAccessExpression> _mappedTo = Pair.<String, ParameterizedPropertyAccessExpression>of(RuleEnvironmentExtensions.GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION, expr);
        TypeRef _env = this.<TypeRef>env(G, _mappedTo, TypeRef.class);
        T = _env;
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
        Pair<String, ParameterizedPropertyAccessExpression> _mappedTo_1 = Pair.<String, ParameterizedPropertyAccessExpression>of(RuleEnvironmentExtensions.GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION, expr);
        ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G2);
        boolean _add = G2.add(_mappedTo_1, _anyTypeRef);
        /* G2.add(GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION -> expr, G2.anyTypeRef) */
        if (!_add) {
          sneakyThrowRuleFailedException("G2.add(GUARD_TYPE_PROPERTY_ACCESS_EXPRESSION -> expr, G2.anyTypeRef)");
        }
        /* G2 |- expr.target : var TypeRef receiverTypeRef */
        Expression _target = expr.getTarget();
        TypeRef receiverTypeRef = null;
        Result<TypeRef> result = typeInternal(G2, _trace_, _target);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        receiverTypeRef = (TypeRef) result.getFirst();
        
        this.typeSystemHelper.addSubstitutions(G2, receiverTypeRef);
        RuleEnvironmentExtensions.addThisType(G2, receiverTypeRef);
        if (((!(receiverTypeRef instanceof UnknownTypeRef)) && ((expr.getTarget() instanceof SuperLiteral) || (expr.getTarget() instanceof ThisLiteral)))) {
          N4ClassDeclaration _containerOfType = EcoreUtil2.<N4ClassDeclaration>getContainerOfType(expr, N4ClassDeclaration.class);
          Type _definedType = null;
          if (_containerOfType!=null) {
            _definedType=_containerOfType.getDefinedType();
          }
          Type containingClass = _definedType;
          if ((containingClass instanceof TClass)) {
            boolean _isStaticPolyfill = ((TClass)containingClass).isStaticPolyfill();
            if (_isStaticPolyfill) {
              ParameterizedTypeRef _superClassRef = ((TClass)containingClass).getSuperClassRef();
              Type _declaredType = null;
              if (_superClassRef!=null) {
                _declaredType=_superClassRef.getDeclaredType();
              }
              containingClass = _declaredType;
            }
            if ((containingClass instanceof TClass)) {
              ParameterizedTypeRef _superClassRef_1 = null;
              if (((TClass)containingClass)!=null) {
                _superClassRef_1=((TClass)containingClass).getSuperClassRef();
              }
              boolean _tripleNotEquals = (_superClassRef_1 != null);
              if (_tripleNotEquals) {
                ParameterizedTypeRef _superClassRef_2 = ((TClass)containingClass).getSuperClassRef();
                this.typeSystemHelper.addSubstitutions(G2, _superClassRef_2);
              }
            }
          }
        }
        TypeRef propTypeRef = null;
        if ((((((receiverTypeRef instanceof ClassifierTypeRef) || (receiverTypeRef instanceof BoundThisTypeRef)) || (receiverTypeRef instanceof ParameterizedTypeRef)) && (expr.getProperty() instanceof TMethod)) && Objects.equal(expr.getProperty().getName(), "constructor"))) {
          Type _switchResult = null;
          boolean _matched = false;
          if (receiverTypeRef instanceof ClassifierTypeRef) {
            _matched=true;
            _switchResult = ((ClassifierTypeRef)receiverTypeRef).staticType();
          }
          if (!_matched) {
            if (receiverTypeRef instanceof BoundThisTypeRef) {
              _matched=true;
              ParameterizedTypeRef _actualThisTypeRef = ((BoundThisTypeRef)receiverTypeRef).getActualThisTypeRef();
              Type _declaredType_1 = null;
              if (_actualThisTypeRef!=null) {
                _declaredType_1=_actualThisTypeRef.getDeclaredType();
              }
              _switchResult = _declaredType_1;
            }
          }
          if (!_matched) {
            _switchResult = receiverTypeRef.getDeclaredType();
          }
          Type receiverType = _switchResult;
          TypeRef _xifexpression = null;
          if ((receiverType != null)) {
            _xifexpression = TypeUtils.createConstructorTypeRef(receiverType);
          } else {
            _xifexpression = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
          }
          propTypeRef = _xifexpression;
        } else {
          if (((receiverTypeRef.isDynamic() && (expr.getProperty() != null)) && expr.getProperty().eIsProxy())) {
            ParameterizedTypeRef _anyTypeRefDynamic = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
            propTypeRef = _anyTypeRefDynamic;
          } else {
            /* G2.wrap |- expr.property : propTypeRef */
            IdentifiableElement _property = expr.getProperty();
            RuleEnvironment _wrap = RuleEnvironmentExtensions.wrap(G2);
            Result<TypeRef> result_1 = typeInternal(_wrap, _trace_, _property);
            checkAssignableTo(result_1.getFirst(), TypeRef.class);
            propTypeRef = (TypeRef) result_1.getFirst();
            
            boolean _isParameterized = expr.isParameterized();
            if (_isParameterized) {
              this.typeSystemHelper.addSubstitutions(G2, expr);
            }
          }
        }
        /* G2 |- propTypeRef ~> T */
        Result<TypeArgument> result_2 = substTypeVariablesInternal(G2, _trace_, propTypeRef);
        checkAssignableTo(result_2.getFirst(), TypeRef.class);
        T = (TypeRef) result_2.getFirst();
        
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NewExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeNewExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeNewExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeNewExpression) {
    	typeThrowException(ruleName("typeNewExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPENEWEXPRESSION,
    		e_applyRuleTypeNewExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeNewExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NewExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- e.callee: T */
    Expression _callee = e.getCallee();
    Result<TypeRef> result = typeInternal(G, _trace_, _callee);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    if ((T instanceof ConstructorTypeRef)) {
      EList<TypeRef> _typeArgs = e.getTypeArgs();
      TypeRef _createTypeRefFromStaticType = RuleEnvironmentExtensions.createTypeRefFromStaticType(((ClassifierTypeRef)T), ((TypeArgument[])Conversions.unwrapArray(_typeArgs, TypeArgument.class)));
      T = _createTypeRefFromStaticType;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NewTarget nt) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeNewTarget(G, _subtrace_, nt);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeNewTarget") + stringRepForEnv(G) + " |- " + stringRep(nt) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeNewTarget) {
    	typeThrowException(ruleName("typeNewTarget") + stringRepForEnv(G) + " |- " + stringRep(nt) + " : " + "TypeRef",
    		TYPENEWTARGET,
    		e_applyRuleTypeNewTarget, nt, new ErrorInformation[] {new ErrorInformation(nt)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeNewTarget(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NewTarget nt) throws RuleFailedException {
    TypeRef T = null; // output parameter
    UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
    T = _createUnknownTypeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedCallExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeCallExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeCallExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeCallExpression) {
    	typeThrowException(ruleName("typeCallExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPECALLEXPRESSION,
    		e_applyRuleTypeCallExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeCallExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedCallExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- expr.target : var TypeRef targetTypeRef */
    Expression _target = expr.getTarget();
    TypeRef targetTypeRef = null;
    Result<TypeRef> result = typeInternal(G, _trace_, _target);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    targetTypeRef = (TypeRef) result.getFirst();
    
    if ((targetTypeRef instanceof FunctionTypeExprOrRef)) {
      final FunctionTypeExprOrRef F = ((FunctionTypeExprOrRef)targetTypeRef);
      /* { val inferring = env(G, GUARD_TYPE_CALL_EXPRESSION -> expr, TypeRef) G |- inferring ~> T } or { (F.returnTypeRef===null); T = G.anyTypeRef; } or { val G2 = G.wrap; G2.add(GUARD_TYPE_CALL_EXPRESSION -> expr, F.returnTypeRef) if (F.returnTypeRef instanceof ThisTypeRef) { G2 |- expr.receiver: T if( F.declaredType instanceof TMethod && (F.declaredType as TMethod).isStatic() ) { if( T instanceof ConstructorTypeRef ) { T = T.createTypeRefFromStaticType(targetTypeRef.typeArgs); } else if ( T instanceof EnumTypeRef ) { T= T.enumType.ref(targetTypeRef.typeArgs) } } } else { typeSystemHelper.addSubstitutions(G2,expr,targetTypeRef); G2 |- F.returnTypeRef ~> T } } */
      {
        RuleFailedException previousFailure = null;
        try {
          Pair<String, ParameterizedCallExpression> _mappedTo = Pair.<String, ParameterizedCallExpression>of(RuleEnvironmentExtensions.GUARD_TYPE_CALL_EXPRESSION, expr);
          final TypeRef inferring = this.<TypeRef>env(G, _mappedTo, TypeRef.class);
          /* G |- inferring ~> T */
          Result<TypeArgument> result_1 = substTypeVariablesInternal(G, _trace_, inferring);
          checkAssignableTo(result_1.getFirst(), TypeRef.class);
          T = (TypeRef) result_1.getFirst();
          
        } catch (Exception e) {
          previousFailure = extractRuleFailedException(e);
          /* { (F.returnTypeRef===null); T = G.anyTypeRef; } or { val G2 = G.wrap; G2.add(GUARD_TYPE_CALL_EXPRESSION -> expr, F.returnTypeRef) if (F.returnTypeRef instanceof ThisTypeRef) { G2 |- expr.receiver: T if( F.declaredType instanceof TMethod && (F.declaredType as TMethod).isStatic() ) { if( T instanceof ConstructorTypeRef ) { T = T.createTypeRefFromStaticType(targetTypeRef.typeArgs); } else if ( T instanceof EnumTypeRef ) { T= T.enumType.ref(targetTypeRef.typeArgs) } } } else { typeSystemHelper.addSubstitutions(G2,expr,targetTypeRef); G2 |- F.returnTypeRef ~> T } } */
          {
            try {
              TypeRef _returnTypeRef = F.getReturnTypeRef();
              boolean _tripleEquals = (_returnTypeRef == null);
              /* F.returnTypeRef===null */
              if (!_tripleEquals) {
                sneakyThrowRuleFailedException("F.returnTypeRef===null");
              }
              ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef;
            } catch (Exception e_1) {
              previousFailure = extractRuleFailedException(e_1);
              final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
              Pair<String, ParameterizedCallExpression> _mappedTo_1 = Pair.<String, ParameterizedCallExpression>of(RuleEnvironmentExtensions.GUARD_TYPE_CALL_EXPRESSION, expr);
              TypeRef _returnTypeRef_1 = F.getReturnTypeRef();
              boolean _add = G2.add(_mappedTo_1, _returnTypeRef_1);
              /* G2.add(GUARD_TYPE_CALL_EXPRESSION -> expr, F.returnTypeRef) */
              if (!_add) {
                sneakyThrowRuleFailedException("G2.add(GUARD_TYPE_CALL_EXPRESSION -> expr, F.returnTypeRef)");
              }
              TypeRef _returnTypeRef_2 = F.getReturnTypeRef();
              if ((_returnTypeRef_2 instanceof ThisTypeRef)) {
                /* G2 |- expr.receiver: T */
                Expression _receiver = expr.getReceiver();
                Result<TypeRef> result_2 = typeInternal(G2, _trace_, _receiver);
                checkAssignableTo(result_2.getFirst(), TypeRef.class);
                T = (TypeRef) result_2.getFirst();
                
                if (((F.getDeclaredType() instanceof TMethod) && ((TMethod) F.getDeclaredType()).isStatic())) {
                  if ((T instanceof ConstructorTypeRef)) {
                    EList<TypeArgument> _typeArgs = ((FunctionTypeExprOrRef)targetTypeRef).getTypeArgs();
                    TypeRef _createTypeRefFromStaticType = RuleEnvironmentExtensions.createTypeRefFromStaticType(((ClassifierTypeRef)T), ((TypeArgument[])Conversions.unwrapArray(_typeArgs, TypeArgument.class)));
                    T = _createTypeRefFromStaticType;
                  } else {
                    if ((T instanceof EnumTypeRef)) {
                      TEnum _enumType = ((EnumTypeRef)T).getEnumType();
                      EList<TypeArgument> _typeArgs_1 = ((FunctionTypeExprOrRef)targetTypeRef).getTypeArgs();
                      TypeRef _ref = TypeExtensions.ref(_enumType, ((TypeArgument[])Conversions.unwrapArray(_typeArgs_1, TypeArgument.class)));
                      T = _ref;
                    }
                  }
                }
              } else {
                this.typeSystemHelper.addSubstitutions(G2, expr, targetTypeRef);
                /* G2 |- F.returnTypeRef ~> T */
                TypeRef _returnTypeRef_3 = F.getReturnTypeRef();
                Result<TypeArgument> result_3 = substTypeVariablesInternal(G2, _trace_, _returnTypeRef_3);
                checkAssignableTo(result_3.getFirst(), TypeRef.class);
                T = (TypeRef) result_3.getFirst();
                
              }
            }
          }
        }
      }
    } else {
      Type _declaredType = null;
      if (targetTypeRef!=null) {
        _declaredType=targetTypeRef.getDeclaredType();
      }
      TObjectPrototype _functionType = RuleEnvironmentExtensions.functionType(G);
      boolean _tripleEquals_1 = (_declaredType == _functionType);
      if (_tripleEquals_1) {
        ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
        T = _anyTypeRef_1;
      } else {
        boolean _isDynamic = targetTypeRef.isDynamic();
        if (_isDynamic) {
          ParameterizedTypeRef _anyTypeRefDynamic = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
          T = _anyTypeRefDynamic;
        } else {
          UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
          T = _createUnknownTypeRef;
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Argument arg) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeArgument(G, _subtrace_, arg);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeArgument") + stringRepForEnv(G) + " |- " + stringRep(arg) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeArgument) {
    	typeThrowException(ruleName("typeArgument") + stringRepForEnv(G) + " |- " + stringRep(arg) + " : " + "TypeRef",
    		TYPEARGUMENT,
    		e_applyRuleTypeArgument, arg, new ErrorInformation[] {new ErrorInformation(arg)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeArgument(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Argument arg) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- arg.expression: T */
    Expression _expression = arg.getExpression();
    Result<TypeRef> result = typeInternal(G, _trace_, _expression);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionDefinition fe) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeFunctionDefinition(G, _subtrace_, fe);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeFunctionDefinition") + stringRepForEnv(G) + " |- " + stringRep(fe) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeFunctionDefinition) {
    	typeThrowException(ruleName("typeFunctionDefinition") + stringRepForEnv(G) + " |- " + stringRep(fe) + " : " + "TypeRef",
    		TYPEFUNCTIONDEFINITION,
    		e_applyRuleTypeFunctionDefinition, fe, new ErrorInformation[] {new ErrorInformation(fe)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeFunctionDefinition(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionDefinition fe) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _elvis = null;
    Type _definedType = fe.getDefinedType();
    TypeRef _ref = null;
    if (_definedType!=null) {
      _ref=TypeExtensions.ref(_definedType);
    }
    if (_ref != null) {
      _elvis = _ref;
    } else {
      UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
      _elvis = _createUnknownTypeRef;
    }
    T = _elvis;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PostfixExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypePostfixExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typePostfixExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypePostfixExpression) {
    	typeThrowException(ruleName("typePostfixExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPEPOSTFIXEXPRESSION,
    		e_applyRuleTypePostfixExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypePostfixExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PostfixExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypePostfixExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypePostfixExpression_1(final RuleEnvironment G, final PostfixExpression e) throws RuleFailedException {
    ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
    return _numberTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnaryExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeUnaryExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeUnaryExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeUnaryExpression) {
    	typeThrowException(ruleName("typeUnaryExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEUNARYEXPRESSION,
    		e_applyRuleTypeUnaryExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeUnaryExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnaryExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    if ((((e.getOp() == UnaryOperator.NEG) || (e.getOp() == UnaryOperator.POS)) && (e.getExpression() instanceof IntLiteral))) {
      /* G |- e.expression : T */
      Expression _expression = e.getExpression();
      Result<TypeRef> result = typeInternal(G, _trace_, _expression);
      checkAssignableTo(result.getFirst(), TypeRef.class);
      T = (TypeRef) result.getFirst();
      
    } else {
      UnaryOperator _op = e.getOp();
      if (_op != null) {
        switch (_op) {
          case DELETE:
            ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
            T = _booleanTypeRef;
            break;
          case VOID:
            ParameterizedTypeRef _undefinedTypeRef = RuleEnvironmentExtensions.undefinedTypeRef(G);
            T = _undefinedTypeRef;
            break;
          case TYPEOF:
            ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
            T = _stringTypeRef;
            break;
          case NOT:
            ParameterizedTypeRef _booleanTypeRef_1 = RuleEnvironmentExtensions.booleanTypeRef(G);
            T = _booleanTypeRef_1;
            break;
          default:
            ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
            T = _numberTypeRef;
            break;
        }
      } else {
        ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
        T = _numberTypeRef;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiplicativeExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeMultiplicativeExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeMultiplicativeExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeMultiplicativeExpression) {
    	typeThrowException(ruleName("typeMultiplicativeExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPEMULTIPLICATIVEEXPRESSION,
    		e_applyRuleTypeMultiplicativeExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeMultiplicativeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiplicativeExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeMultiplicativeExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypeMultiplicativeExpression_1(final RuleEnvironment G, final MultiplicativeExpression e) throws RuleFailedException {
    ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
    return _numberTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AdditiveExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeAdditiveExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeAdditiveExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeAdditiveExpression) {
    	typeThrowException(ruleName("typeAdditiveExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPEADDITIVEEXPRESSION,
    		e_applyRuleTypeAdditiveExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeAdditiveExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AdditiveExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    AdditiveOperator _op = expr.getOp();
    boolean _equals = Objects.equal(_op, AdditiveOperator.ADD);
    if (_equals) {
      /* G |- expr.lhs: var TypeRef l */
      Expression _lhs = expr.getLhs();
      TypeRef l = null;
      Result<TypeRef> result = typeInternal(G, _trace_, _lhs);
      checkAssignableTo(result.getFirst(), TypeRef.class);
      l = (TypeRef) result.getFirst();
      
      /* G |- expr.rhs: var TypeRef r */
      Expression _rhs = expr.getRhs();
      TypeRef r = null;
      Result<TypeRef> result_1 = typeInternal(G, _trace_, _rhs);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      r = (TypeRef) result_1.getFirst();
      
      final boolean lunknown = (l instanceof UnknownTypeRef);
      final boolean runknown = (r instanceof UnknownTypeRef);
      if ((lunknown && runknown)) {
        T = r;
      } else {
        final boolean lnum = (Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.booleanType(G)) || RuleEnvironmentExtensions.isNumeric(G, l.getDeclaredType()));
        final boolean rnum = (Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.booleanType(G)) || RuleEnvironmentExtensions.isNumeric(G, r.getDeclaredType()));
        final boolean undef = (((Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.undefinedType(G)) || Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.nullType(G))) || Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.undefinedType(G))) || Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.nullType(G)));
        if (((lnum && rnum) || (undef && (lnum || rnum)))) {
          ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
          T = _numberTypeRef;
        } else {
          if (((lunknown || runknown) && ((lnum || rnum) || undef))) {
            TypeRef _xifexpression = null;
            if (lunknown) {
              _xifexpression = l;
            } else {
              _xifexpression = r;
            }
            T = _xifexpression;
          } else {
            ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
            T = _stringTypeRef;
          }
        }
      }
    } else {
      ParameterizedTypeRef _numberTypeRef_1 = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef_1;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ShiftExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeShiftExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeShiftExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeShiftExpression) {
    	typeThrowException(ruleName("typeShiftExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPESHIFTEXPRESSION,
    		e_applyRuleTypeShiftExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeShiftExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ShiftExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeShiftExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypeShiftExpression_1(final RuleEnvironment G, final ShiftExpression e) throws RuleFailedException {
    ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
    return _numberTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RelationalExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeRelationalExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeRelationalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeRelationalExpression) {
    	typeThrowException(ruleName("typeRelationalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPERELATIONALEXPRESSION,
    		e_applyRuleTypeRelationalExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeRelationalExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RelationalExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeRelationalExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypeRelationalExpression_1(final RuleEnvironment G, final RelationalExpression e) throws RuleFailedException {
    ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
    return _booleanTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeEqualityExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeEqualityExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeEqualityExpression) {
    	typeThrowException(ruleName("typeEqualityExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPEEQUALITYEXPRESSION,
    		e_applyRuleTypeEqualityExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeEqualityExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeEqualityExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypeEqualityExpression_1(final RuleEnvironment G, final EqualityExpression e) throws RuleFailedException {
    ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
    return _booleanTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryBitwiseExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeBinaryBitwiseExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeBinaryBitwiseExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeBinaryBitwiseExpression) {
    	typeThrowException(ruleName("typeBinaryBitwiseExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "ParameterizedTypeRef",
    		TYPEBINARYBITWISEEXPRESSION,
    		e_applyRuleTypeBinaryBitwiseExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeBinaryBitwiseExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryBitwiseExpression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeBinaryBitwiseExpression_1(G, e));
  }
  
  private ParameterizedTypeRef _applyRuleTypeBinaryBitwiseExpression_1(final RuleEnvironment G, final BinaryBitwiseExpression e) throws RuleFailedException {
    ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
    return _numberTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryLogicalExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeBinaryLogicalExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeBinaryLogicalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeBinaryLogicalExpression) {
    	typeThrowException(ruleName("typeBinaryLogicalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPEBINARYLOGICALEXPRESSION,
    		e_applyRuleTypeBinaryLogicalExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeBinaryLogicalExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryLogicalExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final Expression lhs = e.getLhs();
    final Expression rhs = e.getRhs();
    boolean _xifexpression = false;
    if ((lhs instanceof ArrayLiteral)) {
      EList<ArrayElement> _elements = ((ArrayLiteral)lhs).getElements();
      _xifexpression = _elements.isEmpty();
    }
    final boolean lhsIsEmptyArrayLiteral = _xifexpression;
    boolean _xifexpression_1 = false;
    if ((rhs instanceof ArrayLiteral)) {
      EList<ArrayElement> _elements_1 = ((ArrayLiteral)rhs).getElements();
      _xifexpression_1 = _elements_1.isEmpty();
    }
    final boolean rhsIsEmptyArrayLiteral = _xifexpression_1;
    /* G |- e.lhs : var TypeRef L */
    Expression _lhs = e.getLhs();
    TypeRef L = null;
    Result<TypeRef> result = typeInternal(G, _trace_, _lhs);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    L = (TypeRef) result.getFirst();
    
    /* G |- e.rhs : var TypeRef R */
    Expression _rhs = e.getRhs();
    TypeRef R = null;
    Result<TypeRef> result_1 = typeInternal(G, _trace_, _rhs);
    checkAssignableTo(result_1.getFirst(), TypeRef.class);
    R = (TypeRef) result_1.getFirst();
    
    boolean _and = false;
    boolean _lhsIsEmptyArrayLiteral = lhsIsEmptyArrayLiteral;
    if (!_lhsIsEmptyArrayLiteral) {
      _and = false;
    } else {
      Type _declaredType = null;
      if (R!=null) {
        _declaredType=R.getDeclaredType();
      }
      TObjectPrototype _arrayType = RuleEnvironmentExtensions.arrayType(G);
      boolean _tripleEquals = (_declaredType == _arrayType);
      _and = _tripleEquals;
    }
    if (_and) {
      T = R;
    } else {
      boolean _and_1 = false;
      boolean _rhsIsEmptyArrayLiteral = rhsIsEmptyArrayLiteral;
      if (!_rhsIsEmptyArrayLiteral) {
        _and_1 = false;
      } else {
        Type _declaredType_1 = null;
        if (L!=null) {
          _declaredType_1=L.getDeclaredType();
        }
        TObjectPrototype _arrayType_1 = RuleEnvironmentExtensions.arrayType(G);
        boolean _tripleEquals_1 = (_declaredType_1 == _arrayType_1);
        _and_1 = _tripleEquals_1;
      }
      if (_and_1) {
        T = L;
      } else {
        TypeRef _createUnionType = this.typeSystemHelper.createUnionType(G, L, R);
        T = _createUnionType;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConditionalExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeConditionalExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeConditionalExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeConditionalExpression) {
    	typeThrowException(ruleName("typeConditionalExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPECONDITIONALEXPRESSION,
    		e_applyRuleTypeConditionalExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeConditionalExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConditionalExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- expr.trueExpression : var TypeRef left */
    Expression _trueExpression = expr.getTrueExpression();
    TypeRef left = null;
    Result<TypeRef> result = typeInternal(G, _trace_, _trueExpression);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    left = (TypeRef) result.getFirst();
    
    /* G |- expr.falseExpression : var TypeRef right */
    Expression _falseExpression = expr.getFalseExpression();
    TypeRef right = null;
    Result<TypeRef> result_1 = typeInternal(G, _trace_, _falseExpression);
    checkAssignableTo(result_1.getFirst(), TypeRef.class);
    right = (TypeRef) result_1.getFirst();
    
    TypeRef _createUnionType = this.typeSystemHelper.createUnionType(G, left, right);
    T = _createUnionType;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AssignmentExpression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeAssignmentExpression(G, _subtrace_, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeAssignmentExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeAssignmentExpression) {
    	typeThrowException(ruleName("typeAssignmentExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " : " + "TypeRef",
    		TYPEASSIGNMENTEXPRESSION,
    		e_applyRuleTypeAssignmentExpression, expr, new ErrorInformation[] {new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeAssignmentExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AssignmentExpression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* { expr.op===AssignmentOperator.ASSIGN; G |- expr.rhs: T } or { expr.op===AssignmentOperator.ADD_ASSIGN G |- expr.lhs: var ParameterizedTypeRef l G |- expr.rhs: var ParameterizedTypeRef r val lnum = l.declaredType == G.booleanType || G.isNumeric(l.declaredType); val rnum = r.declaredType == G.booleanType || G.isNumeric(r.declaredType); val undef = l.declaredType == G.undefinedType || l.declaredType == G.nullType || r.declaredType == G.undefinedType || r.declaredType == G.nullType; !(lnum && rnum); !(undef && (lnum || rnum)); T = G.stringTypeRef } or { T = G.numberTypeRef } */
    {
      RuleFailedException previousFailure = null;
      try {
        AssignmentOperator _op = expr.getOp();
        boolean _tripleEquals = (_op == AssignmentOperator.ASSIGN);
        /* expr.op===AssignmentOperator.ASSIGN */
        if (!_tripleEquals) {
          sneakyThrowRuleFailedException("expr.op===AssignmentOperator.ASSIGN");
        }
        /* G |- expr.rhs: T */
        Expression _rhs = expr.getRhs();
        Result<TypeRef> result = typeInternal(G, _trace_, _rhs);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        T = (TypeRef) result.getFirst();
        
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* { expr.op===AssignmentOperator.ADD_ASSIGN G |- expr.lhs: var ParameterizedTypeRef l G |- expr.rhs: var ParameterizedTypeRef r val lnum = l.declaredType == G.booleanType || G.isNumeric(l.declaredType); val rnum = r.declaredType == G.booleanType || G.isNumeric(r.declaredType); val undef = l.declaredType == G.undefinedType || l.declaredType == G.nullType || r.declaredType == G.undefinedType || r.declaredType == G.nullType; !(lnum && rnum); !(undef && (lnum || rnum)); T = G.stringTypeRef } or { T = G.numberTypeRef } */
        {
          try {
            AssignmentOperator _op_1 = expr.getOp();
            boolean _tripleEquals_1 = (_op_1 == AssignmentOperator.ADD_ASSIGN);
            /* expr.op===AssignmentOperator.ADD_ASSIGN */
            if (!_tripleEquals_1) {
              sneakyThrowRuleFailedException("expr.op===AssignmentOperator.ADD_ASSIGN");
            }
            /* G |- expr.lhs: var ParameterizedTypeRef l */
            Expression _lhs = expr.getLhs();
            ParameterizedTypeRef l = null;
            Result<TypeRef> result_1 = typeInternal(G, _trace_, _lhs);
            checkAssignableTo(result_1.getFirst(), ParameterizedTypeRef.class);
            l = (ParameterizedTypeRef) result_1.getFirst();
            
            /* G |- expr.rhs: var ParameterizedTypeRef r */
            Expression _rhs_1 = expr.getRhs();
            ParameterizedTypeRef r = null;
            Result<TypeRef> result_2 = typeInternal(G, _trace_, _rhs_1);
            checkAssignableTo(result_2.getFirst(), ParameterizedTypeRef.class);
            r = (ParameterizedTypeRef) result_2.getFirst();
            
            final boolean lnum = (Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.booleanType(G)) || RuleEnvironmentExtensions.isNumeric(G, l.getDeclaredType()));
            final boolean rnum = (Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.booleanType(G)) || RuleEnvironmentExtensions.isNumeric(G, r.getDeclaredType()));
            final boolean undef = (((Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.undefinedType(G)) || Objects.equal(l.getDeclaredType(), RuleEnvironmentExtensions.nullType(G))) || Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.undefinedType(G))) || Objects.equal(r.getDeclaredType(), RuleEnvironmentExtensions.nullType(G)));
            /* !(lnum && rnum) */
            if (!(!(lnum && rnum))) {
              sneakyThrowRuleFailedException("!(lnum && rnum)");
            }
            /* !(undef && (lnum || rnum)) */
            if (!(!(undef && (lnum || rnum)))) {
              sneakyThrowRuleFailedException("!(undef && (lnum || rnum))");
            }
            ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
            T = _stringTypeRef;
          } catch (Exception e_1) {
            previousFailure = extractRuleFailedException(e_1);
            ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
            T = _numberTypeRef;
          }
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CommaExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeCommaExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeCommaExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeCommaExpression) {
    	typeThrowException(ruleName("typeCommaExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPECOMMAEXPRESSION,
    		e_applyRuleTypeCommaExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeCommaExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CommaExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |- e.exprs.last: T */
    EList<Expression> _exprs = e.getExprs();
    Expression _last = IterableExtensions.<Expression>last(_exprs);
    Result<TypeRef> result = typeInternal(G, _trace_, _last);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CastExpression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeCastExpression(G, _subtrace_, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeCastExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeCastExpression) {
    	typeThrowException(ruleName("typeCastExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " : " + "TypeRef",
    		TYPECASTEXPRESSION,
    		e_applyRuleTypeCastExpression, e, new ErrorInformation[] {new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeCastExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CastExpression e) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _targetTypeRef = e.getTargetTypeRef();
    T = _targetTypeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableDeclaration vdecl) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeVariableDeclaration(G, _subtrace_, vdecl);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeVariableDeclaration") + stringRepForEnv(G) + " |- " + stringRep(vdecl) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeVariableDeclaration) {
    	typeThrowException(ruleName("typeVariableDeclaration") + stringRepForEnv(G) + " |- " + stringRep(vdecl) + " : " + "TypeRef",
    		TYPEVARIABLEDECLARATION,
    		e_applyRuleTypeVariableDeclaration, vdecl, new ErrorInformation[] {new ErrorInformation(vdecl)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeVariableDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableDeclaration vdecl) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = vdecl.getDeclaredTypeRef();
    boolean _tripleNotEquals = (_declaredTypeRef != null);
    if (_tripleNotEquals) {
      TypeRef _declaredTypeRef_1 = vdecl.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      boolean _hasAnnotation = AnnotationDefinition.UNDEFINED.hasAnnotation(vdecl);
      if (_hasAnnotation) {
        ParameterizedTypeRef _undefinedTypeRef = RuleEnvironmentExtensions.undefinedTypeRef(G);
        T = _undefinedTypeRef;
      } else {
        EObject _eContainer = vdecl.eContainer();
        if ((_eContainer instanceof BindingElement)) {
          Expression _expression = vdecl.getExpression();
          Pair<String, Expression> _mappedTo = Pair.<String, Expression>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _expression);
          Object _get = G.get(_mappedTo);
          boolean _tripleEquals = (_get == null);
          if (_tripleEquals) {
            final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
            Expression _expression_1 = vdecl.getExpression();
            Pair<String, Expression> _mappedTo_1 = Pair.<String, Expression>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _expression_1);
            boolean _add = G2.add(_mappedTo_1, Boolean.TRUE);
            /* G2.add(GUARD_VARIABLE_DECLARATION->vdecl.expression,Boolean.TRUE) */
            if (!_add) {
              sneakyThrowRuleFailedException("G2.add(GUARD_VARIABLE_DECLARATION->vdecl.expression,Boolean.TRUE)");
            }
            TypeRef _elvis = null;
            TypeRef _typeOfVariableDeclarationInDestructuringPattern = this.destructureHelper.getTypeOfVariableDeclarationInDestructuringPattern(G2, vdecl);
            if (_typeOfVariableDeclarationInDestructuringPattern != null) {
              _elvis = _typeOfVariableDeclarationInDestructuringPattern;
            } else {
              ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
              _elvis = _anyTypeRef;
            }
            T = _elvis;
          } else {
            ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
            T = _anyTypeRef_1;
          }
        } else {
          if (((vdecl.eContainer() instanceof ForStatement) && ((ForStatement) vdecl.eContainer()).isForOf())) {
            EObject _eContainer_1 = vdecl.eContainer();
            final ForStatement forOfStmnt = ((ForStatement) _eContainer_1);
            EObject _eContainer_2 = vdecl.eContainer();
            Pair<String, EObject> _mappedTo_2 = Pair.<String, EObject>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _eContainer_2);
            Object _get_1 = G.get(_mappedTo_2);
            boolean _tripleEquals_1 = (_get_1 == null);
            if (_tripleEquals_1) {
              final RuleEnvironment G2_1 = RuleEnvironmentExtensions.wrap(G);
              EObject _eContainer_3 = vdecl.eContainer();
              Pair<String, EObject> _mappedTo_3 = Pair.<String, EObject>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _eContainer_3);
              boolean _add_1 = G2_1.add(_mappedTo_3, Boolean.TRUE);
              /* G2.add(GUARD_VARIABLE_DECLARATION->vdecl.eContainer,Boolean.TRUE) */
              if (!_add_1) {
                sneakyThrowRuleFailedException("G2.add(GUARD_VARIABLE_DECLARATION->vdecl.eContainer,Boolean.TRUE)");
              }
              /* { G2 |- forOfStmnt.expression : var TypeRef ofPartTypeRef val elemType = destructureHelper.extractIterableElementType(G2, ofPartTypeRef) elemType!==null G2 |~ elemType /\ T } or { T = TypeRefsFactory.eINSTANCE.createUnknownTypeRef } */
              {
                RuleFailedException previousFailure = null;
                try {
                  /* G2 |- forOfStmnt.expression : var TypeRef ofPartTypeRef */
                  Expression _expression_2 = forOfStmnt.getExpression();
                  TypeRef ofPartTypeRef = null;
                  Result<TypeRef> result = typeInternal(G2_1, _trace_, _expression_2);
                  checkAssignableTo(result.getFirst(), TypeRef.class);
                  ofPartTypeRef = (TypeRef) result.getFirst();
                  
                  final TypeArgument elemType = this.destructureHelper.extractIterableElementType(G2_1, ofPartTypeRef);
                  /* elemType!==null */
                  if (!(elemType != null)) {
                    sneakyThrowRuleFailedException("elemType!==null");
                  }
                  /* G2 |~ elemType /\ T */
                  Result<TypeRef> result_1 = upperBoundInternal(G2_1, _trace_, elemType);
                  checkAssignableTo(result_1.getFirst(), TypeRef.class);
                  T = (TypeRef) result_1.getFirst();
                  
                } catch (Exception e) {
                  previousFailure = extractRuleFailedException(e);
                  UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
                  T = _createUnknownTypeRef;
                }
              }
            } else {
              ParameterizedTypeRef _anyTypeRef_2 = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef_2;
            }
          } else {
            if (((vdecl.eContainer() instanceof ForStatement) && ((ForStatement) vdecl.eContainer()).isForIn())) {
              ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
              T = _stringTypeRef;
            } else {
              Expression _expression_3 = vdecl.getExpression();
              boolean _tripleNotEquals_1 = (_expression_3 != null);
              if (_tripleNotEquals_1) {
                Expression _expression_4 = vdecl.getExpression();
                Pair<String, Expression> _mappedTo_4 = Pair.<String, Expression>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _expression_4);
                Object _get_2 = G.get(_mappedTo_4);
                boolean _tripleEquals_2 = (_get_2 == null);
                if (_tripleEquals_2) {
                  final RuleEnvironment G2_2 = RuleEnvironmentExtensions.wrap(G);
                  Expression _expression_5 = vdecl.getExpression();
                  Pair<String, Expression> _mappedTo_5 = Pair.<String, Expression>of(RuleEnvironmentExtensions.GUARD_VARIABLE_DECLARATION, _expression_5);
                  boolean _add_2 = G2_2.add(_mappedTo_5, Boolean.TRUE);
                  /* G2.add(GUARD_VARIABLE_DECLARATION->vdecl.expression,Boolean.TRUE) */
                  if (!_add_2) {
                    sneakyThrowRuleFailedException("G2.add(GUARD_VARIABLE_DECLARATION->vdecl.expression,Boolean.TRUE)");
                  }
                  /* G2 |- vdecl.expression: var TypeRef E */
                  Expression _expression_6 = vdecl.getExpression();
                  TypeRef E = null;
                  Result<TypeRef> result_2 = typeInternal(G2_2, _trace_, _expression_6);
                  checkAssignableTo(result_2.getFirst(), TypeRef.class);
                  E = (TypeRef) result_2.getFirst();
                  
                  if (((E instanceof BoundThisTypeRef) || ((E instanceof ClassifierTypeRef) && (((ClassifierTypeRef) E).getTypeArg() instanceof BoundThisTypeRef)))) {
                  } else {
                    /* G2 |~ E /\ E */
                    Result<TypeRef> result_3 = upperBoundInternal(G2_2, _trace_, E);
                    checkAssignableTo(result_3.getFirst(), TypeRef.class);
                    E = (TypeRef) result_3.getFirst();
                    
                  }
                  if ((((E.getDeclaredType() == RuleEnvironmentExtensions.undefinedType(G)) || (E.getDeclaredType() == RuleEnvironmentExtensions.nullType(G))) || (E.getDeclaredType() == RuleEnvironmentExtensions.voidType(G)))) {
                    ParameterizedTypeRef _anyTypeRef_3 = RuleEnvironmentExtensions.anyTypeRef(G);
                    T = _anyTypeRef_3;
                  } else {
                    T = E;
                  }
                } else {
                  ParameterizedTypeRef _anyTypeRef_4 = RuleEnvironmentExtensions.anyTypeRef(G);
                  T = _anyTypeRef_4;
                }
              } else {
                ParameterizedTypeRef _anyTypeRef_5 = RuleEnvironmentExtensions.anyTypeRef(G);
                T = _anyTypeRef_5;
              }
            }
          }
        }
      }
    }
    final JavaScriptVariant jsVariant = JavaScriptVariant.getVariant(vdecl);
    boolean _isECMAScript = jsVariant.isECMAScript();
    if (_isECMAScript) {
      TypeRef _makeDynamic = this.typeSystemHelper.makeDynamic(T);
      T = _makeDynamic;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FormalParameter fpar) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeFormalParameter(G, _subtrace_, fpar);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeFormalParameter") + stringRepForEnv(G) + " |- " + stringRep(fpar) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeFormalParameter) {
    	typeThrowException(ruleName("typeFormalParameter") + stringRepForEnv(G) + " |- " + stringRep(fpar) + " : " + "TypeRef",
    		TYPEFORMALPARAMETER,
    		e_applyRuleTypeFormalParameter, fpar, new ErrorInformation[] {new ErrorInformation(fpar)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeFormalParameter(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FormalParameter fpar) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final TypeRef fparTypeRef = fpar.getDeclaredTypeRef();
    if ((fparTypeRef != null)) {
      if (((fparTypeRef instanceof ThisTypeRefStructural) || ((fparTypeRef instanceof FunctionTypeExpression) && IteratorExtensions.<TFormalParameter>exists(Iterators.<TFormalParameter>filter(((FunctionTypeExpression) fparTypeRef).eAllContents(), TFormalParameter.class), ((Function1<TFormalParameter, Boolean>) (TFormalParameter currFpar) -> {
        TypeRef _typeRef = currFpar.getTypeRef();
        return Boolean.valueOf((_typeRef instanceof ThisTypeRef));
      }))))) {
        TypeRef _bindAndSubstituteThisTypeRef = this.typeSystemHelper.bindAndSubstituteThisTypeRef(G, fparTypeRef, fparTypeRef);
        T = _bindAndSubstituteThisTypeRef;
      } else {
        TypeRef _xifexpression = null;
        TFormalParameter _definedTypeElement = null;
        if (fpar!=null) {
          _definedTypeElement=fpar.getDefinedTypeElement();
        }
        TypeRef _typeRef = null;
        if (_definedTypeElement!=null) {
          _typeRef=_definedTypeElement.getTypeRef();
        }
        boolean _tripleNotEquals = (_typeRef != null);
        if (_tripleNotEquals) {
          TFormalParameter _definedTypeElement_1 = fpar.getDefinedTypeElement();
          _xifexpression = _definedTypeElement_1.getTypeRef();
        } else {
          _xifexpression = fpar.getDeclaredTypeRef();
        }
        T = _xifexpression;
      }
    } else {
      TFormalParameter _definedTypeElement_2 = null;
      if (fpar!=null) {
        _definedTypeElement_2=fpar.getDefinedTypeElement();
      }
      TypeRef _typeRef_1 = null;
      if (_definedTypeElement_2!=null) {
        _typeRef_1=_definedTypeElement_2.getTypeRef();
      }
      boolean _tripleNotEquals_1 = (_typeRef_1 != null);
      if (_tripleNotEquals_1) {
        TFormalParameter _definedTypeElement_3 = fpar.getDefinedTypeElement();
        TypeRef _typeRef_2 = _definedTypeElement_3.getTypeRef();
        T = _typeRef_2;
      } else {
        JavaScriptVariant _variant = JavaScriptVariant.getVariant(fpar);
        boolean _tripleEquals = (_variant == JavaScriptVariant.n4js);
        if (_tripleEquals) {
          /* T = env(G, fpar, TypeRef) or T = G.anyTypeRef */
          {
            RuleFailedException previousFailure = null;
            try {
              TypeRef _env = this.<TypeRef>env(G, fpar, TypeRef.class);
              T = _env;
            } catch (Exception e) {
              previousFailure = extractRuleFailedException(e);
              ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef;
            }
          }
        } else {
          ParameterizedTypeRef _anyTypeRefDynamic = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
          T = _anyTypeRefDynamic;
        }
      }
    }
    if ((T != null)) {
      TypeRef _xifexpression_1 = null;
      boolean _isVariadic = fpar.isVariadic();
      if (_isVariadic) {
        ParameterizedTypeRef _xblockexpression = null;
        {
          final TypeRef finalT = T;
          TObjectPrototype _arrayType = RuleEnvironmentExtensions.arrayType(G);
          ParameterizedTypeRef _createTypeRef = null;
          if (_arrayType!=null) {
            _createTypeRef=TypeUtils.createTypeRef(_arrayType, finalT);
          }
          _xblockexpression = (_createTypeRef);
        }
        _xifexpression_1 = _xblockexpression;
      } else {
        _xifexpression_1 = T;
      }
      T = _xifexpression_1;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CatchVariable catchVariable) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeCatchVariable(G, _subtrace_, catchVariable);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeCatchVariable") + stringRepForEnv(G) + " |- " + stringRep(catchVariable) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeCatchVariable) {
    	typeThrowException(ruleName("typeCatchVariable") + stringRepForEnv(G) + " |- " + stringRep(catchVariable) + " : " + "ParameterizedTypeRef",
    		TYPECATCHVARIABLE,
    		e_applyRuleTypeCatchVariable, catchVariable, new ErrorInformation[] {new ErrorInformation(catchVariable)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeCatchVariable(final RuleEnvironment G, final RuleApplicationTrace _trace_, final CatchVariable catchVariable) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeCatchVariable_1(G, catchVariable));
  }
  
  private ParameterizedTypeRef _applyRuleTypeCatchVariable_1(final RuleEnvironment G, final CatchVariable catchVariable) throws RuleFailedException {
    ParameterizedTypeRef _xifexpression = null;
    JavaScriptVariant _variant = JavaScriptVariant.getVariant(catchVariable);
    boolean _tripleEquals = (_variant == JavaScriptVariant.n4js);
    if (_tripleEquals) {
      _xifexpression = RuleEnvironmentExtensions.anyTypeRef(G);
    } else {
      _xifexpression = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
    }
    return _xifexpression;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final LocalArgumentsVariable lArgumentsVar) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeLocalArgumentsVariable(G, _subtrace_, lArgumentsVar);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeLocalArgumentsVariable") + stringRepForEnv(G) + " |- " + stringRep(lArgumentsVar) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeLocalArgumentsVariable) {
    	typeThrowException(ruleName("typeLocalArgumentsVariable") + stringRepForEnv(G) + " |- " + stringRep(lArgumentsVar) + " : " + "ParameterizedTypeRef",
    		TYPELOCALARGUMENTSVARIABLE,
    		e_applyRuleTypeLocalArgumentsVariable, lArgumentsVar, new ErrorInformation[] {new ErrorInformation(lArgumentsVar)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeLocalArgumentsVariable(final RuleEnvironment G, final RuleApplicationTrace _trace_, final LocalArgumentsVariable lArgumentsVar) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleTypeLocalArgumentsVariable_1(G, lArgumentsVar));
  }
  
  private ParameterizedTypeRef _applyRuleTypeLocalArgumentsVariable_1(final RuleEnvironment G, final LocalArgumentsVariable lArgumentsVar) throws RuleFailedException {
    ParameterizedTypeRef _argumentsTypeRef = RuleEnvironmentExtensions.argumentsTypeRef(G);
    return _argumentsTypeRef;
  }
  
  protected Result<TypeRef> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ModuleNamespaceVirtualType t) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleTypeModuleNamespace(G, _subtrace_, t);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("typeModuleNamespace") + stringRepForEnv(G) + " |- " + stringRep(t) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleTypeModuleNamespace) {
    	typeThrowException(ruleName("typeModuleNamespace") + stringRepForEnv(G) + " |- " + stringRep(t) + " : " + "TypeRef",
    		TYPEMODULENAMESPACE,
    		e_applyRuleTypeModuleNamespace, t, new ErrorInformation[] {new ErrorInformation(t)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleTypeModuleNamespace(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ModuleNamespaceVirtualType t) throws RuleFailedException {
    TypeRef T = null; // output parameter
    ParameterizedTypeRef _createTypeRef = TypeUtils.createTypeRef(t);
    T = _createTypeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeTypeArgument(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeTypeArgument") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeTypeArgument) {
    	subtypeThrowException(ruleName("subtypeTypeArgument") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPETYPEARGUMENT,
    		e_applyRuleSubtypeTypeArgument, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeTypeArgument(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeArgument left, final TypeArgument right) throws RuleFailedException {
    /* G |~ left /\ var TypeRef leftUpper */
    TypeRef leftUpper = null;
    Result<TypeRef> result = upperBoundInternal(G, _trace_, left);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    leftUpper = (TypeRef) result.getFirst();
    
    /* G |~ right \/ var TypeRef rightLower */
    TypeRef rightLower = null;
    Result<TypeRef> result_1 = lowerBoundInternal(G, _trace_, right);
    checkAssignableTo(result_1.getFirst(), TypeRef.class);
    rightLower = (TypeRef) result_1.getFirst();
    
    if (((leftUpper == left) && (rightLower == right))) {
      /* fail */
      throwForExplicitFail();
      /* "Prevent endless loop, check rules, see subtypeTypeArgument" */
    } else {
      /* G |- leftUpper <: rightLower */
      subtypeInternal(G, _trace_, leftUpper, rightLower);
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeTypeRef) {
    	subtypeThrowException(ruleName("subtypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPETYPEREF,
    		e_applyRuleSubtypeTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    /* right.declaredType instanceof AnyType || left.declaredType instanceof NullType || left.declaredType instanceof UndefinedType */
    if (!(((right.getDeclaredType() instanceof AnyType) || (left.getDeclaredType() instanceof NullType)) || (left.getDeclaredType() instanceof UndefinedType))) {
      sneakyThrowRuleFailedException("right.declaredType instanceof AnyType || left.declaredType instanceof NullType || left.declaredType instanceof UndefinedType");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnknownTypeRef left, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeUnknownTypeRef_Left(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeUnknownTypeRef_Left") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeUnknownTypeRef_Left) {
    	subtypeThrowException(ruleName("subtypeUnknownTypeRef_Left") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEUNKNOWNTYPEREF_LEFT,
    		e_applyRuleSubtypeUnknownTypeRef_Left, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeUnknownTypeRef_Left(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnknownTypeRef left, final TypeRef right) throws RuleFailedException {
    /* true */
    if (!true) {
      sneakyThrowRuleFailedException("true");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final UnknownTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeUnknownTypeRef_Right(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeUnknownTypeRef_Right") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeUnknownTypeRef_Right) {
    	subtypeThrowException(ruleName("subtypeUnknownTypeRef_Right") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEUNKNOWNTYPEREF_RIGHT,
    		e_applyRuleSubtypeUnknownTypeRef_Right, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeUnknownTypeRef_Right(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final UnknownTypeRef right) throws RuleFailedException {
    /* true */
    if (!true) {
      sneakyThrowRuleFailedException("true");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EnumTypeRef etr, final ClassifierTypeRef ctr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeEnumTypeRefN4Enum(G, _subtrace_, etr, ctr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeEnumTypeRefN4Enum") + stringRepForEnv(G) + " |- " + stringRep(etr) + " <: " + stringRep(ctr);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeEnumTypeRefN4Enum) {
    	subtypeThrowException(ruleName("subtypeEnumTypeRefN4Enum") + stringRepForEnv(G) + " |- " + stringRep(etr) + " <: " + stringRep(ctr),
    		SUBTYPEENUMTYPEREFN4ENUM,
    		e_applyRuleSubtypeEnumTypeRefN4Enum, etr, ctr, new ErrorInformation[] {new ErrorInformation(etr), new ErrorInformation(ctr)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeEnumTypeRefN4Enum(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EnumTypeRef etr, final ClassifierTypeRef ctr) throws RuleFailedException {
    final TypeArgument typeRef = ctr.getTypeArg();
    if ((typeRef instanceof ParameterizedTypeRef)) {
      /* typeRef.declaredType === G.n4EnumType && ! TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === G.n4StringBasedEnumType && TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === G.stringType && TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === etr.enumType */
      if (!(((((((ParameterizedTypeRef)typeRef).getDeclaredType() == RuleEnvironmentExtensions.n4EnumType(G)) && (!TypeSystemHelper.isStringBasedEnumeration(etr.getEnumType()))) || 
        ((((ParameterizedTypeRef)typeRef).getDeclaredType() == RuleEnvironmentExtensions.n4StringBasedEnumType(G)) && TypeSystemHelper.isStringBasedEnumeration(etr.getEnumType()))) || 
        ((((ParameterizedTypeRef)typeRef).getDeclaredType() == RuleEnvironmentExtensions.stringType(G)) && TypeSystemHelper.isStringBasedEnumeration(etr.getEnumType()))) || 
        (((ParameterizedTypeRef)typeRef).getDeclaredType() == etr.getEnumType()))) {
        sneakyThrowRuleFailedException("typeRef.declaredType === G.n4EnumType && ! TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === G.n4StringBasedEnumType && TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === G.stringType && TypeSystemHelper.isStringBasedEnumeration(etr.enumType) || typeRef.declaredType === etr.enumType");
      }
    } else {
      /* false */
      if (!false) {
        sneakyThrowRuleFailedException("false");
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EnumTypeRef etr, final EnumTypeRef etr2) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeEnumTypeRef(G, _subtrace_, etr, etr2);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeEnumTypeRef") + stringRepForEnv(G) + " |- " + stringRep(etr) + " <: " + stringRep(etr2);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeEnumTypeRef) {
    	subtypeThrowException(ruleName("subtypeEnumTypeRef") + stringRepForEnv(G) + " |- " + stringRep(etr) + " <: " + stringRep(etr2),
    		SUBTYPEENUMTYPEREF,
    		e_applyRuleSubtypeEnumTypeRef, etr, etr2, new ErrorInformation[] {new ErrorInformation(etr), new ErrorInformation(etr2)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeEnumTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EnumTypeRef etr, final EnumTypeRef etr2) throws RuleFailedException {
    TEnum _enumType = etr.getEnumType();
    TEnum _replacement = RuleEnvironmentExtensions.<TEnum>getReplacement(G, _enumType);
    TEnum _enumType_1 = etr2.getEnumType();
    TEnum _replacement_1 = RuleEnvironmentExtensions.<TEnum>getReplacement(G, _enumType_1);
    /* G.getReplacement(etr.enumType) === G.getReplacement(etr2.enumType) */
    if (!(_replacement == _replacement_1)) {
      sneakyThrowRuleFailedException("G.getReplacement(etr.enumType) === G.getReplacement(etr2.enumType)");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef leftOriginal, final ParameterizedTypeRef rightOriginal) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeParameterizedTypeRef(G, _subtrace_, leftOriginal, rightOriginal);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(leftOriginal) + " <: " + stringRep(rightOriginal);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeParameterizedTypeRef) {
    	subtypeThrowException(ruleName("subtypeParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(leftOriginal) + " <: " + stringRep(rightOriginal),
    		SUBTYPEPARAMETERIZEDTYPEREF,
    		e_applyRuleSubtypeParameterizedTypeRef, leftOriginal, rightOriginal, new ErrorInformation[] {new ErrorInformation(leftOriginal), new ErrorInformation(rightOriginal)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef leftOriginal, final ParameterizedTypeRef rightOriginal) throws RuleFailedException {
    final TypeRef left = RuleEnvironmentExtensions.getReplacement(G, leftOriginal);
    final TypeRef right = RuleEnvironmentExtensions.getReplacement(G, rightOriginal);
    final Type leftDeclType = left.getDeclaredType();
    final Type rightDeclType = right.getDeclaredType();
    if ((Objects.equal(leftDeclType, null) || Objects.equal(rightDeclType, null))) {
      /* true */
      if (!true) {
        sneakyThrowRuleFailedException("true");
      }
    } else {
      if ((leftDeclType.eIsProxy() || rightDeclType.eIsProxy())) {
        /* true */
        if (!true) {
          sneakyThrowRuleFailedException("true");
        }
      } else {
        if (((leftDeclType instanceof VoidType) && (rightDeclType instanceof VoidType))) {
          /* true */
          if (!true) {
            sneakyThrowRuleFailedException("true");
          }
        } else {
          if (((leftDeclType instanceof VoidType) || (rightDeclType instanceof VoidType))) {
            /* false */
            if (!false) {
              sneakyThrowRuleFailedException("false");
            }
          } else {
            if ((((leftDeclType instanceof NullType) || (leftDeclType instanceof UndefinedType)) || (rightDeclType instanceof AnyType))) {
              /* true */
              if (!true) {
                sneakyThrowRuleFailedException("true");
              }
            } else {
              if ((((leftDeclType == RuleEnvironmentExtensions.intType(G)) && (rightDeclType == RuleEnvironmentExtensions.numberType(G))) || ((leftDeclType == RuleEnvironmentExtensions.numberType(G)) && (rightDeclType == RuleEnvironmentExtensions.intType(G))))) {
                /* true */
                if (!true) {
                  sneakyThrowRuleFailedException("true");
                }
              } else {
                if (((leftDeclType instanceof TEnum) && (rightDeclType == RuleEnvironmentExtensions.n4EnumType(G)))) {
                  boolean _hasAnnotation = AnnotationDefinition.STRING_BASED.hasAnnotation(leftDeclType);
                  /* !AnnotationDefinition.STRING_BASED.hasAnnotation( leftDeclType ) */
                  if (!(!_hasAnnotation)) {
                    sneakyThrowRuleFailedException("!AnnotationDefinition.STRING_BASED.hasAnnotation( leftDeclType )");
                  }
                } else {
                  if (((leftDeclType instanceof TEnum) && (((rightDeclType == RuleEnvironmentExtensions.n4StringBasedEnumType(G)) || (rightDeclType == RuleEnvironmentExtensions.stringType(G))) || (rightDeclType == RuleEnvironmentExtensions.stringObjectType(G))))) {
                    /* AnnotationDefinition.STRING_BASED.hasAnnotation( leftDeclType ) */
                    if (!AnnotationDefinition.STRING_BASED.hasAnnotation(leftDeclType)) {
                      sneakyThrowRuleFailedException("AnnotationDefinition.STRING_BASED.hasAnnotation( leftDeclType )");
                    }
                  } else {
                    if (((leftDeclType == RuleEnvironmentExtensions.n4StringBasedEnumType(G)) && ((rightDeclType == RuleEnvironmentExtensions.stringType(G)) || (rightDeclType == RuleEnvironmentExtensions.stringObjectType(G))))) {
                      /* true */
                      if (!true) {
                        sneakyThrowRuleFailedException("true");
                      }
                    } else {
                      if (((leftDeclType instanceof PrimitiveType) && (((PrimitiveType) leftDeclType).getAssignmentCompatible() == rightDeclType))) {
                        /* true */
                        if (!true) {
                          sneakyThrowRuleFailedException("true");
                        }
                      } else {
                        if (((rightDeclType instanceof PrimitiveType) && (leftDeclType == ((PrimitiveType) rightDeclType).getAssignmentCompatible()))) {
                          /* true */
                          if (!true) {
                            sneakyThrowRuleFailedException("true");
                          }
                        } else {
                          if ((((leftDeclType instanceof TInterface) && (!(rightDeclType instanceof TInterface))) && (!(((rightDeclType == RuleEnvironmentExtensions.n4ObjectType(G)) || (rightDeclType == RuleEnvironmentExtensions.objectType(G))) || (rightDeclType == RuleEnvironmentExtensions.anyType(G)))))) {
                            /* false */
                            if (!false) {
                              sneakyThrowRuleFailedException("false");
                            }
                          } else {
                            boolean structuralTyping = false;
                            boolean _isUseSiteStructuralTyping = right.isUseSiteStructuralTyping();
                            if (_isUseSiteStructuralTyping) {
                              final StructuralTypingResult result = this.typeSystemHelper.isStructuralSubtype(G, left, right);
                              boolean _isValue = result.isValue();
                              boolean _not = (!_isValue);
                              if (_not) {
                                /* fail error result.message data PRIORITY_ERROR */
                                String _message = result.getMessage();
                                String error = _message;
                                Object data = TypeSystemErrorExtensions.PRIORITY_ERROR;
                                throwForExplicitFail(error, new ErrorInformation(null, null, data));
                              }
                              structuralTyping = true;
                            } else {
                              boolean _isDefSiteStructuralTyping = right.isDefSiteStructuralTyping();
                              if (_isDefSiteStructuralTyping) {
                                Pair<TypeRef, TypeRef> _mappedTo = Pair.<TypeRef, TypeRef>of(left, right);
                                Pair<String, Pair<TypeRef, TypeRef>> _mappedTo_1 = Pair.<String, Pair<TypeRef, TypeRef>>of(RuleEnvironmentExtensions.GUARD_SUBTYPE_PARAMETERIZED_TYPE_REF__STRUCT, _mappedTo);
                                Object _get = G.get(_mappedTo_1);
                                final Boolean guard = ((Boolean) _get);
                                if (((guard == null) || (!(guard).booleanValue()))) {
                                  final StructuralTypingResult result_1 = this.typeSystemHelper.isStructuralSubtype(G, left, right);
                                  boolean _isValue_1 = result_1.isValue();
                                  boolean _not_1 = (!_isValue_1);
                                  if (_not_1) {
                                    boolean _and = false;
                                    boolean _isN4ObjectOnLeftWithDefSite = result_1.isN4ObjectOnLeftWithDefSite();
                                    if (!_isN4ObjectOnLeftWithDefSite) {
                                      _and = false;
                                    } else {
                                      /* G.wrap, (GUARD_SUBTYPE_PARAMETERIZED_TYPE_REF__STRUCT->(left->right))<-true |- left <: right */
                                      RuleEnvironment _wrap = RuleEnvironmentExtensions.wrap(G);
                                      Pair<TypeRef, TypeRef> _mappedTo_2 = Pair.<TypeRef, TypeRef>of(left, right);
                                      Pair<String, Pair<TypeRef, TypeRef>> _mappedTo_3 = Pair.<String, Pair<TypeRef, TypeRef>>of(RuleEnvironmentExtensions.GUARD_SUBTYPE_PARAMETERIZED_TYPE_REF__STRUCT, _mappedTo_2);
                                      boolean _ruleinvocation = subtypeSucceeded(environmentComposition(
                                        _wrap, environmentEntry(_mappedTo_3, true)
                                      ), _trace_, left, right);
                                      _and = _ruleinvocation;
                                    }
                                    if (_and) {
                                      structuralTyping = true;
                                    } else {
                                      /* fail error result.message data PRIORITY_ERROR */
                                      String _message_1 = result_1.getMessage();
                                      String error_1 = _message_1;
                                      Object data_1 = TypeSystemErrorExtensions.PRIORITY_ERROR;
                                      throwForExplicitFail(error_1, new ErrorInformation(null, null, data_1));
                                    }
                                  }
                                  boolean _isValue_2 = result_1.isValue();
                                  structuralTyping = _isValue_2;
                                }
                              }
                            }
                            if ((!structuralTyping)) {
                              boolean _and_1 = false;
                              if (!((left.isUseSiteStructuralTyping() || left.isDefSiteStructuralTyping()) && 
                                (!((leftDeclType instanceof TypeVariable) || (rightDeclType instanceof TypeVariable))))) {
                                _and_1 = false;
                              } else {
                                /* G |- right <: G.n4ObjectTypeRef */
                                ParameterizedTypeRef _n4ObjectTypeRef = RuleEnvironmentExtensions.n4ObjectTypeRef(G);
                                boolean _ruleinvocation_1 = subtypeSucceeded(G, _trace_, right, _n4ObjectTypeRef);
                                _and_1 = _ruleinvocation_1;
                              }
                              if (_and_1) {
                                /* fail error "Structural type " + left.typeRefAsString + " is not a subtype of non-structural type " + right.typeRefAsString data PRIORITY_ERROR */
                                String _typeRefAsString = left.getTypeRefAsString();
                                String _plus = ("Structural type " + _typeRefAsString);
                                String _plus_1 = (_plus + " is not a subtype of non-structural type ");
                                String _typeRefAsString_1 = right.getTypeRefAsString();
                                String _plus_2 = (_plus_1 + _typeRefAsString_1);
                                String error_2 = _plus_2;
                                Object data_2 = TypeSystemErrorExtensions.PRIORITY_ERROR;
                                throwForExplicitFail(error_2, new ErrorInformation(null, null, data_2));
                              }
                              if (((leftDeclType instanceof TypeVariable) || (rightDeclType instanceof TypeVariable))) {
                                boolean _equals = Objects.equal(leftDeclType, rightDeclType);
                                if (_equals) {
                                  /* true */
                                  if (!true) {
                                    sneakyThrowRuleFailedException("true");
                                  }
                                } else {
                                  if ((leftDeclType instanceof TypeVariable)) {
                                    EList<ParameterizedTypeRef> _declaredUpperBounds = ((TypeVariable)leftDeclType).getDeclaredUpperBounds();
                                    boolean _isEmpty = _declaredUpperBounds.isEmpty();
                                    if (_isEmpty) {
                                      /* false */
                                      if (!false) {
                                        sneakyThrowRuleFailedException("false");
                                      }
                                    } else {
                                      /* G |- typeSystemHelper.createIntersectionType(G, leftDeclType.declaredUpperBounds) <: right */
                                      EList<ParameterizedTypeRef> _declaredUpperBounds_1 = ((TypeVariable)leftDeclType).getDeclaredUpperBounds();
                                      TypeRef _createIntersectionType = this.typeSystemHelper.createIntersectionType(G, ((TypeRef[])Conversions.unwrapArray(_declaredUpperBounds_1, TypeRef.class)));
                                      subtypeInternal(G, _trace_, _createIntersectionType, right);
                                    }
                                  } else {
                                    /* false */
                                    if (!false) {
                                      sneakyThrowRuleFailedException("false");
                                    }
                                  }
                                }
                              } else {
                                boolean _equals_1 = Objects.equal(leftDeclType, rightDeclType);
                                if (_equals_1) {
                                  if (((left.getTypeArgs().size() > 0) && (left.getTypeArgs().size() <= right.getTypeArgs().size()))) {
                                    EList<TypeArgument> _typeArgs = left.getTypeArgs();
                                    int _size = _typeArgs.size();
                                    EList<TypeArgument> _typeArgs_1 = right.getTypeArgs();
                                    int _size_1 = _typeArgs_1.size();
                                    int _min = Math.min(_size, _size_1);
                                    EList<TypeVariable> _typeVars = rightDeclType.getTypeVars();
                                    int _size_2 = _typeVars.size();
                                    final int len = Math.min(_min, _size_2);
                                    for (int i = 0; (i < len); i++) {
                                      EList<TypeArgument> _typeArgs_2 = left.getTypeArgs();
                                      final TypeArgument leftArg = _typeArgs_2.get(i);
                                      EList<TypeArgument> _typeArgs_3 = right.getTypeArgs();
                                      final TypeArgument rightArg = _typeArgs_3.get(i);
                                      final Variance variance = rightDeclType.getVarianceOfTypeVar(i);
                                      TypeRef leftArgUpper = null;
                                      /* G |~ leftArg /\ leftArgUpper */
                                      Result<TypeRef> result_2 = upperBoundInternal(G, _trace_, leftArg);
                                      checkAssignableTo(result_2.getFirst(), TypeRef.class);
                                      leftArgUpper = (TypeRef) result_2.getFirst();
                                      
                                      TypeRef leftArgLower = null;
                                      /* G |~ leftArg \/ leftArgLower */
                                      Result<TypeRef> result_3 = lowerBoundInternal(G, _trace_, leftArg);
                                      checkAssignableTo(result_3.getFirst(), TypeRef.class);
                                      leftArgLower = (TypeRef) result_3.getFirst();
                                      
                                      TypeRef rightArgUpper = null;
                                      /* G |~ rightArg /\ rightArgUpper */
                                      Result<TypeRef> result_4 = upperBoundInternal(G, _trace_, rightArg);
                                      checkAssignableTo(result_4.getFirst(), TypeRef.class);
                                      rightArgUpper = (TypeRef) result_4.getFirst();
                                      
                                      TypeRef rightArgLower = null;
                                      /* G |~ rightArg \/ rightArgLower */
                                      Result<TypeRef> result_5 = lowerBoundInternal(G, _trace_, rightArg);
                                      checkAssignableTo(result_5.getFirst(), TypeRef.class);
                                      rightArgLower = (TypeRef) result_5.getFirst();
                                      
                                      /* { if(variance!=Variance.CONTRA) { G |- leftArgUpper <: rightArgUpper } if(variance!=Variance.CO) { G |- rightArgLower <: leftArgLower } } or { if(previousFailure.isOrCausedByPriorityError) { fail error stringRep(left) + " is not a subtype of " + stringRep(right) + " due to incompatible type arguments: " + previousFailure.compileMessage data PRIORITY_ERROR } else { fail } } */
                                      {
                                        RuleFailedException previousFailure = null;
                                        try {
                                          boolean _notEquals = (!Objects.equal(variance, Variance.CONTRA));
                                          if (_notEquals) {
                                            /* G |- leftArgUpper <: rightArgUpper */
                                            subtypeInternal(G, _trace_, leftArgUpper, rightArgUpper);
                                          }
                                          boolean _notEquals_1 = (!Objects.equal(variance, Variance.CO));
                                          if (_notEquals_1) {
                                            /* G |- rightArgLower <: leftArgLower */
                                            subtypeInternal(G, _trace_, rightArgLower, leftArgLower);
                                          }
                                        } catch (Exception e) {
                                          previousFailure = extractRuleFailedException(e);
                                          RuleFailedException _previousFailure = previousFailure;
                                          boolean _isOrCausedByPriorityError = TypeSystemErrorExtensions.isOrCausedByPriorityError(_previousFailure);
                                          if (_isOrCausedByPriorityError) {
                                            /* fail error stringRep(left) + " is not a subtype of " + stringRep(right) + " due to incompatible type arguments: " + previousFailure.compileMessage data PRIORITY_ERROR */
                                            String _stringRep = this.stringRep(left);
                                            String _plus_3 = (_stringRep + " is not a subtype of ");
                                            String _stringRep_1 = this.stringRep(right);
                                            String _plus_4 = (_plus_3 + _stringRep_1);
                                            String _plus_5 = (_plus_4 + " due to incompatible type arguments: ");
                                            RuleFailedException _previousFailure_1 = previousFailure;
                                            String _compileMessage = TypeSystemErrorExtensions.compileMessage(_previousFailure_1);
                                            String _plus_6 = (_plus_5 + _compileMessage);
                                            String error_3 = _plus_6;
                                            Object data_3 = TypeSystemErrorExtensions.PRIORITY_ERROR;
                                            throwForExplicitFail(error_3, new ErrorInformation(null, null, data_3));
                                          } else {
                                            /* fail */
                                            throwForExplicitFail();
                                          }
                                        }
                                      }
                                    }
                                  }
                                } else {
                                  List<ParameterizedTypeRef> _xifexpression = null;
                                  if ((leftDeclType instanceof ContainerType<?>)) {
                                    _xifexpression = AllSuperTypeRefsCollector.collect(((ContainerType<?>)leftDeclType));
                                  } else {
                                    _xifexpression = CollectionLiterals.<ParameterizedTypeRef>newArrayList();
                                  }
                                  final List<ParameterizedTypeRef> allSuperTypeRefs = _xifexpression;
                                  List<ParameterizedTypeRef> _collectAllImplicitSuperTypes = RuleEnvironmentExtensions.collectAllImplicitSuperTypes(G, left);
                                  final Iterable<ParameterizedTypeRef> superTypeRefs = Iterables.<ParameterizedTypeRef>concat(allSuperTypeRefs, _collectAllImplicitSuperTypes);
                                  final Function1<ParameterizedTypeRef, Boolean> _function = (ParameterizedTypeRef it) -> {
                                    Type _declaredType = it.getDeclaredType();
                                    return Boolean.valueOf((_declaredType == rightDeclType));
                                  };
                                  boolean _exists = IterableExtensions.<ParameterizedTypeRef>exists(superTypeRefs, _function);
                                  if (_exists) {
                                    final RuleEnvironment localG_left = RuleEnvironmentExtensions.wrap(G);
                                    this.typeSystemHelper.addSubstitutions(localG_left, left);
                                    EList<TypeVariable> _typeVars_1 = rightDeclType.getTypeVars();
                                    final Function1<TypeVariable, TypeRef> _function_1 = (TypeVariable it) -> {
                                      return TypeExtensions.ref(it);
                                    };
                                    List<TypeRef> _map = ListExtensions.<TypeVariable, TypeRef>map(_typeVars_1, _function_1);
                                    final TypeRef syntheticTypeRef = TypeExtensions.ref(rightDeclType, ((TypeArgument[])Conversions.unwrapArray(_map, TypeArgument.class)));
                                    /* localG_left |- syntheticTypeRef ~> var TypeRef effectiveSuperTypeRef */
                                    TypeRef effectiveSuperTypeRef = null;
                                    Result<TypeArgument> result_2 = substTypeVariablesInternal(localG_left, _trace_, syntheticTypeRef);
                                    checkAssignableTo(result_2.getFirst(), TypeRef.class);
                                    effectiveSuperTypeRef = (TypeRef) result_2.getFirst();
                                    
                                    /* G |- effectiveSuperTypeRef <: right */
                                    subtypeInternal(G, _trace_, effectiveSuperTypeRef, right);
                                  } else {
                                    /* false */
                                    if (!false) {
                                      sneakyThrowRuleFailedException("false");
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U, final TypeRef S) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeUnion_Left(G, _subtrace_, U, S);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeUnion_Left") + stringRepForEnv(G) + " |- " + stringRep(U) + " <: " + stringRep(S);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeUnion_Left) {
    	subtypeThrowException(ruleName("subtypeUnion_Left") + stringRepForEnv(G) + " |- " + stringRep(U) + " <: " + stringRep(S),
    		SUBTYPEUNION_LEFT,
    		e_applyRuleSubtypeUnion_Left, U, S, new ErrorInformation[] {new ErrorInformation(U), new ErrorInformation(S)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeUnion_Left(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U, final TypeRef S) throws RuleFailedException {
    EList<TypeRef> _typeRefs = U.getTypeRefs();
    final Function1<TypeRef, Boolean> _function = (TypeRef T) -> {
      /* G |- T <: S */
      boolean _ruleinvocation = subtypeSucceeded(G, _trace_, T, S);
      return Boolean.valueOf(_ruleinvocation);
    };
    /* U.typeRefs.forall[T| G |- T <: S ] */
    if (!IterableExtensions.<TypeRef>forall(_typeRefs, _function)) {
      sneakyThrowRuleFailedException("U.typeRefs.forall[T| G |- T <: S ]");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef S, final UnionTypeExpression U) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeUnion_Right(G, _subtrace_, S, U);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeUnion_Right") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(U);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeUnion_Right) {
    	subtypeThrowException(ruleName("subtypeUnion_Right") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(U),
    		SUBTYPEUNION_RIGHT,
    		e_applyRuleSubtypeUnion_Right, S, U, new ErrorInformation[] {new ErrorInformation(S), new ErrorInformation(U)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeUnion_Right(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef S, final UnionTypeExpression U) throws RuleFailedException {
    EList<TypeRef> _typeRefs = U.getTypeRefs();
    final Function1<TypeRef, Boolean> _function = (TypeRef T) -> {
      /* G |- S <: T */
      boolean _ruleinvocation = subtypeSucceeded(G, _trace_, S, T);
      return Boolean.valueOf(_ruleinvocation);
    };
    /* U.typeRefs.exists[T| G |- S <: T ] */
    if (!IterableExtensions.<TypeRef>exists(_typeRefs, _function)) {
      sneakyThrowRuleFailedException("U.typeRefs.exists[T| G |- S <: T ]");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression S, final IntersectionTypeExpression I) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeIntersection_LeftRight(G, _subtrace_, S, I);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeIntersection_LeftRight") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(I);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeIntersection_LeftRight) {
    	subtypeThrowException(ruleName("subtypeIntersection_LeftRight") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(I),
    		SUBTYPEINTERSECTION_LEFTRIGHT,
    		e_applyRuleSubtypeIntersection_LeftRight, S, I, new ErrorInformation[] {new ErrorInformation(S), new ErrorInformation(I)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeIntersection_LeftRight(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression S, final IntersectionTypeExpression I) throws RuleFailedException {
    EList<TypeRef> _typeRefs = I.getTypeRefs();
    final Function1<TypeRef, Boolean> _function = (TypeRef T) -> {
      /* G |- S <: T */
      boolean _ruleinvocation = subtypeSucceeded(G, _trace_, S, T);
      return Boolean.valueOf(_ruleinvocation);
    };
    /* I.typeRefs.forall[T| G |- S <: T ] */
    if (!IterableExtensions.<TypeRef>forall(_typeRefs, _function)) {
      sneakyThrowRuleFailedException("I.typeRefs.forall[T| G |- S <: T ]");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I, final TypeRef S) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeIntersection_Left(G, _subtrace_, I, S);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeIntersection_Left") + stringRepForEnv(G) + " |- " + stringRep(I) + " <: " + stringRep(S);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeIntersection_Left) {
    	subtypeThrowException(ruleName("subtypeIntersection_Left") + stringRepForEnv(G) + " |- " + stringRep(I) + " <: " + stringRep(S),
    		SUBTYPEINTERSECTION_LEFT,
    		e_applyRuleSubtypeIntersection_Left, I, S, new ErrorInformation[] {new ErrorInformation(I), new ErrorInformation(S)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeIntersection_Left(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I, final TypeRef S) throws RuleFailedException {
    EList<TypeRef> _typeRefs = I.getTypeRefs();
    final Function1<TypeRef, Boolean> _function = (TypeRef T) -> {
      /* G |- T <: S */
      boolean _ruleinvocation = subtypeSucceeded(G, _trace_, T, S);
      return Boolean.valueOf(_ruleinvocation);
    };
    /* I.typeRefs.exists[T| G |- T <: S ] */
    if (!IterableExtensions.<TypeRef>exists(_typeRefs, _function)) {
      sneakyThrowRuleFailedException("I.typeRefs.exists[T| G |- T <: S ]");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef S, final IntersectionTypeExpression I) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeIntersection_Right(G, _subtrace_, S, I);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeIntersection_Right") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(I);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeIntersection_Right) {
    	subtypeThrowException(ruleName("subtypeIntersection_Right") + stringRepForEnv(G) + " |- " + stringRep(S) + " <: " + stringRep(I),
    		SUBTYPEINTERSECTION_RIGHT,
    		e_applyRuleSubtypeIntersection_Right, S, I, new ErrorInformation[] {new ErrorInformation(S), new ErrorInformation(I)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeIntersection_Right(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef S, final IntersectionTypeExpression I) throws RuleFailedException {
    EList<TypeRef> _typeRefs = I.getTypeRefs();
    final Function1<TypeRef, Boolean> _function = (TypeRef T) -> {
      /* G |- S <: T */
      boolean _ruleinvocation = subtypeSucceeded(G, _trace_, S, T);
      return Boolean.valueOf(_ruleinvocation);
    };
    /* I.typeRefs.forall[T| G |- S <: T ] */
    if (!IterableExtensions.<TypeRef>forall(_typeRefs, _function)) {
      sneakyThrowRuleFailedException("I.typeRefs.forall[T| G |- S <: T ]");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef left, final BoundThisTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeBoundThisTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeBoundThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeBoundThisTypeRef) {
    	subtypeThrowException(ruleName("subtypeBoundThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEBOUNDTHISTYPEREF,
    		e_applyRuleSubtypeBoundThisTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeBoundThisTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef left, final BoundThisTypeRef right) throws RuleFailedException {
    boolean _isUseSiteStructuralTyping = right.isUseSiteStructuralTyping();
    if (_isUseSiteStructuralTyping) {
      final StructuralTypingResult result = this.typeSystemHelper.isStructuralSubtype(G, left, right);
      boolean _isValue = result.isValue();
      boolean _not = (!_isValue);
      if (_not) {
        /* fail error result.message data PRIORITY_ERROR */
        String _message = result.getMessage();
        String error = _message;
        Object data = TypeSystemErrorExtensions.PRIORITY_ERROR;
        throwForExplicitFail(error, new ErrorInformation(null, null, data));
      }
    } else {
      boolean _isUseSiteStructuralTyping_1 = left.isUseSiteStructuralTyping();
      boolean _isUseSiteStructuralTyping_2 = right.isUseSiteStructuralTyping();
      boolean _tripleEquals = (Boolean.valueOf(_isUseSiteStructuralTyping_1) == Boolean.valueOf(_isUseSiteStructuralTyping_2));
      /* left.useSiteStructuralTyping === right.useSiteStructuralTyping */
      if (!_tripleEquals) {
        sneakyThrowRuleFailedException("left.useSiteStructuralTyping === right.useSiteStructuralTyping");
      }
      /* G |- left.actualThisTypeRef <: right.actualThisTypeRef */
      ParameterizedTypeRef _actualThisTypeRef = left.getActualThisTypeRef();
      ParameterizedTypeRef _actualThisTypeRef_1 = right.getActualThisTypeRef();
      subtypeInternal(G, _trace_, _actualThisTypeRef, _actualThisTypeRef_1);
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeBoundThisTypeRefTypeRef(G, _subtrace_, boundThisTypeRef, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeBoundThisTypeRefTypeRef") + stringRepForEnv(G) + " |- " + stringRep(boundThisTypeRef) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeBoundThisTypeRefTypeRef) {
    	subtypeThrowException(ruleName("subtypeBoundThisTypeRefTypeRef") + stringRepForEnv(G) + " |- " + stringRep(boundThisTypeRef) + " <: " + stringRep(right),
    		SUBTYPEBOUNDTHISTYPEREFTYPEREF,
    		e_applyRuleSubtypeBoundThisTypeRefTypeRef, boundThisTypeRef, right, new ErrorInformation[] {new ErrorInformation(boundThisTypeRef), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeBoundThisTypeRefTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef, final TypeRef right) throws RuleFailedException {
    /* boundThisTypeRef === right or { G |~ boundThisTypeRef /\ var TypeRef upperExt G |- upperExt <: right } */
    {
      RuleFailedException previousFailure = null;
      try {
        /* boundThisTypeRef === right */
        if (!(boundThisTypeRef == right)) {
          sneakyThrowRuleFailedException("boundThisTypeRef === right");
        }
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* G |~ boundThisTypeRef /\ var TypeRef upperExt */
        TypeRef upperExt = null;
        Result<TypeRef> result = upperBoundInternal(G, _trace_, boundThisTypeRef);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        upperExt = (TypeRef) result.getFirst();
        
        /* G |- upperExt <: right */
        subtypeInternal(G, _trace_, upperExt, right);
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeTypeRefBoundThisTypeRef(G, _subtrace_, left, boundThisTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeTypeRefBoundThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(boundThisTypeRef);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeTypeRefBoundThisTypeRef) {
    	subtypeThrowException(ruleName("subtypeTypeRefBoundThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(boundThisTypeRef),
    		SUBTYPETYPEREFBOUNDTHISTYPEREF,
    		e_applyRuleSubtypeTypeRefBoundThisTypeRef, left, boundThisTypeRef, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(boundThisTypeRef)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeTypeRefBoundThisTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    /* left===boundThisTypeRef or { val leftType = left.declaredType leftType===G.undefinedType || leftType===G.nullType } or { if (boundThisTypeRef.isUseSiteStructuralTyping || (null !== boundThisTypeRef.actualThisTypeRef && null !== boundThisTypeRef.actualThisTypeRef.declaredType && boundThisTypeRef.actualThisTypeRef.declaredType.final)) { val resolvedTypeRef = TypeUtils.createResolvedThisTypeRef(boundThisTypeRef); G |- left <: resolvedTypeRef } else { fail } } */
    {
      RuleFailedException previousFailure = null;
      try {
        /* left===boundThisTypeRef */
        if (!(left == boundThisTypeRef)) {
          sneakyThrowRuleFailedException("left===boundThisTypeRef");
        }
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* { val leftType = left.declaredType leftType===G.undefinedType || leftType===G.nullType } or { if (boundThisTypeRef.isUseSiteStructuralTyping || (null !== boundThisTypeRef.actualThisTypeRef && null !== boundThisTypeRef.actualThisTypeRef.declaredType && boundThisTypeRef.actualThisTypeRef.declaredType.final)) { val resolvedTypeRef = TypeUtils.createResolvedThisTypeRef(boundThisTypeRef); G |- left <: resolvedTypeRef } else { fail } } */
        {
          try {
            final Type leftType = left.getDeclaredType();
            /* leftType===G.undefinedType || leftType===G.nullType */
            if (!((leftType == RuleEnvironmentExtensions.undefinedType(G)) || (leftType == RuleEnvironmentExtensions.nullType(G)))) {
              sneakyThrowRuleFailedException("leftType===G.undefinedType || leftType===G.nullType");
            }
          } catch (Exception e_1) {
            previousFailure = extractRuleFailedException(e_1);
            if ((boundThisTypeRef.isUseSiteStructuralTyping() || (((null != boundThisTypeRef.getActualThisTypeRef()) && (null != boundThisTypeRef.getActualThisTypeRef().getDeclaredType())) && boundThisTypeRef.getActualThisTypeRef().getDeclaredType().isFinal()))) {
              final ParameterizedTypeRef resolvedTypeRef = TypeUtils.createResolvedThisTypeRef(boundThisTypeRef);
              /* G |- left <: resolvedTypeRef */
              subtypeInternal(G, _trace_, left, resolvedTypeRef);
            } else {
              /* fail */
              throwForExplicitFail();
            }
          }
        }
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeExistentialTypeRef_Right(G, _subtrace_, left, existentialTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeExistentialTypeRef_Right") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(existentialTypeRef);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeExistentialTypeRef_Right) {
    	subtypeThrowException(ruleName("subtypeExistentialTypeRef_Right") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(existentialTypeRef),
    		SUBTYPEEXISTENTIALTYPEREF_RIGHT,
    		e_applyRuleSubtypeExistentialTypeRef_Right, left, existentialTypeRef, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(existentialTypeRef)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeExistentialTypeRef_Right(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    boolean _isExistentialTypeToBeReopened = RuleEnvironmentExtensions.isExistentialTypeToBeReopened(G, existentialTypeRef);
    if (_isExistentialTypeToBeReopened) {
      final Wildcard wildThing = existentialTypeRef.getWildcard();
      /* G |~ wildThing /\ var TypeRef upperBound */
      TypeRef upperBound = null;
      Result<TypeRef> result = upperBoundInternal(G, _trace_, wildThing);
      checkAssignableTo(result.getFirst(), TypeRef.class);
      upperBound = (TypeRef) result.getFirst();
      
      /* G |~ wildThing \/ var TypeRef lowerBound */
      TypeRef lowerBound = null;
      Result<TypeRef> result_1 = lowerBoundInternal(G, _trace_, wildThing);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      lowerBound = (TypeRef) result_1.getFirst();
      
      /* G |- left <: upperBound */
      subtypeInternal(G, _trace_, left, upperBound);
      /* G |- lowerBound <: left */
      subtypeInternal(G, _trace_, lowerBound, left);
    } else {
      /* left===existentialTypeRef or { left instanceof ParameterizedTypeRef && (left as ParameterizedTypeRef).declaredType instanceof NullType } or { G |~ existentialTypeRef \/ var TypeRef lowerExt G |- left <: lowerExt } */
      {
        RuleFailedException previousFailure = null;
        try {
          /* left===existentialTypeRef */
          if (!(left == existentialTypeRef)) {
            sneakyThrowRuleFailedException("left===existentialTypeRef");
          }
        } catch (Exception e) {
          previousFailure = extractRuleFailedException(e);
          /* { left instanceof ParameterizedTypeRef && (left as ParameterizedTypeRef).declaredType instanceof NullType } or { G |~ existentialTypeRef \/ var TypeRef lowerExt G |- left <: lowerExt } */
          {
            try {
              /* left instanceof ParameterizedTypeRef && (left as ParameterizedTypeRef).declaredType instanceof NullType */
              if (!((left instanceof ParameterizedTypeRef) && 
                (((ParameterizedTypeRef) left).getDeclaredType() instanceof NullType))) {
                sneakyThrowRuleFailedException("left instanceof ParameterizedTypeRef && (left as ParameterizedTypeRef).declaredType instanceof NullType");
              }
            } catch (Exception e_1) {
              previousFailure = extractRuleFailedException(e_1);
              /* G |~ existentialTypeRef \/ var TypeRef lowerExt */
              TypeRef lowerExt = null;
              Result<TypeRef> result_2 = lowerBoundInternal(G, _trace_, existentialTypeRef);
              checkAssignableTo(result_2.getFirst(), TypeRef.class);
              lowerExt = (TypeRef) result_2.getFirst();
              
              /* G |- left <: lowerExt */
              subtypeInternal(G, _trace_, left, lowerExt);
            }
          }
        }
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeExistentialTypeRef_Left(G, _subtrace_, existentialTypeRef, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeExistentialTypeRef_Left") + stringRepForEnv(G) + " |- " + stringRep(existentialTypeRef) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeExistentialTypeRef_Left) {
    	subtypeThrowException(ruleName("subtypeExistentialTypeRef_Left") + stringRepForEnv(G) + " |- " + stringRep(existentialTypeRef) + " <: " + stringRep(right),
    		SUBTYPEEXISTENTIALTYPEREF_LEFT,
    		e_applyRuleSubtypeExistentialTypeRef_Left, existentialTypeRef, right, new ErrorInformation[] {new ErrorInformation(existentialTypeRef), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeExistentialTypeRef_Left(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef, final TypeRef right) throws RuleFailedException {
    boolean _isExistentialTypeToBeReopened = RuleEnvironmentExtensions.isExistentialTypeToBeReopened(G, existentialTypeRef);
    if (_isExistentialTypeToBeReopened) {
      final Wildcard wildThing = existentialTypeRef.getWildcard();
      /* G |~ wildThing /\ var TypeRef upperBound */
      TypeRef upperBound = null;
      Result<TypeRef> result = upperBoundInternal(G, _trace_, wildThing);
      checkAssignableTo(result.getFirst(), TypeRef.class);
      upperBound = (TypeRef) result.getFirst();
      
      /* G |~ wildThing \/ var TypeRef lowerBound */
      TypeRef lowerBound = null;
      Result<TypeRef> result_1 = lowerBoundInternal(G, _trace_, wildThing);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      lowerBound = (TypeRef) result_1.getFirst();
      
      /* G |- right <: upperBound */
      subtypeInternal(G, _trace_, right, upperBound);
      /* G |- lowerBound <: right */
      subtypeInternal(G, _trace_, lowerBound, right);
    } else {
      /* existentialTypeRef===right or { G |~ existentialTypeRef /\ var TypeRef upperExt G |- upperExt <: right } */
      {
        RuleFailedException previousFailure = null;
        try {
          /* existentialTypeRef===right */
          if (!(existentialTypeRef == right)) {
            sneakyThrowRuleFailedException("existentialTypeRef===right");
          }
        } catch (Exception e) {
          previousFailure = extractRuleFailedException(e);
          /* G |~ existentialTypeRef /\ var TypeRef upperExt */
          TypeRef upperExt = null;
          Result<TypeRef> result_2 = upperBoundInternal(G, _trace_, existentialTypeRef);
          checkAssignableTo(result_2.getFirst(), TypeRef.class);
          upperExt = (TypeRef) result_2.getFirst();
          
          /* G |- upperExt <: right */
          subtypeInternal(G, _trace_, upperExt, right);
        }
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConstructorTypeRef left, final ParameterizedTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeConstructorTypeRef__Function(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeConstructorTypeRef__Function") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeConstructorTypeRef__Function) {
    	subtypeThrowException(ruleName("subtypeConstructorTypeRef__Function") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPECONSTRUCTORTYPEREF__FUNCTION,
    		e_applyRuleSubtypeConstructorTypeRef__Function, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeConstructorTypeRef__Function(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConstructorTypeRef left, final ParameterizedTypeRef right) throws RuleFailedException {
    /* right.declaredType instanceof AnyType || right.declaredType === G.objectType */
    if (!((right.getDeclaredType() instanceof AnyType) || 
      (right.getDeclaredType() == RuleEnvironmentExtensions.objectType(G)))) {
      sneakyThrowRuleFailedException("right.declaredType instanceof AnyType || right.declaredType === G.objectType");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConstructorTypeRef left, final ConstructorTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeConstructorTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeConstructorTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeConstructorTypeRef) {
    	subtypeThrowException(ruleName("subtypeConstructorTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPECONSTRUCTORTYPEREF,
    		e_applyRuleSubtypeConstructorTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeConstructorTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ConstructorTypeRef left, final ConstructorTypeRef right) throws RuleFailedException {
    TypeArgument _typeArg = right.getTypeArg();
    if ((_typeArg instanceof TypeRef)) {
      /* G |- left.getTypeArg <: right.getTypeArg */
      TypeArgument _typeArg_1 = left.getTypeArg();
      TypeArgument _typeArg_2 = right.getTypeArg();
      subtypeInternal(G, _trace_, _typeArg_1, _typeArg_2);
      final Type left_staticType = left.staticType();
      final Type right_staticType = right.staticType();
      if (((left_staticType instanceof TypeVariable) || (right_staticType instanceof TypeVariable))) {
        /* left_staticType === right_staticType */
        if (!(left_staticType == right_staticType)) {
          sneakyThrowRuleFailedException("left_staticType === right_staticType");
        }
      } else {
        Resource _contextResource = RuleEnvironmentExtensions.getContextResource(G);
        ContainerTypesHelper.MemberCollector _fromContext = this.containerTypesHelper.fromContext(_contextResource);
        final TMethod leftCtor = _fromContext.findConstructor(((ContainerType<?>) left_staticType));
        Resource _contextResource_1 = RuleEnvironmentExtensions.getContextResource(G);
        ContainerTypesHelper.MemberCollector _fromContext_1 = this.containerTypesHelper.fromContext(_contextResource_1);
        final TMethod rightCtor = _fromContext_1.findConstructor(((ContainerType<?>) right_staticType));
        /* leftCtor!=null && rightCtor!=null */
        if (!((!Objects.equal(leftCtor, null)) && (!Objects.equal(rightCtor, null)))) {
          sneakyThrowRuleFailedException("leftCtor!=null && rightCtor!=null");
        }
        final RuleEnvironment G_left = RuleEnvironmentExtensions.wrap(G);
        final RuleEnvironment G_right = RuleEnvironmentExtensions.wrap(G);
        TypeRef _ref = TypeExtensions.ref(left_staticType);
        this.typeSystemHelper.addSubstitutions(G_left, _ref);
        TypeRef _ref_1 = TypeExtensions.ref(left_staticType);
        RuleEnvironmentExtensions.addThisType(G_left, _ref_1);
        TypeRef _ref_2 = TypeExtensions.ref(right_staticType);
        this.typeSystemHelper.addSubstitutions(G_right, _ref_2);
        TypeRef _ref_3 = TypeExtensions.ref(right_staticType);
        RuleEnvironmentExtensions.addThisType(G_right, _ref_3);
        /* G_left |- leftCtor.ref ~> var TypeRef leftCtorRefSubst */
        FunctionTypeRef _ref_4 = TypeExtensions.ref(leftCtor);
        TypeRef leftCtorRefSubst = null;
        Result<TypeArgument> result = substTypeVariablesInternal(G_left, _trace_, _ref_4);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        leftCtorRefSubst = (TypeRef) result.getFirst();
        
        /* G_right |- rightCtor.ref ~> var TypeRef rightCtorRefSubst */
        FunctionTypeRef _ref_5 = TypeExtensions.ref(rightCtor);
        TypeRef rightCtorRefSubst = null;
        Result<TypeArgument> result_1 = substTypeVariablesInternal(G_right, _trace_, _ref_5);
        checkAssignableTo(result_1.getFirst(), TypeRef.class);
        rightCtorRefSubst = (TypeRef) result_1.getFirst();
        
        /* G |- leftCtorRefSubst <: rightCtorRefSubst */
        subtypeInternal(G, _trace_, leftCtorRefSubst, rightCtorRefSubst);
      }
    } else {
      /* G |~ left.typeArg /\ var TypeRef upperBoundLeft */
      TypeArgument _typeArg_3 = left.getTypeArg();
      TypeRef upperBoundLeft = null;
      Result<TypeRef> result_2 = upperBoundInternal(G, _trace_, _typeArg_3);
      checkAssignableTo(result_2.getFirst(), TypeRef.class);
      upperBoundLeft = (TypeRef) result_2.getFirst();
      
      /* G |~ left.typeArg \/ var TypeRef lowerBoundLeft */
      TypeArgument _typeArg_4 = left.getTypeArg();
      TypeRef lowerBoundLeft = null;
      Result<TypeRef> result_3 = lowerBoundInternal(G, _trace_, _typeArg_4);
      checkAssignableTo(result_3.getFirst(), TypeRef.class);
      lowerBoundLeft = (TypeRef) result_3.getFirst();
      
      /* G |~ right.typeArg /\ var TypeRef upperBoundRight */
      TypeArgument _typeArg_5 = right.getTypeArg();
      TypeRef upperBoundRight = null;
      Result<TypeRef> result_4 = upperBoundInternal(G, _trace_, _typeArg_5);
      checkAssignableTo(result_4.getFirst(), TypeRef.class);
      upperBoundRight = (TypeRef) result_4.getFirst();
      
      /* G |~ right.typeArg \/ var TypeRef lowerBoundRight */
      TypeArgument _typeArg_6 = right.getTypeArg();
      TypeRef lowerBoundRight = null;
      Result<TypeRef> result_5 = lowerBoundInternal(G, _trace_, _typeArg_6);
      checkAssignableTo(result_5.getFirst(), TypeRef.class);
      lowerBoundRight = (TypeRef) result_5.getFirst();
      
      /* G |- upperBoundLeft <: upperBoundRight */
      subtypeInternal(G, _trace_, upperBoundLeft, upperBoundRight);
      /* G |- lowerBoundRight <: lowerBoundLeft */
      subtypeInternal(G, _trace_, lowerBoundRight, lowerBoundLeft);
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef left, final ClassifierTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeClassifierTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeClassifierTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeClassifierTypeRef) {
    	subtypeThrowException(ruleName("subtypeClassifierTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPECLASSIFIERTYPEREF,
    		e_applyRuleSubtypeClassifierTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeClassifierTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef left, final ClassifierTypeRef right) throws RuleFailedException {
    /* !(right instanceof ConstructorTypeRef) */
    if (!(!(right instanceof ConstructorTypeRef))) {
      sneakyThrowRuleFailedException("!(right instanceof ConstructorTypeRef)");
    }
    /* G |- left.getTypeArg <: right.getTypeArg */
    TypeArgument _typeArg = left.getTypeArg();
    TypeArgument _typeArg_1 = right.getTypeArg();
    subtypeInternal(G, _trace_, _typeArg, _typeArg_1);
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef left, final ParameterizedTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeClassifierTypeRefObject(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeClassifierTypeRefObject") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeClassifierTypeRefObject) {
    	subtypeThrowException(ruleName("subtypeClassifierTypeRefObject") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPECLASSIFIERTYPEREFOBJECT,
    		e_applyRuleSubtypeClassifierTypeRefObject, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeClassifierTypeRefObject(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef left, final ParameterizedTypeRef right) throws RuleFailedException {
    /* right.declaredType instanceof AnyType || right.declaredType == G.objectType */
    if (!((right.getDeclaredType() instanceof AnyType) || 
      Objects.equal(right.getDeclaredType(), RuleEnvironmentExtensions.objectType(G)))) {
      sneakyThrowRuleFailedException("right.declaredType instanceof AnyType || right.declaredType == G.objectType");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef left, final FunctionTypeExprOrRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeFunctionTypeExprOrRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeFunctionTypeExprOrRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeFunctionTypeExprOrRef) {
    	subtypeThrowException(ruleName("subtypeFunctionTypeExprOrRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEFUNCTIONTYPEEXPRORREF,
    		e_applyRuleSubtypeFunctionTypeExprOrRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeFunctionTypeExprOrRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef left, final FunctionTypeExprOrRef right) throws RuleFailedException {
    /* typeSystemHelper.isSubtypeFunction(G,left,right) */
    if (!this.typeSystemHelper.isSubtypeFunction(G, left, right)) {
      sneakyThrowRuleFailedException("typeSystemHelper.isSubtypeFunction(G,left,right)");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef left, final FunctionTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeFunctionTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeFunctionTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeFunctionTypeRef) {
    	subtypeThrowException(ruleName("subtypeFunctionTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEFUNCTIONTYPEREF,
    		e_applyRuleSubtypeFunctionTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeFunctionTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef left, final FunctionTypeRef right) throws RuleFailedException {
    /* typeSystemHelper.isSubtypeFunction(G,left,right) */
    if (!this.typeSystemHelper.isSubtypeFunction(G, left, right)) {
      sneakyThrowRuleFailedException("typeSystemHelper.isSubtypeFunction(G,left,right)");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> subtypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExpression left, final ParameterizedTypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSubtypeFunctionTypeExpression_ParameterizedTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("subtypeFunctionTypeExpression_ParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubtypeFunctionTypeExpression_ParameterizedTypeRef) {
    	subtypeThrowException(ruleName("subtypeFunctionTypeExpression_ParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " <: " + stringRep(right),
    		SUBTYPEFUNCTIONTYPEEXPRESSION_PARAMETERIZEDTYPEREF,
    		e_applyRuleSubtypeFunctionTypeExpression_ParameterizedTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSubtypeFunctionTypeExpression_ParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExpression left, final ParameterizedTypeRef right) throws RuleFailedException {
    if ((right instanceof FunctionTypeExprOrRef)) {
      /* typeSystemHelper.isSubtypeFunction(G,left,right) */
      if (!this.typeSystemHelper.isSubtypeFunction(G, left, ((FunctionTypeExprOrRef)right))) {
        sneakyThrowRuleFailedException("typeSystemHelper.isSubtypeFunction(G,left,right)");
      }
    } else {
      /* right.declaredType instanceof AnyType || right.declaredType === G.functionType */
      if (!((right.getDeclaredType() instanceof AnyType) || 
        (right.getDeclaredType() == RuleEnvironmentExtensions.functionType(G)))) {
        sneakyThrowRuleFailedException("right.declaredType instanceof AnyType || right.declaredType === G.functionType");
      }
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> supertypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleSupertypeTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("supertypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " :> " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSupertypeTypeRef) {
    	supertypeThrowException(ruleName("supertypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " :> " + stringRep(right),
    		SUPERTYPETYPEREF,
    		e_applyRuleSupertypeTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleSupertypeTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    /* G |- right <: left */
    subtypeInternal(G, _trace_, right, left);
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> equaltypeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<Boolean> _result_ = applyRuleEqualTypeTypeRef(G, _subtrace_, left, right);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("equalTypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " ~~ " + stringRep(right);
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleEqualTypeTypeRef) {
    	equaltypeThrowException(ruleName("equalTypeTypeRef") + stringRepForEnv(G) + " |- " + stringRep(left) + " ~~ " + stringRep(right),
    		EQUALTYPETYPEREF,
    		e_applyRuleEqualTypeTypeRef, left, right, new ErrorInformation[] {new ErrorInformation(left), new ErrorInformation(right)});
    	return null;
    }
  }
  
  protected Result<Boolean> applyRuleEqualTypeTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef left, final TypeRef right) throws RuleFailedException {
    /* G |- left <: right */
    subtypeInternal(G, _trace_, left, right);
    /* G |- right <: left */
    subtypeInternal(G, _trace_, right, left);
    return new Result<Boolean>(true);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EObject o, final Expression e) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeNone(G, _subtrace_, o, e);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeNone") + stringRepForEnv(G) + " |- " + stringRep(o) + " |> " + stringRep(e) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeNone) {
    	expectedTypeInThrowException(ruleName("expectedTypeNone") + stringRepForEnv(G) + " |- " + stringRep(o) + " |> " + stringRep(e) + " : " + "TypeRef",
    		EXPECTEDTYPENONE,
    		e_applyRuleExpectedTypeNone, o, e, new ErrorInformation[] {new ErrorInformation(o), new ErrorInformation(e)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeNone(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EObject o, final Expression e) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleExpectedTypeNone_2(G, o, e));
  }
  
  private TypeRef _applyRuleExpectedTypeNone_2(final RuleEnvironment G, final EObject o, final Expression e) throws RuleFailedException {
    return ((TypeRef) null);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Argument argument, final Expression argumentExpression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfArgument(G, _subtrace_, argument, argumentExpression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfArgument") + stringRepForEnv(G) + " |- " + stringRep(argument) + " |> " + stringRep(argumentExpression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfArgument) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfArgument") + stringRepForEnv(G) + " |- " + stringRep(argument) + " |> " + stringRep(argumentExpression) + " : " + "TypeRef",
    		EXPECTEDTYPEOFARGUMENT,
    		e_applyRuleExpectedTypeOfArgument, argument, argumentExpression, new ErrorInformation[] {new ErrorInformation(argument), new ErrorInformation(argumentExpression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfArgument(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Argument argument, final Expression argumentExpression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final EObject expr = argument.eContainer();
    if ((expr instanceof NewExpression)) {
      EList<Argument> _arguments = ((NewExpression)expr).getArguments();
      boolean _contains = _arguments.contains(argument);
      boolean _not = (!_contains);
      if (_not) {
      } else {
        /* G |- expr.callee : var ConstructorTypeRef ctorTypeRef */
        Expression _callee = ((NewExpression)expr).getCallee();
        ConstructorTypeRef ctorTypeRef = null;
        Result<TypeRef> result = typeInternal(G, _trace_, _callee);
        checkAssignableTo(result.getFirst(), ConstructorTypeRef.class);
        ctorTypeRef = (ConstructorTypeRef) result.getFirst();
        
        EList<TypeRef> _typeArgs = ((NewExpression)expr).getTypeArgs();
        TypeRef typeRefOfInstanceToCreate = RuleEnvironmentExtensions.createTypeRefFromStaticType(ctorTypeRef, ((TypeArgument[])Conversions.unwrapArray(_typeArgs, TypeArgument.class)));
        Type _declaredType = typeRefOfInstanceToCreate.getDeclaredType();
        ContainerType<?> typeOfInstanceToCreate = ((ContainerType<?>) _declaredType);
        final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
        this.typeSystemHelper.addSubstitutions(G2, typeRefOfInstanceToCreate);
        RuleEnvironmentExtensions.addThisType(G2, typeRefOfInstanceToCreate);
        Resource _eResource = ((NewExpression)expr).eResource();
        ContainerTypesHelper.MemberCollector _fromContext = this.containerTypesHelper.fromContext(_eResource);
        TMethod ctor = _fromContext.findConstructor(typeOfInstanceToCreate);
        TFormalParameter _fparForArgIdx = null;
        if (ctor!=null) {
          EList<Argument> _arguments_1 = ((NewExpression)expr).getArguments();
          int _indexOf = ECollections.indexOf(_arguments_1, argument, 0);
          _fparForArgIdx=ctor.getFparForArgIdx(_indexOf);
        }
        final TFormalParameter fpar = _fparForArgIdx;
        boolean _equals = Objects.equal(fpar, null);
        if (_equals) {
          UnknownTypeRef _createUnknownTypeRef = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
          T = _createUnknownTypeRef;
        } else {
          final TypeRef paramType = fpar.getTypeRef();
          boolean _equals_1 = Objects.equal(paramType, null);
          if (_equals_1) {
            ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G2);
            T = _anyTypeRef;
          } else {
            /* G2 |- paramType ~> T */
            Result<TypeArgument> result_1 = substTypeVariablesInternal(G2, _trace_, paramType);
            checkAssignableTo(result_1.getFirst(), TypeRef.class);
            T = (TypeRef) result_1.getFirst();
            
          }
        }
      }
    } else {
      if ((expr instanceof ParameterizedCallExpression)) {
        EList<Argument> _arguments_2 = ((ParameterizedCallExpression)expr).getArguments();
        boolean _contains_1 = _arguments_2.contains(argument);
        /* expr.arguments.contains(argument) */
        if (!_contains_1) {
          sneakyThrowRuleFailedException("expr.arguments.contains(argument)");
        }
        /* G |- expr.target : var TypeRef targetTypeRef */
        Expression _target = ((ParameterizedCallExpression)expr).getTarget();
        TypeRef targetTypeRef = null;
        Result<TypeRef> result_2 = typeInternal(G, _trace_, _target);
        checkAssignableTo(result_2.getFirst(), TypeRef.class);
        targetTypeRef = (TypeRef) result_2.getFirst();
        
        if ((targetTypeRef instanceof FunctionTypeExprOrRef)) {
          final FunctionTypeExprOrRef F = ((FunctionTypeExprOrRef)targetTypeRef);
          EList<Argument> _arguments_3 = ((ParameterizedCallExpression)expr).getArguments();
          final int argIndex = ECollections.indexOf(_arguments_3, argument, 0);
          final TFormalParameter fpar_1 = F.getFparForArgIdx(argIndex);
          boolean _equals_2 = Objects.equal(fpar_1, null);
          if (_equals_2) {
            UnknownTypeRef _createUnknownTypeRef_1 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
            T = _createUnknownTypeRef_1;
          } else {
            final TypeRef paramType_1 = fpar_1.getTypeRef();
            boolean _equals_3 = Objects.equal(paramType_1, null);
            if (_equals_3) {
              ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef_1;
            } else {
              final RuleEnvironment G2_1 = RuleEnvironmentExtensions.wrap(G);
              this.typeSystemHelper.addSubstitutions(G2_1, ((ParameterizedCallExpression)expr), F);
              Expression _target_1 = ((ParameterizedCallExpression)expr).getTarget();
              if ((_target_1 instanceof SuperLiteral)) {
                N4ClassDeclaration _containerOfType = EcoreUtil2.<N4ClassDeclaration>getContainerOfType(expr, N4ClassDeclaration.class);
                Type _definedType = null;
                if (_containerOfType!=null) {
                  _definedType=_containerOfType.getDefinedType();
                }
                final Type containingClass = _definedType;
                if ((containingClass instanceof TClass)) {
                  TypeRef _ref = TypeExtensions.ref(containingClass);
                  RuleEnvironmentExtensions.addThisType(G2_1, _ref);
                  ParameterizedTypeRef _superClassRef = ((TClass)containingClass).getSuperClassRef();
                  boolean _tripleNotEquals = (_superClassRef != null);
                  if (_tripleNotEquals) {
                    ParameterizedTypeRef _superClassRef_1 = ((TClass)containingClass).getSuperClassRef();
                    this.typeSystemHelper.addSubstitutions(G2_1, _superClassRef_1);
                  }
                  if ((paramType_1 instanceof ThisTypeRefStructural)) {
                    ParameterizedTypeRef _superClassRef_2 = ((TClass)containingClass).getSuperClassRef();
                    RuleEnvironmentExtensions.addThisType(G2_1, _superClassRef_2);
                  }
                }
              }
              /* G2 |- paramType ~> T */
              Result<TypeArgument> result_3 = substTypeVariablesInternal(G2_1, _trace_, paramType_1);
              checkAssignableTo(result_3.getFirst(), TypeRef.class);
              T = (TypeRef) result_3.getFirst();
              
            }
          }
        } else {
          UnknownTypeRef _createUnknownTypeRef_2 = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
          T = _createUnknownTypeRef_2;
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PostfixExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInPostfixExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInPostfixExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInPostfixExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInPostfixExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINPOSTFIXEXPRESSION,
    		e_applyRuleExpectedTypeInPostfixExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInPostfixExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PostfixExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    boolean _isActive = JavaScriptVariant.n4js.isActive(e);
    if (_isActive) {
      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnaryExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInUnaryExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInUnaryExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInUnaryExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInUnaryExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINUNARYEXPRESSION,
    		e_applyRuleExpectedTypeInUnaryExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInUnaryExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnaryExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    StaticBaseTypeRef _xifexpression = null;
    boolean _isActive = JavaScriptVariant.n4js.isActive(e);
    if (_isActive) {
      StaticBaseTypeRef _switchResult = null;
      UnaryOperator _op = e.getOp();
      if (_op != null) {
        switch (_op) {
          case DELETE:
            ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef, _voidTypeRef);
            break;
          case VOID:
            ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef_1 = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef_1, _voidTypeRef_1);
            break;
          case TYPEOF:
            ParameterizedTypeRef _anyTypeRef_2 = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef_2 = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef_2, _voidTypeRef_2);
            break;
          case INC:
            _switchResult = RuleEnvironmentExtensions.numberTypeRef(G);
            break;
          case DEC:
            _switchResult = RuleEnvironmentExtensions.numberTypeRef(G);
            break;
          case POS:
            _switchResult = RuleEnvironmentExtensions.numberTypeRef(G);
            break;
          case NEG:
            _switchResult = RuleEnvironmentExtensions.numberTypeRef(G);
            break;
          case INV:
            _switchResult = RuleEnvironmentExtensions.numberTypeRef(G);
            break;
          case NOT:
            _switchResult = RuleEnvironmentExtensions.anyTypeRef(G);
            break;
          default:
            _switchResult = RuleEnvironmentExtensions.anyTypeRef(G);
            break;
        }
      } else {
        _switchResult = RuleEnvironmentExtensions.anyTypeRef(G);
      }
      _xifexpression = _switchResult;
    } else {
      StaticBaseTypeRef _switchResult_1 = null;
      UnaryOperator _op_1 = e.getOp();
      if (_op_1 != null) {
        switch (_op_1) {
          case DELETE:
            ParameterizedTypeRef _anyTypeRef_3 = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef_3 = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult_1 = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef_3, _voidTypeRef_3);
            break;
          case VOID:
            ParameterizedTypeRef _anyTypeRef_4 = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef_4 = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult_1 = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef_4, _voidTypeRef_4);
            break;
          case TYPEOF:
            ParameterizedTypeRef _anyTypeRef_5 = RuleEnvironmentExtensions.anyTypeRef(G);
            ParameterizedTypeRef _voidTypeRef_5 = RuleEnvironmentExtensions.voidTypeRef(G);
            _switchResult_1 = TypeUtils.createNonSimplifiedUnionType(_anyTypeRef_5, _voidTypeRef_5);
            break;
          default:
            _switchResult_1 = RuleEnvironmentExtensions.anyTypeRef(G);
            break;
        }
      } else {
        _switchResult_1 = RuleEnvironmentExtensions.anyTypeRef(G);
      }
      _xifexpression = _switchResult_1;
    }
    T = _xifexpression;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiplicativeExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInMultiplicativeExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInMultiplicativeExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInMultiplicativeExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInMultiplicativeExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINMULTIPLICATIVEEXPRESSION,
    		e_applyRuleExpectedTypeInMultiplicativeExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInMultiplicativeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiplicativeExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    boolean _isActive = JavaScriptVariant.n4js.isActive(e);
    if (_isActive) {
      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AdditiveExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInAdditiveExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInAdditiveExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInAdditiveExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInAdditiveExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINADDITIVEEXPRESSION,
    		e_applyRuleExpectedTypeInAdditiveExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInAdditiveExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AdditiveExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    if (((!Objects.equal(e.getOp(), AdditiveOperator.ADD)) && JavaScriptVariant.n4js.isActive(e))) {
      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ShiftExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInShiftExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInShiftExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInShiftExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInShiftExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINSHIFTEXPRESSION,
    		e_applyRuleExpectedTypeInShiftExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInShiftExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ShiftExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    boolean _isActive = JavaScriptVariant.n4js.isActive(e);
    if (_isActive) {
      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RelationalExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInRelationalExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInRelationalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInRelationalExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInRelationalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINRELATIONALEXPRESSION,
    		e_applyRuleExpectedTypeInRelationalExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInRelationalExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final RelationalExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    RelationalOperator _op = e.getOp();
    if (_op != null) {
      switch (_op) {
        case INSTANCEOF:
          Expression _rhs = e.getRhs();
          boolean _tripleEquals = (expression == _rhs);
          if (_tripleEquals) {
            ParameterizedTypeRef _functionTypeRef = RuleEnvironmentExtensions.functionTypeRef(G);
            TClassifier _objectType = RuleEnvironmentExtensions.objectType(G);
            TypeRef _createClassifierTypeRef = null;
            if (_objectType!=null) {
              _createClassifierTypeRef=TypeUtils.createClassifierTypeRef(_objectType);
            }
            TObjectPrototype _n4EnumType = RuleEnvironmentExtensions.n4EnumType(G);
            TypeRef _createClassifierTypeRef_1 = null;
            if (_n4EnumType!=null) {
              _createClassifierTypeRef_1=TypeUtils.createClassifierTypeRef(_n4EnumType);
            }
            UnionTypeExpression _createNonSimplifiedUnionType = TypeUtils.createNonSimplifiedUnionType(_functionTypeRef, _createClassifierTypeRef, _createClassifierTypeRef_1);
            T = _createNonSimplifiedUnionType;
          } else {
            ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
            T = _anyTypeRef;
          }
          break;
        case IN:
          Expression _rhs_1 = e.getRhs();
          boolean _tripleEquals_1 = (expression == _rhs_1);
          if (_tripleEquals_1) {
            ParameterizedTypeRef _objectTypeRef = RuleEnvironmentExtensions.objectTypeRef(G);
            T = _objectTypeRef;
          } else {
            boolean _isActive = JavaScriptVariant.n4js.isActive(e);
            if (_isActive) {
              ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
              ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
              UnionTypeExpression _createNonSimplifiedUnionType_1 = TypeUtils.createNonSimplifiedUnionType(_numberTypeRef, _stringTypeRef);
              T = _createNonSimplifiedUnionType_1;
            } else {
              ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
              T = _anyTypeRef_1;
            }
          }
          break;
        default:
          boolean _isActive_1 = JavaScriptVariant.n4js.isActive(e);
          if (_isActive_1) {
            ParameterizedTypeRef _numberTypeRef_1 = RuleEnvironmentExtensions.numberTypeRef(G);
            ParameterizedTypeRef _stringTypeRef_1 = RuleEnvironmentExtensions.stringTypeRef(G);
            ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
            final UnionTypeExpression primsTR = TypeUtils.createNonSimplifiedUnionType(_numberTypeRef_1, _stringTypeRef_1, _booleanTypeRef);
            Expression _xifexpression = null;
            Expression _lhs = e.getLhs();
            boolean _tripleEquals_2 = (expression == _lhs);
            if (_tripleEquals_2) {
              _xifexpression = e.getRhs();
            } else {
              _xifexpression = e.getLhs();
            }
            final Expression otherSide = _xifexpression;
            /* G |- otherSide : var TypeRef otherSideTR */
            TypeRef otherSideTR = null;
            Result<TypeRef> result = typeInternal(G, _trace_, otherSide);
            checkAssignableTo(result.getFirst(), TypeRef.class);
            otherSideTR = (TypeRef) result.getFirst();
            
            boolean _and = false;
            /* G |- otherSideTR <: primsTR */
            boolean _ruleinvocation = subtypeSucceeded(G, _trace_, otherSideTR, primsTR);
            if (!_ruleinvocation) {
              _and = false;
            } else {
              /* G |- otherSideTR <: G.nullTypeRef */
              ParameterizedTypeRef _nullTypeRef = RuleEnvironmentExtensions.nullTypeRef(G);
              boolean _ruleinvocation_1 = subtypeSucceeded(G, _trace_, otherSideTR, _nullTypeRef);
              boolean _not = (!_ruleinvocation_1);
              _and = _not;
            }
            if (_and) {
              T = otherSideTR;
            } else {
              T = primsTR;
            }
          } else {
            ParameterizedTypeRef _anyTypeRef_2 = RuleEnvironmentExtensions.anyTypeRef(G);
            T = _anyTypeRef_2;
          }
          break;
      }
    } else {
      boolean _isActive_1 = JavaScriptVariant.n4js.isActive(e);
      if (_isActive_1) {
        ParameterizedTypeRef _numberTypeRef_1 = RuleEnvironmentExtensions.numberTypeRef(G);
        ParameterizedTypeRef _stringTypeRef_1 = RuleEnvironmentExtensions.stringTypeRef(G);
        ParameterizedTypeRef _booleanTypeRef = RuleEnvironmentExtensions.booleanTypeRef(G);
        final UnionTypeExpression primsTR = TypeUtils.createNonSimplifiedUnionType(_numberTypeRef_1, _stringTypeRef_1, _booleanTypeRef);
        Expression _xifexpression = null;
        Expression _lhs = e.getLhs();
        boolean _tripleEquals_2 = (expression == _lhs);
        if (_tripleEquals_2) {
          _xifexpression = e.getRhs();
        } else {
          _xifexpression = e.getLhs();
        }
        final Expression otherSide = _xifexpression;
        /* G |- otherSide : var TypeRef otherSideTR */
        TypeRef otherSideTR = null;
        Result<TypeRef> result = typeInternal(G, _trace_, otherSide);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        otherSideTR = (TypeRef) result.getFirst();
        
        boolean _and = false;
        /* G |- otherSideTR <: primsTR */
        boolean _ruleinvocation = subtypeSucceeded(G, _trace_, otherSideTR, primsTR);
        if (!_ruleinvocation) {
          _and = false;
        } else {
          /* G |- otherSideTR <: G.nullTypeRef */
          ParameterizedTypeRef _nullTypeRef = RuleEnvironmentExtensions.nullTypeRef(G);
          boolean _ruleinvocation_1 = subtypeSucceeded(G, _trace_, otherSideTR, _nullTypeRef);
          boolean _not = (!_ruleinvocation_1);
          _and = _not;
        }
        if (_and) {
          T = otherSideTR;
        } else {
          T = primsTR;
        }
      } else {
        ParameterizedTypeRef _anyTypeRef_2 = RuleEnvironmentExtensions.anyTypeRef(G);
        T = _anyTypeRef_2;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInEqualityExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInEqualityExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInEqualityExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInEqualityExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "ParameterizedTypeRef",
    		EXPECTEDTYPEINEQUALITYEXPRESSION,
    		e_applyRuleExpectedTypeInEqualityExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInEqualityExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EqualityExpression e, final Expression expression) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleExpectedTypeInEqualityExpression_2(G, e, expression));
  }
  
  private ParameterizedTypeRef _applyRuleExpectedTypeInEqualityExpression_2(final RuleEnvironment G, final EqualityExpression e, final Expression expression) throws RuleFailedException {
    ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
    return _anyTypeRef;
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryBitwiseExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInBinaryBitwiseExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInBinaryBitwiseExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInBinaryBitwiseExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInBinaryBitwiseExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINBINARYBITWISEEXPRESSION,
    		e_applyRuleExpectedTypeInBinaryBitwiseExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInBinaryBitwiseExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryBitwiseExpression e, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    boolean _isActive = JavaScriptVariant.n4js.isActive(e);
    if (_isActive) {
      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
      T = _numberTypeRef;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryLogicalExpression e, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInBinaryLogicalExpression(G, _subtrace_, e, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInBinaryLogicalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInBinaryLogicalExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInBinaryLogicalExpression") + stringRepForEnv(G) + " |- " + stringRep(e) + " |> " + stringRep(expression) + " : " + "ParameterizedTypeRef",
    		EXPECTEDTYPEINBINARYLOGICALEXPRESSION,
    		e_applyRuleExpectedTypeInBinaryLogicalExpression, e, expression, new ErrorInformation[] {new ErrorInformation(e), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInBinaryLogicalExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BinaryLogicalExpression e, final Expression expression) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleExpectedTypeInBinaryLogicalExpression_2(G, e, expression));
  }
  
  private ParameterizedTypeRef _applyRuleExpectedTypeInBinaryLogicalExpression_2(final RuleEnvironment G, final BinaryLogicalExpression e, final Expression expression) throws RuleFailedException {
    ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
    return _anyTypeRef;
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AssignmentExpression expr, final Expression operand) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfOperandInAssignmentExpression(G, _subtrace_, expr, operand);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfOperandInAssignmentExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " |> " + stringRep(operand) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfOperandInAssignmentExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfOperandInAssignmentExpression") + stringRepForEnv(G) + " |- " + stringRep(expr) + " |> " + stringRep(operand) + " : " + "TypeRef",
    		EXPECTEDTYPEOFOPERANDINASSIGNMENTEXPRESSION,
    		e_applyRuleExpectedTypeOfOperandInAssignmentExpression, expr, operand, new ErrorInformation[] {new ErrorInformation(expr), new ErrorInformation(operand)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfOperandInAssignmentExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AssignmentExpression expr, final Expression operand) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* { ! n4js.isActive(expr) if (operand===expr.lhs) { T = G.bottomTypeRef } else { T = G.topTypeRef } } or { N4JSASTUtils.isDestructuringAssignment(expr) if (operand===expr.lhs) { T = G.bottomTypeRef } else { T = G.topTypeRef } } or { expr.op===AssignmentOperator.ASSIGN; if (operand===expr.lhs) { T = G.bottomTypeRef } else { G |- expr.lhs : T } } or { expr.op===AssignmentOperator.ADD_ASSIGN if (operand===expr.lhs) { T = TypeUtils.createNonSimplifiedIntersectionType(G.numberTypeRef, G.stringTypeRef); } else { G |- expr.lhs : var ParameterizedTypeRef lhsTypeRef if (lhsTypeRef.declaredType === G.stringType) { T = G.anyTypeRef } else if(G.isNumeric(lhsTypeRef.declaredType)) { T = G.numberTypeRef } else { T = G.anyTypeRef } } } or { T = G.numberTypeRef } */
    {
      RuleFailedException previousFailure = null;
      try {
        boolean _isActive = JavaScriptVariant.n4js.isActive(expr);
        boolean _not = (!_isActive);
        /* ! n4js.isActive(expr) */
        if (!_not) {
          sneakyThrowRuleFailedException("! n4js.isActive(expr)");
        }
        Expression _lhs = expr.getLhs();
        boolean _tripleEquals = (operand == _lhs);
        if (_tripleEquals) {
          ParameterizedTypeRef _bottomTypeRef = RuleEnvironmentExtensions.bottomTypeRef(G);
          T = _bottomTypeRef;
        } else {
          ParameterizedTypeRef _pTypeRef = RuleEnvironmentExtensions.topTypeRef(G);
          T = _pTypeRef;
        }
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        /* { N4JSASTUtils.isDestructuringAssignment(expr) if (operand===expr.lhs) { T = G.bottomTypeRef } else { T = G.topTypeRef } } or { expr.op===AssignmentOperator.ASSIGN; if (operand===expr.lhs) { T = G.bottomTypeRef } else { G |- expr.lhs : T } } or { expr.op===AssignmentOperator.ADD_ASSIGN if (operand===expr.lhs) { T = TypeUtils.createNonSimplifiedIntersectionType(G.numberTypeRef, G.stringTypeRef); } else { G |- expr.lhs : var ParameterizedTypeRef lhsTypeRef if (lhsTypeRef.declaredType === G.stringType) { T = G.anyTypeRef } else if(G.isNumeric(lhsTypeRef.declaredType)) { T = G.numberTypeRef } else { T = G.anyTypeRef } } } or { T = G.numberTypeRef } */
        {
          try {
            boolean _isDestructuringAssignment = N4JSASTUtils.isDestructuringAssignment(expr);
            /* N4JSASTUtils.isDestructuringAssignment(expr) */
            if (!_isDestructuringAssignment) {
              sneakyThrowRuleFailedException("N4JSASTUtils.isDestructuringAssignment(expr)");
            }
            Expression _lhs_1 = expr.getLhs();
            boolean _tripleEquals_1 = (operand == _lhs_1);
            if (_tripleEquals_1) {
              ParameterizedTypeRef _bottomTypeRef_1 = RuleEnvironmentExtensions.bottomTypeRef(G);
              T = _bottomTypeRef_1;
            } else {
              ParameterizedTypeRef _pTypeRef_1 = RuleEnvironmentExtensions.topTypeRef(G);
              T = _pTypeRef_1;
            }
          } catch (Exception e_1) {
            previousFailure = extractRuleFailedException(e_1);
            /* { expr.op===AssignmentOperator.ASSIGN; if (operand===expr.lhs) { T = G.bottomTypeRef } else { G |- expr.lhs : T } } or { expr.op===AssignmentOperator.ADD_ASSIGN if (operand===expr.lhs) { T = TypeUtils.createNonSimplifiedIntersectionType(G.numberTypeRef, G.stringTypeRef); } else { G |- expr.lhs : var ParameterizedTypeRef lhsTypeRef if (lhsTypeRef.declaredType === G.stringType) { T = G.anyTypeRef } else if(G.isNumeric(lhsTypeRef.declaredType)) { T = G.numberTypeRef } else { T = G.anyTypeRef } } } or { T = G.numberTypeRef } */
            {
              try {
                AssignmentOperator _op = expr.getOp();
                boolean _tripleEquals_2 = (_op == AssignmentOperator.ASSIGN);
                /* expr.op===AssignmentOperator.ASSIGN */
                if (!_tripleEquals_2) {
                  sneakyThrowRuleFailedException("expr.op===AssignmentOperator.ASSIGN");
                }
                Expression _lhs_2 = expr.getLhs();
                boolean _tripleEquals_3 = (operand == _lhs_2);
                if (_tripleEquals_3) {
                  ParameterizedTypeRef _bottomTypeRef_2 = RuleEnvironmentExtensions.bottomTypeRef(G);
                  T = _bottomTypeRef_2;
                } else {
                  /* G |- expr.lhs : T */
                  Expression _lhs_3 = expr.getLhs();
                  Result<TypeRef> result = typeInternal(G, _trace_, _lhs_3);
                  checkAssignableTo(result.getFirst(), TypeRef.class);
                  T = (TypeRef) result.getFirst();
                  
                }
              } catch (Exception e_2) {
                previousFailure = extractRuleFailedException(e_2);
                /* { expr.op===AssignmentOperator.ADD_ASSIGN if (operand===expr.lhs) { T = TypeUtils.createNonSimplifiedIntersectionType(G.numberTypeRef, G.stringTypeRef); } else { G |- expr.lhs : var ParameterizedTypeRef lhsTypeRef if (lhsTypeRef.declaredType === G.stringType) { T = G.anyTypeRef } else if(G.isNumeric(lhsTypeRef.declaredType)) { T = G.numberTypeRef } else { T = G.anyTypeRef } } } or { T = G.numberTypeRef } */
                {
                  try {
                    AssignmentOperator _op_1 = expr.getOp();
                    boolean _tripleEquals_4 = (_op_1 == AssignmentOperator.ADD_ASSIGN);
                    /* expr.op===AssignmentOperator.ADD_ASSIGN */
                    if (!_tripleEquals_4) {
                      sneakyThrowRuleFailedException("expr.op===AssignmentOperator.ADD_ASSIGN");
                    }
                    Expression _lhs_4 = expr.getLhs();
                    boolean _tripleEquals_5 = (operand == _lhs_4);
                    if (_tripleEquals_5) {
                      ParameterizedTypeRef _numberTypeRef = RuleEnvironmentExtensions.numberTypeRef(G);
                      ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
                      IntersectionTypeExpression _createNonSimplifiedIntersectionType = TypeUtils.createNonSimplifiedIntersectionType(_numberTypeRef, _stringTypeRef);
                      T = _createNonSimplifiedIntersectionType;
                    } else {
                      /* G |- expr.lhs : var ParameterizedTypeRef lhsTypeRef */
                      Expression _lhs_5 = expr.getLhs();
                      ParameterizedTypeRef lhsTypeRef = null;
                      Result<TypeRef> result_1 = typeInternal(G, _trace_, _lhs_5);
                      checkAssignableTo(result_1.getFirst(), ParameterizedTypeRef.class);
                      lhsTypeRef = (ParameterizedTypeRef) result_1.getFirst();
                      
                      Type _declaredType = lhsTypeRef.getDeclaredType();
                      PrimitiveType _stringType = RuleEnvironmentExtensions.stringType(G);
                      boolean _tripleEquals_6 = (_declaredType == _stringType);
                      if (_tripleEquals_6) {
                        ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
                        T = _anyTypeRef;
                      } else {
                        Type _declaredType_1 = lhsTypeRef.getDeclaredType();
                        boolean _isNumeric = RuleEnvironmentExtensions.isNumeric(G, _declaredType_1);
                        if (_isNumeric) {
                          ParameterizedTypeRef _numberTypeRef_1 = RuleEnvironmentExtensions.numberTypeRef(G);
                          T = _numberTypeRef_1;
                        } else {
                          ParameterizedTypeRef _anyTypeRef_1 = RuleEnvironmentExtensions.anyTypeRef(G);
                          T = _anyTypeRef_1;
                        }
                      }
                    }
                  } catch (Exception e_3) {
                    previousFailure = extractRuleFailedException(e_3);
                    ParameterizedTypeRef _numberTypeRef_2 = RuleEnvironmentExtensions.numberTypeRef(G);
                    T = _numberTypeRef_2;
                  }
                }
              }
            }
          }
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableDeclaration vdecl, final Expression rhs) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfRightSideInVariableDeclaration(G, _subtrace_, vdecl, rhs);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfRightSideInVariableDeclaration") + stringRepForEnv(G) + " |- " + stringRep(vdecl) + " |> " + stringRep(rhs) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfRightSideInVariableDeclaration) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfRightSideInVariableDeclaration") + stringRepForEnv(G) + " |- " + stringRep(vdecl) + " |> " + stringRep(rhs) + " : " + "TypeRef",
    		EXPECTEDTYPEOFRIGHTSIDEINVARIABLEDECLARATION,
    		e_applyRuleExpectedTypeOfRightSideInVariableDeclaration, vdecl, rhs, new ErrorInformation[] {new ErrorInformation(vdecl), new ErrorInformation(rhs)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfRightSideInVariableDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableDeclaration vdecl, final Expression rhs) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = vdecl.getDeclaredTypeRef();
    boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
    if (_notEquals) {
      TypeRef _declaredTypeRef_1 = vdecl.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      ParameterizedTypeRef _pTypeRef = RuleEnvironmentExtensions.topTypeRef(G);
      T = _pTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableBinding binding, final Expression initExpr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfRightSideInVariableBinding(G, _subtrace_, binding, initExpr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfRightSideInVariableBinding") + stringRepForEnv(G) + " |- " + stringRep(binding) + " |> " + stringRep(initExpr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfRightSideInVariableBinding) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfRightSideInVariableBinding") + stringRepForEnv(G) + " |- " + stringRep(binding) + " |> " + stringRep(initExpr) + " : " + "TypeRef",
    		EXPECTEDTYPEOFRIGHTSIDEINVARIABLEBINDING,
    		e_applyRuleExpectedTypeOfRightSideInVariableBinding, binding, initExpr, new ErrorInformation[] {new ErrorInformation(binding), new ErrorInformation(initExpr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfRightSideInVariableBinding(final RuleEnvironment G, final RuleApplicationTrace _trace_, final VariableBinding binding, final Expression initExpr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    ParameterizedTypeRef _pTypeRef = RuleEnvironmentExtensions.topTypeRef(G);
    T = _pTypeRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4FieldDeclaration fdecl, final Expression rhs) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfRightSideInN4FieldDeclaration(G, _subtrace_, fdecl, rhs);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfRightSideInN4FieldDeclaration") + stringRepForEnv(G) + " |- " + stringRep(fdecl) + " |> " + stringRep(rhs) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfRightSideInN4FieldDeclaration) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfRightSideInN4FieldDeclaration") + stringRepForEnv(G) + " |- " + stringRep(fdecl) + " |> " + stringRep(rhs) + " : " + "TypeRef",
    		EXPECTEDTYPEOFRIGHTSIDEINN4FIELDDECLARATION,
    		e_applyRuleExpectedTypeOfRightSideInN4FieldDeclaration, fdecl, rhs, new ErrorInformation[] {new ErrorInformation(fdecl), new ErrorInformation(rhs)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfRightSideInN4FieldDeclaration(final RuleEnvironment G, final RuleApplicationTrace _trace_, final N4FieldDeclaration fdecl, final Expression rhs) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = fdecl.getDeclaredTypeRef();
    boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
    if (_notEquals) {
      TypeRef _declaredTypeRef_1 = fdecl.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      ParameterizedTypeRef _pTypeRef = RuleEnvironmentExtensions.topTypeRef(G);
      T = _pTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PropertyNameValuePair pnvp, final Expression rhs) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeOfRightSideInPropertyNameValuePair(G, _subtrace_, pnvp, rhs);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeOfRightSideInPropertyNameValuePair") + stringRepForEnv(G) + " |- " + stringRep(pnvp) + " |> " + stringRep(rhs) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeOfRightSideInPropertyNameValuePair) {
    	expectedTypeInThrowException(ruleName("expectedTypeOfRightSideInPropertyNameValuePair") + stringRepForEnv(G) + " |- " + stringRep(pnvp) + " |> " + stringRep(rhs) + " : " + "TypeRef",
    		EXPECTEDTYPEOFRIGHTSIDEINPROPERTYNAMEVALUEPAIR,
    		e_applyRuleExpectedTypeOfRightSideInPropertyNameValuePair, pnvp, rhs, new ErrorInformation[] {new ErrorInformation(pnvp), new ErrorInformation(rhs)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeOfRightSideInPropertyNameValuePair(final RuleEnvironment G, final RuleApplicationTrace _trace_, final PropertyNameValuePair pnvp, final Expression rhs) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredTypeRef = pnvp.getDeclaredTypeRef();
    boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
    if (_notEquals) {
      TypeRef _declaredTypeRef_1 = pnvp.getDeclaredTypeRef();
      T = _declaredTypeRef_1;
    } else {
      ParameterizedTypeRef _pTypeRef = RuleEnvironmentExtensions.topTypeRef(G);
      T = _pTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ReturnStatement stmt, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInReturnStatement(G, _subtrace_, stmt, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInReturnStatement") + stringRepForEnv(G) + " |- " + stringRep(stmt) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInReturnStatement) {
    	expectedTypeInThrowException(ruleName("expectedTypeInReturnStatement") + stringRepForEnv(G) + " |- " + stringRep(stmt) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINRETURNSTATEMENT,
    		e_applyRuleExpectedTypeInReturnStatement, stmt, expression, new ErrorInformation[] {new ErrorInformation(stmt), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInReturnStatement(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ReturnStatement stmt, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final FunctionDefinition funDef = EcoreUtil2.<FunctionDefinition>getContainerOfType(stmt, FunctionDefinition.class);
    final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
    /* G |~ stmt ~> var TypeRef myThisTypeRef */
    TypeRef myThisTypeRef = null;
    Result<TypeRef> result = thisTypeRefInternal(G, _trace_, stmt);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    myThisTypeRef = (TypeRef) result.getFirst();
    
    RuleEnvironmentExtensions.addThisType(G2, myThisTypeRef);
    /* { !funDef.isAsync() G2 |- funDef : var FunctionTypeExprOrRef fType G2 |- fType.returnTypeRef ~> T } or { if (funDef !== null) { if (funDef.returnTypeRef!==null) { T = funDef.returnTypeRef } else { val tFun = funDef.definedType; if(tFun instanceof TFunction) { val actualReturnTypeRef = tFun.returnTypeRef; if(TypeUtils.isPromise(actualReturnTypeRef, G.getPredefinedTypes().builtInTypeScope)) { val firstTypeArg = actualReturnTypeRef.typeArgs.head; if(firstTypeArg!==null) { G |~ firstTypeArg /\ T } } } } } else { val getterDef = EcoreUtil2.getContainerOfType(stmt, GetterDeclaration); T = getterDef?.definedGetter?.declaredTypeRef } } */
    {
      RuleFailedException previousFailure = null;
      try {
        boolean _isAsync = funDef.isAsync();
        boolean _not = (!_isAsync);
        /* !funDef.isAsync() */
        if (!_not) {
          sneakyThrowRuleFailedException("!funDef.isAsync()");
        }
        /* G2 |- funDef : var FunctionTypeExprOrRef fType */
        FunctionTypeExprOrRef fType = null;
        Result<TypeRef> result_1 = typeInternal(G2, _trace_, funDef);
        checkAssignableTo(result_1.getFirst(), FunctionTypeExprOrRef.class);
        fType = (FunctionTypeExprOrRef) result_1.getFirst();
        
        /* G2 |- fType.returnTypeRef ~> T */
        TypeRef _returnTypeRef = fType.getReturnTypeRef();
        Result<TypeArgument> result_2 = substTypeVariablesInternal(G2, _trace_, _returnTypeRef);
        checkAssignableTo(result_2.getFirst(), TypeRef.class);
        T = (TypeRef) result_2.getFirst();
        
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        if ((funDef != null)) {
          TypeRef _returnTypeRef_1 = funDef.getReturnTypeRef();
          boolean _tripleNotEquals = (_returnTypeRef_1 != null);
          if (_tripleNotEquals) {
            TypeRef _returnTypeRef_2 = funDef.getReturnTypeRef();
            T = _returnTypeRef_2;
          } else {
            final Type tFun = funDef.getDefinedType();
            if ((tFun instanceof TFunction)) {
              final TypeRef actualReturnTypeRef = ((TFunction)tFun).getReturnTypeRef();
              PredefinedTypes _predefinedTypes = RuleEnvironmentExtensions.getPredefinedTypes(G);
              boolean _isPromise = TypeUtils.isPromise(actualReturnTypeRef, _predefinedTypes.builtInTypeScope);
              if (_isPromise) {
                EList<TypeArgument> _typeArgs = actualReturnTypeRef.getTypeArgs();
                final TypeArgument firstTypeArg = IterableExtensions.<TypeArgument>head(_typeArgs);
                if ((firstTypeArg != null)) {
                  /* G |~ firstTypeArg /\ T */
                  Result<TypeRef> result_3 = upperBoundInternal(G, _trace_, firstTypeArg);
                  checkAssignableTo(result_3.getFirst(), TypeRef.class);
                  T = (TypeRef) result_3.getFirst();
                  
                }
              }
            }
          }
        } else {
          final GetterDeclaration getterDef = EcoreUtil2.<GetterDeclaration>getContainerOfType(stmt, GetterDeclaration.class);
          TGetter _definedGetter = null;
          if (getterDef!=null) {
            _definedGetter=getterDef.getDefinedGetter();
          }
          TypeRef _declaredTypeRef = null;
          if (_definedGetter!=null) {
            _declaredTypeRef=_definedGetter.getDeclaredTypeRef();
          }
          T = _declaredTypeRef;
        }
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ForStatement forStmnt, final Expression expression) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInForStatement(G, _subtrace_, forStmnt, expression);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInForStatement") + stringRepForEnv(G) + " |- " + stringRep(forStmnt) + " |> " + stringRep(expression) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInForStatement) {
    	expectedTypeInThrowException(ruleName("expectedTypeInForStatement") + stringRepForEnv(G) + " |- " + stringRep(forStmnt) + " |> " + stringRep(expression) + " : " + "TypeRef",
    		EXPECTEDTYPEINFORSTATEMENT,
    		e_applyRuleExpectedTypeInForStatement, forStmnt, expression, new ErrorInformation[] {new ErrorInformation(forStmnt), new ErrorInformation(expression)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInForStatement(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ForStatement forStmnt, final Expression expression) throws RuleFailedException {
    TypeRef T = null; // output parameter
    if ((forStmnt.isForOf() && (expression == forStmnt.getExpression()))) {
      final Wildcard wildThing = TypeRefsFactory.eINSTANCE.createWildcard();
      boolean _isDestructuringForStatement = N4JSASTUtils.isDestructuringForStatement(forStmnt);
      if (_isDestructuringForStatement) {
      } else {
        VariableDeclaration _xifexpression = null;
        EList<VariableDeclaration> _varDecl = forStmnt.getVarDecl();
        boolean _isEmpty = _varDecl.isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          EList<VariableDeclaration> _varDecl_1 = forStmnt.getVarDecl();
          _xifexpression = _varDecl_1.get(0);
        }
        final VariableDeclaration varDeclInFor = _xifexpression;
        VariableDeclaration _xifexpression_1 = null;
        if (((forStmnt.getInitExpr() instanceof IdentifierRef) && (((IdentifierRef) forStmnt.getInitExpr()).getId() instanceof VariableDeclaration))) {
          Expression _initExpr = forStmnt.getInitExpr();
          IdentifiableElement _id = ((IdentifierRef) _initExpr).getId();
          _xifexpression_1 = ((VariableDeclaration) _id);
        }
        final VariableDeclaration varDeclOutside = _xifexpression_1;
        boolean _or = false;
        TypeRef _declaredTypeRef = null;
        if (varDeclInFor!=null) {
          _declaredTypeRef=varDeclInFor.getDeclaredTypeRef();
        }
        boolean _notEquals = (!Objects.equal(_declaredTypeRef, null));
        if (_notEquals) {
          _or = true;
        } else {
          _or = (varDeclOutside != null);
        }
        if (_or) {
          VariableDeclaration _xifexpression_2 = null;
          if ((varDeclOutside != null)) {
            _xifexpression_2 = varDeclOutside;
          } else {
            _xifexpression_2 = varDeclInFor;
          }
          final VariableDeclaration varDecl = _xifexpression_2;
          /* G |- varDecl : var TypeRef varTypeRef */
          TypeRef varTypeRef = null;
          Result<TypeRef> result = typeInternal(G, _trace_, varDecl);
          checkAssignableTo(result.getFirst(), TypeRef.class);
          varTypeRef = (TypeRef) result.getFirst();
          
          TypeRef _copyIfContained = TypeUtils.<TypeRef>copyIfContained(varTypeRef);
          wildThing.setDeclaredUpperBound(_copyIfContained);
        }
      }
      ParameterizedTypeRef _iterableTypeRef = RuleEnvironmentExtensions.iterableTypeRef(G, wildThing);
      T = _iterableTypeRef;
    } else {
      if ((forStmnt.isForIn() && (expression == forStmnt.getExpression()))) {
        ParameterizedTypeRef _objectTypeRef = RuleEnvironmentExtensions.objectTypeRef(G);
        ParameterizedTypeRef _stringTypeRef = RuleEnvironmentExtensions.stringTypeRef(G);
        ParameterizedTypeRef _argumentsTypeRef = RuleEnvironmentExtensions.argumentsTypeRef(G);
        UnionTypeExpression _createNonSimplifiedUnionType = TypeUtils.createNonSimplifiedUnionType(_objectTypeRef, _stringTypeRef, _argumentsTypeRef);
        T = _createNonSimplifiedUnionType;
      }
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> expectedTypeInImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AwaitExpression await, final Expression expr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleExpectedTypeInAwaitExpression(G, _subtrace_, await, expr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("expectedTypeInAwaitExpression") + stringRepForEnv(G) + " |- " + stringRep(await) + " |> " + stringRep(expr) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleExpectedTypeInAwaitExpression) {
    	expectedTypeInThrowException(ruleName("expectedTypeInAwaitExpression") + stringRepForEnv(G) + " |- " + stringRep(await) + " |> " + stringRep(expr) + " : " + "TypeRef",
    		EXPECTEDTYPEINAWAITEXPRESSION,
    		e_applyRuleExpectedTypeInAwaitExpression, await, expr, new ErrorInformation[] {new ErrorInformation(await), new ErrorInformation(expr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleExpectedTypeInAwaitExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AwaitExpression await, final Expression expr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    boolean _isAutoPromisify = this.promisifyHelper.isAutoPromisify(await);
    if (_isAutoPromisify) {
      T = null;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(typeRef) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(typeRef) + " /\\ " + "TypeRef",
    		UPPERBOUNDTYPEREF,
    		e_applyRuleUpperBoundTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef typeRef) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleUpperBoundTypeRef_1(G, typeRef));
  }
  
  private TypeRef _applyRuleUpperBoundTypeRef_1(final RuleEnvironment G, final TypeRef typeRef) throws RuleFailedException {
    return typeRef;
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundWildcardTypeRef(G, _subtrace_, wildcard);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundWildcardTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(wildcard) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundWildcardTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundWildcardTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(wildcard) + " /\\ " + "TypeRef",
    		UPPERBOUNDWILDCARDTYPEREF,
    		e_applyRuleUpperBoundWildcardTypeRef, wildcard, new ErrorInformation[] {new ErrorInformation(wildcard)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundWildcardTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final TypeRef ub = TypeUtils.getDeclaredOrImplicitUpperBound(wildcard);
    if ((ub != null)) {
      T = ub;
    } else {
      ParameterizedTypeRef _anyTypeRef = RuleEnvironmentExtensions.anyTypeRef(G);
      T = _anyTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundExistentialTypeRef(G, _subtrace_, existentialTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundExistentialTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(existentialTypeRef) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundExistentialTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundExistentialTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(existentialTypeRef) + " /\\ " + "TypeRef",
    		UPPERBOUNDEXISTENTIALTYPEREF,
    		e_applyRuleUpperBoundExistentialTypeRef, existentialTypeRef, new ErrorInformation[] {new ErrorInformation(existentialTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundExistentialTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |~ existentialTypeRef.wildcard /\ T */
    Wildcard _wildcard = existentialTypeRef.getWildcard();
    Result<TypeRef> result = upperBoundInternal(G, _trace_, _wildcard);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    TypeRef _copy = TypeUtils.<TypeRef>copy(T);
    T = _copy;
    TypeUtils.copyTypeModifiers(T, existentialTypeRef);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundUnionTypeExpression(G, _subtrace_, U);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundUnionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(U) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundUnionTypeExpression) {
    	upperBoundThrowException(ruleName("upperBoundUnionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(U) + " /\\ " + "TypeRef",
    		UPPERBOUNDUNIONTYPEEXPRESSION,
    		e_applyRuleUpperBoundUnionTypeExpression, U, new ErrorInformation[] {new ErrorInformation(U)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundUnionTypeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U) throws RuleFailedException {
    TypeRef T = null; // output parameter
    EList<TypeRef> _typeRefs = U.getTypeRefs();
    final Function1<TypeRef, TypeRef> _function = (TypeRef it) -> {
      TypeRef _xblockexpression = null;
      {
        TypeRef E = null;
        /* G|~ it /\ E */
        Result<TypeRef> result = upperBoundInternal(G, _trace_, it);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        _xblockexpression = E;
      }
      return _xblockexpression;
    };
    List<TypeRef> _map = ListExtensions.<TypeRef, TypeRef>map(_typeRefs, _function);
    UnionTypeExpression _createNonSimplifiedUnionType = TypeUtils.createNonSimplifiedUnionType(_map);
    final Procedure1<UnionTypeExpression> _function_1 = (UnionTypeExpression it) -> {
      it.setOriginalComposedTypeRef(U);
    };
    UnionTypeExpression _doubleArrow = ObjectExtensions.<UnionTypeExpression>operator_doubleArrow(_createNonSimplifiedUnionType, _function_1);
    T = _doubleArrow;
    TypeUtils.copyTypeModifiers(T, U);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundIntersectionTypeExpression(G, _subtrace_, I);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundIntersectionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(I) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundIntersectionTypeExpression) {
    	upperBoundThrowException(ruleName("upperBoundIntersectionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(I) + " /\\ " + "TypeRef",
    		UPPERBOUNDINTERSECTIONTYPEEXPRESSION,
    		e_applyRuleUpperBoundIntersectionTypeExpression, I, new ErrorInformation[] {new ErrorInformation(I)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundIntersectionTypeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I) throws RuleFailedException {
    TypeRef T = null; // output parameter
    EList<TypeRef> _typeRefs = I.getTypeRefs();
    final Function1<TypeRef, TypeRef> _function = (TypeRef it) -> {
      TypeRef _xblockexpression = null;
      {
        TypeRef E = null;
        /* G|~ it /\ E */
        Result<TypeRef> result = upperBoundInternal(G, _trace_, it);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        _xblockexpression = E;
      }
      return _xblockexpression;
    };
    List<TypeRef> _map = ListExtensions.<TypeRef, TypeRef>map(_typeRefs, _function);
    IntersectionTypeExpression _createNonSimplifiedIntersectionType = TypeUtils.createNonSimplifiedIntersectionType(_map);
    final Procedure1<IntersectionTypeExpression> _function_1 = (IntersectionTypeExpression it) -> {
      it.setOriginalComposedTypeRef(I);
    };
    IntersectionTypeExpression _doubleArrow = ObjectExtensions.<IntersectionTypeExpression>operator_doubleArrow(_createNonSimplifiedIntersectionType, _function_1);
    T = _doubleArrow;
    TypeUtils.copyTypeModifiers(T, I);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef ptr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundParameterizedTypeRef(G, _subtrace_, ptr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ptr) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundParameterizedTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ptr) + " /\\ " + "TypeRef",
    		UPPERBOUNDPARAMETERIZEDTYPEREF,
    		e_applyRuleUpperBoundParameterizedTypeRef, ptr, new ErrorInformation[] {new ErrorInformation(ptr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef ptr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    Type _declaredType = ptr.getDeclaredType();
    if ((_declaredType instanceof TypeVariable)) {
      T = ptr;
    } else {
      T = ptr;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef F) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundFunctionTypeRef(G, _subtrace_, F);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundFunctionTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundFunctionTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundFunctionTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " /\\ " + "TypeRef",
    		UPPERBOUNDFUNCTIONTYPEREF,
    		e_applyRuleUpperBoundFunctionTypeRef, F, new ErrorInformation[] {new ErrorInformation(F)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundFunctionTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef F) throws RuleFailedException {
    TypeRef T = null; // output parameter
    Result<TypeRef> _applyRuleUpperBoundFunctionTypeExprOrRef = this.applyRuleUpperBoundFunctionTypeExprOrRef(G, _trace_, F);
    TypeRef _value = _applyRuleUpperBoundFunctionTypeExprOrRef.getValue();
    T = _value;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef F) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundFunctionTypeExprOrRef(G, _subtrace_, F);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundFunctionTypeExprOrRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundFunctionTypeExprOrRef) {
    	upperBoundThrowException(ruleName("upperBoundFunctionTypeExprOrRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " /\\ " + "TypeRef",
    		UPPERBOUNDFUNCTIONTYPEEXPRORREF,
    		e_applyRuleUpperBoundFunctionTypeExprOrRef, F, new ErrorInformation[] {new ErrorInformation(F)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundFunctionTypeExprOrRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef F) throws RuleFailedException {
    TypeRef T = null; // output parameter
    FunctionTypeExpression _createUpperBoundOfFunctionTypeExprOrRef = this.typeSystemHelper.createUpperBoundOfFunctionTypeExprOrRef(G, F);
    T = _createUpperBoundOfFunctionTypeExprOrRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundThisTypeRef(G, _subtrace_, boundThisTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundThisTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(boundThisTypeRef) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundThisTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundThisTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(boundThisTypeRef) + " /\\ " + "TypeRef",
    		UPPERBOUNDTHISTYPEREF,
    		e_applyRuleUpperBoundThisTypeRef, boundThisTypeRef, new ErrorInformation[] {new ErrorInformation(boundThisTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundThisTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    TypeRef T = null; // output parameter
    ParameterizedTypeRef _createResolvedThisTypeRef = TypeUtils.createResolvedThisTypeRef(boundThisTypeRef);
    T = _createResolvedThisTypeRef;
    TypeUtils.copyTypeModifiers(T, boundThisTypeRef);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> upperBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef ct) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleUpperBoundClassifierTypeRef(G, _subtrace_, ct);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("upperBoundClassifierTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ct) + " /\\ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleUpperBoundClassifierTypeRef) {
    	upperBoundThrowException(ruleName("upperBoundClassifierTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ct) + " /\\ " + "TypeRef",
    		UPPERBOUNDCLASSIFIERTYPEREF,
    		e_applyRuleUpperBoundClassifierTypeRef, ct, new ErrorInformation[] {new ErrorInformation(ct)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleUpperBoundClassifierTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef ct) throws RuleFailedException {
    TypeRef result = null; // output parameter
    result = ct;
    boolean _matched = false;
    if (ct instanceof ConstructorTypeRef) {
      _matched=true;
    }
    if (!_matched) {
      final TypeArgument typeRef = ct.getTypeArg();
      boolean _matched_1 = false;
      if (typeRef instanceof BoundThisTypeRef) {
        _matched_1=true;
        ClassifierTypeRef _createResolvedClassifierTypeRef = TypeUtils.createResolvedClassifierTypeRef(ct);
        result = _createResolvedClassifierTypeRef;
      }
    }
    return new Result<TypeRef>(result);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(typeRef) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundTypeRef) {
    	lowerBoundThrowException(ruleName("lowerBoundTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(typeRef) + " \\/ " + "TypeRef",
    		LOWERBOUNDTYPEREF,
    		e_applyRuleLowerBoundTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeRef typeRef) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleLowerBoundTypeRef_1(G, typeRef));
  }
  
  private TypeRef _applyRuleLowerBoundTypeRef_1(final RuleEnvironment G, final TypeRef typeRef) throws RuleFailedException {
    return typeRef;
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundWildcard(G, _subtrace_, wildcard);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundWildcard") + stringRepForEnv(G) + " |~ " + stringRep(wildcard) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundWildcard) {
    	lowerBoundThrowException(ruleName("lowerBoundWildcard") + stringRepForEnv(G) + " |~ " + stringRep(wildcard) + " \\/ " + "TypeRef",
    		LOWERBOUNDWILDCARD,
    		e_applyRuleLowerBoundWildcard, wildcard, new ErrorInformation[] {new ErrorInformation(wildcard)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundWildcard(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    TypeRef T = null; // output parameter
    TypeRef _declaredLowerBound = wildcard.getDeclaredLowerBound();
    boolean _tripleNotEquals = (_declaredLowerBound != null);
    if (_tripleNotEquals) {
      TypeRef _declaredLowerBound_1 = wildcard.getDeclaredLowerBound();
      T = _declaredLowerBound_1;
    } else {
      ParameterizedTypeRef _bottomTypeRef = RuleEnvironmentExtensions.bottomTypeRef(G);
      T = _bottomTypeRef;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundExistentialTypeRef(G, _subtrace_, existentialTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundExistentialTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(existentialTypeRef) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundExistentialTypeRef) {
    	lowerBoundThrowException(ruleName("lowerBoundExistentialTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(existentialTypeRef) + " \\/ " + "TypeRef",
    		LOWERBOUNDEXISTENTIALTYPEREF,
    		e_applyRuleLowerBoundExistentialTypeRef, existentialTypeRef, new ErrorInformation[] {new ErrorInformation(existentialTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundExistentialTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ExistentialTypeRef existentialTypeRef) throws RuleFailedException {
    TypeRef T = null; // output parameter
    /* G |~ existentialTypeRef.wildcard \/ T */
    Wildcard _wildcard = existentialTypeRef.getWildcard();
    Result<TypeRef> result = lowerBoundInternal(G, _trace_, _wildcard);
    checkAssignableTo(result.getFirst(), TypeRef.class);
    T = (TypeRef) result.getFirst();
    
    TypeRef _copy = TypeUtils.<TypeRef>copy(T);
    T = _copy;
    TypeUtils.copyTypeModifiers(T, existentialTypeRef);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundUnionTypeExpression(G, _subtrace_, U);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundUnionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(U) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundUnionTypeExpression) {
    	lowerBoundThrowException(ruleName("lowerBoundUnionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(U) + " \\/ " + "TypeRef",
    		LOWERBOUNDUNIONTYPEEXPRESSION,
    		e_applyRuleLowerBoundUnionTypeExpression, U, new ErrorInformation[] {new ErrorInformation(U)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundUnionTypeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final UnionTypeExpression U) throws RuleFailedException {
    TypeRef T = null; // output parameter
    EList<TypeRef> _typeRefs = U.getTypeRefs();
    final Function1<TypeRef, TypeRef> _function = (TypeRef it) -> {
      TypeRef _xblockexpression = null;
      {
        TypeRef E = null;
        /* G|~ it \/ E */
        Result<TypeRef> result = lowerBoundInternal(G, _trace_, it);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        _xblockexpression = E;
      }
      return _xblockexpression;
    };
    List<TypeRef> _map = ListExtensions.<TypeRef, TypeRef>map(_typeRefs, _function);
    UnionTypeExpression _createNonSimplifiedUnionType = TypeUtils.createNonSimplifiedUnionType(_map);
    T = _createNonSimplifiedUnionType;
    TypeUtils.copyTypeModifiers(T, U);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundIntersectionTypeExpression(G, _subtrace_, I);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundIntersectionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(I) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundIntersectionTypeExpression) {
    	lowerBoundThrowException(ruleName("lowerBoundIntersectionTypeExpression") + stringRepForEnv(G) + " |~ " + stringRep(I) + " \\/ " + "TypeRef",
    		LOWERBOUNDINTERSECTIONTYPEEXPRESSION,
    		e_applyRuleLowerBoundIntersectionTypeExpression, I, new ErrorInformation[] {new ErrorInformation(I)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundIntersectionTypeExpression(final RuleEnvironment G, final RuleApplicationTrace _trace_, final IntersectionTypeExpression I) throws RuleFailedException {
    TypeRef T = null; // output parameter
    EList<TypeRef> _typeRefs = I.getTypeRefs();
    final Function1<TypeRef, TypeRef> _function = (TypeRef it) -> {
      TypeRef _xblockexpression = null;
      {
        TypeRef E = null;
        /* G|~ it \/ E */
        Result<TypeRef> result = lowerBoundInternal(G, _trace_, it);
        checkAssignableTo(result.getFirst(), TypeRef.class);
        E = (TypeRef) result.getFirst();
        
        _xblockexpression = E;
      }
      return _xblockexpression;
    };
    List<TypeRef> _map = ListExtensions.<TypeRef, TypeRef>map(_typeRefs, _function);
    IntersectionTypeExpression _createNonSimplifiedIntersectionType = TypeUtils.createNonSimplifiedIntersectionType(_map);
    T = _createNonSimplifiedIntersectionType;
    TypeUtils.copyTypeModifiers(T, I);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef ptr) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundParameterizedTypeRef(G, _subtrace_, ptr);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ptr) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundParameterizedTypeRef) {
    	lowerBoundThrowException(ruleName("lowerBoundParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(ptr) + " \\/ " + "TypeRef",
    		LOWERBOUNDPARAMETERIZEDTYPEREF,
    		e_applyRuleLowerBoundParameterizedTypeRef, ptr, new ErrorInformation[] {new ErrorInformation(ptr)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef ptr) throws RuleFailedException {
    TypeRef T = null; // output parameter
    Type _declaredType = ptr.getDeclaredType();
    if ((_declaredType instanceof TypeVariable)) {
      T = ptr;
    } else {
      T = ptr;
    }
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef F) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundFunctionTypeRef(G, _subtrace_, F);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundFunctionTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundFunctionTypeRef) {
    	lowerBoundThrowException(ruleName("lowerBoundFunctionTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " \\/ " + "TypeRef",
    		LOWERBOUNDFUNCTIONTYPEREF,
    		e_applyRuleLowerBoundFunctionTypeRef, F, new ErrorInformation[] {new ErrorInformation(F)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundFunctionTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef F) throws RuleFailedException {
    TypeRef T = null; // output parameter
    Result<TypeRef> _applyRuleLowerBoundFunctionTypeExprOrRef = this.applyRuleLowerBoundFunctionTypeExprOrRef(G, _trace_, F);
    TypeRef _value = _applyRuleLowerBoundFunctionTypeExprOrRef.getValue();
    T = _value;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef F) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundFunctionTypeExprOrRef(G, _subtrace_, F);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundFunctionTypeExprOrRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundFunctionTypeExprOrRef) {
    	lowerBoundThrowException(ruleName("lowerBoundFunctionTypeExprOrRef") + stringRepForEnv(G) + " |~ " + stringRep(F) + " \\/ " + "TypeRef",
    		LOWERBOUNDFUNCTIONTYPEEXPRORREF,
    		e_applyRuleLowerBoundFunctionTypeExprOrRef, F, new ErrorInformation[] {new ErrorInformation(F)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundFunctionTypeExprOrRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef F) throws RuleFailedException {
    TypeRef T = null; // output parameter
    FunctionTypeExpression _createLowerBoundOfFunctionTypeExprOrRef = this.typeSystemHelper.createLowerBoundOfFunctionTypeExprOrRef(G, F);
    T = _createLowerBoundOfFunctionTypeExprOrRef;
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeRef> lowerBoundImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleLowerBoundThisTypeRef(G, _subtrace_, boundThisTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("lowerBoundThisTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(boundThisTypeRef) + " \\/ " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleLowerBoundThisTypeRef) {
    	lowerBoundThrowException(ruleName("lowerBoundThisTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(boundThisTypeRef) + " \\/ " + "TypeRef",
    		LOWERBOUNDTHISTYPEREF,
    		e_applyRuleLowerBoundThisTypeRef, boundThisTypeRef, new ErrorInformation[] {new ErrorInformation(boundThisTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleLowerBoundThisTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BoundThisTypeRef boundThisTypeRef) throws RuleFailedException {
    TypeRef T = null; // output parameter
    ParameterizedTypeRef _undefinedTypeRef = RuleEnvironmentExtensions.undefinedTypeRef(G);
    T = _undefinedTypeRef;
    TypeUtils.copyTypeModifiers(T, boundThisTypeRef);
    return new Result<TypeRef>(T);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeArgument type) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesBaseCase(G, _subtrace_, type);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesBaseCase") + stringRepForEnv(G) + " |- " + stringRep(type) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesBaseCase) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesBaseCase") + stringRepForEnv(G) + " |- " + stringRep(type) + " ~> " + "TypeArgument",
    		SUBSTTYPEVARIABLESBASECASE,
    		e_applyRuleSubstTypeVariablesBaseCase, type, new ErrorInformation[] {new ErrorInformation(type)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesBaseCase(final RuleEnvironment G, final RuleApplicationTrace _trace_, final TypeArgument type) throws RuleFailedException {
    
    return new Result<TypeArgument>(_applyRuleSubstTypeVariablesBaseCase_1(G, type));
  }
  
  private TypeArgument _applyRuleSubstTypeVariablesBaseCase_1(final RuleEnvironment G, final TypeArgument type) throws RuleFailedException {
    return type;
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesWildcard(G, _subtrace_, wildcard);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesWildcard") + stringRepForEnv(G) + " |- " + stringRep(wildcard) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesWildcard) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesWildcard") + stringRepForEnv(G) + " |- " + stringRep(wildcard) + " ~> " + "Wildcard",
    		SUBSTTYPEVARIABLESWILDCARD,
    		e_applyRuleSubstTypeVariablesWildcard, wildcard, new ErrorInformation[] {new ErrorInformation(wildcard)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesWildcard(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Wildcard wildcard) throws RuleFailedException {
    Wildcard T = null; // output parameter
    TypeRef ub = wildcard.getDeclaredUpperBound();
    if ((ub != null)) {
      /* G |- ub ~> ub */
      Result<TypeArgument> result = substTypeVariablesInternal(G, _trace_, ub);
      checkAssignableTo(result.getFirst(), TypeRef.class);
      ub = (TypeRef) result.getFirst();
      
    }
    TypeRef lb = wildcard.getDeclaredLowerBound();
    if ((lb != null)) {
      /* G |- lb ~> lb */
      Result<TypeArgument> result_1 = substTypeVariablesInternal(G, _trace_, lb);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      lb = (TypeRef) result_1.getFirst();
      
    }
    if (((ub != wildcard.getDeclaredUpperBound()) || (lb != wildcard.getDeclaredLowerBound()))) {
      Wildcard _copy = TypeUtils.<Wildcard>copy(wildcard);
      T = _copy;
      TypeRef _copyIfContained = TypeUtils.<TypeRef>copyIfContained(ub);
      T.setDeclaredUpperBound(_copyIfContained);
      TypeRef _copyIfContained_1 = TypeUtils.<TypeRef>copyIfContained(lb);
      T.setDeclaredLowerBound(_copyIfContained_1);
    } else {
      T = wildcard;
    }
    return new Result<TypeArgument>(T);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisTypeRef thisTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesThisTypeRef(G, _subtrace_, thisTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(thisTypeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesThisTypeRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesThisTypeRef") + stringRepForEnv(G) + " |- " + stringRep(thisTypeRef) + " ~> " + "ThisTypeRef",
    		SUBSTTYPEVARIABLESTHISTYPEREF,
    		e_applyRuleSubstTypeVariablesThisTypeRef, thisTypeRef, new ErrorInformation[] {new ErrorInformation(thisTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesThisTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisTypeRef thisTypeRef) throws RuleFailedException {
    ThisTypeRef T = null; // output parameter
    /* { val BoundThisTypeRef boundRefFromEnv = G.getThisType() as BoundThisTypeRef; val boundRef = TypeUtils.createBoundThisTypeRef(boundRefFromEnv.actualThisTypeRef); boundRef.setTypingStrategy(thisTypeRef.typingStrategy); TypeUtils.copyTypeModifiers(boundRef, thisTypeRef); T = boundRef; } or { T = thisTypeRef } */
    {
      RuleFailedException previousFailure = null;
      try {
        TypeRef _thisType = RuleEnvironmentExtensions.getThisType(G);
        final BoundThisTypeRef boundRefFromEnv = ((BoundThisTypeRef) _thisType);
        ParameterizedTypeRef _actualThisTypeRef = boundRefFromEnv.getActualThisTypeRef();
        final BoundThisTypeRef boundRef = TypeUtils.createBoundThisTypeRef(_actualThisTypeRef);
        TypingStrategy _typingStrategy = thisTypeRef.getTypingStrategy();
        boundRef.setTypingStrategy(_typingStrategy);
        TypeUtils.copyTypeModifiers(boundRef, thisTypeRef);
        T = boundRef;
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        T = thisTypeRef;
      }
    }
    return new Result<TypeArgument>(T);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisTypeRefStructural thisTypeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesThisTypeRefStructural(G, _subtrace_, thisTypeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesThisTypeRefStructural") + stringRepForEnv(G) + " |- " + stringRep(thisTypeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesThisTypeRefStructural) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesThisTypeRefStructural") + stringRepForEnv(G) + " |- " + stringRep(thisTypeRef) + " ~> " + "ThisTypeRef",
    		SUBSTTYPEVARIABLESTHISTYPEREFSTRUCTURAL,
    		e_applyRuleSubstTypeVariablesThisTypeRefStructural, thisTypeRef, new ErrorInformation[] {new ErrorInformation(thisTypeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesThisTypeRefStructural(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ThisTypeRefStructural thisTypeRef) throws RuleFailedException {
    ThisTypeRef T = null; // output parameter
    /* { val BoundThisTypeRef boundRefFromEnv = G.getThisType() as BoundThisTypeRef; val boundRef = TypeUtils.createBoundThisTypeRefStructural(boundRefFromEnv.actualThisTypeRef, thisTypeRef); TypeUtils.copyTypeModifiers(boundRef, thisTypeRef); T = boundRef; } or { T = thisTypeRef } */
    {
      RuleFailedException previousFailure = null;
      try {
        TypeRef _thisType = RuleEnvironmentExtensions.getThisType(G);
        final BoundThisTypeRef boundRefFromEnv = ((BoundThisTypeRef) _thisType);
        ParameterizedTypeRef _actualThisTypeRef = boundRefFromEnv.getActualThisTypeRef();
        final BoundThisTypeRef boundRef = TypeUtils.createBoundThisTypeRefStructural(_actualThisTypeRef, thisTypeRef);
        TypeUtils.copyTypeModifiers(boundRef, thisTypeRef);
        T = boundRef;
      } catch (Exception e) {
        previousFailure = extractRuleFailedException(e);
        T = thisTypeRef;
      }
    }
    return new Result<TypeArgument>(T);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesInFunctionTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesInFunctionTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesInFunctionTypeRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesInFunctionTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + "TypeRef",
    		SUBSTTYPEVARIABLESINFUNCTIONTYPEREF,
    		e_applyRuleSubstTypeVariablesInFunctionTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesInFunctionTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeRef typeRef) throws RuleFailedException {
    TypeRef result = null; // output parameter
    Result<TypeArgument> _applyRuleSubstTypeVariablesInFunctionTypeExprOrRef = this.applyRuleSubstTypeVariablesInFunctionTypeExprOrRef(G, _trace_, typeRef);
    TypeArgument _value = _applyRuleSubstTypeVariablesInFunctionTypeExprOrRef.getValue();
    result = ((TypeRef) _value);
    return new Result<TypeArgument>(result);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesInFunctionTypeExprOrRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesInFunctionTypeExprOrRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesInFunctionTypeExprOrRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesInFunctionTypeExprOrRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + "TypeRef",
    		SUBSTTYPEVARIABLESINFUNCTIONTYPEEXPRORREF,
    		e_applyRuleSubstTypeVariablesInFunctionTypeExprOrRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesInFunctionTypeExprOrRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final FunctionTypeExprOrRef typeRef) throws RuleFailedException {
    TypeRef result = null; // output parameter
    FunctionTypeExpression _createSubstitutionOfFunctionTypeExprOrRef = this.typeSystemHelper.createSubstitutionOfFunctionTypeExprOrRef(G, typeRef);
    result = _createSubstitutionOfFunctionTypeExprOrRef;
    return new Result<TypeArgument>(result);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ComposedTypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesInComposedTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesInComposedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesInComposedTypeRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesInComposedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + "ComposedTypeRef",
    		SUBSTTYPEVARIABLESINCOMPOSEDTYPEREF,
    		e_applyRuleSubstTypeVariablesInComposedTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesInComposedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ComposedTypeRef typeRef) throws RuleFailedException {
    ComposedTypeRef result = null; // output parameter
    boolean haveReplacement = false;
    final ArrayList<TypeRef> substTypeRefs = CollectionLiterals.<TypeRef>newArrayList();
    EList<TypeRef> _typeRefs = typeRef.getTypeRefs();
    for (final TypeRef currTypeRef : _typeRefs) {
      /* G |- currTypeRef ~> var TypeRef substTypeRef */
      TypeRef substTypeRef = null;
      Result<TypeArgument> result_1 = substTypeVariablesInternal(G, _trace_, currTypeRef);
      checkAssignableTo(result_1.getFirst(), TypeRef.class);
      substTypeRef = (TypeRef) result_1.getFirst();
      
      boolean _add = substTypeRefs.add(substTypeRef);
      /* substTypeRefs.add(substTypeRef) */
      if (!_add) {
        sneakyThrowRuleFailedException("substTypeRefs.add(substTypeRef)");
      }
      haveReplacement = (haveReplacement || (substTypeRef != currTypeRef));
    }
    if (haveReplacement) {
      ComposedTypeRef _copy = TypeUtils.<ComposedTypeRef>copy(typeRef);
      result = _copy;
      EList<TMember> _cachedComposedMembers = result.getCachedComposedMembers();
      _cachedComposedMembers.clear();
      result.setOriginalComposedTypeRef(typeRef);
      EList<TypeRef> _typeRefs_1 = result.getTypeRefs();
      _typeRefs_1.clear();
      EList<TypeRef> _typeRefs_2 = result.getTypeRefs();
      Collection<TypeRef> _copyAll = TypeUtils.<TypeRef>copyAll(substTypeRefs);
      /* result.typeRefs.addAll(TypeUtils.copyAll(substTypeRefs)) */
      if (!_typeRefs_2.addAll(_copyAll)) {
        sneakyThrowRuleFailedException("result.typeRefs.addAll(TypeUtils.copyAll(substTypeRefs))");
      }
    } else {
      result = typeRef;
    }
    return new Result<TypeArgument>(result);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesInClassifierTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesInClassifierTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesInClassifierTypeRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesInClassifierTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + "ClassifierTypeRef",
    		SUBSTTYPEVARIABLESINCLASSIFIERTYPEREF,
    		e_applyRuleSubstTypeVariablesInClassifierTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesInClassifierTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ClassifierTypeRef typeRef) throws RuleFailedException {
    ClassifierTypeRef result = null; // output parameter
    /* G |- typeRef.getTypeArg ~> var TypeRef tResult */
    TypeArgument _typeArg = typeRef.getTypeArg();
    TypeRef tResult = null;
    Result<TypeArgument> result_1 = substTypeVariablesInternal(G, _trace_, _typeArg);
    checkAssignableTo(result_1.getFirst(), TypeRef.class);
    tResult = (TypeRef) result_1.getFirst();
    
    TypeArgument _typeArg_1 = typeRef.getTypeArg();
    boolean _tripleNotEquals = (_typeArg_1 != tResult);
    if (_tripleNotEquals) {
      TypeRef _copy = TypeUtils.<TypeRef>copy(tResult);
      tResult = _copy;
      ClassifierTypeRef _copy_1 = TypeUtils.<ClassifierTypeRef>copy(typeRef);
      result = _copy_1;
      result.setTypeArg(tResult);
    } else {
      result = typeRef;
    }
    return new Result<TypeArgument>(result);
  }
  
  protected Result<TypeArgument> substTypeVariablesImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef typeRef) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeArgument> _result_ = applyRuleSubstTypeVariablesInParameterizedTypeRef(G, _subtrace_, typeRef);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("substTypeVariablesInParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleSubstTypeVariablesInParameterizedTypeRef) {
    	substTypeVariablesThrowException(ruleName("substTypeVariablesInParameterizedTypeRef") + stringRepForEnv(G) + " |- " + stringRep(typeRef) + " ~> " + "TypeRef",
    		SUBSTTYPEVARIABLESINPARAMETERIZEDTYPEREF,
    		e_applyRuleSubstTypeVariablesInParameterizedTypeRef, typeRef, new ErrorInformation[] {new ErrorInformation(typeRef)});
    	return null;
    }
  }
  
  protected Result<TypeArgument> applyRuleSubstTypeVariablesInParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef typeRef) throws RuleFailedException {
    TypeRef result = null; // output parameter
    result = typeRef;
    Type _declaredType = typeRef.getDeclaredType();
    if ((_declaredType instanceof TypeVariable)) {
      Type _declaredType_1 = typeRef.getDeclaredType();
      final TypeVariable typeVar = ((TypeVariable) _declaredType_1);
      /* { var temp = env(G, typeVar, TypeRef) val tempDeclaredType = temp.declaredType if (typeVar !== tempDeclaredType && (TypeUtils.isOrContainsRefToTypeVar(temp) || (tempDeclaredType !== null && tempDeclaredType.generic)) && G.get(GUARD_SUBST_TYPE_VARS -> temp) === null) { val G2 = G.wrap; G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE) G2 |- temp ~> result result = TypeUtils.copy(result); } else { result = TypeUtils.copy(temp); } TypeUtils.copyTypeModifiers(result, typeRef) } or { val List<TypeRef> l_raw = env(G, typeVar, List) val l = newArrayList; for(var i=0;i<l_raw.size;i++) { val temp = l_raw.get(i); val tempDeclaredType = temp.declaredType; if(typeVar !== tempDeclaredType && (TypeUtils.isOrContainsRefToTypeVar(temp) || (tempDeclaredType !== null && tempDeclaredType.generic)) && G.get(GUARD_SUBST_TYPE_VARS -> temp) === null) { val G2 = G.wrap; G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE) G2 |- temp ~> var TypeRef tempResult tempResult = TypeUtils.copy(tempResult); l += tempResult; } else { l += TypeUtils.copy(temp); } } result = if(typeVar.declaredCovariant) { typeSystemHelper.createIntersectionType(G,l) } else if(typeVar.declaredContravariant) { typeSystemHelper.createUnionType(G,l) } else { G.addInconsistentSubstitutions(typeVar, l); TypeRefsFactory.eINSTANCE.createUnknownTypeRef }; TypeUtils.copyTypeModifiers(result, typeRef) } or { } */
      {
        RuleFailedException previousFailure = null;
        try {
          TypeRef temp = this.<TypeRef>env(G, typeVar, TypeRef.class);
          final Type tempDeclaredType = temp.getDeclaredType();
          if ((((typeVar != tempDeclaredType) && (TypeUtils.isOrContainsRefToTypeVar(temp) || ((tempDeclaredType != null) && tempDeclaredType.isGeneric()))) && (G.get(Pair.<String, TypeRef>of(RuleEnvironmentExtensions.GUARD_SUBST_TYPE_VARS, temp)) == null))) {
            final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
            Pair<String, TypeRef> _mappedTo = Pair.<String, TypeRef>of(RuleEnvironmentExtensions.GUARD_SUBST_TYPE_VARS, temp);
            boolean _add = G2.add(_mappedTo, Boolean.TRUE);
            /* G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE) */
            if (!_add) {
              sneakyThrowRuleFailedException("G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE)");
            }
            /* G2 |- temp ~> result */
            Result<TypeArgument> result_1 = substTypeVariablesInternal(G2, _trace_, temp);
            checkAssignableTo(result_1.getFirst(), TypeRef.class);
            result = (TypeRef) result_1.getFirst();
            
            TypeRef _copy = TypeUtils.<TypeRef>copy(result);
            result = _copy;
          } else {
            TypeRef _copy_1 = TypeUtils.<TypeRef>copy(temp);
            result = _copy_1;
          }
          TypeUtils.copyTypeModifiers(result, typeRef);
        } catch (Exception e) {
          previousFailure = extractRuleFailedException(e);
          /* { val List<TypeRef> l_raw = env(G, typeVar, List) val l = newArrayList; for(var i=0;i<l_raw.size;i++) { val temp = l_raw.get(i); val tempDeclaredType = temp.declaredType; if(typeVar !== tempDeclaredType && (TypeUtils.isOrContainsRefToTypeVar(temp) || (tempDeclaredType !== null && tempDeclaredType.generic)) && G.get(GUARD_SUBST_TYPE_VARS -> temp) === null) { val G2 = G.wrap; G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE) G2 |- temp ~> var TypeRef tempResult tempResult = TypeUtils.copy(tempResult); l += tempResult; } else { l += TypeUtils.copy(temp); } } result = if(typeVar.declaredCovariant) { typeSystemHelper.createIntersectionType(G,l) } else if(typeVar.declaredContravariant) { typeSystemHelper.createUnionType(G,l) } else { G.addInconsistentSubstitutions(typeVar, l); TypeRefsFactory.eINSTANCE.createUnknownTypeRef }; TypeUtils.copyTypeModifiers(result, typeRef) } or { } */
          {
            try {
              final List<TypeRef> l_raw = this.<List>env(G, typeVar, List.class);
              final ArrayList<TypeRef> l = CollectionLiterals.<TypeRef>newArrayList();
              for (int i = 0; (i < l_raw.size()); i++) {
                final TypeRef temp_1 = l_raw.get(i);
                final Type tempDeclaredType_1 = temp_1.getDeclaredType();
                if ((((typeVar != tempDeclaredType_1) && (TypeUtils.isOrContainsRefToTypeVar(temp_1) || ((tempDeclaredType_1 != null) && tempDeclaredType_1.isGeneric()))) && (G.get(Pair.<String, TypeRef>of(RuleEnvironmentExtensions.GUARD_SUBST_TYPE_VARS, temp_1)) == null))) {
                  final RuleEnvironment G2_1 = RuleEnvironmentExtensions.wrap(G);
                  Pair<String, TypeRef> _mappedTo_1 = Pair.<String, TypeRef>of(RuleEnvironmentExtensions.GUARD_SUBST_TYPE_VARS, temp_1);
                  boolean _add_1 = G2_1.add(_mappedTo_1, Boolean.TRUE);
                  /* G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE) */
                  if (!_add_1) {
                    sneakyThrowRuleFailedException("G2.add(GUARD_SUBST_TYPE_VARS -> temp, Boolean.TRUE)");
                  }
                  /* G2 |- temp ~> var TypeRef tempResult */
                  TypeRef tempResult = null;
                  Result<TypeArgument> result_2 = substTypeVariablesInternal(G2_1, _trace_, temp_1);
                  checkAssignableTo(result_2.getFirst(), TypeRef.class);
                  tempResult = (TypeRef) result_2.getFirst();
                  
                  TypeRef _copy_2 = TypeUtils.<TypeRef>copy(tempResult);
                  tempResult = _copy_2;
                  /* l += tempResult */
                  if (!l.add(tempResult)) {
                    sneakyThrowRuleFailedException("l += tempResult");
                  }
                } else {
                  TypeRef _copy_3 = TypeUtils.<TypeRef>copy(temp_1);
                  /* l += TypeUtils.copy(temp) */
                  if (!l.add(_copy_3)) {
                    sneakyThrowRuleFailedException("l += TypeUtils.copy(temp)");
                  }
                }
              }
              TypeRef _xifexpression = null;
              boolean _isDeclaredCovariant = typeVar.isDeclaredCovariant();
              if (_isDeclaredCovariant) {
                _xifexpression = this.typeSystemHelper.createIntersectionType(G, ((TypeRef[])Conversions.unwrapArray(l, TypeRef.class)));
              } else {
                TypeRef _xifexpression_1 = null;
                boolean _isDeclaredContravariant = typeVar.isDeclaredContravariant();
                if (_isDeclaredContravariant) {
                  _xifexpression_1 = this.typeSystemHelper.createUnionType(G, ((TypeRef[])Conversions.unwrapArray(l, TypeRef.class)));
                } else {
                  UnknownTypeRef _xblockexpression = null;
                  {
                    RuleEnvironmentExtensions.addInconsistentSubstitutions(G, typeVar, l);
                    _xblockexpression = (TypeRefsFactory.eINSTANCE.createUnknownTypeRef());
                  }
                  _xifexpression_1 = _xblockexpression;
                }
                _xifexpression = _xifexpression_1;
              }
              result = _xifexpression;
              TypeUtils.copyTypeModifiers(result, typeRef);
            } catch (Exception e_1) {
              previousFailure = extractRuleFailedException(e_1);
            }
          }
        }
      }
    }
    boolean _and = false;
    Type _declaredType_2 = null;
    if (typeRef!=null) {
      _declaredType_2=typeRef.getDeclaredType();
    }
    boolean _tripleNotEquals = (_declaredType_2 != null);
    if (!_tripleNotEquals) {
      _and = false;
    } else {
      Type _declaredType_3 = typeRef.getDeclaredType();
      boolean _isGeneric = _declaredType_3.isGeneric();
      _and = _isGeneric;
    }
    if (_and) {
      EList<TypeArgument> _typeArgs = typeRef.getTypeArgs();
      final int len = _typeArgs.size();
      boolean haveSubstitution = false;
      final TypeArgument[] argsChanged = new TypeArgument[len];
      for (int i = 0; (i < len); i++) {
        EList<TypeArgument> _typeArgs_1 = typeRef.getTypeArgs();
        final TypeArgument arg = _typeArgs_1.get(i);
        /* G |- arg ~> var TypeArgument argSubst */
        TypeArgument argSubst = null;
        Result<TypeArgument> result_2 = substTypeVariablesInternal(G, _trace_, arg);
        checkAssignableTo(result_2.getFirst(), TypeArgument.class);
        argSubst = (TypeArgument) result_2.getFirst();
        
        if ((argSubst != arg)) {
          argsChanged[i] = argSubst;
          haveSubstitution = true;
        }
      }
      if (haveSubstitution) {
        if ((result == typeRef)) {
          ParameterizedTypeRef _copy_2 = TypeUtils.<ParameterizedTypeRef>copy(typeRef);
          result = _copy_2;
        }
        for (int i = 0; (i < len); i++) {
          final TypeArgument argCh = argsChanged[i];
          if ((argCh != null)) {
            EList<TypeArgument> _typeArgs_1 = result.getTypeArgs();
            _typeArgs_1.set(i, argCh);
          }
        }
      }
    }
    if ((result instanceof StructuralTypeRef)) {
      TypeRef _substTypeVariablesInStructuralMembers = this.typeSystemHelper.substTypeVariablesInStructuralMembers(G, ((StructuralTypeRef)result));
      result = _substTypeVariablesInStructuralMembers;
    }
    return new Result<TypeArgument>(result);
  }
  
  protected Result<TypeRef> thisTypeRefImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef type) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleThisTypeRefParameterizedTypeRef(G, _subtrace_, type);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("thisTypeRefParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(type) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleThisTypeRefParameterizedTypeRef) {
    	thisTypeRefThrowException(ruleName("thisTypeRefParameterizedTypeRef") + stringRepForEnv(G) + " |~ " + stringRep(type) + " ~> " + "BoundThisTypeRef",
    		THISTYPEREFPARAMETERIZEDTYPEREF,
    		e_applyRuleThisTypeRefParameterizedTypeRef, type, new ErrorInformation[] {new ErrorInformation(type)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleThisTypeRefParameterizedTypeRef(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ParameterizedTypeRef type) throws RuleFailedException {
    
    return new Result<TypeRef>(_applyRuleThisTypeRefParameterizedTypeRef_1(G, type));
  }
  
  private BoundThisTypeRef _applyRuleThisTypeRefParameterizedTypeRef_1(final RuleEnvironment G, final ParameterizedTypeRef type) throws RuleFailedException {
    BoundThisTypeRef _createBoundThisTypeRef = TypeUtils.createBoundThisTypeRef(type);
    return _createBoundThisTypeRef;
  }
  
  protected Result<TypeRef> thisTypeRefImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EObject location) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<TypeRef> _result_ = applyRuleThisTypeRefEObject(G, _subtrace_, location);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("thisTypeRefEObject") + stringRepForEnv(G) + " |~ " + stringRep(location) + " ~> " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleThisTypeRefEObject) {
    	thisTypeRefThrowException(ruleName("thisTypeRefEObject") + stringRepForEnv(G) + " |~ " + stringRep(location) + " ~> " + "TypeRef",
    		THISTYPEREFEOBJECT,
    		e_applyRuleThisTypeRefEObject, location, new ErrorInformation[] {new ErrorInformation(location)});
    	return null;
    }
  }
  
  protected Result<TypeRef> applyRuleThisTypeRefEObject(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EObject location) throws RuleFailedException {
    TypeRef T = null; // output parameter
    final FunctionOrFieldAccessor containingFunctionOrAccessor = N4JSASTUtils.getContainingFunctionOrAccessor(location);
    boolean _matched = false;
    if (containingFunctionOrAccessor instanceof ArrowFunction) {
      _matched=true;
      /* G |~ containingFunctionOrAccessor ~> T */
      Result<TypeRef> result = thisTypeRefInternal(G, _trace_, ((ArrowFunction)containingFunctionOrAccessor));
      checkAssignableTo(result.getFirst(), TypeRef.class);
      T = (TypeRef) result.getFirst();
      
    }
    if (!_matched) {
      Type x = null;
      if ((containingFunctionOrAccessor instanceof FunctionDefinition)) {
        Type _definedType = ((FunctionDefinition)containingFunctionOrAccessor).getDefinedType();
        x = _definedType;
      }
      final TypeRef declaredThisType = TypeSystemHelper.declaredThisType(x);
      if ((declaredThisType != null)) {
        if ((declaredThisType instanceof ParameterizedTypeRef)) {
          /* G |~ declaredThisType ~> T */
          Result<TypeRef> result = thisTypeRefInternal(G, _trace_, ((ParameterizedTypeRef)declaredThisType));
          checkAssignableTo(result.getFirst(), TypeRef.class);
          T = (TypeRef) result.getFirst();
          
        } else {
          T = declaredThisType;
        }
      } else {
        final ThisTarget thisTarget = N4JSASTUtils.getProbableThisTarget(location);
        boolean _matched_1 = false;
        if (thisTarget instanceof ObjectLiteral) {
          _matched_1=true;
          /* G |- thisTarget: T */
          Result<TypeRef> result_1 = typeInternal(G, _trace_, ((ObjectLiteral)thisTarget));
          checkAssignableTo(result_1.getFirst(), TypeRef.class);
          T = (TypeRef) result_1.getFirst();
          
        }
        if (!_matched_1) {
          if (thisTarget instanceof N4ClassifierDefinition) {
            _matched_1=true;
            Type thisTargetDEFTYPE = ((N4ClassifierDefinition)thisTarget).getDefinedType();
            if ((thisTarget instanceof N4ClassDeclaration)) {
              final TClass clazz = ((N4ClassDeclaration)thisTarget).getDefinedTypeAsClass();
              if (((clazz != null) && clazz.isStaticPolyfill())) {
                ParameterizedTypeRef _superClassRef = clazz.getSuperClassRef();
                final Type actualClazz = _superClassRef.getDeclaredType();
                if ((actualClazz != null)) {
                  thisTargetDEFTYPE = actualClazz;
                }
              }
            }
            if ((thisTargetDEFTYPE != null)) {
              final FunctionDefinition containingFunction = N4JSASTUtils.getContainingFunction(location);
              if (((containingFunction instanceof N4MethodDeclaration) && 
                ((N4MemberDeclaration) containingFunction).isStatic())) {
                boolean _isInReturnDeclaration_Of_StaticMethod = RuleEnvironmentExtensions.isInReturnDeclaration_Of_StaticMethod(location, ((N4MethodDeclaration) containingFunction));
                if (_isInReturnDeclaration_Of_StaticMethod) {
                  /* G |~ thisTargetDEFTYPE.ref ~> T */
                  TypeRef _ref = TypeExtensions.ref(thisTargetDEFTYPE);
                  Result<TypeRef> result_1 = thisTypeRefInternal(G, _trace_, _ref);
                  checkAssignableTo(result_1.getFirst(), TypeRef.class);
                  T = (TypeRef) result_1.getFirst();
                  
                } else {
                  boolean _isInBody_Of_StaticMethod = RuleEnvironmentExtensions.isInBody_Of_StaticMethod(location, ((N4MethodDeclaration) containingFunction));
                  if (_isInBody_Of_StaticMethod) {
                    TypeRef _createClassifierTypeRef = TypeUtils.createClassifierTypeRef(thisTargetDEFTYPE);
                    ClassifierTypeRef _createClassifierBoundThisTypeRef = TypeUtils.createClassifierBoundThisTypeRef(((ClassifierTypeRef) _createClassifierTypeRef));
                    T = _createClassifierBoundThisTypeRef;
                  } else {
                    TypeRef _createClassifierTypeRef_1 = TypeUtils.createClassifierTypeRef(thisTargetDEFTYPE);
                    T = _createClassifierTypeRef_1;
                  }
                }
              } else {
                final N4FieldDeclaration n4Field = EcoreUtil2.<N4FieldDeclaration>getContainerOfType(location, N4FieldDeclaration.class);
                if (((n4Field != null) && n4Field.isStatic())) {
                  TypeRef _createClassifierTypeRef_2 = TypeUtils.createClassifierTypeRef(thisTargetDEFTYPE);
                  T = _createClassifierTypeRef_2;
                } else {
                  final N4GetterDeclaration n4Getter = EcoreUtil2.<N4GetterDeclaration>getContainerOfType(location, N4GetterDeclaration.class);
                  if (((n4Getter != null) && n4Getter.isStatic())) {
                    TypeRef _createClassifierTypeRef_3 = TypeUtils.createClassifierTypeRef(thisTargetDEFTYPE);
                    T = _createClassifierTypeRef_3;
                  } else {
                    final N4SetterDeclaration n4Setter = EcoreUtil2.<N4SetterDeclaration>getContainerOfType(location, N4SetterDeclaration.class);
                    if (((n4Setter != null) && n4Setter.isStatic())) {
                      TypeRef _createClassifierTypeRef_4 = TypeUtils.createClassifierTypeRef(thisTargetDEFTYPE);
                      T = _createClassifierTypeRef_4;
                    } else {
                      /* G |~ thisTargetDEFTYPE.ref ~> T */
                      TypeRef _ref_1 = TypeExtensions.ref(thisTargetDEFTYPE);
                      Result<TypeRef> result_2 = thisTypeRefInternal(G, _trace_, _ref_1);
                      checkAssignableTo(result_2.getFirst(), TypeRef.class);
                      T = (TypeRef) result_2.getFirst();
                      
                    }
                  }
                }
              }
            } else {
              ParameterizedTypeRef _anyTypeRefDynamic = RuleEnvironmentExtensions.anyTypeRefDynamic(G);
              T = _anyTypeRefDynamic;
            }
          }
        }
        if (!_matched_1) {
          boolean _isActive = JavaScriptVariant.unrestricted.isActive(location);
          if (_isActive) {
            ParameterizedTypeRef _globalObjectTypeRef = RuleEnvironmentExtensions.globalObjectTypeRef(G);
            T = _globalObjectTypeRef;
          } else {
            ParameterizedTypeRef _undefinedTypeRef = RuleEnvironmentExtensions.undefinedTypeRef(G);
            T = _undefinedTypeRef;
          }
        }
      }
    }
    return new Result<TypeRef>(T);
  }
}
