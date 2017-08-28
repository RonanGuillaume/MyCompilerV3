package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FunType_A extends AST {
    private FunType funType;

    public FunType_A(FunType funType) {
        this.funType = funType;
    }

    @Override
    public String toString() {
        return ":: "+funType;
    }
}
