package Controladores.Bodegas;

import Clases.Cruds.Bodegas_Crud;
import Clases.Modelos.Bodega;
import Clases.Modelos.Configuracion;
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

public class BuscarBodega_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuracion.leerConfiguracion();
    }

    public void cancelar() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
        panel_menu_bodegas = fxmlLoader.load();
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_bodegas);
    }

    public void buscarBodegas(){
        buscar_bodegas_tabla_bodegas.setItems(bodegasCrud.buscarBodega(datosIntroducidos()));
        bodega_clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        bodega_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        bodega_clm_condicion.setCellValueFactory(new PropertyValueFactory<>("condicion"));
        bodega_clm_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        bodega_clm_tramo.setCellValueFactory(new PropertyValueFactory<>("tramo"));
    }

    public String datosIntroducidos(){
        String sqlQuery = "SELECT id, nombre, condicion, region, tramo FROM bodega WHERE";
        if(!bodega_buscar_numero.getText().isEmpty()){
            sqlQuery = sqlQuery + " id LIKE '%"+ bodega_buscar_numero.getText()+"%' AND ";
        }
        if (!bodega_buscar_nombre.getText().isEmpty()){
            sqlQuery = sqlQuery + " nombre LIKE '%"+bodega_buscar_nombre.getText()+"%' AND ";
        }
        if (!bodega_buscar_region.getText().isEmpty()){
            sqlQuery = sqlQuery + " region LIKE '%"+bodega_buscar_region.getText()+"%' AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String limpiarDatos = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlQuery = limpiarDatos;
        return sqlQuery;
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
    TableView<Bodega> buscar_bodegas_tabla_bodegas;
    @FXML
    TableColumn<Bodega, Integer> bodega_clm_id;
    @FXML
    TableColumn<Bodega, String> bodega_clm_nombre;
    @FXML
    TableColumn<Bodega, String> bodega_clm_condicion;
    @FXML
    TableColumn<Bodega, String> bodega_clm_region;
    @FXML
    TableColumn<Bodega, String> bodega_clm_tramo;

    FXMLLoader fxmlLoader;
    Bodegas_Crud bodegasCrud = new Bodegas_Crud();
    Configuracion configuracion = new Configuracion();


}
