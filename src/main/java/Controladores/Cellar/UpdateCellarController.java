package Controladores.Cellar;

import Clases.Cruds.CellarCrud;
import Clases.Modelos.Cellar;
import Controladores.MenuPrincipal.MainMenuController;
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

public class UpdateCellarController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void cancel(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
            panel_menu_bodegas = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(panel_menu_bodegas);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getCellars(){
        modificar_bodegas_tabla_bodegas.setItems(cellarCrud.read(dataInput()));
        modificar_bodega_clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        modificiar_bodega_clm_nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        modificar_bodega_clm_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        modificar_bodega_clm_condicion.setCellValueFactory(new PropertyValueFactory<>("condition"));
        modificar_bodega_clm_tramo.setCellValueFactory(new PropertyValueFactory<>("section"));
        modificar_bodegas_tabla_bodegas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Bodega/datos_bodega_gui.fxml"));
                try{
                    fxmlLoader.load();
                    CellarDataController cellarDataController = fxmlLoader.getController();
                    cellarDataController.cellarData(modificar_bodegas_tabla_bodegas.getSelectionModel().getSelectedItem());
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

    public Cellar dataInput(){
        int id;
        if(modificar_bodega_txtf_numero.getText().isEmpty()){
            id = 0;
        }else{
            id =  Integer.parseInt(modificar_bodega_txtf_numero.getText());
        }
        Cellar cellar = new Cellar( id, modificar_bodega_txtf_nombre.getText(), modificar_bodega_txtf_region.getText());
        return cellar;
    }

    private CellarCrud cellarCrud = new CellarCrud();
    private FXMLLoader fxmlLoader;
    private AnchorPane panel_menu_bodegas;

    @FXML private TextField modificar_bodega_txtf_numero;
    @FXML private TextField modificar_bodega_txtf_nombre;
    @FXML private TextField modificar_bodega_txtf_region;

    @FXML private TableView<Cellar> modificar_bodegas_tabla_bodegas;
    @FXML private TableColumn<Cellar, Integer> modificar_bodega_clm_id;
    @FXML private TableColumn<Cellar, String> modificiar_bodega_clm_nombre;
    @FXML private TableColumn<Cellar, String>  modificar_bodega_clm_region;
    @FXML private TableColumn<Cellar, String>  modificar_bodega_clm_condicion;
    @FXML private TableColumn<Cellar, String>  modificar_bodega_clm_tramo;
}
