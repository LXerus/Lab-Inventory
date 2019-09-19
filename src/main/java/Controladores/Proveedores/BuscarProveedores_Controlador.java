package Controladores.Proveedores;

import Clases.Cruds.ProviderCrud;
import Clases.Modelos.Provider;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
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

public class BuscarProveedores_Controlador implements Initializable {
    private FXMLLoader fxmlLoader;
    private ProviderCrud proveedoresCrud = new ProviderCrud();
    private AnchorPane panel_menu_proveedor;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void irMenuProveedores(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
            panel_menu_proveedor = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_proveedor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buscarProveedor(){
        buscar_proveedor_table_proveedores.setItems(proveedoresCrud.read(datosIntroducidos()));
        buscar_proveedor_clm_id.setCellValueFactory(new PropertyValueFactory<>("codigoDeProveedor"));
        buscar_proveedor_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        buscar_proveedor_clm_tellefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        buscar_proveedor_clm_contacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
    }

    public Provider datosIntroducidos(){
        String nombre =  buscar_proveedor_txtfl_nombre.getText();
        String telefono = buscar_proveedor_txtfl_telefono.getText();
        String contacto = buscar_proveedor_txtfl_contacto.getText();
        String codigo = buscar_proveedor_txtfl_id.getText();
        return new Provider(nombre, telefono, contacto, codigo);
    }

}
