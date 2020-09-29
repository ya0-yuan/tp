package seedu.address.model.person.hairdresser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.Phone;
import seedu.address.model.specialisation.Specialisation;

/**
 * Represents a Hairdresser in HairStyleX.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Hairdresser extends Person {

    // Data fields
    private final Title title;
    private final Set<Specialisation> specList = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Hairdresser(Name name, Phone phone, Email email, Gender gender, Title title, Set<Specialisation> specList) {
        super(name, phone, email, gender);
        requireAllNonNull(title, specList);
        this.title = title;
        this.specList.addAll(specList);
    }

    /**
     * This is an existing hairdresser and does not need to generate a new ID.
     */
    public Hairdresser(PersonId id, Name name, Phone phone, Email email, Gender gender, Title title,
                       Set<Specialisation> specList) {
        super(id, name, phone, email, gender);
        requireAllNonNull(title, specList);
        this.title = title;
        this.specList.addAll(specList);
    }

    public Title getTitle() {
        return this.title;
    }

    /**
     * Returns an immutable specialisation set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Specialisation> getSpecs() {
        return Collections.unmodifiableSet(specList);
    }

    /**
     * Returns true if both hairdressers of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two hairdressers.
     */
    public boolean isSameHairdresser(Hairdresser otherHairdresser) {
        if (otherHairdresser == this) {
            return true;
        }

        return otherHairdresser != null
                && (otherHairdresser.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both hairdressers have the same identity and data fields.
     * This defines a stronger notion of equality between two hairdressers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Hairdresser)) {
            return false;
        }

        Hairdresser otherHairdresser = (Hairdresser) other;
        return otherHairdresser.getName().equals(getName())
                && otherHairdresser.getPhone().equals(getPhone())
                && otherHairdresser.getEmail().equals(getEmail())
                && otherHairdresser.getTitle().equals(getTitle())
                && otherHairdresser.getGender().equals(getGender())
                && otherHairdresser.getSpecs().equals(getSpecs());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, specList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(" Title: ")
                .append(getTitle())
                .append(" Specialisations: ");
        getSpecs().forEach(builder::append);
        return builder.toString();
    }
}
