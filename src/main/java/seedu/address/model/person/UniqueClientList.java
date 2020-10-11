package seedu.address.model.person;

import java.util.List;

import seedu.address.model.person.client.Client;


public class UniqueClientList extends UniquePersonList<Client> {


    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedClient} must not be the same as another existing client in the list.
     */
    public void setClient(Client target, Client editedClient) {
        setPerson(target, editedClient);
    }


    public void setClients(UniqueClientList replacement) {
        setPersons(replacement);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        setPersons(clients);
    }


}

