package AST;


/**
 * Created by ronan
 * on 03/08/2017.
 */
public class RetType_void extends RetType{
    public RetType_void() {
    }

    @Override
    public String toString() {
        return "Void";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == RetType_void.class;

    }
}
