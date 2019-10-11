package Controllers.Configuration;

import Clases.Cruds.ProductTypeCrud;
import Clases.Models.ProductType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateProductTypeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createProductType() {
        if (verifyData()) {
            productTypeCrud.create(new ProductType(nuevo_tdp_txtfl_tdp.getText(), nuevo_tdp_txtarea_descripcion.getText()));
            cancel();
            JOptionPane.showMessageDialog(null, "'" + nuevo_tdp_txtfl_tdp.getText() + "' fue registrado correctamente.");
        }
    }

    public void cancel() {
        Stage stage = (Stage) nuevo_tdp_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verifyData() {
        boolean valid = true;
        if (nuevo_tdp_txtfl_tdp.getText().isEmpty()) {
            valid = false;
        }
        if (nuevo_tdp_txtarea_descripcion.getText().isEmpty()) {
            valid = false;
        }
        return valid;
    }

    @FXML
    private TextField nuevo_tdp_txtfl_tdp;
    @FXML
    private TextArea nuevo_tdp_txtarea_descripcion;
    @FXML
    private Button nuevo_tdp_btn_cancelar;
    private ProductTypeCrud productTypeCrud = new ProductTypeCrud();
}
