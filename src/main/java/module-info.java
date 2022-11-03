module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.chesschess to javafx.fxml;
    exports com.chesschess;
}
