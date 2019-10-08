package Controllers.User;

import Controllers.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuCotroller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getCreateUser(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Register/register_user_gui.fxml"));
            selectedPanel = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(selectedPanel);
    }

    public void getSearchUser(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Search/search_user_gui.fxml"));
            selectedPanel = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(selectedPanel);
    }

    public void getUpdateUser(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/User/update_user_gui.fxml"));
            selectedPanel = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(selectedPanel);
    }

    @FXML
    private AnchorPane panel_menu_usuarios;
    @FXML
    private Button menu_usuarios_btn_insertar;
    @FXML
    private Button menu_usuarios_btn_buscar;
    @FXML
    private Button menu_usuarios_btn_desactivar;
    @FXML
    private Button menu_usuarios_btn_activar;

    private FXMLLoader fxmlLoader;
    private AnchorPane selectedPanel;
}
