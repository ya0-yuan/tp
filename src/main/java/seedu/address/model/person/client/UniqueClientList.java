package seedu.address.model.person.client;

import java.util.List;

import seedu.address.model.UniqueEntityList;
import seedu.address.model.person.client.exceptions.ClientNotFoundException;
import seedu.address.model.person.client.exceptions.DuplicateClientException;


public class UniqueClientList extends UniqueEntityList<Client> {


    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedClient} must not be the same as another existing client in the list.
     */
    public void setClient(Client target, Client editedClient) {
        setEntity(target, editedClient);
    }


    public void setClients(UniqueClientList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        setEntities(clients);
    }


    @Override
    public DuplicateClientException duplicateException() {
        return new DuplicateClientException();
    }

    @Override
    public ClientNotFoundException notFoundException() {
        return new ClientNotFoundException();
    }
}

