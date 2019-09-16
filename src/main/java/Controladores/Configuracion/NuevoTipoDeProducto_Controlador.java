package Controladores.Configuracion;

import Clases.Cruds.TipoDeProductos_Crud;
import Clases.Modelos.TipoDeProducto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NuevoTipoDeProducto_Controlador implements Initializable {
    @FXML private TextField nuevo_tdp_txtfl_tdp;
    @FXML private TextArea nuevo_tdp_txtarea_descripcion;
    @FXML private Button nuevo_tdp_btn_cancelar;
    private TipoDeProductos_Crud tipoDeProductosCrud = new TipoDeProductos_Crud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void nuevoTipoDeProducto(){
        if(validarDatos()){
            tipoDeProductosCrud.nuevoTipoDeProducto(new TipoDeProducto(nuevo_tdp_txtfl_tdp.getText(),nuevo_tdp_txtarea_descripcion.getText() ));
            cancelar();
            JOptionPane.showMessageDialog(null, "'"+nuevo_tdp_txtfl_tdp.getText()+"' fue registrado correctamente.");
        }
    }

    public void cancelar(){
        Stage stage  = (Stage) nuevo_tdp_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarDatos(){
        boolean datosValidos = true;
        if(nuevo_tdp_txtfl_tdp.getText().isEmpty()){
            datosValidos  = false;
        }
        if (nuevo_tdp_txtarea_descripcion.getText().isEmpty()){
            datosValidos = false;
        }
        return datosValidos;
    }
}
