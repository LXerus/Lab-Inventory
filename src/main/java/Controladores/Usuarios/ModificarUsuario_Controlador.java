package Controladores.Usuarios;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Cruds.UserCrud;
import Clases.Modelos.User;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificarUsuario_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modificar_usuario_cmbox_privilegios.setItems(obtenerListaDePrivilegios());
    }

    public void buscarUsuario(){
        modificar_usuario_tabla_lista_usuarios.setItems(usuariosCrud.read(datosIntroducidos()));
        modificar_usuario_cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        modificar_usuario_cl_nombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        modificar_usuario_cl_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        modificar_usuario_cl_fdi.setCellValueFactory(new PropertyValueFactory<>("fecha_de_ingreso"));
        modificar_usuario_cl_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        modificar_usuario_cl_activo.setCellValueFactory(new PropertyValueFactory<>("estado"));
        modificar_usuario_cl_email.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        modificar_usuario_cl_privilegios.setCellValueFactory(new PropertyValueFactory<>("tipoPrivilegios"));
        modificar_usuario_tabla_lista_usuarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Usuario/datos_usuario_gui.fxml"));
                try{
                    fxmlLoader.load();
                    DatosUsuario_Controlador datosUsuario = fxmlLoader.getController();
                    datosUsuario.datosUsuario(modificar_usuario_tabla_lista_usuarios.getSelectionModel().getSelectedItem());
                    Parent parent = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.show();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public User datosIntroducidos(){
        String nombre = modificar_usuario_txtfl_nombres.getText();
        String apellidos = modificar_usuario_txtfl_apellidos.getText();
        String email = modificar_usuario_txtfl_email.getText();
        String area = modificar_usuario_txtfl_area.getText();
        int privilegio = modificar_usuario_cmbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
        return new User(nombre, apellidos,area, email, privilegio);
    }

    public ObservableList<String> obtenerListaDePrivilegios(){
        javafx.collections.ObservableList<java.lang.String> listaDePrivilegios = FXCollections.observableArrayList();
        java.lang.String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        JDBConnection JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        Connection conexionSQL = JDBConnection.getConnection();
        try{
            Statement privilegiosStatement = conexionSQL.createStatement();
            ResultSet rsPrivilegios = privilegiosStatement.executeQuery(consultaSQL);
            while (rsPrivilegios.next()){
                java.lang.String tipo_de_privilegio = rsPrivilegios.getString(2);
                listaDePrivilegios.add(tipo_de_privilegio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(conexionSQL != null){
                    conexionSQL.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            JDBConnection.disconnect();
        }
        return  listaDePrivilegios;
    }

    @FXML private TextField modificar_usuario_txtfl_nombres;
    @FXML private TextField modificar_usuario_txtfl_apellidos;
    @FXML private TextField modificar_usuario_txtfl_email;
    @FXML private TextField modificar_usuario_txtfl_area;
    @FXML private ComboBox modificar_usuario_cmbox_privilegios;
    @FXML private Button modificar_usuario_btn_cancelar;
    @FXML private TableView<User> modificar_usuario_tabla_lista_usuarios;
    @FXML private TableColumn<User, Integer> modificar_usuario_cl_id;
    @FXML private TableColumn<User, String> modificar_usuario_cl_nombres;
    @FXML private TableColumn<User, String> modificar_usuario_cl_apellidos;
    @FXML private TableColumn<User, LocalDate> modificar_usuario_cl_fdi;
    @FXML private TableColumn<User, String> modificar_usuario_cl_area;
    @FXML private TableColumn<User, String> modificar_usuario_cl_activo;
    @FXML private TableColumn<User, String> modificar_usuario_cl_email;
    @FXML private TableColumn<User, String> modificar_usuario_cl_privilegios;
    private UserCrud usuariosCrud = new UserCrud();
}
