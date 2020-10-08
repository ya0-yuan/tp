package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.Entity;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Represents an Appointment between a client and a hairdresser.
 */
public class Appointment implements Entity {

    private final PersonId clientId;
    private final PersonId hairdresserId;
    private final AppointmentDate date;
    private final AppointmentTime time;
    private final AppointmentStatus appointmentStatus;
    private final Client client;
    private final Hairdresser hairdresser;

    ///**
    // * Constructs a new {@code Appointment}. New appointments default to ACTIVE status.
    // *
    // * @param clientId      A valid personId.
    // * @param hairdresserId A valid personId.
    // * @param date          A valid appointment date
    // * @param time          A valid appointment time
    // */
    //public Appointment(PersonId clientId, PersonId hairdresserId, AppointmentDate date,
    //                   AppointmentTime time) {
    //
    //    this(clientId, hairdresserId, date, time, AppointmentStatus.ACTIVE);
    //}

    ///**
    // * Constructs an {@code Appointment} with a stated status.
    // *
    // * @param clientId      A valid personId.
    // * @param hairdresserId A valid personId.
    // * @param date          A valid appointment date
    // * @param time          A valid appointment time
    // */
    //public Appointment(PersonId clientId, PersonId hairdresserId, AppointmentDate date,
    //                   AppointmentTime time, AppointmentStatus appointmentStatus) {
    //    requireAllNonNull(clientId, hairdresserId, date, time, appointmentStatus);
    //    this.clientId = clientId;
    //    this.hairdresserId = hairdresserId;
    //    this.date = date;
    //    this.time = time;
    //    this.appointmentStatus = appointmentStatus;
    //}

    /**
     * Constructs an {@code Appointment} with a stated status.
     *
     * @param client      A valid client.
     * @param hairdresser A valid hairdresser.
     * @param date          A valid appointment date
     * @param time          A valid appointment time
     * @param appointmentStatus A valid appointmentStatus
     */
    public Appointment(Client client, Hairdresser hairdresser, AppointmentDate date,
                       AppointmentTime time, AppointmentStatus appointmentStatus) {
        requireAllNonNull(date, time, appointmentStatus);
        this.client = client;
        this.hairdresser = hairdresser;
        this.clientId = client.getId();
        this.hairdresserId = hairdresser.getId();
        this.date = date;
        this.time = time;
        this.appointmentStatus = appointmentStatus;
    }

    ///**
    // * Returns true if a given string is a valid appointment.
    // */
    //public static boolean isValidAppointment(String test) {
    //    return !test.trim().isEmpty();
    //}

    @Override
    public String toString() {
        String str = "";
        str += "Appointment - ";
        str += "Client ID: " + clientId;
        str += " Hairdresser ID: " + hairdresserId;
        str += " Date: " + date;
        str += " Time: " + time;
        return str;
    }

    public PersonId getClientId() {
        return clientId;
    }

    public PersonId getHairdresserId() {
        return hairdresserId;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Replaces the representation of the client in this appointment.
     * @param newClient the client to replace the existing.
     * @return a new Appointment object with client replaced by the new client.
     */
    public Appointment replaceClient(Client newClient) {
        return new Appointment(
            newClient,
            this.hairdresser,
            this.date,
            this.time,
            this.appointmentStatus
        );
    }

    /**
     * Deletes the representation of the client in this appointment.
     * @return a new Appointment object with client replaced by null.
     */
    public Appointment deleteClient() {
        return replaceClient(null);
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    /**
     * Replaces the representation of the Hairdresser in this appointment.
     * @param newHairdresser the Hairdresser to replace the existing.
     * @return a new Appointment object with Hairdresser replaced by the new Hairdresser.
     */
    public Appointment replaceHairdresser(Hairdresser newHairdresser) {
        return new Appointment(
            this.client,
            newHairdresser,
            this.date,
            this.time,
            this.appointmentStatus
        );
    }

    /**
     * Deletes the representation of the hairdresser in this appointment.
     * @return a new Appointment object with hairdresser replaced by null.
     */
    public Appointment deleteHairdresser() {
        return replaceHairdresser(null);
    }

    public AppointmentDate getDate() {
        return date;
    }

    public AppointmentTime getTime() {
        return time;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public boolean isSameAppointment(Appointment that) {
        return isSame(that);
    }

    @Override
    public boolean isSame(Entity that) {
        return this.equals(that);
    }

    /**
     * Checks if an appointment is in the past compared to system time.
     *
     * @return true if the appointment is in the past.
     */
    public boolean isPast() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime appointmentDateTime = LocalDateTime.of(date.date, time.time);

        return appointmentDateTime.isBefore(currentDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Appointment)) {
            return false;
        }

        Appointment that = (Appointment) o;

        if (!this.hairdresserId.equals(that.hairdresserId)) {
            return false;
        }

        if (!this.clientId.equals(that.clientId)) {
            return false;
        }

        if (!this.date.equals(that.date)) {
            return false;
        }

        if (!this.time.equals(that.time)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, hairdresserId, date, time);
    }
}
