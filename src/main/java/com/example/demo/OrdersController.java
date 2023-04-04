package com.example.demo;

import DAO.CommandeDAO;
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
import model.Commande;
import model.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.EventObject;
import java.util.ResourceBundle;


public class OrdersController implements Initializable {
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


    //////////////////////////////////////////////////////

    @FXML
    private TextField id_produit ;
    @FXML
    private TextField id_livreur ;
    @FXML
    private DatePicker date_debut ;
    @FXML
    private DatePicker date_fin ;
    @FXML
    private TableView<Commande> mytable ;
    @FXML
    private TableColumn<Commande ,Long> col_id ;
    @FXML
    private TableColumn<Commande ,Long> col_id_produit ;
    @FXML
    private TableColumn<Commande ,Long> col_id_livreur ;
    @FXML
    private TableColumn <Commande , LocalDate> col_date_debut ;
    @FXML
    private TableColumn <Commande ,LocalDate> col_date_fin ;

    @FXML
    protected void onSaveButtonClick() throws SQLException {

        // accees a la bdd
        if (id_livreur.getText().isEmpty() || id_produit.getText().isEmpty()|| date_debut.getValue()==null || date_fin.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            CommandeDAO commandeDAO = new CommandeDAO();

            if (mytable.getSelectionModel().isEmpty()) {
                try {

                    Commande com = new Commande(0l, Long.parseLong(id_produit.getText()), Long.parseLong(id_livreur.getText()) , date_debut.getValue() , date_fin.getValue());

                    commandeDAO.save(com);


                    UpdateTable();

                    id_livreur.setText("");
                    id_produit.setText("");
                    date_debut.setValue(null);
                    date_fin.setValue(null);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Commande com = new Commande();
                com.setId_commande(mytable.getSelectionModel().getSelectedItem().getId_commande());
                com.setId_produit(Long.parseLong(id_produit.getText()));
                com.setId_livreur(Long.parseLong(id_livreur.getText()));
                com.setDate_debut(date_debut.getValue());
                com.setDate_fin(date_fin.getValue());

                commandeDAO.update(com);
                UpdateTable();

                id_livreur.setText("");
                id_produit.setText("");
                date_debut.setValue(null);
                date_fin.setValue(null);


            }
        }


    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        col_id_produit.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_produit"));
        col_id_livreur.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_livreur"));
        col_date_debut.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("date_debut"));
        col_date_fin.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("date_fin"));
        mytable.setItems(this.getDataCommandes());
    }
    public static ObservableList<Commande> getDataCommandes(){

        CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande ettemp : commandeDAO.getAll())
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
        CommandeDAO commandeDAO  = new CommandeDAO();
        commandeDAO.delete(mytable.getSelectionModel().getSelectedItem());
        UpdateTable();
    }



    public  Commande selectedRow() throws SQLException {
        CommandeDAO commandeDAO  = new CommandeDAO();
        Commande commande = mytable.getSelectionModel().getSelectedItem();
        id_produit.setText(String.valueOf(commande.getId_produit()));
        id_livreur.setText(String.valueOf(commande.getId_livreur()));
        date_debut.setValue(commande.getDate_debut());
        date_fin.setValue(commande.getDate_fin());
        return commande;
    }




}