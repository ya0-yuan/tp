package seedu.address.model.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ElementNotFoundException() {
        super("The Element could not be found");
    }
}
