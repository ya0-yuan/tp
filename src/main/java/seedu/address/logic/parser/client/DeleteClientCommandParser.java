package seedu.address.logic.parser.client;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.parser.DeleteCommandParser;
/**
 * Parses input arguments and creates a new DeleteClientCommand object
 */
public class DeleteClientCommandParser extends DeleteCommandParser<DeleteClientCommand> {

    @Override
    public DeleteClientCommand getCommand(Index index) {
        return new DeleteClientCommand(index);
    }

    @Override
    public String getMessageUsage() {
        return DeleteClientCommand.MESSAGE_USAGE;
    }


}
