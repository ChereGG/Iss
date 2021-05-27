package controller;

import domain.Bibliotecar;
import domain.Inchiriere;
import domain.Penalizare;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainBibliotecarController {

    private Service service;
    private Bibliotecar bibliotecar;
    @FXML
    private Button vizualizeazaInchirieriButton;

    @FXML
    private TableView<Inchiriere> inchirieriTableView;

    @FXML
    private TableColumn<Inchiriere, String> numeCititorTableColumn;

    @FXML
    private TableColumn<Inchiriere, String> titluCarteTableColumn;

    @FXML
    private TableColumn<Inchiriere, Boolean> restituitTableColumn;

    @FXML
    private Button penalizeazaCItitorButton;

    @FXML
    private Button logOutButton;

    private ObservableList<Inchiriere> model= FXCollections.observableArrayList();
    public void setService(Service service, Bibliotecar bibliotecar){
        this.service=service;
        this.bibliotecar = bibliotecar;
        this.titluCarteTableColumn.setCellValueFactory(c-> new SimpleStringProperty(
                this.service.findCarteById(c.getValue().getIdCarte()).getTitlu()
        ));
        this.numeCititorTableColumn.setCellValueFactory(c-> new SimpleStringProperty(
                this.service.findCititorById(c.getValue().getIdUtilizator()).getNume()+
                        " "+
                        this.service.findCititorById(c.getValue().getIdUtilizator()).getPrenume()
        ));
        this.restituitTableColumn.setCellValueFactory(new PropertyValueFactory<Inchiriere,Boolean>("returned"));
        this.inchirieriTableView.setItems(model);
    }
    private void loadModel(List<Inchiriere> list){
        model.setAll(list);
    }
    @FXML
    void penalizeazaCititorEvent(MouseEvent event) {
        Inchiriere inchiriere=this.inchirieriTableView.getSelectionModel().getSelectedItem();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, 10);
        Date finalDate = c.getTime();
        Penalizare penalizare=new Penalizare(inchiriere.getIdUtilizator(),strDate,sdfDate.format(finalDate),false);
        this.service.addPenalizare(penalizare);
    }

    @FXML
    void vizualizeazaInchirieriEvent(MouseEvent event) {
        this.loadModel((List<Inchiriere>) this.service.findAllInchirieri());
    }

    public void logOutEvent(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/LogInView.fxml"));
            AnchorPane root = loader.load();
            LogInController ctrl = loader.getController();
            Stage stage=new Stage();
            ctrl.setService(this.service);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main");
            stage.show();
            Stage thisstage=(Stage)this.logOutButton.getScene().getWindow();
            thisstage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
