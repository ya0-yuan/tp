package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_COMMAND;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddAliasCommandParser extends AddCommandParser<AddAliasCommand> {
    @Override
    public AddAliasCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_OLD_COMMAND, PREFIX_NEW_COMMAND);
        if (!arePrefixesPresent(argMultimap, PREFIX_OLD_COMMAND, PREFIX_NEW_COMMAND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAliasCommand.MESSAGE_USAGE));
        }

        return new AddAliasCommand(argMultimap.getValue(PREFIX_OLD_COMMAND).get(),
                argMultimap.getValue(PREFIX_NEW_COMMAND).get());
    }
}
