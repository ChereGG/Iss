package controller;

import domain.Cititor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import service.Service;

public class MainCititorController {

    private Service service;

    private Cititor cititor;
    @FXML
    private Button listaCartiButton;

    @FXML
    private TableView<?> tableViewCarti;

    @FXML
    private TableColumn<?, ?> tableColumnTitlu;

    @FXML
    private TableColumn<?, ?> tableColumnAutor;

    @FXML
    private TableColumn<?, ?> tableColumnEditura;

    @FXML
    private TableColumn<?, ?> tableColumnDataPublicarii;

    @FXML
    private Button inchiriazaCarteButton;

    @FXML
    private Button stareaInchirierillorButton;

    public void setService(Service service,Cititor cititor){
        this.service=service;
        this.cititor=cititor;
    }
    @FXML
    void inchiriazaCarteEvent(MouseEvent event) {

    }

    @FXML
    void listaCartiEvent(MouseEvent event) {

    }

    @FXML
    void stareaInchirieriilorEvent(MouseEvent event) {

    }

}
