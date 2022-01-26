module com.example.hotel_ishiraku {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;

    opens com.example.hotel_ishiraku to javafx.fxml;
    exports com.example.hotel_ishiraku;
    exports com.example.hotel_ishiraku.disponibilite;
    opens com.example.hotel_ishiraku.disponibilite to javafx.fxml;
    exports com.example.hotel_ishiraku.client;
    opens com.example.hotel_ishiraku.client to javafx.fxml;
    opens com.example.hotel_ishiraku.lavage to javafx.fxml;
    exports com.example.hotel_ishiraku.lavage;
    exports com.example.hotel_ishiraku.sommaire;
    opens com.example.hotel_ishiraku.sommaire to javafx.fxml;
    exports com.example.hotel_ishiraku.login;
    opens com.example.hotel_ishiraku.login to javafx.fxml;
    exports com.example.hotel_ishiraku.reservation_place;
    opens com.example.hotel_ishiraku.reservation_place to javafx.fxml;
}