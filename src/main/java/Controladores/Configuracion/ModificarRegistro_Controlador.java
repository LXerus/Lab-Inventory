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

public class ModificarRegistro_Controlador implements Initializable {
    @FXML private TextField configurar_registro_txtfl_id;
    @FXML private TextField configurar_registro_txtfl_registro;
    @FXML private TextArea configurar_registro_txtarea_descripcion;
    @FXML private Button configurar_registro_btn_eliminar;
    @FXML private Button configurar_registro_btn_guardar;
    @FXML private Button configurar_registro_btn_cancelar;
    private int id;
    private String nombre;
    private  String descripcion;
    private RegistryCrud registroCrud = new RegistryCrud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void actualizarRegistro(){
        if(validarDatos()){
            id = Integer.parseInt(configurar_registro_txtfl_id.getText());
            nombre = configurar_registro_txtfl_registro.getText();
            descripcion = configurar_registro_txtarea_descripcion.getText();
            registroCrud.update(new Registry(id, nombre, descripcion));
            cancelar();
            JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente.");
        }else {
            JOptionPane.showMessageDialog(null, "Todos los datos son necesarios para actualizar el registro.");
        }
    }

    public void eliminarRegistro(){
        id = Integer.parseInt(configurar_registro_txtfl_id.getText());
        nombre = configurar_registro_txtfl_registro.getText();
        descripcion = configurar_registro_txtarea_descripcion.getText();
        registroCrud.delete(new Registry(id, nombre, descripcion));
        cancelar();
        JOptionPane.showMessageDialog(null, "Registro Eliminado.");
    }

    public void datosDelRegistro(Registry registry){
        id = registry.getId();
        nombre = registry.getName();
        descripcion = registry.getDescription();

        configurar_registro_txtfl_id.setText(Integer.toString(id));
        configurar_registro_txtfl_registro.setText(nombre);
        configurar_registro_txtarea_descripcion.setText(descripcion);
    }

    public void cancelar(){
        Stage stage = (Stage) configurar_registro_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarDatos(){
        boolean datosValidos = true;
        if(configurar_registro_txtfl_id.getText().isEmpty()){
            datosValidos = false;
        }
        if (configurar_registro_txtfl_registro.getText().isEmpty()){
            datosValidos = false;
        }
        if (configurar_registro_txtarea_descripcion.getText().isEmpty()){
            datosValidos = false;
        }
        return datosValidos;
    }
}
