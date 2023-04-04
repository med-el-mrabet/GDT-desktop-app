package DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Commande;
import model.Livreur;
import model.Produit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.utils.Utils.getSqlDate;

public class CommandeDAO extends BaseDAO<Commande> {


    public CommandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Commande object) throws SQLException {

        String request = "insert into commande (id_produit, id_livreur ,date_debut, date_fin) values (? ,? ,? , ?)";
        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setLong(1, object.getId_produit());

        this.preparedStatement.setLong(2, object.getId_livreur());

        this.preparedStatement.setDate(3, java.sql.Date.valueOf(object.getDate_debut()));

        this.preparedStatement.setDate(4, java.sql.Date.valueOf(object.getDate_fin()));


        this.preparedStatement.execute();

    }

    @Override
    public void update(Commande object) throws SQLException {
        String UpdateRequest = "UPDATE commande SET id_produit=?, id_livreur=? ,date_debut=?, date_fin=? WHERE id_commande=?";

        this.preparedStatement=this.connection.prepareStatement(UpdateRequest);
        this.preparedStatement.setLong(1, object.getId_produit());

        this.preparedStatement.setLong(2, object.getId_livreur());

        this.preparedStatement.setDate(3, java.sql.Date.valueOf(object.getDate_debut()));

        this.preparedStatement.setDate(4, java.sql.Date.valueOf(object.getDate_fin()));
        this.preparedStatement.setLong(5, object.getId_commande());
        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Commande object) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Order ID: " + object.getId_commande() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            String request = "DELETE FROM commande WHERE id_commande=?";

            // mapping objet table

            this.preparedStatement = this.connection.prepareStatement(request);
            // mapping
            this.preparedStatement.setLong(1, object.getId_commande());

            this.preparedStatement.execute();
        }

    }


    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> mylist = new ArrayList<Commande>();

        String request = "select * from commande ";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(request);

// parcours de la table
        while (this.resultSet.next()) {

// mapping table objet
            mylist.add(new Commande(this.resultSet.getLong(1), this.resultSet.getLong(2), this.resultSet.getLong(3),
                    this.resultSet.getDate(4).toLocalDate(), this.resultSet.getDate(5).toLocalDate()));

        }
        return mylist;

    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }
}
