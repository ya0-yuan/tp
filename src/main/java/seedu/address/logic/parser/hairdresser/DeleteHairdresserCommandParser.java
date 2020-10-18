package seedu.address.logic.parser.hairdresser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.hairdresser.DeleteHairdresserCommand;
import seedu.address.logic.parser.DeleteCommandParser;

public class DeleteHairdresserCommandParser extends DeleteCommandParser<DeleteHairdresserCommand> {

    @Override
    public DeleteHairdresserCommand getCommand(Index index) {
        return new DeleteHairdresserCommand(index);
    }

    @Override
    public String getMessageUsage() {
        return DeleteHairdresserCommand.MESSAGE_USAGE;
    }

}
