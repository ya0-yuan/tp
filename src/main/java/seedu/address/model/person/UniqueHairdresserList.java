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
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setHairdressers(List<Hairdresser> hairdressers) {
        setPersons(hairdressers);
    }

}

