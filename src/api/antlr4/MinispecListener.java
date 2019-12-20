package api.antlr4;
// Generated from Minispec.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinispecParser}.
 */
public interface MinispecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinispecParser#lowerCaseIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterLowerCaseIdentifier(MinispecParser.LowerCaseIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#lowerCaseIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitLowerCaseIdentifier(MinispecParser.LowerCaseIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#upperCaseIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterUpperCaseIdentifier(MinispecParser.UpperCaseIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#upperCaseIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitUpperCaseIdentifier(MinispecParser.UpperCaseIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MinispecParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MinispecParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#anyIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterAnyIdentifier(MinispecParser.AnyIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#anyIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitAnyIdentifier(MinispecParser.AnyIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(MinispecParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(MinispecParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(MinispecParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(MinispecParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#argFormal}.
	 * @param ctx the parse tree
	 */
	void enterArgFormal(MinispecParser.ArgFormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#argFormal}.
	 * @param ctx the parse tree
	 */
	void exitArgFormal(MinispecParser.ArgFormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#argFormals}.
	 * @param ctx the parse tree
	 */
	void enterArgFormals(MinispecParser.ArgFormalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#argFormals}.
	 * @param ctx the parse tree
	 */
	void exitArgFormals(MinispecParser.ArgFormalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(MinispecParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(MinispecParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(MinispecParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(MinispecParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#paramFormal}.
	 * @param ctx the parse tree
	 */
	void enterParamFormal(MinispecParser.ParamFormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#paramFormal}.
	 * @param ctx the parse tree
	 */
	void exitParamFormal(MinispecParser.ParamFormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#paramFormals}.
	 * @param ctx the parse tree
	 */
	void enterParamFormals(MinispecParser.ParamFormalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#paramFormals}.
	 * @param ctx the parse tree
	 */
	void exitParamFormals(MinispecParser.ParamFormalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MinispecParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MinispecParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#packageDef}.
	 * @param ctx the parse tree
	 */
	void enterPackageDef(MinispecParser.PackageDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#packageDef}.
	 * @param ctx the parse tree
	 */
	void exitPackageDef(MinispecParser.PackageDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#packageStmt}.
	 * @param ctx the parse tree
	 */
	void enterPackageStmt(MinispecParser.PackageStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#packageStmt}.
	 * @param ctx the parse tree
	 */
	void exitPackageStmt(MinispecParser.PackageStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(MinispecParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(MinispecParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#bsvImportDecl}.
	 * @param ctx the parse tree
	 */
	void enterBsvImportDecl(MinispecParser.BsvImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#bsvImportDecl}.
	 * @param ctx the parse tree
	 */
	void exitBsvImportDecl(MinispecParser.BsvImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(MinispecParser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(MinispecParser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeDefSynonym}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefSynonym(MinispecParser.TypeDefSynonymContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeDefSynonym}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefSynonym(MinispecParser.TypeDefSynonymContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeId(MinispecParser.TypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeId(MinispecParser.TypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeDefEnum}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefEnum(MinispecParser.TypeDefEnumContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeDefEnum}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefEnum(MinispecParser.TypeDefEnumContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeDefEnumElement}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefEnumElement(MinispecParser.TypeDefEnumElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeDefEnumElement}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefEnumElement(MinispecParser.TypeDefEnumElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#typeDefStruct}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefStruct(MinispecParser.TypeDefStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#typeDefStruct}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefStruct(MinispecParser.TypeDefStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#structMember}.
	 * @param ctx the parse tree
	 */
	void enterStructMember(MinispecParser.StructMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#structMember}.
	 * @param ctx the parse tree
	 */
	void exitStructMember(MinispecParser.StructMemberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarBinding(MinispecParser.VarBindingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarBinding(MinispecParser.VarBindingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterLetBinding(MinispecParser.LetBindingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitLetBinding(MinispecParser.LetBindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(MinispecParser.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(MinispecParser.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#moduleDef}.
	 * @param ctx the parse tree
	 */
	void enterModuleDef(MinispecParser.ModuleDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#moduleDef}.
	 * @param ctx the parse tree
	 */
	void exitModuleDef(MinispecParser.ModuleDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#moduleId}.
	 * @param ctx the parse tree
	 */
	void enterModuleId(MinispecParser.ModuleIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#moduleId}.
	 * @param ctx the parse tree
	 */
	void exitModuleId(MinispecParser.ModuleIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#moduleStmt}.
	 * @param ctx the parse tree
	 */
	void enterModuleStmt(MinispecParser.ModuleStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#moduleStmt}.
	 * @param ctx the parse tree
	 */
	void exitModuleStmt(MinispecParser.ModuleStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#submoduleDecl}.
	 * @param ctx the parse tree
	 */
	void enterSubmoduleDecl(MinispecParser.SubmoduleDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#submoduleDecl}.
	 * @param ctx the parse tree
	 */
	void exitSubmoduleDecl(MinispecParser.SubmoduleDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#inputDef}.
	 * @param ctx the parse tree
	 */
	void enterInputDef(MinispecParser.InputDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#inputDef}.
	 * @param ctx the parse tree
	 */
	void exitInputDef(MinispecParser.InputDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#methodDef}.
	 * @param ctx the parse tree
	 */
	void enterMethodDef(MinispecParser.MethodDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#methodDef}.
	 * @param ctx the parse tree
	 */
	void exitMethodDef(MinispecParser.MethodDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#ruleDef}.
	 * @param ctx the parse tree
	 */
	void enterRuleDef(MinispecParser.RuleDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#ruleDef}.
	 * @param ctx the parse tree
	 */
	void exitRuleDef(MinispecParser.RuleDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(MinispecParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(MinispecParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#functionId}.
	 * @param ctx the parse tree
	 */
	void enterFunctionId(MinispecParser.FunctionIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#functionId}.
	 * @param ctx the parse tree
	 */
	void exitFunctionId(MinispecParser.FunctionIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(MinispecParser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(MinispecParser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterMemberLvalue(MinispecParser.MemberLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitMemberLvalue(MinispecParser.MemberLvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterIndexLvalue(MinispecParser.IndexLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitIndexLvalue(MinispecParser.IndexLvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterSimpleLvalue(MinispecParser.SimpleLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitSimpleLvalue(MinispecParser.SimpleLvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sliceLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterSliceLvalue(MinispecParser.SliceLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sliceLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitSliceLvalue(MinispecParser.SliceLvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOperatorExpr(MinispecParser.OperatorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOperatorExpr(MinispecParser.OperatorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCondExpr(MinispecParser.CondExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCondExpr(MinispecParser.CondExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(MinispecParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(MinispecParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#caseExprItem}.
	 * @param ctx the parse tree
	 */
	void enterCaseExprItem(MinispecParser.CaseExprItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#caseExprItem}.
	 * @param ctx the parse tree
	 */
	void exitCaseExprItem(MinispecParser.CaseExprItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#binopExpr}.
	 * @param ctx the parse tree
	 */
	void enterBinopExpr(MinispecParser.BinopExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#binopExpr}.
	 * @param ctx the parse tree
	 */
	void exitBinopExpr(MinispecParser.BinopExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#unopExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnopExpr(MinispecParser.UnopExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#unopExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnopExpr(MinispecParser.UnopExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(MinispecParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(MinispecParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitConcat}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterBitConcat(MinispecParser.BitConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitConcat}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitBitConcat(MinispecParser.BitConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(MinispecParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(MinispecParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(MinispecParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(MinispecParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterReturnExpr(MinispecParser.ReturnExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitReturnExpr(MinispecParser.ReturnExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterStructExpr(MinispecParser.StructExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitStructExpr(MinispecParser.StructExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code undefinedExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterUndefinedExpr(MinispecParser.UndefinedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code undefinedExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitUndefinedExpr(MinispecParser.UndefinedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpr(MinispecParser.BlockExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpr(MinispecParser.BlockExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sliceExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterSliceExpr(MinispecParser.SliceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sliceExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitSliceExpr(MinispecParser.SliceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(MinispecParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(MinispecParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterFieldExpr(MinispecParser.FieldExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitFieldExpr(MinispecParser.FieldExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(MinispecParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(MinispecParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#memberBinds}.
	 * @param ctx the parse tree
	 */
	void enterMemberBinds(MinispecParser.MemberBindsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#memberBinds}.
	 * @param ctx the parse tree
	 */
	void exitMemberBinds(MinispecParser.MemberBindsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#memberBind}.
	 * @param ctx the parse tree
	 */
	void enterMemberBind(MinispecParser.MemberBindContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#memberBind}.
	 * @param ctx the parse tree
	 */
	void exitMemberBind(MinispecParser.MemberBindContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#beginEndBlock}.
	 * @param ctx the parse tree
	 */
	void enterBeginEndBlock(MinispecParser.BeginEndBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#beginEndBlock}.
	 * @param ctx the parse tree
	 */
	void exitBeginEndBlock(MinispecParser.BeginEndBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#regWrite}.
	 * @param ctx the parse tree
	 */
	void enterRegWrite(MinispecParser.RegWriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#regWrite}.
	 * @param ctx the parse tree
	 */
	void exitRegWrite(MinispecParser.RegWriteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MinispecParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MinispecParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MinispecParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MinispecParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmt(MinispecParser.CaseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmt(MinispecParser.CaseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#caseStmtItem}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmtItem(MinispecParser.CaseStmtItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#caseStmtItem}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmtItem(MinispecParser.CaseStmtItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#caseStmtDefaultItem}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmtDefaultItem(MinispecParser.CaseStmtDefaultItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#caseStmtDefaultItem}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmtDefaultItem(MinispecParser.CaseStmtDefaultItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinispecParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MinispecParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinispecParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MinispecParser.ForStmtContext ctx);
}