package Controllers.Configuration;

import Clases.Models.Configuration;
import Controllers.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showConfiguration();
    }

    public void cancel() {
        MainMenuController.getParentPane().setCenter(null);
    }

    public void createDataBase() {
        //Metodo llamado desde el menu principal.
        //Tambien es llamado desde el menu de log in, en cuando faltan datos en la configuration.
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/database_input_gui.fxml"));
            Parent parent = loader.load();
            Stage registrarDBStage = new Stage();
            registrarDBStage.setScene(new Scene(parent));
            registrarDBStage.centerOnScreen();
            registrarDBStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateConfiguration() {
        //Guarda los datos en la configuration actual.
        if (verifyData()) {
            configuration.configurationFile(configuracion_txtfl_ip.getText(), configuracion_txtfl_puerto.getText(), configuracion_txtfl_DB.getText());
            JOptionPane.showMessageDialog(null, "La configuration ha sido guardada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos de configuration.");
        }
    }

    public void showConfiguration() {
        //Comprueba que el archivo de configuration exista,
        // si no es encontrado, lo crea y muestra los datos.
        configuration.readConfiguration();

        configuracion_txtfl_ip.setText(Configuration.getServer());
        configuracion_txtfl_puerto.setText(Configuration.getPort());
        configuracion_txtfl_DB.setText(Configuration.getDataBase());
    }

    public void productTypeConfiguration() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/product_type_configuration_gui.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void presentationConfiguration() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/modificar_presentaciones_gui.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void registryConfiguration() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/registry_configuration_gui.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verifyData() {
        boolean valid = true;

        if (configuracion_txtfl_DB.getText().isEmpty()) {
            valid = false;
        }

        if (configuracion_txtfl_ip.getText().isEmpty()) {
            valid = false;
        }

        if (configuracion_txtfl_puerto.getText().isEmpty()) {
            valid = false;
        }

        return valid;
    }

    Configuration configuration = new Configuration();
    FXMLLoader fxmlLoader;
    @FXML
    private TextField configuracion_txtfl_DB;
    @FXML
    private TextField configuracion_txtfl_ip;
    @FXML
    private TextField configuracion_txtfl_puerto;
    @FXML
    private TableView<String> configuracion_table_DB;
    @FXML
    private TableColumn configuracion_clmn_id;
    @FXML
    private TableColumn configuracion_clmn_nombre;

}
