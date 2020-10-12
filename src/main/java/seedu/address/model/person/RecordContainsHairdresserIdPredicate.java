package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Tests that a {@code Hairdresser}'s {@code id} matches the given id.
 */
public class RecordContainsHairdresserIdPredicate implements Predicate<Hairdresser> {
    private final PersonId id;

    public RecordContainsHairdresserIdPredicate(PersonId id) {
        this.id = id;
    }

    @Override
    public boolean test(Hairdresser hairdresser) {
        return hairdresser.getId().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsHairdresserIdPredicate // instanceof handles nulls
                && this.id.equals(((RecordContainsHairdresserIdPredicate) other).id)); // state check
    }
}
