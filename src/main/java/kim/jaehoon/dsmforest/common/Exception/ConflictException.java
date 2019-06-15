package kim.jaehoon.dsmforest.common.Exception;

public class ConflictException extends RuntimeException {
    private final String field;

    public ConflictException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
