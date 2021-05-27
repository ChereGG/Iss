package controller;

import domain.Cititor;
import domain.Inchiriere;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.List;


public class RestituireController {

    private Service service;
    private  Cititor cititor;
    @FXML
    private Button restituieCarteButton;

    @FXML
    private TableView<Inchiriere> inchirieriTableView;

    @FXML
    private TableColumn<Inchiriere, String> numeCititorTableColumn;

    @FXML
    private TableColumn<Inchiriere, String> titluCarteTableColumn;

    @FXML
    private TableColumn<Inchiriere, Boolean> restituitTableColumn;
    private ObservableList<Inchiriere> model= FXCollections.observableArrayList();
    public void setService(Service service, Cititor cititor) {
        this.service = service;
        this.cititor = cititor;
        this.titluCarteTableColumn.setCellValueFactory(c-> new SimpleStringProperty(
                this.service.findCarteById(c.getValue().getIdCarte()).getTitlu()
        ));
        this.numeCititorTableColumn.setCellValueFactory(c-> new SimpleStringProperty(
                this.service.findCititorById(c.getValue().getIdUtilizator()).getNume()+
                        " "+
                        this.service.findCititorById(c.getValue().getIdUtilizator()).getPrenume()
        ));
        this.restituitTableColumn.setCellValueFactory(new PropertyValueFactory<Inchiriere,Boolean>("returned"));
        this.loadModel((List<Inchiriere>) this.service.findAllInchirieriNotReturned());
        this.inchirieriTableView.setItems(model);
    }
    private void loadModel(List<Inchiriere> list){
        model.setAll(list);
    }

    @FXML
    void restituieCarteEvent(MouseEvent event) {
        Inchiriere inchiriere = this.inchirieriTableView.getSelectionModel().getSelectedItem();
        if (inchiriere != null) {
            try {
                this.service.returnBook(inchiriere);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/MainViewCititor.fxml"));
                AnchorPane root = loader.load();
                MainCititorController ctrl = loader.getController();
                Stage stage = new Stage();
                ctrl.setService(this.service, cititor);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Main");
                stage.show();
                Stage thisstage = (Stage) this.restituieCarteButton.getScene().getWindow();
                thisstage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Va rog selectati o inchiriere!");
            alert.showAndWait();
        }
    }

}
