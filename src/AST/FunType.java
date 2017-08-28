package AST;

/**
 * Created by ronan
 * on 02/08/2017.
 */
public class FunType extends AST{
    private FunType_A funType_a;
    private RetType retType;

    public FunType(FunType_A funType_a, RetType retType) {
        this.funType_a = funType_a;
        this.retType = retType;
    }

    @Override
    public String toString() {
        String result = "";
        if (funType_a != null){
            result += funType_a;
        }
        return result + " -> " + retType;
    }
}
