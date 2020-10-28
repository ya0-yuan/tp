package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_COMMAND;

import seedu.address.logic.commandalias.CommandAliasSet;
import seedu.address.logic.commandalias.exceptions.CommandWordException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class AddAliasCommand extends AddCommand {

    public static final String COMMAND_WORD = "add_alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an alias to the address book. "
            + "Parameters: "
            + PREFIX_OLD_COMMAND + " ORIGINAL COMMAND"
            + PREFIX_NEW_COMMAND + " NEW COMMAND\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OLD_COMMAND + " add_hairdresser "
            + PREFIX_NEW_COMMAND + " a_h ";
    private String defaultWord;
    private String newAlias;

    /**
     * Creates an add alias command given a default word and the new alias
     * @param defaultWord the original command
     * @param newAlias the new command
     */
    public AddAliasCommand(String defaultWord, String newAlias) {
        this.defaultWord = defaultWord;
        this.newAlias = newAlias;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            CommandAliasSet.getInstance().insertNewAlias(defaultWord, newAlias);
        } catch (CommandWordException ex) {
            throw new CommandException(ex.getMessage());
        }
        return new CommandResult("Success! Added "
                + newAlias + " as alias for " + defaultWord);
    }
}
