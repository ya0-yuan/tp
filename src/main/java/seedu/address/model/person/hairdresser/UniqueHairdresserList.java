package seedu.address.model.person.hairdresser;

import java.util.List;

import seedu.address.model.UniqueEntityList;
import seedu.address.model.person.hairdresser.exception.DuplicateHairdresserException;
import seedu.address.model.person.hairdresser.exception.HairdresserNotFoundException;

public class UniqueHairdresserList extends UniqueEntityList<Hairdresser> {


    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        setEntity(target, editedHairdresser);
    }



    public void setHairdressers(UniqueHairdresserList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code hairdressers}.
     * {@code hairdressers} must not contain duplicate hairdressers.
     */
    public void setHairdressers(List<Hairdresser> clients) {
        setEntities(clients);
    }

    @Override
    public DuplicateHairdresserException duplicateException() {
        return new DuplicateHairdresserException();
    }

    @Override
    public HairdresserNotFoundException notFoundException() {
        return new HairdresserNotFoundException();
    }

}

