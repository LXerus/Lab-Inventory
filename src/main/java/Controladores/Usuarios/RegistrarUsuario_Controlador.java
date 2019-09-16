package Controladores.Usuarios;

import Clases.BaseDeDatos.Conectar;
import Clases.Cruds.Usuarios_Crud;
import Clases.Modelos.Usuario;
import Clases.Modelos.UsuarioActual;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
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

public class RegistrarUsuario_Controlador implements Initializable {
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

    AnchorPane pane_menu_usuarios;
    FXMLLoader fxmlLoader;
    Usuario usuario;
    Usuarios_Crud usuariosCrud = new Usuarios_Crud();
    String nombres;
    String apellidos;
    String password;
    LocalDate fechaDeIngreso;
    String area;
    Boolean activo = true;
    String email;
    int privilegios;
    ObservableList listaDePrivilegios;
    CrearUsuario creadorUsuario = new CrearUsuario();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaDePrivilegios = obtenerListaDePrivilegios();
        registrar_usuario_cmbox_privilegios.setItems(listaDePrivilegios);
    }

    public void regresarMenuUsuario(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_usuarios_gui.fxml"));
            pane_menu_usuarios = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(pane_menu_usuarios);
    }

    public void registrarUsuario(){
        fechaDeIngreso = LocalDate.now();
        if(validarDatos()){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar Usuario");
            confirmacion.setHeaderText("Registrando un nuevo usuario");
            confirmacion.setContentText("Esta a punto de registrar a un nuevo usuario ¿desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                usuario = new Usuario(nombres, apellidos, password, fechaDeIngreso, area, activo, email, privilegios);
                usuariosCrud.registrarUsuario(usuario);
                creadorUsuario.crearUsuario(usuario);
                creadorUsuario.establecerPrivilegios(usuario);
                JOptionPane.showMessageDialog(null,"¡El usuario se ha registrado exitosamente!");
                regresarMenuUsuario();
            }else {
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
            }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
         }


    }

    private ObservableList<String> obtenerListaDePrivilegios(){
        ObservableList<String> listaDePrivilegios = FXCollections.observableArrayList();
        String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        Conectar conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        Connection conexionSQL = conectar.getConnection();
        try{
            Statement privilegiosStatement = conexionSQL.createStatement();
            ResultSet rsPrivilegios = privilegiosStatement.executeQuery(consultaSQL);
            while (rsPrivilegios.next()){
                String tipo_de_privilegio = rsPrivilegios.getString(2);
                listaDePrivilegios.add(tipo_de_privilegio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conexionSQL = null;
            conectar.desconectar();
        }
        return  listaDePrivilegios;
    }

    public boolean validarDatos(){
        boolean datosValidos = true;

        if(registrar_usuario_txtfl_nombres.getText().isEmpty()){
            datosValidos = false;
        }else{
            nombres = registrar_usuario_txtfl_nombres.getText();
        }

        if(registrar_usuario_txtfl_apellidos.getText().isEmpty()){
            datosValidos = false;
        }else{
            apellidos = registrar_usuario_txtfl_apellidos.getText();
        }

        if(registrar_usuario_txtfl_password.getText().isEmpty()){
            datosValidos = false;
        }else{
            password = registrar_usuario_txtfl_password.getText();
        }

        if(registrar_usuario_txtfl_area.getText().isEmpty()){
            datosValidos = false;
        }else {
            area = registrar_usuario_txtfl_area.getText();
        }

        if(registrar_usuario_txtfl_email.getText().isEmpty()){
            datosValidos = false;
        }else{
            email = registrar_usuario_txtfl_email.getText();
        }

        if(registrar_usuario_cmbox_privilegios.getValue() == null){
            datosValidos = false;
        }else {
            privilegios = registrar_usuario_cmbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
        }


        return datosValidos;
    }
}
