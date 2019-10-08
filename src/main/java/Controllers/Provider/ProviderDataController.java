package Controllers.Provider;

import Clases.Cruds.ProviderCrud;
import Clases.Models.Provider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProviderDataController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datos_proveedor_rdbtn_critico_si.setToggleGroup(critialProvider);
        datos_proveedor_rdbtn_critico_no.setToggleGroup(critialProvider);
        datos_proveedor_rdbtn_aprobado_si.setToggleGroup(approvedProvider);
        datos_proveedor_rdbtn_aprobado_no.setToggleGroup(approvedProvider);
    }

    public void updateProvider(){
       if(verifyData()) {
           id = Integer.parseInt(datos_proveedor_txtfl_id.getText());
           name = datos_proveedor_txtfl_nombre.getText();
           telephone = datos_proveedor_txtfl_telefono.getText();
           contact = datos_proveedor_txtfl_contacto.getText();
           providerCode = datos_proveedor_txtfl_proveedor.getText();
           service = datos_proveedor_txtfl_servicio.getText();
           rating = Integer.parseInt(datos_proveedor_txtfl_punteo.getText());
           if (datos_proveedor_rdbtn_critico_si.isSelected()) {
               critical = true;
           } else {
               critical = false;
           }
           if (datos_proveedor_rdbtn_aprobado_si.isSelected()) {
               approved = true;
           } else {
               approved = false;
           }
           approvalDate = datos_proveedor_dtpicker_aprobacion.getValue();
           revalidationDate = datos_proveedor_dtpicker_revalidacion.getValue();

           providerCrud.update(new Provider(id, name, telephone, contact, providerCode, service, rating, critical, approved, approvalDate, revalidationDate));
           cancel();
           JOptionPane.showMessageDialog(null, "Los datos del proveedor fueron actualizados correctamente.");
       }else {
           JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los campos.");
       }
    }

    public void cancel(){
        Stage stage = (Stage) datos_proveedor_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void providerData(Provider provider){
        id = provider.getId();
        name = provider.getName();
        telephone = provider.getTelephone();
        contact = provider.getContact();
        providerCode = provider.getProviderCode();
        service = provider.getService();
        rating = provider.getRating();
        critical = provider.isCritical();
        approved = provider.isApproved();
        approvalDate = provider.getApprovalDate();
        revalidationDate = provider.getRevalidationDate();

        datos_proveedor_txtfl_id.setText(Integer.toString(id));
        datos_proveedor_txtfl_nombre.setText(name);
        datos_proveedor_txtfl_telefono.setText(telephone);
        datos_proveedor_txtfl_contacto.setText(contact);
        datos_proveedor_txtfl_proveedor.setText(providerCode);
        datos_proveedor_txtfl_servicio.setText(service);
        datos_proveedor_txtfl_punteo.setText(Integer.toString(rating));

        if(critical){
            datos_proveedor_rdbtn_critico_si.setSelected(true);
        }else{
            datos_proveedor_rdbtn_critico_no.setSelected(true);
        }

        if(approved){
            datos_proveedor_rdbtn_aprobado_si.setSelected(true);
        }else {
            datos_proveedor_rdbtn_aprobado_no.setSelected(true);
        }

        datos_proveedor_dtpicker_aprobacion.setValue(approvalDate);
        datos_proveedor_dtpicker_revalidacion.setValue(revalidationDate);
    }

    private boolean verifyData(){
        boolean valid = true;

        if(datos_proveedor_txtfl_id.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_txtfl_nombre.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_txtfl_telefono.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_txtfl_contacto.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_txtfl_proveedor.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_txtfl_servicio.getText().isEmpty()){
            valid = false;
        }
        if (datos_proveedor_txtfl_punteo.getText().isEmpty()){
            valid = false;
        }
        if(datos_proveedor_dtpicker_aprobacion.getValue() == null){
            valid = false;
        }
        if(datos_proveedor_dtpicker_revalidacion.getValue() == null){
            valid = false;
        }
        return valid;
    }

    private int id;
    private String name;
    private String telephone;
    private String contact;
    private String providerCode;
    private String service;
    private int rating;
    private boolean critical;
    private boolean approved;
    private LocalDate approvalDate;
    private LocalDate revalidationDate;
    private ToggleGroup critialProvider = new ToggleGroup();
    private ToggleGroup approvedProvider = new ToggleGroup();
    private ProviderCrud providerCrud = new ProviderCrud();

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
