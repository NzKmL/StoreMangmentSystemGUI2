module pl.nzkml {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.nzkml.views to javafx.fxml;
    opens pl.nzkml.controllers to javafx.fxml;
    exports pl.nzkml.controllers;
    exports pl.nzkml;
}
