package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.Hairdresser;

public class AddHairdresserCommand extends Command {
    public static final String COMMAND_WORD = "add_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a hairdresser to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_SPECIALISATION + "SPECIALISATION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_TITLE + "senior stylist "
            + PREFIX_TAG + "perm "
            + PREFIX_TAG + "dye";

    public static final String MESSAGE_SUCCESS = "New hairdresser added: %1$s";
    public static final String MESSAGE_DUPLICATE_HAIRDRESSER = "This hairdresser already exists in the address book";

    private final Hairdresser toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddHairdresserCommand(Hairdresser hairdresser) {
        requireNonNull(hairdresser);
        toAdd = hairdresser;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasHairdresser(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_HAIRDRESSER);
        }

        model.addHairdresser(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddHairdresserCommand // instanceof handles nulls
                && toAdd.equals(((AddHairdresserCommand) other).toAdd));
    }
}
