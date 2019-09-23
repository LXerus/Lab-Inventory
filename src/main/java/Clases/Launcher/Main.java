package Clases.Launcher;

import Controladores.Usuarios.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginController =  new LoginController();
        loginController.createLoginPane(stage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}