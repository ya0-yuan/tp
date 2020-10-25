---
layout: page
title: User Guide
---

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HairStyleX.jar` from [here](https://github.com/AY2021S1-CS2103T-T15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HairStyleX.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### General commands

#### Viewing help : `help`

Shows the link to the user guide in case the user forgets the commands.

#### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

All hairdressers and client information will be stored automatically after any command that changes the data. There is no need to save manually. Will be loaded upon restart of the application.

### Appointment commands

#### Adding an appointment : `add_appt`

Adds an appointment to the database.

Format: `add_appt cid/CLIENT_ID hid/HAIRDRESSER_ID d/DATE t/TIME`
* `DATE` must be entered in YYYY-MM-DD format
* `TIME` must be entered in 24 Hour HH:MM format

Examples:
* `add_appt cid/1 hid/1 d/2020-12-12 t/17:30`

#### Listing all appointments : `list_appt`

Shows a list of all appointments in the database.

Format: `list_appt`

#### Editing an appointment : `edit_appt`

Edits an existing appointment in the database.

Format: `edit_appointment ID s/STATUS`

* Edits the appointment with the specified `ID`. The index refers to the index number shown in the displayed appointment list. The index **must be a positive integer** 1, 2, 3, …​
* Only the status of the appointment can be updated.
* All appointments are active by default.

Examples:
*  `edit_appt 1 s/CANCELLED` Edits the status of the first appointment to be CANCELLED.

#### Deleting an appointment : `delete_appt`

Removes a specific appointment from the database.

Format: `delete_appt ID`

* Deletes the appointment with the specified `ID`.
* The index refers to the index number shown in the displayed appointment list.
* The index **must be a positive integer** 1, 2, 3, …​

### Client commands

#### Adding a client : `add_client`

Adds a client to the database.

Format: `add_client n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​`

Examples:
* `add_hairdresser n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 ti/senior_stylist`
* `add_client n/John Doe p/98765432 e/johnd@example.com g/M a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney`

#### Listing all clients : `list_client`

Shows a list of all clients in the database.

Format: `list_client`

#### Editing a client : `edit_client`

Edits an existing client in the database.

Format: `edit_client ID [n/NAME] [p/PHONE] [e/EMAIL] [g/GENDER] [a/ADDRESS] [t/TAG]…​`

* Edits the client with the specified `ID`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit_client 1 p/91234567 e/yy@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `yy@example.com` respectively.
*  `edit_client 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

#### Deleting a client : `delete_client`

Removes a specific client from the database.

Format: `delete_client ID`

* Deletes the client with the specified `ID`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* All appointments with this corresponding client will now show "DELETED" for the client placeholder

### 4.3 Hairdresser Management

This feature allows you to manage the information of hairdressers in your salon. You can record the following information about hairdressers: 
* Name
* Title
* Gender
* Phone
* Email
* Specialisations

#### 4.3.1 Hairdresser Management Command Parameters

Parameter Name | Description
--------|------------------
`NAME` | The name of the hairdresser.<br>It should only contain alphanumeric characters and spaces, and it should not be blank.<br>E.g. `Anna Sue`
`TITLE` | The title of the hairdresser.<br>It should not be blank.<br>E.g. `Senior Stylist`
`GENDER` | The gender of the hairdresser.<br>Gender should be either F or M or f or m.<br>E.g. `F`
`PHONE` | The phone number of the hairdresser.<br>Phone numbers should only contain numbers, and it should be at least 3 digits long.<br>E.g. `81526354`
`EMAIL` | The email of the hairdresser.<br>Emails should be of the format local-part@domain and adhere to the following constraints: <br>1. The local-part should only contain alphanumeric characters and these special characters: `!#$%&'*+/=?{}~^.-` .<br>2. This is followed by a '@' and then a domain name. The domain name must be at least 2 characters long, start and end with alphanumeric characters, consist of alphanumeric characters, a period or a hyphen for the characters in between, if any.<br>E.g. `johnd@example.com`
`SPECIALISATION` | The specialisation of the hairdresser.<br>Specialisations should be one of the following options: <br>`Color`, `Perm`, `HairExtension`, `Styling`, `HairConditioning`, `Straightening`, `ScalpTreatment`, `HairLossTreatment`.<br>E.g. `Color`
`ID` | The unique hairdresser ID `hid` that is assigned to each hairdresser. <br>The ID is unique to each hairdresser, and the ID will not be assigned to another hairdresser even if one is deleted from the database. </br>Thus, the ID displayed in the list may not be sequential. 

#### 4.3.2 Adding a hairdresser : `add_hairdresser`

You can use this command to add a hairdresser to the database.

**Format:**

`add_hairdresser n/NAME p/PHONE e/EMAIL g/GENDER ti/TITLE [s/SPECIALISATION]…​`

<div markdown="block" class="alert alert-info">

:information_source: Refer to Section 4.3.1 for more details on each parameter.

</div>

**Example:**

In the example below, you will register a female Senior Stylist called **Helen Lim** with phone number **82716252**, email **helenlim@example.com**, who is specialised in **Perm** and **Color**, into the HairstyleX.

<div markdown="block" class="alert alert-white">

Adding a new hairdresser: <br>

1. Type `add_hairdresser n/Helen Lim p/82716252 e/helenlim@example.com g/F ti/Senior Stylist s/Perm s/Color` into the *Command Box*.
1. Press `Enter` to execute.

Outcome: <br>

1. The `Result Display` will show a success message. 
1. You can now see the hairdresser's information in the *Hairdressers Panel*.

</div>

![AddHairdresserOutcome](images/AddHairdresserOutcome.png)
*Figure 3. Outcome of a successful `add_hairdresser` command*


#### 4.3.3 Listing all hairdressers : `list_hairdresser`

You can use this command to show a list of all hairdressers in the database. This command is especially useful if you used `filter_hairdresser` command to search for hairdressers- `list_hairdresser` will restore the full list to view. 

**Format:**

`list_hairdresser`

**Example:**

<div markdown="block" class="alert alert-white">

Listing all hairdressers: <br>

1. Type `list_hairdresser` into the *Command Box*.
1. Press `Enter` to execute.

Outcome: <br>

1. The `Result Display` will show a success message. 
1. You can now see a list of all hairdresser's information in the *Hairdressers Panel*.

</div>

#### 4.3.4 Editing a hairdresser : `edit_hairdresser`

You can use this command to edit an existing hairdresser in the database.

**Format:** 

`edit_hairdresser ID [n/NAME] [p/PHONE] [e/EMAIL] [e/GENDER] [ti/TITLE] [s/SPECIALISATION]…​`

<div markdown="block" class="alert alert-info">

:information_source:<br>
* Edits the hairdresser with the specified `ID`. The index refers to the index number `hid` shown in the displayed hairdresser list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing specialisations, the existing specialisations of the hairdresser will be removed i.e adding of specialisations is not cumulative.
* You can remove all the hairdresser’s tags by typing `s/` without
    specifying any specialisations after it.
* Refer to Section 4.3.1 for more details on each parameter.

</div>

**Example:**
Assume that the hairdresser with ID `4` changed his/her contact details and you wish to change them in the database. You will perform the following steps: 

<div markdown="block" class="alert alert-white">

Adding a new hairdresser: <br>

1. Type `edit_hairdresser 4 p/91234567 e/yy@example.com` into the *Command Box*.
1. Press `Enter` to execute.

Outcome: <br>

1. The `Result Display` will show a success message. 
1. You can now see the updated hairdresser's information in the *Hairdressers Panel*.

</div>

![EditHairdresserOutcome](images/EditHairdresserOutcome.png)
*Figure 4. Outcome of a successful `edit_hairdresser` command*

#### Deleting a hairdresser : `delete_hairdresser`

Removes a specific hairdresser from the database.

Format: `delete_hairdresser ID`

* Deletes the hairdresser with the specified `ID`.
* The index refers to the index number `hid` shown in the displayed hairdresser list.
* The index **must be a positive integer** 1, 2, 3, …​
* All appointments with this corresponding hairdresser will now show "DELETED" for the hairdresser placeholder

### Alias commands

#### Adding a command alias : `add_alias`

Adds a new command alias to the database.

Format: `add_alias old/OLD_ALIAS new/NEW_ALIAS`


#### Deleting a command_alias : `delete_alias`

Removes a command alias from the database.

Format: `delete_alias ALIAS`

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HairstyleX home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Clear** | `clear`
**Exit** | `exit`
**Add Hairdresser** | `add_hairdresser n/NAME p/PHONE_NUMBER e/EMAIL ti/TITLE [s/SPECIALISATION] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com ti/senior s/colouring t/colleague`
**Add Client** | `add_client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS tr/TREATMENT [p/PREFERENCE] [t/TAG]…​` <br> e.g., `add n/Nicholas Toh p/12345678 e/niktoh@example.com a/123, Clementi Rd, 1234665 tr/colouring p/female stylist t/frequent`
**Add Appointment** | `add_appt cid/CLIENT_ID hid/HAIRDRESSER_ID d/DATE t/TIME` <br> e.g., `add_appt cid/1 hid/1 d/2020-12-12 t/17:30`
**List Hairdressers** | `list_hairdresser`
**List Clients** | `list_client`
**List Appointments** | `list_appt`
**Edit Hairdresser** | `edit_hairdresser ID ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [ti/TITLE] [s/SPECIALISATION] [t/TAG]…​`<br> e.g.,`edit_hairdresser 2 n/James Lee ti/senior`
**Edit Client** | `edit_client ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tr/TREATMENT] [p/PREFERENCE] [t/TAG]…​`<br> e.g.,`edit_client 2 n/James Tan p/Female Stylist`
**Edit Appointment** | `edit_appointment ID s/STATUS` <br> e.g.,`edit_appt 1 s/CANCELLED`
**Delete Hairdresser** | `delete_hairdresser ID` <br> e.g., `delete_hairdresser 3`
**Delete Client** | `delete_client ID` <br> e.g., `delete_client 3`
**Delete Appointment** | `delete_appt ID` <br> e.g., `delete_appt 3`
