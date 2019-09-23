package Controladores.Configuracion;

import Clases.Cruds.ProductTypeCrud;
import Clases.Modelos.ProductType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateProductTypeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deleteProductType(){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Eliminar "+ productType);
        confirmation.setHeaderText("Eliminar elemento.");
        confirmation.setContentText("¿Desea continuar?");
        Optional<ButtonType> resultado = confirmation.showAndWait();
        if(resultado.get() == ButtonType.OK) {
            productTypeCrud.delete(new ProductType(id, productType, description));
            cancel();
            JOptionPane.showMessageDialog(null, "El elemento '"+ productType +"' ha sido eliminado correctamente.");
        }else{
            JOptionPane.showMessageDialog(null,"No se han efectuado cambios.");
        }
    }

    public void updateProductType(){
        if(verifyData()){
            productTypeCrud.update(new ProductType(productType, description));
            cancel();
            JOptionPane.showMessageDialog(null, "Los datos han sido actualizados correctamente.");
        }else {
            JOptionPane.showMessageDialog(null,"Todos los campos son necesarios para actualizar.");
        }
    }

    public void getProductTypeData(ProductType productType){
        id = productType.getId();
        this.productType = productType.getProductType();
        description = productType.getDescription();

        modificar_tipo_txtfl_id.setText(Integer.toString(id));
        modificar_tipo_txtfl_tipoproducto.setText(this.productType);
        midificar_tipo_txtarea_descripcion.setText(description);
    }

    public void cancel(){
        Stage stage = (Stage) modificar_tipo_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verifyData(){
        //Confirma que los datos necesarios sean introducidos para actualizar
        boolean valid = true;
        if(modificar_tipo_txtfl_id.getText().isEmpty()){
            valid = false;
        }
        if (modificar_tipo_txtfl_tipoproducto.getText().isEmpty()){
            valid = false;
        }else{
            productType =  modificar_tipo_txtfl_tipoproducto.getText();
        }
        if (midificar_tipo_txtarea_descripcion.getText().isEmpty()){
            valid = false;
        }else{
            description = midificar_tipo_txtarea_descripcion.getText();
        }
        return valid;
    }

    @FXML private TextField modificar_tipo_txtfl_id;
    @FXML private TextField modificar_tipo_txtfl_tipoproducto;
    @FXML private TextArea midificar_tipo_txtarea_descripcion;
    @FXML private Button modificar_tipo_btn_cancelar;
    private int id;
    private String productType;
    private String description;
    private ProductTypeCrud productTypeCrud = new ProductTypeCrud();
}
