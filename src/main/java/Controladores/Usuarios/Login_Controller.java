package Controladores.Usuarios;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.Configuration;
import Clases.Modelos.User;
import Clases.Modelos.CurrentUser;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
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

public class Login_Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comprobarDatosConfiguracion();
    }

    public Stage crearPanelLogin(Stage stage) throws IOException {
        panel_login = FXMLLoader.load(getClass().getResource("/gui/Menus/login_gui.fxml"));
        stage.setTitle("LogIn");
        stage.setScene(new Scene(panel_login, 600, 400));
        stage.show();
        return stage;
    }

    public void abrirConfiguracion(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/ConfiguracionBD/login_configuracion_gui.fxml"));
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
        LocalDate fechaDeEntrada;
        LocalTime horaDeEntrada;
        User user;

        if(validarDatos()){
            userName = login_txtfl_id.getText();
            password = login_pswfl_password.getText();
            JDBConnection = new JDBConnection(userName, password);
            SQLConnection  = JDBConnection.getConnection();
            ResultSet usuarioResultSet = null;

                if(JDBConnection.getConnection() == null){
                    login_texto_loginfail.setText("El ID de usuario o contraseña son invalidos");
                }else{
                    try {

                        String sqlQuery = "SELECT id, nombres, apellidos, password, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios FROM usuario WHERE nombres='"+ userName +"' AND password='" + password + "'";
                        Statement buscarUsuarioStatement = SQLConnection.createStatement();
                        usuarioResultSet = buscarUsuarioStatement.executeQuery(sqlQuery);
                        if(usuarioResultSet.next()) {
                            int id_usuario = usuarioResultSet.getInt(1);
                            String nombre_usuario = usuarioResultSet.getString(2);
                            String apellidos_usuario = usuarioResultSet.getString(3);
                            String password_usuario = usuarioResultSet.getString(4);
                            Date fdi_usuario = usuarioResultSet.getDate(5);
                            String area_usuario = usuarioResultSet.getString(6);
                            boolean activo_usuario = usuarioResultSet.getBoolean(7);
                            String correo_electronico_usuario = usuarioResultSet.getString(8);
                            int privilegios_usuario = usuarioResultSet.getInt(9);

                            fechaDeEntrada = LocalDate.now();
                            horaDeEntrada = LocalTime.now();
                            user = new User(id_usuario, nombre_usuario, apellidos_usuario, password_usuario, fdi_usuario.toLocalDate(), area_usuario, activo_usuario, correo_electronico_usuario, privilegios_usuario);
                            CurrentUser.getUserData(user, fechaDeEntrada, horaDeEntrada);
                            usuarioResultSet.beforeFirst();

                            loginStage = (Stage) login_btn_login.getScene().getWindow();
                            menuPrincipalControlador.crearMenuPrincipal(loginStage);
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

    public void cancelar(){
        Stage stage = (Stage) login_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private void comprobarDatosConfiguracion(){
        configuration.readConfiguration();
        String servidor = Configuration.getServer();
        String puerto = Configuration.getPort();
        String baseDeDatos = Configuration.getDataBase();
        if(Configuration.getServer() == null || Configuration.getPort() == null || Configuration.getDataBase() == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/ConfiguracionBD/login_configuracion_gui.fxml"));
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

    private boolean validarDatos(){
        boolean datosEstado = true;
        if(login_txtfl_id.getText().isEmpty()){
            datosEstado = false;
        }
        if(login_pswfl_password.getText().isEmpty()){
            datosEstado = false;
        }
        return datosEstado;
    }

    private Stage loginStage;
    private MenuPrincipal_Controlador menuPrincipalControlador = new MenuPrincipal_Controlador();
    private JDBConnection JDBConnection;
    private Connection SQLConnection;
    private String userName;
    private String password;
    private Configuration configuration = new Configuration();
    @FXML
    private AnchorPane panel_login;
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
