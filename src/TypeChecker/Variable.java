package TypeChecker;

/**
 * Created by ronan
 * on 30/08/2017.
 */
public class Variable {
    private String name;
    private VariableType type;

    public Variable(String name, VariableType type) {
        this.name = name;
        this.type = type;
    }
}
