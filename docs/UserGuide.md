---
layout: page
title: User Guide
---

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands.

<div markdown="block" class="alert alert-secondary">
* Table of Contents
{:toc}
</div>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction
(Contributed by Tan Yu Li, James)

Welcome to the User Guide of HairStyleX.
 
Today, many budding hair salons still resort to using pen and paper to keep track of their appointments, clients, and hairdressers due to their tight budget. While suitable for a small client base, this is not sustainable in the long run as their client base increases, since recording and searching for information manually would be slow and prone to human errors. Our salon management application **HairStyleX** is designed to make this process much easier for salon managers who have at least a laptop at their front desk.

This application uses a Command Line Interface (CLI); this means that you operate the application by typing commands into a Command Box. If you are fast at typing, you can manage your appointments faster than other Graphical User Interface (GUI) applications; GUI applications allow users to interact with the application through graphical icons such as buttons.

The purpose of this user guide is to provide you with an in-depth documentation on how to install and use our application. What are you waiting for? Head on to [Section 2, “Quick Start”](#quick-start) .

--------------------------------------------------------------------------------------------------------------------

## 2. Quick Start

To get started with using **HairStyleX**, you can follow these steps:

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HairStyleX.jar` from [here](https://github.com/AY2021S1-CS2103T-T15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for **HairStyleX**.

1. Double-click the file to start the app. The GUI similar to the one shown in <u>Figure 1</u> should appear in a few seconds. Note how the app contains some sample data.<br>

1. At the top of the screen, type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to [Section 3.2.3, "Command Syntax and Usage"](#command-syntax-and-usage) on how to use commands and [Section 4, "Features"](#features) below for details of each command.

![Ui](images/Ui.png)
*Figure 1. GUI of **HairStyleX***
--------------------------------------------------------------------------------------------------------------------

## 3. About

(Contributed by Tan Yu Li, James)

### 3.1 Structure of this document

There are many things you can do with **HairStyleX**. Thus, we have structured this User Guide in such a way that you can easily find what you need. In the next subsection, [Section 3.2, “Reading this Document”](#reading-this-document), you will find useful tips on reading this document. It is then followed by [Section 4, “Features”](#features) where the four main features of **HairStyleX** and their commands are documented:

* Client Management

* Hairdresser Management

* Appointment Management

* Alias Management

### 3.2 Reading this document

Before moving on to the next section, you may want to familiarise yourself with some technical terms, symbols and syntax that are used throughout the document. In this subsection, you can find all the information you need to know when reading this document.

#### 3.2.1 Terminology related to the Graphical User Interface (GUI)

The following figure shows the GUI of **HairStyleX**. It is annotated with the name of the GUI components.

![UiAnnotation](images/UiAnnotation.png)
*Figure 2. Annotated GUI of **HairStyleX***

#### 3.2.2 General Symbols and Syntax

The table below explains the general symbols and syntax used throughout the document.

**Symbol/Syntax** | **Meaning**
--------|------------------
`command` | A light blue highlight (called a code-block markup) indicates that this is a command that can be typed into the command box and executed by the application.
:information_source: | An information icon indicates that the enclosed text are notes regarding this section.
:warning: | A warning sign indicates that the enclosed text is important, and usually entails details about potential errors.
:bulb: | A light bulb indicates that the enclosed text is a tip.


#### 3.2.3 Command Syntax and Usage

Since **HairStyleX** is a CLI application, knowing how to use commands is very important. The following subsection will teach you how to read and use commands in **HairStyleX**. All commands in **HairStyleX** follow similar formats as described below and examples will be provided to help you understand their usage. Examples of commands and their formats will be written in `code-blocks`.

The table below explains some important technical terms. An example will be provided to help you visualize these terms.

**Technical Term** | **Meaning**
--------|------------------
Command word | 	The first word of a command. It determines the action that **HairStyleX** should perform.
Parameter | The word or group of words following the command word. They are values given to a command to perform the specified action.
Prefix | The letter(s) that at the start of a parameter. It distinguishes one parameter from another.

Breakdown:

Command Word - `add_hairdresser`

Parameters - `NAME`, `PHONE`, `EMAIL`

Prefix - `n/`, `p/`, `e/`

The following four points explain how you can use a command with the provided format. More examples will be provided for each command in [Section 4, “Features”](#features).

1. Words in the upper case are the parameters which are required.

    * Format given: `delete_client CLIENT_ID`
    
    * Acceptable input: `delete_client 1`

1. Items in square brackets are optional.

    * Format given: `edit_client ID [n/NAME] [p/PHONE] [e/EMAIL] [g/GENDER] [a/ADDRESS] [t/TAG]…​`
    
    * Acceptable input: `edit_client 1 n/Aileen e/aileenlam@email.com`
    
1. Items with `…`​ after them can be used zero or multiple times.

    * Format given: `add_client n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​`
    
    * Acceptable input 1 (no tags): `add_client n/John Doe p/98765432 e/johnd@example.com g/M a/311, Clementi Ave 2, #02-25`
    
    * Acceptable input 2: (two tags): `add_client n/John Doe p/98765432 e/johnd@example.com g/M a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney`
    
1. Parameters with prefixes can be supplied in any order.

    * Format given: `add_client n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​`
    
    * Acceptable input 1 (same order as given format): `add_client n/John Doe p/98765432 e/johnd@example.com g/M a/311, Clementi Ave 2, #02-25 t/friends`
    
    * Acceptable input 2 (different order as given format): `add_client p/98765432 e/johnd@example.com n/John Doe g/M a/311, Clementi Ave 2, #02-25 t/friends`
    
With the above information, you can now move on to [Section 4, Features](#features) to learn all the features of the application and start using the commands.
    
--------------------------------------------------------------------------------------------------------------------

## Features

This section contains all the information you need to know about the features of **HairStyleX**. To use each feature or sub-feature, you will need to enter the command into the Command Box.

<div markdown="block" class="alert alert-warning">

**:Warning: Warning about the use of features:**<br>

As you use our features, other files will be created and stored in the folder that contains **HairStyleX.jar**. These files are created with the purpose of storing your data. Please do not alter or delete any of them, as this might result in permanent loss of data.

</div>

<div markdown="block" class="alert alert-light">

**:bulb: Tips for first time users**<br>

If this is your first time using **HairStyleX**, you might notice that this application is pre-filled with sample data. To delete all data, use the `clear` command as stated below.

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

All hairdressers and client information will be stored automatically after any command that changes the data. There is no need to save manually. This data be loaded upon restart of the application.

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

### Hairdresser commands

#### Adding a hairdresser : `add_hairdresser`

Adds a hairdresser to the database.

Format: `add_hairdresser n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER ti/TITLE [s/SPECIALISATION]…​`

#### Listing all hairdressers : `list_hairdresser`

Shows a list of all hairdressers in the database.

Format: `list_hairdresser`

Format: `list_client`

#### Editing a hairdresser : `edit_hairdresser`

Edits an existing hairdresser in the database.

Format: `edit_hairdresser ID [n/NAME] [p/PHONE] [e/EMAIL] [e/GENDER] [ti/TITLE] [s/SPECIALISATION]…​`

* Edits the hairdresser with the specified `ID`. The index refers to the index number shown in the displayed hairdresser list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing specialisations, the existing specialisations of the hairdresser will be removed i.e adding of specialisations is not cumulative.
* You can remove all the hairdresser’s tags by typing `s/` without
    specifying any specialisations after it.

Examples:
*  `edit_hairdresser 1 p/91234567 e/yy@example.com` Edits the phone number and email address of the 1st hairdresser to be `91234567` and `yy@example.com` respectively.
*  `edit_hairdresser 2 n/Betsy Crower s/` Edits the name of the 2nd hairdresser to be `Betsy Crower` and clears all existing specialisations.

#### Deleting a hairdresser : `delete_hairdresser`

Removes a specific hairdresser from the database.

Format: `delete_hairdresser ID`

* Deletes the hairdresser with the specified `ID`.
* The index refers to the index number shown in the displayed hairdresser list.
* The index **must be a positive integer** 1, 2, 3, …​
* All appointments with this corresponding hairdresser will now show "DELETED" for the hairdresser placeholder

### Alias commands

#### Adding a command alias : `add_alias`

Adds a new command alias to the database.

Format: `add_alias old/OLD_ALIAS new/NEW_ALIAS`


#### Deleting a command_alias : `delete_alias`

Removes a command alias from the database.

Format: `delete_alias ALIAS`

## 5. FAQ
(Contributed by Tan Yu Li, James)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HairstyleX home folder.

**Q**: Why aren’t I allowed to edit my date or time for the appointment?
**A**: Editing the date or time may cause conflicts in your schedule if the change results in two appointments with the same hairdresser occuring concurrently. However, we are working on a feature that will allow you to resolve these conflicts in the upcoming version of **HairStyleX**.

**Q**: What do I do if I want to change my appointment’s date or time since I cannot edit them?
**A**: Unfortunately, you have to delete the appointment and create a new one.

**Q**: What happens if I schedule 2 of my appointments with the same hairdresser on the same date and time? Will **HairStyleX** detect it?
**A**: Yes, **HairStyleX** will detect such cases and prevent you from creating such an appointment.

--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

(Contributed by Tan Yu Li, James)

## 6.1 General Commands

Action | Format, Examples
--------|------------------
**Help** | `help`
**Clear** | `clear`
**Exit** | `exit`

## 6.2 Client Commands

Action | Format, Examples
--------|------------------
**Add Client** | `add_client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS tr/TREATMENT [p/PREFERENCE] [t/TAG]…​` <br> e.g., `add n/Nicholas Toh p/12345678 e/niktoh@example.com a/123, Clementi Rd, 1234665 tr/colouring p/female stylist t/frequent`
**List Clients** | `list_client`
**Edit Client** | `edit_client ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tr/TREATMENT] [p/PREFERENCE] [t/TAG]…​`<br> e.g.,`edit_client 2 n/James Tan p/Female Stylist`
**Delete Client** | `delete_client ID` <br> e.g., `delete_client 3`
**Filter Client** | `filter_client KEYWORD...`

## 6.3 Hairdresser Commands

Action | Format, Examples
--------|------------------
**Add Hairdresser** | `add_hairdresser n/NAME p/PHONE_NUMBER e/EMAIL ti/TITLE [s/SPECIALISATION] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com ti/senior s/colouring t/colleague`
**List Hairdressers** | `list_hairdresser`
**Edit Hairdresser** | `edit_hairdresser ID ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [ti/TITLE] [s/SPECIALISATION] [t/TAG]…​`<br> e.g.,`edit_hairdresser 2 n/James Lee ti/senior`
**Filter Hairdresser** | `filter_hairdresser KEYWORD...`

## 6.4 Appointment Commands

Action | Format, Examples
--------|------------------
**Add Appointment** | `add_appt cid/CLIENT_ID hid/HAIRDRESSER_ID d/DATE t/TIME` <br> e.g., `add_appt cid/1 hid/1 d/2020-12-12 t/17:30`
**List Appointments** | `list_appt`
**Delete Appointment** | `delete_appt ID` <br> e.g., `delete_appt 3`

## 6.5 Alias Commands

Action | Format, Examples
--------|------------------
**Add Alias** | `add_alias old/OLD_ALIAS new/NEW_ALIAS` <br> e.g., `add_alias old/add_alias new/aa`
**Delete Alias** | `delete_alias ALIAS` <br> e.g., `delete_alias aa`
**List Alias** | `list_alias`
