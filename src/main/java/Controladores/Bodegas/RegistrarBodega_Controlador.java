package Controladores.Bodegas;

import Clases.Cruds.CellarCrud;
import Clases.Modelos.Cellar;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarBodega_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //*********************************** Acciones de botones ***********************************
    public void regresarMenuBodega(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_bodegas_gui.fxml"));
            panel_menu_bodegas = fxmlLoader.load();
            MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_bodegas);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void registrarBodega() throws SQLException {

        if(validarDatos()){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar Bodega");
            confirmacion.setHeaderText("Registrando una nueva bodega");
            confirmacion.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                cellar = new Cellar(nombre, condicion, region, tramo);
                crudBodegas.create(cellar);
                JOptionPane.showMessageDialog(null,"¡La bodega se ha registrado exitosamente!");
                regresarMenuBodega();
            }else{
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
        }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
        }
    }

    public Boolean validarDatos(){
       //Asegurandonos de que todos los datos hayan sido ingresados
        Boolean datosValidos = true;

        if(registrar_bodega_txtfl_nombre.getText().isEmpty()){
            datosValidos = false;
        }else{
            nombre = this.registrar_bodega_txtfl_nombre.getText();
        }

        if(registrar_bodega_txtfl_condicion.getText().isEmpty()){
            datosValidos = false;
        }else{
            condicion = this.registrar_bodega_txtfl_condicion.getText();
        }

        if(registrar_bodega_txtfl_region.getText().isEmpty()){
            datosValidos = false;
        }else{
            region = this.registrar_bodega_txtfl_region.getText();
        }

        if(registrar_bodega_txtfl_tramo.getText().isEmpty()){
            datosValidos = false;
        }else{
            tramo = this.registrar_bodega_txtfl_tramo.getText();
        }

        return datosValidos;
    }

    //Datos
    private String nombre;
    private String condicion;
    private String region;
    private String tramo;
    Cellar cellar;
    CellarCrud crudBodegas = new CellarCrud();

    //Paneles
    FXMLLoader fxmlLoader;
    AnchorPane panel_menu_bodegas;
    @FXML
    AnchorPane panel_ingresar_bodega;

    //TextFields
    @FXML
    private TextField registrar_bodega_txtfl_nombre;
    @FXML
    private TextField registrar_bodega_txtfl_condicion;
    @FXML
    private TextField registrar_bodega_txtfl_region;
    @FXML
    private TextField registrar_bodega_txtfl_tramo;
}


