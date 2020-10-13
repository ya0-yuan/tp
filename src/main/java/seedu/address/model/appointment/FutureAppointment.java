package seedu.address.model.appointment;

import seedu.address.model.appointment.exceptions.AppointmentNotInFutureException;
import seedu.address.model.person.PersonId;

/**
 * Represents an Appointment that must be created in the future compared to system time.
 */
public class FutureAppointment extends Appointment {

    public static final String MESSAGE_CONSTRAINT_FUTURE = "Appointments can only be made in the future";

    /**
     * Constructs an {@code Appointment} with a stated status.
     *
     * @param clientId            A valid client.
     * @param hairdresserId       A valid hairdresser.
     * @param date              A valid appointment date
     * @param time              A valid appointment time
     */
    public FutureAppointment(PersonId clientId, PersonId hairdresserId, AppointmentDate date, AppointmentTime time) {
        super(clientId, hairdresserId, date, time, AppointmentStatus.ACTIVE);
        //TODO: Should it be !isFuture() instead?
        if (isPast()) {
            throw new AppointmentNotInFutureException();
        }
    }
}
