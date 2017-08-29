package AST;

/**
 * Created by ronan
 * on 02/08/2017.
 */
public class FunType extends AST{
    private FTypes_A fTypes_a;
    private RetType retType;

    public FunType(FTypes_A fTypes_a, RetType retType) {
        this.fTypes_a = fTypes_a;
        this.retType = retType;
    }

    @Override
    public String toString() {
        String result = "";
        if (fTypes_a != null){
            result += fTypes_a;
        }
        return result + " -> " + retType;
    }
}
