package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class FunCall_A_Field extends FunCall_A {
    private Field field;

    public FunCall_A_Field(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
