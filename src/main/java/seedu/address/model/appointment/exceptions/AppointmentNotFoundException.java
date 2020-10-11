package seedu.address.model.appointment.exceptions;

import seedu.address.model.exception.ElementNotFoundException;

/**
 * Signals that the operation is unable to find the specified appointment.
 */
public class AppointmentNotFoundException extends ElementNotFoundException {
    public AppointmentNotFoundException() {
        super("The Appointment could not be found");
    }
}
