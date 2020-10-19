package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Hairdresser> filteredHairdressers;
    private final FilteredList<Appointment> filteredAppointments;
    private final SimpleObjectProperty<Appointment> selectedAppointment = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        filteredHairdressers = new FilteredList<>(this.addressBook.getHairdresserList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public Client getClientById(ClientId clientId) {
        requireNonNull(clientId);
        return addressBook.getClientById(clientId);
    }

    @Override
    public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
        requireNonNull(hairdresserId);
        return addressBook.getHairdresserById(hairdresserId);
    }

    @Override
    public Appointment getAppointmentById(AppointmentId appointmentId) {
        requireNonNull(appointmentId);
        return addressBook.getAppointmentById(appointmentId);
    }

    //=========== Client =============================================================

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client client) {
        addressBook.removeClient(client);
        addressBook.updateAppointmentWhenClientDeleted(client.getId());
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
        addressBook.updateAppointmentWhenClientIsUpdated(target.getId(), editedClient);
    }

    //=========== Hairdresser =============================================================

    @Override
    public boolean hasHairdresser(Hairdresser person) {
        requireNonNull(person);
        return addressBook.hasHairdresser(person);
    }

    @Override
    public void deleteHairdresser(Hairdresser target) {
        addressBook.removeHairdresser(target);
        addressBook.updateAppointmentWhenHairdresserDeleted(target.getId());
    }

    @Override
    public void addHairdresser(Hairdresser person) {
        addressBook.addHairdresser(person);
        updateFilteredHairdresserList(PREDICATE_SHOW_ALL_HAIRDRESSERS);
    }

    @Override
    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        requireAllNonNull(target, editedHairdresser);

        addressBook.setHairdresser(target, editedHairdresser);
        addressBook.updateAppointmentWhenHairdresserIsUpdated(target.getId(), editedHairdresser);
    }

    //=========== Appointment =============================================================
    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        addressBook.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        requireAllNonNull(target, changedAppointment);

        addressBook.setAppointment(target, changedAppointment);
    }



    //=========== Filtered Client List Accessors =============================================================

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== Filtered Hairdresser List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Hairdresser} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Hairdresser> getFilteredHairdresserList() {
        return filteredHairdressers;
    }

    @Override
    public void updateFilteredHairdresserList(Predicate<Hairdresser> predicate) {
        requireNonNull(predicate);
        filteredHairdressers.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedDocX}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    //=========== Selected appointment ======================================================================

    @Override
    public ReadOnlyProperty<Appointment> selectedAppointmentProperty() {
        return selectedAppointment;
    }

    @Override
    public Appointment getSelectedAppointment() {
        return selectedAppointment.getValue();
    }

    @Override
    public void setSelectedAppointment(Appointment appointment) {
        if (appointment != null && !filteredAppointments.contains(appointment)) {
            throw new AppointmentNotFoundException();
        }
        selectedAppointment.setValue(appointment);
    }

    /**
     * Ensures {@code selectedAppointment} is a valid appointment in {@code filteredAppointments}.
     */
    private void ensureSelectedAppointmentIsValid(ListChangeListener.Change<? extends Appointment> change) {
        while (change.next()) {
            if (selectedAppointment.getValue() == null) {
                // null is always a valid selected appointment, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedAppointmentReplaced =
                    change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                            && change.getRemoved().contains(selectedAppointment.getValue());
            if (wasSelectedAppointmentReplaced) {
                // Update selectedAppointment to its new value.
                int index = change.getRemoved().indexOf(selectedAppointment.getValue());
                selectedAppointment.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedAppointmentRemoved = change.getRemoved().stream()
                    .anyMatch(removedAppointment -> selectedAppointment.getValue()
                            .isSameAppointment(removedAppointment));
            if (wasSelectedAppointmentRemoved) {
                // Select the appointment that came before it in the list,
                // or clear the selection if there is no such appointment.
                selectedAppointment.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs);
    }




}
