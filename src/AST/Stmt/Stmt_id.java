package AST.Stmt;

import AST.FunCall;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_id extends Stmt {
    private String id;
    private FunCall funCall;

    public Stmt_id(String id, FunCall funCall) {
        this.id = id;
        this.funCall = funCall;
    }

    @Override
    public String toString() {
        String result = id;

        if (funCall != null){
            result += " " + funCall;
        }

        return result + ";";
    }
}
