package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class ActArgs_A extends AST {
    private ActArgs actArgs;

    public ActArgs_A(ActArgs actArgs) {
        this.actArgs = actArgs;
    }

    @Override
    public String toString() {
        return actArgs.toString();
    }
}
