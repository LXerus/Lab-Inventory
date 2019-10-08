package Clases.Models;


import Clases.Cruds.ProductCrud;

import java.time.LocalDate;

public class Product {
    private ProductCrud productosCrud = new ProductCrud();
    private int id;
    private String name;
    private String brand;
    private String cas;
    private String internalCode;
    private String standardCode;
    private String lot;
    private LocalDate entryDate;
    private LocalDate expiryDate;
    private LocalDate invoiceDate;
    private LocalDate openDate;
    private String invoiceNumber;
    private double stock;
    private double cost;
    private double costPerUnit;
    private boolean controlledProduct;
    private int ghs;
    private String presentation;
    private int cellarID;
    private int providerID;
    private int productTypeID;
    private int registryID;
    private String cellar; //Atributo cuya funcion es almacenar el nombre correscpondiente a idBodega
    private String provider;//Atributo cuya funcion es almacenar el nombre correscpondiente a idProveedor
    private String productType;//Atributo cuya funcion es almacenar el nombre correscpondiente a id_tipo_de_producto
    private String registry;//Atributo cuya funcion es almacenar el nombre correscpondiente a idRegistro

    public Product(int id, String name, String brand, String cas, String internalCode, String standardCode, String lot, LocalDate entryDate, LocalDate expiryDate, LocalDate invoiceDate, LocalDate openDate, String invoiceNumber, double stock, double cost, double costPerUnit, boolean controlledProduct, int ghs, String presentation,  int cellarID, int providerID, int productTypeID, int registryID) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.cas = cas;
        this.internalCode = internalCode;
        this.standardCode = standardCode;
        this.lot = lot;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.invoiceDate = invoiceDate;
        this.openDate = openDate;
        this.invoiceNumber = invoiceNumber;
        this.stock = stock;
        this.cost = cost;
        this.costPerUnit = costPerUnit;
        this.controlledProduct = controlledProduct;
        this.ghs = ghs;
        this.presentation = presentation;
        this.cellarID = cellarID;
        this.providerID = providerID;
        this.productTypeID = productTypeID;
        this.registryID = registryID;
        this.provider = productosCrud.getProvider(providerID).getName();
        this.cellar = productosCrud.getCellar(cellarID).getName();
        this.productType = productosCrud.getProductType(productTypeID).getProductType();
        this.registry = productosCrud.getRegistry(registryID).getName();
    }

    public Product(String name, String brand, String cas, String internalCode, String standardCode, String lot, LocalDate entryDate, LocalDate expiryDate, LocalDate invoiceDate, LocalDate openDate, String invoiceNumber, double stock, double cost, double costPerUnit, boolean controlledProduct, int ghs, String presentation, int cellarID, int providerID, int productTypeID, int registryID) {
        this.name = name;
        this.brand = brand;
        this.cas = cas;
        this.internalCode = internalCode;
        this.standardCode = standardCode;
        this.lot = lot;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.invoiceDate = invoiceDate;
        this.openDate = openDate;
        this.invoiceNumber = invoiceNumber;
        this.stock = stock;
        this.cost = cost;
        this.costPerUnit = costPerUnit;
        this.controlledProduct = controlledProduct;
        this.ghs = ghs;
        this.presentation = presentation;
        this.cellarID = cellarID;
        this.providerID = providerID;
        this.productTypeID = productTypeID;
        this.registryID = registryID;
        this.provider = productosCrud.getProvider(providerID).getName();
        this.cellar = productosCrud.getCellar(cellarID).getName();
        this.productType = productosCrud.getProductType(productTypeID).getProductType();
        this.registry = productosCrud.getRegistry(registryID).getName();
    }

    public Product(String name, String brand, String cas, String internalCode, String standardCode, String lot, LocalDate entryDate, LocalDate expiryDate, String invoiceNumber, int cellarID, int providerID) {
        this.name = name;
        this.brand = brand;
        this.cas = cas;
        this.internalCode = internalCode;
        this.standardCode = standardCode;
        this.lot = lot;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.invoiceNumber = invoiceNumber;
        this.cellarID = cellarID;
        this.providerID = providerID;
        if(providerID != 0) {
            this.provider = productosCrud.getProvider(providerID).getName();
        }
        if(cellarID != 0) {
            this.cellar = productosCrud.getCellar(cellarID).getName();
        }
    }



    public int getRegistryID() {
        return registryID;
    }

    public void setRegistryID(int registryID) {
        this.registryID = registryID;
    }

    public int getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public void setControlledProduct(boolean controlledProduct) {
        this.controlledProduct = controlledProduct;
    }

    public void setGhs(int ghs) {
        this.ghs = ghs;
    }

    public boolean isControlledProduct() {
        return controlledProduct;
    }

    public int getGhs() {
        return ghs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public int getCellarID() {
        return cellarID;
    }

    public void setCellarID(int cellarID) {
        this.cellarID = cellarID;
    }

    public String getCellar() {
        return cellar;
    }

    public void setCellar(String cellar) {
        this.cellar = cellar;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }
}


