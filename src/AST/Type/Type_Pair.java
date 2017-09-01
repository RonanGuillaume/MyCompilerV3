package AST.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_Pair extends Type {
    private Type type1;
    private Type type2;

    public Type_Pair(Type type1, Type type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    @Override
    public String toString() {
        return "( " + type1 + ", " + type2 + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != Type_Pair.class && obj.getClass() != Type_poly.class)){
            return false;
        }

        if (obj.getClass() != Type_poly.class){
            return true;
        }

        return type1 == ((Type_Pair) obj).type1 && type2 == ((Type_Pair) obj).type2;

    }
}
