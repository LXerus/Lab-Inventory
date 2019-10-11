package Controllers.User;

import Clases.BaseDeDatos.connection;
import Clases.Cruds.UserCrud;
import Clases.Models.User;
import Clases.Models.CurrentUser;
import Controllers.MainMenu.MainMenuController;
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

public class SearchUserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buscar_usuario_cmbox_privilegios.setItems(getPrivilegesList());
    }

    public void getUserMenu(){
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/user_menu_gui.fxml"));
        try {
            userMenuPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(userMenuPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUser(){
        buscar_usuario_tabla_lista_usuarios.setItems(userCrud.read(inputData()));
        buscar_usuario_cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        buscar_usuario_cl_nombres.setCellValueFactory(new PropertyValueFactory<>("name"));
        buscar_usuario_cl_apellidos.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        buscar_usuario_cl_fdi.setCellValueFactory(new PropertyValueFactory<>("entryDae"));
        buscar_usuario_cl_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        buscar_usuario_cl_activo.setCellValueFactory(new PropertyValueFactory<>("status"));
        buscar_usuario_cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        buscar_usuario_cl_privilegios.setCellValueFactory(new PropertyValueFactory<>("privilegesType"));
    }

    public ObservableList<String> getPrivilegesList(){
        ObservableList<String> privilegesList = FXCollections.observableArrayList();
        String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        connection JDBConnection = new connection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        Connection connection = JDBConnection.getConnection();
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
            JDBConnection.disconnect();
        }
        return  privilegesList;
    }

    public User inputData(){
        String name = buscar_usuario_txtfl_nombres.getText();
        String lastName = buscar_usuario_txtfl_apellidos.getText();
        String email = buscar_usuario_txtfl_email.getText();
        String area = buscar_usuario_txtfl_area.getText();
        int privileges = buscar_usuario_cmbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
        return new User(name, lastName, area, email, privileges);
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
    private AnchorPane userMenuPane;
    private UserCrud userCrud = new UserCrud();

}
