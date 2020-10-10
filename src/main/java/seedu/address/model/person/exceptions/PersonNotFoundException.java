package seedu.address.model.person.exceptions;

import seedu.address.model.exception.ElementNotFoundException;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends ElementNotFoundException {
    public PersonNotFoundException() {
        super("The person could not be found");
    }
}
