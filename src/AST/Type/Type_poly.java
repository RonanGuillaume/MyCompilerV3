package AST.Type;

/**
 * Created by ronan
 * on 01/09/2017.
 */
public class Type_poly extends Type {
    @Override
    public boolean equals(Object o) {
        return o.getClass() == Type.class;
    }

    @Override
    public String toString() {
        return "T";
    }
}
