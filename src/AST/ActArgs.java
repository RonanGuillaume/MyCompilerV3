package AST;

import AST.Exp.Exp;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class ActArgs extends AST {
    private Exp exp;
    private ActArgs2_A actArgs2_a;

    public ActArgs(Exp exp, ActArgs2_A actArgs2_a) {
        this.exp = exp;
        this.actArgs2_a = actArgs2_a;
    }

    @Override
    public String toString() {
        String result = exp.toString();

        if (actArgs2_a != null){
            result += actArgs2_a;
        }

        return result;
    }
}
