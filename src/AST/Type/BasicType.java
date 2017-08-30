package AST.Type;


/**
 * Created by ronan
 * on 28/08/2017.
 */
public abstract class BasicType extends Type{
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }
}
