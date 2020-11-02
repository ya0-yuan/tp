package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HairStyleX;
import seedu.address.model.ReadOnlyHairStyleX;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * An Immutable HairStyleX that is serializable to JSON format.
 */
@JsonRootName(value = "hairstylex")
class JsonSerializableHairStyleX {

    public static final String MESSAGE_DUPLICATE_HAIRDRESSER = "Hairdressers list contains duplicate hairdresser(s).";
    public static final String MESSAGE_DUPLICATE_CLIENT = "Client list contains duplicate hairdresser(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";
    public static final String MISSING_ID_COUNTER_MESSAGE = "The ID counter format is corrupt!";

    private final List<JsonAdaptedHairdresser> hairdressers = new ArrayList<>();
    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final JsonAdaptedIdCounter idCounter;
    private final JsonAdaptedCommandShortcutSet aliasSet;
    /**
     * Constructs a {@code JsonSerializableHairStyleX} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHairStyleX(@JsonProperty("hairdressers") List<JsonAdaptedHairdresser> hairdressers,
                                      @JsonProperty("clients") List<JsonAdaptedClient> clients,
                                      @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                                      @JsonProperty("idCounter") JsonAdaptedIdCounter idCounter,
                                      @JsonProperty("aliasSet") JsonAdaptedCommandShortcutSet aliasSet) {
        this.hairdressers.addAll(hairdressers);
        this.clients.addAll(clients);
        this.appointments.addAll(appointments);
        this.idCounter = idCounter;
        this.aliasSet = aliasSet;
    }

    /**
     * Converts a given {@code ReadOnlyHairStyleX} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHairStyleX}.
     */
    public JsonSerializableHairStyleX(ReadOnlyHairStyleX source) {
        hairdressers.addAll(source.getHairdresserList().stream().map(JsonAdaptedHairdresser::new)
                .collect(Collectors.toList()));
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        idCounter = new JsonAdaptedIdCounter(source.getIdCounter());
        aliasSet = new JsonAdaptedCommandShortcutSet(source.getCommandAliasSet());
    }

    /**
     * Converts this HairStyleX into the model's {@code HairStyleX} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HairStyleX toModelType() throws IllegalValueException {
        HairStyleX hairStyleX = new HairStyleX();
        for (JsonAdaptedHairdresser jsonAdaptedHairdresser : hairdressers) {
            Hairdresser hairdresser = jsonAdaptedHairdresser.toModelType();
            if (hairStyleX.hasHairdresser(hairdresser)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_HAIRDRESSER);
            }
            hairStyleX.addHairdresser(hairdresser);
        }

        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (hairStyleX.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            hairStyleX.addClient(client);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (hairStyleX.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            hairStyleX.addAppointment(appointment);
        }
        if (idCounter == null) {
            throw new IllegalValueException(MISSING_ID_COUNTER_MESSAGE);
        }

        hairStyleX.setIdCounter(idCounter.toModelType());
        hairStyleX.setCommandAliasSet(aliasSet.toModelType());
        return hairStyleX;
    }

}
