---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the HairStyleX data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Hairdresser Management Features

#### Overview of implementation for Hairdresser

* `Hairdresser` - This is an entity class to store information regarding an appointment, such as name, phone, email, gender, title, specialisation.
* `Specialisation` - This is class containing an enum which represents the specialisations of a hairdresser, which includes `Color`, `Perm`, `HairExtension`, `Styling`, `HairConditioning`, `Straightening`, `ScalpTreatment`, `HairLossTreatment`.
* `HairdresserID` - This is a class which represents the unique ID of an appointment.
* `UniqueHairdresserList` - This is a `UniqueEntityList` which represents all hairdressers. It ensures that no duplicates of hairdresser can be added and supports add, update, delete of hairdressers.
* `JsonAdaptedHairdresser` - This class functions as an adapter between Appointment and the Storage layer. It specifies how to convert from an appointment object to a JSON representation and vice versa. It also serves as validation for correct data format when the save file is loaded.
* `AddHairdresserCommandParser` - This class parses a user input string to a `HairdresserCommand` object and performs validation.
* `AddHairdresserCommand` - This contains `execute` method which interact with model to perform the add action.

#### Add Hairdresser Feature

##### Current implementation

The `add_hairdresser` command allows the `LogicManager` to create a new hairdresser and add it to the list of hairdressers. 

The following sequence shows the sequence when the add command is execute by the `LogicManager`:

![AddHairdresserSequenceDiagram](images/AddHairdresserSequenceDiagram.png)

From the diagram above:

1. `LogicManager`’s `execute` is called when `add_hairdresser` is entered and it calls upon `parseCommand` of `AddressBookParser` to parse the command.

2. `AddressBookParser` will initialize `AddHairdresserCommandParser` and invoke the method `parse` to further parse add hairdresser command

3. `parse` will be invoked and passed the parameters of the add hairdresser command.

4. If all the arguments of `add_hairdresser` commands are valid, `AddHairdresserCommand` will be returned to the `LogicManager`

5. `LogicManger` will then call `execute` method of `AddHairdresserCommand`

6. `AddHairdresserCommand` will call `addHairdresser` passing `toAdd` as an argument to `Model` and returns a `result` to the `LogicManager`

7. `LogicManger` will then call `saveAddressBook` method of `Storage`

8. A `CommandResult` will be returned at the end.


### Appointment feature
This feature represents an appointment between a hairdresser and a client. An appointment consists of a client and a hairdresser. If one of these persons are deleted, the reference will be replaced with a tombstone value indicating a deleted hairdresser/client. A client can have multiple appointments that do not clash, similarly for hairdressers. An appointment must also have a date, time, and status.

#### Current Implementation

* `Appointment` - This is an entity class to store information regarding an appointment, such as hairdresser, client, hairdresser ID, client ID, date, time and status.
                       
* `FutureAppointment` - This is an entity class which extends Appointment. This class ensures that a newly created appointment is always in the future compared to the system time.
* `AppointmentStatus` - This is an enum which represents the status of an appointment, which can be `active`, `cancelled`, `completed`, or `missed`.
* `AppointmentID` - This is a class which represents the unique ID of an appointment.
* `UniqueAppointmentList` - This is a `UniqueEntityList` which represents all appointments. It implements features including ensuring that no duplicate appointments can be added. It allows for clients or hairdressers to be replaced or deleted, and updates the relevant appointments.
*`JsonAdaptedAppointment` - This class functions as an adapter between Appointment and the Storage layer. It specifies how to convert from an appointment object to a JSON representation and vice versa. It also serves as validation for correct data format when the save file is loaded.
*`AddAppointmentCommandParser` - This class parses a user input string to an AppointmentCommand object. Validation for inputs that do not require access to the model is performed here.                                                                  
*`AddAppointmentCommand` - This is where majority of the logic of the add appointment command is performed, when the `execute` method is called. It will access the model to ensure there is no duplicate appointment before adding the appointment to the model.

Steps:

1. The user enters an add appointment command, the input is first validated by AddAppointmentCommandParser. Inputs that do not require access to the model is validated, for example validating the format of hairdresser ID, client ID, date, and time. It ensures that the date/time is valid and is in the future, but does not check whether the hairdresser/client ID corresponds to an actual hairdresser/client (this is validated when the command is executed). A new AddAppointmentCommand object is created.

1. The Logic layer then executes the AddAppointmentCommand. This checks if the hairdresser/client ID corresponds to an actual hairdresser/client, and the appointment is checked against existing appointments in the model to ensure that there are no duplicates or clashes. The appointment object is then added to the model.

1. The model adds the appointment to its internal list `UniqueAppointmentList`. This list is an `javafx.collections.ObservableList` and the UI layer is notified and updated through a `ListChangeListener`. This follows the observer pattern.

1. The Logic layer will be notified that the model has been modified through an `InvalidationListener`. This triggers storing information to non-volatile memory using the Storage layer, and the process is detailed there.

#### Proposed improvements
1. Currently, validation or updating of appointments based on client/hairdresser ID requires iterating through all appointments to check if they involve the relevant client/hairdresser. Hence this process is slow and runs in O(n) time. It can be improved by implementing two `HashMaps` of appointments keyed by `ClientId`/`HairdresserId`  respectively. This will allow for the search to be done in O(1) time. We did not implement this feature as it would introduce unnecessary complexity, and the current solution meets the non-functional requirements regarding performance.

1. Currently, the order of appointments in the `UniqueAppointmentList` is not ordered by time. Hence, sorting appointments by appointment time requires O(n log(n)) time. By maintaining a `TreeMap` of appointments keyed by time, a list of appointments ordered by appointment time can be generated in O(n), while the next k appointments after a given appointment can be found in O(k log(n)) time. We did not implement this feature as it would introduce unnecessary complexity, and the current solution meets the non-functional requirements regarding performance.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* managers for budding hair salons
* has a growing number of hairstylists and clients
* has a need to keep track of bookings, store supplies, stylist availability, and client information 
* has a desktop at the front desk
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage hairstylist, client and appointment information faster than the traditional pen and paper

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                | So that I can…​                                                     |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see the instruction guide      | refer to instructions when I forget how to use the App                 |
| `* * *`  | manager                                    | add a new hairdresser          |                                                                        |
| `* * *`  | receptionist                               | add a new client               |                                   |
| `* * *`  | manager                                    | edit a client/hairdresser      | keep my database updated |
| `* * *`  | user                                       | exit the program               |          |
| `* * *`  | manager                                    | find persons by name           | locate a person easily                                                 |
| `* * *`  | receptionist                               | book appointments              |        |
| `* * *`  | new user                                   | navigate the UI easily         | learn how to use the app quickly                                             |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `HairStyleX` application, and the **Actor** is the `user`, unless specified otherwise)

(A `person` refers to either a `hairdresser` or a `client`. If a `person` is used in any of the use cases below,
it means that the use case can be performed similarly on both a `hairdresser` and a `client`)

**Use case: Delete a person**

**MSS**

1.  User requests to list people
2.  HairStyleX shows a list of people
3.  User requests to delete a specific person in the list
4.  HairStyleX deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HairStyleX shows an error message.

      Use case resumes at step 2.
      
**Use case: Add a person**

**MSS**

1.  User requests to add a specific person in the list
2.  HairStyleX adds the person
3.  HairStyleX shows a success message

    Use case ends.

**Extensions**

* 1a. The input syntax is invalid.
    
    * 1a1. HairStyleX shows an error message.
    
      Use case resumes at step 1.
          
**Use case: View people**

**MSS**

1.  User requests to view all people in the list
2.  HairStyleX shows all people

    Use case ends.

**Extensions**

* 1a. The list is empty.
    
    Use case ends.
    
**Use case: Edit Person**

**MSS**

1.  User requests to view all people in the list
2.  HairStyleX shows all people
3.  User requests to edit specific person
4.  HairStyleX displays success message with updated person details

    Use case ends.

**Extensions**

* 1a. The list is empty.
    
    Use case ends.
    
**Use case: Find Person**

**MSS**

1.  User requests to find all people whose name contains a specified `string`
2.  HairStyleX shows all people that has names that contain the specified `string`

    Use case ends.

**Extensions**

* 2a. No people has names that contain that specified `string`.
    
    Use case ends.
    
**Use case: View help**

**MSS**

1.  User requests to view all valid commands
2.  HairStyleX shows all valid commands

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 hairdressers and clients without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  There is no need for internet connection

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Hairdresser**: A hair stylist working in the salon
* **Client**: A customer that visits the salon
* **Person**: Can refer to either a hairdresser or a client
* **Appointment**: A specified time when a hairdresser services a client.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
