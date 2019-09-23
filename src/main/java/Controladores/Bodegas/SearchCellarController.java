package Controladores.Bodegas;

import Clases.Cruds.CellarCrud;
import Clases.Modelos.Cellar;
import Clases.Modelos.Configuration;
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

public class SearchCellarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuration.readConfiguration();
    }

    public void cancel() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
        panel_menu_bodegas = fxmlLoader.load();
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_bodegas);
    }

    public void getCellars(){
        buscar_bodegas_tabla_bodegas.setItems(cellarCrud.read(dataInput()));
        bodega_clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        bodega_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        bodega_clm_condicion.setCellValueFactory(new PropertyValueFactory<>("condition"));
        bodega_clm_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        bodega_clm_tramo.setCellValueFactory(new PropertyValueFactory<>("section"));
    }

    public Cellar dataInput(){
        int id;
        if (bodega_buscar_numero.getText().isEmpty()){
            id = 0;
        }else {
            id= Integer.parseInt(bodega_buscar_numero.getText());
        }
        Cellar cellar = new Cellar(id, bodega_buscar_nombre.getText(), bodega_buscar_region.getText());
        return cellar;
    }


    @FXML
    AnchorPane panel_buscar_bodegas;
    AnchorPane panel_menu_bodegas;
    @FXML
    TextField bodega_buscar_numero;
    @FXML
    TextField bodega_buscar_nombre;
    @FXML
    TextField bodega_buscar_region;
    @FXML
    TableView<Cellar> buscar_bodegas_tabla_bodegas;
    @FXML
    TableColumn<Cellar, Integer> bodega_clm_id;
    @FXML
    TableColumn<Cellar, String> bodega_clm_nombre;
    @FXML
    TableColumn<Cellar, String> bodega_clm_condicion;
    @FXML
    TableColumn<Cellar, String> bodega_clm_region;
    @FXML
    TableColumn<Cellar, String> bodega_clm_tramo;

    FXMLLoader fxmlLoader;
    CellarCrud cellarCrud = new CellarCrud();
    Configuration configuration = new Configuration();


}
