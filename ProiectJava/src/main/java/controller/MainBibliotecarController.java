package controller;

import domain.Bibliotecar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import service.Service;

public class MainBibliotecarController {

    private Service service;
    private Bibliotecar bibliotecar;
    @FXML
    private Button vizualizeazaInchirieriButton;

    @FXML
    private TableView<?> inchirieriTableView;

    @FXML
    private TableColumn<?, ?> numeCititorTableColumn;

    @FXML
    private TableColumn<?, ?> titluCarteTableColumn;

    @FXML
    private TableColumn<?, ?> restituitTableColumn;

    @FXML
    private Button penalizeazaCItitorButton;

    public void setService(Service service, Bibliotecar bibliotecar){
        this.service=service;
        this.bibliotecar = bibliotecar;
    }
    @FXML
    void penalizeazaCititorEvent(MouseEvent event) {

    }

    @FXML
    void vizualizeazaInchirieriEvent(MouseEvent event) {

    }

}
