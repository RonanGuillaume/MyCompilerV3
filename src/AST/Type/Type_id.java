package AST.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_id extends Type {
    private String id;

    public Type_id(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
