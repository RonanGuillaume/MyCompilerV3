package AST;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Field extends AST {
    private Position position;
    private Field_A field_a;

    public Field(Position position, Field_A field_a) {
        this.position = position;
        this.field_a = field_a;
    }

    @Override
    public String toString() {
        String result = "." + position;

        if (field_a != null){
            result += field_a;
        }

        return result;
    }
}
