package Controladores.Proveedores;

import Controladores.MenuPrincipal.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProviderMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getCreateProvider(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_proveedor_gui.fxml"));
            selectedPane = fxmlLoader.load();
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        MainMenuController.getParentPane().setCenter(selectedPane);
    }

    public void getSearchProvider(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_proveedor_gui.fxml"));
            selectedPane = fxmlLoader.load();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        MainMenuController.getParentPane().setCenter(selectedPane);
    }

    public void getUpdateProvider(){
        try{
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Proveedor/modificar_proveedor_gui.fxml"));
            selectedPane = fxmlLoader.load();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(selectedPane);
    }

    @FXML
    AnchorPane panel_menu_proveedores;
    private FXMLLoader fxmlLoader;
    private AnchorPane selectedPane;
}
