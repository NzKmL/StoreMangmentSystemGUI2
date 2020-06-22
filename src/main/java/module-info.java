module pl.nzkml {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.core;

    opens pl.nzkml.views to javafx.fxml;
    opens pl.nzkml.controllers to javafx.fxml;
    opens pl.nzkml.datasource.entity.users to com.fasterxml.jackson.databind;
    exports pl.nzkml.controllers;
    exports pl.nzkml;
    exports pl.nzkml.datasource.xml.file;

}
