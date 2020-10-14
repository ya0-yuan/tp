package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Entity;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person implements Entity {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final PersonId id;

    // Data fields
    private final Gender gender;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender) {
        requireAllNonNull(name, phone, email, gender);
        this.id = PersonIdCounter.getInstance().generateNewId();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    /**
     * This is an existing person and does not need to generate a new ID.
     */
    public Person(PersonId id, Name name, Phone phone, Email email, Gender gender) {
        requireAllNonNull(name, phone, email);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public PersonId getId() {
        return id;
    }

    public String getIdToString() {
        return String.valueOf(this.id);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone())
                        || otherPerson.getEmail().equals(getEmail())
                        || otherPerson.getId().equals(getId()));
    }

    @Override
    public boolean isSame(Entity other) {
        if (other instanceof Person) {
            return isSamePerson((Person) other);
        } else {
            return false;
        }

    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append((getGender().equals(Gender.MALE_GENDER) ? "Mr " : "Ms "))
                .append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail());
        return builder.toString();
    }

}
