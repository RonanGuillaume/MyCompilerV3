package AST.Stmt;


import AST.Exp.Exp_A;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_return extends Stmt {
    private Exp_A exp_a;

    public Stmt_return(Exp_A exp_a) {
        this.exp_a = exp_a;
    }

    @Override
    public String toString() {
        String result = "return";
        if (exp_a != null){
            result += exp_a;
        }
        return result + " ;";
    }
}
