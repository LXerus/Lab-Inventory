package Controllers.User;

import Clases.BaseDeDatos.connection;
import Clases.Cruds.UserCrud;
import Clases.Models.User;
import Clases.Models.CurrentUser;
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

public class UserDataController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datos_usuario_rdbtn_si.setToggleGroup(grupoEstado);
        datos_usuario_rdbtn_no.setToggleGroup(grupoEstado);
        datos_usuario_cbox_privilegios.setItems(getPrivilegesList());
    }

    public void updateData(){
        if(verifyData()){
            id = Integer.parseInt(datos_usuario_txtfl_id.getText());
            name = datos_usuario_txtfl_nombres.getText();
            lastName = datos_usuario_txtfl_apellidos.getText();
            password = datos_usuario_txtfl_password.getText();
            area = datos_usuario_txtfl_area.getText();
            email = datos_usuario_txtfl_email.getText();
            entrydate = datos_usuario_datepk_fdi.getValue();
            privileges = datos_usuario_cbox_privilegios.getSelectionModel().getSelectedIndex() + 1;
            if(datos_usuario_rdbtn_si.isSelected()){
                active = true;
            }else {
                active = false;
            }

            usuariosCrud.update(new User(id, name, lastName, password, entrydate, area, active, email, privileges));
            cancel();
            JOptionPane.showMessageDialog(null, "Los datos de usuario han sido actualizados");
        }else {
            JOptionPane.showMessageDialog(null, "Por favor, asegurese que todos los campos esten completos.");
        }
    }

    public void cancel(){
        Stage stage = (Stage) datos_usuario_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void userData(User user){
        id = user.getId();
        name = user.getName();
        lastName = user.getLastName();
        password = user.getPassword();
        area = user.getArea();
        email = user.getEmail();
        entrydate = user.getStartDate();
        active = user.isActive();
        privileges = user.getPrivileges();

        datos_usuario_txtfl_id.setText(Integer.toString(id));
        datos_usuario_txtfl_nombres.setText(name);
        datos_usuario_txtfl_apellidos.setText(lastName);
        datos_usuario_txtfl_password.setText(password);
        datos_usuario_txtfl_area.setText(area);
        datos_usuario_txtfl_email.setText(email);
        datos_usuario_datepk_fdi.setValue(entrydate);
        datos_usuario_cbox_privilegios.setValue(usuariosCrud.getUserPrivileges(user));
        if(active){
            datos_usuario_rdbtn_si.setSelected(true);
        }else {
            datos_usuario_rdbtn_no.setSelected(true);
        }
    }

    public ObservableList<String> getPrivilegesList(){
        javafx.collections.ObservableList<java.lang.String> privilegesList = FXCollections.observableArrayList();
        java.lang.String consultaSQL = "SELECT id, tipo_de_privilegios, descripcion FROM privilegios_de_usuario";
        connection JDBConnection = new connection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        Connection conexionSQL = JDBConnection.getConnection();
        try{
            Statement statement = conexionSQL.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSQL);
            while (resultSet.next()){
                java.lang.String tipo_de_privilegio = resultSet.getString(2);
                privilegesList.add(tipo_de_privilegio);
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
            JDBConnection.disconnect();
        }
        return  privilegesList;
    }

    public Boolean verifyData(){
        boolean valid = true;
        if(datos_usuario_txtfl_nombres.getText().isEmpty()){
            valid = false;
        }
        if(datos_usuario_txtfl_apellidos.getText().isEmpty()){
            valid = false;
        }
        if(datos_usuario_txtfl_password.getText().isEmpty()){
            valid = false;
        }
        if(datos_usuario_txtfl_area.getText().isEmpty()){
            valid = false;
        }
        if(datos_usuario_txtfl_email.getText().isEmpty()){
            valid = false;
        }
        return valid;
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
    private UserCrud usuariosCrud = new UserCrud();
    private ToggleGroup grupoEstado =  new ToggleGroup();
    int id;
    String name;
    String lastName;
    String password;
    String area;
    String email;
    LocalDate entrydate;
    boolean active;
    int privileges;

}
