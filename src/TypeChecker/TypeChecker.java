package TypeChecker;

import AST.*;
import AST.Type.*;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 30/08/2017.
 */
public class TypeChecker {
    private ArrayList<VarDecl> variables;
    private ArrayList<FunDecl> functions;
    private ArrayList<Type> types;
    private ArrayList<RetType> retTypes;

    public TypeChecker() {
        variables = new ArrayList<>();
        functions = new ArrayList<>();
        types = new ArrayList<>();
        retTypes = new ArrayList<>();

        types.add(new Basic_Bool());
        types.add(new Type_id("%"));
        types.add(new Basic_Int());

        retTypes.add(new RetType_Type(new Basic_Bool()));
        retTypes.add(new RetType_Type(new Basic_Int()));
        retTypes.add(new RetType_Type(new Type_id("%")));
        retTypes.add(new RetType_void());

//        functions.add(new FunDecl("print", new ))
    }

    public void addVariables(VarDecl varDecl){
        if (varDecl.getClass() == VarDecl_type.class){
//            if (types.indexOf() == -1){
//
//            }
        }
        else{

        }
    }
}


