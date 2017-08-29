package AST.Exp;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_exp extends Factor {
    private Exp exp;
    private next_Exp next_exp;

    public Factor_exp(Exp exp, next_Exp next_exp) {
        this.exp = exp;
        this.next_exp = next_exp;
    }

    @Override
    public String toString() {
        String result = "(" + exp;

        if (next_exp != null){
            result += " " + next_exp.toString();
        }

        result += ")";

        return result;
    }
}
