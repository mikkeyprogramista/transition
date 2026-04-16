module com.adz1q.transition {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adz1q.transition to javafx.fxml;
    exports com.adz1q.transition;
}