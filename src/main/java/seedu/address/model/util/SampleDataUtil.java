package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.client.Address;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Gender("M")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Gender("F")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Gender("F")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Gender("M")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Gender("M")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Gender("M"))
        };
    }

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Ivan Choo"), new Phone("84213456"), new Email("annayeoh@example.com"),
                new Gender("M"), new Address("Blk 47 Tampines Street 20 #17-35"), getTagSet("shorthair")),
            new Client(new Name("Kimberly Lim"), new Phone("92837461"), new Email("berniceyu@example.com"),
                    new Gender("F"), new Address("Blk 30 Lorong 3 Serangoon Gardens #07-18"),
                    getTagSet("longhair", "preferperm"))
        };
    }

    public static Hairdresser[] getSampleHairdressers() {
        return new Hairdresser[] {
            new Hairdresser(new Name("Anna Yeoh"), new Phone("84213456"), new Email("annayeoh@example.com"),
                new Gender("F"), new Title("Senior Stylist"), getSpecSet("perm", "cut")),
            new Hairdresser(new Name("Bella Yu"), new Phone("92837461"), new Email("berniceyu@example.com"),
                    new Gender("F"), new Title("Shampooist"), getSpecSet("washhair"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        for (Hairdresser sampleHairdresser : getSampleHairdressers()) {
            sampleAb.addHairdresser(sampleHairdresser);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a specialisation set containing the list of strings given.
     */
    public static Set<Specialisation> getSpecSet(String... strings) {
        return Arrays.stream(strings)
                .map(Specialisation::new)
                .collect(Collectors.toSet());
    }

}
