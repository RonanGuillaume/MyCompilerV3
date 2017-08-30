package TypeChecker;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 30/08/2017.
 */
public class TypeChecker {
    private ArrayList<Variable> variables;
    private ArrayList<Function> functions;
    private ArrayList<VariableType> types;

    public TypeChecker() {
        variables = new ArrayList<Variable>();
        functions = new ArrayList<Function>();
        types = new ArrayList<VariableType>();

        ArrayList<Variable> printArguments = new ArrayList<Variable>();
        printArguments.add(new Variable("T", new VariableType("T")));

        ArrayList<Variable> isEmptyArguments = new ArrayList<Variable>();
        isEmptyArguments.add(new Variable("T", new VariableType("T")));


        functions.add(new Function("print", printArguments, new VariableType("String")));
    }
}


