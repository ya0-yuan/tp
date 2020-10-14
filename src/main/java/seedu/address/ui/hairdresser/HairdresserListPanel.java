package seedu.address.ui.hairdresser;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Hairdressers.
 */
public class HairdresserListPanel extends UiPart<Region> {
    private static final String FXML = "HairdresserListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HairdresserListPanel.class);

    @FXML
    private ListView<Hairdresser> hairdresserListView;

    /**
     * Creates a {@code HairdresserListPanel} with the given {@code ObservableList}.
     */
    public HairdresserListPanel(ObservableList<Hairdresser> hairdresserList) {
        super(FXML);
        hairdresserListView.setItems(hairdresserList);
        hairdresserListView.setCellFactory(listView -> new HairdresserListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Hairdresser} using a {@code HairdresserCard}.
     */
    class HairdresserListViewCell extends ListCell<Hairdresser> {
        @Override
        protected void updateItem(Hairdresser hairdresser, boolean empty) {
            super.updateItem(hairdresser, empty);

            if (empty || hairdresser == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HairdresserCard(hairdresser, getIndex() + 1).getRoot());
            }
        }
    }

}
