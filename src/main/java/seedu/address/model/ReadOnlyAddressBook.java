package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;


/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();


    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the hairdressers list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Hairdresser> getHairdresserList();


}
