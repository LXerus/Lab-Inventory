package Controladores.Proveedores;

import Clases.Cruds.ProviderCrud;
import Clases.Modelos.Provider;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
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

public class RegistrarProveedor_Controlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registrar_proveedor_rdbtn_crit_si.setSelected(true);
        registrar_proveedor_rdbtn_app_si.setSelected(true);
        registrar_proveedor_rdbtn_crit_si.setToggleGroup(proveedorCritico);
        registrar_proveedor_rdbtn_crit_no.setToggleGroup(proveedorCritico);
        registrar_proveedor_rdbtn_app_si.setToggleGroup(proveedorAprobado);
        registrar_proveedor_rdbtn_app_no.setToggleGroup(proveedorAprobado);
    }

    public void cancelarRegistrarProveedor(){
        try{
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
            panel_menu_proveedores = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_proveedores);
    }

    public void registrarProveedor(){

        if(validarDatos()){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar proveedor");
            confirmacion.setHeaderText("Registrando un nuevo proveedor");
            confirmacion.setContentText("¿Desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
               provider = new Provider(nombre, telefono, contacto, codigo, servicio, puntaje, critico, aprobado, fechaDeAprobacion, fechaDeRevalidacion);
                proveedoresCrud.create(provider);
                JOptionPane.showMessageDialog(null,"¡El proveedor se ha registrado exitosamente!");

                try {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_proveedores_gui.fxml"));
                    panel_menu_proveedores = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_proveedores);

            }else{
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
            }
        }else {
            JOptionPane.showMessageDialog(null,"Por favor ingresar todos los campos.");
        }
    }

    public boolean validarDatos(){
        boolean validarDatos = true;

        if (registrar_proveedor_txtfl_nombre.getText().isEmpty()){
            validarDatos = false;
        }else {
            nombre = registrar_proveedor_txtfl_nombre.getText();
        }

        if(registrar_proveedor_txtfl_telefono.getText().isEmpty()){
            validarDatos = false;
        }else {
            telefono = registrar_proveedor_txtfl_telefono.getText();
        }

        if (registrar_proveedor_txtfl_contacto.getText().isEmpty()){
            validarDatos = false;
        }else {
            contacto = registrar_proveedor_txtfl_contacto.getText();
        }

        if(registrar_proveedor_txtfl_codigo.getText().isEmpty()){
            validarDatos = false;
        }else {
            codigo = registrar_proveedor_txtfl_codigo.getText();
        }

        if (registrar_proveedor_txtfl_servicio.getText().isEmpty()){
            validarDatos = false;
        }else {
            servicio = registrar_proveedor_txtfl_servicio.getText();
        }

        if (registrar_proveedor_txtfl_puntaje.getText().isEmpty()){
            validarDatos = false;
        }else {
            puntaje = Integer.parseInt(registrar_proveedor_txtfl_puntaje.getText());
        }

        if(registrar_proveedor_rdbtn_crit_si.isSelected()){
            critico = true;
        }else{
            critico = false;
        }

        if(registrar_proveedor_rdbtn_app_si.isSelected()){
            aprobado = true;
        }else {
            aprobado = false;
        }

        if(registrar_proveedor_rdbtn_date_revalidacion.getValue() == null){
            validarDatos = false;
        }else{
            fechaDeRevalidacion = registrar_proveedor_rdbtn_date_revalidacion.getValue();
        }

        if(registrar_proveedor_date_app.getValue() == null){
            validarDatos = false;
        }else {
            fechaDeAprobacion = registrar_proveedor_date_app.getValue();
        }
        return validarDatos;
    }

    //datos de proveedor
    private String nombre;
    private String telefono;
    private String contacto;
    private String codigo;
    private String servicio;
    private int puntaje;
    private boolean critico;
    private boolean aprobado;
    private LocalDate fechaDeAprobacion;
    private LocalDate fechaDeRevalidacion;
    private ProviderCrud proveedoresCrud = new ProviderCrud();
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
    private ToggleGroup proveedorCritico = new ToggleGroup();
    private ToggleGroup proveedorAprobado = new ToggleGroup();

    @FXML private DatePicker registrar_proveedor_date_app;
    @FXML private DatePicker registrar_proveedor_rdbtn_date_revalidacion;
}
