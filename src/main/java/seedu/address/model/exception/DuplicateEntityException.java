package seedu.address.model.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String errorMessage) {
        super(errorMessage);
    }

    public DuplicateEntityException() {
        super("Operation would result in duplicate entities");
    }
}
