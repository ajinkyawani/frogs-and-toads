module com.mycompany.frogsandtoads {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.frogsandtoads to javafx.fxml;
    exports com.mycompany.frogsandtoads;
}