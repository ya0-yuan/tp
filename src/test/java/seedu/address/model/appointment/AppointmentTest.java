package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointmentTimes.getValidTime;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import seedu.address.testutil.TypicalAppointmentDates;
import seedu.address.testutil.TypicalAppointmentTimes;

class AppointmentTest {
    @Test
    public void testValidConstructor(){
        Assertions.assertDoesNotThrow(() -> new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(
            null,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            null,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            null,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            null,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            null
        ));
    }

    @Test
    void getId() {
    }

    @Test
    void getClientId() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getHairdresserId() {
    }

    @Test
    void getClient() {
    }

    @Test
    void replaceClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void getHairdresser() {
    }

    @Test
    void replaceHairdresser() {
    }

    @Test
    void deleteHairdresser() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getTime() {
    }

    @Test
    void getAppointmentStatus() {
    }

    @Test
    void startDateTime() {
    }

    @Test
    void endDateTime() {
    }

    @Test
    void isSameAppointment() {
    }

    @Test
    void isSame() {
    }

    @Test
    void isPast() {
    }

    @Test
    void isClash() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}
