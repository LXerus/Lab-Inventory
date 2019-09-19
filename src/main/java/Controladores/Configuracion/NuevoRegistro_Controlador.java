package Controladores.Configuracion;

import Clases.Cruds.RegistryCrud;
import Clases.Modelos.Registry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NuevoRegistro_Controlador implements Initializable {
    @FXML private TextField nuevo_registro_txtfl_registro;
    @FXML private TextArea nuevo_registro_txtarea_descripcion;
    @FXML private Button nuevo_registro_btn_crear;
    @FXML private Button nuevo_registro_btn_cancelar;
    private RegistryCrud registroCrud = new RegistryCrud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void crearNuevoRegistro(){
        if(validarDatos()){
            String registro = nuevo_registro_txtfl_registro.getText();
            String descripcion = nuevo_registro_txtarea_descripcion.getText();
            registroCrud.create(new Registry(registro, descripcion));
            cancelar();
            JOptionPane.showMessageDialog(null,"El numero de registro se ha guardado con exito!");
        }else {
            JOptionPane.showMessageDialog(null,"Todos los datos son necesarios para crear el regristro.");
        }
    }

    public void cancelar(){
        Stage stage = (Stage) nuevo_registro_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarDatos(){
        boolean datosValidos = true;
        if(nuevo_registro_txtfl_registro.getText().isEmpty()){
            datosValidos = false;
        }
        if (nuevo_registro_txtarea_descripcion.getText().isEmpty()){
            datosValidos = false;
        }
        return datosValidos;
    }
}
