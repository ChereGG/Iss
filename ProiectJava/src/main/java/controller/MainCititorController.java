package controller;

import domain.Carte;
import domain.Cititor;
import domain.Inchiriere;
import domain.Penalizare;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainCititorController {

    private Service service;

    private Cititor cititor;
    @FXML
    private Button listaCartiButton;

    @FXML
    private TableView<Carte> tableViewCarti;

    @FXML
    private TableColumn<Carte, String> tableColumnTitlu;

    @FXML
    private TableColumn<Carte, String> tableColumnAutor;

    @FXML
    private TableColumn<Carte, String> tableColumnEditura;

    @FXML
    private TableColumn<Carte, String> tableColumnDataPublicarii;

    @FXML
    private Button inchiriazaCarteButton;

    @FXML
    private Button stareaInchirierillorButton;

    @FXML
    private Button restituieButton;

    @FXML
    private Button logOutButton;

    private ObservableList<Carte> model= FXCollections.observableArrayList();

    public void setService(Service service,Cititor cititor){
        this.service=service;
        this.cititor=cititor;
        this.tableViewCarti.setItems(model);
        this.tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Carte,String>("autor"));
        this.tableColumnEditura.setCellValueFactory(new PropertyValueFactory<Carte,String>("editura"));
        this.tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Carte,String>("titlu"));
        this.tableColumnDataPublicarii.setCellValueFactory(new PropertyValueFactory<Carte,String>("data"));

    }

    private void loadModel(List<Carte> list){
        model.setAll(list);
    }
    @FXML
    void inchiriazaCarteEvent(MouseEvent event) {
        ArrayList<Penalizare> penalizares = (ArrayList<Penalizare>) this.service.findAllPenalizareForCititor(this.cititor.getId());
        AtomicReference<Penalizare> penal = new AtomicReference<>();
        penalizares.forEach(pen -> {
            if (!pen.isEnded()) {
                penal.set(pen);
            }
        });
        if (penal.get() != null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Sunteti penalizat pana la data de: " + penal.get().getDataFinalizarii());
            alert.showAndWait();
        } else {
            Carte carte = this.tableViewCarti.getSelectionModel().getSelectedItem();
            if (carte != null) {
                Inchiriere inchiriere = new Inchiriere(carte.getId(), this.cititor.getId(), false);
                this.service.addInchiriere(inchiriere);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setContentText("Va rog selectati o carte!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void listaCartiEvent(MouseEvent event) {
        this.loadModel((List<Carte>) this.service.findAllCarti());

    }

    @FXML
    void stareaInchirieriilorEvent(MouseEvent event) {
        List<Carte> inchiriate=new ArrayList<>();
        ArrayList<Inchiriere> inchirieri= (ArrayList<Inchiriere>) this.service.findAllInchirieriNotReturned();
        inchirieri.forEach(inchiriere -> {
            Carte carte=this.service.findCarteById(inchiriere.getIdCarte());
            inchiriate.add(carte);
        });
        this.loadModel(inchiriate);
    }

    public void restituieEvent(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/RestituireView.fxml"));
            AnchorPane root = loader.load();
            RestituireController ctrl = loader.getController();
            Stage stage = new Stage();
            ctrl.setService(this.service,this.cititor);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Restituire");
            stage.show();
            Stage thisstage = (Stage) this.restituieButton.getScene().getWindow();
            thisstage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOutEvent(MouseEvent mouseEvent) {
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
