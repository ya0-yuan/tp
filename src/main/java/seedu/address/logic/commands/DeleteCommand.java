package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Id;
import seedu.address.model.Model;

/**
 * Deletes an entity identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand <T extends Id> extends Command {

    protected final T targetId;

    public DeleteCommand(T targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String result = deleteFromModel(model);

        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other != null && other.getClass() == this.getClass()
                && targetId.equals(((DeleteCommand) other).targetId)); // state check
    }

    public abstract String deleteFromModel(Model model) throws CommandException;
}
