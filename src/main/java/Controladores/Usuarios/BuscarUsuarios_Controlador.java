package Controladores.Usuarios;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Cruds.UserCrud;
import Clases.Modelos.User;
import Clases.Modelos.CurrentUser;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BuscarUsuarios_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buscar_usuario_cmbox_privilegios.setItems(obtenerListaDePrivilegios());
    }

    public void regresarMenuUsuarios(){
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_usuarios_gui.fxml"));
        try {
            panel_menu_usuarios = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buscarUsuario(){
        buscar_usuario_tabla_lista_usuarios.setItems(usuariosCrud.read(datosIntroducidos()));
        buscar_usuario_cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        buscar_usuario_cl_nombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        buscar_usuario_cl_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        buscar_usuario_cl_fdi.setCellValueFactory(new PropertyValueFactory<>("fecha_de_ingreso"));
        buscar_usuario_cl_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        buscar_usuario_cl_activo.setCellValueFactory(new PropertyValueFactory<>("estado"));
        buscar_usuario_cl_email.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        buscar_usuario_cl_privilegios.setCellValueFactory(new PropertyValueFactory<>("tipoPrivilegios"));
    }

    public ObservableList<String> obtenerListaDePrivilegios(){
        ObservableList<String> listaDePrivilegios = FXCollections.observableArrayList();
        String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        JDBConnection JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        Connection conexionSQL = JDBConnection.getConnection();
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
            JDBConnection.disconnect();
        }
        return  listaDePrivilegios;
    }

    public User datosIntroducidos(){
        String nombre = buscar_usuario_txtfl_nombres.getText();
        String apellidos = buscar_usuario_txtfl_apellidos.getText();
        String email = buscar_usuario_txtfl_email.getText();
        String area = buscar_usuario_txtfl_area.getText();
        int privilegio = buscar_usuario_cmbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
        return new User(nombre, apellidos, area, email, privilegio);
    }

    @FXML
    private TextField buscar_usuario_txtfl_nombres;
    @FXML
    private TextField buscar_usuario_txtfl_apellidos;
    @FXML
    private TextField buscar_usuario_txtfl_email;
    @FXML
    private TextField buscar_usuario_txtfl_area;
    @FXML
    private ComboBox buscar_usuario_cmbox_privilegios;
    @FXML
    private AnchorPane panel_buscar_usuarios;

    @FXML
    private TableView<User> buscar_usuario_tabla_lista_usuarios;
    @FXML
    private TableColumn<User, Integer> buscar_usuario_cl_id;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_nombres;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_apellidos;
    @FXML
    private TableColumn<User, LocalDate> buscar_usuario_cl_fdi;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_area;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_activo;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_email;
    @FXML
    private TableColumn<User, String> buscar_usuario_cl_privilegios;

    private FXMLLoader fxmlLoader;
    private AnchorPane panel_menu_usuarios;
    private UserCrud usuariosCrud = new UserCrud();

}
