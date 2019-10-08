package Clases.Launcher;

import Controllers.User.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    /**
     * <h1>LabInventory</h1>
     * <p>This is an specific program to record the inventory for a laboratory,
     * it also can keep track of the usage for each of the profucts.</p>
     *<p><br/> This application also needs a direct connection to a MySQL data base, the structure should be provided in the documentation./p>
     *
     * @author Cristhian Rodrigo Franco
     * @version 0.8
     * @since 2019-10-05
     * */
    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginController =  new LoginController();
        loginController.createLoginPane(stage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}