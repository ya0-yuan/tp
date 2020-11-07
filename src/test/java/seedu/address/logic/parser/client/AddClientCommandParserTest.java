package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIALISATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_PERM;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_PERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.Title;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withSpecs(VALID_SPECIALISATION_PERM).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ALICE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_ALICE + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_ALICE + EMAIL_DESC_BOB + TITLE_DESC_BOB
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple titles - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_ALICE + TITLE_DESC_BOB
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple genders - last address accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_ALICE
                + GENDER_DESC_BOB + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClient));

        // multiple specs - all accepted
        Client expectedClientMultipleSpecs = new ClientBuilder(BOB)
                .withSpecs(VALID_SPECIALISATION_PERM, VALID_SPECIALISATION_COLOR).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB
                + SPECIALISATION_DESC_COLOR
                + SPECIALISATION_DESC_PERM, new AddClientCommand(expectedClientMultipleSpecs));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero specs
        Client expectedClient = new ClientBuilder(ALICE).withSpecs().build();
        assertParseSuccess(parser, NAME_DESC_ALICE + PHONE_DESC_ALICE
                        + EMAIL_DESC_ALICE + TITLE_DESC_ALICE + GENDER_DESC_ALICE,
                new AddClientCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing title prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_TITLE_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + VALID_GENDER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_TITLE_BOB + VALID_EMAIL_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + TITLE_DESC_BOB + GENDER_DESC_BOB
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Email.MESSAGE_CONSTRAINTS);

        // invalid title
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_TITLE_DESC + GENDER_DESC_BOB
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Title.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + INVALID_GENDER_DESC
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Gender.MESSAGE_CONSTRAINTS);

        // invalid spec
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TITLE_DESC_BOB + GENDER_DESC_BOB
                + INVALID_SPECIALISATION_DESC + VALID_SPECIALISATION_PERM, Specialisation.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_TITLE_DESC + GENDER_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + TITLE_DESC_BOB
                        + GENDER_DESC_BOB + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
    }
}
