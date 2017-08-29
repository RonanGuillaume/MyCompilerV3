package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class ActArgs2_A extends AST {
    private ActArgs actArgs;

    public ActArgs2_A(ActArgs actArgs) {
        this.actArgs = actArgs;
    }

    @Override
    public String toString() {
        return ", " + actArgs;
    }
}
