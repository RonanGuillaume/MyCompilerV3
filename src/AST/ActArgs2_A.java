package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class ActArgs2_A extends AST {
    private ActArgs_A actArgs_a;

    public ActArgs2_A(ActArgs_A actArgs_a) {
        this.actArgs_a = actArgs_a;
    }

    @Override
    public String toString() {
        String result = "";
        if(actArgs_a != null){
            result += actArgs_a;
        }

        return result;
    }
}
