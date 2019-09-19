package Controladores.Configuracion;

import Clases.BaseDeDatos.DBCreador;
import Clases.Modelos.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IngresarDB_Controlador implements Initializable {
    @FXML private TextField crear_db_nombre;
    @FXML private Button crear_db_btn_cancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelar(){
        Stage stage  = (Stage) crear_db_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void registrarBaseDeDatos(){
        DBCreador creador = new DBCreador(crear_db_nombre.getText(), CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        creador.createDataBase();
    }
}
