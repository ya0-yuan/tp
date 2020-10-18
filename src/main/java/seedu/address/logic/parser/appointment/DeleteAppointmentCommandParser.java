package seedu.address.logic.parser.appointment;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.address.logic.parser.DeleteCommandParser;

/**
 * Parses input arguments and creates a new DeleteAppointmentCommand object
 */
public class DeleteAppointmentCommandParser extends DeleteCommandParser<DeleteAppointmentCommand> {


    @Override
    public DeleteAppointmentCommand getCommand(Index index) {
        return new DeleteAppointmentCommand(index);
    }

    @Override
    public String getMessageUsage() {
        return DeleteAppointmentCommand.MESSAGE_USAGE;
    }
}
