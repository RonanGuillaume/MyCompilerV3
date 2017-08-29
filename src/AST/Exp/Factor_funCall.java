package AST.Exp;

import AST.FunCall;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_funCall extends Factor {
    private FunCall funCall;

    public Factor_funCall(FunCall funCall) {
        this.funCall = funCall;
    }

    @Override
    public String toString() {
        return funCall.toString();
    }
}
