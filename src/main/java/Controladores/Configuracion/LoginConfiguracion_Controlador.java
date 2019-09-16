package Controladores.Configuracion;

import Clases.Modelos.Configuracion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginConfiguracion_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarConfiguracion();
    }

    public void guardarConfiguracion(){
        //Guarda los datos en la configuracion actual.
        if(validarDatos()) {
            configuracion.archivoConfiguracion(login_config_txtfl_servidor.getText(), login_config_txtfl_puerto.getText(), login_config_txtfl_db.getText());
            JOptionPane.showMessageDialog(null, "La configuracion ha sido guardada exitosamente.");
            Stage stage = (Stage) login_config_btn_cancelar.getScene().getWindow();
            stage.close();
        }else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos de configuracion.");
        }
    }

    public void mostrarConfiguracion(){
        //Comprueba que el archivo de configuracion exista,
        // si no es encontrado, lo crea y muestra los datos.
        configuracion.leerConfiguracion();

        login_config_txtfl_servidor.setText(Configuracion.getServidor());
        login_config_txtfl_puerto.setText(Configuracion.getPuerto());
        login_config_txtfl_db.setText(Configuracion.getBaseDeDatos());
    }

    private boolean validarDatos(){
        boolean datosValidos = true;

        if(login_config_txtfl_db.getText().isEmpty()){datosValidos = false;}

        if(login_config_txtfl_servidor.getText().isEmpty()){datosValidos = false;}

        if(login_config_txtfl_puerto.getText().isEmpty()){datosValidos = false;}

        return datosValidos;
    }

    public void cancelar(){
        Stage stage = (Stage) login_config_btn_cancelar.getScene().getWindow();
      stage.close();
    }

    //Atributos y objetos
    Configuracion configuracion = new Configuracion();
    @FXML private TextField login_config_txtfl_servidor;
    @FXML private TextField login_config_txtfl_puerto;
    @FXML private TextField login_config_txtfl_db;
    @FXML private Button login_config_btn_cancelar;
}
