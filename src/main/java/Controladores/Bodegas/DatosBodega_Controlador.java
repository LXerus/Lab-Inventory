package Controladores.Bodegas;

import Clases.Cruds.Bodegas_Crud;
import Clases.Modelos.Bodega;
import Clases.Modelos.Configuracion;
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

public class DatosBodega_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuracion.leerConfiguracion();
    }

    public void datosDeLaBodega(Bodega bodega){
        //obteniendo los datos de la bodega hacia la nueva ventana
        id = bodega.getId();
        nombre = bodega.getNombre();
        condicion = bodega.getCondicion();
        tramo = bodega.getTramo();
        region = bodega.getRegion();

        datos_bodega_txtfl_id.setText(Integer.toString(id));
        datos_bodega_txtfl_nombre.setText(nombre);
        datos_bodega_txtfl_condicion.setText(condicion);
        datos_bodega_txtfl_tramo.setText(tramo);
        datos_bodega_txtfl_region.setText(region);
    }

    public void guardarCambios(){
        if(validarDatos()){
            String nuevo_nombre =  datos_bodega_txtfl_nombre.getText();
            String nuevo_condicion = datos_bodega_txtfl_condicion.getText();
            String nuevo_tramo = datos_bodega_txtfl_tramo.getText();
            String nuevo_region = datos_bodega_txtfl_region.getText();

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Actualizar Bodega");
            confirmacion.setHeaderText("Cambiando datos de la bodega.");
            confirmacion.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                bodegasCrud.actualizarDatos(new Bodega(id, nuevo_nombre, nuevo_condicion, nuevo_tramo, nuevo_region));
            }else{
                JOptionPane.showMessageDialog(null,"No se han efectuado cambios.");
            }
        }
    }

    public void cancelar(){
        Stage stage = (Stage) datos_bodega_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarDatos(){
        //Se asegura que todos los datos necesarios sean ingresados.
        boolean datosValidos = true;
        if(datos_bodega_txtfl_id.getText().isEmpty()){
            datosValidos = false;
        }else {
            datosValidos = true;
        }

        if(datos_bodega_txtfl_nombre.getText().isEmpty()){
            datosValidos = false;
        }else {
            datosValidos = true;
        }

        if(datos_bodega_txtfl_condicion.getText().isEmpty()){
            datosValidos = false;
        }else {
            datosValidos = true;
        }

        if(datos_bodega_txtfl_tramo.getText().isEmpty()){
            datosValidos = false;
        }else {
            datosValidos = true;
        }

        if(datos_bodega_txtfl_region.getText().isEmpty()){
            datosValidos = false;
        }else {
            datosValidos = true;
        }
        return  datosValidos;
    }

    @FXML private TextField datos_bodega_txtfl_id;
    @FXML private TextField datos_bodega_txtfl_nombre;
    @FXML private TextField datos_bodega_txtfl_condicion;
    @FXML private TextField datos_bodega_txtfl_tramo;
    @FXML private TextField datos_bodega_txtfl_region;
    @FXML private Button datos_bodega_btn_cancelar;

    private int id = 0;
    private String nombre;
    private String condicion;
    private String tramo;
    private String region;
    private Bodegas_Crud bodegasCrud = new Bodegas_Crud();
    private Configuracion configuracion = new Configuracion();
}
