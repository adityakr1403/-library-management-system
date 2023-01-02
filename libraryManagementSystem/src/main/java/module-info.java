module libraryManagementSystem {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens com.example.librarymanagementsystem to javafx.fxml;
    opens com.example.controllers to javafx.fxml;
    opens com.example.entity to org.hibernate.orm.core;

    exports com.example.librarymanagementsystem;
    exports com.example.entity;
    exports com.example.controllers;
    exports com.example.utils;

}
