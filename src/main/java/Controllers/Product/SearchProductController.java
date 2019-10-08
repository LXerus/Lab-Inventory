package Controllers.Product;

import Clases.Cruds.ProductCrud;
import Clases.Models.ListGenerator;
import Clases.Models.Product;
import Controllers.MainMenu.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchProductController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getProviderList();
        buscar_producto_cmbox_prov.setItems(providersList);
        buscar_producto_cmbox_bodega.setItems(productCrud.getCellarList());
    }

    public void cancel() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/product_menu_gui.fxml"));
        selectedPane = fxmlLoader.load();
        MainMenuController.getParentPane().setCenter(selectedPane);
    }

    public void getProduct(){
        buscar_producto_table_lista_productos.setItems(productCrud.read(inputData()));
        producto_cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        producto_cl_nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        producto_cl_marca.setCellValueFactory(new PropertyValueFactory<>("brand"));
        producto_cl_bodega.setCellValueFactory(new PropertyValueFactory<>("cellarID"));
        producto_cl_cas.setCellValueFactory(new PropertyValueFactory<>("cas"));
        producto_cl_cod_interno.setCellValueFactory(new PropertyValueFactory<>("internalCode"));
        producto_cl_cod_stnd.setCellValueFactory(new PropertyValueFactory<>("standardCode"));
        producto_cl_lote.setCellValueFactory(new PropertyValueFactory<>("lot"));
        producto_cl_fingreso.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        producto_cl_fvencimiento.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        producto_cl_fabierto.setCellValueFactory(new PropertyValueFactory<>("openDate"));
        producto_cl_presentacion.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        producto_cl_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        producto_cl_costo.setCellValueFactory(new PropertyValueFactory<>("cost"));
        producto_cl_proveedor.setCellValueFactory(new PropertyValueFactory<>("providerID"));
        buscar_producto_table_lista_productos.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Product/selected_product_gui.fxml"));
                try{
                    loader.load();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                selectedProductController selectedProductController = loader.getController();
                selectedProductController.productData(buscar_producto_table_lista_productos.getSelectionModel().getSelectedItem());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
                stage.show();
            }
        });
    }

    public Product inputData(){
        String name = buscar_producto_txtfl_nombre.getText();
        String brand = buscar_producto_txtfl_marca.getText();
        String lot = buscar_producto_txtfl_lote.getText();
        String invoice = buscar_producto_txtfl_factura.getText();
        String cas = buscar_producto_txtfl_cas.getText();
        String internalCode = buscar_producto_txtfl_codinterno.getText();
        String standardCode = buscar_producto_txtfl_codstandard.getText();
        LocalDate entryDate = buscar_producto_dtpcker_ingreso.getValue();
        LocalDate expiryDate = buscar_producto_dtpcker_vence.getValue();
        int providerID = 0;
        if(!(buscar_producto_cmbox_prov.getValue() == null)) {
            providerID = productCrud.getProviderID(buscar_producto_cmbox_prov.getValue().toString()) + 1;
        }
        int cellarID = 0;
        if(!(buscar_producto_cmbox_bodega.getValue() == null)) {
            cellarID = productCrud.getCellarID(buscar_producto_cmbox_bodega.getValue().toString()) + 1;
        }

        return new Product(name, brand, cas, internalCode, standardCode, lot, entryDate, expiryDate, invoice, cellarID, providerID);
    }

    public void getProviderList() {
        for (int i = 0; i < productCrud.getProviderList().size(); i++) {
            providersList.add(productCrud.getProviderList().get(i).getName());
        }
    }

    private FXMLLoader fxmlLoader;
    private ProductCrud productCrud = new ProductCrud();
    private AnchorPane selectedPane;
    private ObservableList providersList = FXCollections.observableArrayList();
    private ObservableList cellarList = FXCollections.observableArrayList();
    private ListGenerator listGenerator = new ListGenerator();

    @FXML
    private TextField buscar_producto_txtfl_nombre;
    @FXML
    private TextField buscar_producto_txtfl_marca;
    @FXML
    private TextField buscar_producto_txtfl_lote;
    @FXML
    private TextField buscar_producto_txtfl_factura;
    @FXML
    private TextField buscar_producto_txtfl_cas;
    @FXML
    private TextField buscar_producto_txtfl_codinterno;
    @FXML
    private TextField buscar_producto_txtfl_codstandard;

    @FXML
    private ComboBox buscar_producto_cmbox_prov;
    @FXML
    private ComboBox buscar_producto_cmbox_bodega;

    @FXML
    private DatePicker buscar_producto_dtpcker_ingreso;
    @FXML
    private DatePicker buscar_producto_dtpcker_vence;

    @FXML
    private TableView<Product> buscar_producto_table_lista_productos;
    @FXML
    private TableColumn<Product, Integer> producto_cl_id;
    @FXML
    private TableColumn<Product, String> producto_cl_nombre;
    @FXML
    private TableColumn<Product, String> producto_cl_marca;
    @FXML
    private TableColumn<Product, String> producto_cl_bodega;
    @FXML
    private TableColumn<Product, String> producto_cl_cas;
    @FXML
    private TableColumn<Product, String> producto_cl_cod_interno;
    @FXML
    private TableColumn<Product, String> producto_cl_cod_stnd;
    @FXML
    private TableColumn<Product, String> producto_cl_lote;
    @FXML
    private TableColumn<Product, LocalDate> producto_cl_fingreso;
    @FXML
    private TableColumn<Product, LocalDate> producto_cl_fvencimiento;
    @FXML
    private TableColumn<Product, LocalDate> producto_cl_fabierto;
    @FXML
    private TableColumn<Product, String> producto_cl_presentacion;
    @FXML
    private TableColumn<Product, Double> producto_cl_stock;
    @FXML
    private TableColumn<Product, Double> producto_cl_costo;
    @FXML
    private TableColumn<Product, String> producto_cl_proveedor;
}
