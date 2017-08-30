package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FArgs2_A extends AST {
    private String id;
    private FArgs2_A fArgs2_a;

    public FArgs2_A(String id, FArgs2_A fArgs2_a) {
        this.id = id;
        this.fArgs2_a = fArgs2_a;
    }

    @Override
    public String toString() {
        String result = ", " + id;

        if (fArgs2_a != null){
            result += " " + fArgs2_a;
        }

        return result;
    }
}
