package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.exception.DuplicateElementException;
import seedu.address.model.exception.ElementNotFoundException;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.UniqueEntityList;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class UniqueAppointmentList extends UniqueEntityList<Appointment> {

    @Override
    public void remove(Appointment toRemove) {
        try {
            super.remove(toRemove);
        } catch (ElementNotFoundException ex) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    @Override
    public void add(Appointment toAdd) {
        try {
            super.add(toAdd);
        } catch (DuplicateElementException ex) {
            throw new DuplicateAppointmentException();
        }
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code changedAppointment}.
     * {@code target} must exist in the list.
     * The appointment details of {@code changedAppointment} must not be
     * the same as another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        try {
            setElement(target, changedAppointment);
        } catch (ElementNotFoundException ex) {
            throw new AppointmentNotFoundException();
        } catch (DuplicateElementException ex) {
            throw new DuplicateAppointmentException();
        }
    }

    /**
     * Replace the content of the list with another list.
     *
     * @param replacement the new list to replace the current list
     */
    public void setAppointments(UniqueAppointmentList replacement) {
        setElements(replacement);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        try {
            setElements(appointments);
        } catch (DuplicateElementException ex) {
            throw new DuplicateAppointmentException();
        }
    }

    /**
     * Update all appointments where a client is involved.
     *
     * @param toEdit       The Id of the client to be replaced.
     * @param editedClient The object representing the new client.
     */
    public void updateClient(PersonId toEdit, Client editedClient) {
        requireAllNonNull(toEdit, editedClient);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getClientId().equals(toEdit)) {
                Appointment newAppointment = currentAppointment.replaceClient(editedClient);
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Update all appointments where a hairdresser is involved.
     *
     * @param toEdit            The Id of the hairdresser to be replaced.
     * @param editedHairdresser The object representing the new hairdresser.
     */
    public void updateHairdresser(PersonId toEdit, Hairdresser editedHairdresser) {
        requireAllNonNull(toEdit, editedHairdresser);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getClientId().equals(toEdit)) {
                Appointment newAppointment = currentAppointment.replaceHairdresser(editedHairdresser);
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Update all appointments to reflect a deleted client.
     *
     * @param deleted id of the deleted client
     */
    public void setClientToNull(PersonId deleted) {
        requireAllNonNull(deleted);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getClientId().equals(deleted)) {
                Appointment newAppointment = currentAppointment.deleteClient();
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Update all appointments to reflect a deleted hairdresser.
     *
     * @param deleted id of the deleted client
     */
    public void setHairdresserToNull(PersonId deleted) {
        requireAllNonNull(deleted);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getHairdresserId().equals(deleted)) {
                Appointment newAppointment = currentAppointment.deleteHairdresser();
                internalList.set(i, newAppointment);
            }
        }
    }


}
