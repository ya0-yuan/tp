package seedu.address.logic.parser.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.AddAppointmentCommand;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentTime;

public class AddAppointmentCommandParser extends AddCommandParser<AddAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddAppointmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_CLIENT_INDEX, PREFIX_HAIRDRESSER_INDEX,
                        PREFIX_DATE_OF_APPT, PREFIX_START_TIME);
        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_INDEX, PREFIX_HAIRDRESSER_INDEX, PREFIX_DATE_OF_APPT,
                PREFIX_START_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }
        Index clientIndex = ParserUtil
                .parseIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get());
        Index hairdresserIndex = ParserUtil
                .parseIndex(argMultimap.getValue(PREFIX_HAIRDRESSER_INDEX).get());
        AppointmentDate date = ParserUtil
                .parseAppointmentDate(argMultimap.getValue(PREFIX_DATE_OF_APPT).get());
        AppointmentTime time = ParserUtil
                .parseAppointmentTime(argMultimap.getValue(PREFIX_START_TIME).get());

        return new AddAppointmentCommand(clientIndex, hairdresserIndex, date, time);
    }


}
