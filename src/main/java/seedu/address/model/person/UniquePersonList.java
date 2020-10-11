package seedu.address.model.person;

import java.util.List;

import seedu.address.model.UniqueEntityList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;


public class UniquePersonList extends UniqueEntityList<Person> {

    @Override
    public DuplicatePersonException duplicateException() {
        return new DuplicatePersonException();
    }

    @Override
    public PersonNotFoundException notFoundException() {
        return new PersonNotFoundException();
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        setEntity(target, editedPerson);
    }


    public void setPersons(UniquePersonList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        setEntities(persons);
    }




}
