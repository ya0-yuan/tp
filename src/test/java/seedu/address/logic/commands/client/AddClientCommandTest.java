package seedu.address.logic.commands.client;


import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HairStyleX;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHairStyleX;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        seedu.address.logic.commands.client.AddClientCommandTest.ModelStubAcceptingClientAdded modelStub =
                new seedu.address.logic.commands.client.AddClientCommandTest.ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddClientCommand(validClient).execute(modelStub);

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, validClient),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddClientCommand addCommand = new AddClientCommand(validClient);
        seedu.address.logic.commands.client.AddClientCommandTest.ModelStub modelStub =
                new seedu.address.logic.commands.client.AddClientCommandTest.ModelStubWithClient(validClient);

        assertThrows(CommandException.class, AddClientCommand.MESSAGE_DUPLICATE_CLIENT, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(alice);
        AddClientCommand addBobCommand = new AddClientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHairStyleXFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHairStyleXFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHairdresser(Hairdresser hairdresser) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHairStyleX(ReadOnlyHairStyleX newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHairStyleX getHairStyleX() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client getClientById(ClientId clientId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getAppointmentById(AppointmentId appointmentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasHairdresser(Hairdresser hairdresser) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHairdresser(Hairdresser target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Hairdresser> getFilteredHairdresserList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHairdresserList(Predicate<Hairdresser> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment changedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Appointment> getAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getSelectedAppointment() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Appointment> selectedAppointmentProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends seedu.address.logic.commands.client.AddClientCommandTest.ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSame(client);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends
            seedu.address.logic.commands.client.AddClientCommandTest.ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return clientsAdded.stream().anyMatch(person::isSame);
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSame);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyHairStyleX getHairStyleX() {
            return new HairStyleX();
        }
    }

}

