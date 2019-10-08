package Controllers.Cellar;

import Clases.Cruds.CellarCrud;
import Clases.Models.Cellar;
import Clases.Models.Configuration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CellarDataController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuration.readConfiguration();
    }

    public void cellarData(Cellar cellar){
        //obteniendo los datos de la bodega hacia la nueva ventana
        id = cellar.getId();
        name = cellar.getName();
        condition = cellar.getCondition();
        section = cellar.getSection();
        region = cellar.getRegion();

        datos_bodega_txtfl_id.setText(Integer.toString(id));
        datos_bodega_txtfl_nombre.setText(name);
        datos_bodega_txtfl_condicion.setText(condition);
        datos_bodega_txtfl_tramo.setText(section);
        datos_bodega_txtfl_region.setText(region);
    }

    public void updateData(){
        if(verifyData()){
            String name =  datos_bodega_txtfl_nombre.getText();
            String condition = datos_bodega_txtfl_condicion.getText();
            String section = datos_bodega_txtfl_tramo.getText();
            String region = datos_bodega_txtfl_region.getText();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Actualizar Cellar");
            confirmation.setHeaderText("Cambiando datos de la bodega.");
            confirmation.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmation.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                cellarCrud.update(new Cellar(id, name, condition, section, region));
            }else{
                JOptionPane.showMessageDialog(null,"No se han efectuado cambios.");
            }
        }
    }

    public void cancel(){
        Stage stage = (Stage) datos_bodega_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verifyData(){
        //Se asegura que todos los datos necesarios sean ingresados.
        boolean valid = true;
        if(datos_bodega_txtfl_id.getText().isEmpty()){
            valid = false;
        }else {
            valid = true;
        }

        if(datos_bodega_txtfl_nombre.getText().isEmpty()){
            valid = false;
        }else {
            valid = true;
        }

        if(datos_bodega_txtfl_condicion.getText().isEmpty()){
            valid = false;
        }else {
            valid = true;
        }

        if(datos_bodega_txtfl_tramo.getText().isEmpty()){
            valid = false;
        }else {
            valid = true;
        }

        if(datos_bodega_txtfl_region.getText().isEmpty()){
            valid = false;
        }else {
            valid = true;
        }
        return  valid;
    }

    @FXML private TextField datos_bodega_txtfl_id;
    @FXML private TextField datos_bodega_txtfl_nombre;
    @FXML private TextField datos_bodega_txtfl_condicion;
    @FXML private TextField datos_bodega_txtfl_tramo;
    @FXML private TextField datos_bodega_txtfl_region;
    @FXML private Button datos_bodega_btn_cancelar;

    private int id = 0;
    private String name;
    private String condition;
    private String section;
    private String region;
    private CellarCrud cellarCrud = new CellarCrud();
    private Configuration configuration = new Configuration();
}
