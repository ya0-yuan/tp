package seedu.address.model.appointment.exceptions;

import seedu.address.model.exception.EntityNotFoundException;

/**
 * Signals that the operation is unable to find the specified appointment.
 */
public class AppointmentNotFoundException extends EntityNotFoundException {
    public AppointmentNotFoundException() {
        super("appointment");
    }

}
