package AST;

import AST.Type.Type;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public class RetType_Type extends RetType {
    private Type type;

    public RetType_Type(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
