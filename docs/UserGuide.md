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

### Viewing help : `help`

Shows the user instructions in case the receptionist forgets a command.

#### User Guide:

Format: `guide`

Show the user guide.

#### View list of commands:

Format: `help [command]`

Shows all the command if `[command]` is not specified, else show the syntax for a specific command and detailed information.

### Adding a hairdresser : `add_hairdresser`

Adds a hairdresser to the database.

Format: `add_hairdresser n/NAME p/PHONE_NUMBER e/EMAIL ti/TITLE [s/SPECIALISATION] [t/TAG]…​`

### Adding a client : `add_client`

Adds a client to the database.

Format: `add_client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS tr/TREATMENT [p/PREFERENCE] [t/TAG]…​`

Examples:
* `add_hairdresser n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 ti/senior_stylist`
* `add_client n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 tr/perm`

### Listing all hairdressers/clients : `list`

Shows a list of all hairdressers/clients in the database.

Format: `list_hairdresser`

Format: `list_client`

### Editing a hairdresser : `edit_hairdresser`

Edits an existing hairdresser in the database.

Format: `edit_hairdresser INDEX [n/NAME] [p/PHONE] [e/EMAIL] [ti/TITLE] [s/SPECIALISATION] [t/TAG]…​`

* Edits the hairdresser at the specified `INDEX`. The index refers to the index number shown in the displayed hairdresser list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the hairdresser will be removed i.e adding of tags is not cumulative.
* You can remove all the hairdresser’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit_hairdresser 1 p/91234567 e/yy@example.com` Edits the phone number and email address of the 1st hairdresser to be `91234567` and `yy@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd hairdresser to be `Betsy Crower` and clears all existing tags.

### Editing a client : `edit_client`

Edits an existing client in the database.

Format: `edit_client INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tr/TREATMENT] [p/PREFERENCE] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit_client 1 p/91234567 e/yy@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `yy@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

### Locating hairdressers/clients by name: `find`

Finds hairdressers/clients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

### Deleting a client : `delete_client`

Removes a specific client from the database.

Format: `delete_client INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

### Deleting a hairdresser : `delete_hairdresser`

Removes a specific hairdresser from the database.

Format: `delete_hairdresser INDEX`

* Deletes the hairdresser at the specified `INDEX`.
* The index refers to the index number shown in the displayed hairdresser list.
* The index **must be a positive integer** 1, 2, 3, …​


### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

All hairdressers and client information will be stored automatically after any command that changes the data. There is no need to save manually. Will be loaded upon restart of the application.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HairstyleX home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add Hairdresser** | `add_hairdresser n/NAME p/PHONE_NUMBER e/EMAIL ti/TITLE [s/SPECIALISATION] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com ti/senior s/colouring t/colleague`
**Add Client** | `add_client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS tr/TREATMENT [p/PREFERENCE] [t/TAG]…​` <br> e.g., `add n/Nicholas Toh p/12345678 e/niktoh@example.com a/123, Clementi Rd, 1234665 tr/colouring p/female stylist t/frequent`
**List Hairdressers** | `list_hairdresser`
**List Clients** | `list_client`
**Edit Hairdresser** | `edit_hairdresser INDEX INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [ti/TITLE] [s/SPECIALISATION] [t/TAG]…​`<br> e.g.,`edit_hairdresser 2 n/James Lee ti/senior`
**Edit Client** | `edit_client INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tr/TREATMENT] [p/PREFERENCE] [t/TAG]…​`<br> e.g.,`edit_client 2 n/James Tan p/Female Stylist`
**Delete Hairdresser** | `delete_hairdresser INDEX`<br> e.g., `delete_hairdresser 3`
**Delete Client** | `delete_client INDEX`<br> e.g., `delete_client 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Clear** | `clear`
**Exit** | `exit`
