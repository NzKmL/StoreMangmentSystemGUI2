module pl.nzkml {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;

    opens pl.nzkml.views to javafx.fxml;
    opens pl.nzkml.controllers to javafx.fxml;
    exports pl.nzkml.controllers;
    exports pl.nzkml;
}
