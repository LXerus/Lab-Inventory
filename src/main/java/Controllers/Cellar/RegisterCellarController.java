package Controllers.Cellar;

import Clases.Cruds.CellarCrud;
import Clases.Models.Cellar;
import Controllers.MainMenu.MainMenuController;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterCellarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //*********************************** Acciones de botones ***********************************
    public void returnCellarMenu(){
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/cellar_menu_gui.fxml"));
            panel_menu_bodegas = fxmlLoader.load();
            MainMenuController.getParentPane().setCenter(panel_menu_bodegas);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void createCellar(){
        if(verifyData()){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Registrar Cellar");
            confirmation.setHeaderText("Registrando una nueva bodega");
            confirmation.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmation.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                cellar = new Cellar(name, condition, region, section);
                cellarCrud.create(cellar);
                JOptionPane.showMessageDialog(null,"¡La bodega se ha registrado exitosamente!");
                returnCellarMenu();
            }else{
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
        }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
        }
    }

    public Boolean verifyData(){
       //Asegurandonos de que todos los datos hayan sido ingresados
        Boolean valid = true;

        if(registrar_bodega_txtfl_nombre.getText().isEmpty()){
            valid = false;
        }else{
            name = this.registrar_bodega_txtfl_nombre.getText();
        }

        if(registrar_bodega_txtfl_condicion.getText().isEmpty()){
            valid = false;
        }else{
            condition = this.registrar_bodega_txtfl_condicion.getText();
        }

        if(registrar_bodega_txtfl_region.getText().isEmpty()){
            valid = false;
        }else{
            region = this.registrar_bodega_txtfl_region.getText();
        }

        if(registrar_bodega_txtfl_tramo.getText().isEmpty()){
            valid = false;
        }else{
            section = this.registrar_bodega_txtfl_tramo.getText();
        }

        return valid;
    }

    //Datos
    private String name;
    private String condition;
    private String region;
    private String section;
    Cellar cellar;
    CellarCrud cellarCrud = new CellarCrud();
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


