package Clases.Launcher;

import Controladores.Usuarios.Login_Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Login_Controller loginController =  new Login_Controller();
        loginController.crearPanelLogin(stage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}