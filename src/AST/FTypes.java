package AST;

import AST.Type.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class FTypes extends AST{
    private Type type;
    private FTypes_A fTypes_a;

    public FTypes(Type type, FTypes_A fTypes_a) {
        this.type = type;
        this.fTypes_a = fTypes_a;
    }

    public Type getType() {
        return type;
    }

    public FTypes_A getfTypes_a() {
        return fTypes_a;
    }

    @Override
    public String toString() {
        String result = type.toString();

        if (fTypes_a != null){
            result += " " + fTypes_a;
        }

        return result;
    }
}
