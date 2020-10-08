package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.hairdresser.Hairdresser;

public class UniqueHairdresserList extends UniquePersonList<Hairdresser> {


    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        setPerson(target, editedHairdresser);
    }



    public void setHairdressers(UniqueHairdresserList replacement) {
        setPersons(replacement);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setHairdressers(List<Hairdresser> hairdressers) {
        setPersons(hairdressers);
    }

}

