package bmsc;

import static bmsc.GeneralizedIdentifier.identifier;

import java.util.ArrayList;
import java.util.List;

import api.antlr4.MinispecParser.ParamContext;
import api.antlr4.MinispecParser.ParamFormalContext;
import api.antlr4.MinispecParser.TypeContext;
import api.antlr4.MinispecParser.VarExprContext;

/**
 * Collection of function related to extractin GID from Node
 * @author boomza654
 *
 */
public class GidExtracter{
    
    /**
     * Try to extract GId of a type and also register them if the type did not exist before but its parametrized version exist)
     * 
     * @param ctx
     * @param gidManager
     * @return Generalized identifier from the extraction
     */
    public static GeneralizedIdentifier extractGidAndRegisterType(TypeContext ctx, GeneralizedIdentifierManager gidManager) {

        List<ParamContext> paramNodes= ctx.params()!=null?ctx.params().param():List.of();
        GeneralizedIdentifier gid = GidExtracter.extractGid(ctx.name.getText(), paramNodes, gidManager);
        tryRegisterType(gid,gidManager);
//        if(gidManager.getType(gid)==null && gidManager.getParametric(ctx.name.getText())!=null) {
//            // REgister new Type
//            Object definition = gidManager.getParametric(ctx.name.getText()).definition;
//            Utility.println("Define new Type "+ gid+" from parametric");
//            gidManager.defineTypeAtTop(gid, new Type(gid,definition));
//        }
        return gid;
    }
    /**
     * try register the gid and its subtype in the type def
     * recursively define new stuff
     * @param gid
     * @param gidManager
     */
    public static void tryRegisterType(GeneralizedIdentifier gid, GeneralizedIdentifierManager gidManager) {
        //Utility.println("Try register "+gid);
        if(gidManager.getType(gid)==null && gidManager.getParametric(gid.name)!=null) {
            // REgister new Type
            Object definition = gidManager.getParametric(gid.name).definition;
            Utility.println("Define new Type "+ gid+" from parametric");
            Type newType =new Type(gid,definition);
            newType.toSynth=gidManager.getParametric(gid.name).toSynth;
            gidManager.defineTypeAtTop(gid, newType);
        }
        if(gidManager.getType(gid)==null && gidManager.getParametric(gid.name)==null) {
            //Utility.println(gid.name+" is Bluespec Type");
            for(int i=0;i<gid.params.size();i++) {
                if(gid.params.get(i).gid!=null) tryRegisterType(gid.params.get(i).gid, gidManager);
            }
        }
    }
    
    /**
     * Try to extract GId of a Function name and also register them if the function name did not exist before but its parametrized version exist)
     * 
     * @param ctx
     * @param gidManager
     * @return Generalized identifier from the extraction
     */
    public static GeneralizedIdentifier extractGidAndRegisterFunc(VarExprContext ctx, GeneralizedIdentifierManager gidManager) {
        if(ctx.params()==null) {
            String name = ctx.var.getText();
            Func foundFunc = gidManager.getFunc(identifier(name));
            Variable foundVar = gidManager.getVar(name);
            if(foundFunc==null && foundVar==null) {
                Utility.println("Warning "+name+" is not either Minispec function or varaible");
                // Still okay cuz this might be lusepc function
            }
            return identifier(ctx.var.getText());
        }
        
        List<ParamContext> paramNodes= ctx.params().param();
        GeneralizedIdentifier gid = GidExtracter.extractGid(ctx.var.getText(), paramNodes, gidManager);
        Func foundFunc = gidManager.getFunc(gid);
        Parametric foundParametric = gidManager.getParametric(ctx.var.getText());
        if(foundFunc==null) {
            if(foundParametric!=null) {
                // call func#(4) but never declared func#(4)
                // add new func
                Utility.println("Define new Function "+gid+" from parametric");
                Func newFunc = new Func(gid,foundParametric.definition);
                newFunc.toSynth=foundParametric.toSynth;
                gidManager.defineFunc(gid, newFunc);
            }else {
                // Error case: Paremetric call but nothing is found but let it slide cuz maybe some Bluespec thing is parametric
                Utility.println("Warning "+gid+" has no defined function / parametric asssociated with it");
            }
        } // Else the func#(4) is already defined either by user / by prior synthesizing
        // Register eac type as well
        gid.params.forEach((p)->{if(p.gid!=null) tryRegisterType(p.gid,gidManager);});
        return gid;
    }
    /**
     * Create a generalized identifier
     * @param name the name of gID
     * @param paramNodes the param Node needed to be synthesized
     * @param gidManager the gidManager to extract integer value from
     * @return Gid of name#(paramNodes)
     */
    public static GeneralizedIdentifier extractGid (String name, List<ParamContext> paramNodes , GeneralizedIdentifierManager gidManager) {
        List<Parameter> params = new ArrayList<>();
        if(paramNodes.isEmpty() && gidManager.getType(identifier(name))!=null && gidManager.getType(identifier(name)).definition instanceof GeneralizedIdentifier ) {
            // Minispec static typedef need substitution
            return (GeneralizedIdentifier)gidManager.getType(identifier(name)).definition;
        }
        if(!paramNodes.isEmpty()) {
            for(ParamContext paramNode : paramNodes) {
                if(paramNode.type()!=null) {
                    TypeContext paramType = paramNode.type();
                    List<ParamContext> typeParamNodes = paramType.params()!=null? paramType.params().param():List.of();
                    GeneralizedIdentifier toAdd =extractGid(paramType.name.getText(),typeParamNodes,gidManager);
                    params.add(new Parameter(null,toAdd));
                    
                } else if (paramNode.intParam!=null) {
                    Object value = ExpressionEvaluator.evaluate(paramNode.expression(), gidManager);
                    assert value instanceof Integer: "The parameter is not compiletime unrolled";
                    params.add(new Parameter((Integer)value,null));
                }
            }
        } 
        return new GeneralizedIdentifier(name, params);
    }
    /**
     * Create a generalized identifier
     * @param name the name of gID
     * @param paramFormalNodes the paramforml Node needed to be synthesized ( must be concrete)
     * @param gidManager the gidManager to extract integer value from
     * @return Gid of name#(paramNodes)
     */
    public static GeneralizedIdentifier extractGidFormal (String name, List<ParamFormalContext> paramFormalNodes , GeneralizedIdentifierManager gidManager) {
        List<ParamContext> paramNodes = new ArrayList<>();
        for(ParamFormalContext paramFormalNode : paramFormalNodes) {
            ParamContext paramNode= paramFormalNode.param();
            assert paramNode!=null : "paramFormal is not concrete";
            paramNodes.add(paramNode);
        }
        return extractGid(name,paramNodes,gidManager);
    }
    /**
     * Return whether in those Param Formal nodes it is all concrete
     * @param paramFormalNodes
     * @return boolean
     */
    public static boolean isParametric(List<ParamFormalContext> paramFormalNodes) {
        boolean out=false;
        for(ParamFormalContext paramFormalNode : paramFormalNodes) {
            if(paramFormalNode.param()==null) {out=true; break;}
        }
        return out;
    }
}