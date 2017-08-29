package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class FunCall_A extends AST {
    private ActArgs_A actArgs_a;

    public FunCall_A(ActArgs_A actArgs_a) {
        this.actArgs_a = actArgs_a;
    }

    @Override
    public String toString() {
        return "(" + actArgs_a + ")";
    }
}
