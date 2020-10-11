package seedu.address.model.person;

import java.util.List;

import seedu.address.model.exception.DuplicateEntityException;
import seedu.address.model.exception.EntityNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;


public class UniquePersonList<T extends Person> extends UniqueEntityList<T> {


    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(T target, T editedPerson) {
        try {
            setElement(target, editedPerson);
        } catch (EntityNotFoundException ex) {
            throw new PersonNotFoundException();
        } catch (DuplicateEntityException ex) {
            throw new DuplicatePersonException();
        }
    }

    @Override
    public void remove(T toRemove) {
        try {
            super.remove(toRemove);
        } catch (EntityNotFoundException ex) {
            throw new PersonNotFoundException();
        }
    }


    @Override
    public void add(T toAdd) {
        try {
            super.add(toAdd);
        } catch (DuplicateEntityException ex) {
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
        } catch (DuplicateEntityException ex) {
            throw new DuplicatePersonException();
        }
    }




}
