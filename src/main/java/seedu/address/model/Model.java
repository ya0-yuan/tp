package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Hairdresser> PREDICATE_SHOW_ALL_HAIRDRESSERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Return object Client with given id
     */
    Client getClientById(PersonId clientId);

    /**
     * Return object Hairdresser with given id
     */
    Hairdresser getHairdresserById(PersonId hairdresserId);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client client);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);


    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a hairdresser with the same identity as {@code hairdresser} exists in the address book.
     */
    boolean hasHairdresser(Hairdresser hairdresser);

    /**
     * Deletes the given hairdresser.
     * The hairdresser must exist in the address book.
     */
    void deleteHairdresser(Hairdresser target);

    /**
     * Adds the given hairdresser.
     * {@code hairdresser} must not already exist in the address book.
     */
    void addHairdresser(Hairdresser hairdresser);

    /**
     * Replaces the given hairdresser {@code target} with {@code editedHairdresser}.
     * {@code target} must exist in the address book.
     * The hairdresser identity of {@code editedHairdresser} must not be
     * the same as another existing hairdresser in the address book.
     */
    void setHairdresser(Hairdresser target, Hairdresser editedHairdresser);

    /** Returns an unmodifiable view of the filtered hairdresser list */
    ObservableList<Hairdresser> getFilteredHairdresserList();

    /**
     * Updates the filter of the filtered hairdresser list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHairdresserList(Predicate<Hairdresser> predicate);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the HairStyleX.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Adds the appointment.
     * {@code appointment} must not be a duplicate
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code changedAppointment}.
     * {@code target} must exist in the HairStyleX.
     * The new appointment must not be the same as another existing appointment in HairStyleX.
     */
    void setAppointment(Appointment target, Appointment changedAppointment);

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the selected appointment in the filtered appointment list.
     * null if no appointment is selected.
     */
    Appointment getSelectedAppointment();

    /**
     * Sets the selected appointment in the filtered appointment list.
     */
    void setSelectedAppointment(Appointment appointment);


    /**
     * Selected appointment in the filtered appointment list.
     * null if no appointment is selected.
     */
    ReadOnlyProperty<Appointment> selectedAppointmentProperty();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code appointment}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
