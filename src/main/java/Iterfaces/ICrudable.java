package Iterfaces;

import javafx.collections.ObservableList;

public interface ICrudable {

    void create(Object object);

    ObservableList read(Object object);

    void update(Object object);

    void delete(Object object);
}
