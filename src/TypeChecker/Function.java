package TypeChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronan
 * on 30/08/2017.
 */
public class Function {
    private String name;
    private ArrayList<Variable> arguments;
    private VariableType resultType;

    public Function(String name, List<Variable> arguments, VariableType resultType) {
        this.name = name;
        this.arguments = new ArrayList<Variable>(arguments);
        this.resultType = resultType;
    }
}
