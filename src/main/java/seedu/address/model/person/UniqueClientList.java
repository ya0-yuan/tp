package seedu.address.model.person;

import java.util.List;

import seedu.address.model.person.client.Client;


public class UniqueClientList extends UniquePersonList<Client> {


    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setClient(Client target, Client editedClient) {
        setPerson(target, editedClient);
    }


    public void setClients(UniqueClientList replacement) {
        setPersons(replacement);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setClients(List<Client> clients) {
        setPersons(clients);
    }


}

