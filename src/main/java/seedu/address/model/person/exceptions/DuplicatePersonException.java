package seedu.address.model.person.exceptions;

import seedu.address.model.exception.DuplicateEntityException;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends DuplicateEntityException {
    public DuplicatePersonException() {
        super("persons");
    }


}
