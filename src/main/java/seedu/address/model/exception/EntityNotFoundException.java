package seedu.address.model.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public EntityNotFoundException() {
        super("The Element could not be found");
    }
}
