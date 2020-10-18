package seedu.address.logic.commands.hairdresser;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Deletes a hairdresser identified using it's displayed index from docX.
 */
public class DeleteHairdresserCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the hairdresser identified by the index number used in the displayed hairdresser list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_HAIRDRESSER_SUCCESS = "Deleted Hairdresser: %1$s";

    public DeleteHairdresserCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {
        List<Hairdresser> lastShownList = model.getFilteredHairdresserList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_HAIRDRESSER_DISPLAYED_INDEX);
        }

        Hairdresser hairdresserToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteHairdresser(hairdresserToDelete);
        return String.format(MESSAGE_DELETE_HAIRDRESSER_SUCCESS, hairdresserToDelete);
    }
}
