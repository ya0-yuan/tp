package seedu.address.model.appointment.exceptions;

import seedu.address.model.exception.DuplicateEntityException;

/**
 * Signals that the operation will result in duplicate Appointment
 * (Appointments are considered duplicates if they have the same
 * client, hairdresser, date, and time).
 */
public class DuplicateAppointmentException extends DuplicateEntityException {
    public DuplicateAppointmentException() {
        super("appointments");
    }
}
