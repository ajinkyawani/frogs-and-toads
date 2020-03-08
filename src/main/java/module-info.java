module com.mycompany.frogsandtoads {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.frogsandtoads to javafx.fxml;
    exports com.mycompany.frogsandtoads;
}