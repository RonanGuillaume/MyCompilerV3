package TypeChecker;

import java.util.Objects;

/**
 * Created by ronan
 * on 30/08/2017.
 */
public class VariableType {
    private String name;

    public VariableType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass()==VariableType.class && Objects.equals(((VariableType) obj).getName(), name);
    }
}
