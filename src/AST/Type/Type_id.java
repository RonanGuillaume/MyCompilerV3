package AST.Type;

import java.util.Objects;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Type_id extends Type {
    private String id;

    public Type_id(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != Type_id.class && obj.getClass() != Type_poly.class)){
            return false;
        }

        if (obj.getClass() != Type_poly.class){
            return true;
        }

        return Objects.equals(id, ((Type_id) obj).id);
    }
}
