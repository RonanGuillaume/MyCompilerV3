package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class FunCall_Args extends FunCall {
    private ActArgs_A actArgs_a;

    public FunCall_Args(ActArgs_A actArgs_a) {
        this.actArgs_a = actArgs_a;
    }

    @Override
    public String toString() {
        String result = "(";

        if (actArgs_a != null){
            result += actArgs_a;
        }

        return result+")";
    }
}
