package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commandalias.CommandAlias;
import seedu.address.logic.commandalias.CommandAliasSet;


public class JsonAdaptedCommandAliasSet {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Alias Set's %s field is missing!";

    private final List<JsonAdaptedCommandAlias> aliasList;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code appointment}.
     */
    @JsonCreator
    public JsonAdaptedCommandAliasSet(@JsonProperty("aliasList") List<JsonAdaptedCommandAlias> aliasList) {
        this.aliasList = aliasList;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedCommandAliasSet(CommandAliasSet source) {
        this.aliasList = source.getListOfAliases().stream()
                .map(JsonAdaptedCommandAlias::new).collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public CommandAliasSet toModelType() throws IllegalValueException {
        if (aliasList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }
        List<CommandAlias> alias = new ArrayList<>();
        for (JsonAdaptedCommandAlias jsonAdaptedCommandAlias : aliasList) {
            CommandAlias commandAlias = jsonAdaptedCommandAlias.toModelType();
            alias.add(commandAlias);
        }

        CommandAliasSet.getInstance().setUpAliasSet(alias);
        return CommandAliasSet.getInstance();
    }
}
