package DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Livreur;
import model.Produit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitDAO extends BaseDAO<Produit> {

    public ProduitDAO() throws SQLException {
    }

    @Override
    public void save(Produit object) throws SQLException {
        String request = "insert into produit (libelle , description , prix , onStock) values (? , ? ,? , ?)";
        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getLibelle());

        this.preparedStatement.setString(2, object.getDescription());

        this.preparedStatement.setDouble(3, object.getPrix());

        this.preparedStatement.setInt(4, object.getOnStock());

        this.preparedStatement.execute();

    }

    @Override
    public void update(Produit object) throws SQLException {
        String UpdateRequest = "UPDATE produit SET libelle=?, description=?, prix=?, onStock=? where id_produit=?";

        this.preparedStatement = this.connection.prepareStatement(UpdateRequest);

        this.preparedStatement.setString(1, object.getLibelle());

        this.preparedStatement.setString(2, object.getDescription());

        this.preparedStatement.setDouble(3, object.getPrix());

        this.preparedStatement.setInt(4, object.getOnStock());

        this.preparedStatement.setLong(5,object.getId_produit());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Produit object) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE produit ID: " + object.getId_produit() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            String request = "DELETE FROM produit WHERE id_produit=?";

            // mapping objet table

            this.preparedStatement = this.connection.prepareStatement(request);
            // mapping
            this.preparedStatement.setLong(1, object.getId_produit());

            this.preparedStatement.execute();

        }
    }






        @Override
        public List<Produit> getAll()  throws SQLException {

            List<Produit> mylist = new ArrayList<Produit>();

            String request = "select * from produit";

            this.statement = this.connection.createStatement();

            this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
            while ( this.resultSet.next()){

// mapping table objet
                mylist.add(new Produit(this.resultSet.getLong(1) , this.resultSet.getString(2),
                        this.resultSet.getString(3) , this.resultSet.getDouble(4) , this.resultSet.getInt(5)));
            }


            return mylist;
        }

    public Produit getOne(Long id) throws SQLException {
        return null;
    }



    }
