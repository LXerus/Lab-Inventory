package Controladores.Productos;

import Clases.Cruds.Productos_Crud;
import Clases.Modelos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductoSeleccionado_Controlador implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        producto_seleccionado_cbox_unidad_medida.setItems(unidadDeMedida);
        producto_seleccionado_txtfl_cantidad.setText("0");
        producto_seleccionado_txtfl_cantidad.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!producto_seleccionado_txtfl_cantidad.getText().isEmpty()){
                costoDelConsumo();
            }else {
                producto_seleccionado_txtfl_cantidad.setText("0");
            }
        }));
    }

    public void datosDelProducto(Producto producto){
        this.productoSeleccionado = producto;
        id = producto.getId();
        nombre = producto.getNombre();
        marca = producto.getMarca();
        lote = producto.getLote();
        CAS = producto.getCas();
        codigoInterno = producto.getCodigo_interno();
        codigoStandard = producto.getCodigo_standard();
        factura = producto.getNumero_de_factura();
        costo = producto.getCosto();
        stock =  producto.getStock();
        costoXUnidad = producto.getCostoXUnidad();
        presentacion = producto.getPresentacion();
        bodega = producto.getBodega();
        unidadDeMedida.addAll("mg", "g", "kg");

        producto_seleccionado_txtfl_nombre.setText(nombre);
        producto_seleccionado_txtfl_marca.setText(marca);
        producto_seleccionado_txtfl_lote.setText(lote);
        producto_seleccionado_txtfl_cas.setText(CAS);
        producto_seleccionado_txtfl_codigo_interno.setText(codigoInterno);
        producto_seleccionado_txtfl_codigo_standard.setText(codigoStandard);
        producto_seleccionado_txtfl_numero_factura.setText(factura);
        producto_seleccionado_txtfl_costo.setText(Double.toString(costo));
        producto_seleccionado_txtfl_stock.setText(Double.toString(stock));
        producto_seleccionado_txtfl_presentacion.setText(presentacion);
        producto_seleccionado_txtfl_bodega.setText(bodega);
    }


    public void costoDelConsumo(){
        cantidad = Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
        producto_seleccionado_txtfl_costo_consumo.setText(Double.toString(costoXUnidad * cantidad));
    }

    public void efectuarConsumo(){
        cantidad = Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Consumo de producto");
        confirmacion.setHeaderText("Realizando un consumo");
        confirmacion.setContentText("Esta a punto de consumir "+cantidad+" "+presentacion+" de "+nombre+" ¿desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if(resultado.get() == ButtonType.OK) {
            double consumo = stock - Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
            productosCrud.actualizarStock(id, consumo);
            Alert confirmacionConsumo = new Alert(Alert.AlertType.INFORMATION);
            confirmacionConsumo.setTitle("Consumo");
            confirmacionConsumo.setHeaderText("Consumo realizado");
            confirmacionConsumo.setContentText("Se ha efectuado el consumo, la cantidad restante de "+nombre+" en stock es de: "+consumo);
            confirmacionConsumo.show();
            cancelar();
        }else{
            Alert confirmacionCancelar = new Alert(Alert.AlertType.INFORMATION);
            confirmacionCancelar.setTitle("Consumo");
            confirmacionCancelar.setHeaderText("Consumo cancelado");
            confirmacionCancelar.setContentText("Se ha cancelado el consumo");
            confirmacionCancelar.show();
        }
    }

    public void cancelar(){
        Stage stage = (Stage) btn_guardar_cancelar.getScene().getWindow();
        stage.close();
    }

    private int id;
    private String nombre;
    private String marca;
    private String lote;
    private String CAS;
    private String codigoInterno;
    private String codigoStandard;
    private String factura;
    private double costo = 0;
    private double stock = 0;
    private double costoXUnidad = 0;
    private String presentacion;
    private String bodega;
    private Productos_Crud productosCrud =  new Productos_Crud();
    private ObservableList<String> unidadDeMedida = FXCollections.observableArrayList();
    private  double cantidad;
    private Producto productoSeleccionado;


    @FXML private TextField producto_seleccionado_txtfl_nombre;
    @FXML private TextField producto_seleccionado_txtfl_marca;
    @FXML private TextField producto_seleccionado_txtfl_lote;
    @FXML private TextField producto_seleccionado_txtfl_cas;
    @FXML private TextField producto_seleccionado_txtfl_codigo_interno;
    @FXML private TextField producto_seleccionado_txtfl_codigo_standard;
    @FXML private TextField producto_seleccionado_txtfl_numero_factura;
    @FXML private TextField producto_seleccionado_txtfl_costo;
    @FXML private TextField producto_seleccionado_txtfl_stock;
    @FXML private TextField producto_seleccionado_txtfl_presentacion;
    @FXML private TextField producto_seleccionado_txtfl_bodega;
    @FXML private TextField producto_seleccionado_txtfl_cantidad;
    @FXML private TextField producto_seleccionado_txtfl_costo_consumo;
    @FXML private ComboBox producto_seleccionado_cbox_unidad_medida;
    @FXML private ImageView producto_seleccionado_img_ghs;
    @FXML private Button btn_guardar_cancelar;
}
