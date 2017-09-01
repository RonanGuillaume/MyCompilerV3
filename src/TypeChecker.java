package TypeChecker;

import AST.*;
import AST.Type.*;

import java.util.ArrayList;
import java.util.Objects;

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
        types.add(new Basic_Int());

        retTypes.add(new RetType_Type(new Basic_Bool()));
        retTypes.add(new RetType_Type(new Basic_Int()));
        retTypes.add(new RetType_void());

        functions.add(new FunDecl("print", null, new FunType_A(new FunType(new FTypes_A(new FTypes(
                new Type_poly(), null)), new RetType_void()))));
        functions.add(new FunDecl("isEmpty", null, new FunType_A(new FunType(new FTypes_A(new FTypes(
                new Type_List(new Type_poly()), null)), new RetType_Type(new Basic_Bool())))));
    }

    public void addVariables(VarDecl varDecl){
        if (variables.contains(varDecl)){
            throw typeChechError("The variable " + varDecl.getName() + " already exist");
        }


//        if (varDecl.getClass() == VarDecl_type.class){
//            if (!varDecl.getType().equals(((VarDecl_type)varDecl).getExp().getType())){
//                throw typeChechError("Type error : variable defined as a " + varDecl.getType() + " but found a "
//                        + ((VarDecl_type)varDecl).getExp().getType());
//            }
//        }


        if (!types.contains(varDecl.getType())){
            types.add(varDecl.getType());
            retTypes.add(new RetType_Type(varDecl.getType()));
        }


        variables.add(varDecl);
    }

    public void addFunction(FunDecl funDecl){
        if (funDecl.getArgs().size() != funDecl.getTypes().size()){
            throw typeChechError("Number of arguments and types declared doesn't match (" + funDecl.getArgs().size()
                    + " arguments found but " + funDecl.getTypes().size() + " types found)");
        }

        if(functions.contains(funDecl)){
            throw typeChechError("The function " + funDecl.getId() + " already defined");
        }

        for (Type type : funDecl.getTypes()) {
            if (!types.contains(type)){
                throw typeChechError("The type "+ type + " was never defined");
            }
        }

        if (!retTypes.contains(funDecl.getReturnType())){
            throw typeChechError("The type "+ funDecl.getReturnType() + " was never defined");
        }

        if (funDecl.getReturnStmt() == null && funDecl.getReturnType().getClass() != RetType_void.class){
            throw typeChechError("The function "+ funDecl.getId() + " hasn't got any return clause");
        }

        functions.add(funDecl);
        variables.clear();

    }


    public IllegalArgumentException typeChechError(String msg){
        return new TypeCheckError("TypeChecker error : " + msg);
    }

    public void callFunction(String id, FunCall funCall) {
        boolean foundDecl = false;

        for (FunDecl funDecl : functions) {
            if (Objects.equals(funDecl.getId(), id)){
                foundDecl=true;
            }
        }

        if (!foundDecl){
            throw typeChechError("Method called "+ id +" unknown");
        }
    }
}


class TypeCheckError extends IllegalArgumentException {
    TypeCheckError(String s) {
        super(s);
    }
}