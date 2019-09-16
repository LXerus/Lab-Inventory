package Controladores.Bodegas;

import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBodegas_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void irBuscarBodega()  {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_bodega_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
  }

  public void irRegistrarBodega() {
       try {
           fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_bodega_gui.fxml"));
           panel_seleccionado = fxmlLoader.load();
           MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
       }catch (IOException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
  }

  public void irModificarBodega(){
        try{
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Bodega/modificar_bodegas_gui.fxml"));
            panel_seleccionado = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
  }


    @FXML
    private AnchorPane panel_menu_bodegas;
    private FXMLLoader fxmlLoader;
    private AnchorPane panel_seleccionado;
}
