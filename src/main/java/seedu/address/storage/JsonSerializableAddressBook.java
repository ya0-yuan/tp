package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_HAIRDRESSER = "Hairdressers list contains duplicate hairdresser(s).";
    public static final String MESSAGE_DUPLICATE_CLIENT = "Client list contains duplicate hairdresser(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";
    public static final String MISSING_ID_COUNTER_MESSAGE = "The ID counter format is corrupt!";

    private final List<JsonAdaptedHairdresser> hairdressers = new ArrayList<>();
    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final JsonAdaptedIdCounter idCounter;
    private final JsonAdaptedCommandAliasSet aliasSet;
    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("hairdressers") List<JsonAdaptedHairdresser> hairdressers,
                                       @JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                                       @JsonProperty("idCounter") JsonAdaptedIdCounter idCounter,
                                       @JsonProperty("aliasSet") JsonAdaptedCommandAliasSet aliasSet) {
        this.hairdressers.addAll(hairdressers);
        this.clients.addAll(clients);
        this.appointments.addAll(appointments);
        this.idCounter = idCounter;
        this.aliasSet = aliasSet;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        hairdressers.addAll(source.getHairdresserList().stream().map(JsonAdaptedHairdresser::new)
                .collect(Collectors.toList()));
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        idCounter = new JsonAdaptedIdCounter(source.getIdCounter());
        aliasSet = new JsonAdaptedCommandAliasSet(source.getCommandAliasSet());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedHairdresser jsonAdaptedHairdresser : hairdressers) {
            Hairdresser hairdresser = jsonAdaptedHairdresser.toModelType();
            if (addressBook.hasHairdresser(hairdresser)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_HAIRDRESSER);
            }
            addressBook.addHairdresser(hairdresser);
        }

        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (addressBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            addressBook.addAppointment(appointment);
        }
        if (idCounter == null) {
            throw new IllegalValueException(MISSING_ID_COUNTER_MESSAGE);
        }

        addressBook.setIdCounter(idCounter.toModelType());
        addressBook.setCommandAliasSet(aliasSet.toModelType());
        return addressBook;
    }

}
