package seedu.address.logic.commands.appointment;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes an appointment identified using it's displayed index from the HairStyleX.
 */
public class DeleteAppointmentCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delete_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    public DeleteAppointmentCommand(Index targetIndex) {
        super(targetIndex);
    }


    @Override
    public String deleteFromModel(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Appointment appointmentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAppointment(appointmentToDelete);

        return String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete);
    }

}
