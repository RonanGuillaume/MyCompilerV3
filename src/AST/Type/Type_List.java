package AST.Type;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_List extends Type {
    private Type type;

    public Type_List(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[ " + type + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != Type_List.class && obj.getClass() != Type_poly.class)){
            return false;
        }

        if (obj.getClass() != Type_poly.class){
            return true;
        }

        return type == ((Type_List)obj).type;
    }
}
