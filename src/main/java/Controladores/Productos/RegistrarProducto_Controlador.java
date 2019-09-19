package Controladores.Productos;

import Clases.Cruds.ProductCrud;
import Clases.Modelos.Product;
import Controladores.MenuPrincipal.MenuPrincipal_Controlador;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarProducto_Controlador implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle) {
        generarListas();
        productoControladoStatus();
        registrar_producto_cmbox_presentacion.setItems(lista_presentaciones);
        registrar_producto_cmbox_bodega.setItems(productosCrud.getCellarList());
        registrar_producto_cmbox_tipo_producto.setItems(productosCrud.getProductList());
        registrar_producto_cmbox_registro.setItems(productosCrud.getRegistryNumbers());
        registrar_producto_cmbox_proveedor.setItems(proveedores_nombres);

        registrar_producto_txtfl_codigoproveedor.setEditable(false);
        registrar_producto_cmbox_proveedor.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                obtenerCodigoProveedores();
            }
        });

        registrar_producto_cbox_prod_controlado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productoControladoStatus();
            }
        });

        registrar_producto_cmbox_ghs.setItems(llenarListaGHS());
    }



    public void cancelar() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_productos_gui.fxml"));
        panel_menu_productos = fxmlLoader.load();
        MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_productos);
    }

    public void registrarProducto(){
        presentacion = registrar_producto_cmbox_presentacion.getValue().toString();
        registro = registrar_producto_cmbox_registro.getValue().toString();
        tipo_de_producto = registrar_producto_cmbox_tipo_producto.getValue().toString();
        bodega = registrar_producto_cmbox_bodega.getValue().toString();
        proveedor = registrar_producto_cmbox_proveedor.getValue().toString();
        codigo_de_proveedor = registrar_producto_txtfl_codigo_standard.getText();
        fecha_de_factura = registrar_producto_dtpicker_fecha_factura.getValue();
        fecha_de_ingreso = registrar_producto_dtpicker_fecha_ingreso.getValue();
        fecha_abierto = registrar_producto_dtpicker_fecha_abierto.getValue();
        fecha_de_vencimiento = registrar_producto_dtpicker_fecha_vencimiento.getValue();

        //Los nombres de los siguientes campos son unicos, obtiene los ID de los tales.
        idProveedor = productosCrud.getProviderID(proveedor);
        idBodega = productosCrud.getCellarID(bodega);
        idTipoProdcuto = productosCrud.getProductTypeID(tipo_de_producto);
        idPresentacion = productosCrud.getPresentationID(presentacion);
        idRegistro = productosCrud.getRegistryID(registro);


        if(validarDatos()){
            costoXUnidad = (costo/stock);
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar Producto");
            confirmacion.setHeaderText("Registrando un nuevo producto");
            confirmacion.setContentText("Esta a punto de registrar a un nuevo producto ¿desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                product = new Product(nombre, marca, cas, codigo_interno, codigo_standard, lote, fecha_de_ingreso,
                                        fecha_de_vencimiento, fecha_abierto,fecha_de_factura, numero_de_factura, stock,
                                        costo, producto_controlado, ghs, idBodega, idProveedor, idTipoProdcuto, idPresentacion,idRegistro);
                productosCrud.create(product);
                JOptionPane.showMessageDialog(null,"¡El usuario se ha registrado exitosamente!");

                try {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/menu_productos_gui.fxml"));
                    panel_menu_productos = fxmlLoader.load();
                    MenuPrincipal_Controlador.obntenerPanelPadre().setCenter(panel_menu_productos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 }else{
                JOptionPane.showMessageDialog(null, "No se han efectuado cambios en la base de datos.");
                 }
        }else{
            JOptionPane.showMessageDialog(null,"Por favor ingresar la informacion en todos los campos.");
        }
    }

    public void ghsSeleccionado(){
        int posicion = registrar_producto_cmbox_ghs.getSelectionModel().getSelectedIndex();
        Image imagenGHS;
        switch (posicion){
            case 0:
                System.out.println(posicion);
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/inflamable1.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 1:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/inflamable2.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 2:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/inflamable3.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 3:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/inflamable4.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 4:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/irritacion_cutanea.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 5:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/peligro_para_el_medio_ambiente.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 6:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/peligro_por_aspiracion.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 7:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_comburentes.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 8:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_corrosivas_combustibles.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
            case 9:
                imagenGHS = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_toxicas.jpg"));
                registrar_producto_img_ghs.setImage(imagenGHS);
                break;
        }
    }

    public void productoControladoStatus(){
        if(registrar_producto_cbox_prod_controlado.isSelected()){
            producto_controlado = true;
            registrar_producto_cmbox_ghs.setDisable(false);
        }else {
            producto_controlado = false;
            registrar_producto_cmbox_ghs.setDisable(true);
        }
    }

    public void obtenerCodigoProveedores(){
        String proveedor = registrar_producto_cmbox_proveedor.getValue().toString();
        for(int i = 0; i < productosCrud.getProviderList().size(); i++){
            if(proveedor.contains(productosCrud.getProviderList().get(i).getName())){
                registrar_producto_txtfl_codigoproveedor.setText(productosCrud.getProviderList().get(i).getProviderCode());
                idProveedor = productosCrud.getProviderList().get(i).getId();
            }
        }
    }

    public void generarListas(){
        for(int i = 0; i < productosCrud.getProviderList().size(); i++){
            proveedores_nombres.add(productosCrud.getProviderList().get(i).getName());
            proveedores_codigos.add(productosCrud.getProviderList().get(i).getProviderCode());
        }
        for(int j = 0; j<productosCrud.getPresentationList().size(); j++){
            lista_presentaciones.add(productosCrud.getPresentationList().get(j).getPresentation());
        }
    }

    public ObservableList llenarListaGHS(){
        listaGHS.addAll("Líquidos y aerosoles inflamables",
                "Sustancias susceptibles de combustión espontánea",
                "Sustancias que forman gases inflamables en contacto con el agua",
                "Sólidos inflamabeles",
                "Sustancias comburrentes",
                "Sustancias tóxicas agudas combustibles",
                "Sustancias corrosivas combustibles",
                "Peligro por aspiración",
                "Peligro para el medio ambiente",
                "Irritación cutanea",
                "Sustancias no etiquetadas");
        return listaGHS;
    }

    public boolean validarDatos(){
        boolean datosValidos = true;

        if(registrar_producto_dtpicker_fecha_abierto.getValue() == null){
            datosValidos = false;
        }else {
            fecha_abierto = registrar_producto_dtpicker_fecha_abierto.getValue();
        }

        if(registrar_producto_dtpicker_fecha_vencimiento.getValue() == null){
            datosValidos = false;
        }else{
            fecha_de_vencimiento = registrar_producto_dtpicker_fecha_vencimiento.getValue();
        }

        if (registrar_producto_dtpicker_fecha_factura.getValue() == null){
            datosValidos = false;
        }else {
            fecha_de_factura = registrar_producto_dtpicker_fecha_factura.getValue();
        }

        if (registrar_producto_dtpicker_fecha_ingreso.getValue() == null){
            datosValidos = false;
        }else{
            fecha_de_ingreso = registrar_producto_dtpicker_fecha_ingreso.getValue();
        }

        if(registrar_producto_txtfl_nombre.getText().isEmpty()){
            datosValidos = false;
        }else{
            nombre = registrar_producto_txtfl_nombre.getText();
        }

        if(registrar_producto_txtfl_marca.getText().isEmpty()){
            datosValidos = false;
        }else{
            marca = registrar_producto_txtfl_marca.getText();
        }

        if(registrar_producto_txtfl_cas.getText().isEmpty()){
            datosValidos = false;
        }else{
            cas = registrar_producto_txtfl_cas.getText();
        }

        if(registrar_producto_txtfl_codigo_interno.getText().isEmpty()){
            datosValidos = false;
        }else {
            codigo_interno = registrar_producto_txtfl_codigo_interno.getText();
        }

        if(registrar_producto_txtfl_codigo_standard.getText().isEmpty()){
            datosValidos = false;
        }else {
            codigo_standard = registrar_producto_txtfl_codigo_standard.getText();
        }

        if(registrar_producto_txtfl_lote.getText().isEmpty()){
            datosValidos = false;
        }else {
            lote = registrar_producto_txtfl_lote.getText();
        }

        if(registrar_producto_txtfl_stock.getText().isEmpty()){
            datosValidos = false;
        }else {
            stock = Integer.parseInt(registrar_producto_txtfl_stock.getText());
        }

        if(registrar_producto_txtfl_numero_factura.getText().isEmpty()){
            datosValidos = false;
        }else {
            numero_de_factura =  registrar_producto_txtfl_numero_factura.getText();
        }

        if(registrar_producto_txtfl_costo.getText().isEmpty()){
            datosValidos = false;
        }else {
             costo = Double.parseDouble(registrar_producto_txtfl_costo.getText());
        }
        if(registrar_producto_cmbox_presentacion.getValue() == null){
            datosValidos = false;
        }else{
            presentacion =registrar_producto_cmbox_presentacion.getValue().toString();
        }
        if(registrar_producto_cmbox_bodega.getValue() == null){
            datosValidos = false;
        }else{
            bodega = registrar_producto_cmbox_bodega.getValue().toString();
        }
        if(registrar_producto_cmbox_tipo_producto.getValue() == null){
            datosValidos = false;
        }else {
            tipo_de_producto = registrar_producto_cmbox_tipo_producto.getValue().toString();
        }
        if(registrar_producto_cmbox_registro.getValue() == null){
            datosValidos = false;
        }else{
            registro = registrar_producto_cmbox_registro.getValue().toString();
        }
        if(registrar_producto_cmbox_proveedor.getValue() == null){
            datosValidos = false;
        }else {
            proveedor = registrar_producto_cmbox_proveedor.getValue().toString();
        }

        return datosValidos;
    }

    private FXMLLoader fxmlLoader;
    private AnchorPane panel_menu_productos;
    private ProductCrud productosCrud = new ProductCrud();
    private ObservableList proveedores_nombres = FXCollections.observableArrayList();
    private ObservableList proveedores_codigos = FXCollections.observableArrayList();
    private ObservableList lista_presentaciones  = FXCollections.observableArrayList();
    private ObservableList<String> listaGHS = FXCollections.observableArrayList();
    private Product product;

    private String nombre;
    private String marca;
    private String cas;
    private String codigo_interno;
    private String codigo_standard;
    private String lote;
    private String presentacion;
    private String numero_de_factura;
    private double costo;
    private double stock;
    private double costoXUnidad;
    private String tipo_de_producto;
    private String registro;
    private String bodega;
    private String proveedor;
    private String codigo_de_proveedor;
    private LocalDate fecha_de_factura;
    private LocalDate fecha_de_ingreso;
    private LocalDate fecha_de_vencimiento;
    private LocalDate fecha_abierto;
    private boolean producto_controlado = false;
    private int ghs;
    private int idProveedor;
    private int idBodega;
    private int idTipoProdcuto;
    private int idPresentacion;
    private int idRegistro;
    private LocalDate fechaDeHoy = LocalDate.now();

    //TextFields
    @FXML
    private TextField registrar_producto_txtfl_nombre;
    @FXML
    private TextField registrar_producto_txtfl_marca;
    @FXML
    private TextField registrar_producto_txtfl_cas;
    @FXML
    private TextField registrar_producto_txtfl_codigo_interno;
    @FXML
    private TextField registrar_producto_txtfl_codigo_standard;
    @FXML
    private TextField registrar_producto_txtfl_lote;
    @FXML
    private TextField registrar_producto_txtfl_stock;
    @FXML
    private TextField registrar_producto_txtfl_numero_factura;
    @FXML
    private TextField registrar_producto_txtfl_costo;
    @FXML
    private TextField registrar_producto_txtfl_codigoproveedor;

    //ComboBoxes
    @FXML
    private ComboBox registrar_producto_cmbox_presentacion;
    @FXML
    private ComboBox registrar_producto_cmbox_registro;
    @FXML
    private ComboBox registrar_producto_cmbox_tipo_producto;
    @FXML
    private ComboBox registrar_producto_cmbox_bodega;
    @FXML
    private ComboBox registrar_producto_cmbox_proveedor;
    @FXML
    private ComboBox registrar_producto_cmbox_ghs;

    @FXML private CheckBox registrar_producto_cbox_prod_controlado;

    @FXML private ImageView registrar_producto_img_ghs;

    //DatePickers
    @FXML
    private DatePicker registrar_producto_dtpicker_fecha_factura;
    @FXML
    private DatePicker registrar_producto_dtpicker_fecha_ingreso;
    @FXML
    private DatePicker registrar_producto_dtpicker_fecha_vencimiento;
    @FXML
    private DatePicker registrar_producto_dtpicker_fecha_abierto;

}