package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_CLIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_HAIRDRESSER_ID;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.FutureAppointment;

public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add-appt";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_ID + "1 "
            + PREFIX_HAIRDRESSER_ID + "7 "
            + PREFIX_DATE_OF_APPT + "2019-06-01 "
            + PREFIX_START_TIME + "09:00 ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new appointment to HairStyleX. "
            + "Parameters: "
            + PREFIX_CLIENT_ID + PLACEHOLDER_CLIENT_ID + " "
            + PREFIX_HAIRDRESSER_ID + PLACEHOLDER_HAIRDRESSER_ID + " "
            + PREFIX_DATE_OF_APPT + PLACEHOLDER_DATE_OF_APPT + " "
            + PREFIX_START_TIME + PLACEHOLDER_START_TIME + " " + "\n"
            + COMMAND_EXAMPLE;
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in HairStyleX";
    public static final String MESSAGE_CLIENT_NOT_FOUND =
            "Client with the ID is not found. Please enter a valid client ID.";
    public static final String MESSAGE_HAIRDRESSER_NOT_NOT_FOUND =
            "Hairdresser with the ID is not found. Please enter a valid hairdresser ID.";

    private final FutureAppointment appointment;

    public AddAppointmentCommand(FutureAppointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        appointment.replaceClient(model.getClientById(appointment.getClientId()));
        appointment.replaceHairdresser(model.getHairdresserById(appointment.getHairdresserId()));

        if (appointment.getClient() == null) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
        if (appointment.getHairdresser() == null) {
            throw new CommandException(MESSAGE_HAIRDRESSER_NOT_NOT_FOUND);
        }

        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }
}
