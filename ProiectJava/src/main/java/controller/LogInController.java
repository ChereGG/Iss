package controller;

import domain.Bibliotecar;
import domain.Cititor;
import domain.Utilizator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LogInController {

    private Service service;
    @FXML
    private TextField usernameTextField;

    @FXML
    private Button logInButon;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField passwordTextField;


    public void setService(Service service){
        this.service=service;
    }
    @FXML
    void logInEvent(MouseEvent event) {
        String username=this.usernameTextField.getText();
        String password=this.passwordTextField.getText();

        Utilizator utilizator=this.service.getUtilizator(username);
        if(utilizator!= null){
            if(utilizator.getHashing()==password.hashCode()){
                if(utilizator instanceof Cititor){
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/views/MainViewCititor.fxml"));
                        AnchorPane root = loader.load();
                        MainCititorController ctrl = loader.getController();
                        Stage stage=new Stage();
                        ctrl.setService(this.service, (Cititor) utilizator);
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
                else if(utilizator instanceof Bibliotecar){
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/views/MainViewBibliotecar.fxml"));
                        AnchorPane root = loader.load();
                        MainBibliotecarController ctrl = loader.getController();
                        Stage stage=new Stage();
                        ctrl.setService(this.service, (Bibliotecar) utilizator);
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
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Date gresite");alert.showAndWait();
            alert.showAndWait();
        }
    }

    @FXML
    void signUpEvent(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/SignUpView.fxml"));
            AnchorPane root = loader.load();
            SignUpController ctrl = loader.getController();
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

}
