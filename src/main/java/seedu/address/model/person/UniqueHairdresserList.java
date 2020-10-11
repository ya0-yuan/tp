package seedu.address.model.person;

import java.util.List;

import seedu.address.model.person.hairdresser.Hairdresser;

public class UniqueHairdresserList extends UniquePersonList<Hairdresser> {


    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        setPerson(target, editedHairdresser);
    }



    public void setHairdressers(UniqueHairdresserList replacement) {
        setPersons(replacement);
    }

    /**
     * Replaces the contents of this list with {@code hairdressers}.
     * {@code hairdressers} must not contain duplicate hairdressers.
     */
    public void setHairdressers(List<Hairdresser> hairdressers) {
        setPersons(hairdressers);
    }

}

