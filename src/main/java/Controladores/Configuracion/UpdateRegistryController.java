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

public class UpdateRegistryController implements Initializable {
    @FXML private TextField configurar_registro_txtfl_id;
    @FXML private TextField configurar_registro_txtfl_registro;
    @FXML private TextArea configurar_registro_txtarea_descripcion;
    @FXML private Button configurar_registro_btn_eliminar;
    @FXML private Button configurar_registro_btn_guardar;
    @FXML private Button configurar_registro_btn_cancelar;
    private int id;
    private String name;
    private  String description;
    private RegistryCrud registryCrud = new RegistryCrud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateRegistry(){
        if(verifyData()){
            id = Integer.parseInt(configurar_registro_txtfl_id.getText());
            name = configurar_registro_txtfl_registro.getText();
            description = configurar_registro_txtarea_descripcion.getText();
            registryCrud.update(new Registry(id, name, description));
            cancel();
            JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente.");
        }else {
            JOptionPane.showMessageDialog(null, "Todos los datos son necesarios para actualizar el registro.");
        }
    }

    public void deleteRegistry(){
        id = Integer.parseInt(configurar_registro_txtfl_id.getText());
        name = configurar_registro_txtfl_registro.getText();
        description = configurar_registro_txtarea_descripcion.getText();
        registryCrud.delete(new Registry(id, name, description));
        cancel();
        JOptionPane.showMessageDialog(null, "Registro Eliminado.");
    }

    public void registryData(Registry registry){
        id = registry.getId();
        name = registry.getName();
        description = registry.getDescription();

        configurar_registro_txtfl_id.setText(Integer.toString(id));
        configurar_registro_txtfl_registro.setText(name);
        configurar_registro_txtarea_descripcion.setText(description);
    }

    public void cancel(){
        Stage stage = (Stage) configurar_registro_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verifyData(){
        boolean valid = true;
        if(configurar_registro_txtfl_id.getText().isEmpty()){
            valid = false;
        }
        if (configurar_registro_txtfl_registro.getText().isEmpty()){
            valid = false;
        }
        if (configurar_registro_txtarea_descripcion.getText().isEmpty()){
            valid = false;
        }
        return valid;
    }
}
