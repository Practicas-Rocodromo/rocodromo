module com.gbjoanna.rocodromo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.crypto;


    opens com.gbjoanna.rocodromo to javafx.fxml;
    exports com.gbjoanna.rocodromo;
}