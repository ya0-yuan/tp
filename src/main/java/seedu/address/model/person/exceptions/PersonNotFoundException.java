package seedu.address.model.person.exceptions;

import seedu.address.model.exception.EntityNotFoundException;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException() {
        super("The person could not be found");
    }
}
