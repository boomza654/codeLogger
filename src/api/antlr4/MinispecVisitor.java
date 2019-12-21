package api.antlr4;
// Generated from Minispec.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinispecParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinispecVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MinispecParser#lowerCaseIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLowerCaseIdentifier(MinispecParser.LowerCaseIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#upperCaseIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpperCaseIdentifier(MinispecParser.UpperCaseIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MinispecParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#anyIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyIdentifier(MinispecParser.AnyIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(MinispecParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(MinispecParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#argFormal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgFormal(MinispecParser.ArgFormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#argFormals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgFormals(MinispecParser.ArgFormalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(MinispecParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(MinispecParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#paramFormal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamFormal(MinispecParser.ParamFormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#paramFormals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamFormals(MinispecParser.ParamFormalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MinispecParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#packageDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDef(MinispecParser.PackageDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#packageStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageStmt(MinispecParser.PackageStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(MinispecParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#bsvImportDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBsvImportDecl(MinispecParser.BsvImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(MinispecParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeDefSynonym}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefSynonym(MinispecParser.TypeDefSynonymContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeId(MinispecParser.TypeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeDefEnum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefEnum(MinispecParser.TypeDefEnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeDefEnumElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefEnumElement(MinispecParser.TypeDefEnumElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#typeDefStruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefStruct(MinispecParser.TypeDefStructContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#structMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructMember(MinispecParser.StructMemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarBinding(MinispecParser.VarBindingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letBinding}
	 * labeled alternative in {@link MinispecParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBinding(MinispecParser.LetBindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#varInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInit(MinispecParser.VarInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#moduleDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDef(MinispecParser.ModuleDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#moduleId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleId(MinispecParser.ModuleIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#moduleStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleStmt(MinispecParser.ModuleStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#submoduleDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubmoduleDecl(MinispecParser.SubmoduleDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#inputDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputDef(MinispecParser.InputDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#methodDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDef(MinispecParser.MethodDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#ruleDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleDef(MinispecParser.RuleDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(MinispecParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#functionId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionId(MinispecParser.FunctionIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#varAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssign(MinispecParser.VarAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberLvalue(MinispecParser.MemberLvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexLvalue(MinispecParser.IndexLvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleLvalue(MinispecParser.SimpleLvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sliceLvalue}
	 * labeled alternative in {@link MinispecParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceLvalue(MinispecParser.SliceLvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorExpr(MinispecParser.OperatorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondExpr(MinispecParser.CondExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MinispecParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(MinispecParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#caseExprItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExprItem(MinispecParser.CaseExprItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#binopExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinopExpr(MinispecParser.BinopExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#unopExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnopExpr(MinispecParser.UnopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(MinispecParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitConcat}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitConcat(MinispecParser.BitConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(MinispecParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(MinispecParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnExpr(MinispecParser.ReturnExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructExpr(MinispecParser.StructExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code undefinedExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUndefinedExpr(MinispecParser.UndefinedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpr(MinispecParser.BlockExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sliceExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceExpr(MinispecParser.SliceExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(MinispecParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fieldExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldExpr(MinispecParser.FieldExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MinispecParser#exprPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(MinispecParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#memberBinds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberBinds(MinispecParser.MemberBindsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#memberBind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberBind(MinispecParser.MemberBindContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#beginEndBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginEndBlock(MinispecParser.BeginEndBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#regWrite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegWrite(MinispecParser.RegWriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MinispecParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MinispecParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#caseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStmt(MinispecParser.CaseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#caseStmtItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStmtItem(MinispecParser.CaseStmtItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#caseStmtDefaultItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStmtDefaultItem(MinispecParser.CaseStmtDefaultItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinispecParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MinispecParser.ForStmtContext ctx);
}