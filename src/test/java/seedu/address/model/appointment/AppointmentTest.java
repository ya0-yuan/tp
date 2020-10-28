package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalAppointmentDates;
import seedu.address.testutil.TypicalAppointmentTimes;

class AppointmentTest {
    @Test
    public void testValidConstructor() {
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
        Assertions.assertDoesNotThrow(() -> {
            new Appointment(
                ALICE,
                BENEDICT,
                TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
                TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            ).getId();
        });
    }

    @Test
    void getClientId() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getClientId(), ALICE.getId());
    }

    @Test
    void getHairdresserId() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getHairdresserId(), BENEDICT.getId());
    }

    @Test
    void getClient() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getClient(), ALICE);
    }

    @Test
    void replaceClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void getHairdresser() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getHairdresser(), BENEDICT);
    }

    @Test
    void replaceHairdresser() {
    }

    @Test
    void deleteHairdresser() {
    }

    @Test
    void getDate() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getDate(), TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE);
    }

    @Test
    void getTime() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getTime(), TypicalAppointmentTimes.VALID_APPOINTMENT_TIME);
    }

    @Test
    void getAppointmentStatus() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getAppointmentStatus(), AppointmentStatus.ACTIVE);
        appt = new Appointment(
            ALICE,
            BENEDICT,
            TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE,
            TypicalAppointmentTimes.VALID_APPOINTMENT_TIME,
            AppointmentStatus.CANCELLED
        );
        Assertions.assertEquals(appt.getAppointmentStatus(), AppointmentStatus.CANCELLED);
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
