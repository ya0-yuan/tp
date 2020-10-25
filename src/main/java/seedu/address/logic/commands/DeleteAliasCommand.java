package seedu.address.logic.commands;

import seedu.address.logic.commandalias.CommandAliasSet;
import seedu.address.logic.commandalias.exceptions.CommandWordException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DeleteAliasCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete_alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the alias.\n"
            + "Parameters: ALIAS\n"
            + "Example: " + COMMAND_WORD + " a_a";

    public static final String MESSAGE_DELETE_ALIAS_SUCCESS = "Deleted Alias: %1$s";

    private String aliasToDelete;

    public DeleteAliasCommand(String alias) {
        this.aliasToDelete = alias;
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {

        try {
            CommandAliasSet.getInstance().deleteAlias(aliasToDelete);
        } catch (CommandWordException ex) {
            throw new CommandException(ex.getMessage());
        }
        return String.format(MESSAGE_DELETE_ALIAS_SUCCESS, aliasToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other != null && other.getClass() == this.getClass()
                && aliasToDelete.equals(((DeleteAliasCommand) other).aliasToDelete)); // state check
    }
}
