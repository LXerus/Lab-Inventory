package Controllers.Configuration;

import Clases.Cruds.RegistryCrud;
import Clases.Models.Registry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRegistryController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createRegistry() {
        if (verifyData()) {
            String registry = nuevo_registro_txtfl_registro.getText();
            String description = nuevo_registro_txtarea_descripcion.getText();
            registryCrud.create(new Registry(registry, description));
            cancel();
            JOptionPane.showMessageDialog(null, "El numero de registry se ha guardado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "Todos los datos son necesarios para crear el regristro.");
        }
    }

    public void cancel() {
        Stage stage = (Stage) nuevo_registro_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verifyData() {
        boolean valid = true;
        if (nuevo_registro_txtfl_registro.getText().isEmpty()) {
            valid = false;
        }
        if (nuevo_registro_txtarea_descripcion.getText().isEmpty()) {
            valid = false;
        }
        return valid;
    }

    @FXML
    private TextField nuevo_registro_txtfl_registro;
    @FXML
    private TextArea nuevo_registro_txtarea_descripcion;
    @FXML
    private Button nuevo_registro_btn_crear;
    @FXML
    private Button nuevo_registro_btn_cancelar;
    private RegistryCrud registryCrud = new RegistryCrud();

}
