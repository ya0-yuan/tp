package seedu.address.model.person.exceptions;

import seedu.address.model.exception.DuplicateElementException;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends DuplicateElementException {
    public DuplicatePersonException() {
        super("Operation would result in duplicate persons");
    }
}
