////package seedu.address.ui;
////
////import java.util.logging.Logger;
////
////import javafx.collections.ObservableList;
////import javafx.fxml.FXML;
////import javafx.scene.control.ListCell;
////import javafx.scene.control.ListView;
////import javafx.scene.layout.Region;
////import seedu.address.commons.core.LogsCenter;
////import seedu.address.model.person.Person;
////import seedu.address.model.person.client.Client;
////
/////**
//// * Panel containing the list of persons.
//// */
////public class ClientListPanel extends UiPart<Region> {
////    private static final String FXML = "ClientListPanel.fxml";
////    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);
////
////    @FXML
////    private ListView<Client> clientListView;
////
////    /**
////     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
////     */
////    public ClientListPanel(ObservableList<Client> clientList) {
////        super(FXML);
////        clientListView.setItems(clientList);
////        clientListView.setCellFactory(listView -> new ClientListViewCell());
////    }
////
////    /**
////     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
////     */
////    class ClientListViewCell extends ListCell<Client> {
////        @Override
////        protected void updateItem(Client client, boolean empty) {
////            super.updateItem(client, empty);
////
////            if (empty || client == null) {
////                setGraphic(null);
////                setText(null);
////            } else {
////                setGraphic(new ClientCard(client, getIndex() + 1).getRoot());
////            }
////        }
////    }
////
////}
//
//
//import java.util.Objects;
//import java.util.function.Consumer;
//import java.util.logging.Logger;
//
//import javafx.beans.value.ObservableValue;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.Region;
//import seedu.address.commons.core.LogsCenter;
//import seedu.address.model.person.client.Client;
//import seedu.address.model.person.patient.Patient;
//import seedu.address.ui.UiPart;
//
///**
// * Panel containing the list of patients.
// */
//public class ClientListPanel extends UiPart<Region> {
//    private static final String FXML = "ClientListPanel.fxml";
//    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);
//
//    @FXML
//    private ListView<Client> patientListView;
//
//    public ClientListPanel(ObservableList<Client> patientList, ObservableValue<Client> selectedPatient,
//                            Consumer<Client> onSelectedClientChange) {
//        super(FXML);
//        patientListView.setItems(patientList);
//        patientListView.setCellFactory(listView -> new ClientListViewCell());
//        patientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            logger.fine("Selection in patient list panel changed to : '" + newValue + "'");
//            onSelectedPatientChange.accept(newValue);
//        });
//        selectedPatient.addListener((observable, oldValue, newValue) -> {
//            logger.fine("Selected patient changed to: " + newValue);
//
//            // Don't modify selection if we are already selecting the selected patient,
//            // otherwise we would have an infinite loop.
//            if (Objects.equals(patientListView.getSelectionModel().getSelectedItem(), newValue)) {
//                return;
//            }
//
//            if (newValue == null) {
//                patientListView.getSelectionModel().clearSelection();
//            } else {
//                int index = patientListView.getItems().indexOf(newValue);
//                patientListView.scrollTo(index);
//                patientListView.getSelectionModel().clearAndSelect(index);
//            }
//        });
//    }
//
//    /**
//     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
//     */
//    class PatientListViewCell extends ListCell<Patient> {
//        @Override
//        protected void updateItem(Patient patient, boolean empty) {
//            super.updateItem(patient, empty);
//
//            if (empty || patient == null) {
//                setGraphic(null);
//                setText(null);
//            } else {
//                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
//            }
//        }
//    }
//
//}
