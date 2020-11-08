---
layout: page
title: Toh Zhen Yu, Nicholas's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

#### Code contributed

Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nicktohzyu) to view the code that I have contributed.

#### Enhancements implemented
* **Create Appointment and UniqueAppointmentList class**: Designed and created `Appointment` and `UniqueAppointmentList` classes to be used in **HairStyleX** (Pull request [\#89](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/89), [\#93](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/93), [\#97](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/97))
    * What it does: Defines the blueprint and rules of creating appointments in **HairStyleX**
    * Justification: These classes are fundamental to the entire project, since they define an interaction between a client and hairdresser and what attributes they should have.
    * Highlights:
        * Careful consideration of good OOP design was made when creating these classes. The Appointment class was made to be immutable, and encapsulating classes for various attributes such as `AppointmentDate`, `AppointmentTime`, and `AppointmentStatus`.

* **Testing for Client class**: Created tests for `Client` class, created testing utilities such as `ClientBuilder`, `TypicalClients` (Pull request [\#131](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/131))

* **Refactor Gender representation from String to Enum**: Changed the representation of gender from using `String`s to `Enum`s (Pull request [\#159](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/159))
    * The enhancement also included a process to convert the gender to a `String` representation for display purposes.

* **Added feature to prevent clashing appointments**: Added checks to the "add appointment" process such that no client or hairdresser can be involved in simultaneous appointments. (Pull request [\#178](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/178), [\#198](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/198))

* **Testing for Appointment class**: Created tests for `Appointment` class, including extensive testing for the clash-avoidance feature (Pull request [\#210](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/210))

#### Contributions to the User Guide
* Responsible for the section on [appointments](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#44-appointment-management). 
* Provided a detailed guide supplemented with screenshots and examples for relevant commands including `add_appt`, `edit_appt`, `delete_appt`, `filter_appt`.

#### Contributions to the Developer Guide
* Responsible for the section on implementation of the [appointment](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#appointment-feature) feature. 
* Added a detailed sequence diagram representing the process involved in validating and executing an "add appointment" command.
* Proposed future improvements which improve the runtime performance of HairStyleX.

#### Review/mentoring contributions
* Reviewed PRs including [\#55](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/55), [\#85](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/85), [\#161](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/161)

* Reviewed documentation work of other teammates for typos (through informal communication channels such as Telegram)

#### Contributions beyond the project team

* [Reported 13 bugs](https://github.com/iamjamestan/ped/issues) in group T-17-'s tP during mock PE
