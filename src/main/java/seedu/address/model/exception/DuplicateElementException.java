package seedu.address.model.exception;

public class DuplicateElementException extends RuntimeException {
    public DuplicateElementException(String errorMessage) {
        super(errorMessage);
    }

    public DuplicateElementException() {
        super("Operation would result in duplicate elements");
    }
}
