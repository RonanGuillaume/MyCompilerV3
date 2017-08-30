package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Position extends AST {
    private String id;

    public Position(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
