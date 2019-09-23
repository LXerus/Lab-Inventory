package Controladores.Productos;

import Controladores.MenuPrincipal.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenuController implements Initializable {
    private AnchorPane selectedPane;
    private FXMLLoader fxmlLoader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getCreateProductGUI(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Registro/registrar_producto_gui.fxml"));
            selectedPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(selectedPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void getSearchProductGUI(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Busquda/buscar_producto_gui.fxml"));
            selectedPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(selectedPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
