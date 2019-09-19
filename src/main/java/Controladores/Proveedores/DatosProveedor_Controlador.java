package Controladores.Proveedores;

import Clases.Cruds.ProviderCrud;
import Clases.Modelos.Provider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DatosProveedor_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datos_proveedor_rdbtn_critico_si.setToggleGroup(proveedorCritico);
        datos_proveedor_rdbtn_critico_no.setToggleGroup(proveedorCritico);
        datos_proveedor_rdbtn_aprobado_si.setToggleGroup(proveedorAprobado);
        datos_proveedor_rdbtn_aprobado_no.setToggleGroup(proveedorAprobado);
    }

    public void guardarCambios(){
       if(validarDatos()) {
           id = Integer.parseInt(datos_proveedor_txtfl_id.getText());
           nombre = datos_proveedor_txtfl_nombre.getText();
           telefono = datos_proveedor_txtfl_telefono.getText();
           contacto = datos_proveedor_txtfl_contacto.getText();
           codigoProveedor = datos_proveedor_txtfl_proveedor.getText();
           servicio = datos_proveedor_txtfl_servicio.getText();
           punteo = Integer.parseInt(datos_proveedor_txtfl_punteo.getText());
           if (datos_proveedor_rdbtn_critico_si.isSelected()) {
               critico = true;
           } else {
               critico = false;
           }
           if (datos_proveedor_rdbtn_aprobado_si.isSelected()) {
               aprobado = true;
           } else {
               aprobado = false;
           }
           fechaAprobacion = datos_proveedor_dtpicker_aprobacion.getValue();
           fechaRevalidacion = datos_proveedor_dtpicker_revalidacion.getValue();

           proveedoresCrud.update(new Provider(id, nombre, telefono, contacto, codigoProveedor, servicio, punteo, critico, aprobado, fechaAprobacion, fechaRevalidacion));
           cancelar();
           JOptionPane.showMessageDialog(null, "Los datos del proveedor fueron actualizados correctamente.");
       }else {
           JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los campos.");
       }
    }

    public void cancelar(){
        Stage stage = (Stage) datos_proveedor_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void datosProveedor(Provider provider){
        id = provider.getId();
        nombre = provider.getName();
        telefono = provider.getTelephone();
        contacto = provider.getContact();
        codigoProveedor = provider.getProviderCode();
        servicio = provider.getService();
        punteo = provider.getRating();
        critico = provider.isCritical();
        aprobado = provider.isApproved();
        fechaAprobacion = provider.getApprovalDate();
        fechaRevalidacion = provider.getRevalidationDate();

        datos_proveedor_txtfl_id.setText(Integer.toString(id));
        datos_proveedor_txtfl_nombre.setText(nombre);
        datos_proveedor_txtfl_telefono.setText(telefono);
        datos_proveedor_txtfl_contacto.setText(contacto);
        datos_proveedor_txtfl_proveedor.setText(codigoProveedor);
        datos_proveedor_txtfl_servicio.setText(servicio);
        datos_proveedor_txtfl_punteo.setText(Integer.toString(punteo));

        if(critico){
            datos_proveedor_rdbtn_critico_si.setSelected(true);
        }else{
            datos_proveedor_rdbtn_critico_no.setSelected(true);
        }

        if(aprobado){
            datos_proveedor_rdbtn_aprobado_si.setSelected(true);
        }else {
            datos_proveedor_rdbtn_aprobado_no.setSelected(true);
        }

        datos_proveedor_dtpicker_aprobacion.setValue(fechaAprobacion);
        datos_proveedor_dtpicker_revalidacion.setValue(fechaRevalidacion);
    }

    private boolean validarDatos(){
        boolean datosValidos = true;

        if(datos_proveedor_txtfl_id.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_txtfl_nombre.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_txtfl_telefono.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_txtfl_contacto.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_txtfl_proveedor.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_txtfl_servicio.getText().isEmpty()){
            datosValidos = false;
        }
        if (datos_proveedor_txtfl_punteo.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_proveedor_dtpicker_aprobacion.getValue() == null){
            datosValidos = false;
        }
        if(datos_proveedor_dtpicker_revalidacion.getValue() == null){
            datosValidos = false;
        }
        return datosValidos;
    }

    private int id;
    private String nombre;
    private String telefono;
    private String contacto;
    private String codigoProveedor;
    private String servicio;
    private int punteo;
    private boolean critico;
    private boolean aprobado;
    private LocalDate fechaAprobacion;
    private LocalDate fechaRevalidacion;
    private ToggleGroup proveedorCritico = new ToggleGroup();
    private ToggleGroup proveedorAprobado = new ToggleGroup();
    private ProviderCrud proveedoresCrud = new ProviderCrud();

    @FXML private TextField datos_proveedor_txtfl_id;
    @FXML private TextField datos_proveedor_txtfl_nombre;
    @FXML private TextField datos_proveedor_txtfl_telefono;
    @FXML private TextField datos_proveedor_txtfl_contacto;
    @FXML private TextField datos_proveedor_txtfl_proveedor;
    @FXML private TextField datos_proveedor_txtfl_servicio;
    @FXML private TextField datos_proveedor_txtfl_punteo;
    @FXML private RadioButton datos_proveedor_rdbtn_critico_si;
    @FXML private RadioButton datos_proveedor_rdbtn_critico_no;
    @FXML private RadioButton datos_proveedor_rdbtn_aprobado_si;
    @FXML private RadioButton datos_proveedor_rdbtn_aprobado_no;
    @FXML private DatePicker datos_proveedor_dtpicker_aprobacion;
    @FXML private DatePicker datos_proveedor_dtpicker_revalidacion;
    @FXML private Button datos_proveedor_btn_cancelar;
}
