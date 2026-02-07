module com.gbjoanna.rocodromo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.crypto;
    requires javafx.base;


    opens com.gbjoanna.rocodromo to javafx.fxml;
    exports com.gbjoanna.rocodromo;
}