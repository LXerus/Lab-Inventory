package Controladores.Proveedores;

import Clases.Cruds.ProviderCrud;
import Clases.Modelos.Provider;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificarProveedor_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void buscarProveedor(){
        modificar_proveedor_table_proveedores.setItems(proveedoresCrud.read(datosIntroducidos()));
        modificar_proveedor_clm_id.setCellValueFactory(new PropertyValueFactory<>("codigoDeProveedor"));
        modificar_proveedor_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        modificar_proveedor_clm_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        modificar_proveedor_clm_contacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));

        modificar_proveedor_table_proveedores.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Proveedor/datos_proveedor_gui.fxml"));
                try{
                    fxmlLoader.load();
                    DatosProveedor_Controlador proveedorDatosControlador = fxmlLoader.getController();
                    proveedorDatosControlador.datosProveedor(modificar_proveedor_table_proveedores.getSelectionModel().getSelectedItem());
                    Parent parent = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.show();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }

        });
    }

    public Provider datosIntroducidos(){
        String nombre = modificar_proveedor_txtfl_nombre.getText();
        String telefono = modificar_proveedor_txtfl_telefono.getText();
        String contacto = modificar_proveedor_txtfl_contacto.getText();
        String codigo = modificar_proveedor_txtfl_id.getText();
        return new Provider(nombre, telefono, contacto, codigo);
    }

    public void cancelar(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
            AnchorPane panelMenuProveedores = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panelMenuProveedores);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML private TextField modificar_proveedor_txtfl_nombre;
    @FXML private TextField modificar_proveedor_txtfl_telefono;
    @FXML private TextField modificar_proveedor_txtfl_id;
    @FXML private TextField modificar_proveedor_txtfl_contacto;
    @FXML private TableView<Provider> modificar_proveedor_table_proveedores;
    @FXML private TableColumn<Provider, Integer> modificar_proveedor_clm_id;
    @FXML private TableColumn<Provider, String> modificar_proveedor_clm_nombre;
    @FXML private TableColumn<Provider, String> modificar_proveedor_clm_telefono;
    @FXML private TableColumn<Provider, String> modificar_proveedor_clm_contacto;
    private ProviderCrud proveedoresCrud = new ProviderCrud();


}
