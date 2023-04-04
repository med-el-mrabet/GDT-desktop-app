package DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Livreur;
import model.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivreurDAO extends BaseDAO<Livreur> {
    public LivreurDAO() throws SQLException {

        super();
    }
    @Override
    public void save(Livreur object) throws SQLException {

        String request = "insert into livreur (nom , telephone) values (? , ?)";
            // mapping objet table

            this.preparedStatement = this.connection.prepareStatement(request);
            // mapping
            this.preparedStatement.setString(1, object.getNom());

            this.preparedStatement.setString(2, object.getTelephone());


            this.preparedStatement.execute();

    }

    @Override
    public void update(Livreur object) throws SQLException {

        String UpdateRequest = "UPDATE livreur SET nom=?, telephone=? where id_livreur=?";

        this.preparedStatement=this.connection.prepareStatement(UpdateRequest);
        this.preparedStatement.setString(1,object.getNom());
        this.preparedStatement.setString(2,object.getTelephone());
        this.preparedStatement.setLong(3,object.getId_livreur());
        this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Livreur object) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE delevry man ID: " + object.getId_livreur() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            String request = "DELETE FROM livreur WHERE id_livreur=?";

            // mapping objet table

            this.preparedStatement = this.connection.prepareStatement(request);
            // mapping
            this.preparedStatement.setLong(1, object.getId_livreur());

            this.preparedStatement.execute();
        }


    }

    @Override
    public List<Livreur> getAll()  throws SQLException {

        List<Livreur> mylist = new ArrayList<Livreur>();

        String request = "select * from livreur ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Livreur(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));


        }


        return mylist;
    }

    @Override
    public Livreur getOne(Long id) throws SQLException {
            String UpdateRequest = "SELECT * FROM livreur WHERE id_livreur=?";

        this.preparedStatement=this.connection.prepareStatement(UpdateRequest);
        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        return new Livreur(this.resultSet.getLong(1) ,  this.resultSet.getString(2) , this.resultSet.getString(3));
    }
}
