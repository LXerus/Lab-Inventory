package Controladores.Proveedores;

import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuProveedores_Controlador implements Initializable {
   @FXML
   AnchorPane panel_menu_proveedores;

   private FXMLLoader fxmlLoader;

   private AnchorPane panelSeleccionado;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void irRegistrarProveedor(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_proveedor_gui.fxml"));
            panelSeleccionado = fxmlLoader.load();
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panelSeleccionado);
    }

    public void irBuscarProveedor(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_proveedor_gui.fxml"));
            panelSeleccionado = fxmlLoader.load();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panelSeleccionado);
    }

    public void irModificarProveedor(){
        try{
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Proveedor/modificar_proveedor_gui.fxml"));
            panelSeleccionado = fxmlLoader.load();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panelSeleccionado);
    }
}
