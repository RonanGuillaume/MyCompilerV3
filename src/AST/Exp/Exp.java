package AST.Exp;


import AST.AST;
import AST.Type.Type;

/**
 * Created by ronan
 * on 02/08/2017.
 */
public class Exp extends AST{
    private Factor factor;
    private Term term;

    public Exp(Factor factor, Term term) {
        this.factor = factor;
        this.term = term;
    }

    public Type getType(){
        return null;
    }

    @Override
    public String toString() {
        String result = factor.toString();

        if (term != null){
            result += " " + term;
        }

        return result;
    }
}
