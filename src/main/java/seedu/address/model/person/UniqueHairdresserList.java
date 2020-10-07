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

public class UniqueHairdresserList implements Iterable<Hairdresser> {

    private final ObservableList<Hairdresser> internalList = FXCollections.observableArrayList();
    private final ObservableList<Hairdresser> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent doctor as the given argument.
     */
    public boolean contains(Hairdresser toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameHairdresser);
    }

    /**
     * Adds a doctor to the list.
     * The doctor must not already exist in the list.
     */
    public void add(Hairdresser toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException(); // TODO: to change to HairdresserException
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the doctor {@code target} in the list with {@code editedHairdresser}.
     * {@code target} must exist in the list.
     * The hairdresser identity of {@code editedHairdresser} must not be the same
     * as another existing hairdresser in the list.
     */
    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        requireAllNonNull(target, editedHairdresser);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameHairdresser(editedHairdresser) && contains(editedHairdresser)) {
            throw new DuplicatePersonException(); // TODO: to change to HairdresserException
        }

        internalList.set(index, editedHairdresser);
    }

    /**
     * Removes the equivalent hairdresser from the list.
     * The hairdresser must exist in the list.
     */
    public void remove(Hairdresser toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setHairdressers(UniqueHairdresserList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code hairdressers}.
     * {@code hairdressers} must not contain duplicate hairdressers.
     */
    public void setHairdressers(List<Hairdresser> hairdressers) {
        requireAllNonNull(hairdressers);
        if (!hairdressersAreUnique(hairdressers)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(hairdressers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Hairdresser> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Hairdresser> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueHairdresserList // instanceof handles nulls
                && internalList.equals(((UniqueHairdresserList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code hairdressers} contains only unique hairdressers.
     */
    private boolean hairdressersAreUnique(List<Hairdresser> hairdressers) {
        for (int i = 0; i < hairdressers.size() - 1; i++) {
            for (int j = i + 1; j < hairdressers.size(); j++) {
                if (hairdressers.get(i).isSameHairdresser(hairdressers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

