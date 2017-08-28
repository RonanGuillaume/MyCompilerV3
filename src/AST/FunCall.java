package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FunCall extends AST {
    private String id;
    private ActArgs_A actArgs_a;

    public FunCall(String id, ActArgs_A actArgs_a) {
        this.id = id;
        this.actArgs_a = actArgs_a;
    }

    @Override
    public String toString() {
        String result = id + " (";

        if (actArgs_a != null){
            result += actArgs_a;
        }

        return result+")";
    }
}
