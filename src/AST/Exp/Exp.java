package AST.Exp;


import AST.AST;
import AST.Op2.Colon;
import AST.Type.Type;
import AST.Type.Type_List;

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

    public Type getType() throws IllegalStateException{
        if (term == null){
            return factor.getType();
        }
        else {
            if (term.getOp2().getClass() == Colon.class){
                Type factorType = factor.getType();
                Type termType = term.getType();
                if (!(factorType.equals(new Type_List(termType))) && !(new Type_List(factorType).equals(termType))){
                    throw new IllegalStateException("Type error");
                }
                if (new Type_List(factorType).equals(termType)){
                    return new Type_List(factorType);
                }
                throw new IllegalStateException("Type error");
            }
            else if (factor.getType().equals(term.getType())){
                throw new IllegalStateException("Type error");
            }
        }

        return factor.getType();
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
