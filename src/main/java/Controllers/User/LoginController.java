package Controllers.User;

import Clases.BaseDeDatos.DBConnection;
import Clases.Models.Configuration;
import Clases.Models.User;
import Clases.Models.CurrentUser;
import Controllers.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verifyConfigurationData();
    }

    public Stage createLoginPane(Stage stage) throws IOException {
        loginPane = FXMLLoader.load(getClass().getResource("/gui/Menus/login_gui.fxml"));
        stage.setTitle("LogIn");
        stage.setScene(new Scene(loginPane, 600, 400));
        stage.show();
        return stage;
    }

    public void getConfiguration(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/login_configuration_gui.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loginCheck(){
        LocalDate entryDate;
        LocalTime entryTime;
        User user;

        if(verifyData()){
            userName = login_txtfl_id.getText();
            password = login_pswfl_password.getText();
            JDBDBConnection = new DBConnection(userName, password);
            connection = JDBDBConnection.getConnection();
            ResultSet usuarioResultSet = null;

            if (JDBDBConnection.getConnection() == null) {
                    login_texto_loginfail.setText("El ID de usuario o contraseña son invalidos");
                }else{
                    try {

                        String sqlQuery = "SELECT id, nombres, apellidos, password, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios FROM usuario WHERE nombres='"+ userName +"' AND password='" + password + "'";
                        Statement buscarUsuarioStatement = connection.createStatement();
                        usuarioResultSet = buscarUsuarioStatement.executeQuery(sqlQuery);
                        if(usuarioResultSet.next()) {
                            int id = usuarioResultSet.getInt(1);
                            String name = usuarioResultSet.getString(2);
                            String lastName = usuarioResultSet.getString(3);
                            String password = usuarioResultSet.getString(4);
                            Date userRegistryDate = usuarioResultSet.getDate(5);
                            String area = usuarioResultSet.getString(6);
                            boolean active = usuarioResultSet.getBoolean(7);
                            String email = usuarioResultSet.getString(8);
                            int privileges = usuarioResultSet.getInt(9);

                            entryDate = LocalDate.now();
                            entryTime = LocalTime.now();
                            user = new User(id, name, lastName, password, userRegistryDate.toLocalDate(), area, active, email, privileges);
                            CurrentUser.getUserData(user, entryDate, entryTime);
                            usuarioResultSet.beforeFirst();

                            loginStage = (Stage) login_btn_login.getScene().getWindow();
                            menuPrincipalControlador.createMainMenu(loginStage);
                        }else{
                            JOptionPane.showMessageDialog(null,"La cuenta de usuario no esta completa, por favor contacte a su administrador de red.");
                        }

                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar su ID y contraseña.");
        }
    }

    public void cancel(){
        Stage stage = (Stage) login_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private void verifyConfigurationData(){
        configuration.readConfiguration();
        String server = Configuration.getServer();
        String port = Configuration.getPort();
        String dataBase = Configuration.getDataBase();
        if(Configuration.getServer() == null || Configuration.getPort() == null || Configuration.getDataBase() == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/login_configuration_gui.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
                stage.alwaysOnTopProperty();
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private boolean verifyData(){
        boolean valid = true;
        if(login_txtfl_id.getText().isEmpty()){
            valid = false;
        }
        if(login_pswfl_password.getText().isEmpty()){
            valid = false;
        }
        return valid;
    }

    private Stage loginStage;
    private MainMenuController menuPrincipalControlador = new MainMenuController();
    private DBConnection JDBDBConnection;
    private Connection connection;
    private String userName;
    private String password;
    private Configuration configuration = new Configuration();
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Label login_texto_loginfail;
    @FXML
    private TextField login_txtfl_id;
    @FXML
    private PasswordField login_pswfl_password;
    @FXML
    private Button login_btn_cancelar;
    @FXML
    private Button login_btn_login;

}
