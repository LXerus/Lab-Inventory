package Controladores.MenuPrincipal;

import Clases.Cruds.UserCrud;
import Clases.Modelos.Configuration;
import Clases.Modelos.CurrentUser;
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

public class MenuPrincipal_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuration.readConfiguration();
        menu_principal_label_usuario.setText(CurrentUser.getCurrentUser().getName());
        menu_principal_label_id.setText(String.valueOf(CurrentUser.getCurrentUser().getId()));
        menu_principal_label_area.setText(CurrentUser.getCurrentUser().getArea());
        menu_principal_label_email.setText(CurrentUser.getCurrentUser().getEmail());
        menu_principal_label_privilegios.setText(usuariosCrud.getUserPrivileges(CurrentUser.getCurrentUser()));
    }


    public void salir(){
        Stage stage = (Stage) menu_principal_btn_salir.getScene().getWindow();
        stage.close();
    }

    public static BorderPane obntenerPanelPadre(){
        return panelPadre;
    }

    public Stage crearMenuPrincipal(Stage stage){
        configuration.readConfiguration();
        try {
            panelPadre = FXMLLoader.load(getClass().getResource("/gui/Menus/menu_principal_gui.fxml"));
            stage.setTitle("Inventario");
            stage.setScene(new Scene(panelPadre, 1075, 650));
            stage.centerOnScreen();
            stage.show();
        }catch (IOException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }return stage;
    }

    public void irMenuProductos() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_productos_gui.fxml"));
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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

    public void irMenuBodegas() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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

    public void irMenuProveedores() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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


    public void menuUsuarios() {
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_usuarios_gui.fxml"));
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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

    public void irLogActividades(){
        configuration.readConfiguration();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/log_de_actividades_gui.fxml"));
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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

    public void irConfiguracion(){
        configuration.readConfiguration();
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/ConfiguracionBD/configuracion_gui.fxml"));
        try {
            panel_menu_seleccionado = fxmlLoader.load();
            panelPadre.setCenter(panel_menu_seleccionado);
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
    private AnchorPane panel_menu_seleccionado;
    private static BorderPane panelPadre;
    private UserCrud usuariosCrud = new UserCrud();
    private Configuration configuration = new Configuration();




}
