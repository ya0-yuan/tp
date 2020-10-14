package seedu.address.model.person.client.exceptions;

import seedu.address.model.exception.DuplicateEntityException;

public class DuplicateClientException extends DuplicateEntityException {

    public DuplicateClientException() {
        super("clients");
    }
}
