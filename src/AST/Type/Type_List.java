package AST.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_List extends Type {
    private Type type;

    public Type_List(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[ " + type + " ]";
    }
}
