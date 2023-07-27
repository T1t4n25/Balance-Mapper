module com.zeyadh.balancemapper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.zeyadh.balancemapper to javafx.fxml;
    exports com.zeyadh.balancemapper;
}