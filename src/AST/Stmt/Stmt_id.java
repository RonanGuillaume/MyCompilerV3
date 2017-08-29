package AST.Stmt;

import AST.FunCall;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_id extends Stmt {
    private FunCall funCall;

    public Stmt_id(FunCall funCall) {
        this.funCall = funCall;
    }

    @Override
    public String toString() {
        return funCall + ";";
    }
}
