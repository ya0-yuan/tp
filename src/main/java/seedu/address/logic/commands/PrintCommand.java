package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Copies all appointments, hairdressers and clients into a csv file.
 */
public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_SUCCESS = "CSV files successfully made.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves all appointments, hairdressers and clients"
            + " into 3 separate CSV files.\n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final char CSV_SEPARATOR = ',';

    private ObservableList<Hairdresser> hairdresserList;
    private ObservableList<Client> clientList;
    private ObservableList<Appointment> appointmentList;

    private enum ExportType {
        hairdresser, client, appointment;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        hairdresserList = model.getHairStyleX().getHairdresserList();
        clientList = model.getHairStyleX().getClientList();
        appointmentList = model.getHairStyleX().getAppointmentList();

        writeToCsv(ExportType.appointment);
        writeToCsv(ExportType.client);
        writeToCsv(ExportType.hairdresser);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void writeToCsv(ExportType type) {
        String fileName;
        switch (type) {
        case hairdresser:
            fileName = "hairdressers";
            break;
        case appointment:
            fileName = "appointments";
            break;
        case client:
            fileName = "clients";
            break;
        default:
            throw new IllegalArgumentException();
        }

        Path csvFilePath = Paths.get("data" , fileName + ".csv");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath.toString()));
            appendToWriter(writer, type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void appendToWriter(BufferedWriter writer, ExportType type) throws IOException {
        switch (type) {
        case hairdresser:
            writer.append(makeFileCreationTime("Hairdresser"));
            writer.append(makeHairdresserHeader());
            for (Hairdresser hairdresser : hairdresserList) {
                writer.append(printHairdresser(hairdresser));
            }
            break;
        case appointment:
            writer.append(makeFileCreationTime("Appointment"));
            writer.append(makeAppointmentHeader());
            for (Appointment appt : appointmentList) {
                writer.append(printAppointment(appt));
            }
            break;
        case client:
            writer.append(makeFileCreationTime("Client"));
            writer.append(makeClientHeader());
            for (Client client : clientList) {
                writer.append(printClient(client));
            }
            break;
        default:
            throw new IllegalArgumentException();
        }

        writer.close();
    }

    private String printHairdresser(Hairdresser hairdresser) {
        String specialisations = hairdresser.getSpecs()
                .stream()
                .map(Specialisation::toString)
                .collect(Collectors.joining());

        return new StringBuilder()
                .append(hairdresser.getId()).append(CSV_SEPARATOR)
                .append(hairdresser.getName()).append(CSV_SEPARATOR)
                .append(hairdresser.getTitle()).append(CSV_SEPARATOR)
                .append(hairdresser.getGender()).append(CSV_SEPARATOR)
                .append(hairdresser.getPhone()).append(CSV_SEPARATOR)
                .append(hairdresser.getEmail()).append(CSV_SEPARATOR)
                .append(specialisations).append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String makeHairdresserHeader() {
        return new StringBuilder()
                .append("ID").append(CSV_SEPARATOR)
                .append("Name").append(CSV_SEPARATOR)
                .append("Title").append(CSV_SEPARATOR)
                .append("Gender").append(CSV_SEPARATOR)
                .append("Phone").append(CSV_SEPARATOR)
                .append("Email").append(CSV_SEPARATOR)
                .append("Specialisations").append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String printClient(Client client) {
        String tags = client.getTags()
                .stream()
                .map(Tag::toString)
                .collect(Collectors.joining());

        return new StringBuilder()
                .append(client.getId()).append(CSV_SEPARATOR)
                .append(client.getName()).append(CSV_SEPARATOR)
                .append(client.getGender()).append(CSV_SEPARATOR)
                .append(client.getPhone()).append(CSV_SEPARATOR)
                .append(client.getEmail()).append(CSV_SEPARATOR)
                .append(removeCommaConflict(client.getAddress().toString())).append(CSV_SEPARATOR)
                .append(tags).append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String makeClientHeader() {
        return new StringBuilder()
                .append("ID").append(CSV_SEPARATOR)
                .append("Name").append(CSV_SEPARATOR)
                .append("Gender").append(CSV_SEPARATOR)
                .append("Phone").append(CSV_SEPARATOR)
                .append("Email").append(CSV_SEPARATOR)
                .append("Address").append(CSV_SEPARATOR)
                .append("Tags").append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String printAppointment(Appointment appointment) {
        return new StringBuilder()
                .append(appointment.getId()).append(CSV_SEPARATOR)
                .append(appointment.getClient().getName()).append(CSV_SEPARATOR)
                .append(appointment.getHairdresser().getName()).append(CSV_SEPARATOR)
                .append(appointment.getDate()).append(CSV_SEPARATOR)
                .append(appointment.getTime()).append(CSV_SEPARATOR)
                .append(appointment.getAppointmentStatus().name().toLowerCase()).append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String makeAppointmentHeader() {
        return new StringBuilder()
                .append("ID").append(CSV_SEPARATOR)
                .append("Client Name").append(CSV_SEPARATOR)
                .append("Hairdresser Name").append(CSV_SEPARATOR)
                .append("Date").append(CSV_SEPARATOR)
                .append("Time").append(CSV_SEPARATOR)
                .append("Status").append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    private String makeFileCreationTime(String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy 'at' HH:mm:ss.");
        Date date = new Date(System.currentTimeMillis());

        return new StringBuilder()
                .append(type)
                .append(" list updated as of: ")
                .append(formatter.format(date))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString();
    }

    private String removeCommaConflict(String input) {
        if (input.contains(",")) {
            return String.format("\"%s\"", input);
        } else {
            return input;
        }
    }
}
