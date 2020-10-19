package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.client.UniqueClientList;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.person.hairdresser.UniqueHairdresserList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueClientList clients;
    private final UniqueHairdresserList hairdressers;
    private final UniqueAppointmentList appointments;
    private final IdCounter idCounter;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {

        clients = new UniqueClientList();

        hairdressers = new UniqueHairdresserList();

        appointments = new UniqueAppointmentList();

        idCounter = IdCounter.getInstance();

    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the content of the idCounter with {@code idCounter}.
     * {@code appointments} must not contain duplicate entities.
     */
    public void setIdCounter(IdCounter idCounter) {
        this.idCounter.setCurrentMaxClientId(idCounter.getCurrentMaxClientId());
        this.idCounter.setCurrentMaxHairdresserId(idCounter.getCurrentMaxHairdresserId());
        this.idCounter.setCurrentMaxAppointmentId(idCounter.getCurrentMaxAppointmentId());
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setHairdressers(newData.getHairdresserList());
        setClients(newData.getClientList());
        setHairdressers(newData.getHairdresserList());
        setAppointments(newData.getAppointmentList());
        setIdCounter(newData.getIdCounter());
    }


    //===============hairdresser-level operations=============

    /**
     * Returns true if a hairdresser with the same identity as {@code hairdresser} exists in the address book.
     */
    public boolean hasHairdresser(Hairdresser hairdresser) {
        requireNonNull(hairdresser);
        return hairdressers.contains(hairdresser);
    }

    /**
     * Adds a hairdresser to the address book.
     * The hairdresser must not already exist in the address book.
     */
    public void addHairdresser(Hairdresser p) {
        hairdressers.add(p);
    }

    /**
     * Replaces the given hairdresser {@code target} in the list with {@code editedHairdresser}.
     * {@code target} must exist in the address book.
     * The hairdresser identity of {@code editedHairdresser} must not be the same as
     * another existing hairdresser in the address book.
     */
    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        requireNonNull(editedHairdresser);

        hairdressers.setHairdresser(target, editedHairdresser);
    }

    /**
     * Replaces the contents of the hairdresser list with {@code hairdressers}.
     * {@code hairdressers} must not contain duplicate persons.
     */
    public void setHairdressers(List<Hairdresser> hairdressers) {
        this.hairdressers.setHairdressers(hairdressers);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeHairdresser(Hairdresser key) {
        hairdressers.remove(key);
    }

    //============client level operations=================

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Replaces the given client {@code client} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //// util methods

    /**
     * Return object Client with given id
     */
    public Client getClientById(ClientId clientId) {
        requireNonNull(clientId);
        return clients.findClientById(clientId);
    }

    /**
     * Return object Hairdresser with given id
     */
    Hairdresser getHairdresserById(HairdresserId hairdresserId) {
        requireNonNull(hairdresserId);
        return hairdressers.findHairdresserById(hairdresserId);
    }

    /**
     * Return object Appointment with given id
     */
    Appointment getAppointmentById(AppointmentId appointmentId) {
        requireNonNull(appointmentId);
        return appointments.findAppointmentById(appointmentId);
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }


    // ================Appointment level operations ==============

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointment} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * When patient is modified, update patient info in appointment
     */
    public void updateAppointmentWhenClientIsUpdated(ClientId clientId, Client editedClient) {
        requireNonNull(clientId);

        appointments.updateClient(clientId, editedClient);
    }

    /**
     * Returns true if a duplicate {@code appointment} exists in HairStyleX.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment.
     * The appointment must not already exist.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code changedAppointment}.
     * {@code target} must exist in HairStyleX.
     * The new appointment must not be a duplicate of an existing appointment in HairStyleX.
     */
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        requireNonNull(changedAppointment);

        appointments.setAppointment(target, changedAppointment);
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    /**
     * Set client in appointments to null when the client with the id is deleted
     */
    public void updateAppointmentWhenClientDeleted(ClientId clientId) {
        requireNonNull(clientId);
        appointments.setClientToNull(clientId);
    }

    /**
     * Set hairdresser in appointments to null when the hairdresser with the id is deleted
     */
    public void updateAppointmentWhenHairdresserDeleted(HairdresserId hairdresserId) {
        requireNonNull(hairdresserId);
        appointments.setHairdresserToNull(hairdresserId);
    }

    /**
     * When hairdresser is modified, update hairdresser info in appointment
     */
    public void updateAppointmentWhenHairdresserIsUpdated(HairdresserId hairdresserId, Hairdresser editedHairdresser) {
        requireNonNull(hairdresserId);

        appointments.updateHairdresser(hairdresserId, editedHairdresser);
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }



    @Override
    public ObservableList<Hairdresser> getHairdresserList() {
        return hairdressers.asUnmodifiableObservableList();
    }

    @Override
    public IdCounter getIdCounter() {
        return idCounter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients)
                && hairdressers.equals(((AddressBook) other).hairdressers)
                && appointments.equals(((AddressBook) other).appointments)
                && idCounter.equals(((AddressBook) other).idCounter));
    }

    @Override
    public int hashCode() {
        return Objects.hash(hairdressers, appointments, idCounter, clients);
    }

}
