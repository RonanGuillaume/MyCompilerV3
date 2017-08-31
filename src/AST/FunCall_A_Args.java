package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class FunCall_A_Args extends FunCall_A {
    private ActArgs_A actArgs_a;

    public FunCall_A_Args(ActArgs_A actArgs_a) {
        this.actArgs_a = actArgs_a;
    }

    public ActArgs_A getActArgs_a() {
        return actArgs_a;
    }

    @Override
    public String toString() {
        String result = "(";

        if (actArgs_a != null){
            result += actArgs_a;
        }

        result += ")";
        return result;
    }
}
