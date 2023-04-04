package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;


public class HomeController  {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private EventObject event;

    @FXML
    Text deleverymenNum;

    public void DMNUM(Integer DMNUM)
    {
        deleverymenNum.setText(String.valueOf(DMNUM));
    }

    @FXML
    public void onDashboardClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void onDeliverymanClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Deliveryman.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void onOrdersClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void onProductsClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //////////////////////////////////////////////////////



}