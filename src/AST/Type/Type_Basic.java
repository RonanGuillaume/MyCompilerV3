package AST.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_Basic extends Type {
    private BasicType basicType;

    public Type_Basic(BasicType basicType) {
        this.basicType = basicType;
    }

    @Override
    public String toString() {
        return basicType.toString();
    }
}
