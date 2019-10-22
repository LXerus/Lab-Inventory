package Controllers.User;

import Clases.BaseDeDatos.DBConnection;
import Clases.Cruds.UserCrud;
import Clases.Models.User;
import Clases.Models.CurrentUser;
import Controllers.MainMenu.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        privilegesList = getPrivilegesList();
        registrar_usuario_cmbox_privilegios.setItems(privilegesList);
    }

    public void getUserMenu(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/user_menu_gui.fxml"));
            userMenuPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(userMenuPane);
    }

    public void createUser(){
        entryDate = LocalDate.now();
        if(verifyData()){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar User");
            confirmacion.setHeaderText("Registrando un nuevo usuario");
            confirmacion.setContentText("Esta a punto de registrar a un nuevo usuario ¿desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                user = new User(name, lastName, password, entryDate, area, active, email, privileges);
                usuariosCrud.create(user);
                createUser.createUser(user);
                createUser.setPrivileges(user);
                JOptionPane.showMessageDialog(null,"¡El usuario se ha registrado exitosamente!");
                getUserMenu();
            }else {
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
            }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
         }


    }

    private ObservableList<String> getPrivilegesList(){
        ObservableList<String> privilegesList = FXCollections.observableArrayList();
        String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        DBConnection JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        Connection connection = JDBDBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSQL);
            while (resultSet.next()){
                String tipo_de_privilegio = resultSet.getString(2);
                privilegesList.add(tipo_de_privilegio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connection = null;
            JDBDBConnection.disconnect();
        }
        return privilegesList;
    }

    public boolean verifyData(){
        boolean valid = true;

        if(registrar_usuario_txtfl_nombres.getText().isEmpty()){
            valid = false;
        }else{
            name = registrar_usuario_txtfl_nombres.getText();
        }

        if(registrar_usuario_txtfl_apellidos.getText().isEmpty()){
            valid = false;
        }else{
            lastName = registrar_usuario_txtfl_apellidos.getText();
        }

        if(registrar_usuario_txtfl_password.getText().isEmpty()){
            valid = false;
        }else{
            password = registrar_usuario_txtfl_password.getText();
        }

        if(registrar_usuario_txtfl_area.getText().isEmpty()){
            valid = false;
        }else {
            area = registrar_usuario_txtfl_area.getText();
        }

        if(registrar_usuario_txtfl_email.getText().isEmpty()){
            valid = false;
        }else{
            email = registrar_usuario_txtfl_email.getText();
        }

        if(registrar_usuario_cmbox_privilegios.getValue() == null){
            valid = false;
        }else {
            privileges = registrar_usuario_cmbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
        }

        return valid;
    }

    @FXML
    TextField registrar_usuario_txtfl_nombres;
    @FXML
    TextField registrar_usuario_txtfl_apellidos;
    @FXML
    TextField registrar_usuario_txtfl_password;
    @FXML
    TextField registrar_usuario_txtfl_area;
    @FXML
    TextField registrar_usuario_txtfl_email;
    @FXML
    AnchorPane pane_registrar_usuario;
    @FXML
    ComboBox registrar_usuario_cmbox_privilegios;

    AnchorPane userMenuPane;
    FXMLLoader fxmlLoader;
    User user;
    UserCrud usuariosCrud = new UserCrud();
    String name;
    String lastName;
    String password;
    LocalDate entryDate;
    String area;
    Boolean active = true;
    String email;
    int privileges;
    ObservableList privilegesList;
    CreateUser createUser = new CreateUser();
}
