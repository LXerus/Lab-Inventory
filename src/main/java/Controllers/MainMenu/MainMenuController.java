package Controllers.MainMenu;

import Clases.Cruds.UserCrud;
import Clases.Models.Configuration;
import Clases.Models.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuration.readConfiguration();
        menu_principal_label_usuario.setText(CurrentUser.getCurrentUser().getName());
        menu_principal_label_id.setText(String.valueOf(CurrentUser.getCurrentUser().getId()));
        menu_principal_label_area.setText(CurrentUser.getCurrentUser().getArea());
        menu_principal_label_email.setText(CurrentUser.getCurrentUser().getEmail());
        menu_principal_label_privilegios.setText(userCrud.getUserPrivileges(CurrentUser.getCurrentUser()));
    }


    public void close(){
        Stage stage = (Stage) menu_principal_btn_salir.getScene().getWindow();
        stage.close();
    }

    public static BorderPane getParentPane(){
        return parentPane;
    }

    public Stage createMainMenu(Stage stage){
        configuration.readConfiguration();
        try {
            parentPane = FXMLLoader.load(getClass().getResource("/gui/Menus/main_menu_gui.fxml"));
            stage.setTitle("Inventario");
            stage.setScene(new Scene(parentPane, 1075, 650));
            stage.centerOnScreen();
            stage.show();
        }catch (IOException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }return stage;
    }

    public void getProductMenu() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/product_menu_gui.fxml"));
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
          /*  menu_principal_btn_productos.setDisable(true);
            menu_principal_btn_bodegas.setDisable(false);
            menu_principal_btn_proveedores.setDisable(false);
            menu_principal_btn_usuarios.setDisable(false);
            menu_principal_btn_log.setDisable(false);
            menu_principal_btn_configuracion.setDisable(false);*/
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getCellarMenu() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/cellar_menu_gui.fxml"));
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
         /*   menu_principal_btn_productos.setDisable(false);
            menu_principal_btn_bodegas.setDisable(true);
            menu_principal_btn_proveedores.setDisable(false);
            menu_principal_btn_usuarios.setDisable(false);
            menu_principal_btn_log.setDisable(false);
            menu_principal_btn_configuracion.setDisable(false);*/
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getProviderMenu() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/provider_menu_gui.fxml"));
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
        /*    menu_principal_btn_productos.setDisable(false);
            menu_principal_btn_bodegas.setDisable(false);
            menu_principal_btn_proveedores.setDisable(true);
            menu_principal_btn_usuarios.setDisable(false);
            menu_principal_btn_log.setDisable(false);
            menu_principal_btn_configuracion.setDisable(false);*/
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }


    public void getUserMenu() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/user_menu_gui.fxml"));
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
         /*   menu_principal_btn_productos.setDisable(false);
            menu_principal_btn_bodegas.setDisable(false);
            menu_principal_btn_proveedores.setDisable(false);
            menu_principal_btn_usuarios.setDisable(true);
            menu_principal_btn_log.setDisable(false);
            menu_principal_btn_configuracion.setDisable(false);*/
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getActivityLog(){
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Search/search_activity_log_gui.fxml"));
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
      /*  menu_principal_btn_productos.setDisable(false);
        menu_principal_btn_bodegas.setDisable(false);
        menu_principal_btn_proveedores.setDisable(false);
        menu_principal_btn_usuarios.setDisable(false);
        menu_principal_btn_log.setDisable(true);
        menu_principal_btn_configuracion.setDisable(false);*/
    }

    public void getConfigurationGUI(){
        configuration.readConfiguration();
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/configuration_gui.fxml"));
        try {
            selectedPane = fxmlLoader.load();
            parentPane.setCenter(selectedPane);
        } catch (IOException ex) {
           ex.printStackTrace();
        }
       /* menu_principal_btn_productos.setDisable(false);
        menu_principal_btn_bodegas.setDisable(false);
        menu_principal_btn_proveedores.setDisable(false);
        menu_principal_btn_usuarios.setDisable(false);
        menu_principal_btn_log.setDisable(false);
        menu_principal_btn_configuracion.setDisable(true);*/
    }


    @FXML
    private Button menu_principal_btn_salir;
    @FXML
    private Label menu_principal_label_usuario;
    @FXML
    private Label menu_principal_label_id;
    @FXML
    private Label menu_principal_label_area;
    @FXML
    private Label menu_principal_label_email;
    @FXML
    private Label menu_principal_label_privilegios;

    private FXMLLoader fxmlLoader;
    private AnchorPane selectedPane;
    private static BorderPane parentPane;
    private UserCrud userCrud = new UserCrud();
    private Configuration configuration = new Configuration();




}
