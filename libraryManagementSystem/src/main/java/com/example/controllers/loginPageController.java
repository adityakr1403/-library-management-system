package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class loginPageController {
    @FXML
    TextField userNameTextField;

    @FXML
    PasswordField userPasswordField;

    @FXML
    Button loginButton;

    @FXML
    CheckBox showPasswordCheckBox;

    @FXML
    Label showPasswordLabel;


    private Stage stage;
    private Parent root;
    private Scene scene;
    public void login(ActionEvent event) throws IOException {

        String userName = userNameTextField.getText();
        String password = userPasswordField.getText();

        if (!userName.equals("admin")|| !password.equals("admin")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("CAUTION!");
            alert.setContentText("Wrong Username or Password!");

            alert.showAndWait();
            return;
        }

        System.out.println(userName + " : " + password);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/librarymanagementsystem/libraryInchargePage.fxml"));
        root = loader.load();

//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene2.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void showPassword(ActionEvent event) {
        boolean isSelected =  showPasswordCheckBox.isSelected();
        if (isSelected) {
            showPasswordLabel.setText(userPasswordField.getText());
        }else {
            showPasswordLabel.setText("");
        }
    }


    public void userPasswordFieldTextChange(KeyEvent keyEvent) {
        if (showPasswordCheckBox.isSelected()){
            showPasswordLabel.setText(userPasswordField.getText());
        }else {
            showPasswordLabel.setText(" ");
        }
    }
}