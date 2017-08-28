package AST.Exp;

import AST.FunCall;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Exp_Funcall extends Exp {
    private FunCall funCall;

    public Exp_Funcall(FunCall funCall) {
        this.funCall = funCall;
    }

    @Override
    public String toString() {
        return funCall.toString();
    }
}
