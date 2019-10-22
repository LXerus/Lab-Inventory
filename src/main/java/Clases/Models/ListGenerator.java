package Clases.Models;

import Clases.BaseDeDatos.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ListGenerator {
    private ObservableList<String> presentationListMass = FXCollections.observableArrayList();
    private ObservableList<String> presentationListVolume = FXCollections.observableArrayList();
    DBConnection JDBDBConnection;
    Connection connection;
    Configuration configuration = new Configuration();
    private PreparedStatement preparedStatement;
    UserActivity activity;

    public ListGenerator() {
        generatePresentationLists();
    }


    public ObservableList<String> getPresentationListMass() {
        return presentationListMass;
    }

    public ObservableList<String> getPresentationListVolume() {
        return presentationListVolume;
    }

    private void generatePresentationLists() {
        this.presentationListMass.addAll("kg", "hg", "dag", "g", "dg", "cg", "mg");
        this.presentationListVolume.addAll("kl", "hl", "dal", "L", "dl", "cl", "ml");
    }

}
