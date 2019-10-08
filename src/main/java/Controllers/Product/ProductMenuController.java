package Controllers.Product;

import Controllers.MainMenu.MainMenuController;
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
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Register/register_product_gui.fxml"));
            selectedPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(selectedPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void getSearchProductGUI(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Search/search_product_gui.fxml"));
            selectedPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(selectedPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
