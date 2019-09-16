package Controladores.Configuracion;

import Clases.Cruds.TipoDeProductos_Crud;
import Clases.Modelos.Bodega;
import Clases.Modelos.TipoDeProducto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModificarTipoDeProducto_Controlador implements Initializable {
    @FXML private TextField modificar_tipo_txtfl_id;
    @FXML private TextField modificar_tipo_txtfl_tipoproducto;
    @FXML private TextArea midificar_tipo_txtarea_descripcion;
    @FXML private Button modificar_tipo_btn_eliminar;
    @FXML private Button modificar_tipo_btn_guardar;
    @FXML private Button modificar_tipo_btn_cancelar;
    private int id;
    private String tipo_de_producto;
    private String descripcion;
    private TipoDeProductos_Crud tipoDeProductosCrud = new TipoDeProductos_Crud();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void eliminarTipoProducto(){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar "+tipo_de_producto);
        confirmacion.setHeaderText("Eliminar elemento.");
        confirmacion.setContentText("¿Desea continuar?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if(resultado.get() == ButtonType.OK) {
            tipoDeProductosCrud.borrarTipoDeProducto(new TipoDeProducto(id,tipo_de_producto, descripcion));
            cancelar();
            JOptionPane.showMessageDialog(null, "El elemento '"+tipo_de_producto+"' ha sido eliminado correctamente.");
        }else{
            JOptionPane.showMessageDialog(null,"No se han efectuado cambios.");
        }
    }

    public void actualizarTipoDeProducto(){
        if(validarDatos()){
            tipoDeProductosCrud.actualizarTipoDeProducto(new TipoDeProducto(tipo_de_producto, descripcion));
            cancelar();
            JOptionPane.showMessageDialog(null, "Los datos han sido actualizados correctamente.");
        }else {
            JOptionPane.showMessageDialog(null,"Todos los campos son necesarios para actualizar.");
        }
    }

    public void recibirDatos(TipoDeProducto tipoDeProducto){
        id = tipoDeProducto.getId();
        tipo_de_producto = tipoDeProducto.getTipoDeProducto();
        descripcion = tipoDeProducto.getDescripcion();

        modificar_tipo_txtfl_id.setText(Integer.toString(id));
        modificar_tipo_txtfl_tipoproducto.setText(tipo_de_producto);
        midificar_tipo_txtarea_descripcion.setText(descripcion);
    }

    public void cancelar(){
        Stage stage = (Stage) modificar_tipo_btn_cancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarDatos(){
        boolean datosValidos = true;
        if(modificar_tipo_txtfl_id.getText().isEmpty()){
            datosValidos = false;
        }
        if (modificar_tipo_txtfl_tipoproducto.getText().isEmpty()){
            datosValidos = false;
        }else{
            tipo_de_producto =  modificar_tipo_txtfl_tipoproducto.getText();
        }
        if (midificar_tipo_txtarea_descripcion.getText().isEmpty()){
            datosValidos = false;
        }else{
            descripcion = midificar_tipo_txtarea_descripcion.getText();
        }
        return datosValidos;
    }
}
