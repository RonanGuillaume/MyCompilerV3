package AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FTypes_A extends AST {
    private FTypes fTypes;

    public FTypes_A(FTypes fTypes) {
        this.fTypes = fTypes;
    }

    @Override
    public String toString() {
        String result = "";

        if (fTypes != null){
            result += fTypes;
        }

        return result;
    }
}


