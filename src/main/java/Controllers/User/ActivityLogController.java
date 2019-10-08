package Controllers.User;

import Clases.Cruds.ActivityLogCrud;
import Clases.Models.UserActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ActivityLogController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log_txtfl_id.setText("0");
    }

    public void getLog(){
        log_tabla_log.setItems(activityLogCrud.read(dataInput()));
        log_clmn_id.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        log_clmn_nombres.setCellValueFactory(new PropertyValueFactory<>("userName"));
        log_clmn_apellidos.setCellValueFactory(new PropertyValueFactory<>("userLastName"));
        log_clmn_email.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        log_clmn_actividad.setCellValueFactory(new PropertyValueFactory<>("activityType"));
        log_clmn_categoria.setCellValueFactory(new PropertyValueFactory<>("table"));
        log_clmn_fecha.setCellValueFactory(new PropertyValueFactory<>("date"));
        log_clmn_hora.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private UserActivity dataInput() {
        int id;
        if (log_txtfl_id.getText().isEmpty()) {
            id = 0;
        } else {
            id = Integer.parseInt(log_txtfl_id.getText());
        }
        String userName = log_txtfl_nombres.getText();
        String userLastName = log_txtfl_apellidos.getText();
        String userEmail = log_txtfl_email.getText();
        LocalDate date = log_dpicker_fecha.getValue();

        return new UserActivity(id, userName, userLastName, userEmail, date);
    }

    @FXML private TextField log_txtfl_nombres;
    @FXML private TextField log_txtfl_apellidos;
    @FXML private TextField log_txtfl_email;
    @FXML private TextField log_txtfl_id;
    @FXML private DatePicker log_dpicker_fecha;

    @FXML private TableView<UserActivity> log_tabla_log;
    @FXML private TableColumn<UserActivity, Integer> log_clmn_id;
    @FXML private TableColumn<UserActivity, String> log_clmn_nombres;
    @FXML private TableColumn<UserActivity, String> log_clmn_apellidos;
    @FXML private TableColumn<UserActivity, String> log_clmn_email;
    @FXML private TableColumn<UserActivity, String> log_clmn_actividad;
    @FXML private TableColumn<UserActivity, String> log_clmn_categoria;
    @FXML private TableColumn<UserActivity, LocalDate> log_clmn_fecha;
    @FXML private TableColumn<UserActivity, LocalTime> log_clmn_hora;

    ActivityLogCrud activityLogCrud = new ActivityLogCrud();

}
