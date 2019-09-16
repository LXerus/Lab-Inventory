package Controladores.Bodegas;

import Clases.Cruds.Bodegas_Crud;
import Clases.Modelos.Bodega;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificarBodega_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void cancelar(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
            panel_menu_bodegas = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_bodegas);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void buscarBodega(){
        modificar_bodegas_tabla_bodegas.setItems(bodegasCrud.buscarBodega(datosIntroducidos()));
        modificar_bodega_clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        modificiar_bodega_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        modificar_bodega_clm_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        modificar_bodega_clm_condicion.setCellValueFactory(new PropertyValueFactory<>("condicion"));
        modificar_bodega_clm_tramo.setCellValueFactory(new PropertyValueFactory<>("tramo"));
        modificar_bodegas_tabla_bodegas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Bodega/datos_bodega_gui.fxml"));
                try{
                    fxmlLoader.load();
                    DatosBodega_Controlador bodegaDatosControlador = fxmlLoader.getController();
                    bodegaDatosControlador.datosDeLaBodega(modificar_bodegas_tabla_bodegas.getSelectionModel().getSelectedItem());
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

    public String datosIntroducidos(){
        String sqlQuery = "SELECT id, nombre, condicion, region, tramo FROM bodega WHERE";
        if(!modificar_bodega_txtf_numero.getText().isEmpty()){
            sqlQuery = sqlQuery + " id LIKE '%"+modificar_bodega_txtf_numero.getText()+"%' AND ";
        }
        if (!modificar_bodega_txtf_nombre.getText().isEmpty()){
            sqlQuery = sqlQuery + " nombre LIKE '%"+modificar_bodega_txtf_nombre.getText()+"%' AND ";
        }
        if (!modificar_bodega_txtf_region.getText().isEmpty()){
            sqlQuery = sqlQuery + " region LIKE '%"+modificar_bodega_txtf_region.getText()+"%' AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String limpiarDatos = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlQuery = limpiarDatos;
        return sqlQuery;
    }

    private Bodegas_Crud bodegasCrud = new Bodegas_Crud();
    private FXMLLoader fxmlLoader;
    private AnchorPane panel_menu_bodegas;

    @FXML private TextField modificar_bodega_txtf_numero;
    @FXML private TextField modificar_bodega_txtf_nombre;
    @FXML private TextField modificar_bodega_txtf_region;

    @FXML private TableView<Bodega> modificar_bodegas_tabla_bodegas;
    @FXML private TableColumn<Bodega, Integer> modificar_bodega_clm_id;
    @FXML private TableColumn<Bodega, String> modificiar_bodega_clm_nombre;
    @FXML private TableColumn<Bodega, String>  modificar_bodega_clm_region;
    @FXML private TableColumn<Bodega, String>  modificar_bodega_clm_condicion;
    @FXML private TableColumn<Bodega, String>  modificar_bodega_clm_tramo;
}
