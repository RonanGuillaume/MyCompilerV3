package AST.Exp;

import AST.next_Exp;
import AST.second_Exp;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Exp_Pair extends Exp{
    private Exp exp;
    private next_Exp next_exp;
    private second_Exp second_exp;

    public Exp_Pair(Exp exp, next_Exp next_exp, second_Exp second_exp) {
        this.exp = exp;
        this.next_exp = next_exp;
        this.second_exp = second_exp;
    }

    @Override
    public String toString() {
        String result = "( " + exp;

        if (next_exp != null){
            result += " " + next_exp;
        }
        result += " )";
        if (second_exp != null){
            result += " " + second_exp;
        }
        return result;
    }
}
