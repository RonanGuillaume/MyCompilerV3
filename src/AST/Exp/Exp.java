package AST.Exp;


import AST.Term;
import AST.AST;

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

    @Override
    public String toString() {
        String result = factor.toString();

        if (term != null){
            result += " " + term;
        }

        return result;
    }
}
