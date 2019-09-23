package Controladores.Proveedores;

import Clases.Cruds.ProviderCrud;
import Clases.Modelos.Provider;
import Controladores.MenuPrincipal.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchProviderController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getProvidersMenu(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
            providerMenuPane = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(providerMenuPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchProvider(){
        buscar_proveedor_table_proveedores.setItems(providerCrud.read(inputData()));
        buscar_proveedor_clm_id.setCellValueFactory(new PropertyValueFactory<>("providerCode"));
        buscar_proveedor_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        buscar_proveedor_clm_tellefono.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        buscar_proveedor_clm_contacto.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public Provider inputData(){
        String name =  buscar_proveedor_txtfl_nombre.getText();
        String telephone = buscar_proveedor_txtfl_telefono.getText();
        String contact = buscar_proveedor_txtfl_contacto.getText();
        String code = buscar_proveedor_txtfl_id.getText();
        return new Provider(name, telephone, contact, code);
    }

    private FXMLLoader fxmlLoader;
    private ProviderCrud providerCrud = new ProviderCrud();
    private AnchorPane providerMenuPane;

    @FXML
    AnchorPane panel_buscar_proveedor;

    @FXML private TextField buscar_proveedor_txtfl_nombre;
    @FXML private TextField buscar_proveedor_txtfl_id;
    @FXML private TextField buscar_proveedor_txtfl_telefono;
    @FXML private TextField buscar_proveedor_txtfl_contacto;

    @FXML private TableView<Provider> buscar_proveedor_table_proveedores;
    @FXML private TableColumn<Provider, Integer> buscar_proveedor_clm_id;
    @FXML private TableColumn<Provider, String> buscar_proveedor_clm_nombre;
    @FXML private TableColumn<Provider, String> buscar_proveedor_clm_tellefono;
    @FXML private TableColumn<Provider, String> buscar_proveedor_clm_contacto;

}
