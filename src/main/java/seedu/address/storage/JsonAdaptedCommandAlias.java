package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commandalias.CommandAlias;
import seedu.address.logic.commandalias.CommandWord;

public class JsonAdaptedCommandAlias {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Alias Set's %s field is missing!";

    private final String commandWord;
    private final List<String> aliases;

    /**
     * Constructs a {@code JsonAdaptedCommandAlias} with the given {@code String and @code List<String>}.
     */
    @JsonCreator
    public JsonAdaptedCommandAlias(@JsonProperty("word") String commandWord,
                                  @JsonProperty("aliases") List<String> aliases) {
        this.commandWord = commandWord;
        this.aliases = aliases;
    }

    /**
     * Converts a given {@code CommandAlias} into this class for Jackson use.
     */
    public JsonAdaptedCommandAlias(CommandAlias source) {
        this.commandWord = source.getCommandWord().getDefaultWord();
        this.aliases = source.getAllAliases();
    }

    /**
     * Converts this Jackson-friendly adapted commandAlias object into the model's {@code CommandAlias} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted commandAlias.
     */
    public CommandAlias toModelType() throws IllegalValueException {
        if (commandWord == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }
        CommandWord word = CommandWord.getCommandWord(commandWord);
        if (word == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }


        return new CommandAlias(word, this.aliases);
    }
}
