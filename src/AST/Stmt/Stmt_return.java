package AST.Stmt;


import AST.Exp.Exp_A;
import AST.RetType;
import AST.RetType_Type;
import AST.RetType_void;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_return extends Stmt {
    private Exp_A exp_a;

    public Stmt_return(Exp_A exp_a) {
        this.exp_a = exp_a;
    }

    public RetType getRetType(){
        if (exp_a == null){
            return new RetType_void();
        }

        return new RetType_Type(exp_a.getExp().getType());
    }

    @Override
    public String toString() {
        String result = "return ";
        if (exp_a != null){
            result += exp_a;
        }
        return result + ";";
    }
}
