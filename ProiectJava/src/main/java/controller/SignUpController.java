package controller;

import domain.Bibliotecar;
import domain.Cititor;
import domain.Utilizator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class SignUpController {

    private Service service;
    @FXML
    private TextField numeTextField;

    @FXML
    private TextField prenumeTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ComboBox<String> utilizatorTypeCombo;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField adresaTextField;

    @FXML
    private TextField telefonTextField;

    public void setService(Service service){
        this.service=service;
        ObservableList<String> l= FXCollections.observableArrayList("Cititor","Bibliotecar");
        this.utilizatorTypeCombo.setItems(l);
        this.utilizatorTypeCombo.setValue("Select one");
    }

    @FXML
    void signUpEvent(MouseEvent event) {
        String nume=this.numeTextField.getText();
        String prenume=this.prenumeTextField.getText();
        String username=this.usernameTextField.getText();
        String password=this.passwordTextField.getText();
        String email=this.emailTextField.getText();
        String adresa=this.adresaTextField.getText();
        String telefon=this.telefonTextField.getText();
        Utilizator utilizator=this.service.findUtilizator(username);
        if(utilizator==null){
            if(this.utilizatorTypeCombo.getValue().equals("Cititor")){
                Cititor cititor=new Cititor(username,password.hashCode(),nume,prenume,email ,adresa,telefon);
                this.service.addUtilizator(cititor);
            }
            else if(this.utilizatorTypeCombo.getValue().equals("Bibliotecar")){
                Bibliotecar bibliotecar =new Bibliotecar(username,password.hashCode(),nume,prenume);
                this.service.addUtilizator(bibliotecar);
            }
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
                Stage thisstage=(Stage)this.signUpButton.getScene().getWindow();
                thisstage.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Date gresite");alert.showAndWait();
            alert.showAndWait();
        }




    }

}
