package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Models.*;
import Iterfaces.ICrudable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProductCrud implements ICrudable {
    //Clase encargada de todas las conexiones con la base de datos para la tabla productos.

    @Override
    public void create(Object object) {
        Product product = (Product) object;
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        String sqlQuery = "INSERT INTO productos(nombre, marca, cas, codigo_interno, codigo_standard, lote, fecha_ingreso, fecha_vence, " +
                "fecha_abierto, fecha_factura, factura, stock, costo, producto_controlado, ghs, presentacion,  id_bodega, id_proveedor, " +
                "id_tipo_producto, id_registro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setString(3, product.getCas());
            preparedStatement.setString(4, product.getInternalCode());
            preparedStatement.setString(5, product.getStandardCode());
            preparedStatement.setString(6, product.getLot());
            preparedStatement.setDate(7, Date.valueOf(product.getEntryDate()));
            preparedStatement.setDate(8, Date.valueOf(product.getExpiryDate()));
            preparedStatement.setDate(9, Date.valueOf(product.getOpenDate()));
            preparedStatement.setDate(10, Date.valueOf(product.getInvoiceDate()));
            preparedStatement.setString(11, product.getInvoiceNumber());
            preparedStatement.setDouble(12, product.getStock());
            preparedStatement.setDouble(13, product.getCost());
            preparedStatement.setBoolean(14, product.isControlledProduct());
            preparedStatement.setInt(15, product.getGhs());
            preparedStatement.setString(16, product.getPresentation());
            preparedStatement.setInt(17, product.getCellarID());
            preparedStatement.setInt(18, product.getProviderID());
            preparedStatement.setInt(19, product.getProductTypeID());
            preparedStatement.setInt(20, product.getRegistryID());
            preparedStatement.executeUpdate();

            activity.registerActivity(activity.REGISTER, "Producto");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<Product> read(Object object) {
        Product product = (Product) object;
        String sqlQuery = "SELECT id, nombre, marca, cas, codigo_interno, codigo_standard, lote, fecha_ingreso, fecha_vence, fecha_abierto," +
                " fecha_factura, factura, stock, costo, costo_x_unidad, producto_controlado, ghs, presentacion, id_bodega, id_proveedor, id_tipo_producto," +
                " id_registro FROM productos WHERE ";
        ObservableList<Product> productList = FXCollections.observableArrayList();
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();

        if (!product.getName().isEmpty()) {
            sqlQuery += "nombre LIKE '%" + product.getName() + "%' AND ";
        }

        if (!product.getBrand().isEmpty()) {
            sqlQuery += "marca LIKE '%" + product.getBrand() + "%' AND ";
        }

        if (!product.getLot().isEmpty()) {
            sqlQuery += "lote LIKE '%" + product.getLot() + "%' AND ";
        }

        if (!product.getInvoiceNumber().isEmpty()) {
            sqlQuery += "factura LIKE '%" + product.getInvoiceNumber() + "%' AND ";
        }

        if (!product.getCas().isEmpty()) {
            sqlQuery += "cas LIKE '%" + product.getCas() + "%' AND ";
        }

        if (!product.getInternalCode().isEmpty()) {
            sqlQuery += "codigo_interno LIKE '%" + product.getInternalCode() + "%' AND ";
        }

        if (!product.getStandardCode().isEmpty()) {
            sqlQuery += "codigo_standard LIKE '%" + product.getStandardCode() + "%' AND ";
        }

        if (product.getId() != 0) {
            sqlQuery += "id_proveedor = " + product.getProviderID() + " AND ";
        }

        if (product.getCellarID() != 0) {
            sqlQuery += "id_bodega = " + product.getCellarID() + " AND ";
        }

        if (!(product.getEntryDate() == null)) {
            sqlQuery += "fecha_ingreso=" + product.getEntryDate().toString() + " AND ";
        }

        if (!(product.getExpiryDate() == null)) {
            sqlQuery += "fecha_vence=" + product.getExpiryDate().toString() + " AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String cleanQuery = "";

        for (int i = 0; i < stringToArray.length - 4; i++) {
            cleanQuery = cleanQuery + stringToArray[i];
        }

        sqlQuery = cleanQuery;
        try {
            Statement statementProduct = connection.createStatement();
            ResultSet resultSetProduct = statementProduct.executeQuery(sqlQuery);
            while (resultSetProduct.next()) {
                int id = resultSetProduct.getInt("id");
                String name = resultSetProduct.getString("nombre");
                String brand = resultSetProduct.getString("marca");
                String cas = resultSetProduct.getString("cas");
                String internalCode = resultSetProduct.getString("codigo_interno");
                String standardCode = resultSetProduct.getString("codigo_standard");
                String lot = resultSetProduct.getString("lote");
                LocalDate entryDate = resultSetProduct.getDate("fecha_ingreso").toLocalDate();
                LocalDate expiryDate = resultSetProduct.getDate("fecha_vence").toLocalDate();
                LocalDate openDate = resultSetProduct.getDate("fecha_abierto").toLocalDate();
                LocalDate invoiceDate = resultSetProduct.getDate("fecha_factura").toLocalDate();
                String invoice = resultSetProduct.getString("factura");
                double stock = resultSetProduct.getInt("stock");
                double cost = resultSetProduct.getDouble("costo");
                double costPerUnit = resultSetProduct.getDouble("costo_x_unidad");
                Boolean controlledProduct = resultSetProduct.getBoolean("producto_controlado");
                int ghs = resultSetProduct.getInt("ghs");
                String presentation = resultSetProduct.getString("presentacion");
                int idCellar = resultSetProduct.getInt("id_bodega");
                int idProvider = resultSetProduct.getInt("id_proveedor");
                int idProductType = resultSetProduct.getInt("id_tipo_producto");
                int idRegistry = resultSetProduct.getInt("id_registro");
                productList.add(new Product(
                        id, name, brand, cas, internalCode, standardCode, lot, entryDate, expiryDate, openDate, invoiceDate,
                        invoice, stock, cost, costPerUnit, controlledProduct, ghs, presentation, idCellar, idProvider, idProductType, idRegistry
                ));
            }
            activity.registerActivity(activity.SEARCH, "Producto");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productList;
    }

    public void updateStock(int idProducto, double consumo) {
        String sqlQuery = "UPDATE productos SET stock=? WHERE id=" + idProducto;
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setDouble(1, consumo);
            preparedStatement.executeUpdate();
            activity.registerActivity("Actualizar Stock", "Producto");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {
        Product product = (Product) object;
        String sqlQuery = "DELETE FROM bodega Where id = ?";
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();

            activity.registerActivity(activity.DELETE, "Bodegas");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //*****************************************************Listas para los ComboBox para los formularios de productos********************************************

    public ObservableList<String> getCellarList() {
        ObservableList<String> cellarList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT nombre, condicion, region, tramo FROM bodega";
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String condition = resultSet.getString(2);
                String region = resultSet.getString(3);
                String section = resultSet.getString(4);
                String cell = name + "  |  " + condition + "  |  " + region + "  |  " + section;
                cellarList.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cellarList;
    }

    public ObservableList<String> getProductList() {
        String product_type;
        ObservableList<String> productList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT tipo_de_producto FROM tipo_producto";
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                product_type = resultSet.getString("tipo_de_producto");
                productList.add(product_type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productList;
    }

    public ObservableList<String> getRegistryNumbers() {
        String registry_name;
        ObservableList<String> registryList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT nombre FROM registro";
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                registry_name = resultSet.getString(1);
                String cell = registry_name;
                registryList.add(cell);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registryList;
    }

    public ObservableList<Provider> getProviderList() {

        ObservableList<Provider> providersList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion FROM " + Configuration.getDataBase() + ".proveedores";
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String telephone = resultSet.getString(3);
                String contact = resultSet.getString(4);
                String providerCode = resultSet.getString(5);
                String service = resultSet.getString(6);
                boolean critical = resultSet.getBoolean(7);
                boolean approved = resultSet.getBoolean(8);
                int rating = resultSet.getInt(9);
                LocalDate approvalDate = resultSet.getDate(10).toLocalDate();
                LocalDate revalidationDate = resultSet.getDate(11).toLocalDate();
                providersList.add(new Provider(id, name, telephone, contact, providerCode, service, rating, critical, approved, approvalDate, revalidationDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return providersList;
    }


    //************************************************Estas funciones son encargadas de obtener los IDs de las clases seleccionadas cuando se registra un producto************************************************

    public int getProviderID(String providerName) { //Funcion que lee el nombre del proveedor seleccionado y encuentra su id en la base de datos
        int providerID = 0;
        String sqlQuery = "SELECT id FROM proveedores WHERE nombre ='" + providerName + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                providerID = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return providerID;
    }

    public int getCellarID(String cellarName) {//Funcion que lee el nombre de la bodega seleccionada y encuentra su id en la base de datos
        int cellarID = 0;
        String sqlQuery = "SELECT id FROM bodega WHERE nombre = '" + cellarName + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                cellarID = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cellarID;
    }

    public int getProductTypeID(String productType) {//Funcion que lee el tipo de producto seleccionado y encuentra su id en la base de datos
        int productTypeID = 0;
        String sqlQuery = "SELECT id FROM tipo_producto WHERE tipo_de_producto = '" + productType + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                productTypeID = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productTypeID;
    }

    public int getPresentationID(String presentation) {//Funcion que lee el nombre de la presentacion seleccionada y encuentra su id en la base de datos
        int presentationID = 0;
        String sqlQuery = "SELECT id FROM presentaciones WHERE presentacion = '" + presentation + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                presentationID = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return presentationID;
    }

    public int getRegistryID(String registry) {//Funcion que lee el registro seleccionado y encuentra su id en la base de datos
        int registryID = 0;
        String sqlQuery = "SELECT id FROM registro WHERE nombre = '" + registry + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                registryID = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registryID;
    }
    //******************************************************************************************************************************************************************************

    //************************************************Estas funciones son encargadas de obtener los objetos correspondiente a los ID************************************************
    //
    public Provider getProvider(int providerID) {
        Provider provider = null;
        String sqlQuery = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion " +
                "FROM proveedores WHERE id = " + providerID;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String telephone = resultSet.getString("telefono");
                String contact = resultSet.getString("contacto");
                String providerCode = resultSet.getString("codigo_de_proveedor");
                String service = resultSet.getString("servicio");
                boolean critical = resultSet.getBoolean("critico");
                boolean approved = resultSet.getBoolean("aprobado");
                int rating = resultSet.getInt("punteo");
                LocalDate approvalDate = resultSet.getDate("fecha_aprobacion").toLocalDate();
                LocalDate revalidationDate = resultSet.getDate("fecha_revalidacion").toLocalDate();

                provider = new Provider(id, name, telephone, contact, providerCode, service, rating, critical, approved, approvalDate, revalidationDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return provider;
    }

    public Cellar getCellar(int cellarID) {
        Cellar cellar = null;
        String sqlQuery = "SELECT id, nombre, condicion, region, tramo FROM bodega WHERE id = " + cellarID;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String condition = resultSet.getString("condicion");
                String region = resultSet.getString("region");
                String section = resultSet.getString("tramo");

                cellar = new Cellar(id, name, condition, region, section);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cellar;
    }

    public ProductType getProductType(int productTypeID) {
        ProductType productType = null;
        String sqlQuery = "SELECT id, tipo_de_producto, descripcion FROM tipo_producto WHERE id = " + productTypeID;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String product_type = resultSet.getString("tipo_de_producto");
                String description = resultSet.getString("descripcion");

                productType = new ProductType(id, product_type, description);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productType;
    }

    public Presentation getPresentation(int presentationID) {
        Presentation presentation = null;
        String sqlQuery = "SELECT id, presentacion, unidad_medida FROM presentaciones WHERE id = " + presentationID;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String presentation_re = resultSet.getString("presentacion");
                String unit = resultSet.getString("unidad_medida");


                presentation = new Presentation(id, presentation_re, unit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return presentation;
    }

    public Registry getRegistry(int registryID) {
        Registry registry = null;
        String sqlQuery = "SELECT id, nombre, descripcion FROM registro WHERE id = " + registryID;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String description = resultSet.getString("descripcion");

                registry = new Registry(id, name, description);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registry;
    }

    //**********************************************************************************************************************************************************************************************
    JDBConnection JDBConnection;
    Connection connection;
    Configuration configuration = new Configuration();
    private ObservableList<Cellar> productList;
    private PreparedStatement preparedStatement;
    private UserActivity activity = new UserActivity();
}
