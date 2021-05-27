import controller.LogInController;
import domain.Penalizare;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repository.*;
import service.Service;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Service getService(){
        CititorRepository cititorRepository=new CititorHiberRepo();
        BibliotecarRepository bibliotecarRepository=new BibliotecarHiberRepo();
        CarteRepository carteRepository=new CarteHiberRepo();
        InchiriereRepository inchiriereRepository=new InchiriereHiberRepo();
        PenalizareRepository penalizareRepository=new PenalizareHiberRepo();
        Service service=new Service(cititorRepository,bibliotecarRepository,carteRepository,inchiriereRepository,penalizareRepository);
        return service;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/LogInView.fxml"));
            Parent root = loader.load();
            LogInController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("LogIn");
            primaryStage.show();
        }
        catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e);
            alert.showAndWait();
        }
    }
}
