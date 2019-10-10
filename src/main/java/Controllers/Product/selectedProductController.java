package Controllers.Product;

import Clases.Cruds.ProductCrud;
import Clases.Models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class selectedProductController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        producto_seleccionado_txtfl_cantidad.setText("0");
    }

    public void productData(Product product){
        selectedProduct = product;
        id = product.getId();
        name = product.getName();
        brand = product.getBrand();
        lot = product.getLot();
        CAS = product.getCas();
        internalCode = product.getInternalCode();
        standardCode = product.getStandardCode();
        invoice = product.getInvoiceNumber();
        cost = product.getCost();
        stock =  product.getStock();
        costPerUnit = product.getCostPerUnit();
        presentation = product.getPresentation();
        cellar = product.getCellar();
        ghs = product.getGhs();
        producto_seleccionado_txtfl_nombre.setText(name);
        producto_seleccionado_txtfl_marca.setText(brand);
        producto_seleccionado_txtfl_lote.setText(lot);
        producto_seleccionado_txtfl_cas.setText(CAS);
        producto_seleccionado_txtfl_codigo_interno.setText(internalCode);
        producto_seleccionado_txtfl_codigo_standard.setText(standardCode);
        producto_seleccionado_txtfl_numero_factura.setText(invoice);
        producto_seleccionado_txtfl_costo.setText(Double.toString(cost));
        producto_seleccionado_txtfl_stock.setText(Double.toString(stock));
        producto_seleccionado_txtfl_presentacion.setText(presentation);
        producto_seleccionado_txtfl_bodega.setText(cellar);
        checkProductPresentation();
        if (ghs != 0){
            selectedGHS();
        }
        consumptionMass = new ConsumptionMass(selectedProduct);
        consumptionVolume = new ConsumptionVolume(selectedProduct);
        producto_seleccionado_cbox_unidad_medida.setOnAction(event -> {
            consumeCost();
        });
        producto_seleccionado_txtfl_cantidad.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!producto_seleccionado_txtfl_cantidad.getText().isEmpty()){
                consumeCost();
            }else {
                producto_seleccionado_txtfl_cantidad.setText("0");
            }
        }));
    }


    public void consumeCost(){
        amount = Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
        unitConsume = producto_seleccionado_cbox_unidad_medida.getValue();
        if(presentationConfirmer.presentationType(selectedProduct).equals("mass")){
            System.out.println(unitConsume);
            producto_seleccionado_txtfl_costo_consumo.setText(Double.toString(consumptionMass.calculateConsumeCost(unitConsume, amount)));
            convertedAmount = consumptionMass.calculateRemainingStock(unitConsume,amount);
        }else if (presentationConfirmer.presentationType(selectedProduct).equals("volume")){
            producto_seleccionado_txtfl_costo_consumo.setText(Double.toString(consumptionVolume.calculateConsumeCost(unitConsume, amount)));
            convertedAmount = consumptionVolume.calculateRemainingStock(unitConsume,amount);
        }
    }

    public void consume(){
        double stockLeft = stock - convertedAmount;
        if(stockLeft > 0) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Consumo de producto");
            confirmacion.setHeaderText("Realizando un consumo");
            confirmacion.setContentText("Esta a punto de consumir " + amount + " " + presentation + " de " + name + " ¿desea continuar?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                productCrud.updateStock(id, stockLeft);
                Alert confirmacionConsumo = new Alert(Alert.AlertType.INFORMATION);
                confirmacionConsumo.setTitle("Consumo");
                confirmacionConsumo.setHeaderText("Consumo realizado");
                confirmacionConsumo.setContentText("Se ha efectuado el consumo, la cantidad restante de " + name + " en stock es de: " + stockLeft);
                confirmacionConsumo.show();
                cancel();
            } else {
                Alert confirmacionCancelar = new Alert(Alert.AlertType.INFORMATION);
                confirmacionCancelar.setTitle("Consumo");
                confirmacionCancelar.setHeaderText("Consumo cancelado");
                confirmacionCancelar.setContentText("Se ha cancelado el consumo");
                confirmacionCancelar.show();
            }
        }else {
            JOptionPane.showMessageDialog(null,"El consumo excede a la cantidad actual de producto.");
        }
    }

    public void cancel(){
        Stage stage = (Stage) btn_guardar_cancelar.getScene().getWindow();
        stage.close();
    }

    private void checkProductPresentation(){
        if(presentationConfirmer.presentationType(selectedProduct).equals("mass")){
            producto_seleccionado_cbox_unidad_medida.setItems(listGenerator.getPresentationListMass());
        }else if(presentationConfirmer.presentationType(selectedProduct).equals("volume")){
            producto_seleccionado_cbox_unidad_medida.setItems(listGenerator.getPresentationListVolume());
        }
    }

    private void selectedGHS(){
        int position = ghs + 1;
        Image ghsImage;
        switch (position){
            case 1:
                System.out.println(position);
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable1.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 2:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable2.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 3:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable3.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 4:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/inflamable4.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 5:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/irritacion_cutanea.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 6:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/peligro_para_el_medio_ambiente.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 7:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/peligro_por_aspiracion.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 8:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_comburentes.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 9:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_corrosivas_combustibles.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
            case 10:
                ghsImage = new Image(getClass().getResourceAsStream("/Imagenes/sustancias_toxicas.jpg"));
                producto_seleccionado_img_ghs.setImage(ghsImage);
                break;
        }
    }

    private int id;
    private String name;
    private String brand;
    private String lot;
    private String CAS;
    private String internalCode;
    private String standardCode;
    private String invoice;
    private double cost = 0;
    private double stock = 0;
    private double costPerUnit = 0;
    private String presentation;
    private String cellar;
    private int ghs;
    private ProductCrud productCrud =  new ProductCrud();
    private  double amount;
    private Product selectedProduct;
    private Presentation presentationConfirmer = new Presentation();
    private ListGenerator listGenerator = new ListGenerator();
    private ConsumptionMass consumptionMass;
    private ConsumptionVolume consumptionVolume;
    private String unitConsume;
    private double convertedAmount;

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
    @FXML private ComboBox<String> producto_seleccionado_cbox_unidad_medida;
    @FXML private ImageView producto_seleccionado_img_ghs;
    @FXML private Button btn_guardar_cancelar;
}
