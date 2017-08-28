package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FArgs2_A extends AST {
    private String id;

    public FArgs2_A(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ", " + id;
    }
}
