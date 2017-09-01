package AST.Exp;

import AST.FunCall;
import AST.FunCall_A;
import AST.FunCall_A_Args;
import AST.Type.Type;
import AST.Type.Type_poly;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_funCall_A extends Factor {
    private String id;
    private FunCall_A funCall_A;

    public Factor_funCall_A(String id, FunCall_A funCall_A) {
        this.id = id;
        this.funCall_A = funCall_A;
    }

    @Override
    public Type getType() {
        return new Type_poly();
    }

    @Override
    public String toString() {
        String result = id;

        if (funCall_A!=null){
            result += " " + funCall_A;
        }
        return result;
    }
}
