package bmsc;
import api.antlr4.*;
import static api.antlr4.MinispecParser.*;
import static bmsc.GeneralizedIdentifier.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bunch of methods that will perform static elaboration
 * - Loop unroll
 * - If/case Integer
 * - Parametrics unroll
 * 
 * adapted fromm elaborator class in translate.cpp
 * @author boomza654
 *
 */
public class Elaborater {
    /**
     * Read the parsedFile (single one of them) and register type into the manager in the current scope (which should be the outermost minis[ec scope)
     * @param parsedFile the parsed File intance to parse
     * @param gidManager GIDManager to put register type/paramterics/value
     */
    public static void registerGidfromParsedFile(ParsedFile parsedFile, GeneralizedIdentifierManager gidManager) {
        PackageDefContext fileNode=(PackageDefContext)parsedFile.parserResult.parseTree();
        for(PackageStmtContext defNode: fileNode.packageStmt()) {
            // Register typedef
            if(defNode.typeDecl()!=null) {
                TypeDeclContext typeDefNode = defNode.typeDecl();
                GeneralizedIdentifier newGid=null;
                SemanticElement definition=null;
                // REgister Synonym typedef
                if(typeDefNode.typeDefSynonym()!=null) {
                    // Check if the type is parametric
                    TypeDefSynonymContext typeDefSynNode = typeDefNode.typeDefSynonym();
                    boolean isParametric = isTypeIdNodeParametric(typeDefSynNode.typeId(), gidManager);
                    if(isParametric) {
                        String name =typeDefSynNode.typeId().name.getText();
                        newGid= identifier(name);
                        definition= new Parametric(name,typeDefSynNode);
                    } else {
                        newGid = extractGidFromTypeIdNode(typeDefSynNode.typeId(), gidManager);
                        definition = new Type(newGid,extractGidFromTypeNode(typeDefSynNode.type(), gidManager));
                    }
                } else if (typeDefNode.typeDefEnum()!=null) {
                    TypeDefEnumContext typeDefEnumNode = typeDefNode.typeDefEnum();
                    String name = typeDefEnumNode.upperCaseIdentifier().getText();
                    newGid = identifier(name);
                    Type newEnumType = new Type(newGid, typeDefEnumNode);
                    definition =newEnumType;
                    // register each value of enum
                    int curEnumInt=0;
                    for(TypeDefEnumElementContext element: typeDefEnumNode.typeDefEnumElement()) {
                        String varName= element.upperCaseIdentifier().getText();
                        Integer value=null;
                        if(element.tagval!=null) {
                            value = Utility.getValueMinispecIntLiteral(element.tagval.getText());
                            curEnumInt= value.intValue()+1; // TODO: Change this to assigning Bit value to each enum instead
                        } else {
                            value= curEnumInt;
                            curEnumInt++;
                        }
                        Variable newEnumVar = new Variable(newEnumType,name);
                        newEnumVar.value=value;
                        gidManager.defineElement(identifier(varName), newEnumVar);
                    }
                } else if (typeDefNode.typeDefStruct()!=null) {
                    TypeDefStructContext typeDefStructNode = typeDefNode.typeDefStruct();
                    boolean isParametric = isTypeIdNodeParametric(typeDefStructNode.typeId(), gidManager);
                    if(isParametric) {
                        String name =typeDefStructNode.typeId().name.getText();
                        newGid= identifier(name);
                        definition= new Parametric(name,typeDefStructNode);
                    } else {
                        newGid = extractGidFromTypeIdNode(typeDefStructNode.typeId(), gidManager);
                        definition = new Type(newGid,typeDefStructNode);
                    }
                } else {
                    throw new RuntimeException ("WTF Error when encounter node typeDef: \n "+ typeDefNode.getText());
                }
                if(newGid!=null && definition!=null)
                gidManager.defineElement(newGid, definition);
                
            }
        }
    }
    
    /**
     * Convert Type Node to Semantic Type
     * @param typeNode  the parse Tree Type
     * @param GeneralizedIdnetifierManager gidManager (to pull value of some Integer / type in case needed)
     * @return Semanticelement of Type
     */
    public static GeneralizedIdentifier extractGidFromTypeNode (TypeContext typeNode, GeneralizedIdentifierManager gidManager) {
        List<Parameter> params = new ArrayList<>();
        if(typeNode.params()!=null) {
            for(ParamContext paramNode : typeNode.params().param()) {
                if(paramNode.type()!=null) {
                    params.add(new Parameter(null,extractGidFromTypeNode(paramNode.type(),gidManager)));
                } else if (paramNode.intParam!=null) {
                    Object value = evaluateExpressionNode(paramNode.expression(), gidManager);
                    assert value instanceof Integer: "The parameter is not compiletime unrolled";
                    params.add(new Parameter((Integer)value,null));
                }
            }
        }
        String name = typeNode.name.getText();
        return new GeneralizedIdentifier(name, params);
    }
    /**
     * Convert TypeId (should always be concrete) Node to Semantic Type
     * @param typeIdNode  the parse Tree TypeId (should be concrete)
     * @param GeneralizedIdnetifierManager gidManager (to pull value of some Integer / type in case needed)
     * @return Semanticelement of Type (if parametric return null)
     */
    public static GeneralizedIdentifier extractGidFromTypeIdNode (TypeIdContext typeIdNode, GeneralizedIdentifierManager gidManager) {
        List<Parameter> params = new ArrayList<>();
        if(typeIdNode.paramFormals()!=null) {
            for(ParamFormalContext paramFormalNode : typeIdNode.paramFormals().paramFormal()) {
                assert paramFormalNode.param()!=null: "Error the parameter is arametric not concrete";
                ParamContext paramNode = paramFormalNode.param();
                if(paramNode.type()!=null) {
                    params.add(new Parameter(null,extractGidFromTypeNode(paramNode.type(),gidManager)));
                } else if (paramNode.intParam!=null) {
                    Object value = evaluateExpressionNode(paramNode.expression(), gidManager);
                    assert value instanceof Integer: "The parameter is not compiletime unrolled";
                    params.add(new Parameter((Integer)value,null));
                }
            }
        }
        String name = typeIdNode.name.getText();
        return new GeneralizedIdentifier(name, params);
    }
    
    /**
     * see whether typeId Node is parametric
     * @param typeIdNode  the parse Tree TypeId (should be concrete)
     * @param GeneralizedIdnetifierManager gidManager (to pull value of some Integer / type in case needed)
     * @return whether typeId is parametric
     */
    public static boolean isTypeIdNodeParametric (TypeIdContext typeIdNode, GeneralizedIdentifierManager gidManager) {
        List<Parameter> params = new ArrayList<>();
        boolean isParametric=false;
        if(typeIdNode.paramFormals()!=null) {
            for(ParamFormalContext paramFormalNode : typeIdNode.paramFormals().paramFormal()) {
                if(paramFormalNode.param()==null) {isParametric=true; break;}
            }
        }
        return isParametric;
    }
    
    /**
     * 
     * @param exprNode
     * @param gidManager
     * @return
     */
    public static Object evaluateExpressionNode(ExpressionContext exprNode, GeneralizedIdentifierManager gidManager) {
        // Fake expressino evaluation
        try {
            return Utility.getValueMinispecIntLiteral(exprNode.getText());
        } catch (Exception e) {
            return 0;
        }
        //throw new RuntimeException("Unimplemented");
        
    }

}
