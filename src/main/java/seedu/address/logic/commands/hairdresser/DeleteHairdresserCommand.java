package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Deletes a hairdresser identified using it's displayed index from docX.
 */
public class DeleteHairdresserCommand extends Command {

    public static final String COMMAND_WORD = "delete_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the hairdresser identified by the index number used in the displayed hairdresser list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_HAIRDRESSER_SUCCESS = "Deleted Hairdresser: %1$s";

    private final Index targetIndex;

    public DeleteHairdresserCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Hairdresser> lastShownList = model.getFilteredHairdresserList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_HAIRDRESSER_DISPLAYED_INDEX);
        }

        Hairdresser hairdresserToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteHairdresser(hairdresserToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_HAIRDRESSER_SUCCESS, hairdresserToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHairdresserCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteHairdresserCommand) other).targetIndex)); // state check
    }
}
