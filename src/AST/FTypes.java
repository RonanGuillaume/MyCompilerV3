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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != FTypes.class){
            return false;
        }

        FTypes fTypes = (FTypes)obj;

        if (fTypes_a == null && fTypes.fTypes_a == null){
            return type.equals(fTypes.getType());
        }

        if (fTypes_a != null && fTypes.fTypes_a == null){
            return false;
        }

        if (fTypes_a == null && fTypes.fTypes_a != null){
            return false;
        }

        return type.equals(fTypes.getType()) && fTypes_a.equals(fTypes.getfTypes_a());
    }
}
