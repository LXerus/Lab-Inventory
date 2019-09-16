package Controladores.Usuarios;

import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuUsuarios_Controlador implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private AnchorPane panel_seleccionado;

    public void irRegistrarUsuario(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_usuario_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
    }

    public void irBuscarUsuario(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_usuarios_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
    }

    public void irModificarUsuario(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Usuario/modificar_usuario_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
    }
}
