package Controllers.Provider;

import Clases.Cruds.ProviderCrud;
import Clases.Models.Provider;
import Controllers.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateProviderController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registrar_proveedor_rdbtn_crit_si.setSelected(true);
        registrar_proveedor_rdbtn_app_si.setSelected(true);
        registrar_proveedor_rdbtn_crit_si.setToggleGroup(critialGroup);
        registrar_proveedor_rdbtn_crit_no.setToggleGroup(critialGroup);
        registrar_proveedor_rdbtn_app_si.setToggleGroup(approvedGroup);
        registrar_proveedor_rdbtn_app_no.setToggleGroup(approvedGroup);
    }

    public void cancel(){
        try{
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/provider_menu_gui.fxml"));
            panel_menu_proveedores = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        MainMenuController.getParentPane().setCenter(panel_menu_proveedores);
    }

    public void createProvider(){

        if(verifyData()){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar proveedor");
            confirmacion.setHeaderText("Registrando un nuevo proveedor");
            confirmacion.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
               provider = new Provider(name, telephone, contact, code, service, rating, critial, approved, approvalDate, revalidationDate);
                providerCrud.create(provider);
                JOptionPane.showMessageDialog(null,"¡El proveedor se ha registrado exitosamente!");

                try {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/provider_menu_gui.fxml"));
                    panel_menu_proveedores = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MainMenuController.getParentPane().setCenter(panel_menu_proveedores);

            }else{
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
        }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
        }
    }

    public boolean verifyData(){
        boolean valid = true;

        if (registrar_proveedor_txtfl_nombre.getText().isEmpty()){
            valid = false;
        }else {
            name = registrar_proveedor_txtfl_nombre.getText();
        }

        if(registrar_proveedor_txtfl_telefono.getText().isEmpty()){
            valid = false;
        }else {
            telephone = registrar_proveedor_txtfl_telefono.getText();
        }

        if (registrar_proveedor_txtfl_contacto.getText().isEmpty()){
            valid = false;
        }else {
            contact = registrar_proveedor_txtfl_contacto.getText();
        }

        if(registrar_proveedor_txtfl_codigo.getText().isEmpty()){
            valid = false;
        }else {
            code = registrar_proveedor_txtfl_codigo.getText();
        }

        if (registrar_proveedor_txtfl_servicio.getText().isEmpty()){
            valid = false;
        }else {
            service = registrar_proveedor_txtfl_servicio.getText();
        }

        if (registrar_proveedor_txtfl_puntaje.getText().isEmpty()){
            valid = false;
        }else {
            rating = Integer.parseInt(registrar_proveedor_txtfl_puntaje.getText());
        }

        if(registrar_proveedor_rdbtn_crit_si.isSelected()){
            critial = true;
        }else{
            critial = false;
        }

        if(registrar_proveedor_rdbtn_app_si.isSelected()){
            approved = true;
        }else {
            approved = false;
        }

        if(registrar_proveedor_rdbtn_date_revalidacion.getValue() == null){
            valid = false;
        }else{
            revalidationDate = registrar_proveedor_rdbtn_date_revalidacion.getValue();
        }

        if(registrar_proveedor_date_app.getValue() == null){
            valid = false;
        }else {
            approvalDate = registrar_proveedor_date_app.getValue();
        }
        return valid;
    }

    //datos de proveedor
    private String name;
    private String telephone;
    private String contact;
    private String code;
    private String service;
    private int rating;
    private boolean critial;
    private boolean approved;
    private LocalDate approvalDate;
    private LocalDate revalidationDate;
    private ProviderCrud providerCrud = new ProviderCrud();
    Provider provider;

    //Paneles
    private AnchorPane panel_menu_proveedores;
    private FXMLLoader fxmlLoader;
    @FXML
    AnchorPane pane_registrar_proveedor;

    //TextFields
    @FXML
    TextField registrar_proveedor_txtfl_nombre;
    @FXML
    TextField registrar_proveedor_txtfl_telefono;
    @FXML
    TextField registrar_proveedor_txtfl_contacto;
    @FXML
    TextField registrar_proveedor_txtfl_servicio;
    @FXML
    TextField registrar_proveedor_txtfl_puntaje;
    @FXML
    TextField registrar_proveedor_txtfl_codigo;

    //RadioButtons y groupos
    @FXML
    RadioButton registrar_proveedor_rdbtn_crit_si;
    @FXML
    RadioButton registrar_proveedor_rdbtn_crit_no;
    @FXML
    RadioButton registrar_proveedor_rdbtn_app_si;
    @FXML
    RadioButton registrar_proveedor_rdbtn_app_no;
    private ToggleGroup critialGroup = new ToggleGroup();
    private ToggleGroup approvedGroup = new ToggleGroup();

    @FXML private DatePicker registrar_proveedor_date_app;
    @FXML private DatePicker registrar_proveedor_rdbtn_date_revalidacion;
}
