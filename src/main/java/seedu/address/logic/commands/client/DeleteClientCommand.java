package seedu.address.logic.commands.client;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.client.Client;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class DeleteClientCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete_client";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";


    public DeleteClientCommand(Index targetIndex) {
        super(targetIndex);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Client clientToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteClient(clientToDelete);
        return String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);
    }
}

