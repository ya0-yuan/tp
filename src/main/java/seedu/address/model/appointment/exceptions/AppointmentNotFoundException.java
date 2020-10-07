package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation is unable to find the specified appointment.
 */
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super("The Appointment could not be found");
    }
}
