package Controladores.Productos;

import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuProductos_Controlador implements Initializable {
    private AnchorPane panel_seleccionado;
    private FXMLLoader fxmlLoader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void irRegistrarProducto(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_producto_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void irBuscarProductos(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_producto_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
