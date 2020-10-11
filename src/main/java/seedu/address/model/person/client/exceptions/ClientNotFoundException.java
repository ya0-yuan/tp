package seedu.address.model.person.client.exceptions;

import seedu.address.model.exception.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException() {
        super("client");
    }
}
