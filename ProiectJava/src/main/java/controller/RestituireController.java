package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class RestituireController {

    @FXML
    private Button restituieCarteButton;

    @FXML
    private TableView<?> inchirieriTableView;

    @FXML
    private TableColumn<?, ?> numeCititorTableColumn;

    @FXML
    private TableColumn<?, ?> titluCarteTableColumn;

    @FXML
    private TableColumn<?, ?> restituitTableColumn;

    @FXML
    void restituieCarteEvent(MouseEvent event) {

    }

}
