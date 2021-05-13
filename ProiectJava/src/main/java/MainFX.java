import controller.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repository.BibliotecarHiberRepo;
import repository.BibliotecarRepository;
import repository.CititorHiberRepo;
import repository.CititorRepository;
import service.Service;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Service getService(){
        CititorRepository cititorRepository=new CititorHiberRepo();
        BibliotecarRepository bibliotecarRepository=new BibliotecarHiberRepo();
        Service service=new Service(cititorRepository,bibliotecarRepository);
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
