package Controladores.Usuarios;

import Clases.Cruds.ActivityLogCrud;
import Clases.Modelos.UserActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class LogDeActividades_Controlador implements Initializable {
    @FXML private TextField log_txtfl_nombres;
    @FXML private TextField log_txtfl_apellidos;
    @FXML private TextField log_txtfl_email;
    @FXML private TextField log_txtfl_id;
    @FXML private TextField log_txtfl_id_producto;
    @FXML private DatePicker log_dpicker_fecha;
    @FXML private ComboBox log_cbox_categoria;
    @FXML private ComboBox log_cbox_actividad;

    @FXML private TableView<UserActivity> log_tabla_log;
    @FXML private TableColumn<UserActivity, Integer> log_clmn_id;
    @FXML private TableColumn<UserActivity, String> log_clmn_nombres;
    @FXML private TableColumn<UserActivity, String> log_clmn_apellidos;
    @FXML private TableColumn<UserActivity, String> log_clmn_email;
    @FXML private TableColumn<UserActivity, String> log_clmn_actividad;
    @FXML private TableColumn<UserActivity, String> log_clmn_categoria;
    @FXML private TableColumn<UserActivity, LocalDate> log_clmn_fecha;
    @FXML private TableColumn<UserActivity, LocalTime> log_clmn_hora;
    @FXML private TableColumn<UserActivity, Integer> log_clmn_id_producto;

    ActivityLogCrud logActivadesCrud = new ActivityLogCrud();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void obtenerLog(){
        log_tabla_log.setItems(logActivadesCrud.obtenerLogActividades(datosIntroducidos()));
        log_clmn_id.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        log_clmn_nombres.setCellValueFactory(new PropertyValueFactory<>("nombre_usuario"));
        log_clmn_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos_usuario"));
        log_clmn_email.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        log_clmn_actividad.setCellValueFactory(new PropertyValueFactory<>("tipo_de_actividad"));
        log_clmn_categoria.setCellValueFactory(new PropertyValueFactory<>("tabla"));
        log_clmn_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        log_clmn_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        log_clmn_id_producto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
    }

    public String datosIntroducidos(){
        String sqlQuery = "SELECT id_usuario, nombre, apellidos, correo_electronico, tipo_de_actividad, tabla, fecha, hora, id_producto FROM registro_de_actividades WHERE ";
        if(!log_txtfl_nombres.getText().isEmpty()){
            sqlQuery += "nombre LIKE '%"+log_txtfl_nombres.getText()+"%' AND ";
        }

        if(!log_txtfl_apellidos.getText().isEmpty()){
            sqlQuery += "apellidos LIKE '%"+log_txtfl_apellidos.getText()+"%' AND ";
        }

        if(!log_txtfl_email.getText().isEmpty()){
            sqlQuery += "correo_electronico LIKE '%"+log_txtfl_email.getText()+"%' AND ";
        }

        if(!log_txtfl_id.getText().isEmpty()){
            sqlQuery += "id_usuario LIKE '%"+log_txtfl_id.getText()+"%' AND ";
        }

        if(!log_txtfl_id_producto.getText().isEmpty()){
            sqlQuery += "id_producto LIKE '%"+log_txtfl_id_producto.getText()+"%' AND ";
        }

        if(!(log_dpicker_fecha.getValue() == null)){
            sqlQuery += "fecha = '"+ log_dpicker_fecha.getValue()+"' AND ";
        }

        if(!(log_cbox_categoria.getValue() == null)){
            sqlQuery += "tabla LIKE '%"+log_cbox_categoria.getValue()+"%' AND ";
        }

        if(!(log_cbox_actividad.getValue() == null)){
            sqlQuery += "tipo_de_actividad LIKE '%"+log_cbox_actividad.getValue()+"%' AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String limpiarDatos = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlQuery = limpiarDatos;
        return sqlQuery;
    }
}
