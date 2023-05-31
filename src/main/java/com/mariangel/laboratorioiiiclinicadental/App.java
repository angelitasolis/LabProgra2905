package com.mariangel.laboratorioiiiclinicadental;

import com.mariangel.clinicadental.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

        private static Scene scene;
   @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);
        stage.setTitle("Laboratorio III");
        stage.getIcons().add(new Image("/com/mariangel/laboratorioiiiclinicadental/imagenes/icono.png"));
       //FlowController.getInstance().goViewInWindow("LogInView");
       FlowController.getInstance().goMain();
       
    }

    public static void main(String[] args) {
        launch();
    }

}