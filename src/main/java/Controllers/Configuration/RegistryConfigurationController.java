package Controllers.Configuration;

import Clases.Cruds.RegistryCrud;
import Clases.Models.Registry;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistryConfigurationController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getRegistries() {
        if (!config_txtfl_id.getText().isEmpty()) {
            id = Integer.parseInt(config_txtfl_id.getText());
        } else {
            id = 0;
        }
        name = config_txtfl_registro.getText();
        config_table_registros.setItems(registryCrud.read(new Registry(id, name)));
        config_clmn_idreg.setCellValueFactory(new PropertyValueFactory<>("id"));
        config_clmn_registro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        config_clmn_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        config_table_registros.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/update_registry_gui.fxml"));
                try {
                    fxmlLoader.load();
                    UpdateRegistryController modificarRegistroControlador = fxmlLoader.getController();
                    modificarRegistroControlador.registryData(config_table_registros.getSelectionModel().getSelectedItem());
                    Parent parent = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void createRegistry() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/new_registry_gui.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cancel() {
        Stage stage = (Stage) config_btn_cancelar.getScene().getWindow();
        stage.close();
    }


    private RegistryCrud registryCrud = new RegistryCrud();
    private int id;
    private String name;
    private FXMLLoader fxmlLoader;
    @FXML
    private TextField config_txtfl_id;
    @FXML
    private TextField config_txtfl_registro;
    @FXML
    private Button config_btn_cancelar;
    @FXML
    private TableView<Registry> config_table_registros;
    @FXML
    private TableColumn<Registry, Integer> config_clmn_idreg;
    @FXML
    private TableColumn<Registry, String> config_clmn_registro;
    @FXML
    private TableColumn<Registry, String> config_clmn_descripcion;
}
