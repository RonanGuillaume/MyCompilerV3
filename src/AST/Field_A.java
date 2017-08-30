package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Field_A extends AST {
    private Field field;

    public Field_A(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
