package Controladores.Usuarios;

import Clases.BaseDeDatos.Conectar;
import Clases.Cruds.Usuarios_Crud;
import Clases.Modelos.Usuario;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DatosUsuario_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datos_usuario_rdbtn_si.setToggleGroup(grupoEstado);
        datos_usuario_rdbtn_no.setToggleGroup(grupoEstado);
        datos_usuario_cbox_privilegios.setItems(obtenerListaDePrivilegios());
    }

    public void actualizarDatos(){
        if(validarDatos()){
            id = Integer.parseInt(datos_usuario_txtfl_id.getText());
            nombres = datos_usuario_txtfl_nombres.getText();
            apellidos = datos_usuario_txtfl_apellidos.getText();
            password = datos_usuario_txtfl_password.getText();
            area = datos_usuario_txtfl_area.getText();
            email = datos_usuario_txtfl_email.getText();
            fechaIngreso = datos_usuario_datepk_fdi.getValue();
            privilegios = datos_usuario_cbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
            if(datos_usuario_rdbtn_si.isSelected()){
                activo = true;
            }else {
                activo = false;
            }

            usuariosCrud.actualizarUsuario(new Usuario(id, nombres, apellidos, password, fechaIngreso, area, activo, email, privilegios));
            cancelar();
            JOptionPane.showMessageDialog(null, "Los datos de usuario han sido actualizados");
        }else {
            JOptionPane.showMessageDialog(null, "Por favor, asegurese que todos los campos esten completos.");
        }
    }

    public void cancelar(){
        Stage stage = (Stage) datos_usuario_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void datosUsuario(Usuario usuario){
        id = usuario.getId();
        nombres = usuario.getNombres();
        apellidos = usuario.getApellidos();
        password = usuario.getPassword();
        area = usuario.getArea();
        email = usuario.getCorreo_electronico();
        fechaIngreso = usuario.getFecha_de_ingreso();
        activo = usuario.isActivo();
        privilegios = usuario.getPrivilegios();

        datos_usuario_txtfl_id.setText(Integer.toString(id));
        datos_usuario_txtfl_nombres.setText(nombres);
        datos_usuario_txtfl_apellidos.setText(apellidos);
        datos_usuario_txtfl_password.setText(password);
        datos_usuario_txtfl_area.setText(area);
        datos_usuario_txtfl_email.setText(email);
        datos_usuario_datepk_fdi.setValue(fechaIngreso);
        datos_usuario_cbox_privilegios.setValue(usuariosCrud.obtenerPrivilegiosDeUsuario(usuario));
        if(activo){
            datos_usuario_rdbtn_si.setSelected(true);
        }else {
            datos_usuario_rdbtn_no.setSelected(true);
        }
    }

    public ObservableList<String> obtenerListaDePrivilegios(){
        javafx.collections.ObservableList<java.lang.String> listaDePrivilegios = FXCollections.observableArrayList();
        java.lang.String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        Conectar conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        Connection conexionSQL = conectar.getConnection();
        try{
            Statement privilegiosStatement = conexionSQL.createStatement();
            ResultSet rsPrivilegios = privilegiosStatement.executeQuery(consultaSQL);
            while (rsPrivilegios.next()){
                java.lang.String tipo_de_privilegio = rsPrivilegios.getString(2);
                listaDePrivilegios.add(tipo_de_privilegio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(conexionSQL != null){
                    conexionSQL.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            conectar.desconectar();
        }
        return  listaDePrivilegios;
    }

    public Boolean validarDatos(){
        boolean datosValidos = true;
        if(datos_usuario_txtfl_nombres.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_usuario_txtfl_apellidos.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_usuario_txtfl_password.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_usuario_txtfl_area.getText().isEmpty()){
            datosValidos = false;
        }
        if(datos_usuario_txtfl_email.getText().isEmpty()){
            datosValidos = false;
        }
        return datosValidos;
    }

    @FXML private TextField datos_usuario_txtfl_id;
    @FXML private TextField datos_usuario_txtfl_nombres;
    @FXML private TextField datos_usuario_txtfl_apellidos;
    @FXML private TextField datos_usuario_txtfl_password;
    @FXML private TextField datos_usuario_txtfl_area;
    @FXML private TextField datos_usuario_txtfl_email;
    @FXML private DatePicker datos_usuario_datepk_fdi;
    @FXML private RadioButton datos_usuario_rdbtn_si;
    @FXML private RadioButton datos_usuario_rdbtn_no;
    @FXML private ComboBox datos_usuario_cbox_privilegios;
    @FXML private Button datos_usuario_btn_cancelar;
    private Usuarios_Crud usuariosCrud = new Usuarios_Crud();
    private ToggleGroup grupoEstado =  new ToggleGroup();
    int id;
    String nombres;
    String apellidos;
    String password;
    String area;
    String email;
    LocalDate fechaIngreso;
    boolean activo;
    int privilegios;

}
