package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code changedAppointment}.
     * {@code target} must exist in the list.
     * The appointment details of {@code changedAppointment} must not be
     * the same as another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        requireAllNonNull(target, changedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(changedAppointment) && contains(changedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, changedAppointment);
    }

    /**
     * Replace the content of the list with another list.
     *
     * @param replacement the new list to replace the current list
     */
    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
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

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueAppointmentList // instanceof handles nulls
            && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
