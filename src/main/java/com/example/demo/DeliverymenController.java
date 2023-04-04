package com.example.demo;

import DAO.LivreurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Livreur;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;


public class DeliverymenController implements Initializable {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private EventObject event;

    @FXML
    public void onDashboardClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onDeliverymanClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Deliveryman.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onOrdersClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onProductsClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //////////////////////////////////////////////////////


    @FXML
    private TextField nom;


    @FXML
    private TextField tele;
    @FXML
    private TextField idd;

    @FXML
    private TableView<Livreur> mytable;


    @FXML
    private TableColumn<Livreur, Long> col_id;

    @FXML
    private TableColumn<Livreur, String> col_nom;
    @FXML
    private TableColumn<Livreur, String> col_tele;


    @FXML
    protected void onSaveButtonClick() throws SQLException {

        // accees a la bdd
        if (nom.getText().isEmpty() || tele.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            LivreurDAO livreurDAO = new LivreurDAO();

            if (mytable.getSelectionModel().isEmpty()) {
                try {

                    Livreur liv = new Livreur(0l, nom.getText(), tele.getText());

                    livreurDAO.save(liv);


                    UpdateTable();

                    nom.setText("");
                    tele.setText("");


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Livreur livreur = new Livreur();
                livreur.setNom(nom.getText());
                livreur.setTelephone(tele.getText());
                livreur.setId_livreur(mytable.getSelectionModel().getSelectedItem().getId_livreur());
                livreurDAO.update(livreur);
                UpdateTable();
                nom.setText("");
                tele.setText("");

            }
        }


    }

    public Button Delete = new Button("Delete");

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur, Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur, String>("telephone"));


        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs() {

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LivreurDAO livreurDAO = null;
        try {
            livreurDAO = new LivreurDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            int DMNUM = livreurDAO.getAll().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void deleteSelectedRow() throws SQLException {
        LivreurDAO livreurDAO = new LivreurDAO();
        livreurDAO.delete(mytable.getSelectionModel().getSelectedItem());
        UpdateTable();
    }

    @FXML
    public Livreur selectedRow() throws SQLException {
        LivreurDAO livreurDAO = new LivreurDAO();
        Livreur livreur = mytable.getSelectionModel().getSelectedItem();
        nom.setText(String.valueOf(livreur.getNom()));
        tele.setText(livreur.getTelephone());
        idd.setText(String.valueOf(livreur.getId_livreur()));
        return livreur;
    }

}