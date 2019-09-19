package Controladores.Productos;

import Clases.Cruds.ProductCrud;
import Clases.Modelos.Product;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
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

public class BuscarProducto_Controlador implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generarListaProveedores();
        buscar_producto_cmbox_prov.setItems(listaDeProveedores);
        buscar_producto_cmbox_bodega.setItems(productosCrud.getCellarList());
    }

    public void cancelar() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_productos_gui.fxml"));
        panel_seleccionado = fxmlLoader.load();
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_seleccionado);
    }

    public void buscarProducto(){
        buscar_producto_table_lista_productos.setItems(productosCrud.read(datosIntroducidos()));
        producto_cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        producto_cl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        producto_cl_marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        producto_cl_bodega.setCellValueFactory(new PropertyValueFactory<>("idBodega"));
        producto_cl_cas.setCellValueFactory(new PropertyValueFactory<>("cas"));
        producto_cl_cod_interno.setCellValueFactory(new PropertyValueFactory<>("codigo_interno"));
        producto_cl_cod_stnd.setCellValueFactory(new PropertyValueFactory<>("codigo_standard"));
        producto_cl_lote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        producto_cl_fingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_de_ingreso"));
        producto_cl_fvencimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_de_vencimiento"));
        producto_cl_fabierto.setCellValueFactory(new PropertyValueFactory<>("fecha_abierto"));
        producto_cl_presentacion.setCellValueFactory(new PropertyValueFactory<>("idPresentacion"));
        producto_cl_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        producto_cl_costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        producto_cl_proveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        buscar_producto_table_lista_productos.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Formularios/Modificacion/Productos/producto_seleccionado_gui.fxml"));
                try{
                    loader.load();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                ProductoSeleccionado_Controlador productoSeleccionadoControlador = loader.getController();
                productoSeleccionadoControlador.datosDelProducto(buscar_producto_table_lista_productos.getSelectionModel().getSelectedItem());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
                stage.show();
            }
        });
    }

    public Product datosIntroducidos(){
        String nombre = buscar_producto_txtfl_nombre.getText();
        String marca = buscar_producto_txtfl_marca.getText();
        String lote = buscar_producto_txtfl_lote.getText();
        String factura = buscar_producto_txtfl_factura.getText();
        String cas = buscar_producto_txtfl_cas.getText();
        String codigoInterno = buscar_producto_txtfl_codinterno.getText();
        String codigoStandard = buscar_producto_txtfl_codstandard.getText();
        LocalDate fechaIngreso = buscar_producto_dtpcker_ingreso.getValue();
        LocalDate fechaVencimiento = buscar_producto_dtpcker_vence.getValue();
        int idProveedor = 0;
        if(!(buscar_producto_cmbox_prov.getValue() == null)) {
            idProveedor = productosCrud.getProviderID(buscar_producto_cmbox_prov.getValue().toString()) + 1;
        }
        int idBodega = 0;
        if(!(buscar_producto_cmbox_bodega.getValue() == null)) {
            idBodega = productosCrud.getCellarID(buscar_producto_cmbox_bodega.getValue().toString()) + 1;
        }

        return new Product(nombre, marca, cas, codigoInterno, codigoStandard, lote, fechaIngreso, fechaVencimiento, factura, idBodega, idProveedor);
    }

    public void generarListaProveedores() {
        for (int i = 0; i < productosCrud.getProviderList().size(); i++) {
            listaDeProveedores.add(productosCrud.getProviderList().get(i).getName());
        }
    }

    private FXMLLoader fxmlLoader;
    private ProductCrud productosCrud = new ProductCrud();
    private AnchorPane panel_seleccionado;
    private ObservableList listaDeProveedores = FXCollections.observableArrayList();
    private ObservableList listaDeBodegas = FXCollections.observableArrayList();

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
