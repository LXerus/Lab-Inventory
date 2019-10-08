package Controllers.Product;

import Clases.Cruds.ProductCrud;
import Clases.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class selectedProductController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkProductPresentation();
        if (ghs != 0){
            selectedGHS();
        }
        producto_seleccionado_txtfl_cantidad.setText("0");
        producto_seleccionado_txtfl_cantidad.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!producto_seleccionado_txtfl_cantidad.getText().isEmpty()){
                consumeCost();
            }else {
                producto_seleccionado_txtfl_cantidad.setText("0");
            }
        }));
    }

    public void productData(Product product){
        this.selectedProduct = product;
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
    }


    public void consumeCost(){
        amount = Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
        if(presentationConfirmer.presentationType(selectedProduct).equals("mass")){
            producto_seleccionado_txtfl_costo_consumo.setText(Double.toString(consumptionMass.calculateConsumeCost(producto_seleccionado_cbox_unidad_medida.getValue().toString(), amount)));
        }else if (presentationConfirmer.presentationType(selectedProduct).equals("volume")){
            producto_seleccionado_txtfl_costo_consumo.setText(Double.toString(consumptionVolume.calculateConsumeCost(producto_seleccionado_cbox_unidad_medida.getValue().toString(), amount)));
        }
    }

    public void consume(){
        amount = Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Consumo de producto");
        confirmacion.setHeaderText("Realizando un consumo");
        confirmacion.setContentText("Esta a punto de consumir "+ amount +" "+ presentation +" de "+ name +" ¿desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if(resultado.get() == ButtonType.OK) {
            double stockLeft = stock - Double.parseDouble(producto_seleccionado_txtfl_cantidad.getText());
            productCrud.updateStock(id, stockLeft);
            Alert confirmacionConsumo = new Alert(Alert.AlertType.INFORMATION);
            confirmacionConsumo.setTitle("Consumo");
            confirmacionConsumo.setHeaderText("Consumo realizado");
            confirmacionConsumo.setContentText("Se ha efectuado el consumo, la cantidad restante de "+ name +" en stock es de: "+stockLeft);
            confirmacionConsumo.show();
            cancel();
        }else{
            Alert confirmacionCancelar = new Alert(Alert.AlertType.INFORMATION);
            confirmacionCancelar.setTitle("Consumo");
            confirmacionCancelar.setHeaderText("Consumo cancelado");
            confirmacionCancelar.setContentText("Se ha cancelado el consumo");
            confirmacionCancelar.show();
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

    public void selectedGHS(){
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
    private ConsumptionMass consumptionMass = new ConsumptionMass(selectedProduct);
    private ConsumptionVolume consumptionVolume = new ConsumptionVolume(selectedProduct);

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
