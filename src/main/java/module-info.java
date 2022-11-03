module se.iths.java22.labb3.labb3williamkarlstrom {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.java22.labb3.labb3williamkarlstrom to javafx.fxml;
    exports se.iths.java22.labb3.labb3williamkarlstrom;
    exports se.iths.java22.labb3.labb3williamkarlstrom.controller;
    opens se.iths.java22.labb3.labb3williamkarlstrom.controller to javafx.fxml;
    exports se.iths.java22.labb3.labb3williamkarlstrom.model;
    opens se.iths.java22.labb3.labb3williamkarlstrom.model to javafx.fxml;
}