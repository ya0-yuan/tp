package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_HAIRDRESSER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.appointment.FutureAppointment;
import seedu.address.model.appointment.exceptions.AppointmentNotInFutureException;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add_appt";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_HAIRDRESSER_INDEX + "1 "
            + PREFIX_DATE_OF_APPT + "2021-06-01 "
            + PREFIX_START_TIME + "09:00 ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new appointment to HairStyleX. "
            + "Parameters: "
            + PREFIX_CLIENT_INDEX + PLACEHOLDER_CLIENT_INDEX + " "
            + PREFIX_HAIRDRESSER_INDEX + PLACEHOLDER_HAIRDRESSER_INDEX + " "
            + PREFIX_DATE_OF_APPT + PLACEHOLDER_DATE_OF_APPT + " "
            + PREFIX_START_TIME + PLACEHOLDER_START_TIME + " " + "\n"
            + COMMAND_EXAMPLE;
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in HairStyleX";
    public static final String MESSAGE_CLIENT_NOT_FOUND =
            "Client with the ID is not found. Please enter a valid client ID.";
    public static final String MESSAGE_HAIRDRESSER_NOT_NOT_FOUND =
            "Hairdresser with the ID is not found. Please enter a valid hairdresser ID.";

    private final Index clientIndex;
    private final Index hairdresserIndex;
    private final AppointmentDate date;
    private final AppointmentTime time;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code appointment} created from {@code clientIndex},
     * {@code hairdresserIndex}, {@code date} and {@code time}.
     * @param clientIndex
     * @param hairdresserIndex
     * @param date
     * @param time
     */
    public AddAppointmentCommand(Index clientIndex, Index hairdresserIndex, AppointmentDate date,
                                 AppointmentTime time) {
        this.clientIndex = clientIndex;
        this.hairdresserIndex = hairdresserIndex;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Client client = model.getFilteredClientList().get(clientIndex.getZeroBased());
        Hairdresser hairdresser = model.getFilteredHairdresserList().get(hairdresserIndex.getZeroBased());
        if (client == null) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
        if (hairdresser == null) {
            throw new CommandException(MESSAGE_HAIRDRESSER_NOT_NOT_FOUND);
        }
        Appointment appointment;
        try {
            appointment = new FutureAppointment(client, hairdresser, date, time);
        } catch (AppointmentNotInFutureException e) {
            throw new CommandException(FutureAppointment.MESSAGE_CONSTRAINT_FUTURE);
        }

        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }
}
