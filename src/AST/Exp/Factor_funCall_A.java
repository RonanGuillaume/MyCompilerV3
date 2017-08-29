package AST.Exp;

import AST.FunCall;
import AST.FunCall_A;

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
    public String toString() {
        return id + " " + funCall_A.toString();
    }
}
