package Controllers.Configuration;

import Clases.Cruds.ProductTypeCrud;
import Clases.Models.ProductType;
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

public class ProductTypeConfigurationController implements Initializable {
    @FXML
    private TextField config_tdp_txtfl_id;
    @FXML
    private TextField config_tdp_txtfl_reg;
    @FXML
    private Button config_tdp_btn_buscar;
    @FXML
    private Button config_tdp_btn_cancelar;
    @FXML
    private Button config_tdp_btn_nuevo;
    @FXML
    private TableView<ProductType> config_tdp_table_tdp;
    @FXML
    private TableColumn<ProductType, Integer> config_tdp_clmn_idtdp;
    @FXML
    private TableColumn<ProductType, String> config_tdp_clmn_nombretdp;
    @FXML
    private TableColumn<ProductType, String> config_tdp_clmn_desctdp;
    private ProductTypeCrud productTypeCrud = new ProductTypeCrud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getProductType() {
        int id = 0;
        if (!config_tdp_txtfl_id.getText().isEmpty()) {
            id = Integer.parseInt(config_tdp_txtfl_id.getText());
        }
        String productType = config_tdp_txtfl_reg.getText();
        productTypeCrud.read(new ProductType(id, productType));
        config_tdp_table_tdp.setItems(productTypeCrud.read(new ProductType(id, productType)));
        config_tdp_clmn_idtdp.setCellValueFactory(new PropertyValueFactory<>("id"));
        config_tdp_clmn_nombretdp.setCellValueFactory(new PropertyValueFactory<>("tipoDeProducto"));
        config_tdp_clmn_desctdp.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        config_tdp_table_tdp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/update_product_type_gui.fxml"));
                try {
                    fxmlLoader.load();
                    UpdateProductTypeController modificarTipoDeProducto = fxmlLoader.getController();
                    modificarTipoDeProducto.getProductTypeData(config_tdp_table_tdp.getSelectionModel().getSelectedItem());
                    Parent parent = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.setAlwaysOnTop(true);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void createProductType() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Configuration/new_product_type_gui.fxml"));
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cancel() {
        Stage stage = (Stage) config_tdp_btn_cancelar.getScene().getWindow();
        stage.close();
    }


}
