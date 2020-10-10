package seedu.address.model.person;

import java.util.List;

import seedu.address.model.exception.DuplicateElementException;
import seedu.address.model.exception.ElementNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;


/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList<T extends Person> extends UniqueEntityList<T> {


    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(T target, T editedPerson) {
        try {
            setElement(target, editedPerson);
        } catch (ElementNotFoundException ex) {
            throw new PersonNotFoundException();
        } catch (DuplicateElementException ex) {
            throw new DuplicatePersonException();
        }
    }

    @Override
    public void remove(T toRemove) {
        try {
            super.remove(toRemove);
        } catch (ElementNotFoundException ex) {
            throw new PersonNotFoundException();
        }
    }


    @Override
    public void add(T toAdd) {
        try {
            super.add(toAdd);
        } catch (DuplicateElementException ex) {
            throw new DuplicatePersonException();
        }
    }

    public void setPersons(UniquePersonList<T> replacement) {
        setElements(replacement);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<T> persons) {
        try {
            setElements(persons);
        } catch (DuplicateElementException ex) {
            throw new DuplicatePersonException();
        }
    }




}
