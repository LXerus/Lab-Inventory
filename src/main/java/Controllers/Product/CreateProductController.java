package Controllers.Product;

import Clases.Cruds.ProductCrud;
import Clases.Models.ListGenerator;
import Clases.Models.Product;
import Controllers.MainMenu.MainMenuController;
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

public class CreateProductController implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateLists();
        controledProductStatus();
        selectPresentation();
        registrar_producto_cmbox_bodega.setItems(productosCrud.getCellarList());
        registrar_producto_cmbox_tipo_producto.setItems(productosCrud.getProductList());
        registrar_producto_cmbox_registro.setItems(productosCrud.getRegistryNumbers());
        registrar_producto_cmbox_proveedor.setItems(proveedores_nombres);

        registrar_producto_txtfl_codigoproveedor.setEditable(false);
        registrar_producto_cmbox_proveedor.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                getProvderCode();
            }
        });

        registrar_producto_cbox_prod_controlado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controledProductStatus();
            }
        });

        registrar_producto_cmbox_ghs.setItems(fillGHSList());
    }

    private void selectPresentation(){
        registrar_producto_rbtn_masa.setToggleGroup(measurementUnits);
        registrar_producto_rbtn_masa.setSelected(true);
        registrar_producto_rbtn_volumen.setToggleGroup(measurementUnits);
        registrar_producto_rbtn_masa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registrar_producto_cmbox_presentacion.setItems(listGenerator.getPresentationListMass());
            }
        });
        registrar_producto_rbtn_volumen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registrar_producto_cmbox_presentacion.setItems(listGenerator.getPresentationListVolume());
            }
        });
        if(registrar_producto_rbtn_masa.isSelected()){
            registrar_producto_cmbox_presentacion.setItems(listGenerator.getPresentationListMass());
        }else if(registrar_producto_rbtn_volumen.isSelected()){
            registrar_producto_cmbox_presentacion.setItems(listGenerator.getPresentationListVolume());
        }
    }



    public void cancel() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/product_menu_gui.fxml"));
        panel_menu_productos = fxmlLoader.load();
        MainMenuController.getParentPane().setCenter(panel_menu_productos);
    }

    public void createProduct(){
        presentation = registrar_producto_cmbox_presentacion.getValue().toString();
        registry = registrar_producto_cmbox_registro.getValue().toString();
        productType = registrar_producto_cmbox_tipo_producto.getValue().toString();
        cellar = registrar_producto_cmbox_bodega.getValue().toString();
        provider = registrar_producto_cmbox_proveedor.getValue().toString();
        providerCode = registrar_producto_txtfl_codigo_standard.getText();
        invoiceDate = registrar_producto_dtpicker_fecha_factura.getValue();
        entryDate = registrar_producto_dtpicker_fecha_ingreso.getValue();
        openedDate = registrar_producto_dtpicker_fecha_abierto.getValue();
        expiryDate = registrar_producto_dtpicker_fecha_vencimiento.getValue();

        //Los nombres de los siguientes campos son unicos, obtiene los ID de los tales.
        providerID = productosCrud.getProviderID(provider);
        cellarID = productosCrud.getCellarID(cellar);
        productTypeID = productosCrud.getProductTypeID(productType);
        registryID = productosCrud.getRegistryID(registry);


        if(verifyData()){
            costPerUnit = (cost /stock);
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Registrar Producto");
            confirmacion.setHeaderText("Registrando un nuevo producto");
            confirmacion.setContentText("Esta a punto de registrar a un nuevo producto ¿desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK) {
                product = new Product(name, brand, cas, internalCode, standardCode, lot, entryDate, expiryDate, invoiceDate, openedDate, invoiceNumber, stock, cost,
                        costPerUnit, controlledProduct, ghs, presentation, cellarID, providerID, productTypeID, registryID);
                productosCrud.create(product);
                JOptionPane.showMessageDialog(null,"¡El usuario se ha registrado exitosamente!");

                try {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Menus/product_menu_gui.fxml"));
                    panel_menu_productos = fxmlLoader.load();
                    MainMenuController.getParentPane().setCenter(panel_menu_productos);
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

    public void selectedGHS(){
        int position = registrar_producto_cmbox_ghs.getSelectionModel().getSelectedIndex() + 1;
        Image ghsImage;
        switch (position){
            case 1:
                System.out.println(position);
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable1.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 2:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable2.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 3:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable3.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 4:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable4.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 5:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/irritacion_cutanea.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 6:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/peligro_para_el_medio_ambiente.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 7:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/peligro_por_aspiracion.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 8:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_comburentes.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 9:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_corrosivas_combustibles.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
            case 10:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_toxicas.jpg"));
                registrar_producto_img_ghs.setImage(ghsImage);
                break;
        }
    }

    public void controledProductStatus(){
        if(registrar_producto_cbox_prod_controlado.isSelected()){
            controlledProduct = true;
            registrar_producto_cmbox_ghs.setDisable(false);
        }else {
            controlledProduct = false;
            registrar_producto_cmbox_ghs.setDisable(true);
        }
    }

    public void getProvderCode(){
        String proveedor = registrar_producto_cmbox_proveedor.getValue().toString();
        for(int i = 0; i < productosCrud.getProviderList().size(); i++){
            if(proveedor.contains(productosCrud.getProviderList().get(i).getName())){
                registrar_producto_txtfl_codigoproveedor.setText(productosCrud.getProviderList().get(i).getProviderCode());
                providerID = productosCrud.getProviderList().get(i).getId();
            }
        }
    }

    public void generateLists(){
        for(int i = 0; i < productosCrud.getProviderList().size(); i++){
            proveedores_nombres.add(productosCrud.getProviderList().get(i).getName());
            proveedores_codigos.add(productosCrud.getProviderList().get(i).getProviderCode());
        }
    }

    public ObservableList fillGHSList(){
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

    public boolean verifyData(){
        boolean valid = true;

        if(registrar_producto_dtpicker_fecha_abierto.getValue() == null){
            valid = false;
        }else {
            openedDate = registrar_producto_dtpicker_fecha_abierto.getValue();
        }

        if(registrar_producto_dtpicker_fecha_vencimiento.getValue() == null){
            valid = false;
        }else{
            expiryDate = registrar_producto_dtpicker_fecha_vencimiento.getValue();
        }

        if (registrar_producto_dtpicker_fecha_factura.getValue() == null){
            valid = false;
        }else {
            invoiceDate = registrar_producto_dtpicker_fecha_factura.getValue();
        }

        if (registrar_producto_dtpicker_fecha_ingreso.getValue() == null){
            valid = false;
        }else{
            entryDate = registrar_producto_dtpicker_fecha_ingreso.getValue();
        }

        if(registrar_producto_txtfl_nombre.getText().isEmpty()){
            valid = false;
        }else{
            name = registrar_producto_txtfl_nombre.getText();
        }

        if(registrar_producto_txtfl_marca.getText().isEmpty()){
            valid = false;
        }else{
            brand = registrar_producto_txtfl_marca.getText();
        }

        if(registrar_producto_txtfl_cas.getText().isEmpty()){
            valid = false;
        }else{
            cas = registrar_producto_txtfl_cas.getText();
        }

        if(registrar_producto_txtfl_codigo_interno.getText().isEmpty()){
            valid = false;
        }else {
            internalCode = registrar_producto_txtfl_codigo_interno.getText();
        }

        if(registrar_producto_txtfl_codigo_standard.getText().isEmpty()){
            valid = false;
        }else {
            standardCode = registrar_producto_txtfl_codigo_standard.getText();
        }

        if(registrar_producto_txtfl_lote.getText().isEmpty()){
            valid = false;
        }else {
            lot = registrar_producto_txtfl_lote.getText();
        }

        if(registrar_producto_txtfl_stock.getText().isEmpty()){
            valid = false;
        }else {
            stock = Integer.parseInt(registrar_producto_txtfl_stock.getText());
        }

        if(registrar_producto_txtfl_numero_factura.getText().isEmpty()){
            valid = false;
        }else {
            invoiceNumber =  registrar_producto_txtfl_numero_factura.getText();
        }

        if(registrar_producto_txtfl_costo.getText().isEmpty()){
            valid = false;
        }else {
             cost = Double.parseDouble(registrar_producto_txtfl_costo.getText());
        }
        if(registrar_producto_cmbox_presentacion.getValue() == null){
            valid = false;
        }else{
            presentation =registrar_producto_cmbox_presentacion.getValue().toString();
        }
        if(registrar_producto_cmbox_bodega.getValue() == null){
            valid = false;
        }else{
            cellar = registrar_producto_cmbox_bodega.getValue().toString();
        }
        if(registrar_producto_cmbox_tipo_producto.getValue() == null){
            valid = false;
        }else {
            productType = registrar_producto_cmbox_tipo_producto.getValue().toString();
        }
        if(registrar_producto_cmbox_registro.getValue() == null){
            valid = false;
        }else{
            registry = registrar_producto_cmbox_registro.getValue().toString();
        }
        if(registrar_producto_cmbox_proveedor.getValue() == null){
            valid = false;
        }else {
            provider = registrar_producto_cmbox_proveedor.getValue().toString();
        }

        return valid;
    }

    private FXMLLoader fxmlLoader;
    private AnchorPane panel_menu_productos;
    private ProductCrud productosCrud = new ProductCrud();
    private ObservableList proveedores_nombres = FXCollections.observableArrayList();
    private ObservableList proveedores_codigos = FXCollections.observableArrayList();
    private ObservableList lista_presentaciones  = FXCollections.observableArrayList();
    private ObservableList<String> listaGHS = FXCollections.observableArrayList();
    private Product product;
    private ToggleGroup measurementUnits = new ToggleGroup();
    private ListGenerator listGenerator = new ListGenerator();

    private String name;
    private String brand;
    private String cas;
    private String internalCode;
    private String standardCode;
    private String lot;
    private String invoiceNumber;
    private double cost;
    private double stock;
    private double costPerUnit;
    private String productType;
    private String registry;
    private String cellar;
    private String provider;
    private String providerCode;
    private LocalDate invoiceDate;
    private LocalDate entryDate;
    private LocalDate expiryDate;
    private LocalDate openedDate;
    private boolean controlledProduct = false;
    private int ghs = 0;
    private String presentation;
    private int providerID;
    private int cellarID;
    private int productTypeID;
    private int registryID;
    private LocalDate todaysDate = LocalDate.now();

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
    @FXML
    private RadioButton registrar_producto_rbtn_masa;
    @FXML
    private RadioButton registrar_producto_rbtn_volumen;

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