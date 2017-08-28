package AST;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 02/08/2017.
 */
public class SPL extends AST {
    private ArrayList<FunDecl> funDecls;

    public SPL() {
        funDecls = new ArrayList<>();
    }

    public void addFunDecl(FunDecl funDecl){
        funDecls.add(funDecl);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (FunDecl funDecl:funDecls) {
            result.append(funDecl).append("\n\n");
        }

        return result.toString();
    }
}
