package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.model.person.PersonIdCounter;

public class JsonAdaptedPersonIdCounter {
        private final int id;

    /**
     * Constructs a {@code JsonAdaptedPersonIdCounter} with the given {@code id}.
     */
    @JsonCreator
    protected JsonAdaptedPersonIdCounter(int id) {
        this.id = id;
    }

    /**
     * Converts a given {@code PersonIdCounter} into this class for Jackson use.
     */
    protected JsonAdaptedPersonIdCounter(PersonIdCounter source) {
        id = source.getCurrentMaxId();
    }

    @JsonValue
    public int getId() {
        return id;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PersonIdCounter} object.
     */
    public PersonIdCounter toModelType() {
        PersonIdCounter.getInstance().setCurrentMaxId(id);
        return PersonIdCounter.getInstance();
    }
}
