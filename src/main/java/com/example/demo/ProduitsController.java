package com.example.demo;

import DAO.LivreurDAO;
import DAO.ProduitDAO;
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
import model.Livreur;
import model.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;


public class ProduitsController implements Initializable {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private EventObject event;

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

    public void onLogoutClick(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Logout?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    //////////////////////////////////////////////////////

    @FXML
    private TextField libelle ;
    @FXML
    private TextField description ;
    @FXML
    private TextField prix ;
    @FXML
    private TextField onStock ;
    @FXML
    private TableView<Produit> mytable ;
    @FXML
    private TableColumn<Produit ,Long> col_id ;
    @FXML
    private TableColumn <Produit ,String> col_libelle ;
    @FXML
    private TableColumn <Produit ,String> col_description ;
    @FXML
    private TableColumn <Produit ,Double> col_prix ;
    @FXML
    private TableColumn <Produit ,Integer> col_onStock ;

    @FXML
    protected void onSaveButtonClick() throws SQLException {

        // accees a la bdd
        if (libelle.getText().isEmpty() || description.getText().isEmpty()|| prix.getText().isEmpty() || onStock.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            ProduitDAO produitDAO = new ProduitDAO();

            if (mytable.getSelectionModel().isEmpty()) {
                try {

                    Produit prod = new Produit(0l, libelle.getText(), description.getText() , Double.valueOf(prix.getText()), Integer.valueOf(onStock.getText()));

                    produitDAO.save(prod);


                    UpdateTable();

                    libelle.setText("");
                    description.setText("");
                    prix.setText("");
                    onStock.setText("");


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Produit prod = new Produit();
                prod.setId_produit(mytable.getSelectionModel().getSelectedItem().getId_produit());
                prod.setLibelle(libelle.getText());
                prod.setDescription(description.getText());
                prod.setPrix(Double.valueOf(prix.getText()));
                prod.setOnStock(Integer.valueOf(onStock.getText()));

                produitDAO.update(prod);
                UpdateTable();

                libelle.setText("");
                description.setText("");
                prix.setText("");
                onStock.setText("");

            }
        }


    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_libelle.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
        col_onStock.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("onStock"));
        mytable.setItems(this.getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit ettemp : produitDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
    @FXML
    public void deleteSelectedRow() throws SQLException{
        ProduitDAO produitDAO  = new ProduitDAO();
        produitDAO.delete(mytable.getSelectionModel().getSelectedItem());
        UpdateTable();
    }

    public  Produit selectedRow() throws SQLException {
        ProduitDAO produitDAO  = new ProduitDAO();
        Produit produit = mytable.getSelectionModel().getSelectedItem();
        libelle.setText(String.valueOf(produit.getLibelle()));
        description.setText(produit.getDescription());
        prix.setText(String.valueOf(produit.getPrix()));
        onStock.setText(String.valueOf(produit.getOnStock()));
        return produit;
    }







}